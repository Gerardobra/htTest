<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="caronte" uri="/WEB-INF/caronte.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage caronte</title>
<style>
#content {
	padding-left: 300px;
}

@media only screen and (max-width: 992px) {
	header, main, footer, #content {
		padding-left: 0;
	}
}

.user-icon {
	width: 100px;
	height: 100px;
}

.left-sidenav {
	top: 64px;
}

.profile-image {
	margin: auto;
}

#user-name, #user-role {
	color: white !important;
}

.slim-title {
	font-weight: 300 !important;
}

.user-info-gradient {
	background: #fd746c; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #ff9068, #fd746c);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #ff9068, #fd746c);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

ul#desktop-menu {
	z-index: 1000;
}

ul#dashboard-leftside-menu {
	z-index: 996;
}

ul.profile-detail {
	padding-bottom: 3em;
}

.myImage {

	-webkit-animation: 20s linear 0s normal none infinite animate;
	-moz-animation: 20s linear 0s normal none infinite animate;
	-ms-animation: 20s linear 0s normal none infinite animate;
	-o-animation: 20s linear 0s normal none infinite animate;
	animation: 20s linear 0s normal none infinite animate;
	animation-direction: alternate;
	background-repeat: no-repeat;
    background-size: 900px 900px;
    	background-image: url('<spring:url value="/resources/img/home/home2.jpg"/>');
}


li.mypad {
	padding-bottom: 0.7em;
	padding-top: 0.7em;
}

i.padIcon {
	padding-right: 0.6em;
}
</style>

</head>
<body>
	
	<aside> <caronte:mobile-menu />


	<ul class="sidenav fixed white left-sidenav"
		id="dashboard-leftside-menu">
		<!--USER STUFF-->
		<li class="user-view center-align"><spring:eval
				expression="T(it.aizoon.ersaf.security.SecurityUtils).getUtenteLoggato()"
				var="utente" />

			<div
				class="background deep-orange accent-2 white-text user-info-gradient">
			</div>
			<div class="row">
				<img src='<spring:url value="/resources/img/user.png"/>' alt=""
					class="circle responsive-img valign profile-image">
			</div>
			<div class="row">
				<c:if test="${not empty utente}">
					<h5 id="user-name" class="slim-title white-text">${utente.denominazione}</h5>
				</c:if>
			</div></li>
		<li><a href="<spring:url value="home"/>"
			class="grey-text text-darken-1"> <i class="material-icons">home</i>
				Home
		</a></li>
		<li><a href="#" class="grey-text text-darken-1"> <i
				class="material-icons">settings</i> Impostazioni
		</a></li>
		<li class="divider"></li>
		<li><a href='<spring:url value="/logout"/>'
			class="red-text text-accent-2"><i class="material-icons">keyboard_tab</i>
				Log out </a></li>
	</ul>
	</aside>

	<section id="content">
	<div class="row" style="padding-top: 2em; padding-left: 5em;">
		<div class="col l10">
			<div class="card horizontal">
				<div class="card-image col l4 myImage">

				</div>
				<div class="card-stacked col l6" style="padding-right: 4em;">
					<div class="card-content card-action">
						<a href="#" style="font-size:18px;">Dati profilo</a>
						<ul class="fixed white" id="dettaglioProfilo">
							<li class="grey-text text-darken-1 mypad"><i
								class="material-icons padIcon">face</i>
								${dettaglioUtente.nome}&nbsp;${dettaglioUtente.cognome}</li>
							<li class="grey-text text-darken-1 mypad"><i
								class="material-icons padIcon">credit_card</i>
								${dettaglioUtente.codiceFiscale}</li>
							<li class="grey-text text-darken-1 mypad"><i
								class="material-icons padIcon">email</i>
								${dettaglioUtente.email}</li>
							<li class="grey-text text-darken-1 mypad"><i
								class="material-icons padIcon">phone</i>
								${dettaglioUtente.telefono}</li>
							<li class="grey-text text-darken-1 mypad"><i
								class="material-icons padIcon">phone_android</i>
								${dettaglioUtente.cellulare}</li>
							<li class="divider"></li>
							<li class="grey-text text-darken-1 mypad"><i
								class="material-icons padIcon">directions_bus</i>
								${dettaglioUtente.denomSpedizioniere}</li>
							<li class="grey-text text-darken-1 mypad"><i
								class="material-icons padIcon">visibility</i>
								${dettaglioUtente.denomRuolo}</li>
							<li class="divider"></li>
							<li class="grey-text text-darken-1 mypad"><i
								class="material-icons padIcon">textsms</i>
								${dettaglioUtente.note}</li>
						</ul>
					</div>
					<div class="card-action">
						<a href="<spring:url value="modificaProfiloUtente"/>">Modifica</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	</section>

</body>
</html>