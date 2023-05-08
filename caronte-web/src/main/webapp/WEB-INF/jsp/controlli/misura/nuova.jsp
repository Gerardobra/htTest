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
</style>
<style type="text/css">

.daterangepicker {
  position: fixed !important;
}
  
</style>
</head>
<body>
  <spring:url value="/controlli/misura/nuova/salva" var="formAction" />
  <form:form action="${formAction}" method="post" modelAttribute="nuovoControlloForm" accept-charset="utf-8">    
	<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>NUOVA MISURA UFFICIALE</b></h3>
			</div>			
  			<div class="card-content" style="padding-top:5px;">
  				<div class="row">
   	 				<div class="col s12">
      					<ul class="tabs">        						        				    		        	
			        	    <li class="tab" >
	        					<a href="<spring:url value="/controlli/datiBase/nuova" />" target="_self">Dati generali</a>
	        				</li>	        				
	        				<c:if test="${nuovoControlloForm.tabDocumentale}">
	        					<li class="tab" >
	        				   		<a href="<spring:url value="/controlli/controllodocumentale/nuova" />" target="_self">Documentale</a>
        						</li>
        					</c:if>
        					<c:if test="${nuovoControlloForm.tabIdentita}">
        						<li class="tab" >
	        						<a href="<spring:url value="/controlli/controlloidentita/nuova" />" target="_self">Identità</a>
	        					</li>
	        				</c:if>	
	        				<c:if test="${nuovoControlloForm.tabFisico}">
	        					<li class="tab">
							  		<a href="<spring:url value="/controlli/controllofisico/nuova" />" target="_self">Fisico</a>
								</li>
							</c:if>	
	        				<c:if test="${nuovoControlloForm.tabFisico && nuovoControlloForm.flControllo5x23 != null && nuovoControlloForm.flControllo5x23 =='S' }">
		        				<li class="tab" >
		        					<a href="<spring:url value="/controlli/campioni/nuova" />" target="_self">Campioni</a>
		        				</li>
	        				</c:if>
	        				<li class="tab" >
	        					<a href="<spring:url value="/controlli/allegati/nuova" />" target="_self">Allegati</a>
	        				</li>
	        				<c:if test="${false}">
	        				<li class="tab" >
	        					<a href="<spring:url value="/controlli/monitoraggio/nuova" />" target="_self">Monitoraggio cofinanziato</a>
	        				</li>
	        				</c:if>
	        				<li class="tab" >
        						<a class="active"><b>Misura ufficiale</b></a>
	        				</li>
	        				<li class="tab" >
	        					<a href="<spring:url value="/controlli/esito/nuova" />" target="_self">Esito</a>
	        				</li>
		        					
      					</ul>
    				</div>
  				</div>
    
        <!-- INIZIO MISURA UFFICIALE DISPOSIZIONE -->    			
		<div class="card-panel">	        	    
				  <div class="row">
						<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Misura ufficiale - Disposizione</em>
						</p>
				  </div>
				  <div class="row s12">
				    <div class="input-field col s6 m4 l4">
							<form:input type='text' id="dataMisura" path="dataMisura"
								cssClass="datepicker_birthdate"/>
							<form:errors path="dataMisura" cssClass="error" />
							<label for="dataMisura">Data misura</label>
					</div>
					<div class="input-field col s6 m4 l4">
							<form:input type='text' id="oraMisura"
								path="oraMisura" cssClass="timepicker validate"/>
							<form:errors path="oraMisura" cssClass="error" />
							<label for="oraMisura">Ora</label>
					</div>
					<div class="input-field col s6 m4 l4">
									<form:input type="text" id="numVerbMisuraUff" readonly="true"
										path="numVerbMisuraUff"/>
									<form:errors path="numVerbMisuraUff" cssClass="error"/>
									<label for="numVerbMisuraUff">N. verbale misura ufficiale</label>
					</div>
					<div class="input-field col s6 m4 l12">
							<form:select multiple="multiple" id="nomeIspettoreMisura"
								path="idsIspettoreMisura" cssClass="validate" required="required">
								<option value="" disabled="">Selezionare</option>
								<form:options items="${elencoIspettoriMisura}" itemValue="id"
									itemLabel="descr" />
							</form:select>
							<form:errors path="idsIspettoreMisura" cssClass="error" />
							<label for="idsIspettoreMisura" class="active">Ispettore che ha emesso la misura *</label>
					</div>																															
				</div>
				<div class="row s12">
				  <div class="input-field col s4 m4 l12">					
						<form:textarea id="noteNonConformita" path="noteNonConformita"
							cssClass="materialize-textarea validate" rows="2"
							data-length="1000" maxlength="1000" required="required"/>
							<form:errors path="noteNonConformita" cssClass="error" />
							<label for="noteNonConformita">Elenco delle non conformità accertate *</label>								  									  
					</div>
				</div>
				<div class="row col s12">
							<h6>Misure applicate</h6>
							<!------ MISURA APPLICATA -------->
							<c:forEach items="${listaMisure}" var="misura" varStatus="status">							
							<div class="row col s12">
								<div class="input-field col s4 m4 l6">								
									<form:errors path="misure[${status.index}].idMisura" cssClass="error" />
										<label> <form:checkbox
												path="misure[${status.index}].idMisura"
												name="tipoMisura" value="${misura.idTipologiaMisura}"/>
											<span>${misura.descEstesa} :</span>
										</label>																
								</div>
								<div class="input-field col s4 m4 l6">
									<form:input type="text" placeholder="specificare"
												path="misure[${status.index}].note" name="noteMisura"
												maxlength="300" />
											<form:errors path="misure[${status.index}].note" cssClass="error" />	
											<label for="noteMisura"></label>																								  	
								</div>
							</div>															
						</c:forEach>							
				</div>
				<div class="row col s12">	
				 <div class="input-field col s4 m4 l6">			
				  Le misure di cui alla/e lettera/e *
									<form:input type="text" id="lettereMisura"
										path="lettereMisura" cssClass="validate" required="required"/>
									<form:errors path="lettereMisura" cssClass="error" />
				  </div>
				  <div class="input-field col s4 m4 l6">					
                  devono essere eseguite entro il *
                                   <form:input type='text' id="dataMisuraEntro" path="dataMisuraEntro"
								cssClass="datepicker" required="required"/>
							<form:errors path="dataMisuraEntro" cssClass="error" />
				  </div>
				  <div class="input-field col s4 m4 l12">			      
 					con le modalità di seguito indicate: *
 				                   <form:input type="text" id="modalita" path="modalita" cssClass="validate" required="required" maxlength="300"/>
									<form:errors path="modalita" cssClass="error" />				            																	
				   </div>			            																	
				</div>
				
				<div class="row col s12">
				  <div class="input-field col s4 m4 l12">			      
 					La Misura ufficiale viene disposta in relazione alle seguenti piante, prodotti vegetali, altri oggetti:	*
 				                   <form:input type="text" id="noteMisuraDisp" path="noteMisuraDisp" cssClass="validate" required="required" maxlength="300"/>
									<form:errors path="noteMisuraDisp" cssClass="error" />				            																	
				   </div>			            		
				</div>
			
			
			<!-- INIZIO COLLAPSIBLE AGGIUNGI ORGANISMO NOCIVO, GENERE, SPECIE, NUMERO PIANTE -->
				<div class="row s12">
						<ul class="collapsible" id="collapsible" data-collapsible="accordion">
							<li>
								<div class="collapsible-header" id="datiCampione">
									<i class="material-icons">arrow_drop_down</i>Aggiungi Oganismo nocivo
								</div>
								<div class="collapsible-body">
								  <div class="row col l12" id="divOrgNoc">
								    <div class="input-field col s6 m8 l6">										
										<form:select id="idOrgNocMotivoMisura" name="idOrgNocMotivoMisura" path="idOrgNocMotivoMisura"
												cssClass="validate">
												<form:option value="" label="Selezionare" />
												<form:options items="${listaMotiviMisura}" itemValue="idOrgNocivo"
													itemLabel="descBreve" />
										</form:select>									
										<form:errors path="idOrgNocMotivoMisura" cssClass="error" />
										<label for="labelIdOrgNocMotivoMisura">Motivo della misura (organismo nocivo) *</label>
									</div>					
									<div class="input-field col s6 m4 l6" id="divNote" style="display:none">
										<form:input type="text" id="altroMotivoMisura" 
											data-length="300" maxlength="300" path="altroMotivoMisura"/>
										<form:errors path="altroMotivoMisura" cssClass="error" />
										<label for="altroMotivoMisura">Note *</label>
									</div>
								  </div>
								  <div class="row col l12" id="divSpecie">
								     <div class="input-field col s6 m8 l6">
								       <select id="genereMisura" name="genereMisura" multiple="multiple"
									                class="validate multiselect">
									                	 <option value="" disabled="">Scrivere i generi più rappresentativi</option>
									   </select>
									   <label class="active">Genere *</label>
								     </div>
								     <div class="input-field col s6 m8 l6">
								       <select name="specieMisura" id="specieMisura" multiple="multiple"
									                class="validate multiselect">
									                	 <option value="" disabled="">Selezionare</option>
									   </select>
									   <label>Specie</label>
								     </div>								     
								  </div>
								  <div class="row">									
								    <button class="btn green waves-effect waves-light" style=""
											type="submit" name="aggiungiOrgNocivo" id="aggiungiOrgNocivo">
											AGGIUNGI ORGANISMO NOCIVO
									</button>
								  </div>
								</div>
							</li>
						</ul>
					</div>			
					<!-- FINE collapsible AGGIUNGI  -->
					
					<div class=row">
					   <table id="tabellaSpecie" class="data-table striped display" data-orderable='false' data-paging="false" data-info="false">
									<thead>
										<tr>
											<th></th>
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
												<td nowrap style="white-spaces: nowrap">
												  <input type="hidden" name="idSpecieMisura" value="${specie.idSpecieMisura}" />
										          <input type="hidden" name="denomGenereMisura" value="${specie.denomGenere}" />	
										          <input type="hidden" name="idGenereMisura" value="${specie.idGenere}" />
										          <input type="hidden" name="idOrganismoNocivoMisura" value="${specie.idOrganismoNocivo}" />	
										          <input type="hidden" name="noteMisuraH" value="${specie.note}" />
													<label>											
														<span>
														<a title="Elimina"
										                onclick="javascript:eliminaOrgNocivo(this)"
										                class="btn btn-floating btn-floating-medium deep-orange accent-2"> 
										                <i class="material-icons">delete</i></a>
														</span>
													</label>
												</td>
												<td>${specie.denomOrganismoNocivo}</td>
												<td>${specie.note}</td>
												<td>${specie.denomGenere}</td>
												<td>${specie.denomSpecie}</td>
												<td>
													<div class="input-field">
														<input type="text" name="numeroPianteMisura" value="${specie.numeroPiante}"  class="validate" 
														pattern="\d*"  maxlength="8" 
														oninvalid="this.setCustomValidity('Numero piante non valido')" oninput="this.setCustomValidity('')"/>			       			       	
													</div>													
												</td>												
											</tr>
										</c:forEach>	
									</tbody>																				
									<!-- inizio  - RIGA CHE VIENE CLONATA -->
										          <tr id="rigaTemplateSpecie" style="display:none">
										            <td nowrap style="white-spaces: nowrap">										              										             
										              <input type="hidden" name="idSpecieMisura" value="" disabled="disabled" />
										              <input type="hidden" name="denomGenereMisura" value="" disabled="disabled" />	
										              <input type="hidden" name="idGenereMisura" value="" disabled="disabled"/>
										              <input type="hidden" name="idOrganismoNocivoMisura" value="" disabled="disabled"/>	
										              <input type="hidden" name="noteMisuraH" value="" disabled="disabled"/>
										              <label> 
															<span>
															 <a title="Elimina"
												                onclick="javascript:eliminaOrgNocivo(this)"
												                class="btn btn-floating btn-floating-medium deep-orange accent-2"> 
												                <i class="material-icons">delete</i></a>
												                </span>
														</label>
										            </td>
										            <td></td>
										            <td></td>
										            <td></td>
										            <td></td>
										            <td>
										              <div class="input-field">       			
										       			  <input type="text" name="numeroPianteMisura" value="" disabled="disabled"/>			       			       	
										      		  </div>
										            </td>
										          </tr>
        							<!-- fine - RIGA CHE VIENE CLONATA -->
									
								</table>
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
				  <div class="row col l12">
				    <div class="input-field col s6 m4 l6">
								<form:input type="text" id="cognomeCustode"
									path="cognomeCustode" cssClass="validate"
									required="required" maxlength="50" />
								<form:errors path="cognomeCustode" cssClass="error" />
								<label for="cognomeCustode">Cognome *</label>
					</div>
					<div class="input-field col s6 m4 l6">
								<form:input type="text" id="nomeCustode"
									path="nomeCustode" cssClass="validate"
									required="required" maxlength="50" />
								<form:errors path="nomeCustode" cssClass="error" />
								<label for="nomeCustode">Nome *</label>
					</div>										
				</div>
				<div class="row">
				  <div class="input-field col s6 m4 l3">
						<form:select id="idProvNascitaCustode" path="idProvNascitaCustode"
							cssClass="validate" required="required" disabled="">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaProvinceCustode}" itemValue="idProvincia"
								itemLabel="denomProvincia" />
						</form:select>
						<form:errors path="idProvNascitaCustode" cssClass="error" />
						<label for="idProvNascitaCustode">Provincia Nascita *</label>
					</div>
					<div class="input-field col s6 m4 l3">
						<form:select id="idComNascitaCustode" path="idComNascitaCustode"
							cssClass="validate" required="required" disabled="">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaComuniNascitaCustode}" itemValue="idComune"
								itemLabel="denomComune" />
						</form:select>
						<form:errors path="idComNascitaCustode" cssClass="error" />
						<label for="idComNascitaCustode">Comune Nascita *</label>
					</div>
					<div class="input-field col s6 m4 l6">
						<form:input type='text' id="dataNascitaCustode"
							path="dataNascitaCustode" class="validate"
							required="required" pattern="^(((0[1-9]|[12][0-9]|3[01])[- /.](0[13578]|1[02])|(0[1-9]|[12][0-9]|30)[- /.](0[469]|11)|(0[1-9]|1\d|2[0-8])[- /.]02)[- /.]\d{4}|29[- /.]02[- /.](\d{2}(0[48]|[2468][048]|[13579][26])|([02468][048]|[13579][26])00))$"/>							
						<form:errors path="dataNascitaCustode" cssClass="error" />
						<label for="dataNascitaCustode" class="active">Data nascita (gg/mm/aaaa) *</label>
					</div>
				</div>	
				<div class="row col l12">						
					<div class="input-field col s6 m4 l3">
						<form:select id="idProvResidCustode" path="idProvResidCustode"
							cssClass="validate" required="required" disabled="">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaProvinceCustode}" itemValue="idProvincia"
								itemLabel="denomProvincia" />
						</form:select>
						<form:errors path="idProvResidCustode" cssClass="error" />
						<label for="idProvResidCustode">Provincia Residenza *</label>
					</div>
					<div class="input-field col s6 m4 l3">
						<form:select id="idComResidCustode" path="idComResidCustode"
							cssClass="validate" required="required" disabled="">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaComuniResidenzaCustode}" itemValue="idComune"
								itemLabel="denomComune" />
						</form:select>
						<form:errors path="idComResidCustode" cssClass="error" />
						<label for="idComResidCustode">Comune Residenza *</label>
					</div>
					<div class="input-field col s12 m8 l6">
						<form:input type="text" id="indirResidCustode"
							required="required" path="indirResidCustode" cssClass="validate" maxlength="200" />
						<form:errors path="indirResidCustode" cssClass="error" />
						<label for="indirResidCustode">Indirizzo residenza (Es. Via Roma, 24)*</label>
					</div>
				</div>
				<div class="row col l12">	
					<div class="input-field col s12 m8 l6">
						<form:input type="text" id="tipoDocIdentificazCustode"
							required="required" path="tipoDocIdentificazCustode" cssClass="validate" maxlength="50" />
						<form:errors path="tipoDocIdentificazCustode" cssClass="error" />
						<label for="tipoDocIdentificazCustode">Tipo documento identificazione *</label>
					</div>
					<div class="input-field col s12 m8 l3">
						<form:input type="text" id="numDocIdentificazCustode"
							required="required" path="numDocIdentificazCustode" cssClass="validate" maxlength="40" />
						<form:errors path="numDocIdentificazCustode" cssClass="error" />
						<label for="numDocIdentificazCustode">Num. documento identificazione *</label>
					</div>
					<div class="input-field col s6 m4 l6">
						<form:input type='text' id="dataEmissioneDocumento"
							path="dataEmissioneDocumento" class="validate" required="required" pattern="^(((0[1-9]|[12][0-9]|3[01])[- /.](0[13578]|1[02])|(0[1-9]|[12][0-9]|30)[- /.](0[469]|11)|(0[1-9]|1\d|2[0-8])[- /.]02)[- /.]\d{4}|29[- /.]02[- /.](\d{2}(0[48]|[2468][048]|[13579][26])|([02468][048]|[13579][26])00))$"/>
						<form:errors path="dataEmissioneDocumento" cssClass="error" />
						<label for="dataEmissioneDocumento">Data emissione documento (gg/mm/aaaa) *</label>
					</div>
					<div class="input-field col s12 m8 l6">
						<form:input type="text" id="orgEmissioneDocumento"
							required="required" path="orgEmissioneDocumento" cssClass="validate" maxlength="100" />
						<form:errors path="orgEmissioneDocumento" cssClass="error" />
						<label for="orgEmissioneDocumento">Organismo emissione documento *</label>
					</div>					
				</div>
				<div class="row col l12">
					<div class="input-field col s6">
								<form:select id="idQualificaCustode" path="idQualificaCustode"
									cssClass="validate" required="required">
									<form:option value="" label="Selezionare" />
									<form:options items="${elencoTipiRespAzienda}"
										itemValue="idTipoRespAzienda" itemLabel="descBreve" />
								</form:select>
								<form:errors path="idQualificaCustode" cssClass="error" />
								<label for="idQualificaCustode">Qualifica *</label>
					</div>
				</div>
				<div class="row col l12">	
					<div class="input-field col s12 m8 l12">
								<form:textarea id="prescrizioniCustode" path="prescrizioniCustode"
										cssClass="materialize-textarea validate" rows="2"
										data-length="300" maxlength="300" />
									<form:errors path="prescrizioniCustode" cssClass="error" />
									<label for="prescrizioniCustode">Raccomandazioni/prescrizioni</label>
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
				   <div class="input-field col s6 m4 l4">
							<form:input type='text' id="dataConsegnaDisp" path="dataConsegnaDisp"
								cssClass="datepicker_birthdate" required="required"/>
							<form:errors path="dataConsegnaDisp" cssClass="error"/>
							<label for="dataConsegnaDisp">Data sottoscrizione verbale *</label>
					</div>
				    <div class="input-field col s6 m4 l6">
						  <form:input type="text" id="personaRifVerbale" cssClass="validate" required="required"
								path="personaRifVerbale" maxlength="100"/>
								<form:errors path="personaRifVerbale" cssClass="error" />
								<label for="personaRifVerbale">Persona di riferimento per il verbale *</label>
					</div>
					<div class="input-field col s6 m4 l6">
					     <form:select id="idTipoRespConsegnaDisp" path="idTipoRespConsegnaDisp"
									cssClass="validate" required="required">
									<form:option value="" label="Selezionare" />
									<form:options items="${elencoTipiRespAzienda}"
										itemValue="idTipoRespAzienda" itemLabel="descBreve" />
								</form:select>
								<form:errors path="idTipoRespConsegnaDisp" cssClass="error" />
								<label for="idTipoRespConsegnaDisp">Ruolo della persona di riferimento per il verbale *</label>																 
					</div>
					<div class="input-field col s12 m8 l12">
								<form:textarea id="dichPersRifVerbale" path="dichPersRifVerbale"
										cssClass="materialize-textarea validate" rows="2"
										data-length="300" maxlength="300" />
									<form:errors path="dichPersRifVerbale" cssClass="error" />
									<label for="dichPersRifVerbale">Dichiarazioni</label>
					</div>
				  </div>
			</div> 
			
		 
	</div>				
	<!-- FINE MISURA UFFICIALE DISPOSIZIONE -->			
					

  	<div class="card-panel">
  	  <div class="row">  		  
		  <div class="col l8">
					<label>
						<form:checkbox id="idConstatazionePresente" value="1" path="idConstatazionePresente" />
						<span>Compila Misura ufficiale - Constatazione</span>
					</label>
		  </div>
	  </div>
	</div>  									
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
					    <div class="input-field col s6 m4 l4">
								<form:input type='text' id="dataConstMisura" path="dataConstMisura"
									cssClass="datepicker_birthdate validate" />
								<form:errors path="dataConstMisura" cssClass="error" />
								<label for="dataConstMisura">Data constatazione misura</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="oraConstMisura"
								path="oraConstMisura" cssClass="timepicker validate" />
							<form:errors path="oraConstMisura" cssClass="error" />
							<label for="oraConstMisura">Ora constatazione misura</label>
						</div>
						<div class="input-field col s6 m8 l4">
							<form:input type="text" id="numVerbConstatazMisuraUff" readonly="true"
										path="numVerbConstatazMisuraUff"/>
									<form:errors path="numVerbConstatazMisuraUff" cssClass="error" />
									<label for="numVerbConstatazMisuraUff">N. verbale constatazione misura ufficiale</label>	
						</div>
						<div class="input-field col s6 m4 l12">
							<form:select multiple="multiple" id="nomeIspettoreConstMisura"
								path="idsIspettoreConstMisura" cssClass="validate ">
								<option value="" disabled="">Selezionare</option>
								<form:options items="${elencoIspettoriMisura}" itemValue="id"
									itemLabel="descr" />
							</form:select>
							<form:errors path="idsIspettoreConstMisura" cssClass="error" />
							<label for="idsIspettoreConstMisura" class="active">Ispettore che ha constatato l'esito della misura *</label>
					    </div>
					    <div class="input-field col s12 m8 l12">
							 Esito : *
				         			<div class="row">													
										  <label class="radio-inline">
													<form:radiobutton id="flEsitoMisura"
														cssClass="with-gap validate " path="flEsitoMisura"
														value="S"/>
													<span>Positivo</span>
										  </label>																																							  												
										  <label class="radio-inline">
												<form:radiobutton id="flEsitoMisura"
													cssClass="with-gap validate " path="flEsitoMisura"
													value="N"/>
												<span>Negativo</span>
										  </label>	
										  <label class="radio-inline">
												<form:radiobutton id="flEsitoMisura"
													cssClass="with-gap validate " path="flEsitoMisura"
													value="P"/>
												<span>Parziale</span>
										  </label>																									  													  								
									</div>
							<form:errors path="flEsitoMisura" cssClass="error" />		
					    </div>	
					    <div class="input-field col s12 m8 l12">
								<form:textarea id="noteConstMisura" path="noteConstMisura"
										cssClass="materialize-textarea validate" rows="2"
										data-length="300" maxlength="300" />
									<form:errors path="noteConstMisura" cssClass="error" />
									<label for="noteConstMisura">Note</label>
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
									  <form:input type="text" id="personaPresenteConst"
											path="personaPresenteConst" cssClass="validate "/>
											<form:errors path="personaPresenteConst" cssClass="error" />
											<label for="personaPresenteConst">Persona presente durante la Constatazione della Misura ufficiale *</label>
								</div>
								<div class="input-field col s6">
											<form:select id="idQualificaPersonaPresenteConst" path="idQualificaPersonaPresenteConst"
												cssClass="validate ">
												<form:option value="" label="Selezionare" />
												<form:options items="${elencoTipiRespAzienda}"
													itemValue="idTipoRespAzienda" itemLabel="descBreve" />
											</form:select>
											<form:errors path="idQualificaPersonaPresenteConst" cssClass="error" />
											<label for="idQualificaPersonaPresenteConst">Qualifica *</label>
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
				    <div class="input-field col s6 m4 l4">
								<form:input type='text' id="dataConsegnaConst" path="dataConsegnaConst"
									cssClass="datepicker_birthdate validate " />
								<form:errors path="dataConsegnaConst" cssClass="error" />
								<label for="dataConsegnaConst">Data sottoscrizione verbale constatazione *</label>
					</div>
				    <div class="input-field col s6 m4 l8">
						  <form:input type="text" id="personaRifVerbaleConst"
								path="personaRifVerbaleConst" cssClass="validate " />
								<form:errors path="personaRifVerbaleConst" cssClass="error" />
								<label for="personaRifVerbaleConst">Persona di riferimento per il verbale della Constatazione *</label>
					</div>
					<div class="input-field col s6 m4 l6">					   
					     <form:select id="idTipoRespConsegnaConst" path="idTipoRespConsegnaConst"
									cssClass="validate ">
									<form:option value="" label="Selezionare" />
									<form:options items="${elencoTipiRespAzienda}"
										itemValue="idTipoRespAzienda" itemLabel="descBreve" />
								</form:select>
								<form:errors path="idTipoRespConsegnaConst" cssClass="error" />
								<label for="idTipoRespConsegnaConst">Ruolo della persona di riferimento per il verbale della Constatazione *</label>					     											  
					</div>
					<div class="input-field col s12 m8 l12">
								<form:textarea id="dichPersRifVerbaleConst" path="dichPersRifVerbaleConst"
										cssClass="materialize-textarea validate" rows="2"
										data-length="300" maxlength="300" />
									<form:errors path="dichPersRifVerbaleConst" cssClass="error" />
									<label for="dichPersRifVerbaleConst">Dichiarazioni</label>
					</div>
					<div class="input-field col s12 m8 l12">
								<form:textarea id="notePersRifVerbaleConst" path="notePersRifVerbaleConst"
										cssClass="materialize-textarea validate" rows="2"
										data-length="1000" maxlength="1000" />
									<form:errors path="notePersRifVerbaleConst" cssClass="error" />
									<label for="notePersRifVerbaleConst">Note</label>
					</div>
				  </div>
		    </div>
		    <!-- FINE PERSONA ALLA QUALE VIENE CONSEGNATO IL VERBALE DI CONSTATAZIONE -->		
   	</div>
   </div>
   <!-- FINE MISURA UFFICIALE CONSTATAZIONE -->				
   
  </div>
	</div>
	  <div class="card-action">	
	  	<div class="row">
		    <a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a> 
		  	<div class="right" style="text-align: right"> 
		  		<c:if test="${abilitaStampaDisposizione}">	
			  		<a href='<spring:url value="stampadisposizione/${nuovoControlloForm.idControllo}"/>' 
							title="Stampa verbale ispezione"
							class="btn amber darken-1 waves-effect waves-light"
							style="display: inline-block">STAMPA DISPOSIZIONE
					</a>
				</c:if>	
		  		<c:if test="${abilitaStampaConstatazione}">	
			  		<a href='<spring:url value="stampaconstatazione/${nuovoControlloForm.idControllo}"/>' 
							title="Stampa verbale ispezione"
							class="btn amber darken-1 waves-effect waves-light"
							style="display: inline-block">STAMPA CONSTATAZIONE
					</a>
				</c:if>			  	   
			    
			    <button id="salvaMisuraUfficiale" type="submit" class="btn confirm waves-effect waves-light" style="display: inline-block"> SALVA </button>
			 </div>
		  </div>
	  </div>  
  </form:form>	

<content tag="script">
<script>
	$(document).ready(function() {	
	
		if('${hasErrors}'=='true')
		{
			M.Collapsible.getInstance($("#collapsible")).open()
		}
		
		// Check per visualizzare o meno la parte per la Constatazione
		 $('#idConstatazionePresente').click(function() {
	            if (!$(this).is(':checked')) {
	              console.log('no checked : hide');
		          $("#datiConstatazione").hide();	
		          
		          console.log('Tolgo il required i campi');
		          $("#dataConstMisura").removeAttr("required");
		          $("#oraConstMisura").removeAttr("required");
		          $("#nomeIspettoreConstMisura").removeAttr("required");
		          $("#flEsitoMisura").removeAttr("required");
		          $("#personaPresenteConst").removeAttr("required");
		          $("#idQualificaPersonaPresenteConst").removeAttr("required");
		          $("#dataConsegnaConst").removeAttr("required");
		          $("#personaRifVerbaleConst").removeAttr("required");
		          $("#idTipoRespConsegnaConst").removeAttr("required");
	            }
	            else{
	              console.log('checked : show');
		          $("#datiConstatazione").show();
		          console.log('Mettere a required i campi');
		       	  // Mettere a required i campi
		          $("#dataConstMisura").attr("required", "required");
			      $("#oraConstMisura").attr("required", "required");
			      $("#nomeIspettoreConstMisura").attr("required", "required");
			      $("#flEsitoMisura").attr("required", "required");
			      $("#personaPresenteConst").attr("required", "required");
			      $("#idQualificaPersonaPresenteConst").attr("required", "required");
			      $("#dataConsegnaConst").attr("required", "required");
			      $("#personaRifVerbaleConst").attr("required", "required");
			      $("#idTipoRespConsegnaConst").attr("required", "required");			      
	            }
	        });
	        
	        // In base al check, visualizzo o meno la parte del centro aziendale al caricamento della pagina
	        if($("#idConstatazionePresente").is(':checked')){
	        	$("#datiConstatazione").show();
	        	console.log('Mettere a required i campi');
		       	// Mettere a required i campi
	        	$("#dataConstMisura").attr("required", "required");
		        $("#oraConstMisura").attr("required", "required");
		        $("#nomeIspettoreConstMisura").attr("required", "required");
		        $("#flEsitoMisura").attr("required", "required");
		        $("#personaPresenteConst").attr("required", "required");
		        $("#idQualificaPersonaPresenteConst").attr("required", "required");
		        $("#dataConsegnaConst").attr("required", "required");
		        $("#personaRifVerbaleConst").attr("required", "required");
		        $("#idTipoRespConsegnaConst").attr("required", "required");		        
	        }
	        else{
	        	$("#datiConstatazione").hide();	
	        	console.log('Tolgo il required i campi');
	        	$("#dataConstMisura").removeAttr("required");
		        $("#oraConstMisura").removeAttr("required");
		        $("#nomeIspettoreConstMisura").removeAttr("required");
		        $("#flEsitoMisura").removeAttr("required");
		        $("#personaPresenteConst").removeAttr("required");
		        $("#idQualificaPersonaPresenteConst").removeAttr("required");
		        $("#dataConsegnaConst").removeAttr("required");
		        $("#personaRifVerbaleConst").removeAttr("required");
		        $("#idTipoRespConsegnaConst").removeAttr("required");		        
	        }
		
		
	});


	// --- Popolamento dinamico della select Genere
	$('#idOrgNocMotivoMisura').change(function() {
		var params = {
			"idOrgNocivo" : $(this).val()			
		}
	
		getSelectAsincrona(
			'genereMisura',
			'<spring:url value="/ajax/getGeneriByIdOrgNocivo" />',
			params,
			getOptionGenere, 1);
		
		// Controllo se devo visualizzare o no il campo Note : deve essere visualizzato se viene selezionata la voce 'Altro'
		if($(this).val() != ''){
			var idOrgNocivoSel = $(this).val();
			console.log('idOrgNocivoSel = '+idOrgNocivoSel);
			if(idOrgNocivoSel == 120){ // Altro
			  console.log('visualizzo campo note');
			  $("#divNote").show();					 
			}
			else{
			  console.log('tolgo campo note');
			  $("#divNote").hide();
			  // pulisco il campo
			  $("#altroMotivoMisura").val("");
			}
		}
		
		// Pulisco la combo Specie		
		var numSpecieTrovate = $('select[name=specieMisura] option').length;
		console.log('numSpecieTrovate ='+numSpecieTrovate);
		if(numSpecieTrovate > 1){					
			pulisciSpecie();			
		}			
	});
	
	function pulisciSpecie() {
		console.log('pulisciSpecie');
		$selectSpecie = $('#divSpecie').find('select[name=specieMisura]');
		$selectSpecie[0].options.length = 1;
		$selectSpecie.formSelect();
	}
		
	function getOptionGenere(genere) {
		return getOption(genere.id, genere.descr);
	}
	
	// --- Popolamento dinamico della select Specie
	$('#genereMisura').change(function(e) {				
        var $selectedOptions = $(this).find('option:selected');
        console.log('$selectedOptions ='+$selectedOptions);
        if($selectedOptions.length>0){
        	console.log('Ricerca delle specie, legate ai genere selezionati nella combo')
	        var values = [];
	        $selectedOptions.each(function(){
	        	values.push($(this).val());
	        });
	        console.log('Genere values ='+values);       
	        var params = {
				"idGenereList" : JSON.stringify(values)			
			}
			
			let $self = $(this);
			$selectSpecie = $self.closest('.row').find('select[name=specieMisura]');	    
			    
			getSelectAsincronaRef($selectSpecie,
				'<spring:url value="/ajax/getSpecieByIdGeneri" />',
				params, getOptionSpecie, 1, reloadMultiselect);
        }
	    
	});
	
	function reloadMultiselect($selectRef) {
	  console.log('reloadMultiselect');						
			
	  $selectRef.find('option').each(function() {
		  $option = $(this);
							
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
							
	  $selectRef.formSelect();
	  inizializzaMultiselect($selectRef);
	}
	
	function getOptionSpecie(specie) {
		return getOption(specie.id, specie.descr);
	}
	// Fine gestione popolamento combo specie
	
	
	//Popolamento dinamico della select Comune nascita custode
	$('#idProvNascitaCustode').change(
		function() {
			var params = {
				"idProvincia" : $(this).val()
			}
			getSelectAsincrona(
					'idComNascitaCustode',
					'<spring:url value="/ajax/getComuniProvincia" />',
					params,
					getOptionComune, 1);
	});
	
	//Popolamento dinamico della select Comune residenza custode
	$('#idProvResidCustode').change(
		function() {
			var params = {
				"idProvincia" : $(this).val()
			}
			getSelectAsincrona(
					'idComResidCustode',
					'<spring:url value="/ajax/getComuniProvincia" />',
					params,
					getOptionComune, 1);
	});
	
	function getOptionComune(comune) {
	  return getOption(comune.idComune, comune.denomComune);
	}
	
	// Gestione 'aggiungi organismo nocivo'
	// --- CLICK SUL PULANTE AGGIUNGI ORGANISMO NOCIVO
	$('#aggiungiOrgNocivo').click(function(e) {
		console.log('aggiungi organismo nocivo');
	    e.preventDefault();
	    aggiungiOrganismoNocivo();
		pulisciOrgNocGenereSpecie();
	});
	
	var $rigaTabellaSpecie = null;
	
	var idSpecieSelezionati = null;	
	var idSpecieHidden = null;
	var idGenereMisuraHidden = null;
	
	function aggiungiOrganismoNocivo(){
	  console.log('BEGIN aggiungiOrganismoNocivo');	

		// Per ogni specie selezionata, devo clonare una riga : con genere selezionato e le possibili N specie splittate su N righe
		var idSpecieSel = '';
		var descSpecieSel = '';
		
		
		var idOrganismoNoc = $('#divOrgNoc select[name=idOrgNocMotivoMisura] option:selected').val();
		console.log('idOrganismoNoc ='+idOrganismoNoc);
		var descOrganismoNoc = $('#divOrgNoc select[name=idOrgNocMotivoMisura] option:selected').text()
		console.log('descOrganismoNoc ='+descOrganismoNoc);		
		var note = $('#divNote input[name=altroMotivoMisura]').val();
		console.log('note ='+note);
		
		// Controllo che sia stato selezionato un Organismo Nocivo
		if(idOrganismoNoc == null || idOrganismoNoc == ''){
			swal({
				title : 'Selezionare un Organismo nocivo',
				type : 'warning',
			});
			return;
		}
		// Se l'organismo nocivo selezionato è = 'Altro' (120), controllare che siano state compilate le note
		else if(idOrganismoNoc == 120){ // Altro
			if(note == null || note ==''){
				swal({
					title : 'Compilare le note',
					type : 'warning',
				});
				return;
			}
		}
		
		var numGeneriTrovati = $('select[name=genereMisura] option').length;
		console.log('numero di generi trovati ='+numGeneriTrovati);
		if(numGeneriTrovati > 1) {
			// Controllo che sia stato selezionato almeno un genere
			if ($('#divSpecie select[name=genereMisura] option:selected').val() == null) {
				swal({
					title : 'Selezionare un genere',
					type : 'warning',
				});
				return;
			}
		}
		

		var numSpecieTrovate = $('select[name=specieMisura] option').length;
		console.log('lunghezza select specie lunghezzaSelectSpecie1 = ' + numSpecieTrovate);
		/* se sono presenti specie nella select allora è obbligatorio selezionarne almeno una,
		  altrimenti è stato inserito un genere famiglia che non ha specie associate, quindi devono poter comunque inserire
		*/
		if (numSpecieTrovate > 1) {
			console.log('----------- CASO ci sono specie nella combo');
			// controllare se e' stata selezionata almeno una specie
			if ($('#divSpecie select[name=specieMisura] option:selected').val() == null) {
				swal({
					title : 'Selezionare una specie',
					type : 'warning',
				});
				return;
			}
			
			// CASO di selezione N Generi dove ci possono essere sia generi con specie, che generi senza specie
			// ---Controllare se è stato selezionato un genere che non ha specie
			$('#divSpecie select[name=genereMisura] option:selected')
					.each(
							function() {
								var genereSelezionato = $(this).text();
								var idGenereSelezionato = $(this).val();
								console.log('genereSelezionato ='+genereSelezionato);
								console.log('idGenereSelezionato ='+idGenereSelezionato);
								
								$.ajax({
									url : '<spring:url value="/controlli/isTipoGenereFamiglia_"/>' + genereSelezionato,
									async : false,
									success : function(response) {
										console.log('response isTipoGenereFamiglia_ ='+response);
										if(response == 'S'){
											console.log('----- Aggiungere il genere '+genereSelezionato+' nella tabella senza la specie');
											
											
											descSpecieSel = '';
											console.log('descSpecieSel =' + descSpecieSel);
											var genere = genereSelezionato;	
											console.log('genere ='+genere);			

											console.log('CLONE row');
											var $rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
											$rigaTabellaSpecie.prop('id', '');

											// Numero piante						
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('name', 'numeroPianteMisura');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('class', 'validate');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('maxlength', '8');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('oninvalid','this.setCustomValidity(\'Numero piante non valido\')');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('oninput', 'this.setCustomValidity(\'\')');

											$rigaTabellaSpecie.find('input[name=idSpecieMisura]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=idGenereMisura]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=denomGenereMisura]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMisura]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=noteMisuraH]').removeAttr('disabled');
											
											$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
											$rigaTabellaSpecie.show();

											// valorizzo campi tabella con riga clonata
											$rigaTabellaSpecie.find('td:nth-of-type(2)').text(descOrganismoNoc);
											$rigaTabellaSpecie.find('td:nth-of-type(3)').text(note);
											console.log('********* visualizzo genere ='+genere);
											$rigaTabellaSpecie.find('td:nth-of-type(4)').text(genere);
											$rigaTabellaSpecie.find('td:nth-of-type(5)').text(descSpecieSel);
											
											// valorizzo campi hidden
											$rigaTabellaSpecie.find('input[name=denomGenereMisura]').val(genere);																		
											$rigaTabellaSpecie.find('input[name=idSpecieMisura]').val('');
											
											// valore da salvare sul db
											console.log('-------------------- idGenereMisura da salvare su db ='+idGenereSelezionato);
											$rigaTabellaSpecie.find('input[name=idGenereMisura]').val(idGenereSelezionato);
											console.log('-------------------- note da salvare su db ='+note);
											$rigaTabellaSpecie.find('input[name=noteMisuraH]').val(note);
											
											$rigaTabellaSpecie.find('input[name=denomGenereMisura]').val(genere);
											var idOrgNocSel = $('#divOrgNoc select[name=idOrgNocMotivoMisura] option:selected').val();									
											$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMisura]').val(idOrgNocSel);											

											$table = $rigaTabellaSpecie.closest('table');

											var dataTable = $table.DataTable();
											dataTable.row.add($rigaTabellaSpecie);
											dataTable.draw();
											
										}
									}
								});	// fine chiamata ajax isTipoGenereFamiglia
									
								
					        }
						);// chiusura ciclo sui generi selezionati

			$('#divSpecie select[name=specieMisura] option:selected')
					.each(
							function() {
								var genere = '';
								idSpecieSel = $(this).val();								
								console.log('**** idSpecieSel =' + idSpecieSel);
								console.log('idSpecieHidden =' + idSpecieHidden);
								
								descSpecieSel = $(this).text();
								console.log('**** descSpecieSel =' + descSpecieSel);
								
								// Cerco il genere della specie selezionata
								console.log('Cerco il genere della specie selezionata ='+idSpecieSel);
								//var param = {	"idSpecie" : idSpecieSel	}														
			 
								//$.getJSON('<spring:url value="/ajax/getGenereByIdSpecie" />', param, function(genereReturn)	 {
									var idGenereSel = '';
									var descGenereSel = '';
									$.ajax({
										url : '<spring:url value="/controlli/getGenereByIdSpecie_"/>' + idSpecieSel,
										async : false,
										success : function(response) {
											console.log( "success getGenereByIdSpecie" );
											//idGenereSel = response.id;											
											descGenereSel = response;	
											
											//console.log('**** idGenereSel ='+idGenereSel);		
											console.log('**** descGenereSel ='+descGenereSel);
											
											if (idGenereMisuraHidden) {
												idGenereMisuraHidden += ',' + idGenereSel;
											} 
											else {
												idGenereMisuraHidden = idGenereSel;
											}
											console.log("^^ idGenereMisuraHidden ="+idGenereMisuraHidden);
																				
											genere = descGenereSel;	
											console.log("genere: " + genere);
											
											// controllo se l'idSpecieSelezionata è già in idSpecieHidden: in questo caso non la faccio aggiungere : la specie è già presente nella tabella sotto
											/*if (idSpecieHidden != null && idSpecieSel != null) {
												const idSp = idSpecieHidden.split(',');
												if (idSp != null) {
													console.log('idSp =' + idSp);
													if (idSp.indexOf(idSpecieSel) != -1) {
														console.log('idSpecie gia presente nella pagina!');
														swal({
															title : 'Non &egrave; possibile aggiungere la specie selezionata : specie gi&agrave; presente',
															type : 'warning',
														});
														return;
													}
												}
											}*/
										    
											// per non visualizzare le specie già selezionate nella combo delle specie
											if (idSpecieHidden) {
												idSpecieHidden += ',' + idSpecieSel;
											} else {
												idSpecieHidden = idSpecieSel;
											}
											console.log(' ^^ idSpecieHidden =' + idSpecieHidden);
				
											console.log('CLONE row');
											$rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
											
											$rigaTabellaSpecie.prop('id', '');
											// Numero piante										
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('name', 'numeroPianteMisura');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('class', 'validate');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('pattern', '\\d*');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('maxlength', '8');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('oninvalid','this.setCustomValidity(\'Numero piante non valido\')');
											$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('oninput','this.setCustomValidity(\'\')');

											$rigaTabellaSpecie.find('input[name=idSpecieMisura]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=idGenereMisura]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=denomGenereMisura]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMisura]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=noteMisuraH]').removeAttr('disabled');
											
											$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
											$rigaTabellaSpecie.show();

											// valorizzo campi tabella con riga clonata
											$rigaTabellaSpecie.find('td:nth-of-type(2)').text(descOrganismoNoc);
											$rigaTabellaSpecie.find('td:nth-of-type(3)').text(note);
											console.log('********* visualizzo genere ='+genere);
											$rigaTabellaSpecie.find('td:nth-of-type(4)').text(genere);
											$rigaTabellaSpecie.find('td:nth-of-type(5)').text(descSpecieSel);
											
											// valorizzo campi hidden
											$rigaTabellaSpecie.find('input[name=denomGenereMisura]').val(genere);	
											console.log('---------------- idSpecieMisura da salvare su db ='+idSpecieSel);
											$rigaTabellaSpecie.find('input[name=idSpecieMisura]').val(idSpecieSel);
											$rigaTabellaSpecie.find('input[name=idGenereMisura]').val('');
											$rigaTabellaSpecie.find('input[name=noteMisuraH]').val('');
											
											var idOrgNocSel = $('#divOrgNoc select[name=idOrgNocMotivoMisura] option:selected').val();									
											$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMisura]').val(idOrgNocSel);

											$table = $rigaTabellaSpecie.closest('table');

											var dataTable = $table.DataTable();
											dataTable.row.add($rigaTabellaSpecie);
											dataTable.draw();
											
										/*}).fail(function(jqxhr, textStatus, error) {
											console.log( "fail getGenereByIdSpecie" );	
											if (window.console) {
												console.log( "Errore in reperimento dati genere getGenereByIdSpecie : " + textStatus + ", " + error);
											}         
										});*/
											
											
										}// fine chiamata ajax per ottenere la descrizione del genere
									});
																														
											

								
							});// chiusura ciclo sulle specie	
		}
		// CASO : non ci sono specie nella combo (genere famiglia senza specie)
		else {
			console.log('----------- CASO non ci sono specie nella combo');
			descSpecieSel = '';
			console.log('descSpecieSel =' + descSpecieSel);
			var genere = $('#divSpecie select[name=genereMisura] option:selected').text();
			console.log('genere ='+genere);
			var idGenereSelezionato = $('#divSpecie select[name=genereMisura] option:selected').val();
			console.log('idGenereSelezionato ='+idGenereSelezionato);			
			
			console.log('CLONE row');
			var $rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
			$rigaTabellaSpecie.prop('id', '');

			// Numero piante						
			$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('name', 'numeroPianteMisura');
			$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('class', 'validate');
			$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('pattern', '\\d*');			
			$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('value', '');
			$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('disabled', false);
			$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('maxlength', '8');
			$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('oninvalid','this.setCustomValidity(\'Numero piante non valido\')');
			$rigaTabellaSpecie.find('input[name=numeroPianteMisura]').attr('oninput', 'this.setCustomValidity(\'\')');

			$rigaTabellaSpecie.find('input[name=idSpecieMisura]').removeAttr('disabled');
			$rigaTabellaSpecie.find('input[name=idGenereMisura]').removeAttr('disabled');
			$rigaTabellaSpecie.find('input[name=denomGenereMisura]').removeAttr('disabled');
			$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMisura]').removeAttr('disabled');
			$rigaTabellaSpecie.find('input[name=noteMisuraH]').removeAttr('disabled');
			
			$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
			$rigaTabellaSpecie.show();

			// valorizzo campi tabella con riga clonata
			$rigaTabellaSpecie.find('td:nth-of-type(2)').text(descOrganismoNoc);
			$rigaTabellaSpecie.find('td:nth-of-type(3)').text(note);
			console.log('********* visualizzo genere ='+genere);
			$rigaTabellaSpecie.find('td:nth-of-type(4)').text(genere);
			$rigaTabellaSpecie.find('td:nth-of-type(5)').text(descSpecieSel);
			
			// valorizzo campi hidden
			$rigaTabellaSpecie.find('input[name=denomGenereMisura]').val(genere);																		
			$rigaTabellaSpecie.find('input[name=idSpecieMisura]').val('');
			
			// valore da salvare sul db
			console.log('-------------------- idGenereMisura da salvare su db ='+idGenereSelezionato);
			$rigaTabellaSpecie.find('input[name=idGenereMisura]').val(idGenereSelezionato);
			console.log('-------------------- note da salvare su db ='+note);
			$rigaTabellaSpecie.find('input[name=noteMisuraH]').val(note);
			
			$rigaTabellaSpecie.find('input[name=denomGenereMisura]').val(genere);
			var idOrgNocSel = $('#divOrgNoc select[name=idOrgNocMotivoMisura] option:selected').val();		
			console.log('-------------------- idOrgNocSel da salvare su db ='+idOrgNocSel);
			$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMisura]').val(idOrgNocSel);

			$table = $rigaTabellaSpecie.closest('table');

			var dataTable = $table.DataTable();
			dataTable.row.add($rigaTabellaSpecie);
			dataTable.draw();

		} // FINE CASO : non ci sono specie nella combo
	}
	
	function pulisciOrgNocGenereSpecie(){
		console.log('pulisciOrgNocGenereSpecie');
		
		// Organismo nocivo
		$selectOrgNoc = $('#divOrgNoc').find('select[name=idOrgNocMotivoMisura]');	    
		document.getElementById("idOrgNocMotivoMisura").selectedIndex = 0
		$selectOrgNoc.formSelect();
		
		// Pulisco il campo note e lo rendo non visualizzabile
		$("#divNote").hide();		
		$("#altroMotivoMisura").val("");
		
		// Genere		
		$selectOrgNoc = $('#divSpecie').find('select[name=genereMisura]');
		$selectOrgNoc[0].options.length = 1;
		$selectOrgNoc.formSelect();
		
		// Specie
		pulisciSpecie();						
	}
	
	
	function eliminaOrgNocivo(link) {
		console.log('eliminaOrgNocivo');		

		$riga = $(link).closest('tr');
		denomGenere = $riga.find('td:nth-of-type(2)').text();

		// idSpecie da rimuovere da idSpecieHidden
		idSpecie = $riga.find('input[name=idSpecie]').val();
		console.log('idSpecie da rimuovere =' + idSpecie);

		swal(
				{
					title : 'Attenzione!',
					html : 'Si desidera cancellare la riga selezionata?',
					type : 'warning',
					showCancelButton : true,
					confirmButtonText : 'Sì',
					cancelButtonText : 'No',
				}).then(
				function(result) {
					if (result.value) {
						var dataTable = $riga.closest('table').DataTable();
						dataTable.row($riga).remove().draw();
						$riga.remove();
					}
				});
	}
	
  
</script> 	  
</content>

</body>
</html>
