/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.93
 * Generated at: 2024-09-27 11:33:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menubar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1727435740545L));
    _jspx_dependants.put("jar:file:/C:/moduerp_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/moduerp/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write(" \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Navigation Menu</title>\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("body {\r\n");
      out.write("    margin-top: 60px; /* 네비게이션 바 높이만큼 상단 여백 설정 */\r\n");
      out.write("    font-family: 'Arial', sans-serif;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* 상단 메뉴바 */\r\n");
      out.write("nav {\r\n");
      out.write("	padding: 8px ;\r\n");
      out.write("    width: 100%; /* 메뉴바 너비 */\r\n");
      out.write("    position: fixed;\r\n");
      out.write("    top: 0;\r\n");
      out.write("    left: 0;\r\n");
      out.write("    height: 60px;\r\n");
      out.write("    background: white;\r\n");
      out.write("    color: black;\r\n");
      out.write("    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 그림자 효과 */\r\n");
      out.write("    display: flex; /* nav 안의 요소들을 한 줄에 배치 */\r\n");
      out.write("    align-items: center;\r\n");
      out.write("}\r\n");
      out.write("nav ul {\r\n");
      out.write("    list-style: none;\r\n");
      out.write("    padding: 0;\r\n");
      out.write("    margin: 0;\r\n");
      out.write("    display: flex;\r\n");
      out.write("    justify-content: start;\r\n");
      out.write("    align-items: center;\r\n");
      out.write("    height: 100%;\r\n");
      out.write("}\r\n");
      out.write("nav ul li {\r\n");
      out.write("    margin: 0 20px; /* 메뉴 항목 간격 */\r\n");
      out.write("}\r\n");
      out.write("nav ul li a {\r\n");
      out.write("    color: black;\r\n");
      out.write("    text-decoration: none;\r\n");
      out.write("    padding: 15px 20px;\r\n");
      out.write("    display: block;\r\n");
      out.write("    font-size: 16px; /* 텍스트 크기 */\r\n");
      out.write("    position: relative; /* 밑줄에 대한 상대 위치 설정 */\r\n");
      out.write("    transition: background 0.3s ease, transform 0.3s ease; /* 호버 효과 애니메이션 */\r\n");
      out.write("}\r\n");
      out.write("nav ul li a::after {\r\n");
      out.write("    content: '';\r\n");
      out.write("    position: absolute;\r\n");
      out.write("    width: 0;\r\n");
      out.write("    height: 3px;\r\n");
      out.write("    background: black; /* 밑줄 색상 */\r\n");
      out.write("    left: 0;\r\n");
      out.write("    bottom: 0;\r\n");
      out.write("    transition: width 0.3s ease; /* 밑줄 애니메이션 */\r\n");
      out.write("}\r\n");
      out.write("nav ul li a:hover::after {\r\n");
      out.write("    width: 100%; /* 호버 시 밑줄이 왼쪽에서 오른쪽으로 그어짐 */\r\n");
      out.write("}\r\n");
      out.write("nav ul li a i {\r\n");
      out.write("    margin-right: 10px; /* 아이콘과 텍스트 간격 */\r\n");
      out.write("}\r\n");
      out.write("#logout-button {\r\n");
      out.write("    width: 15px;\r\n");
      out.write("    height: 20px;\r\n");
      out.write("    margin: 10px;\r\n");
      out.write("    background: #E74C3C; /* 로그아웃 버튼 색 */\r\n");
      out.write("    border-radius: 50%;\r\n");
      out.write("    display: flex;\r\n");
      out.write("    justify-content: center;\r\n");
      out.write("    align-items: center;\r\n");
      out.write("    color: white;\r\n");
      out.write("    text-decoration: none;\r\n");
      out.write("    transition: background 0.3s ease;\r\n");
      out.write("}\r\n");
      out.write("#logout-button:hover {\r\n");
      out.write("    background: #C0392B; /* 호버 시 버튼 색상 변경 */\r\n");
      out.write("}\r\n");
      out.write("nav .moduerp-logo {\r\n");
      out.write("    font-size: 28px; /* ModuERP 글씨 크기 */\r\n");
      out.write("    font-weight: bold;\r\n");
      out.write("    color: black;\r\n");
      out.write("    margin-right: 20px; /* 메뉴바와 로고 거리*/\r\n");
      out.write("    padding-left: 20px;\r\n");
      out.write("    text-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* 모달 창 스타일 */\r\n");
      out.write(".modal {\r\n");
      out.write("    display: none; /* 기본적으로 숨김 */\r\n");
      out.write("    position: fixed;\r\n");
      out.write("    z-index: 1;\r\n");
      out.write("    left: 0;\r\n");
      out.write("    top: 0;\r\n");
      out.write("    width: 100%;\r\n");
      out.write("    height: 100%;\r\n");
      out.write("    background-color: rgba(0,0,0,0.4); /* 배경 어둡게 */\r\n");
      out.write("    justify-content: center;\r\n");
      out.write("    align-items: center;\r\n");
      out.write("}\r\n");
      out.write(".modal-content {\r\n");
      out.write("    background-color: #fefefe;\r\n");
      out.write("    padding: 20px;\r\n");
      out.write("    border-radius: 10px;\r\n");
      out.write("    box-shadow: 0 5px 15px rgba(0,0,0,0.3);\r\n");
      out.write("    width: 300px;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("}\r\n");
      out.write(".modal-content button {\r\n");
      out.write("    margin-top: 20px;\r\n");
      out.write("    padding: 10px;\r\n");
      out.write("    width: 100%;\r\n");
      out.write("    border: none;\r\n");
      out.write("    background-color: navy;\r\n");
      out.write("    color: white;\r\n");
      out.write("    font-size: 16px;\r\n");
      out.write("    cursor: pointer;\r\n");
      out.write("}\r\n");
      out.write(".modal-content button:hover {\r\n");
      out.write("    background-color: #444;\r\n");
      out.write("}\r\n");
      out.write(".close {\r\n");
      out.write("    color: #aaa;\r\n");
      out.write("    float: right;\r\n");
      out.write("    font-size: 28px;\r\n");
      out.write("    font-weight: bold;\r\n");
      out.write("    cursor: pointer;\r\n");
      out.write("}\r\n");
      out.write(".close:hover,\r\n");
      out.write(".close:focus {\r\n");
      out.write("    color: black;\r\n");
      out.write("    text-decoration: none;\r\n");
      out.write("    cursor: pointer;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("<!-- Font Awesome for icons -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css\">\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("// 로그인 모달 열기\r\n");
      out.write("function openLoginModal() {\r\n");
      out.write("    document.getElementById(\"loginModal\").style.display = \"flex\";\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("// 로그인 모달 닫기\r\n");
      out.write("function closeLoginModal() {\r\n");
      out.write("    document.getElementById(\"loginModal\").style.display = \"none\";\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("// 모달 외부 클릭 시 닫기\r\n");
      out.write("window.onclick = function(event) {\r\n");
      out.write("    var modal = document.getElementById(\"loginModal\");\r\n");
      out.write("    if (event.target == modal) {\r\n");
      out.write("        modal.style.display = \"none\";\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 로그인 모달 -->\r\n");
      out.write("<div id=\"loginModal\" class=\"modal\">\r\n");
      out.write("    <div class=\"modal-content\">\r\n");
      out.write("        <span class=\"close\" onclick=\"closeLoginModal()\">&times;</span>\r\n");
      out.write("        <h2>로그인</h2>\r\n");
      out.write("        <form action=\"login.do\" method=\"post\">\r\n");
      out.write("            <input type=\"text\" name=\"userId\" placeholder=\"아이디\" required><br><br>\r\n");
      out.write("            <input type=\"password\" name=\"userPwd\" placeholder=\"비밀번호\" required><br><br>\r\n");
      out.write("            <button type=\"submit\">로그인</button>\r\n");
      out.write("        </form>\r\n");
      out.write("        <br>\r\n");
      out.write("        <button onclick=\"location.href='enrollPage.do'\">회원가입</button>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f0_reused = false;
    try {
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(null);
      // /WEB-INF/views/common/menubar.jsp(167,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ empty sessionScope.loginUser }", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("	<nav>\r\n");
          out.write("		<a href=\"main.do\" class=\"moduerp-logo\">ModuERP</a>\r\n");
          out.write("		<ul id=\"menubar\">\r\n");
          out.write("			<li><a href=\"nlist.do?page=1\"><i class=\"fas fa-bullhorn\"></i>공지사항</a></li>\r\n");
          out.write("			<li><a href=\"blist.do?page=1\"><i class=\"fas fa-clipboard\"></i>구매</a></li>\r\n");
          out.write("			<li><a href=\"moveAjax.do\"><i class=\"fas fa-code\"></i>체험페이지</a></li>\r\n");
          out.write("			<li><a href=\"moveApi.do\"><i class=\"fas fa-plug\"></i>고객서비스</a></li>\r\n");
          out.write("			<li><a href=\"moveApi.do\"><i class=\"fas fa-plug\"></i>회사소개</a></li>\r\n");
          out.write("		</ul>\r\n");
          out.write("		<ul>\r\n");
          out.write("		    <li><a href=\"#\" onclick=\"openLoginModal()\"><i class=\"fas fa-sign-in-alt\"></i> 로그인</a></li>\r\n");
          out.write("		</ul>\r\n");
          out.write("	</nav>\r\n");
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      _jspx_th_c_005fif_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f1_reused = false;
    try {
      _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f1.setParent(null);
      // /WEB-INF/views/common/menubar.jsp(183,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ !empty sessionScope.loginUser and sessionScope.loginUser.adminYN eq 'Y' }", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
      if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("	<nav>\r\n");
          out.write("		<a href=\"main.do\" class=\"moduerp-logo\">ModuERP - Adminpage</a>\r\n");
          out.write("		<ul id=\"menubar\">\r\n");
          out.write("			<li><a href=\"nlist.do?page=1\"><i class=\"fas fa-bullhorn\"></i> 공지사항 관리</a></li>\r\n");
          out.write("			<li><a href=\"blist.do?page=1\"><i class=\"fas fa-clipboard\"></i> 게시글 관리</a></li>\r\n");
          out.write("			<li><a href=\"mlist.do?page=1\"><i class=\"fas fa-users\"></i> 회원 관리</a></li>\r\n");
          out.write("			<!-- 로그아웃 -->\r\n");
          out.write("		    <li><a href=\"logout.do\"><i class=\"fas fa-sign-out-alt\"></i> 로그아웃</a></li>\r\n");
          out.write("		</ul>\r\n");
          out.write("	</nav>\r\n");
          int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      _jspx_th_c_005fif_005f1_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f1, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f1_reused);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f2_reused = false;
    try {
      _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f2.setParent(null);
      // /WEB-INF/views/common/menubar.jsp(196,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ !empty sessionScope.loginUser and sessionScope.loginUser.adminYN eq 'N' }", boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null)).booleanValue());
      int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
      if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("	<nav>\r\n");
          out.write("		<a href=\"main.do\" class=\"moduerp-logo\">ModuERP</a>\r\n");
          out.write("		<ul id=\"menubar\">\r\n");
          out.write("		    <li><a href=\"nlist.do?page=1\"><i class=\"fas fa-bullhorn\"></i>공지사항</a></li>\r\n");
          out.write("		    <li><a href=\"blist.do?page=1\"><i class=\"fas fa-clipboard\"></i>구매</a></li>\r\n");
          out.write("		    <li><a href=\"moveAjax.do\"><i class=\"fas fa-code\"></i>체험페이지</a></li>\r\n");
          out.write("		    <li><a href=\"moveApi.do\"><i class=\"fas fa-plug\"></i>고객서비스</a></li>\r\n");
          out.write("		    <li><a href=\"moveApi.do\"><i class=\"fas fa-plug\"></i>회사소개</a></li>\r\n");
          out.write("		    <li><a href=\"erpmain.do\"><i class=\"fas fa-home\"></i> ERP</a></li>\r\n");
          out.write("		    <li><a href=\"myinfo.do?userId=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ sessionScope.loginUser.userId }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
          out.write("\"><i class=\"fas fa-user\"></i> My Page</a></li>\r\n");
          out.write("			<!-- 로그아웃 -->\r\n");
          out.write("		    <li><a href=\"logout.do\"><i class=\"fas fa-sign-out-alt\"></i> 로그아웃</a></li>\r\n");
          out.write("		</ul>\r\n");
          out.write("	</nav>\r\n");
          int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      _jspx_th_c_005fif_005f2_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f2, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f2_reused);
    }
    return false;
  }
}
