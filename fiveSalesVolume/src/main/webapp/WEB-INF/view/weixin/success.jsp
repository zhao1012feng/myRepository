<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + request.getContextPath()
            + "/";
    session.setAttribute("path", path);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>龙果学院</title>
    <meta charset="utf-8">
    <link href="${path}pay_files/pay.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${path}js/jquery-1.7.2.min.js"></script>
    <script language="javascript">
      
    </script>
</head>
<body>

    <div class="wrap_header">
        <div class="header clearfix">
            <div class="logo_panel clearfix">
            返回页面啦
                <div class="logo fl"><img src="${path}pay_files/logo.png" alt=""></div>
                <div class="lg_txt">| 收银台</div>
            </div>
            <div class="fr tip_panel">
                <div class="txt">欢迎使用龙果支付付款</div>
                <a href="">常见问题</a>
            </div>
        </div>
    </div>

   
</body>
</html>
