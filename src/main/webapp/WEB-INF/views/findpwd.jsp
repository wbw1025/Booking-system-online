<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/style/global.css"/>
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <script src="/static/js/jquery.validate.min.js"></script>
    <script src="/static/js/messages_zh.js"></script>
    <title>挂号系统-找回密码</title>
</head>
<body>
<div class="login wrap">
    <img src="static/images/upload/login_bg.jpg" alt="登录"/>

    <div class="login_cont">
        <h3>找回密码</h3>
        <form id="find_form">
            <input id="email" class="pwd" name="email" type="email" placeholder="注册时使用的电子邮箱"/>
            <input class="btn find_email" type="submit" value="发送链接至邮箱"/>
        </form>
        <a class="back" href="/login">返回</a>
    </div>
    <div class="succes_box">
        <p class="succes_bg"></p>
        <div class="succes_wd">
            <span class="close"></span>
            <p class="text">连接已发送至您的邮箱</p>
            <a class="go" href="login">半个小时内有效，请注意查收</a>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
<script type="text/javascript">
    //remote 事件验证正确 后 的事件
    $.validator.setDefaults({
        submitHandler: function() {
            $.ajax({
                type: "POST",
                url: "/user/sendemail",
                data: $('#find_form').serialize(),
                success: function (data) {
                    if (data.status == 200) {
                        $('.succes_box').show();
                        $('.find_email').val('重新发送');
                    } else {
                        alert(data.msg);
                    }
                }
            });
        }
    });

    //remote事件
    $().ready(function () {
        $("#find_form").validate({
            rules: {
                email: {
                    required: true,
                    remote: {
                        url: '/user/checkemail',
                        type: "POST",
                        data: {
                            account: function () {
                                return $('#email').val();
                            }
                        },
                    }
                },
            },
            messages:{
                email: {
                    remote:"此邮箱未注册",
                },
            }
        });
    });

    //关闭弹出框
    $(function(){
        $('.close').on('click',function () {
            $('.succes_box').hide();
        })
    })
</script>
</body>
</html>