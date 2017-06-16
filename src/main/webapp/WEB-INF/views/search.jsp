<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/style/global.css"/>
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <title>挂号系统</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="nav_box">
    <div class="nav wrap">
        <a class="nav_li"  href="/index">首页</a>
        <a href="/hospital">按医院挂号</a>
        <a href="/section">按科室挂号</a>
        <a href="/sick">按疾病挂号</a>
        <a href="/zom">个人中心</a>
    </div>
</div>
</div>
<div class="list wrap">
    <div class="host_type">
        <ul class="host host_rank">
            <span>医院等级</span>
            <li class="curr">全部</li>
            <c:forEach items="${rankSet}" var="rank">
                <li>${rank}医院</li>
            </c:forEach>
        </ul>
        <ul class="host host_adr">
            <span>医院位置</span>
            <li class="curr">全部</li>
            <c:forEach items="${areaSet}" var="area">
                <li>${area}</li>
            </c:forEach>
        </ul>
    </div>

    <ul class="host_list wrap">
    <p class="msg">暂无数据</p>
    </ul>
</div>
<%@include file="footer.jsp"%>

<!--js-->
<script src="/static/js/jquerysession.js"></script>
<script src="/static/js/main.js"></script>
<script>
    //页面加载完成后
    var newData = '';  //翻页数据
    var page = 1;   //默认显示页码
    var type =getUrlParam("type");
    var word=getUrlParam('word');//搜索关键字
    var area='全部';
    var rank='全部';
    $(function(){
        //根据用户的条件去获取医院信息
        $.ajax({
            type:"POST",
            url:"/searchList/"+page,
            dataType: "json",
            data:{type:type,word:word,area:area,rank:rank},
            success:function(data){
                //获取第一页医院列表
                //获取页码
                console.log(data);
                if (data.total > 0) {
                    newData = data;
                    getHospital(data);

                } else {
                    $('.host_list').empty();
                    $('.host_list').append('<p class="msg">暂无数据</p>');
                }
                if (data.total > 6) {
                    getPage(data);
                } else {
                    $('.page').empty();
                }
            }

        })

    });

    //等级和地区点击事件
    $('.host').on('click', 'li', function () {
        rank = ($('.host_rank .curr').text()).substr(0, 2);
        area = ($('.host_adr .curr').text());
        $.ajax({
            url:"/searchList/"+page,
            dataType: "json",
            data:{type:type,word:word,area:area,rank:rank},
            success: function (data) {
                if (data.total > 0) {
                    newData = data;
                    getHospital(data);
                } else {
                    $('.host_list').empty();
                    $('.host_list').append('<p class="msg">暂无数据</p>');
                }

                if (data.total > 6) {
                    getPage(data);
                } else {
                    $('.page').empty();
                }
            }
        })

    });

    //页码点击事件
    //数字页
    $('.page').on('click', ".ym", function () {
        $(this).siblings('.ym').removeClass('curr');
        $(this).addClass('curr');
        var page = $(this).text();
        getCont(page);
    });

    //首页
    $('.page').on('click', '.first', function () {
        console.log(newData.isFirstPage);
        if (!newData.isFirstPage) {
            $(this).siblings('.ym').removeClass('curr');
            $(".page .ym:first").addClass('curr');
            page = 1;
            getCont(page);
        }
    });

    //末页
    $('.page').on('click', '.last', function () {
        if (!newData.isLastPage) {
            $(this).siblings('.ym').removeClass('curr');
            $(".page .ym:last").addClass('curr');
            page = newData.pages;
            getCont(page);
        }

    });

    //上一页
    $('.page').on('click', '.pre', function () {
        console.log(newData.hasPreviousPage)
        if (newData.hasPreviousPage) {
            var curr = $('.page .curr');
            $('.page .ym').removeClass('curr');
            curr.prev('.ym').addClass('curr');
            page = curr.prev(".ym").text();
            getCont(page);
        }
    });

    //下一页
    $('.page').on('click', '.next', function () {
        if (newData.hasNextPage) {
            var curr = $('.page .curr');
            $('.page .ym').removeClass('curr');
            curr.next('.ym').addClass('curr');
            page = curr.next(".ym").text();
            getCont(page);
        }
    });

    addCo(".host li");
    addCo(".page .ym");
    //生成页码
    function getPage(data) {
        var html = "";
        for (var i = 2; i <= data.pages; i++) {
            html += '<span class="ym" href="javascript:void(0);">' + i + '</span>';
        }
        var headHtml = '<span class="first fe"  href="javascript:void(0);">首页</span><span class="pre"  href="javascript:void(0);"> </span><span class="ym curr" href="javascript:void(0);">1</span>'
        var footHtml = '<span class="next"  href="javascript:void(0);"> </span> <span class="last fe"   href="javascript:void(0);">末页</span> <span class="total">共 <em>' + data.pages + '</em> 页</span>'
        $(".page").empty();
        $('.page').append(headHtml + html + footHtml);
    }
    //获取翻页后内容
    function getCont(page) {
        console.log(page);
        $.ajax({
            type: 'POST',
            url:"/searchList/"+page,
            dataType: "json",
            data:{type:type,word:word,area:area,rank:rank},
            success: function (data) {
                console.log(data);
                newData=data;
                getHospital(data);

            }
        });
    }
    //循环医院列表
    function getHospital(data) {
        var hospitalHtml = "";
        for (var i = 0; i < data.list.length; i++) {
            hospitalHtml += '<li class="clearfix">' +
                '<div class="detail">' +
                '<img src="' + data.list[i].himg + '" alt="' + data.list[i].hname + '"/><div>' +
                '<a class="name" href="javascript:void(0);">' + data.list[i].hname + '</a><span class="rank">' + data.list[i].hrank + '等</span>' +
                '<span class="time">放号时间： ' + data.list[i].htime + '</span>' +
                '<span class="tell">咨询电话：' + data.list[i].htell + '</span>' +
                '<span class="adr">地址：' + data.list[i].hadr + '</span></div></div>' +
                '<a class="order" href="/hosSection/'+data.list[i].hid+'"><i></i>现在预约</a></li>'
        }
        $(".host_list").empty();
        $('.host_list').append(hospitalHtml);
    }
    //获取地址参数值
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return   decodeURI (r[2]); return null; //返回参数值
    }
</script>
</body>
</html>