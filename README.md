# 惠聚生活

> 面向本地生活场景的 AI 顾问，让用户用一句话完成找店、领券和预约。

<p align="center">
  <strong>Spring Boot · LangChain4j · 阿里云百炼 · MySQL · Redis</strong>
</p>

惠聚生活将自然语言对话连接到商家查询、优惠券查询和到店预约等业务能力，并通过流式响应与会话记忆提供连续、自然的交互体验。

## 产品概览

<p align="center">
  <img src="https://raw.githubusercontent.com/yileon707/consultant/main/docs/assets/home-desktop.png" alt="惠聚生活首页" width="100%">
</p>

橙色本地生活视觉提供餐厅、优惠和预约三个快捷入口，聊天区域支持实时展示 AI 回复。

## 核心能力

| 能力 | 实现 |
| --- | --- |
| AI 应用编排 | LangChain4j `AiService` 串联模型、记忆、检索和业务工具 |
| 流式交互 | Reactor `Flux` 输出模型内容，前端实时渲染回复 |
| 工具调用 | 根据用户意图调用商家、优惠券和预约能力 |
| 检索增强 | 使用本地生活知识内容和 `text-embedding-v3` 提升回答相关性 |
| 会话记忆 | Redis 持久化多轮对话上下文 |
| 业务数据 | MySQL 管理商家、优惠券和预约等结构化数据 |

## 系统架构

<p align="center">
  <img src="https://raw.githubusercontent.com/yileon707/consultant/main/docs/assets/architecture.svg" alt="惠聚生活系统架构" width="100%">
</p>

核心请求链路：

```text
浏览器 / 惠聚生活 UI → Spring Boot → LangChain4j AiService
                                      ├─ 阿里云百炼
                                      ├─ RAG 检索
                                      ├─ Redis 会话记忆
                                      ├─ MySQL 业务数据
                                      └─ 商家 / 优惠券 / 预约 Tools
```

## 主要功能

- **商家查询**：根据商家名称检索商家信息。
- **优惠券查询**：查询商家可用优惠券和用户拥有的优惠券。
- **到店预约**：围绕姓名、电话、时间和商家完成预约流程。
- **多轮对话**：保留会话上下文，支持连续追问。
- **检索增强**：结合本地生活知识内容生成更相关的回答。
- **响应式界面**：适配桌面端和移动端使用场景。

## 技术栈

- Java 17
- Spring Boot 3.5
- Spring MVC + Reactor `Flux`
- LangChain4j 1.0.1-beta6
- 阿里云百炼：Qwen Plus、`text-embedding-v3`
- MyBatis-Plus
- MySQL 8
- Redis
- Vue 3 CDN

## 运行环境

- JDK 17+
- MySQL 8+
- Redis
- 阿里云百炼模型服务
