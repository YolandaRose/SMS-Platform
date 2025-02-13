package com.sms.platform.domain;

import lombok.Data;
import java.util.Map;

@Data
public class SmsSendRequest {
    private String accessKey;        // 访问密钥
    private String phoneNumbers;     // 手机号码，多个用逗号分隔
    private String templateCode;     // 模板编码
    private Map<String, String> templateParams;  // 模板参数
    private String timestamp;        // 时间戳
    private String nonce;           // 随机字符串
    private String sign;            // 签名
} 