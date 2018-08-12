<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Administration Panel</title>
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
                <a class="navbar-brand" href="/">
                    <img style="margin-left:15px;" src="/res/img/fsociety-logo1.png" alt="logo"> </a>
            </div>
            <div class="navbar-collapse collapse ">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/">Home</a>
                    </li>
                    <li>
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
    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="pageTitle">Customer edit page</h2>
                </div>
            </div>
        </div>
    </section>
    <section id="content">
        <div class="container content">
            <hr class="margin-bottom-50">
            <div class="row">
                <div class="col-sm-4 info-blocks" style="width:400px;">
                    <div class="info-blocks-in">
                        <h2> First name: </h2>
                        <h3>${customer.firstName}</h3> <br/>
                        <h2> Second name: </h2>
                        <h3>${customer.secondName}</h3> <br/>
                        <h2> E-mail: </h2>
                        <h3>${customer.email}</h3> <br/>
                        <h2> Date of birth: </h2>
                        <h3>${customer.dateOfBirth}</h3> <br/>
                    </div>
                </div>
                <div class="col-sm-4 info-blocks" style="width:800px;">
                    <div class="info-blocks-in">
                        <c:if test="${not empty contractList}">
                        <c:forEach var="contractVar" items="#{contractList}">
                        <div class="panel-group" id="accordion-alt3" style="width:400px;">
                            <div class="panel">
                                <!-- Panel heading -->
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion-alt3"
                                           href="#collapse<c:out value="${contractVar.id}"/>-alt3">
                                            <h3>
                                                <c:out value="${contractVar.number}"/></h3></a>
                                    </h4>
                                </div>
                                <div id="collapse<c:out value="${contractVar.id}"/>-alt3"
                                     class="panel-collapse collapse">
                                    <!-- Panel body -->
                                    <div class="panel-body"><h5>Tariff: </h5> <c:out
                                            value="${contractVar.tariff.tariffName}"/> <br/>
                                        <h5>Balance: </h5><c:out value="${contractVar.balance}"/>
                                        <form action="/contractPage/${contractVar.id}" method="GET">
                                            <input type="hidden" name="contract" value="${contractVar.id}">
                                            <input type='submit' value='Edit contract'>
                                        </form>
                                    </div>
                                    <br/>
                                </div>
                                </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4 info-blocks" style="width:400px;">
                    <div class="info-blocks-in">
                        <form:form method='POST' modelAttribute="newContract"
                                   action="/customer/addContract/${customer.id}">
                            <h2>Add new contract</h2>
                            <dl class="dl_class">
                                <spring:bind path="number">
                                    <dt>
                                        Phone number
                                    </dt>
                                    <dd>
                                        <form:input type='text' path="number"
                                                    placeholder="Phone number"/>
                                        <p>Phone number</p>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="balance">
                                    <dt>
                                        Start balance
                                    </dt>
                                    <dd>
                                        <form:input type='text' path="balance"
                                                    placeholder="Start balance"/>
                                        <p>Enter start balance</p>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="tariffId">
                                    <dt>
                                        Select tariff
                                    </dt>
                                    <dd>
                                        <form:select path="tariffId" onchange="tariffChanged()">
                                            <form:options items="${tariffList}" itemValue="id" itemLabel="tariffName"/>
                                        </form:select>
                                        <p>Select contract tariff</p>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="optionsIds">
                                    <dt>
                                        Choose tariff options
                                    </dt>
                                    <dd>
                                        <div id="optionCheckboxes">
                                            <form:checkboxes cssClass="optionCheckbox" path="optionsIds"
                                                             items="${availableOptions}" itemLabel="name"
                                                             itemValue="id" id="id"/>
                                        </div>
                                        <p>Select tariff options</p>
                                    </dd>
                                </spring:bind></dl>
                            <input type='submit' value='Confirm'>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div id="sub-footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="copyright">
                        <p>
                            <span>&copy; Creative Bee 2015 All right reserved. By </span><a href="http://webthemez.com"
                                                                                            target="_blank">WebThemez</a>
                        </p>
                    </div>
                </div>
                <div class="col-lg-6">
                    <ul class="social-network">
                        <li><a href="#" data-placement="top" title="Facebook"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#" data-placement="top" title="Twitter"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#" data-placement="top" title="Linkedin"><i class="fa fa-linkedin"></i></a></li>
                        <li><a href="#" data-placement="top" title="Pinterest"><i class="fa fa-pinterest"></i></a></li>
                        <li><a href="#" data-placement="top" title="Google plus"><i class="fa fa-google-plus"></i></a>
                        </li>
                    </ul>
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
<script src="/res/js/script.js"></script>


</body>
</html>