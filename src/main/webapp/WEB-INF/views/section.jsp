<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/style/global.css"/>
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <title>挂号系统</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="nav_box">
    <div class="nav wrap">
        <a class="nav_li" href="/index">首页</a>
        <a href="/hospital">按医院挂号</a>
        <a class="curr" href="/section">按科室挂号</a>
        <a href="/sick">按疾病挂号</a>
        <a href="/zom">个人中心</a>
    </div>
</div>
<div class="list wrap clearfix">
    <div class="reg">
        <a>科室列表</a>
    </div>
    <ul class="sec_side fl">
        <c:forEach items="${sectionSet}" var="sec" varStatus="status">
            <li><a href="#sec${status.index}"><i style="background:url(${sec.icon}) no-repeat center left"></i>${sec.name}</a></li>
        </c:forEach>
    </ul>
    <div class="sec_main fr">

    </div>
</div>
<%@include file="footer.jsp" %>

<!--js-->
<script src="/static/js/main.js"></script>
<script>
    addCo(".sec_side li");
    $(function () {
        $.ajax({
            type: "POST",
            url: "/sectionList",
            success: function (data) {
                console.log(data);
                var html = "";
                var htmlP = "";
                var htmlU = "";
                $.each(data, function (n, val) {
                    htmlP = "";
                    htmlU = "";
                    htmlP = '<p><a name="sec' + n+ '"></a>' + val.parent.name + '</p>';
                    $.each(val.child, function (m, child) {
                        htmlU += '<li><a href="/hospital/'+child.sid+'">' + child.sname + '</a></li>';
                    });
                    html += htmlP+'<ul class="clearfix">'+htmlU+'</ul>';
                });
                $(".sec_main").append(html);
            }
        })
    })
</script>
</body>
</html>