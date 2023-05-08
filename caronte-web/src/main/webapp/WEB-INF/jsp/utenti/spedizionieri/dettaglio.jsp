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
			<div class="card-title">Gestione operatore - dettaglio
				operatore</div>
			<div class="col s12" id="nuovoProfilo">
				<form:form action="${formAction}" method="post"
					modelAttribute="spedizioniere" accept-charset="utf-8">
					

						<div class="card">
							<div class="card-content">
								<div class="row">
											<!-- 
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneImport" path="sezioneImport" disabled="true"/>
													<span>Import</span>
												</label>
											</div>
											 -->
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneExport" path="sezioneExport" disabled="true"/>
													<span>Export</span>
												</label>
											</div>
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneVivai" path="sezioneVivai" disabled="true"/>
													<span>Vivai</span>
												</label>
											</div>
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneAutorizzazioni" path="sezioneAutorizzazioni" disabled="true"/>
													<span>Autorizzazioni</span>
												</label>
											</div>
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneControlli" path="sezioneControlli" disabled="true"/>
													<span>Controlli</span>
												</label>
											</div>																						
								</div>
								<div class="row">
								   <div class=" col l3">
											  <label>
													<form:checkbox id="autorizPagamPosticip"  disabled="true" path="autorizPagamPosticip" />
													<span>Autoriz. pagamento posticipato</span>
												</label>
									</div>
								</div>

							</div>
						</div>
						<div class="row">
							<div class="section">
								<div class="row col l12">
									<div class="input-field col s6 m4 l6">
										<form:select id="idTipoSpedizioniere" readonly="true"
											onchange="visualizzaNascondiCampi();"
											path="idTipoSpedizioniere" required="required"
											cssClass="validate myRequired azienda aziendaIndividuale">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaTipiSpedizionieri}"
												itemValue="idTipoSpedizioniere"
												itemLabel="denomTipoSpedizioniere" />
										</form:select>
										<form:errors path="idTipoSpedizioniere" cssClass="error" />
										<label for="spedizioniere">Tipo Operatore *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="cuaa" path="cuaa"
											readonly="true" cssCalss="azienda aziendaIndividuale"
											data-length="16" required="required" />
										<form:errors path="cuaa" cssClass="error" />
										<label for="cuaa">CUAA / CF *</label>
									</div>
								</div>

								<div class=" hiddenField row col l12">

									<div id="inputDenomSpedizioniere"
										class="input-field col s6 m4 l6">
										<form:input type="text" id="denomSpedizioniere"
											readonly="true" required="required" cssClass="myRequired"
											path="denomSpedizioniere" />
										<form:errors path="denomSpedizioniere" cssClass="error" />
										<label id="labelDenominazione" for="denomSpedizioniere">Denominazione
											/ Ragione sociale *</label>
									</div>
									<div id="inputNomeDitta" class="input-field col s6 m4 l6">
										<form:input type="text" id="nomeDitta" required="required"
											readonly="true" cssClass="myRequired" path="nomeDitta" />
										<form:errors path="nomeDitta" cssClass="error" />
										<label id="labelNome" for="nomeDitta">Nome *</label>
									</div>
									<div id="inputCognomeDitta" class="input-field col s6 m4 l6">
										<form:input type="text" id="cognomeDitta" readonly="true"
											cssClass="myRequired" path="cognomeDitta" />
										<form:errors path="cognomeDitta" cssClass="error" />
										<label id="labelCognome" for="cognomeDitta">Cognome *</label>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:select id="idProvinciaSedeSociale" required="required"
											readonly="true" onchange="getComuni()"
											path="idProvinciaSedeSociale" cssClass="validate myRequired">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaProvince}"
												itemValue="idProvincia" itemLabel="denomProvincia" />
										</form:select>
										<form:errors path="idProvinciaSedeSociale" cssClass="error" />
										<label id="labelProvincia" for="idProvinciaSedeSociale">Provincia
											sede legale *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:select id="idComuneSedeSociale" required="required"
											path="idComuneSedeSociale" readonly="true"
											cssClass="validate myRequired ">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaComuni}" itemValue="idComune"
												itemLabel="denomComune" />
										</form:select>
										<form:errors path="idComuneSedeSociale" cssClass="error" />
										<label id="labelComune" for="idComuneSedeSociale">Comune
											sede legale *</label>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="capSedeSociale"
											readonly="true" required="required" path="capSedeSociale"
											cssClass="myRequired" />
										<form:errors path="capSedeSociale" cssClass="error" />
										<label id="labelCap" for="capSedeSociale">CAP sede
											legale *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="indirizzoSedeSociale"
											readonly="true" required="required" cssClass="myRequired"
											path="indirizzoSedeSociale" />
										<form:errors path="indirizzoSedeSociale" cssClass="error" />
										<label id="labelIndirizzo" for="indirizzoSedeSociale">Indirizzo
											sede legale (Es. Via Roma, 24)*</label>
									</div>
								</div>

								<div class="hiddenField row col l12">
								  <div class="input-field col s6 m4 l6">
				                    <form:input type="text" id="codiceRuop" 
				                      readonly="true" path="codiceRUOP" />
				                    <label for="codiceRuop">Codice RUOP *</label>
				                  </div>
				                  
				                  <div class="input-field col s6 m4 l6">
				                    <form:input type="text" id="partitaIVA" 
				                      readonly="true" path="partitaIVA" />
				                    <label for="partitaIVA">Partita IVA *</label>
				                  </div>
				         		</div> 
				         		
				                  <div class="hiddenField row col l12">                
									<div class="input-field col s6 m4 l6">
										<form:input type="email" id="emailSpedizioniere"
											readonly="true" cssClass="myRequired"
											path="emailSpedizioniere" class="validate" />
										<form:errors path="emailSpedizioniere" cssClass="error" />
										<label for="emailSpedizioniere">Email *</label>
										<span class="helper-text" data-error="Indirizzo email non valido"
                      						data-success="Indirizzo email valido"></span>
									</div>
								
								
									<div class="input-field col s6 m4 l6">
										<form:input type="email" id="pec" readonly="true"
											cssClass="myRequired" path="pec" class="validate" />
										<form:errors path="pec" cssClass="error" />
										<label id="labelPec" for="pec">PEC *</label>
										<span class="helper-text" data-error="Indirizzo email non valido"
                      					data-success="Indirizzo email valido"></span>
									</div>
									</div>									
									
								<div class="hiddenField row col l12">
									<div class="input-field col s6 m6 l6">
										<form:input type="text" id="numeroCellulare" readonly="true"
											required="required" path="numeroCellulare" />
										<form:errors path="numeroCellulare" cssClass="error" />
										<label for="numeroCellulare">Cellulare (Es. 3471234567)*</label>
									</div>
							
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="numeroTelefono" readonly="true"
											path="numeroTelefono" />
										<form:errors path="numeroTelefono" cssClass="error" />
										<label for="numeroTelefono">Numero di telefono (Es. 0245673467)</label>
									</div>
								</div>
								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="descStatoAzienda"
											readonly="true" cssClass="" path="descStatoAzienda" />										
										<label for="descStatoAzienda">Stato</label>
									</div>
								</div>
								<div class="row">
									<div class="hiddenField row col l6">
										<label for=tipologiaAttivita>Tipologia attività</label>
										<form:input type="text" id="tipologiaAttivita" path="tipologiaAttivita" maxlength="1000" disabled="true"/>
									</div>
									<div class="hiddenField row col l6">
										<label for=tipologiaAttivita>Codice FITOK</label>
										<form:input type="text" id="codiceFitok" path="codiceFitok" maxlength="20" disabled="true"/>
										
									</div>
								</div>
							</div>
						</div>
						
						<div class="row col l12">
						<p class="center-align" style="font-size:20px;margin-bottom:5px;">
					  		<em>Elenco centri aziendali</em>
						</p>
					       	<table id="tabellaCentroAziendale" class="data-table striped display" 
					       		data-order='[[ 1, "asc" ], [ 2, "asc" ], [ 3, "asc" ]]' data-paging="false" data-info="false">
								<thead>
									<tr>
										<th>Stato</th>								
										<th>Denominazione</th>
										<th>Comune</th>																			
										<th>Indirizzo</th>																																												
									</tr>
								</thead>
								<tbody id="bodyTabellaCentriAziendali">										
								  <c:forEach var="centroAz" items="${listaCentriAziendali}">								  
									   <tr>	
									    <td>${centroAz.descrStatoAzienda}</td>																																					
										<td>${centroAz.denominazione}</td>
										<td>${centroAz.denomComune}</td>
										<td>${centroAz.indirizzo}</td>																												
								      </tr>
							      </c:forEach>								
								</tbody>																	
							</table>											
						</div>
						
						
						<div class="card-action">
							<div class="row col s12 right-align">
								<a
									href='<spring:url value="/admin/utenti/spedizionieri/elenco"/>'
									class="btn confirm waves-effect waves-light col">TORNA ALLA
									RICERCA</a>
							</div>
						</div>
				</form:form>
			</div>
		</div>
	</div>

<content tag="script">
<script>
	$(document).ready(function() {

		visualizzaNascondiCampi();
		//ricarica correttamente quando ritorna in pagina 
		$("select").formSelect();

		//disabilito le select
		$(".select-dropdown").attr("disabled", "disabled").attr("readonly", "readonly");
		$("select").attr("disabled", "disabled").attr("readonly", "readonly");
		$("#idTipoSpedizioniere").attr("readonly", "readonly").attr("disabled", "disabled");
		$("#idProvinciaSedeSociale").attr("readonly", "readonly").attr("disabled", "disabled");
		$("#idComuneSedeSociale").attr("readonly", "readonly").attr("disabled", "disabled");
		
	});

	function visualizzaCampiAzienda() {

		//cambio label
		$("#labelProvincia").text("Provincia sede legale *");
		$("#labelComune").text("Comune sede legale *");
		$("#labelIndirizzo").text("Indirizzo sede legale *");
		$("#labelCap").text("CAP sede legale *");
		$("#labelPec").text("PEC *");

		//mostro/nascondo campi
		$("#inputDenomSpedizioniere").show();
		$("#inputNomeDitta").hide();
		$("#inputCognomeDitta").hide();

	}

	function visualizzaCampiAziendaIndividuale() {

		var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();

		//cambio label campi comuni
		if (idTipoSpedizioniere == 4) {
			$("#labelProvincia").text("Provincia ditta *");
			$("#labelComune").text("Comune ditta *");
			$("#labelIndirizzo").text("Indirizzo ditta *");
			$("#labelCap").text("CAP ditta *");
			$("#labelPec").text("PEC *");
		} else {
			$("#labelProvincia").text("Provincia residenza *");
			$("#labelComune").text("Comune residenza *");
			$("#labelIndirizzo").text("Indirizzo residenza *");
			$("#labelCap").text("CAP ditta *");
			$("#labelPec").text("PEC (facoltativa)");
		}

		//mostro/nasocndo campi
		$("#inputDenomSpedizioniere").hide();
		$("#inputNomeDitta").show();
		$("#inputCognomeDitta").show();


	}


	//controlla l'idTipoSpedizioniere e mostra/nasconde i campi corretti
	function visualizzaNascondiCampi() {

		var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();

		$(".hiddenField").show();
		$("select").formSelect();

		if (idTipoSpedizioniere !== undefined && idTipoSpedizioniere != null) {
			if (idTipoSpedizioniere == 1 || idTipoSpedizioniere == 2 || idTipoSpedizioniere == 3) {
				visualizzaCampiAzienda();
			} else if (idTipoSpedizioniere == 4 || idTipoSpedizioniere == 5) {
				visualizzaCampiAziendaIndividuale();
			}
		} else {
			visualizzaCampiAzienda();
		}
	}

	
	
	</script></content>
	</body>
</html>