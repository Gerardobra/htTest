<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
</head>
<body>
	<spring:url value="/autorizzazioni/comunicazioni/centroaziendale/modifica/salva" var="formAction" />
	<spring:url value="/autorizzazioni/comunicazioni" var="autorizzAction" />
	<form:form action="${formAction}" id="form" method="post" modelAttribute="nuovaDomandaForm" accept-charset="utf-8">
	   <input type="hidden" id="chiamata" name="chiamata" value=""/>
	   <input type="hidden" id="idVoceUtenteElim" name="idVoceUtenteElim" value=""/>
	   <input type="hidden" id="idSpecieElim" name="idSpecieElim" value=""/>	
	   <spring:eval var="tipProdGenereObblig"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIP_PROD_GENERE_OBBLIGATORIO" />	
	   <input type="hidden" value=${tipProdGenereObblig} id="listaTipologieProduttive" />	   
		<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>MODIFICA DOMANDA : ${nuovaDomandaForm.descTipoDomanda}</b>
				<jsp:include page="../datiAnagraficiAzienda.jsp" >
					<jsp:param name="cuaa" value="${nuovaDomandaForm.codFiscaleAz }" />
					<jsp:param name="ruop" value="${nuovaDomandaForm.codiceRuop }" />
					<jsp:param name="ragSociale" value="${nuovaDomandaForm.denomAzienda }" />
				</jsp:include>
				</h3>
			</div>
			<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
  			<spring:eval var="statoInBozza"	expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />		
  			
  			<div class="card-content" style="padding-top:5px;">
  				<div class="row">
   	 				<div class="col s12">
      					<ul class="tabs">
      						<li class="tab" >
      							<a href="<spring:url value="/autorizzazioni/comunicazioni/anagrafica/modifica" />" target="_self">Anagrafica</a>
      						</li>
        					<li class="tab" >
        						<a href="<spring:url value="/autorizzazioni/comunicazioni/azienda/modifica" />" target="_self">Dati operatore</a>
        					</li>
        					<spring:eval var="idTipoDomandaRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP" />
							<spring:eval var="idTipoDomandaVariazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP" />
        					<spring:eval var="idTipoDomandaCancellazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP" />
        					<spring:eval var="idTipoDomandaPassaporto" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO" />
        					<c:if test="${idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">        					      
		        				   <c:forEach var="ceAz" items="${nuovaDomandaForm.listaCentriAz}">
			        				  <c:if test="${ceAz.idCentroAziendale == nuovaDomandaForm.idCentroAziendaleSel}"> 
			        				  <li class="tab" >
			        					  <a class="active"><b>${ceAz.codCentroAziendale}</b></a>
			        				    </li>       		
			        				  </c:if>
			        				  <c:if test="${ceAz.idCentroAziendale != nuovaDomandaForm.idCentroAziendaleSel}">   
			        				  	<li class="tab" >
				        					<a href="<spring:url value="/autorizzazioni/comunicazioni/centroaziendale/modifica/${ceAz.idCentroAziendale}" />" target="_self">${ceAz.codCentroAziendale}</a>
				        				  </li>     		
			        				  </c:if>
		        				  </c:forEach>
		        				  <c:if test="${nuovaDomandaForm.tabImport}">
		        					<li class="tab" >
		        						<a href="<spring:url value="/autorizzazioni/comunicazioni/impAuto/modifica" />" target="_self">Import</a>
		        					</li>
	        					  </c:if>
	        					  <c:if test="${nuovaDomandaForm.tabExport}">
		        					<li class="tab" >
		        						<a href="<spring:url value="/autorizzazioni/comunicazioni/expAuto/modifica" />" target="_self">Export</a>
		        					</li>	        					
	        					  </c:if>	  
	        					  <c:if test="${nuovaDomandaForm.tabPassaporto}">     					
									  <li class="tab" >
				        			    <a href="<spring:url value="/autorizzazioni/comunicazioni/passaporto/modifica" />" target="_self">Passaporto</a>
				        			  </li>		        				
			        			  </c:if>
		        			</c:if>
			        	    <li class="tab" >
       							<a href="<spring:url value="/autorizzazioni/comunicazioni/allegati/modifica" />" target="_self">Allegati</a>
       					    </li>
			        		<c:if test="${idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
									<c:if test="${isSuperUser and statoInBozza != nuovaDomandaForm.idStatoComunicazione}">
	        					  	<li class="tab" >
				        			    <a href="<spring:url value="/autorizzazioni/comunicazioni/gestione/modifica" />" target="_self">Gestione</a>
				        			 </li>	
	        					  </c:if>
	        				</c:if>  	 	
      					</ul>
    				</div>
  				</div>
  			
    <jsp:include page="../includes/boxheader.jsp"></jsp:include>    				        		        			  														
			<!-- DATI CENTRO AZIENDALE (CAR_T_CENTRO_AZIENDALE)-->
        				          				        		        			  														
			<!-- INSERIMENTO DATI CENTRO AZIENDALE (CAR_T_CENTRO_AZIENDALE)-->
		
				  <div class="row">				
				 					
								<div class="row col l12">
										<div class="input-field col s12 m8 l6">
											<form:input type="text" id="denominazCentroAz" name="denominazCentroAz"
												path="denominazCentroAz" cssClass="myRequired campiAz"
												 maxlength="100" />
											<form:errors path="denominazCentroAz" cssClass="error" />
											<label for="denominazCentroAz">Denominazione</label>
										</div>	
									<div class="input-field col s6 m4 l6">
											<form:input type="text" id="codCentroAz" name="codCentroAz"
												path="codCentroAz" cssClass="campiAz" readonly="true" style="visibility:hidden;"/>		
									</div>								
								</div>				
													
								<div class="row col l12">
										<div class="input-field col s6 m4 l6">
											<form:select id="idProvCentroAz" path="idProvCentroAz"
												cssClass="validate myRequired campiAz" required="required">
												<form:option value="" label="Selezionare" />
												<form:options items="${listaProvinceCentroAz}" itemValue="idProvincia"
													itemLabel="denomProvincia" />
											</form:select>
											<form:errors path="idProvCentroAz" cssClass="error" />
											<label for="labelidProvCentroAz">Provincia *</label>
										</div>
										<div class="input-field col s6 m4 l6">
											<form:select id="idComuneCentroAz" path="idComuneCentroAz"
												cssClass="validate myRequired campiAz" required="required">
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
										  <form:input type="text" id="capCentroAz" name="capCentroAz" required="required" maxlength="5" path="capCentroAz" cssClass="myRequired campiAz" />
										  <form:errors path="capCentroAz" cssClass="error" />
										  <label id="labelcapCentroAz" for="capCentroAz">CAP *</label>
										</div>
										<div class="input-field col s6 m4 l6">
											<form:input type="text" id="indirizzoCentroAz" name="indirizzoCentroAz"
												path="indirizzoCentroAz" cssClass="myRequired campiAz" required="required" maxlength="200" />
											<form:errors path="indirizzoCentroAz" cssClass="error" />
											<label for="indirizzoCentroAz">Indirizzo (Es. Via Roma, 24)*</label>
										</div>
										<div class="input-field col s6 m4 l6">
											<form:input type="text" id="frazioneCentroAz"
												path="frazioneCentroAz" cssClass="campiAz" maxlength="100" />
											<form:errors path="frazioneCentroAz" cssClass="error" />
											<label for="frazioneCentroAz">Frazione</label>
										</div>
									</div>
									
									
									<div class="hiddenField row col l12">
										<div class="input-field col s6 m4 l6">
											<form:input type="text" id="telefonoCentroAz"
												path="telefonoCentroAz" cssClass="campiAz"  maxlength="20" />
											<form:errors path="telefonoCentroAz" cssClass="error" />
											<label for="telefonoCentroAz">Telefono (Es. 0245673467)</label>
										</div>
										<div class="input-field col s6 m4 l6">
											<form:input type="text" id="cellulareCentroAz"
												path="cellulareCentroAz" cssClass="campiAz"  maxlength="30" />
											<form:errors path="cellulareCentroAz" cssClass="error" />
											<label for="cellulareCentroAz">Cellulare (Es. 3471234567)</label>
										</div>						
									</div>
											
									<div class="hiddenField row col l12">																		
										<div class="input-field col s6 m4 l6">
														<form:input type="email" id="mailCentroAz"
															cssClass="campiAz" path="mailCentroAz"
															class="validate"  />
														<form:errors path="mailCentroAz" cssClass="error" />
														<label for="labelmailCentroAz">Email </label>
														<span class="helper-text" data-error="Indirizzo email non valido"
				                      											  data-success="Indirizzo email valido"></span>
													</div>
										<div class="input-field col s6 m4 l6">
														<form:input type="email" id="pecCentroAz"
															cssClass="myRequired campiAz" path="pecCentroAz" class="validate"
															 />
														<form:errors path="pecCentroAz" cssClass="error" />
														<label id="labelPec">PEC</label>
														<span class="helper-text" data-error="Indirizzo email non valido"
				                      											  data-success="Indirizzo email valido"></span>
										</div>
								  </div>
						
								
											<div id="datiCentroAziendale" class="row">        				  
			<div class="card panel">	
				<!-- ELENCO CENTRI AZIENDALI -->		      
					
					
					
			</div>

					</div>
					
					<!-- Tipologie produttive -->					
				  <div class="card-panel" id="tipologieProdDiv" >
				    <div class="row">										
				 	  <ul class="collapsible" data-collapsible="accordion">
						<li>
							<div class="collapsible-header" id="datiIspezione">
								<i class="material-icons">arrow_drop_down</i>Aggiungi tipologia produttiva
							</div>							
						   <div class="collapsible-body">
						      
						       <div class="row col l12">				  				  				  
								  <div class="input-field col s12 m8 l12">
										<form:select id="idTipologiaProd" name="tipologiaProduttiva" path="idTipologiaProd" cssClass="campiAz" >
											<form:option value="" label="Selezionare" />
											<form:options items="${listaTipologieProd}" itemValue="idVoce"
												itemLabel="descBreve" />
										</form:select>
										<form:errors path="idTipologiaProd" cssClass="error" />
										<label for="labelidTipologiaProd">Tipologia produttiva *</label>
								  </div>											    			
							    </div>	
							    <!-- Campo note -->
					            <div id="divNoteTipologia" class="row col l12">				  				 						  				 					  				  		
						            	  <div class="input-field col s12 m8 l12">
												<form:textarea id="noteTipologiaCentroAz" path="noteTipologiaCentroAz" 
												  cssClass="materialize-textarea validate tooltipped" rows="2"
													data-length="500" maxlength="500" data-position="right" 
													data-tooltip="Note"/>
												<form:errors path="noteTipologiaCentroAz" cssClass="error" />
										 	<label for="noteTipologiaCentroAz">Note</label>
										 </div>
								</div>
	            				<!-- FINE Campo note -->			
								<div class="row col l12">
										<div class="input-field col s12 m5 l6">
							              <input type="text" name="genere" placeholder="Scrivere generi più rappresentativi" id="genereTipologiaProduttiva" 
							                class="validate autocomplete campiAz" autocomplete="off"/>
							              <label class="active">Genere *</label>
							            </div>            
							            <!-- <div class="input-field col s12 m5 l6">
							              <select name="specie" multiple="multiple" id="specieTipologiaProduttiva" 
							                class="validate multiselect campiAz">
							                <option value="" disabled="">SELEZIONARE</option>
							              </select>
							              <label>Specie *</label>
							            </div>		-->					
								</div>	
								<div class="row">							    	            
				            		<button class="btn green waves-effect waves-light"  style="button"
	              						type="submit" name="aggiungiTipologiaProd" id="aggiungiTipologiaProd" formnovalidate="formnovalidate" >
	              						SALVA
	              					</button>
	              				</div>	
	              				              				
	              						              													
							</div> <!-- FINE collapsible-body -->						  
						</li>
					</ul>	
				  </div>
				  
				  <div class="row">
						        <table id="tabellaSpecie" class="data-table striped display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">				        
						          <thead>
						            <tr>
						              <th></th>
						              <th>Tipologia produttiva del centro aziendale</th>
						              <th>Note</th>
						              <th>Genere</th>
						              <th>Specie</th>				              
						            </tr>
						          </thead>
						          <tbody id="bodyTabellaSpecie">				            
						            <c:forEach var="tipologia" items="${tipologieProdDb}">	
						            <c:choose>
			            				<c:when test="${not empty tipologia.specieList}">			              
							              	<c:forEach var="specie" items="${tipologia.specieList}">
											   		<tr>								     
														<td  style="white-spaces: nowrap">
															<sec:authorize access="hasRole('WRITE') ">	
															<c:choose>	
																<c:when test="${specie.idSpecie != null}">														  
															   		<a href="${autorizzAction}/centroaziendale/modifica/eliminaTipProduttiva/idTip/${tipologia.idTipologia}/idSpecie/${specie.idSpecie}" 
																	  	title="Elimina" 
																	    class="btn btn-floating btn-floating-medium deep-orange accent-2"
																	    onclick="return eliminaTipologiaProdut(this)">
																		<i class="material-icons">delete</i>
															   		</a>	
															   </c:when>	
										  					   <c:otherwise>  
																   <a href="${autorizzAction}/centroaziendale/modifica/eliminaTipProduttiva/idTip/${tipologia.idTipologia}" 
																	  	title="Elimina" 
																	    class="btn btn-floating btn-floating-medium deep-orange accent-2"
																	    onclick="return eliminaTipologiaProdut(this)">
																		<i class="material-icons">delete</i>
																   </a>	
															   </c:otherwise>
															</c:choose>										  
															</sec:authorize>
														</td>																										
														<td>${tipologia.denomTipologia}</td>
														<td style="word-break: break-word">${tipologia.note}</td>									
														<td>${specie.denomGenere}</td>
														<td>${specie.denomSpecie}</td>									
										      		</tr>
										     </c:forEach> 
									     </c:when>	
						     			<c:otherwise>
						    				<tr>
											  	<td nowrap style="white-spaces: nowrap">
													<sec:authorize access="hasRole('WRITE') ">	
														<a href="${autorizzAction}/centroaziendale/modifica/eliminaTipProduttiva/idTip/${tipologia.idTipologia}" 
														    title="Elimina" 
														    class="btn btn-floating btn-floating-medium deep-orange accent-2"
														    onclick="return eliminaTipologiaProd(this)">
																<i class="material-icons">delete</i>
														  </a>
													  </sec:authorize>
												</td>
												<td>${tipologia.denomTipologia}</td>	
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
	            	<!-- FINE Tipologie produttive -->
	            	
	            	
	            	<!-- Siti di produzione -->
	            	<div class="card-panel" id="sitiProdDiv" >
	            	  <div class=row">
						<ul class="collapsible" id="collapsible" data-collapsible="accordion">
						  <li>
							<div class="collapsible-header" id="datiIspezione">
								<i class="material-icons">arrow_drop_down</i>Aggiungi Siti di produzione
							</div>
							<div class="collapsible-body">							   
								<div class="input-field col s12 m8 l12">
									<form:input type="text" name="descSitoProduzione" id="descSitoProduzione" path="descSitoProduzione" maxlength="100"/>
									<form:errors path="descSitoProduzione" cssClass="error" />
									<label for="descSitoProduzione">Sito di produzione</label>
								  </div>
							    <div class="input-field col s12 m8 l12">
								    <form:input type="text" name="ubicazione" id="ubicazione" path="ubicazione" maxlength="50"/>
									<form:errors path="ubicazione" cssClass="error" />
									<label for="ubicazione">Indirizzo</label>
								</div>
								<div class="input-field col s12 m4">
									<form:select id="provinciaSitoProd" name="provinciaSitoProd" path="provinciaSitoProd"
										required="required" class="validate">
										<form:option value="" label="Selezionare" />
										<form:options items="${listaProvinceCentroAz}" itemValue="idProvincia"
											itemLabel="denomProvincia" />
									</form:select>
									<form:errors path="provinciaSitoProd" cssClass="error" />
									<label for="provinciaSitoProd">Provincia *</label>
								</div>
								<div class="input-field col s12 m8">
									<form:select id="comuneSitoProd" name="comuneSitoProd" path="comuneSitoProd" 
										required="required" class="validate" >
										<form:option value="" label="Selezionare" />
										<form:options items="${listaComuniCentroAz}" itemValue="idComune"
											itemLabel="denomComune" />
									</form:select>
									<form:errors path="comuneSitoProd" cssClass="error" />
									<label for="comuneSitoProd">Comune *</label>
								</div>
								<div class="input-field col s6 m4">
									<form:input name="foglio" id="foglio" path="foglio" required="required" 
										class="validate" pattern="\d*" maxlength="4" 
										oninvalid="this.setCustomValidity('Foglio non valido')" 
										oninput="this.setCustomValidity('')" />
									<form:errors path="foglio" cssClass="error" />
									<label for="foglio">Foglio *</label>
								</div>
								<div class="input-field col s6 m4">
									<form:input name="mappale" id="mappale" path="mappale" required="required" 
										class="validate" pattern="\d*" maxlength="8" 
										oninvalid="this.setCustomValidity('Mappale non valida')" 
										oninput="this.setCustomValidity('')" />
									<form:errors path="mappale" cssClass="error" />
									<label for="mappale">Mappale *</label>
								</div>
								<div class="input-field col s6 m4">
									<form:input name="superficie" id="superficie" path="superficie" required="required" 
										class="validate" pattern="\d+([\.,]\d{1,2})?" maxlength="10" 
										oninvalid="this.setCustomValidity('Superficie non valida')" 
										oninput="this.setCustomValidity('')" />
									<form:errors path="superficie" cssClass="error" />
									<label for="superficie">Superficie (mq.) *</label>
								</div>		
								<div class="row">	
									<button class="btn green waves-effect waves-light"  style="button"
	              						type="submit" name="aggiungiSitoProduzione" id="aggiungiSitoProduzione" formnovalidate="formnovalidate">
	              						SALVA
	              					</button>	
	              				</div>				
									 							
							</div> <!-- FINE collapsible-body -->			
						  </li>
					   </ul>  	
					   </div>
				
					  <div class="row">
							       	<table id="tabellaSitiProduzione" class="data-table striped display" 
							       		data-order='[[ 1, "asc" ], [ 2, "asc" ], [ 3, "asc" ], [ 4, "asc" ]]' data-paging="false" data-info="false">
										<thead>
											<tr>
												<th data-orderable="false"></th>
												<th>Sito di produzione del centro aziendale</th>
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
											<td nowrap style="white-spaces: nowrap">
												<sec:authorize access="hasRole('WRITE') ">											
													  <a href="${autorizzAction}/modifica/centroaziendale/eliminaSitoProd/${sitoProd.idSitoProduzione}"  
													    title="Elimina"
													    class="btn btn-floating btn-floating-medium deep-orange accent-2" 
													    onclick="return eliminaSitoProd(this)">
															<i class="material-icons">delete</i>
													  </a>											   																				
												</sec:authorize>
											</td>																										
											<td>${sitoProd.denominazione}</td>
											<td>${sitoProd.ubicazione}</td>
											<td>${sitoProd.denomComune} (${sitoProd.siglaProvincia})</td>
											<td>${sitoProd.foglio}</td>									
											<td>${sitoProd.mappale}</td>
											<td>${sitoProd.superficie}</td>
									      </tr>
									    </c:forEach>
										</tbody>																
									</table>											
					 </div> 		
					   												   					   					    
	            	</div>	            	            	
	            	<!-- FINE Siti di produzione -->	            	
								
				<!-- </div>-->
		      </div>
					
			
			
				
			
		</div> <!-- Chiusura div che appare se viene selezionata la checkbox iniziale -->
       		   
	</form:form>		   		
	<br />
	</div>
	<div class="row">
				<a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
											        
		        <button id="avantiCentroAziendale" name="avantiCentroAziendale" type="submit" 
			              	class="btn confirm waves-effect waves-light right" formnovalidate="formnovalidate" style="button">SALVA
			    </button>		    		         
		   	</div>
	
	<content tag="script"> 
		<script type="text/javascript" src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script> 
		<script>
				
		
		// Al caricamento della pagina
		$(document).ready(function() {			
			// Reperimento dei generi e popolazione Specie		
	        $('input[name=genere]').each(function() {
	        	let $self = $(this);
	        	$self.autocomplete({
	        		limit: 50,
	        		minLength: 2,
	        		onAutocomplete: function(value) {
	        			caricaSpecieGenere($self);
	        		}
	        	});
	        	
	        	$self.on("input", function () {
	        		popolaListaGeneri($(this));
	        		caricaSpecieGenere($self);
	        	});	 
	        });	
			
			
	        $('#idCentroAzNoSedeLeg').click(function() {
	            if (!$(this).is(':checked')) {
	              console.log('no checked : hide');
		          $("#datiCentroAziendale").hide();		              
	            }
	            else{
	              console.log('checked : show');
		          $("#datiCentroAziendale").show();
	            }
	        });
	        
	        // In base al check, visualizzo o meno la parte del centro aziendale al caricamento della pagina
	 /*       if($("#idCentroAzNoSedeLeg").is(':checked')){
	        	$("#datiCentroAziendale").show();
	        }
	        else{
	        	$("#datiCentroAziendale").hide();	
	        }
	*/		
	        if('${hasErrorsSitoProd}'=='true'){
				M.Collapsible.getInstance($("#collapsible")).open();
				
				$("label[for='provinciaSitoProd']").addClass("active");
				$("label[for='comuneSitoProd']").addClass("active");
			}
			
		});
		
		
		
		
		// Selezione del radio nella tabella dei centri aziendali : visualizzo la parte delle tipologie e dei siti produzione		
		$('input:radio[name="idCentroAziendaleSel"]').change(
			    function(){
			        if (this.checked) {
			        	console.log('radio selezionato');
			        	$("#tipologieProdDiv").show();
			        	$("#sitiProdDiv").show();		
			        	
			        	// Controllare se ci sono dati di Tipologie produttive e Siti produzione relativi al centro aziendale selezionato
			        	var radioValue = $("input[name='idCentroAziendaleSel']:checked").val();
			        	console.log("-- Radio value" + radioValue);
			        	// SUBMIT PER CARICARE I DATI DAL DB
			        	caricoDatiCentroAziendale(radioValue);
			        }
		 });
		
		// Se al caricamento della pagina c'è un radio selezionato, visualizzo la parte delle tipologie e dei siti produzione
		var radioValue = $("input[name='idCentroAziendaleSel']:checked").val();
		if(radioValue){
			console.log("-- Radio value =" + radioValue);
			console.log("-- Visualizzo tipologie produttive ");
			$("#tipologieProdDiv").show();
			console.log("-- Visualizzo siti produzione ");
        	$("#sitiProdDiv").show();
		}
		
		
		// Carica i dati del centro aziendale dal db per poter caricare le Tipologie produttive ed i siti produzione
		function caricoDatiCentroAziendale(idCentroAzSel){
			console.log('caricoDatiCentroAziendale');
			console.log('idCentroAzSel ='+idCentroAzSel);
			$("#chiamata").val('caricoDatiCentroAziendale');

			console.log('Effettuo submit caricoDatiCentroAziendale');
			$('#form').submit();
			return true;
		}
		
			
			
		// Gestione combo Genere e Specie
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

			function caricaSpecieGenere($inputGenere) {
				console.log('caricaSpecieGenere');
				$selectSpecie = $inputGenere.closest('.row').find('select[name=specie]');
				
				if ($selectSpecie && $inputGenere.val()) {
					var params = {
						"denomGenere" : $inputGenere.val()	
						, "idSpecieSel" : null
					}
					
					getSelectAsincronaRef($selectSpecie,
							'<spring:url value="/ajax/getListaSpecieDenomGenere" />',
							params, getOptionSpecie, 1, reloadMultiselect);
		       }
			}
			
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
			
			function getOptionSpecie(specie) {
				return getOption(specie.idSpecie, specie.denomSpecie);
		    }
			// fine Gestione combo Genere e specie
		
		/* *** Evento alla selezione della combo Provincia:
		  - popolamento dinamico della select Comune sede legale
		  
		  - se nella combo è stato selezionato l'elemento 'Nuovo centro aziendale' :
			calcolare il codice centro aziendale : sigla provincia + progressivo (relativo alla provincia e all'azienda)
			e valorizzare il campo readomly 'codice centro aziendale'
	    */		
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
			
		function getOptionComune(comune) {
			return getOption(comune.idComune, comune.denomComune);
		}
		
		
		
		
		
		
		/* *** Evento alla selezione della combo Centri aziendali :
			
			- se viene selezionato un idCentroAz != 0 : cercare i dati del centro aziendale selezionato e precaricare i dati nella pagina
			- se viene selezionato l'idAzienda = 0 ('Nuovo centro aziendale') : sbiancare tutti i campi (l'utente vuole inserire un nuovo centro aziendale)
		*/
		$('#idCentroAziendale').change(function() {
			var idCentroAz  = $(this).val();
			console.log('idCentroAz selezionato ='+idCentroAz);
			if(idCentroAz != null && idCentroAz != ""){
				// -- CASO SELEZIONE DI UN CENTRO AZIENDALE DALLA COMBO
				if(idCentroAz != '0'){
				  console.log('-- CASO SELEZIONE DI UN CENTRO AZIENDALE DALLA COMBO');
				  // sbianco i campi nel caso avessero dati
				  $(".campiAz").val("");				  
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
							   $(".campiAz:not(.select-wrapper)").next().addClass("active");	
							}
							else {							
						      console.log('centro aziendale non trovato su car_t_centro_aziendale');
						      console.log('Sbianco i campi');
							  $(".campiAz").val("");	
							}  
						}	
				  });				 					  				 
				}
				// -- CASO SELEZIONE DI 'NUOVO CENTRO AZIENDALE' DALLA COMBO
				else{
				  console.log('-- CASO SELEZIONE DI NUOVO CENTRO AZIENDALE DALLA COMBO');
				  $(".campiAz").val("");	  
				  $("select").formSelect();				  				  	 				  
				}
			}
		});
			
			
		// --- CLICK SUL PULANTE AGGIUNGI TIPOLOGIA PRODUTTIVA
        $('#aggiungiTipologiaProd').click(function(e) {
        	console.log('AGGIUNGI TIPOLOGIA PRODUTTIVA');
        	e.preventDefault();
        	aggiungiTipologiaProdut();        
        });
				
			
		
     // ********** Gestione AGGIUNGI TIPOLOGIA PRODUTTIVA
		function aggiungiTipologiaProdut(){
		  console.log('aggiungiTipologiaProdut');
			
		  // setto hidden controllato dal controller
		  $("#chiamata").val('aggiungiTipologiaProdut');
		  
		  
		  var idSpecieSel = '';
		  var descSpecieSel = '';
		 	 		   
		  // Tipologia produttiva selezionata
		  var e = document.getElementById("idTipologiaProd");
		  var idTipologProdSel = e.options[e.selectedIndex].value;
		  console.log('idTipologia selezionata ='+idTipologProdSel);
		  var descTipologProdSel = e.options[e.selectedIndex].text;
		  console.log('tipologia selezionata ='+descTipologProdSel);
		  
		  // Controllare se e' stata selezionata almeno una tipologia produttiva
		  if(idTipologProdSel == null || idTipologProdSel ==''){		  
			  swal({
        			title : 'Selezionare una tipologia produttiva',
        			type : 'warning',
		          });  
			  return;
		  }

		  var tipologieProduttive=document.getElementById("listaTipologieProduttive").value.split(",");
		  var isGenereObbligatorio=false; 
		  for(let i=0; i<tipologieProduttive.length; i++){
				if (tipologieProduttive[i]==idTipologProdSel){
					isGenereObbligatorio=true;
					}
			  }
		  		  
		   var genereTP = document.getElementById("genereTipologiaProduttiva").value;
			// Controllare se e' stata selezionata una tipologia produttiva con genere obbligatorio  
				if ((genereTP == null || genereTP == '') && isGenereObbligatorio) {
					swal({
						title : 'Selezionare un genere',
						type : 'warning',
					});
					return;
				}
				/*var specieTP = document.getElementById("specieTipologiaProduttiva").value;
				var nSpecie = document.getElementById("specieTipologiaProduttiva").length;

				// Controllare se e' stata selezionata una tipologia produttiva con specie obbligatoria 
				if ((specieTP == null || specieTP == '') && nSpecie > 1 && isGenereObbligatorio) {
					swal({
						title : 'Selezionare una specie',
						type : 'warning',
					});
					return;
				}*/

				console.log('Effettuo submit tipologia');
				$('#form').submit();
				return true;
			}

			// Elimina elemento della tabella delle Tipologie produttive
			function eliminaTipologiaProdut(link) {
				console.log('eliminaTipologiaProdut');

				var tipologProd;
				var genere;
				var specie;

				$riga = $(link).closest('tr');
				tipologProd = $riga.find('td:nth-of-type(2)').text();
				genere = $riga.find('td:nth-of-type(4)').text();
				specie = $riga.find('td:nth-of-type(5)').text();

				var messaggio;
				// sono stati indicati sia il genere che la specie
				if ((genere !== undefined && genere != null && genere != 'null' && genere != '')) {
					messaggio = 'Si desidera eliminare la tipologia produttiva : <b>'
							+ tipologProd
							+ '</b>, con genere : <b>'
							+ genere
							+ '</b> e specie : <b>' + specie + '</b>?';
				} else {
					// NON sono stati indicati genere e specie
					if (genere == undefined || genere == null
							|| genere == 'null' || genere == '') {
						messaggio = 'Si desidera eliminare la tipologia produttiva : <b>'
								+ tipologProd + '</b>?';
					}
					// e' stato indicato solo il genere
					else {
						messaggio = 'Si desidera eliminare la tipologia produttiva : <b>'
								+ tipologProd
								+ '</b>, con genere : <b>'
								+ genere + '</b>?';
					}
				}

				swal({
					title : 'Attenzione!',
					html : messaggio,
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

			// --- Gestione Siti di produzione        	
			// --- CLICK SUL PULSANTE AGGIUNGI SITO PRODUZIONE
			$('#aggiungiSitoProduzione').click(function(e) {
				console.log('AGGIUNGI SITO PRODUZIONE');
				e.preventDefault();
				aggiungiSitoProduzione();
			});

			function aggiungiSitoProduzione() {
				console.log('aggiungiSitoProduzione');

				// setto hidden controllato dal controller
				$("#chiamata").val('aggiungiSitoProduzione');

				// Sito produzione
				var descSitoProduzione = $("#descSitoProduzione").val();
				console.log('descSitoProduzione =' + descSitoProduzione);

				var ubicazione = $("#ubicazione").val();
				console.log('ubicazione =' + ubicazione);

				var provinciaSitoProd = $("#provinciaSitoProd").val();
				console.log('provinciaSitoProd =' + provinciaSitoProd);

				var comuneSitoProd = $("#comuneSitoProd").val();
				console.log('comuneSitoProd =' + comuneSitoProd);

				var foglio = $("#foglio").val();
				console.log('foglio =' + foglio);

				var mappale = $("#mappale").val();
				console.log('mappale =' + mappale);

				var superficie = $("#superficie").val();
				console.log('superficie =' + superficie);

				if ((provinciaSitoProd == null || provinciaSitoProd == '')
						|| (comuneSitoProd == null || comuneSitoProd == '')
						|| (foglio == null || foglio == '')
						|| (mappale == null || mappale == '')
						|| (superficie == null || superficie == '')) {
					swal({
						title : 'Compilare tutti i dati del Sito di produzione',
						type : 'warning',
					});
					return;
				} else {
					console.log('Effettuo submit sito produzione');
					$('#form').submit();
					return true;
				}
			}

			// Alla selezione della Provincia nella modale Sito Produzione
			$('#provinciaSitoProd')
					.change(
							function() {
								// ------ Popolamento combo comune
								console
										.log('Popolamento combo comune modale sito produzione');
								var params = {
									"idProvincia" : $(this).val()
								}

								getSelectAsincrona(
										'comuneSitoProd',
										'<spring:url value="/ajax/getComuniProvincia" />',
										params, getOptionComune, 1);
							});

			// Evento ELIMINA sulla tabella dei Siti produzione        
			// Evento ELIMINA sulla tabella dei Siti produzione        
			function eliminaSitoProd(link) {
				console.log('eliminaSitoProd');

				var sitoProduz;
				var ubicazione;

				$riga = $(link).closest('tr');
				sitoProduz = $riga.find('td:nth-of-type(2)').text();
				ubicazione = $riga.find('td:nth-of-type(3)').text();

				var messaggio;
				if (sitoProduz != '' && ubicazione != '') {
					messaggio = 'Si desidera eliminare il sito produzione : <b>'
							+ sitoProduz
							+ '</b>, con indirizzo : <b>'
							+ ubicazione + '</b>?'
				} else {
					if (sitoProduz == '' && ubicazione == '') {
						messaggio = 'Si desidera eliminare il sito produzione?';
					} else {
						if (sitoProduz != '') {
							messaggio = 'Si desidera eliminare il sito produzione : <b>'
									+ sitoProduz + '</b>?';
						} else if (ubicazione != '') {
							messaggio = 'Si desidera eliminare il sito produzione con indirizzo : <b>'
									+ ubicazione + '</b>?';
						}
					}
				}
				swal({
					title : 'Attenzione!',
					html : messaggio,
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

			// ----- Gestione centri aziendali

			// --- CLICK SUL PULANTE AGGIUNGI CENTRO AZIENDALE
			$('#aggiungiCentroAziendale').click(function(e) {
				console.log('AGGIUNGI TIPOLOGIA PRODUTTIVA');
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
		</script> 
</content>

</body>
</html>