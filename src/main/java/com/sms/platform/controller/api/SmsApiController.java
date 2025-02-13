package com.sms.platform.controller.api;

import com.sms.platform.common.core.domain.AjaxResult;
import com.sms.platform.domain.SmsSendRequest;
import com.sms.platform.domain.SmsSendResult;
import com.sms.platform.domain.SmsUser;
import com.sms.platform.service.ISmsService;
import com.sms.platform.service.ISmsUserService;
import com.sms.platform.util.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/sms")
public class SmsApiController {
    
    @Autowired
    private ISmsService smsService;
    
    @Autowired
    private ISmsUserService smsUserService;
    
    @PostMapping("/send")
    public AjaxResult send(@RequestBody SmsSendRequest request) {
        try {
            log.info("收到短信发送请求: {}", request);
            
            // 1. 验证签名
            SmsUser smsUser = smsUserService.getByAccessKey(request.getAccessKey());
            if (smsUser == null) {
                return AjaxResult.error("无效的AccessKey");
            }
            
            if (!SignUtils.validateSign(request.getAccessKey(), 
                                     request.getTimestamp(),
                                     request.getNonce(),
                                     request.getSign(),
                                     smsUser.getSecretKey())) {
                return AjaxResult.error("签名验证失败");
            }
            
            // 2. 检查余额
            if (smsUser.getBalance() <= 0) {
                return AjaxResult.error("账户余额不足，请充值");
            }
            
            // 3. 发送短信并计费
            SmsSendResult result = smsService.send(request, smsUser);
            
            // 4. 扣除余额
            if (result.getStatus().equals("success")) {
                smsUserService.deductBalance(smsUser.getId(), result.getFee());
            }
            
            return AjaxResult.success(result);
            
        } catch (Exception e) {
            log.error("短信发送失败", e);
            return AjaxResult.error("短信发送失败：" + e.getMessage());
        }
    }
    
    @GetMapping("/balance")
    public AjaxResult getBalance(@RequestHeader("X-Access-Key") String accessKey,
                                @RequestHeader("X-Timestamp") String timestamp,
                                @RequestHeader("X-Nonce") String nonce,
                                @RequestHeader("X-Sign") String sign) {
        try {
            // 1. 获取用户信息
            SmsUser smsUser = smsUserService.getByAccessKey(accessKey);
            if (smsUser == null) {
                return AjaxResult.error("无效的AccessKey");
            }
            
            // 2. 验证签名
            if (!SignUtils.validateSign(accessKey, timestamp, nonce, sign, smsUser.getSecretKey())) {
                return AjaxResult.error("签名验证失败");
            }
            
            // 3. 返回余额信息
            return AjaxResult.success(smsUserService.getBalanceInfo(smsUser.getId()));
            
        } catch (Exception e) {
            log.error("查询余额失败", e);
            return AjaxResult.error("查询余额失败：" + e.getMessage());
        }
    }
} 