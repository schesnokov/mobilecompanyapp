<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Login</title>
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
            <li class="active">
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
            <h2 class="pageTitle">Login</h2>
          </div>
        </div>
      </div>
    </section>
    <section id="content">
      <div class="container content">
        <hr class="margin-bottom-50">
        <div class="row">
          <div class="col-sm-4 info-blocks">
            <div class="info-blocks-in">
              <form action="/login" method='POST'>
                <h2>Sign in</h2>
                  <dt>
                    E-mail
                  </dt>
                  <dd>
                    <input name="email" id="email" placeholder="E-mail">
                    <p>Enter email.</p>
                  </dd>
                  <dt>
                    Password
                  </dt>
                  <dd>
                    <input type="password" name="password" id="password"
                           placeholder="Password*">
                    <p>Enter password.</p>
                  </dd>
                <input type='submit' value='Sign in'>
              </form>
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