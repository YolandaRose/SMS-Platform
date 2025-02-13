package com.sms.platform.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

@Data
@TableName("sms_user")
public class SmsUser {
    private Long id;
    private String account;     // 账号
    private String password;    // 密码
    private String apiKey;      // API密钥
    private BigDecimal balance; // 账户余额
} 