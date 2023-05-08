<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
.allpad5 {
	padding-bottom: 5px;
	padding-top: 5px;
	padding-left: 5px;
	padding-right: 5px;
}

.card-panel {
	padding-bottom: 0px;
}

.theme-blue {
	background-color: #0277bd !important;
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
		<div class="nav-content">
			<ul class="tabs">
				<li class="tab col s2"><a target="_self"
					<c:if test="${secondaryActiveLink == 'profili'}">active</c:if>"
						href='<spring:url value="/admin/utenti/profili/elenco"/>'><strong>PROFILI</strong></a></li>
				<li class="tab col s2"><a target="_self"
					class="push-s1 <c:if test="${secondaryActiveLink == 'ispettori'}">active</c:if>"
					href='<spring:url value="/admin/utenti/ispettori/elenco"/>'><strong>ISPETTORI</strong></a></li>
				<li class="tab col s2"><a target="_self"
					class="push-s4 <c:if test="${secondaryActiveLink == 'spedizionieri'}">active</c:if>"
					href='<spring:url value="/admin/utenti/spedizionieri/elenco"/>'><strong>OPERATORI</strong></a></li>
			</ul>
		</div>
		<!-- <div class="card-title">
				<div class="right-align allpad5 col l12">
					<h6>GESTIONE PROFILI, ISPETTORI, SPEDIZIONIERI</h6>
				</div>
				<div class="card-panel hoverable col l10 offset-l1"
					style="padding-top: 1em; padding-bottom: 1em;">
					<h6>
						In questa sezione l’utente può gestire le varie tipologie di
						attori del sistema: <i>Utenti</i>, <i>Spedizionieri,</i> <i>Ispettori</i>
						e <i>Amministratori</i>.<br> Per accedere ai sotto menu dedicati
						utilizzare il menu sottostante.
					</h6>
				</div>
			</div>-->

		<div class="card row">
			<div class="card-content" style="padding-top: 2em;">
				<spring:url value="/admin/utenti/spedizionieri/elenco"
					var="formAction" />
				<form:form action="${formAction}" method="get"
					modelAttribute="spedizioniereForm" accept-charset="UTF-8">
					<div class="card-title">Ricerca operatori</div>
					<div class="row">
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
							<form:select id="idStatoAzienda" path="idStatoAzienda"
								cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${listaStatiAzienda}"
									itemValue="idStatoAzienda"
									itemLabel="descCentroAz" />
							</form:select>
							<form:errors path="idStatoAzienda" cssClass="error" />
							<label for="idStatoAzienda">Stato operatore</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="denomSpedizioniere"
								path="denomSpedizioniere" />
							<form:errors path="denomSpedizioniere" cssClass="error" />
							<label for="denomSpedizioniere">Denominazione
								Operatore</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInserimentoDa"
								path="dataInserimentoDa" cssClass="datepicker" />
							<form:errors path="dataInserimentoDa" cssClass="error" />
							<label for="dataInserimentoDa">Data inserimento: da</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInserimentoA"
								path="dataInserimentoA" cssClass="datepicker" />
							<form:errors path="dataInserimentoA" cssClass="error" />
							<label for="dataInserimentoA">Data inserimento: a</label>
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
		<sec:authorize access="hasRole('WRITE') ">
			<div class="row">
				<a href='<spring:url value="/admin/utenti/spedizionieri/nuovo"/>'
					class="btn green waves-effect waves-light">NUOVO OPERATORE</a>
			</div>
		</sec:authorize>

		<c:choose>
			<c:when test="${not empty elencoSpedizionieri}">

				<div class="row">
					<div class="col-s11">
						<table id="tabellaSpedizionieri"
							class="data-table striped display">
							<thead>
								<tr>
									<th style="width: 100px">Stato          </th>
									<th>Tipo Operatore</th>
									<th>Denominazione Operatore</th>
									<th>Sezione</th>
									<th>Data Inserimento</th>
									<th>Azioni</th>
								</tr>
							</thead>
							<tbody id="bodyTabellaSpedizionieri">
								<c:forEach var="spedizioniere" items="${elencoSpedizionieri}">
									<tr>
										<spring:eval var="statoAttiva"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_ATTIVA" />
										<spring:eval var="statoSospesa"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_SOSPESA" />
										<spring:eval var="statoRevocata"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_REVOCATA" />
										<spring:eval var="statoCancellata"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_CANCELLATA" />	
									<td><c:choose>
										<c:when test="${statoAttiva == spedizioniere.idStatoAzienda}">
											<a class="btn-floating btn-flat btn-floating-medium light-green accent-2"
												style="cursor: default"></a>
										</c:when>
										<c:when test="${statoSospesa == spedizioniere.idStatoAzienda}">
											<a class="btn-floating btn-flat btn-floating-medium yellow accent-2"
												style="cursor: default"></a>
										</c:when>
										<c:when test="${statoRevocata == spedizioniere.idStatoAzienda}">
											<a class="btn-floating btn-flat btn-floating-medium orange"
												style="cursor: default"></a>
										</c:when>
										<c:when test="${statoCancellata == spedizioniere.idStatoAzienda}">
											<a
												class="btn-floating btn-flat btn-floating-medium red darken-4"
												style="cursor: default">
											</a>
										</c:when>
										
										</c:choose>${spedizioniere.descCentroAz}</td>
										<td>${spedizioniere.denomTipoSpedizioniere}</td>
										<td>${spedizioniere.denomSpedizioniere}</td>
										<td>${spedizioniere.descrAssociazioneSezione}</td>
										
										<td><fmt:formatDate value="${spedizioniere.dataInsert}"
												pattern="dd/MM/yyyy" /></td>
										<td nowrap style="white-spaces: nowrap">
											<a
												href="<spring:url value="/admin/utenti/spedizionieri/dettaglio_${spedizioniere.idSpedizioniere}"/>"
												title="Dettaglio"
												class="btn btn-floating btn-floating-medium light-blue accent-3">
													<i class="material-icons">visibility</i>
											</a> 
											<a
												href="<spring:url value="/admin/utenti/spedizionieri/modifica_${spedizioniere.idSpedizioniere}"/>"
												title="Modifica"
												class="btn btn-floating btn-floating-medium light-green accent-3">
													<i class="material-icons">mode_edit</i>
											</a>
											<a href='javascript:confermaEliminaSpediz(${spedizioniere.idSpedizioniere}, ${spedizioniere.flEliminabile})'
												title="Elimina"
												class="btn btn-floating btn-floating-medium red darken-3">
													<i class="material-icons">delete</i>
											</a>
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
					<div id="spedizionieriWarning"
						class="card card-alert orange lighten-5">
						<div class="card-content orange-text valign-wrapper center"
							style="">
							<i class="material-icons large" style="font-size: 36px">warning</i>
							<p>&nbsp;&nbsp;Non sono stati trovati operatori</p>
						</div>
						<button type="button" class="close orange-text"
							data-dismiss="alert" aria-label="Chiudi">
							<span aria-hidden="true">×</span>
						</button>
					</div>
				</div>

			</c:otherwise>
		</c:choose>
	</div>
	<content tag="script">
	   <c:set var="locale">${pageContext.response.locale}</c:set> 
	<script>

		function clearDatePicker() {

			$("input[name='dataInserimentoDa']")[0].value = null;
			$("input[name='dataInserimentoA']")[0].value = null;

		}

		function clearCheck() {

			$("input[name='abilitato']")[0].checked = false;

		}
		$(document).ready(function() {

			/*$('#bodyTabellaSpedizionieri').pageMe({
				pagerSelector : '#myPager',
				activeColor : '#ff5252',
				perPage : 10,
			});*/

		});

		function confermaEliminaSpediz(idSpedizioniere, flEliminabile) {
			console.log('idSpedizioniere selezionato = ' + idSpedizioniere);
			if (flEliminabile) {
				swal({
							title : 'Attenzione',
							html : 'Si desidera eliminare definitivamente lo spedizioniere selezionato?',
							type : 'warning',
							showCancelButton : true,
							confirmButtonText : 'CONTINUA',
							cancelButtonText : 'ANNULLA',
						})
						.then(
								function(result) {
									if (result.value) {
										window.location.href = '<spring:url value="/admin/utenti/spedizionieri/eliminaSpedizioniere_"/>'
												+ idSpedizioniere;
									}
								});
			}else{
				swal({
                    title: "Impossibile eliminare lo spedizioniere",
                    html : 'Lo spedizioniere selezionato è associato a uno o più centri aziendali o utenti, pertanto è impossibile eliminarlo.',
       		            type : 'warning',
                  
                 }).then(function(){
                    
                 });
            }

		}
	</script> </content>
</body>
</html>