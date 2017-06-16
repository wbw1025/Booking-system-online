<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="mheader.jsp"%>
<div class="m_content wrap  clearfix">
    <div id="nav" class="nav fl">
        <a href="/mUser" dataUrl = "/mUserList">用户信息</a>
        <a href="/mHos" dataUrl = "/mHosList">医院信息</a>
        <a class="curr"  href="/mSec" dataUrl = "/mSecList">科室信息</a>
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
        var editurl = "/editorSec";
        cName=['科室编号','科室名称','所属科室编号'];
        cModel=[
            {name: 'sid', index: 'sid',width: 50,key:true,editable:false},
            {name: 'sname', index: 'sname', width: 100,editable:true},
            {name: 'type', index: 'type', width: 50,editable:true},

        ];
        //使用jqGurid加载数据
        dataInit(url,cName,cModel,editurl,nav);
        jQuery('#hos').jqGrid('navGrid',{closeAfterEdit:true,
            closeAfterAdd:true,reloadAfterSubmit:true});
        console.log(url);
    });
</script>
</body>
</html>