<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<body>

	<spring:url value="/controlli/datiBase/nuova" var="formAction" />
	<form:form action="${formAction}" method="post"
		modelAttribute="nuovoControlloForm" accept-charset="utf-8">
		<div class="card">
			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding">
					<b>NUOVO CONTROLLO</b>
				</h3>
			</div>
			<div class="card-content">
				<div class="row">
					<div class="col s12">
						<ul class="tabs">
							<li class="tab"><a class="active"><b>Dati generali</b></a></li>
							<c:if test="${nuovoControlloForm.tabDocumentale}">
								<li class="tab"><a
									href="<spring:url value="/controlli/controllodocumentale/nuova" />"
									target="_self">Documentale</a>
								</li>
							</c:if>
							<c:if test="${nuovoControlloForm.tabIdentita}">	
								<li class="tab"><a
									href="<spring:url value="/controlli/controlloidentita/nuova" />"
									target="_self">Identità</a>
								</li>
							</c:if>
							<c:if test="${nuovoControlloForm.tabFisico}">	
								<li class="tab"><a
									href="<spring:url value="/controlli/controllofisico/nuova" />"
									target="_self">Fisico</a>
								</li>
							</c:if>	
							<c:if test="${nuovoControlloForm.tabFisico && nuovoControlloForm.flControllo5x23 != null && nuovoControlloForm.flControllo5x23 =='S' }">
		        					<li class="tab" >
		        						<a href="<spring:url value="/controlli/campioni/nuova" />" target="_self">Campioni</a>
		        					</li>
							</c:if>
							<li class="tab"><a
								href="<spring:url value="/controlli/allegati/nuova" />"
								target="_self">Allegati</a></li>
							<c:if test="${false}">
		        				<li class="tab" >
		        					<a href="<spring:url value="/controlli/monitoraggio/nuova" />" target="_self">Monitoraggio cofinanziato</a>
		        				</li>
	        				</c:if>	 
	        				<c:if test="${nuovoControlloForm.misuraUfficiale != null && nuovoControlloForm.misuraUfficiale =='S' }">
	        					<li class="tab" >
        					  		<a href="<spring:url value="/controlli/misura/nuova" />" target="_self">Misura ufficiale</a>
        				    	</li>
        				    </c:if>								
							<li class="tab"><a
								href="<spring:url value="/controlli/esito/nuova" />"
								target="_self">Esito</a></li>

						</ul>
					</div>
				</div>

				<div class="card-panel">

					<div class="row">
						<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Dati generali</em>
						</p>
					</div>

					<div class="row s12" id="nuovoControllo">

						<!------ DATEPICKER::DATA CONTROLLO -------->
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataControllo" path="dataControllo"
								cssClass="datepicker_birthdate" />
							<form:errors path="dataControllo" cssClass="error" />
							<label for="dataControllo">Data controllo *</label>
						</div>
						<!------ TIMEPICKER::ORA INIZIO CONTROLLO -------->
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="oraInizioControllo"
								path="oraInizioControllo" cssClass="timepicker" />
							<form:errors path="oraInizioControllo" cssClass="error" />
							<label for="oraInizioControllo">Ora inizio controllo *</label>
						</div>

						<!------ TIMEPICKER::ORA FINE CONTROLLO -------->
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="oraFineControllo"
								path="oraFineControllo" cssClass="timepicker" />
							<form:errors path="oraFineControllo" cssClass="error" />
							<label for="oraFineControllo">Ora fine controllo</label>
						</div>

						<!------ INPUT::NOME ISPETTORE -------->
						<div class="input-field col s6 m4 l3">
							<form:select multiple="true" id="nomeIspettore"
								path="idsIspettore" cssClass="validate">
								<option value="" disabled="">Selezionare</option>
								<form:options items="${elencoIspettori}" itemValue="idIspettore"
									itemLabel="descIspettore" />
							</form:select>
							<form:errors path="idsIspettore" cssClass="error" />
							<label for="idsIspettore" class="active">Nome ispettore/agente</label>
						</div>

						<!------ INPUT::RAGIONE SOCIALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${spedizioniere.denomSpedizioniere }" id="ragioneSociale"
								value="" /> <label for="ragioneSociale">Ragione sociale
								operatore professionale</label>
						</div>

						<!------ INPUT::COMUNE SEDE LEGALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${comuneSpedizioniere }" id="denomComuneSedeSociale" />
							<label for="denomComuneSedeSociale">Comune sede legale</label>
						</div>
						<!------ INPUT::PROVINCIA SEDE LEGALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${provinciaSpedizioniere }" id="provinciaSedeLegale" />
							<label for="provinciaSedeLegale">Provincia sede legale</label>
						</div>
						<!------ INPUT::INDIRIZZO SEDE LEGALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${spedizioniere.indirizzoSedeSociale }"
								id="indirizzoSedeSociale" /> <label for="indirizzoSedeSociale">Indirizzo
								sede legale (Es. Via Roma, 24)</label>
						</div>
						<!------ INPUT::CUAA -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${spedizioniere.cuaa }" id="cuaa" /> <label for="cuaa">CUAA</label>
						</div>
						<!------ INPUT::PARTITA IVA -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${spedizioniere.partitaIVA }" id="partitaIva" /> <label
								for="partitaIva">Partita IVA</label>
						</div>

						<!------ INPUT::TELEFONO -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${spedizioniere.numeroTelefono}" id="numeroTelefono" />
							<label for="numeroTelefono">Telefono (Es. 0245673467)</label>
						</div>

						<!------ INPUT::CELLULARE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${spedizioniere.numeroCellulare }" id="numeroCellulare" />
							<label for="numeroCellulare">Cellulare (Es. 3471234567)</label>
						</div>

						<!------ INPUT::MAIL -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${spedizioniere.emailSpedizioniere }"
								id="emailSpedizioniere" /> <label for="emailSpedizioniere">Mail</label>
						</div>

						<!------ INPUT::PEC -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${spedizioniere.pec }" id="pec" /> <label for="pec">Pec</label>
						</div>

						<!------ INPUT::NUMERO ISCRIZIONE RUOP -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${spedizioniere.codiceRUOP }" id="codiceRUOP" /> <label
								for="codiceRUOP">Numero iscrizione RUOP</label>
						</div>

						<!------ INPUT::CODICE CENTRO AZIENDALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${centroAziendale.codCentroAziendale }"
								id="codCentroAziendale" /> <label for="codCentroAziendale">Codice
								centro aziendale</label>
						</div>

						<!------ INPUT::COMUNE CENTRO AZIENDALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled" id="comuneCentroAziendale"
								value="${comuneCentroAziendale }" /> <label
								for="comuneCentroAziendale">Comune centro aziendale</label>
						</div>

						<!------ INPUT::PROVINCIA CENTRO AZIENDALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								id="provinciaCentroAziendale"
								value="${provinciaCentroAziendale }" /> <label
								for="provinciaCentroAziendale">Provincia centro
								aziendale</label>
						</div>

						<!------ INPUT::INDIRIZZO CENTRO AZIENDALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" disabled="disabled"
								value="${centroAziendale.indirizzo }"
								id="indirizzoCentroAziendale" /> <label
								for="indirizzoCentroAziendale">Indirizzo centro
								aziendale (Es. Via Roma, 24)</label>
						</div>

						<!------ INPUT::SOGGETTI SUPPORTO SOPRALLUOGO -------->
						<div class="input-field col s6 m4 l6">
							<form:input type="text" id="soggettiSupportoSopralluogo"
								path="soggettiSupportoSopralluogo" />
							<form:errors path="soggettiSupportoSopralluogo" cssClass="error" />
							<label for="soggettiSupportoSopralluogo">Cognome e nome dei tecnici presenti al controllo ufficiale 
							e motivo della presenza</label>
						</div>

						<div class="col container divider s12" style="margin-bottom: 1em;"></div>
						<div class="row col s12">
							<h6>Norme applicate in verbale</h6>
							<!------ SELECT::NORMA APPLICATIVA VERBALE -------->
							<c:forEach items="${elencoNorme}" var="norma">
								<div class="input-field col s4 m4 l4">
									<form:errors path="idsNormaVerbale" cssClass="error" />
									<label> <form:checkbox path="idsNormaVerbale"
											id="idsNormaVerbale" name="idsNormaVerbale"
											value="${norma.idNormaVerbale}" /> <span>${norma.descBreve }</span>
									</label>
								</div>
							</c:forEach>

							<!------ INPUT::ALTRE NORME -------->
							<div class="input-field col s4 m4 l4">
								<form:input type="text" id="altreNorme" path="altreNorme" />
								<form:errors path="altreNorme" cssClass="error" />
								<label for="altreNorme">Altra norma: </label>
							</div>
						</div>
						<div class="col container divider s12" style="margin-bottom: 1em;"></div>
						
						<!------ TABELLA TIPOLOGIA ATTIVITA -------->
						<div class="row col s12">		
							<h6>Elenco delle attività inserite in fase di domanda registrazione RUOP:</h6>			
							<table id="tabellaAttivita" class="data-table striped display"
									data-order='[[ 2, "asc" ]]' data-paging="false"
									data-info="false">
									<thead>
										<tr>
											<th>Tipologia attività</th>
											<th>Materiale</th>
										</tr>
									</thead>
									<tbody id="bodyTabellaAttivita">
										<c:forEach var="attivita" items="${tipologieAttDb}">
											<c:choose>
												<c:when test="${attivita != null}">
													<tr>
														<td>${attivita.denomTipologiaEstesa}</td>
														<td>${attivita.descEstesa}</td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<td>${attivita.denomTipologiaEstesa}</td>
														<td></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</tbody>
								</table>							
						</div>
						<div class="input-field col s12 m8 l12">
							<form:textarea id="tipologiaAttivita" path="tipologiaAttivita"
								cssClass="materialize-textarea validate" rows="2"
								data-length="1000" maxlength="1000" />
							<form:errors path="tipologiaAttivita" cssClass="error" />
							<label for="tipologiaAttivita">Tipologia Attività annotazioni</label>
						</div>
					</div>
					<div class="row s12">
						<ul class="collapsible" id="collapsible" data-collapsible="accordion">
							<li>
								<div class="collapsible-header" id="datiIspezione">
									<i class="material-icons">arrow_drop_down</i>Aggiungi attività sementiera
								</div>
								<div class="collapsible-body">		
									<h6>Attività sementiera:</h6>
									<c:forEach items="${elencoMaterialiSementi}"
										var="tipoAttivitaSemente">
										<div class="row">
											<div class="input-field col s6 m6 l12">
												<form:errors
													path="idsMaterialeSementi"
													cssClass="error" />
												<label> <form:checkbox path="idsMaterialeSementi" name="idsMaterialeSementi"
														value="${tipoAttivitaSemente.idMateriale}" /> 
													<span>${tipoAttivitaSemente.descEstesa}</span>
												</label>
											</div>										
										</div>
									</c:forEach>
									<div class="col container divider s12"
										style="margin-bottom: 1em; margin-top: 1em;">
									</div>
									
									<!------ SELECT::TIPOLOGIA SEMENTI LAVORATE -------->
									<h6>Sementi lavorate</h6>
									<c:forEach items="${elencoSementi}" var="tipoSemente" varStatus="s">
										<div class="row">						
											<div class="input-field col s6 m6 l6">									
												<form:errors path="sementi[${s.index}].idTipologiaSemente" 
													cssClass="error" />
												<label> <form:checkbox
														path="sementi[${s.index}].idTipologiaSemente"
														name="idsTipologiaSementi" value="${tipoSemente.idTipologiaSemente}" /> 
													<span>${tipoSemente.descEstesa }:</span>
												</label>
											</div>
											<div class="input-field col s6 m6 l6">
												<c:if test="${tipoSemente.idTipologiaSemente==14 || tipoSemente.idTipologiaSemente==20}">
													<form:input type="text" placeholder="specificare"
														path="sementi[${s.index}].note" name="altraSpecie"
														maxlength="100" />
													<form:errors path="sementi[${s.index}].note"
														cssClass="error" />		
													<label for="altraSpecie"></label>
												</c:if>
												<form:input type="text" placeholder="quantità (q.li/anno)"
													path="sementi[${s.index}].quantita" name="quantitaSementi"												
													maxlength="100" id="specificare${s.index}"/>
												<form:errors path="sementi[${s.index}].quantita"
													cssClass="error" />
												<label for="quantita"></label>
											</div>
										</div>									
									</c:forEach>																
								</div>								
							</li>
						</ul>
						</div>						<div class="row col s12">
							<h6>Responsabile dell'azienda che assiste al controllo</h6>
							<!------ INPUT::NOME COGNOME RESPONSABILE AZIENDA CHE ASSISTE AL CONTROLLO -------->
							<div class="input-field col s6">
								<form:input type="text" id="responsabileAzienda"
									path="responsabileAzienda" />
								<form:errors path="responsabileAzienda" cssClass="error" />
								<label for="responsabileAzienda" class="active">Nome e cognome </label>
							</div>

							<!------ SELECT::QUALIFICA -------->
							<div class="input-field col s6">
								<form:select id="qualifica" path="idQualifica"
									cssClass="validate">
									<form:option value="" label="Selezionare" />
									<form:options items="${elencoTipiRespAzienda}"
										itemValue="idTipoRespAzienda" itemLabel="descBreve" />
								</form:select>
								<form:errors path="idQualifica" cssClass="error" />
								<label for="idQualifica">Qualifica</label>
							</div>
						</div>

						<div class="col container divider s12" style="margin-bottom: 1em;"></div>
						<div class="row col s12">

							<!------ SELECT::TIPO CONTROLLO ESEGUITO -------->
							<h6>Tipologia controllo eseguito</h6>
							<c:forEach items="${elencoTipologieControlli}"
								var="tipologiaControllo">
								<div class="input-field col s6 m4 l3">
									<form:errors path="idsTipologiaControllo" cssClass="error" />
									<label> <form:checkbox path="idsTipologiaControllo"
											id="idTipologiaControllo" name="idTipologiaControllo"
											value="${tipologiaControllo.idTipologiaControllo }" /> <span>${tipologiaControllo.descBreve }</span>
									</label>
								</div>
							</c:forEach>
							<!------ INPUT::ALTRO CONTROLLO -------->
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="altroControllo"
									path="altroControllo" />
								<form:errors path="altroControllo" cssClass="error" />
								<label for="altroControllo">Altro controllo </label>
							</div>
						</div>

						<div class="col container divider s12" style="margin-bottom: 1em;"></div>						

						<!------ SELECT::MONITORAGGIO COFINANZIATO -------->
						<c:if test="${false}">
							<div class="col l8">
								<label> <form:checkbox value="S"
										id="monitoraggioCofinanziato" path="monitoraggioCofinanziato" />
									<span>Monitoraggio cofinanziato</span>
								</label>
							</div>
						</c:if>

					
				</div>

				<div class="card-action">
					<div class="row">
						<div class="col s6">
							<a href='<spring:url value="/controlli/elenco"/>'
								class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
						</div>
						<div class="col s6 right-align">
							<button class="btn confirm waves-effect waves-light"
								type="submit" name="datiNuovoControllo">SALVA</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>

	<content tag="script"> <script type="text/javascript">
		$(document).ready(function() {
			if('${hasSementi}'=='true' || '${hasErrorsSementi}'=='true')
			{
				M.Collapsible.getInstance($("#collapsible")).open()
			}
		});

		function aggiornaQuantita(index){

			if(!document.getElementById('check' + index).checked){
				document.getElementById('quantita' + index).value = "";
				document.getElementById('specificare' + index).value = "";
				}
			
			}
	</script> </content>
</body>