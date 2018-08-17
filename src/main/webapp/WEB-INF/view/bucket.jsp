<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Your's account</title>
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
    <!-- start header -->
    <header>
        <div class="navbar navbar-default navbar-static-top">
            <!-- <div class="container"> -->
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
                    <li>
                        <a href="/tariffs">Our Tariffs</a>
                    </li>
                    <li class="active">
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
                    <h2 class="pageTitle">Personal Account</h2>
                </div>
            </div>
        </div>
    </section>
    <section id="content">
        <div class="container content">
            <hr class="margin-bottom-50">
            <div class="row">
                <div class="col-sm-12 col-md-12 info-blocks">
                    <div class="panel  panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">Your cart</h3>
                        </div>
                        <div class="panel-body">
                            <h4 class="panel-body">Choosed tariff: ${requestScope.bucket.tariff.tariffName}</h4>
                            <c:choose>
                                <c:when test="${not empty requestScope.bucket.options}">
                                    <div class="row">
                                        <c:forEach var="optionsVar" items="#{requestScope.bucket.options}">
                                            <div class="col-sm-6 col-md-6 info-blocks">
                                                <div class="panel  panel-success">
                                                    <div class="panel-heading">
                                                        <h3 class="panel-title">Option name: <c:out
                                                                value="${optionsVar.name}"/></h3>
                                                        <h4 class="panel-body">Option description: <c:out
                                                                value="${optionsVar.description}"/></h4>
                                                        <h4 class="panel-body">Option connection cost: <c:out
                                                                value="${optionsVar.connectionCost}"/></h4>
                                                        <h4 class="panel-body">Option price: <c:out
                                                                value="${optionsVar.price}"/></h4>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <h4 class="panel-body">There is no selected options for new tariff</h4>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                            <c:when test="${not empty requestScope.bucket.options}">
                            <div class="panel  panel-success">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Sum for changing you tariff and options
                                        is ${orderResult}</h3>

                                    <form:form modelAttribute="contractChanges"
                                               action="/changeTariff/${requestScope.bucket.contractId}" method="POST">
                                        <input class="btn btn-success" type='submit' value='Submit changes'/>
                                    </form:form>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-block text-uppercase btn-danger"
                                           href="<c:url value="/account"/>">Back to account</a>
                                    </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
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