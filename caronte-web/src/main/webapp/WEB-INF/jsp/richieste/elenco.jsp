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
			<spring:url value="/import/richieste/elenco" var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaRichiestaForm" accept-charset="utf-8">
				<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
				<div class="card-content col s12">
					<span class="card-title">Cerca richieste</span>
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<!-- i class="material-icons md-36 prefix">view_list</i-->
							<form:select id="statoRichiesta" path="idStatoRichiesta"
								cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${statoRichiesta}"
									itemValue="idStatoRichiesta" itemLabel="descStatoRichiesta" />
							</form:select>
							<form:errors path="idStatoRichiesta" cssClass="error" />
							<label for="statoRichiesta">Stato richiesta</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<!-- i class="material-icons md-18 prefix">list</i-->
							<form:select id="dogana" path="idUfficioDoganaleEntrata"
								cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${ufficioDoganale}"
									itemValue="idUfficioDoganale" itemLabel="denomUfficioDoganale" />
							</form:select>
							<form:errors path="idUfficioDoganaleEntrata" cssClass="error" />
							<label for="dogana">Dogana</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataRichiesta" path="dataInoltro"
								cssClass="datepicker" />
							<form:errors path="dataInoltro" cssClass="error" />
							<label for="dataRichiesta">Data richiesta</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<!-- i class="material-icons md-18 prefix">mode_edit</i-->
							<form:input type="text" id="mittente" path="denomMittente" />
							<form:errors path="denomMittente" cssClass="error" />
							<label for="mittente">Mittente</label>
						</div>

						<div class="input-field col s6 m4 l3">
							<!-- i class="material-icons md-36 prefix">view_list</i-->
							<form:input type="text" id="nRichiesta" path="codRichiesta" />
							<form:errors path="codRichiesta" cssClass="error" />
							<label for="nRichiesta">N. Richiesta</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="spedizioniere" path="spedizioniere" />
							<form:errors path="spedizioniere" cssClass="error" />
							<label for="spedizioniere">Operatore</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataEsecuzione" path="dataEsecuzione"
								cssClass="datepicker" />
							<form:errors path="dataEsecuzione" cssClass="error" />
							<label for="dataEsecuzione">Data esecuzione</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<!-- i class="material-icons md-18 prefix">mode_edit</i-->
							<form:input type="text" id="destinatario"
								path="denomDestinatario" />
							<form:errors path="denomDestinatario" cssClass="error" />
							<label for="destinatario">Destinatario</label>
						</div>

						<div class="input-field col s6 m4 l3">
							<!-- i class="material-icons md-36 prefix">view_list</i-->
							<form:input type="text" id="nCertificato"
								path="numeroCertificato" />
							<form:errors path="numeroCertificato" cssClass="error" />
							<label for="nCertificato">N. Certificato</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="documentoMezzo"
								path="identifMezzoTrasporto" />
							<form:errors path="identifMezzoTrasporto" cssClass="error" />
							<label for="documentoMezzo">Documento mezzo</label>
						</div>
						<c:if test="${isSuperUser}">
            <div class="input-field col s6 m4 l3">
              <form:select id="ispettoreFirmatario" path="idIspettoreFirmatario"
                cssClass="validate">
                <form:option value="" label="Selezionare" />
                <form:options items="${listaispettori}" itemValue="idIspettore"
                   itemLabel="denominazioneIspettore" />
              </form:select>
              <form:errors path="idIspettoreFirmatario" cssClass="error" />
              <label for="ispettoreFirmatario">Ispettore Firmatario</label>             
            </div>
            </c:if>
					</div>
					<div class="card-action">
						<div class="row">
							<div class="col s6">
								<button type="button" class="btn clear-form waves-effect">PULISCI</button>
							</div>
							<div class="col s6 right-align">
								<button class="btn confirm waves-effect waves-light" type="submit" name="cerca">
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
			<a href='<spring:url value="/import/richieste/nuova"/>'
				class="btn green waves-effect waves-light">NUOVA RICHIESTA</a>
		</div>
	</sec:authorize>
	
	<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />

	<c:choose>
		<c:when test="${not empty elencoRichieste}">

			<div class="row">
				<div class="col-s12">

					<table id="tabellaRichieste" class="data-table striped display">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th>Stato</th>
								<th>N. Richiesta</th>
								<th>Dogana</th>
								<th>Operatore</th>
								<th>Partenza merce</th>
								<th>Data esecuzione</th>
								<th>N. Documento mezzo Trasporto</th>
								<th>N. Certificato</th>
								<th>Mittente</th>
								<th>Destinatario</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody id="bodyTabellaRichieste">
							<c:forEach var="richiesta" items="${elencoRichieste}">
								<tr>
									<spring:eval var="statoInBozza"
										expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_BOZZA" />
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
												test="${statoInoltrata == richiesta.idStatoRichiesta.longValue()}">
												<a
													class="btn-floating btn-flat btn-floating-medium yellow accent-2"
													style="cursor: default"></a>
											</c:when>
											<c:when test="${statoEseguita == richiesta.idStatoRichiesta}">
												<a
													class="btn-floating btn-flat btn-floating-medium light-green accent-4"
													style="cursor: default"></a>
											</c:when>
											<c:when
												test="${statoLiquidata == richiesta.idStatoRichiesta}">
												<a
													class="btn-floating btn-flat btn-floating-medium light-green accent-4"
													style="cursor: default"> <i class="material-icons">payment</i>
												</a>
											</c:when>
											<c:when
												test="${statoRestituita == richiesta.idStatoRichiesta}">
												<a
													class="btn-floating btn-flat btn-floating-medium red darken-4"
													style="cursor: default"> <i class="material-icons">arrow_back</i>
												</a>
											</c:when>
											<c:when
												test="${statoAnnullata == richiesta.idStatoRichiesta}">
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
									<td>${richiesta.idStatoRichiesta} - ${richiesta.statoRichiesta}</td>
									<td>${richiesta.numeroRichiesta}</td>
									<td>${richiesta.dogana}</td>
									<td>${richiesta.spedizioniere}</td>
									<td><fmt:formatDate value="${richiesta.dataPartenzaMerce}"
											pattern="dd/MM/yyyy" /></td>
									<td><fmt:formatDate value="${richiesta.dataEsecuzione}"
											pattern="dd/MM/yyyy" /></td>
									<td>${richiesta.documentoMezzo}</td>
									<td>${richiesta.numeroCertificato}</td>
									<td>${richiesta.mittente}</td>
									<td>${richiesta.destinatario}</td>
									<spring:url value="/import/richieste" var="richiesteAction" />
									<td nowrap style="white-spaces: nowrap"><a
										href="${richiesteAction}/dettaglio/${richiesta.idRichiesta}"
										title="Visualizza"
										class="btn btn-floating btn-floating-medium light-blue accent-3">
											<i class="material-icons">visibility</i>
									</a> <c:if
											test="${statoAnnullata != richiesta.idStatoRichiesta.longValue()}">
											<a
												href="${richiesteAction}/modifica/${richiesta.idRichiesta}"
												title="Modifica"
												class="btn btn-floating btn-floating-medium light-green accent-3">
												<i class="material-icons">mode_edit</i>
											</a>
										</c:if> <a href="${richiesteAction}/copia/${richiesta.idRichiesta}"
										title="Copia"
										class="btn btn-floating btn-floating-medium yellow"
										onclick="return checkCopia(this)"> <i
											class="material-icons">content_copy</i></a> <c:if
											test="${statoInBozza == richiesta.idStatoRichiesta.longValue()}">
											<a href="${richiesteAction}/elimina/${richiesta.idRichiesta}"
												title="Elimina"
												class="btn btn-floating btn-floating-medium deep-orange accent-2"
												onclick="return checkElimina(this)"> <i
												class="material-icons">delete</i></a>
										</c:if> 
										<c:if test="${isSuperUser}">
										<c:if test="${statoEseguita == richiesta.idStatoRichiesta.longValue() or statoLiquidata == richiesta.idStatoRichiesta.longValue()}">
										<a href='<spring:url value="/import/report/esporta/nullaosta/${richiesta.idRichiesta}"/>'
										  title="Stampa NullaOsta"
										  class="btn btn-floating btn-floating-medium grey lighten-1">
											<i class="material-icons">print</i></a>
										</c:if>
										</c:if>	
										<a href='<spring:url value="/import/report/richiesta/${richiesta.idRichiesta}"/>'
										title="Stampa Dettaglio Richiesta"
										class="btn btn-floating btn-floating-medium teal lighten-1">
											<i class="material-icons">print</i></a>
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
						<p>&nbsp;&nbsp;Non sono state trovate richieste</p>
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

			/*$('#bodyTabellaRichieste').pageMe({
				pagerSelector : '#myPager',
				activeColor : '#0277bd',
				perPage : 6,
			});*/
			
			
			$('#statoRichiesta').change(function() {
				$(this).closest('form').submit();
			});

		});

		function checkElimina(link) {
			swal({
					title : 'Attenzione!',
					html : 'Si desidera cancellare la richiesta numero <b>'
							+ $(link).closest('tr').find('td:nth-child(3)')
									.text() + '</b>?',
					type : 'warning',
					showCancelButton : true,
					/*confirmButtonColor: '#3085d6',
					cancelButtonColor: '#d33',*/
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
					html : 'Si desidera creare una copia in bozza della richiesta numero <b>'
							+ $(link).closest('tr').find('td:nth-child(3)')
									.text() + '</b>?',
					type : 'question',
					showCancelButton : true,
					/*confirmButtonColor: '#3085d6',
					cancelButtonColor: '#d33',*/
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