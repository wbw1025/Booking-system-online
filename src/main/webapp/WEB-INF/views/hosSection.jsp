<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/style/global.css"/>
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <title>挂号系统-科室列表</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="nav_box">
    <div class="nav wrap">
        <a class="nav_li" href="/index">首页</a>
        <a class="curr" href="/hospital">按医院挂号</a>
        <a href="/section">按科室挂号</a>
        <a href="/sick">按疾病挂号</a>
        <a href="/zom">个人中心</a>
    </div>
</div>
<div class="list wrap">
    <div class="reg">
        <a>预约挂号</a>
    </div>
    <ul class="host_list wrap clearfix">
            <li class="host_det">
                <h3 class="hos_tit">${hosInfo.hname}</h3>
                <div class="detail">
                    <img src="${hosInfo.himg}" alt="${hosInfo.hname}"/>
                    <div>
                        <span class="time">放号时间： ${hosInfo.htime}</span>
                        <span class="tell">咨询电话：${hosInfo.htell}</span>
                        <span class="adr">地址：${hosInfo.hadr}</span>
                    </div>
                </div>
            </li>
    </ul>
</div>
<%@include file="footer.jsp"%>

<script>
    $(function () {
        var  id ="${hosInfo.hid}";
        $.ajax({
            type:"POST",
            url:"/hosSecList",
            data:{id:id},
            success:function (data) {
               if (data.length > 0){
                    var html = '';
                    var htmlH3 = '';
                    var htmlLi = '';
                    $(data).each(function (n,val) {
                        htmlLi ='';
                        htmlH3 = '<h3>'+val.hosParent.name+'</h3>';
                        $(val.hosChild).each(function (n,child) {
                            htmlLi += '<li><a href="/order/${hosInfo.hid}/'+child.sid+'">'+child.sname+'</a></li>';
                        })
                        html += '<div class="section clearfix">'+htmlH3+
                            '<ul>'+ htmlLi+'</ul></div>';
                    })
                    $('.list').append(html);
                }else {
                    var error ='<p class="msg">暂无数据</p>';
                    $('.list').append(error);
                }

            }
        })
    });
    //获取地址参数值
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return   decodeURI (r[2]); return null; //返回参数值
    }
</script>
</body>
</html>