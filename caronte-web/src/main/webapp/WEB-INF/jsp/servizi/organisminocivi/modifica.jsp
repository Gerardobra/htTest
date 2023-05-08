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
	<spring:url value="/admin/organisminocivi/modifica" var="formAction" />
	<form:form action="${formAction}" method="post" modelAttribute="nuovoOrganismoNocivoForm" accept-charset="utf-8" id="formId">
		<div class="card">
			<div class="card-content">
				<span class="card-title">Modifica organismo nocivo</span></br>
				<div class="col s12">
					<div class="row">						
							<div class="row col l12">							
							
							<!------ INPUT: CODICE EPPO -------->							
							<div class="input-field col s6 m4 l6">
								<form:input type="text" id="nuovoCodiceEppo" path="nuovoCodiceEppo" maxlength="40" required="required" cssClass="validate"/>
								<form:errors path="nuovoCodiceEppo" cssClass="error" />
								<label for="nuovoCodiceEppo">Codice Eppo *</label>
							</div>
							<!------ INPUT: DESCRIZIONE ORGANSMO NOCIVO -------->
							<div class="input-field col s12 m8 l6">
								<form:input type="text" id="descrizioneOrganismoNocivo" path="descrizioneOrganismoNocivo" required="required" maxlength="40"/>
								<form:errors path="descrizioneOrganismoNocivo" cssClass="error" />
								<label for="descrizioneOrganismoNocivo">Descrizione organismo nocivo *</label>
							</div>
							
							</div>
						</div>
						<div class="row">						
							<div class="row col l12">																
								<div class="col l8">
									<label> <form:checkbox value="S"
											id="orgNocInZonaProtetta" path="orgNocInZonaProtetta" />
										<span>Organismo nocivo in Zona protetta</span>
									</label>
								</div>
							</div>							
							</div>
						</div>
				</div>
			</div>
			<!--  PARTE PER AGGIUNGERE GENERI E SPECIE -->
			<div class="card">
			<div class="card-content">
				<span class="card-title">Aggiungi : Tipo Organismo Nocivo, Tipo Prodotto, Genere e Specie per l'Organismo Nocivo</span></br>
				<div class="col s12">
					<div class="row">						
						<!------ SELECT::TIPO ORGANISMO NOCIVO -------->
						<div class="input-field col s12 m8 l4">
							<form:select id="idTipoOrgNocivo" path="idTipoOrgNocivo" cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${selectTipiON}" itemValue="idTipoOrgNocivo"
									itemLabel="descBreve" />
							</form:select>
							<form:errors path="idTipoOrgNocivo" cssClass="error" />
							<label for="ruolo">Tipo organismo Nocivo *</label>
						</div>
						<!------ SELECT::TIPO PRODOTTO -------->
						<div class="input-field col s12 m8 l8">
							<form:select id="idTipoProdotto" path="idTipoProdotto" cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${selectTipiProdotto}" itemValue="idTipoProdotto"
									itemLabel="descTipoProdotto" />
							</form:select>
							<form:errors path="idTipoProdotto" cssClass="error" />
							<label for="ruolo">Tipo prodotto *</label>
						</div>	
					</div>										
				  	<div class="row l12" id="divGenereSpecie">
						<div class="input-field col s12 m8 l6">
							<form:select id="idGenere" path="idGenere" cssClass="validate" onchange="getspecie();">
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
					<div class="row">
						<button class="btn green waves-effect waves-light" style=""
							type="submit" name="aggiungiGenere" id="aggiungiGenere">
							AGGIUNGI
						</button>
					</div>
					  
			   </div>
				   <div class="row">
						<table id="tabellaGeneriSpecie"
							class="data-table striped display" data-paging="false"
							data-info="false">
							<thead>
								<tr>
									<th></th>
									<th>Tipo Organismo Nocivo</th>
									<th>Tipo Prodotto</th>
									<th>Genere</th>
									<th>Specie</th>													
								</tr>
							</thead>
							<tbody id="bodyTabellaZonaProt">
								<c:forEach var="zonaProt" items="${orgNociviList}">
									<tr>										
										<td>
											<a href='<spring:url value="/admin/organisminocivi/elimina/${zonaProt.idZonaProtetta}" />'
												title="Elimina"
												class="btn btn-floating btn-floating-medium deep-orange accent-2"
												onclick="return eliminaCampione(this)"> <i
												class="material-icons">delete</i>
											</a>
										</td>
										<td>${zonaProt.descTipoOrgNocivo}</td>
										<td>${zonaProt.descTipoProdotto}</td>
										<td>${zonaProt.denomGenere}</td>
										<td>${zonaProt.denomSpecie}</td>																			
									</tr>
								</c:forEach>
							</tbody>
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
						<button class="btn confirm waves-effect waves-light"
							onclick="return confermaModifica()">SALVA</button>
					</div> 
					
				</div>
				<button class="btn confirm waves-effect waves-light" type="submit" style="display: none" id="pulsanteSubmit"
							name="datiOrganismoNocivo">SALVA</button>
			</div>
		</div>
	</form:form>

	<content tag="script"> 
		<script type="text/javascript">
		
		var eppo = document.getElementById('nuovoCodiceEppo').value;
		var desc = document.getElementById('descrizioneOrganismoNocivo').value;
		var flagZp = document.getElementById('orgNocInZonaProtetta').checked;

		function confermaModifica() {
			console.log(flagZp);
			console.log(document.getElementById('orgNocInZonaProtetta').checked);
			if(document.getElementById('nuovoCodiceEppo').value == eppo &&
			   document.getElementById('descrizioneOrganismoNocivo').value == desc &&
			   document.getElementById('orgNocInZonaProtetta').checked == flagZp	){
					window.location.href = '<spring:url value="/admin/organisminocivi/elenco"/>';
			}else{
				document.getElementById('pulsanteSubmit').click();
				}

		return false;
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
    	
		function getspecie(){
			
			var idGenere = $("#idGenere").val();
			if(idGenere != ""){
				$selectSpecie = $('#divSpecie').find('select[name=specie]');
				var params = {
						"idGenere" : idGenere
				};
				getSelectAsincronaRef($selectSpecie,
								'<spring:url value="/ajax/getListaSpecieByIdGenere" />',
								params, getOptionSpecie, 1, reloadMultiselect);			
				
			}

		}


		
		</script>
		</content>
</body>