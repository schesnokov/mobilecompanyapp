<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">
    <title>About F SOCIETY mobile operator</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="http://webthemez.com">
    <!-- css -->
    <link href="/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="/res/css/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="/res/css/jcarousel.css" rel="stylesheet">
    <link href="/res/css/flexslider.css" rel="stylesheet">
    <link href="/res/css/style.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
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
                <a class="navbar-brand" href="/">
                    <img style="margin-left:15px;" src="/res/img/fsociety-logo1.png" alt="logo"> </a>
            </div>
            <div class="navbar-collapse collapse ">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/">Home</a>
                    </li>
                    <li class="active">
                        <a href="/about">About Us</a>
                    </li>
                    <li>
                        <a href="/tariffs">Our Tariffs</a>
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
    <!-- end header -->
    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="pageTitle">About Us</h2>
                </div>
            </div>
        </div>
    </section>
    <section id="content" class="">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h3 class="text-center">We are the most safest
                        <span class="color">MOBILE OPERATOR</span>
                    </h3>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <p class="text-center">Allow us to provide perfect safe connection with your friends and family.
                        Also you could be patient about your conversations with your bussiness partners and your
                        unpaided fee.</p>
                </div>
            </div>
            <div class="about">
                <div class="row">
                    <div class="col-md-12" style="margin-left: auto; margin-right: auto;">
                        <div class="about-logo"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <!-- Heading and para -->
                        <div class="block-heading-two">
                            <h3>
                                <span>Why Choose Us?</span>
                            </h3>
                        </div>
                        <p>Our connection between user are end-to-end encryption. All your's calls and messages will be
                            safe and either Yarovaya's law can't brake your privacy.
                            Also, we have a nice prices and 5G mobile internet more than on 80% of earth's land.
                            <br>
                            <br>Just create an customer's account and try it for free during first week!</p>
                    </div>
                    <div class="col-md-8">
                        <div class="block-heading-two">
                            <h3>
                                <span>Our Tariffs</span>
                            </h3>
                        </div>
                        <c:forEach var="tariffVar" items="#{tariffList}">
                        <div class="panel-group" id="accordion-alt3" style="width:500px;">
                            <div class="panel">
                                <!-- Panel heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion-alt3"
                                           href="#collapse<c:out value="${tariffVar.id}"/>-alt3">
                                            <h3><c:out value="${tariffVar.tariffName}"/></h3></a>
                                    </h4>
                                </div>
                                <div id="collapse<c:out value="${tariffVar.id}"/>-alt3" class="panel-collapse collapse">
                                    <!-- Panel body -->
                                    <div class="panel-body"><h5>Tariff description:</h5> <c:out
                                            value="${tariffVar.tariffDescription}"/> <br/>
                                        <h5>Tariff price: </h5><c:out value="${tariffVar.tariffPrice}"/></div>
                                </div>
                                <br/>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                    <br></div>
            </div>
        </div>
    </section>
    <div id="sub-footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="copyright">
                        <p>
                            <span>© Creative Bee 2015 All right reserved. By </span>
                            <a href="http://webthemez.com" target="_blank">WebThemez</a>
                        </p>
                    </div>
                </div>
                <div class="col-lg-6">
                    <ul class="social-network">
                        <li>
                            <a href="#" data-placement="top" title="Facebook">
                                <i class="fa fa-facebook"></i>
                            </a>
                        </li>
                        <li>
                            <a href="#" data-placement="top" title="Twitter">
                                <i class="fa fa-twitter"></i>
                            </a>
                        </li>
                        <li>
                            <a href="#" data-placement="top" title="Linkedin">
                                <i class="fa fa-linkedin"></i>
                            </a>
                        </li>
                        <li>
                            <a href="#" data-placement="top" title="Pinterest">
                                <i class="fa fa-pinterest"></i>
                            </a>
                        </li>
                        <li>
                            <a href="#" data-placement="top" title="Google plus">
                                <i class="fa fa-google-plus"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="#" class="scrollup">
    <i class="fa fa-angle-up active"></i>
</a>
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
<script src="/res/js/owl-carousel/owl.carousel.js"></script>
</body>

</html>