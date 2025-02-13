package com.sms.platform.service;

import com.sms.platform.domain.SmsSendRequest;
import com.sms.platform.domain.SmsSendResult;
import com.sms.platform.domain.SmsUser;

public interface ISmsService {
    /**
     * 发送短信
     */
    SmsSendResult send(SmsSendRequest request, SmsUser smsUser);
    
    /**
     * 记录发送日志
     */
    void saveLog(SmsSendRequest request, SmsSendResult result, SmsUser smsUser);
} 