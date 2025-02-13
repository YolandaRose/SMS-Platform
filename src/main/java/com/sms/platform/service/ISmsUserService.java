package com.sms.platform.service;

import com.sms.platform.domain.SmsUser;
import java.math.BigDecimal;
import java.util.Map;

public interface ISmsUserService {
    /**
     * 根据API密钥获取用户信息
     */
    SmsUser getByAccessKey(String apiKey);
    
    /**
     * 扣除用户余额
     */
    boolean deductBalance(Long userId, Integer fee);
    
    /**
     * 获取用户余额信息
     */
    Map<String, Object> getBalanceInfo(Long userId);
} 