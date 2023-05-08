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
	<spring:url value="/autorizzazioni/comunicazioni/impAuto/nuova" var="formAction" />
	<spring:url value="/autorizzazioni/comunicazioni/impAuto/nuova" var="autorizzAction" />
	<form:form action="${formAction}" id="form" method="post" modelAttribute="nuovaDomandaForm" accept-charset="utf-8">	   
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
      					<ul class="tabs">
      						<li class="tab" >
      							<a href="<spring:url value="/autorizzazioni/comunicazioni/anagrafica/nuova" />" target="_self">Anagrafica</a>
      						</li>
        					<li class="tab" >
        						<a href="<spring:url value="/autorizzazioni/comunicazioni/azienda/nuova" />" target="_self">Dati operatore</a>
        					</li>
        					<spring:eval var="idTipoDomandaRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP" />
							<spring:eval var="idTipoDomandaVariazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP" />
        					<spring:eval var="idTipoDomandaCancellazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP" />
        					<spring:eval var="idTipoDomandaPassaporto" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO" />
        					<!-- in caso di Cancellazione non mostro i seguenti tab -->
        					<c:if test="${idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
        					  <c:if test="${ idTipoDomandaVariazioneRUOP == nuovaDomandaForm.idTipoComunicazione || idTipoDomandaRUOP == nuovaDomandaForm.idTipoComunicazione}">
								  <c:forEach var="ceAz" items="${nuovaDomandaForm.listaCentriAz}">
			        				  <li class="tab" >
			        					<a href="<spring:url value="/autorizzazioni/comunicazioni/centroaziendale/nuova/${ceAz.idCentroAziendale}" />" target="_self">${ceAz.codCentroAziendale}</a>
			        				  </li>
		        				  </c:forEach>
		        				</c:if>	  	        				  
		        				  <li class="tab" >
		        						<a class="active"><b>Import</b></a>
		        				  </li>	        					  
	        					  <c:if test="${nuovaDomandaForm.tabExport}">
		        					<li class="tab" >
		        						<a href="<spring:url value="/autorizzazioni/comunicazioni/expAuto/nuova" />" target="_self">Export</a>
		        					</li>	        					
	        					  </c:if>	  
	        					  <c:if test="${nuovaDomandaForm.tabPassaporto}">   					
									  <li class="tab" >
				        			    <a href="<spring:url value="/autorizzazioni/comunicazioni/passaporto/nuova" />" target="_self">Passaporto</a>
				        			  </li>		        				
			        			  </c:if>
		        			</c:if>
		        			<!-- -->			        	    
		        			<li class="tab" >
	        					<a href="<spring:url value="/autorizzazioni/comunicazioni/allegati/nuova" />" target="_self">Allegati</a>
	        				</li>	        				   					        						
      					</ul>
    				</div>
  				</div>
  					<jsp:include page="../includes/boxheader.jsp"></jsp:include>
					<!-- Tipologie import -->
					<div class="card-panel" id="tipologieProdDiv">					
						<p class="center-align" style="font-size:20px;margin-bottom:5px;">
						  <em>Le importazioni riguardano le seguenti tipologie di merci/produzioni</em>
						</p>										
					    <div class="row">
					       <div class="input-field col l12" id="divTipologiaProdutt">
								<div class="input-field col s6 m4 l12">
									<form:select id="idTipologiaProdImport" name="idTipologiaProdImport" path="idTipologiaProdImport" cssClass="campiPass">
										<form:option value="" label="Selezionare" />
										<form:options items="${listaTipologieImport}" itemValue="idVoce"
											itemLabel="descEstesa" />
									</form:select>
									<form:errors path="idTipologiaProdImport" cssClass="error" />
									<label for="labelIdTipologiaProdImport">Tipologia produttiva *</label>
								</div>
							</div>
							<!-- Campo note -->
					        <div id="divNoteTipologia" class="row col l12">				  				 						  				 					  				  		
						       <div class="input-field col s12 m8 l12">
								  <form:textarea id="noteTipologiaImport" path="noteTipologiaImport" 
									  cssClass="materialize-textarea validate tooltipped" rows="2"
									  data-length="500" maxlength="500" data-position="right" 
									  data-tooltip="Note"/>
								  <form:errors path="noteTipologiaImport" cssClass="error" />
								  <label for="noteTipologiaImport">Note</label>
							   </div>
							</div>
	            			<!-- FINE Campo note -->
							<div class="row col l12" id="divSpecieTpProd">
						            <div id="divGenereTpProd" class="input-field col s12 m5">
						              <input type="text" name="genereImport" placeholder="Scrivere generi più rappresentativi"  
						                class="validate autocomplete" autocomplete="off" />
						              <label class="active">Genere *</label>
						            </div>            
						            <div class="input-field col s12 m5">
						              <select name="specieImport" multiple="multiple"
						                class="validate multiselect">
						                <option value="" disabled="">SELEZIONARE</option>
						              </select>
						              <label>Specie *</label>
						            </div>					           
						  </div>							
					    </div>	
				    			    				    
 	            	<div class="input-field col s12 m2 l12">
		              <button id="aggiungiSpecieImport" name="aggiungiSpecieImport" type="submit" 
		              	class="btn green waves-effect waves-light" type="button"  style="button"> AGGIUNGI TIPOLOGIA PRODUTTIVA </button>
	            	</div>
	            	
	            	<div class="row">
				        <table id="tabellaSpecie" class="data-table striped display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">
				          <thead>
				            <tr>
				              <th></th>
				              <th>Tipologia produttiva</th>
				              <th>Note</th>
				              <th>Genere</th>
				              <th>Specie</th>				              
				            </tr>
				          </thead>
				          <tbody id="bodyTabellaSpecie">
				            <c:forEach var="tipologia" items="${tipologieProdImportDb}">
				            <c:choose>
				            	<c:when test="${not empty tipologia.specieList}">
					              <c:forEach var="specie" items="${tipologia.specieList}">
									   <tr>
										<td nowrap style="white-spaces: nowrap">
											<sec:authorize access="hasRole('WRITE') ">		
											<c:choose>	
												<c:when test="${specie.idSpecie != null}">																													
													<a href="${autorizzAction}/eliminaTipologiaProd/idTipologia/${tipologia.idTipologia}/idSpecie/${specie.idSpecie}" 
													    title="Elimina" 
													    class="btn btn-floating btn-floating-medium deep-orange accent-2"
													    onclick="return eliminaTipologiaProd(this)">
															<i class="material-icons">delete</i>
												  	</a>
											     </c:when>	
												  <c:otherwise>
													  <a href="${autorizzAction}/eliminaTipologiaProd/idTipologia/${tipologia.idTipologia}" 
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
										<td>${tipologia.note}</td>							
										<td>${specie.denomGenere}</td>
										<td>${specie.denomSpecie}</td>									
								      </tr>
								     </c:forEach> 
							     </c:when>	
							     <c:otherwise>
							    	<tr>
									  	<td nowrap style="white-spaces: nowrap">
											<sec:authorize access="hasRole('WRITE') ">	
												<a href="${autorizzAction}/eliminaTipologiaProd/idTipologia/${tipologia.idTipologia}" 
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
					
				<!-- FINE Tipologie import --> 	
	            	  				  
	            <!-- Zone protette -->	            		
	            <div class="row">
  				  	<div class="card-panel">
  				  	  <div class="row">
  				  	    <div class="input-field col s6 m4 l12">
								<form:input type="text" id="impDatoAggiuntivo" path="impDatoAggiuntivo" cssClass="validate" maxlength="100" />
								<form:errors path="impDatoAggiuntivo" cssClass="error" />
								<label for="labelimpDatoAggiuntivo">Informazioni aggiuntive</label>
						</div>						
					 </div> 
  				  		<div class="row">			            				            	
			            	<div class="row" id="bloccoVociRadio">
									<c:forEach var="voceRadio"
										items="${vociRadioZoneProtette}">
										<div class=" col l8">
											<label> <form:radiobutton path="idVoceRadioZonaProtetta"
													value="${voceRadio.idVoce}" /> <span>${voceRadio.descEstesa}</span>
											</label>
										</div>
									</c:forEach>
									<form:errors path="idVoceRadioZonaProtetta" cssClass="error" />
							</div>
  						</div>
				  	</div>
  				</div>
	            	<!-- FINE Zone protette -->
	            	
	              <!-- Stati import -->	          
  				  <div class="card-panel">
  				    <p class="center-align" style="font-size:20px;margin-bottom:5px;">
						  <em>Le importazioni avvengono principalmente da :</em>
					</p>
					<div class="row">
						<c:forEach var="continente" items="${listaContinenti}" >		  				  	
			            		<div class="col s12 m2 l4">		            						            	
												<div class="input-field col s12 m8 l2">
													<label> <form:checkbox id="idVoceCheckContinenti" value="${continente.idVoce}" 
												       path="idVoceCheckContinenti" /> <span>${continente.descEstesa}</span></label>												
												</div>																		
								</div>						
						</c:forEach>
						<form:errors path="idVoceCheckContinenti" cssClass="error" />
					</div>
					<div class="row">
						<div class="input-field col s12 m8 l12">
												  <form:input type="text" id="statoOrigine" name="statoOrigine" path="statoOrigine" maxlength="100" />
												  <form:errors path="statoOrigine" cssClass="error" />
												  <label for="statoOrigine">Eventuali stati di origine</label>
						</div>
					</div>					
				  </div>
	            <!-- FINE Stati import -->
	            	
	            <!-- Campo note -->
	            <div class="row">
  				 	<div class="card-panel">
	  				 	<p class="center-align" style="font-size:20px;margin-bottom:5px;">
							  <em>Informazioni relative ai tipi di merci di base cui appartengono piante e i prodotti vegetali che l’Operatore professionale intende importare in EU
							     (specificare generi/specie; in caso di molti generi/specie si deve fornire una lista delle piante importate dall'Operatore Professionale)  
							  </em>
						</p>
  				  		<div class="row">	            			 
		            	  <div class="input-field col s12 m8 l12">
								<form:textarea id="noteImport" path="noteImport" 
								  cssClass="materialize-textarea validate tooltipped" rows="2"
									data-length="500" maxlength="500" data-position="right" 
									data-tooltip="Note"/>
								<form:errors path="noteImport" cssClass="error" />
						 	<label for="note">Note</label>
						 </div>					
						</div>
					</div>
				</div>
	            <!-- FINE Campo note -->
	            								
				</div>
						
			</div>
			
		
       	<div class="row">
			<a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>			
			<button class="btn confirm waves-effect waves-light right" type="submit" name="datiImport">SALVA</button>
	   	</div>
	   	</div>	   
	</form:form>		   		
	<br />
	
	
	
	<content tag="script"> 
		<script type="text/javascript" src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script> 
		<script>
				
		
		// Al caricamento della pagina
		$(document).ready(function() {	
			
			// al caricamento della pagina abilito le label per mostrare gli eventuali errori
	        console.log('Abilito le label');	        	        
	        $("label[for='labelIdTipologiaProdImport']").addClass("active");
	        $("label[for='labelimpDatoAggiuntivo']").addClass("active");
	        $("label[for='statoOrigine']").addClass("active");
	        $("label[for='note']").addClass("active");
			
			// Reperimento dei generi e popolazione Specie		
	        $('input[name=genereImport]').each(function() {
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
			
	        
		});
			
			
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
			$selectSpecie = $inputGenere.closest('.row').find('select[name=specieImport]');			
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

	</script> 
</content>

</body>
</html>