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

.dropdown-content li>span {
	color: #26a69a;
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
			<div class="card-title">Gestione operatore - dati nuovo
				operatore</div>
			<div class="col l12" id="modificaSpedizioniere">
				<form:form action="${formAction}" method="post"
					modelAttribute="spedizioniereForm" accept-charset="utf-8">
					<div class="row">
						<div class="card">
							<div class="card-content">
								<div class="row">
									<div class="row col s6 m4 l12">
										<!--  
										<div class=" col l3">
										  <label>
												<form:checkbox id="sezioneImport" path="sezioneImport" />
												<span>Import</span>
											</label>
										</div>
										-->
										<div class=" col l3">
										  <label>
												<form:checkbox id="sezioneExport" path="sezioneExport" />
												<span>Export</span>
											</label>
										</div>
										<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneVivai" path="sezioneVivai" />
													<span>Vivai</span>
												</label>
										</div>
										<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneAutorizzazioni" path="sezioneAutorizzazioni" />
													<span>Autorizzazioni</span>
												</label>
										</div>
										<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneControlli" path="sezioneControlli" />
													<span>Controlli</span>
												</label>
										</div>										
										<div class="row col l6">
											<form:errors path="sezioneImport" cssClass="error" />
										</div>
									</div>
									<div class="row col s6 m4 l12">	
									  <div class="row col s6 m4 l12">									  
											  <label>
													<form:checkbox id="autorizPagamPosticip"
														path="autorizPagamPosticip" />
													<span>Autoriz. pagamento posticipato</span>
												</label>
										</div>		
									</div>
								</div>

							</div>
						</div>

						<div class="row">
							<div class="section">
								<div class="row col l12">
									<div class="input-field col s6 m4 l6">
										<form:select id="idTipoSpedizioniere"
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
											cssCalss="azienda aziendaIndividuale" data-length="16"
											required="required" />
										<form:errors path="cuaa" cssClass="error" />
										<label for="cuaa">CUAA / CF *</label>
									</div>
								</div>

								<div class=" hiddenField row col l12">
									<div id="inputTipoSpedizioniereAltro" class="input-field col s6 m4 l6">
										<form:input type="text" id="tipoSpedizioniereAltro" required="required"
											readonly="true" cssClass="myRequired myDisabled"
											maxlength="100" path="tipoSpedizioniereAltro" />
										<form:errors path="tipoSpedizioniereAltro" cssClass="error" />
											<label id="labelTipoSpedizioniereAltro" for="tipoSpedizioniereAltro">Descrizione tipologia Altro *</label>
										</div>
									<div id="inputDenomSpedizioniere"
										class="input-field col s6 m4 l6">
										<form:input type="text" id="denomSpedizioniere"
											required="required"
											cssClass="myRequired" path="denomSpedizioniere" />
										<form:errors path="denomSpedizioniere" cssClass="error" />
										<label id="labelDenominazione" for="denomSpedizioniere">Denominazione
											/ Ragione sociale *</label>
									</div>
									<div id="inputNomeDitta" class="input-field col s6 m4 l6">
										<form:input type="text" id="nomeDitta" required="required"
										    cssClass="myRequired"
											path="nomeDitta" />
										<form:errors path="nomeDitta" cssClass="error" />
										<label id="labelNome" for="nomeDitta">Nome *</label>
									</div>
									<div id="inputCognomeDitta" class="input-field col s6 m4 l6">
										<form:input type="text" id="cognomeDitta" required="required"
											cssClass="myRequired"
											path="cognomeDitta" />
										<form:errors path="cognomeDitta" cssClass="error" />
										<label id="labelCognome" for="cognomeDitta">Cognome *</label>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:select id="idProvinciaSedeSociale" required="required"
											onchange="getComuni()" path="idProvinciaSedeSociale"
											cssClass="validate myRequired">
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
											path="idComuneSedeSociale"
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
											required="required" path="capSedeSociale"
											cssClass="myRequired" />
										<form:errors path="capSedeSociale" cssClass="error" />
										<label id="labelCap" for="capSedeSociale">CAP sede
											legale *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="indirizzoSedeSociale"
											required="required" cssClass="myRequired"
											path="indirizzoSedeSociale" />
										<form:errors path="indirizzoSedeSociale" cssClass="error" />
										<label id="labelIndirizzo" for="indirizzoSedeSociale">Indirizzo
											sede legale (Es. Via Roma, 24)*</label>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="codiceRuop" required="required"
											cssClass="myRequired" path="codiceRUOP" class="validate"
											pattern="[a-zA-Z]{2}-\d{2}-\w{2,}" maxlength="20"
											oninvalid="this.setCustomValidity('Codice RUOP non valido')"
											oninput="this.setCustomValidity('')" />
										<form:errors path="codiceRUOP" cssClass="error" />
										<label for="codiceRuop">Codice RUOP *</label> <span
											class="helper-text" data-error="Codice RUOP non valido"
											data-success="Codice RUOP valido"></span>
									</div>
									
									<div id="inputPartitaIVA"  class="input-field col s6 m4 l6">
										<form:input type="text" id="partitaIVA"
											path="partitaIVA" cssClass="myRequired campiAz" data-length="11" required="required" />
										<form:errors path="partitaIVA" cssClass="error" />
										<label for="partitaIVA">Partita IVA *</label>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="email" id="emailSpedizioniere"
											cssClass="myRequired" path="emailSpedizioniere"
											class="validate" />
										<form:errors path="emailSpedizioniere" cssClass="error" />
										<label for="emailSpedizioniere">Email *</label> <span
											class="helper-text" data-error="Indirizzo email non valido"
											data-success="Indirizzo email valido"></span>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="email" id="pec" cssClass="myRequired"
											path="pec" class="validate" />
										<form:errors path="pec" cssClass="error" />
										<label id="labelPec" for="pec">PEC *</label> <span
											class="helper-text" data-error="Indirizzo email non valido"
											data-success="Indirizzo email valido"></span>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="numeroCellulare"
											required="required" path="numeroCellulare" />
										<form:errors path="numeroCellulare" cssClass="error" />
										<label for="numeroCellulare">Cellulare (Es. 3471234567)*</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="numeroTelefono"
											path="numeroTelefono" />
										<form:errors path="numeroTelefono" cssClass="error" />
										<label id="labelTelefono" for="numeroTelefono">Numero
											di telefono (Es. 0245673467)</label>
									</div>
								</div>
								
							</div>
						</div>

					</div>
					<div class="card-action">

						<div class="row col s12 right-align">
							<button
								class="btn  confirm waves-effect waves-light offset-s1 right-align"
								type="submit" name="datiSpedizioniere">SALVA</button>

							<a
								href='<spring:url value="/admin/utenti/spedizionieri/elenco"/>'
								class="btn waves-effect waves-light col">ANNULLA</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<br />
	<content tag="script"> <script type="text/javascript">

		function getComuni() {

			var idProvincia = $("#idProvinciaSedeSociale").val();
			$.ajax({
				url : '<spring:url value="/registrazione/getListaComuni_"/>' + idProvincia,
				async : false,
				success : function(response) {

					$("#idComuneSedeSociale").html(response);
					$('#idComuneSedeSociale').formSelect();

				}
			});
		}

		$(document).ready(function() {

			//ricarica correttamente quando ritorna in pagina 
			$("select").formSelect();

			//disabilito le select
			$(".select-dropdown").attr("disabled", "disabled");
			$(".select-dropdown").attr("readonly", "readonly");
			$("select").attr("disabled", "disabled");
			$("select").attr("readonly", "readonly");
		
			visualizzaNascondiCampi();

			//se la pagina è pulita
			if ($("#cuaa").val() == null || $("#cuaa").val() == "") {
				//nascondo i campi dell'organizzazione a parte la tipologia ed il cuaa
				$(".hiddenField").hide();
			}
			$("#idTipoSpedizioniere").removeAttr("disabled").removeAttr("readonly");
			$("#idTipoSpedizioniere").formSelect();
			$("#idAssociazioneSezione").removeAttr("disabled").removeAttr("readonly");
			$("#idAssociazioneSezione").formSelect();
		});

		function visualizzaCampiAzienda() {

			//cambio label
			$("#labelProvincia").text("Provincia sede legale *");
			$("#labelComune").text("Comune sede legale *");
			$("#labelIndirizzo").text("Indirizzo sede legale *");
			$("#labelCap").text("CAP sede legale *");
			$("#labelPec").text("PEC *");
			$("#labelTelefono").text("Telefono");

			$("#idProvinciaSedeSociale").removeAttr("disabled").removeAttr("readonly");
			$("#idComuneSedeSociale").removeAttr("disabled").removeAttr("readonly");
			$("#idProvinciaSedeSociale").formSelect();
			$("#idComuneSedeSociale").formSelect();
			
			//mostro/nascondo campi
			$("#inputDenomSpedizioniere").show();
			$("#inputNomeDitta").hide();
			$("#inputCognomeDitta").hide();
			$("#inputNomeDitta").removeAttr("required");
			$("#inputCognomeDitta").removeAttr("required");
			$("#inputTipoSpedizioniereAltro").hide();
			$("#tipoSpedizioniereAltro").val('');

			//sistemo i required
			$(".myRequired").attr("required", "required");
			$("#cognomeDitta").removeAttr("required");
			$("#nomeDitta").removeAttr("required");
			$("#denomSpedizioniere").attr("required", "required");
			$("#tipoSpedizioniereAltro").removeAttr("required");
			
		}

		function visualizzaCampiAziendaIndividuale() {

			var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();

			//cambio label campi comuni
			if (idTipoSpedizioniere == 5) {
				$("#labelProvincia").text("Provincia ditta *");
				$("#labelComune").text("Comune ditta *");
				$("#labelIndirizzo").text("Indirizzo ditta *");
				$("#labelCap").text("CAP ditta *");
				$("#pec").attr("required","required");
				$("#labelPec").text("PEC *");	
				$("#labelTelefono").text("Telefono");
				$("#inputTipoSpedizioniereAltro").hide();
				$("#tipoSpedizioniereAltro").removeAttr("required");
				$("#tipoSpedizioniereAltro").val('');
							
			} else {
				$("#labelProvincia").text("Provincia residenza *");
				$("#labelComune").text("Comune residenza *");
				$("#labelIndirizzo").text("Indirizzo residenza *");
				$("#labelCap").text("CAP *");
				$("#labelPec").text("PEC (facoltativa)");
				$("#labelTelefono").text("Telefono");
				$("#pec").removeAttr("required");
				// solo per la tipologia Altro idTipoAzienda = 1
				$("#tipoSpedizioniereAltro").text("Descrizione tipologia Altro");
				$("#inputTipoSpedizioniereAltro").show();
				$("#tipoSpedizioniereAltro").attr("required","required");
				$("#tipoSpedizioniereAltro").removeAttr("readonly");
			}

			//mostro/nasocndo campi
			$("#inputDenomSpedizioniere").hide();
			$("#inputDenomSpedizioniere").removeAttr("required");
			$("#inputNomeDitta").show();
			$("#inputCognomeDitta").show();

			//abilito gli altri campi
			$("#pec").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
			$("#capSedeSociale").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
			$("#indirizzoSedeSociale").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
			$("#codiceRuop").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
			$("#motivoRichiesta").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");

			//tolgo i required ai campi disabilitati
			$("#denomSpedizioniere").removeAttr("required");

			enableSelect();
		}

		function enableSelect() {

			$(".select-dropdown").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");

			$("#idProvinciaSedeSociale").removeAttr("disabled").removeAttr("readonly");
			$("#idComuneSedeSociale").removeAttr("disabled").removeAttr("readonly");
			$("#idAssociazioneSezione").removeAttr("disabled").removeAttr("readonly");
			$("#idTipoSpedizioniere").removeAttr("disabled").removeAttr("readonly");

			$("#idProvinciaSedeSociale").formSelect();
			$("#idComuneSedeSociale").formSelect();
			$("#idAssociazioneSezione").formSelect();
			$("#idTipoSpedizioniere").formSelect();
		}

		//controlla l'idTipoSpedizioniere e mostra/nasconde i campi corretti
		function visualizzaNascondiCampi() {

			var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();

			$(".hiddenField").show();
			$("select").formSelect();

			if (idTipoSpedizioniere !== undefined && idTipoSpedizioniere != null) {
				if (idTipoSpedizioniere == 4 || idTipoSpedizioniere == 2 || idTipoSpedizioniere == 3) {
					visualizzaCampiAzienda();
				} else if (idTipoSpedizioniere == 1 || idTipoSpedizioniere == 5) {
					visualizzaCampiAziendaIndividuale();
				}
			} else {
				visualizzaCampiAzienda();
			}
		}

		function pulisci() {

			//pulisci form di default e riporto alla situa iniziale
			$("#idTipoSpedizioniere").removeAttr("disabled").removeAttr("readonly");
			$("#idTipoSpedizioniere").formSelect();
			$("#tipoSpedizionireAltro").val("");
			$("#cuaa").removeAttr("disabled").removeAttr("readonly");

			//ridisabilito ciò che inizialmente era disabilitato
			$(".myDisabled").attr("readonly", "readonly");

			$("#email").val("");

			//rimuovo elementi di validazione ed errori
			$(".valid").removeClass("valid");
			$(".invalid").removeClass("invalid");

			$(".error").remove();

			//nascondo i campi per riportare la pagina alla situazione iniziale
			$(".hiddenField").hide();

		}
	</script> </content>
</body>
</html>