<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/style/global.css"/>
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <script src="/static/js/jquery.validate.min.js"></script>
    <script src="/static/js/messages_zh.js"></script>
    <title>挂号系统-重置密码</title>
</head>
<body>
<div class="login wrap">
    <img src="/static/images/upload/login_bg.jpg" alt="登录"/>

    <div class="login_cont">
        <h3>重置密码</h3>
        <form id="reset_form">
            <p class="pname">邮箱：<input type="email" name="email" value="${param.email}"></p>
            <input id="pass" class="pwd" name="password" type="password" placeholder="请输入新密码"/>
            <input class="pwd" name="vertifypass" type="password" placeholder="请再次输入密码"/>
            <input class="btn" type="submit" value="确定"/>
        </form>
    </div>
    <div class="succes_box">
        <p class="succes_bg"></p>
        <div class="succes_wd">
            <p class="text">重置密码成功</p>
            <a class="go" href="login">即将跳转到登录页面</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler: function () {
            $.ajax({
                type: "POST",
                url: "/user/resetpass",
                data: $('#reset_form').serialize(),
                success: function (data) {
                    if (data.status == 200) {
                        $('.succes_box').show();
                        setTimeout(function () {
                            window.location.href = "/login";
                        }, 3000);

                    } else {
                        alert(data.msg);
                    }
                }
            });
        }
    });
    $(function () {
        $('#reset_form').validate({
            rules: {
                password: {
                    required: true
                },
                vertifypass:{
                    required:true,
                    equalTo:'#pass'
                }
            }
        })
    });
</script>
</body>
</html>