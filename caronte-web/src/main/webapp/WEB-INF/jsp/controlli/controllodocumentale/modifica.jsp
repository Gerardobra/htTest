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
  <spring:url value="/controlli/controllodocumentale/modifica/salva" var="formAction" />
  <form:form action="${formAction}" method="post" modelAttribute="nuovoControlloForm" accept-charset="utf-8">    
	<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>MODIFICA CONTROLLO DOCUMENTALE</b></h3>
			</div>			
  			<div class="card-content" style="padding-top:5px;">
  				<div class="row">
   	 				<div class="col s12">
      					<ul class="tabs">        						        				    		        	
			        	    <li class="tab" >
	        					<a href="<spring:url value="/controlli/datiBase/modifica" />" target="_self">Dati generali</a>
	        				</li>	
	        				<li class="tab" >
        						<a class="active"><b>Documentale</b></a>
        					</li>
        					<c:if test="${nuovoControlloForm.tabIdentita}">
        						<li class="tab" >
	        						<a href="<spring:url value="/controlli/controlloidentita/modifica" />" target="_self">Identità</a>
	        					</li>
	        				</c:if>	
	        				<c:if test="${nuovoControlloForm.tabFisico}">
	        					<li class="tab" >
	        						<a href="<spring:url value="/controlli/controllofisico/modifica" />" target="_self">Fisico</a>
	        					</li>
	        				</c:if>		        				
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
        						  															
				<spring:eval var="versioneBegin" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_CONTROLLO_BEGIN" />	
				<spring:eval var="versione042021" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_CONTROLLO_04_2021" />	
				
				<div class="card-panel">	
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
													  <label class="radio-inline">
																<form:radiobutton id="flControllo3x1"
																	cssClass="with-gap" path="flControllo3x1"
																	value="S"/>
																<span>Si</span>
													  </label>																																							  												
													  <label class="radio-inline">
															<form:radiobutton id="flControllo3x1"
																cssClass="with-gap" path="flControllo3x1"
																value="N"/>
															<span>No</span>
														</label>													
													  <label class="radio-inline">
															<form:radiobutton id="flControllo3x1"
																cssClass="with-gap" path="flControllo3x1"
																value="NA"/>
															<span>NA</span>
													  </label>	
													  <form:input type="text" id="descControllo3x1"
														path="descControllo3x1" maxlength="30" />
														<form:errors path="descControllo3x1" cssClass="error"/>
														<label for="labelDescControllo3x1">Codice iscrizione RUOP/n. autorizzazione:</label>									
												</div>
                            <ol style="list-style-type: decimal;">										
									  <!-- 3.1.1 -->	
					        			<li-lista><b>La tipologia di attività riportata nel documento di iscrizione al RUOP è adeguata alla tipologia di attività svolta dall'OP</b>
					                       <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x1x1"
																		cssClass="with-gap" path="flControllo3x1x1"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x1x1"
																	cssClass="with-gap" path="flControllo3x1x1"
																	value="N"/>
																<span>No</span>
														  </label>																																				
										 </div>
										 <div class=row">
										 	<div class="input-field col s6 m5 l12">
								                <form:textarea id="noteControllo3x1x1" path="noteControllo3x1x1"
								                  cssClass="materialize-textarea validate" rows="2"
								                  data-length="300" maxlength="300" />
								                <form:errors path="noteControllo3x1x1" cssClass="error" />
								                <label for="noteControllo3x1x1">Annotazioni</label>							                	
	
										 </div>	
									   </li-lista>			     			
					   		    </ol>												
					  </li-lista>
				 </c:if>
				 <c:if test="${nuovoControlloForm.idVersioneControllo >= versione042021}">	
				 		<!-- 3.1 V2 -->	
					     <li-lista><b>La tipologia di attività riportata nel documento di iscrizione al RUOP è adeguata alla tipologia di attività svolta dall'OP</b>
					                       <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x1x1"
																		cssClass="with-gap" path="flControllo3x1x1"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x1x1"
																	cssClass="with-gap" path="flControllo3x1x1"
																	value="N"/>
																<span>No</span>
														  </label>																																				
										 </div>
										 
						</li-lista>		
				 </c:if>								
                  <!-- 3.2 -->				   						  
				  <li-lista><b>La domanda di iscrizione al RUOP inviata al servizio è provvista di marca da bollo</b>
				     <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x2"
																		cssClass="with-gap" path="flControllo3x2"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x2"
																	cssClass="with-gap" path="flControllo3x2"
																	value="N"/>
																<span>No</span>
														  </label>
					</div>

				  </li-lista>	
				  
				  <c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
				  <!-- 3.3 -->	
				  <li-lista><b>L'OP è autorizzato all'uso del passaporto:</b>
				                   <div class="row">													
													  <label class="radio-inline">
																<form:radiobutton id="flControllo3x3"
																	cssClass="with-gap" path="flControllo3x3"
																	value="S"/>
																<span>Si</span>
													  </label>																																							  												
													  <label class="radio-inline">
															<form:radiobutton id="flControllo3x3"
																cssClass="with-gap" path="flControllo3x3"
																value="N"/>
															<span>No</span>
														</label>													
													  <label class="radio-inline">
															<form:radiobutton id="flControllo3x3"
																cssClass="with-gap" path="flControllo3x3"
																value="NA"/>
															<span>NA</span>
													  </label>													 									
									</div>
									<!-- 3.3.1 -->
									<ol style="list-style-type: decimal;">
									  <li-lista><b>L'autorizzazione al passaporto è adeguata alla tipologia di piante, prodotti vegetali e altri oggetti prodotte dall'OP:</b>
					                       <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x3x1"
																		cssClass="with-gap" path="flControllo3x3x1"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x3x1"
																	cssClass="with-gap" path="flControllo3x3x1"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x3x1"
																	cssClass="with-gap" path="flControllo3x3x1"
																	value="NA"/>
																<span>NA</span>
														  </label>																																					
										 </div>
										 <div class=row">
										 	<div class="input-field col s6 m5 l12">
								                <form:textarea id="noteControllo3x3x1" path="noteControllo3x3x1"
								                  cssClass="materialize-textarea validate" rows="2"
								                  data-length="300" maxlength="300" />
								                <form:errors path="noteControllo3x3x1" cssClass="error" />
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
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x3x1"
																		cssClass="with-gap" path="flControllo3x3x1"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x3x1"
																	cssClass="with-gap" path="flControllo3x3x1"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x3x1"
																	cssClass="with-gap" path="flControllo3x3x1"
																	value="NA"/>
																<span>NA</span>
														  </label>																																					
										 </div>
										 
									  </li-lista>
					</c:if>		
                  							   
                  <!-- 3.4 -->								   													  
				  <li-lista><b>La richiesta di passaporto inviata al servizio è conforme alle disposizioni sul bollo</b>
				       <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x4"
																		cssClass="with-gap" path="flControllo3x4"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x4"
																	cssClass="with-gap" path="flControllo3x4"
																	value="N"/>
																<span>No</span>
														  </label>
														   <label class="radio-inline">
																<form:radiobutton id="flControllo3x4"
																	cssClass="with-gap" path="flControllo3x4"
																	value="NA"/>
																<span>NA</span>
														  </label>
                        </div>														  
														
					
				  </li-lista>	
				  <!-- 3.5 -->
				  <li-lista><b>L’autorizzazione al passaporto è conforme alle disposizioni sul bollo e il numero sulla marca da bollo corrisponde al numero riportato nel documento</b>
				    <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x5"
																		cssClass="with-gap" path="flControllo3x5"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x5"
																	cssClass="with-gap" path="flControllo3x5"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x5"
																	cssClass="with-gap" path="flControllo3x5"
																	value="NA"/>
																<span>NA</span>
														  </label>
					</div>														  																																				
																				
				  </li-lista>
				  
				  <c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
				  <!-- 3.6 -->
				  <li-lista><b>L'OP espone presso il CA copia del documento di iscrizione al RUOP:</b>
				       <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x6"
																		cssClass="with-gap" path="flControllo3x6"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x6"
																	cssClass="with-gap" path="flControllo3x6"
																	value="N"/>
																<span>No</span>
														  </label>
					   </div>					    																															   
				  </li-lista>
				  </c:if>
				  
				  <!-- 3.7 -->
				  <li-lista><b>L'OP riporta il numero dell'iscrizione al RUOP sui documenti (carta intestata, fatture, DDT, timbri, passaporti, etc):</b>
				    <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x7"
																		cssClass="with-gap" path="flControllo3x7"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x7"
																	cssClass="with-gap" path="flControllo3x7"
																	value="N"/>
																<span>No</span>
														  </label>
					</div>														  																																				
																						  
				  </li-lista>
				  <!-- 3.8 -->
				  <li-lista><b>L'OP comunica annualmente l'elenco delle piante in produzione secondo le indicate dal Servizio per ogni CA autorizzato</b>
				     <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x8"
																		cssClass="with-gap" path="flControllo3x8"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x8"
																	cssClass="with-gap" path="flControllo3x8"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x8"
																	cssClass="with-gap" path="flControllo3x8"
																	value="NA"/>
																<span>NA</span>
														  </label>																																				
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
								                <form:textarea id="noteControllo3x8" path="noteControllo3x8"
								                  cssClass="materialize-textarea validate" rows="2"
								                  data-length="300" maxlength="300" />
								                <form:errors path="noteControllo3x8" cssClass="error" />
								                <label for="noteControllo3x8">Annotazioni</label>

											</div>		
				    </div>
				    </c:if>
				  </li-lista>
				  <!-- 3.9 -->
				  <li-lista><b>L’OP paga annualmente la tariffa fitosanitaria dovuta</b>
				  	<div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x9"
																		cssClass="with-gap" path="flControllo3x9"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x9"
																	cssClass="with-gap" path="flControllo3x9"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x9"
																	cssClass="with-gap" path="flControllo3x9"
																	value="NA"/>
																<span>NA</span>
														  </label>																																				
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
								                <form:textarea id="noteControllo3x9" path="noteControllo3x9"
								                  cssClass="materialize-textarea validate" rows="2"
								                  data-length="300" maxlength="300" />
								                <form:errors path="noteControllo3x9" cssClass="error" />
								                <label for="noteControllo3x9">Annotazioni</label>

											</div>		
				    </div>
				    </c:if>
				  </li-lista>
				  <!-- 3.10 -->
				  <li-lista><b>L'OP tiene la tracciabilità delle piante acquistate, cedute o spostate in ambito aziendale per almeno 3 anni</b>
				     <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x10"
																		cssClass="with-gap" path="flControllo3x10"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x10"
																	cssClass="with-gap" path="flControllo3x10"
																	value="N"/>
																<span>No</span>
														  </label>																																				
					 </div>

				  </li-lista>
				  <!-- 3.11 -->
				  <li-lista><b>L'OP ha il registro dei trattamenti e provvede alla registrazione entro 30 giorni dal trattamento</b>
				  	<div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x11"
																		cssClass="with-gap" path="flControllo3x11"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x11"
																	cssClass="with-gap" path="flControllo3x11"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x11"
																	cssClass="with-gap" path="flControllo3x11"
																	value="NA"/>
																<span>NA</span>
														  </label>																																				
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
								                <form:textarea id="noteControllo3x11" path="noteControllo3x11"
								                  cssClass="materialize-textarea validate" rows="2"
								                  data-length="300" maxlength="300" />
								                <form:errors path="noteControllo3x11" cssClass="error" />
								                <label for="noteControllo3x11">Annotazioni</label>
												
											</div>		
				    </div>
				    </c:if>
				  </li-lista>
				  <!-- 3.12 -->
				  <li-lista><b>L'OP tiene presso il CA la planimetria aggiornata dei propri campi di produzione, dei magazzini e dei vegetali in produzione</b>
				  		<div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x12"
																		cssClass="with-gap" path="flControllo3x12"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x12"
																	cssClass="with-gap" path="flControllo3x12"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x12"
																	cssClass="with-gap" path="flControllo3x12"
																	value="NA"/>
																<span>NA</span>
														  </label>																																				
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
								                <form:textarea id="noteControllo3x12" path="noteControllo3x12"
								                  cssClass="materialize-textarea validate" rows="2"
								                  data-length="300" maxlength="300" />
								                <form:errors path="noteControllo3x12" cssClass="error" />
								                <label for="noteControllo3x12">Annotazioni</label>
													
											</div>		
				    </div>	
				    </c:if>			  
				  </li-lista>
				  <!-- 3.13 -->
				  <li-lista><b>L'OP possiede un fascicolo aziendale aggiornato con i mappali a vivaio georeferenziati</b>
				     <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x13"
																		cssClass="with-gap" path="flControllo3x13"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x13"
																	cssClass="with-gap" path="flControllo3x13"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x13"
																	cssClass="with-gap" path="flControllo3x13"
																	value="NA"/>
																<span>NA</span>
														  </label>																																				
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
								                <form:textarea id="noteControllo3x13" path="noteControllo3x13"
								                  cssClass="materialize-textarea validate" rows="2"
								                  data-length="300" maxlength="300" />
								                <form:errors path="noteControllo3x13" cssClass="error" />
								                <label for="noteControllo3x13">Annotazioni</label>
												
											</div>		
				    </div>
				    </c:if>
				  </li-lista>	
				  <!-- 3.14 -->
				  <li-lista><b>L'OP compila i passaporti in modo corretto e li rilascia quando previsto</b>
				  	<div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x14"
																		cssClass="with-gap" path="flControllo3x14"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x14"
																	cssClass="with-gap" path="flControllo3x14"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x14"
																	cssClass="with-gap" path="flControllo3x14"
																	value="NA"/>
																<span>NA</span>
														  </label>																																				
					</div>

				  </li-lista>
				  <!-- 3.15 -->
				  <li-lista><b>L'OP compila il documento di commercializzazione in modo corretto e lo rilascia quando previsto</b>
				      <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x15"
																		cssClass="with-gap" path="flControllo3x15"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x15"
																	cssClass="with-gap" path="flControllo3x15"
																	value="N"/>
																<span>No</span>
														  </label>
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x15"
																	cssClass="with-gap" path="flControllo3x15"
																	value="NA"/>
																<span>NA</span>
														  </label>																																				
					</div>
					<c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">
					<div class=row">
										 	<div class="input-field col s6 m5 l12">
								                <form:textarea id="noteControllo3x15" path="noteControllo3x15"
								                  cssClass="materialize-textarea validate" rows="2"
								                  data-length="300" maxlength="300" />
								                <form:errors path="noteControllo3x15" cssClass="error" />
								                <label for="noteControllo3x15">Annotazioni</label>
							              	</div>										 										 											    	
				    </div>
				    </c:if>
				  </li-lista>
				  <!-- 3.16 -->
				  <li-lista><b>Nel caso di produzioni forestali è presente il registro di carico e scarico dei materiali forestali:</b>
				      <div class="row">														
														  <label class="radio-inline">
																	<form:radiobutton id="flControllo3x16"
																		cssClass="with-gap" path="flControllo3x16"
																		value="S"/>
																	<span>Si</span>
														  </label>														
														  <label class="radio-inline">
																<form:radiobutton id="flControllo3x16"
																	cssClass="with-gap" path="flControllo3x16"
																	value="N"/>
																<span>No</span>
														  </label>
                     </div> 

				  </li-lista>			  	
				  </ol> 				
				</ol>  
				</div>	
			</div>
  </div>
  <div class="card-action">	
	  <div class="row">
	    <a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a> 
		  <div class="right" style="text-align: right"> 		  	   
		    <button id="salvaControlloDoc" type="submit" class="btn confirm waves-effect waves-light" style="display: inline-block"> SALVA </button>
		 </div>
	  </div>
  </div>
 </div> 
  </form:form>

  
		

<content tag="script">     
	  <script>
	    
	 			
 	  </script>
 	  
 	 </content>

</body>
</html>