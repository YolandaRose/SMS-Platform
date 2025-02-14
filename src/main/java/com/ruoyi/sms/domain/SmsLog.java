package com.ruoyi.sms.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_sms_log")
public class SmsLog {
    private Long id;
    private Long userId;      // 用户ID
    private String recipient; // 接收手机号
    private String content;   // 短信内容
    private String status;    // 发送状态
    private Date sendTime;    // 发送时间
    private String remark;    // 备注
} 