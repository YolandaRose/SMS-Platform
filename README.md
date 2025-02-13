# SMS-Platform

## 项目介绍
这是一个基于若依(RuoYi)框架开发的短信服务平台系统。通过该平台,外部公司可以注册账号并获取API密钥,用于发送短信服务。

## 技术架构
- 基础框架：若依(RuoYi) Spring Boot
- 数据库：MySQL
- 缓存：Redis
- ORM：MyBatis-Plus
- 接口文档：Swagger

## 主要功能
1. 短信服务管理
   - 短信模板管理
   - 短信发送记录
   - 短信发送统计
   
2. 账户管理
   - 账户注册
   - 余额管理
   - API密钥管理
   
3. 系统管理
   - 用户管理
   - 角色管理
   - 菜单管理
   - 部门管理
   - 操作日志

## API接口说明
### 1. 短信发送接口
- 接口地址：`/api/sms/send`
- 请求方式：POST
- 接口认证：apiKey
- 请求参数：
  ```json
  {
    "accessKey": "987654",                    // API密钥
    "phoneNumbers": "13800138000,13900139000", // 手机号码，多个用逗号分隔
    "templateCode": "SMS_001",                 // 短信模板编码
    "templateParams": {                        // 模板参数
      "code": "123456",
      "product": "测试产品"
    },
    "timestamp": "1647331200000",             // 时间戳
    "nonce": "abc123",                        // 随机字符串
    "sign": "md5签名"                         // 签名
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
- 接口认证：apiKey
- 请求头：
  - X-Access-Key: API密钥
  - X-Timestamp: 时间戳
  - X-Nonce: 随机字符串
  - X-Sign: 签名信息
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

## 签名算法说明
1. 签名参数说明：
   - apiKey: API密钥
   - timestamp: 当前时间戳(毫秒)
   - nonce: 随机字符串
   
2. 签名生成步骤：
   - 按照apiKey + timestamp + nonce的顺序拼接字符串
   - 对拼接后的字符串进行MD5加密
   - 加密结果即为签名

3. 签名验证规则：
   - 请求时间与服务器时间相差不能超过5分钟
   - 签名参数不能为空
   - 签名必须与服务器计算结果一致

## 环境要求
- JDK 1.8+
- Maven 3.0+
- MySQL 5.7+
- Redis 5.0+

## 数据库表结构
### sms_user表
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | bigint | 主键ID |
| account | varchar(255) | 账号 |
| password | varchar(255) | 密码 |
| apiKey | varchar(255) | API密钥 |
| balance | decimal(10,2) | 账户余额 |

## 使用说明
1. 注册账号获取API密钥
2. 使用API密钥调用接口
3. 每次调用接口需要生成签名
4. 发送短信会根据条数扣除相应余额

## 监控管理
- 短信发送实时监控
- 通道状态监控
- 系统性能监控

## 安装部署
待补充...
