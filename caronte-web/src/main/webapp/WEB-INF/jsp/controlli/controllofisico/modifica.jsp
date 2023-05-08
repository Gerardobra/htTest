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

.modal { 
	width: 80% !important; 
	max-height: 95% !important; 
	top: 2px !important;
	overflow: visible;
}
</style>
</head>
<body>
  <spring:url value="/controlli/controllofisico/modifica/salva" var="formAction" />
  <form:form action="${formAction}" method="post" modelAttribute="nuovoControlloForm" accept-charset="utf-8">    
	<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>NUOVO CONTROLLO FISICO</b></h3>
			</div>			
  			<div class="card-content" style="padding-top:5px;">
  				<div class="row">
   	 				<div class="col s12">
      					<ul class="tabs">        						        				    		        	
			        	    <li class="tab" >
	        					<a href="<spring:url value="/controlli/datiBase/modifica" />" target="_self">Dati generali</a>
	        				</li>	
	        				<c:if test="${nuovoControlloForm.tabDocumentale}">
	        					<li class="tab" >
	        				   		<a href="<spring:url value="/controlli/controllodocumentale/modifica" />" target="_self">Documentale</a>
        						</li>
        					</c:if>	
        					<c:if test="${nuovoControlloForm.tabIdentita}">
        						<li class="tab" >
	        						<a href="<spring:url value="/controlli/controlloidentita/modifica" />" target="_self">Identità</a>
	        					</li>
	        				</c:if>	
	        				<li class="tab" >
        						<a class="active"><b>Fisico</b></a>
	        				</li>
	        				<c:if test="${nuovoControlloForm.tabFisico && nuovoControlloForm.flControllo5x23 != null && nuovoControlloForm.flControllo5x23 =='S' }">
		        				<li class="tab" >
		        					<a href="<spring:url value="/controlli/campioni/modifica" />" target="_self">Campioni</a>
		        				</li>
	        				</c:if>
	        				<li class="tab" >
	        					<a href="<spring:url value="/controlli/allegati/modifica" />" target="_self">Allegati</a>
	        				</li>
	        				<c:if test="${false}">
		        				<li class="tab" >
		        					<a href="<spring:url value="/controlli/monitoraggio/modifica" />" target="_self">Monitoraggio cofinanziato</a>
		        				</li>
	        				</c:if>	 
	        				<c:if test="${nuovoControlloForm.misuraUfficiale != null && nuovoControlloForm.misuraUfficiale =='S' }">
	        					<li class="tab" >
        					  		<a href="<spring:url value="/controlli/misura/modifica" />" target="_self">Misura ufficiale</a>
        				    	</li>
        				    </c:if>	      				
	        				<li class="tab" >
	        					<a href="<spring:url value="/controlli/esito/modifica" />" target="_self">Esito</a>
	        				</li>
		        					
      					</ul>
    				</div>
  				</div>
        						  															
				<spring:eval var="versioneBegin"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_CONTROLLO_BEGIN" />	
				<spring:eval var="versione042021"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_CONTROLLO_04_2021" />	
				
				<div class="card-panel">	
        	     <div class="row">
		  					<p class="center-align"
									style="font-size: 20px; margin-bottom: 5px;">
									<em>Controllo Fisico</em>
							</p>
		  		 </div>		
				<div class="row">
				  <ol-start>
				  <!-- 5 -->				   
				   <li-lista><b>Controllo Fisico</b>
				      <ol style="list-style-type: decimal;">
				          <!-- 5.1 -->
				       		<li-lista><b>L’OP possiede strutture e attrezzature adeguate all’attività produttiva:</b>
				         						<div class="row">													
													  <label class="radio-inline">
													  			<form:errors path="flControllo5x1" cssClass="error"/>
																	<form:radiobutton id="flControllo5x1"
																	cssClass="with-gap" path="flControllo5x1"
																	value="S"/>
																<span>Si</span>
													  </label>																																							  												
													  <label class="radio-inline">
															<form:radiobutton id="flControllo5x1"
																cssClass="with-gap" path="flControllo5x1"
																value="N"/>
															<span>No</span>
														</label>													
													  <label class="radio-inline">
															<form:radiobutton id="flControllo5x1"
																cssClass="with-gap" path="flControllo5x1"
																value="NA"/>
															<span>NA</span>
													  </label>	
													
												</div>
												
                            <ol style="list-style-type: decimal;">										
									  <!-- 5.1.1-->	
					        			<li-lista>Elenco delle strutture e delle attrezzature presenti:
					                       <div class="row">														
													<c:forEach items="${listaStrutturaAttrezzatura}" var="strutt" varStatus="index">
														<div class="input-field col s4 m4 l4">
															<c:if test="${index.index ==0}">
																<form:errors path="idsStrutturaAttrezzatura" cssClass="error" />
															</c:if>
															<label> <form:checkbox path="idsStrutturaAttrezzatura"
																	id="idsStrutturaAttrezzatura" name="idsStrutturaAttrezzatura"
																	value="${strutt.idStrutturaAttrezzatura}" /> <span>${strutt.descEstesa }</span>
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
														  <label class="radio-inline">
														  	<form:errors path="flControllo5x2" cssClass="error"/>
																	<form:radiobutton id="flControllo5x2"
																		cssClass="with-gap" path="flControllo5x2"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x2"
																	cssClass="with-gap" path="flControllo5x2"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x2"
																	cssClass="with-gap" path="flControllo5x2"
																	value="NA"/>
																<span>NA</span>
														  </label>
                     </div> 
               
				  </li-lista>				  										
                  <!-- 5.3 -->				   						  
				  <li-lista><b>Tecniche di produzione</b>
				     <div class="row">														
							<c:forEach items="${listaMetodiProduzione}" var="metodo" varStatus="index">
								<div class="input-field col s4 m4 l4">
								<c:if test="${index.index ==0}">
									<form:errors path="idsMetodiProduzione" cssClass="error" />
									</c:if>
									<label> <form:checkbox path="idsMetodiProduzione"
											id="idsMetodiProduzione" name="idsMetodiProduzione"
											value="${metodo.idMetodoProduzione}" /> <span>${metodo.descEstesa }</span>
									</label>
								</div>	
							</c:forEach>						  
					</div>

				  </li-lista>	
				  <!-- 5.4 -->	
				  <li-lista><b>L’OP utilizza un substrato di coltivazione è idoneo al fine di garantire l’assenza di ON</b>
				                   <div class="row">													
													  <label class="radio-inline">
															    <form:errors path="flControllo5x4" cssClass="error"/>
																<form:radiobutton id="flControllo5x4"
																	cssClass="with-gap" path="flControllo5x4"
																	value="S"/>
																<span>Si</span>
													  </label>																																							  												
													  <label class="radio-inline">
															<form:radiobutton id="flControllo5x4"
																cssClass="with-gap" path="flControllo5x4"
																value="N"/>
															<span>No</span>
														</label>	
														<label class="radio-inline">
															<form:radiobutton id="flControllo5x4"
																cssClass="with-gap" path="flControllo5x4"
																value="NA"/>
															<span>NA</span>
														</label>												
												 									
									</div>	
								
                  </li-lista>								   
                  <!-- 5.5 -->								   													  
				  <li-lista><b>Nel caso di coltivazione in contenitore, i contenitori sono adeguatamente isolati dal suolo </b>
				       <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x5" cssClass="error"/>
																	<form:radiobutton id="flControllo5x5"
																		cssClass="with-gap" path="flControllo5x5"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x5"
																	cssClass="with-gap" path="flControllo5x5"
																	value="N"/>
																<span>No</span>
														  </label>
                        </div>														  											
						
				  </li-lista>	
				  <!-- 5.6 -->
				  <li-lista><b>L’OP mantiene la separazione tra i diversi lotti di produzione e le produzioni sono identificate con apposite etichette</b>
				    <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x6" cssClass="error"/>
																	<form:radiobutton id="flControllo5x6"
																		cssClass="with-gap" path="flControllo5x6"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x6"
																	cssClass="with-gap" path="flControllo5x6"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x6"
																	cssClass="with-gap" path="flControllo5x6"
																	value="NA"/>
																<span>NA</span>
														  </label>
					</div>			
														  																																																							
				  </li-lista>
				  <!-- 5.7 -->
				  <li-lista><b>L’OP utilizza materiale di moltiplicazione adeguatamente tracciato e nel caso di autoproduzione mette in atto tutte le procedure per garantire la sanità e la qualità del materiale prodotto</b>
				       <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x7" cssClass="error"/>
																	<form:radiobutton id="flControllo5x7"
																		cssClass="with-gap" path="flControllo5x7"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x7"
																	cssClass="with-gap" path="flControllo5x7"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x7"
																	cssClass="with-gap" path="flControllo5x7"
																	value="NA"/>
																<span>NA</span>
														  </label>
					   </div>														  																																				
																													   
				  </li-lista>
				  <!-- 5.8 -->
				  <li-lista><b>L’OP ha attuato una corretta gestione per lo stoccaggio e l’eliminazione dei residui vegetali e del materiale non idoneo alla commercializzazione</b>
				     <div class="row">										
				     				
														  <label class="radio-inline">
														  			<form:errors path="flControllo5x8" cssClass="error"/>
																	<form:radiobutton id="flControllo5x8"
																		cssClass="with-gap" path="flControllo5x8"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x8"
																	cssClass="with-gap" path="flControllo5x8"
																	value="N"/>
																<span>No</span>
														  </label>	
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x8"
																	cssClass="with-gap" path="flControllo5x8"
																	value="NA"/>
																<span>NA</span>
														  </label>																																	
					</div>
				
				  </li-lista>
				  <!-- 5.9 -->
				  <li-lista><b>Il luogo di produzione e le immediate vicinanze sono adeguatamente ripulite dalle le erbe infestanti al fine di ridurre i rischi fitosanitari:	</b>
				  	<div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x9" cssClass="error"/>
																	<form:radiobutton id="flControllo5x9"
																		cssClass="with-gap" path="flControllo5x9"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x9"
																	cssClass="with-gap" path="flControllo5x9"
																	value="N"/>
																<span>No</span>
														  </label>	
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x9"
																	cssClass="with-gap" path="flControllo5x9"
																	value="NA"/>
																<span>NA</span>
														  </label>																																	
					</div>
				
				  </li-lista>
				  <!-- 5.10 -->
				  <li-lista><b>L’acqua utilizzata per l’irrigazione costituisce un rischio fitosanitario: </b>
				  							
  												  <div class="row">													
														  <label class="radio-inline">
																<form:errors path="flControllo5x10" cssClass="error"/>
																<form:radiobutton id="flControllo5x10"
																	cssClass="with-gap" path="flControllo5x10"
																	value="S"/>
																<span>Si</span>
													  </label>																																							  												
													  <label class="radio-inline">
															<form:radiobutton id="flControllo5x10"
																cssClass="with-gap" path="flControllo5x10"
																value="N"/>
															<span>No</span>
														</label>
														<label class="radio-inline">
															<form:radiobutton id="flControllo5x10"
																cssClass="with-gap" path="flControllo5x10"
																value="NA"/>
															<span>NA</span>
														</label>													
												 									
													</div>
										<div class="row">
											<b>Approvvigionamento dell’acqua d’irrigazione:</b>
										</div>		
										  <div class="row">					
											<c:forEach items="${listaTipoIrrigazione}" var="tipi" varStatus="index">
												<div class="input-field col s4 m4 l4">
												<c:if test="${index.index ==0}">
													<form:errors path="idsTipiIrrigazione" cssClass="error" />
												</c:if>
													<label> <form:checkbox path="idsTipiIrrigazione"
															id="idsTipiIrrigazione" name="idsTipiIrrigazione"
															value="${tipi.idTipoIrrigazione}" /> <span>${tipi.descEstesa }</span>
													</label>
												</div>	
											</c:forEach>	
											</div>	
													

				  </li-lista>
				  <!-- 5.11 -->
				  <li-lista><b>Il luogo di produzione ricade all’interno di aree delimitate ufficialmente dal Servizio a seguito di accertamento di organismi nocivi da quarantena: </b>
				  	<div class="row">														
														  <label class="radio-inline">
														  			<form:errors path="flControllo5x11" cssClass="error"/>
																	<form:radiobutton id="flControllo5x11"
																		cssClass="with-gap" path="flControllo5x11"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x11"
																	cssClass="with-gap" path="flControllo5x11"
																	value="N"/>
																<span>No</span>
														  </label>
																																		
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
						<div class="row">
											 	<div class="input-field col s6 m5 l12">
									                <form:textarea id="noteControllo5x11" path="noteControllo5x11"
									                  cssClass="materialize-textarea validate" rows="2"
									                  data-length="300" maxlength="300" />
									                <form:errors path="noteControllo5x11" cssClass="error" />
									                <label for="noteControllo5x11"><b>Risposta positiva:</b> indicare l’ON </label>											
												</div>		
					    </div>
				    </c:if>
				    <c:if test="${nuovoControlloForm.idVersioneControllo >= versione042021}">
						<div class="input-field col s6 m5 l12">
											<form:select id="orgNoc" name="orgNoc" multiple="multiple"
												path="orgNoc" class="validate multiselect">
												<option value="" disabled="">Selezionare</option>											
												<form:options items="${listaOrgNoc}"
													itemValue="idOrgNocivo" itemLabel="descBreve" />
											</form:select>
											<form:errors path="orgNoc" cssClass="error" />
											<label for="labelorgNoc" class="active"><b>Risposta positiva:</b> indicare l’ON</label>
						</div>
					</c:if>
				  </li-lista>
				  <!-- 5.12 -->
				  <li-lista><b>L’OP è informato sull’obbligo di comunicare al Servizio le variazioni dei dati dichiarati in sede di 
				  presentazione della domanda di registrazione al RUOP e la cessazione dell’attività: </b>
				  		<div class="row">														
														  <label class="radio-inline">
														  <form:errors path="flControllo5x12" cssClass="error"/>
																	<form:radiobutton id="flControllo5x12"
																		cssClass="with-gap" path="flControllo5x12"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x12"
																	cssClass="with-gap" path="flControllo5x12"
																	value="N"/>
																<span>No</span>
														  </label>
																																			
					</div>
			 							
				  </li-lista>
				  <!-- 5.13 -->
				  <li-lista><b>L’OP è informato sull’obbligo derivante dalla presenza o sospetta presenza di un ON sulle piante in produzione di cui all’art. 14 del reg. 2016/2031</b>
				     <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x13" cssClass="error"/>
																	<form:radiobutton id="flControllo5x13"
																		cssClass="with-gap" path="flControllo5x13"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x13"
																	cssClass="with-gap" path="flControllo5x13"
																	value="N"/>
																<span>No</span>
														  </label>
																																			
					</div>
										
				  </li-lista>	
				  <!-- 5.14 -->
				  <li-lista><b>L’OP è a conoscenza delle procedure sulla tracciabilità previste dall’art 69 del reg. 2016/2031</b>
				  	<div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x14" cssClass="error"/>
																	<form:radiobutton id="flControllo5x14"
																		cssClass="with-gap" path="flControllo5x14"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x14"
																	cssClass="with-gap" path="flControllo5x14"
																	value="N"/>
																<span>No</span>
														  </label>																																		
					</div>
					
				  </li-lista>
				  <!-- 5.15 -->
				  <li-lista><b>L’OP ha istituito procedure sulla tracciabilità atte a consentire l’identificazione dei vegetali in applicazione dall’art 70 del reg. 2016/2031</b>
				      <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x15" cssClass="error"/>
																	<form:radiobutton id="flControllo5x15"
																		cssClass="with-gap" path="flControllo5x15"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x15"
																	cssClass="with-gap" path="flControllo5x15"
																	value="N"/>
																<span>No</span>
														  </label>	
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x15"
																	cssClass="with-gap" path="flControllo5x15"
																	value="NA"/>
																<span>NA</span>
														  </label>																																	
					</div>
				
				  </li-lista>
				  <!-- 5.16 -->
				  <li-lista><b>L’OP possiede le conoscenze necessarie per poter rilasciare i passaporti in attuazione dell’art 89 del reg. 2016/2031	</b>
				      <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x16" cssClass="error"/>
																	<form:radiobutton id="flControllo5x16"
																		cssClass="with-gap" path="flControllo5x16"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x16"
																	cssClass="with-gap" path="flControllo5x16"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x16"
																	cssClass="with-gap" path="flControllo5x16"
																	value="NA"/>
																<span>NA</span>
														  </label>
                     </div> 
                   
				  </li-lista>	
				   <!-- 5.17 -->
				  <li-lista><b>L’OP provvede a identificare i punti critici del suo processo di produzione prime di rilasciare il passaporto delle piante in applicazione dall’art 90 del reg. 2016/2031	</b>
				      <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x17" cssClass="error"/>
																	<form:radiobutton id="flControllo5x17"
																		cssClass="with-gap" path="flControllo5x17"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x17"
																	cssClass="with-gap" path="flControllo5x17"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x17"
																	cssClass="with-gap" path="flControllo5x17"
																	value="NA"/>
																<span>NA</span>
														  </label>
                     </div> 
                
				  </li-lista>	
				   <!-- 5.18 -->
				  <li-lista><b>L’OP ha istituito un piano di gestione dei rischi connessi agli organismi nocivi approvato dal Servizio in applicazione dall’art 91 del reg. 2016/2031</b>
				      <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x18" cssClass="error"/>
																	<form:radiobutton id="flControllo5x18"
																		cssClass="with-gap" path="flControllo5x18"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x18"
																	cssClass="with-gap" path="flControllo5x18"
																	value="N"/>
																<span>No</span>
														  </label>
                     </div> 
                    
				  </li-lista>	
				  
				     <!-- 5.19 -->
				  <li-lista><b>La ditta collabora con il Servizio e consente il corretto svolgimento delle ispezioni</b>
				      <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x19" cssClass="error"/>
																	<form:radiobutton id="flControllo5x19"
																		cssClass="with-gap" path="flControllo5x19"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x19"
																	cssClass="with-gap" path="flControllo5x19"
																	value="N"/>
																<span>No</span>
														  </label>
                     </div> 
                     	
				  </li-lista>	
				  
				  <c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
				<!-- 5.20 -->
				<c:if test="${abilitaSementi}">
				  <li-lista><b>La ditta sementiera è una figura tecnica che possiede adeguate conoscenze professionali sulle tecniche di produzione/selezione meccanica, sulla normativa sementiera e fitosanitaria e riguardanti le categorie delle sementi per le quali richiede l’autorizzazione	</b>
				      <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x20" cssClass="error"/>
																	<form:radiobutton id="flControllo5x20"
																		cssClass="with-gap" path="flControllo5x20"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x20"
																	cssClass="with-gap" path="flControllo5x20"
																	value="N"/>
																<span>No</span>
														  </label>
                     </div> 
                       <div class="row">					
							<c:forEach items="${listaConoscenzaProfessionale}" var="conosc" varStatus="index">
								<div class="input-field col s12 m12 l12">
									<c:if test="${index.index ==0}">
										<form:errors path="idsConoscenzeProfessionali" cssClass="error" />
									</c:if>
									<label> <form:checkbox path="idsConoscenzeProfessionali"
											id="idsConoscenzeProfessionali" name="idsConoscenzeProfessionali"
											value="${conosc.idConoscenzaProfessionale}" /> <span>${conosc.descEstesa }</span>
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
								<label class="radio-inline"> <form:errors
										path="flControllo5x21" cssClass="error" /> <form:radiobutton
										id="flControllo5x21" cssClass="with-gap"
										path="flControllo5x21" value="S" /> <span>Si</span>
								</label> <label class="radio-inline"> <form:radiobutton
										id="flControllo5x21" cssClass="with-gap"
										path="flControllo5x21" value="N" /> <span>No</span>
								</label>

							</div>

							<div class="row">					
						<c:forEach items="${listaRequisitoProfessionale}" var="requisito" varStatus="index">
						 		
						 		<c:if test="${requisito.idRequisitoProfessionale!=1}">
						 		<div class="input-field col s12 m12 l12">
						 		<c:if test="${index.index ==0}">
									<form:errors path="idsRequisitiProfessionali" cssClass="error" />
								</c:if>
								<label> <form:checkbox path="idsRequisitiProfessionali"
										id="idsRequisitiProfessionali" name="idsRequisitiProfessionali"
										value="${requisito.idRequisitoProfessionale}" /> <span>${requisito.descEstesa }</span>
								</label>
								</div>
								</c:if>
								<c:if test="${requisito.idRequisitoProfessionale==1}">
									<div class="input-field col s4 m4 l4">
									<c:if test="${index.index ==0}">
										<form:errors path="idsRequisitiProfessionali" cssClass="error" />
									</c:if>									
									<label> <form:checkbox path="idsRequisitiProfessionali"
											id="idsRequisitiProfessionali" name="idsRequisitiProfessionali"
											value="${requisito.idRequisitoProfessionale}" /> <span>${requisito.descEstesa }</span>
									</label>
									</div>
									<div class="input-field col s4 m4 l4">
									<form:input type="text" id="laurea" path="laurea" />
									<form:errors path="laurea" cssClass="error" />
									<label for="laurea">Laurea: </label>
									</div>
									<div class="input-field col s4 m4 l4">
									<form:input type="text" id="diploma" path="diploma" />
									<form:errors path="diploma" cssClass="error" />
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
														  <label class="radio-inline">
																	<form:errors path="flControllo5x22" cssClass="error"/>
																	<form:radiobutton id="flControllo5x22"
																		cssClass="with-gap" path="flControllo5x22"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x22"
																	cssClass="with-gap" path="flControllo5x22"
																	value="N"/>
																<span>No (vedi tabella)</span>
														  </label>
                     </div> 
                     <c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
                     <div class="row">
										 	<div class="input-field col s6 m5 l12">
								                <form:textarea id="noteControllo5x22" path="noteControllo5x22"
								                  cssClass="materialize-textarea validate" rows="2"
								                  data-length="300" maxlength="300" />
								                <form:errors path="noteControllo5x22" cssClass="error" />
								                <label for="noteControllo5x22">In caso di risposta negativa indicare i sintomi riscontrati e l’eventuale ON a cui sono riconducibili: </label>											
											</div>		
				    </div>
				    </c:if>
							<div class="row">
							<div class="input-field col s12 m12">
						           <div class="row" id="divSpecie">
									            <div class="input-field col s12 m5">
									              <input type="text" name="genereFisico" placeholder="Scrivere generi più rappresentativi"  
									                class="validate autocomplete" autocomplete="off" />
									              <label class="active">Genere *</label>
									            </div>            
									            <div class="input-field col s12 m5">
									              <select name="specieFisico" multiple="multiple"
									                class="validate multiselect">
									                	 <option value="" disabled="">SELEZIONARE</option>
									              </select>	
									              <form:errors path="specieFisico" cssClass="error" />								              
									              <label>Specie *</label>
									            </div>
									            <div class="input-field col s12 m5 l12">
									            	<form:select id="orgNociviFisico"  multiple="multiple"
														path="orgNociviFisico" class="validate multiselect">											
														<form:options items="${listaOrgNocGenereSpecie}"
															itemValue="idOrgNocivo" itemLabel="descBreve" />
													</form:select>
													<form:errors path="orgNociviFisico" cssClass="error" />
													<label for="labelOrgNociviFisico" class="active">Organismo nocivo</label>
									            </div>
									            <div class="input-field col s12 m2">									              
									               <button id="aggiungiSpecie" name="aggiungiSpecie" type="submit" 
									              	class="btn green waves-effect waves-light" type="button"  style="button"> AGGIUNGI SPECIE 
									              </button>									             									                									               
									            </div>
								  </div>  
					        </div>					           
								<table id="tabellaSpecie" class="data-table striped display "
									data-orderable='false' data-paging="false" data-info="false">
									<thead>
										<tr>
											<th>Genere</th>
											<th>Specie</th>
											<th>Numero piante controllate</th>
											<th>Numero piante sintomatiche</th>
											<th>Organismo Nocivo</th>
											<th>
												<p>
												   <label>
												      <span class="white-text">
												        <input type="checkbox" onClick="check_uncheck_checkbox(this.checked);" id="checkall" name="checkall" />
												        <span>Seleziona tutti</span>
												      </span>
												   </label>
												</p>
											</th>
											<th></th>
										</tr>
									</thead>
									<tbody id="bodyTabellaSpecie">
										<c:forEach items="${nuovoControlloForm.genereSpecie}" var="specie" varStatus="status">
											<tr>
												<td>${specie.denomGenere }</td>
												<td>${specie.denomSpecie }</td>
												<td>
													<div class="input-field">
														<input type="text" name="numeroPiante" value="${specie.numeroPiante }"  class="validate" 
														pattern="\d*"  maxlength="10" 
														oninvalid="this.setCustomValidity('Numero piante non valido')" oninput="this.setCustomValidity('')"/>			       			       	
													</div>
													
												</td>
												<td>
													<div class="input-field">
														<input type="text" name="numPianteSintomatiche" value="${specie.numPianteSintomatiche }"  class="validate" 
														pattern="\d*"  maxlength="10" 
														oninvalid="this.setCustomValidity('Numero piante non valido')" oninput="this.setCustomValidity('')"/>			       			       	
													</div>
												</td>
												<td>
												  ${specie.descBreveOn}																									
												</td>
												<td style="text-align: center;">
												    <p>
												      <label>
												        <input type="checkbox" value="${specie.idControlloFisicoSpecie}" id="idsDaEliminare" name="idsDaEliminare" path="idsDaEliminare"/>
												        <span></span>
												      </label>
												    </p>
												</td> 
												<td>
													<c:choose>
														<c:when test="${specie.idOrgNocivo != null}">												  
													    	<a href='javascript:modificaOrganismiNocivi(${specie.idControlloFisicoSpecie}, ${specie.idOrgNocivo}, ${status.index})'
																class="btn btn-floating btn-floating-medium tooltipped light-green accent-3" 
																data-tooltip="Modifica organismi nocivi">
																<i class="material-icons">mode_edit</i>
													    	</a>
													  	</c:when>
													  	<c:otherwise>
													  		<a href='javascript:modificaOrganismiNocivi(${specie.idControlloFisicoSpecie}, null, ${status.index})'
																class="btn btn-floating btn-floating-medium tooltipped light-green accent-3" 
																data-tooltip="Modifica organismi nocivi">
																<i class="material-icons">mode_edit</i>
													    	</a>
													  	</c:otherwise>
													</c:choose> 													    												    
												</td>												
												<c:set var="nRigheSpecie" value="${status.index}"/>
											</tr>
										</c:forEach>																				   
									</tbody>
								</table>
								<div class="right" style="text-align: right; padding-top: 10px;"> 	
									<button type="button" onclick="return eliminaSpecie()" class="btn confirm waves-effect waves-light" style="display: inline-block">Elimina selezionati</button>
									<button name="salvaCancella" id="salvaCancella" type="submit" class="btn confirm waves-effect waves-light" style="display:none">cancella</button>
								</div>
								
							</div>
					
							</li-lista>		
				    <!-- 5.23 -->
				  <br>
				  <li-lista><b>Nel corso del controllo si è proceduto a prelievo di campioni per le analisi di laboratorio</b>
				      <div class="row">														
														  <label class="radio-inline">
																	<form:errors path="flControllo5x23" cssClass="error"/>
																	<form:radiobutton id="flControllo5x23"
																		cssClass="with-gap" path="flControllo5x23"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo5x23"
																	cssClass="with-gap" path="flControllo5x23"
																	value="N"/>
																<span>No</span>
														  </label>
                     </div> 
				  </li-lista>				  	
				  </ol> 				
				</div>	
			</div>
			</div>
			  <div class="card-action">	
				  <div class="row">
				    <a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a> 
					  <div class="right" style="text-align: right"> 		  	   
					    <button name="salvaControlloFisico" type="submit" class="btn confirm waves-effect waves-light" style="display: inline-block"> SALVA </button>
					 </div>
				  </div>
			  </div>
    		</div>
  
  </form:form>	
  
  
  <!-- INIZIO MODALE -->
	<div id="editOrgNocFisicoModal" class="modal scrollbar-thin">					
		<div class="modal-content" style="padding-top:0; padding-bottom:0;"></div>
	</div>

<content tag="script"> <script>

	var idSpecieSelezionati = null;
	var idSpecieHidden = null;

	$(document).ready(function() {
						$('input[name=genereFisico]').each(function() {
							let $self = $(this);
							$self.autocomplete({
								limit : 50,
								minLength : 2,
								onAutocomplete : function(value) {
									caricaSpecieGenere($self);
								}
							});

							$self.on("input", function() {
								popolaListaGeneri($(this));
								caricaSpecieGenere($self);
							});
						});
					});

	
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
		$selectSpecie = $inputGenere.closest('.row').find('select[name=specieFisico]');
		
		if ($selectSpecie && $inputGenere.val()) {
			var params = {
				"denomGenere" : $inputGenere.val()
				, "idSpecieSel" : idSpecieHidden
			}
			
			getSelectAsincronaRef($selectSpecie,
					'<spring:url value="/ajax/getListaSpecieDenomGenere" />',
					params, getOptionSpecie, 1, reloadMultiselect);
		}
	}
	
	function reloadMultiselect($selectRef) {
		if (idSpecieSelezionati) {
			var arrayIdSpecie = idSpecieSelezionati.split(',');
			    
			$selectRef.find('option').each(function() {
				$option = $(this);
			
				var indiceInArray = $.inArray($option.val(), arrayIdSpecie);
				
				/*  
				 *  Se viene trovato l'id specie nell'array, la option viene selezionata 
				 *  e l'id specie viene rimosso dall'array per velocizzare le ricerche successive 
				 */
				 
				if (indiceInArray >= 0) {
					$option.prop('selected', 'selected');
					arrayIdSpecie.splice(indiceInArray, 1);
				}
			});
			
		}
		
		$selectRef.formSelect();
		inizializzaMultiselect($selectRef);
	}
	
	function getOptionSpecie(specie) {
		return getOption(specie.idSpecie, specie.denomSpecie);
  }
	
	

	// Gestione modale elimina specie
	function eliminaSpecie() {
		   console.log('BEGIN eliminaSpecie');


		   var messaggio =  "Sei sicuro di voler eliminare le specie selezionate?";
				 
				swal({
					title : 'Attenzione!',
					html : messaggio,
					type : 'warning',
					showCancelButton : true,
					confirmButtonText : 'Sì',
					cancelButtonText : 'No',
				}).then(function(result) {
					if (result.value) {
						document.getElementById('salvaCancella').click();
					}
				});
				return false;
	}
	
	// Gestione modale modifica organismo nocivo
	
	function modificaOrganismiNocivi(idControlloFisicoSpecie, idOrgNocivo, index) {
			console.log('BEGIN modificaOrganismiNocivi');
			
			console.log('-- idControlloFisicoSpecie ='+idControlloFisicoSpecie);
			console.log('-- idOrgNocivo ='+idOrgNocivo);
			console.log('********** index: ' + index);
			
			// numero piante
			var numPianteArr = document.getElementsByName('numeroPiante');
			var numPiante;
			if(numPianteArr != null){
				numPiante = numPianteArr[index].value;
			}
			console.log('numPiante ='+numPiante);
			
			// numero piante asintomatiche
			var numPianteSintomaticheArr = document.getElementsByName('numPianteSintomatiche');
			var numPianteSintomatiche;
			if(numPianteSintomaticheArr != null){
				numPianteSintomatiche = numPianteSintomaticheArr[index].value;
			}
			console.log('numPianteSintomatiche ='+numPianteSintomatiche);
						
			// Controllare che numPiante e numPianteSintomatiche siano campi numerici validi : lunghezza massima 10 caratteri e solo numeri		
			var isAnumber = true;
			var lunghezza = true;
			if(numPiante != null && numPiante != ''){
				console.log('numPiante.trim().length ='+numPiante.trim().length);
				if(numPiante.trim().length <=10){
					if(!numPiante.match(/^\d+$/)){
						isAnumber = false;				
					}
				}
				else{
					lunghezza = false;
				}
			}
			if(numPianteSintomatiche != null && numPianteSintomatiche != ''){
				console.log('numPianteSintomatiche.trim().length ='+numPianteSintomatiche.trim().length);
				if(numPianteSintomatiche.trim().length <=10){
					if(!numPianteSintomatiche.match(/^\d+$/)){
						isAnumber = false;				
					}
				}
				else{
					lunghezza = false;
				}
			}
			console.log('isAnumber ='+isAnumber);
			console.log('lunghezza ='+lunghezza);
			if(!isAnumber){
				console.log('Non apro la modale, devono indicare in valore numerico in numpiante o num piante sintomatiche');
				Swal.fire({
					  title : 'Errore',
					  html : 'Indicare un valore numerico nel campo Numero piante controllate e Numero piante sintomatiche',
					  type : 'error',
					  confirmButtonText : 'OK',
				});
			}
			if(!lunghezza){
				console.log('Non apro la modale, devono indicare un valore al massimo di 10 numeri in numpiante o num piante sintomatiche');
				Swal.fire({
					  title : 'Errore',
					  html : 'Indicare un valore con al massimo dieci cifre numeriche nel campo Numero piante controllate e Numero piante sintomatiche',
					  type : 'error',
					  confirmButtonText : 'OK',
				});
			}
			if(isAnumber && lunghezza){
				console.log('apro la modale');
				$.ajax({				
					url : '<spring:url value="/controlli/controllofisico/editOrgNocFisico"/>/' +idControlloFisicoSpecie
				}).done(function(response) {
					apriModaleOrganismiNocivi(response, idControlloFisicoSpecie, idOrgNocivo, numPiante, numPianteSintomatiche);
				}).fail(function(jqXHR, textStatus) {
				    console.log('TEXT STATUS: '+textStatus);
				    Swal.fire({
						  title : 'Errore',
						  html : 'Utente non abilitato',
						  type : 'error',
						  confirmButtonText : 'OK',
					});
				});
			}
	}
	
	
	function IsNumeric(sText) {
		console.log('BEGIN IsNumeric');
		  if(!sText)
		    return false;
		  var ValidChars = "0123456789";
		  var IsNumber=true;
		  var Char;
		  for(i = 0; i < sText.length && IsNumber == true; i++) { 
		      Char = sText.charAt(i); 
		      if(ValidChars.indexOf(Char) == -1) {
		        IsNumber = false;
		      }
		    }
		  return IsNumber;
	 }
	
	
	function apriModaleOrganismiNocivi(content, idControlloFisicoSpecie, idOrgNocivo, numPiante, numPianteSintomatiche) {
			console.log('BEGIN apriModaleOrganismiNocivi');
			
			console.log('-- idControlloFisicoSpecie ='+idControlloFisicoSpecie);
			console.log('-- idOrgNocivo ='+idOrgNocivo);
			console.log('-- numPiante ='+numPiante);
			console.log('-- numPianteSintomatiche ='+numPianteSintomatiche);
			

			$modal = $('#editOrgNocFisicoModal');
			$modalContent = $modal.find('div.modal-content');
			$modalContent.empty();
			
			var instance = M.Modal.getInstance($modal);
			console.log('INSTANCE: '+instance.id);
			if (instance.isOpen) {
				console.log('Modale aperta!!!');
				instance.destroy();
				console.log('Modale distrutta!!!');
			}
			
			
			$modalContent.html(content);
			
		    if (instance) {
				editInit(idControlloFisicoSpecie, idOrgNocivo, numPiante, numPianteSintomatiche);
			}
			
			$modal.find('select').formSelect();
			M.updateTextFields();
			// per l'apertura della modale è necessario configurare anche i file:
			// SiteMeshFilter.java e SpringSecurityConfig.java
			$modal.modal('open');
	}
	
	
	function salvaModificaOrganismiNocivi($form, idControlloFisicoSpecie, numPiante, numPianteSintomatiche) {
		console.log('BEGIN salvaModificaOrganismiNocivi');
		
		console.log('- idControlloFisicoSpecie ='+idControlloFisicoSpecie);
		console.log('- numPiante ='+numPiante);
		console.log('- numPianteSintomatiche ='+numPianteSintomatiche);
		
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
				console.log('RISPOSTA SALVATAGGIO: ');//+response);
				$modal = $('#editOrgNocFisicoModal');
			    
			    if (response) {
			    	console.log('RESPONSE VALORIZZATO');
			    	$modal.find('div.modal-content').html(response);
			    	
			    	//$modal.find('select').formSelect();
					M.updateTextFields();
			    }
			    else {
			    	console.log('AGGIORNO TABELLA GENERI, SPECIE, ORGANISMI NOCIVI');
			    	$modal.modal('close');
			    	Swal.fire({
						  title : idControlloFisicoSpecie ? 'Organismo nocivo modificato' : '',
						  type : 'success',
						  confirmButtonText : 'OK',
					}).then(function(result) {
						if (result.value) {
							  console.log('RICARICO LA PAGINA');
							  window.location.href = '<spring:url value="/controlli/controllofisico/modifica"/>';
						}
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
	
	function check_uncheck_checkbox(isChecked) {
		if(isChecked) {
			$('input[name="idsDaEliminare"]').each(function() { 
				this.checked = true; 
			});
		} else {
			$('input[name="idsDaEliminare"]').each(function() {
				this.checked = false;
			});
		}
	}
	
	
	
	
</script>
 	  
 	 </content>

</body>
</html>