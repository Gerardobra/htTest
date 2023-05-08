<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style>
select[required] {
	/*visibility: hidden;
  display: block;
  position: absolute;*/
	opacity: 0;
	display: block;
	position: absolute;
	height: 0;
	border: none;
	margin-top: -60px;
}

.theme-blue {
	background-color: #0277bd !important;
}

.allpad5 {
	padding-bottom: 5px;
	padding-top: 5px;
	padding-left: 5px;
	padding-right: 5px;
}

.card-panel .row {
	margin-bottom: 0px;
	margin-left: 2px;
	margin-right: 2px;
}
</style>
</head>
<body>
	<div class="col s12">
		<div class="card row">
			<div class="card-content">
				<div class="card-title">Gestione profili - dettaglio utenza</div>
				<div class="col s12" id="nuovoProfilo">
					<form:form action="${formAction}" method="post"
						modelAttribute="utente" accept-charset="utf-8">
						<div class="row">
							<div class="col s12 card-content" style="padding-top: 2em;">
								<div class="card">
									<div class="card-content">
										<div class="row">
											<div class="input-field col s6 m4 l3">
												<form:select id="ruolo" path="idRuolo" disabled="true"
													cssClass="validate">
													<form:option value="" label="Selezionare" />
													<form:options items="${tipiProfilo}" itemValue="idRuolo"
														itemLabel="denomRuolo" />
												</form:select>
												<label for="ruolo">Profilo</label>
											</div>	
										 </div>				
											<!-- 							
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneImport" path="sezioneImport"
														disabled="true" />
													<span>Import</span>
												</label>
											</div>
											 -->
										<div class="row"> 
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneExport" path="sezioneExport"
														disabled="true" />
													<span>Export</span>
												</label>
											</div>
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneVivai" path="sezioneVivai"
														disabled="true" />
													<span>Vivai</span>
												</label>
											</div>
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneAutorizzazioni" path="sezioneAutorizzazioni"
														disabled="true" />
													<span>Autorizzazioni</span>
												</label>
											</div>
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneControlli" path="sezioneControlli"
														disabled="true" />
													<span>Controlli</span>
												</label>
											</div>											
										</div>
										<div class="row">
										  <div class="col s6 m4 l3">
											  <label>
													<form:checkbox id="abilitato" path="abilitato"
														disabled="true" />
													<span>Abilitato</span>
												</label>
											</div>
										</div>
									</div>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="cognome" path="cognome"
										readonly="true" />
									<label for="cognome">Cognome</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="nome" path="nome" readonly="true" />
									<label for="nome">Nome</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="codiceFiscale" readonly="true"
										path="codiceFiscale" />
									<label for="codiceFiscale">Codice Fiscale</label>
								</div>
								<c:if
									test="${utente.idTipoSpedizioniere!=1 && utente.idTipoSpedizioniere!=5}">
									<div class="input-field col s6 m4 l3">
							              <form:input type="text" name="denomSpedizioniere" placeholder="Selezionare" id="denomSpedizioniere" path="denomSpedizioniere"
							                class="validate autocomplete campiAz" autocomplete="off" readonly="true" />
							              <form:errors path="denomSpedizioniere" cssClass="error" />
							              <label class="active">Operatore principale *</label>
							    	</div>
								</c:if>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="email" path="email" readonly="true" />
									<label for="email">Email</label>
								</div>
								<div class="input-field col s6 m4 l8">
									<form:textarea id="note" path="note"
										cssClass="materialize-textarea" class="materialize-textarea"
										readonly="true" />
									<label for="note">Note</label>
								</div>

							</div>
						</div>
					</form:form>
				</div>
				<div class="card-action">
					<div class="row col s12 right-align">
						<a href='<spring:url value="/admin/utenti/profili/elenco"/>'
							class="btn confirm waves-effect waves-light col">TORNA ALLA
							RICERCA</a>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>