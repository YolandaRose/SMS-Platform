package com.sms.platform.domain;

import lombok.Data;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("sms_send_log")
public class SmsSendLog {
    private Long id;
    private Long userId;         // 用户ID
    private String phoneNumbers; // 手机号码
    private String templateCode; // 模板编码
    private String templateParams; // 模板参数(JSON)
    private Integer fee;         // 计费条数
    private String status;       // 发送状态
    private String messageId;    // 消息ID
    private Date createTime;     // 创建时间
    private String remark;       // 备注
} 