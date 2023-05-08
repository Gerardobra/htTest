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
	<div class="card row">
		<div class="card-content">
			<div class="card-title">Gestione ispettori - dettaglio
				ispettore</div>
			<div class="col l12" id="nuovoIstruttore">
				<form:form action="${formAction}" method="post"
					modelAttribute="ispettore" accept-charset="utf-8">
					<div class="row">
						<div class="card-content" style="padding-top: 2em;">
							<div class="card col l12">
								<div class="card-content">
									<div class="row">
										<div class="col l12">
											<div class="input-field col s6 m4 l3">
												<!-- i class="material-icons md-36 prefix">view_list</i-->
												<form:select id="ruolo" path="idRuolo" cssClass="validate"
													disabled="true">
													<form:option value="" label="Selezionare" />
													<form:options items="${tipiProfilo}" itemValue="idRuolo"
														itemLabel="denomRuolo" />
												</form:select>
												<form:errors path="idRuolo" cssClass="error" />
												<label for="ruolo">Profilo</label>
											</div>
											<div class="input-field col s6 m4 l3">
											  <label>
												  <form:checkbox id="abilitato" path="attivo" disabled="true" />
												  <span>Abilitato</span>
												</label>
											</div>
											<div class="input-field col s6 m4 l12">
											<!-- 
											<div class=" col l3">
											  <label>
												  <form:checkbox id="sezioneImport" path="sezioneImport"
													  disabled="true" />
												  <span>Import</span>
												</label>
											</div>
											 -->
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
													<span>Comunicazione vegetali</span>
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
										</div>
									</div>
								</div>
							</div>

							<div class="row col l12">
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
									<form:input type="text" id="codiceFiscale" data-length="16"
										path="codiceFiscale" readonly="true" />
									<label for="codiceFiscale">Codice Fiscale</label>
								</div>
								<div class="input-field col s6 m4 l3">
							              <form:input type="text" name="denomSpedizioniere" placeholder="Selezionare" id="denomSpedizioniere" path="denomSpedizioniere"
							                class="validate autocomplete campiAz" autocomplete="off" readonly="true" />
							              <form:errors path="denomSpedizioniere" cssClass="error" />
							              <label class="active">Operatore *</label>
							    </div>
							</div>
							<div class="row col l12">
								<div class="input-field col s6 m4 l3">
									<form:input type="email" id="email" path="email"
										readonly="true" class="validate" />
									<label for="email">Email *</label>
								</div>

								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="numeroTessera" readonly="true"
										path="numeroTessera" />
									<label for="numeroTessera">Numero Tessera *</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:select id="sesso" path="sesso" cssClass="validate"
										disabled="true">
										<form:option value="" label="Selezionare" />
										<form:option value="M" label="Maschio" />
										<form:option value="F" label="Femmina" />
									</form:select>
									<label for="sesso">Sesso *</label>
								</div>

								<div class="input-field col s6 m4 l3">
									<form:input type='text' id="dataNascita" path="dataNascita"
										disabled="true" cssClass="datepicker" readonly="true" />
									<label for="dataNascita">Data di nascita: *</label>
								</div>

								<div class="input-field col s6 m4 l3">
									<form:select id="idNazioneNascita" path="idNazioneNascita"
										disabled="true" onchange="checkIfITA();" cssClass="validate">
										<form:option value="" label="Selezionare" />
										<form:options items="${listaNazioni}" itemValue="idNazione"
											itemLabel="denomNazione" />
									</form:select>
									<label for="idNazioneNascita">Nazione di nascita</label>
								</div>

								<div id="cittaItaliana" class="input-field col s6 m4 l3">
									<form:input type='text' id="denomComuneNascita" readonly="true"
										path="denomComuneNascita" cssClass="autocomplete" />
									<label for="denomComuneNascita">Comune di nascita: *</label>
								</div>
								<div id="cittaStraniera" style="display: none;" class="input-field col s6 m4 l3">
									<form:input type='text' id="cittaNascita" readonly="true"
										path="cittaNascita" cssClass="autocomplete" />
									<form:errors path="cittaNascita" cssClass="error" />
									<label for="cittaNascita">Comune di nascita:</label>
								</div>

								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="titoloStudio" readonly="true"
										path="titoloStudio" />
									<form:errors path="titoloStudio" cssClass="error" />
									<label for="titoloStudio">Titolo di studio</label>
								</div>

								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="denomComuneUfficio" readonly="true"
										path="denomComuneUfficio" />
									<label for="denomComuneUfficio">Comune ufficio</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="capUfficio" path="capUfficio"
										readonly="true" />
									<label for="capUfficio">CAP Ufficio</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="indirizzoUfficio" readonly="true"
										path="indirizzoUfficio" />
									<label for="indirizzoUfficio">Indirizzo ufficio (Es. Via Roma, 24)</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:textarea id="note" data-length="1000" path="note"
										readonly="true" cssClass="materialize-textarea"
										class="materialize-textarea" />
									<label for="note">Note</label>
								</div>
							</div>
						</div>
					</div>

					<div class="card-action">
						<div class="row col s12 right-align">
							<a href='<spring:url value="/admin/utenti/ispettori/elenco"/>'
								class="btn confirm waves-effect waves-light col">TORNA ALLA
								RICERCA</a>
						</div>
					</div>
				</form:form>

			</div>
		</div>
	</div>
</body>
<content tag="script"> <script>	

function checkIfITA()
{
	if($("#idNazioneNascita").val() == 1)
		{
			$("#cittaItaliana").show();
			$("#cittaStraniera").hide();
		}
	else
		{
			$("#cittaItaliana").hide();
			$("#cittaStraniera").show();
		}

	}
	
$(document).ready(function() {

	$("#denomComuneUfficioStr").val($("#denomComuneUfficio").val());
	$("#denomComuneNascitaStr").val($("#denomComuneNascita").val());
	checkIfITA();

	});
	
	</script></content>
</html>