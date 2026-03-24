<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生管理首页</title>
    <style>
        :root {
            --overlay: rgba(8, 24, 43, 0.42);
            --panel-bg: rgba(255, 255, 255, 0.16);
            --panel-border: rgba(255, 255, 255, 0.32);
            --text-main: #f6fbff;
            --text-muted: rgba(246, 251, 255, 0.82);
            --accent: #ffde8a;
            --shadow: rgba(0, 0, 0, 0.25);
        }

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            min-height: 100vh;
            font-family: "PingFang SC", "Microsoft YaHei", sans-serif;
            color: var(--text-main);
            background:
                    linear-gradient(var(--overlay), var(--overlay)),
                    url("ship-beach.jpg") center center / cover no-repeat;
        }

        .page {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 24px;
        }

        .panel {
            width: min(680px, 100%);
            padding: 36px 32px;
            border: 1px solid var(--panel-border);
            border-radius: 24px;
            background: var(--panel-bg);
            backdrop-filter: blur(14px);
            box-shadow: 0 24px 60px var(--shadow);
        }

        .eyebrow {
            margin: 0 0 10px;
            font-size: 13px;
            letter-spacing: 0.18em;
            text-transform: uppercase;
            color: var(--accent);
        }

        h1 {
            margin: 0;
            font-size: clamp(32px, 5vw, 52px);
            line-height: 1.1;
            font-weight: 700;
        }

        .intro {
            margin: 14px 0 28px;
            font-size: 16px;
            line-height: 1.7;
            color: var(--text-muted);
            max-width: 560px;
        }

        .link-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
            gap: 16px;
        }

        .nav-card {
            display: block;
            padding: 18px 18px 20px;
            border-radius: 18px;
            text-decoration: none;
            color: var(--text-main);
            background: rgba(255, 255, 255, 0.13);
            border: 1px solid rgba(255, 255, 255, 0.2);
            transition: transform 0.2s ease, background 0.2s ease, border-color 0.2s ease;
        }

        .nav-card:hover {
            transform: translateY(-4px);
            background: rgba(255, 255, 255, 0.18);
            border-color: rgba(255, 255, 255, 0.42);
        }

        .nav-title {
            display: block;
            margin-bottom: 8px;
            font-size: 20px;
            font-weight: 700;
        }

        .nav-desc {
            display: block;
            font-size: 14px;
            line-height: 1.6;
            color: var(--text-muted);
        }

        @media (max-width: 640px) {
            .panel {
                padding: 28px 22px;
                border-radius: 20px;
            }
        }
    </style>
</head>
<body>
<div class="page">
    <main class="panel">
        <p class="eyebrow">Student Console</p>
        <h1>学生信息管理入口</h1>
        <p class="intro">
            这里集中放置当前项目的功能入口。你可以新增学生、查询数据库中的学生列表，
            也可以进入示例 Servlet 页面查看基础功能。
        </p>

        <section class="link-grid">
            <a class="nav-card" href="student-form.jsp">
                <span class="nav-title">新增学生</span>
                <span class="nav-desc">填写姓名、年龄和邮箱，并把数据写入 MySQL 数据库。</span>
            </a>

            <a class="nav-card" href="students">
                <span class="nav-title">查询学生</span>
                <span class="nav-desc">查看数据库中已有的学生记录，同时在控制台输出查询结果。</span>
            </a>

            <a class="nav-card" href="hello-servlet">
                <span class="nav-title">Hello Servlet</span>
                <span class="nav-desc">保留项目自带的 Servlet 示例，便于验证基础路由是否正常。</span>
            </a>
        </section>
    </main>
</div>
</body>
</html>
