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
  <spring:url value="/controlli/monitoraggio/nuova/salva" var="formAction" />
  <form:form action="${formAction}" method="post" modelAttribute="nuovoControlloForm" accept-charset="utf-8">    
	<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>NUOVO MONITORAGGIO COFINANZIATO</b></h3>
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
	        				<li class="tab" >
        						<a class="active"><b>Monitoraggio cofinanziato</b></a>
	        				</li>
	        				<c:if test="${nuovoControlloForm.misuraUfficiale != null && nuovoControlloForm.misuraUfficiale =='S' }">
	        					<li class="tab" >
	        						<a href="<spring:url value="/controlli/misura/nuova" />" target="_self">Misura ufficiale</a>
	        					</li>
	        				</c:if>	
	        				<li class="tab" >
	        					<a href="<spring:url value="/controlli/esito/nuova" />" target="_self">Esito</a>
	        				</li>
		        					
      					</ul>
    				</div>
  				</div>
        			
				<div class="card-panel">	
        	    
				<!-- INIZIO COLLAPSIBLE AGGIUNGI ORGANISMO NOCIVO, GENERE, SPECIE, NUMERO PIANTE -->
				<div class="row s12">
						<!-- <ul class="collapsible" id="collapsible" data-collapsible="accordion">
							<li>-->
								<!-- <div class="collapsible-header" id="datiCampione">
									<i class="material-icons">arrow_drop_down</i>Aggiungi Oganismo nocivo
								</div>-->
								<!-- <div class="collapsible-body">-->
								  <div class="row col l12" id="divOrgNoc">
								    <div class="input-field col s6 m8 l6">										
										<form:select id="idOrgNocMotivoMonit" name="idOrgNocMotivoMonit" path="idOrgNocMotivoMonit"
												cssClass="validate">
												<form:option value="" label="Selezionare" />
												<form:options items="${listaMotiviMisura}" itemValue="idOrgNocivo"
													itemLabel="descBreve" />
										</form:select>									
										<form:errors path="idOrgNocMotivoMonit" cssClass="error" />
										<label for="labelidOrgNocMotivoMonit">Organismo nocivo *</label>
									</div>					
									<div class="input-field col s6 m4 l6" id="divNote" style="display:none">
										<form:input type="text" id="altroMotivoMonit" 
											data-length="300" maxlength="300" path="altroMotivoMonit"/>
										<form:errors path="altroMotivoMonit" cssClass="error" />
										<label for="altroMotivoMonit">Note *</label>
									</div>
								  </div>
								  <div class="row col l12" id="divSpecie">
								     <div class="input-field col s6 m8 l6">
								       <select id="genereMonit" name="genereMonit" multiple="multiple"
									                class="validate multiselect">
									                	 <option value="" disabled="">Indicare i generi più rappresentativi</option>
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
								  <div class="row col l12" id="divCoordinate">									
									<div class="input-field col s12 m8 l6">
										<form:input type="text" name="latitudineM"
											id="latitudineM" path="latitudineM" maxlength="20"/>
										<form:errors path="latitudineM" cssClass="error" />
										<label for="latitudineM">Latitudine (coordinate gps)</label>
									</div>
									<div class="input-field col s12 m8 l6">
										<form:input type="text" name="longitudineM"
											id="longitudineM" path="longitudineM" maxlength="20"/>
										<form:errors path="longitudineM" cssClass="error" />
										<label for="longitudineM">Longitudine (coordinate gps)</label>
									</div>
								  </div>
								  <div class="row col l12" id="divNoteMonit">
								  	<div class="input-field col s12 m8 l12">
										<form:textarea id="noteM" path="noteM" name="noteM"
											cssClass="materialize-textarea validate" rows="2"
											data-length="1000" maxlength="1000" />
										<form:errors path="noteM" cssClass="error" />
										<label for="noteM">Note</label>
									</div>	
								  </div>								  
								  <div class="row">									
								    <button class="btn green waves-effect waves-light" style=""
											type="submit" name="aggiungiOrgNocivo" id="aggiungiOrgNocivo">
											AGGIUNGI MONITORAGGIO
									</button>
								  </div>
								<!-- </div>-->
						<!--	</li>
						 </ul>-->
					</div>			
					<!-- FINE collapsible AGGIUNGI  -->
					
					<div class=row">
					   <table id="tabellaSpecie" class="data-table striped display" data-orderable='false' data-paging="false" data-info="false">
									<thead>
										<tr>
											<th></th>
											<th>Organismo nocivo</th>
											<th>Note org. nocivo</th>
											<th>Genere</th>
											<th>Specie</th>
											<th>Numero piante</th>
											<th>Tempo impiegato (dalle ore)</th>
											<th>Tempo impiegato (alle ore)</th>
											<th>Latitudine</th>
											<th>Longitudine</th>
											<th>Note</th>
										</tr>
									</thead>
									<tbody id="bodyTabellaSpecie">	
									  <c:forEach items="${nuovoControlloForm.monitoraggiCofinanziati}" var="monit" varStatus="status">
											<tr>
												<td nowrap style="white-spaces: nowrap">
												  <input type="hidden" name="idSpecieMonit" value="${monit.idSpecie}" />
										          <input type="hidden" name="denomgenereMonit" value="${monit.denomGenere}" />	
										          <input type="hidden" name="idGenereMonit" value="${monit.idGenere}" />
										          <input type="hidden" name="idOrganismoNocivoMonit" value="${monit.idOrganismoNocivo}" />	
										          <input type="hidden" name="noteMonitH" value="${monit.note}" />										          
													<label>											
														<span>
														<a title="Elimina"
										                onclick="javascript:eliminaOrgNocivo(this)"
										                class="btn btn-floating btn-floating-medium deep-orange accent-2"> 
										                <i class="material-icons">delete</i></a>
														</span>
													</label>
												</td>
												<td>${monit.denomOrganismoNocivo}</td>
												<td>${monit.note}</td>
												<td>${monit.denomGenere}</td>
												<td>${monit.denomSpecie}</td>
												<td>
													<div class="input-field">
														<input type="text" name="numeroPianteMonit" value="${monit.numeroPiante}"  class="validate" 
														pattern="\d*"  maxlength="8" 
														oninvalid="this.setCustomValidity('Numero piante non valido')" oninput="this.setCustomValidity('')"/>			       			       	
													</div>													
												</td>
												<td>
													<div class="input-field">
														<input type="text" name="oraInizioMonit" value="${monit.oraInizioMonit}"  class="validate" maxlength="8" 
														oninvalid="this.setCustomValidity('Ora inizio non valida')" oninput="this.setCustomValidity('')"/>			       			       	
													</div>													
												</td>
												<td>
													<div class="input-field">
														<input type="text" name="oraFineMonit" value="${monit.oraFineMonit}"  class="validate"  maxlength="8" 
														oninvalid="this.setCustomValidity('Ora fine non valida')" oninput="this.setCustomValidity('')"/>			       			       	
													</div>													
												</td>	
												<td>
													<div class="input-field">
														<input type="text" name="latitudine" value="${specie.latitudine}"  class="validate"  maxlength="20" 
														oninvalid="this.setCustomValidity('Latitudine non valida')" oninput="this.setCustomValidity('')"/>			       			       	
													</div>													
												</td>
												<td>
													<div class="input-field">
														<input type="text" name="longitudine" value="${monit.longitudine}"  class="validate"  maxlength="20" 
														oninvalid="this.setCustomValidity('Longitudine non valida')" oninput="this.setCustomValidity('')"/>			       			       	
													</div>													
												</td>
												<td>
													<div class="input-field">
														<input type="textarea" name="noteMonit" value="${monit.noteMonit}"  class="validate"  maxlength="1000" 
														 oninput="this.setCustomValidity('')"/>			       			       	
													</div>													
												</td>											
											</tr>
										</c:forEach>	
									</tbody>																				
									<!-- inizio  - RIGA CHE VIENE CLONATA -->
										          <tr id="rigaTemplateSpecie" style="display:none">
										            <td nowrap style="white-spaces: nowrap">										              										             
										              <input type="hidden" name="idSpecieMonit" value="" disabled="disabled" />
										              <input type="hidden" name="denomgenereMonit" value="" disabled="disabled" />	
										              <input type="hidden" name="idGenereMonit" value="" disabled="disabled"/>
										              <input type="hidden" name="idOrganismoNocivoMonit" value="" disabled="disabled"/>	
										              <input type="hidden" name="noteMonitH" value="" disabled="disabled"/>
										              <label> 
															<span>
															 <a title="Elimina"
												                onclick="javascript:eliminaMonitCofinanziato(this)"
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
										       			  <input type="text" name="numeroPianteMonit" value="" disabled="disabled"/>			       			       	
										      		  </div>
										            </td>
										            <td>
										              <div class="input-field">       			
										       			  <input type="text" name="oraInizioMonit" value="" disabled="disabled"/>			       			       	
										      		  </div>
										            </td>
										            <td>
										              <div class="input-field">       			
										       			  <input type="text" name="oraFineMonit" value="" disabled="disabled"/>			       			       	
										      		  </div>
										            </td>
										            <td>
										              <div class="input-field">       			
										       			  <input type="text" name="latitudine" value="" disabled="disabled"/>			       			       	
										      		  </div>
										            </td>
										            <td>
										              <div class="input-field">       			
										       			  <input type="text" name="longitudine" value="" disabled="disabled"/>			       			       	
										      		  </div>
										            </td>
										            <td>
										              <div class="input-field">       			
										       			  <input type="textarea" name="noteMonit" value="" disabled="disabled"/>			       			       	
										      		  </div>
										            </td>
										          </tr>
        							<!-- fine - RIGA CHE VIENE CLONATA -->
									
								</table>
					</div>
				
				
				
				</div>
			</div>
			  <div class="card-action">	
				  <div class="row">
				    <a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a> 
					  <div class="right" style="text-align: right"> 		  	   
					    <button id="salvaMonitCofinanziato" type="submit" class="btn confirm waves-effect waves-light" style="display: inline-block"> SALVA </button>
					 </div>
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
	});


	// --- Popolamento dinamico della select Genere
	$('#idOrgNocMotivoMonit').change(function() {
		var params = {
			"idOrgNocivo" : $(this).val()			
		}
	
		getSelectAsincrona(
			'genereMonit',
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
			  $("#altroMotivoMonit").val("");
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
	$('#genereMonit').change(function(e) {				
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
	var idGenereMonitHidden = null;
	
	function aggiungiOrganismoNocivo(){
	  console.log('BEGIN aggiungiOrganismoNocivo');	

		// Per ogni specie selezionata, devo clonare una riga : con genere selezionato e le possibili N specie splittate su N righe
		var idSpecieSel = '';
		var descSpecieSel = '';
		
		
		var idOrganismoNoc = $('#divOrgNoc select[name=idOrgNocMotivoMonit] option:selected').val();
		console.log('idOrganismoNoc ='+idOrganismoNoc);
		var descOrganismoNoc = $('#divOrgNoc select[name=idOrgNocMotivoMonit] option:selected').text()
		console.log('descOrganismoNoc ='+descOrganismoNoc);		
		var note = $('#divNote input[name=altroMotivoMonit]').val();
		console.log('note ='+note);
		
		var latitudine = $('#divCoordinate input[name=latitudineM]').val();
		console.log('latitudine ='+latitudine);
		var longitudine = $('#divCoordinate input[name=longitudineM]').val();
		console.log('longitudine ='+longitudine);
		var noteMonit = $('#divNoteMonit textarea[name=noteM]').val();
		console.log('noteMonit ='+noteMonit);
		
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
		
		var numGeneriTrovati = $('select[name=genereMonit] option').length;
		console.log('numero di generi trovati ='+numGeneriTrovati);
		if(numGeneriTrovati > 1) {
			// Controllo che sia stato selezionato almeno un genere
			if ($('#divSpecie select[name=genereMonit] option:selected').val() == null) {
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
			$('#divSpecie select[name=genereMonit] option:selected')
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
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('name', 'numeroPianteMonit');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('class', 'validate');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('maxlength', '8');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('oninvalid','this.setCustomValidity(\'Numero piante non valido\')');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
											
											// Tempo impiegato oraInizioMonit
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('name', 'oraInizioMonit');
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('class', 'validate');
											//$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('maxlength', '8');
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('oninvalid','this.setCustomValidity(\'Ora inizio non valida\')');
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
											
											// Tempo impiegato oraFineMonit
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('name', 'oraFineMonit');
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('class', 'validate');
											//$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('maxlength', '8');
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('oninvalid','this.setCustomValidity(\'Ora fine non valida\')');
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
											
											// Latitudine
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('name', 'latitudine');
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('class', 'validate');
											//$rigaTabellaSpecie.find('input[name=latitudine]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('maxlength', '20');
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('oninvalid','this.setCustomValidity(\'Latitudine non valida\')');
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('oninput', 'this.setCustomValidity(\'\')');
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('value', latitudine);
											
											// Longitudine
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('name', 'longitudine');
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('class', 'validate');
											//$rigaTabellaSpecie.find('input[name=longitudine]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('maxlength', '20');
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('oninvalid','this.setCustomValidity(\'Longitudine non valida\')');
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('oninput', 'this.setCustomValidity(\'\')');
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('value', longitudine);
											
											// Note - noteMonit
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('name', 'noteMonit');
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('class', 'validate');
											//$rigaTabellaSpecie.find('input[name=noteMonit]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('maxlength', '1000');
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('oninvalid','this.setCustomValidity(\'Note non valide\')');
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('value', noteMonit);
											

											$rigaTabellaSpecie.find('input[name=idSpecieMonit]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=idGenereMonit]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=denomgenereMonit]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMonit]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=noteMonitH]').removeAttr('disabled');
											
											$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
											$rigaTabellaSpecie.show();

											// valorizzo campi tabella con riga clonata
											$rigaTabellaSpecie.find('td:nth-of-type(2)').text(descOrganismoNoc);
											$rigaTabellaSpecie.find('td:nth-of-type(3)').text(note);
											console.log('********* visualizzo genere ='+genere);
											$rigaTabellaSpecie.find('td:nth-of-type(4)').text(genere);
											$rigaTabellaSpecie.find('td:nth-of-type(5)').text(descSpecieSel);
											
											// valorizzo campi hidden
											$rigaTabellaSpecie.find('input[name=denomgenereMonit]').val(genere);																		
											$rigaTabellaSpecie.find('input[name=idSpecieMonit]').val('');
											
											// valore da salvare sul db
											console.log('-------------------- idGenereMonit da salvare su db ='+idGenereSelezionato);
											$rigaTabellaSpecie.find('input[name=idGenereMonit]').val(idGenereSelezionato);
											console.log('-------------------- note da salvare su db ='+note);
											$rigaTabellaSpecie.find('input[name=noteMonitH]').val(note);
											
											$rigaTabellaSpecie.find('input[name=denomgenereMonit]').val(genere);
											var idOrgNocSel = $('#divOrgNoc select[name=idOrgNocMotivoMonit] option:selected').val();									
											$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMonit]').val(idOrgNocSel);											

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
											
											if (idGenereMonitHidden) {
												idGenereMonitHidden += ',' + idGenereSel;
											} 
											else {
												idGenereMonitHidden = idGenereSel;
											}
											console.log("^^ idGenereMonitHidden ="+idGenereMonitHidden);
																				
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
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('name', 'numeroPianteMonit');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('class', 'validate');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('pattern', '\\d*');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('maxlength', '8');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('oninvalid','this.setCustomValidity(\'Numero piante non valido\')');
											$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('oninput','this.setCustomValidity(\'\')');
											
											// Tempo impiegato oraInizioMonit
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('name', 'oraInizioMonit');
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('class', 'validate');
											//$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('maxlength', '8');
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('oninvalid','this.setCustomValidity(\'Ora inizio non valida\')');
											$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
											
											// Tempo impiegato oraFineMonit
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('name', 'oraFineMonit');
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('class', 'validate');
											//$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('maxlength', '8');
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('oninvalid','this.setCustomValidity(\'Ora fine non valida\')');
											$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
											
											// Latitudine
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('name', 'latitudine');
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('class', 'validate');
											//$rigaTabellaSpecie.find('input[name=latitudine]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('maxlength', '20');
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('oninvalid','this.setCustomValidity(\'Latitudine non valida\')');
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('oninput', 'this.setCustomValidity(\'\')');
											$rigaTabellaSpecie.find('input[name=latitudine]').attr('value', latitudine);
											
											// Longitudine
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('name', 'longitudine');
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('class', 'validate');
											//$rigaTabellaSpecie.find('input[name=longitudine]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('maxlength', '20');
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('oninvalid','this.setCustomValidity(\'Longitudine non valida\')');
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('oninput', 'this.setCustomValidity(\'\')');
											$rigaTabellaSpecie.find('input[name=longitudine]').attr('value', longitudine);
											
											// Note - noteMonit
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('name', 'noteMonit');
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('class', 'validate');
											//$rigaTabellaSpecie.find('input[name=noteMonit]').attr('pattern', '\\d*');			
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('value', '');
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('disabled', false);
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('maxlength', '1000');
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('oninvalid','this.setCustomValidity(\'Note non valide\')');
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
											$rigaTabellaSpecie.find('input[name=noteMonit]').attr('value', noteMonit);
											

											$rigaTabellaSpecie.find('input[name=idSpecieMonit]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=idGenereMonit]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=denomgenereMonit]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMonit]').removeAttr('disabled');
											$rigaTabellaSpecie.find('input[name=noteMonitH]').removeAttr('disabled');
											
											$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
											$rigaTabellaSpecie.show();

											// valorizzo campi tabella con riga clonata
											$rigaTabellaSpecie.find('td:nth-of-type(2)').text(descOrganismoNoc);
											$rigaTabellaSpecie.find('td:nth-of-type(3)').text(note);
											console.log('********* visualizzo genere ='+genere);
											$rigaTabellaSpecie.find('td:nth-of-type(4)').text(genere);
											$rigaTabellaSpecie.find('td:nth-of-type(5)').text(descSpecieSel);
											
											// valorizzo campi hidden
											$rigaTabellaSpecie.find('input[name=denomgenereMonit]').val(genere);	
											console.log('---------------- idSpecieMonit da salvare su db ='+idSpecieSel);
											$rigaTabellaSpecie.find('input[name=idSpecieMonit]').val(idSpecieSel);
											$rigaTabellaSpecie.find('input[name=idGenereMonit]').val('');
											$rigaTabellaSpecie.find('input[name=noteMonitH]').val('');
											
											var idOrgNocSel = $('#divOrgNoc select[name=idOrgNocMotivoMonit] option:selected').val();									
											$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMonit]').val(idOrgNocSel);

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
			var genere = $('#divSpecie select[name=genereMonit] option:selected').text();
			console.log('genere ='+genere);
			var idGenereSelezionato = $('#divSpecie select[name=genereMonit] option:selected').val();
			console.log('idGenereSelezionato ='+idGenereSelezionato);			
			
			console.log('CLONE row');
			var $rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
			$rigaTabellaSpecie.prop('id', '');

			// Numero piante						
			$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('name', 'numeroPianteMonit');
			$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('class', 'validate');
			$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('pattern', '\\d*');			
			$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('value', '');
			$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('disabled', false);
			$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('maxlength', '8');
			$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('oninvalid','this.setCustomValidity(\'Numero piante non valido\')');
			$rigaTabellaSpecie.find('input[name=numeroPianteMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
			
			// Tempo impiegato oraInizioMonit
			$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('name', 'oraInizioMonit');
			$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('class', 'validate');
			//$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('pattern', '\\d*');			
			$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('value', '');
			$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('disabled', false);
			$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('maxlength', '8');
			$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('oninvalid','this.setCustomValidity(\'Ora inizio non valida\')');
			$rigaTabellaSpecie.find('input[name=oraInizioMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
			
			// Tempo impiegato oraFineMonit
			$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('name', 'oraFineMonit');
			$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('class', 'validate');
			//$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('pattern', '\\d*');			
			$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('value', '');
			$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('disabled', false);
			$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('maxlength', '8');
			$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('oninvalid','this.setCustomValidity(\'Ora fine non valida\')');
			$rigaTabellaSpecie.find('input[name=oraFineMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
			
			// Latitudine
			$rigaTabellaSpecie.find('input[name=latitudine]').attr('name', 'latitudine');
			$rigaTabellaSpecie.find('input[name=latitudine]').attr('class', 'validate');
			//$rigaTabellaSpecie.find('input[name=latitudine]').attr('pattern', '\\d*');			
			$rigaTabellaSpecie.find('input[name=latitudine]').attr('value', '');
			$rigaTabellaSpecie.find('input[name=latitudine]').attr('disabled', false);
			$rigaTabellaSpecie.find('input[name=latitudine]').attr('maxlength', '20');
			$rigaTabellaSpecie.find('input[name=latitudine]').attr('oninvalid','this.setCustomValidity(\'Latitudine non valida\')');
			$rigaTabellaSpecie.find('input[name=latitudine]').attr('oninput', 'this.setCustomValidity(\'\')');
			$rigaTabellaSpecie.find('input[name=latitudine]').attr('value', latitudine);
			
			// Longitudine
			$rigaTabellaSpecie.find('input[name=longitudine]').attr('name', 'longitudine');
			$rigaTabellaSpecie.find('input[name=longitudine]').attr('class', 'validate');
			//$rigaTabellaSpecie.find('input[name=longitudine]').attr('pattern', '\\d*');			
			$rigaTabellaSpecie.find('input[name=longitudine]').attr('value', '');
			$rigaTabellaSpecie.find('input[name=longitudine]').attr('disabled', false);
			$rigaTabellaSpecie.find('input[name=longitudine]').attr('maxlength', '20');
			$rigaTabellaSpecie.find('input[name=longitudine]').attr('oninvalid','this.setCustomValidity(\'Longitudine non valida\')');
			$rigaTabellaSpecie.find('input[name=longitudine]').attr('oninput', 'this.setCustomValidity(\'\')');
			$rigaTabellaSpecie.find('input[name=longitudine]').attr('value', longitudine);
			
			// Note - noteMonit
			$rigaTabellaSpecie.find('input[name=noteMonit]').attr('name', 'noteMonit');
			$rigaTabellaSpecie.find('input[name=noteMonit]').attr('class', 'validate');
			//$rigaTabellaSpecie.find('input[name=noteMonit]').attr('pattern', '\\d*');			
			$rigaTabellaSpecie.find('input[name=noteMonit]').attr('value', '');
			$rigaTabellaSpecie.find('input[name=noteMonit]').attr('disabled', false);
			$rigaTabellaSpecie.find('input[name=noteMonit]').attr('maxlength', '1000');
			$rigaTabellaSpecie.find('input[name=noteMonit]').attr('oninvalid','this.setCustomValidity(\'Note non valide\')');
			$rigaTabellaSpecie.find('input[name=noteMonit]').attr('oninput', 'this.setCustomValidity(\'\')');
			$rigaTabellaSpecie.find('input[name=noteMonit]').attr('value', noteMonit);
			

			$rigaTabellaSpecie.find('input[name=idSpecieMonit]').removeAttr('disabled');
			$rigaTabellaSpecie.find('input[name=idGenereMonit]').removeAttr('disabled');
			$rigaTabellaSpecie.find('input[name=denomgenereMonit]').removeAttr('disabled');
			$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMonit]').removeAttr('disabled');
			$rigaTabellaSpecie.find('input[name=noteMonitH]').removeAttr('disabled');
			
			$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
			$rigaTabellaSpecie.show();

			// valorizzo campi tabella con riga clonata
			$rigaTabellaSpecie.find('td:nth-of-type(2)').text(descOrganismoNoc);
			$rigaTabellaSpecie.find('td:nth-of-type(3)').text(note);
			console.log('********* visualizzo genere ='+genere);
			$rigaTabellaSpecie.find('td:nth-of-type(4)').text(genere);
			$rigaTabellaSpecie.find('td:nth-of-type(5)').text(descSpecieSel);
			
			// valorizzo campi hidden
			$rigaTabellaSpecie.find('input[name=denomgenereMonit]').val(genere);																		
			$rigaTabellaSpecie.find('input[name=idSpecieMonit]').val('');
			
			// valore da salvare sul db
			console.log('-------------------- idGenereMonit da salvare su db ='+idGenereSelezionato);
			$rigaTabellaSpecie.find('input[name=idGenereMonit]').val(idGenereSelezionato);
			console.log('-------------------- note da salvare su db ='+note);
			$rigaTabellaSpecie.find('input[name=noteMonitH]').val(note);
			
			$rigaTabellaSpecie.find('input[name=denomgenereMonit]').val(genere);
			var idOrgNocSel = $('#divOrgNoc select[name=idOrgNocMotivoMonit] option:selected').val();		
			console.log('-------------------- idOrgNocSel da salvare su db ='+idOrgNocSel);
			$rigaTabellaSpecie.find('input[name=idOrganismoNocivoMonit]').val(idOrgNocSel);

			$table = $rigaTabellaSpecie.closest('table');

			var dataTable = $table.DataTable();
			dataTable.row.add($rigaTabellaSpecie);
			dataTable.draw();

		} // FINE CASO : non ci sono specie nella combo
	}
	
	function pulisciOrgNocGenereSpecie(){
		console.log('pulisciOrgNocGenereSpecie');
		
		// Organismo nocivo
		$selectOrgNoc = $('#divOrgNoc').find('select[name=idOrgNocMotivoMonit]');	    
		document.getElementById("idOrgNocMotivoMonit").selectedIndex = 0
		$selectOrgNoc.formSelect();
		
		// Pulisco il campo note e lo rendo non visualizzabile
		$("#divNote").hide();		
		$("#altroMotivoMonit").val("");
		
		// Genere		
		$selectOrgNoc = $('#divSpecie').find('select[name=genereMonit]');
		$selectOrgNoc[0].options.length = 1;
		$selectOrgNoc.formSelect();
		
		// Specie
		pulisciSpecie();
		
		// Latitdine
		$("#latitudineM").val("");
		
		// Longitudine
		$("#longitudineM").val("");
		
		// Notemonit
		$("#noteM").val("");
	}
	
		
	function eliminaMonitCofinanziato(link) {
		console.log('eliminaMonitCofinanziato');		

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