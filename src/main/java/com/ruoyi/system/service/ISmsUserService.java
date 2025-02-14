package com.ruoyi.system.service;

import com.ruoyi.system.domain.SmsUser;
import java.math.BigDecimal;

public interface ISmsUserService {
    /**
     * 根据账号和API密钥获取用户
     */
    SmsUser getUserByAccountAndApiKey(String account, String apiKey);
    
    /**
     * 扣除用户余额
     */
    void deductBalance(Long userId, BigDecimal amount);
}