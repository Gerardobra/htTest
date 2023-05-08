<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<body>
	<!-- div class="row">
		<div class="nav-content">
			<ul class="tabs">
				<li class="tab col s2"><a target="_self"
					<c:if test="${secondaryActiveLink == 'profili'}">active</c:if>
					href='<spring:url value="/admin/messaggistica/configura"/>'><strong>MESSAGGISTICA</strong></a></li>
				<li class="tab col s2"><a target="_self"
					class="push-s1 <c:if test="${secondaryActiveLink == 'ispettori'}">active</c:if>"
					href='<spring:url value="/import/servizi/documentazione"/>'><strong>DOCUMENTAZIONE</strong></a></li>
			</ul>
		</div>
	</div-->

	<div class="col l12">
		<div class="card row">
			<div class="card-content" style="padding-top: 2em;">
				<spring:url value="/admin/messaggistica/configura/salva"
					var="formAction" />
				<form:form action="${formAction}" method="get"
					modelAttribute="notificheForm" accept-charset="utf-8">
					<div class="card-title">Messaggistica per gli utenti</div>
					<br/>
					<div class="row">
					    <div class="col s12">
					    <h6>Messaggio Generico</h6>
					    </div>
					</div>
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInserimentoDaGenerico"
								path="dataInserimentoDaGenerico" cssClass="datepicker" />
							<form:errors path="dataInserimentoDaGenerico" cssClass="error" />
							<label for="dataInserimentoDaGenerico">Data inserimento:
								da</label>
						</div>

						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInserimentoAGenerico"
								path="dataInserimentoAGenerico" cssClass="datepicker" />
							<form:errors path="dataInserimentoAGenerico" cssClass="error" />
							<label for="dataInserimentoAGenerico">Data inserimento: a</label>
						</div>

						<div class="input-field col s12 m8 l6">
							<form:textarea id="messaggioGenerico" path="messaggioGenerico"
								cssClass="materialize-textarea validate tooltipped" rows="2"
								data-length="1000" maxlength="1000" data-position="right"
								data-tooltip="Inserire qui il messaggio generico"
								value="${notificheForm.messaggioGenerico}" />
							<form:errors path="messaggioGenerico" cssClass="error" />
						</div>

					</div>

					
                    <div class="row">
					    <div class="col s12">
					    <h6>Messaggio Servizio</h6>
					    </div>
					</div>
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInserimentoDaServizio"
								path="dataInserimentoDaServizio" cssClass="datepicker" />
							<form:errors path="dataInserimentoDaServizio" cssClass="error" />
							<label for="dataInserimentoDaServizio">Data inserimento:
								da</label>
						</div>

						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInserimentoAServizio"
								path="dataInserimentoAServizio" cssClass="datepicker" />
							<form:errors path="dataInserimentoAServizio" cssClass="error" />
							<label for="dataInserimentoAServizio">Data inserimento: a</label>
						</div>

						<div class="input-field col s12 m8 l6">
							<form:textarea id="messaggioServizio" path="messaggioServizio"
								cssClass="materialize-textarea validate tooltipped" rows="2"
								data-length="1000" maxlength="1000" data-position="right"
								data-tooltip="Inserire qui il messaggio di servizio"
								value="${notificheForm.messaggioServizio}" />
							<form:errors path="messaggioServizio" cssClass="error" />
						</div>

					</div>

					<div class="row">
					    <div class="col s12">
					    <h6>Messaggio Certificato</h6>
					    </div>
					</div>
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInserimentoDaCertificato"
								path="dataInserimentoDaCertificato" cssClass="datepicker" />
							<form:errors path="dataInserimentoDaCertificato" cssClass="error" />
							<label for="dataInserimentoDaCertificato">Data inserimento: da</label>
						</div>

						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInserimentoACertificato"
								path="dataInserimentoACertificato" cssClass="datepicker" />
							<form:errors path="dataInserimentoACertificato" cssClass="error" />
							<label for="dataInserimentoACertificato">Data inserimento: a</label>
						</div>

						<div class="input-field col s12 m8 l6">
							<form:textarea id="messaggioCertificato"
								path="messaggioCertificato"
								cssClass="materialize-textarea validate tooltipped" rows="2"
								data-length="1000" maxlength="1000" data-position="right"
								data-tooltip="Inserire qui il messaggio certificato"
								value="${notificheForm.messaggioCertificato}" />
							<form:errors path="messaggioCertificato" cssClass="error" />
						</div>

					</div>
					<div class="card-action">
						<div class="row">
							<div class="col l12 right-align">
								<a class="btn waves-effect waves-light" href='<spring:url value="/home"/>'>ANNULLA</a>
								<button class="btn confirm waves-effect waves-light"
									type="submit" name="cerca">SALVA</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>