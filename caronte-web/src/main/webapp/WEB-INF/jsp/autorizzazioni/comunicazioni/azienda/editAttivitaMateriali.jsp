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
	<h5 class="primary-color bold">Aggiungi attività azienda</h5>	
	<spring:url value="/autorizzazioni/comunicazioni/azienda/editAttivitaMateriali" var="formAction" />	
	<form:form action="${formAction}" method="post" modelAttribute="modaliForm" accept-charset="utf-8">
	<div class="card">
		<div class="card-content"> 
		    <div class="row">
		    	<form:input type="hidden" path="idAttivitaMaterialeUtente" value="${idAttivitaMaterialeUtente}"/>
		    	<div class="input-field col s6 m4 l6">							
					<form:select id="idTipoAttivitaMat" name="idTipoAttivitaMat" path="idTipoAttivitaMat" 
						 class="validate" required="required">
						<form:option value="" label="Selezionare" />
						<form:options items="${listaTipologieAttivita}" itemValue="idTipoAttivita"
							itemLabel="descEstesa" />														
					</form:select>	
					<form:errors path="idTipoAttivitaMat" cssClass="error" />								
					<label for="labelidTipoAttivita">Tipologia attività *</label>
				</div>
				<div id="divNote" class="input-field col s6 m5 l6">
	                <form:textarea id="note" path="note"
	                  cssClass="materialize-textarea validate" rows="2"
	                  data-length="1000" maxlength="1000" />
	                <form:errors path="note" cssClass="error" />
	                <label for="note">Note attività</label>
              	</div>						       
		    </div>
		    <div class="row">
		    <c:if test="${idAttivitaMaterialeUtente != null}">		    
		    	<div id="divMateriale" class="input-field col s6 m4 l12">							
					<form:select id="idMateriale" name="idMateriale" path="idMateriale">
						<form:option value="" label="Selezionare" />
						<form:options items="${listaMateriali}" itemValue="idMateriale"
							itemLabel="descEstesa" />
					</form:select>
					<form:errors path="idMateriale" cssClass="error" />
					<label for="labelidMateriale">Materiale</label>
				</div>
			</c:if>
			<c:if test="${idAttivitaMaterialeUtente == null}">	
				 <div id="divMateriale" class="input-field col s6 m4 l6">	
	              <select name="idMaterialeList" id="idMaterialeList" multiple="multiple"
	                	class="multiselect">
	                	<option value="" disabled="">Selezionare</option>
	              </select>
	              <label>Materiale</label>
	            </div>			
			</c:if>
            </div>	
        
			<div class="card-action">
           		<div class="row">									
           			<div class="col s3">
						<a class="btn orange lighten-1 modal-action modal-close waves-effect back"> CHIUDI </a>
					</div>
					<div class="col s3 right" style="text-align: right">
	               		<button class="btn confirm waves-effect waves-light" type="button" 
	               			style="display: inline-block"> SALVA </button>
	               		<button type="submit" style="display:none"></button>
					</div>
           		</div>
			</div>
		</div>
	</div>
	</form:form>
	
	<script>
	
	function editInit(idAttivitaMaterialeUtente) {
		console.log('In EDIT INIT modale  - idAttivitaMaterialeUtente: ' + idAttivitaMaterialeUtente);
		
		inizializePage(idAttivitaMaterialeUtente);

	}
	
	function inizializePage(idAttivitaMaterialeUtente){
		console.log('inizializePage()');

		if (idAttivitaMaterialeUtente == null) {
			// in aggiungi	
			$("#divNote").hide();
			$("#note").val("");
			$("#note").removeAttr("required","required");
		} else {
			// in modifica
			console.log('Modifica note:'+$("#note").val().trim().length);
			if ($("#note").val().trim().length > 0) {
				$("#divNote").show();
				$("#note").attr("required","required");
			} else {
				$("#divNote").hide();
			}
			
		}		
		
		console.log('idTipoAttivitaMat = '+$("#idTipoAttivitaMat").val());
		if ($("#idTipoAttivitaMat").val() != null) {			
			popolaListaMateriali($("#idTipoAttivitaMat").val());
		}
		$("select").formSelect();
		M.updateTextFields();
		
		//ricarica correttamente quando ritorna in pagina 
		//$("#idMateriale").formSelect();
		
		// al caricamento della pagina abilito le label per mostrare gli eventuali errori
        $("label[for='labelidTipoAttivita']").addClass("active");	
		
	}
	
	/*
	 * Prima di definire l'handler sul click si resetta lo stesso handler nel caso 
	 * fosse stato associato a una funzione in un precedente caricamento della pagina
	 */
	$('#editAttivitaMaterialiModal').find('button.confirm').off().on('click', function(e) {
		e.preventDefault();
		console.log('onClick Salva modale');
		salvaModificaAttivitaMateriali($(this).closest('form'), $('#idAttivitaMaterialeUtente').val(), $("#idTipoAttivitaMat").val());
	});
	
	
	$(document).ready(function() {
		console.log('in READY idAttivitaMaterialeUtente: '+$('#idAttivitaMaterialeUtente').val());
		$("#notifica").hide();
		inizializePage($('#idAttivitaMaterialeUtente').val());

	});

	function reloadMultiselect($selectRef) {
		console.log('reloadMultiselect');
		$selectRef.formSelect();
		inizializzaMultiselect($selectRef);
	}

	function getOptionMateriale(materiale) {
		return getOption(materiale.idMateriale, materiale.descEstesa);
	}

	//  combo Materiale
	// fare in modo che il primo elemento visibile nella select sia "Selezionare"
	function popolaListaMateriali(idTipoAttivita) {
		console.log('popolaListaMateriali');
		console.log('#idMateriale = '+$('#idMateriale').val());
		console.log('#idMaterialeList ='+$('#idMaterialeList').val());
		if ($('#idMateriale').val() !== undefined ) {
			$selectMateriale = $('#idMateriale');
		} else {
			$selectMateriale = $('#idMaterialeList');	
		}
		console.log('popolaListaMateriali selectAttivita = '
				+ idTipoAttivita);
		if (idTipoAttivita > 0) {
			var params = {
				"idTipoAttivitaSel" : idTipoAttivita
			};
			getSelectAsincronaRef(
					$selectMateriale,
					'<spring:url value="/ajax/getListaMateriliByIdTipoAttivita" />',
					params, getOptionMateriale, 1, reloadMultiselect);
		}

	}


	//mostra tutti i materiali o alcuni in base alla tipologia selezionata
	$('#idTipoAttivitaMat').change(function() {
		var idTipoAttivita = $("#idTipoAttivitaMat option:selected").val();
		console.log("idTipoAttivita selezionata = " + idTipoAttivita);
		popolaListaMateriali(idTipoAttivita);
		console.log("idTipoAttivita = "+idTipoAttivita);
		
		if (idTipoAttivita == 8) {
			// se è tipo attività Altro
			$("#divNote").show();
			$("#note").attr("required","required");
		} else if(idTipoAttivita == 1){
			/*Swal.fire({
				  title : 'Compilare tab passaporto',
				  type : 'success',
				  confirmButtonText : 'OK',
			});*/
		}else {				
			$("#divNote").hide();
			$("#note").val("");
			$("#note").removeAttr("required","required");	
		}
	});
		
		
	</script>
	
</body>
</html>