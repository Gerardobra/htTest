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
</head>
<body>
	<spring:url value="/autorizzazioni/comunicazioni/passaporto/nuova" var="formAction" />
	<spring:url value="/autorizzazioni/comunicazioni/passaporto/nuova" var="autorizzAction" />
	<form:form action="${formAction}" method="post" modelAttribute="nuovaDomandaForm" accept-charset="utf-8">
		<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>NUOVA DOMANDA : ${nuovaDomandaForm.descTipoDomanda}</b>
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
   	 				   <spring:eval var="idTipoDomandaRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP" />
					   <spring:eval var="idTipoDomandaVariazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP" />
        			   <spring:eval var="idTipoDomandaCancellazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP" />
   	 				   <spring:eval var="idTipoDomandaPassaporto" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO" />
      					<ul class="tabs">
      						<li class="tab" >
      							<a href="<spring:url value="/autorizzazioni/comunicazioni/anagrafica/nuova" />" target="_self">Anagrafica</a>
      						</li>
        					<li class="tab" >
        						<a href="<spring:url value="/autorizzazioni/comunicazioni/azienda/nuova" />" target="_self">Dati operatore</a>
        					</li>
        					<!-- in caso di Cancellazione non mostro i seguenti tab -->
        					<c:if test="${idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
	        					<c:if test="${ idTipoDomandaVariazioneRUOP == nuovaDomandaForm.idTipoComunicazione || idTipoDomandaRUOP == nuovaDomandaForm.idTipoComunicazione}">
								  <c:forEach var="ceAz" items="${nuovaDomandaForm.listaCentriAz}">
			        				  <li class="tab" >
			        					<a href="<spring:url value="/autorizzazioni/comunicazioni/centroaziendale/nuova/${ceAz.idCentroAziendale}" />" target="_self">${ceAz.codCentroAziendale}</a>
			        				  </li>
		        				  </c:forEach>
		        				</c:if>					
								<c:if test="${nuovaDomandaForm.tabImport}">
		        					<li class="tab" >
		        						<a href="<spring:url value="/autorizzazioni/comunicazioni/impAuto/nuova" />" target="_self">Import</a>
		        					</li>
	        					</c:if>
	        					<c:if test="${nuovaDomandaForm.tabExport}">
		        					<li class="tab" >
		        						<a href="<spring:url value="/autorizzazioni/comunicazioni/expAuto/nuova" />" target="_self">Export</a>
		        					</li>	        					
	        					</c:if>	        					
								<li class="tab" >
			        			  <a class="active"><b>Passaporto</b></a>
			        			</li>		        				
		        			</c:if>
			        	   	<li class="tab" >
	        						<a href="<spring:url value="/autorizzazioni/comunicazioni/allegati/nuova" />" target="_self">Allegati</a>
	        				</li>	        					        					        					        						
      					</ul>
    				</div>
  				</div>
  				<jsp:include page="../includes/boxheader.jsp"></jsp:include>
				<div class="row">
					<div class="card-panel">
						<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Richiesta di autorizzazione al rilascio del Passaporto
								delle piante [Reg. (UE) 2016/2031, art. 89]</em>
						</p>
						<div class="row"></div>
						<div class="row" id="bloccoVociRadio">
							<c:forEach var="voceRadio"
								items="${nuovaDomandaForm.vociRadioTipoRichiesta}">
								<div class=" col l8">
									<label> <form:radiobutton
											path="idVoceRadioTipoRichiesta" required="required"
											value="${voceRadio.idVoce}" /> <span>${voceRadio.descEstesa}</span>
									</label>
								</div>
							</c:forEach>
							<form:errors path="idVoceRadioTipoRichiesta" cssClass="error" />
						</div>
						<div class="row"></div>
						<div class="row">
							<p style="font-size:15px;margin-bottom:5px;">
								  <em>Richiesta relativa al centro aziendale: </em>
							</p> 
							<div class="input-field col s6 m4 l6">
									<form:select id="idCentroAziendalePassaporto" path="idCentroAziendalePassaporto" name ="idCentroAziendalePassaporto"
										cssClass="validate" required="required">
										<form:option value="" label="Selezionare" />
										<form:options items="${centriAziendaliList}" itemValue="idCentroAziendale"
											itemLabel="nomeElenco" />
									</form:select>
									<form:errors path="idCentroAziendalePassaporto" cssClass="error" />
									<label for="idCentroAziendalePassaporto">Centri aziendali *</label>
							</div>
						</div>
					</div>
				
				  <!-- RESPONSABILE -->
				  <div class="card-panel">				    
					<div class="row">
						<div class=" col l8">
							<label> 
							  <form:checkbox id="checkRespFito" value="1" path="checkRespFito" /> 
							  <span>Il Responsabile per la comunicazione con il Servizio Fitosanitario regionale è diverso dal Rappresentante legale</span>
							</label>
						</div>
					</div>
					<div id="divDatiRespFito" class="row" style="display:none">
						<p class="center-align"
								style="font-size: 20px; margin-bottom: 5px;">
								<em>Responsabile per la comunicazione con il Servizio Fitosanitario Regionale competente in merito alle disposizioni del 
								Regolamento (UE) 2016/2031</em>
						</p>
							<div class="input-field col s6 m4 l4">
								<form:input type="text" id="cognomeRespPass"
									path="cognomeRespPass" cssClass="validate"
									maxlength="50" />
								<form:errors path="cognomeRespPass" cssClass="error" />
								<label for="cognomeRespPass">Cognome *</label>
							</div>
							<div class="input-field col s6 m4 l4">
								<form:input type="text" id="nomeRespPass"
									path="nomeRespPass" cssClass="validate"
									maxlength="50" />
								<form:errors path="nomeRespPass" cssClass="error" />
								<label for="nomeRespPass">Nome *</label>
							</div>						
							<div class="input-field col s6 m4 l4">
								<form:input type="text" id="codFiscaleRespPass"
									path="codFiscaleRespPass" cssClass="validate"
									maxlength="16"/>
								<form:errors path="codFiscaleRespPass" cssClass="error" />
								<label for="codFiscaleRespPass">Codice fiscale *</label>
							</div>
							<div class="input-field col s6 m4 l4">
							<form:input type="text" id="numTelefonoRespPass"
								path="numTelefonoRespPass" cssClass="validate"
								maxlength="20" />
							<form:errors path="numTelefono" cssClass="error" />
							<label for="numTelefonoRespPass">Numero di telefono (Es. 0245673467)</label>
						</div>
						<div class="input-field col s6 m4 l4">
							<form:input type="text" id="cellulareRespPass"
								path="cellulareRespPass" cssClass="validate"
								maxlength="30" />
							<form:errors path="cellulareRespPass" cssClass="error" />
							<label for="cellulareRespPass">Numero di cellulare (Es. 3471234567)</label>
						</div>						
						<div class="input-field col s6 m4 l4">
								<form:input type="text" id="emailRespPass"
									path="emailRespPass" cssClass="validate"
									maxlength="100"/>
								<form:errors path="emailRespPass" cssClass="error"/>
								<label for="emailRespPass">Email *</label>
						  </div>
						  <div class="input-field col s6 m4 l4">
								<form:input type="text" id="qualificaProfRespPass"
									path="qualificaProfRespPass" cssClass="validate"
									maxlength="200"/>
								<form:errors path="qualificaProfRespPass" cssClass="error"/>
								<label for="qualificaProfRespPass">Qualifica professionale *</label>
						  </div>
					</div>
				  </div>
				
					<div class="card-panel">
						<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Informazioni relative a piante, prodotti vegetali e
								altri oggetti che devono essere movimentate con un Passaporto
								delle piante</em>
						</p>
						<div class="row">
						
						</div>
						<div class="row" id="bloccoVociRadio">
							<c:forEach var="voceRadio"
								items="${nuovaDomandaForm.vociRadioPassaporto}">
								<div class=" col l8">
									<label> <form:radiobutton path="idVoceRadio" required="required"
											value="${voceRadio.idVoce}" /> <span>${voceRadio.descEstesa}</span>
									</label>
								</div>
							</c:forEach>
							<form:errors path="idVoceRadio" cssClass="error" />
						</div>
						<div class="row">
						
						</div>
						<div class="row" id="bloccoVociCheckbox">
							<c:forEach var="voceCheckbox"
								items="${nuovaDomandaForm.vociCheckboxPassaporto}">
								<div class="row">
									<div class=" col l12">
										<label> <form:checkbox id="idVoceCheck" name="idVoceCheck"
												value="${voceCheckbox.idVoce}" path="idVoceCheck" /> <span>${voceCheckbox.descEstesa}</span>
										</label>
									</div>
								</div>
							</c:forEach>
							<form:errors path="idVoceCheck" cssClass="error" />
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
				</div>
							
				<div class="card-panel">				
					<!-- Tipologie produttive -->
					<p class="center-align" style="font-size:20px;margin-bottom:5px;">
					  <em>Selezionare le categorie di interesse: </em>
					</p>	
					<div class="row">
	  				
  					</div>									
				    <div class="row">
				       <div class="row col l12" id="divTipologiaProdutt">
							<div class="input-field col s6 m4 l12">
								<form:select id="idTipologiaProd" name="tipologiaProduttiva" path="idTipologiaProd" cssClass="campiPass">
									<form:option value="" label="Selezionare" />
									<form:options items="${listaTipologieProd}" itemValue="idVoce"
										itemLabel="descEstesa" />
								</form:select>
								<form:errors path="idTipologiaProd" cssClass="error" />
								<label for="labelidTipologiaProd">Tipologia produttiva *</label>
							</div>
						</div>
						<div class="row col l12" id="divSpecieTpProd">
					            <div id="divGenereTpProd" class="input-field col s12 m5">
					              <input type="text" name="genere" placeholder="Scrivere generi più rappresentativi"  
					                class="validate autocomplete" autocomplete="off" />
					              <label class="active">Genere *</label>
					            </div>            
					            <div class="input-field col s12 m5">
					              <select name="specie" multiple="multiple"
					                class="validate multiselect">
					                <option value="" disabled="">SELEZIONARE</option>
					              </select>
					              <label>Specie *</label>
					            </div>					           
					  </div>							
				    </div>				    				    
 	            	<div class="input-field col s12 m2 l12">
		              <button id="aggiungiSpecie" name="aggiungiSpecie" type="submit" 
		              	class="btn green waves-effect waves-light" type="button"  style="button"> AGGIUNGI TIPOLOGIA PRODUTTIVA </button>
	            	</div>
	            	<div class="row">
				        <table id="tabellaSpecie" class="data-table striped display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">
				          <thead>
				            <tr>
				              <th></th>
				              <th>Tipologia produttiva</th>
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
										<td nowrap style="white-spaces: nowrap">
											<sec:authorize access="hasRole('WRITE') ">		
											<c:choose>	
												<c:when test="${specie.idSpecie != null}">																									
													<a href="${autorizzAction}/eliminaTipologiaProd/idTip/${tipologia.idTipologia}/idSpecie/${specie.idSpecie}" 
												    	title="Elimina" 
												    	class="btn btn-floating btn-floating-medium deep-orange accent-2"
												    	onclick="return eliminaTipologiaProd(this)">
														<i class="material-icons">delete</i>
												  	</a>	
												  </c:when>	
												  <c:otherwise>
												  	<a href="${autorizzAction}/eliminaTipologiaProd/idTip/${tipologia.idTipologia}" 
												    	title="Elimina" 
												    	class="btn btn-floating btn-floating-medium deep-orange accent-2"
												    	onclick="return eliminaTipologiaProd(this)">
														<i class="material-icons">delete</i>
												  	</a>
												  </c:otherwise>
											</c:choose>																																									
											</sec:authorize>
										</td>																										
										<td>${tipologia.denomTipologiaEstesa}</td>									
										<td>${specie.denomGenere}</td>
										<td>${specie.denomSpecie}</td>									
								      </tr>
								     </c:forEach> 
								     </c:when>	
						     			<c:otherwise>
						    				<tr>
											  	<td nowrap style="white-spaces: nowrap">
													<sec:authorize access="hasRole('WRITE') ">	
														<a href="${autorizzAction}/eliminaTipologiaProd/idTip/${tipologia.idTipologia}" 
														    title="Elimina" 
														    class="btn btn-floating btn-floating-medium deep-orange accent-2"
														    onclick="return eliminaTipologiaProd(this)">
																<i class="material-icons">delete</i>
														  </a>
													  </sec:authorize>
												</td>
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
				<!-- FINE Tipologie produttive -->  
				<div class="card-panel">	
					<!-- Zone protette-->
					<p class="center-align" style="font-size:20px;margin-bottom:5px;">
					  <em>Passaporto delle piante Zona Protette </em>
					</p>										
				    <div class="row">
				    
				    </div>	
				    <div class="row">
				       <div class="row col l12" id="divZonaProtetta">
							<div class="input-field col s6 m4 l12">
								<form:select id="idZonaProtetta" name="zonaProtetta" path="idZonaProtetta" >
									<form:option value="" label="Selezionare" />
									<form:options items="${listaZoneProtette}" itemValue="idGruppoZonaProtetta"
										itemLabel="descBreve" />
								</form:select>
								<form:errors path="idZonaProtetta" cssClass="error" />
								<label for="labelidZonaProtetta">Zona protetta *</label>
							</div>
						</div>
						<div class="row col l12" id="divSpecieZP">
					            <div id="divGenereZP" class="input-field col s12 m5">
					             <select id='idGenereZP' name="genereZP">
					                <option value="" disabled="">Selezionare</option>
					              </select>
					              <label>Genere *</label>
					            </div>            
					            <div id="divSpecieZP" class="input-field col s12 m5">
					              <select name="specieZP" multiple="multiple"
					                class="validate multiselect">
					                <option value="" disabled="">Selezionare</option>
					              </select>
					              <label>Specie *</label>
					            </div>					           
					  </div>											
  	            	<div class="input-field col s12 m2 l12">
		              <button id="aggiungiZonaProtetta" name="aggiungiZonaProtetta" type="submit" 
		              	class="btn green waves-effect waves-light" type="button"  style="button"> AGGIUNGI ZONA PROTETTA - SPECIE </button>
	            	</div>
	            	<div class="row">
				        <table id="tabellaZoneProtette" class="data-table striped display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">
				          <thead>
				            <tr>
				              <th></th>
				              <th>Zona Protetta</th>
				              <th>Genere</th>
				              <th>Specie</th>				              
				            </tr>
				          </thead>
				          <tbody id="bodyTabellaZoneProtette">
				            <c:forEach var="zonaProtetta" items="${zoneProtetteDb}">
				              <c:forEach var="specie" items="${zonaProtetta.specieList}">
								   <tr>
									<td nowrap style="white-spaces: nowrap">
										<sec:authorize access="hasRole('WRITE') ">																														
											  <a href="${autorizzAction}/eliminaZonaProtetta/idZonaProtetta/${zonaProtetta.idGruppoZonaProtetta}/idSpecie/${specie.idSpecie}" 
											    title="Elimina" 
											    class="btn btn-floating btn-floating-medium deep-orange accent-2"
											    onclick="return eliminaTipologiaProd(this)">
													<i class="material-icons">delete</i>
											  </a>																															
										</sec:authorize>									
									</td>																										
									<td>${zonaProtetta.denomZonaProtetta}</td>									
									<td>${specie.denomGenere}</td>
									<td>${specie.denomSpecie}</td>									
							      </tr>
							     </c:forEach> 
							 </c:forEach>
				          </tbody>				          				          				          
				        </table>                      
			      	</div>	
	            	<!-- FINE Zone protette -->
	            </div>					
			</div>						
		   </div>
		</div>		
		<div class="card-panel">
					<p class="center-align"
						style="font-size: 20px; margin-bottom: 5px;">
						<em>L’operatore professionale dichiara </em>
					</p>
					<div class="row"></div>
					<div class="row" id="bloccoVociCheckDichiara">
					<li>
						<p>${voceDichiaraConoscenze}</p>
						<label class="radio-inline"> 
							<form:errors path="voceDichiaraConoscenze" cssClass="error" /> 
							<form:radiobutton id="voceDichiaraConoscenze" cssClass="with-gap" path="voceDichiaraConoscenze"
									value="S" /> <span>Si</span>
						</label> 
						<label class="radio-inline"> 
							<form:radiobutton id="voceDichiaraConoscenze" cssClass="with-gap" path="voceDichiaraConoscenze"
									value="N" /> <span>No</span>
						</label>
					</li>
					<li>
						<p>${voceDichiaraDisporreSistemi}</p>
						<label class="radio-inline"> 
							<form:errors path="voceDichiaraDisporreSistemi" cssClass="error" /> 
							<form:radiobutton id="voceDichiaraDisporreSistemi" cssClass="with-gap" path="voceDichiaraDisporreSistemi"
									value="S" /> <span>Si</span>
						</label> 
						<label class="radio-inline"> 
							<form:radiobutton id="voceDichiaraDisporreSistemi" cssClass="with-gap" path="voceDichiaraDisporreSistemi"
									value="N" /> <span>No</span>
						</label>
					</li>
					
					</div>
					
					<p class="center-align"
						style="font-size: 20px; margin-bottom: 5px;">
						<em>L’operatore professionale si impegna a </em>
					</p>
					<div class="row"></div>
						<li>
							<p>identificare e controllare i punti critici del suo processo di produzione e i punti relativo allo spostamento di piante, prodotti vegetali e altri oggetti che risultano critici per quanto riguarda il rispetto della norma vigente </p>
						</li>
						<li>
							<p>conservare per almeno tre anni i dati riguardanti l’identificazione e il controllo dei punti critici dei propri processi di produzione </p>
						</li>
						<li>
							<p>assicurare una formazione adeguata al personale che partecipa all’esecuzione degli esami di cui all’art.87 del Reg. (UE) 2016/2031 al fine di garantire che possieda le conoscenze necessarie per effettuare gli esami</p>
						</li>
					
					
					<div class="row"></div>					
					<li>
						<p>${vocePianoRischi}</p>
						<label class="radio-inline"> 
							<form:errors path="vocePianoRischi" cssClass="error" /> 
							<form:radiobutton id="vocePianoRischi" cssClass="with-gap" path="vocePianoRischi"
									value="S" /> <span>Si</span>
						</label> 
						<label class="radio-inline"> 
							<form:radiobutton id="vocePianoRischi" cssClass="with-gap" path="vocePianoRischi"
									value="N" /> <span>No</span>
						</label>
					</li>
				</div>	
       	<div class="row">
			<a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
			<button class="btn confirm waves-effect waves-light right" type="submit" name="datiPassaporto">SALVA</button>
	   	</div>
	   
	</form:form>
		   		
	<br />
	
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
			
			
			// ripopolo la combo Genere se è stata selezionata la zona protetta
			var idZonaProtetta = $("#idZonaProtetta option:selected").val();
			console.log('idZonaProtetta sel ='+idZonaProtetta);
			if(idZonaProtetta != null && idZonaProtetta !=''){
			  popolaListaGeneriZP(idZonaProtetta);
			}  
			
			
			 // al caricamento della pagina abilito le label per mostrare gli eventuali errori
	        $("label[for='labelidTipologiaProd']").addClass("active");
	        $("label[for='labelidZonaProtetta']").addClass("active");
	        
	        
	        
	        // Se viene ricaricata la pagina con 'Tipologia produttiva' selezionata (dopo una validazione non andata a buon fine) 
	        var tipologiaSel = $("#idTipologiaProd option:selected").val();
		   	console.log("tipologia selezionata = "+ tipologiaSel);	
		   	// sostituire gli id_voce con delle costanti e recuperali da DB 
		   	if(tipologiaSel == 50 || tipologiaSel == 54  ) {
		   		console.log("Mostra tutti i generi e specie ");
		   		$('#divGenereTpProd').show();
		   		$('#divSpecieTpProd').find('input[name=genere]').prop('disabled', false);		   		
		   	} else {
		   		popolaListaSpecieByTipologiaProd(tipologiaSel);
		   		console.log("Nascondo la select Genere");
		   		$('#divGenereTpProd').hide();		   		
		   		$('#divSpecieTpProd').find('input[name=genere]').prop('disabled', 'disabled');
		   	}
	        
		});
			
			
		// Gestione combo Genere e Specie
		function popolaListaGeneri($input) {
				console.log('popolaListaGeneri');
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
		
		// Gestione combo Genere e Specie
		function popolaListaSpecieByTipologiaProd(idTipologiaProd) {
			console.log("popolaListaSpecieByTipologiaProd = "+ idTipologiaProd);	
			$selectSpecie = $('#divSpecieTpProd').find('select[name=specie]');				
			if (idTipologiaProd > 0) {
				var params = {
						  "idTipologiaProdSel" : idTipologiaProd
				};
				getSelectAsincronaRef($selectSpecie,
								'<spring:url value="/ajax/getListaSpecieByIdTipologiaProd" />',
								params, getOptionSpecie, 1, reloadMultiselect);				
			}
		}
		
	
		//mostra tutte le specie o alcune in base alla tipologia selezionata
		$('#idTipologiaProd').change(function() {
		    var tipologiaSel = $("#idTipologiaProd option:selected").val();
		   	console.log("tipologia selezionata = "+ tipologiaSel);	
		   	// sostituire gli id_voce con delle costanti e recuperali da DB 
		   	if(tipologiaSel == 50 || tipologiaSel == 54  ) {
		   		console.log("Mostra tutti i generi e specie ");
		   		$('#divGenereTpProd').show();
		   		$('#divSpecieTpProd').find('input[name=genere]').prop('disabled', false);			   		
		   	} else {
		   		popolaListaSpecieByTipologiaProd(tipologiaSel);
		   		console.log("Nascondo la select Genere");
		   		$('#divGenereTpProd').hide();
		   		$('#divSpecieTpProd').find('input[name=genere]').prop('disabled', 'disabled');
		   	}
		    
		});	
		
	    // Elimina elemento della tabella delle Tipologie produttive
        function eliminaTipologiaProd(link) {
			console.log('eliminaTipologiaProd');			
			
			var tipologProd;
			var genere;
			var specie;
			
			$riga = $(link).closest('tr');
			tipologProd = $riga.find('td:nth-of-type(2)').text();
			genere = $riga.find('td:nth-of-type(3)').text();
			specie = $riga.find('td:nth-of-type(4)').text();
			
			var messaggio;
			// sono stati indicati sia il genere che la specie
			if((genere !== undefined && genere != null && genere != 'null' && genere != '') &&
					(specie !== undefined && specie != null && specie != 'null' && specie !='')){
				messaggio =  'Si desidera eliminare la tipologia produttiva : <b>' + tipologProd 
		    	+ '</b>, con genere : <b>' + genere +'</b> e specie : <b>' + specie + '</b>?';
			}
			else{
				// NON sono stati indicati genere e specie
				if(genere == undefined || genere == null || genere == 'null' || genere == ''){
					messaggio =  'Si desidera eliminare la tipologia produttiva : <b>' + tipologProd + '</b>?';
				}
				// e' stato indicato solo il genere
				else{
					messaggio =  'Si desidera eliminare la tipologia produttiva : <b>' + tipologProd 
			    	+ '</b>, con genere : <b>' + genere +'</b>?';
				}
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
	    			    window.location.href = $(link).attr('href');
	    		  }
	    	});
			return false;
		}
			

			
			// -----------INIZIO JS ZONA PROTETTA-------//
				
			function getOptionGenere(genere) {
				return getOption(genere.idGenere, genere.denomGenere);
	    	}
			
			//  combo Genere
			// fare in modo che il primo elemento visibile nella select sia "Selezionare"
			function popolaListaGeneriZP(idGruppoZonaProtetta) {
				//$selectGenere = $('#divGenereZP').find('select[name=genereZP]');
				$selectGenere = $('#idGenereZP');
				console.log('popolaListaGeneriZP idGruppoZonaProtetta = '+idGruppoZonaProtetta);
				if (idGruppoZonaProtetta > 0) {
					var params = {
							  "idGruppoZonaProtettaSel" : idGruppoZonaProtetta
					};
					getSelectAsincronaRef($selectGenere,
									'<spring:url value="/ajax/getListaGenereByIdGruppoZP" />',
									params, getOptionGenere, 1);				
				}				
					
			}
			
			//mostra tutte le specie o alcune in base alla tipologia selezionata
			$('#idZonaProtetta').change(function() {
			    var idZonaProtetta = $("#idZonaProtetta option:selected").val();
			   	console.log("idZonaProtetta selezionata = "+ idZonaProtetta);	
			   	popolaListaGeneriZP(idZonaProtetta);
			});
			
			function getOptionSpecieZP(specie) {
				return getOption(specie.idSpecie, specie.denomSpecie);
	    	}
			
		    //  combo Specie
			function popolaListaSpecieZP(idZonaProtetta, idGenere) {
				$selectSpecie = $('#divSpecieZP').find('select[name=specieZP]');
				console.log('popolaListaSpecieZP idGenereSel = '+idGenere);
				if (idZonaProtetta > 0 && idGenere > 0) {
					var params = {
							"idGruppoZonaProtettaSel" : idZonaProtetta,					
							 "idGenereSel" : idGenere
					};
					getSelectAsincronaRef($selectSpecie,
									'<spring:url value="/ajax/getListaSpecieByIdGruppoZPIdGenere" />',
									params, getOptionSpecieZP, 1, reloadMultiselect);				
				}				
					
			}
			
			//mostra tutte le specie o alcune in base alla tipologia selezionata
			$('#idGenereZP').change(function() {
			    var idZonaProtetta = $("#idZonaProtetta option:selected").val();
			    var idGenereZP = $("#idGenereZP option:selected").val();
			   	console.log("idGenere selezionata = "+ idGenereZP);	
			   	popolaListaSpecieZP(idZonaProtetta, idGenereZP);
			});
			
			function eliminaZonaProtetta(link) {
				console.log('eliminaZonaProtetta');		
				
				var zonaProtetta;
				var genere;
				var specie;
				
				$riga = $(link).closest('tr');
				zonaProtetta = $riga.find('td:nth-of-type(2)').text();
				genere = $riga.find('td:nth-of-type(3)').text();
				specie = $riga.find('td:nth-of-type(4)').text();
				
				swal({
					title : 'Attenzione!',
				    html : 'Si desidera eliminare la zona protetta : <b>' + zonaProtetta 
				    	+ '</b>, con genere : <b>' + genere +'</b> e specie : <b>' + specie + '</b>?',
				    type : 'warning',
		   			showCancelButton : true,
					confirmButtonText : 'Sì',
					cancelButtonText : 'No',
				}).then(function (result) {
		    		  if (result.value) {
		    			    window.location.href = $(link).attr('href');
		    		  }
		    	});
				return false;
			}
			
			
			 $('#checkRespFito').click(function() {
		            if (!$(this).is(':checked')) {
		              console.log('no checked : hide');
			          $("#divDatiRespFito").hide();		              
		            }
		            else{
		              console.log('checked : show');
			          $("#divDatiRespFito").show();
		            }
		        });
		        
		        // In base al check, visualizzo o meno la parte del centro aziendale al caricamento della pagina
		        if($("#checkRespFito").is(':checked')){
		        	$("#divDatiRespFito").show();
		        }
		        else{
		        	$("#divDatiRespFito").hide();	
		        }
		</script> 
	</content>

</body>
</html>