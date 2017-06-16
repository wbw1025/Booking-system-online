<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<body>
<div class="header">
    <div class="wrap clearfix">
        <p class="tell fl">028-1111111 电话预约</p>
        <c:choose>
            <c:when test="${empty loginUser}">
                <p class="head fr">
                    <span>欢迎来到预约挂号统一平台 请</span>
                    <a href="login">登录</a>
                    <a href="register">注册</a>
                </p>
            </c:when>
            <c:otherwise>
                <p class="head fr">
                    <span>欢迎来到预约挂号统一平台</span>
                    <a class="uname" href="#">${loginUser.uname}</a>
                    <a href="login">退出</a>
                </p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div class="main_box wrap clearfix">
    <a class="logo fl" href="/index">
        <img src="/static/images/icon/logo.png" alt="logo"/>
        <i>预约挂号平台</i>
    </a>

    <div class="search fr clearfix">
        <select>
            <option value="">医院</option>
            <option value="">科室</option>
            <option value="">疾病</option>
        </select>
        <input type="text" placeholder="请输入搜索内容"/>
        <a href="#"><i></i></a>
    </div>
</div>
<script>
    //搜索
    $('.search input').blur(function(){
        var type=$('.search option:selected').text();
        var value = $('.search input').val();
        if (value!=""){
            url='/search/?type='+type+'&word='+value;
            $('.search a').attr('href',url);
        }
    });
</script>

</body>
</html>