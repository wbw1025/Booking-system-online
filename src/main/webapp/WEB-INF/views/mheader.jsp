<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/style/global.css">
    <link rel="stylesheet" href="/static/style/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="/static/style/ui.jqgrid.css">
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <script src="/static/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script src="/static/js/grid.locale-cn.js"></script>
    <script src="/static/js/jquery.jqGrid.min.js"></script>
    <title>挂号系统</title>
</head>
<body>
<div class="m_header">
    <div class="wrap clearfix">
        <p class="logo fl">
            <img src="/static/images/icon/logo.png" alt="图标">
            <span>健康114挂号平台管理系统</span>
        </p>
        <p class="user fr">
            <span>${loginUser.uname}</span>
            <a href=/login>[退出]</a>
            <img src="/static/images/upload/user.png" alt="头像">
        </p>
    </div>
</div>
</body>
</html>