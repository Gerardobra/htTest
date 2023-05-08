<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script>
    if (!window.jQuery) {
      document
          .write('<script src="<spring:url value="/resources/js/jquery-3.2.1.min.js"/>"><\/script>');
    }
  </script>
<script type="text/javascript"
	src='<spring:url value="/resources/js/util.js"/>'></script>
</head>
<body>
	<spring:url value="/autorizzazioni/comunicazioni/anagrafica/nuova"
		var="formAction" />
	<form:form action="${formAction}" method="post"
		modelAttribute="nuovaDomandaForm" accept-charset="utf-8">
		<form:input type="hidden" id="idTipoComunicazione"
			path="idTipoComunicazione"
			value="${nuovaDomandaForm.idTipoComunicazione}" />
		<form:input type="hidden" id="idStatoComunicazione"
			path="idStatoComunicazione"
			value="${nuovaDomandaForm.idStatoComunicazione}" />
		<div class="card">
			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding">
					<b>NUOVA DOMANDA : ${nuovaDomandaForm.descTipoDomanda}</b>
					<jsp:include page="../datiAnagraficiAzienda.jsp">
						<jsp:param name="cuaa" value="${nuovaDomandaForm.codFiscaleAz }" />
						<jsp:param name="ruop" value="${nuovaDomandaForm.codiceRuop}" />
						<jsp:param name="ragSociale"
							value="${nuovaDomandaForm.denomAzienda }" />
					</jsp:include>
				</h3>
			</div>
			<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
			<spring:eval var="statoInBozza"
				expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />
			<sec:authorize access="hasRole('ADMIN')" var="isAdmin" />
			<div class="card-content" style="padding-top: 5px;">
				<div class="row">
					<div class="col s12">
						<ul class="tabs">
							<li class="tab"><a class="active"><b>Anagrafica</b></a></li>
							<c:if test="${not empty nuovaDomandaForm.idDomanda}">
								<li class="tab"><a
									href="<spring:url value="/autorizzazioni/comunicazioni/azienda/nuova" />"
									target="_self">Dati operatore</a></li>
								<spring:eval var="idTipoDomandaRUOP"
									expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP" />
								<spring:eval var="idTipoDomandaVariazioneRUOP"
									expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP" />
								<spring:eval var="idTipoDomandaCancellazioneRUOP"
									expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP" />
								<spring:eval var="idTipoDomandaPassaporto"
									expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO" />
								<!-- in caso di Cancellazione non mostro i seguenti tab -->
								<c:if
									test="${idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
									<c:if test="${ idTipoDomandaVariazioneRUOP == nuovaDomandaForm.idTipoComunicazione || idTipoDomandaRUOP == nuovaDomandaForm.idTipoComunicazione}">
								  <c:forEach var="ceAz" items="${nuovaDomandaForm.listaCentriAz}">
			        				  <li class="tab" >
			        					<a href="<spring:url value="/autorizzazioni/comunicazioni/centroaziendale/nuova/${ceAz.idCentroAziendale}" />" target="_self">${ceAz.codCentroAziendale}</a>
			        				  </li>
		        				  </c:forEach>
		        				</c:if>
									<c:if test="${nuovaDomandaForm.tabImport}">
										<li class="tab"><a
											href="<spring:url value="/autorizzazioni/comunicazioni/impAuto/nuova" />"
											target="_self">Import</a></li>
									</c:if>
									<c:if test="${nuovaDomandaForm.tabExport}">
										<li class="tab"><a
											href="<spring:url value="/autorizzazioni/comunicazioni/expAuto/nuova" />"
											target="_self">Export</a></li>
									</c:if>
									<c:if test="${nuovaDomandaForm.tabPassaporto}">
										<li class="tab"><a
											href="<spring:url value="/autorizzazioni/comunicazioni/passaporto/nuova" />"
											target="_self">Passaporto</a></li>
									</c:if>
								</c:if>
								<li class="tab"><a
									href="<spring:url value="/autorizzazioni/comunicazioni/allegati/nuova" />"
									target="_self">Allegati</a></li>
							</c:if>
						</ul>
					</div>
				</div>
				<div class="card-panel hoverable">I dati devono riferirsi al
					rappresentante legale</div>
				<!-- DATI ANAGRAFICI PERSONA -->
				<div class="row">
					<c:choose>
						<c:when test="${isSuperUser || isAdmin}">
							<c:choose>
								<c:when
									test="${idTipoDomandaVariazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="cognome" path="cognome"
											cssClass="validate campiUt" required="required"
											maxlength="50" />
										<form:errors path="cognome" cssClass="error" />
										<label for="cognome">Cognome *</label>
									</div>
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="nome" path="nome"
											cssClass="validate campiUt" required="required"
											maxlength="50" />
										<form:errors path="nome" cssClass="error" />
										<label for="nome">Nome *</label>
									</div>
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="codFiscale" path="codFiscale"
											cssClass="validate campiUt" required="required"
											maxlength="16" onchange="checkIfUtenteExists();" />
										<form:errors path="codFiscale" cssClass="error" />
										<label for="codFiscale">Codice fiscale *</label>
									</div>
								</c:when>
								<c:otherwise>
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="cognome" path="cognome"
											cssClass="validate campiUt" required="required"
											maxlength="50" disabled="true" />
										<form:errors path="cognome" cssClass="error" />
										<label for="cognome">Cognome *</label>
									</div>
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="nome" path="nome"
											cssClass="validate campiUt" required="required"
											maxlength="50" disabled="true" />
										<form:errors path="nome" cssClass="error" />
										<label for="nome">Nome *</label>
									</div>
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="codFiscale" path="codFiscale"
											cssClass="validate campiUt" required="required"
											maxlength="16" disabled="true" />
										<form:errors path="codFiscale" cssClass="error" />
										<label for="codFiscale">Codice fiscale *</label>
									</div>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when
									test="${idTipoDomandaVariazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="cognome" path="cognome"
											cssClass="validate campiUt" required="required"
											maxlength="50" disabled="true" />
										<form:errors path="cognome" cssClass="error" />
										<label for="cognome">Cognome *</label>
									</div>
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="nome" path="nome"
											cssClass="validate campiUt" required="required"
											maxlength="50" disabled="true" />
										<form:errors path="nome" cssClass="error" />
										<label for="nome">Nome *</label>
									</div>
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="codFiscale" path="codFiscale"
											cssClass="validate campiUt" required="required"
											maxlength="16" disabled="true" />
										<form:errors path="codFiscale" cssClass="error" />
										<label for="codFiscale">Codice fiscale *</label>
									</div>
								</c:when>
								<c:otherwise>
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="cognome" path="cognome"
											cssClass="validate campiUt" required="required"
											maxlength="50" disabled="true" />
										<form:errors path="cognome" cssClass="error" />
										<label for="cognome">Cognome *</label>
									</div>
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="nome" path="nome"
											cssClass="validate campiUt" required="required"
											maxlength="50" disabled="true" />
										<form:errors path="nome" cssClass="error" />
										<label for="nome">Nome *</label>
									</div>
									<div class="input-field col s6 m4 l3">
										<form:input type="text" id="codFiscale" path="codFiscale"
											cssClass="validate campiUt" required="required"
											maxlength="16" disabled="true" />
										<form:errors path="codFiscale" cssClass="error" />
										<label for="codFiscale">Codice fiscale *</label>
									</div>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="row">
					<div class="input-field col s6 m4 l3 idProvNascita">
						<form:select id="provinciaNascita" path="idProvinciaNascita"
							cssClass="validate campiUt" required="required" disabled="">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaProvince}" itemValue="idProvincia"
								itemLabel="denomProvincia" />
						</form:select>
						<form:errors path="idProvinciaNascita" cssClass="error" />
						<label for="idProvinciaNascita">Provincia Nascita *</label>
					</div>
					<div class="input-field col s6 m4 l3 idComuneNascita">
						<form:select id="comuneNascita" path="idComuneNascita"
							cssClass="validate campiUt" required="required" disabled="">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaComuniNascita}" itemValue="idComune"
								itemLabel="denomComune" />
						</form:select>
						<form:errors path="idComuneNascita" cssClass="error" />
						<label for="comuneNascita">Comune Nascita *</label>
					</div>
					<div class="input-field col s6 m4 l3">
						<label> <form:checkbox path="nascitaEstera"
								id="nascitaEstera" cssClass="campiUt" /> <span>Nascita
								in Stato estero</span>
						</label>
					</div>
					<div class="input-field col s6 m4 l3 idNazioneEstNascita">
						<form:select id="nazioneEstNascita" path="idNazioneEstNascita"
							cssClass="validate campiUt" required="required" disabled="">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaNazioniNascita}"
								itemValue="idNazione" itemLabel="denomNazione" />
						</form:select>
						<label for="idNazioneEstNascita">Stato Nascita *</label>
						<form:errors path="idNazioneEstNascita" cssClass="error" />
					</div>
					<div class="input-field col s6 m4 l3">
						<form:input type='text' id="dataNascita" path="dataNascita"
							cssClass="datepicker_birthdate campiUt" required="required" />
						<form:errors path="dataNascita" cssClass="error" />
						<label for="dataNascita">Data nascita *</label>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s6 m4 l3 idProvResidenza">
						<form:select id="provinciaResidenza" path="idProvinciaResidenza"
							cssClass="validate campiUt" required="required">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaProvince}" itemValue="idProvincia"
								itemLabel="denomProvincia" />
						</form:select>
						<form:errors path="idProvinciaResidenza" cssClass="error" />
						<label for="idProvinciaResidenza">Provincia Residenza *</label>
					</div>
					<div class="input-field col s6 m4 l3 idComuneResidenza">
						<form:select id="comuneResidenza" path="idComuneResidenza"
							cssClass="validate campiUt" required="required">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaComuniResidenza}"
								itemValue="idComune" itemLabel="denomComune" />
						</form:select>
						<form:errors path="idComuneResidenza" cssClass="error" />
						<label for="idComuneResidenza">Comune Residenza *</label>
					</div>
					<div class="input-field col s6 m4 l3">
						<form:input type="text" id="cap" required="required" path="cap"
							cssClass="validate campiUt" maxlength="10" />
						<form:errors path="cap" cssClass="error" />
						<label for="cap">Cap residenza *</label>
					</div>
					<div class="input-field col s6 m4 l3">
						<label> <form:checkbox path="residenzaEstera"
								id="residenzaEstera" cssClass="campiUt" /> <span>Residenza
								in Stato estero</span>
						</label>
					</div>
					<!-- <div class="input-field col s6 m4 l3">
						<form:input type="text" id="denomComuneEstResid"
							path="denomComuneEstResid" cssClass="validate campiUt"
							required="required" maxlength="100" />
						<form:errors path="denomComuneEstResid" cssClass="error" />
						<label for="denomComuneEstResid">Comune estero di residenza *</label>
					</div>-->
					<div class="input-field col s6 m4 l3">
						<form:select id="nazioneEstResid" path="idNazioneEstResid"
							cssClass="validate campiUt" required="required" disabled="">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaNazioniResid}" itemValue="idNazione"
								itemLabel="denomNazione" />
						</form:select>
						<label for="idNazioneEstResid">Stato Residenza *</label>
						<form:errors path="idNazioneEstResid" cssClass="error" />
					</div>

					<div class="input-field col s12 m8 l6">
						<form:input type="text" id="indirizzo" required="required"
							path="indirizzo" cssClass="validate campiUt" maxlength="200" />
						<form:errors path="indirizzo" cssClass="error" />
						<label for="indirizzo">Indirizzo residenza (Es. Via Roma,
							24)*</label>
					</div>
					<div class="input-field col s6 m4 l3">
						<form:input type="text" id="numTelefono" path="numTelefono"
							cssClass="validate campiUt" maxlength="30" />
						<form:errors path="numTelefono" cssClass="error" />
						<label for="numTelefono">Numero di telefono (Es.
							0245673467)</label>
					</div>
					<div class="input-field col s6 m4 l3">
						<form:input type="text" id="cellulare" path="cellulare"
							cssClass="validate campiUt" required="required" maxlength="30" />
						<form:errors path="cellulare" cssClass="error" />
						<label for="cellulare">Numero di cellulare (Es.
							3471234567)*</label>
					</div>
					<div class="input-field col s6 m5 l4">
						<form:input type="text" id="email" path="email"
							cssClass="validate campiUt" required="required" maxlength="30"
							disabled="true" />
						<form:errors path="email" cssClass="error" />
						<label for="email">Email *</label>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>'
				class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
			<div class="right" style="text-align: right">
				<button id="salvaDomanda" type="submit"
					class="btn confirm right waves-effect waves-light"
					style="display: inline-block">SALVA</button>
			</div>
		</div>
		<spring:eval var="statoInBozza"
			expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />

	</form:form>


	<content tag="script"> <script>
	    
	  // Al caricamento della pagina
	  $(document).ready(function() {
	     // Controllo sulle checkBox per visualizare o  meno i campi ad esse legati
		 checkNascitaEstera();
		 checkResidenzaEstera();
		 var idTipo = $("#idTipoComunicazione").val();
		 if(idTipo == 3){
			 disableFields();
		 }
	  })
	  
	  
	  //Popolamento dinamico della select Comune nascita
	  $('#provinciaNascita').change(
				function() {
					var params = {
						"idProvincia" : $(this).val()
					}
	
					getSelectAsincrona(
							'comuneNascita',
							'<spring:url value="/ajax/getComuniProvincia" />',
							params,
							getOptionComune, 1);
		});
			
		//Popolamento dinamico della select Comune residenza
		$('#provinciaResidenza').change(
				function() {
					var params = {
						"idProvincia" : $(this).val()
					}
	
					getSelectAsincrona(
							'comuneResidenza',
							'<spring:url value="/ajax/getComuniProvincia" />',
							params,
							getOptionComune, 1);
		});
			
		function getOptionComune(comune) {
		  return getOption(comune.idComune, comune.denomComune);
		}
		
		
		
	  
	   // *** Checkbox 'Residenza in Stato estero 'residenzaEstera'
		$('#residenzaEstera').change(function() {
			checkResidenzaEstera();
		});
			
		// Se viene checkato 'Residenza in Stato estero' devono essere disbilitati e non visualizzati i campi : 'Provincia residenza e Comune residenza', mentre deve essere abilitato e visualizzato il campo 'Comune estero di residenza'
		function checkResidenzaEstera(){
				console.log('checkResidenzaEstera');
				if ($('#residenzaEstera').is(":checked")) {
					console.log('-- selezione residenzaEstera');	
					
					$('#provinciaResidenza').formSelect();
					$('#comuneResidenza').formSelect();
					$('#nazioneEstResid').formSelect();
					
					$('#residenzaEstera').val('true');
			    	
					
					$("#provinciaResidenza").val("");
					$("#comuneResidenza").val("");
					$("#cap").val("");
					
					$("#provinciaResidenza").attr('disabled', true);
					$("#comuneResidenza").attr('disabled', true);
					
					$("#provinciaResidenza").removeAttr("required");
					$("#comuneResidenza").removeAttr("required");
					
					$("#cap").attr('disabled', true);
					$("#cap").removeAttr("required");
					
					$("#provinciaResidenza").closest('div.input-field').hide();
					$("#comuneResidenza").closest('div.input-field').hide();					
					$("#cap").closest('div.input-field').hide();									
					
					
					$("#nazioneEstResid").attr('disabled', false);
					$("#nazioneEstResid").attr("required", "required");
					$("#nazioneEstResid").closest('div.input-field').show();	
					
					$('#provinciaResidenza').formSelect();
					$('#comuneResidenza').formSelect();
					$('#nazioneEstResid').formSelect();
					
				}
				else {
					console.log('-- deselezione residenzaEstera');
					
					$('#provinciaResidenza').formSelect();
					$('#comuneResidenza').formSelect();
					$('#nazioneEstResid').formSelect();
					
					$('#residenzaEstera').val('false');
					
					$("#provinciaResidenza").attr('disabled', false);	
					$("#provinciaResidenza").attr("required", "required");
					$("#comuneResidenza").attr('disabled', false);
					$("#comuneResidenza").attr("required", "required");
					$("#cap").attr('disabled', false);
					$("#cap").attr("required", "required");
					
					$("#provinciaResidenza").closest('div.input-field').show();
					$("#comuneResidenza").closest('div.input-field').show();					
					$("#cap").closest('div.input-field').show();
			    	  					
					$("#nazioneEstResid").val("");
					$("#nazioneEstResid").attr('disabled', true);
					$("#nazioneEstResid").removeAttr("required");
					$("#nazioneEstResid").closest('div.input-field').hide();	
					
					$('#provinciaResidenza').formSelect();
					$('#comuneResidenza').formSelect();
					$('#nazioneEstResid').formSelect();
					
			    }
				M.updateTextFields();
			}
			
			// *** Checkbox 'Nascita in Stato estero'
			$('#nascitaEstera').change(function() {
				checkNascitaEstera();
			});
			
			// Se viene checkato 'Nascita in Stato estero' devono essere disabilitati e non visualizzati i campi : 'Provincia nascita e Comune nascita', mentre deve essere abilitato e visualizzato il campo 'Comune estero di nascita'
			function checkNascitaEstera() {	
				console.log('checkNascitaEstera');
				if ($('#nascitaEstera').is(":checked")) {
					console.log('selezione nascitaEstera');
					
					$('#provinciaNascita').formSelect();
					$('#comuneNascita').formSelect();
					$('#nazioneEstNascita').formSelect();
					
					$('#nascitaEstera').val('true');
					
					$("#provinciaNascita").val("");
					$("#comuneNascita").val("");
					$("#provinciaNascita").attr('disabled', true);
					$("#comuneNascita").attr('disabled', true);
					$("#provinciaNascita").removeAttr("required");
					$("#comuneNascita").removeAttr("required");
					$("#provinciaNascita").closest('div.input-field').hide();
					$("#comuneNascita").closest('div.input-field').hide();
					
									
					$("#nazioneEstNascita").attr('disabled', false);
					$("#nazioneEstNascita").attr("required", "required");
					$("#nazioneEstNascita").closest('div.input-field').show();
					
					$('#provinciaNascita').formSelect();
					$('#comuneNascita').formSelect();
					$('#nazioneEstNascita').formSelect();
					
				}
				else {
					console.log('deselezione nascitaEstera');
					
					$('#provinciaNascita').formSelect();
					$('#comuneNascita').formSelect();
					$('#nazioneEstNascita').formSelect();
					
					$('#nascitaEstera').val('false');
										
					$("#provinciaNascita").attr('disabled', false);		
					$("#provinciaNascita").attr("required", "required");
					$("#comuneNascita").attr('disabled', false);
					$("#comuneNascita").attr("required", "required");
					
					$("#provinciaNascita").closest('div.input-field').show();
					$("#comuneNascita").closest('div.input-field').show();
										
					$("#nazioneEstNascita").val("");
					$("#nazioneEstNascita").attr('disabled', true);
					$("#nazioneEstNascita").removeAttr("required");
					$("#nazioneEstNascita").closest('div.input-field').hide();
					
					$('#provinciaNascita').formSelect();
					$('#comuneNascita').formSelect();
					$('#nazioneEstNascita').formSelect();
			    }
				
				M.updateTextFields();
		    }
			
			// Richiamata all'onchange del campo codice fiscale per precaricare i dati se gia presenti su car_t_utente
			function checkIfUtenteExists(){
				console.log('checkIfUtenteExists');
				var codiceFiscaleInput = $("#codFiscale").val();
				console.log('codiceFiscaleInput ='+codiceFiscaleInput);
				if (codiceFiscaleInput !== undefined && codiceFiscaleInput != null && codiceFiscaleInput.trim().length == 16) {
					$.ajax({
						url : '<spring:url value="/autorizzazioni/comunicazioni/checkIfUtenteExists_"/>'+ codiceFiscaleInput.trim(),
						success : function(response) {
							if (response.indexOf("NOTEXISTS") == -1) {
								console.log('utente trovato su car_t_utente');
							    
								// valorizzo i campi con i valori trovati
								var splittedStr = response.split("&&&");
							    
								/*   utente.getIdUtente()+ "&&&" +
						    		 utente.getCognome() + "&&&" +
						    		 utente.getNome() + "&&&" +
						    		 utente.getCodiceFiscale() + "&&&" + 
						    		 utente.getDataNascita() + "&&&" +
						    		 idProvinciaNasc + "&&&" +
						    		 utente.getIdComuneNascita() + "&&&" +
						    		 utente.getDenomComuneEstNascita() + "&&&" +
						    		 idProvinciaResid + "&&&" +
						    		 utente.getIdComuneResidenza() + "&&&" +
						    		 utente.getCap() + "&&&" +
						    		 utente.getDenomComuneEstResid() + "&&&" +
						    		 utente.getIndirizzo() + "&&&" +
						    		 utente.getTelefono() + "&&&" +
						    		 utente.getCellulare() + "&&&" +
						    		 utente.getEmail(); 
								*/
								
							    var idUtente = splittedStr[0];
								console.log('idUtente ='+idUtente);
								
							    var cognome = splittedStr[1];
							    console.log('cognome ='+cognome);
							    var nome = splittedStr[2];
							    console.log('nome ='+nome);
							    var codFiscale = splittedStr[3];
							    
							    // Dati nascita
							    var dataNascita = splittedStr[4];
							    console.log('-- dataNascita ='+dataNascita);
							    var idProvNascita = splittedStr[5];
							    console.log('idProvNascita ='+idProvNascita);
							    var idComuneNascita = splittedStr[6];
							    console.log('idComuneNascita ='+idComuneNascita);
							    var denomComuneEstNascita = splittedStr[7];
							    console.log('denomComuneEstNascita ='+denomComuneEstNascita);
							    
							    // Dati residenza
							    var idProvinciaResid = splittedStr[8];
							    console.log('idProvinciaResid ='+idProvinciaResid);
							    var idComuneResid = splittedStr[9];
							    console.log('idComuneResid ='+idComuneResid);
							    var cap = splittedStr[10]; 
							    console.log('cap ='+cap);
							    var denomComuneEstResid = splittedStr[11];
							    console.log('denomComuneEstResid ='+denomComuneEstResid);
							    var indirizzo = splittedStr[12];
							    console.log('indirizzo ='+indirizzo);
							    
							    var telefono = splittedStr[13];
							    console.log('telefono ='+telefono);
							    var cell = splittedStr[14];
							    console.log('cell ='+cell);
							    var email = splittedStr[15];
							    console.log('email ='+email);
							    
							    var comuniNascita = splittedStr[16];
							    //console.log('comuniNascita ='+comuniNascita);
							    var comuniResid = splittedStr[17];
							    //console.log('comuniResid ='+comuniResid);
							    
							    var year = splittedStr[18];
							    console.log('year ='+year);
							    var month = splittedStr[19];
							    console.log('month ='+month);
							    var day = splittedStr[20];
							    console.log('day ='+day);
							    var idNazioneEstNascita = splittedStr[21];
							    console.log('idNazioneEstNascita ='+idNazioneEstNascita);
							    var idNazioneEstResid = splittedStr[22];
							    console.log('idNazioneEstResid ='+idNazioneEstResid);
						
							    //svuoto i campi
								$(".campiUt").val("");							   							    
							    
								//abilito le select per poterle modificare
								$(".select-dropdown").removeAttr("disabled");
								$(".select-dropdown").removeAttr("readonly");
								$("select").removeAttr("disabled");
								$("select").removeAttr("readonly");
							    
								if(cognome !== undefined && cognome != null && cognome != 'null'){
								  $("#cognome").val(cognome);
								}								
								if(nome !== undefined && nome != null && nome != 'null'){
								  $("#nome").val(nome);
								}							   							    
							    $("#codFiscale").val(codFiscale);
							    							    
    							// Dati nascita
    							console.log('Setto data nascita');
    							$("input[name='dataNascita']")[0].value = dataNascita;
    							var element = document.querySelector('.datepicker_birthdate');
    							M.Datepicker.getInstance(element).setDate(new Date(year,month-1,day), true);    							
    							$("label[for='dataNascita']").addClass("active");
    							
							    if(idProvNascita !== undefined && idProvNascita != null && idProvNascita != 'null'){	
							    	$("#comuneNascita").html(comuniNascita);
									$('#comuneNascita').formSelect();	
									
									console.log('Seleziono prov nascita ='+idProvNascita);
							    	$("#provinciaNascita").val(idProvNascita);
							    	
							    	if(idComuneNascita !== undefined && idComuneNascita != null && idComuneNascita != 'null'){
							    	  console.log('Seleziono comune nascita ='+idComuneNascita);
							    	  $("#comuneNascita").val(idComuneNascita);
								    }
								    else{
								      $("#comuneNascita").val("");
								    }							    	
							    	$('#nascitaEstera').prop('checked', false);
							    	checkNascitaEstera();
							    }
							    else{							    	
							    	console.log('Provincia e comune di nascita non presenti sul db');
							    	
							    	$('#provinciaNascita').formSelect();
							    	$('#comuneNascita').formSelect();
							    	$('#nazioneEstNascita').formSelect();
							    	
							    	$("#provinciaNascita").val("");
							    	$("#comuneNascita").val("");
							    	
							    	console.log('Controllo se abbiamo idNazioneEstNascita');
							        if(idNazioneEstNascita !== undefined && idNazioneEstNascita != null && idNazioneEstNascita != 'null'){
							    	  console.log('Stato nascita estero presente sul db ='+idNazioneEstNascita);
									  
							    	  $('#nascitaEstera').prop('checked', true);
									  $('#nascitaEstera').val('true');
									  									  
									  $("#nazioneEstNascita").val(idNazioneEstNascita);										  
									  						
									  document.getElementById("provinciaNascita").disabled = true;									 			    	
									  document.getElementById("comuneNascita").disabled = true;
									 									  
									  var provinciaNascita = document.getElementById("provinciaNascita");									  
									  provinciaNascita.style.display = "none";									  									  

									  var comuneNascita = document.getElementById("comuneNascita");									  
									  comuneNascita.style.display = "none";							

									  document.getElementById("nazioneEstNascita").disabled = false;
									  var nazioneEstNascita = document.getElementById("nazioneEstNascita");
									  nazioneEstNascita.style.display = "block";

									  
									  $('#provinciaNascita').formSelect();
									  $('#comuneNascita').formSelect();
									  $('#nazioneEstNascita').formSelect();
									  
									  checkNascitaEstera();
							    }
								else{
									  $('#provinciaNascita').formSelect();
									  $('#comuneNascita').formSelect();
									  $('#nazioneEstNascita').formSelect();
									  
								      console.log('Stato nascita estero NON presente sul db');
								      								      
								      document.getElementById("nascitaEstera").checked = false;
									  $("#nazioneEstNascita").val("");
									  
									  $('#nascitaEstera').val('false');
																			  
									  document.getElementById("provinciaNascita").disabled = false;									  
									  document.getElementById("comuneNascita").disabled = false;
																			  
									  var provinciaNascita = document.getElementById("provinciaNascita");									  
									  provinciaNascita.style.display = "block";										  
									  var comuneNascita = document.getElementById("comuneNascita");									  
									  comuneNascita.style.display = "block";	
																													  
									  document.getElementById("nazioneEstNascita").disabled = true;
									  var nazioneEstNascita = document.getElementById("nazioneEstNascita");
									  nazioneEstNascita.style.display = "none";									  
									  
									  $('#provinciaNascita').formSelect();
									  $('#comuneNascita').formSelect();
									  $('#nazioneEstNascita').formSelect();
								}
							   }
							   
							    	
							    
							    // Dati Residenza
							    if(idProvinciaResid !== undefined && idProvinciaResid != null && idProvinciaResid != 'null'){	
							    	console.log('Provincia e comune di residenza presenti sul db');
							    	
							    	$("#comuneResidenza").html(comuniResid);
									$('#comuneResidenza').formSelect();	
									
									console.log('Seleziono prov residenza ='+idProvinciaResid);
							    	$("#provinciaResidenza").val(idProvinciaResid);
							    	
							    	if(idComuneResid !== undefined && idComuneResid != null && idComuneResid != 'null'){
							    	  console.log('Seleziono comune residenza ='+idComuneResid);
							    	  $("#comuneResidenza").val(idComuneResid);
								    }
								    else{
								      $("#comuneResidenza").val("");
								    }
							    	$('#residenzaEstera').prop('checked', false);
							    	checkResidenzaEstera();
							    }
							    else{
							    	console.log('Provincia e comune di residenza NON presenti sul db');
							    	
							    	$('#provinciaResidenza').formSelect();
							    	$('#comuneResidenza').formSelect();
							    	$('#nazioneEstResid').formSelect();
							    	
							    	$("#provinciaResidenza").val("");
							    	$("#comuneResidenza").val("");
							    	
							    	console.log('Controllo se abbiamo idNazioneEstResidenza');
							    	if(idNazioneEstResid !== undefined && idNazioneEstResid != null && idNazioneEstResid != 'null'){
								    	console.log('Stato residenza estera presente sul db = '+idNazioneEstResid);
										$('#residenzaEstera').prop('checked', true);
										$('#residenzaEstera').val('true');
										  
										$("#nazioneEstResid").val(idNazioneEstResid);
																																								
										document.getElementById("provinciaResidenza").disabled = true;										  			    	
										document.getElementById("comuneResidenza").disabled = true;
										 
										var provinciaResidenza = document.getElementById("provinciaResidenza");									  
										provinciaResidenza.style.display = "none";									  
										  										  
										var comuneResidenza = document.getElementById("comuneResidenza");									  
										comuneResidenza.style.display = "none";							
																															  
										document.getElementById("nazioneEstResid").disabled = false;
										var nazioneEstResid = document.getElementById("nazioneEstResid");
										nazioneEstResid.style.display = "block";
										  
										$('#provinciaResidenza').formSelect();
										$('#comuneResidenza').formSelect();
										$('#nazioneEstResid').formSelect();
										  
										checkResidenzaEstera();
									}
									else{													
									  $('#provinciaResidenza').formSelect();
									  $('#comuneResidenza').formSelect();
									  $('#nazioneEstResid').formSelect();
									  
									  console.log('Stato residenza estero NON presente sul db');
									  
									  document.getElementById("residenzaEstera").checked = false;
									  $("#nazioneEstResid").val("");
										  
									  $('#residenzaEstera').val('false');
										
									  document.getElementById("provinciaResidenza").disabled = false;									 
									  document.getElementById("comuneResidenza").disabled = false;
										
									  var provinciaResidenza = document.getElementById("provinciaResidenza");									  
									  provinciaResidenza.style.display = "block";										  
									  var comuneResidenza = document.getElementById("comuneResidenza");									  
									  comuneResidenza.style.display = "block";	
																				
									  document.getElementById("nazioneEstResid").disabled = true;
									  var nazioneEstResid = document.getElementById("nazioneEstResid");
									  nazioneEstResid.style.display = "none";									  
									  									  
									  
									  $('#provinciaResidenza').formSelect();
									  $('#comuneResidenza').formSelect();
									  $('#nazioneEstResid').formSelect();
									}
							    	
							    	
							    }
							    
							    if(cap !== undefined && cap != null && cap != 'null'){
							      $("#cap").val(cap);
							    }
							    else{
							      $("#cap").val("");
							    }
							  
							    
							    if(indirizzo !== undefined && indirizzo != null && indirizzo != 'null'){
							      $("#indirizzo").val(indirizzo);
							    }
							    else{
							      $("#indirizzo").val("");
							    }
							    
							    if(telefono !== undefined && telefono != null && telefono != 'null'){
							      $("#numTelefono").val(telefono);
							    }
							    else{
							      $("#numTelefono").val("");
							    }
							    
							    if(cell !== undefined && cell != null && cell != 'null'){
							      $("#cellulare").val(cell);							 
							    }
							    else{
							      $("#cellulare").val("");
							    }
							    
							    
							    if(email !== undefined && email != null && email != 'null'){
								  $("#email").val(email);
								}
							    else{
							      $("#email").val("");
							    }
							    
							  
							    
							    // abilito le label, altrimenti si sovrappongono ai valori
							    $(".campiUt:not(.select-wrapper)").next().addClass("active");
							    
							    //re-inizializzo le select
							    console.log('re-inizializzo le select');
								$("select").formSelect();
							}
							// riabilito i campi per inserimento password
							else {							
						      console.log('utente non trovato su car_t_utente');
						      
						      //svuoto i campi
							  $(".campiUt").val("");
							  console.log('setto codFiscale ='+codiceFiscaleInput);
							  $("#codFiscale").val(codiceFiscaleInput);
						    
						      //re-inizializzo le select
							  $("select").formSelect();
						      
						      /*$("#password").val("");
						      $("#password").removeAttr("disabled");
							  $("#password").attr("required", "required");
							  
							  $("#confermaPassword").val("");
							  $("#confermaPassword").removeAttr("disabled");
							  $("#confermaPassword").attr("required", "required");*/
							  						      					  				
							}
						}
						
					});
				}
				// se svuota il campo del codice fiscale, ripulisco anche gli altri campi, nel caso prima avesse indicato un codice fiscale
				else{
				  console.log('Svuoto tutti i campi');	
				  $("#codFiscale").val(codiceFiscaleInput);
				  $("#cognome").val("");						    
				  $("#nome").val("");
				  
				  // Dati nascita
				  $("input[name='dataNascita']")[0].value = null;
				  $("#provinciaNascita").val("");
			      $("#comuneNascita").val("");
				  $('#nascitaEstera').prop('checked', false);
				  $("#denomComuneEstNascita").val("");
				  $("#nazioneEstNascita").val("");
				  
				  // Dati residenza
				  $("#provinciaResidenza").val("");
  		    	  $("#comuneResidenza").val("");
				  $("#cap").val("");
				  $('#residenzaEstera').prop('checked', false);
				  $("#denomComuneEstResid").val("");
				  $("#indirizzo").val("");
				  $("#nazioneEstResid").val("");
				  
				  
				  $("#numTelefono").val("");
				  $("#cellulare").val("");							    
				  $("#email").val("");				  
				  
				  //re-inizializzo le select
				  $("select").formSelect();
				}
			}
		
		
		
		
 	  </script> </content>

</body>
</html>