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

p {
	word-break: break-all;
	white-space: normal;
}

.card-panel .row {
	margin-bottom: 0px;
	margin-left: 2px;
	margin-right: 2px;
}


</style>
</head>
<body>

	<div>
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
			</div> -->
		<div class="card row">
			<div class="card-content" style="padding-top: 2em;">
				<spring:url value="/admin/utenti/ispettori/elenco" var="formAction" />
				<form:form action="${formAction}" method="get"
					modelAttribute="ispettoreForm" accept-charset="utf-8">
					<div class="card-title">Ricerca ispettori</div>
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<form:select id="ruolo" path="idRuolo" cssClass="validate">
								<form:option value="" label="Amministratori / Supervisori" />
								<form:options items="${tipiProfilo}" itemValue="idRuolo"
									itemLabel="denomRuolo" />
							</form:select>
							<form:errors path="idRuolo" cssClass="error" />
							<label for="ruolo">Profilo</label>
						</div>
						<div class="col s6 m4 l3">
						  <label>
								<form:checkbox path="abilitato" id="abilitato" />
								<span>Abilitato</span>
							</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="cognomeNome" path="cognomeNome" />
							<form:errors path="cognomeNome" cssClass="error" />
							<label for="cognomeNome">Cognome e Nome</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="codiceFiscale" path="codiceFiscale" />
							<form:errors path="codiceFiscale" cssClass="error" />
							<label for="codiceFiscale">Codice Fiscale</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="denomSpedizioniere" path="denomSpedizioniere" />
							<form:errors path="denomSpedizioniere" cssClass="error" />
							<label for="denomSpedizioniere">Operatore</label>
						</div>
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="email" path="email" />
							<form:errors path="email" cssClass="error" />
							<label for="email">Email</label>
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
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="numeroTessera" path="numeroTessera" />
							<form:errors path="numeroTessera" cssClass="error" />
							<label for="codIspettore">Numero Tessera</label>
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
	<sec:authorize access="hasRole('ADMIN') ">
		<div class="row">
			<a href='<spring:url value="/admin/utenti/ispettori/nuovo"/>'
				class="inline btn green waves-effect waves-light">NUOVO
				ISPETTORE</a> 
				
				<c:if test="${not empty elencoIspettori}">
					<a href='<spring:url value="/admin/utenti/ispettori/esportaDatiIspettori"/>'
					class="inline btn green waves-effect waves-light">ESPORTA PER
					FORNITURA DATI</a>
				</c:if>
		</div>
	</sec:authorize>

	<c:choose>
		<c:when test="${not empty elencoIspettori}">
			<div class="row">
				<div class="col-s11">
				
				    <table id="tabellaIspettori" class="data-table striped display">					
						<thead>
							<tr>
								<th>Abilitato</th>
								<th>Profilo</th>
								<th>Sezione</th>
								<th>Cognome e Nome</th>
								<th>Codice Fiscale</th>
								<th>Numero Tessera</th>
								<th>Operatore di appartenenza</th>
								<th>Email</th>
								<th>Data inserimento</th>
								<th>Note</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody id="bodyTabellaIspettori">
							<c:forEach var="ispettore" items="${elencoIspettori}">
								<tr>
									<td>
										<c:if test="${ispettore.attivo}">
											<a
												class="btn-floating btn-flat btn-floating-medium light-green"
												style="cursor: default"> <i class="material-icons">check</i></a>
										</c:if>
									</td>
									<td>${ispettore.denomRuolo}</td>
									<td>${ispettore.descrAssociazioneSezione}</td>
									<td>${ispettore.cognome}&nbsp;${ispettore.nome}</td>
									<td>${ispettore.codiceFiscale}</td>
									<td>${ispettore.numeroTessera}</td>
									<td>${ispettore.denomSpedizioniere}</td>
									<td>${ispettore.email}</td>
									<td><fmt:formatDate value="${ispettore.dataInsert}"
											pattern="dd/MM/yyyy" /></td>
									<td>${ispettore.note}</td>
									<td nowrap style="white-spaces: nowrap">
										<c:if
											test="${!ispettore.passwordImpostata}">
											<!-- Modal Structure -->
											<div id="modal_${ispettore.idUtente }" class="modal">
												<div class="modal-content">
													<h4>Invia Mail</h4>
													<p>
														Si è scelto di inviare la richiesta di conferma e
														conclusione della registrazione dell’utente selezionato
														all’indirizzo mail indicato nella fase di
														inserimento dell’utente stesso. Confermare l’invio?
													</p>
												</div>
												<div class="modal-footer">
													<a href="#!"
														class="modal-action modal-close waves-effect waves-red btn-flat ">Annulla</a>
													<a onclick="sendMail(${ispettore.idUtente});" class="modal-action modal-close waves-effect waves-green btn-flat">Conferma</a>
												</div>
											</div>
											<!-- Modal Trigger -->
											<a href="#modal_${ispettore.idUtente }" title="Invia Mail"
												class=" waves-effect waves-light btn modal-trigger btn btn-floating btn-floating-medium light-black ">
												<i class="material-icons">contact_mail</i>
											</a>
										</c:if> <a
										href="<spring:url value="/admin/utenti/ispettori/dettaglio_${ispettore.idIspettore}"/>"
										title="Dettaglio"
										class="btn btn-floating btn-floating-medium light-blue accent-3">
											<i class="material-icons">visibility</i>
									</a> <a
										href="<spring:url value="/admin/utenti/ispettori/modifica_${ispettore.idIspettore}"/>"
										title="Modifica"
										class="btn btn-floating btn-floating-medium light-green accent-3">
											<i class="material-icons">mode_edit</i>
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
				<div id="ispettoriWarning" class="card card-alert orange lighten-5">
					<div class="card-content orange-text valign-wrapper center"
						style="">
						<i class="material-icons large" style="font-size: 36px">warning</i>
						<p>&nbsp;&nbsp;Non sono stati trovati ispettori</p>
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
	<script>

	function sendMail(idUtente){

		$
		.ajax({
			url : '<spring:url value="/admin/utenti/profili/inviaMail_"/>'+idUtente,
			success : function(response) {
				if(response="SUCCESS")
					$("#modal_"+idUtente).modal('close');
				else
					alert("Si è verificato un errore durante l'invio della mail.")

			}
		
		});
	}

	
		function clearDatePicker() {

			$("input[name='dataInserimentoDa']")[0].value = null;
			$("input[name='dataInserimentoA']")[0].value = null;

		}

		function clearCheck() {

			$("input[name='abilitato']")[0].checked = false;

		}		

		$(document).ready(function() {
			$("input[name='_abilitato']").remove();

			/*$('#bodyTabellaIspettori').pageMe({
				pagerSelector : '#myPager',
				activeColor : '#ff5252',
				perPage : 10,
			});*/

		});
	</script> </content>
</body>
</html>