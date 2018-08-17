<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                <div class="col-sm-4 col-md-6 info-blocks">
                    <div class="panel  panel-success">
                        <div class="panel-heading"><h3 class="panel-title">First name:</h3></div>
                        <div class="panel-body">
                            <h4 class="panel-body">${customer.firstName}</h4>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4 col-md-6 info-blocks">
                    <div class="panel  panel-success">
                        <div class="panel-heading"><h3 class="panel-title">Second name:</h3></div>
                        <div class="panel-body">
                            <h4 class="panel-body">${customer.secondName}</h4>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4 col-md-6 info-blocks">
                    <div class="panel  panel-success">
                        <div class="panel-heading">
                            <h3 class="panel-title">E-mail:</h3>
                        </div>
                        <div class="panel-body">
                            <h4 class="panel-body">${customer.email}</h4>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4 col-md-6 info-blocks">
                    <div class="panel  panel-success">
                        <div class="panel-heading"><h3 class="panel-title"> Date of birth:</h3></div>
                        <div class="panel-body">
                            <h4 class="panel-body">${customer.dateOfBirth}</h4>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4 col-md-6 info-blocks">
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <div class="panel  panel-success">
                            <div class="panel-heading">
                                <h3 class="panel-title">Add new contract</h3>
                            </div>
                            <div class="panel-body">
                                <h4 class="panel-body">
                                    <form:form method='POST' modelAttribute="newContract"
                                               action="/customer/addContract/${customer.id}">
                                        <dl class="dl_class">
                                            <spring:bind path="number">
                                                <dt>
                                                    Phone number
                                                </dt>
                                                <dd>
                                                    <form:input class="form-control" type='text' path="number"
                                                                placeholder="+7XXXXXXXXXX" required="required"
                                                                pattern="([+]{1}[7]{1}[0-9]{10})"/>
                                                </dd>
                                            </spring:bind>
                                            <spring:bind path="balance">
                                                <dt>
                                                    Start balance
                                                </dt>
                                                <dd>
                                                    <form:input class="form-control" type='text' path="balance"
                                                                required="required" pattern="[1-9]{8}"/>
                                                </dd>
                                            </spring:bind>
                                            <spring:bind path="tariffId">
                                                <dt>
                                                    Select tariff
                                                </dt>
                                                <dd>
                                                    <form:select class="form-control" path="tariffId"
                                                                 onchange="tariffChanged()">
                                                        <form:options items="${tariffList}" itemValue="id"
                                                                      itemLabel="tariffName"/>
                                                    </form:select>
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
                                                </dd>
                                            </spring:bind></dl>
                                        <c:if test="${phoneError!=null}">
                                            <div class="error">
                                                <span>${phoneError}</span>
                                            </div>
                                        </c:if>
                                        <input class="btn btn-success dbutton" type='submit' value='Confirm'>
                                    </form:form>
                                </h4>
                            </div>
                        </div>
                    </security:authorize>
                </div>
                <div class="col-sm-4 col-md-6 info-blocks">
                    <div class="panel-group" id="accordion-alt3">
                        <c:if test="${not empty contractList}">
                            <c:forEach var="contractVar" items="#{contractList}">
                                <%--
                                                                <div class="panel">
                                --%>
                                <div class="panel  panel-success">
                                    <div class="panel-heading">
                                        <a data-toggle="collapse" data-parent="#accordion-alt3"
                                           href="#collapse<c:out value="${contractVar.id}"/>-alt3">
                                            <h3 class="panel-title"><c:out value="${contractVar.number}"/></h3></a>
                                    </div>
                                    <div id="collapse<c:out value="${contractVar.id}"/>-alt3"
                                         class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <h4 class="panel-body">
                                                Tariff: <c:out value="${contractVar.tariff.tariffName}"/>
                                                Balance: <c:out value="${contractVar.balance}"/>
                                                <form action="/contractPage/${contractVar.id}" method="GET">
                                                    <input type="hidden" name="contract" value="${contractVar.id}">
                                                    <input class="btn btn-success dbutton" type='submit'
                                                           value='Edit contract'>
                                                </form>
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
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
<script src="/res/js/script.js"></script>
</body>

</html>