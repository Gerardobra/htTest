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

	<div id="" class="row">
		<div class="col s12 z-depth-4 card-panel">
			<br />
			<div class="row">
				<div class="col s12 center">
					<img src='<spring:url value="/resources/img/mountain_logo.png"/>'
						class="circle valign profile-image-login">

					<p class="center login-form-text">Password dimenticata</p>
					<div class="row">
						<div class="col l12">
							<c:if test="${err!=null}">
								<div class="card red">
									<div class="card-content white-text">

										<p>${err}</p>
									</div>
								</div>

							</c:if>
						</div>
					</div>
				</div>
			</div>
			<form:form id="formPsw" name='f' action="forgotPassword"
				method='POST' class="login-form" modelAttribute="utenteForm"
				accept-charset="utf-8">

				<div class="container">
					<div class="row">
						<div class="left-align input-field col l12">
							<div class="input-field col s6 m4 l12">
								<form:input type="email" id="email" path="email"
									required="required" />
								<form:errors path="email" cssClass="error" />
								<label for="email">Email *</label>
							</div>

						</div>
					</div>

					<div class="row">
						<div class="input-field col s12">
							<button type="submit"
								class="btn waves-effect waves-light col s12 confirm">invia
								mail reimpostazione password</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>

</body>
</html>