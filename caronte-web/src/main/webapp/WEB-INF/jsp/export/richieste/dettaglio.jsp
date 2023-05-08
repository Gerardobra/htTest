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
                <input type="text" id="tipoRichiesta"
                  value="${fn:escapeXml(dettaglioRichiesta.descTipoRichiesta)}" disabled="disabled" /> 
                <label for="tipoRichiesta">Tipo richiesta</label>
              </div>
							<div class="input-field col s6 m4 l3">
								<input type="text" id="statoRichiesta" value="${fn:escapeXml(dettaglioRichiesta.descStatoRichiesta)}" 
								  disabled="disabled" /> 
							  <label for="statoRichiesta">Stato richiesta</label>
							</div>
							<div class="input-field col s6 m4 l3">
                <input type="text" id="codiceRichiesta" value="${fn:escapeXml(dettaglioRichiesta.codRichiesta)}" 
                  disabled="disabled" /> 
                <label for="codiceRichiesta">Codice richiesta</label>
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
									Spedizioniere</label>
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
              <input type="text" id="provinciaMittente"
                value="${fn:escapeXml(dettaglioRichiesta.denomProvincia)}"
                disabled="disabled" /> 
              <label for="provinciaMittente">Provincia</label>
            </div>
            <div class="input-field col s6 m4 l3">
              <input type="text" id="comuneMittente"
                value="${fn:escapeXml(dettaglioRichiesta.denomComuneIta)}"
                disabled="disabled" /> 
              <label for="comuneMittente">Comune</label>
            </div>
						<div class="input-field col s6 m4 l3">
              <input type="text" id="codiceRupMittente"
                value="${fn:escapeXml(dettaglioRichiesta.codiceRupMittente)}"
                disabled="disabled" /> 
              <label for="codiceRupMittente">Codice RUOP</label>
            </div>
            <div class="input-field col s12 m8 l6">
              <textarea id="note" class="materialize-textarea" rows="2"
                disabled="disabled">${fn:escapeXml(dettaglioRichiesta.noteMittenteCertif)}</textarea>
              <label for="note">Note mittente</label>
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
              <input type="text" id="comuneDestinatario"
                value="${fn:escapeXml(dettaglioRichiesta.denomComuneEstDestinatario)}"
                disabled="disabled" /> 
              <label for="comuneDestinatario">Città</label>
            </div>
            <div class="input-field col s6 m4 l3">
              <input type="text" id="nazioneDestinatario"
                value="${fn:escapeXml(dettaglioRichiesta.denomNazioneDestinatario)}"
                class="active" disabled="disabled"> 
              <label for="nazioneDestinatario">Stato </label>
            </div>
						<!-- <div class="input-field col s6 m4 l3">
              <input type="text" id="codiceRupDestinatario"
                value="${fn:escapeXml(dettaglioRichiesta.codiceRupDestinatario)}"
                disabled="disabled" /> 
              <label for="codiceRupDestinatario">Codice RUP</label>
            </div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="statoRupDestinatario"
								value="${fn:escapeXml(dettaglioRichiesta.denomNazioneRupDestinatario)}"
								disabled="disabled" /> 
						  <label for="statoRupDestinatario">Stato RUP destinatario</label>
						</div>-->
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
              <input type="text" id="nazioneProtVeg"
                value="${fn:escapeXml(dettaglioRichiesta.descNazioneProtVegDestinat)}"
                disabled="disabled" /> 
              <label for="nazioneProtVeg">Nazione servizio protezione vegetali destinatario</label>
            </div>
            <div class="input-field col s6 m8 l6">
              <input type="text" id="puntoEntrataDichiarato"
                value="${fn:escapeXml(dettaglioRichiesta.puntoEntrataDichiarato)}"
                disabled="disabled" /> 
              <label for="puntoEntrataDichiarato">Punto d'entrata dichiarato </label>
            </div>
            <div class="input-field col s6 m4 l3">
              <input type="text" id="numDocumentoTrasporto"
                value="${fn:escapeXml(dettaglioRichiesta.identifMezzoTrasporto)}"
                disabled="disabled" /> 
              <label for="numDocumentoTrasporto">N. Identificativo mezzo trasporto </label>
            </div>
            <div class="input-field col s6 m4 l3">
              <input type="text" id="mezzoTrasporto"
                value="${fn:escapeXml(dettaglioRichiesta.descModoTrasportoExp)}"
                disabled="disabled" /> 
              <label for="mwzzoTrasporto">Mezzo di trasporto dichiarato </label>
            </div>
						<div class="input-field col s6 m4 l6">
              <input type="text" id="luogoDeposito" 
                value="${fn:escapeXml(dettaglioRichiesta.luogoDeposito)}"
                disabled="disabled" />
              <label for="luogoDeposito">Luogo in cui è depositata la merce per l'ispezione</label>
            </div>
						<fmt:formatDate value="${dettaglioRichiesta.dataInizioDisponibilita}" pattern="dd/MM/yyyy" var="dataInizioDisponibilita" />
            <div class="input-field col s6 m4 l3">
              <input type="text" value="${dataInizioDisponibilita}" id="dataInizioDisponibilita" disabled="disabled" /> 
              <label for="dataInizioDisponibilita">Data inizio disponibilità merce</label>
            </div>
            <div class="input-field col s6 m4 l3">
              <input type="text" id="oraInizioDisponibilita" 
                value="${fn:escapeXml(dettaglioRichiesta.oraInizioDisponibilita)}"
                disabled="disabled" />
              <label for="oraInizioDisponibilita">Ora inizio disponibilità merce</label>
            </div>
            <fmt:formatDate value="${dettaglioRichiesta.dataPartenzaMerce}" pattern="dd/MM/yyyy" var="dataPartenzaMerce" />
            <div class="input-field col s6 m4 l3">
              <input type="text" value="${dataPartenzaMerce}" id="dataPartenzaMerce" disabled="disabled" /> 
              <label for="dataPartenzaMerce">Data partenza merce</label>
            </div>
            <div class="input-field col s12 m8 l6">
              <textarea id="note" class="materialize-textarea" rows="2"
                disabled="disabled">${fn:escapeXml(dettaglioRichiesta.noteTrasporto)}</textarea>
              <label for="note">Note</label>
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
									<th>Stato d'origine</th>
                  <th>Classe prodotto</th>
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
								</tr>
							</thead>
							<tbody id="bodyTabellaMerci">
								<c:forEach var="merceRichiesta" items="${listaMerci}"
									varStatus="s">
									<tr>
										<td>${fn:escapeXml(merceRichiesta.descNazioneOrigine)}</td>
										<td>${fn:escapeXml(merceRichiesta.descClasseProdotto)}</td>
										<td>${fn:escapeXml(merceRichiesta.descProdotto)}</td>
										<td><i>${fn:escapeXml(merceRichiesta.descGenere)}</i></td>
										<td><i>${fn:escapeXml(merceRichiesta.descSpecie)}</i></td>
										<td>${fn:escapeXml(merceRichiesta.descUnitaMisura)}</td>
										<td>${fn:escapeXml(merceRichiesta.numeroPezzi)}</td>
										<td><fmt:formatNumber type="number" pattern="##0.###"
                        value="${fn:escapeXml(merceRichiesta.quantitaLordaProdotto)}" /></td>
                    <td><fmt:formatNumber type="number" pattern="##0.###"
                        value="${fn:escapeXml(merceRichiesta.quantita)}" /></td>
										<td>${fn:escapeXml(merceRichiesta.descNaturaCollo)}</td>
										<td>${fn:escapeXml(merceRichiesta.numeroColli)}</td>
										<td>${fn:escapeXml(merceRichiesta.marchio)}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div class="row">
						<div class="input-field col s12 right-align">
							<a class="btn waves-effect back" href="#trasporto">INDIETRO</a>
							<a class="btn waves-effect forward" href="#tariffa">AVANTI</a>
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
							<h5>Tabella di riferimento per tipologie e tariffe della
								merce</h5>
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
              <input type="text" id="importoTotaleDovuto"
                value="${tariffaTotale}" disabled="disabled" /> 
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
            <spring:url value="/export/richieste/allegato/scarica/${dettaglioRichiesta.idRichiesta}" var="pathAllegatoPagamento" />
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
              <input type="text" id="tipoCertificato"
                value="${fn:escapeXml(dettaglioRichiesta.descTipoRichiesta)}" disabled="disabled" /> 
              <label for="tipoCertificato" class="bold">Tipo certificato</label>
            </div>
              
            <div class="input-field col s6 m8 l9">
              <c:choose>
              <c:when test="${not empty datiCertificato.idCertificato 
                and dettaglioRichiesta.idStatoRichiesta != statoInBozza
                and dettaglioRichiesta.idStatoRichiesta != statoInoltrata
                and dettaglioRichiesta.idStatoRichiesta != statoRestituita}">
                <a href='<spring:url value="/export/report/esporta/certificatoexport/${dettaglioRichiesta.idRichiesta}"/>'
                  class="btn green waves-effect waves-light right">STAMPA CERTIFICATO</a>   
              </c:when>
              <c:otherwise>
                <button class="btn green right" type="button" disabled="disabled">STAMPA CERTIFICATO</button>
              </c:otherwise>
              </c:choose>
            </div>
          </div>
          
          <spring:eval var="idTipoRiexport"
              expression="T(it.aizoon.ersaf.util.CaronteConstants).TIPO_RICHIESTA_RIEXPORT" />
          <c:if test="${dettaglioRichiesta.idTipoRichiesta == idTipoRiexport}">
          <div class="row">
            <div class="card-panel">
              <p class="center-align" style="font-size:12px;margin-bottom:5px;">
                <em><b>Si certifica</b> che i vegetali sono stati importati in Italia con le 
                seguenti caratteristiche:</em></p>
              <div class="row">
                <div class="input-field col s6 m6 l3">
                  <input type="text" id="tipoCertificatoPrecedente" 
                    value="${fn:escapeXml(datiCertificato.descCopiaConforme)}" disabled="disabled"> 
                  <label for="tipoCertificatoPrecedente">Tipo Certificato precedente</label>
                </div>
                <div class="input-field col s6 m6 l3">
                  <input type="text" id="merceColliOriginali" 
                    value="${fn:escapeXml(datiCertificato.descTipoImballaggio)}" disabled="disabled"> 
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
	                      <input type="checkbox" id="certificatoFitoOriginale" class="centered" 
	                        disabled="disabled" ${datiCertificato.conformeCertifOrig ? 'checked="checked"' : ''} />
	                      <span>del certificato fitosanitario originale</span>
                      </label>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col s12">
                      <label>
	                      <input type="checkbox" id="ispezioneSupplementare" class="centered" 
	                        disabled="disabled" ${datiCertificato.ispezioneSupplementare ? 'checked="checked"' : ''} />
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
							<input type="text" id="provinciaEsecuzione"
								value="${fn:escapeXml(datiCertificato.provinciaEsecuzione)}"
								disabled="disabled" /> 
							<label for="provinciaEsecuzione">Provincia esecuzione</label>
						</div>
						<div class="input-field col s6 m4 l3">
              <input type="text" id="comuneEsecuzione"
                value="${fn:escapeXml(datiCertificato.comuneEsecuzione)}"
                disabled="disabled" /> 
              <label for="comuneEsecuzione">Comune esecuzione</label>
            </div>
						<div class="input-field col s6 m4 l3">
							<fmt:formatDate
								value="${datiCertificato.dataEsecuzione}"
								pattern="dd/MM/yyyy" var="data" />
							<input type="text" value="${data}" id="dataEsecuzione"
								disabled="disabled" /> <label for="dataEsecuzione">Data
								esecuzione e rilascio certificato</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<input type="text" id="ispettore"
								value="${fn:escapeXml(datiCertificato.denominazioneIspettore)}"
								disabled="disabled" /> 
						  <label for="ispettore">Ispettore che rilascia il certificato</label>
						</div>
						<div class="input-field col s12 m4 l6">
              <input type="text" id="ispettoreFirmatario"
                value="${fn:escapeXml(datiCertificato.denominazioneIspettoreDocumentale)}"
                disabled="disabled" /> 
              <label for="ispettoreFirmatario">Ispettore firmatario</label>
            </div>
						<div class="input-field col s6 m4 l3">
              <input type="text" id="numeroCertificatoManuale"
                value="${fn:escapeXml(datiCertificato.numeroCertificatoManuale)}"
                disabled="disabled" /> 
              <label for="numeroCertificatoManuale">Numero certificato</label>
            </div>
						<div class="input-field col s12 m8 l6">
							<textarea id="dichiarazioneSupplementare" class="materialize-textarea"
								rows="2" disabled="disabled">${fn:escapeXml(datiCertificato.dichiarazioneSupplementare)}</textarea>
							<label for="dichiarazioneSupplementare">Dichiarazioni supplementari</label>
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
		<a href='<spring:url value="/export/richieste/elenco"/>'
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