<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="mheader.jsp"%>
<div class="m_content wrap  clearfix">
    <div id="nav" class="nav fl">
        <a href="/mUser" dataUrl = "/mUserList">用户信息</a>
        <a href="/mHos" dataUrl = "/mHosList">医院信息</a>
        <a href="/mSec" dataUrl = "/mSecList">科室信息</a>
        <a href="/mDoctor " dataUrl = "/mDoctorList">医生信息</a>
        <a class="curr"  dataUrl = "/mWorkList">医生排班信息</a>
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
        var editurl = "/editorWork";
        cName=['排班编号','医生姓名','排班日期','上午可挂号数','下午可挂号数'];
        cModel=[
            {name: 'wid', index: 'wid',width: 50,key:true,editable:false},
            {name: 'name', index: 'name', width: 50,editable:true},
            {name: 'date', index: 'date', width: 100,editable:true},
            {name: 'anum', index: 'anum', width: 50,editable:true},
            {name: 'pnum', index: 'pnum', width: 50,editable:true},
        ];
        console.log(url);
        //使用jqGurid加载数据
        dataInit(url,cName,cModel,editurl,nav);
        console.log(editurl);

    });



</script>
</body>
</html>