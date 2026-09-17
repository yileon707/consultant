# 惠聚生活

> 面向本地生活场景的 AI 顾问：让用户用一句话完成找店、领券和预约。

<p align="center">
  <strong>Spring Boot · LangChain4j · 阿里云百炼 · MySQL · Redis</strong>
</p>

惠聚生活是一个可运行的本地生活 AI 应用作品。它把自然语言对话连接到商家查询、优惠券查询和到店预约等业务能力，并通过流式响应和会话记忆提供连续、自然的交互体验。

## 产品界面

<p align="center">
  <img src="https://raw.githubusercontent.com/yileon707/consultant/main/docs/assets/home-desktop.png" alt="惠聚生活首页" width="100%">
</p>

首页采用橙色本地生活视觉，提供餐厅、优惠和预约三个快捷入口；聊天区域支持流式展示 AI 回复。

<p align="center">
  <img src="https://raw.githubusercontent.com/yileon707/consultant/main/docs/assets/chat-recommendation.png" alt="惠聚生活餐厅推荐对话" width="100%">
</p>

对话场景展示了从用户需求理解到餐厅推荐、进一步筛选和预约引导的完整交互。

## 面试官快速了解

| 能力点 | 项目体现 |
| --- | --- |
| AI 应用编排 | LangChain4j `AiService` 串联模型、记忆、RAG 和业务工具 |
| 流式交互 | `Flux<String>` 输出模型内容，前端实时渲染回复 |
| Tool Calling | AI 根据意图调用商家、优惠券和预约工具 |
| RAG | 使用本地知识内容和 `text-embedding-v3` 完成检索增强问答 |
| 会话记忆 | Redis 持久化多轮对话上下文 |
| 业务数据 | MySQL 保存商家、优惠券和预约等结构化数据 |
| 工程安全 | API Key、数据库密码和 Redis 密码均通过环境变量注入 |

## 系统架构

<p align="center">
  <img src="https://raw.githubusercontent.com/yileon707/consultant/main/docs/assets/architecture.svg" alt="惠聚生活系统架构" width="100%">
</p>

核心请求链路：

```text
浏览器 / 惠聚生活 UI
        ↓
Spring Boot / ChatController
        ↓
LangChain4j AiService
   ┌────┼──────────────┐
阿里云百炼   业务 Tools   RAG
              │          │
        MySQL 数据    Redis 会话记忆
```

## 主要功能

- **商家查询**：根据商家全称检索商家信息。
- **优惠券查询**：查询商家可用优惠券，以及用户拥有的优惠券。
- **到店预约**：引导用户补充姓名、电话、时间和商家后创建预约。
- **多轮对话**：保留会话上下文，支持连续追问。
- **检索增强**：结合本地生活知识内容提升回答的相关性。
- **响应式界面**：桌面端侧栏布局和移动端单列布局均可使用。

## 技术栈

- Java 17
- Spring Boot 3.5
- Spring MVC + Reactor `Flux`
- LangChain4j 1.0.1-beta6
- 阿里云百炼兼容 OpenAI API：Qwen Plus、`text-embedding-v3`
- MyBatis-Plus
- MySQL 8
- Redis
- Vue 3 CDN

## 本地运行

项目使用环境变量读取敏感配置，请不要把真实密钥写入代码或提交到 Git：

```powershell
$env:DASHSCOPE_API_KEY = "你的阿里云百炼 API Key"
$env:MYSQL_PASSWORD = "你的 MySQL 密码"
$env:REDIS_PASSWORD = "你的 Redis 密码"

$env:JAVA_HOME = "你的 JDK 17 路径"
mvn spring-boot:run
```

启动后访问 <http://127.0.0.1:8080/>。

## 测试与安全说明

```powershell
mvn -q test
```

仓库不会提交以下内容：

- API Key、数据库密码和 Redis 密码；
- 包含手机号和用户记录的本地 SQL 导出；
- `target/`、IDE 配置和运行日志。

本地运行需要自行准备 `hmdp` 数据库和 Redis 服务。
