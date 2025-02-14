package com.ruoyi.sms.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_sms_user")
public class SmsUser {
    private Long id;
    private String account;     // 账号
    private String password;    // 密码
    private String apiKey;      // API密钥
    private String secretKey;   // 密钥
    private BigDecimal balance; // 账户余额
} 