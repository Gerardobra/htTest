<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/dropify.min.css"/>" media="screen" />
<style>
	
</style>
</head>
<body>

	<!-- div class="row" -->
		<div class="card row">
			<div class="card-title">
				<div class="hide-on-large-only bg-primary-color"
					style="color: rgba(255, 255, 255, 0.7); padding-bottom: 5px; padding-top: 5px; padding-left: 5px">
					<h6 style="color: #fff; font-size: 18px;" id="small_label">Dati Richiesta</h6>
				</div>
				<nav class="breadcrumb-nav col s12 hide-on-med-and-down">
					<div class="nav-wrapper">
						<div class="col s12">
						  <a href="#richiesta" data-value="richiesta" class="breadcrumb">&nbsp;&nbsp;Dati
                Richiesta</a>
              <a href="#mittente" data-value="mittente" class="breadcrumb">Mittente</a>
              <a href="#destinatario" data-value="destinatario" class="breadcrumb">Destinatario</a>
              <a href="#trasporto" data-value="trasporto" class="breadcrumb">Trasporto</a>
              <a href="#merce" data-value="merce" class="breadcrumb">Merce</a>
              <a href="#tariffa" data-value="tariffa" class="breadcrumb">Tariffa</a>
              <a href="#trattamento" data-value="trattamento" class="breadcrumb">Trattamento</a>
              <a href="#pagamento" data-value="pagamento" class="breadcrumb">Pagamento</a>
              <a style="display: none;"></a>
						</div>
					</div>
				</nav>
			</div>
			
			<div class="col s12" id="richiesta">
				<spring:url value="/import/richieste/nuova" var="formAction" />
				<form:form action="${formAction}" method="post" modelAttribute="nuovaRichiestaForm"
					accept-charset="utf-8" enctype="multipart/form-data">
					<div class="card-content">
						<div class="card-panel hoverable">Si sta creando una nuova richiesta di rilascio di
							nullaosta fitosanitario di importazione per merce in ingresso in Italia da Paesi Terzi,
							vincolato all’esito positivo dell’ispezione e del controllo fitosanitario da parte degli
							Ispettori Fitosanitari. Per confermare e procedere con la richiesta utilizzare il bottone
							«SALVA», in caso contrario utilizzare «TORNA ALLA RICERCA».</div>
						<div class="row">						
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="statoRichiesta" path="descStatoRichiesta" disabled="true" />
								<label for="statoRichiesta">Stato richiesta</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="utenteResponsabile" path="descUtenteResponsabile" disabled="true" />
								<label for="utenteResponsabile">Utente Responsabile</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="tipoSpedizioniere" path="denomTipoSpedizioniere"
									disabled="true" />
								<label for="tipoSpedizioniere">Tipo Operatore</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="spedizioniere" path="spedizioniere" disabled="true" />
								<label for="spedizioniere" style="white-space: nowrap">Denominazione Operatore</label>
							</div>
							<div class="input-field col s12 m8 l6">
								<form:textarea id="note" path="note" 
								  cssClass="materialize-textarea validate tooltipped" rows="2"
									data-length="1000" maxlength="1000" data-position="right" 
									data-tooltip="Note per l'Operatore (ad esempio informazioni di contatto)"/>
								<form:errors path="note" cssClass="error" />
								<label for="note">Note</label>
							</div>
						</div>
						
					</div>
					<div class="card-action">
            <div class="row">
              <c:if test="${nuovaRichiestaForm.schedaAbilitata > 1}">
              <a class="btn waves-effect forward left" href="#mittente">AVANTI</a>
              </c:if>
              
              <button class="btn confirm waves-effect waves-light right " type="submit" name="datiRichiesta">SALVA</button>
            </div>
          </div>
				</form:form>
			</div>

			<div class="col s12" id="mittente">
				<form:form action="${formAction}" method="post" modelAttribute="nuovaRichiestaForm"
					accept-charset="utf-8">
					<div class="card-content">
						<div class="row">
							<div class="input-field col s12 m8 l6">
								<form:input type="text" id="denominazioneMittente" path="denominazioneMittente"
									cssClass="validate" required="required" maxlength="100" />
								<form:errors path="denominazioneMittente" cssClass="error" />
								<label for="denominazioneMittente">Cognome e nome / Denominazione *</label>
							</div>
							<div class="input-field col s12 m8 l6">
								<form:input type="text" id="indirizzoMittente" path="indirizzoMittente" cssClass="validate"
									maxlength="200" />
								<form:errors path="indirizzoMittente" cssClass="error" />
								<label for="indirizzoMittente">Indirizzo (Es. Via Roma, 24)</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="comuneMittente" path="comuneMittente" cssClass="validate"
									required="required" maxlength="100" />
								<form:errors path="comuneMittente" cssClass="error" />
								<label for="comuneMittente">Città *</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<form:select id="nazioneMittente" path="idNazioneMittente" cssClass="validate"
									 required="required">
									<form:option value="" label="Selezionare" />
									<form:options items="${listaNazioni}" itemValue="idNazione" itemLabel="denomNazione" />
								</form:select>
								<label for="nazioneMittente">Stato *</label>
								<form:errors path="idNazioneMittente" cssClass="error" />
							</div>
						</div>
					</div>
					<div class="card-action">
            <div class="row">
              <a class="btn waves-effect back" href="#richiesta">INDIETRO</a>
              <c:if test="${nuovaRichiestaForm.schedaAbilitata > 2}">
              <a class="btn waves-effect forward" href="#destinatario">AVANTI</a>
              </c:if>
              <button class="btn confirm waves-effect waves-light right" type="submit" name="datiMittente">SALVA</button>
            </div>
          </div>
				</form:form>
			</div>
				
			<div class="col s12" id="destinatario">
        <form:form action="${formAction}" method="post" modelAttribute="nuovaRichiestaForm" accept-charset="utf-8">
          <div class="card-content">
            <div class="row">
              <div class="input-field col s12 m8 l6">
                <form:input type="text" id="denominazioneDestinatario" path="denominazioneDestinatario" 
                  cssClass="validate" required="required" maxlength="100" />
                <form:errors path="denominazioneDestinatario" cssClass="error" />
                <label for="denominazioneDestinatario">Cognome e nome / Denominazione *</label>
              </div>
              <div class="input-field col s12 m8 l6">
                <form:input type="text" id="indirizzoDestinatario" path="indirizzoDestinatario" 
                  cssClass="validate" required="required" maxlength="200" />
                <form:errors path="indirizzoDestinatario" cssClass="error" />
                <label for="indirizzoDestinatario">Indirizzo (Es. Via Roma, 24)*</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:select id="provinciaDestinatario" path="idProvincia" cssClass="validate" required="required">
                  <form:option value="" label="Selezionare" />
                  <form:options items="${listaProvince}" itemValue="idProvincia" itemLabel="denomProvincia" />
                </form:select>
                <form:errors path="idProvincia" cssClass="error" />
                <label for="provinciaDestinatario">Provincia *</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:select id="comuneDestinatario" path="idComuneIta" cssClass="validate" required="required">
                  <form:option value="" label="Selezionare" />
                  <form:options items="${listaComuni}" itemValue="idComune" itemLabel="denomComune" />
                </form:select>
                <form:errors path="idComuneIta" cssClass="error" />
                <label for="comuneDestinatario">Comune *</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:input type="text" id="codiceRup" path="codiceRup" cssClass="validate"
                  maxlength="20" />
                <form:errors path="codiceRup" cssClass="error" />
                <label for="codiceRup">Codice RUP</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:select id="regioneRup" path="idRegioneRup" cssClass="validate">
                  <form:option value="" label="Selezionare" />
                  <form:options items="${listaRegioni}" itemValue="idRegione" itemLabel="denomRegione" />
                </form:select>
                <form:errors path="idRegioneRup" cssClass="error" />
                <label for="regioneRup">Regione RUP</label>
              </div>
            </div>
          </div>
          <div class="card-action">
            <div class="row">
              <a class="btn waves-effect back" href="#mittente">INDIETRO</a>
              <c:if test="${nuovaRichiestaForm.schedaAbilitata > 3}">
              <a class="btn waves-effect forward" href="#trasporto">AVANTI</a> 
              </c:if>
              <button class="btn confirm waves-effect waves-light right" type="submit" name="datiDestinatario">SALVA</button>
            </div>
          </div>
        </form:form>
      </div>
        
      <div class="col s12" id="trasporto">
        <form:form action="${formAction}" method="post" modelAttribute="nuovaRichiestaForm"
            accept-charset="utf-8">
          <div class="card-content">
            <div class="row">
              <div class="input-field col s6 m4 l6">
                <form:input type="text" id="numDocumentoTrasporto" path="numDocumentoTrasporto"
                  cssClass="validate" required="required" maxlength="30" />
                <form:errors path="numDocumentoTrasporto" cssClass="error" />
                <label for="numDocumentoTrasporto">N. Documento di trasporto *</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:select id="mwzzoTrasporto" path="idModoTrasporto" cssClass="validate" required="required">
                  <form:option value="" label="Selezionare" />
                  <form:options items="${listaModiTrasporto}" itemValue="idModoTrasporto"
                    itemLabel="descModoTrasporto" />
                </form:select>
                <form:errors path="idModoTrasporto" cssClass="error" />
                <label for="mwzzoTrasporto">Mezzo di trasporto dichiarato *</label>
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
	                <form:checkbox path="spedizioneMultipla" id="spedizioneMultipla" cssClass="left centered" />
	                <span>Spedizione multipla</span>
                </label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:input type="text" id="numCertificatiCollegati" path="numCertificatiCollegati" 
                  cssClass="validate" required="required" maxlength="2" />
                <form:errors path="numCertificatiCollegati" cssClass="error" />
                <label for="numCertificatiCollegati">Numero certificati collegati *</label>
              </div>
            </div>
          </div>
          <div class="card-action">
            <div class="row">
              <a class="btn waves-effect back" href="#destinatario">INDIETRO</a>
              <c:if test="${nuovaRichiestaForm.schedaAbilitata > 4}">
              <a class="btn waves-effect forward" href="#merce">AVANTI</a> 
              </c:if>
              <button class="btn confirm waves-effect waves-light right" type="submit" name="datiTrasporto">SALVA</button>
           </div>
          </div>
        </form:form>
      </div>
        
      <div class="col s12" id="merce">
        <form:form id="formMerce" action="${formAction}" method="post" modelAttribute="nuovaRichiestaForm"
            accept-charset="utf-8">
          <input type="hidden" id="idTipoRichiesta" value="${nuovaRichiestaForm.idTipoRichiesta}" disabled="disabled" />
          <input type="hidden" id="numeroMerci" value="${fn:length(nuovaRichiestaForm.listaMerceRichiesta)}" disabled="disabled" />
          <div class="card-content">
            <div class="row">
              <div class="input-field col s6 m4 l3">
                <select id="nazioneOrigine" name="nazioneOrigine" data-error="#errorTxt1">
                  <option value="">Selezionare</option>
                  <c:forEach var="item" items="${listaNazioni}">
                  <option value="${item.idNazione}">${item.denomNazione}</option>
                  </c:forEach>
                </select>
                <label for="nazioneOrigine">Stato d'origine *</label>
                <div id="errorTxt1" style="height:0px"></div>
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
                <div id="errorTxt3" style="height:0px"></div>
                <input type="hidden" id="idGenereMerce" name="idGenereMerce" data-error="#errorTxt3" />
              </div>
              <div class="input-field col s6 m4 l3">
                <select id="specieMerce" name="specieMerce" data-error="#errorTxt4">
                  <option value="">Selezionare</option>
                  <!-- c:forEach var="item" items="${listaGeneriMerce}">
                  <option value="${item.idGenere}">${item.denomGenere}</option-->
                  <!--/c:forEach-->
                </select>
                <label for="specieMerce">Specie</label>
                <div id="errorTxt4" style="height:0px"></div>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:input type="text" id="unitaMisuraMerce" path="" disabled="true" />
                <label for="unitaMisuraMerce">Unità di misura</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <input type="text" id="quantitaMerce" name="quantitaMerce" maxlength="10" />
                <label for="quantitaMerce">Quantità *</label>
              </div>
              <div class="input-field col s6 m4 l3 right-align">
                <button type="button" class="btn clear-form waves-effect">PULISCI</button>
                <button type="button" id="aggiungiMerce" class="btn green waves-effect waves-light">AGGIUNGI</button>
              </div>
              <div class="input-field col s6 m4 l3 right-align">
                <button type="submit" id="salvaDatiMerce" name="datiMerce" formnovalidate="formnovalidate" 
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
	              <c:forEach var="merceRichiesta" items="${nuovaRichiestaForm.listaMerceRichiesta}" varStatus="s">
                  <tr>
                  <td>${merceRichiesta.descNazioneOrigine}
                    <form:input type="hidden" id="idMerceRichiesta" path="listaMerceRichiesta[${s.index}].idMerceRichiesta" />
                    <form:input type="hidden" id="idNazioneOrigine" path="listaMerceRichiesta[${s.index}].idNazioneOrigine" />
                    <form:input type="hidden" id="descNazioneOrigine" path="listaMerceRichiesta[${s.index}].descNazioneOrigine" />
                  </td>
                  <td>${merceRichiesta.descProdotto}
                    <form:input type="hidden" id="idProdotto" path="listaMerceRichiesta[${s.index}].idProdotto" /> 
                    <form:input type="hidden" id="descProdotto" path="listaMerceRichiesta[${s.index}].descProdotto" />
                  </td>
                  <td><i>${merceRichiesta.descGenere}</i>
                      <form:input type="hidden" id="idGenere" path="listaMerceRichiesta[${s.index}].idGenere" />
                      <form:input type="hidden" id="descGenere" path="listaMerceRichiesta[${s.index}].descGenere" />
                    </td>
                  <td><i>${merceRichiesta.descSpecie}</i>
                      <form:input type="hidden" id="idSpecie" path="listaMerceRichiesta[${s.index}].idSpecie" />
                      <form:input type="hidden" id="descSpecie" path="listaMerceRichiesta[${s.index}].descSpecie" />
                    </td>
                  <td>${merceRichiesta.descUnitaMisura}
                      <form:input type="hidden" id="descUnitaMisura" path="listaMerceRichiesta[${s.index}].descUnitaMisura" />
                    </td>
                  <td><fmt:formatNumber type="number" pattern="##0.###" value="${merceRichiesta.quantita}" />
                      <form:input type="hidden" id="quantita" path="listaMerceRichiesta[${s.index}].quantita" />
                    </td>
                    <td><fmt:formatNumber type="number" pattern="##0.00" value="${merceRichiesta.tariffaTeorica}" />
                      <form:input type="hidden" id="tariffaTeorica" path="listaMerceRichiesta[${s.index}].tariffaTeorica" />
                    </td>
                  <td nowrap style="white-spaces: nowrap"> 
                    <a id="eliminaMerce" title="Elimina" onClick="eliminaRiga(this)"
                      class="btn btn-floating btn-floating-medium deep-orange accent-2"> 
                    <i class="material-icons">delete</i></a> 
                  </td>
                </tr>
	              </c:forEach>
	              <tr id="rigaTemplate" style="display:none">
	                 <td nowrap style="white-spaces: nowrap"> 
                      <a id="eliminaMerce" title="Elimina" onClick="eliminaRiga(this)" 
                        class="btn btn-floating btn-floating-medium deep-orange accent-2"> 
                      <i class="material-icons">delete</i></a> 
                    </td>
	              </tr>
	            </tbody>
	          </table>
            </div>

          </div>
          <div class="card-action">
            <div class="row">
             <a class="btn waves-effect back" href="#trasporto">INDIETRO</a>
             <c:if test="${nuovaRichiestaForm.schedaAbilitata > 5}">
             <a class="btn waves-effect forward" href="#tariffa">AVANTI</a>
             </c:if> 
            </div>
          </div>
        </form:form>
      </div>
        
      <div class="col s12" id="tariffa">
        <form:form id="formTariffe" action="${formAction}" method="post" modelAttribute="nuovaRichiestaForm"
            accept-charset="utf-8">
         <div class="card-content">
           <div class="row">
             <div class="input-field col s6 m4 l3">
               <form:input type="text" id="tariffaIdentita" path="tariffaIdentita" disabled="true" />
               <label for="tariffaIdentita">Tariffa controlli d'identità (€)</label>
             </div>
             <div class="input-field col s6 m4 l3">
               <form:input type="text" id="tariffaDocumentali" path="tariffaDocumentali" disabled="true" />
               <label for="tariffaDocumentali">Tariffa controlli documentali (€)</label>
             </div>
             <div class="input-field col s6 m4 l3">
               <form:input type="text" id="tariffaPartite" path="tariffaFitosanitari" disabled="true" />
               <label for="tariffaPartite">Tariffa partite (€)</label>
             </div>
             <div class="input-field col s6 m4 l3">
               <form:input type="text" id="massimalePartite" path="massimalePartite" disabled="true" />
               <label for="massimalePartite">Massimale partite (€)</label>
             </div>
             <div class="input-field col s6 m4 l3">
               <form:input type="text" id="numeroCertificati" path="numeroCertificati" disabled="true" />
               <label for="numeroCertificati">Numero certificati</label>
             </div>
             <div class="input-field col s6 m4 l3">
               <form:input type="text" id="tariffaTotale" path="tariffaTotale" disabled="true" />
               <label for="tariffaTotale">Tariffa totale (€)</label>
             </div>
             <div class="input-field col s6 m4 l3 center">
               <a title="Legenda" class="btn modal-trigger btn-floating btn-large cyan hoverable" href="#legendaTariffe">
                 <i class="material-icons">live_help</i></a>
             </div>
             <div class="col s12 m9 l9">
	             <div class="card-panel hoverable">
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
               <h5>Tabella di riferimento per tipologie e tariffe della merce</h5>
               <table id="tabellaTariffe" class="responsive-table striped display">
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
                     <td><fmt:formatNumber type="number" pattern="##0.00" value="${tariffa.tariffa}" /></td>
                     <td>${tariffa.quantitaLimite}</td>
                     <td><fmt:formatNumber type="number" pattern="##0.00" value="${tariffa.incrementoTariffa}" /></td>
                     <td>${tariffa.incrementoQuantita}</td>
                     <td><fmt:formatNumber type="number" pattern="##0.00" value="${tariffa.massimaleTariffa}" /></td>
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
             <c:if test="${nuovaRichiestaForm.schedaAbilitata > 6}">
             <a class="btn waves-effect forward" href="#trattamento">AVANTI</a>
             </c:if>
            </div>
         </div>
        </form:form>
      </div>
        
      <div class="col s12" id="trattamento">
        <form:form id="formTrattamento" action="${formAction}" method="post" modelAttribute="nuovaRichiestaForm"
            accept-charset="utf-8">
          <div class="card-content">
            <div class="row">
              <div class="input-field col s6 m4 l3">
                <form:select id="trattamento" path="idTrattamento">
                  <form:option value="" label="Selezionare" />
                  <form:options items="${listaTrattamenti}" itemValue="idTrattamento" itemLabel="descTrattamento" />
                </form:select>
                <form:errors path="idTrattamento" cssClass="error" />
                <label for="trattamento">Trattamento</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:input type="text" id="prodottoChimico" path="prodottoChimico" maxlength="100" />
                <form:errors path="prodottoChimico" cssClass="error" />
                <label for="prodottoChimico">Prodotto chimico (sostanza attiva)</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:input type="text" id="durata" path="durata" maxlength="20" />
                <form:errors path="durata" cssClass="error" />
                <label for="durata">Durata</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:input type="text" id="temperatura" path="temperatura" maxlength="20" />
                <form:errors path="durata" cssClass="error" />
                <label for="temperatura">Temperatura</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:input type="text" id="concentrazione" path="concentrazione" maxlength="50" />
                <form:errors path="concentrazione" cssClass="error" />
                <label for="concentrazione">Concentrazione</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:input type='text' id="dataTrattamento" path="dataTrattamento" 
                  cssClass="datepicker" />
                <form:errors path="dataTrattamento" cssClass="error" />
                <label for="dataTrattamento">Data</label>
              </div>
              <div class="input-field col s6 m4 l3">
                <form:textarea id="infoSupplementari" path="infoSupplementari" cssClass="materialize-textarea validate" rows="2"
                    data-length="1000" maxlength="1000" />
                  <form:errors path="infoSupplementari" cssClass="error" />
                  <label for="infoSupplementari">Informazioni supplementari</label>
              </div>
            </div>
          </div>
          <div class="card-action">
            <div class="row">
              <a class="btn waves-effect back" href="#tariffa">INDIETRO</a>
              <c:if test="${nuovaRichiestaForm.schedaAbilitata > 7}">
              <a class="btn waves-effect forward" href="#pagamento">AVANTI</a>
              </c:if>
              
              <button class="btn confirm waves-effect waves-light right" type="submit" name="datiTrattamento">SALVA</button>
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
	            <spring:eval
					expression="T(it.aizoon.ersaf.security.SecurityUtils).getUtenteLoggato().isAutorizPagamPosticip()"
					var="autorizPagamPosticip" />
	            <button class="btn confirm waves-effect waves-light right"
	              type="submit" name="datiPagamento">SALVA</button>
	            <c:if test="${autorizPagamPosticip}">
	            	<button class="btn deep-orange lighten-2 waves-effect waves-light right" style="margin-right: 5px"
	              	type="submit" name="posticipaPagamento" formnovalidate="formnovalidate">POSTICIPA</button>
	            </c:if>
	          </div>
	        </div>
	      </form:form>
	    </div>
        
		</div>

		<div class="col s12 left-align">
			<a href='<spring:url value="/import/richieste/elenco"/>' class="btn waves-effect waves-light">TORNA
				ALLA RICERCA</a>
		</div>
		<br />
	<!-- /div-->

	<content tag="script"> 
	<script type="text/javascript" src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script>
	<script type="text/javascript" src='<spring:url value="/resources/js/dropify.min.js"/>'></script>
	<script>
	  var numeroMerci = $('#numeroMerci').val();
	  
		$(document).ready(function() {
			
			console.log('INIZIO ON READY NUOVA!!!', +Date.now());
			if (numeroMerci == 0) {
				$('#tabellaMerci').hide();	
			}
			
			$('.breadcrumb').click(function(e) {
				if (!$(this).attr('href')) {
					return;
				}
				
			  var current = $(this).data('value');
			  //var last_index = $(this).attr('href').length - 1;
			  
			  //$('#' + $(this).attr('href').substr(1, last_index)).show();
			  $('#' + current).show();
			  
			  $(this).css('color', '#fff');
			  
			  //var current = $(this).attr('href').substr(1, last_index);

			  $('.breadcrumb').each(function(index) {
				  /*if ($(this).attr('href').substr(1, $(this).attr('href').length - 1) != current) {
					  $('#' + $(this).attr('href').substr(1, $(this).attr('href').length - 1)).hide();
					  
					  $(this).css('color', 'rgba(255,255,255,0.7)');
					}else {
						$("#schedaSelezionata").val(index+1);
					}*/
					if ($(this).data('value') != current) {
               $('#' + $(this).data('value')).hide();
               
               $(this).css('color', 'rgba(255,255,255,0.7)');
             }else {
               $("#schedaSelezionata").val(index+1);
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
																
						  $(this).css('color','rgba(255,255,255,0.7)');
						} else {
							$(this).css('color', '#fff');
							$('#small_label').text($(this).text());
							$("#schedaSelezionata").val(index+1);
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
							
							$("#schedaSelezionata").val(index+1);
						}
					});
			});
			
			/*
			 * All'apertura della pagina si visualizza la scheda impostata nel controller, e si disabilitano le schede 
			 * successive alla scheda visitata con indice maggiore
			 */
			$( ".breadcrumb:gt(${nuovaRichiestaForm.schedaAbilitata-1})").removeAttr("href");
			$( ".breadcrumb:nth-child(${nuovaRichiestaForm.schedaSelezionata})").click();
			 
			//Popolamento dinamico della select
			$('#provinciaDestinatario').change(function() {
				  var params = {
						  "idProvincia": $(this).val()
					}
				  
				  getSelectAsincrona('comuneDestinatario', '<spring:url value="/ajax/getComuniProvincia" />', params, getOptionComune, 1);
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
			
			$('#aggiungiMerce').click(function() {
				/*
				  Reset del campo hidden del genere merce, se l'utente ha modificato il campo di testo Genere senza selezionare
				  un valore dell'autocomplete, l'hidden vale 0 e viene quindi svuotato per dare messaggio di errore nella validazione
				*/
				if ($('#idGenereMerce').val() == '0') {
					$('#idGenereMerce').val('');
				}
				
				validator = $("#formMerce").validate({
					rules: {
						nazioneOrigine: {
							required: true
						}, 
						idProdotto: {
							required: true
						},
						idGenereMerce: {
						  required: function(element) {
							  return $('#idProdotto').find(":selected").text().match(/ALTRO/i) === null;
							}
						},
						quantitaMerce: {
							required: true,
							fourDecimals: true,
							minStrict: 0,
							max: 999999999
						},
					},
					messages: {
						idGenereMerce: "Selezionare un genere",
					}
				});
		    
				validator.form();
				
				if ($("#formMerce").valid()) {
					/*
           * Si controlla che non si inserisca una merce con stesso prodotto, genere (ed eventualmente specie) già presente in tabella
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
					
					$(hiddenTemplate.replace(/NOME_CAMPO/g, 'idNazioneOrigine')).val($selectedOption.val()).appendTo($cella);
					
					$cella.append($(hiddenTemplate.replace(/NOME_CAMPO/g, 'idMerceRichiesta')));
					
					$cella.appendTo($riga);
					
					//Prodotto
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
                  
					//Quantità
					textValue = $('#quantitaMerce').val();
					
					$cella = $('<td></td>').text(textValue);
					
					$(hiddenTemplate.replace(/NOME_CAMPO/g, 'quantita')).val(textValue).appendTo($cella);
					
					$cella.appendTo($riga);
					
					//Tariffa teorica
					$cella = $('<td></td>');
                    
          $(hiddenTemplate.replace(/NOME_CAMPO/g, 'tariffaTeorica')).appendTo($cella);;

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
              
        	}).fail(function(jqxhr, textStatus, error) {
        		if (window.console) {
        			console.log( "Errore in reperimento valore asincrono: " + textStatus + ", " + error);
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
						title: 'Aggiungere almeno una merce',
						type: 'warning',
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
			
			$('#spedizioneMultipla').change(function() {
				checkSpedizioneMultipla();
			});
			
			checkSpedizioneMultipla();
			
			$('#allegatoPagamento').dropify( {
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
			console.log('DOPO INIT DROPIFY ALLEGATO PAGAMENTO!!!', +Date.now());
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
			}else {
				$('#formTrattamento input[type=text], #formTrattamento textarea').each(function(index) {
					if ($(this).attr('id')) {
						$(this).attr('disabled', true).val('');
					}
				});
			}
		}
		
		function checkSpedizioneMultipla() {
      /*
        Se la checkbox è selezionata, occorre abilitare il campo 'Numero certificati collegati', in caso contrario lo stesso 
        campo deve essere svuotato e disabilitato
      */
      if ($('#spedizioneMultipla').is(":checked")) {
    	  $('#numCertificatiCollegati').attr('disabled', false);
      }else {
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