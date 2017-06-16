<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="mheader.jsp"%>
<div class="m_content wrap  clearfix">
    <div id="nav" class="nav fl">
        <a class="curr" href="/mUser" dataUrl = "/mUserList">用户信息</a>
        <a  href="/mHos" dataUrl = "/mHosList">医院信息</a>
        <a href="/mSec" dataUrl = "/mSecList">科室信息</a>
        <a  href="/mDoctor " dataUrl = "/mDoctorList">医生信息</a>
        <a href="/mWork" dataUrl = "/mWorkList">医生排班信息</a>
        <a href="/mOrder" dataUrl = "/mOrderList">预约信息</a>
    </div>
    <div id="cont" class="cont fl">
        <table id="hos" class="mHos"></table>
        <div id="pager" class="mPage"></div>
    </div>
</div>
<script src="/static/js/dataInit.js"></script>
<script>
    $(function(){
        var cName=[];
        var cModel=[];
        var nav="";
        var url=$(".nav a.curr").attr("dataUrl");
        var editurl = "/editorUser";
        cName=['用户编号','用户名称','密码','管理员','邮箱'];
        cModel=[
            {name: 'uid', index: 'uid',width: 50,key:true,editable:false},
            {name: 'uname', index: 'uname', width: 50,editable:true},
            {name: 'upswd', index: 'upswd', width: 200,editable:true},
            {name: 'state', index: 'state', width: 50,editable:true},
            {name: 'uemail', index: 'uemail', width: 80,editable:true},
        ];
        console.log(url);
        //使用jqGurid加载数据
        dataInit(url,cName,cModel,editurl,nav);
        console.log(editurl);

    });



</script>
</body>
</html>