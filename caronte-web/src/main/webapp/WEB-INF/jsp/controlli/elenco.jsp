<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
			<spring:url value="/controlli/elenco" var="formAction" />
			<form:form action="${formAction}" method="get" modelAttribute="ricercaOperatoreForm" accept-charset="utf-8">				
				<div class="card-content col s12">
					<span class="card-title">Cerca operatori</span>
					<div class="row">					
					  <div class="input-field col s6 m4 l4">
			              <form:select id="idIspettore" path="idIspettore">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaIspettori}" itemValue="idIspettore"
									itemLabel="descIspettore" />
							</form:select>
              			<form:errors path="idIspettore" cssClass="error" />
              			<label for="labelIdIspettore">Ispettore/Agente</label>
            		  </div>
					  <div class="input-field col s6 m4 l4">
							<form:select id="idProvincia" path="idProvincia">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaProvince}" itemValue="idProvincia" itemLabel="denomProvincia" />
							</form:select>
							<form:errors path="idProvincia" cssClass="error" />
							<label for="labelProvincia">Provincia Sede Legale</label>							
					  </div>
					  <div class="input-field col s6 m4 l4">
							<form:select id="idTipoAttivita" name="idTipoAttivita" path="idTipoAttivita">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaTipologieAttivita}" itemValue="idTipoAttivita" itemLabel="descEstesa" />														
							</form:select>	
							<form:errors path="idTipoAttivita" cssClass="error" />								
							<label for="labelidTipoAttivita">Tipologia attività</label>							
					  </div>					  
			            <div class="input-field col s6 m4 l6">
			              <form:input type="text" id="ragioneSociale" path="ragioneSociale"/>
			              <form:errors path="ragioneSociale" cssClass="error" />
			              <label for="ragioneSociale">Ragione sociale</label>
			            </div>
						 <div class="input-field col s6 m4 l6">
			              <form:input type="text" id="cuaa" path="cuaa" maxlength="16"/>
			              <form:errors path="cuaa" cssClass="error" />
			              <label for="labelCuaa">Cuaa</label>
			            </div>
					  <div class="input-field col s12 m5 l6">
						    <input type="text" name="genere" placeholder="Selezionare"  
						     class="validate autocomplete" autocomplete="off"/>
						     <label class="active">Genere</label>
					  </div>            
					  <div class="input-field col s12 m5 l6">
					          <select name="specie" multiple="multiple"
					             class="validate multiselect">
					             <option value="" disabled="">Selezionare</option>
					          </select>
					          <label>Specie</label>
					  </div>
					  <div class="input-field col s6 m4 l4">
							<form:select id="idOrgNocivo" name="idOrgNocivo" path="idOrgNocivo">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaOrganismiNocivi}" itemValue="idOrgNocivo" itemLabel="descEstesa" />														
							</form:select>	
							<form:errors path="idOrgNocivo" cssClass="error" />								
							<label for="labelidOrgNocivo">Organismo nocivo</label>							
					  </div>					 					
					</div>
					<div class="card-action">
						<div class="row">
							<div class="col s6">
								<button id="btnDeleteAll" type="button" class="btn clear-form waves-effect">PULISCI</button>
							</div>
							<div class="col s6 right-align">
								<button class="btn confirm waves-effect waves-light" type="submit" name="cerca">
									CERCA <i class="material-icons right">search</i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>


	<c:choose>
		<c:when test="${not empty elencoOperatori}">
			<div class="row">
				<div class="col-s12">
					<table id="tabellaOperatori" class="data-table striped display">
						<thead>
							<tr>								
								<th>Cuaa</th>
								<th>Partita iva</th>
								<th>Ragione Sociale</th>
								<th>Provincia sede legale</th>
								<th>Codice RUOP</th>
								<th>Codice Centro Aziendale</th>
								<th>Tipo passaporto</th>
								<th>Ispettore/Agente assegnatario</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody id="bodyTabellaOperatori">						  				                
						     <c:forEach var="operatore" items="${elencoOperatori}">
								<tr>						
									<td>${operatore.cuaa}</td>
									<td>${operatore.partitaIVA}</td>
									<td>${operatore.denomSpedizioniere}</td>
									<td>${operatore.provinciaSedeLegale}</td>	
									<td>${operatore.codiceRUOP}</td>
									<td>${operatore.codiceCentroAz}</td>									
									<td>${operatore.tipoPassaporto}</td>
									<td>${operatore.ispettoreAssegnatario}</td>									
									<spring:url value="/controlli" var="controlliAction" />
									<td nowrap style="white-spaces: nowrap">
                    				   <!--  <a href="${controlliAction}/dettaglio/${operatore.idSpedizioniere}"
										  title="Visualizza"
										  class="btn btn-floating btn-floating-medium light-blue accent-3">
											<i class="material-icons">visibility</i>
					                    </a>-->
					                    <c:if test="${operatore.idStatoComunicazione == null}">					                
					                        <a href="${controlliAction}/azienda/modifica/${operatore.idSpedizioniere}-${operatore.idCentroAziendale}"
					                        title="Modifica"
					                        class="btn btn-floating btn-floating-medium light-green accent-3">
					                        <i class="material-icons">mode_edit</i>
										  </a>
										</c:if> 
					                    <c:if test="${operatore.idStatoComunicazione != null && statoAnnullata != operatore.idStatoComunicazione.longValue()}">					                
					                        <a href="${controlliAction}/azienda/modifica/${operatore.idSpedizioniere}-${operatore.idCentroAziendale}"
					                        title="Modifica"
					                        class="btn btn-floating btn-floating-medium light-green accent-3">
					                        <i class="material-icons">mode_edit</i>
										  </a>
										</c:if>										
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="valign-wrapper" style="justify-content: space-between">
				<span class="left" id="total_reg"></span>
				<ul class="pagination pager right" id="myPager"></ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="row">
				<div id="richiesteWarning" class="card card-alert orange lighten-5">
					<div class="card-content orange-text valign-wrapper center"
						style="">
						<i class="material-icons large" style="font-size: 36px">warning</i>
						<p>&nbsp;&nbsp;Non sono stati trovati operatori</p>
					</div>
					<button type="button" class="close orange-text"
						data-dismiss="alert" aria-label="Chiudi">
						<span aria-hidden="true">×</span>
					</button>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	
	
	

	<content tag="script"> <c:set var="locale">${pageContext.response.locale}</c:set>
	<script>
	
	<!-- Per popolare l'autocomplete del genere e popolare la specie -->
	$(document).ready(function() {	
		// Reperimento dei generi e popolazione Specie	
		console.log('Reperimento dei generi e popolazione Specie');
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
			$selectRef.formSelect();
			inizializzaMultiselect($selectRef);
		}
		
		function getOptionSpecie(specie) {
			return getOption(specie.idSpecie, specie.denomSpecie);
	    }
		// fine Gestione combo Genere e specie   
 
		
		
    // Richiamata quando viene selezionato il pulsante 'PULISCI'
    $(function () {
        $("#btnDeleteAll").bind("click", function () { 
        	console.log('PULISCI');
            $("#specieSelect option").remove();
            
            $(this).closest('form').clearForm();
        	M.updateTextFields();
        	$('select').formSelect();
        });
    });  

				
</script> </content>

</body>

</html>