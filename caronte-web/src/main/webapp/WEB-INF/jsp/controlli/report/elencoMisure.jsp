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
			<spring:url value="/controlli/report/cercaMisure" var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaReportForm" accept-charset="utf-8">				
				<div class="card-content col s12">
					<span class="card-title">Ricerca report</span>
					<div class="row">						
			            <div class="input-field col s12 m8 l6">
							<form:input type='text' id="dataMisuraInizio" path="dataMisuraInizio"
								cssClass="datepicker" />
							<form:errors path="dataMisuraInizio" cssClass="error" />
							<label for="dataMisuraInizio">Data misura inizio</label>
						</div>
						<div class="input-field col s12 m8 l6">
							<form:input type='text' id="dataMisuraFine" path="dataMisuraFine"
								cssClass="datepicker" />
							<form:errors path="dataMisuraFine" cssClass="error" />
							<label for="dataMisuraFine">Data misura fine</label>
						</div>						
						<div class="input-field col s12 m8 l6">
							<form:input type='text' id="numeroVerbale" path="numeroVerbale" />
							<form:errors path="numeroVerbale" cssClass="error" />
							<label for="numeroVerbale">Numero verbale</label>
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
				<a href='<spring:url value="/vivai/report/esportaMisure"/>' class="btn green waves-effect waves-light">ESPORTA</a>
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
								<th>Centro aziendale</th>	
								<th>Numero verbale</th>
								<th>Data misura</th>		
								<th>Esito constatazione</th>	
							</tr>
						</thead>
						<tbody id="bodyTabellaComunicazioni">	  
			
							<c:forEach var="report" items="${elencoReport}">
								<tr>
									<td>${report.ragioneSociale}</td>
									<td>${report.codCentroAziendale}</td>
									<td>${report.numeroVerbale}</td>								
									<td><fmt:formatDate value="${report.dataMisura}"	pattern="dd/MM/yyyy" /></td>
									<td>${report.esitoConstatazione}</td>																
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
        	
        	$("#dataMisura").val('');
            $("#numeroVerbale").val('');
            $("#idIspettore").val('');
            $("#idOrgNocivo").val('');
          
            $(this).closest('form').clearForm();
        	M.updateTextFields();
        });
    });  
    
        

    </script>  
	
 </content>
	
	

</body>

</html>