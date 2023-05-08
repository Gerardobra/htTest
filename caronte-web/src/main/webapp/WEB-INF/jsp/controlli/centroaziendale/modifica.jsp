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
.modal { 
	width: 80% !important; 
	max-height: 95% !important; 
	top: 2px !important;
	overflow: visible;
} 
</style>
</head>
<body>
    
	<spring:url value="/controlli/centroaziendale/modifica" var="formAction" />
	<form:form action="${formAction}" method="post" modelAttribute="nuovoControlloForm" accept-charset="utf-8">
		<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>AZIENDA : 
					<c:if test="${not empty nuovoControlloForm.denomSpedizioniere}">
						${nuovoControlloForm.denomSpedizioniere}  
					</c:if>
					<c:if test="${empty nuovoControlloForm.denomSpedizioniere}">
						${nuovoControlloForm.cognome} ${nuovoControlloForm.nome} 
					</c:if>
				</b></h3>
			</div>
			<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
  			<spring:eval var="statoInBozza"	expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />	
  			<div class="card-content" style="padding-top:5px;">
  				<div class="row">
   	 				<div class="col s12">
      					<ul class="tabs">
      						
        					<li class="tab" >
        						<a href="<spring:url value="/controlli/azienda/modifica" />" target="_self">Dati operatore</a>
        					</li> 
        					
			        		<li class="tab" >
		        				<a class="active"><b>Centro Aziendale</b></a>
		        			</li>
        					
			    <!--   	    <c:forEach var="centroAziendale" items="${listaCentriAziendali}">
				        		<c:if test="${centroAziendale.idCentroAziendale == nuovoControlloForm.idCentroAziendale}">
					        		<li class="tab" >
		        						<a class="active"><b>C. Aziendale ${centroAziendale.codCentroAziendale}</b></a>
		        					</li>
				        		</c:if>
				        		<c:if test="${centroAziendale.idCentroAziendale != nuovoControlloForm.idCentroAziendale}">
					        		<li class="tab" >
		        						<a href="<spring:url value="/controlli/centroaziendale/modifica/${centroAziendale.idCentroAziendale}" />" target="_self">C. Aziendale ${centroAziendale.codCentroAziendale}</a>
				        			</li>
				        		</c:if>
				        		
		        			</c:forEach> 	--> 
							      					
      					</ul>
    				</div>
  				</div>
        	  <div class="card-panel">	
        	     <div class="row">
		  					<p class="center-align"
									style="font-size: 20px; margin-bottom: 5px;">
									<em>Dati del centro aziendale</em>
							</p>
		  		 </div>			
        		  															
				<!-- DATI CENTRO AZIENDALE-->
				<div class="row">
					<div class="input-field col s6 m4 l6">
						<input type="text" id="codCentroAziendale" 
							value="${fn:escapeXml(centroAziendale.descrStatoAzienda)}" disabled="disabled" /> 
						<label for="codCentroAziendale">Stato centro aziendale</label>	
					</div>
					<div class="input-field col s6 m4 l6">
						<input type="text" id="codCentroAziendale" 
							value="${fn:escapeXml(centroAziendale.codCentroAziendale)}" disabled="disabled" /> 
						<label for="codCentroAziendale">Codice centro aziendale</label>	
					</div>
					<div class="input-field col s6 m4 l6">
						<input type="text" id="denominazioneCa" 
							value="${fn:escapeXml(centroAziendale.denominazioneCa)}" disabled="disabled" /> 
						<label for="denominazioneCa">Denominazione</label>	
					</div>
					<div class="input-field col s6 m4 l6">
						<input type="text" id="contattoSFR" 
							value="${fn:escapeXml(centroAziendale.cognomeRP)} ${fn:escapeXml(centroAziendale.nomeRP)}" disabled="disabled" /> 
						<label for="contattoSFR">Persona di contatto per il SFR</label>	
					</div>
					<div class="input-field col s6 m4 l6">
						<input type="text" id="telefonoRP" 
							value="${fn:escapeXml(centroAziendale.telefonoRP)}" disabled="disabled" /> 
						<label for="telefonoRP">Telefono SFR</label>	
					</div>
					<div class="input-field col s6 m4 l6">
						<input type="text" id="denomProvinciaCa" 
							value="${fn:escapeXml(centroAziendale.denomProvinciaCa)}" disabled="disabled" /> 
						<label for="denomProvinciaCa">Provincia</label>	
					</div>
					<div class="input-field col s6 m4 l6">
						<input type="text" id="denomComuneCa" 
							value="${fn:escapeXml(centroAziendale.denomComuneCa)}" disabled="disabled" /> 
						<label for="denomComuneCa">Comune</label>	
					</div>
					
					<div class="input-field col s6 m4 l6">
						<input type="text" id="capCa" 
							value="${fn:escapeXml(centroAziendale.capCa)}" disabled="disabled" /> 
						<label for="capCa">Cap</label>	
					</div>
					<div class="input-field col s6 m4 l6">
						<input type="text" id="indirizzoCa" 
							value="${fn:escapeXml(centroAziendale.indirizzoCa)}" disabled="disabled" /> 
						<label for="indirizzoCa">Indirizzo (Es. Via Roma, 24)</label>	
					</div>
					<div class="input-field col s6 m4 l6">
						<input type="text" id="frazioneCa" 
							value="${fn:escapeXml(centroAziendale.frazioneCa)}" disabled="disabled" /> 
						<label for="frazioneCa">Frazione</label>	
					</div>
				</div>				
				  
				<div class="row col l12">
					<div class="input-field col s6 m4 l6">
						<form:input type="text" id="telefonoCa"
							path="telefonoCa" cssClass="campiAz"  maxlength="40" />
						<form:errors path="telefonoCa" cssClass="error" />
						<label for="telefonoCa">Telefono (Es. 0245673467)</label>
					</div>
					<div class="input-field col s6 m4 l6">
						<form:input type="text" id="cellulareCa"
							path="cellulareCa" cssClass="campiAz"  maxlength="30" />
						<form:errors path="cellulareCa" cssClass="error" />
						<label for="cellulareCa">Cellulare (Es. 3471234567)</label>
					</div>
					
				</div>
							
				<div class="row col l12">																		
					<div class="input-field col s6 m4 l6">
									<form:input type="email" id="emailCa"
										 path="emailCa" />
									<form:errors path="emailCa" cssClass="error" />
									<label for="emailCa">Email</label>
					</div>
					<div class="input-field col s6 m4 l6">
									<form:input type="email" id="pecCa"
										 path="pecCa" required=""	 />
									<form:errors path="pecCa" cssClass="error" />
									<label id="pecCa">PEC</label>

					</div>
				</div>
				
				<div class="row col l12">																		
					<div class="input-field col s6 m4 l6">
							<form:select id="idIspettore" path="idIspettore">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaIspettori}" itemValue="idIspettore"
									itemLabel="descIspettore" />
							</form:select>
							<form:errors path="idIspettore" cssClass="error" />
							<label for="idIspettore">Ispettore/Agente</label>
						</div>
					<div class="input-field col s6 m4 l6">
							<form:select id="idTipologiaPassaporto" path="idTipologiaPassaporto">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaTipologiaPassaporto}" itemValue="idTipologiaPassaporto"
									itemLabel="descEstesa" />
							</form:select>
							<form:errors path="idTipologiaPassaporto" cssClass="error" />
							<label for="idTipologiaPassaporto">Tipologia passaporto</label>
						</div>
				</div>
				<div class="row col l12">	
						<div class="input-field col s6 m4 l6">
							<form:input type="text" id="tariffa"
								path="tariffa" class="validate" pattern="\d+([\.,]\d{1,4})?" maxlength="9"/>
							<form:errors path="tariffa" cssClass="error" />
							<label for="tariffa">Tariffa</label>
						</div>
				</div>		
				
				<c:if test="${isSuperUser}">
					<div class="row">
						<a
								href='<spring:url value="/controlli/datiBase/nuova"/>'
								title="Aggiungi Controllo"
								class="btn green darken-1 waves-effect waves-light right"
								style="display: inline-block"
								>AGGIUNGI CONTROLLO</a>
				   	</div>
				</c:if>
				
				
				
				
				
				    <div class="row">
				        <table id="tabellaControlli" class="data-table striped display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">
				          <thead>
				            <tr>
				              <th>Numero verbale</th>
				              <th>Esito controllo</th>
				              <th>Data</th>	
				              <th>Azione</th>			              
				            </tr>
				          </thead>
				          <tbody id="bodytabellaControlli">
				            <c:forEach var="controllo" items="${listaControlli}">				              
				              		<tr>																																	
										<td>${controllo.numeroVerbaleIS} </td>	
										<td>${controllo.esitoControllo} </td>				    				
										<td><fmt:formatDate value="${controllo.dataControllo}" pattern="dd/MM/yyyy" /></td>
										<td style="white-spaces: nowrap">
								
										<a href='<spring:url value="/controlli/dettaglio/${controllo.idControllo}" />'
										        title="Visualizza"
										  		class="btn btn-floating btn-floating-medium light-blue accent-3">
												<i class="material-icons">visibility</i>
					                     </a>
					            
					            <c:if test="${isSuperUser}">																						
											 <a href='<spring:url value="/controlli/datiBase/modifica/${controllo.idControllo}" />'
											 	title="Modifica"
												class="btn btn-floating btn-floating-medium light-green accent-3">
												<i class="material-icons">mode_edit</i>
											</a> 
									<c:if test="${controllo.numeroVerbaleIS == null}">	
												<a href='<spring:url value="/controlli/elimina/${controllo.idControllo}" />'
											 	title="Elimina"
												class="btn btn-floating btn-floating-medium red accent-4"
												onclick="return eliminaControllo(this)"> 
												<i class="material-icons" onclick="javascript:eliminaControllo(this)">delete</i>
											</a> 
									</c:if>	
								</c:if>		   
										</td>							
								      </tr>
							     						     
							 </c:forEach>
				          </tbody>	
				          											          				          				          
				        </table>                      
			      	</div>
				
			 </div>	
				 	 		
			</div>
		</div>	
		
       	<div class="row">
			<a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
			<button class="btn confirm waves-effect waves-light right" type="submit" name="datiAzienda">SALVA</button>
	   	</div>
	   
	</form:form>  		
	

	<br />
	 
	<content tag="script"> 
		<script>
		
		function eliminaControllo(link) {
			swal(
					{
						title : 'Attenzione!',
						html : 'Si desidera cancellare il controllo?',
						type : 'question',
						showCancelButton : true,
						confirmButtonText : 'Cancella',
						confirmButtonClass : 'btn amber darken-1 waves-effect waves-light',
						cancelButtonText : 'Annulla',
						cancelButtonClass : 'btn',
						focusConfirm : false,
						buttonsStyling : false,
					})
					.then(
							function(result) {
								if (result.value) {
									console.log("prima di windows location " );
									window.location.href = $(link).attr('href');
									}

							});

			return false;
		}
	
			
		</script> 
	</content>

</body>
</html>