package com.ruoyi.sms.service.impl;

import com.ruoyi.sms.service.ISmsService;
import com.ruoyi.system.domain.SmsLog;
import com.ruoyi.system.mapper.SmsLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SmsServiceImpl implements ISmsService {
    
    @Autowired
    private SmsLogMapper smsLogMapper;
    
    @Override
    public boolean sendSmsToGzhPort(String recipient, String message) {
        // TODO: 实现广州港短信平台的调用
        // 这里需要集成实际的短信平台SDK
        return true;
    }
    
    @Override
    public void logSmsRecord(Long userId, String recipient, String message) {
        SmsLog smsLog = new SmsLog();
        smsLog.setUserId(userId);
        smsLog.setRecipient(recipient);
        smsLog.setContent(message);
        smsLog.setSendTime(new Date());
        smsLog.setStatus("1"); // 发送成功
        
        smsLogMapper.insert(smsLog);
    }
}