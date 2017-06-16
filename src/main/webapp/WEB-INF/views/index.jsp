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
<%@ include file="header.jsp" %>
<div class="nav_box">
    <div class="nav wrap">
        <a class="nav_li curr" href="/index">首页</a>
        <a href="/hospital">按医院挂号</a>
        <a href="/section">按科室挂号</a>
        <a href="/sick">按疾病挂号</a>
        <a href="/zom">个人中心</a>
    </div>
</div>
<div class="cont wrap">
    <div class="box">
        <ul class="img_box">
            <li><a href=""><img src="/static/images/upload/banner1.gif" alt=""/></a></li>
            <li><a href=""><img src="/static/images/upload/banner2.jpg" alt=""/></a></li>
            <li><a href=""><img src="/static/images/upload/banner3.jpg" alt=""/></a></li>
            <li><a href=""><img src="/static/images/upload/banner2.jpg" alt=""/></a></li>
            <li><a href=""><img src="/static/images/upload/banner3.jpg" alt=""/></a></li>
        </ul>
        <ol class="num">
            <li class="curr"></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ol>
    </div>
    <div class="order">
        <h3>快速预约</h3>
        <p><select class="area">
            <option value="">医院地区</option>
            <c:forEach items="${areaSet}" var="area">
                <option value="">${area}</option>
            </c:forEach>
        </select></p>
        <p><select class="rank">
            <option value="">医院等级</option>
            <c:forEach items="${rank}" var="rk">
                <option value="">${rk}</option>
            </c:forEach>
        </select></p>
        <p><select class="hos">
            <option value="">医院名称</option>
        </select></p>
        <p><select class="sec">
            <option value="">科室名称</option>
        </select></p>
        <p>
            <a class="refer"  href = "/hospital">快速预约</a>
        </p>
    </div>
</div>
<div class="list wrap">
    <p class="item"><span>医院</span></p>
    <p class="sub"><span class="curr">全部</span>
        <c:forEach items="${areaSet}" var="area">
            <span>${area}</span>
        </c:forEach>
    </p>
    <ul class="item_cont wrap clearfix"></ul>
    <a class="more wrap" href="hospital">更多医院</a>
</div>
<%@ include file="footer.jsp" %>

<!--js-->
<script src="/static/js/main.js"></script>
<script src="/static/js/picCarousel.js"></script>
<script>
    $(function () {
        //设置初始值 快速预约
        var page=1;
        var sid = 0;
        var hid = 0;
        var url = '';

        //加载6条医院信息
        $.ajax({
            type: "POST",
            url: "hospital/filter",
            data: {area: '全部'},
            success: function (data) {
                getHospital(data);
            }
        });
         //快速预约
        //select值改变 更改来链接地址
        $('.order select').change(function () {
        //初始值
            var area = $('.order .area option:selected').text();
            var rank = $('.order .rank option:selected').text();
            var hos = $('.order .hos option:selected').text();
            var sec = $('.order .sec option:selected').text();
            if (area=="医院地区"){
                area="全部";
            }
            if (rank == "医院等级"){
                rank="全部";
            }
            if(hos=="医院名称"){
                hos = "全部";
            }
            //有待改进  选择医院后跳转到科室信息页面
            if (sec =="科室名称"){
                url = "/hospital/?rank="+rank+'&area='+area+'&hos='+hos;
                $('.order .refer').attr('href',url);
            }else {
                //查询医院id ,科室id(预约信息是根据医院科室id 进行查询的)
                $.ajax({
                    type:"POST",
                    url:"/queryId",
                    data:{hName:hos,sName:sec},
                    success:function (data) {
                        console.log(data);
                        hid=data[0];
                        sid=data[1];
                        url = "/order/"+hid+'/'+sid;
                        console.log(url);
                        $('.order .refer').attr('href',url);
                    }
                });
            }

        });

        //获取医院信息
        $('.order .area,.rank').change(function () {
            var html = "";
            var area = $('.order .area option:selected').text();
            var rank = $('.order .rank option:selected').text();
            $.ajax({
                type: "POST",
                url: "/quickHos",
                data: {area: area, rank: rank},
                success: function (data) {
                    html = "";
                    if (data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            html += '<option value="">' + data[i].hname+'</option>';
                        }
                        html = '<option value="">医院名称</option>' + html;
                    } else {
                        html = '<option value="">医院名称</option>';
                    }
                    $('.order .hos').empty().append(html);
                }
            })
        });
        //获取科室信息
        $('.order .hos').change(function () {
            var html = "";
            var hos = $('.order .hos option:selected').text();
            $.ajax({
                type: "POST",
                url: "/quickSec",
                data: {hos:hos},
                success: function (data) {
                    html = "";
                    if (data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            html += '<option value="">' + data[i].sname+'</option>';
                        }
                        html = '<option value="">科室名称</option>' + html;
                    } else {
                        html = '<option value="">科室名称</option>';
                    }
                    $('.order .sec').empty().append(html);
                }
            })
        });

         //根据地区筛选医院
                var area = $(".sub span");
                area.on('click', function () {
                    var currVal = $(this).text();
                    $.ajax({
                        type: "POST",
                        url: "hospital/filter",
                        data: {area: currVal},
                        success: function (data) {
                            $('.item_cont').empty();
                            getHospital(data);
                        }
                    });
                });

        //图片的轮播
        picCarousel();

        //nav点击效果
        addCo(".sub span");
    });

    //医院信息写入页面
    function getHospital(data) {
        var html = "";
        for (var i = 0; i < data.length; i++) {
            html += '<li> <a class="img" href=""><img src="' + data[i].himg + ' "alt="' + data[i].hname + '"/></a>' +
                '<p class="title"><a href="">' + data[i].hname + '</a><span>【' + data[i].hrank + '】</span></p>' +
                '<p class="tell">电话：' + data[i].htell + '</p>' +
                '<p class="adr">地址： ' + data[i].hadr + '</p> </li>'
        }
        $('.item_cont').append(html);
    }
</script>
</body>
</html>