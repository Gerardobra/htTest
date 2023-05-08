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
select[required] {
	/*visibility: hidden;
  display: block;
  position: absolute;*/
	opacity: 0;
	display: block;
	position: absolute;
	height: 0;
	border: none;
	margin-top: -60px;
}

.dropdown-content li>span {
	color: #26a69a;
}

.theme-blue {
	background-color: #0277bd !important;
}

.allpad5 {
	padding-bottom: 5px;
	padding-top: 5px;
	padding-left: 5px;
	padding-right: 5px;
}

.card-panel .row {
	margin-bottom: 0px;
	margin-left: 2px;
	margin-right: 2px;
}
</style>
</head>
<body>
	<spring:url value="/admin/utenti/spedizionieri/salva" var="formAction" />
	<div class="card row">
		<div class="card-content">
			<div class="card-title">Gestione operatore - modifica operatore</div>
			<div class="col s12" id="nuovoProfilo">
				<form:form action="${formAction}" method="post"
					modelAttribute="spedizioniereForm" accept-charset="utf-8">
					<div class="row">
						<div class="row">
							<div class="card">
								<div class="card-content">
									<div class="row l12">
										<div class="row col s6 m4 l12">
											<!-- 
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneImport" path="sezioneImport" />
													<span>Import</span>
												</label>
											</div>
											-->
											<div class=" col l3">
												<label> <form:checkbox id="sezioneExport"
														path="sezioneExport" /> <span>Export</span>
												</label>
											</div>
											<div class=" col l3">
												<label> <form:checkbox id="sezioneVivai"
														path="sezioneVivai" /> <span>Vivai</span>
												</label>
											</div>
											<div class=" col l3">
												<label> <form:checkbox id="sezioneAutorizzazioni"
														path="sezioneAutorizzazioni" /> <span>Autorizzazioni</span>
												</label>
											</div>
											<div class=" col l3">
												<label> <form:checkbox id="sezioneControlli"
														path="sezioneControlli" /> <span>Controlli</span>
												</label>
											</div>
											<div class="row col l6">
												<form:errors path="sezioneImport" cssClass="error" />
											</div>
										</div>
										<div class="row col s6 m4 l12">
											<div class="row col s6 m4 l12">
												<label> <form:checkbox id="autorizPagamPosticip"
														path="autorizPagamPosticip" /> <span>Autoriz.
														pagamento posticipato</span>
												</label>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="section">
								<div class="row col l12">
									<div class="input-field col s6 m4 l6">
										<form:select id="idTipoSpedizioniere"
											onchange="visualizzaNascondiCampi();"
											path="idTipoSpedizioniere" required="required"
											cssClass="validate myRequired azienda aziendaIndividuale">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaTipiSpedizionieri}"
												itemValue="idTipoSpedizioniere"
												itemLabel="denomTipoSpedizioniere" />
										</form:select>
										<form:errors path="idTipoSpedizioniere" cssClass="error" />
										<label for="spedizioniere">Tipo Operatore *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="cuaa" path="cuaa"
											cssCalss="azienda aziendaIndividuale" data-length="16"
											required="required" />
										<form:errors path="cuaa" cssClass="error" />
										<label for="cuaa">CUAA / CF *</label>
									</div>
								</div>

								<div class=" hiddenField row col l12">
									<div id="inputTipoSpedizioniereAltro"
										class="input-field col s6 m4 l6">
										<form:input type="text" id="tipoSpedizioniereAltro"
											required="required" readonly="true"
											cssClass="myRequired myDisabled" maxlength="100"
											path="tipoSpedizioniereAltro" />
										<form:errors path="tipoSpedizioniereAltro" cssClass="error" />
										<label id="labelTipoSpedizioniereAltro"
											for="tipoSpedizioniereAltro">Descrizione tipologia
											Altro *</label>
									</div>
									<div id="inputDenomSpedizioniere"
										class="input-field col s6 m4 l6">
										<form:input type="text" id="denomSpedizioniere"
											required="required" cssClass="myRequired"
											path="denomSpedizioniere" />
										<form:errors path="denomSpedizioniere" cssClass="error" />
										<label id="labelDenominazione" for="denomSpedizioniere">Denominazione
											/ Ragione sociale *</label>
									</div>
									<div id="inputNomeDitta" class="input-field col s6 m4 l6">
										<form:input type="text" id="nomeDitta" required="required"
											readonly="true" cssClass="myRequired" path="nomeDitta" />
										<form:errors path="nomeDitta" cssClass="error" />
										<label id="labelNome" for="nomeDitta">Nome *</label>
									</div>
									<div id="inputCognomeDitta" class="input-field col s6 m4 l6">
										<form:input type="text" id="cognomeDitta" readonly="true"
											cssClass="myRequired" path="cognomeDitta" />
										<form:errors path="cognomeDitta" cssClass="error" />
										<label id="labelCognome" for="cognomeDitta">Cognome *</label>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:select id="idProvinciaSedeSociale" required="required"
											onchange="getComuni()" path="idProvinciaSedeSociale"
											cssClass="validate myRequired">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaProvince}"
												itemValue="idProvincia" itemLabel="denomProvincia" />
										</form:select>
										<form:errors path="idProvinciaSedeSociale" cssClass="error" />
										<label id="labelProvincia" for="idProvinciaSedeSociale">Provincia
											sede legale *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:select id="idComuneSedeSociale" required="required"
											path="idComuneSedeSociale" cssClass="validate myRequired ">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaComuni}" itemValue="idComune"
												itemLabel="denomComune" />
										</form:select>
										<form:errors path="idComuneSedeSociale" cssClass="error" />
										<label id="labelComune" for="idComuneSedeSociale">Comune
											sede legale *</label>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="capSedeSociale"
											required="required" path="capSedeSociale"
											cssClass="myRequired" />
										<form:errors path="capSedeSociale" cssClass="error" />
										<label id="labelCap" for="capSedeSociale">CAP sede
											legale *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="indirizzoSedeSociale"
											required="required" cssClass="myRequired"
											path="indirizzoSedeSociale" />
										<form:errors path="indirizzoSedeSociale" cssClass="error" />
										<label id="labelIndirizzo" for="indirizzoSedeSociale">Indirizzo
											sede legale (Es. Via Roma, 24)*</label>
									</div>

									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="codiceRuop" cssClass="myRequired"
											path="codiceRUOP" class="validate"
											pattern="[a-zA-Z]{2}-\d{2}-\w{2,}" maxlength="20"
											oninvalid="this.setCustomValidity('Codice RUOP non valido')"
											oninput="this.setCustomValidity('')" />
										<form:errors path="codiceRUOP" cssClass="error" />
										<label for="codiceRuop">Codice RUOP</label> <span
											class="helper-text" data-error="Codice RUOP non valido"
											data-success="Codice RUOP valido"></span>
									</div>

									<div id="inputPartitaIVA" class="input-field col s6 m4 l6">
										<form:input type="text" id="partitaIVA" path="partitaIVA"
											cssClass="myRequired campiAz" data-length="11"
											required="required" />
										<form:errors path="partitaIVA" cssClass="error" />
										<label for="partitaIVA">Partita IVA *</label>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="email" id="emailSpedizioniere"
											cssClass="myRequired" path="emailSpedizioniere"
											class="validate" />
										<form:errors path="emailSpedizioniere" cssClass="error" />
										<label for="emailSpedizioniere">Email *</label> <span
											class="helper-text" data-error="Indirizzo email non valido"
											data-success="Indirizzo email valido"></span>
									</div>

									<div class="input-field col s6 m6 l6">
										<form:input type="email" id="pec" cssClass="myRequired"
											path="pec" class="validate" />
										<form:errors path="pec" cssClass="error" />
										<label id="labelPec" for="pec">PEC *</label> <span
											class="helper-text" data-error="Indirizzo email non valido"
											data-success="Indirizzo email valido"></span>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m6 l6">
										<form:input type="text" id="numeroCellulare"
											required="required" path="numeroCellulare" />
										<form:errors path="numeroCellulare" cssClass="error" />
										<label for="numeroCellulare">Cellulare (Es.
											3471234567)*</label>
									</div>

									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="numeroTelefono"
											path="numeroTelefono" />
										<form:errors path="numeroTelefono" cssClass="error" />
										<label for="numeroTelefono">Numero di telefono (Es.
											0245673467)</label>
									</div>
								</div>
								<div class="row">
									<div class="hiddenField row col l6">
										<label for=tipologiaAttivita>Tipologia attività</label>
										<form:input type="text" id="tipologiaAttivita" path="tipologiaAttivita" maxlength="1000"/>
									</div>
									<div class="hiddenField row col l6">
										<label for=tipologiaAttivita>Codice FITOK</label>
										<form:input type="text" id="codiceFitok" path="codiceFitok" maxlength="20"/>
										
									</div>
								</div>



								<div class="row col l12" id="statiAzienda">
									<p class="center-align"
										style="font-size: 20px; margin-bottom: 5px;">
										<em>Stato azienda</em>
									</p>
									<table>
										<tbody>
											<tr>
												<td></td>
												<c:forEach var="statoAzienda" items="${statiAzienda}">
													<td><div class=" col l8">
															<label> <form:radiobutton path="idStatoAzienda"
																	value="${statoAzienda.idStatoAzienda}" /> <span>${statoAzienda.descEstesa}</span>
															</label>
														</div></td>
												</c:forEach>
												<form:errors path="idStatoAzienda" cssClass="error" />
											</tr>
										</tbody>
									</table>
								</div>

								<div class="row col l12">
									<p class="center-align"
										style="font-size: 20px; margin-bottom: 5px;">
										<em>Elenco centri aziendali</em>
									</p>
									<table id="tabellaCentroAziendale"
										class="data-table striped display"
										data-order='[[ 1, "asc" ], [ 2, "asc" ], [ 3, "asc" ]]'
										data-paging="false" data-info="false">
										<thead>
											<tr>
												<th>Stato</th>
												<th>Denominazione</th>
												<th>Comune</th>
												<th>Indirizzo</th>
												<th>CAP</th>
												<th>Codice Centro Aziendale</th>
												<th>Modifica stato</th>
											</tr>
										</thead>
										<tbody id="bodyTabellaCentriAziendali">
											<spring:eval var="statoAttiva"
												expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_ATTIVA" />
											<spring:eval var="statoSospesa"
												expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_SOSPESA" />
											<spring:eval var="statoRevocata"
												expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_REVOCATA" />
											<spring:eval var="statoCancellata"
												expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_STATO_AZIENDA_CANCELLATA" />
											<c:forEach var="centroAz" items="${listaCentriAziendali}">
												<tr>
													<td><c:choose>
															<c:when test="${statoAttiva == centroAz.idStatoAzienda}">
																<a
																	class="btn-floating btn-flat btn-floating-medium light-green accent-2"
																	style="cursor: default"></a>
															</c:when>
															<c:when test="${statoSospesa == centroAz.idStatoAzienda}">
																<a
																	class="btn-floating btn-flat btn-floating-medium yellow accent-2"
																	style="cursor: default"></a>
															</c:when>
															<c:when
																test="${statoRevocata == centroAz.idStatoAzienda}">
																<a
																	class="btn-floating btn-flat btn-floating-medium orange"
																	style="cursor: default"></a>
															</c:when>
															<c:when
																test="${statoCancellata == centroAz.idStatoAzienda}">
																<a
																	class="btn-floating btn-flat btn-floating-medium red darken-4"
																	style="cursor: default"> </a>
															</c:when>
														</c:choose> ${centroAz.descrStatoAzienda}</td>
													<td>${centroAz.denominazione}</td>
													<td>${centroAz.denomComune}</td>
													<td>${centroAz.indirizzo}</td>
													<td>${centroAz.cap}</td>
													<td>${centroAz.codCentroAziendale}</td>
													<spring:url value="/admin/utenti/spedizionieri"
														var="spedizioniereAction" />
													<td><c:if
															test="${statoAttiva != centroAz.idStatoAzienda}">
															<a
																href="${spedizioniereAction}/attivaCentroAz_${centroAz.idCentroAziendale}"
																title="Attiva"
																class="btn btn-floating btn-floating-medium light-green accent-3">
																<i class="material-icons">adjust</i>
															</a>
														</c:if> <c:if test="${statoSospesa != centroAz.idStatoAzienda}">
															<a
																href="${spedizioniereAction}/sospendiCentroAz_${centroAz.idCentroAziendale}"
																title="Sospendi"
																class="btn btn-floating btn-floating-medium yellow accent-3">
																<i class="material-icons">adjust</i>
															</a>
														</c:if> <c:if test="${statoRevocata != centroAz.idStatoAzienda}">
															<a
																href="${spedizioniereAction}/revocaCentroAz_${centroAz.idCentroAziendale}"
																title="Revoca"
																class="btn btn-floating btn-floating-medium amber accent-3">
																<i class="material-icons">adjust</i>
															</a>
														</c:if> <c:if
															test="${statoCancellata != centroAz.idStatoAzienda}">
															<a
																href="${spedizioniereAction}/cancellaCentroAz_${centroAz.idCentroAziendale}"
																title="Cancella"
																class="btn btn-floating btn-floating-medium red"> <i
																class="material-icons">adjust</i>
															</a>
														</c:if> <a
														href='javascript:confermaEliminaCentroAz(${centroAz.idCentroAziendale}, ${centroAz.flEliminabile})'
														title="Elimina"
														class="btn btn-floating btn-floating-medium red darken-3">
															<i class="material-icons">delete</i>
													</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="card-action" style="padding-bottom: 2em;">
							<div class="row col s12 right-align">
								<button
									class="btn  confirm waves-effect waves-light offset-s1 right-align"
									type="submit" name="datiSpedizioniere">SALVA</button>
								<a
									href='<spring:url value="/admin/utenti/spedizionieri/elenco"/>'
									class="btn waves-effect waves-light col">ANNULLA</a>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>

		<content tag="script"> <script>
			$(document).ready(function() {

				//ricarica correttamente quando ritorna in pagina 
				$("select").formSelect();

				//disabilito le select
				$(".select-dropdown").attr("disabled", "disabled");
				$(".select-dropdown").attr("readonly", "readonly");
				$("select").attr("disabled", "disabled");
				$("select").attr("readonly", "readonly");

				visualizzaNascondiCampi();

				//se la pagina è pulita
				if ($("#cuaa").val() == null || $("#cuaa").val() == "") {
					//nascondo i campi dell'organizzazione a parte la tipologia ed il cuaa
					$(".hiddenField").hide();
				}
				$("#idTipoSpedizioniere").removeAttr("disabled").removeAttr("readonly");
				$("#idTipoSpedizioniere").formSelect();
				$("#idAssociazioneSezione").removeAttr("disabled").removeAttr("readonly");
				$("#idAssociazioneSezione").formSelect();

			});

			function visualizzaCampiAzienda() {

				//cambio label
				$("#labelProvincia").text("Provincia sede legale *");
				$("#labelComune").text("Comune sede legale *");
				$("#labelIndirizzo").text("Indirizzo sede legale *");
				$("#labelCap").text("CAP sede legale *");
				$("#labelPec").text("PEC *");

				//mostro/nascondo campi
				$("#inputDenomSpedizioniere").show();
				$("#inputNomeDitta").hide();
				$("#inputCognomeDitta").hide();
				$("#inputNomeDitta").removeAttr("required");
				$("#inputCognomeDitta").removeAttr("required");
				$("#inputTipoSpedizioniereAltro").hide();
				$("#tipoSpedizioniereAltro").val('');
					

				//sistemo i required
				$(".myRequired").attr("required", "required");
				$("#cognomeDitta").removeAttr("required");
				$("#nomeDitta").removeAttr("required");
				$("#denomSpedizioniere").attr("required", "required");
				$("#tipoSpedizioniereAltro").removeAttr("required");
				
				enableSelect();
			}

			function visualizzaCampiAziendaIndividuale() {

				var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();

				//cambio label campi comuni
				if (idTipoSpedizioniere == 5) {
					$("#labelProvincia").text("Provincia ditta *");
					$("#labelComune").text("Comune ditta *");
					$("#labelIndirizzo").text("Indirizzo ditta *");
					$("#labelCap").text("CAP ditta *");
					$("#labelPec").text("PEC *");
					$("#pec").attr("required", "required");
					$("#inputTipoSpedizioniereAltro").hide();
					$("#tipoSpedizioniereAltro").removeAttr("required");
					$("#tipoSpedizioniereAltro").val('');

				} else {
					$("#labelProvincia").text("Provincia residenza *");
					$("#labelComune").text("Comune residenza *");
					$("#labelIndirizzo").text("Indirizzo residenza *");
					$("#labelCap").text("CAP *");
					$("#labelPec").text("PEC (facoltativa)");
					$("#pec").removeAttr("required");
					// solo per la tipologia Altro idTipoAzienda = 1
					$("#tipoSpedizioniereAltro").text("Descrizione tipologia Altro");
					$("#inputTipoSpedizioniereAltro").show();
					$("#tipoSpedizioniereAltro").attr("required","required");
					$("#tipoSpedizioniereAltro").removeAttr("readonly");

				}

				//mostro/nasocndo campi
				$("#inputDenomSpedizioniere").hide();
				$("#inputDenomSpedizioniere").removeAttr("required");
				$("#inputNomeDitta").show();
				$("#inputCognomeDitta").show();

				//abilito gli altri campi
				$("#pec").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
				$("#capSedeSociale").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
				$("#indirizzoSedeSociale").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
				$("#codiceRuop").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
				$("#motivoRichiesta").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");

				//tolgo i required ai campi disabilitati
				$("#denomSpedizioniere").removeAttr("required");
				/*$("#cognomeDitta").removeAttr("required");
				$("#cuaa").removeAttr("required");
				$("#nomeDitta").removeAttr("required");
				$("#numeroTelefono").removeAttr("required");
				$("#emailSpedizioniere").removeAttr("required");
				$("#motivoRichiesta").removeAttr("required");*/

				enableSelect();
			}

			function enableSelect() {

				$(".select-dropdown").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");

				$("#idProvinciaSedeSociale").removeAttr("disabled").removeAttr("readonly");
				$("#idComuneSedeSociale").removeAttr("disabled").removeAttr("readonly");
				$("#idAssociazioneSezione").removeAttr("disabled").removeAttr("readonly");
				$("#idTipoSpedizioniere").removeAttr("disabled").removeAttr("readonly");

				$("#idProvinciaSedeSociale").formSelect();
				$("#idComuneSedeSociale").formSelect();
				$("#idAssociazioneSezione").formSelect();
				$("#idTipoSpedizioniere").formSelect();

			}

			//controlla l'idTipoSpedizioniere e mostra/nasconde i campi corretti
			function visualizzaNascondiCampi() {

				var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();

				$(".hiddenField").show();
				$("select").formSelect();

				if (idTipoSpedizioniere !== undefined && idTipoSpedizioniere != null) {
					if (idTipoSpedizioniere == 4 || idTipoSpedizioniere == 2 || idTipoSpedizioniere == 3) {
						visualizzaCampiAzienda();
					} else if (idTipoSpedizioniere == 1 || idTipoSpedizioniere == 5) {
						visualizzaCampiAziendaIndividuale();
					}
				} else {
					visualizzaCampiAzienda();
				}
			}

			function pulisci() {

				//pulisci form di default e riporto alla situa iniziale
				$("#idTipoSpedizioniere").removeAttr("disabled").removeAttr("readonly");
				$("#idTipoSpedizioniere").formSelect();
				$("#tipoSpedizionireAltro").val("");
				$("#cuaa").removeAttr("disabled").removeAttr("readonly");

				//ridisabilito ciò che inizialmente era disabilitato
				$(".myDisabled").attr("readonly", "readonly");

				$("#email").val("");

				//rimuovo elementi di validazione ed errori
				$(".valid").removeClass("valid");
				$(".invalid").removeClass("invalid");

				$(".error").remove();

				//nascondo i campi per riportare la pagina alla situazione iniziale
				$(".hiddenField").hide();

			}

			function getComuni() {

				var idProvincia = $("#idProvinciaSedeSociale").val();
				$.ajax({
					url : '<spring:url value="/registrazione/getListaComuni_"/>' + idProvincia,
					async : false,
					success : function(response) {

						$("#idComuneSedeSociale").html(response);
						$('#idComuneSedeSociale').formSelect();

					}
				});
			}

			
			function confermaEliminaCentroAz(idCentroAziendale, flEliminabile) {
				console.log('idCentroAziendale selezionato = '
						+ idCentroAziendale);
				if (flEliminabile) {
					swal({
								title : 'Attenzione',
								html : 'Si desidera eliminare definitivamente il centro aziendale selezionato?',
								type : 'warning',
								showCancelButton : true,
								confirmButtonText : 'CONTINUA',
								cancelButtonText : 'ANNULLA',
							})
							.then(
									function(result) {
										if (result.value) {
											window.location.href = '<spring:url value="/admin/utenti/spedizionieri/eliminaCentroAz_"/>'
													+ idCentroAziendale;
										}
									});
				}else{
					swal({
                        title: "Impossibile eliminare il centro aziendale",
                        html : 'Il centro aziendale selezionato è stato utilizzato in una domanda RUOP, pertanto è impossibile eliminarlo.',
	       		            type : 'warning',
                      
                     }).then(function(){
                        
                     });
                }

			}
		</script> </content>
</body>
</html>