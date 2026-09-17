# 雅鉴生活志

一个基于 Spring Boot、LangChain4j、阿里云百炼和 MySQL/Redis 的本地生活 AI 顾问项目。

## 功能

- AI 流式对话
- 商家信息查询
- 优惠券查询
- 到店预约
- Redis 对话记忆
- 基于本地知识内容的检索增强问答

## 本地运行

项目使用环境变量读取敏感配置，请勿把真实密钥写入代码或提交到 Git：

```powershell
$env:DASHSCOPE_API_KEY = "你的阿里云百炼 API Key"
$env:MYSQL_PASSWORD = "你的 MySQL 密码"
$env:REDIS_PASSWORD = "你的 Redis 密码"

$env:JAVA_HOME = "你的 JDK 17 路径"
mvn spring-boot:run
```

启动后访问 <http://127.0.0.1:8080/>。

## 数据库说明

仓库不会提交本地 SQL 数据导出文件，因为其中可能包含手机号、用户记录等个人数据。请在本地准备 `hmdp` 数据库，并根据实际环境导入数据。
