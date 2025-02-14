package com.ruoyi.web.controller.sms;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.sms.domain.SmsUser;
import com.ruoyi.sms.service.ISmsService;
import com.ruoyi.sms.service.ISmsUserService;

@RestController
@RequestMapping("/sms")
public class SmsSendController extends BaseController {

    @Autowired
    private ISmsUserService smsUserService;

    @Autowired
    private ISmsService smsService;

    @PostMapping("/send")
    public AjaxResult sendSms(@RequestParam String account,
                             @RequestParam String apiKey,
                             @RequestParam String recipient,
                             @RequestParam String message) {
        try {
            // 1. 验证账户和API密钥
            SmsUser smsUser = smsUserService.getUserByAccountAndApiKey(account, apiKey);
            if (smsUser == null) {
                return AjaxResult.error("无效的账户或API密钥");
            }

            // 2. 检查余额
            if (smsUser.getBalance().compareTo(new BigDecimal("1.00")) < 0) {
                return AjaxResult.error("账户余额不足");
            }

            // 3. 发送短信
            boolean smsSent = smsService.sendSmsToGzhPort(recipient, message);
            if (!smsSent) {
                return AjaxResult.error("短信发送失败");
            }

            // 4. 扣除余额并记录日志
            smsUserService.deductBalance(smsUser.getId(), new BigDecimal("1.00"));
            smsService.logSmsRecord(smsUser.getId(), recipient, message);

            return AjaxResult.success("短信发送成功");
            
        } catch (Exception e) {
            logger.error("短信发送异常", e);
            return AjaxResult.error("短信发送异常：" + e.getMessage());
        }
    }
} 