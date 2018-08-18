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
                    <li>
                        <a href="/account">Personal account</a>
                    </li>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li>
                            <a href="/adminPanel">Admin panel</a>
                        </li>
                        <li>
                            <a href="/customerPage">Edit Customers</a>
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
                    <h2 class="pageTitle">Administration panel</h2>
                </div>
            </div>
        </div>
    </section>
    <section id="content">
        <div class="container content">
            <hr class="margin-bottom-50">
            <div class="row">
                <div class="col-sm-4 col-md-4 info-blocks">
                    <form action="tariffs" method="GET">
                        <input type='submit' class="btn btn-success" value='All tariffs'>
                    </form>
                </div>
                <div class="col-sm-4 col-md-4 info-blocks">
                    <form action="optionsPage" method="GET">
                        <input type='submit' class="btn btn-success" value='All options'>
                    </form>
                </div>
                <div class="col-sm-4 col-md-4 info-blocks">
                    <form action="customerPage" method="GET">
                        <input type='submit' class="btn btn-success" value='All Customers'>
                    </form>
                </div>
            </div>
            <div class="col-sm-4 col-md-4 info-blocks">
                <div class="panel  panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-bug"></i><a href="/tariffs">All tariffs</a></h3>
                    </div>
                    <div class="panel-body">
                        <form:form cssClass="center" method='POST' modelAttribute="tariffDto" action="/addTariff">
                            <dl class="dl_class">
                                <spring:bind path="tariffName">
                                    <dt>
                                        Tariff
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="tariffName"
                                                    placeholder="Tariff name" required="required" pattern="([a-zA-Z]{3,14})"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="tariffPrice">
                                    <dt>
                                        Tariff price
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="tariffPrice"
                                                    placeholder="Tariff price" required="required" pattern="([0-9]{1,10})"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="tariffDescription">
                                    <dt>
                                        Tariff Description
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="tariffDescription"
                                                    placeholder="Tariff description" required="required"/>
                                    </dd>
                                </spring:bind>
                            </dl>
                            <input class="btn btn-success" type='submit' value='Add new tariff'>
                        </form:form>
                    </div>
                </div>
                <div class="panel  panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">Find customer by phone number</h3>
                    </div>
                    <div class="panel-body">
                        <form:form cssClass="center" method='GET' action="/findByPhone">
                            <dl class="dl_class">
                                <dt>
                                    Phone
                                </dt>
                                <dd>
                                    <input class="form-control" name="phone" type="text" required
                                           placeholder="+7XXXXXXXXXX" pattern="([+]{1}[7]{1}[0-9]{10})"/>
                                </dd>
                                <c:if test="${findContractByPhoneError!=null}">
                                    <div class="error">
                                        <span>${findContractByPhoneError}</span>
                                    </div>
                                </c:if>
                            </dl>
                            <input class="btn btn-success" type="submit" value="Find">
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="col-sm-4 col-md-4 info-blocks">
                <div class="panel  panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">Add new option</h3>
                    </div>
                    <div class="panel-body">
                        <form:form cssClass="center" method='POST' modelAttribute="newOption" action="/addOption">
                            <dl class="dl_class">
                                <spring:bind path="name">
                                    <dt>
                                        Option name
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="name"
                                                    placeholder="Option name" required="required" pattern="([a-zA-Z]{1,14})"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="description">
                                    <dt>
                                        Option Description
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="description"
                                                    placeholder="Option description" required="required"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="price">
                                    <dt>
                                        Option price
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="price"
                                                    placeholder="Option price" required="required" pattern="([0-9]{1,10})"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="connectionCost">
                                    <dt>
                                        Connection cost
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="connectionCost"
                                                    placeholder="Option connection cost" required="required" pattern="([0-9]{1,10})"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="dependentIds">
                                    <dt>
                                        Choose dependent options:
                                    </dt>
                                    <dd>
                                        <div id="optionCheckboxes1">
                                            <form:checkboxes cssClass="optionCheckbox" path="dependentIds"
                                                             items="${optionsList}" itemLabel="name"
                                                             itemValue="id" id="id"/>
                                        </div>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="conflictedIds">
                                    <dt>
                                        Choose conflicted options:
                                    </dt>
                                    <dd>
                                        <div id="optionCheckboxes2">
                                            <form:checkboxes cssClass="optionCheckbox" path="conflictedIds"
                                                             items="${optionsList}" itemLabel="name"
                                                             itemValue="id" id="id"/>
                                        </div>
                                    </dd>
                                </spring:bind>
                            </dl>
                            <input class="btn btn-success " type='submit' value='Add new option'>
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="col-sm-4 col-md-4 info-blocks">
                <div class="panel  panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">Registration of new customer</h3>
                    </div>
                    <div class="panel-body">
                        <form:form cssClass="center" method='POST' modelAttribute="userDto">
                            <dl class="dl_class">
                                <spring:bind path="firstName">
                                    <dt>
                                        First Name
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="firstName"
                                                    placeholder="First Name" required="required" pattern="([a-zA-Z]{2,14})"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="secondName">
                                    <dt>
                                        Second Name
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="secondName"
                                                    placeholder="Second Name" required="required" pattern="([a-zA-Z]{2,14})"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="dateOfBirth">
                                    <dt>
                                        Date of birth
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="dateOfBirth"
                                                    placeholder="YYYY-MM-DD" required="required" pattern="(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="passportNumber">
                                    <dt>
                                        Passport Number
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="passportNumber"
                                                    placeholder="passport" required="required" pattern="([0-9]{10})"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="adress">
                                    <dt>
                                        Address
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="adress"
                                                    placeholder="Address" required="required"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="email">
                                    <dt>
                                        Email
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="email"
                                                    placeholder="xxxxx@xxx.xxx" required="required" pattern="([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z\.]{2,6})"/>
                                    </dd>
                                </spring:bind>
                                <spring:bind path="password">
                                    <dt>
                                        Password
                                    </dt>
                                    <dd>
                                        <form:input class="form-control" type='text' path="password"
                                                    placeholder="Password" required="required" pattern="([a-z0-9_-]{5,32})"/>
                                    </dd>
                                </spring:bind>
                            </dl>
                            <c:if test="${registrationError!=null}">
                                <div class="error">
                                    <span>${registrationError}</span>
                                </div>
                            </c:if>
                            <input class="btn btn-success register" type='submit' value='Confirm'>
                        </form:form>
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