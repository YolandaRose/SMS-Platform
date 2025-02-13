package com.sms.platform.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

public class SignUtils {
    
    public static String generateSign(String accessKey, String timestamp, String nonce, String secretKey) {
        String content = accessKey + timestamp + nonce + secretKey;
        return DigestUtils.md5Hex(content);
    }
    
    /**
     * 验证签名
     * @param apiKey API密钥
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @param sign 签名
     * @return 验证结果
     */
    public static boolean validateSign(String apiKey, String timestamp, String nonce, String sign) {
        if (!StringUtils.hasText(apiKey) || !StringUtils.hasText(timestamp) 
            || !StringUtils.hasText(nonce) || !StringUtils.hasText(sign)) {
            return false;
        }
        
        // 验证时间戳是否在5分钟内
        long now = System.currentTimeMillis();
        long requestTime = Long.parseLong(timestamp);
        if (Math.abs(now - requestTime) > 5 * 60 * 1000) {
            return false;
        }
        
        // 生成签名
        String content = apiKey + timestamp + nonce;
        String expectedSign = DigestUtils.md5Hex(content);
        
        return expectedSign.equals(sign);
    }
} 