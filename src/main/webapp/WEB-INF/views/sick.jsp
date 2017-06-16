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
        <a href="/section">按科室挂号</a>
        <a class="curr" href="/sick">按疾病挂号</a>
        <a href="/zom">个人中心</a>
    </div>
</div>
<div class="list wrap clearfix">
    <div class="reg">
        <a>疾病列表</a>
    </div>
    <div class="sick_side fl">
        <div class="bg  man">
            <div class="sick_btn">
                <a class="btn_p" href="javascript:void(0);"> </a>
                <a class="btn_z" href="javascript:void(0);"></a>
            </div>
            <div class="link man_link">
                <a class="tb" href="javascript:void(0);" datatype="头部"></a>
                <a class="qs" href="javascript:void(0);" datatype="全身"></a>
                <a class="sz" href="javascript:void(0);" datatype="四肢"></a>
                <a class="xb" href="javascript:void(0);" datatype="胸部"></a>
                <a class="fb" href="javascript:void(0);" datatype="腹部"></a>
                <a class="xs" href="javascript:void(0);" datatype="男下身"></a>
            </div>
        </div>
        <div class="bg manb">
            <div class="sick_btn">
                <a class="btn_p" href="javascript:void(0);"> </a>
                <a class="btn_z" href="javascript:void(0);"></a>
            </div>
            <div class="link manb_link">
                <a class="qs" href="javascript:void(0);" datatype="全身"></a>
                <a class="gt" href="javascript:void(0);" datatype="骨"></a>
                <a class="ytb" href="javascript:void(0);" datatype="腰臀"></a>
            </div>
        </div>
        <div class="bg women">
            <div class="sick_btn">
                <a class="btn_p" href="javascript:void(0);"> </a>
                <a class="btn_z" href="javascript:void(0);"></a>
            </div>
            <div class="link women_link">
                <a class="tb" href="javascript:void(0);" datatype="头部"></a>
                <a class="qs" href="javascript:void(0);" datatype="全身"></a>
                <a class="sz" href="javascript:void(0);" datatype="四肢"></a>
                <a class="xb" href="javascript:void(0);" datatype="胸部"></a>
                <a class="fb" href="javascript:void(0);" datatype="腹部"></a>
                <a class="xs" href="javascript:void(0);" datatype="女下身"></a>
            </div>
        </div>
        <div class="bg womenb">
            <div class="sick_btn">
                <a class="btn_p" href="javascript:void(0);"> </a>
                <a class="btn_z" href="javascript:void(0);"></a>
            </div>
            <div class="link womenb_link">
                <a class="qs" href="javascript:void(0);" datatype="全身"></a>
                <a class="gt" href="javascript:void(0);" datatype="骨"></a>
                <a class="ytb" href="javascript:void(0);" datatype="腰臀"></a>
            </div>
        </div>
        <div class="bg children"></div>
        <div class="bg old"></div>
        <div class="sick_p">
            <a class="curr" href="javascript:void(0);" datatype="全身">成人</a>
            <a href="javascript:void(0);" datatype="老人">老人</a>
            <a href="javascript:void(0);" datatype="小儿">儿童</a>
        </div>
    </div>
    <div class="sick_main fl">

        <%--<div class="sick_cont  older">
            <h3>老人</h3>
            //重复内容结构
            <div class="sick_list clearfix">
                <p>
                    <img src="images/upload/older.jpg" alt="老人"/>
                    <span>老人</span>
                </p>
                <ul>
                    <li><a href="">痴呆</a></li>
                    <li><a href="">肺炎</a></li>
                </ul>
            </div>
        </div>--%>

    </div>
</div>
<%@include file="footer.jsp" %>
<!--js-->
<script>

    var body ="全身";
    $(function () {
        getData(body);
        ageGroup();
        changeFace(".man", ".manb");//man
        changeSex(".man", ".women");
        changeFace(".manb", ".man");//manb
        changeSex(".manb", ".womenb");
        changeFace(".women", ".womenb");
        changeSex(".women", ".man");
        changeFace(".womenb", ".women");
        changeSex(".womenb", ".manb");

        //点击不同区域显示相应数据
        $('.sick_side .link a').on('click', function () {
            body = $(this).attr('datatype');
            getData(body);
        });
        $('.sick_p').on('click', 'a', function () {
            body = $(this).attr('datatype');
            getData(body);
        });
    });
    //一次性加载疾病信息
               /* var cont = "";//内容
                var hHtml = "";//标题
                var pHtml = "";//一级内容
                var cHtml = "";//二级内容
                var html = "";//合并一二级内容
                console.log(data[1].second[0].sick[0].name);
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        hHtml = '';
                        hHtml = '<h3>' + data[i].first.body + '</h3>';
                        if (data[i].second.length > 0) {
                            for (var j = 0; j < data[i].second.length; j++) {
                                if (data[i].second[j].sick.length > 0) {
                                    pHtml = "";
                                    cHtml = "";
                                    pHtml = '<p><img src="' + data[i].second[j].body.img + '" alt="' + data[i].second[j].body.name + '"/> <span>' + data[i].second[j].body.name + '</span> </p>';
                                    for (var s = 0; s < data[i].second[j].sick.length; s++) {
                                        cHtml += '<li><a href="/hospital/' + data[i].second[j].sick[s].section_sid + '">' + data[i].second[j].sick[s].name + '</a></li>'
                                    }
                                    html += '<div class="sick_list clearfix">' + pHtml + '<ul>' + cHtml + '</ul></div>';
                                }
                            }
                        }
                        cont += '<div class="sick_cont">' + hHtml + html + '</div>';
                    }
                    $('.sick_main').append(cont);
                }*/


    //获取后台数据 加载到页面
    function getData(body){
        console.log("ajax"+body);
        $.ajax({
            type:"post",
            url:"/sickList",
            data:{body:body},
            success:function (data) {
                console.log(data);
                var  title="";//一级标题
                var  tit="";//二级标题
                var  li ="";//列表
                var  cont="";//内容
                var html="";//组合内容标题
                console.log(html);
                if(data.length >0){
                    title='<h3>'+body+'</h3>';
                    for (var i=0; i<data.length;i++){
                        var error="";//无数据提示信息
                        if(data[i].sick.length >0){
                            error="";
                            tit = '<p><img src="'+data[i].body.img +'" alt="' + data[i].body.name + '"/> <span>' + data[i].body.name + '</span> </p>';
                            for (var j = 0; j < data[i].sick.length; j++) {
                                li += '<li><a href="/hospital/' + data[i].sick[j].section_sid + '">' + data[i].sick[j].name + '</a></li>'
                            }
                            cont += '<div class="sick_list clearfix">' + tit + '<ul>' + li + '</ul></div>';
                        } else { var error = '<p class="msg">暂无数据</p>';}
                    }
                    html ='<div class="sick_cont">' + title + cont +error + '</div>';
                }else { html='<p class="msg">暂无数据</p>'}

                $('.sick_main').empty();
                $('.sick_main').append(html);
            }

        })
    }
    //    改变正反面
    function changeFace(elem, show) {
        $(elem).find(".btn_z").click(function () {
            $(elem).fadeOut();
            $(show).fadeIn();
        });
    }

    //        改变性别
    function changeSex(elem, show) {
        $(elem).find(".btn_p").click(function () {
            $(elem).fadeOut();
            $(show).fadeIn();
        });
    }
    //    年龄层次的区分  成人  老人  儿童
    function ageGroup() {
        $('.sick_p a').on('click', function () {
            $(this).addClass('curr').siblings().removeClass('curr');
            var $val = $(this).text();
            if ($val == "成人") {
                $(".man").fadeIn().siblings('div.bg').fadeOut();
                $('.mbody').show().siblings().hide();
            } else if ($val == '老人') {
                $(".old").fadeIn().siblings('div.bg').fadeOut();
                $('.older').show().siblings().hide();
            } else {
                $(".children").fadeIn().siblings('div.bg').fadeOut();
                $('.child').show().siblings().hide();
            }
        });
    }

</script>
</body>
</html>