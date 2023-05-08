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
.card-panel {
	padding-bottom: 0px;
}

.card-panel .row {
	margin-bottom: 0px;
	margin-left: 2px;
	margin-right: 2px;
}
</style>
</head>
<body>

	<div class="row">
		<div class="card col s12">
			<spring:url value="/controlli/report/cercaCampioni" var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaReportForm" accept-charset="utf-8">				
				<div class="card-content col s12">
					<span class="card-title">Ricerca report</span>
					<div class="row">
			            <div class="input-field col s12 m8 l6">
							<form:input type='text' id="codCampione" path="codCampione" />
							<form:errors path="codCampione" cssClass="error" />
							<label for="codCampione">Codice campione</label>
						</div>
						<div class="input-field col s12 m8 l6">
								<form:select id="idOrgNocivo" name="idOrgNocivo" path="idOrgNocivo">	
								<form:option value="" label="Selezionare" />										
									<form:options items="${listaOrgNoc}"
												itemValue="idOrgNocivo" itemLabel="descBreve" />
								</form:select>
								<form:errors path="idOrgNocivo" cssClass="error" />
								<label for="idOrgNocivo">Organismo nocivo</label>
						</div>
						<div class="input-field col s12 m8 l6">
							<input type="text" name="genere" placeholder="Selezionare"  
									class="validate autocomplete" autocomplete="off" />
							<label class="active">Genere</label>
						</div>            
						<div class="input-field col s12 m8 l6">
							<select name="specie" multiple="multiple"
				                class="multiselect">
									<option value="" >Selezionare</option>
							</select>
							<label>Specie</label>
						</div>
			            <div class="input-field col s12 m8 l6">
							<form:input type='text' id="dataRdpInizio" path="dataRdpInizio"
								cssClass="datepicker" />
							<form:errors path="dataRdpInizio" cssClass="error" />
							<label for="dataRdpInizio">Data controllo da</label>
						</div>
						<div class="input-field col s12 m8 l6">
							<form:input type='text' id="dataRdpFine" path="dataRdpFine"
								cssClass="datepicker" />
							<form:errors path="dataRdpFine" cssClass="error" />
							<label for="dataRdpFine">Data controllo a</label>
						</div>
			        </div>
					<div class="card-action">
						<div class="row">
							<div class="col s6">
								<button id="btnDeleteAll" type="button" class="btn waves-effect">PULISCI</button>
							</div>
							<div class="col s6 right-align">
								<button id="btnCerca" class="btn confirm waves-effect waves-light"
									type="submit" name="cerca">
									CERCA <i class="material-icons right">search</i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>

	<div class="row">
			<sec:authorize access="hasRole('WRITE') ">
				<!-- <a href='<spring:url value="/vivai/report/stampaVivai"/>' class="btn confirm waves-effect waves-light">STAMPA</a> -->
				<a href='<spring:url value="/controlli/report/esportaCampioni"/>' class="btn green waves-effect waves-light">ESPORTA</a>
			</sec:authorize>		   
	</div>	

	<c:choose>
		<c:when test="${not empty elencoReport}">

			<div class="row">
				<div class="col-s12">

					<table id="tabellaRichieste" class="data-table striped display">
						<thead>
							<tr>					
								
								<th>Codice campione</th>
								<th>Organismo nocivo</th>
								<th>Genere</th>	
								<th>Specie</th>										
								<th>Data controllo</th>								
																				
							</tr>
						</thead>
						<tbody id="bodyTabellaComunicazioni">
						  
			
							<c:forEach var="report" items="${elencoReport}">
								<tr>
									<td>${report.codCampioneInizio} - ${report.codCampioneFine}</td>
									<td>${report.organismoNocivo}</td>
									<td>${report.denomGenere}</td>
									<td>${report.denomSpecie}</td>									
									<td><fmt:formatDate value="${report.dataControllo}"	pattern="dd/MM/yyyy" /></td>																
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>				
		</c:when>
		<c:otherwise>

			<div class="row">
				<div id="richiesteWarning" class="card card-alert orange lighten-5">
					<div class="card-content orange-text valign-wrapper center"
						style="">
						<i class="material-icons large" style="font-size: 36px">warning</i>
						<p>&nbsp;&nbsp;Non sono stati trovati report</p>
					</div>
					<button type="button" class="close orange-text"
						data-dismiss="alert" aria-label="Chiudi">
						<span aria-hidden="true">×</span>
					</button>
				</div>
			</div>

		</c:otherwise>
	</c:choose>

	<content tag="script"> 
	<c:set var="locale">${pageContext.response.locale}</c:set>
			<c:choose>
				<c:when test="${locale != 'en'}">
					<!-- localizzazione js (solo per lingue diverse da inglese) -->
					<script>
						$(document).ready(function() {
		
							$('#bodyTabellaReport').pageMe({
								pagerSelector : '#myPager',
								activeColor : '#00897b',
								perPage : 6,
							});
		
						});
					</script>
					<br />
				</c:when>
				<c:otherwise>
					<script>
						$('#bodyTabellaReport').pageMe({
							pagerSelector : '#myPager',
							activeColor : '#00897b',
							perPage : 6,
						});
					</script>
					<br />
				</c:otherwise>
			</c:choose> 
	
	
	
	<script>
   
 
    // Richiamata quando viene selezionato il pulsante 'PULISCI'
    $(function () {
        $("#btnDeleteAll").bind("click", function () { 
        	console.log('PULISCI');
        	
        	$("#codCampione").val('');
            $("#genere").val('');
            $("#specie").val('');
            $("#organismoNocivo").val('');
            $("#dataRdp").val('');
     			          
            $(this).closest('form').clearForm();
        	M.updateTextFields();
        });
    });  


	var $rigaTabellaSpecie = null;
	var idSpecieSelezionati = null;
	var visualizzaModalInoltra = false;
	var idSpecieHidden = null;
	var generiHidden = null;

	$(document)
			.ready(
					function() {

						
						
						$('input[name=genere]').each(function() {
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

						// --- CLICK SUL PULANTE AGGIUNGI PER AGGIUNGERE LA SPECIE (pagina principale)
						$('#aggiungiSpecie').click(function(e) {
							console.log('aggiungi specie');
							e.preventDefault();
							aggiungiSpecie();
							pulisciGenereSpecie();
						});

						function pulisciGenereSpecie() {
							console.log('pulisciGenerespecie');
							$('#divSpecie input[name=genere]').val('');
							$selectSpecie = $('#divSpecie').find('select[name=specie]');
							$selectSpecie[0].options.length = 1;
							$selectSpecie.formSelect();
						}

						// ------------- Recupero tutti gli idSpecie presenti nella pagina
						var concat = [];
						var elencoIdSpecie = null;

						console.log('numero di idSpecie nella pagina ='
								+ $('#bodyTabellaSpecie input[name=idSpecie]').length);
						if ($('#bodyTabellaSpecie input[name=idSpecie]').length > 0) {
							$('#bodyTabellaSpecie').find("input[name=idSpecie]").each(function() {
								console.log('idSpecie =' + $(this).val());
								if ($(this).val() != null && $(this).val().trim().length > 0) {
									concat.push($(this).val());
								}
							});
							elencoIdSpecie = concat.join();
						}
						console.log('^^^^ elencoIdSpecie =' + elencoIdSpecie);
						idSpecieHidden = elencoIdSpecie;
						console.log('-- idSpecieHidden =' + idSpecieHidden);
						// ---------------

						// ------------- Recupero tutti i generi presenti nella pagina
						var concatGeneri = [];
						var elencoGeneri = null;

						console.log('numero di generi nella pagina ='
								+ $('#bodyTabellaSpecie input[name=denomGenere]').length);
						if ($('#bodyTabellaSpecie input[name=denomGenere]').length > 0) {
							$('#bodyTabellaSpecie').find("input[name=denomGenere]").each(function() {
								console.log('denomGenere =' + $(this).val());
								if ($(this).val() != null && $(this).val().trim().length > 0) {
									concatGeneri.push($(this).val());
								}
							});
							elencoGeneri = concatGeneri.join();
						}
						console.log('^^^^ elencoGeneri =' + elencoGeneri);
						generiHidden = elencoGeneri;
						console.log('-- generiHidden =' + generiHidden);

					});
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
    
	// ********** Gestione Aggiungi Genere/Specie - Clicl sul pulsante 'AGGIUNGI SPECIE' pagina principale
	function aggiungiSpecie() {
		console.log('aggiungiSpecie');

		// Per ogni specie selezionata, devo clonare una riga : con genere selezionato e le possibili N specie splittate su N righe
		var idSpecieSel = '';
		var descSpecieSel = '';
		var genere = $('#divSpecie input[name=genere]').val();
		console.log('genere =' + genere);

		var numSpecieTrovate = $('select[name=specie] option').length;
		console.log('lunghezza select specie lunghezzaSelectSpecie1 = ' + numSpecieTrovate);
		//se sono presenti specie nella select allora è obbligatorio selezionarne almeno una
		// altrimenti è stato inserito un genere famiglia che non ha specie associate, quindi devono poter comunque inserire.
		if (numSpecieTrovate > 1) {
			// controllare se e' stata selezionata almeno una specie
			if ($('#divSpecie select[name=specie] option:selected').val() == null) {
				swal({
					title : 'Selezionare una specie',
					type : 'warning',
				});
				return;
			}

			$('#divSpecie select[name=specie] option:selected')
					.each(
							function() {
								
								idSpecieSel = $(this).val();
								console.log('idSpecieSel =' + idSpecieSel);
								console.log('idSpecieHidden =' + idSpecieHidden);

								// controllo se l'idSpecieSelezionata è già in idSpecieHidden: in questo caso non la faccio aggiungere : la specie è già presente nella tabella sotto
								if (idSpecieHidden != null && idSpecieSel != null) {
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
								}
							    
								// per non visualizzare le specie già selezionate nella combo delle specie
								if (idSpecieHidden) {
									idSpecieHidden += ',' + idSpecieSel;
								} else {
									idSpecieHidden = idSpecieSel;
								}
								console.log(' ^^^^^^ idSpecieHidden =' + idSpecieHidden);
								    
								descSpecieSel = $(this).text();
								console.log('descSpecieSel =' + descSpecieSel);

								console.log('CLONE row');
								$rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
								
								$rigaTabellaSpecie.prop('id', '');
								// Numero piante										
								$rigaTabellaSpecie.find('input[name=numeroPianteTemplate]').attr('name', 'numeroPiante');
								$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('class', 'validate');
								$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('pattern', '\\d*');
								$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('required', 'required');
								$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('value', '');
								$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('disabled', false);
								$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('maxlength', '8');
								$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('oninvalid',
										'this.setCustomValidity(\'Numero piante non valido\')');
								$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('oninput',
										'this.setCustomValidity(\'\')');

								$rigaTabellaSpecie.find('input[name=idSpecie]').removeAttr('disabled');
								$rigaTabellaSpecie.find('input[name=idGenere]').removeAttr('disabled');
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
		} else if (genere) { //Non ci sono specie
			descSpecieSel = '';
			console.log('descSpecieSel =' + descSpecieSel);

			console.log('CLONE row');
			var $rigaTabellaSpecie = $('#rigaTemplateSpecie').clone();
			$rigaTabellaSpecie.prop('id', '');

			// Numero piante						
			$rigaTabellaSpecie.find('input[name=numeroPianteTemplate]').attr('name', 'numeroPiante');
			$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('class', 'validate');
			$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('pattern', '\\d*');
			$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('required', 'required');
			$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('value', '');
			$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('disabled', false);
			$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('maxlength', '8');
			$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('oninvalid',
					'this.setCustomValidity(\'Numero piante non valido\')');
			$rigaTabellaSpecie.find('input[name=numeroPiante]').attr('oninput', 'this.setCustomValidity(\'\')');

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

						// Togliere l'idSpecie che si sta rimuovendo anche dalla stringa idSpecieHidden, usata per filtrare la query (not in...)
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
    </script>  
	
 </content>
	
	

</body>

</html>