package com.sms.platform.service.impl;

import com.sms.platform.domain.SmsUser;
import com.sms.platform.mapper.SmsUserMapper;
import com.sms.platform.service.ISmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class SmsUserServiceImpl implements ISmsUserService {

    @Autowired
    private SmsUserMapper smsUserMapper;

    @Override
    public SmsUser getByAccessKey(String apiKey) {
        return smsUserMapper.selectByApiKey(apiKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deductBalance(Long userId, Integer fee) {
        // 先查询用户余额
        SmsUser user = smsUserMapper.selectById(userId);
        if (user == null || user.getBalance().compareTo(new BigDecimal(fee)) < 0) {
            return false;
        }
        
        // 扣除余额
        BigDecimal newBalance = user.getBalance().subtract(new BigDecimal(fee));
        return smsUserMapper.updateBalance(userId, newBalance) > 0;
    }

    @Override
    public Map<String, Object> getBalanceInfo(Long userId) {
        SmsUser user = smsUserMapper.selectById(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("balance", user != null ? user.getBalance() : BigDecimal.ZERO);
        result.put("frozen", BigDecimal.ZERO); // 暂时不考虑冻结金额
        return result;
    }
} 