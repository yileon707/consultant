# 惠聚生活作品集展示 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 将项目品牌统一为“惠聚生活”，并把 GitHub README 升级为适合招聘面试官快速浏览的作品集页面。

**Architecture:** 保持现有 Spring Boot 单体服务和业务接口不变，只更新用户可见品牌文案、生成当前运行页面的截图、增加项目架构图，并在 README 中按“产品效果 → 功能证据 → 技术实现 → 运行方式”的顺序组织内容。敏感配置和本地用户数据继续由 `.gitignore` 排除。

**Tech Stack:** Spring Boot 3.5、LangChain4j、Vue CDN、HTML/CSS、MySQL、Redis、阿里云百炼、GitHub Markdown、SVG/PNG 图片资产。

---

### Task 1: 完成品牌文案迁移

**Files:**
- Modify: `README.md`
- Modify: `src/main/resources/system.txt`
- Modify: `src/main/resources/static/index.html`
- Modify: `docs/superpowers/specs/2026-09-17-portfolio-readme-design.md`

- [x] **Step 1: 替换用户可见品牌**

将旧品牌名称替换为 `惠聚生活`，将旧顾问名称替换为 `惠聚顾问`，将旧快捷文案替换为“先问惠聚”，并把头像/品牌首字替换为“惠”。Java 包名、仓库名、数据库表名和 API 路径不变。

- [x] **Step 2: 验证旧品牌清零**

运行：

```powershell
rg -n "惠聚生活|惠聚顾问|先问惠聚|旧品牌名称" --glob '!target/**' .
```

预期：无匹配结果。

### Task 2: 生成作品集图片资产

**Files:**
- Create: `docs/assets/home-desktop.png`
- Create: `docs/assets/chat-recommendation.png`
- Create: `docs/assets/architecture.svg`

- [ ] **Step 1: 编译最新静态页面**

运行：

```powershell
$env:JAVA_HOME = 'D:\develop\jdk17'
$env:Path = "$env:JAVA_HOME\bin;D:\develop\apache-maven-3.9.4-bin\apache-maven-3.9.4\bin;$env:Path"
mvn -q -DskipTests compile
```

- [ ] **Step 2: 截取真实首页**

使用本地运行服务 `http://127.0.0.1:8080/` 的桌面视口截取首页，确保截图展示“惠聚生活”、橙色本地生活视觉、快捷入口和输入框，不包含 API Key、手机号或数据库数据。保存为 `docs/assets/home-desktop.png`。

- [ ] **Step 3: 截取真实聊天结果**

在隔离浏览器会话中发送一条不含个人信息的餐厅推荐问题，等待正常返回后截取聊天区域，确保截图体现流式对话和本地生活推荐结果。保存为 `docs/assets/chat-recommendation.png`。

- [ ] **Step 4: 创建架构图**

创建可缩放的 SVG，包含以下节点和连接：

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

使用浅色背景、橙色品牌强调色和中性灰文字，保证在 GitHub README 中可读。

### Task 3: 编写招聘导向 README

**Files:**
- Modify: `README.md`

- [ ] **Step 1: 重写 README 结构**

按以下顺序组织：

1. 项目标题、定位和核心截图；
2. “为什么值得看”能力卡片：AI 流式响应、工具调用、RAG、Redis、MySQL；
3. 产品截图和功能说明；
4. 架构图和请求链路；
5. 技术栈；
6. 本地运行与环境变量；
7. 安全与测试说明。

- [ ] **Step 2: 加入图片引用**

使用 GitHub 相对路径引用：

```markdown
![惠聚生活首页](docs/assets/home-desktop.png)
![餐厅推荐对话](docs/assets/chat-recommendation.png)
![系统架构](docs/assets/architecture.svg)
```

- [ ] **Step 3: 保留安全说明**

明确说明仓库不提交真实 API Key、数据库密码、Redis 密码和本地 SQL 用户数据，并保留环境变量配置示例。

### Task 4: 验证、提交并同步 GitHub

**Files:**
- No additional source files.

- [ ] **Step 1: 静态检查品牌和敏感文件**

运行：

```powershell
rg -n "惠聚生活|惠聚顾问|先问惠聚|旧品牌名称" --glob '!target/**' .
git ls-files | rg "(^|/)(target|\.idea|.*\.sql$|.*\.env.*|.*\.log$)"
```

预期：第一条无输出；第二条无输出。

- [ ] **Step 2: 运行完整测试**

使用本地 MySQL 和 Redis 配置运行：

```powershell
mvn -q test
```

预期：退出码为 0，测试报告显示 3 个测试全部通过。

- [ ] **Step 3: 检查图片和 README**

确认三个图片文件存在、README 引用路径可解析、工作区无未预期文件。

- [ ] **Step 4: 创建提交并同步 `main`**

```powershell
git add README.md src/main/resources/system.txt src/main/resources/static/index.html docs/assets docs/superpowers
git commit -m "Polish Huiju Life portfolio presentation"
```

随后将最新提交同步到 `yileon707/consultant` 的 `main` 分支，并通过 GitHub API 核对远程提交和文件树。
