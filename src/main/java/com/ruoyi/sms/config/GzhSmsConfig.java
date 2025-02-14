package com.ruoyi.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "sms.gzh")
public class GzhSmsConfig {
    private String apiUrl;    // 接口地址
    private String account;   // 账号
    private String password;  // 密码
    private String sign;      // 短信签名
} 