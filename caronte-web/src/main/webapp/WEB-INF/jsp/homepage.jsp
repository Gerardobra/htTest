<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="it.aizoon.ersaf.util.CaronteConstants"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Homepage caronte</title>
<link rel="icon" type="image/png"
	href="<spring:url value="/resources/img/favicon.png"/>" />

<style type="text/css">
.home-nav-ext {
	padding: 20px;
	padding-right: 5%;
	padding-left: 5%;
	background: white;
	box-shadow: none;
}

body {
	/*background: linear-gradient(#42ADE2, #80d8ff);*/
	/*background: #42c4ac;*/
	/*background: radial-gradient(ellipse farthest-side at 100% 100%,#dbf6c8 20%,#42c4ac 50%,#4caf50 110%);*/
	/*background: linear-gradient(#42ADE2, #80d8ff);*/
	/*background-image: url('<spring:url value="/resources/img/home/home2.jpg"/>');
	background-size: cover;
	-webkit-animation: 20s linear 0s normal none infinite animate;
	-moz-animation: 20s linear 0s normal none infinite animate;
	-ms-animation: 20s linear 0s normal none infinite animate;
	-o-animation: 20s linear 0s normal none infinite animate;
	animation: 20s linear 0s normal none infinite animate;
	animation-direction: alternate;*/
	background: #fafafa;
}

a.nav-sign-up {
	box-shadow: none;
	border-radius: 2pc;
	/*border: 1px solid white;*/
	padding: 0 20px;
	background: #0d992a;
	color: white;
}

.hero-title {
	font-weight: 600;
}

.page-footer {
	/*height: 100px;*/
	background: #002008;
}

.header-row {
	height: 21px;
	background: #00931e;
	margin-bottom: 0;
}

#home-carousel {
	padding: 0;
}

.carousel {
	max-height: 350px
}

.no-bottom-margin {
	margin-bottom: 0;
}

.middle-text {
	padding: 10px;
}

.logo {
	height: 60px;
}

a.no-hover-bg:hover {
	background-color: rgba(0, 0, 0, 0);
}

div.bottom-fixed {
	position: fixed;
	bottom: 0;
	width: 100%;
	height: 25%;
	background-color: #C11B17;
}

div.bottom-fixed button {
	background: none;
	border: none;
	font-size: 20px;
	color: #fff;
}

.logoLombardia {
	height: 60px;
	vertical-align: middle;
	margin-left: 5px;
}

@media ( max-width : 530px) {
	.logoLombardia {
		height: 50px;
	}
}

@media ( max-width : 490px) {
	.logoLombardia {
		height: 40px;
	}
}

@media ( max-width : 455px) {
	.logoLombardia {
		height: 30px;
	}
}

@media ( max-width : 425px) {
	.logoLombardia {
		height: 20px;
	}
}

@media ( max-width : 400px) {
	.logoLombardia {
		height: 0px;
	}
}
</style>

</head>
<body>
	<div id="backgroung-overlay"></div>
	<header>
	<div class="row header-row"></div>
	<nav class="nav-extended home-nav-ext">
	<div class="nav-wrapper">
		<ul class="left brand-logo">
			<!-- <li style="margin-right: 5px">
			  <a class="no-hover-bg" href="http://www.ersaf.lombardia.it">
			    <img class="logo" style="height: 58px; vertical-align: bottom;" src="<spring:url value="/resources/img/home/logo_ersaf.jpg"/>">
			  </a>
			</li>
			<li><div class="hdivider logo" style="width: 1px; background: black;"></div></li>-->
			<li><a class="no-hover-bg"
				href="http://www.regione.lombardia.it"> <img
					class="logoLombardia"
					src="<spring:url value="/resources/img/home/logo_lombardia.png"/>">
			</a></li>
		</ul>

		<ul class="right">
			<li style="margin-right: 5px"><a
				href='<spring:url value="/login"/>' class="nav-sign-up"> <i
					class="material-icons left">person_outline</i>Accedi
			</a></li>
			<li><a href="#!" class="nav-btn-register"></a> <a
				href="<spring:url value="/registrazione/registrazione"/>"
				class="nav-sign-up"> <i class="material-icons left">create</i>Registrati
			</a></li>
			<spring:eval var="manCaronte" expression="T(it.aizoon.ersaf.util.CaronteConstants).MAN_CARONTE" />
			<li style="margin-left: 5px;"><a href="#!" class="nav-btn-register"></a> <a
				href="<spring:url value="/import/servizi/documentazione/scaricaguida/${manCaronte}"	/>"
				class="nav-sign-up"> <i class="material-icons left">file_download</i>Guida
			</a></li>
		</ul>
	</div>
	</nav> </header>
	<main>
	<div class="row no-bottom-margin">
		<div class="col s12 m12 l12" id="home-carousel">
			<div class="carousel carousel-slider">
				<a class="carousel-item" href="#!"> <img
					src="<spring:url value="/resources/img/home/slide3.jpg"/>">
				</a> <a class="carousel-item" href="#!"> <img
					src="<spring:url value="/resources/img/home/slide1.jpg"/>">
				</a> <a class="carousel-item" href="#!"> <img
					src="<spring:url value="/resources/img/home/slide2.jpg"/>">
				</a>
			</div>
		</div>
	</div>
	<div class="row grey lighten-5 no-bottom-margin middle-text">
		<div class="col s12 m12">
			<div class="center-align container"
				style="padding-right: 10%; padding-left: 10%;">
				<h5>
					<b>CARONTE</b> è l’applicativo del Servizio fitosanitario di
					Regione Lombardia che consente la gestione online delle richieste
					di registrazione al RUOP, delle denunce annuali dei vegetali da
					parte dei vivaisti e delle richieste di certificati export e
					riexport per i vegetali e i prodotti vegetali verso i Paesi terzi.
				</h5>
			</div>
		</div>
	</div>
	</main>
	<footer class="page-footer">
	<div class="row center-align">© 2019 Copyright Text</div>
	</footer>

	<spring:eval var="nomeCookie"
		expression="T(it.aizoon.ersaf.util.CaronteConstants).NOME_COOKIE_HOMEPAGE" />
	<!-- PROVA COOKIE:
  <c:out value= "${cookie.containsKey(nomeCookie)}"/>
   -->
	<c:if test="${!cookie.containsKey(nomeCookie)}">
		<div class="bottom-fixed valign-wrapper">
			<div class="container">
				<div class="row valign-wrapper">
					<div class="white-text">
						<h5>Questo sito utilizza cookie tecnici e di profilazione
							propri per le sue funzionalità. Proseguendo nella navigazione o
							chiudendo questo messaggio, acconsenti all'uso dei cookie.</h5>
					</div>
					<button type="button" class="close white-text right"
						aria-label="Chiudi">
						<i class="material-icons">close</i>
					</button>
				</div>
			</div>
		</div>
	</c:if>

	<content tag="script"> <script>
    $('.bottom-fixed .close').click(function() {
    	$.post('<spring:url value="/ajax/accettaCookie" />').done(function() {
    		  console.log("cookie creato");
    	});
    	  
    	$(this).closest(".bottom-fixed").fadeOut();
    });
  </script> </content>

</body>
</html>