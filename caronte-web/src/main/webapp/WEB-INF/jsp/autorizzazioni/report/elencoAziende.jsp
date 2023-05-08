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
			<spring:url value="/autorizzazioni/report/cercaAziendeAut" var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaReportForm" accept-charset="utf-8">				
				<div class="card-content col s12">
					<span class="card-title">Ricerca per data creazione</span>
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<form:select id="idStatoAzienda" path="idStatoAzienda"
								cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaStatiAzienda}"
									itemValue="idStatoAzienda"
									itemLabel="descBreve" />
							</form:select>
							<form:errors path="idStatoAzienda" cssClass="error" />
							<label for="idStatoAzienda">Stato operatore</label>
						</div>
						<div class="input-field col s6 m4 l3">
								<form:input type="text" id="ragioneSociale"	path="ragioneSociale" />
								<form:errors path="ragioneSociale" cssClass="error" />
								<label id="labelRagioneSociale" for="ragioneSociale">Ragione sociale</label>
						</div>	
						<div class="input-field col s6 m4 l3">
								<form:input type="text" id="codiceRuop"	path="codiceRuop" />
								<form:errors path="codiceRuop" cssClass="error" />
								<label id="labelCodiceRuop" for="codiceRuop">Codice RUOP</label>
						</div>	
						<div class="input-field col s6 m4 l3">
							<form:select id="spedizioniere" path="idTipoSpedizioniere"
								cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaTipiSpedizionieri}"
									itemValue="idTipoSpedizioniere"
									itemLabel="denomTipoSpedizioniere" />
							</form:select>
							<form:errors path="idTipoSpedizioniere" cssClass="error" />
							<label for="spedizioniere">Tipo Operatore</label>
						</div>
			            <div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataRuopInizio" path="dataRuopInizio"
								cssClass="datepicker" />
							<form:errors path="dataRuopInizio" cssClass="error" />
							<label for="dataRuopInizio">Data registrazione RUOP inizio</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataRuopFine" path="dataRuopFine"
								cssClass="datepicker" />
							<form:errors path="dataRuopFine" cssClass="error" />
							<label for="dataRuopFine">Data registrazione RUOP fine</label>
						</div>
						<div class="input-field col s6 m4 l3">
								<form:input type="text" id="tipologiaAttivita"	path="tipologiaAttivita" />
								<form:errors path="tipologiaAttivita" cssClass="error" />
								<label id="labelTipologiaAttivita" for="tipologiaAttivita">Tipologia attivita</label>
						</div>							
						<div class="input-field col s6 m4 l3 idProvNascita">
							<form:select id="idProvincia" path="idProvincia">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaProvince}" itemValue="idProvincia"
									itemLabel="denomProvincia" />
							</form:select>
							<form:errors path="idProvincia" cssClass="error" />
							<label for="idProvincia">Provincia</label>
						</div>
						<div class="input-field col s6 m4 l3 ">
							<form:select id="idComune" path="idComune">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaComuni}" itemValue="idComune"
									itemLabel="denomComune" />
							</form:select>
							<form:errors path="idComune" cssClass="error" />
							<label for="idComune">Comune </label>
						</div>					
						<div class="input-field col s6 m4 l3">
								<form:input type="text" id="partitaIva"	path="partitaIva" />
								<form:errors path="partitaIva" cssClass="error" />
								<label id="labelpartitaIva" for="partitaIva">Partita IVA</label>
						</div>
						
						<div class="input-field col s6 m4 l6">
							<label> <form:checkbox path="aziendeConDomandaRuop"	id="aziendeConDomandaRuop"/> 
								<span>Solo aziende con domanda di registrazione Ruop su Caronte</span>
							</label>
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
				<a href='<spring:url value="/autorizzazioni/report/esportaAziendeAut"/>' class="btn green waves-effect waves-light">ESPORTA</a>
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
								<th>Codice fiscale</th>
								<th>Partita IVA</th>
								<th>Ragione sociale</th>	
								<th>Codice ruop</th>								
								<th>Data registrazione Ruop</th>																							
							</tr>
						</thead>
						<tbody id="bodyTabellaComunicazioni">
						  <spring:eval var="statoAttiva"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_ATTIVA" />
				              <spring:eval var="statoSospesa"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_SOSPESA" />
				              <spring:eval var="statoRevocata"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_REVOCATA" />
				              <spring:eval var="statoCancellata"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_CANCELLATA" />
									
						<c:forEach var="report" items="${elencoReport}">
							<tr>
								<td>
				      				<c:choose>
	                  					<c:when test="${statoAttiva == report.idStatoAzienda}">
											<a
												class="btn-floating btn-flat btn-floating-medium light-green accent-4"
												style="cursor: default"></a>
										</c:when>
										<c:when test="${statoSospesa == report.idStatoAzienda}">
											<a
												class="btn-floating btn-flat btn-floating-medium yellow accent-2"
												style="cursor: default"></a>
										</c:when>
										<c:when
											test="${statoRevocata == report.idStatoAzienda}">
											<a
												class="btn-floating btn-flat btn-floating-medium red darken-4"
												style="cursor: default"> <i class="material-icons">arrow_back</i>
											</a>
										</c:when>
										<c:when
											test="${statoCancellata == report.idStatoAzienda}">
											<a
												class="btn-floating btn-flat btn-floating-medium red darken-4"
												style="cursor: default;"> <i class="material-icons">clear</i>
											</a>
										</c:when>
										<c:otherwise>
											<a class="btn-floating btn-flat btn-floating-medium red"
												style="cursor: default"></a>
										</c:otherwise>
									</c:choose>
								</td>
								<td>${report.descStatoAzienda}</td>
								<td>${report.codiceFiscale}</td>
								<td>${report.partitaIva}</td>
								<td>${report.ragioneSociale}</td>
								<td>${report.codiceRuop}</td>												
								<td><fmt:formatDate value="${report.dataRegistrazioneRuop}"	pattern="dd/MM/yyyy" /></td>																
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
            $("#dataRuopInizio").val('');
            $("#dataRuopFine").val('');  
            $("#idStatoAzienda").val('');
            $("#idStatoAzienda").formSelect();
            $("#idTipoSpedizioniere").val('');
            $("#idTipoSpedizioniere").formSelect();            
            $("#ragioneSociale").val('');
            $("#codiceRuop").val('');
            $("#tipologiaAttivita").val('');
            $("#idProvincia").val('');
            $("#idProvincia").formSelect();
            $("#idComune").val('');
            $("#idComune").formSelect();
            
            
            $(this).closest('form').clearForm();
        	M.updateTextFields();
        });
    });  
    
  //Popolamento dinamico della select Comune nascita
	  $('#idProvincia').change(
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