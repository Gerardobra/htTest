<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Pagina non trovata</title>
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/materialize.css"/>"
  media="screen,projection" />

<link href="<spring:url value="/resources/css/style.css"/>" rel="stylesheet">

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<style type="text/css">
.home-nav-ext {
	padding: 20px;
	padding-right: 40px;
	background: transparent;
	box-shadow: none;
}

body {
	/*background: linear-gradient(#42ADE2, #80d8ff);*/
	/*background: #42c4ac;*/
	/*background: radial-gradient(ellipse farthest-side at 100% 100%,#dbf6c8 20%,#42c4ac 50%,#4caf50 110%);*/
	/*background: linear-gradient(#42ADE2, #80d8ff);*/
	background-image: url('<spring:url value="/resources/img/home/home2.jpg"/>');
	background-size: cover;
	-webkit-animation: 20s linear 0s normal none infinite animate;
	-moz-animation: 20s linear 0s normal none infinite animate;
	-ms-animation: 20s linear 0s normal none infinite animate;
	-o-animation: 20s linear 0s normal none infinite animate;
	animation: 20s linear 0s normal none infinite animate;
	animation-direction: alternate;
}

a.nav-sign-up {
	box-shadow: none;
	border-radius: 2pc;
	border: 1px solid white;
	padding: 0 20px;
	background: white;
	color: black;
}

.hero-title {
	font-weight: 600;
}

.expand-panel {
	/*
	-webkit-animation: 2s ease-in-out 0s normal none  expand;
	-moz-animation: 2s ease-in-out 0s normal none  expand;
	-ms-animation: 2s ease-in-out 0s normal none  expand;
	-o-animation: 2s ease-in-out 0s normal none  expand;
	animation: 2s ease-in-out 0s normal none  expand;
	*/
}

.page-footer{
	background:transparent !important;
}
@-webkit-keyframes animate {
	from {background-position: 0 0;}
  to {background-position: -500px 0;}
}
@moz-keyframes animate {
	from {background-position: 0 0;
  to {background-position: -500px 0;}
}
@-ms-keyframes animate {
	from {background-position: 0 0;}
  to {background-position: -500px 0;}
}
@-o-keyframes animate {
	from {background-position: 0 0;}
  to {background-position: -500px 0;}
}
@keyframes animate {
	from {background-position: 0 0;}
  to {background-position: -500px 0;}
}
@-webkit-keyframes expand {
	from {transform: scale(0);
	 border-radius: 100pc;}
  to {
	 transform: scale(1.0);
	 border-radius: 0pc;}
}
@-moz-keyframes expand {
	from {transform: scale(0);
	 border-radius: 100pc;}
  to {
	 transform: scale(1.0);
	 border-radius: 0pc;}
}
@-ms-keyframes expand {
	from {transform: scale(0);
	 border-radius: 100pc;}
  to {
	 transform: scale(1.0);
	 border-radius: 0pc;
  }
}
@-o-keyframes expand {
	from {transform: scale(0);
	 border-radius: 100pc;}
  to {
	 transform: scale(1.0);
	 border-radius: 0pc;
  }
}
@keyframes expand {
	from {transform: scale(0);
	 border-radius: 100pc;}
  to {
	 transform: scale(1.0);
	 border-radius: 0pc;}
}
</style>

</head>
<body>
	<div id="backgroung-overlay"></div>
	<header>
	<nav class="nav-extended home-nav-ext">
		<div class="nav-wrapper">
			<a href="<spring:url value="/home"/>" class="brand-logo center">CARONTE</a>
		</div>
	</nav>
	</header>
	<main>
	<div class="row center">
		<div class="col s12">
			<h1 class="white-text">404 - Pagina non trovata</h1>
		</div>
	</div>
	</main>
	<footer class="page-footer">
		<div class="row center-align">
			© 2017 Copyright Text
		</div>
	</footer>
</body>
</html>