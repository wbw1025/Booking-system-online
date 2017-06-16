<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/style/global.css"/>
    <script src="/static/js/jquery-1.8.3.min.js"></script>
    <script src="/static/js/jquery.validate.min.js"></script>
    <script src="/static/js/messages_zh.js"></script>
    <title>挂号系统</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="nav_box">
    <div class="nav wrap">
        <a class="nav_li" href="/index">首页</a>
        <a href="/hospital">按医院挂号</a>
        <a href="/section">按科室挂号</a>
        <a href="/sick">按疾病挂号</a>
        <a class="curr" href="/zom">个人中心</a>
    </div>
</div>
<div class="center wrap clearfix">
    <div class="side fl">
        <img src="/static/images/upload/user.png" alt="头像"/>
        <p>欢迎您！<span class="name">${loginUser.uname}</span> <em style="display: none">${loginUser.uid}</em></p>
        <ul>
            <li class="curr">预约信息</li>
            <li>个人信息</li>
            <li>密码修改</li>
            <li>意见反馈</li>
        </ul>
    </div>
    <div class="main fl">
        <h3>预约信息</h3>

        <div class="main_order clearfix">
                <c:choose>
                    <c:when test="${orderInfo!=null && fn:length(orderInfo) > 0}">
                    <table>
                        <tr class="user_tit">
                            <th>预约编号</th>
                            <th>预约人</th>
                            <th>就诊时间</th>
                            <th> &nbsp&nbsp 预约医院 &nbsp&nbsp  </th>
                            <th>预约科室</th>
                            <th>预约医师</th>
                            <th>订单状态</th>
                            <th>操作</th>
                            <th>  </th>
                        </tr>
                        <c:forEach items="#{orderInfo}" var="order">
                            <tr>
                                <td class="id">${order.oid}</td>
                                <td class="uname">${order.uname}</td>
                                <td class="stime">${order.stime}</td>
                                <td class="hname">${order.hname}</td>
                                <td class="sname">${order.sname}</td>
                                <td class="dname">${order.dname}</td>
                                <td class="state">${order.state}</td>
                                <c:if test="${order.state=='无效'}">
                                    <td class="btn brace"><input type="button" value="取消"></td>
                                    <td class="btn del"><input type="button" value="删除"></td>
                                </c:if>
                                <c:if test="${order.state=='正常'}">
                                    <td class="btn cancel"><input type="button" value="取消"></td>
                                    <td class="btn brace"><input type="button" value="删除"></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                    </c:when>
                    <c:otherwise>
                        <p class="msg">暂无预约信息</p>
                    </c:otherwise>
                </c:choose>
        </div>
        <div class="main_person">
            <ul>
                <li>用户名: <span>${loginUser.uname}</span></li>
                <li>注册邮箱: <span>${loginUser.uemail}</span></li>
            </ul>
        </div>

        <div class="main_psw">
            <form id="change_form">
                <ul class="psw_list">
                    <li>注册邮箱：<input class="email" name="email" type="email" value="${loginUser.uemail}"/></li>
                    <li class="text">新密码：<input id="newpass" class="new_psw" name="password" type="password"/></li>
                    <li class="text">重复新密码：<input class="affirm_psw" name="vertifypass" type="password"/></li>
                </ul>
                <input class="btn" type="submit" value="确认修改"/>
            </form>
        </div>
        <div class="main_idea clearfix">
            <textarea>请输入您的意见，我们会根据您的意见反馈认真改进</textarea>
            <input type="button" value="递交意见"/>
            <ul class="idea_list">
                <c:forEach items="${ideaInfo}" var="idea">
                    <li><span>${idea.imsg}</span> <em>${idea.idate}</em></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<!--js-->
<script type="text/javascript">

    //左侧菜单栏点击
    $(function () {
        $('.side li').on('click', function () {
            $('.side li').each(function () {
                $(this).click(function () {
                    var $val = $(this).text();
                    var index = $(this).index();
                    console.log($val);
                    $(this).addClass('curr').siblings().removeClass('curr');
                    $('.main > h3').text($val);
                    $('.main > div').eq(index).show().siblings('div').hide();
                })
            })
        });
        //密码修改
        $('#change_form').validate({
            rules: {
                password: {
                    required: true,
                },
                vertifypass: {
                    required: true,
                    equalTo: '#newpass'
                }

            }
        });

        // 意见反馈
        //获取焦点时清空内容
        $('.main_idea textarea').focus(function () {
            var def = "请输入您的意见，我们会根据您的意见反馈认真改进";
           var txt= $(this).text();
           console.log(txt);
           if(txt == def){
               $('.main_idea textarea').text("");
           }
        });
        //提交意见到后台
        $('.main_idea input').on('click', function () {
            var idea = $('.main_idea textarea').val(); //获取填写内容
            var uid = ${loginUser.uid}; //用户的id
            var mydate = new Date();//获取系统日期
            //年月日用-分隔
            var str = "" + mydate.getFullYear() + "-";
            str += (mydate.getMonth()+1) + "-";
            str += mydate.getDate();
            //提交后台处理
            $.ajax({
                type: "POST",
                url: "/idea",
                data: {uid: uid, idea: idea},
                success: function (data) {
                    console.log(data.status);
                    if(data.status == 200){
                        var html = '<li><span>'+idea+'</span><em>'+str+'<em></li>';
                        $('.idea_list').prepend(html);
                    }else {
                        alert('内容不能为空！')
                    }
                }
            })
        });

//        预约管理
        //取消
        $('.main .cancel').on("click",function () {
            var id = $(this).siblings("td.id").text(); //预约编号
            var dname=$(this).siblings("td.dname").text(); //医生的名称
            var date =$(this).siblings("td.stime").text(); //预约就诊的时间
            $(this).siblings().removeClass('on');
            $(this).addClass("on")
            console.log(id);
            //提交后台处理
            $.ajax({
                type:"POST",
                url:"/updateOrder",
                data:{id:id,dname:dname,date:date},
                success:function (data) {
                    console.log(data);
                    if (data){
                        $('.on').removeClass("cancel")
                         $('.on').addClass("brace");
                        $('.on').siblings(".btn").removeClass("brace").addClass("del");
                        $('.on').prev("td").text('无效');
                    }
                }
            })
        });

        //删除
        $('.main .del').on("click",function () {
            var id = $(this).siblings("td:first").text();
            $(this).parent("tr").removeClass('ondel');
            $(this).parent("tr").addClass("ondel")
            //提交后台处理
            $.ajax({
                type:"POST",
                url:"/delOrder",
                data:{id:id},
                success:function (data) {
                    console.log(data);
                    if (data){
                        $('.main_order .ondel').remove();
                    }
                }
            })
        });
    });

    // 密码修改验证通过后 提交数据
    $.validator.setDefaults({
        submitHandler: function () {
            $.ajax({
                type: "POST",
                url: "/user/resetpass",
                data: $('#change_form').serialize(),
                success: function (data) {
                    if (data.status == '200') {
                        alert("密码修改成功！")
                        $(".main_psw  .text").find("input").val("");
                    }
                }
            })
        }
    });
</script>
</body>
</html>