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
	<h5 class="primary-color bold">Modifica Organismi nocivi</h5>	
	<spring:url value="/controlli/controllofisico/editOrgNocFisico" var="formAction" />	
	<form:form action="${formAction}" method="post" modelAttribute="modaliForm" accept-charset="utf-8">
	<div class="card">
		<div class="card-content">
		
		    <div class="row">
		      <form:input type="hidden" path="idControlloFisicoSpecie" value="${idControlloFisicoSpecie}"/>
		      <form:input type="hidden" value="" name="numPiante" id="numPiante" path="numPiante"/>
		      <form:input type="hidden" value="" name="numPianteSintomatiche" id="numPianteSintomatiche" path="numPianteSintomatiche"/>
		      
		        <div class="input-field col s6 m4 l6">
					<form:input type="text" id="denomGenereFisico"
							path="denomGenereFisico"
							disabled="true" />							
					<label for="denomGenereFisico">Genere</label>
				</div>
				<div class="input-field col s6 m4 l6">
					<form:input type="text" id="denomSpecieFisico"
							path="denomSpecieFisico"
							disabled="true" />					
					<label for="denomSpecieFisico">Specie</label>
				</div>				
		    </div>
		     
		    <div class="row">		    			    
		    	<div class="input-field col s6 m4 l6">							
					<form:select id="idOrgNociviFisico"  multiple="multiple"
								path="idOrgNociviFisico" class="validate multiselect">											
								<form:options items="${listaOrganismiNociviModale}"
								itemValue="idOrgNocivo" itemLabel="descBreve" />
					</form:select>
					<form:errors path="idOrgNociviFisico" cssClass="error" />
					<label for="labelIdOrgNociviFisico" class="active">Organismo nocivo</label>
				</div>				
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
	
	function editInit(idControlloFisicoSpecie, idOrgNocivo, numPiante, numPianteSintomatiche) {
		console.log('BEGIN editInit');	
		inizializePage(idControlloFisicoSpecie, idOrgNocivo, numPiante, numPianteSintomatiche);
	}
	
	function inizializePage(idControlloFisicoSpecie, idOrgNocivo, numPiante, numPianteSintomatiche){
		console.log('BEGIN inizializePage');
		
		console.log('idControlloFisicoSpecie ='+idControlloFisicoSpecie);
		console.log('idOrgNocivo ='+idOrgNocivo);
		// seleziono la combo degli organismi nocivi con l'eventuale organismo nocivo presente nella pagina chiamante per quella riga
		if(idOrgNocivo != null && idOrgNocivo != ''){
			console.log('seleziono la combo degli organismi nocivi con organismo nocivo presente nella pagina chiamante');
			$("#idOrgNociviFisico").val(idOrgNocivo);
		}
		
		// Setto i campi hidden
		console.log('-- numPiante ='+numPiante);
		console.log('-- numPianteSintomatiche ='+numPianteSintomatiche);
		document.getElementById("numPiante").value = numPiante;
		document.getElementById("numPianteSintomatiche").value = numPianteSintomatiche;	


		$("select").formSelect();
		M.updateTextFields();		
	}
	
	/*
	 * Prima di definire l'handler sul click si resetta lo stesso handler nel caso 
	 * fosse stato associato a una funzione in un precedente caricamento della pagina
	 */
	$('#editOrgNocFisicoModal').find('button.confirm').off().on('click', function(e) {
		e.preventDefault();
		
		// Controllare che sia stato selezionato un organismo nocivo
		console.log('Controllare che sia stato selezionato un organismo nocivo');
		
		// get the select element
	    var elements = document.getElementById("idOrgNociviFisico").childNodes; 
	    // if we want to use only array of selected values we will set this array
	    var arrayOfSelecedIDs=[];

	    // loop over option values
	    for(i=0;i<elements.length;i++){
	           // if option is select then push it to object or array
	           if(elements[i].selected){	           
	            //push to array of selected values
	            arrayOfSelecedIDs.push(elements[i].value)
	           }           
	    }        	    
	    console.log('arrayOfSelecedIDs ='+arrayOfSelecedIDs);
		if(arrayOfSelecedIDs == null || arrayOfSelecedIDs.length == 0){		
			Swal.fire({
				type: 'error',
				title: 'Organismo nocivo non selezionato',
				text: 'Selezionare almeno un Organismo nocivo'
			})
		}
		else{
			console.log('-- Salva modale modifica organismi nocivi');
			salvaModificaOrganismiNocivi($(this).closest('form'), $('#idControlloFisicoSpecie').val(), $('#numPiante').val(), $('#numPianteSintomatiche').val());
		}
	});
	
	
	$(document).ready(function() {
		console.log('in READY idControlloFisicoSpecie: '+$('#idControlloFisicoSpecie').val());	
		console.log('in READY numPiante: '+$('#numPiante').val());
		console.log('in READY numPianteSintomatiche: '+$('#numPianteSintomatiche').val());
		
		inizializePage($('#idControlloFisicoSpecie').val(), '', $('#numPiante').val(), $('#numPianteSintomatiche').val());	   			
	});
	
	function reloadMultiselect($selectRef) {
		console.log('reloadMultiselect');
		$selectRef.formSelect();
		inizializzaMultiselect($selectRef);
	}



	
		
		
	</script>
	
</body>
</html>