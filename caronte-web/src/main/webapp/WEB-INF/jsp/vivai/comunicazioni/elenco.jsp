<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<spring:url value="/vivai/comunicazioni/elenco" var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaComunicazioneForm" accept-charset="utf-8">
				<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
				<div class="card-content col s12">
					<span class="card-title">Cerca comunicazioni</span>
					<div class="row">
						<c:if test="${isSuperUser}">
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="spedizioniere" path="spedizioniere" />
								<form:errors path="spedizioniere" cssClass="error" />
								<label for="spedizioniere">Operatore</label>
							</div>
						</c:if>
						<div class="input-field col s6 m4 l3">
							<form:select id="tipoComunicazione" path="idTipoComunicazione"
								cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${tipiComunicazione}"
									itemValue="idTipoComunicazione" itemLabel="descBreve" />
							</form:select>
							<form:errors path="idTipoComunicazione" cssClass="error" />
							<label for="tipoComunicazione">Tipo comunicazione</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:select id="statoComunicazione" path="idStatoComunicazione"
								cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${statiComunicazione}"
									itemValue="idStatoComunicazione" itemLabel="descStato" />
							</form:select>
							<form:errors path="idStatoComunicazione" cssClass="error" />
							<label for="statoComunicazione">Stato comunicazione</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataCreazione" path="dataCreazione"
								cssClass="datepicker" />
							<form:errors path="dataCreazione" cssClass="error" />
							<label for="dataCreazione">Data creazione</label>
						</div>

					</div>
					<div class="card-action">
						<div class="row">
							<div class="col s6">
								<button type="button" class="btn clear-form waves-effect">PULISCI</button>
							</div>
							<div class="col s6 right-align">
								<button class="btn confirm waves-effect waves-light"
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

	<sec:authorize access="hasRole('WRITE') ">
		<div class="row">
			<a href='<spring:url value="/vivai/comunicazioni/nuova"/>'
				class="btn green waves-effect waves-light">NUOVA COMUNICAZIONE</a>
		</div>
	</sec:authorize>
	
	<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />

	<c:choose>
		<c:when test="${not empty elencoComunicazioni}">

			<div class="row">
				<div class="col-s12">

					<table id="tabellaRichieste" class="data-table striped display">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th>Stato</th>
								<th>Tipo</th>
								<th>Operatore</th>
								<th>Centro aziendale</th>
								<th>Data creazione</th>
								<th>Azioni</th>
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
							<c:forEach var="comunicazione" items="${elencoComunicazioni}">
								<tr>
									<td>
									<c:choose>
				                  	<c:when
					                    test="${statoInoltrata == comunicazione.idStatoComunicazione}">
										<a
											class="btn-floating btn-flat btn-floating-medium yellow accent-2"
											style="cursor: default"></a>
									</c:when>
									<c:when test="${statoConvalidata == comunicazione.idStatoComunicazione}">
										<a
											class="btn-floating btn-flat btn-floating-medium light-green accent-4"
											style="cursor: default"></a>
									</c:when>
									<c:when
										test="${statoRespinta == comunicazione.idStatoComunicazione}">
										<a
											class="btn-floating btn-flat btn-floating-medium red darken-2"
											style="cursor: default"> <i class="material-icons">arrow_back</i>
										</a>
									</c:when>
									<c:when
										test="${statoAnnullata == comunicazione.idStatoComunicazione}">
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
									<td>${comunicazione.idStatoComunicazione} - ${comunicazione.descStatoComunicazione}</td>
									<td>${comunicazione.descTipoComunicazione}</td>
									<td>${comunicazione.denomSpedizioniere}</td>
									<td>${comunicazione.codCentroAziendale} - ${comunicazione.descCentroAziendale}</td>
									<td><fmt:formatDate value="${comunicazione.dataInserimento}"
											pattern="dd/MM/yyyy" /></td>
									<spring:url value="/vivai/comunicazioni" var="comunicazioniAction" />
									<td nowrap style="white-spaces: nowrap">
                    <a href="${comunicazioniAction}/dettaglio/${comunicazione.idComunicazione}"
										  title="Visualizza"
										  class="btn btn-floating btn-floating-medium light-blue accent-3">
											<i class="material-icons">visibility</i>
                    </a>
                    <c:if test="${statoAnnullata != comunicazione.idStatoComunicazione.longValue()}">
                    <a href="${comunicazioniAction}/anagrafica/modifica/${comunicazione.idComunicazione}"
                      title="Modifica"
                      class="btn btn-floating btn-floating-medium light-green accent-3">
                      <i class="material-icons">mode_edit</i>
										</a>
										</c:if> 
										<a href="${comunicazioniAction}/copia/${comunicazione.idComunicazione}"
										  title="Copia"
										  class="btn btn-floating btn-floating-medium yellow"
										  onclick="return checkCopia(this)"> <i
											class="material-icons">content_copy</i>
										</a> 
										<c:if test="${statoInBozza == comunicazione.idStatoComunicazione.longValue()}">
										<a href="${comunicazioniAction}/elimina/${comunicazione.idComunicazione}"
                      title="Elimina"
                      class="btn btn-floating btn-floating-medium deep-orange accent-2"
                      onclick="return checkElimina(this)"> <i
                      class="material-icons">delete</i>
										</a>
										</c:if> 
										<a href='<spring:url value="/vivai/comunicazioni/excel/${comunicazione.idComunicazione}"/>'
										title="Scarica Excel"
										class="btn btn-floating btn-floating-medium teal lighten-1">
											<i class="material-icons">get_app</i></a>
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
						<p>&nbsp;&nbsp;Non sono state trovate comunicazioni</p>
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
		$(document).ready(function() {
						
			$('#statoComunicazione').change(function() {
				$(this).closest('form').submit();
			});

		});

		function checkElimina(link) {
			swal({
					title : 'Attenzione!',
					html : 'Si desidera cancellare la comunicazione del Centro Aziendale <b>'
							+ $(link).closest('tr').find('td:nth-child(5)')
									.text() + '</b>?',
					type : 'warning',
					showCancelButton : true,
					confirmButtonText : 'Sì',
					cancelButtonText : 'No',
			}).then(function(result) {
				  if (result.value) {
					    window.location.href = $(link).attr('href');
				  }
			});

			return false;
		}

		function checkCopia(link) {
			swal({
					//title: 'Attenzione!',
					html : 'Si desidera creare una copia in bozza della comunicazione del Centro Aziendale <b>'
							+ $(link).closest('tr').find('td:nth-child(5)').text() + '</b>?',
					type : 'question',
					showCancelButton : true,
					confirmButtonText : 'Sì',
					cancelButtonText : 'No',
			}).then(function(result) {
				  if (result.value) {
					    window.location.href = $(link).attr('href');
				  }
			});

			return false;
		}
	</script> </content>

</body>

</html>