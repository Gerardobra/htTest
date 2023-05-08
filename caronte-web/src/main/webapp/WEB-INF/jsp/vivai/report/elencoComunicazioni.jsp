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
			<spring:url value="/vivai/report/cercaComVivai" var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaReportForm" accept-charset="utf-8">				
				<div class="card-content col s12">
					<span class="card-title">Ricerca per data creazione</span>
					<div class="row">
			            <div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataCreazioneInizio" path="dataCreazioneInizio"
								cssClass="datepicker" />
							<form:errors path="dataCreazioneInizio" cssClass="error" />
							<label for="dataCreazioneInizio">Data inizio</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataCreazioneFine" path="dataCreazioneFine"
								cssClass="datepicker" />
							<form:errors path="dataCreazioneFine" cssClass="error" />
							<label for="dataCreazioneFine">Data fine</label>
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
				<a href='<spring:url value="/vivai/report/esportaComVivai"/>' class="btn green waves-effect waves-light">ESPORTA</a>
			</sec:authorize>		   
	</div>	

	<c:choose>
		<c:when test="${not empty elencoReport}">

			<div class="row">
				<div class="col-s12">

					<table id="tabellaRichieste" class="data-table striped display">
						<thead>
							<tr>								
								<th>&nbsp;</th>
								<th>Stato</th>
								<th>Tipo</th>
								<th>Ragione Sociale</th>
								<th>Codice fiscale</th>
								<th>Partita iva</th>
								<th>Codice ruop</th>								
								<th>Codice centro aziendale</th>								
								<th>Data creazione</th>																							
							</tr>
						</thead>
						<tbody id="bodyTabellaComunicazioni">
						  <spring:eval var="statoInBozza"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />
				              <spring:eval var="statoInoltrata"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_INOLTRATA" />
				              <spring:eval var="statoConvalidata"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_CONVALIDATA" />
				              <spring:eval var="statoRespinta"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_RESPINTA" />
				              <spring:eval var="statoAnnullata"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_ANNULLATA" />
							
			
			<c:forEach var="report" items="${elencoReport}">
				<tr>
							<td>
			      				<c:choose>
                  					<c:when test="${statoInoltrata == report.idStatoComunicazione.longValue()}">
										<a
											class="btn-floating btn-flat btn-floating-medium yellow accent-2"
											style="cursor: default"></a>
									</c:when>
									<c:when test="${statoEseguita == report.idStatoComunicazione}">
										<a
											class="btn-floating btn-flat btn-floating-medium light-green accent-4"
											style="cursor: default"></a>
									</c:when>
									<c:when
										test="${statoRestituita == report.idStatoComunicazione}">
										<a
											class="btn-floating btn-flat btn-floating-medium red darken-4"
											style="cursor: default"> <i class="material-icons">arrow_back</i>
										</a>
									</c:when>
									<c:when
										test="${statoAnnullata == report.idStatoComunicazione}">
										<a
											class="btn-floating btn-flat btn-floating-medium red darken-4"
											style="cursor: default;"> <i class="material-icons">clear</i>
										</a>
									</c:when>
									<c:when test="${statoConvalidata == report.idStatoComunicazione}">
										<a
											class="btn-floating btn-flat btn-floating-medium light-green accent-4"
											style="cursor: default"></a>
									</c:when>
									<c:when
										test="${statoRespinta == report.idStatoComunicazione}">
										<a
											class="btn-floating btn-flat btn-floating-medium red darken-4"
											style="cursor: default"> <i class="material-icons">arrow_back</i>
										</a>
									</c:when>
									<c:otherwise>
										<a class="btn-floating btn-flat btn-floating-medium red"
											style="cursor: default"></a>
									</c:otherwise>
									</c:choose>
							</td>
									<td>${report.descStatoComunicazione}</td>
									<td>${report.descTipoComunicazione}</td>
									<td>${report.ragioneSociale}</td>
									<td>${report.codiceFiscale}</td>
									<td>${report.partitaIva}</td>
									<td>${report.codiceRuop}</td>									
									<td>${report.codCentroAziendale}</td>									
									<td><fmt:formatDate value="${report.dataCreazione}"	pattern="dd/MM/yyyy" /></td>																
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
            $("#dataCreazioneInizio").val('');
            $("#dataCreazioneFine").val('');
            
            $(this).closest('form').clearForm();
        	M.updateTextFields();
        });
    });  
    
        

    </script>  
	
 </content>
	
	

</body>

</html>