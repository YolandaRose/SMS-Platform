package com.sms.platform.domain;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class SmsSendResult {
    private String messageId;    // 消息ID
    private Integer fee;         // 计费条数
    private String status;       // 发送状态
    private String message;      // 状态说明
} 