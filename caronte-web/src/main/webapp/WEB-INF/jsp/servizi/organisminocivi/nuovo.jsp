<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<body>
	<spring:url value="/admin/organisminocivi/nuovo" var="formAction" />
	<form:form action="${formAction}" method="post"
		modelAttribute="nuovoOrganismoNocivoForm" accept-charset="utf-8">
		<div class="card">
			<div class="card-content">
				<span class="card-title">Nuovo organismo nocivo</span>
				<div class="col s12" id="nuovoOrganismoNocivo">
					<div class="row">						
						<div class="row col l12">								
								<!------ SELECT::TIPO ORGANISMO NOCIVO -------->
								<div class="input-field col s6 m4 l6">
									<!-- i class="material-icons md-36 prefix">view_list</i-->
									<form:select id="idTipoOrgNocivo" path="idTipoOrgNocivo" cssClass="validate" required="required">
										<form:option value="" label="Selezionare" />
										<form:options items="${selectTipiON}" itemValue="idTipoOrgNocivo"
											itemLabel="descBreve" />
									</form:select>
									<form:errors path="idTipoOrgNocivo" cssClass="error" />
									<label for="ruolo">Tipo organismo Nocivo *</label>
								</div>
								<div class="input-field col s6 m4 l6">
									<label> <form:checkbox value="S"
											id="orgNocInZonaProtetta" path="orgNocInZonaProtetta" />
										<span>Organismo nocivo in Zona protetta</span>
									</label>
								</div>
						</div>
						<div class="row col l12">														
							<!------ INPUT::NUOVO CODICE EPPO -------->
							<div class="input-field col s6 m4 l6">
								<form:input type="text" id="nuovoCodiceEppo" path="nuovoCodiceEppo" maxlength="40" required="required"/>
								<form:errors path="nuovoCodiceEppo" cssClass="error" />
								<label for="nuovoCodiceEppo">Codice Eppo *</label>
							</div>
							<!------ INPUT:NUOVA DESCRIZIONE ORGANSMO NOCIVO -------->
							<div class="input-field col s12 m8 l6">
								<form:input type="text" id="descrizioneOrganismoNocivo" path="descrizioneOrganismoNocivo" maxlength="40" required="required"/>
								<form:errors path="descrizioneOrganismoNocivo" cssClass="error" />
								<label for="descrizioneOrganismoNocivo">Descrizione Organismo Nocivo *</label>
							</div>													
						</div>						
						  <div class="row col l12">						
								<!------ SELECT::TIPO PRODOTTO -------->
								<div class="input-field col s12 m8 l6">
									<!-- i class="material-icons md-36 prefix">view_list</i-->
									<form:select id="idTipoProdotto" path="idTipoProdotto" cssClass="validate" required="required">
										<form:option value="" label="Selezionare" />
										<form:options items="${selectTipiProdotto}" itemValue="idTipoProdotto"
											itemLabel="descTipoProdotto" />
									</form:select>
									<form:errors path="idTipoProdotto" cssClass="error" />
									<label for="ruolo">Tipo prodotto *</label>
								</div>								
						  </div>
					</div>
						
						<!-- div class="row">						
						<div class="row col l12" id="divSpecie">
							<div class="input-field col s12 m8 l6">
								<form:select id="idGenere" path="idGenere" cssClass="validate" onchange="getspecie();" required="required">
									<form:option value="" label="Selezionare" />
									<form:options items="${selectGenere}" itemValue="idGenere"
										itemLabel="denomGenere" />
								</form:select>
								<form:errors path="idGenere" cssClass="error" />
								<label for="idGenere">Genere *</label>
							</div>
							
							<div id="divSpecie" class="input-field col s12 m8 l6">
								<select name="specie" multiple="multiple"
									class="validate multiselect">
									<option value="" disabled="">Selezionare</option>
								</select> <label>Specie</label>
							</div> 
						</div>	
						
					</div> -->
					
						<div class="row" id="divSpecie">
	            <div class="input-field col s12 m5">
	              <input type="text" name="genere" placeholder="Selezionare"  
	                class="validate autocomplete" autocomplete="off" />
	              <label class="active">Genere *</label>
	            </div>            
	            <div class="input-field col s12 m5">
	              <select name="specie" multiple="multiple"
	                class="validate multiselect">
	                <option value="" disabled="">Selezionare</option>
	              </select>
	              <label>Specie *</label>
	            </div>
	            <div class="input-field col s12 m2">
	              <button id="aggiungiSpecie" class="btn confirm waves-effect waves-light" type="button" 
	                style="display: inline-block"> AGGIUNGI SPECIE </button>
	              <button type="submit" style="display:none"></button>
	            </div>
	  </div>    
	  
	  <div class="row">
        <table id="tabellaSpecie" class="data-table striped display" 
          data-orderable='false' data-paging="false" data-info="false">
          <thead>
            <tr>
              <th></th>
              <th>Genere</th>
              <th>Specie</th>
            </tr>
          </thead>
          <tbody id="bodyTabellaSpecie"></tbody>
          <!-- inizio  - RIGA CHE VIENE CLONATA -->
          <tr id="rigaTemplateSpecie" style="display:none">
            <td nowrap style="white-spaces: nowrap">
              <a title="Modifica" 
                onclick="javascript:modificaGenere(this)"
                class="btn btn-floating btn-floating-medium light-green accent-3">
                <i class="material-icons">mode_edit</i>
              </a>
              <a title="Elimina"
                onclick="javascript:eliminaGenere(this)"
                class="btn btn-floating btn-floating-medium deep-orange accent-2"> 
                <i class="material-icons">delete</i></a>
              <input type="hidden" name="idSpecie" value="" disabled="disabled" />
              <input type="hidden" name="denomGenere" value="" disabled="disabled" />
            </td>
            <td></td>
            <td></td>
          </tr>
          <!-- fine - RIGA CHE VIENE CLONATA -->
        </table>                      
      </div>
      
      

				</div>
			</div>
			
			

  
  
      
      
      
  
			<div class="card-action">
				<div class="row">
					<div class="col s6">
						<a href='<spring:url value="/admin/organisminocivi/elenco"/>' class="btn waves-effect waves-light">ANNULLA</a>
					</div>
					<div class="col s6 right-align">
						<button id="salvaComunicazione"class="btn confirm waves-effect waves-light" type="submit"
							name="datiNuovoOrganismoNocivo">SALVA</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	
	<!-- INIZIO MODALE -->
	<div id="editSpecieModal" class="modal scrollbar-thin long90-modal modal-90">
    <div class="modal-content" style="padding-top:0; padding-bottom:0;">
      <h5 class="primary-color bold">Dati specie</h5>
      <form method="post" accept-charset="utf-8">
      <div class="card">
        <div class="card-content">
          <div class="row">
            <div class="input-field col s12 m6">
              <input type="text" name="genere" placeholder="Selezionare"  
                class="validate autocomplete" required="required" autocomplete="off" />
              <label class="active">Genere *</label>
            </div>
            
            <div class="input-field col s12 m6">
              <select name="specie" class="validate">
                <option value="" disabled="">Selezionare</option>
              </select>
              <label>Specie *</label>
            </div>
          </div>
        </div>
        <div class="card-action">
          <div class="row">                             
            <div class="col s3">
              <button class="btn confirm waves-effect waves-light" type="button" 
                style="display: inline-block"> MODIFICA </button>
              <button type="submit" style="display:none"></button>
            </div>
            <div class="col s3 right" style="text-align: right">
              <a class="btn orange lighten-1 modal-action modal-close waves-effect back"> CHIUDI </a>
            </div>
          </div>
        </div>
      </div>
      </form>
    </div>
  </div>
  <!-- FINE MODALE -->

	<content tag="script"> 
		<script type="text/javascript">
/*			vecchio script per il vecchio funzionamento
		function reloadMultiselect($selectRef) {
			console.log('reloadMultiselect');				
			//if (idSpecieSelezionati) {
				//var arrayIdSpecie = idSpecieSelezionati.split(',');
				
				$selectRef.find('option').each(function() {
					$option = $(this);
				
					//var indiceInArray = $.inArray($option.val(), arrayIdSpecie);
					var indiceInArray = $.inArray($option.val(), null);

					  
					 *  Se viene trovato l'id specie nell'array, la option viene selezionata 
					 *  e l'id specie viene rimosso dall'array per velocizzare le ricerche successive 
					 
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
    	*/
    
//		function getspecie(){
			
//			var idGenere = $("#idGenere").val();
//			if(idGenere != ""){
//				$selectSpecie = $('#divSpecie').find('select[name=specie]');
//				var params = {
//						"idGenere" : idGenere
//				};
//				getSelectAsincronaRef($selectSpecie,
//								'<spring:url value="/ajax/getListaSpecieByIdGenere" />',
//								params, getOptionSpecie, 1, reloadMultiselect);		
						
//			}
//		}



var $rigaTabellaSpecie = null;
var idSpecieSelezionati = null;
var idSpecieHidden = null;

$(document).ready(function() {
//Reperimento del centro aziendale in base allo spedizioniere selezionato

	
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


// --- CLICK SUL PULSANTE AGGIUNGI PER AGGIUNGERE LA SPECIE sulla MODALE
$('#editSpecieModal').find('button.confirm').off().on('click', function(e) {
  e.preventDefault();
salvaModificaSpecie($(this).closest('form'));           
});


// --- CLICK SUL PULANTE AGGIUNGI PER AGGIUNGERE LA SPECIE (pagina principale)
$('#aggiungiSpecie').click(function(e) {
console.log('aggiungi specie');
e.preventDefault();
aggiungiSpecie();
pulisciGenereSpecie();
});

function pulisciGenereSpecie(){
console.log('pulisciGenerespecie');
$('#divSpecie input[name=genere]').val('');
$selectSpecie = $('#divSpecie').find('select[name=specie]');
$selectSpecie[0].options.length = 1;
$selectSpecie.formSelect();	        	
}


$('#salvaComunicazione').click(function(event) {
//Si controlla che l'utente abbia inserito almeno una specie prima di effettuare il submit
if ($('#bodyTabellaSpecie input[name=idSpecie]').length < 1) {
	event.preventDefault();
	swal({
		title : 'Aggiungere almeno una specie',
		type : 'warning',
	});
}
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
	console.log('caricaSpecieGenere');
	$selectSpecie = $inputGenere.closest('.row').find('select[name=specie]');
	
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
	console.log('reloadMultiselect');
	console.log('idSpecieSelezionati ='+idSpecieSelezionati);
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
			



// Richiamato alla selezione del pulsante 'modifica', per aprire la modale
function modificaGenere(link) {
	console.log('modificaGenere');
	// recupero tutti gli idSpecie presenti nella pagina, da passare alla modale (meno quella che si sta modificando)
	var concat = [];				
	var elencoIdSpecie = null;
	
	var idSpecieDaModificare = $(link).closest('tr').find('input[name=idSpecie]').val();
	console.log('-- idSpecieDaModificare ='+idSpecieDaModificare);								
	
	console.log('numero di idSpecie nella pagina ='+$('#bodyTabellaSpecie input[name=idSpecie]').length);
	if ($('#bodyTabellaSpecie input[name=idSpecie]').length > 0) {
		$('#bodyTabellaSpecie').find("input[name=idSpecie]").each(function () {
			console.log('idSpecie ='+$(this).val());
			if($(this).val() != idSpecieDaModificare){
			  concat.push($(this).val());
			}  
		  });
		elencoIdSpecie = concat.join();
	}								
	console.log('^^^^ elencoIdSpecie ='+elencoIdSpecie);
	
	apriModaleSpecie($(link).closest('tr'), elencoIdSpecie);
}


	function apriModaleSpecie($riga, elencoIdSpecie) {
		console.log('apriModaleSpecie');
		$modal = $('#editSpecieModal');

		$selectSpecie = $modal.find('select[name=specie]');
		$selectSpecie[0].options.length = 1;
		$selectSpecie.formSelect();					

		if ($riga) {
			console.log('--- caso riga valorizzata');
			$rigaTabellaSpecie = $riga;
									
			$inputGenere = $modal.find('input[name=genere]');
			$inputGenere.val($rigaTabellaSpecie.find('td:nth-of-type(2)').text());
			
			idSpecieSelezionati = $rigaTabellaSpecie.find('input[name=idSpecie]').val();
			console.log('****** idSpecieSelezionati ='+ idSpecieSelezionati);

			caricaSpecieGenere($inputGenere);
		} 
		else {
			console.log('--- caso riga non valorizzata');
			$rigaTabellaSpecie = null;
			idSpecieSelezionati = null;

			$modal.find('input[name=genere]').val('');
		}

		$modal.modal('open');
	}

	
	
// ********** Gestione Aggiungi Genere/Specie - Clicl sul pulsante 'AGGIUNGI SPECIE' pagina principale
function aggiungiSpecie(){
  console.log('aggiungiSpecie');
														
  // Per ogni specie selezionata, devo clonare una riga : con genere selezionato e le possibili N specie splittate su N righe
  var idSpecieSel = '';
  var descSpecieSel = '';
  var genere = $('#divSpecie input[name=genere]').val();
  console.log('genere ='+genere);
  
  var numSpecieTrovate = $('select[name=specie] option').length;			  
  console.log('lunghezza select specie lunghezzaSelectSpecie1 = '+ numSpecieTrovate);
  //se sono presenti specie nella select allora è obbligatorio selezionarne almeno una
  // altrimenti è stato inserito un genere famiglia che non ha specie associate, quindi devono poter comunque inserire.
  if (numSpecieTrovate > 2) {
	  // controllare se e' stata selezionata almeno una specie
	  if($('#divSpecie select[name=specie] option:selected').val() == null){		  
		  swal({
    			title : 'Selezionare una specie',
    			type : 'warning',
	          });  
		  return;
	  }
  

	      $('#divSpecie select[name=specie] option:selected').each(							 
		 function() {
		   idSpecieSel = $(this).val();
		   console.log('idSpecieSel ='+ idSpecieSel);
		   console.log('idSpecieHidden ='+ idSpecieHidden);
		   
		   // controllo se l'idSpecieSelezionata è già in idSpecieHidden: in questo caso non la faccio aggiungere : la specie è già presente nella tabella sotto
		   if(idSpecieHidden != null && idSpecieSel != null){
			 const idSp = idSpecieHidden.split(',');
			 if(idSp != null){
				 console.log('idSp ='+idSp);
				 if(idSp.indexOf(idSpecieSel) != -1){
					 console.log('idSpecie gia presente nella pagina!');
					 swal({
		        			title : 'Non &egrave; possibile aggiungere la specie selezionata : specie gi&agrave; presente',
		        			type : 'warning',
        		       });
					   return;
				 }
			 }				     
		   }
		   

		   // per non visualizzare le specie già selezionate nella combo delle specie
		   if (idSpecieHidden) {
			 idSpecieHidden += ',' + idSpecieSel;
		   } 
		   else {
		     idSpecieHidden = idSpecieSel;
		   }
		   console.log(' ^^^^^^ idSpecieHidden ='+ idSpecieHidden);

		   descSpecieSel = $(this).text();
		   console.log('descSpecieSel ='+ descSpecieSel);

		   console.log('CLONE row');
		   $rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
		   $rigaTabellaSpecie.prop('id', '');

			
			$rigaTabellaSpecie.find('input[name=idSpecie]').removeAttr('disabled');		
			$rigaTabellaSpecie.find('input[name=denomGenere]').removeAttr('disabled');
			$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
			$rigaTabellaSpecie.show();

			$rigaTabellaSpecie.find('td:nth-of-type(2)').text(genere);
			$rigaTabellaSpecie.find('input[name=denomGenere]').val(genere);
								
			$rigaTabellaSpecie.find('td:nth-of-type(3)').text(descSpecieSel);
			$rigaTabellaSpecie.find('input[name=idSpecie]').val(idSpecieSel);
								
			$table = $rigaTabellaSpecie.closest('table');

			var dataTable = $table.DataTable();
			dataTable.row.add($rigaTabellaSpecie);
			dataTable.draw();
		  });// chiusura ciclo sulle specie	
  		}	
	  else if (genere) {
			descSpecieSel = '';
			console.log('descSpecieSel ='+ descSpecieSel);
			
			console.log('CLONE row');
			$rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
			$rigaTabellaSpecie.prop('id', '');
			
			
			$rigaTabellaSpecie.find('input[name=idSpecie]').removeAttr('disabled');	
			$rigaTabellaSpecie.find('input[name=denomGenere]').removeAttr('disabled');
			$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
			$rigaTabellaSpecie.show();
									
			$rigaTabellaSpecie.find('td:nth-of-type(2)').text(genere);
			$rigaTabellaSpecie.find('input[name=denomGenere]').val(genere);
								
			$rigaTabellaSpecie.find('td:nth-of-type(3)').text(descSpecieSel);
			$rigaTabellaSpecie.find('input[name=idSpecie]').val('');
								
			$table = $rigaTabellaSpecie.closest('table');
			
			var dataTable = $table.DataTable();
			dataTable.row.add($rigaTabellaSpecie);
			dataTable.draw();
			  
		}
	}
	
	
	
	// ************** Gestione modifica Genere/Specie - Click sul pulsante 'MODIFICA SPECIE MODALE'
	function salvaModificaSpecie($form) {
		console.log('salvaModificaSpecie');
		if ($form.length == 0) {
			return;
		}

		if (!$form[0].checkValidity()) {

			//  Se il form non è valido, si simula un click sul pulsante "submit" nascosto 
			// nella form, per attivare la validazione HTML5. Se non si desidera vedere 
			// anche i messaggi di JQuery Validate, fare return dopo il click

			$form.find(':submit').click();
			return;
		}

		//if ($form.valid()) {
		console.log('IN SALVA MODIFICA SPECIE - FORM VALIDO!!');

		var casoModifica = 0;
		var numPiante = null;
		// Caso di modifica : rimuovo prima la riga
		if ($rigaTabellaSpecie) {
			
			
			// idSpecie da rimuovere da idSpecieHidden
			idSpecieRim = $rigaTabellaSpecie.find('input[name=idSpecie]').val();
			console.log('idSpecie da rimuovere =' + idSpecieRim);

			// Togliere l'idSpecie che si sta rimuovendo anche dalla stringa idSpecieHidden, usata per filtrare la query (not in...)
			if (idSpecieHidden) {
				console.log('idSpecieHidden PRIMA ='+ idSpecieHidden);
				var arrayIdSpecie = idSpecieHidden.split(',');
				console.log('idSpecieRim da rimuovere ='+ idSpecieRim);
				var indiceInArray = $.inArray(idSpecieRim,arrayIdSpecie);
				console.log('indiceInArray =' + indiceInArray);
				/*  
				 *  Se viene trovato l'id specie nell'array, la option viene selezionata 
				 *  e l'id specie viene rimosso dall'array 
				 */
				if (indiceInArray >= 0) {
					arrayIdSpecie.splice(indiceInArray, 1);
					idSpecieHidden = arrayIdSpecie.toString();
					console.log('IdSpecieHidden DOPO ='+ idSpecieHidden);
				}
			}

			console.log('REMOVE row');
			var dataTable = $rigaTabellaSpecie.closest('table').DataTable();
			dataTable.row($rigaTabellaSpecie).remove().draw();
			$rigaTabellaSpecie.remove();
			casoModifica = 1;
		}
		console.log('CASO inserimento nuova specie');
		var numSpecieTrovate = $('select[name=specie] option').length;			  
		console.log('lunghezza select specie lunghezzaSelectSpecie1 = '+ numSpecieTrovate);
		//se sono presenti specie nella select allora è obbligatorio selezionarne almeno una
		// altrimenti è stato inserito un genere famiglia che non ha specie associate, quindi devono poter comunque inserire.
		var genereSel = $form.find('input[name=genere]').val();
		console.log('genereSel ='+ genereSel);
		
		if (numSpecieTrovate > 2) {
			// Per ogni specie selezionata, devo clonare una riga : con genere selezionato e le possibili N specie splittate su N righe
			var idSpecieSel = '';
			var descSpecieSel = '';

			$form.find("select[name=specie] option:selected").each(
				function() {
					idSpecieSel = $(this).val();
					console.log('idSpecieSel ='+ idSpecieSel);

					// per non visualizzare le specie già selezionate nella combo delle specie
					if (idSpecieHidden) {
						idSpecieHidden += ',' + idSpecieSel;
					} 
					else {
						idSpecieHidden = idSpecieSel;
					}
					console.log(' ^^^^^^ idSpecieHidden ='+ idSpecieHidden);

					descSpecieSel = $(this).text();
					console.log('descSpecieSel ='+ descSpecieSel);

					console.log('CLONE row');
					$rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
					$rigaTabellaSpecie.prop('id', '');


					$rigaTabellaSpecie.find('input[name=idSpecie]').removeAttr('disabled');
					$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
					$rigaTabellaSpecie.show();

					$rigaTabellaSpecie.find('td:nth-of-type(2)').text(genereSel);
					$rigaTabellaSpecie.find('input[name=denomGenere]').val(genereSel);
					$rigaTabellaSpecie.find('td:nth-of-type(3)').text(descSpecieSel);
					$rigaTabellaSpecie.find('input[name=idSpecie]').val(idSpecieSel);


					$table = $rigaTabellaSpecie.closest('table');

					var dataTable = $table.DataTable();
					dataTable.row.add($rigaTabellaSpecie);
					dataTable.draw();
				});// chiusura ciclo sulle specie						
			}	
	  	    else {
	  	    	console.log('CASO GENERE FAMIGLIA, QUINDI NESSUNA SPECIE PRESENTE');
				descSpecieSel = '';
				
				console.log('CLONE row');
				$rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
				$rigaTabellaSpecie.prop('id', '');
				
				$rigaTabellaSpecie.find('input[name=idSpecie]').removeAttr('disabled');	
				$rigaTabellaSpecie.find('input[name=denomGenere]').removeAttr('disabled');
				$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
				$rigaTabellaSpecie.show();
				
				/*var genere = $('#divSpecie input[name=genere]').val();
				console.log('genere ='+genere);*/
				$rigaTabellaSpecie.find('td:nth-of-type(2)').text(genereSel);
				$rigaTabellaSpecie.find('input[name=denomGenere]').val(genereSel);
									
				$rigaTabellaSpecie.find('td:nth-of-type(3)').text(descSpecieSel);
				$rigaTabellaSpecie.find('input[name=idSpecie]').val('');
									
				$table = $rigaTabellaSpecie.closest('table');
				
				var dataTable = $table.DataTable();
				dataTable.row.add($rigaTabellaSpecie);
				dataTable.draw();
			}
		$modal.modal('close');
	}

	// IVAN 
	/* function salvaModificaSpecie($form) {
		console.log('IN SALVA MODIFICA SPECIE!!');
		if ($form.length == 0) {
			return;
		}

		if (!$form[0].checkValidity()) {
			
			//  Se il form non è valido, si simula un click sul pulsante "submit" nascosto 
			 // nella form, per attivare la validazione HTML5. Se non si desidera vedere 
			 // anche i messaggi di JQuery Validate, fare return dopo il click
			 
			$form.find(':submit').click();
			return;
		}

		//if ($form.valid()) {
		console.log('IN SALVA MODIFICA SPECIE - FORM VALIDO!!');
		if (!$rigaTabellaSpecie) {
			$rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
			$rigaTabellaSpecie.prop('id', '');
			$rigaTabellaSpecie.find('input[name=idSpecie]')
					.removeAttr('disabled');
			$rigaTabellaSpecie.appendTo($('#bodyTabellaSpecie'));
			$rigaTabellaSpecie.show();
		}

		$rigaTabellaSpecie.find('td:nth-of-type(2)').text(
				$form.find('input[name=genere]').val());

		var idSpecie = '';
		var descSpecie = '';

		$form.find("select[name=specie] option:selected").each(
				function() {
					idSpecie += $(this).val() + ',';
					descSpecie += $(this).text() + ', ';
				}
				);

		if (idSpecie) {
			idSpecie = idSpecie.slice(0, -1);
			console.log('idSpecie ='+idSpecie);
			descSpecie = descSpecie.slice(0, -2);
			console.log('descSpecie ='+descSpecie);
		}

		$rigaTabellaSpecie.find('td:nth-of-type(3)').text(
				descSpecie);
		$rigaTabellaSpecie.find('input[name=idSpecie]').val(
				idSpecie);

		$table = $rigaTabellaSpecie.closest('table');

		var dataTable = $table.DataTable();
		dataTable.row.add($rigaTabellaSpecie);
		dataTable.draw();

		$modal.modal('close');
		//}
	} */

	function eliminaGenere(link) {
		console.log('eliminaGenere');
		var denomGenere;

		$riga = $(link).closest('tr');
		denomGenere = $riga.find('td:nth-of-type(2)').text();

		// idSpecie da rimuovere da idSpecieHidden
		idSpecie = $riga.find('input[name=idSpecie]').val();
		console.log('idSpecie da rimuovere =' + idSpecie);

		swal(
				{
					title : 'Attenzione!',
					html : 'Si desidera cancellare le specie del genere <b>'
							+ denomGenere + '</b>?',
					type : 'warning',
					showCancelButton : true,
					confirmButtonText : 'Sì',
					cancelButtonText : 'No',
				}).then(
				function(result) {
					if (result.value) {
						var dataTable = $riga.closest('table')
								.DataTable();
						dataTable.row($riga).remove().draw();
						$riga.remove();

						// Togliere l'idSpecie che si sta rimuovando anche dalla stringa idSpecieHidden, usata per filtrare la query (not in...)
						if (idSpecieHidden) {
							console.log('idSpecieHidden PRIMA ='
									+ idSpecieHidden);
							var arrayIdSpecie = idSpecieHidden
									.split(',');
							console.log('idSpecie da rimuovere ='
									+ idSpecie);
							var indiceInArray = $.inArray(idSpecie,
									arrayIdSpecie);
							console.log('indiceInArray ='
									+ indiceInArray);
							/*  
							 *  Se viene trovato l'id specie nell'array, la option viene selezionata 
							 *  e l'id specie viene rimosso dall'array 
							 */
							if (indiceInArray >= 0) {
								arrayIdSpecie.splice(indiceInArray,
										1);
								idSpecieHidden = arrayIdSpecie
										.toString();
								console.log('IdSpecieHidden DOPO ='
										+ idSpecieHidden);
							}
						}
					}
				});
	}
	
	function getOptionSpedizioniere(spedizioniere) {
		if(spedizioniere != null){
		  if(spedizioniere.denomSpedizioniere != null)
		    return getOption(spedizioniere.idSpedizioniere, spedizioniere.denomSpedizioniere);
		  else
			return getOption(spedizioniere.idSpedizioniere, '');
		}  
    }
	
	$('#spedizioniereRuop').change(function() {
		console.log('params spedizioniereRuop = '+$(this).val().trim());
		if ($(this).val().trim().length == 0 || $(this).val().trim().length > 1) {
		  console.log('ricerca spedizioniere');
    	  var params = {
    			  "spedizioniereRuop" : $(this).val().trim()
    		}		        	  
    	  getSelectAsincrona(
    			  'spedizioniere',
    			  '<spring:url value="/ajax/getListaSpedizionieriByDenomRuop" />',
    			  params,
    			  getOptionSpedizioniere, 1);
		}
	});


		</script>
		</content>
</body>