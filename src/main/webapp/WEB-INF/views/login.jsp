<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/style/global.css"/>
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <title>挂号系统-登录</title>
</head>
<body>
<div class="login wrap">
    <img src="/static/images/upload/login_bg.jpg" alt="登录"/>

    <div class="login_cont">
        <h3>登录</h3>
        <form id="login_form">
            <input class="name" name="account" type="text" placeholder="用户名/邮箱" value=""/>
            <input class="pwd" name="passwd" type="password" placeholder="密码" value=""/>
            <p><input class="admin" name="admin" type="checkbox" value="1"><span>管理员</span></p>
            <a class="fgt" href="findpwd">忘记密码？</a>
            <input class="btn" type="button" value="登录"/>
        </form>
    </div>
</div>
<script>
    $(function () {

        //登录事件
        $(".btn").click(function () {
            $.ajax({
                type: "POST",
                url: "/user/login",
                data: $('#login_form').serialize(),
                success: function (data) {
                    if (data.status == 200) {
                        if($('.admin').is(":checked")){
                            window.location.href = "/mUser";
                        }else{
                            window.location.href = "/index";
                        }
                    } else {
                        alert(data.msg);
                    }
                }
            });
        })
    })
</script>
</body>
</html>