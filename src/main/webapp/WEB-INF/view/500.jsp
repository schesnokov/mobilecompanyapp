<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Something went wrong</title>
    <meta charset="utf-8">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700&subset=latin,cyrillic'
          rel='stylesheet' type='text/css'>
    <style type="text/css">
        @import url('http://fonts.googleapis.com/css?family=Noto+Serif:400,400italic,700|Open+Sans:400,600,700');

        html, body {
            width: 100%;
            height: 100%;
            overflow: hidden;
            margin: 0px;
            padding: 0px;
            font-family: 'Open Sans', sans-serif;
            font-size: 16px
        }

        body {
            background: url('https://psv4.userapi.com/c848324/u1141581/docs/d17/8ad53730fffe/123.jpg?extra=zwthSNgCJgKiOsDpTrcec0TX_oWkNsJFdYuODd1g1_kQGamYtjmtKdYlbD1jsLOlCxaBrI0lS_Dld9lnbLCGeuVGmQfk320JebagCAcgmfUpy4_Z5srHTwK0OnsO-6I3-g_ZxbxLwU6a') no-repeat center center fixed;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }

        .stackTraceClass {
            position: fixed;
            bottom: 100px;
            right: 130px;
            top: 75px;
            left: 280px;
            color: rgb(156, 242, 90);
            font-family: 'Nunito', sans-serif;
            font-size: 14px;
            font-weight: 300;
            line-height: 30px;
            word-wrap: break-word;
        }

        .prokrutka {
            height: 480px;
            width: 975px;
            background: rgba(0, 0, 0, 0.1);
            border: 1px solid #020202;
            overflow-y: scroll;
        }
    </style>
</head>
<body>
<div class="content">
    <a href="/">Home</a>
    <div class="stackTraceClass">
        <div class="prokrutka">
            Exception: ${exception.message}
            <c:forEach items="${exception.stackTrace}" var="ste">    ${ste}
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>