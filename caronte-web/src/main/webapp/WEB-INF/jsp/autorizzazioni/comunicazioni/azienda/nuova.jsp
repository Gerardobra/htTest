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
.modal {
	width: 80% !important;
	max-height: 95% !important;
	top: 2px !important;
	overflow: visible;
}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src='<spring:url value="/resources/js/util.js"/>'></script> 
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
	<spring:url value="/autorizzazioni/comunicazioni/azienda/nuova"
		var="formAction" />
	<spring:url value="/autorizzazioni/comunicazioni/azienda"
		var="autorizzAction" />

	<form:form action="${formAction}"  id="form" method="post"
		modelAttribute="nuovaDomandaForm" accept-charset="utf-8">
		

		<form:input type="hidden" id="idStatoComunicazione"
			path="idStatoComunicazione"
			value="${nuovaDomandaForm.idStatoComunicazione}" />
			<input type="hidden" id="chiamata" name="chiamata" value=""/>
		<div class="card">
			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding">
					<b>NUOVA DOMANDA : ${nuovaDomandaForm.descTipoDomanda}</b>
					<jsp:include page="../datiAnagraficiAzienda.jsp">
						<jsp:param name="cuaa" value="${nuovaDomandaForm.codFiscaleAz }" />
						<jsp:param name="ruop" value="${nuovaDomandaForm.codiceRuop }" />
						<jsp:param name="ragSociale"
							value="${nuovaDomandaForm.denomAzienda }" />
					</jsp:include>
				</h3>
			</div>
			<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
			<spring:eval var="statoInBozza"
				expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />
			<div class="card-content" style="padding-top: 5px;">
				<div class="row">
					<div class="col s12">
						<ul class="tabs">
							<li class="tab"><a
								href="<spring:url value="/autorizzazioni/comunicazioni/anagrafica/nuova" />"
								target="_self">Anagrafica</a></li>
							<li class="tab"><a class="active"><b>Dati operatore</b></a>
							</li>
							<spring:eval var="idTipoDomandaRUOP"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP" />
							<spring:eval var="idTipoDomandaVariazioneRUOP"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP" />
							<spring:eval var="idTipoDomandaCancellazioneRUOP"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP" />
							<spring:eval var="idTipoDomandaPassaporto"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO" />
							<form:input type="hidden" id="idTipoComunicazione"
								path="idTipoComunicazione"
								value="${nuovaDomandaForm.idTipoComunicazione}" />
							<form:input type="hidden" id="domandaRuopConvalidataPresente"
								path="domandaRuopConvalidataPresente"
								value="${nuovaDomandaForm.domandaRuopConvalidataPresente}" />
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
							<!-- -->
							<li class="tab"><a
								href="<spring:url value="/autorizzazioni/comunicazioni/allegati/nuova" />"
								target="_self">Allegati</a></li>
						</ul>
					</div>
				</div>
				<jsp:include page="../includes/boxheader.jsp"></jsp:include>
				<div class="card-panel">
					<div class="row">
						<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Dati dell'azienda</em>
						</p>
					</div>
					<div class="row">
						<div class="input-field col s12 m8 l6">
							<form:select id="idAzienda" path="idAzienda" cssClass="validate"
								required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${spedizionieriList}"
									itemValue="idSpedizioniere" itemLabel="denomSpedizioniere" />
							</form:select>
							<form:errors path="idAzienda" cssClass="error" />
							<label for="idAzienda">Aziende *</label>
						</div>
					</div>
					<!-- DATI ANAGRAFICI AZIENDA (CAR_T_SPEDIZIONIERE)-->
					<div class="row">
						<div class="row col l12">
							<div class="input-field col s6 m4 l6">
								<form:select id="idTipoAzienda" path="idTipoAzienda"
									cssClass="validate myRequired azienda aziendaIndividuale"
									required="required" onchange="visualizzaNascondiCampi();">
									<form:option value="" label="Selezionare" />
									<form:options items="${listaTipiAzienda}"
										itemValue="idTipoSpedizioniere"
										itemLabel="denomTipoSpedizioniere" />
								</form:select>
								<form:errors path="idTipoAzienda" cssClass="error" />
								<label for="idTipoAzienda">Tipo Azienda *</label>
							</div>
							<div class="input-field col s6 m4 l6">
								<form:input type="text" id="codFiscaleAz" path="codFiscaleAz"
									cssClass="azienda aziendaIndividuale campiAz" data-length="16"
									required="required" />
								<form:errors path="codFiscaleAz" cssClass="error" />
								<label for="codFiscaleAz">CUAA / CF *</label>
							</div>
						</div>
						<div class="hiddenField row col l12">
							<div id="inputTipoSpedizioniereAltro"
								class="input-field col s6 m4 l6">
								<form:input type="text" id="tipoSpedizioniereAltro"
									path="tipoSpedizioniereAltro" cssClass="campiAz"
									data-length="100" required="required" />
								<form:errors path="tipoSpedizioniereAltro" cssClass="error" />
								<label for="tipoSpedizioniereAltro">Specificare
									tipologia Altro *</label>
							</div>
							<div id="inputPartitaIva" class="input-field col s6 m4 l6">
								<form:input type="text" id="partitaIva" path="partitaIva"
									cssClass="myRequired campiAz" data-length="11"
									required="required" />
								<form:errors path="partitaIva" cssClass="error" />
								<label for="partitaIva">Partita IVA *</label>
							</div>
							<div id="inputDenomSpedizioniere"
								class="input-field col s6 m4 l6">
								<form:input type="text" id="denomAzienda" path="denomAzienda"
									cssClass="myRequired campiAz" required="required"
									maxlength="200" />
								<form:errors path="denomAzienda" cssClass="error" />
								<label for="denomAzienda">Denominazione/Ragione Sociale
									*</label>
							</div>
						</div>
						<div class="hiddenField row col l12">
							<div id="inputNomeAzienda" class="input-field col s6 m4 l6">
								<form:input type="text" id="nomeAzienda"
									cssClass="myRequired campiAz" required="required"
									onchange="aggiornaDenom();" path="nomeAzienda" />
								<form:errors path="nomeAzienda" cssClass="error" />
								<label id="labelNome" for="nomeAzienda">Nome *</label>
							</div>
							<div id="inputCognomeAzienda" class="input-field col s6 m4 l6">
								<form:input type="text" id="cognomeAzienda"
									cssClass="myRequired campiAz" required="required"
									onchange="aggiornaDenom();" path="cognomeAzienda" />
								<form:errors path="cognomeAzienda" cssClass="error" />
								<label id="labelCognome" for="cognomeAzienda">Cognome *</label>
							</div>
						</div>


						<!-- INIZIO Dati sede legale -->
						<div class="hiddenField row col l12">
							<div class="input-field col s6 m4 l6">
								<form:select id="idProvinciaSedeLegale"
									path="idProvinciaSedeLegale"
									cssClass="validate myRequired campiAz" required="required">
									<form:option value="" label="Selezionare" />
									<form:options items="${listaProvinceSedeLegale}"
										itemValue="idProvincia" itemLabel="denomProvincia" />
								</form:select>
								<form:errors path="idProvinciaSedeLegale" cssClass="error" />
								<label for="labelProvSedeLegale">Provincia Sede Legale *</label>
							</div>
							<div class="input-field col s6 m4 l6">
								<form:select id="idComuneSedeLegale" path="idComuneSedeLegale"
									cssClass="validate myRequired campiAz" required="required">
									<form:option value="" label="Selezionare" />
									<form:options items="${listaComuniSedeLegale}"
										itemValue="idComune" itemLabel="denomComune" />
								</form:select>
								<form:errors path="idComuneSedeLegale" cssClass="error" />
								<label for="labelComuneSedeLegale">Comune Sede Legale *</label>
							</div>
						</div>

						<div class="hiddenField row col l12">
							<div class="input-field col s6 m4 l6">
								<form:input type="text" id="capSedeLegale" required="required"
									path="capSedeLegale" cssClass="myRequired campiAz" />
								<form:errors path="capSedeLegale" cssClass="error" />
								<label id="labelCap" for="capSedeLegale">CAP sede legale
									*</label>
							</div>
							<div class="input-field col s6 m4 l6">
								<form:input type="text" id="indirizzoSedeLegale"
									path="indirizzoSedeLegale" cssClass="myRequired campiAz"
									required="required" maxlength="200" />
								<form:errors path="indirizzoSedeLegale" cssClass="error" />
								<label for="labelIndirizzoSedeLegale">Indirizzo sede
									legale (Es. Via Roma, 24)*</label>
							</div>
						</div>


						<div class="hiddenField row col l12">
							<div class="input-field col s6 m4 l4">
								<form:input type="text" id="numTelefonoSedeLegale"
									path="numTelefonoSedeLegale" cssClass="campiAz" maxlength="40" />
								<form:errors path="numTelefonoSedeLegale" cssClass="error" />
								<label for="labelTelefonoSedeLegale">Telefono sede
									legale (Es. 0245673467)</label>
							</div>
							<div class="input-field col s6 m4 l4">
								<form:input type="text" id="numCellulareSedeLegale"
									path="numCellulareSedeLegale" required="required"
									cssClass="campiAz" maxlength="30" />
								<form:errors path="numCellulareSedeLegale" cssClass="error" />
								<label for="labelCellulareSedeLegale">Cellulare sede
									legale (Es. 3471234567)*</label>
							</div>
							<div class="input-field col s12 m8 l4">
								<form:input type="text" id="faxSedeLegale" path="faxSedeLegale"
									cssClass="campiAz" maxlength="30" />
								<form:errors path="faxSedeLegale" cssClass="error" />
								<label for="faxSedeLegale">Fax sede legale (Es.
									0298765432)</label>
							</div>
						</div>

						<div class="hiddenField row col l12">
							<div class="input-field col s6 m4 l6">
								<form:input type="email" id="emailSedeLegale"
									cssClass="myRequired campiAz" path="emailSedeLegale"
									class="validate" />
								<form:errors path="emailSedeLegale" cssClass="error" />
								<label for="labelEmailSedeLegale">Email *</label> <span
									class="helper-text" data-error="Indirizzo email non valido"
									data-success="Indirizzo email valido"></span>
							</div>
							<div class="input-field col s6 m4 l6">
								<form:input type="email" id="pecSedeLegale"
									cssClass="myRequired campiAz" path="pecSedeLegale"
									class="validate" />
								<form:errors path="pecSedeLegale" cssClass="error" />
								<label id="labelPec">PEC *</label> <span class="helper-text"
									data-error="Indirizzo email non valido"
									data-success="Indirizzo email valido"></span>
							</div>
						</div>

						<!-- FINE Dati sede legale -->


					</div>
					<!-- FINE DATI ANAGRAFICI AZIENDA -->
				</div>


				<!-- DATI ATTIVITA AZIENDA -->
				<c:if
					test="${ (idTipoDomandaPassaporto != nuovaDomandaForm.idTipoComunicazione)}">
					<div class="card-panel">
						<div class="row">
							<p class="center-align"
								style="font-size: 20px; margin-bottom: 5px;">
								<em>L’Operatore Professionale dichiara l'intenzione di
									svolgere una o più attività indicate dal Reg. (UE) 2016/2031
									art. 66.2.b-c, in qualità di:</em>
							</p>
						</div>
						<div class="row"></div>
						<div class="row" id="bloccoOperatoriCheckbox">
							<c:forEach var="voceCheckbox"
								items="${nuovaDomandaForm.vociCheckboxTipologia}">
								<div class="row">
									<div class=" col l12">
										<label> <form:checkbox id="idVoceCheckTip"
												value="${voceCheckbox.idVoce}" path="idVoceCheckTip" /> <span>${voceCheckbox.descEstesa}</span>
										</label>
									</div>
								</div>
							</c:forEach>
							<form:errors path="idVoceCheckTip" cssClass="error" />
						</div>
					</div>
					<div class="card-panel">
						<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Tipologia di Attività </em>
						</p>
						<div class="row">
							<a href='javascript:aggiungiAttivitaMateriale()'
								class="btn green waves-effect waves-light "> <i
								class="material-icons right">add</i> Aggiungi attività
							</a>
						</div>
						<div class="row col l6">
							<form:errors path="idTipologiaAttTip" cssClass="error" />
						</div>
						<div class="row">
							<table id="tabellaAttivita" class="data-table striped display"
								data-order='[[ 2, "asc" ]]' data-paging="false"
								data-info="false">
								<thead>
									<tr>
										<th>Tipologia attività</th>
										<th>Materiale</th>
										<th class="action">Azione</th>
									</tr>
								</thead>
								<tbody id="bodyTabellaAttivita">
									<c:forEach var="attivita" items="${tipologieAttDb}">
										<c:choose>
											<c:when test="${attivita != null}">
												<tr>
													<td>${attivita.denomTipologiaEstesa}</td>
													<td>${attivita.descEstesa}</td>
													<td style="white-spaces: nowrap" class="action"><a
														href='javascript:modificaAttivitaMateriale(${attivita.idAttivitaMaterialeUtente})'
														class="btn btn-floating btn-floating-medium tooltipped light-green accent-3"
														data-tooltip="Modificare attività"> <i
															class="material-icons">mode_edit</i>
													</a> <sec:authorize access="hasRole('WRITE') ">
															<a title="Elimina"
																onclick="javascript:eliminaTipologiaAttivita(this, ${attivita.idAttivitaMaterialeUtente})"
																class="btn btn-floating btn-floating-medium deep-orange accent-2">
																<i class="material-icons">delete</i>
															</a>
														</sec:authorize></td>
												</tr>
											</c:when>
											<c:otherwise>
												<tr>
													<td nowrap style="white-spaces: nowrap" class="action"><sec:authorize
															access="hasRole('WRITE') ">
															<a title="Elimina"
																onclick="javascript:eliminaTipologiaAttivita(this, ${attivita.idAttivitaMaterialeUtente})"
																class="btn btn-floating btn-floating-medium deep-orange accent-2">
																<i class="material-icons">delete</i>
															</a>
														</sec:authorize></td>
													<td>${attivita.denomTipologiaEstesa}</td>
													<td></td>
												</tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</tbody>
								<tr id="rigaTemplateAttMateriali" style="display: none">
									<td>
									<td>
									<td nowrap style="white-spaces: nowrap" class="action"><a
										href='javascript:modificaAttivitaMateriale(PLACEHOLDER_idAttivitaMaterialeUtente)'
										class="btn btn-floating btn-floating-medium tooltipped light-green accent-3"
										data-tooltip="Modificare attività"> <i
											class="material-icons">mode_edit</i>
									</a> <sec:authorize access="hasRole('WRITE') ">
											<a title="Elimina"
												onclick="javascript:eliminaTipologiaAttivita(this, PLACEHOLDER_idAttivitaMaterialeUtente)"
												class="btn btn-floating btn-floating-medium deep-orange accent-2">
												<i class="material-icons">delete</i>
											</a>
										</sec:authorize></td>
								</tr>
							</table>
						</div>
						<div class="row">
							<p style="font-size: 15px; margin-bottom: 5px;">
								<em>Aggiungere la spunta se le attività inserite richiedono
									l'utilizzo del passaporto (obbligatorio per l'attività
									Vivaismo) </em>
							</p>
							<div id="divRichiestaPass" class=" col l6">
								<label> <form:checkbox id="richiestaPassaporto"
										path="richiestaPassaporto" /> <span>Richiesta
										contestuale di autorizzazione al rilascio del Passaporto delle
										piante</span> <form:errors path="richiestaPassaporto"
										cssClass="error" />
								</label>
							</div>
						</div>
					</div>
					<!-- FINE DATI ATTIVITA AZIENDA -->
				</c:if>
				
				<!-- INSERIMENTO DATI CENTRO AZIENDALE (CAR_T_CENTRO_AZIENDALE)-->
				<c:if test="${nuovaDomandaForm.denomAzienda != null}">
				<div class="card-panel">	
				  <div class="row">				
				 	<ul class="collapsible" data-collapsible="accordion">
						<li>
							<div class="collapsible-header" id="datiIspezione">
								<i class="material-icons">arrow_drop_down</i>Aggiungi centro aziendale
							</div>							
						   <div class="collapsible-body">
						      
						       <div class="row col l12" >				  				  				  
								  <div class="input-field col s12 m8 l12">
										<form:select id="idCentroAziendale" path="idCentroAziendale" name ="idCentroAziendale"
											cssClass="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${centriAziendaliList}" itemValue="idCentroAziendale"
												itemLabel="nomeElenco" />
										</form:select>
										<form:errors path="idCentroAziendale" cssClass="error" />
										<label for="idCentroAziendale">Centri aziendali *</label>
								  </div>											    			
							    </div>	
							    <div id="campiCentriAz">			
								<div class="row col l12">
										<div class="input-field col s12 m8 l6">
											<form:input type="text" id="denominazCentroAz" name="denominazCentroAz"
												path="denominazCentroAz" cssClass=" campiCentroAz"
												 maxlength="100" />
											<form:errors path="denominazCentroAz" cssClass="error" />
											<label for="denominazCentroAz">Denominazione</label>
										</div>	
									<div class="input-field col s6 m4 l6">
											<form:input type="text" id="codCentroAz" name="codCentroAz"
												path="codCentroAz" cssClass="campiCentroAz" readonly="true" style="visibility:hidden;"/>		
									</div>								
								</div>				
													
								<div class="row col l12" >
										<div class="input-field col s6 m4 l6">
											<form:select id="idProvCentroAz" path="idProvCentroAz"
												cssClass="validate campiCentroAz" >
												<form:option value="" label="Selezionare" />
												<form:options items="${listaProvinceCentroAz}" itemValue="idProvincia"
													itemLabel="denomProvincia" />
											</form:select>
											<form:errors path="idProvCentroAz" cssClass="error" />
											<label for="labelidProvCentroAz">Provincia *</label>
										</div>
										<div class="input-field col s6 m4 l6">
											<form:select id="idComuneCentroAz" path="idComuneCentroAz"
												cssClass="validate campiCentroAz" >
												<form:option value="" label="Selezionare" />
												<form:options items="${listaComuniCentroAz}" itemValue="idComune"
													itemLabel="denomComune" />
											</form:select>
											<form:errors path="idComuneCentroAz" cssClass="error" />
											<label for="labelidComuneCentroAz">Comune *</label>
										</div>
									</div>
									
									<div class="row col l12">
										<div class="input-field col s6 m4 l6">
										  <form:input type="text" id="capCentroAz" name="capCentroAz" maxlength="5" path="capCentroAz" cssClass="campiCentroAz" />
										  <form:errors path="capCentroAz" cssClass="error" />
										  <label id="labelcapCentroAz" for="capCentroAz">CAP *</label>
										</div>
										<div class="input-field col s6 m4 l6">
											<form:input type="text" id="indirizzoCentroAz" name="indirizzoCentroAz"
												path="indirizzoCentroAz" cssClass="campiCentroAz" maxlength="200" />
											<form:errors path="indirizzoCentroAz" cssClass="error" />
											<label for="indirizzoCentroAz">Indirizzo (Es. Via Roma, 24)*</label>
										</div>
										<div class="input-field col s6 m4 l6">
											<form:input type="text" id="frazioneCentroAz"
												path="frazioneCentroAz" cssClass="campiCentroAz" maxlength="100" />
											<form:errors path="frazioneCentroAz" cssClass="error" />
											<label for="frazioneCentroAz">Frazione</label>
										</div>
									</div>
									
									
									<div class="hiddenField row col l12">
										<div class="input-field col s6 m4 l6">
											<form:input type="text" id="telefonoCentroAz"
												path="telefonoCentroAz" cssClass="campiCentroAz"  maxlength="20" />
											<form:errors path="telefonoCentroAz" cssClass="error" />
											<label for="telefonoCentroAz">Telefono (Es. 0245673467)</label>
										</div>
										<div class="input-field col s6 m4 l6">
											<form:input type="text" id="cellulareCentroAz"
												path="cellulareCentroAz" cssClass="campiCentroAz"  maxlength="30" />
											<form:errors path="cellulareCentroAz" cssClass="error" />
											<label for="cellulareCentroAz">Cellulare (Es. 3471234567)</label>
										</div>						
									</div>
											
									<div class="hiddenField row col l12">																		
										<div class="input-field col s6 m4 l6">
														<form:input type="email" id="mailCentroAz"
															cssClass="campiCentroAz" path="mailCentroAz"
															class="validate"  />
														<form:errors path="mailCentroAz" cssClass="error" />
														<label for="labelmailCentroAz">Email </label>
														<span class="helper-text" data-error="Indirizzo email non valido"
				                      											  data-success="Indirizzo email valido"></span>
													</div>
										<div class="input-field col s6 m4 l6">
														<form:input type="email" id="pecCentroAz"
															cssClass="campiCentroAz" path="pecCentroAz" class="validate"
															 />
														<form:errors path="pecCentroAz" cssClass="error" />
														<label id="labelPec">PEC</label>
														<span class="helper-text" data-error="Indirizzo email non valido"
				                      											  data-success="Indirizzo email valido"></span>
										</div>
								  </div>
								  </div>
								  <!-- INIZIO AGGIUNGI CENTRO AZIENDALE -->					    	            	
				            		
		              				<button class="btn green waves-effect waves-light"  style="button"
				            	        type="submit" 
				            	        name="aggiungiCentroAziendale" id="aggiungiCentroAziendale">
				              			SALVA
				              		</button>
				              		
							</div> <!-- FINE collapsible-body -->	
								  
						</li>
											<div id="datiCentroAziendale" class="row">        				  
			<div class="card panel">	
				<!-- ELENCO CENTRI AZIENDALI -->		      
					
					
					
			</div>
					</ul>	
					</div>
					
					<!-- INSERISCI QUI TABELLA  -->
					<div class="row">
					       	<table id="tabellaCentroAziendale" class="data-table striped display" 
					       		data-order='[[ 1, "asc" ], [ 2, "asc" ], [ 3, "asc" ]]' data-paging="false" data-info="false">
								<thead>
									<tr>
										<th data-orderable="false"></th>									
										<th>Denominazione centro aziendale</th>																		
										<th>Indirizzo centro aziendale</th>	
										<th>Comune centro aziendale</th>																															
									</tr>
								</thead>
								<tbody id="bodyTabellaCentriAziendali">
								  <c:forEach var="centroAz" items="${centriAziendaliDb}">
									   <tr>
										<td nowrap style="white-spaces: nowrap">
											<sec:authorize access="hasRole('WRITE') ">												
												<a href="${autorizzAction}/modifica/eliminaCentroAz/${centroAz.idCentroAziendale}"  
												  title="Elimina" 
												  class="btn btn-floating btn-floating-medium deep-orange accent-2"
											      onclick="return eliminaCentroAz(this)">
													<i class="material-icons">delete</i>
											    </a>											    																					
											</sec:authorize>
										</td>																		
										<td>${centroAz.denominazione}</td>
										<td>${centroAz.indirizzo}</td>
										<td>${centroAz.denomComune} (${centroAz.siglaProvincia})</td>									
								      </tr>
							      </c:forEach>								
								</tbody>																	
							</table>											
					</div>
				 </div>
				 </c:if>
				 <!-- FINE AGGIUNGI CENTRO AZIENDALE -->
				
			</div>
		</div>

		<div class="row">
			<a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>'
				class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
			<button class="btn confirm waves-effect waves-light right"
				type="submit" name="datiAzienda">SALVA</button>
		</div>
		<spring:eval var="statoInBozza"	expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />
		<c:if test="${nuovaDomandaForm.idTipoComunicazione == 3}">
			<script>
        		disableFields();
        	</script>
		</c:if>
	</form:form>

	<!-- INIZIO MODALE -->
	<div id="editAttivitaMaterialiModal" class="modal scrollbar-thin">
		<div class="modal-content" style="padding-top: 0; padding-bottom: 0;"></div>
	</div>

	<br />

	<content tag="script"> <script type="text/javascript"
		src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script>
	<script>
		
		
		/*function visualizzaCampi(){
			console.log('visualizzaCampi');
			console.log('----- Visualizzare la parte da compilare per la Domanda Ruop');
			$("#datiAttivAzienda").show();
			$("#datiTipAttivAzienda").show();			
		}
				
		function controllaDomandaRuop(){
			console.log('controllaDomandaRuop');
			var azienda = document.getElementById("idAzienda");
			var idAziendaSel = azienda.options[azienda.selectedIndex].value;			
			console.log('idAziendaSel ='+idAziendaSel);
			if(idAzienda != null && idAzienda!=''){
				console.log('chiamata ajax isDomandaRuopConvPresente_');
				$.ajax({
					url : '<spring:url value="/autorizzazioni/comunicazioni/isDomandaRuopConvPresente_"/>' + idAziendaSel,
					async : false,
					success : function(response) {
						console.log('response isDomandaRuopConvPresente_ ='+response);
						if(response == 'N'){
							console.log('----- Visualizzare la parte da compilare per la Domanda Ruop');						
							$("#datiAttivAzienda").show();			
							$("#datiTipAttivAzienda").show();
						}
						else{
							console.log('----- Non visualizzare la parte da compilare per la Domanda Ruop');
							$("#datiAttivAzienda").hide();
							$("#datiTipAttivAzienda").hide();
						}
					}				
				});	
			}
		}*/
			
		// Popolamento dinamico della select Comune sede legale
		$('#idProvinciaSedeLegale').change(function() {
			var params = {
				"idProvincia" : $(this).val()			
			}

			getSelectAsincrona(
				'idComuneSedeLegale',
				'<spring:url value="/ajax/getComuniProvincia" />',
				params,
				getOptionComune, 1);
		});
			
		function getOptionComune(comune) {
			return getOption(comune.idComune, comune.denomComune);
		}
		
		
		
		/* Evento alla selezione della combo Aziende :
			- se viene selezionato un idAzienda != 0 : cercare i dati dello spedizioniere selezionato e precaricare i dati nella pagina (disabilitare i campi partita iva e codice fiscale)
			- se viene selezionato l'idAzienda = 0 ('Nuova azienda') : sbiancare tutti i campi e abilitare i campi partita iva e codice fiscale (l'utente vuole inserire un nuovo spedizioniere)
		*/
		$('#idAzienda').change(function() {
			var idAzienda  = $(this).val();
			console.log('idAzienda selezionato ='+idAzienda);
			if(idAzienda != null && idAzienda != ""){
				// -- CASO SELEZIONE DI UNO SPEDIZIONIERE DALLA COMBO
				if(idAzienda != '0'){
				  console.log('-- CASO SELEZIONE DI UNO SPEDIZIONIERE DALLA COMBO');
				  // sbianco i campi nel caso avessero dati
				  $(".campiAz").val("");				  
				  console.log('---- Cerco e carico i dati dell azienda');
				  $.ajax({
					    // Ricerca dell'azienda su car_t_utente.id_spedizioniere e su car_r_utente_spedizionieri				  
						url : '<spring:url value="/autorizzazioni/comunicazioni/getSpedizioniereById_"/>'+ idAzienda,
						success : function(response) {
							if (response.indexOf("NOTEXISTS") == -1) {
							  console.log('Azienda trovata');
							  
							  //abilito le select per poterle modificare
							  $(".select-dropdown").removeAttr("disabled");
							  $(".select-dropdown").removeAttr("readonly");
							  $("select").removeAttr("disabled");
							  $("select").removeAttr("readonly");							  							  
							  
							  // --- Recupero i valori						  
							  var splittedStr = response.split("&&&");
							  
							  var idSpediz = splittedStr[0];
							  console.log('idSpediz ='+idSpediz);
							  
							  var idTipoSpediz = splittedStr[1];
							  console.log('idTipoSpediz ='+idTipoSpediz);
							  
							  var cuaa = splittedStr[2];
							  console.log('cuaa ='+cuaa);
							  
							  var cognome = splittedStr[3];
							  console.log('cognome ='+cognome);
							  
							  var nome = splittedStr[4];
							  console.log('nome ='+nome);
							  
							  var denomSpedizioniere = splittedStr[5];
							  console.log('denomSpedizioniere ='+denomSpedizioniere);
							  if (idTipoSpediz == 5 && (denomSpedizioniere == null || denomSpedizioniere == 'null' || denomSpedizioniere==undefined)
									  && cognome != null && nome != null) {
								  denomSpedizioniere = cognome + ' '+ nome;
								  console.log('nome cognome in denomSpedizioniere ='+denomSpedizioniere);
							  }
							  
							  var idProvSedeLeg = splittedStr[6];
							  console.log('idProvSedeLeg ='+idProvSedeLeg);
							  
							  var idComSedeLeg = splittedStr[7];
							  console.log('idComSedeLeg ='+idComSedeLeg);
							  
							  var capSedeLeg = splittedStr[8];
							  console.log('capSedeLeg ='+capSedeLeg);
							  
							  var indirSedeLeg = splittedStr[9];
							  console.log('indirSedeLeg ='+indirSedeLeg);
							  
							  var numTel = splittedStr[10];
							  console.log('numTel ='+numTel);
							  
							  var numCell = splittedStr[11];
							  console.log('numCell ='+numCell);
							  
							  var numFax = splittedStr[12];
							  console.log('numFax ='+numFax);
							  
							  var eMail = splittedStr[13];
							  console.log('eMail ='+eMail);
							  
							  var pec = splittedStr[14];
							  console.log('pec ='+pec);
							  
							  var comuniSedeLeg = splittedStr[15];
							  
							  var partitaIva = splittedStr[16];
							  console.log('partitaIva ='+partitaIva);
							  
							  var tipoSpedizioniereAltro = splittedStr[17];
							  console.log('tipoSpedizioniereAltro ='+tipoSpedizioniereAltro);
							  
							  // ------ Valorizzo i campi
							  console.log('Seleziono idTipoSpedizioniere ='+idTipoSpediz);
							  $("#idTipoAzienda").val(idTipoSpediz);
							  
							  
							  $("#idProvinciaSedeLegale").formSelect();
							  $("#idComuneSedeLegale").formSelect();
							  if(idProvSedeLeg !== undefined && idProvSedeLeg != null && idProvSedeLeg != 'null'){	
							    	$("#idComuneSedeLegale").html(comuniSedeLeg);
									$('#idComuneSedeLegale').formSelect();	
									
									console.log('Seleziono prov ='+idProvSedeLeg);
							    	$("#idProvinciaSedeLegale").val(idProvSedeLeg);
							    	
							    	if(idComSedeLeg !== undefined && idComSedeLeg != null && idComSedeLeg != 'null'){
							    	  console.log('Seleziono comune  ='+idComSedeLeg);
							    	  $("#idComuneSedeLegale").val(idComSedeLeg);
								    }
								    else{
								      $("#idComuneSedeLegale").val("");
								    }
							  }
							  else{
							    	console.log('Provincia e comune di nasccita non presenti sul db');
							    	$("#idProvinciaSedeLegale").val("");
							    	$("#idComuneSedeLegale").val("");
							  }
							  
							  // richiamo l'evento che viene richiamato alla selezione del tipo di azienda
							  console.log('Richiamo evento per visualizare i campi, in baes al tipo di azienda');
							  visualizzaNascondiCampi();							  							  
							  
							  // CUAA			
							  console.log('visualizzo il codice fiscale e disabilito il campo');
							  if (cuaa != "") {
								$("#codFiscaleAz").val(cuaa);
								$("label[for='codFiscaleAz']").addClass("active");
							  }
							  $("#codFiscaleAz").attr("readonly", "readonly");
							  $("#codFiscaleAz").removeAttr("required");	
							  							 							  
							  
							  if(denomSpedizioniere!== undefined && denomSpedizioniere != null && denomSpedizioniere != 'null'){
							    $("#denomAzienda").val(denomSpedizioniere);							  
							  }
							  if(cognome!== undefined && cognome != null && cognome != 'null'){								  
								  $("#cognomeAzienda").val(cognome);
							  }							  
							  if(nome!== undefined && nome != null && nome != 'null'){
								  $("#nomeAzienda").val(nome);
							  }
							    
							   if(capSedeLeg!== undefined && capSedeLeg != null && capSedeLeg != 'null'){ 	
								   $("#capSedeLegale").val(capSedeLeg);
							   }
							   else{
							     $("#capSedeLegale").val(""); 
							   }
							   
							   if(indirSedeLeg!== undefined && indirSedeLeg != null && indirSedeLeg != 'null'){
							     $("#indirizzoSedeLegale").val(indirSedeLeg);
							   }
							   else{
							     $("#indirizzoSedeLegale").val("");   
							   }
							   
							   if(numTel!== undefined && numTel != null && numTel != 'null'){
							     $("#numTelefonoSedeLegale").val(numTel);   
							   }
							   else{
							     $("#numTelefonoSedeLegale").val("");  
							   }
							   
							   if(numCell!== undefined && numCell != null && numCell != 'null'){
							     $("#numCellulareSedeLegale").val(numCell);   
							   }
							   else{
							     $("#numCellulareSedeLegale").val("");  
							   }
							   
							   if(numFax!== undefined && numFax != null && numFax != 'null'){
						         $("#faxSedeLegale").val(numFax);   
							   }
							   else{
							     $("#faxSedeLegale").val("");  
							   }
							   
							   if(eMail!== undefined && eMail != null && eMail != 'null'){
							     $("#emailSedeLegale").val(eMail);   
							   }
							   else{
							     $("#emailSedeLegale").val("");  
							   }
							   
							   if(pec!== undefined && pec != null && pec != 'null'){
							     $("#pecSedeLegale").val(pec);   
							   }
							   else{
							     $("#pecSedeLegale").val("");  
							   }
							   
							   // partita iva
							   if(partitaIva !== undefined && partitaIva != null && partitaIva != 'null'){
								 $("#partitaIva").val(partitaIva);
								 $("label[for='partitaIva']").addClass("active");
							   }
							   else{
								 $("#partitaIva").val("");  
							   }
							   // disabilito il campo partita iva
							   $("#partitaIva").attr("readonly","readonly");							   
							   
							   if(tipoSpedizioniereAltro !== undefined && tipoSpedizioniereAltro != null && tipoSpedizioniereAltro != 'null'){
							   	 $("#tipoSpedizioniereAltro").val(tipoSpedizioniereAltro);   
							   }
							   else{
								 $("#tipoSpedizioniereAltro").val("");  
							   }


							   // abilito le label, altrimenti si sovrappongono ai valori
							   $(".campiAz:not(.select-wrapper)").next().addClass("active");	
							}
							else {							
						      console.log('spedizioniere non trovato su car_t_spedizioniere');
						      console.log('Sbianco i campi');
							  $(".campiAz").val("");	
							}  
						}	
				  });				 					  				 
				}
				// -- CASO SELEZIONE DI 'NUOVA AZIENDA' DALLA COMBO
				else{
				  console.log('-- CASO SELEZIONE DI NUOVA AZIENDA DALLA COMBO');
				  $(".campiAz").val("");
				  $("#idTipoAzienda").formSelect();
				  $("#idTipoAzienda").val("");
				  inizializePage();	
				  
				  // riabilito il campo CUAA				  
				  console.log('riabilito il campo CUAA');
				  $("#codFiscaleAz").attr("required","required");
				  $("#codFiscaleAz").removeAttr("readonly");
				  $("label[for='codFiscaleAz']").addClass("active");
				  
				  // riabilito il campo partita iva
				  console.log('riabilito il campo partita iva');
				  $("#partitaIva").attr("required","required");
				  $("#partitaIva").removeAttr("readonly");
				  $("label[for='partitaIva']").addClass("active");
				}
			}
		});
			
		function inizializePage(){
			console.log('inizializePage()');
			
			//ricarica correttamente quando ritorna in pagina 
			$("select").formSelect();

			visualizzaNascondiCampi();

			//se la pagina è pulita
			if ($("#codFiscaleAz").val() == null || $("#codFiscaleAz").val() == "") {
				//nascondo i campi a parte la tipologia ed il cuaa
				console.log('nascondo i campi a parte la tipologia ed il cuaa');
				$(".hiddenField").hide();
			}
			
			var idTipoDomanda = $('#idTipoComunicazione').val();
			console.log('idTipoDomanda ='+idTipoDomanda);
			
			/* Controllo se devo visualizzare i campi per la Domanda Ruop :
				 se sto ricaricando la pagina in caso di validazione campi non andata a buon fine,
				 il tipo domanda è diverso da 6 (Domanda Passaporto),
				 oppure il tipo domanda è = 6 (Domanda Passaporto) e non c'è una Domanda ruop precedente sul db :
				 bisogna visualizzare la parte per la compilazione Domanda Ruop
			*/			
			/*var domandaRuopConvalidataPresente = $('#domandaRuopConvalidataPresente').val();
			console.log('domandaRuopConvalidataPresente ='+domandaRuopConvalidataPresente);
			if(idTipoDomanda !=  6 || (idTipoDomanda == 6 && domandaRuopConvalidataPresente == true)){
				console.log('----- Visualizzare la parte da compilare per la Domanda Ruop');
				$("#datiAttivAzienda").show();
				$("#datiTipAttivAzienda").show();
			}
			else{
				$("#datiAttivAzienda").hide();
				$("#datiTipAttivAzienda").hide();
			}*/
			
			
			/*
			   Controllo se devo disabilitare la combo 'Aziende', partita iva e cuaa :
				   - se si sta creando una Domanda di tipo Variazione RUOP, i campi devono essere disabilitati
			*/					
			if(idTipoDomanda ==  3){
				console.log('disabilito la combo Aziende');
				$("#idAzienda").attr("disabled", "disabled").attr("readonly","readonly");	
			    $("#idAzienda").formSelect();
			    
			    console.log('disabilito partita iva e cuaa');
			    $("#partitaIva").attr("disabled", "disabled").attr("readonly","readonly");
			    $("#codFiscaleAz").attr("disabled", "disabled").attr("readonly","readonly");
			    
			    console.log('disabilito combo Tipo azienda');
			    $("#idTipoAzienda").attr("disabled", "disabled").attr("readonly","readonly");	
			    $("#idTipoAzienda").formSelect();
			}
			else{
				console.log('riabilito combo idAzienda');
				$("#idAzienda").removeAttr("disabled").removeAttr("readonly");
				$("#idAzienda").formSelect();
				
				console.log('riabilito partita iva e cuaa');
				$("#partitaIva").removeAttr("disabled").removeAttr("readonly");
				$("#codFiscaleAz").removeAttr("disabled").removeAttr("readonly");
				
				console.log('riabilito combo Tipo azienda');			
				$("#idTipoAzienda").removeAttr("disabled").removeAttr("readonly");
				$("#idTipoAzienda").formSelect();
			}
			
		}
			
		$(document).ready(function() {
			inizializePage();
			saveCheckboxStateOnChange("richiestaPassaporto");
			setCheckboxState("richiestaPassaporto");
			var idTipo = $("#idTipoComunicazione").val();
			 if(idTipo == 3){
				 disableAndHide();
			 }
			 
		});

		function visualizzaCampiAzienda() {
			console.log('visualizzaCampiAzienda');
			//cambio label
			$("#labelProvSedeLegale").text("Provincia sede legale *");
			$("#labelComuneSedeLegale").text("Comune sede legale *");
			$("#labelIndirizzoSedeLegale").text("Indirizzo sede legale *");
			$("#labelCap").text("CAP sede legale *");
			$("#labelPec").text("PEC *");
			$("#labelEmailSedeLegale").text("Email *");			
			$("#labelTelefonoSedeLegale").text("Telefono");
			$("#labelCellulareSedeLegale").text("Cellulare");
			$("#faxSedeLegale").text("Fax");
			

			$("#idProvinciaSedeLegale").removeAttr("disabled").removeAttr("readonly");
			$("#idComuneSedeLegale").removeAttr("disabled").removeAttr("readonly");
			$("#idProvinciaSedeLegale").formSelect();
			$("#idComuneSedeLegale").formSelect();
			
			//mostro/nascondo campi
			$("#inputPartitaIva").show();
			$("#inputDenomSpedizioniere").show();
			$("#inputNomeAzienda").hide();
			$("#inputCognomeAzienda").hide();
			$("#inputNomeAzienda").removeAttr("required");
			$("#inputCognomeAzienda").removeAttr("required");
			$("#inputTipoSpedizioniereAltro").hide();			
			$("#tipoSpedizioniereAltro").val("");  

			//sistemo i required
			$(".myRequired").attr("required", "required");
			$("#cognomeAzienda").removeAttr("required");
			$("#nomeAzienda").removeAttr("required");
			$("#denomAzienda").attr("required", "required");
			$("#partitaIva").attr("required", "required");
			$("#tipoSpedizioniereAltro").removeAttr("required");
			
			// abilito le label, altrimenti si sovrappongono ai valori
		    $(".campiUt:not(.select-wrapper)").next().addClass("active");
		}

		function visualizzaCampiAziendaIndividuale() {
			console.log('visualizzaCampiAziendaIndividuale');
			var idTipoAzienda = $("#idTipoAzienda").val();
			console.log('idTipoAzienda ='+idTipoAzienda);

			//cambio label campi comuni
			if (idTipoAzienda == 5) {
				$("#labelProvSedeLegale").text("Provincia Azienda *");
				$("#labelComuneSedeLegale").text("Comune Azienda *");
				$("#labelIndirizzoSedeLegale").text("Indirizzo Azienda *");
				$("#labelCap").text("CAP Azienda *");
				$("#labelPec").text("PEC *");	
				$("#labelEmailSedeLegale").text("Email *");
				$("#labelTelefonoSedeLegale").text("Telefono");				
				
				$("#pecSedeLegale").attr("required","required");
				$("#emailSedeLegale").attr("required","required");
				
				$("#inputTipoSpedizioniereAltro").hide();
				$("#tipoSpedizioniereAltro").removeAttr("required");
				$("#tipoSpedizioniereAltro").val("");  
				$("#inputDenomSpedizioniere").show();
				$("#inputDenomSpedizioniere").removeAttr("required");	
							
			} 
			else {
				$("#labelProvSedeLegale").text("Provincia residenza *");
				$("#labelComuneSedeLegale").text("Comune residenza *");
				$("#labelIndirizzoSedeLegale").text("Indirizzo residenza *");
				$("#labelCap").text("CAP *");
				$("#labelPec").text("PEC (facoltativa)");
				$("#labelTelefonoSedeLegale").text("Telefono");
				// solo per la tipologia Altro idTipoAzienda = 1
				$("#tipoSpedizioniereAltro").text("Specificare tipologia Altro");
				$("#inputTipoSpedizioniereAltro").show();
				$("#tipoSpedizioniereAltro").attr("required","required");
								
				$("#pecSedeLegale").removeAttr("required");
				$("#emailSedeLegale").removeAttr("required");
			}

			//mostro/nasocndo campi
			//$("#inputDenomSpedizioniere").hide();
			//$("#inputDenomSpedizioniere").removeAttr("required");
			
			$("#inputNomeAzienda").show();
			$("#nomeAzienda").attr("required","required");
			
			$("#inputCognomeAzienda").show();
			$("#cognomeAzienda").attr("required","required");

			//abilito gli altri campi
			$("#pecSedeLegale").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
			$("#capSedeLegale").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
			$("#indirizzoSedeLegale").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");			

			//tolgo i required ai campi disabilitati
			$("#denomAzienda").removeAttr("required");	
			

			enableSelect();
			
			// abilito le label, altrimenti si sovrappongono ai valori
		    $(".campiUt:not(.select-wrapper)").next().addClass("active");
		}

		function enableSelect() {
			console.log('enableSelect');
			$(".select-dropdown").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");

			$("#idProvinciaSedeLegale").removeAttr("disabled").removeAttr("readonly");
			$("#idComuneSedeLegale").removeAttr("disabled").removeAttr("readonly");			
			$("#idTipoAzienda").removeAttr("disabled").removeAttr("readonly");

			$("#idProvinciaSedeLegale").formSelect();
			$("#idComuneSedeLegale").formSelect();			
			$("#idTipoAzienda").formSelect();
		}

		//controlla l'idTipoAzienda e mostra/nasconde i campi corretti
		function visualizzaNascondiCampi() {
			console.log('visualizzaNascondiCampi');
			var idTipoAzienda = $("#idTipoAzienda").val();
			console.log('idTipoAzienda = '+idTipoAzienda);

			$(".hiddenField").show();
			$("select").formSelect();

			if (idTipoAzienda !== undefined && idTipoAzienda != null) {
				if (idTipoAzienda == 4 || idTipoAzienda == 2 || idTipoAzienda == 3) {
					visualizzaCampiAzienda();
				} 
				//Altro (ex-Utente Privato)  e  Azienda individuale (ex-Ditta individuale)
				else if (idTipoAzienda == 1 || idTipoAzienda == 5) {
					visualizzaCampiAziendaIndividuale();
				}
			} 
			else {
				visualizzaCampiAzienda();
			}
		}		
		
		
		// --- *** Gestione attivita azienda *** ---
		function reloadMultiselect($selectRef) {
			console.log('reloadMultiselect');				
			//if (idSpecieSelezionati) {
				//var arrayIdSpecie = idSpecieSelezionati.split(',');
				
				$selectRef.find('option').each(function() {
					$option = $(this);
				
					//var indiceInArray = $.inArray($option.val(), arrayIdSpecie);
					var indiceInArray = $.inArray($option.val(), null);

					/*  
					 *  Se viene trovato l'id specie nell'array, la option viene selezionata 
					 *  e l'id specie viene rimosso dall'array per velocizzare le ricerche successive 
					 */
					if (indiceInArray >= 0) {
						$option.prop('selected', 'selected');
						arrayIdSpecie.splice(indiceInArray, 1);
					}
				});
				
			//}
			
			$selectRef.formSelect();
			inizializzaMultiselect($selectRef);
		}
		
		/*--------------- Gestione Attivita e materiali ------------------*/		
		
		// Elimina elemento della tabella delle Tipologie attivita       		
		function eliminaTipologiaAttivita(link, idAttivitaMaterialeUtente) {
			console.log('BEGIN eliminaTipologiaAttivita');			
			
			var tipologAtt;			
			var materiale;
			
			$riga = $(link).closest('tr');
			tipologAtt = $riga.find('td:nth-of-type(1)').text();
			materiale = $riga.find('td:nth-of-type(2)').text();	
			
			var messaggio = '';
			if(materiale != null && materiale != ''){
			  	messaggio = 'Si desidera eliminare la tipologia : <b>' + tipologAtt 
		    	+ '</b>, con materiale : <b>' + materiale +'</b>?';
			}
			else{
				messaggio ='Si desidera eliminare la tipologia : <b>' + tipologAtt + '</b>?';
			}
			
			swal({
				title : 'Attenzione!',
			    html : messaggio,
			    type : 'warning',
	   			showCancelButton : true,
				confirmButtonText : 'Sì',
				cancelButtonText : 'No',
			}).then(function (result) {
	    		  if (result.value) {
	    			  console.log('elimino idAttivitaMaterialeUtente ='+idAttivitaMaterialeUtente);
	    			  $.post(
	  						'<spring:url value="/autorizzazioni/comunicazioni/azienda/eliminaTipologiaAtt/idAttivitaMaterialeUtente/"/>/' + idAttivitaMaterialeUtente
	  					).done(function(response) {	  						
	  						$modal = $('#editAttivitaMaterialiModal');	  					    
	  					    if (response) {
	  					    	console.log('-- if response');
	  					    	$modal.find('div.modal-content').html(response);
	  					    	
	  					    	//$modal.find('select').formSelect();
	  							M.updateTextFields();
	  					    }
	  					    else {
	  					    	console.log('-- else response');
	  					    	console.log('aggiornaTabellaAttivitaMateriali');	  					    
	  					    	aggiornaTabellaAttivitaMateriali()
	  					    	$modal.modal('close');
	  					    	Swal.fire({
	  								  title : 'Elemento eliminato',
	  								  type : 'success',
	  								  confirmButtonText : 'OK',
	  							});
	  					    }
	  					}).fail(function(jqXHR, textStatus) {
	  						console.log('FAIL TEXT STATUS: '+textStatus);
	  					    Swal.fire({
	  							  title : 'Errore',
	  							  html : 'Errore nell\'eliminazione dell\'elemento',
	  							  type : 'error',
	  							  confirmButtonText : 'OK',
	  						});
	  					}); 
	    		  }
	    	});
		}
		
		// ****************  aggiungi attivita e materiali con modale ***********************
		
		function aggiungiAttivitaMateriale() {
			console.log('aggiungiAttivitaMateriale');
			
			$.ajax({
				url : '<spring:url value="/autorizzazioni/comunicazioni/azienda/editAttivitaMateriali"/>'
			}).done(function(response) {
				apriModaleMateriali(response, null);
			}).fail(function(jqXHR, textStatus) {
			    console.log('TEXT STATUS: '+textStatus);
			    Swal.fire({
					  title : 'Errore',
					  html : 'Utente non abilitato ad aggiungere attivita e materiali',
					  type : 'error',
					  confirmButtonText : 'OK',
				});
			});
		}
		
		function modificaAttivitaMateriale(idAttivitaMaterialeUtente) {
			console.log('modificaAttivitaMateriale');
			
			$.ajax({
				url : '<spring:url value="/autorizzazioni/comunicazioni/azienda/editAttivitaMateriali"/>/' +idAttivitaMaterialeUtente
			}).done(function(response) {
				apriModaleMateriali(response, idAttivitaMaterialeUtente);
			}).fail(function(jqXHR, textStatus) {
			    console.log('TEXT STATUS: '+textStatus);
			    Swal.fire({
					  title : 'Errore',
					  html : 'Utente non abilitato ad aggiungere attivita e materiali',
					  type : 'error',
					  confirmButtonText : 'OK',
				});
			});
		}
		
		function apriModaleMateriali(content, idAttivitaMaterialeUtente) {
			console.log('apriModaleMateriali');
			$modal = $('#editAttivitaMaterialiModal');
			$modalContent = $modal.find('div.modal-content');
			$modalContent.empty();
			
			var instance = M.Modal.getInstance($modal);
			//console.log('INSTANCE: '+instance.id);
			if (instance.isOpen) {
				console.log('Modale aperta!!!');
				instance.destroy();
				console.log('Modale distrutta!!!');
			}
			
			
			$modalContent.html(content);
			
		    if (instance) {
				editInit(idAttivitaMaterialeUtente);
			}
			
			$modal.find('select').formSelect();
			M.updateTextFields();
			// per l'apertura della modale è necessario configurare anche i file:
			// SiteMeshFilter.java e SpringSecurityConfig.java
			$modal.modal('open');
		}
		
		function salvaModificaAttivitaMateriali($form, idAttivitaMaterialeUtente, idTipoAttivita) {
			console.log('salvaModificaAttivitaMateriali - idAttivitaMaterialeUtente: '+idAttivitaMaterialeUtente);
			if ($form.length == 0) {
				return;
			}
			
			if (!$form[0].checkValidity()) {
				/*
				 *	Se il form non è valido, si simula un click sul pulsante "submit" nascosto 
				 *	nella form, per attivare la validazione HTML5. Se non si desidera vedere 
				 *	anche i messaggi di JQuery Validate, fare return dopo il click
				 */
				console.log('Il form non è valido!');
				$form.find(':submit').click();
				return;
			}
			
			if ($form.valid()) {
				console.log('Il form è valido!');
				$.post(
					$form.prop('action'), 
					$form.serialize()
				).done(function(response) {
					console.log('RISPOSTA SALVATAGGIO: '+response);
					$modal = $('#editAttivitaMaterialiModal');
				    
				    if (response) {
				    	$modal.find('div.modal-content').html(response);
				    	
				    	//$modal.find('select').formSelect();
						M.updateTextFields();
				    }
				    else {
				    	console.log('AGGIORNO TABELLA ATTIVITA!!!!!');
				    	aggiornaTabellaAttivitaMateriali(); 
				    	$modal.modal('close');
				    	var mex = "";
				    	if(idTipoAttivita == 1){
				    		mex = "Ricordarsi di compilare il tab passaporto";
				    	}
				    	Swal.fire({
							/*ATTENZIONE CICCIO*/ 
				    		title : idAttivitaMaterialeUtente ? 'Attività e materiale modificati\n'+mex : 'Attività e materiali aggiunti\n'+mex,
							  type : 'success',
							  confirmButtonText : 'OK',
						});
				    }
				}).fail(function(jqXHR, textStatus) {
					console.log('TEXT STATUS: '+textStatus);
				    Swal.fire({
						  title : 'Errore',
						  html :  'Errore nel salvataggio dei dati',
						  type : 'error',
						  confirmButtonText : 'OK',
					});
				});
			}
		}
		
		
		function aggiornaTabellaAttivitaMateriali() {	
			console.log('BEGIN aggiornaTabellaAttivitaMateriali');
			var params = {
				"idDomanda" : '${nuovaDomandaForm.idDomanda}'
			}
				
			$.getJSON('<spring:url value="/ajax/getAttivitaMaterialiByIdDomanda" />', params, function(responseJSON) {
				console.log('ELENCO attivita materiale: '+responseJSON);
				$tbody = $('#bodyTabellaAttivita');
				$rigaTemplate = $('#rigaTemplateAttMateriali');
				
				$tbody.find("tr").remove();
				$tbody.closest('table').DataTable().rows().remove();
				 
				responseJSON.forEach(function(attivita) {
					aggiungiRigaAttMateriale(attivita, $rigaTemplate, $tbody);
				});
				
			}).fail(function(jqxhr, textStatus, error) {
				if (window.console) {
					console.log( "Errore in reperimento oggetto asincrono: " + textStatus + ", " + error);
				}         
			});
		}		
		
		function aggiungiRigaAttMateriale(attivita, $rigaTemplate, $appendTo) {
			console.log('BEGIN aggiungiRigaDettaglioDomanda');
			console.log('attivita.idAttivitaMaterialeUtente ='+attivita.idAttivitaMaterialeUtente);
			console.log('attivita.idAttivitaMaterialeUtente ='+attivita.descEstesa);
			$riga = $rigaTemplate.clone();
			$riga.prop('id', '');			
			
			$riga.find('td:nth-of-type(1)').text((attivita.denomTipologiaEstesa).replace('.', ','));
			if (attivita.descEstesa != null) {
				$riga.find('td:nth-of-type(2)').text((attivita.descEstesa).replace('.', ','));
			} else {
				$riga.find('td:nth-of-type(2)').text('');
			}
			
			
			$cellaLink = $riga.find('td:nth-of-type(3)');
			$cellaLink.html($cellaLink.html().replace(/PLACEHOLDER_idAttivitaMaterialeUtente/g, attivita.idAttivitaMaterialeUtente));
			
			
			$riga.appendTo($appendTo);
			
			$riga.show();
			
			$table = $riga.closest('table');
			
			//$table.filter(':hidden').show();
			
			var dataTable = $table.DataTable();
			dataTable.row.add($riga);
			dataTable.draw();						
		}

		function aggiornaDenom(){
			console.log("function aggiornaDenom");
			
			if ( ($("#cognomeAzienda").val() != null && $("#cognomeAzienda").val() != "" ) &&
				 ($("#nomeAzienda").val() != null && $("#nomeAzienda").val() != "" ) && 
				 ($("#denomAzienda").val() == null || $("#denomAzienda").val() == "" )){

				$("#denomAzienda").val($("#cognomeAzienda").val() + " " + $("#nomeAzienda").val());
			}
		}

		//script relativi al centro aziendale
		/* *** Evento alla selezione della combo Provincia:
			  - popolamento dinamico della select Comune sede legale
			  
			  - se nella combo è stato selezionato l'elemento 'Nuovo centro aziendale' :
				calcolare il codice centro aziendale : sigla provincia + progressivo (relativo alla provincia e all'azienda)
				e valorizzare il campo readomly 'codice centro aziendale'
		    */		

		 // Selezione default idCentroAziendale : Nuovo centro aziendale	
			$("#idCentroAziendale").val(0);
			$("#denominazCentroAz").val(null);
			$("#idProvCentroAz").val(null);
			$("#idComuneCentroAz").val(null);
			$("#capCentroAz").val(null);
			$("#indirizzoCentroAz").val(null);
			$("#frazioneCentroAz").val(null);
			$("#telefonoCentroAz").val(null);
			$("#cellulareCentroAz").val(null);
			$("#mailCentroAz").val(null);
			$("#pecCentroAz").val(null);

			$('#idCentroAziendale').change(function() {
				var idCentrAz = $('#idCentroAziendale').val();
				console.log('centroaziendale=' + idCentrAz);
				if(idCentrAz != null && idCentrAz != "" && idCentrAz != "0"){
					  // -- CASO SELEZIONE di un vecchio centro aziendale DALLA COMBO
					  $("#campiCentriAz").hide();		
				}else{
					$("#campiCentriAz").show();
				}
				});

			
			$('#idProvCentroAz').change(function() {
				// ------ Popolamento combo comune
				console.log('popolamento combo comune');
				var params = {
					"idProvincia" : $(this).val()			
				}

				getSelectAsincrona(
					'idComuneCentroAz',
					'<spring:url value="/ajax/getComuniProvincia" />',
					params,
					getOptionComune, 1);
				
				
				// ------ Controllo se devo generare il codice centro aziendale
				console.log('Controllo se devo generare il codice centro aziendale');
				var idCentroAziendaleSel = $('#idCentroAziendale').val();
				console.log('--- idCentroAziendaleSel ='+idCentroAziendaleSel);
				if(idCentroAziendaleSel != null && idCentroAziendaleSel != ""){
				  // -- CASO SELEZIONE 'NUOVO CENTRO AZIENDALE' DALLA COMBO
				 // commentato perchè permettiamo anche ai centri aziendali già presenti di essere modificati
				 // if(idCentroAziendaleSel == '0'){			    console.log('-- CASO SELEZIONE NUOVO CENTRO AZIENDALE ');
				    // Effettuare il calcolo per il codice centro aziendale (sigla provincia + progressivo per azienda) e valorizzare il campo 'Codice centro aziendale'
					console.log('-- Calcolo il codice centro aziendale');
				    var idProvSel = $("#idProvCentroAz").val();
				    console.log('idProvSel ='+idProvSel);			    
					$.ajax({
					    // Ricerca del centro aziendale su car_t_centro_aziendale				  
						url : '<spring:url value="/autorizzazioni/comunicazioni/getCodiceCentroAzByIdProv_"/>'+ idProvSel,
						success : function(response) {						
						  console.log('-- Codice centro aziendale calcolato = '+response);
						  $("#codCentroAz").val(response);
						  $("label[for='codCentroAz']").addClass("active");
						},	
						error: function(XMLHttpRequest, textStatus, errorThrown) { 
		                 console.log("Status getCodiceCentroAzByIdProv_: " + textStatus); 
		                 console.log("Error getCodiceCentroAzByIdProv_: " + errorThrown); 
		                }
				    });	
			   // }			
				}				
					
			});

			// ----- Gestione centri aziendali

			// --- CLICK SUL PULANTE AGGIUNGI CENTRO AZIENDALE
			$('#aggiungiCentroAziendale').click(function(e) {
				console.log('AGGIUNGI CENTRO AZIENDALE');
				e.preventDefault();
				aggiungiCentroAziendale();
			});

			function aggiungiCentroAziendale() {
				console.log('aggiungiCentroAziendale');

				// setto hidden controllato dal controller
				$("#chiamata").val('aggiungiCentroAziendale');

				var codCentroAz = $("#codCentroAz").val();
				console.log('codCentroAz =' + codCentroAz);

				// Controllare che tutti i campi obbligatori del centro aziendale siano stati valorizzati

				var idProvCentroAz = $("#idProvCentroAz").val();
				console.log('idProvCentroAz =' + idProvCentroAz);

				var idComuneCentroAz = $("#idComuneCentroAz").val();
				console.log('idComuneCentroAz =' + idComuneCentroAz);

				var capCentroAz = $("#capCentroAz").val();
				console.log('capCentroAz =' + capCentroAz);

				var indirizzoCentroAz = $("#indirizzoCentroAz").val();
				console.log('indirizzoCentroAz senza replace ='
						+ indirizzoCentroAz);
				//faccio una raplace nel caso ci fossero slash nel civico
				var indirizzoCaEscape = indirizzoCentroAz.replace(/\//g, "-");
				console.log('indirizzoCentroAz con replace ='
						+ indirizzoCaEscape);

				var idCentroAzSelezionato = $("#idCentroAziendale").val();
				console.log('idCentroAzSelezionato =' + idCentroAzSelezionato);

				if ((idProvCentroAz == null || idProvCentroAz == '')
						|| (idComuneCentroAz == null || idComuneCentroAz == '')
						|| (capCentroAz == null || capCentroAz == '')
						|| (indirizzoCentroAz == null || indirizzoCentroAz == '')) {
					swal({
						title : 'Compilare tutti i dati obbligatori del centro aziendale',
						type : 'warning',
					});
					return;
				} else {
					/*console.log('Effettuo submit centro aziendale');      	
					$('#form').submit();
					return true;*/
					$
							.ajax({
								// Ricerca del centro aziendale su car_t_centro_aziendale	
								//avevo usato encodeURIComponent(indirizzoCentroAz), ma non funziona correttamente su macchiene unix(problema il charset)
								url : '<spring:url value="/autorizzazioni/comunicazioni/checkIfCentroAzExists_"/>'
										+ idCentroAzSelezionato
										+ '_'
										+ indirizzoCaEscape
										+ '_'
										+ idComuneCentroAz,
								success : function(response) {
									if (response.indexOf("OK") == -1) {
										
										swal({
											title : 'A questo indirizzo e comune è già presente un centro aziendale',
											type : 'warning',
										});
										
										return;
									} else {
										console
												.log("Centro aziendale OK, nuovo");
										console
												.log('Effettuo submit centro aziendale');

										$('#form').submit();
										return true;
									}
								},
								error : function(XMLHttpRequest, textStatus,
										errorThrown) {
									console
											.log("Status checkIfCentroAzExists: "
													+ textStatus);
									console.log("Error checkIfCentroAzExists: "
											+ errorThrown);
								}
							});

				}

			}

			// --- CLICK SUL PULANTE SALVA
			$('#avanti').click(function(e) {
				console.log('AVANTI CENTRO AZIENDALE');
				e.preventDefault();
				avanti();
			});

			function avanti() {
				console.log('salvaDatiCentroAziendale');

				$("#chiamata").val('avanti');

				console.log('Effettuo submit avanti dati centro aziendale');
				$('#form').submit();
				return true;
			}

			// Evento ELIMINA sulla tabella dei Centri aziendali       
			function eliminaCentroAz(link) {
				console.log('eliminaCentroAz');

				var centroAz;
				$riga = $(link).closest('tr');
				centroAz = $riga.find('td:nth-of-type(2)').text();

				swal(
						{
							title : 'Attenzione!',
							html : 'Si desidera eliminare il Centro aziendale : <b>'
									+ centroAz + '</b> dalla domanda?',
							type : 'warning',
							showCancelButton : true,
							confirmButtonText : 'Sì',
							cancelButtonText : 'No',
						}).then(function(result) {
					if (result.value) {
						window.location.href = $(link).attr('href');
					}
				});
				return false;
			}

			$('#idCentroAziendale').change(function() {
				var idCentroAz  = $(this).val();
				console.log('idCentroAz selezionato ='+idCentroAz);
				if(idCentroAz != null && idCentroAz != ""){
					// -- CASO SELEZIONE DI UN CENTRO AZIENDALE DALLA COMBO
					if(idCentroAz != '0'){
					  console.log('-- CASO SELEZIONE DI UN CENTRO AZIENDALE DALLA COMBO');
					  // sbianco i campi nel caso avessero dati
					  $(".campiCentroAz").val("");				  
					  console.log('---- Cerco e carico i dati del centro aziendale');
					  $.ajax({
						    // Ricerca del centro aziendale su car_t_centro_aziendale				  
							url : '<spring:url value="/autorizzazioni/comunicazioni/getCentroAzById_"/>'+ idCentroAz,
							success : function(response) {
								if (response.indexOf("NOTEXISTS") == -1) {
								  console.log('Centro aziendale trovato');
								  
								  //abilito le select per poterle modificare
								  $(".select-dropdown").removeAttr("disabled");
								  $(".select-dropdown").removeAttr("readonly");
								  $("select").removeAttr("disabled");
								  $("select").removeAttr("readonly");							  							  
								  
								  // --- Recupero i valori						  
								  var splittedStr = response.split("&&&");							  						
								  
								  var idCentroAz = splittedStr[0];
								  console.log('idCentroAz ='+idCentroAz);
								  
								  var codCentroAz = splittedStr[1];
								  console.log('codCentroAz ='+codCentroAz);
								  
								  var denominazione = splittedStr[2];
								  console.log('denominazione ='+denominazione);
								  
								  var idProvincia = splittedStr[3];
								  console.log('idProvincia ='+idProvincia);							  							 
								  
								  var idComune = splittedStr[4];
								  console.log('idComune ='+idComune);
								  
								  var cap = splittedStr[5];
								  console.log('cap='+cap);
								  
								  var frazione = splittedStr[6];
								  console.log('frazione='+frazione);
								  
								  var indirizzo = splittedStr[7];
								  console.log('indirizzo ='+indirizzo);
								  
								  var numTel = splittedStr[8];
								  console.log('numTel ='+numTel);
								  
								  var numCell = splittedStr[9];
								  console.log('numCell ='+numCell);							  							  
								  
								  var eMail = splittedStr[10];
								  console.log('eMail ='+eMail);
								  
								  var pec = splittedStr[11];
								  console.log('pec ='+pec);
								  
								  var comuni = splittedStr[12];
								  							  
								  
								  // ------ Valorizzo i campi							  
								  console.log('Valorizzo i campi');
								  		
								  $("#codCentroAz").val(codCentroAz);
								  if (denominazione != 'null'){
								  $("#denominazCentroAz").val(denominazione);}
								  
								  $("#idProvCentroAz").formSelect();
								  $("#idComuneCentroAz").formSelect();
								  if(idProvincia !== undefined && idProvincia != null && idProvincia != 'null'){	
								    	$("#idComuneCentroAz").html(comuni);
										$('#idComuneCentroAz').formSelect();	
										
										console.log('Seleziono prov ='+idProvincia);
								    	$("#idProvCentroAz").val(idProvincia);
								    	$('#idProvCentroAz').formSelect();
								    	
								    	if(idComune !== undefined && idComune != null && idComune != 'null'){
								    	  console.log('Seleziono comune  ='+idComune);
								    	  $("#idComuneCentroAz").val(idComune);
								    	  $('#idComuneCentroAz').formSelect();
									    }
									    else{
									      $("#idComuneCentroAz").val("");
									    }
								  }
								  else{
								    	console.log('Provincia e comune non presenti sul db');
								    	$("#idProvCentroAz").val("");
								    	$("#idComuneCentroAz").val("");
								  }
								  
								    
								   if(cap!== undefined && cap != null && cap != 'null'){ 	
									   $("#capCentroAz").val(cap);
								   }
								   else{
								     $("#capCentroAz").val(""); 
								   }
								   
								   if(indirizzo!== undefined && indirizzo != null && indirizzo != 'null'){
									 console.log('valorizzo indirizzo');  
								     $("#indirizzoCentroAz").val(indirizzo);
								   }
								   else{
								     $("#indirizzoCentroAz").val("");   
								   }
								   
								   if(frazione!== undefined && frazione != null && frazione != 'null'){
								     $("#frazioneCentroAz").val(frazione);   
								   }
								   else{
								     $("#frazioneCentroAz").val("");  
								   }
								   
								   
								   if(numTel!== undefined && numTel != null && numTel != 'null'){
								     $("#telefonoCentroAz").val(numTel);   
								   }
								   else{
								     $("#telefonoCentroAz").val("");  
								   }
								   
								   if(numCell!== undefined && numCell != null && numCell != 'null'){
								     $("#cellulareCentroAz").val(numCell);   
								   }
								   else{
								     $("#cellulareCentroAz").val("");  
								   }							   							   
								   
								   if(eMail!== undefined && eMail != null && eMail != 'null'){
								     $("#mailCentroAz").val(eMail);   
								   }
								   else{
								     $("#mailCentroAz").val("");  
								   }
								   
								   if(pec!== undefined && pec != null && pec != 'null'){
								     $("#pecCentroAz").val(pec);   
								   }
								   else{
								     $("#pecCentroAz").val("");  
								   }

								   // abilito le label, altrimenti si sovrappongono ai valori
								   $(".campiCentroAz:not(.select-wrapper)").next().addClass("active");	
								}
								else {							
							      console.log('centro aziendale non trovato su car_t_centro_aziendale');
							      console.log('Sbianco i campi');
								  $(".campiCentroAz").val("");	
								}  
							}	
					  });				 					  				 
					}
					// -- CASO SELEZIONE DI 'NUOVO CENTRO AZIENDALE' DALLA COMBO
					else{
					  console.log('-- CASO SELEZIONE DI NUOVO CENTRO AZIENDALE DALLA COMBO');
					  $(".campiCentroAz").val("");	  
					  $("select").formSelect();				  				  	 				  
					}
				}
			});
			
  	  </script> </content>

</body>
</html>