/**
 * Created by admin on 2017/5/11.
 */
function dataInit(url,cName,cModel,editurl,nav) {
    if (nav==""){
        nav={search:false};
    }
    console.log(nav);
    jQuery('#hos').jqGrid({
        url: url,
        datatype: "json",
        mtype: "post",
        autowidth: true,
        height: 550,
        colNames: cName ,
        colModel: cModel,
        rowNum: 23,
        // viewrecords: true,
        rowList: [10, 23, 30],
        viewrecords : true,
        loadonce:true,
        sortorder : "desc",
        pager: jQuery('#pager'),
        editurl: editurl,

    });
    console.log(editurl);
    jQuery('#hos').jqGrid('navGrid','#pager',nav,{closeAfterEdit:true,
        closeAfterAdd:true,reloadAfterSubmit:true});
}
