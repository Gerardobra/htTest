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
	<spring:url value="/controlli/campioni/nuova" var="formAction" />	
	<form:form action="${formAction}" method="post"
		modelAttribute="nuovoControlloForm" accept-charset="utf-8">
		<div class="card">
			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding">
					<b>CAMPIONI</b>
				</h3>
			</div>
			<div class="card-content" style="padding-top: 5px;">
				<div class="row">
					<div class="col s12">
						<ul class="tabs">
							<li class="tab"><a
								href="<spring:url value="/controlli/datiBase/nuova" />"
								target="_self">Dati generali</a></li>
							<c:if test="${nuovoControlloForm.tabDocumentale}">	
								<li class="tab"><a
									href="<spring:url value="/controlli/controllodocumentale/nuova" />"
									target="_self">Documentale</a>
								</li>
							</c:if>
							<c:if test="${nuovoControlloForm.tabIdentita}">	
								<li class="tab">
							  		<a href="<spring:url value="/controlli/controlloidentita/nuova" />" target="_self">Identità</a>
								</li>
							</c:if>
							<c:if test="${nuovoControlloForm.tabFisico}">	
								<li class="tab">
							  		<a
										href="<spring:url value="/controlli/controllofisico/nuova" />"
										target="_self">Fisico</a>
								</li>
							</c:if>	
							<li class="tab">														 		        			  
		        				<a class="active"><b>Campioni</b></a>		        			  								
							</li>
							<li class="tab"><a
								href="<spring:url value="/controlli/allegati/nuova" />"
								target="_self">Allegati</a>
							</li>
							<c:if test="${false}">
		        				<li class="tab" >
		        					<a href="<spring:url value="/controlli/monitoraggio/modifica" />" target="_self">Monitoraggio cofinanziato</a>
		        				</li>
	        				</c:if>	 
	        				<c:if test="${nuovoControlloForm.misuraUfficiale != null && nuovoControlloForm.misuraUfficiale =='S' }">
	        					<li class="tab" >
        					  		<a href="<spring:url value="/controlli/misura/nuova" />" target="_self">Misura ufficiale</a>
        				    	</li>
        				    </c:if>				        				        					        				        			
							<li class="tab"><a
								href="<spring:url value="/controlli/esito/nuova" />"
								target="_self">Esito</a>
							</li>

						</ul>
					</div>
				</div>


				<div class="card-panel">
					<div class="row">
						<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Campioni</em>
						</p>
					</div>
			
					<!-- Campione -->
					<div class="row s12">
						<ul class="collapsible" id="collapsible" data-collapsible="accordion">
							<li>
								<div class="collapsible-header" id="datiCampione">
									<i class="material-icons">arrow_drop_down</i>Aggiungi Campioni
								</div>
								<div class="collapsible-body">
								  <div class="row col l12">
									<div class="input-field col s6 m4 l6">
										<input type="text" id="genereCampione" name="genereCampione"
											placeholder="Scrivere generi più rappresentativi" data-error="#errorTxt3"
											class="autocomplete" autocomplete="off" > 
											<label id="labelGenere" class="active" for="genereCampione" cssClass="validate">Genere</label>
										<div id="errorTxt3" style="height: 0px"></div>
										<input type="hidden" id="idGenereCampione" name="idGenereCampione" data-error="#errorTxt3" />
									</div>
									<div class="input-field col s6 m4 l6">
										<select id="specieCampione" name="specieCampione"
											data-error="#errorTxt4">
											<option value="">SELEZIONARE</option>											
										</select> <label for="specieCampione">Specie</label>
										<div id="errorTxt4" style="height: 0px"></div>
									</div>
								  </div>
								  <div class="row col l12">	
									<div class="input-field col s6 m8 l4">
										<form:input type="text" name="codCampioneDal"
											id="codCampioneDal" path="codCampioneDal"
											maxlength="20"  cssClass="validate" />
										<form:errors path="codCampioneDal" cssClass="error" />
										<label for="labelcodCampioneDal" class="active">Codice campione dal *</label>
									</div>
									<div class="input-field col s6 m8 l4">
										<form:input type="text" name="codCampioneAl"
											id="codCampioneAl" path="codCampioneAl"
											maxlength="20" />
										<form:errors path="codCampioneAl" cssClass="error" />
										<label for="labelcodCampioneAl" class="active">Codice campione al</label>
									</div>
									<div class="input-field col s6 m8 l4">
										<form:select id="tipoCampione" name="tipoCampione"
											path="tipoCampione"  class="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaTipiCampione}"
												itemValue="cod" itemLabel="descr" />
										</form:select>
										<form:errors path="tipoCampione" cssClass="error" />
										<label for="labeltipoCampione" class="active">Tipo campione *</label>
									</div>
								  </div>
								  <div class="row col l12">
									<div class="input-field col s6 m8 l4">
										<form:select id="orgNocDaRicercare" name="orgNocDaRicercare" multiple="multiple"
											path="orgNocDaRicercare"  class="validate multiselect">	
											<option value="" disabled="">Selezionare</option>										
											<form:options items="${listaOrgNocDaRicercare}"
												itemValue="idOrgNocivo" itemLabel="descBreve" />
										</form:select>
										<form:errors path="orgNocDaRicercare" cssClass="error" />
										<label for="labelorgNocDaRicercare" class="active">Organismo nocivo da ricercare *</label>
									</div>
									<div class="input-field col s6 m8 l4">
										<form:select id="tipologiaCampione" name="tipologiaCampione"
											path="tipologiaCampione"  class="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaTipologieCampione}"
												itemValue="idTipologiaCampione" itemLabel="descEstesaIt" />
										</form:select>
										<form:errors path="tipologiaCampione" cssClass="error" />
										<label for="labeltipologiaCampione" class="active">Matrice campionata *</label>
									</div>
									<div class="input-field col s12 m8 l4">
									  Cofinanzionato * :
				         						<div class="row">													
													  <label class="radio-inline">
																<form:radiobutton id="flCofinanziato"
																	cssClass="with-gap" path="flCofinanziato" 
																	value="S"/>
																<span>Si</span>
													  </label>																																							  												
													  <label class="radio-inline">
															<form:radiobutton id="flCofinanziato"
																cssClass="with-gap" path="flCofinanziato" 
																value="N"/>
															<span>No</span>
														</label>																										  													  								
												</div>
										<form:errors path="flCofinanziato" cssClass="error" />		
									</div>
								  </div>
								  <div class="row col l12">
									<div class="input-field col s12 m8 l4">
										<form:input type="text" name="tempoImpiegato"
											id="tempoImpiegato" path="tempoImpiegato"/>
										<form:errors path="tempoImpiegato" cssClass="error" />
										<label for="tempoImpiegato" class="active">Tempo impiegato (h,m)</label>
									</div>
									<div class="input-field col s6 m4 l4">
										<form:input type='text' id="dataRdp"
											path="dataRdp" cssClass="datepicker_birthdate"/>
										<form:errors path="dataRdp" cssClass="error" />
										<label for="dataRdp">Data RDP</label>
									</div>
									<div class="input-field col s12 m8 l4">
										<form:select id="esitoRdp" name="esitoRdp"
											path="esitoRdp" class="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaEsitiRdp}"
												itemValue="cod" itemLabel="descr" />
										</form:select>
										<form:errors path="esitoRdp" cssClass="error" />
										<label for="labelesitoRdp" class="active">Esito RDP</label>
									</div>
								  </div>	
								  <div class="row col l12">
									<div class="input-field col s12 m8 l4">
										<form:select id="orgNocAccertato" name="orgNocAccertato" multiple="multiple"
											path="orgNocAccertato" class="validate multiselect">											
											<form:options items="${listaOrgNocAccertato}"
												itemValue="idOrgNocivo" itemLabel="descBreve" />
										</form:select>
										<form:errors path="orgNocAccertato" cssClass="error" />
										<label for="labelorgNocAccertato" class="active">Organismo nocivo accertato</label>
									</div>
									
									<div class="input-field col s12 m8 l4">
										<form:input type="text" name="numeroRdp"
											id="numeroRdp" path="numeroRdp"/>
										<form:errors path="numeroRdp" cssClass="error" />
										<label for="numeroRdp" class="active">Numero RDP</label>
									</div>
								</div>
								
								<div class="row col l12">
									<div class="input-field col s12 m8 l12">
										<form:textarea id="noteCampione" path="noteCampione"
											cssClass="materialize-textarea validate" rows="2"
											data-length="1000" maxlength="1000" />
										<form:errors path="noteCampione" cssClass="error" />
										<label for="noteCampione" class="active">Note</label>
									</div>
								  </div>	
								  <div class="row">									
									<button class="btn green waves-effect waves-light" style=""
											type="submit" name="aggiungiCampione" id="aggiungiCampione">
											AGGIUNGI CAMPIONE
									</button>
								  </div>									
								</div> <!-- FINE collapsible-body -->
							</li>
						</ul>
					</div>

					<div class="row">
						<table id="tabellaCampioni"
							class="data-table striped display" data-paging="false"
							data-info="false">
							<thead>
								<tr>
									<th></th>
									<th></th>
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
										<td>
											<a href='javascript:modificaCampioni(${campione.idCampione})'
												class="btn btn-floating btn-floating-medium tooltipped light-green accent-3" 
												data-tooltip="Modifica campione">
												<i class="material-icons">mode_edit</i>
											</a>
										</td>
										<td>
											<a href='<spring:url value="/controlli/campioni/eliminaCampione/${campione.idCampione}" />'
												title="Elimina"
												class="btn btn-floating btn-floating-medium deep-orange accent-2"
												onclick="return eliminaCampione(this)"> 
												<i class="material-icons">delete</i>
											</a>
										</td>
										<c:choose>
											<c:when test="${campione.descGenere != null}">
												<td>${campione.descGenere} / ${campione.descSpecie}</td>
											</c:when>
											<c:otherwise>
												<td></td>
											</c:otherwise>
										</c:choose>										
										<td>${campione.descTipologiaCampione}</td>
										<td>${campione.codCampioneInizio} - ${campione.codCampioneFine}</td>
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
					
					<div class="row col l12">
									
					     			<div class="row col l12">	
					     				<b>I campioni vengono riposti in sacchetti antieffrazione</b> <br>													
											<label class="radio-inline">
												<form:errors path="flSacchetti" cssClass="error"/>
												<form:radiobutton id="flSacchetti"
													cssClass="with-gap" path="flSacchetti"
													value="S"/>
												<span>Si</span>
											</label>														
											<label class="radio-inline">
												<form:radiobutton id="flSacchetti"
													cssClass="with-gap" path="flSacchetti"
													value="N"/>
												<span>No</span>
											</label>																									
									</div>
									
									<div class="row col l12">	
					     				<b>Al titolare/rappresentante legale viene consegnata banda removibile con il codice del campione</b> <br>													
											<label class="radio-inline">
												<form:errors path="flBanda" cssClass="error"/>
												<form:radiobutton id="flBanda"
													cssClass="with-gap" path="flBanda"
													value="S"/>
												<span>Si</span>
											</label>														
											<label class="radio-inline">
												<form:radiobutton id="flBanda"
													cssClass="with-gap" path="flBanda"
													value="N"/>
												<span>No</span>
											</label>																									
									</div>
									
									<div class="row col l12">	
					     				<b>Il titolare viene informato sulla facoltà di assistere alle analisi di laboratorio, personalmente o 
					     				   per il tramite di un suo delegato, a tal proposito dichiara di volere assistere alle analisi</b>	<br>												
											<label class="radio-inline">
												<form:errors path="flAnalisi" cssClass="error"/>
												<form:radiobutton id="flAnalisi"
													cssClass="with-gap" path="flAnalisi"
													value="S"/>
												<span>Si</span>
											</label>														
											<label class="radio-inline">
												<form:radiobutton id="flAnalisi"
													cssClass="with-gap" path="flAnalisi"
													value="N"/>
												<span>No</span>
											</label>																									
									</div>
								</div>
					
					<!-- FINE Campioni -->
				</div>
			</div>
			<div class="card-action">	
				  <div class="row">
				    <a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a> 
					  <div class="right" style="text-align: right"> 		  	   
					    <button name="salvaDatiCampione" type="submit" class="btn confirm waves-effect waves-light" style="display: inline-block"> SALVA </button>
					 </div>
				  </div>
			</div>
		</div>
	</form:form>

	<!-- INIZIO MODALE -->
	<div id="editCampioniModal" class="modal scrollbar-thin">					
		<div class="modal-content" style="padding-top:0; padding-bottom:0;"></div>
	</div>



	<content tag="script"> <script>
	
	$(document).ready(function() {	

		if('${hasErrors}'=='true')
		{
			M.Collapsible.getInstance($("#collapsible")).open()
		}
		
		$('#genereCampione').autocomplete({
	        limit: 50,
	        minLength: 2,
	        onAutocomplete: function(value) {
	          caricaSpecieGenere();
	        }
	      });
		
		
		
	});
	
	$('#genereCampione').on('input', function(event) {
        popolaListaGeneri($(this));
        $('#idGenereCampione').val('0');
     });
	
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
	
	  function caricaSpecieGenere() {
		  console.log('caricaSpecieGenere');
		  var params = {
		        "denomGenere" : $("#genereCampione").val()
		      }

		  setValoreAsincrono(
		        'idGenereCampione',
		        '<spring:url value="/ajax/getDatiDenomGenere" />',
		        params,
		        toStringIdGenere);
		      
		  var params2 = {
		    	        "denomGenere" : $("#genereCampione").val()
		    	        , "idSpecieSel" : null
		    	      }      
		      
		  getSelectAsincrona('specieCampione',
		          '<spring:url value="/ajax/getListaSpecieDenomGenere" />',
		          params2, getOptionSpecie, 1);
	  }
		
	  function toStringIdGenere(genere) {
	    return genere.idGenere;
	  }
		
	  function getOptionSpecie(specie) {
	    return getOption(specie.idSpecie, specie.denomSpecie);
	  }
	
	 // Evento ELIMINA sulla tabella dei Campioni       
	 function eliminaCampione(link) {
	   console.log('eliminaCampione');

	   var genereSpecie;
	   var tipologiaCamp;

	   $riga = $(link).closest('tr');
	   genereSpecie = $riga.find('td:nth-of-type(3)').text();
	   tipologiaCamp = $riga.find('td:nth-of-type(4)').text();

	   var messaggio = 'Si desidera eliminare il campione con Genere/Specie : <b>'
						+ genereSpecie + '</b>, con Matrice campionata : <b>' + tipologiaCamp
						+ '</b>?'
			 
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
	 
	// gestione della modifica con la modale
	 function modificaCampioni(idCampione) {
			console.log('modificaCampione');
			
			$.ajax({
				url : '<spring:url value="/controlli/campioni/editCampioni"/>/' +idCampione
			}).done(function(response) {
				apriModaleCampioni(response, idCampione);
			}).fail(function(jqXHR, textStatus) {
			    console.log('TEXT STATUS: '+textStatus);
			    Swal.fire({
					  title : 'Errore',
					  html : 'Utente non abilitato ad modificare il campione',
					  type : 'error',
					  confirmButtonText : 'OK',
				});
			});
		}
		
		function apriModaleCampioni(content, idCampione) {
			console.log('apriModaleCampioni');
			$modal = $('#editCampioniModal');
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
				editInit(idCampione);
			}
			
			$modal.find('select').formSelect();
			M.updateTextFields();
			// per l'apertura della modale è necessario configurare anche i file:
			// SiteMeshFilter.java e SpringSecurityConfig.java
			$modal.modal('open');
		}
		
		
		function salvaModificaCampioni($form, idCampione) {
			console.log('salvaModificaCampioni - idCampione: '+idCampione);
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
					$modal = $('#editCampioniModal');
				    
				    if (response) {
				    	console.log('RESPONSE VALORIZZATO');
				    	$modal.find('div.modal-content').html(response);
				    	
				    	//$modal.find('select').formSelect();
						M.updateTextFields();
				    }
				    else {
				    	console.log('AGGIORNO TABELLA CAMPIONI');
				    	$modal.modal('close');
				    	Swal.fire({
							  title : idCampione ? 'Campione modificato' : '',
							  type : 'success',
							  confirmButtonText : 'OK',
						}).then(function(result) {
							if (result.value) {
									console.log('RICARICO LA PAGINA');
								  window.location.href = '<spring:url value="/controlli/campioni/nuova"/>';
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
	
	</script> </content>

</body>
</html>