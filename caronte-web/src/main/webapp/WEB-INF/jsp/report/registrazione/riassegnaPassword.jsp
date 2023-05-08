<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!doctype html>
<html>
<head>
<title>Benvenuto in Caronte</title>
<style>
body {
	background: linear-gradient(#42ADE2, #80d8ff);
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
</style>
</head>
<body>
	<!-- body class="cyan"-->

	<div id="login-page" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<br />
			<div class="row">
				<div class="col s12 center">
					<img src='<spring:url value="/resources/img/mountain_logo.png"/>'
						class="circle valign profile-image-login">
					<c:choose>
						<c:when test="${riassegnaPsw}">
							<p class="center login-form-text">Riassegna password</p>
						</c:when>
						<c:otherwise>
							<p class="center login-form-text">Assegna password</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<form:form id="formPsw" name='f' action="riassegnaPassword"
				method='POST' class="login-form" modelAttribute="utenteForm"
				accept-charset="utf-8">

				<div class="row col l12"></div>
				<div class="container">
					<!--<c:if test="${riassegnaPsw}">
						<div class="row">
							<div class="left-align input-field col l12">
								<i class="material-icons prefix">lock_outline</i>
								<form:password id="oldPassword" path="oldPassword"
									class="validate" required="required" />
								<form:errors path="oldPassword" cssClass="error" />
								<label for="oldPassword">Vecchia Password *</label>
							</div>
						</div>
					</c:if>-->
					

					
					<c:if test="${token!=null}">
						<form:input path="token" type="hidden" id="token" name="token"/>
						<div class="row">
							<div class="left-align input-field col l12">
								<i class="material-icons prefix">lock_outline</i>
								<form:password id="password" path="password" class="validate"
									required="required" />
								<form:errors path="password" cssClass="error" />
								<label for="password">Nuova Password *</label>
							</div>
						</div>
						<div class="row">
							<div class="left-align input-field col l12">
								<i class="material-icons prefix">lock_outline</i>
								<form:password id="confermaPassword" path="confermaPassword"
									class="validate" required="required" />
								<form:errors path="confermaPassword" cssClass="error" />
								<label for="confermaPassword">Conferma Password *</label>
								<span class="helper-text" data-error="Le password non coincidono" 
								  data-success=""></span>
							</div>
						</div>

						<div class="row col l12">
							<p>La password deve contenere una lettera maiuscola, una
								minuscola, un numero, un carattere speciale e deve essere di
								almeno 8 caratteri.
						</div>

						<div class="row">
							<div class="input-field col s12">
								<button type="submit"
									class="btn waves-effect waves-light col s12 confirm">conferma</button>
							</div>
						</div>
					</c:if>
				</div>
			</form:form>
		</div>
	</div>

	<content tag="script"> <script>
		$(document).ready(function() {

			$('#passLabel').click();

		});

		$("#password").on(
				"focusout",
				function(e) {
					if ($(this).val() != $("#confermaPassword").val()) {
						$("#confermaPassword").removeClass("valid").addClass(
								"invalid");
					} else {
						$("#confermaPassword").removeClass("invalid").addClass(
								"valid");
					}
				});

		$("#confermaPassword").on("keyup", function(e) {
			if ($("#password").val() != $(this).val()) {
				$(this).removeClass("valid").addClass("invalid");
			} else {
				$(this).removeClass("invalid").addClass("valid");
			}
		});
	</script> </content>
</body>
</html>