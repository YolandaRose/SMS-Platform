package com.ruoyi.sms.service;

public interface ISmsService {
    /**
     * 发送短信到广州港平台
     */
    boolean sendSmsToGzhPort(String recipient, String message);
    
    /**
     * 记录短信发送日志
     */
    void logSmsRecord(Long userId, String recipient, String message);
}