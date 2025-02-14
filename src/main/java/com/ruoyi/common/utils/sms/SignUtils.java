package com.ruoyi.common.utils.sms;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

public class SignUtils {
    
    /**
     * 生成签名
     */
    public static String generateSign(String accessKey, String timestamp, String nonce, String secretKey) {
        String content = accessKey + timestamp + nonce + secretKey;
        return DigestUtils.md5Hex(content);
    }
    
    /**
     * 验证签名
     */
    public static boolean validateSign(String accessKey, String timestamp, 
                                     String nonce, String sign, String secretKey) {
        if (!StringUtils.hasText(accessKey) || !StringUtils.hasText(timestamp) 
            || !StringUtils.hasText(nonce) || !StringUtils.hasText(sign)) {
            return false;
        }
        
        // 验证时间戳是否在5分钟内
        long now = System.currentTimeMillis();
        long requestTime = Long.parseLong(timestamp);
        if (Math.abs(now - requestTime) > 5 * 60 * 1000) {
            return false;
        }
        
        String expectedSign = generateSign(accessKey, timestamp, nonce, secretKey);
        return expectedSign.equals(sign);
    }
} 