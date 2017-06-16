<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="mheader.jsp"%>
<div class="m_content wrap  clearfix">
    <div id="nav" class="nav fl">
        <a href="/mUser" dataUrl = "/mUserList">用户信息</a>
        <a class="curr" href="/mHos" dataUrl = "/mHosList">医院信息</a>
        <a href="/mSec" dataUrl = "/mSecList">科室信息</a>
        <a href="/mDoctor " dataUrl = "/mDoctorList">医生信息</a>
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
        var editurl = "/editorHos";
        cName=['医院编号','医院名称','地区','地址','电话','放号时间','等级','图片地址'];
        cModel=[
            {name: 'hid', index: 'hid',width: 50,key:true,editable:false},
            {name: 'hname', index: 'hname', width: 100,editable:true},
            {name: 'harea', index: 'harea', width: 50,editable:true},
            {name: 'hadr', index: 'hadr', width: 150,editable:true},
            {name: 'htell', index: 'htell', width: 80,editable:true},
            {name: 'htime', index: 'htime', width: 50,editable:true},
            {name: 'hrank', index: 'hrank', width: 50,editable:true},
            {name: 'himg', index: 'himg', width: 150,editable:true},
        ];
        //使用jqGurid加载数据
        dataInit(url,cName,cModel,editurl,nav);
        console.log(url);
    });
</script>
</body>
</html>