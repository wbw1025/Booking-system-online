/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2017-06-09 06:02:20 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class mUser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/views/mheader.jsp", Long.valueOf(1495852712144L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/static/style/global.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/static/style/jquery-ui-1.8.16.custom.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/static/style/ui.jqgrid.css\">\r\n");
      out.write("    <script src=\"/static/js/jquery-1.8.3.min.js\"></script>\r\n");
      out.write("    <script src=\"/static/js/jquery-ui-1.9.2.custom.min.js\"></script>\r\n");
      out.write("    <script src=\"/static/js/grid.locale-cn.js\"></script>\r\n");
      out.write("    <script src=\"/static/js/jquery.jqGrid.min.js\"></script>\r\n");
      out.write("    <title>挂号系统</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"m_header\">\r\n");
      out.write("    <div class=\"wrap clearfix\">\r\n");
      out.write("        <p class=\"logo fl\">\r\n");
      out.write("            <img src=\"/static/images/icon/logo.png\" alt=\"图标\">\r\n");
      out.write("            <span>健康114挂号平台管理系统</span>\r\n");
      out.write("        </p>\r\n");
      out.write("        <p class=\"user fr\">\r\n");
      out.write("            <span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginUser.uname}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>\r\n");
      out.write("            <a href=/login>[退出]</a>\r\n");
      out.write("            <img src=\"/static/images/upload/user.png\" alt=\"头像\">\r\n");
      out.write("        </p>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("<div class=\"m_content wrap  clearfix\">\r\n");
      out.write("    <div id=\"nav\" class=\"nav fl\">\r\n");
      out.write("        <a class=\"curr\" href=\"/mUser\" dataUrl = \"/mUserList\">用户信息</a>\r\n");
      out.write("        <a  href=\"/mHos\" dataUrl = \"/mHosList\">医院信息</a>\r\n");
      out.write("        <a href=\"/mSec\" dataUrl = \"/mSecList\">科室信息</a>\r\n");
      out.write("        <a  href=\"/mDoctor \" dataUrl = \"/mDoctorList\">医生信息</a>\r\n");
      out.write("        <a href=\"/mWork\" dataUrl = \"/mWorkList\">医生排班信息</a>\r\n");
      out.write("        <a href=\"/mOrder\" dataUrl = \"/mOrderList\">预约信息</a>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div id=\"cont\" class=\"cont fl\">\r\n");
      out.write("        <table id=\"hos\" class=\"mHos\"></table>\r\n");
      out.write("        <div id=\"pager\" class=\"mPage\"></div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("<script src=\"/static/js/dataInit.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("    $(function(){\r\n");
      out.write("        var cName=[];\r\n");
      out.write("        var cModel=[];\r\n");
      out.write("        var nav=\"\";\r\n");
      out.write("        var url=$(\".nav a.curr\").attr(\"dataUrl\");\r\n");
      out.write("        var editurl = \"/editorUser\";\r\n");
      out.write("        cName=['用户编号','用户名称','密码','管理员','邮箱'];\r\n");
      out.write("        cModel=[\r\n");
      out.write("            {name: 'uid', index: 'uid',width: 50,key:true,editable:false},\r\n");
      out.write("            {name: 'uname', index: 'uname', width: 50,editable:true},\r\n");
      out.write("            {name: 'upswd', index: 'upswd', width: 200,editable:true},\r\n");
      out.write("            {name: 'state', index: 'state', width: 50,editable:true},\r\n");
      out.write("            {name: 'uemail', index: 'uemail', width: 80,editable:true},\r\n");
      out.write("        ];\r\n");
      out.write("        console.log(url);\r\n");
      out.write("        //使用jqGurid加载数据\r\n");
      out.write("        dataInit(url,cName,cModel,editurl,nav);\r\n");
      out.write("        console.log(editurl);\r\n");
      out.write("\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
