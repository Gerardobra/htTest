<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="it.aizoon.ersaf.util.CaronteConstants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<link type="text/css" rel="stylesheet"
	href="<spring:url value="/resources/css/dropify.min.css"/>"
	media="screen" />
	
<link type="text/css" rel="stylesheet"
  href="<spring:url value="/resources/css/dropzone.min.css"/>"
  media="screen" />

<style>
</style>
</head>
<body>

	<!-- div class="row"-->
	<div class="card">
		<div class="card-title">
			<div class="hide-on-large-only bg-primary-color"
				style="color: rgba(255, 255, 255, 0.7); padding-bottom: 5px; padding-top: 5px; padding-left: 5px">
				<h6 style="color: #fff; font-size: 18px;" id="small_label">Dati
					Richiesta</h6>
			</div>
			<nav class="breadcrumb-nav col s12 hide-on-med-and-down">
				<div class="nav-wrapper">
					<div class="col s12">
						<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
						<a href="#richiesta" data-value="richiesta" class="breadcrumb">&nbsp;&nbsp;Dati
              Richiesta</a>
            <a href="#mittente" data-value="mittente" class="breadcrumb">Mittente</a>
            <a href="#destinatario" data-value="destinatario"
              class="breadcrumb">Destinatario</a>
            <a href="#trasporto" data-value="trasporto" class="breadcrumb">Trasporto</a>
            <a href="#merce" data-value="merce" class="breadcrumb">Merce</a>
            <a href="#tariffa" data-value="tariffa" class="breadcrumb">Tariffa</a>
            <a href="#trattamento" data-value="trattamento"
              class="breadcrumb">Trattamento</a>
            <a href="#pagamento" data-value="pagamento" class="breadcrumb">Pagamento</a>
            <c:if test="${isSuperUser}">
            <a href="#esegui" data-value="esegui" class="breadcrumb">Esegui</a>
            </c:if>
            <a style="display: none;"></a>
					</div>
				</div>
			</nav>
		</div>

		<spring:eval var="statoInBozza"
			expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_BOZZA" />
		<spring:eval var="statoRestituita"
			expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_RESTITUITA" />
		<spring:eval var="statoInoltrata"
			expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_INOLTRATA" />

		<div class="col s12" id="richiesta">
			<spring:url value="/export/richieste/modifica" var="formAction" />
			<form:form action="${formAction}" method="post"
				modelAttribute="nuovaRichiestaForm" accept-charset="utf-8"
				enctype="multipart/form-data">
				<div class="card-content">
					<div class="card-panel hoverable">Si sta creando una nuova
						richiesta di rilascio di certificato fitosanitario di esportazione
						o riesportazione per merce in uscita dall’Italia verso Paesi
						Terzi, vincolato all’esito positivo dell’ispezione e del controllo
						fitosanitario da parte degli Ispettori Fitosanitari. Per
						confermare e procedere con la richiesta utilizzare il bottone
						«SALVA», in caso contrario utilizzare «TORNA ALLA RICERCA».</div>
					<div class="row">
						<spring:eval var="idTipoExport"
							expression="T(it.aizoon.ersaf.util.CaronteConstants).TIPO_RICHIESTA_EXPORT" />
						<spring:eval var="idTipoRiexport"
							expression="T(it.aizoon.ersaf.util.CaronteConstants).TIPO_RICHIESTA_RIEXPORT" />
						<div class="input-field col s6 m4 l3">
							<c:choose>
								<c:when test="${nuovaRichiestaForm.idTipoRichiesta == idTipoExport}">							
									<input type='text' value="Export" disabled="disabled" />
									<form:input type='hidden' id="tipoRichiesta"
										path="idTipoRichiesta" />	
									<label for="tipoRichiesta">Tipo richiesta</label>								
								</c:when>
								<c:when test="${nuovaRichiestaForm.idTipoRichiesta == idTipoRiexport}">								    
									<input type='text' value="Riexport" disabled="disabled" />
									<form:input type='hidden' id="tipoRichiesta"
										path="idTipoRichiesta" />
									<label for="tipoRichiesta">Tipo richiesta</label>									
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="statoRichiesta" path="descStatoRichiesta" disabled="true" />
							<label for="statoRichiesta">Stato richiesta</label>
						</div>
						<div class="input-field col s6 m4 l3">
              <form:input type="text" id="codiceRichiesta" path="codRichiesta" disabled="true" />
              <label for="codiceRichiesta">Codice richiesta</label>
            </div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="utenteResponsabile"
								path="descUtenteResponsabile" disabled="true" />
							<label for="utenteResponsabile">Utente Responsabile</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="tipoSpedizioniere"
								path="denomTipoSpedizioniere" disabled="true" />
							<label for="tipoSpedizioniere">Tipo Operatore</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="spedizioniere" path="spedizioniere"
								disabled="true" />
							<label for="spedizioniere" style="white-space: nowrap">Denominazione
								Operatore</label>
						</div>
						<fmt:formatDate value="${dataApertura}" pattern="dd/MM/yyyy"
							var="dataCreazione" />
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataCreazione" path="dataApertura"
								data-value="${dataCreazione}" disabled="true" />
							<label for="dataCreazione">Data apertura</label>
						</div>
						<fmt:formatDate value="${dataUltimaModifica}" pattern="dd/MM/yyyy"
							var="dataModifica" />
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataModifica"
								path="dataUltimaModifica" data-value="${dataModifica}"
								disabled="true" />
							<label for="dataModifica">Data modifica</label>
						</div>
						<fmt:formatDate value="${dataInoltro}" pattern="dd/MM/yyyy"
							var="dataInoltro" />
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInoltro" path="dataInoltro"
								data-value="${dataInoltro}" disabled="true" />
							<label for="dataInoltro">Data inoltro</label>
						</div>
						<c:if test="${nuovaRichiestaForm.idTipoRichiesta == idTipoRiexport}">
						<div class="input-field col s6 m4 l3">
              <form:input type='text' id="numeroCertificatoOriginale"
                path="numeroCertificatoOriginale"
                data-value="${numeroCertificatoOriginale}" maxlength="23" cssClass="validate tooltipped"
                data-tooltip="Indicare il numero del certificato di origine delle merci di cui si sta richiedendo il certificato per il riexport" />
              <form:errors path="numeroCertificatoOriginale" cssClass="error" />
              <label for="numeroCertificatoOriginale">Numero certificato d'origine *</label>
            </div>
            </c:if>
						<div class="input-field col s12 m8 l6">
							<form:textarea id="note" path="note"
								cssClass="materialize-textarea validate tooltipped" rows="2"
								data-length="1000" maxlength="1000" data-position="right"
								data-tooltip="Note per l'Operatore (ad esempio informazioni di contatto)" />
							<form:errors path="note" cssClass="error" />
							<label for="note">Note</label>
						</div>
					</div>
					<c:if test="${nuovaRichiestaForm.idTipoRichiesta == idTipoRiexport}">
						<c:if test="${not empty nuovaRichiestaForm.nomeFileAllegatoCertificato}">
							<spring:url
								value="/export/richieste/allegato/certificato/${nuovaRichiestaForm.idRichiesta}/${nuovaRichiestaForm.nomeFileAllegatoCertificato}"
								var="pathAllegatoCertificato" />
						</c:if>
						<div class="row">
							<div class="input-field col s12 m8 l6">
								<input type="file" id="allegatoCertificato"
									name="allegatoCertificato" class="dropify"
									data-default-file="${pathAllegatoCertificato}"
									data-max-file-size="3M" data-show-remove="false" />
							</div>
						</div>
					</c:if>
				</div>
				<div class="card-action">
					<div class="row">
						<a class="btn waves-effect forward left" href="#mittente">AVANTI</a>

						<div class="right" style="text-align: right">
							<c:if
								test="${nuovaRichiestaForm.idStatoRichiesta != statoInBozza}">
								<a
									href='<spring:url value="/export/richieste/annulla/${nuovaRichiestaForm.idRichiesta}"/>'
									title="Annulla"
									class="btn red darken-4 waves-effect waves-light"
									style="display: inline-block"
									onclick="return checkAnnulla(this)">ANNULLA<i
									class="material-icons right">clear</i></a>
							</c:if>

							<c:if
								test="${isSuperUser and nuovaRichiestaForm.idStatoRichiesta == statoInoltrata}">
								<a
									href='<spring:url value="/export/richieste/restituisci/${nuovaRichiestaForm.idRichiesta}"/>'
									title="Restituisci"
									class="btn red darken-4 waves-effect waves-light"
									style="display: inline-block"
									onclick="return checkRestituisci(this)">RESTITUISCI<i
									class="material-icons right">arrow_back</i></a>
							</c:if>

							<c:if test="${nuovaRichiestaForm.abilitaInoltra}">
								<a
									href='<spring:url value="/export/richieste/inoltra/${nuovaRichiestaForm.idRichiesta}"/>'
									title="Inoltra"
									class="btn amber darken-1 waves-effect waves-light"
									style="display: inline-block"
									onclick="return checkInoltra(this)">INOLTRA</a>
							</c:if>

							<button class="btn confirm waves-effect waves-light "
								type="submit" name="datiRichiesta" style="display: inline-block">SALVA</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>

		<div class="col s12" id="mittente">
			<form:form action="${formAction}" method="post"
				modelAttribute="nuovaRichiestaForm" accept-charset="utf-8">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s12 m8 l6">
							<form:input type="text" id="denominazioneMittente"
								path="denominazioneMittente" cssClass="validate"
								required="required" maxlength="100" />
							<form:errors path="denominazioneMittente" cssClass="error" />
							<label for="denominazioneMittente">Cognome e nome /
								Denominazione *</label>
						</div>
						<div class="input-field col s12 m8 l6">
							<form:input type="text" id="indirizzoMittente"
								path="indirizzoMittente" cssClass="validate" 
								required="required" maxlength="200" />
							<form:errors path="indirizzoMittente" cssClass="error" />
							<label for="indirizzoMittente">Indirizzo (Es. Via Roma, 24)*</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:select id="provinciaMittente" path="idProvinciaMittente"
								cssClass="validate" required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaProvince}" itemValue="idProvincia"
									itemLabel="denomProvincia" />
							</form:select>
							<form:errors path="idProvinciaMittente" cssClass="error" />
							<label for="provinciaMittente">Provincia *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:select id="comuneMittente" path="idComuneMittente"
								cssClass="validate" required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaComuni}" itemValue="idComune"
									itemLabel="denomComune" />
							</form:select>
							<form:errors path="idComuneMittente" cssClass="error" />
							<label for="comuneMittente">Comune *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="codiceRuopMittente"
								path="codiceRuopMittente" cssClass="validate" required="required" 
								maxlength="20" />
							<form:errors path="codiceRuopMittente" cssClass="error" />
							<label for="codiceRuopMittente">Codice RUOP *</label>
						</div>
						<div class="input-field col s12 m8 l6">
							<form:textarea id="noteMittenteCertificato" path="noteMittenteCertificato"
								cssClass="materialize-textarea validate tooltipped" rows="2"
								data-length="1000" maxlength="1000" data-position="right"
								data-tooltip="Note per l'Operatore (ad esempio informazioni di contatto)" />
							<form:errors path="noteMittenteCertificato" cssClass="error" />
							<label for="noteMittenteCertificato">Note mittente</label>
						</div>
					</div>
				</div>
				<div class="card-action">
					<div class="row">
            <a class="btn waves-effect back" href="#richiesta">INDIETRO</a>
						<a class="btn waves-effect forward" href="#destinatario">AVANTI</a>
						
						<button class="btn confirm waves-effect waves-light right"
							type="submit" name="datiMittente">SALVA</button>
					</div>
				</div>
			</form:form>
		</div>

		<div class="col s12" id="destinatario">
			<form:form action="${formAction}" method="post"
				modelAttribute="nuovaRichiestaForm" accept-charset="utf-8">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s12 m8 l6">
							<form:input type="text" id="denominazioneDestinatario"
								path="denominazioneDestinatario" cssClass="validate"
								required="required" maxlength="100" />
							<form:errors path="denominazioneDestinatario" cssClass="error" />
							<label for="denominazioneDestinatario">Cognome e nome /
								Denominazione *</label>
						</div>
						<div class="input-field col s12 m8 l6">
							<form:input type="text" id="indirizzoDestinatario"
								path="indirizzoDestinatario" cssClass="validate"
								required="required" maxlength="200" />
							<form:errors path="indirizzoDestinatario" cssClass="error" />
							<label for="indirizzoDestinatario">Indirizzo (Es. Via Roma, 24)*</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="comuneDestinatario"
								path="comuneDestinatario" cssClass="validate"
								required="required" maxlength="100" />
							<form:errors path="comuneDestinatario" cssClass="error" />
							<label for="comuneDestinatario">Città *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:select id="nazioneDestinatario"
								path="idNazioneDestinatario" cssClass="validate"
								required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaNazioni}" itemValue="idNazione"
									itemLabel="denomNazione" />
							</form:select>
							<label for="nazioneDestinatario">Stato *</label>
							<form:errors path="idNazioneDestinatario" cssClass="error" />
						</div>
						<!-- 
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="codiceRup" path="codiceRup"
								cssClass="validate" maxlength="20" />
							<form:errors path="codiceRup" cssClass="error" />
							<label for="codiceRup">Codice RUP</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:select id="statoRupDestinatario"
								path="idStatoRupDestinatario" cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaNazioni}" itemValue="idNazione"
									itemLabel="denomNazione" />
							</form:select>
							<form:errors path="idStatoRupDestinatario" cssClass="error" />
							<label for="statoRupDestinatario">Stato RUP destinatario</label>
						</div> -->
					</div>
				</div>
				<div class="card-action">
					<div class="row">
            <a class="btn waves-effect back" href="#mittente">INDIETRO</a>
						<a class="btn waves-effect forward" href="#trasporto">AVANTI</a> 
						
						<button class="btn confirm waves-effect waves-light right"
							type="submit" name="datiDestinatario">SALVA</button>
					</div>
				</div>
			</form:form>
		</div>

		<div class="col s12" id="trasporto">
			<form:form action="${formAction}" method="post"
				modelAttribute="nuovaRichiestaForm" accept-charset="utf-8">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<form:select id="idNazioneProtVeg" path="idNazioneProtVeg"
								cssClass="validate" required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaNazioni}" itemValue="idNazione"
									itemLabel="denomNazione" />
							</form:select>
							<form:errors path="idNazioneProtVeg" cssClass="error" />
							<label for="idNazioneProtVeg">Nazione servizio
								protezione vegetali destinatario *</label>
						</div>
						<div class="input-field col s12 m8 l6">
							<form:input type="text" id="puntoEntrataDichiarato" path="puntoEntrataDichiarato" maxlength="150" />
              <form:errors path="puntoEntrataDichiarato" cssClass="error" />
              <label for="puntoEntrataDichiarato">Punto d'entrata dichiarato</label>
						</div>
						<div class="input-field col s6 m4 l3">
              <form:input type="text" id="numDocumentoTrasporto"
                path="numDocumentoTrasporto" cssClass="validate"
                maxlength="30" />
              <form:errors path="numDocumentoTrasporto" cssClass="error" />
              <label for="numDocumentoTrasporto">N. Identificativo mezzo trasporto</label>
            </div>
						<div class="input-field col s6 m4 l3">
							<form:select id="mwzzoTrasporto" path="idModoTrasporto"
								cssClass="validate" required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaModiTrasporto}"
									itemValue="idModoTrasporto" itemLabel="descModoTrasportoExp" />
							</form:select>
							<form:errors path="idModoTrasporto" cssClass="error" />
							<label for="mwzzoTrasporto">Mezzo di trasporto dichiarato
								*</label>
						</div>
						<div class="input-field col s6 m4 l6">
							<form:input type="text" id="luogoDeposito" path="luogoDeposito"
								cssClass="validate" required="required" maxlength="200" />
							<form:errors path="luogoDeposito" cssClass="error" />
							<label for="luogoDeposito">Luogo in cui è depositata la
								merce per l'ispezione *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInizioDisponibilitaMerce"
								path="dataInizioDisponibilitaMerce" cssClass="datepicker" />
							<form:errors path="dataInizioDisponibilitaMerce" cssClass="error" />
							<label for="dataInizioDisponibilitaMerce">Data inizio
								disponibilità merce *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="oraInizioDisponibilitaMerce"
								path="oraInizioDisponibilitaMerce" cssClass="timepicker" />
							<form:errors path="oraInizioDisponibilitaMerce" cssClass="error" />
							<label for="oraInizioDisponibilitaMerce">Ora inizio
								disponibilità merce</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataPartenzaMerce"
								path="dataPartenzaMerce" cssClass="datepicker" />
							<form:errors path="dataPartenzaMerce" cssClass="error" />
							<label for="dataPartenzaMerce">Data partenza merce *</label>
						</div>
						<div class="input-field col s12 m8 l6">
						  <form:textarea id="noteTrasporto" path="noteTrasporto" cssClass="materialize-textarea validate" rows="2"
                data-length="1000" maxlength="1000" data-position="right" />
              <form:errors path="noteTrasporto" cssClass="error" />
              <label for="noteTrasporto">Note</label>
            </div>
            
					</div>
				</div>
				<div class="card-action">
					<div class="row">
            <a class="btn waves-effect back" href="#destinatario">INDIETRO</a>
						<a class="btn waves-effect forward" href="#merce">AVANTI</a> 
						
						<button class="btn confirm waves-effect waves-light right"
							type="submit" name="datiTrasporto">SALVA</button>
					</div>
				</div>
			</form:form>
		</div>

		<div class="col s12" id="merce">
			<form:form id="formMerce" action="${formAction}" method="post"
				modelAttribute="nuovaRichiestaForm" accept-charset="utf-8">
				<input type="hidden" id="idTipoRichiesta"
					value="${nuovaRichiestaForm.idTipoRichiesta}" disabled="disabled" />
				<input type="hidden" id="numeroMerci"
					value="${fn:length(nuovaRichiestaForm.listaMerceRichiesta)}"
					disabled="disabled" />
				<div class="card-content">
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<select id="idProdotto" name="idProdotto" data-error="#errorTxt1">
								<option value="">Selezionare</option>
								<c:forEach var="item" items="${listaProdotti}">
									<option value="${item.idProdotto}" data-classe="${item.idClasse}">${item.denomProdotto}</option>
								</c:forEach>
							</select><label for="idProdotto">Prodotto *</label>
							<div id="errorTxt1" style="height: 0px"></div>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="genereMerce" name="genereMerce"
							 placeholder="Indicare generi più rappresentativi" data-error="#errorTxt2"
							 class="autocomplete" autocomplete="off"> 
							<label id="labelGenere" class="active" for="genereMerce">Genere *</label>
							<div id="errorTxt2" style="height: 0px"></div>
							<input type="hidden" id="idGenereMerce" name="idGenereMerce" data-error="#errorTxt2" />
						</div>
						<div class="input-field col s6 m4 l3">
							<select id="specieMerce" name="specieMerce"
								data-error="#errorTxt3">
								<option value="">Selezionare</option>
								<!-- c:forEach var="item" items="${listaGeneriMerce}">
                  <option value="${item.idGenere}">${item.denomGenere}</option-->
								<!--/c:forEach-->
							</select> <label for="specieMerce">Specie</label>
							<div id="errorTxt3" style="height: 0px"></div>
						</div>
						<div class="input-field col s6 m4 l3">
							<select id="nazioneOrigine" name="nazioneOrigine"
								data-error="#errorTxt4">
								<option value="">Selezionare</option>
								<c:forEach var="item" items="${listaNazioni}">
									<option value="${item.idNazione}">${item.denomNazione}</option>
								</c:forEach>
							</select> <label for="nazioneOrigine">Stato d'origine *</label>
							<div id="errorTxt4" style="height: 0px"></div>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="unitaMisuraMerce" path=""
								disabled="true" />
							<label for="unitaMisuraMerce">Unità di misura</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="quantitaLordaMerce"
								name="quantitaLordaMerce" maxlength="10" data-error="#errorTxt5" /> 
						  <label for="quantitaLordaMerce">Quantità Lorda</label>
						  <div id="errorTxt5" style="height: 0px"></div>
						</div>
						<div class="input-field col s6 m4 l3" style="display:none">
              <input type="text" id="numeroPezzi"
                name="numeroPezzi" maxlength="5" data-error="#errorTxt6" /> 
              <label id="labelNumeroPezzi" for="numeroPezzi" style="display:none">Numero pezzi</label>
              <label id="labelNumeroPezziObb" for="numeroPezzi">Numero pezzi *</label>
              <div id="errorTxt6" style="height: 0px"></div>
            </div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="quantitaMerce" name="quantitaMerce" maxlength="10" data-error="#errorTxt7" /> 
						  <label id="labelQuantitaNetta" for="quantitaMerce">Quantità Netta *</label>
						  <label id="labelQuantitaNettaNumero" for="quantitaMerce" style="display:none">Quantità Netta (peso in kg) *</label>
						  <div id="errorTxt7" style="height: 0px"></div>
						</div>
						<div class="input-field col s6 m4 l3">
							<select id="naturaCollo" name="idNaturaCollo" data-error="#errorTxt8">
								<option value="">Selezionare</option>
								<c:forEach var="item" items="${listaNaturaColli}">
									<option value="${item.idNaturaCollo}">${item.descNaturaCollo}</option>
								</c:forEach>
							</select> <label for="idNaturaCollo">Natura dei colli *</label>
							<div id="errorTxt8" style="height: 0px"></div>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="numeroColli" name="numeroColli" maxlength="7" data-error="#errorTxt9" />
							<label id="labelNumeroColli" for="numeroColli">Numero di colli</label>
              <label id="labelNumeroColliObb" for="numeroColli" style="display:none">Numero di colli *</label>
							<div id="errorTxt9" style="height: 0px"></div>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="marchio" name="marchio" maxlength="100" />
							<label for="marchio">Marchio</label>
						</div>
						<div class="input-field col s6 m4 l3 right-align">
							<button type="button" class="btn clear-form waves-effect">PULISCI</button>
							<button type="button" id="aggiungiMerce"
								class="btn green waves-effect waves-light">AGGIUNGI</button>
						</div>
						<div class="input-field col s6 m4 l3 right-align">
              <button type="submit" id="salvaDatiMerce" name="datiMerce"
	              formnovalidate="formnovalidate"
	              class="btn confirm waves-effect waves-light right">SALVA</button>
            </div>
					</div>

					<div class="row">
						<table id="tabellaMerci" class="responsive-table striped display">
							<thead>
								<tr>
									<th>Stato d'origine</th>
									<th>Prodotto</th>
									<th>Genere</th>
									<th>Specie</th>
									<th>Unità misura</th>
									<th>N. pezzi</th>
									<th>Quantità Lorda</th>
									<th>Quantità Netta</th>
									<th>Natura colli</th>
									<th>Numero colli</th>
									<th>Marchio</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							<tbody id="bodyTabellaMerci">
								<c:forEach var="merceRichiesta"
									items="${nuovaRichiestaForm.listaMerceRichiesta}" varStatus="s">
									<tr>
										<td>${merceRichiesta.descNazioneOrigine}<form:input
												type="hidden" id="idMerceRichiesta"
												path="listaMerceRichiesta[${s.index}].idMerceRichiesta" />
											<form:input type="hidden" id="idNazioneOrigine"
												path="listaMerceRichiesta[${s.index}].idNazioneOrigine" />
											<form:input type="hidden" id="descNazioneOrigine"
												path="listaMerceRichiesta[${s.index}].descNazioneOrigine" />
										</td>
										<td>${merceRichiesta.descProdotto}<form:input
												type="hidden" id="idProdotto"
												path="listaMerceRichiesta[${s.index}].idProdotto" /> <form:input
												type="hidden" id="descProdotto"
												path="listaMerceRichiesta[${s.index}].descProdotto" />
										</td>
										<td><i>${merceRichiesta.descGenere}</i><form:input type="hidden"
												id="idGenere"
												path="listaMerceRichiesta[${s.index}].idGenere" /> <form:input
												type="hidden" id="descGenere"
												path="listaMerceRichiesta[${s.index}].descGenere" />
										</td>
										<td><i>${merceRichiesta.descSpecie}</i>
										  <form:input type="hidden"
												id="idSpecie"
												path="listaMerceRichiesta[${s.index}].idSpecie" /> <form:input
												type="hidden" id="descSpecie"
												path="listaMerceRichiesta[${s.index}].descSpecie" />
										</td>
										<td>${merceRichiesta.descUnitaMisura}<form:input
												type="hidden" id="descUnitaMisura"
												path="listaMerceRichiesta[${s.index}].descUnitaMisura" />
										</td>
										<td>${merceRichiesta.numeroPezzi}
										  <form:input type="hidden" id="numeroPezzi" path="listaMerceRichiesta[${s.index}].numeroPezzi" />
                    </td>
										<td><fmt:formatNumber type="number" pattern="##0.###"
												value="${merceRichiesta.quantitaLordaProdotto}" /> <form:input
												type="hidden" id="quantitaLordaProdotto"
												path="listaMerceRichiesta[${s.index}].quantitaLordaProdotto" /></td>
										<td><fmt:formatNumber type="number" pattern="##0.###"
												value="${merceRichiesta.quantita}" /> <form:input
												type="hidden" id="quantita"
												path="listaMerceRichiesta[${s.index}].quantita" /></td>
										<td>${merceRichiesta.descNaturaCollo}<form:input type="hidden"
												id="descNaturaCollo"
												path="listaMerceRichiesta[${s.index}].descNaturaCollo" />
										<td>${merceRichiesta.numeroColli}
										  <form:input type="hidden" id="numeroColli" path="listaMerceRichiesta[${s.index}].numeroColli" />
										</td>
										<td>${merceRichiesta.marchio}<form:input type="hidden"
												id="marchio"
												path="listaMerceRichiesta[${s.index}].marchio" />
										<td nowrap style="white-spaces: nowrap"><a
											id="eliminaMerce" title="Elimina" onClick="eliminaRiga(this)"
											class="btn btn-floating btn-floating-medium deep-orange accent-2">
												<i class="material-icons">delete</i>
										</a></td>
									</tr>
								</c:forEach>
								<tr id="rigaTemplate" style="display: none">
									<td nowrap style="white-spaces: nowrap"><a
										id="eliminaMerce" title="Elimina" onClick="eliminaRiga(this)"
										class="btn btn-floating btn-floating-medium deep-orange accent-2">
											<i class="material-icons">delete</i>
									</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="card-action">
					<div class="row">
            <a class="btn waves-effect back" href="#trasporto">INDIETRO</a>
						<a class="btn waves-effect forward" href="#tariffa">AVANTI</a> 
					</div>
				</div>
			</form:form>
		</div>

		<div class="col s12" id="tariffa">
			<form:form id="formTariffe" action="${formAction}" method="post"
				modelAttribute="nuovaRichiestaForm" accept-charset="utf-8">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="tariffaIdentita"
								path="tariffaIdentita" disabled="true" />
							<label for="tariffaIdentita">Tariffa controlli d'identità
								(€)</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="tariffaDocumentali"
								path="tariffaDocumentali" disabled="true" />
							<label for="tariffaDocumentali">Tariffa controlli
								documentali (€)</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="tariffaPartite"
								path="tariffaFitosanitari" disabled="true" />
							<label for="tariffaPartite">Tariffa partite (€)</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="massimalePartite"
								path="massimalePartite" disabled="true" />
							<label for="massimalePartite">Massimale partite (€)</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="tariffaTotale" path="tariffaTotale"
								disabled="true" />
							<label for="tariffaTotale">Tariffa totale (€)</label>
						</div>
						<div class="input-field col s6 m4 l3 center">
							<a title="Legenda"
								class="btn modal-trigger btn-floating btn-large cyan hoverable"
								href="#legendaTariffe"> <i class="material-icons">live_help</i></a>
						</div>
						<div class="card-panel hoverable col s12 m9 l6">
						<i>Modalità per il pagamento della tariffa:</i><br>
						&#8226;	<strong>PagoPA</strong> attraverso il portale pagamenti di Regione Lombardia nella sezione “FITOSANITARIO Tariffa export” al link:<br>
						<a href="https://pagamentinlombardia.servizirl.it/pa/public/richiestaPagamentoSpontaneo.html?codTipo=RL_FITO_EXPORT" >
							https://pagamentinlombardia.servizirl.it/pa/public/richiestaPagamentoSpontaneo.html?codTipo=RL_FITO_EXPORT
						</a><br>			
						&#8226; <strong>Bonifico bancario</strong> a: Regione Lombardia - l'Istituto di Credito: INTESA SANPAOLO<br>
						IBAN per il versamento: <strong>IT49 L030 6909 7901 0000 0300 093</strong>.<br>
						Nella causale di versamento dovrà essere indicato: <strong>M1</strong> - capitolo di entrata <strong>15046</strong> - motivo per cui viene pagata la tariffa: <strong>Controlli Export</strong>.
					</div>
					</div>

					<!-- Legenda Tariffe -->
					<div id="legendaTariffe" class="modal">
						<div class="modal-content">
							<h5>Tabella di riferimento per tariffe controlli fitosanitari</h5>
							<table id="tabellaTariffe" class="responsive-table striped display">
								<thead>
	                <tr>
	                  <th>Quantità</th>
	                  <th>Tariffa (Euro)</th>
	                </tr>
	              </thead>
	              <tbody id="bodyTabellaTariffe">
	                <c:forEach var="tariffa" items="${listaTariffeExport}">
	                  <c:forEach var="range" items="${tariffa.listaRange}">
	                  <tr>
	                    <td>
	                      <c:if test="${range.quantitaLimiteInf > 0}">
	                      <fmt:formatNumber type="number" pattern="##0.###" value="${range.quantitaLimiteInf}" /> &lt;
	                      </c:if>
	                       massa merce
	                      <c:if test="${not empty range.quantitaLimiteSup}">
	                      &le; <fmt:formatNumber type="number" pattern="##0.###" value="${range.quantitaLimiteSup}" /> kg
	                      </c:if>
	                    </td>
	                    <td><fmt:formatNumber type="number" pattern="##0.00"
	                      value="${range.tariffa}" /></td>
	                  </tr>
	                  </c:forEach>
	                <c:if test="${not empty tariffa.incrementoQuantita}">
	                <tr style="font-style: italic;">
	                  <td>&emsp;per ogni +<fmt:formatNumber type="number" pattern="##0.###"
	                    value="${tariffa.incrementoQuantita}" /> kg</td>
	                  <td><fmt:formatNumber type="number" pattern="##0.00"
	                    value="${tariffa.incrementoTariffa}" /></td>
	                </tr>
	                </c:if>
	                <c:if test="${not empty tariffa.massimaleTariffa}">
	                <tr style="font-style: italic;">
	                  <td>&emsp;massimale</td>
	                  <td><fmt:formatNumber type="number" pattern="##0.00"
	                    value="${tariffa.massimaleTariffa}" /></td>
	                </tr>
	                </c:if>               
	                </c:forEach>
	              </tbody>
							</table>
						</div>
					</div>

				</div>
				<div class="card-action">
					<div class="row">
            <a class="btn waves-effect back" href="#merce">INDIETRO</a>
						<a class="btn waves-effect forward" href="#trattamento">AVANTI</a>
					</div>
				</div>
			</form:form>
		</div>

		<div class="col s12" id="trattamento">
			<form:form id="formTrattamento" action="${formAction}" method="post"
				modelAttribute="nuovaRichiestaForm" accept-charset="utf-8">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<form:select id="trattamento" path="idTrattamento">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaTrattamenti}"
									itemValue="idTrattamento" itemLabel="descTrattamento" />
							</form:select>
							<form:errors path="idTrattamento" cssClass="error" />
							<label for="trattamento">Trattamento</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="prodottoChimico"
								path="prodottoChimico" maxlength="100" />
							<form:errors path="prodottoChimico" cssClass="error" />
							<label for="prodottoChimico">Prodotto chimico (sostanza
								attiva)</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="durata" path="durata" maxlength="20" />
							<form:errors path="durata" cssClass="error" />
							<label for="durata">Durata</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="temperatura" path="temperatura"
								maxlength="20" />
							<form:errors path="durata" cssClass="error" />
							<label for="temperatura">Temperatura</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="concentrazione" path="concentrazione"
								maxlength="50" />
							<form:errors path="concentrazione" cssClass="error" />
							<label for="concentrazione">Concentrazione</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataTrattamento"
								path="dataTrattamento" cssClass="datepicker" />
							<form:errors path="dataTrattamento" cssClass="error" />
							<label for="dataTrattamento">Data</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:textarea id="infoSupplementari" path="infoSupplementari"
								cssClass="materialize-textarea validate" rows="2"
								data-length="1000" maxlength="1000" />
							<form:errors path="infoSupplementari" cssClass="error" />
							<label for="infoSupplementari">Informazioni supplementari</label>
						</div>
					</div>
				</div>
				<div class="card-action">
					<div class="row">
            <a class="btn waves-effect back" href="#tariffa">INDIETRO</a>
            <a class="btn waves-effect forward" href="#pagamento">AVANTI</a>
						
						<button class="btn confirm waves-effect waves-light right"
							type="submit" name="datiTrattamento">SALVA</button>
					</div>
				</div>
			</form:form>
		</div>

		<div class="col s12" id="pagamento">
			<form:form id="formPagamento" action="${formAction}" method="post"
				modelAttribute="nuovaRichiestaForm" accept-charset="utf-8"
				enctype="multipart/form-data">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s12 m4 l6">
							<form:input type="text" id="mittentePagamento"
								path="mittentePagamento" cssClass="validate" maxlength="150" />
							<form:errors path="mittentePagamento" cssClass="error" />
							<label for="mittentePagamento">Mittente pagamento</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="destinatarioPagamento"
								path="destinatarioPagamento" disabled="true" />
							<label for="destinatarioPagamento">Destinatario pagamento</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="importoTotaleDovuto"
								path="importoTotaleDovuto" disabled="true" />
							<label for="importoTotaleDovuto">Importo totale dovuto</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:select id="tipoPagamento" path="idTipoPagamento"
								cssClass="validate" required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaMezziPagamento}"
									itemValue="idMezzoPagamento" itemLabel="descMezzoPagamento" />
							</form:select>
							<form:errors path="idTipoPagamento" cssClass="error" />
							<label for="tipoPagamento">Tipo pagamento *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataPagamento" path="dataPagamento"
								cssClass="datepicker" />
							<form:errors path="dataPagamento" cssClass="error" />
							<label for="dataPagamento">Data pagamento *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="numeroDocumento"
								path="numeroDocumento" cssClass="validate" required="required"
								maxlength="30" />
							<form:errors path="numeroDocumento" cssClass="error" />
							<label for="numeroDocumento">Numero documento pagamento *
							</label>
						</div>
					</div>

					<c:if test="${not empty nuovaRichiestaForm.nomeFileAllegato}">
						<spring:url
							value="/export/richieste/allegato/${nuovaRichiestaForm.idRichiesta}/${nuovaRichiestaForm.nomeFileAllegato}"
							var="pathAllegatoPagamento" />
					</c:if>

					<div class="row">
						<div class="input-field col s12 m8 l9">
							<input type="file" id="allegatoPagamento"
								name="allegatoPagamento" class="dropify"
								data-default-file="${pathAllegatoPagamento}"
								data-max-file-size="3M" data-show-remove="false" />
						</div>
					</div>
					<!--div class="row">
					   <div class="col s6 m4 l3">
					     <div class="dropzone" id="myDropzone">
					       <div class="dz-default dz-message">
					         <span class="file-icon"></span>
                   <p>Trascinare un file qui o cliccare</p>
                 </div>
					     </div>
					     <!- div class="dropzone-previews" id="allegatoPagamentoDropzone">
					       <div class="dz-default dz-message">
					         <span>Trascinare un file qui o cliccare</span>
					       </div>
					     </div->
					   </div>
					</div-->
				</div>
				<div class="card-action">
					<div class="row">
					  <a class="btn waves-effect forward" href="#trattamento">INDIETRO</a>
						<c:if test="${isSuperUser}">
						<a class="btn waves-effect forward" href="#esegui">AVANTI</a>
						</c:if>

						<button class="btn confirm waves-effect waves-light right"
							type="submit" name="datiPagamento">SALVA</button>
					</div>
				</div>
			</form:form>
		</div>
		
    <c:if test="${isSuperUser}">
      <div class="col s12" id="esegui">
        <form:form id="formEsegui" action="${formAction}" method="post"
          modelAttribute="nuovaRichiestaForm" accept-charset="utf-8">
          <div class="card-content">

            <div class="row">
              <div class="input-field col s6 m4 l3">
                <c:if test="${nuovaRichiestaForm.idTipoRichiesta == idTipoExport}">
                  <input type='text' id="tipoCertificato" value="Export" disabled="disabled" />
                </c:if>
                <c:if test="${nuovaRichiestaForm.idTipoRichiesta == idTipoRiexport}">
                  <input type='text' id="tipoCertificato" value="Riexport" disabled="disabled" />
                </c:if>
                <label for="tipoCertificato" class="bold">Tipo certificato</label>
              </div>
              <div class="input-field col s6 m8 l9">
                <c:choose>
                  <c:when
                    test="${not empty nuovaRichiestaForm.idCertificatoRichiesta 
                   and nuovaRichiestaForm.idStatoRichiesta != statoInBozza
                   and nuovaRichiestaForm.idStatoRichiesta != statoInoltrata
                   and nuovaRichiestaForm.idStatoRichiesta != statoRestituita}">
                    <a
                      href='<spring:url value="/export/report/esporta/certificatoexport/${nuovaRichiestaForm.idRichiesta}"/>'
                      class="btn green waves-effect waves-light right">STAMPA
                      CERTIFICATO</a>
                  </c:when>
                  <c:otherwise>
                    <button class="btn green right" type="button"
                      disabled="disabled">STAMPA CERTIFICATO</button>
                  </c:otherwise>
                </c:choose>
              </div>
            </div>
            <c:if test="${nuovaRichiestaForm.idTipoRichiesta == idTipoRiexport}">
            <div class="row">
              <div class="card-panel">
                <p class="center-align" style="font-size:12px;margin-bottom:5px;">
                  <em><b>Si certifica</b> che i vegetali sono stati importati in Italia con le 
                  seguenti caratteristiche:</em></p>
                <div class="row">
                  <div class="input-field col s6 m6 l3">
			              <form:select id="tipoCertificatoPrecedente" path="tipoCertificatoPrecedente">
			                <form:options items="${listaTipiCertificatoPrecedente}" itemValue="id"
			                  itemLabel="descrizione" />
			              </form:select>
			              <label for="tipoCertificatoPrecedente">Tipo Certificato precedente</label>
                  </div>
                  <div class="input-field col s6 m6 l3">
                    <form:select id="merceColliOriginali" path="idMerceColliOriginali">
                      <form:option value="" label="Selezionare" />
                      <form:options items="${listaTipiImballaggi}" itemValue="idTipoImballaggio"
                        itemLabel="descTipoImballaggio" />
                    </form:select>
                    <label for="merceColliOriginali">Merce nei colli originali</label>
                  </div>
                  <div class="input-field col s4 m4 l2 valign-wrapper" style="min-height:100px;">
                    <div class="right-align-inner">
                      <em>e inoltre, sulla base:</em>
                    </div>
                  </div>
                  <div class="col s8 m8 l4">
                    <div class="row">
                      <div class="col s12">
                        <label>
	                        <form:checkbox path="certificatoFitoOriginale" id="certificatoFitoOriginale"
	                          cssClass="centered" />
	                        <span>del certificato fitosanitario originale</span>
                        </label>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col s12">
                        <label>
	                        <form:checkbox path="ispezioneSupplementare" id="ispezioneSupplementare"
	                          cssClass="centered" />
	                        <span>e di un'ispezione supplementare</span>
                        </label>
                      </div>
                    </div>
                  </div>
                </div>
                <p class="center-align" style="font-size:12px;"><em>
                  Sono considerati conformi alle disposizioni fitosanitarie in vigore nella parte 
                  contraente importatrice e che durante il periodo di immagazzinamento in Italia
                  la partita non è stata esposta a rischi di infestazione o d'infezione</em></p>
              </div>
            </div>
            </c:if>
            <div class="row">
              <div class="input-field col s6 m4 l3">
	              <form:select id="provinciaEsecuzione" path="idProvinciaEsecuzione"
	                cssClass="validate" required="required">
	                <form:option value="" label="Selezionare" />
	                <form:options items="${listaProvinceRegione}" itemValue="idProvincia"
	                  itemLabel="denomProvincia" />
	              </form:select>
	              <form:errors path="idProvinciaEsecuzione" cssClass="error" />
	              <label for="provinciaEsecuzione">Provincia esecuzione *</label>
	            </div>
	            <div class="input-field col s6 m4 l3">
	              <form:select id="comuneEsecuzione" path="idComuneEsecuzione"
	                cssClass="validate" required="required">
	                <form:option value="" label="Selezionare" />
	                <form:options items="${listaComuniEsecuzione}" itemValue="idComune"
	                  itemLabel="denomComune" />
	              </form:select>
	              <form:errors path="idComuneEsecuzione" cssClass="error" />
	              <label for="comuneEsecuzione">Comune esecuzione *</label>
	            </div>
            
              <div class="input-field col s6 m4 l3">
                <form:input type='text' id="dataEsecuzione"
                  path="dataEsecuzione" cssClass="datepicker_birthdate" />
                <form:errors path="dataEsecuzione" cssClass="error" />
                <label for="dataEsecuzione">Data esecuzione e rilascio certificato *</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:select id="ispettore" path="idIspettore"
                  cssClass="validate" required="required">
                  <form:option value="" label="Selezionare" />
                  <form:options items="${listaispettori}" itemValue="idIspettore"
                    itemLabel="denominazioneIspettore" />
                </form:select>
                <form:errors path="idIspettore" cssClass="error" />
                <label for="ispettore">Ispettore che rilascia il
                  certificato *</label>
              </div>
              <div class="input-field col s12 m4 l6">
                <form:select id="idIspettoreFirmatario"
                  path="idIspettoreFirmatario" cssClass="validate"
                  required="required">
                  <form:option value="" label="Selezionare" />
                  <form:options items="${listaispettori}" itemValue="idIspettore"
                    itemLabel="denominazioneIspettore" />
                </form:select>
                <form:errors path="idIspettoreFirmatario" cssClass="error" />
                <label for="idIspettoreFirmatario">Ispettore firmatario
                  *</label>
              </div>
              <div id="bloccoNumeroCertificatoManuale"
                class="input-field col s6 m4 l3">
                <form:input type="text" id="numeroCertificatoManuale"
                  path="numeroCertificatoManuale" cssClass="validate"
                  maxlength="7" />
                <form:errors path="numeroCertificatoManuale" cssClass="error" />
                <label for="numeroCertificatoManuale">Numero certificato
                  *</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12 m8 l6">
                <form:textarea id="dichiarazioneSupplementare"
                  path="dichiarazioneSupplementare"
                  cssClass="materialize-textarea validate" rows="2"
                  data-length="1000" maxlength="1000" />
                <form:errors path="dichiarazioneSupplementare" cssClass="error" />
                <label for="dichiarazioneSupplementare">Dichiarazioni
                  supplementari</label>
              </div>
            </div>
          </div>
          <div class="card-action">
            <div class="row">
              <a class="btn waves-effect back" href="#pagamento">INDIETRO</a>
              
              <div class="right" style="text-align: right">
                <c:if
                  test="${not empty nuovaRichiestaForm.idCertificatoRichiesta and nuovaRichiestaForm.idStatoRichiesta == statoInoltrata}">
                  <a
                    href='<spring:url value="/export/richieste/esegui/${nuovaRichiestaForm.idRichiesta}"/>'
                    title="Esegui" class="btn green waves-effect waves-light"
                    style="display: inline-block"
                    onclick="return checkEsegui(this)">ESEGUI</a>
                </c:if>

                <button class="btn confirm waves-effect waves-light"
                  type="submit" name="datiEsegui" style="display: inline-block">SALVA</button>
              </div>

            </div>
          </div>
        </form:form>
      </div>
    </c:if>

	</div>

	<div class="col s12 left-align">
		<a href='<spring:url value="/export/richieste/elenco"/>'
			class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
	</div>
	<br />
	<!-- /div-->

	<content tag="script"> 
		<script type="text/javascript"
			src='<spring:url value="/resources/js/dropify.min.js"/>'></script>
		<script type="text/javascript"
      src='<spring:url value="/resources/js/dropzone.js"/>'></script> 
		
		<script>
			var numeroMerci = $('#numeroMerci').val();

			$(document).ready(function() {
					if (numeroMerci == 0) {
						$('#tabellaMerci').hide();
					}

					$('.breadcrumb').click(function(e) {
							if (!$(this).attr('href')) {
								return;
							}

							var current = $(this).data('value');

							$('#' + current).show();

							$(this).css('color', '#fff');

							$('.breadcrumb').each(function(index) {
								if ($(this).data('value') != current) {
									$('#' + $(this).data('value')).hide();

									$(this).css('color', 'rgba(255,255,255,0.7)');
								} else {
									$("#schedaSelezionata").val(index + 1);
								}
							});

							$('#small_label').text(
									$(this).text());

							//Evita di far scrollare la pagina fino al div visualizzato dopo il click
							e.preventDefault();
						});

					$('.forward').click(function() {
							var last_index = $(this).attr('href').length - 1;

							$('#' + $(this).attr('href').substr(1, last_index)).show();

							var current = $(this).attr('href').substr(1, last_index);

							$('.breadcrumb').each(function(index) {
								if ($(this).attr('href').substr(1, $(this).attr('href').length - 1) != current) {
									$('#' + $(this).attr('href').substr(1, $(this).attr('href').length - 1)).hide();

									$(this).css('color', 'rgba(255,255,255,0.7)');
								} else {
									$(this).css('color', '#fff');
									$('#small_label').text($(this).text());
									$("#schedaSelezionata").val(index + 1);
								}
							});
						});

					$('.back').click(function() {
						var last_index = $(this).attr('href').length - 1;

						$('#' + $(this).attr('href').substr(1, last_index)).show();

						var current = $(this).attr('href').substr(1, last_index);

						$('.breadcrumb').each(function(index) {
							if ($(this).attr('href').substr(1, $(this).attr('href').length - 1) != current) {
								$('#' + $(this).attr('href').substr(1, $(this).attr('href').length - 1)).hide();

								$(this).css('color', 'rgba(255,255,255,0.7)');
							} else {
								$(this).css('color', '#fff');

								$('#small_label').text($(this).text());

								$("#schedaSelezionata").val(index + 1);
							}
						});
					});

					//All'apertura della pagina si visualizza la scheda impostata nel controller 
					$(".breadcrumb:nth-child(${nuovaRichiestaForm.schedaSelezionata})").click();

					//Popolamento dinamico della select
					$('#provinciaMittente').change(function() {
						var params = {
							"idProvincia" : $(this).val()
						}

						getSelectAsincrona(
								'comuneMittente',
								'<spring:url value="/ajax/getComuniProvincia" />',
								params,
								getOptionComune, 1);
					});
					
					$('#provinciaEsecuzione').change(function() {
               var params = {
                 "idProvincia" : $(this).val()
               }

               getSelectAsincrona(
             		  'comuneEsecuzione',
                   '<spring:url value="/ajax/getComuniProvincia" />',
                   params,
                   getOptionComune, 1);
             });

					var validator = null;

					//Reperimento dell'unità di misura in base al prodotto
					$('#idProdotto').change(function() {
						  var idProdotto = $(this).val();

							var params = {
								"idProdotto" : idProdotto
							}

							setValoreAsincrono(
									'unitaMisuraMerce',
									'<spring:url value="/ajax/getUnitaMisuraProdotto" />',
									params,
									toStringUnitaMisura,
									idProdotto.length == 0,
									checkUnitaMisura);
							
							if (validator) {
								validator.form();
							}
							
							/*
							 * Il check del prodotto viene fatto dopo la validazione del form per evitare che 
							 * campi appena resi visibili siano immediatamente validati 
							 */
							checkProdotto();
					});

					$("#nazioneOrigine").change(function() {
						if (validator) {
							validator.form();
						}
					});

					$("#genereMerce").change(function() {
						if (validator) {
							validator.form();
						}
					});

					$('#aggiungiMerce').click(function() {
							/*
							  Reset del campo hidden del genere merce, se l'utente ha modificato il campo di testo Genere senza selezionare
							  un valore dell'autocomplete, l'hidden vale 0 e viene quindi svuotato per dare messaggio di errore nella validazione
							 */
							if ($('#idGenereMerce').val() == '0') {
								$('#idGenereMerce').val('');
							}

							validator = $("#formMerce").validate({
								  rules : {
										nazioneOrigine : {
											required : true
										},
										tipoProdotto : {
											required : true
										},
										idGenereMerce : {
											required: function(element) {
	                      return !isAltroProdotto();
	                    }
										},
										quantitaMerce : {
											required : true,
											fourDecimals : true,
											minStrict: 0,
											max: 999999999
										},
										quantitaLordaMerce : {
											required : false,
											fourDecimals : true,
											minStrict: 0,
											max: 999999999
										},
										numeroPezzi : {
											required : function(element){
												return $('#numeroPezzi').is(':visible') && !isAltroProdotto();
									    },
											digits : true
										},
										idNaturaCollo : {
											required : true
										},
										numeroColli : {
											required : function(element) {
												  return isAltroProdotto();
											},
                      digits : true
                    },
									},
									messages : {
										idGenereMerce : "Selezionare un genere",
									}
							});
							
							validator.form();

							if ($("#formMerce").valid()) {
								/*
								 * Si controlla che non si inserisca una merce con stesso genere (ed eventualmente specie) già presente in tabella
								 */
								$idProdotto = $('#idProdotto').val();
								$descProdotto = $('#idProdotto').find(":selected").text();
								$idNazioneOrigine = $('#nazioneOrigine').val();
								$descNazioneOrigine = $('#nazioneOrigine').find(":selected").text();
								$idGenere = $('#idGenereMerce').val();
								$descGenere = $('#genereMerce').val();
								$idSpecie = null;

								$selectedOption = $('#specieMerce').find(":selected");

								if ($selectedOption) {
									$idSpecie = $selectedOption.val();
								}

								var preventInsert = false;
								var regExpHiddenGenere = /listaMerceRichiesta\[(.*?)\]\.idGenere/g;

								$('input[type=hidden]').filter(function() {
									  return this.name.match(regExpHiddenGenere);
								}).each(function(index) {
									/*
									 *  E' importante resettare il lastIndex della regex a ogni iterazione dato che l'oggetto è stateful e mantiene 
									 *  il lastIndex di exec() precedenti
									 */
									regExpHiddenGenere.lastIndex = 0;

									if ($(this).val() == $idGenere) {
										var match = regExpHiddenGenere.exec($(this).attr('name'));
										var indice = match[1];

										if ($idProdotto == $('input[name="listaMerceRichiesta[' + indice + '].idProdotto"]').val() && 
												$idSpecie == $('input[name="listaMerceRichiesta[' + indice + '].idSpecie"]').val() &&
												$idNazioneOrigine == $('input[name="listaMerceRichiesta[' + indice + '].idNazioneOrigine"]').val()) {
											preventInsert = true;

											var html_str = 
												'Nella tabella delle merci è già presente il prodotto <b>'
												+ $descProdotto 
												+ '</b> dalla nazione <b>'   
												+ $descNazioneOrigine
												+ '</b> con il genere <b>'
												+ $descGenere
												+ '</b>';

											if ($idSpecie) {
												html_str = html_str
												+ '</b> e con la specie <b>'
												+ $selectedOption.text()
												+ '</b>';
											}
						                      
											swal({
												title : 'Attenzione!',
												html : html_str,
												type : 'warning',
											});

											return;
										}
									}
								});

								if (preventInsert) {
									return false;
								}

								/*
								 * Fine controllo inserimento merci con stesso genere
								 */

								var hiddenTemplate = '<input type="hidden" id="NOME_CAMPO" name="listaMerceRichiesta['+numeroMerci+'].NOME_CAMPO" value="" />';

								$riga = $('<tr></tr>');

								//Stato
								$cella = $('<td></td>');

								$selectedOption = $('#nazioneOrigine').find(":selected");

								$hiddenField = $(hiddenTemplate.replace(/NOME_CAMPO/g, 'descNazioneOrigine'));

								if ($selectedOption.val()) {
									$cella.text($selectedOption.text());
									$hiddenField.val($selectedOption.text());
								}

								$cella.append($hiddenField);

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'idNazioneOrigine')).val($selectedOption.val()).appendTo($cella);

								$cella.append($(hiddenTemplate.replace(/NOME_CAMPO/g, 'idMerceRichiesta')));

								$cella.appendTo($riga);

								//Prodotto
								$cella = $('<td></td>');
								
								$selectedOption = $('#idProdotto').find(":selected");

								$selectedOption = $('#idProdotto').find(":selected");

								$hiddenField = $(hiddenTemplate.replace(/NOME_CAMPO/g, 'descProdotto'));

								if ($selectedOption.val()) {
									$cella.text($selectedOption.text());
									$hiddenField.val($selectedOption.text());
								}

								$cella.append($hiddenField);

								$cella.append($(hiddenTemplate.replace(/NOME_CAMPO/g, 'idProdotto')).val($selectedOption.val()));

								$cella.appendTo($riga);

								//Genere
								$cella = $('<td></td>');
								$testoCorsivo = $('<i></i>');

								$idGenere = $('#idGenereMerce').val();
								$descGenere = $('#genereMerce').val();

								//$selectedOption = $('#genereMerce').find(":selected");

								$hiddenField = $(hiddenTemplate.replace(/NOME_CAMPO/g, 'descGenere'));

								$testoCorsivo.text($descGenere);
								$hiddenField.val($descGenere);

								/*if ($selectedOption.val()) {
									$cella.text($selectedOption.text());
									$hiddenField.val($selectedOption.text());
								}*/

								$cella.append($testoCorsivo);
								$cella.append($hiddenField);

								//$cella.append($(hiddenTemplate.replace(/NOME_CAMPO/g, 'idGenere')).val($selectedOption.val()));
								$cella.append($(hiddenTemplate.replace(/NOME_CAMPO/g, 'idGenere')).val($idGenere));

								$cella.appendTo($riga);

								//Specie
								$cella = $('<td></td>');
								$testoCorsivo = $('<i></i>');

								$selectedOption = $('#specieMerce').find(":selected");

								$hiddenField = $(hiddenTemplate.replace(/NOME_CAMPO/g, 'descSpecie'));

								if ($selectedOption.val()) {
									$testoCorsivo.text($selectedOption.text().toLowerCase());
									$hiddenField.val($selectedOption.text());
								}

								$cella.append($testoCorsivo);
								$cella.append($hiddenField);

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'idSpecie')).val($selectedOption.val()).appendTo($cella);

								$cella.appendTo($riga);

								//Unità misura
								textValue = $('#unitaMisuraMerce').val();

								$cella = $('<td></td>').text(textValue);

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'descUnitaMisura')).val(textValue).appendTo($cella);

								$cella.appendTo($riga);
								
								//Numero pezzi
                textValue = $('#numeroPezzi').val();

                $cella = $('<td></td>').text(textValue);

                $(hiddenTemplate.replace(/NOME_CAMPO/g, 'numeroPezzi')).val(textValue).appendTo($cella);

                $cella.appendTo($riga);

								//Quantità Lorda
								textValue = $('#quantitaLordaMerce').val();

								$cella = $('<td></td>').text(textValue);

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'quantitaLordaProdotto')).val(textValue).appendTo($cella);

								$cella.appendTo($riga);

								//Quantità
								textValue = $('#quantitaMerce').val();

								$cella = $('<td></td>').text(textValue);

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'quantita')).val(textValue).appendTo($cella);

								$cella.appendTo($riga);

								//Natura Colli
								$cella = $('<td></td>');

								$selectedOption = $('#naturaCollo').find(":selected");

								$hiddenField = $(hiddenTemplate.replace(/NOME_CAMPO/g, 'descNaturaCollo'));

								if ($selectedOption.val()) {
									$cella.text($selectedOption.text());
									$hiddenField.val($selectedOption.text());
								}

								$cella.append($hiddenField);

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'idNaturaCollo')).val($selectedOption.val()).appendTo($cella);

								$cella.appendTo($riga);
								
								//Numero Colli
								
								textValue = $('#numeroColli').val();

								$cella = $('<td></td>').text(textValue);

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'numeroColli')).val(textValue).appendTo($cella);

								$cella.appendTo($riga);
								
								//Marchio

								textValue = $('#marchio').val();

								$cella = $('<td></td>').text(textValue);

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'marchio')).val(textValue).appendTo($cella);

								$cella.appendTo($riga);

								//Azioni
								$riga.append($('#rigaTemplate > td').filter(':last').clone());

								$('#bodyTabellaMerci').prepend($riga);

								$('#tabellaMerci').show();

								numeroMerci++;
							}

							//validator.destroy();
						});

				$('#salvaDatiMerce').click(function(event) {
					//Si controolla che l'utente abbia inserito almeno una merce prima di effettuare il submit
					if ($('#tabellaMerci tr').length <= 2) {
						event.preventDefault();
						swal({
							title : 'Aggiungere almeno una merce',
							type : 'warning',
						});
					}
				});
	
				$('#trattamento').change(function() {
					checkInputTrattamento();
				});
	
				checkInputTrattamento();
				checkProdotto();
	
				$('#genereMerce').on('input', function(event) {
					popolaListaGeneri($(this));
					$('#idGenereMerce').val('0');
				});
				
				$('#genereMerce').autocomplete({
					limit: 50,
					minLength: 2,
					onAutocomplete: function(value) {
						caricaSpecieGenere();
	        }
				});
	
				$('#allegatoCertificato').dropify({
					messages: {
						'default': 'Aggiungi Allegato Certificato  -  Trascinare un file qui o cliccare',
						'replace': 'Sostituisci Allegato Certificato  -  Trascinare un file qui o cliccare per sostituire',
						'remove':  'Rimuovere',
						 error: {
							 'fileSize': 'La dimensione del file è superiore al limite (massimo {{ value }}).',
							 /*'minWidth': 'The image width is too small ({{ value }}}px min).',
					     'maxWidth': 'The image width is too big ({{ value }}}px max).',
					     'minHeight': 'The image height is too small ({{ value }}}px min).',
					     'maxHeight': 'The image height is too big ({{ value }}px max).',
					     'imageFormat': 'The image format is not allowed ({{ value }} only).'*/
					   }
				  }
				});
	
				$('#allegatoPagamento').dropify({
					messages : {
						'default' : 'Trascinare un file qui o cliccare',
						'replace' : 'Trascinare un file qui o cliccare per sostituire',
						'remove' : 'Rimuovere',
						error : {
							'fileSize' : 'La dimensione del file è superiore al limite (massimo {{ value }}).',
						/*'minWidth': 'The image width is too small ({{ value }}}px min).',
						'maxWidth': 'The image width is too big ({{ value }}}px max).',
						'minHeight': 'The image height is too small ({{ value }}}px min).',
						'maxHeight': 'The image height is too big ({{ value }}px max).',
						'imageFormat': 'The image format is not allowed ({{ value }} only).'*/
						}
					}
				});
			
				<c:if test="${nuovaRichiestaForm.visualizzaModalInoltra}">
        checkInoltra();
        </c:if>

			});
			
			Dropzone.options.myDropzone = {
	      url: '${formAction}',
	      paramName: "allegatoPagamento",
	      //headers: 'X-CSRF-Token': $('meta[name="csrf-token"]').attr('content'),
	      //dictDefaultMessage: 'Trascinare un file qui o cliccare',
	      maxFilesize: 3, //MB 
	      dictFileTooBig: 'La dimensione del file è superiore al limite (massimo {{maxFilesize}})',
	      autoProcessQueue: false,
	      uploadMultiple: false,
	      maxFiles: 1,
	      addRemoveLinks: true,
	      dictRemoveFile: 'Rimuovere',
	      /*previewsContainer: '.dropzone-previews',
	      clickable: '.dropzone-previews',*/
	      
	      // The setting up of the dropzone
	      init: function() {
	        console.log('IN INIT DROPZONE PAGAMENTO!!!!!!!');
	        var myDropzone = this;
	        var submitName = null;
	        
	        // First change the button to actually tell Dropzone to process the queue.
	        $(this.element).closest('form').find("button[type=submit]").on("click", function(e) {
	        	//Controllo se alla dropzone sia allegato un file (se ci fosse più di una dropzone 
	        	//nella form, questo codice non andrebbe bene, occorrerebbe gestire l'evento del submit 
	        	//in una funzione esterna)
	        	if (myDropzone.files && myDropzone.files.length) {
		          // Make sure that the form isn't actually being sent.
		          e.preventDefault();
		          e.stopPropagation();
		          submitName = this.name;
		          console.log('NOME SUBMIT: '+submitName);
		          myDropzone.processQueue();
	        	}else {
	        		submitName = null;
	        	}
	        });
	
	        this.on("addedfile", function(file) {
	        	if (this.files.length > 1) {
	        		  this.removeFile(this.files[0]);
	        	}
	        	
	        	$(file.previewElement).find('.dz-progress').css('display', 'none');	  
	        });

	        // Listen to the sendingmultiple event. In this case, it's the sendingmultiple event instead
	        // of the sending event because uploadMultiple is set to true.
	        this.on("sending", function(data, xhr, formData) {
	          // Gets triggered when the form is actually being sent.
	          // Hide the success button or the complete form.
	          $(this.element).closest('form').find("input, textarea, select").each(function() {
	        	  console.log('CAMPO ' + this.id + " CON NOME "+this.name+" E CON VALORE " + $(this).val());
	        	  
	        	  if (this.name) {
	        		  formData.append(this.name, $(this).val());
	        	  }
	          });
	          
	          if (submitName) {
	        	  formData.append(submitName, submitName);
	          }
	          
	          //formData.append("Username", $("#Username").val());
	          console.log('ADESSO STO MANDANDO FILE AL SERVER!!!!!!!!!!!!!!');
	        });
	        this.on("success", function(files, response) {
	          // Gets triggered when the files have successfully been sent.
	          // Redirect user or notify of success.
	          console.log('DATI RISPOSTA: '+response);
	          $("html").replaceWith(response);
	          /*$.each(response, function(key, element) {
	        	  console.log('key: ' + key + '\n' + 'value: ' + element);
	        	});*/
	        });
	        this.on("error", function(files, response) {
	          // Gets triggered when there was an error sending the files.
	          // Maybe show form again, and notify user of error
	        	console.log('DATI RISPOSTA ERRORE: '+response.data);
	        });
	      }
	    }

			function getOptionComune(comune) {
				return getOption(comune.idComune, comune.denomComune);
			}

			function toStringUnitaMisura(unitaMisura) {
				return unitaMisura.descUnitaMisura;
			}
			
			function checkUnitaMisura() {
				console.log('CHECK UNITA MISURA!!! '+$('#unitaMisuraMerce').val());
				console.log('VALORE RICERCA: '+$('#unitaMisuraMerce').val().search(/Numero/i));
				if ($('#unitaMisuraMerce').val().search(/Numero/i) >= 0) {
					  console.log('E\' un numero!!!');    
					  if ($('#quantitaLordaMerce').is(':visible')) {
						  $('#quantitaLordaMerce').closest('div').hide();
						  $('#quantitaLordaMerce').val('');
						  $('#labelQuantitaNetta').hide();
					  }
					  
					  if ($('#numeroPezzi').is(':hidden')) {
						  $('#numeroPezzi').closest('div').show();
						  $('#labelQuantitaNettaNumero').show();
					  }
				}else {
					console.log('Non è un numero!!!');
					if ($('#quantitaLordaMerce').is(':hidden')) {
            $('#quantitaLordaMerce').closest('div').show();
            $('#labelQuantitaNetta').show();
          }
          
          if ($('#numeroPezzi').is(':visible')) {
            $('#numeroPezzi').closest('div').hide();
            $('#numeroPezzi').val('');
            $('#labelQuantitaNettaNumero').hide();
          }
				}            
			}
			
			function checkProdotto() {
				var idProdotto = $('#idProdotto').find(":selected").val();
				
				/*if ($('#idProdotto').find(":selected").text().match(/ALTRO/i)) {*/
			  /*if (idProdotto == '<spring:eval expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_PRODOTTO_MACCHINARI_TERRA" />'
						|| idProdotto == '<spring:eval expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_PRODOTTO_SUBSTRATI_COLTURALI" />'
						|| idProdotto == '<spring:eval expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_PRODOTTO_ALTRO" />') {*/
				if (isAltroProdotto()) {
        	$('#labelNumeroPezzi').show();
        	$('#labelNumeroPezziObb').hide();
        	$('#labelNumeroColli').hide();
        	$('#labelNumeroColliObb').show();
        	$('#genereMerce').closest('div.col').hide();
        	$('#genereMerce').val('');
        	$('#idGenereMerce').val('');
        	$('#specieMerce').closest('div.col').hide();
        	$("#specieMerce")[0].selectedIndex = 0;
        	$("#specieMerce").formSelect();
        }else {
        	$('#labelNumeroPezzi').hide();
        	$('#labelNumeroPezziObb').show();
        	$('#labelNumeroColli').show();
        	$('#labelNumeroColliObb').hide();
        	$('#genereMerce').closest('div.col').show();
          $('#genereMerce').closest('div.col').find('div.error').remove();
        	$('#specieMerce').closest('div.col').show();
        }        
      }
				
			function isAltroProdotto() {
				var idProdotto = $('#idProdotto').find(":selected").val();
				
				return idProdotto == '<spring:eval expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_PRODOTTO_MACCHINARI_TERRA" />'
					  || idProdotto == '<spring:eval expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_PRODOTTO_SUBSTRATI_COLTURALI" />'
					  || idProdotto == '<spring:eval expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_PRODOTTO_ALTRO" />'
			}

			function eliminaRiga(element) {
				$(element).closest('tr').remove();

				//Nel controllo si considerano anche la riga di intestazione e quella nascosta di template
				if ($('#tabellaMerci tr').length <= 2) {
					$('#tabellaMerci').hide();
				}
			}

			function checkInputTrattamento() {
				/*
				   Devo controllare che l'id sia valorizzato nei campi di input per escludere gli input generati da Materialize 
				   per il rendering delle Select
				 */
				if ($('#trattamento').find(":selected").val()) {
					$('#formTrattamento input[type=text], #formTrattamento textarea')
							.each(function(index) {
								if ($(this).attr('id')) {
									$(this).attr('disabled', false);
								}
							});
				} else {
					$('#formTrattamento input[type=text], #formTrattamento textarea')
							.each(function(index) {
						if ($(this).attr('id')) {
							$(this).attr('disabled', true).val('');
						}
					});
				}
			}
			
			function popolaListaGeneri($input) {
        $input.autocomplete("close");
        
        if ($input.val().length > 1) {
          var params = {
                "nomeGenere" : $input.val()
          };
          
          $.getJSON(
              '<spring:url value="/ajax/getListaGeneri" />',
              params,
              function(responseJSON) {
                $input.autocomplete("updateData", responseJSON);                
                $input.autocomplete("open");
              }
          ).fail(function(jqxhr, textStatus,error) {
              if (window.console) {
                console.log("Errore in reperimento valore asincrono: " + textStatus + ", " + error);
              }
          });         
        }
      }

			function caricaSpecieGenere() {
				var params = {
					"denomGenere" : $("#genereMerce").val()
				}

				setValoreAsincrono(
	        'idGenereMerce',
	        '<spring:url value="/ajax/getDatiDenomGenere" />',
	        params,
	        toStringIdGenere);
			
				var params2 = {
		    	        "denomGenere" : $("#genereMerce").val()
		    	        , "idSpecieSel" : null
		    	}
				
				getSelectAsincrona('specieMerce',
						'<spring:url value="/ajax/getListaSpecieDenomGenere" />',
						params2, getOptionSpecie, 1);
			}

			function getOptionSpecie(specie) {
				return getOption(specie.idSpecie, specie.denomSpecie);
			}
			
			function toStringIdGenere(genere) {
          return genere.idGenere;
      }
			
			function checkInoltra() {
        swal({
            title : 'I dati sono stati salvati',
            html : 'Si desidera inoltrare la richiesta?',
            type : 'question',
            showCancelButton : true,
            /*confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',*/
            confirmButtonText : 'INOLTRA',
            confirmButtonClass: 'btn amber darken-1 waves-effect waves-light',
            cancelButtonText : 'ANNULLA',
            cancelButtonClass: 'btn',
            focusConfirm: false,
            buttonsStyling: false,
        }).then(function(result) {
            if (result.value) {
                window.location.href = '<spring:url value="/export/richieste/inoltra/${nuovaRichiestaForm.idRichiesta}"/>';
            }
        });
  
        return false;
      }
		</script> </content>

</body>
</html>