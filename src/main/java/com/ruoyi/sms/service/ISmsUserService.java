package com.ruoyi.sms.service;

import java.math.BigDecimal;
import com.ruoyi.sms.domain.SmsUser;

public interface ISmsUserService {
    /**
     * 根据账号和API密钥获取用户
     */
    SmsUser getUserByAccountAndApiKey(String account, String apiKey);
    
    /**
     * 根据AccessKey获取用户
     */
    SmsUser getByAccessKey(String accessKey);
    
    /**
     * 扣除用户余额
     */
    boolean deductBalance(Long userId, BigDecimal amount);
    
    /**
     * 获取用户余额信息
     */
    java.util.Map<String, Object> getBalanceInfo(Long userId);
} 