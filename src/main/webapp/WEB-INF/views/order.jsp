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
            <h3 class="hos_tit">${hosInfo.hname}-${secInfo.sname}</h3>

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
    <h3 class="time_tit">开放科室</h3>

    <div class="appointment clearfix">
        <c:choose>
        <c:when test="${orderInfo!=null && fn:length(orderInfo) > 0}">
        <div class="day">
            <a class="pre" href="javascript:void(0);"></a>
            <ul>
                <li>上午</li>
                <li>下午</li>
            </ul>
        </div>
        <div class="time_box">
            <ul class="time_list">
                <li class="first clearfix">
                    <c:forEach items="${orderInfo}" var="order">
                        <span>${order.date}</span>
                    </c:forEach>
                </li>
                <li class="am otime clearfix" datatype="1">
                    <c:forEach items="${orderInfo}" var="order">
                       <c:if test="${order.anum >0}">
                           <a>预约<span>剩余：${order.anum}</span></a>
                       </c:if>
                        <c:if test="${order.anum <=0} ">
                            <a>约满</a>
                        </c:if>
                    </c:forEach>
                </li>
                <li class="pm otime clearfix" datatype="-1">
                    <c:forEach items="${orderInfo}" var="order">
                        <c:if test="${order.pnum >0}">
                            <a>预约<span>剩余：${order.pnum}</span></a>
                        </c:if>
                        <c:if test="${order.pnum <=0}">
                            <a>约满</a>
                        </c:if>
                    </c:forEach>
                </li>
            </ul>
        </div>
        <div class="day">
        <a class="next" href="javascript:void(0);"></a>
        <ul>
        <li></li>
        <li></li>
        </ul>
        </div>
        <div class="info">
            <h4>预约规则</h4>
            <ul>
                <li><span>更新时间：</span>每日08:00更新</li>
                <li><span>预约周期：</span>28</li>
                <li><span>停挂时间：</span> 下午14:00停止次日预约挂号 （周五14:00后停挂至下周一）</li>
                <li><span>退号时间：</span> 就诊前一工作日14:00前取消</li>
            </ul>
        </div>
    </div>
    <div class="doctor_box">
        <p class="hint">您还没有选择就诊日期</p>
        <ul class="doctor">

        </ul>
    </div>
    </c:when>
    <c:otherwise>
        <p class="msg">暂无数据</p>
    </c:otherwise>
    </c:choose>

</div>
<%@include file="footer.jsp" %>
<!--js-->
<script src="/static/js/main.js"></script>
<script>
    $(function () {
        var $a = $(".otime a");
            $a.on('click', function () {
                var $txt = $(this).find('span').text();//是否有可挂号
                var index = $(this).index();
                var date = $('.first span').eq(index).text();//选择日期
                var num = $(this).parent('li').attr('datatype');//预约是上午（1）或下午（-1）
                if ($txt) {
                    $('.otime a').removeClass('curr');
                    $(this).addClass('curr');
                    $.ajax({
                        url:"/doctorInfo",
                        data:{hosId:${hosInfo.hid},secId:${secInfo.sid},date:date,num:num},
                        success:function (data) {
                            if(data.length>0){
                                var html='';
                                var ber=0;
                                for(var i=0;i<data.length;i++){
                                    if (data[i].anumc !=null){
                                       ber = data[i].anumc;
                                    }else {
                                        ber = data[i].pnumc;
                                    }
                                    html +='<li class="clearfix">'+
                                        '<div class="intro fl">'+
                                        '<img src="/static/images/upload/doctor.jpg" alt="'+data[i].dname+'"/>'+
                                        '<p> <span>'+data[i].dname+'</span>'+
                                       '<i>'+data[i].dtitle+'</i> </p> </div>'+
                                        '<p class="money fl"> <span>挂号费：<i>￥'+data[i].money+'</i></span></br>'+
                                    '<span>剩余号：<em>'+ber+'</em></span></p>'+
                                    '<input class="fl" type="button" value="预约挂号"/></li>';
                                }
                                $(".doctor").html("");
                                $('.doctor').append(html);
                            }
                        }
                    })
                    doctorList();//显示医生列表
                }
            });
        changePage();//点击移动时间
        $(".doctor ").on("click","input",function () {
//            var uname = $('.header .uname').text();
            var hname= '${hosInfo.hname}'; //医院名称
            var sname = '${secInfo.sname}'; //医院科室
            var dname = $(this).siblings("div").find('span').text(); //选择的医生名称
            var did=$(this).siblings("div").find('.did').text();
            var hadr = '${hosInfo.hadr}'; //医院地址
            var money = ($(this).siblings('p').find("span:first").text()).substr(5,1); //挂号费
            var index= $(".otime .curr").index();//获取选择的index
            var time=$('.first span').eq(index).text(); //选择的日期
            var apm = $('.otime .curr').parent('li').attr("datatype"); //上午 下午
            /*console.log(did);
            if(apm=='1'){
                apm = "上午";
            }else {
                apm = "下午";
            }
            time = time+'  '+apm;
            console.log(time);*/
            //调用order方法
            order(hname,sname,dname,money,time,hadr,did,apm);

        })
    });

    //显示医生列表
    function doctorList() {
        $('.hint').hide();//隐藏暂无数据
        $('.doctor').slideDown();
    }

    //点击移动时间
    function changePage() {
    console.log("翻页");
        var $aLength = $('.first span').length;
        var index = 1;
        $('.day .next').on('click', function () {
            var $changeL = index * 100; //每次移动的长度
            if (index < $aLength - 6) {
                index = index + 1;
                $(".time_list").animate({left: -$changeL + "px"});
            }
        });
        $('.day .pre').on('click', function () {
            var $cLength = parseInt($('.time_list').css('left'));
            var $changeL = $cLength + 100;
            if (index > 1) {
                index = index - 1;
                console.log("pre:" + index);
                $(".time_list").animate({left: $changeL + "px"});
            }
        });
    }

    //将用户选中的挂号信息存入session
    function order(hname,sname,dname,money,time,hadr,did,ap){
        console.log(hname,sname,dname,money,time,hadr,did);
        $.ajax({
            type:"POST",
            url:"/gOrder",
            data:{hname:hname,sname:sname,dname:dname,money:money,time:time,hadr:hadr,id:did,ap:ap},
            success:function (data) {
                console.log(data);
                if (data){
                   window.location.href='/pay';
                }

            }
        });
    }

</script>
</body>
</html>