package com.sms.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sms.platform.domain.SmsUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

public interface SmsUserMapper extends BaseMapper<SmsUser> {
    
    /**
     * 根据API密钥查询用户
     */
    SmsUser selectByApiKey(@Param("apiKey") String apiKey);
    
    /**
     * 更新用户余额
     */
    @Update("update sms_user set balance = #{balance} where id = #{userId}")
    int updateBalance(@Param("userId") Long userId, @Param("balance") BigDecimal balance);
} 