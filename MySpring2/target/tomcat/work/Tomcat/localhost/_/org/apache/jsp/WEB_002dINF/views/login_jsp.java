/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-11-05 09:10:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<!--[if lt IE 7]>      <html class=\"no-js lt-ie9 lt-ie8 lt-ie7\"> <![endif]-->\r\n");
      out.write("<!--[if IE 7]>         <html class=\"no-js lt-ie9 lt-ie8\"> <![endif]-->\r\n");
      out.write("<!--[if IE 8]>         <html class=\"no-js lt-ie9\"> <![endif]-->\r\n");
      out.write("<!--[if gt IE 8]><!--> <html class=\"no-js\"> <!--<![endif]-->\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <title>Minimal and Clean Sign up / Login and Forgot Form by FreeHTML5.co</title>\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <meta name=\"description\" content=\"Free HTML5 Template by FreeHTML5.co\" />\r\n");
      out.write("    <meta name=\"keywords\" content=\"free html5, free template, free bootstrap, html5, css3, mobile first, responsive\" />\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- Facebook and Twitter integration -->\r\n");
      out.write("    <meta property=\"og:title\" content=\"\"/>\r\n");
      out.write("    <meta property=\"og:image\" content=\"\"/>\r\n");
      out.write("    <meta property=\"og:url\" content=\"\"/>\r\n");
      out.write("    <meta property=\"og:site_name\" content=\"\"/>\r\n");
      out.write("    <meta property=\"og:description\" content=\"\"/>\r\n");
      out.write("    <meta name=\"twitter:title\" content=\"\" />\r\n");
      out.write("    <meta name=\"twitter:image\" content=\"\" />\r\n");
      out.write("    <meta name=\"twitter:url\" content=\"\" />\r\n");
      out.write("    <meta name=\"twitter:card\" content=\"\" />\r\n");
      out.write("\r\n");
      out.write("    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->\r\n");
      out.write("    <link rel=\"shortcut icon\" href=\"favicon.ico\">\r\n");
      out.write("\r\n");
      out.write("    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/stataic/css/bootstrap.min.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/stataic/css/animate.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"/stataic/css/style.css\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- Modernizr JS -->\r\n");
      out.write("    <script src=\"/stataic/js/modernizr-2.6.2.min.js\"></script>\r\n");
      out.write("    <!-- FOR IE9 below -->\r\n");
      out.write("    <!--[if lt IE 9]>\r\n");
      out.write("    <script src=\"/stataic/js/respond.min.js\"></script>\r\n");
      out.write("    <![endif]-->\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"style-2\">\r\n");
      out.write("\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("\r\n");
      out.write("    <div class=\"row\">\r\n");
      out.write("        <div class=\"col-md-4\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <!-- Start Sign In Form -->\r\n");
      out.write("            <form action=\"#\" class=\"fh5co-form animate-box\" data-animate-effect=\"fadeInLeft\">\r\n");
      out.write("                <h2>Sign In</h2>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <label for=\"username\" class=\"sr-only\">Username</label>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"username\" placeholder=\"Username\" autocomplete=\"off\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <label for=\"password\" class=\"sr-only\">Password</label>\r\n");
      out.write("                    <input type=\"password\" class=\"form-control\" id=\"password\" placeholder=\"Password\" autocomplete=\"off\">\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <label for=\"remember\"><input type=\"checkbox\" id=\"remember\"> Remember Me</label>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <p>Not registered? <a href=\"sign-up2.html\">Sign Up</a> | <a href=\"forgot2.html\">Forgot Password?</a></p>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <input type=\"submit\" value=\"Sign In\" class=\"btn btn-primary\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </form>\r\n");
      out.write("            <!-- END Sign In Form -->\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"row\" style=\"padding-top: 60px; clear: both;\">\r\n");
      out.write("        <div class=\"col-md-12 text-center\"><p><small>&copy; All Rights Reserved. More Templates <a href=\"http://www.cssmoban.com/\" target=\"_blank\" title=\"模板之家\">模板之家</a> - Collect from <a href=\"http://www.cssmoban.com/\" title=\"网页模板\" target=\"_blank\">网页模板</a></small></p></div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!-- jQuery -->\r\n");
      out.write("<script src=\"/static/js/jquery.min.js\"></script>\r\n");
      out.write("<!-- Bootstrap -->\r\n");
      out.write("<script src=\"/stataic/js/bootstrap.min.js\"></script>\r\n");
      out.write("<!-- Placeholder -->\r\n");
      out.write("<script src=\"/stataic/js/jquery.placeholder.min.js\"></script>\r\n");
      out.write("<!-- Waypoints -->\r\n");
      out.write("<script src=\"/stataic/js/jquery.waypoints.min.js\"></script>\r\n");
      out.write("<!-- Main JS -->\r\n");
      out.write("<script src=\"/stataic/js/main.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
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
