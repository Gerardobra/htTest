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
						<a href="#richiesta" data-value="richiesta" class="breadcrumb"
							style="color: #fff">&nbsp;&nbsp;Dati Richiesta</a> <a
							href="#mittente" data-value="mittente" class="breadcrumb">Mittente</a>
						<a href="#destinatario" data-value="destinatario"
							class="breadcrumb">Destinatario</a> <a href="#trasporto"
							data-value="trasporto" class="breadcrumb">Trasporto</a> <a
							href="#merce" data-value="merce" class="breadcrumb">Merce</a> <a
							href="#tariffa" data-value="tariffa" class="breadcrumb">Tariffa</a>
						<a href="#trattamento" data-value="trattamento" class="breadcrumb">Trattamento</a>
						<a href="#pagamento" data-value="pagamento" class="breadcrumb">Pagamento</a>
						<c:if test="${isSuperUser}">
            <a href="#esegui" data-value="esegui" class="breadcrumb">Esegui</a>
            </c:if>
						<a style="display: none;"></a>
					</div>
				</div>
			</nav>
		</div>

		<div class="card-content">
			<div class="col s12" id="richiesta">
				<div class="card-content">
					<div class="row">
						<div class="row">
							<div class="input-field col s6 m4 l3">
								<input type="text" id="statoRichiesta"
									value="${fn:escapeXml(dettaglioRichiesta.descStatoRichiesta)}" 
								disabled="disabled" /> <label for="statoRichiesta">Stato
									richiesta</label>
							</div>
							<div class="input-field col s6 m4 l3">
                <input type="text" id="codiceRichiesta"
                  value="${fn:escapeXml(dettaglioRichiesta.codRichiesta)}" 
                disabled="disabled" /> <label for="codiceRichiesta">Codice
                  richiesta</label>
              </div>
							<div class="input-field col s6 m4 l3">
								<input type="text" id="utenteResponsabile"
									value="${fn:escapeXml(dettaglioRichiesta.utenteNome)} ${fn:escapeXml(dettaglioRichiesta.utenteCognome)}"
									disabled="disabled" /> <label for="utenteResponsabile">Utente
									Responsabile</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<input type="text" id="tipoSpedizioniere"
									value="${fn:escapeXml(dettaglioRichiesta.denomTipoSpedizioniere)}"
									disabled="disabled" /> <label for="tipoSpedizioniere">Tipo
									Operatore</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<input type="text" id="spedizioniere"
									value="${fn:escapeXml(dettaglioRichiesta.denomSpedizioniere)}"
									disabled="disabled" /> <label for="spedizioniere"
									style="white-space: nowrap">Denominazione Operatore</label>
							</div>
							<fmt:formatDate value="${dettaglioRichiesta.dataInsert}" pattern="dd/MM/yyyy" var="dataApertura" />
							<div class="input-field col s6 m4 l3">
                <input type="text" value="${dataApertura}" id="dataApertura" disabled="disabled" /> 
                <label for="dataApertura">Data apertura</label>
              </div>
              <fmt:formatDate value="${dettaglioRichiesta.dataUpdate}" pattern="dd/MM/yyyy" var="dataModifica" />
              <div class="input-field col s6 m4 l3">
                <input type="text" value="${dataModifica}" id="dataModifica" disabled="disabled" /> 
                <label for="dataModifica">Data modifica</label>
              </div>
              <fmt:formatDate value="${dettaglioRichiesta.dataInoltro}" pattern="dd/MM/yyyy" var="dataInoltro" />
              <div class="input-field col s6 m4 l3">
                <input type="text" value="${dataInoltro}" id="dataInoltro" disabled="disabled" /> 
                <label for="dataInoltro">Data inoltro</label>
              </div>
							<div class="input-field col s12 m8 l6">
								<textarea id="note" class="materialize-textarea" rows="2"
									disabled="disabled">${fn:escapeXml(dettaglioRichiesta.noteDatiRichiesta)}</textarea>
								<label for="note">Note</label>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12 right-align">
								<a class="btn waves-effect forward" href="#mittente">AVANTI</a>
							</div>
						</div>

					</div>
				</div>
			</div>

			<div class="col s12" id="mittente">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s12 m8 l6">
							<input type="text" id="denominazioneMittente"
								value="${fn:escapeXml(dettaglioRichiesta.denomMittente)}"
								disabled="disabled" /> <label for="denominazioneMittente">Cognome
								e nome / Denominazione </label>
						</div>
						<div class="input-field col s12 m8 l6">
							<input type="text" id="indirizzoMittente"
								value="${fn:escapeXml(dettaglioRichiesta.indirizzoMittente)}"
								disabled="disabled" /> <label for="indirizzoMittente">Indirizzo (Es. Via Roma, 24)</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="comuneMittente"
								value="${fn:escapeXml(dettaglioRichiesta.denomComuneEstMittente)}"
								disabled="disabled" /> <label for="comuneMittente">Città
							</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="nazioneMittente"
								value="${fn:escapeXml(dettaglioRichiesta.denomNazioneMittente)}"
								class="active" disabled="disabled"> <label
								for="nazioneMittente">Stato </label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12 right-align">
							<a class="btn waves-effect back" href="#richiesta">INDIETRO</a>
							<a class="btn waves-effect forward" href="#destinatario">AVANTI</a>

						</div>
					</div>
				</div>
			</div>

			<div class="col s12" id="destinatario">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s12 m8 l6">
							<input type="text" id="denominazioneDestinatario"
								value="${fn:escapeXml(dettaglioRichiesta.denomDestinatario)}"
								disabled="disabled" /> <label for="denominazioneDestinatario">Cognome
								e nome / Denominazione </label>
						</div>
						<div class="input-field col s12 m8 l6">
							<input type="text" id="indirizzoDestinatario"
								value="${fn:escapeXml(dettaglioRichiesta.indirizzoDestinatario)}"
								disabled="disabled" /> <label for="indirizzoDestinatario">Indirizzo (Es. Via Roma, 24)
							</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="provinciaDestinatario"
								value="${fn:escapeXml(dettaglioRichiesta.denomProvincia)}"
								disabled="disabled" /> <label for="provinciaDestinatario">Provincia
							</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="comuneDestinatario"
								value="${fn:escapeXml(dettaglioRichiesta.denomComuneIta)}"
								disabled="disabled" /> <label for="comuneDestinatario">Comune
							</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="codiceRup"
								value="${fn:escapeXml(dettaglioRichiesta.codiceRupDestinatario)}"
								disabled="disabled" /> <label for="codiceRup">Codice
								RUP</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="regioneRup"
								value="${fn:escapeXml(dettaglioRichiesta.descRegioneRupDestinatario)}"
								disabled="disabled" /> <label for="regioneRup">Regione
								RUP</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12 right-align">
							<a class="btn waves-effect back" href="#mittente">INDIETRO</a>
							<a class="btn waves-effect forward" href="#trasporto">AVANTI</a>
						</div>
					</div>
				</div>
			</div>

			<div class="col s12" id="trasporto">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s6 m4 l6">
							<input type="text" id="numDocumentoTrasporto"
								value="${fn:escapeXml(dettaglioRichiesta.identifMezzoTrasporto)}"
								disabled="disabled" /> <label for="numDocumentoTrasporto">N.
								Documento di trasporto </label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="mezzoTrasporto"
								value="${fn:escapeXml(dettaglioRichiesta.descModoTrasporto)}"
								disabled="disabled" /> <label for="mwzzoTrasporto">Mezzo
								di trasporto dichiarato </label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="doganaTrasporto"
								value="${fn:escapeXml(dettaglioRichiesta.dogana)}"
								class="active" disabled="disabled" /> <label
								for="doganaTrasporto">Dogana </label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="puntoEntrataDichiarato"
								value="${fn:escapeXml(dettaglioRichiesta.descUfficioDoganaleUtilizzato)}"
								disabled="disabled" /> 
							<label for="puntoEntrataDichiarato">Punto d'entrata dichiarato </label>
						</div>
						<div class="col s6 m4 l3">
						  <label>
							  <input type="checkbox" id="spedizioneMultipla" class="left centered" disabled="disabled" ${dettaglioRichiesta.spedizioneMultipla ? 'checked="checked"' : ''} />
	              <span>Spedizione multipla</span>
              </label>
            </div>
            <div class="input-field col s6 m4 l3">
              <input type="text" id="numCertificatiCollegati" value="${fn:escapeXml(dettaglioRichiesta.numeroCertificatiRichiesti)}" disabled="disabled" /> 
              <label for="numCertificatiCollegati">Numero certificati collegati</label>
            </div>
					</div>
					<div class="row">
						<div class="input-field col s12 right-align">
							<a class="btn waves-effect back" href="#destinatario">INDIETRO</a>
							<a class="btn waves-effect forward" href="#merce">AVANTI</a>
						</div>
					</div>
				</div>
			</div>

			<div class="col s12" id="merce">
				<div class="card-content">
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
								</tr>
							</thead>
							<tbody id="bodyTabellaMerci">
								<c:forEach var="merceRichiesta" items="${listaMerci}"
									varStatus="s">
									<tr>
										<td>${fn:escapeXml(merceRichiesta.descNazioneOrigine)}</td>
										<td>${fn:escapeXml(merceRichiesta.descTipoProdotto)}</td>
										<td><i>${fn:escapeXml(merceRichiesta.descGenere)}</i></td>
                    <td><i>${fn:escapeXml(merceRichiesta.descSpecie)}</i></td>
										<td>${fn:escapeXml(merceRichiesta.descUnitaMisura)}</td>
										<td><fmt:formatNumber type="number" pattern="##0.###"
												value="${fn:escapeXml(merceRichiesta.quantita)}" /></td>
										<td><fmt:formatNumber type="number" pattern="##0.00"
												value="${fn:escapeXml(merceRichiesta.tariffaTeorica)}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div class="row">
						<div class="input-field col s12 right-align">

							<a class="btn waves-effect forward" href="#tariffa">AVANTI</a> <a
								class="btn waves-effect back" href="#trasporto">INDIETRO</a>

						</div>
					</div>
				</div>
			</div>

			<div class="col s12" id="tariffa">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s6 m4 l3">
						  <fmt:formatNumber type="number" pattern="##0.00" value="${fn:escapeXml(tariffe.totaleIdentita)}" 
						    var="totaleIdentita" />
							<input type="text" id="tariffaIdentita" value="${totaleIdentita}" disabled="disabled" /> 
							<label for="tariffaIdentita">Tariffa controlli d'identità (€)</label>
						</div>
						<div class="input-field col s6 m4 l3">
						  <fmt:formatNumber type="number" pattern="##0.00" value="${fn:escapeXml(tariffe.totaleDocumentale)}" 
                var="totaleDocumentale" />
							<input type="text" id="tariffaDocumentali"
								value="${totaleDocumentale}" disabled="disabled" /> 
							<label for="tariffaDocumentali">Tariffa controlli documentali (€)</label>
						</div>
						<div class="input-field col s6 m4 l3">
						  <fmt:formatNumber type="number" pattern="##0.00" value="${fn:escapeXml(tariffe.totaleFitosanitario)}" 
                var="tariffaPartite" />
							<input type="text" id="tariffaPartite" value="${tariffaPartite}" disabled="disabled" /> 
						  <label for="tariffaPartite">Tariffa partite (€)</label>
						</div>
						<div class="input-field col s6 m4 l3">
						  <fmt:formatNumber type="number" pattern="##0.00" value="${fn:escapeXml(tariffe.massimaleTariffa)}" 
                var="massimalePartite" />
							<input type="text" id="massimalePartite" value="${massimalePartite}" disabled="disabled" /> 
							<label for="massimalePartite">Massimale partite (€)</label>
						</div>
						<div class="input-field col s6 m4 l3">
						  <fmt:formatNumber type="number" pattern="##0.00" value="${fn:escapeXml(tariffe.numeroCertificati)}" 
                var="numeroCertificati" />
						  <input type="text" id="numeroCertificati" value="${numeroCertificati}" disabled="disabled" />
               <label for="numeroCertificati">Numero certificati</label>
            </div>
						<div class="input-field col s6 m4 l3">
						  <fmt:formatNumber type="number" pattern="##0.00" value="${fn:escapeXml(tariffe.totaleTariffe)}" 
                var="tariffaTotale" />
							<input type="text" id="tariffaTotale" value="${tariffaTotale}" disabled="disabled" /> 
							<label for="tariffaTotale">Tariffa totale (€)</label>
						</div>
						<div class="input-field col s6 m4 l3 center">
							<a title="Legenda"
								class="btn modal-trigger btn-floating btn-large cyan hoverable"
								href="#legendaTariffe"> <i class="material-icons">live_help</i></a>
						</div>
						<div class="card-panel hoverable col s12 m9 l9">
							<i>Modalità per il pagamento della tariffa</i>:<br>						
							-   bonifico bancario c/c n. <strong>100000300025</strong> - IBAN: <strong>IT90X0306909790100000300025</strong><br>
							Beneficiario: <strong>Regione Lombardia</strong><br>
							Causale Versamento: <strong>CAP 290 Tariffa import</strong>			
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
											<td>${fn:escapeXml(tariffa.descTipoProdotto)}</td>
											<td>${fn:escapeXml(tariffa.descUnitaMisura)}</td>
											<td><fmt:formatNumber type="number" pattern="##0.00"
													value="${fn:escapeXml(tariffa.tariffa)}" /></td>
											<td>${fn:escapeXml(tariffa.quantitaLimite)}</td>
											<td><fmt:formatNumber type="number" pattern="##0.00"
													value="${fn:escapeXml(tariffa.incrementoTariffa)}" /></td>
											<td>${fn:escapeXml(tariffa.incrementoQuantita)}</td>
											<td><fmt:formatNumber type="number" pattern="##0.00"
													value="${fn:escapeXml(tariffa.massimaleTariffa)}" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

					<div class="row">
						<div class="input-field col s12 right-align">
							<a class="btn waves-effect back" href="#merce">INDIETRO</a>
							<a class="btn waves-effect forward" href="#trattamento">AVANTI</a>
						</div>
					</div>
				</div>
			</div>

			<div class="col s12" id="trattamento">
				<div class="card-content">
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<input type="text" id="trattamento"
								value="${fn:escapeXml(trattamentoRichiesta.descTrattamento)}"
								disabled="disabled"> <label for="trattamento">Trattamento</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="prodottoChimico"
								value="${fn:escapeXml(trattamentoRichiesta.prodottoChimico)}"
								disabled=disabled /> <label for="prodottoChimico">Prodotto
								chimico (sostanza attiva)</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="durata"
								value="${fn:escapeXml(trattamentoRichiesta.durata)}"
								disabled="disabled" /> <label for="durata">Durata</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="temperatura"
								value="${fn:escapeXml(trattamentoRichiesta.temperatura)}"
								disabled="disabled" /> <label for="temperatura">Temperatura</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="concentrazione"
								value="${fn:escapeXml(trattamentoRichiesta.concentrazione)}"
								disabled="disabled" /> <label for="concentrazione">Concentrazione</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<fmt:formatDate
								value="${trattamentoRichiesta.dataTrattamento}"
								pattern="dd/MM/yyyy" var="data" />
							<input type="text" value="${data}" id="dataTrattamento"
								disabled="disabled" /> <label for="dataTrattamento">Data</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<textarea id="infoSupplementari" class="materialize-textarea"
								rows="2" disabled="disabled">${fn:escapeXml(trattamentoRichiesta.informazioniSupplementari)}</textarea>
							<label for="infoSupplementari">Informazioni supplementari</label>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12 right-align">
							<a class="btn waves-effect back" href="#tariffa">INDIETRO</a>
							<a class="btn waves-effect forward" href="#pagamento">AVANTI</a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col s12" id="pagamento">
        <div class="card-content">
          <div class="row">
            <div class="input-field col s6 m4 l3">
              <input type="text" id="mittentePagamento"
                value="${fn:escapeXml(pagamentoRichiesta.mittente)}"
                disabled="disabled" /> 
              <label for="mittentePagamento">Mittente Pagamento</label>
            </div>
            <div class="input-field col s6 m4 l3">
              <input type="text" id="destinatarioPagamento"
                value="${fn:escapeXml(destinatarioPagamento.valoreCostante)}"
                disabled="disabled" /> <label for="destinatarioPagamento">Destinatario
                Pagamento</label>
            </div>
            <div class="input-field col s6 m4 l3">
              <input type="text" id="importoTotaleDovuto" value="${tariffaTotale}" disabled="disabled" /> 
              <label for="importoTotaleDovuto">Importo totale dovuto</label>
            </div>
            <div class="input-field col s6 m4 l3">
              <input type="text" id="tipoPagamento"
                value="${fn:escapeXml(pagamentoRichiesta.tipoPagamento)}"
                disabled="disabled"> <label for="tipoPagamento">Tipo
                pagamento</label>
            </div>
            <div class="input-field col s6 m4 l3">
              <fmt:formatDate
                value="${pagamentoRichiesta.dataPagamento}"
                pattern="dd/MM/yyyy" var="dataPag" />
              <input type="text" value="${dataPag}" id="dataPagamento"
                disabled="disabled" /> <label for="dataPagamento">Data
                Pagamento</label>
            </div>
            <div class="input-field col s6 m4 l3">
              <input type="text" id="numeroDocumentoPagamento"
                value="${fn:escapeXml(pagamentoRichiesta.numeroDocumento)}"
                disabled="disabled" /> <label for="numeroDocumentoPagamento">Numero
                documento pagamento</label>
            </div>
            
            <c:if test="${not empty pagamentoRichiesta.nomeFileAllegato}">
            <spring:url value="/import/richieste/allegato/scarica/${dettaglioRichiesta.idRichiesta}" var="pathAllegatoPagamento" />
            <div class="input-field col s6 m4 l3">
              <a class="btn waves-effect back" href="${pathAllegatoPagamento}">SCARICA ALLEGATO</a>
            </div>
            </c:if>
            
            <!--div class="row">
                  <button type="reset" class="btn waves-effect back">AGGIUNGI ALLEGATO</button>
                </div-->
          </div>

          <div class="row">
            <div class="input-field col s12 right-align">
              <a class="btn waves-effect back" href="#trattamento">INDIETRO</a>
              <c:if test="${isSuperUser}">
		          <a class="btn waves-effect forward" href="#esegui">AVANTI</a>
		          </c:if>
            </div>
          </div>
        </div>
      </div>

      <spring:eval var="statoInBozza" expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_BOZZA"/>
      <spring:eval var="statoInoltrata" expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_INOLTRATA"/>
      <spring:eval var="statoRestituita" expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_RESTITUITA"/>
      <c:if test="${isSuperUser}">
			<div class="col s12" id="esegui">
				<div class="card-content">
				  <div class="row">
            <div class="input-field col s6 m4 l3">
              <input type="text" id="tipoCertificato" value="${fn:escapeXml(datiCertificato.descTipoCertificato)}" disabled="disabled" />
              <label for="tipoCertificato" class="bold">Tipo certificato</label>
            </div>
            <div class="input-field col s6 m8 l9">
              <c:choose>
              <c:when test="${not empty datiCertificato.idCertificato 
                and dettaglioRichiesta.idStatoRichiesta != statoInBozza
                and dettaglioRichiesta.idStatoRichiesta != statoInoltrata
                and dettaglioRichiesta.idStatoRichiesta != statoRestituita}">
                <a href='<spring:url value="/import/report/esporta/nullaosta/${dettaglioRichiesta.idRichiesta}"/>'
                  class="btn green waves-effect waves-light right">STAMPA CERTIFICATO</a>
              </c:when>
              <c:otherwise>
                <button class="btn green right" type="button" disabled="disabled">STAMPA CERTIFICATO</button>
              </c:otherwise>
              </c:choose>
            </div>
            <div class="input-field col s6 m8 l9">
              <c:choose>
              <c:when test="${not empty datiCertificato.idCertificato 
                and dettaglioRichiesta.idStatoRichiesta != statoInBozza
                and dettaglioRichiesta.idStatoRichiesta != statoInoltrata
                and dettaglioRichiesta.idStatoRichiesta != statoRestituita}">
                <a href='<spring:url value="/import/report/verbale/${dettaglioRichiesta.idRichiesta}"/>'
                  class="btn green waves-effect waves-light right">STAMPA VERBALE D'ISPEZIONE</a>
              </c:when>
              <c:otherwise>
                <button class="btn green right" type="button" disabled="disabled">STAMPA VERBALE D'ISPEZIONE</button>
              </c:otherwise>
              </c:choose>
            </div>
          </div>
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<input type="text" id="luogoEsecuzione"
								value="${fn:escapeXml(datiCertificato.luogoEsecuzione)}"
								disabled="disabled" /> <label for="luogoEsecuzione">Luogo
								esecuzione e rilascio certificato</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<fmt:formatDate
								value="${datiCertificato.dataEsecuzione}"
								pattern="dd/MM/yyyy" var="data" />
							<input type="text" value="${data}" id="dataEsecuzione"
								disabled="disabled" /> <label for="dataEsecuzione">Data
								esecuzione e rilascio certificato</label>
						</div>
						<div class="input-field col s12 m4 l6">
							<input type="text" id="ispettore"
								value="${fn:escapeXml(datiCertificato.denominazioneIspettore)}"
								disabled="disabled" /> 
						  <label for="ispettore">Ispettore che rilascia il certificato</label>
						</div>
					</div>
					<div class="row">
					  <div class="input-field col s12 m4 l6">
						  <c:choose>
							<c:when test="${datiCertificato.numeroCertificato != null}">
                <input type="text" id="numerazione" value="Numerazione automatica" disabled="disabled" /> 
							</c:when>
							<c:when test="${datiCertificato.numeroCertificatoManuale != null}">
                <input type="text" id="numerazione" value="Numerazione manuale" disabled="disabled" /> 
							</c:when>
							<c:otherwise>
							  <input type="text" id="numerazione" value="Non numerare" disabled="disabled" /> 
							</c:otherwise>
						  </c:choose>
						  <label for="numerazione">Selezione modalità numerazione certificato</label>
						</div>
						<c:if test="${datiCertificato.numeroCertificato != null || datiCertificato.numeroCertificatoManuale != null}">
							<div class="input-field col s6 m4 l3">
								<input type="text" id="codiceEnte"
									value="${fn:escapeXml(datiCertificato.codiceEnte)}"
									disabled="disabled" /> <label for="codiceEnte">Codice ente</label>
							</div>
							<c:choose>
							<c:when test="${datiCertificato.numeroCertificato != null}">
							<spring:eval var="numeroCertificato" expression="datiCertificato.numeroCertificato" />
							</c:when>
							<c:otherwise>
							<spring:eval var="numeroCertificato" expression="datiCertificato.numeroCertificatoManuale" />
							</c:otherwise>
							</c:choose>
							<div class="input-field col s6 m4 l3">
								<input type="text" id="numeroCertificato"
									value="${fn:escapeXml(numeroCertificato)}" disabled="disabled" /> 
								<label for="numeroCertificato">Numero certificato</label>
							</div>
						</c:if>
					</div>
          <div class="row">
						<div class="input-field col s12 m8 l6">
							<textarea id="noteCertificato" class="materialize-textarea"
								rows="2" disabled="disabled">${fn:escapeXml(datiCertificato.note)}</textarea>
							<label for="noteCertificato">Note dell'ispettore</label>
						</div>
					</div>
					<div class="row">
            <div class="col s12">
              <div class="row">
                <h5 class="header primary-color capitalize-uppercase">Ispettori per il verbale d'ispezione</h5>
                <div class="divider primary-color"></div>
              </div>
              <div class="row">
                <div class="input-field col s6 m4 l3">
                  <input type="text" id="ispettoreDocumentale"
                    value="${fn:escapeXml(datiCertificato.denominazioneIspettoreDocumentale)}" disabled="disabled" /> 
                  <label for="ispettoreDocumentale">Ispettore per il controllo documentale</label>
                </div>
                <div class="input-field col s6 m4 l3">
                  <input type="text" id="ispettoreIdentita"
                    value="${fn:escapeXml(datiCertificato.denominazioneIspettoreIdentita)}" disabled="disabled" /> 
                  <label for="ispettoreIdentita">Ispettore per il controllo d'identità</label>
                </div>
                <div class="input-field col s6 m4 l3">
                  <input type="text" id="ispettoreFitosanitario"
                    value="${fn:escapeXml(datiCertificato.denominazioneIspettoreFitosanitario)}" disabled="disabled" /> 
                  <label for="ispettoreFitosanitario">Ispettore per il controllo fitosanitario</label>
                </div>
              </div>
            </div>
          </div>
					<div class="row">
						<div class="input-field col s12 right-align">
							<a class="btn waves-effect back" href="#pagamento">INDIETRO</a>
						</div>
					</div>
				</div>
			</div>
      </c:if>
      
		</div>
	</div>

	<div class="col s12 left-align">
		<a href='<spring:url value="/import/richieste/elenco"/>'
			class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
	</div>
	<br />
	<!-- /div-->

	<content tag="script"> <script>
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
  
        $('.forward').click(
          function() {
            var last_index = $(this).attr(
                'href').length - 1;

            $('#' + $(this).attr('href').substr(1, last_index)).show();

            var current = $(this).attr('href').substr(1, last_index);

            $('.breadcrumb').each(
              function(index) {
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
  
        $('.back').click(
          function() {
            var last_index = $(this).attr(
                'href').length - 1;

            $('#' + $(this).attr('href').substr(1, last_index)).show();

            var current = $(this).attr('href').substr(1,last_index);

            $('.breadcrumb').each(
              function(index) {
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
				//$( ".breadcrumb:nth-child("+$("#schedaSelezionata").val()+")").click();
				$("a[data-value='richiesta']").click();

			});

	</script> </content>


</body>
</html>