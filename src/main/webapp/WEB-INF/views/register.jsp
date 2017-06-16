<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/style/global.css"/>
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <script src="/static/js/jquery.validate.min.js"></script>
    <script src="/static/js/messages_zh.js"></script>
    <title>挂号系统-注册</title>
</head>
<body>
<div class="login wrap">
    <img src="/static/images/upload/login_bg.jpg" alt="登录"/>
    <div class="login_cont">
        <form id="reg_form">
            <h3>用户注册</h3>
            <input id="name" class="name"  name="username" type="text" placeholder="用户名" value=""/>
            <input id="email" class="mail" name="email"  type="email" placeholder="邮箱" value=""/>
            <input id="pass" class="pwd" name="password" type="password" placeholder="密码"/>
            <input class="apwd" name="verifypass" type="password" placeholder="确认密码"/>
            <input class="btn" type="submit" value="注册"/>
        </form>
    </div>
    <div class="succes_box">
        <p class="succes_bg"></p>
        <div class="succes_wd">
            <p class="text">恭喜您注册成功</p>
            <a class="go" href="login">即将跳转到登录页面</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    //表单验证
    $.validator.setDefaults({
        submitHandler: function () {
            $.ajax({
                type: "POST",
                url: "/user/register",
                data: $('#reg_form').serialize(),
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
    $().ready(function () {
        $("#reg_form").validate({  //前台密码验证
            rules: {
                username: {
                    required: true,
                    remote: {
                        url: '/user/checkaccount/2',
                        type: "POST",
                        data: {
                            account: function () {
                                return $('#name').val();
                            }
                        }
                    }
                },
                email: {
                    required: true,
                    remote: {
                        url: '/user/checkaccount/1',
                        type: "POST",
                        data: {
                            account: function () {
                                return $('#email').val();
                            }
                        },
                    }
                },
                password: {
                    required: true,
                },
                verifypass: {
                    required: true,
                    equalTo: '#pass'
                },
            }
        })
    });
</script>
</body>
</html>