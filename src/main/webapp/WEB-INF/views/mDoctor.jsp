<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="mheader.jsp"%>
<div class="m_content wrap  clearfix">
    <div id="nav" class="nav fl">
        <a href="/mUser" dataUrl = "/mUserList">用户信息</a>
        <a href="/mHos"  dataUrl = "/mHosList">医院信息</a>
        <a href="/mSec"  dataUrl = "/mSecList">科室信息</a>
        <a class="curr"  dataUrl = "/mDoctorList">医生信息</a>
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
        var editurl = "/editorDoctor";
        cName=['医生编号','医生名称','性别','职称','所属医院id','所属科室id','挂号费'];
        cModel=[
            {name: 'did', index: 'did',width: 50,key:true,editable:false},
            {name: 'dname', index: 'dname', width: 100,editable:true},
            {name: 'dsex', index: 'dsex', width: 50,editable:true},
            {name: 'dtitle', index: 'dtitle', width: 100,editable:true},
            {name: 'hospitalHid', index: 'hospitalHid', width: 50,editable:true},
            {name: 'sectionSid', index: 'sectionSid', width: 50,editable:true},
            {name: 'money', index: 'money', width: 50,editable:true},
        ];
        //使用jqGurid加载数据
        dataInit(url,cName,cModel,editurl,nav);
        console.log(url);
    });
</script>
</body>
</html>