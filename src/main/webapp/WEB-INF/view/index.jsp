<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>F SOCIETY Home page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content="http://webthemez.com"/>
    <!-- css -->
    <link href="/res/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/res/css/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="/res/css/jcarousel.css" rel="stylesheet"/>
    <link href="/res/css/flexslider.css" rel="stylesheet"/>
    <link href="/res/js/owl-carousel/owl.carousel.css" rel="stylesheet">
    <link href="/res/css/style.css" rel="stylesheet"/>
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

</head>
<body>
<div id="wrapper" class="home-page">
    <header>
        <div class="navbar navbar-default navbar-static-top">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <%--<a class="navbar-brand" href="/"><img style="margin-left:15px;" src="/res/img/fsociety-logo1.png"
                                                      alt="logo"/></a>--%>
            </div>
            <div class="navbar-collapse collapse ">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="/">Home</a>
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
    <section id="banner">

        <!-- Slider -->
        <div id="main-slider" class="flexslider">
            <ul class="slides">
                <li>
                    <img src="/res/img/slides/8.jpg" alt=""/>
                    <div class="flex-caption">
                    </div>
                </li>
                <li>
                    <img src="/res/img/slides/7.jpg" alt=""/>
                    <div class="flex-caption">
                    </div>
                </li>
            </ul>
        </div>
        <!-- end slider -->
    </section>
    <footer>

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
<script src="/res/js/owl-carousel/owl.carousel.js"></script>
</body>
</html>