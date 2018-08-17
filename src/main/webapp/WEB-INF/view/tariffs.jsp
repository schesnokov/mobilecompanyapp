<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>F SOCIETY Tariffs</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content="http://webthemez.com"/>

    <link href="/res/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/res/css/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="/res/css/jcarousel.css" rel="stylesheet"/>
    <link href="/res/css/flexslider.css" rel="stylesheet"/>
    <link href="/res/css/style.css" rel="stylesheet"/>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

</head>

<body>
<div id="wrapper">
    <header>
        <div class="navbar navbar-default navbar-static-top">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <%--<a class="navbar-brand" href="/">
                    <img style="margin-left:15px;" src="/res/img/fsociety-logo1.png" alt="logo"> </a>--%>
            </div>
            <div class="navbar-collapse collapse ">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/">Home</a>
                    </li>
                    <li class="active">
                        <a href="/tariff">Our Tariffs</a>
                    </li>
                    <li>
                        <a href="/account">Personal account</a>
                    </li>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li>
                            <a href="/adminPanel">Admin panel</a>
                        </li>
                    </security:authorize>
                    <c:if test="${pageContext.request.userPrincipal.name == null}">
                        <li>
                            <a href="/login" class="link link_header">Login</a>
                        </li>
                    </c:if>
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <li>
                            <form id="logoutForm" method="post" action="/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </li>
                        <li>
                            <a class="link link_header" onclick="document.forms['logoutForm'].submit()">Logout</a>
                        </li>
                    </c:if>
                </ul>
            </div>
            <!-- </div> -->
        </div>
    </header>
    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="pageTitle">Tariffs
                    </h2>
                </div>
            </div>
        </div>
    </section>
    <section id="content">
        <div class="container content">
            <hr class="margin-bottom-50">
            <div class="row">
                <c:forEach var="tariffVar" items="#{tariffList}">
                    <div class="col-sm-4 col-md-4 info-blocks">
                        <div class="panel  panel-success">
                            <div class="panel-heading">
                                <h3 class="panel-title"><c:out value="${tariffVar.tariffName}"/></h3>
                                <h3 class="panel-title"><c:out value="${tariffVar.tariffDescription}"/></h3>
                                <h3 class="panel-title">Price: <c:out value="${tariffVar.tariffPrice}"/></h3>
                            </div>
                            <div class="panel-body">
                                <h4 class="panel-body">Available options:</h4>
                                <c:choose>
                                    <c:when test="${not empty tariffVar.availableOptions}">
                                        <c:forEach var="optionsVar" items="#{tariffVar.availableOptions}">
                                            <h4 class="panel-body"><c:out value="${optionsVar.name}"/></h4>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <p>There is no available options for this tariff <br/></p>
                                    </c:otherwise>
                                </c:choose>
                                <security:authorize access="hasRole('ROLE_ADMIN')">
                                    <div class="buttons">
                                        <form action="/deleteTariff/${tariffVar.id}" method="POST">
                                            <input type="hidden" name="tariff" value="${tariffVar.id}">
                                            <input type='submit' value='Delete' class="btn btn-danger dbutton"/>
                                        </form>
                                        <form action="/editTariff/${tariffVar.id}" method="GET">
                                            <input type="submit" value='Edit' class="btn btn-success dbutton"/>
                                        </form>
                                    </div>
                                </security:authorize>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    <div id="sub-footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">

                </div>
                <div class="col-lg-6">

                </div>
            </div>
        </div>
    </div>
    </footer>
</div>
<a href="#" class="scrollup"><i class="fa fa-angle-up active"></i></a>
<!-- javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/res/js/jquery.js"></script>
<script src="/res/js/jquery.easing.1.3.js"></script>
<script src="/res/js/bootstrap.min.js"></script>
<script src="/res/js/jquery.fancybox.pack.js"></script>
<script src="/res/js/jquery.fancybox-media.js"></script>
<script src="/res/js/portfolio/jquery.quicksand.js"></script>
<script src="/res/js/portfolio/setting.js"></script>
<script src="/res/js/jquery.flexslider.js"></script>
<script src="/res/js/animate.js"></script>
<script src="/res/js/custom.js"></script>

</body>
</html>