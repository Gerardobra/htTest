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
<head>
<style>
.card-panel {
	/*padding-bottom: 0px;*/
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
			<div class="card-content">
				<div class="card-title">Ricerca report</div>
				<spring:url value="/import/report/cerca" var="formAction" />
				<form:form action="${formAction}" method="get"
					modelAttribute="ricercaReportForm" accept-charset="utf-8">
					<div class="row">
						<div class="col m8 l6">
							<div class="row">
								<div class="input-field col s6 m6 l6">
									<!-- i class="material-icons md-18 prefix">mode_edit</i-->
									<form:input type="text" id="annoReport" path="anno" />
									<label for="annoReport">Anno</label>
									<form:errors path="anno" cssClass="error" />
								</div>
								<div class="input-field col s6 m6 l6">
									<!-- i class="material-icons md-36 prefix">view_list</i-->
									<form:select id="meseReport" path="mese" cssClass="validate">
										<form:option value="" label="Selezionare" />
										<form:options items="${mesi}" />
									</form:select>
									<label for="meseReport">Mese</label>
									<form:errors path="mese" cssClass="error" />
								</div>
							</div>
							<div class="row">
								<div class="col s12 m6">
									<div class="row">
										<h5 class="header primary-color " style="font-weight: 300;">Scelta spedizioniere</h5>
										<div class="divider primary-color"></div>
									</div>
									<div class="row">
										<div class="input-field col s12">
											<form:select id="spedizioniere" path="idSpedizioniere"
												cssClass="validate">
												<form:option value="" label="Tutti" />
												<form:options items="${spedizionieri}"
													itemLabel="denomSpedizioniere" itemValue="idSpedizioniere" />
											</form:select>
											<label for="spedizioniere">Operatore</label>
											<form:errors path="idSpedizioniere" cssClass="error" />
										</div>
									</div>
								</div>
								<div class="col s12 m6">
									<div class="row">
										<h5 class="header primary-color" style="font-weight: 300;">Filtro stato richiesta</h5>
										<div class="divider primary-color"></div>
									</div>
									<div class="row">
										<div class="input-field col s12">
											<form:select id="statoRichiesta" path="idStatoRichiesta"
												cssClass="validate">
												<form:option value="" label="Selezionare" />
												<form:options items="${statoRichiesta}"
													itemValue="idStatoRichiesta" itemLabel="descStatoRichiesta" />
											</form:select>
											<label for="statoRichiesta">Stato richiesta</label>
											<form:errors path="idStatoRichiesta" cssClass="error" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col m4 l6 hide-on-small-only">
							<div class="card-panel hoverable">
								<p>
									<i class="material-icons primary-color small">info</i>   Selezionare
									l'anno e il mese di cui si desidera visualizzare ed
									eventualmente stampare le distinte mensili di pagamento.
									Si possono indicare nello specifico uno o più Operatori di
									riferimento oppure includere nei risultati tutti quelli le cui
									richieste sono state eseguite nel mese di riferimento non
									selezionando nessuna voce nel menù..
								</p>
							</div>
						</div>
					</div>
					<div class="card-action">
						<div class="row">
							<div class="col s6">
								<button type="button" class="btn clear-form waves-effect"
									onclick="clearDatePicker();clearCheck();">PULISCI</button>
							</div>
							<div class="col s6 right-align">
								<button class="btn confirm waves-effect waves-light"
									type="submit" name="cerca">
									CERCA <i class="material-icons right">search</i>
								</button>
							</div>
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>

	<div class="row">
		<sec:authorize access="hasRole('WRITE') ">
			<a href='<spring:url value="/import/report/stampa"/>' class="btn confirm waves-effect waves-light">STAMPA</a>
			<a href='<spring:url value="/import/report/esporta"/>' class="btn green waves-effect waves-light">ESPORTA</a>
		</sec:authorize>
		<c:if test="${not empty elencoReport}">
			<h6 class="right"><strong>Totale Tariffa (€):</strong> <fmt:formatNumber type="number" pattern="##0.00"
											value="${totaleTariffa}"/></h6>
		</c:if>
	</div>

	<c:choose>
		<c:when test="${not empty elencoReport}">

			<div class="row">
				<div class="col-s12">

					<table id="tabellaRichieste"
						class="responsive-table striped display">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th>Operatore</th>
								<th>Stato richiesta</th>
								<th>Data esecuzione richiesta</th>
								<th>Documento mezzo trasporto</th>
								<th>N. Certificato</th>
								<th>Mittente</th>
								<th>Destinatario</th>
								<th>Tariffa (&euro;)</th>

							</tr>
						</thead>
						<tbody id="bodyTabellaReport">
							<c:forEach var="report" items="${elencoReport}">
								<tr>
									<spring:eval var="statoInoltrata"
										expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_INOLTRATA" />
									<spring:eval var="statoEseguita"
										expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_ESEGUITA" />
									<spring:eval var="statoLiquidata"
										expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_LIQUIDATA" />
									<spring:eval var="statoRestituita"
										expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_RESTITUITA" />
									<spring:eval var="statoAnnullata"
										expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_ANNULLATA" />
									<td><c:choose>
											<c:when
												test="${statoInoltrata == report.idStatoRichiesta.longValue()}">
												<a
													class="btn-floating btn-flat btn-floating-medium yellow accent-2"
													style="cursor: default"></a>
											</c:when>
											<c:when test="${statoEseguita == report.idStatoRichiesta}">
												<a
													class="btn-floating btn-flat btn-floating-medium light-green accent-4"
													style="cursor: default"></a>
											</c:when>
											<c:when test="${statoLiquidata == report.idStatoRichiesta}">
												<a
													class="btn-floating btn-flat btn-floating-medium light-green accent-4"
													style="cursor: default"> <i class="material-icons">payment</i>
												</a>
											</c:when>
											<c:when test="${statoRestituita == report.idStatoRichiesta}">
												<a
													class="btn-floating btn-flat btn-floating-medium red darken-4"
													style="cursor: default"> <i class="material-icons">arrow_back</i>
												</a>
											</c:when>
											<c:when test="${statoAnnullata == report.idStatoRichiesta}">
												<a
													class="btn-floating btn-flat btn-floating-medium red darken-4"
													style="cursor: default;"> <i class="material-icons">clear</i>
												</a>
											</c:when>
											<c:otherwise>
												<a class="btn-floating btn-flat btn-floating-medium red"
													style="cursor: default"></a>
											</c:otherwise>
										</c:choose></td>
									<td><c:out value="${report.denomSpedizioniere}"></c:out></td>
									<td><c:out value="${report.descStatoRichiesta}"></c:out></td>
									<td><fmt:formatDate value="${report.dataEsecuzione}"
											pattern="dd/MM/yyyy" /></td>
									<td><c:out value="${report.identifMezzoTrasporto}"></c:out>
									</td>
									<td><c:out value="${report.numeroCertificato}"></c:out></td>
									<td><c:out value="${report.denomMittente}"></c:out></td>
									<td><c:out value="${report.denomDestinatario}"></c:out></td>
									<td><fmt:formatNumber type="number" pattern="##0.00"
											value="${report.tariffa}" /></td>
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

	<content tag="script"> <c:set var="locale">${pageContext.response.locale}</c:set>
	<c:choose>
		<c:when test="${locale != 'en'}">
			<!-- localizzazione js (solo per lingue diverse da inglese) -->
			<script>
				$(document).ready(function() {

					$('#bodyTabellaReport').pageMe({
						pagerSelector : '#myPager',
						activeColor : '#0277bd',
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
					activeColor : '#0277bd',
					perPage : 6,
				});
			</script>
			<br />
		</c:otherwise>
	</c:choose> </content>

</body>

</html>