# LostFound 校园失物招领系统 · Campus Lost & Found

> 一个基于 SSH（Struts2 + Spring + Hibernate）框架的校园失物招领 Web 平台。

With the continuous expansion of higher education in China, the loss of personal belongings on university campuses has become increasingly frequent. Traditional lost and found approaches mainly rely on bulletin board postings, social media announcements, or word-of-mouth communication, which generally suffer from limited information dissemination, delayed updates, and low retrieval efficiency. This project designs and implements a campus lost and found system based on the SSH framework, adopting a front-end and back-end separation architecture: the front-end serves students and faculty (posting, searching, commenting, points ranking), while the back-end serves administrators (user/item management, announcements, help center, data statistics).

## 技术栈 / Tech Stack

| 层级 | 技术 |
| --- | --- |
| 前端 | JSP + HTML/CSS/JS（iframe 拼页） |
| Web 框架 | Apache Struts2（2.3.1） |
| 业务容器 | Spring（3.1.1） |
| 持久层 | Hibernate（3.6）+ MySQL |
| 数据库 | MySQL（Connector/J 5.0.5） |
| 构建 | Maven（`com.phn:LostFound`） |
| 开发工具 | IntelliJ IDEA / Apache Tomcat / MySQL |

## 功能概览 / Features

- 失物 / 招领信息发布（含图片上传）
- 按关键字 / 分类 / 区域搜索与筛选
- 物品详情页与留言交流
- 公告（anno）、帮助（help）、友链展示
- 用户注册 / 登录 / 个人中心
- 管理后台：用户、物品、公告、帮助、基础数据与数据统计
- 积分奖励：发布 / 认领等行为累计积分（由 `create_reward_trigger.sql` 触发器驱动）

## 目录结构（部署副本）

```
LostFound/
├── *.jsp                  页面与入口
├── admin/                 管理后台页面
├── css/ js/ image/        静态资源
├── img-lost/ img-found/   上传的失物 / 招领图片
├── WEB-INF/
│   ├── web.xml            Servlet/Struts 过滤器与 Spring 监听器
│   ├── classes/           编译后的 .class 与配置（db.properties / struts.xml / applicationContext.xml）
│   ├── lib/               运行时依赖 jar
│   └── src/               业务源码
├── META-INF/maven/        Maven artifact 元数据
├── add_reward_points_to_user.sql   积分字段迁移脚本
├── create_reward_trigger.sql       积分触发器脚本
└── README.md / .gitignore
```

## 本地部署 / Deploy

1. 安装 JDK 与 Apache Tomcat（本项目在 Tomcat 9 下验证）。
2. 安装 MySQL，建立数据库。
3. 修改 `WEB-INF/classes/db.properties` 的数据库连接与账号密码，匹配你的环境。
4. 将整个 `LostFound` 目录放入 Tomcat 的 `webapps/`，启动后访问 `http://localhost:8080/LostFound/`。
5. 首个管理员账号请在数据库中为用户记录设置管理员角色后登录后台。

## 已知安全说明 / Security Notes

本项目为课程 / 演示作品，所用框架版本较老，正式上线前需处理：

- Struts2 2.3.1 存在多个已知远程代码执行漏洞，生产环境务必升级或迁移框架。
- 数据库凭据以明文写在 `db.properties`，建议改为最小权限专用账号并移入环境变量。
- 管理后台与部分写操作缺少统一鉴权拦截器，需补齐登录 / 角色校验。
- 用户密码为明文存储，正式环境应改为 bcrypt / Argon2 等加盐哈希。
- 上传接口仅校验 Content-Type，需加扩展名白名单、随机文件名并存放到 web 根之外。
