package com.ruoyi.sms.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.sms.domain.SmsUser;
import com.ruoyi.sms.mapper.SmsUserMapper;
import com.ruoyi.sms.service.ISmsUserService;

@Service
public class SmsUserServiceImpl implements ISmsUserService {

    @Autowired
    private SmsUserMapper smsUserMapper;

    @Override
    public SmsUser getUserByAccountAndApiKey(String account, String apiKey) {
        return smsUserMapper.selectOne(
            new LambdaQueryWrapper<SmsUser>()
                .eq(SmsUser::getAccount, account)
                .eq(SmsUser::getApiKey, apiKey));
    }
    
    @Override
    public SmsUser getByAccessKey(String accessKey) {
        return smsUserMapper.selectOne(
            new LambdaQueryWrapper<SmsUser>()
                .eq(SmsUser::getApiKey, accessKey));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deductBalance(Long userId, BigDecimal amount) {
        SmsUser user = smsUserMapper.selectById(userId);
        if (user == null || user.getBalance().compareTo(amount) < 0) {
            return false;
        }
        
        user.setBalance(user.getBalance().subtract(amount));
        return smsUserMapper.updateById(user) > 0;
    }

    @Override
    public Map<String, Object> getBalanceInfo(Long userId) {
        SmsUser user = smsUserMapper.selectById(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("balance", user != null ? user.getBalance() : BigDecimal.ZERO);
        return result;
    }
} 