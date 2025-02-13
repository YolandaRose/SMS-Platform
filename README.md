# SMS-Platform

## 项目介绍
这是一个基于若依(RuoYi)框架开发的短信服务平台系统。

## 技术架构
- 基础框架：若依(RuoYi) Spring Boot
- 数据库：MySQL
- 缓存：Redis

## 主要功能（计划开发）
1. 短信服务管理
   - 短信模板管理
   - 短信发送记录
   - 短信发送统计
   
2. 短信通道管理
   - 多通道配置
   - 通道负载均衡
   - 通道故障转移
   
3. 系统管理
   - 用户管理
   - 角色管理
   - 菜单管理
   - 部门管理
   - 操作日志
   
4. 监控管理
   - 短信发送实时监控
   - 通道状态监控
   - 系统性能监控

## 环境要求
- JDK 1.8+
- Maven 3.0+
- MySQL 5.7+
- Redis 5.0+

## 安装部署
待补充...

## 使用说明
待补充...

## API接口说明
### 1. 短信发送接口
- 接口地址：`/api/sms/send`
- 请求方式：POST
- 接口认证：AccessKey + SecretKey
- 请求参数：
  ```json
  {
    "phoneNumbers": "13800138000,13900139000", // 手机号码，多个用逗号分隔
    "templateCode": "SMS_001",                 // 短信模板编码
    "templateParams": {                        // 模板参数
      "code": "123456",
      "product": "测试产品"
    }
  }
  ```
- 响应结果：
  ```json
  {
    "code": 200,                              // 状态码
    "msg": "success",                         // 状态信息
    "data": {
      "messageId": "SMS_20240315_123456789",  // 消息ID
      "fee": 1                                // 计费条数
    }
  }
  ```

### 2. 余额查询接口
- 接口地址：`/api/sms/balance`
- 请求方式：GET
- 接口认证：AccessKey + SecretKey
- 响应结果：
  ```json
  {
    "code": 200,
    "msg": "success",
    "data": {
      "balance": 1000,                        // 剩余条数
      "frozen": 0                             // 冻结条数
    }
  }
  ```

## 接口认证说明
1. 所有接口调用需要在Header中携带以下认证信息：
   - X-Access-Key: 访问密钥ID
   - X-Timestamp: 当前时间戳
   - X-Nonce: 随机字符串
   - X-Sign: 签名信息

2. 签名算法：
   ```
   sign = md5(AccessKey + Timestamp + Nonce + SecretKey)
   ```
 
