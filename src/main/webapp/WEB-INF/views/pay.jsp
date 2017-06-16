<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <img src="/static/images/upload/login_bg.jpg" alt="支付"/>
    <div class="pay">
        <h3>确认预约信息</h3>
        <div class="pay_Info">
            <p>就诊人: <span>${loginUser.uname}</span></p>
            <p>预约费用：<span class="mny">${money}元</span></p>
            <p>预约医院：<span>${hname}</span></p>
            <p>预约科室：<span>${sname}</span></p>
            <p>预约医生：<span>${dname}</span></p>
           <p>就诊时间：<span>${time}</span></p>
            <p>医院地址：<span>${hadr}</span></p>
            <input class="btn" type="button" value="确认预约">
            <ul class="info">
                <p>注意事项：</p>
                <li>1.您可以在个人中心查看订单信息；</li>
                <li>2.如果预约成功，由于个人原因不能就诊，请及时取消订单；</li>
                <li>3.如果医生停诊，您的订单会被取消；我们会及时通知到您。</li>
            </ul>
        </div>
    </div>
    <div class="succes_box">
        <p class="succes_bg"></p>
        <div class="succes_wd">
            <a class="close" href="/index"></a>
        </div>
    </div>
</div>
<script>
   $(function () {
       $('.pay_Info .btn').on('click',function () {
           $.ajax({
               type:"POST",
               url:"/order",
               success:function (data) {
                   console.log(data);
                   if(data){
                       $('.succes_wd').append('<p class="text">预约成功</p>');

                   }else {
                       $('.succes_wd').append('<p class="text">预约失败</p>');
                   }
                   $('.succes_box').fadeIn();
               }
           })
       })
   })
</script>
</body>
</html>