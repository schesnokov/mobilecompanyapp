<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                    <li>
                        <a href="/bucket">Cart</a>
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
                            <h3 class="panel-title">Contract's number: <c:out value="${contractDto.number}"/></h3>
                            <h3 class="panel-title">Contract's tariff: <c:out
                                    value="${contractDto.tariff.tariffName}"/></h3>
                        </div>
                        <div class="panel-body">
                            <h4 class="panel-body">Selected options:</h4>
                            <c:choose>
                                <c:when test="${not empty contractDto.selectedOptions}">
                                    <div class="row">
                                        <c:forEach var="optionsVar" items="#{contractDto.selectedOptions}">
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
                                    <h4 class="panel-body">There is no selected options for this contract</h4>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${contractDto.isBlocked == 0}">
                                    <div class="col-sm-6 col-md-6 info-blocks">
                                        <div class="panel  panel-success">
                                            <div class="panel-heading">
                                                <h3 class="panel-title">Choose new tariff: </h3>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                            <h4 class="panel-body">
                                                <form:form modelAttribute="contractChanges"
                                                           action="/bucket/product/${contractDto.id}"
                                                           method="POST" id="changeContract">
                                                    <dl class="dl_class">
                                                        <form:select class="form-control" path="tariffId"
                                                                     onchange="tariffChanged()">
                                                            <form:options items="${tariffList}" itemValue="id"
                                                                          itemLabel="tariffName"/>
                                                        </form:select>
                                                        <td>
                                                            <div id="optionCheckboxes" stylez="text-align: left;margin-left: 75px;">
                                                                <form:checkboxes cssClass="optionCheckbox"
                                                                                 path="optionsIds"
                                                                                 items="${availableOptions}"
                                                                                 itemLabel="name"
                                                                                 itemValue="id"
                                                                                 id="id"/>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <input type='submit' id="addToCartBtn" value='Add to cart'
                                                                   class="btn btn-success"/>
                                                        </td>
                                                    </dl>
                                                    <input type="hidden" id="ids" name="ids"/>
                                                </form:form>
                                                <td>
                                                    <form:form action="/changeStatus/${contractDto.id}" method="POST">
                                                        <input type='submit' value='Block Contract'
                                                               class="btn btn-danger"/>
                                                    </form:form>
                                                </td>
                                            </h4>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${contractDto.isBlocked == 1}">
                                    <h3>Unblock your contract</h3>
                                    <form:form action="/changeStatus/${contractDto.id}" method="POST">
                                        <input type='submit' value='Unblock Contract' class="btn btn-success dbutton"/>
                                    </form:form>
                                </c:when>
                                <c:otherwise>
                                    <h3>Your's contract is blocked by operator!</h3>
                                    <security:authorize access="hasRole('ROLE_ADMIN')">
                                        <form:form action="/changeStatus/${contractDto.id}" method="POST">
                                            <input type='submit' value='Unblock Contract'
                                                   class="btn btn-success dbutton"/>
                                        </form:form>
                                    </security:authorize>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
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
<script src="/res/js/script.js"></script>
<script src="/res/js/owl-carousel/owl.carousel.js"></script>
</body>

</html>