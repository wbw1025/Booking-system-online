<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="mheader.jsp"%>
<div class="m_content wrap  clearfix">
    <div id="nav" class="nav fl">
        <a href="/mUser" dataUrl = "/mUserList">用户信息</a>
        <a  href="/mHos" dataUrl = "/mHosList">医院信息</a>
        <a href="/mSec" dataUrl = "/mSecList">科室信息</a>
        <a href="/mDoctor " dataUrl = "/mDoctorList">医生信息</a>
        <a  href="/mWork" dataUrl = "/mWorkList">医生排班信息</a>
        <a class="curr" href="/mOrder" dataUrl = "/mOrderList">预约信息</a>
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
        var nav={search:false,add:false,edit:false};
        var url=$(".nav a.curr").attr("dataUrl");
        var editurl = "/editorOrder";
        cName=['预约编号','预约人名称','预约时间','就诊时间','科室名称','预约医生','预约医院','预约状态'];
        cModel=[
            {name: 'oid', index: 'oid',width: 50,key:true,editable:false},
            {name: 'uname', index: 'uname', width: 50,editable:true},
            {name: 'otime', index: 'otime', width: 100,editable:true},
            {name: 'stime', index: 'stime', width: 100,editable:true},
            {name: 'sname', index: 'sname', width: 80,editable:true},
            {name: 'dname', index: 'dname', width: 50,editable:true},
            {name: 'hname', index: 'hname', width: 100,editable:true},
            {name: 'state', index: 'state', width: 100,editable:true},

        ];
        //使用jqGurid加载数据
        dataInit(url,cName,cModel,editurl,nav);
        console.log(url);
        jQuery('#hos').jqGrid({add:false});
    });
</script>
</body>
</html>