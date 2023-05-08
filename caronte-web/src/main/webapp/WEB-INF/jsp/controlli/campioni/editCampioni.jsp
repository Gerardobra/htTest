<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<style>
.dropdown-content > li {
  max-height: 38px !important;
}

</style>
</head>
<body>	   		
	<h5 class="primary-color bold">Modifica campione</h5>	
	<spring:url value="/controlli/campioni/editCampioni" var="formAction" />	
	<form:form action="${formAction}" method="post" modelAttribute="modaliForm" accept-charset="utf-8">
	<div class="card">
		<div class="card-content"> 
		    <div class="row">
		    	<form:input type="hidden" path="idCampione" value="${idCampione}"/>
		    	<div class="row col l12">									
					<div class="input-field col s6 m4 l4">
						<form:input type='text' id="dataRdp"
							path="dataRdp" cssClass="datepicker"/>
						<form:errors path="dataRdp" cssClass="error" />
						<label for="dataRdp">Data RDP</label>
					</div>
					<div class="input-field col s12 m8 l4">
						<form:select id="esitoRdp" name="esitoRdp"
							path="esitoRdp">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaEsitiRdp}"
								itemValue="cod" itemLabel="descr" />
						</form:select>
						<form:errors path="esitoRdp" cssClass="error" />
						<label for="labelesitoRdp">Esito RDP</label>
					</div>
					<div class="input-field col s12 m8 l4">
						<form:input type="text" name="numeroRdp"
							id="numeroRdp" path="numeroRdp"/>
						<form:errors path="numeroRdp" cssClass="error" />
						<label for="numeroRdp">Numero RDP</label>
					</div>
			  	</div>	
			    <div class="row col l12">
					<div class="input-field col s12 m8 l12">
						<form:select id="orgNocAccertato" name="orgNocAccertato" multiple="multiple"
							path="orgNocAccertato" class="multiselect">											
							<form:options items="${listaOrgNocAccertato}"
								itemValue="idOrgNocivo" itemLabel="descBreve" />
						</form:select>
						<form:errors path="orgNocAccertato" cssClass="error" />
						<label for="labelorgNocAccertato" class="active">Organismo nocivo accertato</label>
					</div>						
				</div>
						
				<div class="row col l12">	
					<div class="input-field col s12 m8 l12">
						<form:textarea id="noteCampione" path="noteCampione"
							cssClass="materialize-textarea" rows="2"
							data-length="1000" maxlength="1000" />
						<form:errors path="noteCampione" cssClass="error" />
						<label for="noteCampione">Note</label>
					</div>
				  </div>	
			</div>
		</div>
		<div class="card-action">
   			<div class="row">									
       			<div class="col s3">
					<a class="btn orange lighten-1 modal-action modal-close waves-effect back" onclick="funzChiudi()"> CHIUDI </a>
				</div>
				<div class="col s3 right" style="text-align: right">
               		<button class="btn confirm waves-effect waves-light" type="button" 
               			style="display: inline-block"> SALVA </button>
               		<button type="submit" style="display:none"></button>
				</div>
   			</div>
		</div>
	</div>
	</form:form>
	
	
	<script>
	function funzChiudi(){

		var pathname = window.location.pathname;
		console.log('pathname = ' + pathname);
		window.location = pathname; // url dove varrà ridirezionato
		
		}
	
	
	function editInit(idCampione) {
		console.log('In EDIT INIT modale  - idCampione: ' + idCampione);
		
		inizializePage(idCampione);

	}
	
	function inizializePage(idCampione){
		console.log('inizializePage()');

		var datepicker_firstDay = 1;
		var datepicker_format = 'dd/mm/yyyy';
		var datepicker_i18n = {
				cancel:	'Chiudi',
				clear: 'Cancella',
				done: 'Ok',
				months: [ 'gennaio', 'febbraio', 'marzo', 'aprile', 'maggio', 'giugno', 'luglio', 'agosto', 'settembre', 'ottobre', 'novembre', 'dicembre' ],
				monthsShort: [ 'gen', 'feb', 'mar', 'apr', 'mag', 'giu', 'lug', 'ago', 'set', 'ott', 'nov', 'dic' ],
				weekdays: [ 'domenica', 'lunedì', 'martedì', 'mercoledì', 'giovedì', 'venerdì', 'sabato' ],
				weekdaysShort: [ 'dom', 'lun', 'mar', 'mer', 'gio', 'ven', 'sab' ],
				weekdaysAbbrev: ['D', 'L', 'M', 'M', 'G', 'V', 'S']
			};
		
		function getDatepickerInitDate($input) {
			var splitDate = $input.val().split("/");
			return new Date(splitDate[2], splitDate[1] - 1, splitDate[0])
		}
		
		$('.datepicker').datepicker({
	        showMonthAfterYear : true, // Creates a dropdown to control month
	        showClearBtn: true,
	        defaultDate: getDatepickerInitDate($element),
	        setDefaultDate: true,
	        yearRange : 10, // Creates a dropdown of 20 years to control year,
	        autoClose : true, // Close upon selecting a date,
	        firstDay: datepicker_firstDay,
	        format: datepicker_format,
	          i18n : datepicker_i18n,
	          onClose: function () {
	            $(document.activeElement).blur()
	          }
	      });	
		
		$("select").formSelect();
		M.updateTextFields();
		
		//ricarica correttamente quando ritorna in pagina 
		//$("#idMateriale").formSelect();
		
		// al caricamento della pagina abilito le label per mostrare gli eventuali errori
       // $("label[for='labelidTipoAttivita']").addClass("active");	
		
	}
	
	/*
	 * Prima di definire l'handler sul click si resetta lo stesso handler nel caso 
	 * fosse stato associato a una funzione in un precedente caricamento della pagina
	 */
	$('#editCampioniModal').find('button.confirm').off().on('click', function(e) {
		e.preventDefault();
		console.log('onClick Salva modale');
		salvaModificaCampioni($(this).closest('form'), $('#idCampione').val());
	});
	
	
	$(document).ready(function() {
		console.log('in READY idCampione: '+$('#idCampione').val());
	
		inizializePage($('#idCampione').val());	   
			

	});

	function reloadMultiselect($selectRef) {
		console.log('reloadMultiselect');
		$selectRef.formSelect();
		inizializzaMultiselect($selectRef);
	}







		
		
	</script>
	
</body>
</html>