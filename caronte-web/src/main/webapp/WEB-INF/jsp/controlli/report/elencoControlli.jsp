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
			<spring:url value="/controlli/report/cercaControlli" var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaReportForm" accept-charset="utf-8">				
				<div class="card-content col s12">
					<span class="card-title">Ricerca report</span>
					<div class="row">
			            <div class="input-field col s12 m8 l6">
							<form:input type='text' id="ragioneSociale" path="ragioneSociale" />
							<form:errors path="ragioneSociale" cssClass="error" />
							<label for="ragioneSociale">Ragione sociale</label>
						</div>
						<div class="input-field col s12 m8 l6">
							<form:input type='text' id="codiceRuop" path="codiceRuop" />
							<form:errors path="codiceRuop" cssClass="error" />
							<label for="codiceRuop">Codice RUOP</label>
						</div>			          
			          	<div class="input-field col s12 m8 l6">
							<form:input type='text' id="dataControlloInizio" path="dataControlloInizio"
								cssClass="datepicker" />
							<form:errors path="dataControlloInizio" cssClass="error" />
							<label for="dataControlloInizio">Data controllo inizio</label>
						</div>
						<div class="input-field col s12 m8 l6">
							<form:input type='text' id="dataControlloFine" path="dataControlloFine"
								cssClass="datepicker" />
							<form:errors path="dataControlloFine" cssClass="error" />
							<label for="dataControlloFine">Data controllo fine</label>
						</div>
						<div class="input-field col s12 m8 l6">
							<form:input type='text' id="tipologiaAttivita" path="tipologiaAttivita" />
							<form:errors path="tipologiaAttivita" cssClass="error" />
							<label for="tipologiaAttivita">Tipologia attivita </label>
						</div>
			          	<div class="input-field col s12 m8 l6">
							<form:input type='text' id="partitaIva" path="partitaIva" />
							<form:errors path="partitaIva" cssClass="error" />
							<label for="partitaIva">Partita Iva</label>
						</div>
						<div class="input-field col s12 m8 l6">
						<form:select id="provincia" path="idProvincia">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaProvince}" itemValue="idProvincia"
								itemLabel="denomProvincia" />
						</form:select>
						<form:errors path="idProvincia" cssClass="error" />
						<label for="idProvincia">Provincia</label>
					</div>
					<div class="input-field col s12 m8 l6">
						<form:select id="idComune" path="idComune">
							<form:option value="" label="Selezionare" />
							<form:options items="${listaComuni}" itemValue="idComune"
								itemLabel="denomComune" />
						</form:select>
						<form:errors path="idComune" cssClass="error" />
						<label for="idComune">Comune</label>
					</div>	
			          
						<div class="input-field col s12 m8 l6">
			              <form:select id="idIspettore" path="idIspettore">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaIspettori}" itemValue="idIspettore"
									itemLabel="descIspettore" />
							</form:select>
              			<form:errors path="idIspettore" cssClass="error" />
              			<label for="idIspettore">Ispettore</label>
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
				<a href='<spring:url value="/controlli/report/esportaControlli"/>' class="btn green waves-effect waves-light">ESPORTA</a>
			</sec:authorize>		   
	</div>	

	<c:choose>
		<c:when test="${not empty elencoReport}">

			<div class="row">
				<div class="col-s12">

					<table id="tabellaRichieste" class="data-table striped display">
						<thead>
							<tr>								
								
								
								<th>Ragione Sociale</th>
								<th>Cuaa</th>	
								<th>Codice ruop</th>
								<th>Partita iva</th>		
								<th>Centro Aziendale</th>								
								<th>indirizzo</th>
								<th>Data controllo</th>
								<th>Numero Verbale</th>	
								
	
																				
							</tr>
						</thead>
						<tbody id="bodyTabellaComunicazioni">
						  
			
			<c:forEach var="report" items="${elencoReport}">
				<tr>
						
									<td>${report.ragioneSociale}</td>
									<td>${report.cuaa}</td>
									<td>${report.codiceRuop}</td>
									<td>${report.partitaIva}</td>
									<td>${report.codCentroAziendale}</td>									
									<td>${report.indirizzo}</td>									
									<td><fmt:formatDate value="${report.dataControllo}"	pattern="dd/MM/yyyy" /></td>
									<td>${report.numeroVerbale}</td>																
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
        	
        	$("#ragioneSociale").val('');
            $("#codiceRuop").val('');
            $("#provincia").val('');
            $("#idComune").val('');
            $("#pIva").val('');
            $("#ispettore").val('');
            $("#organismoNocivo").val('');
            
            $(this).closest('form').clearForm();
        	M.updateTextFields();
        });
    });  
    
  //Popolamento dinamico della select Comune
	  $('#provincia').change(
				function() {
					var params = {
						"idProvincia" : $(this).val()
					}
	
					getSelectAsincrona(
							'idComune',
							'<spring:url value="/ajax/getComuniProvincia" />',
							params,
							getOptionComune, 1);
		});  

	  function getOptionComune(comune) {
		  return getOption(comune.idComune, comune.denomComune);
		} 

    </script>  
	
 </content>
	
	

</body>

</html>