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
</head>
<body>
	<div class="card">
		<!-- <div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>${nuovaDomandaForm.descTipoDomanda}</b></h3>
		</div>-->	
		<div class="card-title">
			<div class="hide-on-large-only bg-primary-color"
				style="color: rgba(255, 255, 255, 0.7); padding-bottom: 5px; padding-top: 5px; padding-left: 5px">
				<h6 style="color: #fff; font-size: 18px;" id="small_label">Dati Domanda</h6>
			</div>
			<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
			<spring:eval var="statoInBozza"	expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />	
  			<nav class="breadcrumb-nav col s12 hide-on-med-and-down">
				<div class="nav-wrapper">
					<div class="col s12">					  
						<a href="#anagrafica" data-value="anagrafica" class="breadcrumb" style="color: #fff">&nbsp;&nbsp;Anagrafica</a> 
						<a href="#azienda" data-value="azienda" class="breadcrumb">Dati operatore</a>
						
						<spring:eval var="idTipoDomandaRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP" />
						<spring:eval var="idTipoDomandaVariazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP" />
        				<spring:eval var="idTipoDomandaCancellazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP" />
        				<spring:eval var="idTipoDomandaPassaporto" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO" />
        				
        				<spring:eval var="versione1" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_DOMANDA_1" />	
						<spring:eval var="versione2" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_DOMANDA_2" />
        				
        					<!-- in caso di Cancellazione non mostro i seguenti tab -->
        					<c:if test="${idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}"> 
        					    <c:if test="${ idTipoDomandaVariazioneRUOP == nuovaDomandaForm.idTipoComunicazione || idTipoDomandaRUOP == nuovaDomandaForm.idTipoComunicazione}">
								  <a href="#centroaziendale" data-value="centroaziendale"class="breadcrumb">Centri aziendali</a>
								</c:if>  								
								<c:if test="${nuovaDomandaForm.tabImport}">
									<a href="#import" data-value="import" class="breadcrumb">Import</a>
								</c:if>	
								<c:if test="${nuovaDomandaForm.tabExport}"> 
									<a href="#export" data-value="export" class="breadcrumb">Export</a>
								</c:if>	
								<c:if test="${nuovaDomandaForm.tabPassaporto}">
									<a href="#passaporto" data-value="passaporto" class="breadcrumb">Passaporto</a>
								</c:if>	
							</c:if>
							<!--  -->
			        	    <a href="#allegati" data-value="allegati" class="breadcrumb">Allegati</a>
							<!-- in caso di Cancellazione non mostro i seguenti tab -->
        					<c:if test="${idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
								<c:if test="${isSuperUser and statoInBozza != nuovaDomandaForm.idStatoComunicazione}">
				        			<a href="#gestione" data-value="gestione" class="breadcrumb">Gestione</a>	
	        					</c:if>
							</c:if>
							<!--  -->
					</div>
				</div>
			</nav>
		</div>	
		<div class="card-content" style="padding-top:5px;">
			<!-- <div class="row">
 				<div class="col">
   					<ul class="tabs">
     					<li class="tab col" >
     						<a href="#anagrafica">Anagrafica</a>
						</li>
     					   						
   						<li class="tab col" >
   							<a href="#azienda">Dati operatore</a>
						</li> 
						
						<spring:eval var="idTipoDomandaRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP" />
						<c:if test="${idTipoDomandaRUOP == nuovaDomandaForm.idTipoComunicazione}"> 	
							<li class="tab col s3 m2" >
								<a href="#centroaziendale">Centri aziendali</a>
							</li>	
							<li class="tab col" >
								<a href="#tipologia">Tipologia</a>
							</li>
							<c:if test="${nuovaDomandaForm.tabImport}">
							  <li class="tab col" >
								<a href="#import">Import</a>
							  </li>
							</c:if>
							<c:if test="${nuovaDomandaForm.tabExport}">
							  <li class="tab col" >
								<a href="#export">Export</a>
							  </li>
							</c:if>
							<c:if test="${nuovaDomandaForm.tabPassaporto}">
							  <li class="tab col" >
								<a href="#passaporto">Passaporto</a>
							  </li>
							</c:if>
							<li class="tab col" >
							  <a href="#allegati">Allegati</a>
							</li>
						</c:if>
   					</ul>
				</div>
			</div>
			-->
			
			<!-- INIZIO TAB ANAGRAFICA -->
			<div id="anagrafica">
			  <div class="card-content">
				<div class="row">
					<div class="input-field col s6 m4 l3">
						<input type="text" id="cognome"
							value="${fn:escapeXml(dettaglioDomanda.cognome)}" disabled="disabled" />
						<label for="cognome">Cognome </label>
					</div>
					<div class="input-field col s6 m4 l3">
						<input type="text" id="nome" 
							value="${fn:escapeXml(dettaglioDomanda.nome)}"	disabled="disabled" /> 
						<label for="nome">Nome</label>
					</div>									
					<div class="input-field col s6 m4 l3">
					    <input type="text" id="codiceFiscale" 
							value="${fn:escapeXml(dettaglioDomanda.codiceFiscale)}"	disabled="disabled" /> 
						<label for="codiceFiscale">Codice fiscale</label>					
					</div>		
					<fmt:formatDate value="${dettaglioDomanda.dataNascita}" pattern="dd/MM/yyyy" var="dataNascita" />	
					<div class="input-field col s6 m4 l3">
						<input type="text" id="dataNascita" 
							value="${dataNascita}"	disabled="disabled" /> 
						<label for="dataNascita">Data di nascita</label>
					</div>										
					<c:choose>
						<c:when test="${empty dettaglioDomanda.idNazioneEstNascita}">
							<div class="input-field col s6 m4 l3">
								<input type="text" id="provNascita" 
									value="${fn:escapeXml(dettaglioDomanda.provNascita)}"	disabled="disabled" /> 
								<label for="provNascita">Provincia Nascita</label>	
							</div>	
							<div class="input-field col s6 m4 l3">
								<input type="text" id="comuneNascita" 
									value="${fn:escapeXml(dettaglioDomanda.comuneNascita)}"	disabled="disabled" /> 
								<label for="comuneNascita">Comune Nascita</label>	
							</div>
						</c:when>
						<c:otherwise>
							<!-- <div class="input-field col s12 m8 l6">
								<input type="text" id="denomComuneEstNascita" 
									value="${fn:escapeXml(dettaglioDomanda.denomComuneEstNascita)}"	disabled="disabled" /> 
								<label for="denomComuneEstNascita">Comune estero di nascita</label>	
							</div>-->					
							<div class="input-field col s6 m4 l3">
								<input type="text" id="nazioneEstNascita" 
									value="${fn:escapeXml(dettaglioDomanda.nazioneEstNascita)}"	disabled="disabled" /> 
								<label for="nazioneEstNascita">Stato nascita</label>	
							</div>			
						</c:otherwise>
					</c:choose>						
					<c:choose>
						<c:when test="${empty dettaglioDomanda.idNazioneEstResid}">
							<div class="input-field col s6 m4 l3">
								<input type="text" id="provResidenza" 
									value="${fn:escapeXml(dettaglioDomanda.provResidenza)}"	disabled="disabled" /> 
								<label for="provResidenza">Provincia Residenza</label>	
							</div>
							<div class="input-field col s6 m4 l3">
								<input type="text" id="comuneResidenza" 
									value="${fn:escapeXml(dettaglioDomanda.comuneResidenza)}" disabled="disabled" /> 
								<label for="comuneResidenza">Comune Residenza</label>	
							</div>
						</c:when>
						<c:otherwise>
							<!-- <div class="input-field col s12 m8 l6">
								<input type="text" id="denomComuneEstResid" 
									value="${fn:escapeXml(dettaglioDomanda.denomComuneEstResid)}" disabled="disabled" /> 
								<label for="denomComuneEstResid">Comune estero di residenza</label>	
							</div>	 -->
							<div class="input-field col s6 m4 l3">
								<input type="text" id="nazioneEstResid" 
									value="${fn:escapeXml(dettaglioDomanda.nazioneEstResid)}"	disabled="disabled" /> 
								<label for="nazioneEstResid">Stato residenza</label>	
							</div>	
						</c:otherwise>
					</c:choose>
					<div class="input-field col s6 m4 l3">
						<input type="text" id="cap" 
							value="${fn:escapeXml(dettaglioDomanda.cap)}"	disabled="disabled" /> 
						<label for="cap">Cap residenza</label>	
					</div>
					<div class="input-field col s6 m4 l3">
						<input type="text" id="indirizzo" 
							value="${fn:escapeXml(dettaglioDomanda.indirizzo)}" disabled="disabled" /> 
						<label for="indirizzo">Indirizzo Residenza (Es. Via Roma, 24)</label>	
					</div>					
					<div class="input-field col s6 m4 l3">
						<input type="text" id="telefono" 
							value="${fn:escapeXml(dettaglioDomanda.telefono)}" disabled="disabled" /> 
						<label for="telefono">Numero di telefono (Es. 0245673467)</label>	
					</div>
					<div class="input-field col s6 m4 l3">
						<input type="text" id="cellulare" 
							value="${fn:escapeXml(dettaglioDomanda.cellulare)}" disabled="disabled" /> 
						<label for="cellulare">Numero di cellulare (Es. 3471234567)</label>	
					</div>
					<div class="input-field col s6 m5 l4">
						<input type="text" id="email" 
							value="${dettaglioDomanda.email}" disabled="disabled" /> 
						<label for="email">Email</label>	
					</div>
				</div>	
			  </div>			
			</div>
			<!-- FINE TAB ANAGRAFICA -->
			
			<!-- INIZIO TAB AZIENDA -->
			<div id="azienda">
			 <div class="card-content">
			  <div class="card-panel">	
        	     <div class="row">
		  			<p class="center-align"
						style="font-size: 20px; margin-bottom: 5px;">
						<em>Dati dell'azienda</em>
					</p>
				 </div>	
  															
				<!-- DATI ANAGRAFICI AZIENDA (CAR_T_SPEDIZIONIERE)-->
				<div class="row">
				    <div class="row col l12">
					    <div class="input-field col s6 m4 l6">
							<input type="text" id="idTipoAzienda" 
								value="${dettaglioDomanda.denomTipoSpedizioniere}"	disabled="disabled" /> 
							<label for="idTipoAzienda">Tipo Azienda</label>
						</div>
						<div class="input-field col s6 m4 l6">
							<input type="text" id="codFiscaleAz" 
								value="${dettaglioDomanda.cuaa}"	disabled="disabled" />
							<label for="codFiscaleAz">CUAA / CF</label>
						</div>	 
						<c:if test="${dettaglioDomanda.tipoSpedizioniereAltro}">
						<div class="input-field col s6 m4 l6">
							<input type="text" id="tipoSpedizioniereAltro" 
								value="${dettaglioDomanda.tipoSpedizioniereAltro}"	disabled="disabled" />
							<label for="tipoSpedizioniereAltro">Tipologia Altro</label>
						</div>
						</c:if>																																
					</div>
					<div class="row col l12">
						<div class="input-field col s6 m4 l6">
							<input type="text" id="denomSpedizioniere" 
								value="${dettaglioDomanda.denomSpedizioniere}"	disabled="disabled" /> 
							<label for=denomSpedizioniere>Denominazione/Ragione Sociale *</label>
						</div> 										  
						<div class="input-field col s6 m4 l6">
							<input type="text" id="partitaIva" 
								value="${dettaglioDomanda.partitaIva}"	disabled="disabled" />
							<label for="partitaIva">Partita IVA</label>
						</div>				  	
					</div>
					<div class="row col l12">		
						<div class="input-field col s6 m4 l6">					
							<input type="text" id="nomeSped" 
									value="${dettaglioDomanda.nomeSped}"	disabled="disabled" /> 
							 <label for="nomeSped">Nome</label>
						</div>
						<div class="input-field col s6 m4 l6">
						  <input type="text" id="cognomeSped" 
								value="${dettaglioDomanda.cognomeSped}" disabled="disabled" /> 
						  <label for="cognomeSped">Cognome</label>
						</div>			
					</div>					
					<!-- INIZIO Dati sede legale -->
					<div class="row col l12">
						<div class="input-field col s6 m4 l6">
							<input type="text" id="denomProvinciaSped" 
								value="${dettaglioDomanda.denomProvinciaSped}" disabled="disabled" /> 
							<label for="denomProvinciaSped">Provincia Sede Legale</label>
						</div>
						<div class="input-field col s6 m4 l6">
							<input type="text" id="denomComuneSped" 
								value="${dettaglioDomanda.denomComuneSped}" disabled="disabled" /> 
							<label for="denomComuneSped">Comune Sede Legale</label>
						</div>
					</div>
					
					<div class="row col l12">
						<div class="input-field col s6 m4 l6">
						  <input type="text" id="capSped" 
								value="${dettaglioDomanda.capSped}" disabled="disabled" /> 
						  <label for="capSped">CAP sede legale</label>
						</div>
						<div class="input-field col s6 m4 l6">
							<input type="text" id="indirizzoSped" 
								value="${dettaglioDomanda.indirizzoSped}" disabled="disabled" /> 
							<label for="indirizzoSped">Indirizzo sede legale (Es. Via Roma, 24)</label>
						</div>
					</div>
					
					
					<div class="row col l12">
						<div class="input-field col s6 m4 l4">
							<input type="text" id="telefonoSped" 
								value="${dettaglioDomanda.telefonoSped}" disabled="disabled" /> 
							<label for="telefonoSped">Telefono sede legale (Es. 0245673467)</label>
						</div>
						<div class="input-field col s6 m4 l4">
							<input type="text" id="cellulareSped" 
								value="${dettaglioDomanda.cellSped}" disabled="disabled" /> 
							<label for="cellulareSped">Cellulare sede legale (Es. 3471234567)</label>
						</div>
						<div class="input-field col s12 m8 l4">
							<input type="text" id="faxSped" 
								value="${dettaglioDomanda.faxSped}" disabled="disabled" /> 
							<label for="faxSped">Fax sede legale (Es. 0298765432)</label>
						</div>
					</div>
							
					<div class="row col l12">																		
						<div class="input-field col s6 m4 l6">
							<input type="text" id="emailSped" 
								value="${fn:escapeXml(dettaglioDomanda.emailSped)}" disabled="disabled" /> 
							<label for="emailSped">Email</label>
						</div>
						<div class="input-field col s6 m4 l6">
							<input type="text" id="pecSped" 
								value="${fn:escapeXml(dettaglioDomanda.pecSped)}" disabled="disabled" /> 
							<label id="pecSped">PEC *</label>
						</div>
					</div>					
					<!-- FINE Dati sede legale -->
				</div>
				<!-- FINE DATI ANAGRAFICI AZIENDA -->
			 </div>	
				
			
				<!-- Dati attivita azienda -->
				<c:if test="${ idTipoDomandaVariazioneRUOP == nuovaDomandaForm.idTipoComunicazione || idTipoDomandaRUOP == nuovaDomandaForm.idTipoComunicazione}">
				   <div class="card-panel">	  				  
		  				<div class="row">
		  					<p class="center-align"
									style="font-size: 20px; margin-bottom: 5px;">
									<em>L’Operatore Professionale dichiara l'intenzione di svolgere una o più attività indicate dal Reg. (UE) 2016/2031 art. 66.2.b-c, in qualità di :</em>
							</p>
		  				</div>
						<table class="">													
							<c:forEach var="singola" items="${dettaglioDomanda.descrVociAttivitaTipologia}">
								<tr>
									<td>${singola}</td> 
								</tr>
							</c:forEach>
						</table>						
					</div>
					
					<div class="card-panel">
						<div class="row">
		  					<p class="center-align"
									style="font-size: 20px; margin-bottom: 5px;">
									<em>Tipologie di Attività</em>
							</p>
		  				</div>
				        <table id="tabellaAttivita" class="data-table  display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">
				          <thead>
				            <tr>				             
				              <th>Tipologia attività</th>
				              <th>Materiale</th>			              
				            </tr>
				          </thead>
				          <tbody id="bodyTabellaAttivita">
				            <c:forEach var="attivita" items="${dettaglioDomanda.tipAttivitaTipologia}">				              
				              <c:choose>	
				              	<c:when test="${attivita.descEstesa != null}">			                 
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
				        <c:if test="${nuovaDomandaForm.tabPassaporto}">
					      	<div class="row col l6">					   	
								<label>								
									<input type="checkbox" checked="checked" disabled="disabled"/>
									<span>Richiesta contestuale di autorizzazione al rilascio del Passaporto delle piante</span>							
								</label>															
							</div>	
						</c:if>              
			      	</div>		
			      </c:if>  	
				<!-- Fine Dati attivita azienda -->
				
				</div>	
			</div>
			<!-- FINE TAB AZIENDA -->
			
		<spring:eval var="idTipoDomandaRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP" /> 
		<spring:eval var="idTipoDomandaVariazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP" />   					        					        					
	     		
			<!-- INIZIO TAB CENTRO AZIENDALE -->
			<c:if test="${ idTipoDomandaVariazioneRUOP == nuovaDomandaForm.idTipoComunicazione || idTipoDomandaRUOP == nuovaDomandaForm.idTipoComunicazione}">
			<div id="centroaziendale">
			   <div class="card-content">
				<div class="row">
						<div class="input-field col s24 m16 l12">
						<c:choose>
					      <c:when test="${not empty dettaglioDomanda.centriAziendaliList}">
						 	 <p class="left-align" style="font-size:20px;margin-bottom:5px;">
					  			<em>&#8226; Esistono centri aziendali in luogo diverso dalla sede legale :</em>
						  	</p>
						  </c:when>
						  <c:otherwise>
						    <p class="center-align" style="font-size:20px;margin-bottom:5px;">
					  			<em>Non esistono centri aziendali in luogo diverso dalla sede legale</em>
						  	</p>
						  </c:otherwise>
						</c:choose>														
						</div>	
						<c:choose>
					      <c:when test="${not empty dettaglioDomanda.centriAziendaliList}">		
					      <spring:eval var="statoConvalidata"	expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_CONVALIDATA" />			      		
							<div class="row">
					       	<table id="tabellaCentroAziendale" class="data-table  display" 
					       		data-order='[[ 1, "asc" ], [ 2, "asc" ], [ 3, "asc" ], [ 4, "asc" ]]' data-paging="false" data-info="false">
								<thead>
									<tr>										
										<th data-orderable="false"></th>
										<c:if test="${nuovaDomandaForm.idStatoComunicazione == 3 }">
											<td>Codice Centro Aziendale</td>	
										</c:if>	
										<th>Denominazione centro aziendale</th>	
										<th>Cumune (PV)</th>
										<th>Cap</th>
										<th>Indirizzo</th>
										<th>Frazione</th>
										<th>Telefono</th>
										<th>Cellulare</th>																															
										<th>Email</th>
										<th>Pec</th>
									</tr>
								</thead>
								<tbody id="bodyTabellaCentriAziendali">
								  <c:forEach var="centroAz" items="${dettaglioDomanda.centriAziendaliList}">
									   <tr>										
										<td nowrap style="white-spaces: nowrap">
	                    				    <a href="javascript:dettaglioCentroAziendale(${centroAz.idCentroAziendale})"
											  title="Visualizza"
											  class="btn btn-floating btn-floating-medium light-blue accent-3">
												<i class="material-icons">visibility</i>
						                    </a>	  
										</td>
										<c:if test="${nuovaDomandaForm.idStatoComunicazione == 3 }">
											<td>${fn:escapeXml(centroAz.codCentroAziendale)}</td>	
										</c:if>																		
										<td>${fn:escapeXml(centroAz.denominazione)}</td>
										<td>${fn:escapeXml(centroAz.denomComune)} (${fn:escapeXml(centroAz.siglaProvincia)})</td>
										<td>${fn:escapeXml(centroAz.cap)}</td>
										<td>${fn:escapeXml(centroAz.indirizzo)}</td>								
										<td>${fn:escapeXml(centroAz.frazione)}</td>
										<td>${fn:escapeXml(centroAz.telefono)}</td>
										<td>${fn:escapeXml(centroAz.cellulare)}</td>
										<td>${fn:escapeXml(centroAz.email)}</td>
										<td>${fn:escapeXml(centroAz.pec)}</td>
								      </tr>
							      </c:forEach>								
								</tbody>																	
							</table>											
						    </div>
						  </c:when>
						</c:choose>  
				</div>
				</div>								
			</div>
			</c:if>			
			<!-- FINE TAB CENTRO AZIENDALE  -->
			
			<!-- INIZIO TAB IMPORT -->
			<c:if test="${nuovaDomandaForm.tabImport}">
			<div id="import">  	
				<div class="card-content">				  				
	  				<div class="card-panel">
	  				  <div class="row">
		  					<p class="center-align"
									style="font-size: 20px; margin-bottom: 5px;">
									<em>Le importazioni riguardano le seguenti tipologie di merci/produzioni :</em>
							</p>
		  				</div> 
	  				  <table id="tabellaSpecie" class="data-table  display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">
				          <thead>
				            <tr>
				              <th>Tipologia produttiva</th>
				              <th>Note</th>
				              <th>Genere</th>
				              <th>Specie</th>				              
				            </tr>
				          </thead>
				          <tbody id="bodyTabellaSpecie">
				            <c:forEach var="tipologia" items="${dettaglioDomanda.tipologieProdImport}">
				              <c:choose>	
								<c:when test="${not empty tipologia.specieList}">
				              		<c:forEach var="specie" items="${tipologia.specieList}">
									   <tr>																																		
										<td>${tipologia.denomTipologiaEstesa}</td>		
										<td>${tipologia.note}</td>							
										<td>${specie.denomGenere}</td>
										<td>${specie.denomSpecie}</td>									
								      </tr>
								     </c:forEach> 
								 </c:when>
								 <c:otherwise>
								 	<tr>									  	
										<td>${tipologia.denomTipologiaEstesa}</td>	
										<td>${tipologia.note}</td>								
										<td></td>
										<td></td>	
									</tr>
								 </c:otherwise>
							  </c:choose> 
							 </c:forEach>
				          </tbody>				          				          				          
				        </table> 
	  				</div>
	  				<div class="card-panel">
	  				  <div class="row">
		  					<p class="center-align"
									style="font-size: 20px; margin-bottom: 5px;">
									<em>Zone protette :</em>
							</p>
		  			  </div> 
	  				  <table class="">																				
							<c:forEach var="zone" items="${dettaglioDomanda.descrVociZoneProtetteImport}">
								<tr>
									<td>&#8226; ${zone}</td> 
								</tr>
							</c:forEach>							
						</table>
						<div class="input-field col s6 m4 l12">								
							<p style="font-size:15px;margin-bottom:5px;" class="left-align">
							<em>Informazioni aggiuntive :</em>
							<input type="text" id="impDatoAggiuntivo" 
									value="${fn:escapeXml(dettaglioDomanda.impDatoAggiuntivo)}" maxlength="100" disabled="disabled" /> 								
						</div>
					</div>
					<div class="card-panel">
						<div class="row">
			  					<p class="center-align"
										style="font-size: 20px; margin-bottom: 5px;">
										<em>Le importazioni avvengono principalmente da :</em>
								</p>
			  			</div>		
						<table class="">																				
							<c:forEach var="continenti" items="${dettaglioDomanda.descrVociContinentiImport}">
								<tr>
									<td>&#8226; ${continenti}</td> 
								</tr>
							</c:forEach>							
						</table>
						<div class="input-field col s6 m4 l12">								
							<p style="font-size:20px;margin-bottom:5px;" class="left-align">
							<em>Eventuali stati di origine :</em>
								<input type="text" id="statoOrigine" 
									value="${fn:escapeXml(dettaglioDomanda.statoOrigine)}" maxlength="100" disabled="disabled" /> 								
						</div>	
					</div>
					<div class="card-panel">
					  <div class="input-field col s6 m4 l12">								
					  	<p style="font-size:15px;margin-bottom:5px;" class="left-align">
						<em>Informazioni relative ai tipi di merci di base cui appartengono piante e i prodotti vegetali che l’Operatore professionale intende importare in EU :</em>									
								<textarea id="noteImport" class="materialize-textarea" rows="2"
									disabled="disabled">${fn:escapeXml(dettaglioDomanda.noteImport)}</textarea>	
																	
						</div>
					</div>					
	  				<div>
	  				</div>
	  			</div>	
	  		</div>
	  		</c:if>
			<!-- FINE TAB IMPORT -->
			
			<!-- INIZIO TAB EXPORT -->
			<c:if test="${nuovaDomandaForm.tabExport}">
			<div id="export">
				<div class="card-content">
				  <div class="card-panel">
				  	  <div class="row">
			  					<p class="center-align"
										style="font-size: 20px; margin-bottom: 5px;">
										<em>Le esportazioni riguardano principalmente :</em>
								</p>
			  		  </div>
	  				  <table class="">																			
							<c:forEach var="vociExp" items="${dettaglioDomanda.descrVoceCheckExp}">
								<tr>
									<td>&#8226; ${vociExp}</td> 
								</tr>
							</c:forEach>							
						</table>
						<div class="input-field col s6 m4 l12">								
							<p style="font-size:15px;margin-bottom:5px;" class="left-align">
							<em>Informazioni aggiuntive :</em>
							<input type="text" id="expDatoAggiuntivo" 
									value="${fn:escapeXml(dettaglioDomanda.expDatoAggiuntivo)}" maxlength="100" disabled="disabled" /> 								
						</div>
					</div>
					<div class="card-panel">
						<div class="row">
				  					<p class="center-align"
											style="font-size: 20px; margin-bottom: 5px;">
											<em>Le esportazioni avvengono principalmente verso :</em>
									</p>
				  		 </div>		
						<table class="">																				
							<c:forEach var="continentiExp" items="${dettaglioDomanda.descrVoceCheckContinentiExp}">
								<tr>
									<td>&#8226; ${continentiExp}</td> 
								</tr>
							</c:forEach>							
						</table>
						<div class="input-field col s6 m4 l12">								
							<p style="font-size:15px;margin-bottom:5px;" class="left-align">
							<em>Eventuali stati di destinazione :</em>
								<input type="text" id="statoOrigineExp" 
									value="${fn:escapeXml(dettaglioDomanda.statoOrigineExp)}" maxlength="100" disabled="disabled" /> 								
						</div>	
					</div>
					<div class="card-panel">
					    <div class="row">
				  					<p class="center-align"
											style="font-size: 20px; margin-bottom: 5px;">
											<em>Le esportazioni riguardano le seguenti tipologie :</em>
									</p>
				  		 </div>						
				        <table id="tabellaSpecie" class="data-table  display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">
				          <thead>
				            <tr>				              
				              <th>Tipologia produttiva</th>
				              <th>Note</th>
				              <th>Genere</th>
				              <th>Specie</th>				              
				            </tr>
				          </thead>
				          <tbody id="bodyTabellaSpecie">
				            <c:forEach var="tipologia" items="${dettaglioDomanda.tipologieProdExp}">
				              <c:choose>	
								<c:when test="${not empty tipologia.specieList}">
				              		<c:forEach var="specie" items="${tipologia.specieList}">
									   <tr>																																		
										<td>${tipologia.denomTipologiaEstesa}</td>		
										<td>${tipologia.note}</td>							
										<td>${specie.denomGenere}</td>
										<td>${specie.denomSpecie}</td>									
								      </tr>
								     </c:forEach> 
								 </c:when>
								 <c:otherwise>
								 	<tr>									  	
										<td>${tipologia.denomTipologiaEstesa}</td>	
										<td>${tipologia.note}</td>								
										<td></td>
										<td></td>	
									</tr>
								 </c:otherwise>
							  </c:choose>	     
							</c:forEach>
				          </tbody>				          				          				          
				        </table>                      
			      	</div>
			     </div>
			</div>	
			</c:if>
			<!-- FINE TAB EXPORT -->
			
			<!-- INIZIO TAB PASSAPORTO -->
			<c:if test="${nuovaDomandaForm.tabPassaporto}">
			<div id="passaporto">
			<div class="card-content">
				<div class="card-panel">
				  <div class="row">
			  		 <p class="center-align"
						style="font-size: 20px; margin-bottom: 5px;">
						<em>Richiesta di autorizzazione al rilascio del Passaporto
								delle piante [Reg. (UE) 2016/2031, art. 89]</em>
					 </p>					  																			
					<c:forEach var="voceRadioRichiesta" items="${dettaglioDomanda.descrVoceTipoRichiesta}">
						<tr>
							<td>&#8226; ${voceRadioRichiesta}</td> 
						</tr>
					</c:forEach>						
			  	  </div>
		  	  	</div>
				<div class="card-panel">
				  <div class="input-field col s24 m16 l12">
						<c:choose>
					      <c:when test="${not empty dettaglioDomanda.respPassaporto}">
						 	 <p class="left-align" style="font-size:20px;margin-bottom:5px;">
					  			<em>&#8226; Il Responsabile per la comunicazione con il Servizio Fitosanitario regionale è diverso dal Rappresentante legale :</em>
						  	</p>
						  	<div id="divDatiRespFito" class="row">
								<p class="center-align"
										style="font-size: 20px; margin-bottom: 5px;">
										<em>Responsabile per la comunicazione con il Servizio Fitosanitario Regionale competente in merito alle disposizioni del 
										Regolamento (UE) 2016/2031</em>
								</p>
									<div class="input-field col s6 m4 l4">
										<input type="text" id="cognome"
											value="${fn:escapeXml(dettaglioDomanda.respPassaporto.cognome)}" disabled="disabled" />
											<label for="cognome">Cognome</label>
									</div>
									<div class="input-field col s6 m4 l4">
										<input type="text" id="nomeRespPass"
											value="${fn:escapeXml(dettaglioDomanda.respPassaporto.nome)}" disabled="disabled" />										
										<label for="nomeRespPass">Nome</label>
									</div>						
									<div class="input-field col s6 m4 l4">
										<input type="text" id="codiceFiscale"
											value="${fn:escapeXml(dettaglioDomanda.respPassaporto.codiceFiscale)}" disabled="disabled" />										
										<label for="codFiscaleRespPass">Codice fiscale</label>
									</div>
									<div class="input-field col s6 m4 l4">
									<input type="text" id="telefono" 
									      value="${fn:escapeXml(dettaglioDomanda.respPassaporto.telefono)}" disabled="disabled"/>									
									<label for="numTelefonoRespPass">Numero di telefono</label>
								</div>
								<div class="input-field col s6 m4 l4">
									<input type="text" id="cellulare"
										value="${fn:escapeXml(dettaglioDomanda.respPassaporto.cellulare)}" disabled="disabled"/>									
									<label for="cellulareRespPass">Numero di cellulare</label>
								</div>						
								<div class="input-field col s6 m4 l4">
										<input type="text" id="email"
											value="${fn:escapeXml(dettaglioDomanda.respPassaporto.email)}" disabled="disabled"/>
										<label for="emailRespPass">Email</label>
								  </div>
								  <div class="input-field col s6 m4 l4">
										<input type="text" id="qualificaProfessionale"
											value="${fn:escapeXml(dettaglioDomanda.respPassaporto.qualificaProfessionale)}" disabled="disabled"/>
										<label for="qualificaProfRespPass">Qualifica professionale</label>
								  </div>
							</div>
						  </c:when>
						  <c:otherwise>
						    <p class="left-align" style="font-size:20px;margin-bottom:5px;">
					  			<em>&#8226; Il Responsabile per la comunicazione con il Servizio Fitosanitario regionale è uguale al Rappresentante legale</em>
						  	</p>
						  </c:otherwise>
						</c:choose>														
					</div>
				</div>	
				<div class="card-panel">
				  <div class="row">
			  		 <p class="center-align"
						style="font-size: 20px; margin-bottom: 5px;">
						<em>Informazioni relative a piante, prodotti vegetali e altri oggetti che devono essere movimentate con un Passaporto delle piante :</em>
					 </p>
					  <table class="">																				
							<c:forEach var="voceRadioPass" items="${dettaglioDomanda.descrVoceRadioCheckedPass}">
								<tr>
									<td>&#8226; ${voceRadioPass}</td> 
								</tr>
							</c:forEach>							
						</table>
			  	  </div>
			  	  <div class="row">
			  	    <table class="">																				
							<c:forEach var="voceCheckPass" items="${dettaglioDomanda.descrVoceCheckedPass}">
								<tr>
									<td>&#8226; ${voceCheckPass}</td> 
								</tr>
							</c:forEach>							
					</table>
					<div class="col l12">
								<p>
									(1) persona che, non agendo per i fini commerciali o
									professionali, acquista piante o prodotti vegetali per uso
									personale<br> (2) qualsiasi contratto concluso tra il
									venditore e l’acquirente nel quadro di un regime organizzato di
									vendita o di prestazione di servizi a distanza <br> senza
									la presenza fisica e simultanea del venditore e
									dell’acquirente; ciò, mediante l'uso esclusivo di uno o più
									mezzi di comunicazione a distanza (ad esempio <br>
									utilizzando telefono, fax, internet, e-mail, lettere, cataloghi
									online, etc.)
								</p>
					</div>
			  	  </div>
				</div>	
				<div class="card-panel">
					    <div class="row">
				  		  <p class="center-align"
								style="font-size: 20px; margin-bottom: 5px;">
								<em>Categorie di interesse :</em>
						  </p>
				  		 </div>	
				  		 <div class ="row">					
					         <table id="tabellaSpecie" class="data-table striped display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">
					          <thead>
					            <tr>				             
					              <th>Tipologia produttiva</th>
					              <th>Genere</th>
					              <th>Specie</th>				              
					            </tr>
					          </thead>
					          <tbody id="bodyTabellaSpecie">
					            <c:forEach var="tipologia" items="${dettaglioDomanda.tipologieProdPass}">
					              <c:forEach var="specie" items="${tipologia.specieList}">
									   <tr>																																			
										<td>${tipologia.denomTipologiaEstesa}</td>									
										<td>${specie.denomGenere}</td>
										<td>${specie.denomSpecie}</td>									
								      </tr>
								     </c:forEach> 
								 </c:forEach>
					          </tbody>				          				          				          
					      </table>
				      </div>
				</div>	
				
				<c:if test="${dettaglioDomanda.zoneProtettePass != null && dettaglioDomanda.zoneProtettePass.size() >0}">
		          <div class="card-panel">
					<div class="row">
				  		  <p class="center-align"
								style="font-size: 20px; margin-bottom: 5px;">
								<em>Zone protette :</em>
						  </p>
				  	</div>
				  	<div class="row">
				  	  <table id="tabellaZoneProtette" class="data-table striped display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">
				          <thead>
				            <tr>
				              <th>Zona Protetta</th>
				              <th>Genere</th>
				              <th>Specie</th>				              
				            </tr>
				          </thead>
				          <tbody id="bodyTabellaZoneProtette">
				            <c:forEach var="zonaProtetta" items="${dettaglioDomanda.zoneProtettePass}">
				              <c:forEach var="specie" items="${zonaProtetta.specieList}">
								   <tr>																																		
									<td>${zonaProtetta.denomZonaProtetta}</td>									
									<td>${specie.denomGenere}</td>
									<td>${specie.denomSpecie}</td>									
							      </tr>
							     </c:forEach> 
							 </c:forEach>
				          </tbody>				          				          				          
				        </table>
				  	</div>
				  </div>		  
	        	</c:if>
	        	<div class="card-panel">
					<p class="center-align"
						style="font-size: 20px; margin-bottom: 5px;">
						<em>L’operatore professionale dichiara </em>
					</p>
					<div class="row"></div>

					<table class="">
						<tr>
							<td>&#8226; <c:forEach var="voceConoscenze"
									items="${dettaglioDomanda.voceDichiaraConoscenze}">						
							 ${voceConoscenze}													
						</c:forEach>
						<c:choose>
							<c:when test="${dettaglioDomanda.voceDichiaraConoscenzeUtente == 'S'}"> 
								<label class="radio-inline"> 
									<input type="radio" id="voceDichiaraConoscenzeUtente" checked="checked"/> 
									<span>Si</span>
									<input type="radio" id="voceDichiaraConoscenzeUtente"  disabled="disabled"/> 
									<span>No</span>
								</label>
							</c:when>
							<c:otherwise>
								<label class="radio-inline"> 
									<input type="radio" id="voceDichiaraConoscenzeUtente" disabled="disabled"/> 
									<span>Si</span>
									<input type="radio" id="voceDichiaraConoscenzeUtente" checked="checked"/> 
									<span>No</span>
								</label> 
							</c:otherwise>	
							</c:choose>
						</td>
						</tr>
					</table>
					<table class="">
						<tr>
							<td>&#8226; <c:forEach var="voceSistemi"
									items="${dettaglioDomanda.voceDichiaraDisporreSistemi}">
								 ${voceSistemi}						
							</c:forEach>
							<c:choose>
							<c:when test="${dettaglioDomanda.voceDichiaraDisporreSistemiUtente == 'S'}"> 
								<label class="radio-inline"> 
									<input type="radio" id="voceDichiaraDisporreSistemiUtente" checked="checked"/> 
									<span>Si</span>
									<input type="radio" id="voceDichiaraDisporreSistemiUtente"  disabled="disabled"/> 
									<span>No</span>
								</label>
							</c:when>
							<c:otherwise>
								<label class="radio-inline"> 
									<input type="radio" id="voceDichiaraDisporreSistemiUtente" disabled="disabled"/> 
									<span>Si</span>
									<input type="radio" id="voceDichiaraDisporreSistemiUtente" checked="checked"/> 
									<span>No</span>
								</label> 
							</c:otherwise>	
							</c:choose>
							</td>
						</tr>
					</table>

					<p class="center-align"
						style="font-size: 20px; margin-bottom: 5px;">
						<em>L’operatore professionale si impegna a </em>
					</p>
					<div class="row"></div>
					<table class="">	
						<tr>
							<td>&#8226; identificare e controllare i punti critici del suo processo di produzione e i punti relativo allo spostamento di piante, prodotti vegetali e altri oggetti che risultano critici per quanto riguarda il rispetto della norma vigente</td> 
						</tr>
						<tr>
							<td>&#8226; conservare per almeno tre anni i dati riguardanti l’identificazione e il controllo dei punti critici dei propri processi di produzione</td>
						</tr>
						<tr>
							<td>&#8226; assicurare una formazione adeguata al personale che partecipa all’esecuzione degli esami di cui all’art.87 del Reg. (UE) 2016/2031 al fine di garantire che possieda le conoscenze necessarie per effettuare gli esami</td>
						</tr>
					</table>	
					<div class="row"></div>					
				    <c:forEach var="vocePiano" items="${dettaglioDomanda.vocePianoRischi}">
						<tr>
							<td>&#8226; ${vocePiano} </td> 
						</tr>
					</c:forEach> 
					<c:choose>
						<c:when test="${dettaglioDomanda.vocePianoRischiUtente == 'S'}"> 
							<label class="radio-inline"> 
								<input type="radio" id="vocePianoRischiUtente" checked="checked" /> 
								<span>Si</span>
								<input type="radio" id="vocePianoRischiUtente"  /> 
								<span>No</span>
							</label>
						</c:when>
						<c:otherwise>
							<label class="radio-inline"> 
								<input type="radio" id="vocePianoRischiUtente" /> 
								<span>Si</span>
								<input type="radio" id="vocePianoRischiUtente" checked="checked"  /> 
								<span>No</span>
							</label> 
						</c:otherwise>	
					</c:choose>	
				</div>		
			</div>
		</div>		
		</c:if>	
			<!-- FINE TAB PASSAPORTO -->
			
			<!-- INIZIO TAB ALLEGATI -->
			<div id="allegati">
			<div class="card-content">
			<c:if test="${not empty listaModuli}">
				<div class="card-header white-text">
						<h5 class="card-title bg-primary-color text-shadow title-padding"><b>I moduli proposti sono da scaricare, compilare e caricare nell'ultima colonna</b></h5>
					</div>
					<div class="card-content">
						<c:forEach var="modulo" items="${listaModuli}">
							<div class="row valign-wrapper">
								<div class="col s9">
									${fn:escapeXml(modulo.descModulo)}
								</div>						
								<div class="center col s3">
									<c:if test="${not empty modulo.idModulo}">
									<a href="<spring:url value="/autorizzazioni/comunicazioni/modulo/scarica/${modulo.idModulo}" />"
										class="tooltipped" data-tooltip="Scaricare il modulo ${fn:escapeXml(modulo.nomeFile)}">
										<i class="small material-icons primary-color">save</i></a>
									</c:if>
								</div>								
							</div>
						</c:forEach>
					</div>
				</c:if>
				<div class="row">					
					<c:if test="${not empty dettaglioDomanda.listaAllegati}">
					<div class="card-header white-text">
							<h5 class="card-title bg-primary-color text-shadow title-padding"><b>Allegati</b></h5>
					</div>
					<div class="card-content">
						<c:forEach var="allegato" items="${dettaglioDomanda.listaAllegati}">
						<c:if test="${allegato.flagMultiplo}">
							<div class="divider"></div>
						</c:if>
						<div class="row valign-wrapper" style="${allegato.flagMultiplo ? 'margin-bottom:0' : ''}">
							<<c:if  test="${allegato.flagMultiplo}">
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
										<c:forEach var="allegatoComunicazione" items="${allegato.listaAllegati}" >
										<tr>
											<td class="center input-field">
												<a href="<spring:url value="/autorizzazioni/comunicazioni/allegato/scarica/${allegatoComunicazione.idAllegatoDomanda}" />"
													class="tooltipped" data-tooltip="Scaricare l'allegato ${fn:escapeXml(allegatoComunicazione.nomeFile)}">
													<i class="small material-icons primary-color">save</i></a>
											</td>	
											<td>
													<input type="text" id="descAllegato" value="${fn:escapeXml(allegatoComunicazione.descAllegato)}"	disabled="disabled" /> 									 
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
										<a href="<spring:url value="/autorizzazioni/comunicazioni/allegato/scarica/${allegato.listaAllegati[0].idAllegatoDomanda}" />"
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
			<!-- FINE TAB ALLEGATI -->
			
			<!-- INIZIO TAB GESTIONE -->
			<c:if test="${isSuperUser and statoInBozza != nuovaDomandaForm.idStatoComunicazione && idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
			<div id="gestione">
				
			 <div class="card-panel">
	  				<div class="row" >
	  				<div class="input-field col s6 m4 l3">
						<input type="text" id="codiceRuop"
							value= "${fn:escapeXml(dettaglioDomanda.codiceRuop)}" disabled="disabled" />
						<label for="codiceRuop">Codice ruop</label>
					</div>
					<div class="input-field col s6 m4 l3">
								<fmt:formatDate value="${dettaglioDomanda.dataRegistrazioneRuop}" pattern="dd/MM/yyyy" var="dataRegRuop" />
				              	<input type="text" value="${dataRegRuop}" id="dataRegistrazioneRuop" disabled="disabled" /> 
				              	<label for="dataRegistrazioneRuop">Data registrazione Ruop</label>
					</div>
					<c:if test="${idTipoDomandaPassaporto != nuovaDomandaForm.idTipoComunicazione}">
						<div class="input-field col s12 m8 l6">
							<input type="text" id="descIspettore"
								value= "${fn:escapeXml(dettaglioDomanda.descIspettore)}" disabled="disabled" />
							<label for="descIspettore">Ispettore o Agente</label>
						</div>
					</c:if>
					<div class="input-field col s12 m8 l6">
						<input type="text" id="numeroProtocollo"
							value= "${fn:escapeXml(dettaglioDomanda.numeroProtocollo)}" disabled="disabled" />
						<label for="numeroProtocollo">Numero protocollo</label>
					</div>
					<div class="input-field col s12 m8 l6">
						<fmt:formatDate value="${dettaglioDomanda.dataProtocollo}" pattern="dd/MM/yyyy" var="dataProt" />
				              	<input type="text" value="${dataProt}" id="dataProtocollo" disabled="disabled" /> 
				              	<label for="dataProtocollo">Data protocollo</label>
					</div>
					<c:if test="${idTipoDomandaPassaporto != nuovaDomandaForm.idTipoComunicazione}">
						<div class="input-field col s12 m8 l6">
							<input type="text" id="codiceFitok"
								value= "${fn:escapeXml(dettaglioDomanda.codiceFitok)}" disabled="disabled" />
							<label for="codiceFitok">Codice fitok</label>
						</div>						
						<div class="input-field col s12 m8 l6">
							<input type="text" id="tariffa"
								value= "${fn:escapeXml(dettaglioDomanda.tariffa)}" disabled="disabled" />
							<label for="tariffa">Tariffa</label>
					</div>	
					</c:if>	
					<div class="input-field col s24 m16 l12">
					<textarea id="note" class="materialize-textarea" rows="2"
									disabled="disabled">${fn:escapeXml(dettaglioDomanda.note)}</textarea>
						<label for="note">Note</label>
					</div>
					<c:if test="${idTipoDomandaPassaporto == nuovaDomandaForm.idTipoComunicazione}">
						<div class="row">
							<div class="input-field col s6 m4 l3">
								<fmt:formatDate value="${dettaglioDomanda.dataAutorizzazionePassaporto}" pattern="dd/MM/yyyy" var="dataAutPass" />
				              	<input type="text" value="${dataAutPass}" id="dataAutorizzazionePassaporto" disabled="disabled" /> 
				              	<label for="dataAutorizzazionePassaporto">Data autorizzazione passaporto</label>
							</div>
							<div class="input-field col s12 m8 l6">
							<input type="text" id="tipologiaPassaporto"
								value= "${fn:escapeXml(dettaglioDomanda.tipologiaPassaporto)}" disabled="disabled" />
							<label for="tipologiaPassaporto">Tipologia passaporto</label>
						</div>
						</div>	
					</c:if>
					
				  </div>	
				</div>
							
			</div>
			</c:if>	
			<!-- FINE TAB GESTIONE -->
				
	</div>
</div>
		
		<div class="row">
			<a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>										        	        		    		        
	   	</div>



		<div id="dettaglioCentroAzModal" class="modal scrollbar-thin long-modal modal-70">					
			<div class="modal-content" style="padding-top:0; padding-bottom:0;">
			  
			
			</div>
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
			$("a[data-value='anagrafica']").click();
		});
		
		function dettaglioCentroAziendale(idCentroAziendale){
			console.log('dettaglioCentroAziendale');
			
			console.log('idCentroAziendale ='+idCentroAziendale);
			
			// chiamata ajax per il dettaglio centro aziendale ed apertura modale
			$.ajax({
				url : '<spring:url value="/autorizzazioni/comunicazioni/centroaziendale/dettaglio"/>/' +idCentroAziendale
			}).done(function(response) {
				apriModaleCentroAziendale(response, idCentroAziendale);
			}).fail(function(jqXHR, textStatus) {
			    console.log('FAIL dettaglioCentroAziendale! TEXT STATUS: '+textStatus);			    
			});			
		}
		
		
		function apriModaleCentroAziendale(content, idCentroAziendale) {
			console.log('apriModaleCentroAziendale');
			
			$modal = $('#dettaglioCentroAzModal');
			
			$modalContent = $modal.find('div.modal-content');
			$modalContent.empty();
			
			var instance = M.Modal.getInstance($modal);
			//console.log('INSTANCE: '+instance.id);
			if (instance.isOpen) {
				console.log('Modale aperta!!!');
				instance.destroy();
				console.log('Modale distrutta!!!');
			}
			
			console.log('prima di modalContent');
			console.log('content'+content);
			$modalContent.html(content);
			
			
			if (instance) {
				editInit(idCentroAziendale);
			}
			
			//$modal.find('select').formSelect();
			console.log('updateTextFields');
			M.updateTextFields();
			
			console.log('open dettaglioCentroAzModal');
			$modal.modal('open');
		}
		
		</script>
</content>		


</body>
</html>