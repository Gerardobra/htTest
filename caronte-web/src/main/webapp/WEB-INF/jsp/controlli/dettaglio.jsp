<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style>
ol-start {
  counter-reset: item 2
}
ol-start_2 {
  counter-reset: item 3
}
ol-start_3 {
  counter-reset: item 4
}
ol{
 counter-reset: item
}
li-lista {
  display: block
}
li-lista:before {
  content: counters(item, ".") " ";
  counter-increment: item
}
</style>
</head>
<body>
	<div class="card">
		
		<div class="card-title">
			<div class="hide-on-large-only bg-primary-color"
				style="color: rgba(255, 255, 255, 0.7); padding-bottom: 5px; padding-top: 5px; padding-left: 5px">
				<h6 style="color: #fff; font-size: 18px;" id="small_label">Dati Controllo</h6>
			</div>
			<!--  sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />-->
			<nav class="breadcrumb-nav col s12 hide-on-med-and-down bg-primary-color">
				<div class="nav-wrapper">
					<div class="col s12">					  
						<a href="#datibase" data-value="datibase" class="breadcrumb" style="color: #fff">&nbsp;&nbsp;Dati generali</a> 
						<c:if test="${nuovoControlloForm.tabDocumentale}">
							<a href="#documentale" data-value="documentale" class="breadcrumb">Documentale</a>
						</c:if>
						<c:if test="${nuovoControlloForm.tabIdentita}">
							<a href="#identita" data-value="identita" class="breadcrumb">Identità</a>
						</c:if>
						<c:if test="${nuovoControlloForm.tabFisico}">
							<a href="#fisico" data-value="fisico" class="breadcrumb">Fisico</a>
						</c:if>
						<c:if test="${nuovoControlloForm.tabFisico && nuovoControlloForm.flControllo5x23 != null && nuovoControlloForm.flControllo5x23 =='S' }">
							<a href="#campioni" data-value="campioni" class="breadcrumb">Campioni</a>
						</c:if>
						<c:if test="${not empty listaAllegati}">
							<a href="#allegati" data-value="allegati" class="breadcrumb">Allegati</a>
						</c:if>
						<!-- <a href="#monitoraggio" data-value="monitoraggio" class="breadcrumb">Monitoraggio</a>-->
						<c:if test="${nuovoControlloForm.misuraUfficiale != null && nuovoControlloForm.misuraUfficiale =='S' }">
							<a href="#misura" data-value="misura" class="breadcrumb">Misura</a>  
						</c:if>
						<a href="#esito" data-value="esito" class="breadcrumb">Esito</a>
					</div>
				</div>
			</nav>
		</div>	
		
				<spring:eval var="versioneBegin"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_CONTROLLO_BEGIN" />	
				<spring:eval var="versione042021"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_CONTROLLO_04_2021" />	
				
		<div class="card-content" style="padding-top:5px;">
			
			
			<!-- INIZIO TAB datibase -->
			<div id="datibase">
			  <div class="card-content">
				<div class="row">
					
						<!------ DATEPICKER::DATA CONTROLLO -------->
						<fmt:formatDate value="${controllo.dataControllo}" pattern="dd/MM/yyyy" var="dataControllo" />
						<div class="input-field col s6 m4 l4">
							<input type="text" id="dataControllo" 
								value="${dataControllo}"	disabled="disabled" /> 
							<label for="dataControllo">Data controllo</label>
						</div>
						
						<!------ TIMEPICKER::ORA INIZIO CONTROLLO -------->
						<div class="input-field col s6 m4 l4">
							<input type="text" id="oraInizioControllo"
								value="${fn:escapeXml(controllo.oraInizioControllo)}" disabled="disabled" />
							<label for="oraInizioControllo">Ora inizio controllo </label>
						</div>
						
						<!------ TIMEPICKER::ORA FINE CONTROLLO -------->
						<div class="input-field col s6 m4 l4">
							<input type="text" id="oraFineControllo"
								value="${fn:escapeXml(controllo.oraFineControllo)}" disabled="disabled" />
							<label for="oraFineControllo">Ora fine controllo </label>
						</div>
						
						<!------ INPUT::RAGIONE SOCIALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="denomSpedizioniere"
								value="${fn:escapeXml(spedizioniere.denomSpedizioniere)}" disabled="disabled" />
							<label for="denomSpedizioniere">Denominazione spedizioniere</label>
							
						</div>

						<!------ INPUT::COMUNE SEDE LEGALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="denomComuneSedeSociale"
								value="${fn:escapeXml(comuneSpedizioniere)}" disabled="disabled" />
							<label for="denomComuneSedeSociale">Comune sede legale</label>
						</div>
						
						<!------ INPUT::PROVINCIA SEDE LEGALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="provinciaSedeLegale"
								value="${fn:escapeXml(provinciaSpedizioniere)}" disabled="disabled" />
							<label for="provinciaSedeLegale">Provincia sede legale</label>
						</div>
						<!------ INPUT::INDIRIZZO SEDE LEGALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="indirizzoSedeSociale"
								value="${fn:escapeXml(spedizioniere.indirizzoSedeSociale)}" disabled="disabled" />
							<label for="indirizzoSedeSociale">Indirizzo	sede legale (Es. Via Roma, 24)</label>
						</div>
						<!------ INPUT::CUAA -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="cuaa"
								value="${fn:escapeXml(spedizioniere.cuaa)}" disabled="disabled" />
							<label for="cuaa">CUAA</label>
						</div>
						<!------ INPUT::PARTITA IVA -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="partitaIva"
								value="${fn:escapeXml(spedizioniere.partitaIVA)}" disabled="disabled" />
							<label for="partitaIva">Partita IVA</label>
						</div>

						<!------ INPUT::TELEFONO -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="numeroTelefono"
								value="${fn:escapeXml(spedizioniere.numeroTelefono)}" disabled="disabled" />
							<label for="numeroTelefono">Telefono (Es. 0245673467)</label>
						</div>

						<!------ INPUT::CELLULARE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="numeroCellulare"
								value="${fn:escapeXml(spedizioniere.numeroCellulare)}" disabled="disabled" />
							<label for="numeroCellulare">Cellulare (Es. 3471234567)</label>
						</div>

						<!------ INPUT::MAIL -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="emailSpedizioniere"
								value="${fn:escapeXml(spedizioniere.emailSpedizioniere)}" disabled="disabled" />
							<label for="emailSpedizioniere">Mail</label>
						</div>

						<!------ INPUT::PEC -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="pec"
								value="${fn:escapeXml(spedizioniere.pec)}" disabled="disabled" />
							<label for="pec">Pec</label>
						</div>

						<!------ INPUT::NUMERO ISCRIZIONE RUOP -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="codiceRUOP"
								value="${fn:escapeXml(spedizioniere.codiceRUOP)}" disabled="disabled" />
							<label for="codiceRUOP">Numero iscrizione RUOP</label>
						</div>

						<!------ INPUT::CODICE CENTRO AZIENDALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="codCentroAziendale"
								value="${fn:escapeXml(centroAziendale.codCentroAziendale)}" disabled="disabled" />
							<label for="codCentroAziendale">Codice centro aziendale</label>
						</div>

						<!------ INPUT::COMUNE CENTRO AZIENDALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="comuneCentroAziendale"
								value="${fn:escapeXml(comuneCentroAziendale)}" disabled="disabled" />
							<label for="comuneCentroAziendale">Comune centro aziendale</label>
						</div>

						<!------ INPUT::PROVINCIA CENTRO AZIENDALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="provinciaCentroAziendale"
								value="${fn:escapeXml(provinciaCentroAziendale)}" disabled="disabled" />
							<label for="provinciaCentroAziendale">Provincia centro aziendale</label>
						</div>

						<!------ INPUT::INDIRIZZO CENTRO AZIENDALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="indirizzoCentroAziendale"
								value="${fn:escapeXml(centroAziendale.indirizzo)}" disabled="disabled" />
							<label for="indirizzoCentroAziendale">Indirizzo centro aziendale (Es. Via Roma, 24)</label>
						</div>
						
						<!------ INPUT::SOGGETTI SUPPORTO SOPRALLUOGO -------->
						<div class="input-field col s6 m4 l3">
						<input type="text" id="soggettiSupportoSopralluogo"
								value="${fn:escapeXml(controllo.altriSoggetti)}" disabled="disabled" />
							<label for="soggettiSupportoSopralluogo">Cognome e nome dei tecnici presenti al controllo ufficiale 
							e motivo della presenza</label>
						</div>
						
						<div class="row col s12">
						<h6>Nome ispettore/agente</h6>
						<!------ TABELLA ISPETTORI DATIBASE -------->
							<div class=" container ">					
							<div class="col s12">
								<table id="tabellaIspettoriDatiBase" class="data-table striped display"
									data-order='[[ 2, "asc" ]]' data-paging="false"
									data-info="false">
									<thead>
										<tr>
											<th>Nome ispettore</th>
											<th>Ruolo</th>
											<th>Numero tessera</th>
										</tr>
									</thead>
									<tbody id="bodyTabellaConst">
										<c:forEach var="ispettoreDatiBase" items="${listaIspettoriDatiBase}">
												<tr>
													<td>${ispettoreDatiBase.descIspettore}</td>
													<td>${ispettoreDatiBase.ruolo}</td>
													<td>${ispettoreDatiBase.numeroTessera}</td>
												</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							</div>
						

					</div>									
						
						<div class="row col s12">
							<h6>Norme applicate in verbale</h6>
							<!------ SELECT::NORMA APPLICATIVA VERBALE -------->
							<c:forEach items="${listaNorme}" var="norma">
								<div class="input-field col s4 m4 l4">
									<label> <input type="checkbox"  checked disabled>
									<span>${norma.descBreve}</span>
									</label>
								</div>
							</c:forEach>

							<!------ INPUT::ALTRE NORME -------->
							<div class="input-field col s6 m4 l3">
							<input type="text" id="altreNorme"
									value="${fn:escapeXml(controllo.altreNorme)}" disabled="disabled" />
								<label for="altreNorme">Altra norma: </label>
							</div>
						</div>
						
						<div class="col container divider s12" style="margin-bottom: 1em;"></div>
						<h6>Elenco delle attività inserite in fase di domanda registrazione RUOP:</h6>
						<!------ TABELLA TIPOLOGIA ATTIVITA -------->
						<div class="row container s12">						
							<div class="col s12">
								<table id="tabellaAttivita" class="data-table striped display"
									data-order='[[ 2, "asc" ]]' data-paging="false"
									data-info="false">
									<thead>
										<tr>
											<th>Tipologia attività</th>
											<th>Materiale</th>
										</tr>
									</thead>
									<tbody id="bodyTabellaAttivita">
										<c:forEach var="attivita" items="${tipologieAttDb}">
											<c:choose>
												<c:when test="${attivita != null}">
													<tr>
														<td>${attivita.denomTipologiaEstesa}</td>
														<td>${attivita.descEstesa}</td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<td>${attivita.denomTipologiaEstesa}</td>
														<td></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						
						<!------ TIPOLOGIA ATTIVITA ANNOTAZIONI-------->
						<div class="input-field col s12 m8 l12">
							<input type="text" id="tipologiaAttivita"
								value="${fn:escapeXml(spedizioniere.tipologiaAttivita)}" disabled="disabled" />
							<label for="tipologiaAttivita">Tipologia Attività annotazioni</label>
						</div>
						
						<c:if test="${abilitaSementi}">
							<div class="row container s12">						
								<div class="col s12">
									<table id="tabellaSementi" class="data-table striped display"
										data-order='[[ 1, "asc" ]]' data-paging="false"
										data-info="false">
										<thead>
											<tr>
												<th>Tipo Semente</th>
												<th>Quantita</th>
												<th>Note</th>
											</tr>
										</thead>
										<tbody id="bodyTabellaSementi">
											<c:forEach var="tipoSemente" items="${listaSementi}">
												
														<tr>
															<td>${tipoSemente.descEstesa}</td>
															<td>${tipoSemente.quantita}</td>
															<td>${tipoSemente.note}</td>
														</tr>
													
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</c:if>
						
						<div class="col container divider s12" style="margin-bottom: 1em;"></div>
						<div class="row col s12">
							<h6>Responsabile dell'azienda che assiste al controllo</h6>
							<!------ NOME COGNOME RESPONSABILE AZIENDA CHE ASSISTE AL CONTROLLO -------->
							<div class="input-field col s6">
								<input type="text" id="responsabileAzienda"
									value="${fn:escapeXml(controllo.responsabileAzienda)}" disabled="disabled" />
								<label for="responsabileAzienda">Nome e cognome</label>
							</div>

							<!------ QUALIFICA -------->
							<div class="input-field col s6">
								<input type="text" id="qualificaResponsabile"
									value="${fn:escapeXml(qualificaResponsabile)}" disabled="disabled" />
								<label for="qualificaResponsabile">Qualifica</label>								
							</div>
						</div>
						
						<div class="row col s12">
							<h6>Tipologia Controllo Eseguito</h6>
							<!------ SELECT::NORMA APPLICATIVA VERBALE -------->
							<c:forEach items="${listaTipologiaControllo}" var="tipoControllo">
								<div class="input-field col s4 m4 l4">
									<label> <input type="checkbox"  checked disabled>
									<span>${tipoControllo.descBreve}</span>
									</label>
								</div>
							</c:forEach>

							<!------ INPUT::ALTRO CONTROLLO 
							<div class="input-field col s6 m4 l3">
							<input type="text" id="altreNorme"
									value="${fn:escapeXml(controllo.altreNorme)}" disabled="disabled" />
								<label for="altreNorme">Altra norma: </label>
							</div>-------->
						</div>


				</div>	
			  </div>			
			</div>
			<!-- FINE TAB datibase -->
			
			<!-- INIZIO TAB CONTROLLODOCUMENTALE -->
			<c:if test="${nuovoControlloForm.tabDocumentale}">
			<div id="documentale">
			  <div class="card-content">
				<div class="row">
		  					<p class="center-align"
									style="font-size: 20px; margin-bottom: 5px;">
									<em>Controllo Documentale</em>
							</p>
		  		 </div>		
				<div class="row">
				  <ol-start>
				  <!-- 3 -->				   
				   <li-lista><b>Controllo documentale</b>
				      <ol style="list-style-type: decimal;">
				      
				   	<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
				          <!-- 3.1 -->
				       		<li-lista><b>L'OP è iscritto al RUOP/autorizzato art. 19 D.lgs 214/05 :</b>
	         						<div class="row">
		         						<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x1 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x1 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x1 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>
												
										<input type="text" id="descControllo3x1"
											value="${fn:escapeXml(controlloDocumentale.descControllo3x1)}" disabled="disabled" />
										<label for="descControllo3x1">Codice iscrizione RUOP/n. autorizzazione:</label>
									</div>
											
						
                            <ol style="list-style-type: decimal;">										
									  <!-- 3.1.1 -->	
					        			<li-lista><b>La tipologia di attività riportata nel documento di iscrizione al RUOP è adeguata alla tipologia di attività svolta dall'OP</b>
					                     <div class="row">
					                       	<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x1x1 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x1x1 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x1x1 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>											
										</div>
										<div class=row">
										 	<div class="input-field col s6 m5 l12">
												<input type="text" id="noteControllo3x1x1"
													value="${fn:escapeXml(controlloDocumentale.noteControllo3x1x1)}" disabled="disabled" />
												<label for="noteControllo3x1x1">Annotazioni</label>	
											</div>
										</div>	
									   </li-lista>			     			
				   		    </ol>												
				  </li-lista>	
				  </c:if>
				  
				  <c:if test="${nuovoControlloForm.idVersioneControllo >= versione042021}">	
				 		<!-- 3.1 V2 -->	
					     <li-lista><b>La tipologia di attività riportata nel documento di iscrizione al RUOP è adeguata alla tipologia di attività svolta dall'OP</b>
					                   <div class="row">
						                    <c:choose>
												<c:when test="${controlloDocumentale.flControllo3x1x1 == 'S'}">
						         					<input type="radio" checked><span>Si</span>
												</c:when>
												<c:when test="${controlloDocumentale.flControllo3x1x1 == 'N'}">
						         					<input type="radio" checked><span>No</span>
												</c:when>
												<c:when test="${controlloDocumentale.flControllo3x1x1 == 'NA'}">
						         					<input type="radio" checked><span>NA</span>
												</c:when>	
												<c:otherwise>
													<input type="radio"><span>Si</span>
													<input type="radio"><span>No</span>
													<input type="radio"><span>NA</span>
												</c:otherwise>
											</c:choose>											
										</div>
										 	
						</li-lista>		
				 </c:if>				
				 									
                  <!-- 3.2 -->				   						  
				  <li-lista><b>La domanda di iscrizione al RUOP inviata al servizio è provvista di marca da bollo</b>
				     <div class="row">														
										<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x2 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x2 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x2 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>		
							
					</div>
				  </li-lista>	
				  
				  <c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">	
				  <!-- 3.3 -->	
				  <li-lista><b>L'OP è autorizzato all'uso del passaporto:</b>
				                   <div class="row">
				                   		<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x3 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x3 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x3 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>										 									
									</div>
									<!-- 3.3.1 -->
									<ol style="list-style-type: decimal;">
									  <li-lista><b>L'autorizzazione al passaporto è adeguata alla tipologia di piante, prodotti vegetali e altri oggetti prodotte dall'OP:</b>
					                       <div class="row">
					                       				<c:choose>
															<c:when test="${controlloDocumentale.flControllo3x3x1 == 'S'}">
									         					<input type="radio" checked><span>Si</span>
															</c:when>
															<c:when test="${controlloDocumentale.flControllo3x3x1 == 'N'}">
									         					<input type="radio" checked><span>No</span>
															</c:when>
															<c:when test="${controlloDocumentale.flControllo3x3x1 == 'NA'}">
									         					<input type="radio" checked><span>NA</span>
															</c:when>	
															<c:otherwise>
																<input type="radio"><span>Si</span>
																<input type="radio"><span>No</span>
																<input type="radio"><span>NA</span>
															</c:otherwise>
														</c:choose>																																	
										 </div>
										 <div class=row">
										 	<div class="input-field col s6 m5 l12">
										 		<input type="text" id="noteControllo3x3x1"
													value="${fn:escapeXml(controlloDocumentale.noteControllo3x3x1)}" disabled="disabled" />
												<label for="noteControllo3x3x1">Annotazioni</label>	
											</div>		
										</div>
									  </li-lista>		
								   </ol>
                  </li-lista>
                  </c:if>	
                  
                  <c:if test="${nuovoControlloForm.idVersioneControllo >= versione042021}">
                  <!-- 3.3 V2 -->
									  <li-lista><b>L'autorizzazione al passaporto è adeguata alla tipologia di piante, prodotti vegetali e altri oggetti prodotte dall'OP:</b>
					                       <div class="row">
					                       				<c:choose>
															<c:when test="${controlloDocumentale.flControllo3x3x1 == 'S'}">
									         					<input type="radio" checked><span>Si</span>
															</c:when>
															<c:when test="${controlloDocumentale.flControllo3x3x1 == 'N'}">
									         					<input type="radio" checked><span>No</span>
															</c:when>
															<c:when test="${controlloDocumentale.flControllo3x3x1 == 'NA'}">
									         					<input type="radio" checked><span>NA</span>
															</c:when>	
															<c:otherwise>
																<input type="radio"><span>Si</span>
																<input type="radio"><span>No</span>
																<input type="radio"><span>NA</span>
															</c:otherwise>
														</c:choose>																																	
										 </div>
									  </li-lista>
					</c:if>		
												   
                  <!-- 3.4 -->								   													  
				  <li-lista><b>La richiesta di passaporto inviata al servizio è conforme alle disposizioni sul bollo</b>
				       <div class="row">														
									<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x4 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x4 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x4 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>	
                        </div>															
					
				  </li-lista>	
				  <!-- 3.5 -->
				  <li-lista><b>L’autorizzazione al passaporto è conforme alle disposizioni sul bollo e il numero sulla marca da bollo corrisponde al numero riportato nel documento</b>
				    <div class="row">														
									<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x5 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x5 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x5 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>	
					</div>																			
				  </li-lista>
				  
				  <c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
				  <!-- 3.6 -->
				  <li-lista><b>L'OP espone presso il CA copia del documento di iscrizione al RUOP:</b>
				       <div class="row">														
									<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x6 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x6 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x6 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>	
					   </div>																														   
				  </li-lista>
				  </c:if>
				  
				  <!-- 3.7 -->
				  <li-lista><b>L'OP riporta il numero dell'iscrizione al RUOP sui documenti (carta intestata, fatture, DDT, timbri, passaporti, etc):</b>
				    <div class="row">														
										<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x7 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x7 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x7 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>	
					</div>																						  
				  </li-lista>
				  <!-- 3.8 -->
				  <li-lista><b>L'OP comunica annualmente l'elenco delle piante in produzione secondo le indicate dal Servizio per ogni CA autorizzato</b>
				     <div class="row">														
								<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x8 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x8 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x8 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>																														
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
										 		<input type="text" id="noteControllo3x8"
													value="${fn:escapeXml(controlloDocumentale.noteControllo3x8)}" disabled="disabled" />
												<label for="noteControllo3x8">Annotazioni</label>	
											</div>		
				    </div>
				    </c:if>
				  </li-lista>
				  <!-- 3.9 -->
				  <li-lista><b>L’OP paga annualmente la tariffa fitosanitaria dovuta</b>
				  	<div class="row">				
				  						<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x9 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x9 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x9 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>																																	
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 <div class="input-field col s6 m5 l12">
										 		<input type="text" id="noteControllo3x9"
													value="${fn:escapeXml(controlloDocumentale.noteControllo3x9)}" disabled="disabled" />
												<label for="noteControllo3x9">Annotazioni</label>
										</div>		
				    </div>
				    </c:if>
				  </li-lista>
				  <!-- 3.10 -->
				  <li-lista><b>L'OP tiene la tracciabilità delle piante acquistate, cedute o spostate in ambito aziendale per almeno 3 anni</b>
				     <div class="row">	
				     					<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x10 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x10 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x10 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>																																														
					 </div>
				  </li-lista>
				  <!-- 3.11 -->
				  <li-lista><b>L'OP ha il registro dei trattamenti e provvede alla registrazione entro 30 giorni dal trattamento</b>
				  	<div class="row">														
										<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x11 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x11 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x11 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>																																							
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
											 	<input type="text" id="noteControllo3x11"
														value="${fn:escapeXml(controlloDocumentale.noteControllo3x11)}" disabled="disabled" />
													<label for="noteControllo3x11">Annotazioni</label>												
											</div>		
				    </div>
				    </c:if>
				  </li-lista>
				  <!-- 3.12 -->
				  <li-lista><b>L'OP tiene presso il CA la planimetria aggiornata dei propri campi di produzione, dei magazzini e dei vegetali in produzione</b>
				  		<div class="row">				
				  						<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x12 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x12 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x12 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>																																			
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
										 		<input type="text" id="noteControllo3x12"
														value="${fn:escapeXml(controlloDocumentale.noteControllo3x12)}" disabled="disabled" />
													<label for="noteControllo3x12">Annotazioni</label>	
											</div>		
				    </div>			
				    </c:if>	  
				  </li-lista>
				  <!-- 3.13 -->
				  <li-lista><b>L'OP possiede un fascicolo aziendale aggiornato con i mappali a vivaio georeferenziati</b>
				     <div class="row">														
									<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x13 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x13 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x13 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>																															
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
										 		<input type="text" id="noteControllo3x13"
														value="${fn:escapeXml(controlloDocumentale.noteControllo3x13)}" disabled="disabled" />
													<label for="noteControllo3x13">Annotazioni</label>											
											</div>		
				    </div>
				    </c:if>
				  </li-lista>	
				  <!-- 3.14 -->
				  <li-lista><b>L'OP compila i passaporti in modo corretto e li rilascia quando previsto</b>
				  	<div class="row">														
									<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x14 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x14 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x14 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>																													
					</div>
				  </li-lista>
				  <!-- 3.15 -->
				  <li-lista><b>L'OP compila il documento di commercializzazione in modo corretto e lo rilascia quando previsto</b>
				      <div class="row">														
									<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x15 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x15 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x15 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>																															
						</div>
						<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
										 		<input type="text" id="noteControllo3x15"
														value="${fn:escapeXml(controlloDocumentale.noteControllo3x15)}" disabled="disabled" />
													<label for="noteControllo3x15">Annotazioni</label>
							              	</div>										 										 											    	
				    </div>
				    </c:if>
				  </li-lista>
				  <!-- 3.16 -->
				  <li-lista><b>Nel caso di produzioni forestali è presente il registro di carico e scarico dei materiali forestali:</b>
				      <div class="row">														
									<c:choose>
											<c:when test="${controlloDocumentale.flControllo3x16 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x16 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloDocumentale.flControllo3x16 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>	
                     </div> 
				  </li-lista>			  	
				  </ol> 				
				</ol>  					
											
												
				</div>	
			  </div>			
			</div>
			</c:if>
			<!-- FINE TAB CONTROLLODOCUMENTALE -->
			
			<!-- INIZIO TAB CONTROLLOIDENTITA -->
			<c:if test="${nuovoControlloForm.tabIdentita}">
			<div id="identita">
			  <div class="card-content">
				<div class="row">
						<ol-start_2 > <!-- 4 --> <li-lista>
						<b>Controllo di identità</b>
						<ol style="list-style-type: decimal;">
							<!-- 4.1 -->
							<li-lista>
							<b>Le piante presenti nel corso dei controlli presso il Centro Aziendale corrispondono alla tipologia produttiva dichiarata annualmente dall’OP</b>
							<div class="row">
										<c:choose>
											<c:when test="${controlloIdentita.flControllo4x1 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloIdentita.flControllo4x1 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloIdentita.flControllo4x1 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>		
							</div>
							</li-lista>
							<!-- 4.2 -->
							<li-lista>
							<b>I mappali di coltivazione coincidono con i mappali
								dichiarati a vivaio nel fascicolo aziendale</b>
							<div class="row">
										<c:choose>
											<c:when test="${controlloIdentita.flControllo4x2 == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${controlloIdentita.flControllo4x2 == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>
											<c:when test="${controlloIdentita.flControllo4x2 == 'NA'}">
					         					<input type="radio" checked><span>NA</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
												<input type="radio"><span>NA</span>
											</c:otherwise>
										</c:choose>
							</div>
							</li-lista>
						</ol>
						
						
						
					<div class="row">
						<table id="tabellaSitiProduzione"
							class="data-table striped display" data-paging="false"
							data-info="false">
							<thead>
								<tr>
									<th>Sito di produzione</th>
									<th>Indirizzo</th>
									<th>Comune</th>
									<th>Foglio</th>
									<th>Mappale</th>
									<th>Superficie (mq.)</th>
								</tr>
							</thead>
							<tbody id="bodyTabellaSitiProduzione">
								<c:forEach var="sitoProd" items="${sitiProduzioneDb}">
									<tr>
										<td>${sitoProd.denominazione}</td>
										<td>${sitoProd.ubicazione}</td>
										<td>${sitoProd.denomComune}(${sitoProd.siglaProvincia})</td>
										<td>${sitoProd.foglio}</td>
										<td>${sitoProd.mappale}</td>
										<td>${sitoProd.superficie}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- FINE tabella Siti di produzione -->
				</div>
			  </div>			
			</div>
			</c:if>
			<!-- FINE TAB CONTROLLOIDENTITA -->
			
			<!-- INIZIO TAB CONTROLLOFISICO -->
			<c:if test="${nuovoControlloForm.tabFisico}">
			<div id="fisico">
			  <div class="card-content">
				<div class="row">
				  <ol-start_3 >
				  <!-- 5 -->				   
				   <li-lista><b>Controllo Fisico</b>
				      <ol style="list-style-type: decimal;">
				          <!-- 5.1 -->
				       		<li-lista><b>L’OP possiede strutture e attrezzature adeguate all’attività produttiva:</b>
				         						<div class="row">	
				         							<c:choose>
														<c:when test="${controlloFisico.flControllo5x1 == 'S'}">
								         					<input type="radio" checked><span>Si</span>
														</c:when>
														<c:when test="${controlloFisico.flControllo5x1 == 'N'}">
								         					<input type="radio" checked><span>No</span>
														</c:when>
														<c:when test="${controlloFisico.flControllo5x1 == 'NA'}">
								         					<input type="radio" checked><span>NA</span>
														</c:when>	
														<c:otherwise>
															<input type="radio"><span>Si</span>
															<input type="radio"><span>No</span>
															<input type="radio"><span>NA</span>
														</c:otherwise>
													</c:choose>
												</div>
                            <ol style="list-style-type: decimal;">	
									  <!-- 5.1.1-->	
					        			<li-lista>Elenco delle strutture e delle attrezzature presenti:
					                       <div class="row">					
					                       			<c:forEach items="${listaStruttureAttrezzature}" var="struttAttr">
														<div class="input-field col s4 m4 l4">
															<label> <input type="checkbox"  checked disabled>
															<span>${struttAttr.descBreve}</span>
															</label>
														</div>
													</c:forEach>	
										   </div>
									   </li-lista>			     			
				   		    </ol>												
				  </li-lista>	
				      <!-- 5.2 -->
				  <li-lista><b>Esiste la netta separazione tra la zona di produzione e la zona di vendita	</b>
				      <div class="row">														
													<c:choose>
														<c:when test="${controlloFisico.flControllo5x2 == 'S'}">
								         					<input type="radio" checked><span>Si</span>
														</c:when>
														<c:when test="${controlloFisico.flControllo5x2 == 'N'}">
								         					<input type="radio" checked><span>No</span>
														</c:when>
														<c:when test="${controlloFisico.flControllo5x2 == 'NA'}">
								         					<input type="radio" checked><span>NA</span>
														</c:when>	
														<c:otherwise>
															<input type="radio"><span>Si</span>
															<input type="radio"><span>No</span>
															<input type="radio"><span>NA</span>
														</c:otherwise>
													</c:choose>
                     </div> 
				  </li-lista>				  										
                  <!-- 5.3 -->				   						  
				  <li-lista><b>Tecniche di produzione</b>
				     <div class="row">			
				     			<c:forEach items="${listaMetodiProduzione}" var="metodo">
									<div class="input-field col s4 m4 l4">
										<label> <input type="checkbox"  checked disabled>
												<span>${metodo.descBreve}</span>
										</label>
									</div>
								</c:forEach>						  
					</div>

				  </li-lista>	
				  <!-- 5.4 -->	
				  <li-lista><b>L’OP utilizza un substrato di coltivazione è idoneo al fine di garantire l’assenza di ON</b>
				                   <div class="row">													
													<c:choose>
														<c:when test="${controlloFisico.flControllo5x4 == 'S'}">
								         					<input type="radio" checked><span>Si</span>
														</c:when>
														<c:when test="${controlloFisico.flControllo5x4 == 'N'}">
								         					<input type="radio" checked><span>No</span>
														</c:when>
														<c:when test="${controlloFisico.flControllo5x4 == 'NA'}">
								         					<input type="radio" checked><span>NA</span>
														</c:when>	
														<c:otherwise>
															<input type="radio"><span>Si</span>
															<input type="radio"><span>No</span>
															<input type="radio"><span>NA</span>
														</c:otherwise>
													</c:choose>					
									</div>	
                  </li-lista>								   
                  <!-- 5.5 -->								   													  
				  <li-lista><b>Nel caso di coltivazione in contenitore, i contenitori sono adeguatamente isolati dal suolo </b>
				       <div class="row">														
													<c:choose>
														<c:when test="${controlloFisico.flControllo5x5 == 'S'}">
								         					<input type="radio" checked><span>Si</span>
														</c:when>
														<c:when test="${controlloFisico.flControllo5x5 == 'N'}">
								         					<input type="radio" checked><span>No</span>
														</c:when>
														<c:when test="${controlloFisico.flControllo5x5 == 'NA'}">
								         					<input type="radio" checked><span>NA</span>
														</c:when>	
														<c:otherwise>
															<input type="radio"><span>Si</span>
															<input type="radio"><span>No</span>
															<input type="radio"><span>NA</span>
														</c:otherwise>
													</c:choose>
                        </div>
				  </li-lista>	
				  <!-- 5.6 -->
				  <li-lista><b>L’OP mantiene la separazione tra i diversi lotti di produzione e le produzioni sono identificate con apposite etichette</b>
				    <div class="row">														
							<c:choose>
								<c:when test="${controlloFisico.flControllo5x6 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x6 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x6 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>
					</div>											  																																																							
				  </li-lista>
				  <!-- 5.7 -->
				  <li-lista><b>L’OP utilizza materiale di moltiplicazione adeguatamente tracciato e nel caso di autoproduzione mette in atto tutte le procedure per garantire la sanità e la qualità del materiale prodotto</b>
				       <div class="row">		
							<c:choose>
								<c:when test="${controlloFisico.flControllo5x7 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x7 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x7 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>		  
					   </div>																						   
				  </li-lista>
				  <!-- 5.8 -->
				  <li-lista><b>L’OP ha attuato una corretta gestione per lo stoccaggio e l’eliminazione dei residui vegetali e del materiale non idoneo alla commercializzazione</b>
				     <div class="row">										
				     		<c:choose>
								<c:when test="${controlloFisico.flControllo5x8 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x8 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x8 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>	
					 </div>
				  </li-lista>
				  <!-- 5.9 -->
				  <li-lista><b>Il luogo di produzione e le immediate vicinanze sono adeguatamente ripulite dalle le erbe infestanti al fine di ridurre i rischi fitosanitari:	</b>
				  	<div class="row">														
							<c:choose>
								<c:when test="${controlloFisico.flControllo5x9 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x9 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x9 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>																																			
					</div>
				  </li-lista>
				  <!-- 5.10 -->
				  <li-lista><b>L’acqua utilizzata per l’irrigazione costituisce un rischio fitosanitario: </b>
  						
  						<div class="row">
  							<c:choose>
								<c:when test="${controlloFisico.flControllo5x10 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x10 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x10 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>											
						</div>	
											<div class="row">
												<b>Approvvigionamento dell’acqua d’irrigazione:</b>
											</div>
											<div class="row">		
										  			<c:forEach items="${listaTipiIrrigazione}" var="tipi">
														<div class="input-field col s4 m4 l4">
															<label> <input type="checkbox"  checked disabled>
															<span>${tipi.descBreve}</span>
															</label>
														</div>
													</c:forEach>	
											</div>	
													

				  </li-lista>
				  <!-- 5.11 -->
				  <li-lista><b>Il luogo di produzione ricade all’interno di aree delimitate ufficialmente dal Servizio a seguito di accertamento di organismi nocivi da quarantena: </b>
				  	<div class="row">		
				  			<c:choose>
								<c:when test="${controlloFisico.flControllo5x11 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x11 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x11 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>					  														
					</div>
					
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin and controlloFisico.flControllo5x11 == 'S'}">
						<div class="row">
									<div class="input-field col s6 m5 l12">
											 	<input type="text" id="noteControllo5x11"
														value="${fn:escapeXml(controlloFisico.noteControllo5x11)}" disabled="disabled" />
													<label for="noteControllo5x11">Organismo Nocivo</label>
									</div>		
					    </div>
					</c:if>
					<c:if test="${nuovoControlloForm.idVersioneControllo >= versione042021}">
						
						<div class="row">
								<table id="tabellaON" class="data-table striped display"
									data-orderable='false' data-paging="false" data-info="false">
									<thead>
										<tr>
											<th>Organismo Nocivo</th>
											<th>Codice Eppo</th>
										</tr>
									</thead>
									<tbody id="bodyTabellaON">
										<c:forEach items="${listaOrgNoc}" var="orgNoc" varStatus="status">
											<tr>
												<td>${orgNoc.descEstesa}</td>
												<td>${orgNoc.codiceEppo}</td>
												</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							
					</c:if>
				  </li-lista>
				  
				  <!-- 5.12 -->
				  <li-lista><b>L’OP è informato sull’obbligo di comunicare al Servizio le variazioni dei dati dichiarati in sede di 
				  presentazione della domanda di registrazione al RUOP e la cessazione dell’attività: </b>
				  		<div class="row">														
							<c:choose>
								<c:when test="${controlloFisico.flControllo5x12 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x12 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x12 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>																		
					</div>
				  </li-lista>
				  <!-- 5.13 -->
				  <li-lista><b>L’OP è informato sull’obbligo derivante dalla presenza o sospetta presenza di un ON sulle piante in produzione di cui all’art. 14 del reg. 2016/2031</b>
				     <div class="row">	
				     		<div class="row">														
								<c:choose>
									<c:when test="${controlloFisico.flControllo5x13 == 'S'}">
										<input type="radio" checked><span>Si</span>
									</c:when>
									<c:when test="${controlloFisico.flControllo5x13 == 'N'}">
									     <input type="radio" checked><span>No</span>
									</c:when>
									<c:when test="${controlloFisico.flControllo5x13 == 'NA'}">
									     <input type="radio" checked><span>NA</span>
									</c:when>	
									<c:otherwise>
										<input type="radio"><span>Si</span>
										<input type="radio"><span>No</span>
										<input type="radio"><span>NA</span>
									</c:otherwise>
								</c:choose>															
						</div>
				  </li-lista>	
				  <!-- 5.14 -->
				  <li-lista><b>L’OP è a conoscenza delle procedure sulla tracciabilità previste dall’art 69 del reg. 2016/2031</b>
				  	<div class="row">														
							<c:choose>
								<c:when test="${controlloFisico.flControllo5x14 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x14 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x14 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>																															
					</div>
				  </li-lista>
				  <!-- 5.15 -->
				  <li-lista><b>L’OP ha istituito procedure sulla tracciabilità atte a consentire l’identificazione dei vegetali in applicazione dall’art 70 del reg. 2016/2031</b>
				      <div class="row">														
							<c:choose>
								<c:when test="${controlloFisico.flControllo5x15 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x15 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x15 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>																																		
					</div>	
				  </li-lista>
				  <!-- 5.16 -->
				  <li-lista><b>L’OP possiede le conoscenze necessarie per poter rilasciare i passaporti in attuazione dell’art 89 del reg. 2016/2031	</b>
				      <div class="row">	
				      		<c:choose>
								<c:when test="${controlloFisico.flControllo5x16 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x16 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x16 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>
                     </div> 
				  </li-lista>	
				   <!-- 5.17 -->
				  <li-lista><b>L’OP provvede a identificare i punti critici del suo processo di produzione prime di rilasciare il passaporto delle piante in applicazione dall’art 90 del reg. 2016/2031	</b>
				      <div class="row">	
				      		<c:choose>
								<c:when test="${controlloFisico.flControllo5x17 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x17 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x17 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>												
                     </div> 
				  </li-lista>	
				   <!-- 5.18 -->
				  <li-lista><b>L’OP ha istituito un piano di gestione dei rischi connessi agli organismi nocivi approvato dal Servizio in applicazione dall’art 91 del reg. 2016/2031</b>
				      <div class="row">		
				      		<c:choose>
								<c:when test="${controlloFisico.flControllo5x18 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x18 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x18 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>		
                     </div> 
				  </li-lista>	
				  
				     <!-- 5.19 -->
				  <li-lista><b>La ditta collabora con il Servizio e consente il corretto svolgimento delle ispezioni</b>
				      <div class="row">		
				      		<c:choose>
								<c:when test="${controlloFisico.flControllo5x19 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x19 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x19 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>												
                     </div> 	
				  </li-lista>
				  
				 <c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">	
				<!-- 5.20 -->
				<c:if test="${abilitaSementi}">
				  <li-lista><b>La ditta sementiera è una figura tecnica che possiede adeguate conoscenze professionali sulle tecniche di produzione/selezione meccanica, sulla normativa sementiera e fitosanitaria e riguardanti le categorie delle sementi per le quali richiede l’autorizzazione	</b>
				      <div class="row">			
				      		<c:choose>
								<c:when test="${controlloFisico.flControllo5x20 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x20 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x20 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>	
                     </div> 
                       <div class="row">	
                       				<c:forEach items="${listaConoscenzeProfessionali}" var="conoscenza">
											<div class="input-field col s4 m4 l4">
												<label> <input type="checkbox"  checked disabled>
													<span>${conoscenza.descBreve}</span>
												</label>
											</div>
									</c:forEach>	
                       </div>			
				    </li-lista>
				  </c:if>
				  </c:if>					
							<!-- 5.21 -->
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">	
				  <li-lista><b>Nel caso di attività vivaistica per cui non è prevista l’iscrizione al RUOP sono rispettati i requisiti di professionalità	</b>
							<div class="row">
								<c:choose>
								<c:when test="${controlloFisico.flControllo5x21 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x21 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x21 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>
							</div>

							<div class="row">
									<c:forEach items="${listaRequisitiProfessionali}" var="requisito">
											<div class="input-field col s4 m4 l4">
												<label> <input type="checkbox"  checked disabled>
													<span>${requisito.descBreve}</span>
												</label>
											</div>
											<c:if test="${not empty requisito.descLaurea}">
												<div class="input-field col s4 m4 l4">
												 	<input type="text" id="laurea"
															value="${fn:escapeXml(requisito.descLaurea)}" disabled="disabled" />
														<label for="laurea">Laurea:</label>
												</div>
											</c:if>
											<c:if test="${not empty requisito.descDiploma}">
												<div class="input-field col s4 m4 l4">
												 	<input type="text" id="diploma"
															value="${fn:escapeXml(requisito.descDiploma)}" disabled="disabled" />
														<label for="diploma">Diploma: </label>
												</div>
											</c:if>
									</c:forEach>	
						</div>				
				  </li-lista>	
				  </c:if>
				  	
				   <!-- 5.22 -->
				  <li-lista><b>Piante, prodotti vegetali, altri oggetti al controllo visivo risultano idonee alla 
				  			   commercializzazione e indenni da ON o segni riconducibili alla loro presenza</b>
				      <div class="row">	
				      			<c:choose>
								<c:when test="${controlloFisico.flControllo5x22 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x22 == 'N'}">
								     <input type="radio" checked><span>No (vedi tabella)</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x22 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>			
                     </div> 
                     <c:if test="${controlloFisico.flControllo5x22 == 'N' and nuovoControlloForm.idVersioneControllo == versioneBegin}">
	                     <div class="row">
	                     			<div class="input-field col s6 m5 l12">
											 	<input type="text" id="noteControllo5x22"
														value="${fn:escapeXml(controlloFisico.noteControllo5x22)}" disabled="disabled" />
													<label for="noteControllo5x22">Sintomi riscontrati ed eventuale ON a cui sono riconducibili:</label>
									</div>
					    </div>
				    </c:if>
				  
							<div class="row">
								<table id="tabellaSpecie" class="data-table striped display"
									data-orderable='false' data-paging="false" data-info="false">
									<thead>
										<tr>
											<th>Genere</th>
											<th>Specie</th>
											<th>Numero piante controllate</th>
											<th>Numero piante sintomatiche</th>
											<th>Organismo Nocivo</th>
										</tr>
									</thead>
									<tbody id="bodyTabellaSpecie">
										<c:forEach items="${listGenereSpecieDB}" var="genereSpecie" varStatus="status">
											<tr>
												
												<td>${genereSpecie.denomGenere }</td>
												<td>${genereSpecie.denomSpecie }</td>
												<td>
													<div class="input-field">
														<input type="text" id="numeroPiante"
															value="${fn:escapeXml(genereSpecie.numeroPiante)}" disabled="disabled" />
													</div>
												</td>
												<td>
													<div class="input-field">
														<input type="text" id="numeroPiante"
															value="${fn:escapeXml(genereSpecie.numPianteSintomatiche)}" disabled="disabled" />
													</div>
												</td>
												<td>${genereSpecie.descBreveOn }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							</li-lista>		
				    <!-- 5.23 -->
				   <br> 
				  <li-lista><b>Nel corso del controllo si è proceduto a prelievo di campioni per le analisi di laboratorio</b>
				      <div class="row">	
				      			<c:choose>
								<c:when test="${controlloFisico.flControllo5x23 == 'S'}">
									<input type="radio" checked><span>Si</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x23 == 'N'}">
								     <input type="radio" checked><span>No</span>
								</c:when>
								<c:when test="${controlloFisico.flControllo5x23 == 'NA'}">
								     <input type="radio" checked><span>NA</span>
								</c:when>	
								<c:otherwise>
									<input type="radio"><span>Si</span>
									<input type="radio"><span>No</span>
									<input type="radio"><span>NA</span>
								</c:otherwise>
							</c:choose>				
                     </div> 
				  </li-lista>
				  				  	
				  </ol> 				
				</div>	
			  </div>			
			</div>
			</c:if>
			<!-- FINE TAB CONTROLLOFISICO -->
			
			<!-- INIZIO TAB Campioni -->
			<c:if test="${nuovoControlloForm.tabFisico && nuovoControlloForm.flControllo5x23 != null && nuovoControlloForm.flControllo5x23 =='S' }">
			<div id="campioni">
				<div class="card-content">
					<div class="row">
						<table id="tabellaCampioni"
							class="data-table striped display" data-paging="false"
							data-info="false">
							<thead>
								<tr>
									<th>Genere/Specie</th>
									<th>Matrice campionata</th>
									<th>Codice campione</th>
									<th>Tipo Campione</th>
									<th>ON da ricercare</th>	
									<th>Data RDP</th>
									<th>Esito RDP</th>		
									<th>Numero RDP</th>									
								</tr>
							</thead>
							<tbody id="bodyTabellaCampioni">
								<c:forEach var="campione" items="${campioniDb}">
									<tr>
									    <c:choose>
											<c:when test="${campione.descGenere != null}">
												<td>${campione.descGenere} / ${campione.descSpecie}</td>
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>
										</c:choose>										
										<td>${campione.descTipologiaCampione}</td>
										<td>${campione.codCampioneInizio} 
											<c:if test="${not empty campione.codCampioneFine}"> 
												- ${campione.codCampioneFine}
											</c:if>
										</td>
										<td>${campione.descTipoCampione}</td>
										<td>${campione.descrElencoOrgNocDaRicercare}</td>			
										<td><fmt:formatDate value="${campione.dataRdp}"	pattern="dd/MM/yyyy" /></td>	
										<td>${campione.esitoRdp}</td>
										<td>${campione.numRdp}</td>							
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<div class="row">	
							<b>I campioni vengono riposti in sacchetti antieffrazione</b> <br>															
									<c:choose>
											<c:when test="${flSacchetti == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${flSacchetti == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
											</c:otherwise>
										</c:choose>																															
					</div>
					<div class="row">	
							<b>Al titolare/rappresentante legale viene consegnata banda removibile con il codice del campione</b> <br>														
									<c:choose>
											<c:when test="${flBanda == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${flBanda == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
											</c:otherwise>
										</c:choose>																															
					</div>
					<div class="row">	
							<b>Il titolare viene informato sulla facoltà di assistere alle analisi di laboratorio, personalmente o 
					     	per il tramite di un suo delegato, a tal proposito dichiara di volere assistere alle analisi</b>	<br>													
									<c:choose>
											<c:when test="${flAnalisi == 'S'}">
					         					<input type="radio" checked><span>Si</span>
											</c:when>
											<c:when test="${flAnalisi == 'N'}">
					         					<input type="radio" checked><span>No</span>
											</c:when>	
											<c:otherwise>
												<input type="radio"><span>Si</span>
												<input type="radio"><span>No</span>
											</c:otherwise>
										</c:choose>																															
					</div>
					
				</div>
			</div>
			</c:if>
			<!-- FINE TAB Campioni -->
					
			<!-- INIZIO TAB ALLEGATI -->
			<c:if test="${not empty listaAllegati}">
			<div id="allegati">
			<div class="card-content">
				<div class="row">					
					<c:if test="${not empty listaAllegati}">
					<div class="card-header white-text bg-primary-color">
							<h5 class="card-title bg-primary-color text-shadow title-padding"><b>Allegati</b></h5>
					</div>
					<div class="card-content">
						<c:forEach var="allegato" items="${listaAllegati}">
						<c:if test="${allegato.flagMultiplo}">
							<div class="divider"></div>
						</c:if>
						<div class="row valign-wrapper" style="${allegato.flagMultiplo ? 'margin-bottom:0' : ''}">
							<c:if  test="${allegato.flagMultiplo}">
								<div class="col s7" >
										${fn:escapeXml(allegato.descEstesa)}
								</div>	
							</c:if>
							<c:choose>
							<c:when test="${allegato.flagMultiplo}">
							<div class="col s6">
								<table id="tabellaAllegati${allegato.idTipoAllegato}" 
									class="display">
									<tbody>
										<c:forEach var="allegatoControllo" items="${allegato.listaAllegatiControllo}" >
										<tr>
											<td class="center input-field">
												<a href="<spring:url value="/controlli/allegato/scarica/${allegatoControllo.idAllegatoControllo}" />"
													class="tooltipped" data-tooltip="Scaricare l'allegato ${fn:escapeXml(allegatoControllo.nomeFile)}">
													<i class="small material-icons primary-color">save</i></a>
											</td>	
											<td>
													<input type="text" id="descAllegato" value="${fn:escapeXml(allegatoControllo.descAllegato)}"	disabled="disabled" /> 									 
											</td>												
										</tr>
										</c:forEach>											
									</tbody>
								</table>
							</div>
							</c:when>
							<c:otherwise>
								<c:if test="${empty allegato.listaAllegati}">
									<div class="col s12" align="left">
										${fn:escapeXml(allegato.descEstesa)}
									</div>
								</c:if>
								<c:if test="${not empty allegato.listaAllegati}">
									<div class="col s6">
											${fn:escapeXml(allegato.descEstesa)}
									</div>
									<div class="center col s2">
										<a href="<spring:url value="/controlli/allegato/scarica/${allegato.listaAllegati[0].idAllegatoControllo}" />"
											class="tooltipped" data-tooltip="Scaricare l'allegato ${fn:escapeXml(allegato.listaAllegati[0].nomeFile)}">
											<i class="small material-icons primary-color">save</i></a>
									</div>
									<div class="center col s4">
										<input type="text" id="descAllegato" value="${fn:escapeXml(allegato.listaAllegati[0].descAllegato)}"	disabled="disabled" />
									</div>
								</c:if>
								
							</c:otherwise>
							</c:choose>
						</div>
						<c:if test="${allegato.flagMultiplo}">
							<div class="divider" style="margin-bottom:20px;"></div>
						</c:if>
						</c:forEach>
					</div>
					</c:if>					
				</div>
				
				</div>
			</div>		
			</c:if>	
			<!-- FINE TAB ALLEGATI -->
			
		<!-- INIZIO TAB misura -->
		<c:if test="${nuovoControlloForm.misuraUfficiale != null && nuovoControlloForm.misuraUfficiale =='S' }">
			<div id="misura">
			  <div class="card-content">
				<div class="row">
				
				<!-- INIZIO MISURA UFFICIALE DISPOSIZIONE -->    			
				<div class="card-panel">	        	    
				  <div class="row">
						<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Misura ufficiale - Disposizione</em>
						</p>
				  </div>
				  <div class="row s12">
				   	<fmt:formatDate value="${misuraUfficiale.dataMisuraDisp}" pattern="dd/MM/yyyy" var="dataMisura" />
				  	<div class="input-field col s6 m4 l3">
							<input type="text" id="dataMisura" 
								value="${dataMisura}"	disabled="disabled" /> 
							<label for="dataMisura">Data misura</label>
					</div>
							
					<div class="input-field col s6 m4 l4">
							<input type="text" id="oraMisura" 
								value="${misuraUfficiale.oraMisuraDisp}"	disabled="disabled" /> 
							<label for="oraMisura">Ora misura</label>
					</div>
					
					<div class="input-field col s6 m4 l4">
							<input type="text" id="numeroVerbaleMf" 
								value="${misuraUfficiale.numeroVerbaleMf}"	disabled="disabled" /> 
							<label for="numeroVerbaleMf">N. verbale misura ufficiale</label>
					</div>
					
					<div class="row col s12">
						<h6>Ispettore che ha emesso la misura</h6>
					<!------ TABELLA ISPETTORI DISPOSIZIONE -------->
						<div class="container s12">						
							<div class="col s12">
								<table id="tabellaIspettoriDisp" class="data-table striped display"
									data-order='[[ 2, "asc" ]]' data-paging="false"
									data-info="false">
									<thead>
										<tr>
											<th>Nome ispettore</th>
											<th>Ruolo</th>
											<th>Numero tessera</th>
										</tr>
									</thead>
									<tbody id="bodyTabellaDisp">
										<c:forEach var="ispettoreDisp" items="${listaIspettoriDisp}">
												<tr>
													<td>${ispettoreDisp.descIspettore}</td>
													<td>${ispettoreDisp.ruolo}</td>
													<td>${ispettoreDisp.numeroTessera}</td>
												</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

					</div>																															
				</div>
				<div class="row s12">
				  <div class="input-field col s4 m4 l12">
				  			<input type="text" id="noteConformita" 
								value="${misuraUfficiale.noteConformita}"	disabled="disabled" /> 
							<label for="noteConformita">Elenco delle non conformità accertate</label>	
					</div>
				</div>
				
				<div class="row col s12">
						<h6>Misure applicate</h6>
						<!------ TABELLA MISURA APPLICATA -------->
						<div class="container s12">						
							<div class="col s12">
								<table id="tabellamisura" class="data-table striped display"
									data-order='[[ 2, "asc" ]]' data-paging="false"
									data-info="false">
									<thead>
										<tr>
											<th>Descrizione misura</th>
											<th>Note tipologia</th>
										</tr>
									</thead>
									<tbody id="bodyTabellaAttivita">
										<c:forEach var="misura" items="${listaTipologieMisura}">
												<tr>
													<td>${misura.descEstesa}</td>
													<td>${misura.note}</td>
												</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
												
				</div>
				
				
				<div class="row col s12">	
				 <div class="input-field col s4 m4 l6">			
				  Le misure di cui alla/e lettera/e 
				  			<input type="text" id="lettereMisura" 
								value="${misuraUfficiale.lettereMisura}"	disabled="disabled" /> 
				  </div>
				  <div class="input-field col s4 m4 l6">					
                  devono essere eseguite entro il
                  			  <fmt:formatDate value="${misuraUfficiale.dataEntroMisura}" pattern="dd/MM/yyyy" var="dataEntroMisura" />
			                  <input type="text" id="dataEntroMisura" 
											value="${dataEntroMisura}"	disabled="disabled" /> 
				  </div>
				  <div class="input-field col s4 m4 l12">			      
 					con le modalità di seguito indicate: 	
 								<input type="text" id="modalita" 
											value="${misuraUfficiale.modalitaMisura}"	disabled="disabled" /> 	
 				  </div>			            																	
				</div>
				
				<div class="row col s12">
				  <div class="input-field col s4 m4 l12">			      
 					La Misura ufficiale viene disposta in relazione alle seguenti piante, prodotti vegetali, altri oggetti:	
			 					<input type="text" id="noteMisuraDisp" 
											value="${misuraUfficiale.noteMisuraDisp}"	disabled="disabled" /> 
 				   </div>			            		
				</div>
			
					<div class="row">
					<div class="row container s12">	
					   <table id="tabellaSpecie" class="data-table striped display" data-orderable='false' data-paging="false" data-info="false">
									<thead>
										<tr>
											<th>Motivo della misura (organismo nocivo)</th>
											<th>Note</th>
											<th>Genere</th>
											<th>Specie</th>
											<th>Numero piante</th>
										</tr>
									</thead>
									<tbody id="bodyTabellaSpecie">	
									  <c:forEach items="${nuovoControlloForm.orgNocivoGenereSpecie}" var="specie" varStatus="status">
											<tr>
												<td>${specie.denomOrganismoNocivo}</td>
												<td>${specie.note}</td>
												<td>${specie.denomGenere}</td>
												<td>${specie.denomSpecie}</td>
												<td>
													<div class="input-field">
														<input type="text" id="numeroPiante" 
															value="${specie.numeroPiante}"	disabled="disabled" /> 
													</div>													
												</td>												
											</tr>
										</c:forEach>	
									</tbody>						
								</table>
							</div>
					</div>
					
			<!-- INIZIO CUSTODE DELLE PIANTE -->
			<br>
			<div class="card-panel">	        	    
				  <div class="row">
						<p class="center-align"
							style="font-size: 15px; margin-bottom: 5px;">
							<em>Persona identificata come custode dei prodotti oggetto di misura ufficiale</em>
						</p>
				  </div>
				  <div class="row">
				    <div class="input-field col s6 m4 l6">
				    		<input type="text" id="congnomeCustode" 
								value="${misuraUfficiale.congnomeCustode}"	disabled="disabled" /> 
							<label for="congnomeCustode">Cognome</label>	
					</div>
					
					<div class="input-field col s6 m4 l6">
							<input type="text" id="nomeCustode" 
								value="${misuraUfficiale.nomeCustode}"	disabled="disabled" /> 
							<label for="nomeCustode">Nome</label>	
					</div>										
				</div>
				
				
				<div class="row">
				
				  <div class="input-field col s6 m4 l3">
				  		<input type="text" id="provinciaNascitaCustode" 
								value="${provinciaNascitaCustode}"	disabled="disabled" /> 
							<label for="provinciaNascitaCustode">Provincia Nascita</label>
					</div>
					
					<div class="input-field col s6 m4 l3">
							<input type="text" id="comuneNascitaCustode" 
								value="${comuneNascitaCustode}"	disabled="disabled" /> 
							<label for="comuneNascitaCustode">Comune Nascita</label>	
					</div>
					
				 
					<fmt:formatDate value="${misuraUfficiale.dataNascitaCustode}" pattern="dd/MM/yyyy" var="dataNascitaCustode" />
					<div class="input-field col s6 m4 l6">
							<input type="text" id="dataNascitaCustode" 
								value="${dataNascitaCustode}"	disabled="disabled" /> 
							<label for="dataNascitaCustode">Data nascita</label>
					</div>
				</div>	
				
				<div class="row">		
					<div class="input-field col s6 m4 l3">
					<input type="text" id="provinciaResidenzaCustode" 
								value="${provinciaResidenzaCustode}"	disabled="disabled" /> 
							<label for="provinciaResidenzaCustode">Provincia Residenza</label>
					</div>
					
					<div class="input-field col s6 m4 l3">
					<input type="text" id="comuneResidenzaCustode" 
								value="${comuneResidenzaCustode}"	disabled="disabled" /> 
							<label for="comuneResidenzaCustode">Comune Residenza</label>
					</div>
				
					<div class="input-field col s12 m8 l6">
							<input type="text" id="indirizzoCustode" 
								value="${misuraUfficiale.indirizzoCustode}"	disabled="disabled" /> 
							<label for="indirizzoCustode">Indirizzo residenza (Es. Via Roma, 24)</label>
					</div>
				</div>
				<div class="row col l12">	
					<div class="input-field col s12 m8 l6">
							<input type="text" id="tipoDocumento" 
								value="${misuraUfficiale.tipoDocumento}"	disabled="disabled" /> 
							<label for="tipoDocumento">Tipo documento identificazione</label>
					</div>
					<div class="input-field col s12 m8 l3">
							<input type="text" id="codDocumento" 
								value="${misuraUfficiale.codDocumento}"	disabled="disabled" /> 
							<label for="codDocumento">Num. documento identificazione</label>
					</div>
					<fmt:formatDate value="${misuraUfficiale.dataEmissioneDocumento}" pattern="dd/MM/yyyy" var="dataEmissioneDocumento" />
				  	<div class="input-field col s6 m4 l6">
							<input type="text" id="dataEmissioneDocumento" 
								value="${dataEmissioneDocumento}"	disabled="disabled" /> 
							<label for="dataEmissioneDocumento">Data emissione documento</label>
					</div>
					<div class="input-field col s12 m8 l6">
							<input type="text" id="orgEmissioneDocumento" 
								value="${misuraUfficiale.orgEmissioneDocumento}"	disabled="disabled" /> 
							<label for="orgEmissioneDocumento">Organismo emissione documento</label>
					</div>
			   </div>
			   <div class="row col l12">
					<div class="input-field col s6">
							<input type="text" id="qualificaCustode" 
								value="${qualificaCustode}"	disabled="disabled" /> 
							<label for="qualificaCustode">Qualifica</label>
					</div>					
				</div>
				<div class="row">	
					<div class="input-field col s12 m8 l12">
							<input type="text" id="noteCustode" 
								value="${misuraUfficiale.noteCustode}"	disabled="disabled" /> 
							<label for="noteCustode">Raccomandazioni/prescrizioni</label>
					</div>
				</div>	
			  </div>
			<!-- FINE CUSTODE DELLE PIANTE -->	
			
			<!-- INIZIO PERSONA CONSEGNA VERBALE -->
			<div class="card-panel">	        	    
				  <div class="row">
						<p class="center-align"
							style="font-size: 15px; margin-bottom: 5px;">
							<em>Persona di riferimento per il verbale della Misura ufficiale</em>
						</p>
				  </div>
				  <div class="row s12">
				   <fmt:formatDate value="${misuraUfficiale.dataConsegnaDisposizione}" pattern="dd/MM/yyyy" var="dataConsegnaDisposizione" />
				   <div class="input-field col s6 m4 l4">
				   			<input type="text" id="dataConsegnaDisposizione" 
								value="${dataConsegnaDisposizione}"	disabled="disabled" /> 
							<label for="dataConsegnaDisposizione">Data sottoscrizione verbale</label>
					</div>
					
				    <div class="input-field col s6 m4 l6">
				    		<input type="text" id="persConsegnaDisposizione" 
								value="${misuraUfficiale.persConsegnaDisposizione}"	disabled="disabled" /> 
							<label for="persConsegnaDisposizione">Persona di riferimento per il verbale</label>
					</div>
					
					<div class="input-field col s6 m4 l6">
					     		<input type="text" id="tipoRespConsegnaDisp" 
									value="${tipoRespConsegnaDisp}"	disabled="disabled" /> 
								<label for="tipoRespConsegnaDisp">Ruolo della persona di riferimento per il verbale</label>															 
					</div>
					
					<div class="input-field col s12 m8 l12">
							<input type="text" id="noteDichiarazione" 
								value="${misuraUfficiale.noteDichiarazione}"	disabled="disabled" /> 
							<label for="noteDichiarazione">Dichiarazioni</label>
					</div>
				  </div>
			</div> 
			
		 
	</div>				
	<!-- FINE MISURA UFFICIALE DISPOSIZIONE -->			
					
	
  	<c:if test="${not empty misuraUfficiale.dataConstatazione}">								
		<!-- INIZIO MISURA UFFICIALE CONSTATAZIONE -->	
		<div id="datiConstatazione" class="row" >			
			<div class="card-panel">	
					    <div class="row">
							<p class="center-align"
								style="font-size: 20px; margin-bottom: 5px;">
								<em>Misura ufficiale - Constatazione</em>
							</p>
					    </div>
					    <div class="row s12">
					    	<fmt:formatDate value="${misuraUfficiale.dataConstatazione}" pattern="dd/MM/yyyy" var="dataConstatazione" />
						    <div class="input-field col s6 m4 l4">
								    <input type="text" id="dataConstatazione" 
										value="${dataConstatazione}"	disabled="disabled" /> 
									<label for="dataConstatazione">Data constatazione misura</label>
							</div>
							
							<div class="input-field col s6 m4 l3">
								    <input type="text" id="oraConstatazione" 
										value="${misuraUfficiale.oraConstatazione}"	disabled="disabled" /> 
									<label for="oraConstatazione">Ora constatazione misura</label>
							</div>
							
							<div class="input-field col s6 m8 l4">
								    <input type="text" id="numeroVerbaleCo" 
										value="${misuraUfficiale.numeroVerbaleCo}"	disabled="disabled" /> 
									<label for="numeroVerbaleCo">N. verbale constatazione misura ufficiale</label>
							</div>

							<div class="row col s12">
						<h6>Ispettore che ha constatato l'esito della misura</h6>
						<!------ TABELLA ISPETTORI CONSTATAZIONE -------->
						<div class="container s12">						
							<div class="col s12">
								<table id="tabellaIspettoriConst" class="data-table striped display"
									data-order='[[ 2, "asc" ]]' data-paging="false"
									data-info="false">
									<thead>
										<tr>
											<th>Nome ispettore</th>
											<th>Ruolo</th>
											<th>Numero tessera</th>
										</tr>
									</thead>
									<tbody id="bodyTabellaConst">
										<c:forEach var="ispettoreConst" items="${listaIspettoriConst}">
												<tr>
													<td>${ispettoreConst.descIspettore}</td>
													<td>${ispettoreConst.ruolo}</td>
													<td>${ispettoreConst.numeroTessera}</td>
												</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

					</div>										
						    <div class="input-field col s12 m8 l12">
								 Esito :
					         			<div class="row">
						         			<input type="radio" checked>														
											<c:if test="${misuraUfficiale.flEsitoConstatazione == 'S'}">
												<span>Positivo</span>
											</c:if>
											<c:if test="${misuraUfficiale.flEsitoConstatazione == 'N'}">
												<span>Negativo</span>
											</c:if>
											<c:if test="${misuraUfficiale.flEsitoConstatazione == 'P'}">
												<span>Parziale</span>
											</c:if>																									  													  								
										</div>		
						    </div>	
						    <div class="input-field col s12 m8 l12">
						    		<input type="text" id="noteEsitoConst" 
										value="${misuraUfficiale.noteEsitoConst}"	disabled="disabled" /> 
									<label for="noteEsitoConst">Note</label>
						    </div>
						 </div>   
						    
						 <!-- INIZIO PERSONA PRESENTE DURANTE LA CONSTATAZIONE -->
						 <div class="card-panel">	        	    
								  <div class="row">
										<p class="center-align"
											style="font-size: 15px; margin-bottom: 5px;">
											<em>Persona presente durante la Constatazione della Misura ufficiale</em>
										</p>
								  </div>
								  <div class="row s12">
								    <div class="input-field col s6 m4 l6">
									    <input type="text" id="persRiferimentoConst" 
											value="${misuraUfficiale.persRiferimentoConst}"	disabled="disabled" /> 
										<label for="persRiferimentoConst">Persona presente durante la Constatazione della Misura ufficiale</label>
									</div>
									
									<div class="input-field col s6">
										<input type="text" id="qualificaPersPresConstataz" 
											value="${qualificaPersPresConstataz}"	disabled="disabled" /> 
										<label for="qualificaPersPresConstataz">Qualifica</label>
									</div>
									
								  </div>
						  </div>
						  <!-- FINE PERSONA PRESENTE DURANTE LA CONSTATAZIONE -->					    					    
							
				<!-- INIZIO PERSONA ALLA QUALE VIENE CONSEGNATO IL VERBALE DI CONSTATAZIONE -->
				<div class="card-panel">	        	    
					  <div class="row">
							<p class="center-align"
								style="font-size: 15px; margin-bottom: 5px;">
								<em>Persona di riferimento per il verbale della Constatazione della Misura ufficiale</em>
							</p>
					  </div>
					  <div class="row s12">
					  	<fmt:formatDate value="${misuraUfficiale.dataConsegnaConst}" pattern="dd/MM/yyyy" var="dataConsegnaConst" />
					    <div class="input-field col s6 m4 l4">
					    				<input type="text" id="dataConsegnaConst" 
											value="${dataConsegnaConst}"	disabled="disabled" /> 
										<label for="dataConsegnaConst">Data sottoscrizione verbale constatazione</label>
						</div>
					    <div class="input-field col s6 m4 l8">
					    				<input type="text" id="persConsegnaConst" 
											value="${misuraUfficiale.persConsegnaConst}"	disabled="disabled" /> 
										<label for="persConsegnaConst">Persona di riferimento per il verbale della Constatazione</label>
						</div>
						
						<div class="input-field col s6 m4 l6">	
										<input type="text" id="tipoRespConsegnaConst" 
											value="${tipoRespConsegnaConst}"	disabled="disabled" /> 
										<label for="tipoRespConsegnaConst">Ruolo della persona di riferimento per il verbale della Constatazione</label>
						</div>
						 
						<div class="input-field col s12 m8 l12">
										<input type="text" id="noteDichiarazioneConst" 
											value="${misuraUfficiale.noteDichiarazioneConst}"	disabled="disabled" /> 
										<label for="noteDichiarazioneConst">Dichiarazioni</label>
						</div>
						<div class="input-field col s12 m8 l12">
										<input type="text" id="noteConstatazione" 
											value="${misuraUfficiale.noteConstatazione}"	disabled="disabled" /> 
										<label for="noteConstatazione">Note</label>
						</div>
					  </div>
			    </div>
			    <!-- FINE PERSONA ALLA QUALE VIENE CONSEGNATO IL VERBALE DI CONSTATAZIONE -->		
			   	</div>
			   </div>
			   <!-- FINE MISURA UFFICIALE CONSTATAZIONE -->	
		   </c:if>			
				
				</div>		 
			</div>
		</div>	
		</c:if>				
		<!-- FINE TAB misura -->
			
			<!-- INIZIO TAB esito -->
			<div id="esito">
			  <div class="card-content">
				<div class="row">
					
					<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Esito Controllo</em>
						</p>
		  		 	</div>		
					<div class="row">
						<fmt:formatDate value="${esito.dataChiusuraVerbale}" pattern="dd/MM/yyyy" var="dataChiusuraVerbale" />
						<div class="input-field col s6 m4 l3">
								<input type="text" id="dataChiusuraVerbale" cssClass="datepicker_birthdate"
										value="${fn:escapeXml(dataChiusuraVerbale)}" disabled="disabled" />
								<label for="dataChiusuraVerbale">Data chiusura verbale</label>
						</div>						
					</div>
					<div class="row">
					<div class="col s9 m6 l4"><br><br>Persona alla quale verrà consegnato il verbale: </div>
						<div class="input-field col s6 m4 l3">
								<input type="text" id="cognomeResp" 
										value="${fn:escapeXml(esito.cognomeResp)}" disabled="disabled" />
								<label for="cognomeResp">Cognome</label>
						</div>		
										
						<div class="input-field col s6 m4 l3">
								<input type="text" id="nomeResp" 
										value="${fn:escapeXml(esito.nomeResp)}" disabled="disabled" />
								<label for="nomeResp">Nome</label>
						</div>	
					</div>
					
					<div class="input-field col s12 m8 l12">
					    <textarea id="noteDichiarazione" class="materialize-textarea" rows="2"
							disabled="disabled">${fn:escapeXml(esito.noteDichiarazione)}</textarea>														
					</div>
								
										
					<div class="row">		
						<p>Sono state accertate irregolarità</p>	
						<br>				
								  	<input type="radio" checked>
									<c:if test="${esito.flIrregolarita == 'S'}">
										<span>Si</span>
									</c:if>
									<c:if test="${esito.flIrregolarita == 'N'}">
										<span>No</span>
									</c:if>
																																										
					</div>
					
					<div class="input-field col s6 m5 l12">
						<textarea id="noteIrregolarita" class="materialize-textarea" rows="2"
							disabled="disabled">${fn:escapeXml(esito.noteIrregolarita)}</textarea>														
		            </div>
			          
			        <c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">   
			             <div class="row">		
			             	<p>Commercializzazione dei vegetali si rilascia parere: </p>
			             	<br>					
			             				<input type="radio" checked>
										<c:if test="${esito.flEsito == 'S'}">
											<span>Favorevole</span>
										</c:if>
										<c:if test="${esito.flEsito == 'N'}">
											<span>Non favorevole</span>
										</c:if>																													
						 </div>
						
						<div class="row">	
							<p>Al rilascio del Passaporto delle piante si rilascia parere: </p>		
							<br>			
										<input type="radio" checked>
										<c:if test="${esito.flEsitoPassaporto == 'S'}">
											<span>Favorevole</span>
										</c:if>
										<c:if test="${esito.flEsitoPassaporto == 'N'}">
											<span>Non favorevole</span>
										</c:if>																													
						</div>
					</c:if>
					
					
					<c:if test="${nuovoControlloForm.idVersioneControllo >= versione042021}">
						<div class="row">		
		             	<p>Il controllo si è concluso con esito : </p>
		             	<br>													
						  <input type="radio" checked>
										<c:if test="${esito.flEsito == 'S'}">
											<span>Favorevole</span>
										</c:if>
										<c:if test="${esito.flEsito == 'N'}">
											<span>Non favorevole</span>
										</c:if>
						  <textarea id="noteEsitoControllo" class="materialize-textarea" rows="2"
							disabled="disabled">${fn:escapeXml(esito.noteEsitoControllo)}</textarea>						 																										  						 																										
					    </div>
					  
					 	
					    <c:if test="${esito.flMisuraUfficiale !=  null && esito.flMisuraUfficiale == 'S'}">
						    <br>
							<div class="row col s12">													  						   					   									   						   
							       <div class="row col l6">					   	
										<label>								
											<input type="checkbox" checked="checked" disabled="disabled"/>
											<span>Nel corso del controllo è stata emessa misura ufficiale n.</span>							
										</label>															
									</div>		
									<div class="row col l6">
									  <input type="text" id="numMisuraUfficiale" 
											value="${fn:escapeXml(esito.numMisuraUfficiale)}" disabled="disabled" />								      
									</div>				   					
							</div>	
						</c:if>
						
						<c:if test="${esito.flMotivoMisuraUfficiale !=  null && esito.flMotivoMisuraUfficiale == 'S'}">
						    <br>
							<div class="row col s12">													  						   					   									   						   
							       <div class="row col l6">					   	
										<label>								
											<input type="checkbox" checked="checked" disabled="disabled"/>
											<span>Nel corso del controllo è stata proposta misura ufficiale</span>							
										</label>															
									</div>		
									<div class="row col l12">
									  <textarea id="noteMotivoMisuraUfficiale" class="materialize-textarea" rows="2"
										disabled="disabled">${fn:escapeXml(esito.noteMotivoMisuraUfficiale)}</textarea>									  								      
									</div>				   					
							</div>	
						</c:if>
					   																
					    <c:if test="${esito.flSanzioneAmministrativaEmessa !=  null && esito.flSanzioneAmministrativaEmessa == 'S'}">
						    <br>
							<div class="row col s12">													  						   					   									   						   
							       <div class="row col l6">					   	
										<label>								
											<input type="checkbox" checked="checked" disabled="disabled"/>
											<span>Nel corso del controllo è stata emessa sanzione amministrativa</span>							
										</label>															
									</div>		
									<div class="row col l12">
									  <textarea id="noteSanzioneAmministrativaEmessa" class="materialize-textarea" rows="2"
										disabled="disabled">${fn:escapeXml(esito.noteSanzioneAmministrativaEmessa)}</textarea>																		  								      
									</div>				   					
							</div>	
						</c:if>
					  
					    <c:if test="${esito.flSanzioneAmministrativaProposta !=  null && esito.flSanzioneAmministrativaProposta == 'S'}">
						    <br>
							<div class="row col s12">													  						   					   									   						   
							       <div class="row col l6">					   	
										<label>								
											<input type="checkbox" checked="checked" disabled="disabled"/>
											<span>Nel corso del controllo è stata proposta sanzione amministrativa</span>							
										</label>															
									</div>		
									<div class="row col l12">
										<textarea id="noteSanzioneAmministrativaProposta" class="materialize-textarea" rows="2"
										disabled="disabled">${fn:escapeXml(esito.noteSanzioneAmministrativaProposta)}</textarea>									 								      
									</div>				   					
							</div>	
						</c:if>
					  
					   <c:if test="${esito.flPrescrizioni !=  null && esito.flPrescrizioni == 'S'}">
						    <br>
							<div class="row col s12">													  						   					   									   						   
							       <div class="row col l6">					   	
										<label>								
											<input type="checkbox" checked="checked" disabled="disabled"/>
											<span>Nel corso del controllo sono state date le seguenti prescrizioni :</span>							
										</label>															
									</div>		
									<div class="row col l12">
										<textarea id="notePrescrizioni" class="materialize-textarea" rows="2"
											disabled="disabled">${fn:escapeXml(esito.notePrescrizioni)}</textarea>									  								      
									</div>				   					
							</div>	
					  </c:if>

					</c:if>
					
					
					<c:if test="${controllo.misuraUfficiale !=  null && controllo.misuraUfficiale == 'S'}">
					    <br>
						<div class="row col s12">						
						   <p>Controllo eseguito :</p>
						   <br>						   					   									   						   
						       <div class="row col l6">					   	
									<label>								
										<input type="checkbox" checked="checked" disabled="disabled"/>
										<span>Misura ufficiale</span>							
									</label>															
								</div>						   					
						</div>	
					</c:if>
					
					<div class="input-field col s12 m8 l12">
						<textarea id="note" class="materialize-textarea" rows="2"
											disabled="disabled">${fn:escapeXml(esito.note)}</textarea>								
								<label for="note">Note</label>
					</div>
						
					<div class="row">	
						<div class="input-field col s6 m6 l6">
								<input type="text" id="emailInvioVerbale" 
										value="${fn:escapeXml(esito.emailInvioVerbale)}" disabled="disabled" />
								<label for="emailInvioVerbale">Email invio verbale</label>
						</div>
				 	</div>		 
				</div>
			</div>					
			<!-- FINE TAB esito -->
			
				
	</div>
</div>
		
		<div class="row">
			<a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>										        	        		    		        
	   	</div>




<content tag="script"> 
		<script type="text/javascript" src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script> 
		<script>
				

		$(document).ready(
			function() {				
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
			$("a[data-value='datibase']").click();
		});
		
		
		
		</script>
</content>		


</body>
</html>