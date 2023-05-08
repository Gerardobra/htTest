<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="it.aizoon.ersaf.util.CaronteConstants"%>
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

<link type="text/css" rel="stylesheet"
	href="<spring:url value="/resources/css/dropify.min.css"/>"
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
			<spring:url value="/import/richieste/modifica" var="formAction" />
			<form:form action="${formAction}" method="post"
				modelAttribute="nuovaRichiestaForm" accept-charset="utf-8"
				enctype="multipart/form-data">
				<div class="card-content">
					<div class="card-panel hoverable">Si sta creando una nuova
						richiesta di rilascio di nullaosta fitosanitario di importazione
						per merce in ingresso in Italia da Paesi Terzi, vincolato
						all’esito positivo dell’ispezione e del controllo fitosanitario da
						parte degli Ispettori Fitosanitari. Per confermare e procedere con
						la richiesta utilizzare il bottone «SALVA», in caso contrario
						utilizzare «TORNA ALLA RICERCA».</div>
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="statoRichiesta"
								path="descStatoRichiesta" disabled="true" />
							<label for="statoRichiesta">Stato richiesta</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="codiceRichiesta" path="codRichiesta"
								disabled="true" />
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
						<div class="input-field col s12 m8 l6">
							<form:textarea id="note" path="note"
								cssClass="materialize-textarea validate tooltipped" rows="2"
								data-length="1000" maxlength="1000" data-position="right"
								data-tooltip="Note per l'Ente (ad esempio informazioni di contatto)" />
							<form:errors path="note" cssClass="error" />
							<label for="note">Note</label>
						</div>
					</div>
				</div>
				
				<div class="card-action">
					<div class="row">
						<a class="btn waves-effect forward left" href="#mittente">AVANTI</a>

						<div class="right" style="text-align: right">
							<c:if
								test="${nuovaRichiestaForm.idStatoRichiesta != statoInBozza}">
								<a
									href='<spring:url value="/import/richieste/annulla/${nuovaRichiestaForm.idRichiesta}"/>'
									title="Annulla"
									class="btn red darken-4 waves-effect waves-light"
									style="display: inline-block"
									onclick="return checkAnnulla(this)">ANNULLA<i
									class="material-icons right">clear</i></a>
							</c:if>

							<c:if
								test="${isSuperUser and nuovaRichiestaForm.idStatoRichiesta == statoInoltrata}">
								<a
									href='<spring:url value="/import/richieste/restituisci/${nuovaRichiestaForm.idRichiesta}"/>'
									title="Restituisci"
									class="btn red darken-4 waves-effect waves-light"
									style="display: inline-block"
									onclick="return checkRestituisci(this)">RESTITUISCI<i
									class="material-icons right">arrow_back</i></a>
							</c:if>

							<c:if test="${nuovaRichiestaForm.abilitaInoltra}">
								<a
									href='<spring:url value="/import/richieste/inoltra/${nuovaRichiestaForm.idRichiesta}"/>'
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
								path="indirizzoMittente" cssClass="validate" maxlength="200" />
							<form:errors path="indirizzoMittente" cssClass="error" />
							<label for="indirizzoMittente">Indirizzo (Es. Via Roma, 24)</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="comuneMittente" path="comuneMittente"
								cssClass="validate" required="required" maxlength="100" />
							<form:errors path="comuneMittente" cssClass="error" />
							<label for="comuneMittente">Città *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:select id="nazioneMittente" path="idNazioneMittente"
								cssClass="validate" required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaNazioni}" itemValue="idNazione"
									itemLabel="denomNazione" />
							</form:select>
							<label for="nazioneMittente">Stato *</label>
							<form:errors path="idNazioneMittente" cssClass="error" />
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
							<form:select id="provinciaDestinatario" path="idProvincia"
								cssClass="validate" required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaProvince}" itemValue="idProvincia"
									itemLabel="denomProvincia" />
							</form:select>
							<form:errors path="idProvincia" cssClass="error" />
							<label for="provinciaDestinatario">Provincia *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:select id="comuneDestinatario" path="idComuneIta"
								cssClass="validate" required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaComuni}" itemValue="idComune"
									itemLabel="denomComune" />
							</form:select>
							<form:errors path="idComuneIta" cssClass="error" />
							<label for="comuneDestinatario">Comune *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="codiceRup" path="codiceRup"
								cssClass="validate" maxlength="20" />
							<form:errors path="codiceRup" cssClass="error" />
							<label for="codiceRup">Codice RUP</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:select id="regioneRup" path="idRegioneRup"
								cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaRegioni}" itemValue="idRegione"
									itemLabel="denomRegione" />
							</form:select>
							<form:errors path="idRegioneRup" cssClass="error" />
							<label for="regioneRup">Regione RUP</label>
						</div>
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
						<div class="input-field col s6 m4 l6">
							<form:input type="text" id="numDocumentoTrasporto"
								path="numDocumentoTrasporto" cssClass="validate"
								required="required" maxlength="30" />
							<form:errors path="numDocumentoTrasporto" cssClass="error" />
							<label for="numDocumentoTrasporto">N. Documento di
								trasporto *</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:select id="mwzzoTrasporto" path="idModoTrasporto"
								cssClass="validate" required="required">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaModiTrasporto}"
									itemValue="idModoTrasporto" itemLabel="descModoTrasporto" />
							</form:select>
							<form:errors path="idModoTrasporto" cssClass="error" />
							<label for="mwzzoTrasporto">Mezzo di trasporto dichiarato
								*</label>
						</div>
						<div class="input-field col s6 m4 l3">
              <form:select id="puntoEntrataDichiarato"
                path="idPuntoEntrataDichiarato" cssClass="validate"
                required="required">
                <form:option value="" label="Selezionare" />
                <form:options items="${listaPuntiEntrata}"
                  itemValue="idUfficioDoganale" itemLabel="denomUfficioDoganale" />
              </form:select>
              <form:errors path="idPuntoEntrataDichiarato" cssClass="error" />
              <label for="puntoEntrataDichiarato">Punto d'entrata dichiarato *</label>
            </div>
						<div class="col s6 m4 l3">
						  <label>
								<form:checkbox path="spedizioneMultipla" id="spedizioneMultipla"
									cssClass="left centered" />
								<span>Spedizione multipla</span>
							</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="numCertificatiCollegati"
								path="numCertificatiCollegati" cssClass="validate"
								required="required" maxlength="2" />
							<form:errors path="numCertificatiCollegati" cssClass="error" />
							<label for="numCertificatiCollegati">Numero certificati
								collegati *</label>
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
							<select id="nazioneOrigine" name="nazioneOrigine"
								data-error="#errorTxt1">
								<option value="">Selezionare</option>
								<c:forEach var="item" items="${listaNazioni}">
									<option value="${item.idNazione}">${item.denomNazione}</option>
								</c:forEach>
							</select> <label for="nazioneOrigine">Stato d'origine *</label>
							<div id="errorTxt1" style="height: 0px"></div>
						</div>
						<div class="input-field col s6 m4 l3">
							<select id="idProdotto" name="idProdotto" data-error="#errorTxt2">
                <option value="">Selezionare</option>
                <c:forEach var="item" items="${listaProdotti}">
                  <option value="${item.idProdotto}" data-classe="${item.idClasse}">${item.denomProdotto}</option>
                </c:forEach>
              </select> 
              <label for="idProdotto">Prodotto *</label>
              <div id="errorTxt2" style="height: 0px"></div>
						</div>
						<div class="input-field col s6 m4 l3">
              <input type="text" id="genereMerce" name="genereMerce"
                placeholder="Indicare generi più rappresentativi" data-error="#errorTxt3"
                class="autocomplete" autocomplete="off"> 
							<label class="active" for="genereMerce">Genere *</label>
							<div id="errorTxt3" style="height: 0px"></div>
							<input type="hidden" id="idGenereMerce" name="idGenereMerce" data-error="#errorTxt3" />
						</div>
						<div class="input-field col s6 m4 l3">
							<select id="specieMerce" name="specieMerce"
								data-error="#errorTxt4">
								<option value="">Selezionare</option>
							</select> <label for="specieMerce">Specie</label>
							<div id="errorTxt4" style="height: 0px"></div>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="unitaMisuraMerce" path=""
								disabled="true" />
							<label for="unitaMisuraMerce">Unità di misura</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="quantitaMerce" name="quantitaMerce"
								maxlength="10" /> <label for="quantitaMerce">Quantità *</label>
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
									<th>Stato</th>
									<th>Prodotto</th>
									<th>Genere</th>
									<th>Specie</th>
									<th>Unità misura</th>
									<th>Quantità</th>
									<th>Tariffa teorica</th>
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
										<td>${merceRichiesta.descProdotto}
	                    <form:input type="hidden" id="idProdotto" path="listaMerceRichiesta[${s.index}].idProdotto" /> 
	                    <form:input type="hidden" id="descProdotto" path="listaMerceRichiesta[${s.index}].descProdotto" />
	                  </td>
										<td><i>${merceRichiesta.descGenere}</i><form:input type="hidden"
												id="idGenere"
												path="listaMerceRichiesta[${s.index}].idGenere" /> <form:input
												type="hidden" id="descGenere"
												path="listaMerceRichiesta[${s.index}].descGenere" />
										</td>
										<td><i>${merceRichiesta.descSpecie}</i><form:input type="hidden"
												id="idSpecie"
												path="listaMerceRichiesta[${s.index}].idSpecie" /> <form:input
												type="hidden" id="descSpecie"
												path="listaMerceRichiesta[${s.index}].descSpecie" />
										</td>
										<td>${merceRichiesta.descUnitaMisura}<form:input
												type="hidden" id="descUnitaMisura"
												path="listaMerceRichiesta[${s.index}].descUnitaMisura" />
										</td>
										<td><fmt:formatNumber type="number" pattern="##0.###"
												value="${merceRichiesta.quantita}" /> <form:input
												type="hidden" id="quantita"
												path="listaMerceRichiesta[${s.index}].quantita" /></td>
										<td><fmt:formatNumber type="number" pattern="##0.00"
												value="${merceRichiesta.tariffaTeorica}" /> <form:input
												type="hidden" id="tariffaTeorica"
												path="listaMerceRichiesta[${s.index}].tariffaTeorica" /></td>
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
							<form:input type="text" id="numeroCertificati"
								path="numeroCertificati" disabled="true" />
							<label for="numeroCertificati">Numero certificati</label>
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
						<div class="col s12 m9 l9">
						  <div class="card-panel hoverable col s12 m9 l9">
							<i>Modalità per il pagamento della tariffa</i>:<br>						
							-   bonifico bancario c/c n. <strong>100000300025</strong> - IBAN: <strong>IT90X0306909790100000300025</strong><br>
							Beneficiario: <strong>Regione Lombardia</strong><br>
							Causale Versamento: <strong>CAP 290 Tariffa import</strong>			
						  </div>
		        </div>
					</div>

					<!-- Legenda Tariffe -->
					<div id="legendaTariffe" class="modal">
						<div class="modal-content">
							<h5>Tabella di riferimento per tipologie e tariffe della
								merce</h5>
							<table id="tabellaTariffe"
								class="responsive-table striped display">
								<thead>
									<tr>
										<th>Tipologia merce</th>
										<th>Unità misura</th>
										<th>Costo fino alla quantità massima</th>
										<th>Quantità massima</th>
										<th>Incremento costo</th>
										<th>Incremento quantità</th>
										<th>Massimale</th>
									</tr>
								</thead>
								<tbody id="bodyTabellaMerci">
									<c:forEach var="tariffa" items="${listaTariffeProdotti}">
										<tr>
											<td>${tariffa.descTipoProdotto}</td>
											<td>${tariffa.descUnitaMisura}</td>
											<td><fmt:formatNumber type="number" pattern="##0.00"
													value="${tariffa.tariffa}" /></td>
											<td>${tariffa.quantitaLimite}</td>
											<td><fmt:formatNumber type="number" pattern="##0.00"
													value="${tariffa.incrementoTariffa}" /></td>
											<td>${tariffa.incrementoQuantita}</td>
											<td><fmt:formatNumber type="number" pattern="##0.00"
													value="${tariffa.massimaleTariffa}" /></td>
										</tr>
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
							<label for="mittentePagamento">Mittente pagamento </label>
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
								maxlength="20" />
							<form:errors path="numeroDocumento" cssClass="error" />
							<label for="numeroDocumento">Numero documento pagamento *
							</label>
						</div>
					</div>

					<c:if test="${not empty nuovaRichiestaForm.nomeFileAllegato}">
						<spring:url
							value="/import/richieste/allegato/${nuovaRichiestaForm.idRichiesta}/${nuovaRichiestaForm.nomeFileAllegato}"
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

            <div class="card-panel hoverable">
              Il certificato dell'import (nulla osta) non contiene inizialmente
              una numerazione propria, selezionare dal menù a tendina la
              modalità con cui si desidera procedere:
              <ul>
                <li style="list-style-type: disc; list-style-position: inside;">Non
                  numerare: il certificato verrà stampato privo di numerazione</li>
                <li style="list-style-type: disc; list-style-position: inside;">Numerazione
                  manuale: verrà richiesto l’inserimento della numerazione</li>
                <li style="list-style-type: disc; list-style-position: inside;">Numerazione
                  automatica: verrà assegnato al certificato una numerazione
                  automatica dal sistema</li>
              </ul>
              <b>Solo una volta completati e salvati i dati obbligatori
                richiesti nella pagina, verrà abilitato il pulsante dedicato
                alla stampa del certificato «Stampa certificato».</b>
            </div>

            <div class="row">
              <div class="input-field col s6 m4 l3">
                <form:select id="tipoCertificato" path="idTipoCertificato"
                  cssClass="validate" required="required">
                  <form:option value="" label="Selezionare" />
                  <form:options items="${listaTipiCertificato}"
                    itemValue="idTipoCertificato" itemLabel="descTipoCertificato" />
                </form:select>
                <form:errors path="idTipoCertificato" cssClass="error" />
                <label for="tipoCertificato" class="bold">Tipo
                  certificato *</label>
              </div>
              <div class="input-field col s6 m8 l9">
                <c:choose>
                  <c:when
                    test="${not empty nuovaRichiestaForm.idCertificatoRichiesta 
                   and nuovaRichiestaForm.idStatoRichiesta != statoInBozza
                   and nuovaRichiestaForm.idStatoRichiesta != statoInoltrata
                   and nuovaRichiestaForm.idStatoRichiesta != statoRestituita}">
                    <a
                      href='<spring:url value="/import/report/esporta/nullaosta/${nuovaRichiestaForm.idRichiesta}"/>'
                      class="btn green waves-effect waves-light right">STAMPA
                      CERTIFICATO</a>
                  </c:when>
                  <c:otherwise>
                    <button class="btn green right" type="button"
                      disabled="disabled">STAMPA CERTIFICATO</button>
                  </c:otherwise>
                </c:choose>
              </div>
              <div class="input-field col s6 m8 l9">
                <c:choose>
                  <c:when
                    test="${not empty nuovaRichiestaForm.idCertificatoRichiesta 
                   and nuovaRichiestaForm.idStatoRichiesta != statoInBozza
                   and nuovaRichiestaForm.idStatoRichiesta != statoInoltrata
                   and nuovaRichiestaForm.idStatoRichiesta != statoRestituita}">
                    <a
                      href='<spring:url value="/import/report/verbale/${nuovaRichiestaForm.idRichiesta}"/>'
                      class="btn green waves-effect waves-light right">STAMPA
                      VERBALE D'ISPEZIONE</a>
                  </c:when>
                  <c:otherwise>
                    <button class="btn green right" type="button"
                      disabled="disabled">STAMPA VERBALE D'ISPEZIONE</button>
                  </c:otherwise>
                </c:choose>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s6 m4 l3">
                <form:select id="luogoEsecuzione" path="idLuogoEsecuzione"
                  cssClass="validate" required="required">
                  <form:option value="" label="Selezionare" />
                  <form:options items="${listaPuntiEntrata}"
                    itemValue="idUfficioDoganale" itemLabel="denomUfficioDoganale" />
                </form:select>
                <form:errors path="idLuogoEsecuzione" cssClass="error" />
                <label for="luogoEsecuzione">Luogo esecuzione e rilascio
                  certificato *</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:input type='text' id="dataEsecuzione"
                  path="dataEsecuzione" cssClass="datepicker_birthdate" />
                <form:errors path="dataEsecuzione" cssClass="error" />
                <label for="dataEsecuzione">Data esecuzione e rilascio
                  certificato *</label>
              </div>
              <div class="input-field col s12 m4 l6">
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
            </div>
            <div class="row">
              <div class="input-field col s12 m4 l6">
                <form:select id="numerazioniCertificato"
                  path="tipoNumerazioneCertificato" cssClass="validate"
                  required="required">
                  <form:options items="${listaNumerazioniCertificato}"
                    itemValue="id" itemLabel="descrizione" />
                </form:select>
                <form:errors path="tipoNumerazioneCertificato" cssClass="error" />
                <label for="numerazioniCertificato">Selezione modalità
                  numerazione certificato *</label>
              </div>
              <div id="bloccoCodiceEnte" class="input-field col s6 m4 l3">
                <form:input type="text" id="codiceEnte" path="codiceEnte"
                  disabled="true" />
                <label for="codiceEnte">Codice ente</label>
              </div>
              <div id="bloccoNumeroCertificato"
                class="input-field col s6 m4 l3">
                <form:input type="text" id="numeroCertificato"
                  path="numeroCertificato" disabled="true" />
                <label for="numeroCertificato">Numero certificato</label>
              </div>
              <div id="bloccoNumeroCertificatoManuale"
                class="input-field col s6 m4 l3">
                <form:input type="text" id="numeroCertificatoManuale"
                  path="numeroCertificatoManuale" cssClass="validate"
                  maxlength="6" />
                <form:errors path="numeroCertificatoManuale" cssClass="error" />
                <label for="numeroCertificatoManuale">Numero certificato
                  *</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12 m8 l6">
                <form:textarea id="noteCertificato" path="noteCertificato"
                  cssClass="materialize-textarea validate" rows="2"
                  data-length="1000" maxlength="1000" />
                <form:errors path="noteCertificato" cssClass="error" />
                <label for="noteCertificato">Note dell'ispettore</label>
              </div>
            </div>
            <div class="row">
              <div class="col s12">
                <div class="row">
                  <h5 class="header primary-color capitalize-uppercase">Ispettori
                    per il verbale d'ispezione</h5>
                  <div class="divider primary-color"></div>
                </div>
                <div class="row">
                  <div class="input-field col s6 m4 l3">
                    <form:select id="ispettoreDocumentale"
                      path="idIspettoreDocumentale" cssClass="validate"
                      required="required">
                      <form:option value="" label="Selezionare" />
                      <form:options items="${listaispettori}"
                        itemValue="idIspettore" itemLabel="denominazioneIspettore" />
                    </form:select>
                    <form:errors path="idIspettoreDocumentale" cssClass="error" />
                    <label for="ispettoreDocumentale">Ispettore per il
                      controllo documentale *</label>
                  </div>
                  <div class="input-field col s6 m4 l3">
                    <form:select id="ispettoreIdentita" path="idIspettoreIdentita"
                      cssClass="validate" required="required">
                      <form:option value="" label="Selezionare" />
                      <form:options items="${listaispettori}"
                        itemValue="idIspettore" itemLabel="denominazioneIspettore" />
                    </form:select>
                    <form:errors path="idIspettoreIdentita" cssClass="error" />
                    <label for="ispettoreIdentita">Ispettore per il
                      controllo d'identità *</label>
                  </div>
                  <div class="input-field col s6 m4 l3">
                    <form:select id="ispettoreFitosanitario"
                      path="idIspettoreFitosanitario" cssClass="validate"
                      required="required">
                      <form:option value="" label="Selezionare" />
                      <form:options items="${listaispettori}"
                        itemValue="idIspettore" itemLabel="denominazioneIspettore" />
                    </form:select>
                    <form:errors path="idIspettoreFitosanitario" cssClass="error" />
                    <label for="ispettoreFitosanitario">Ispettore per il
                      controllo fitosanitario *</label>
                  </div>
                </div>
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
                    href='<spring:url value="/import/richieste/esegui/${nuovaRichiestaForm.idRichiesta}"/>'
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
		<a href='<spring:url value="/import/richieste/elenco"/>'
			class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
	</div>
	<br />
	<!-- /div-->

	<content tag="script"> 
	<script type="text/javascript"
		src='<spring:url value="/resources/js/dropify.min.js"/>'></script> 
  <script>
			var numeroMerci = $('#numeroMerci').val();

			$(document).ready(
				function() {
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

						  $('#small_label').text($(this).text());

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
					$('#provinciaDestinatario').change(function() {
						var params = {
								  "idProvincia" : $(this).val()
						}

						getSelectAsincrona(
												'comuneDestinatario',
												'<spring:url value="/ajax/getComuniProvincia" />',
												params,
												getOptionComune, 1);
									});

					var validator = null;

					//Reperimento dell'unità di misura in base alla tipologia di merce selezionata
		      $('#idProdotto').change(function() {
		    	  var idProdotto = $(this).val();
	         
	          var params = {
	              "idProdotto": idProdotto
	          }
	          
	          setValoreAsincrono('unitaMisuraMerce', '<spring:url value="/ajax/getUnitaMisuraProdotto" />', params, 
	              toStringUnitaMisura, idProdotto.length==0);
	          
	          if (validator) {
	            validator.form();
	          }
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

					$('#aggiungiMerce').click(
						function() {
							/*
							  Reset del campo hidden del genere merce, se l'utente ha modificato il campo di testo Genere senza selezionare
							  un valore dell'autocomplete, l'hidden vale 0 e viene quindi svuotato per dare messaggio di errore nella validazione
							 */
							if ($('#idGenereMerce').val() == '0') {
								$('#idGenereMerce').val('');
							}

							validator = $("#formMerce").validate(
									{
										  rules : {
												nazioneOrigine : {
													required : true
												},
												idProdotto : {
													required : true
												},
												idGenereMerce : {
													required: function(element) {
						                return $('#idProdotto').find(":selected").text().match(/ALTRO/i) === null;
						              }
												},
												quantitaMerce : {
													required : true,
													fourDecimals : true,
													minStrict: 0,
													max: 999999999
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
												$idSpecie == $('input[name="listaMerceRichiesta[' + indice + '].idSpecie"]').val()) {
											preventInsert = true;

											var html_str = 
												'Nella tabella delle merci è già presente il prodotto <b>'
												+ $descProdotto 
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

								$(hiddenTemplate.replace(/NOME_CAMPO/g,'idNazioneOrigine')).val(
										$selectedOption.val()).appendTo($cella);

								$cella.append($(hiddenTemplate.replace(/NOME_CAMPO/g, 'idMerceRichiesta')));

								$cella.appendTo($riga);

								//Tipologia
								$cella = $('<td></td>');
          
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

								//$selectedOption = $('#genereMerce').find(":selected");

								$hiddenField = $(hiddenTemplate.replace(/NOME_CAMPO/g,'descGenere'));

								$testoCorsivo.text($descGenere);
								$hiddenField.val($descGenere);

								/*if ($selectedOption.val()) {
								  $cella.text($selectedOption.text());
								  $hiddenField.val($selectedOption.text());
								}*/

								$cella.append($testoCorsivo);
								$cella.append($hiddenField);

								//$cella.append($(hiddenTemplate.replace(/NOME_CAMPO/g, 'idGenere')).val($selectedOption.val()));
								$cella.append($(hiddenTemplate.replace(/NOME_CAMPO/g,'idGenere')).val($idGenere));

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

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'idSpecie')).val($selectedOption.val())
										.appendTo($cella);

								$cella.appendTo($riga);

								//Unità misura
								textValue = $('#unitaMisuraMerce').val();

								$cella = $('<td></td>').text(textValue);

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'descUnitaMisura')).val(textValue)
								    .appendTo($cella);

								$cella.appendTo($riga);

								//Quantità
								textValue = $('#quantitaMerce').val();

								$cella = $('<td></td>').text(textValue);

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'quantita')).val(textValue).appendTo($cella);

								$cella.appendTo($riga);

								//Tariffa teorica
								$cella = $('<td></td>');

								$(hiddenTemplate.replace(/NOME_CAMPO/g, 'tariffaTeorica')).appendTo($cella);

								$cella.appendTo($riga);

								//Azioni
								$riga.append($('#rigaTemplate > td').filter(':last').clone());

								$('#bodyTabellaMerci').prepend($riga);

								$('#tabellaMerci').show();

								numeroMerci++;

								//Reperimento tariffa teorica
								var params = {
									"idTipoRichiesta" : $('#idTipoRichiesta').val(),
									"idNazioneOrigine" : $riga.find('#idNazioneOrigine').val(),
									"idProdotto": $riga.find('#idProdotto').val(),
									"idGenere": $riga.find('#idGenere').val(),
									"idSpecie": $riga.find('#idSpecie').val(),
									"quantita" : $riga.find('#quantita').val()
								};

								$.getJSON('<spring:url value="/ajax/getTariffaTeorica" />', params, function(responseJSON) {
									$hiddenField = $riga.find('#tariffaTeorica').val(responseJSON);
									$hiddenField.closest('td').append(responseJSON);
								})
								.fail(function(jqxhr, textStatus, error) {
									if (window.console) {
										console.log("Errore in reperimento valore asincrono: " + textStatus + ", " + error);
									}
								});
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

					$('#numerazioniCertificato').change(function() {
						checkNumeroCertificato();
					});

					checkNumeroCertificato();

					$('#allegatoPagamento').dropify(
									{
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

					$('#spedizioneMultipla').change(function() {
						checkSpedizioneMultipla();
					});

					checkSpedizioneMultipla();
					
					<c:if test="${nuovaRichiestaForm.visualizzaModalInoltra}">
					checkInoltra();
					</c:if>

				});

			function getOptionComune(comune) {
				return getOption(comune.idComune, comune.denomComune);
			}

			function toStringUnitaMisura(unitaMisura) {
				return unitaMisura.descUnitaMisura;
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
					$('#formTrattamento input[type=text], #formTrattamento textarea').each(function(index) {
						if ($(this).attr('id')) {
							$(this).attr('disabled', false);
						}
					});
				} else {
					$('#formTrattamento input[type=text], #formTrattamento textarea').each(function(index) {
						if ($(this).attr('id')) {
							$(this).attr('disabled', true).val('');
						}
					});
				}
			}

			function checkNumeroCertificato() {
				$tipoNumerazione = $('#numerazioniCertificato').find(":selected").val();

				if ($tipoNumerazione == '<spring:eval expression="T(it.aizoon.ersaf.util.CaronteConstants).NUMERAZIONE_CERTIFICATO_NESSUNA"/>') {
					//Nessuna numerazione
					$('#bloccoCodiceEnte').hide();
					$('#bloccoNumeroCertificato').hide();
					$('#bloccoNumeroCertificatoManuale').hide();
				} else if ($tipoNumerazione == '<spring:eval expression="T(it.aizoon.ersaf.util.CaronteConstants).NUMERAZIONE_CERTIFICATO_MANUALE"/>') {
					//Numerazione manuale
					$('#bloccoCodiceEnte').show();
					$('#bloccoNumeroCertificato').hide();
					$('#bloccoNumeroCertificatoManuale').show();
				} else {
					//Numerazione automatica
					if ($('#numeroCertificato').val()) {
						$('#bloccoCodiceEnte').show();
						$('#bloccoNumeroCertificato').show();
					} else {
						$('#bloccoCodiceEnte').hide();
						$('#bloccoNumeroCertificato').hide();
					}
					$('#bloccoNumeroCertificatoManuale').hide();
				}
			}

			function checkSpedizioneMultipla() {
				/*
				  Se la checkbox è selezionata, occorre abilitare il campo 'Numero certificati collegati', in caso contrario lo stesso 
				  campo deve essere svuotato e disabilitato
				 */
				if ($('#spedizioneMultipla').is(":checked")) {
					$('#numCertificatiCollegati').attr('disabled', false);
				} else {
					$('#numCertificatiCollegati').attr('disabled', true).val('').removeClass("valid invalid");
					M.updateTextFields();
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
	          //confirmButtonText : "<a href='<spring:url value="/import/richieste/inoltra/${nuovaRichiestaForm.idRichiesta}"/>' title='Inoltra' class='btn amber darken-1 waves-effect waves-light' style='display: inline-block'>INOLTRA</a>",
	          cancelButtonText : 'ANNULLA',
	          cancelButtonClass: 'btn',
	          focusConfirm: false,
	          buttonsStyling: false,
	      }).then(function(result) {
	          if (result.value) {
	              window.location.href = '<spring:url value="/import/richieste/inoltra/${nuovaRichiestaForm.idRichiesta}"/>';
	          }
	      });

	      return false;
	    }
		</script> </content>

</body>
</html>