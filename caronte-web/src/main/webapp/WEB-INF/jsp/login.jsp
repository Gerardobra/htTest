<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Benvenuto in Caronte</title>
<link rel="icon" type="image/png" href="<spring:url value="/resources/img/favicon.png"/>" />
<style>
body {
	/*background: linear-gradient(#42ADE2, #80d8ff);*/
	background: linear-gradient(#00931e, #57CA54);
}

.profile-image-login {
	width: 100px;
	height: 100px !important;
}

.login-form-text {
	text-transform: uppercase;
	letter-spacing: 2px;
	font-size: 0.9rem;
}

.login-text {
	margin-top: -6px;
	margin-left: 12px;
}

.margin {
	margin: -12px !important;
}

html, body {
	height: 100%;
	min-width: 360px;
}

html {
	display: table;
	margin: auto;
}

body {
	display: table-cell;
	vertical-align: middle;
}
.login-button {
  background-color: #00931e;
}
.login-link-margin {
	margin-left: 12px;
	margin-top: -12px;
	margin-bottom: -12px;
}
.login-link {
  color: #00931e;
}
</style>
</head>
<body id="login-page">
	<!-- body class="cyan"-->

	<div class="row">
		<div id="login-panel" class="col s12 z-depth-4 card-panel">
			<br />
			<div class="row">
				<div class="col s12 center">
					<!-- img src='<spring:url value="/resources/img/mountain_logo.png"/>'
						class="circle valign profile-image-login"-->
				  <img src='<spring:url value="/resources/img/tree-2750381_313.png"/>'
            class="circle valign profile-image-login">
					<p class="center login-form-text">Accesso a Caronte</p>
				</div>
			</div>
			<form name='f' action="login" method='POST' class="login-form">
				<div class="row margin">
					<div class="input-field col s12">
						<i class="material-icons prefix">person_outline</i> <input
							type='text' id="username" name='username' value='' required="required"> <label
							for="username" >Email</label>
					</div>
				</div>
				<div class="row margin">
					<div class="input-field col s12">
						<i class="material-icons prefix">lock_outline</i> <input
							type='password' id="password" name='password' required="required" placeholder=" "/> <label
							id="passLabel" for="password">Password</label>
					</div>
				</div>
				<div class="row">
					<div class="col s12 login-text">
						<div class="login-text">
							<label>
								<input type="checkbox" id="rememberme" name="remember-me" />
								<span>Ricordami su questo pc</span>
							</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s12">
						<div class="login-text">
							<button type="submit" 
								class="btn waves-effect waves-light col s12 login-button">login</button>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s5">
						<p class="login-link-margin medium-small">
							<a class="login-link" href="<spring:url value="/registrazione/registrazione"/>">Nuovo utente</a>
						</p>
					</div>
					<div class="input-field col s7">
              <p class="login-link-margin right-align medium-small">
                <a class="login-link" href="<spring:url value="/registrazione/forgotPassword"/>">Password dimenticata?&nbsp;</a></p>
          </div>
				</div>
				<c:if test="${registrazioneOK!=null || riassegnazionePswOK!=null}">
					<div class="row">
						<div class="col l12">
							<div class="card green">
								<div class="card-content white-text">
									<c:if test="${registrazioneOK!=null}">
										<span class="card-title">Registrazione</span>
										<p>La registrazione è avvenuta con successo. Effettuare il login per accedere alla sezione delle Autorizzazioni</p>
										<!-- <p>La registrazione è avvenuta con successo. La richiesta verrà validata da un amministratore e sarà inviata una mail di attivazione all'indirizzo indicato.</p>-->
									</c:if>
									<c:if test="${riassegnazionePswOK!=null}">
										<span class="card-title">Modifica password</span>
										<p>La password è stata salvata con successo. E' necessario effettuare il login.</p>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</c:if>

			</form>
		</div>
	</div>

	<!--<c:if test="${not empty requested}">
		<b style="color:orange;">login richiesto</b>
	</c:if> -->

	<c:if test="${not empty error}">
		<b style="color: red;">${error}</b>
	</c:if>
	<content tag="script"> <script>
		$(document).ready(function() {

			//registerOutsideClickHandler($(window), $('div.swal2-modal'), doNothing);
			registerOutsideClickHandler($(window), $('#login-panel'), checkModalAlert);
			
			//$('#passLabel').click();

		});
		
		function checkModalAlert(event) {
			$modalPanel = $('div.swal2-modal');

			if (!$modalPanel.length/* || !checkIfEventInsideElement(event, $modalPanel)*/) {
				  home();
			}
		}
		
		function home() {
			window.location.href = '<spring:url value="/home"/>';
		}
	</script> </content>
</body>
</html>