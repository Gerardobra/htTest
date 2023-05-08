<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="it.aizoon.ersaf.util.CaronteConstants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

</head>
<style>
td input {
    float: left;
    margin: 0 auto;
    width: 100%;
}
td p {
    float: left;
    margin: 0 auto;
    width: 100%;
}
td a {
    float: left;
    margin: 0 auto;
    width: 100%;
}
</style>
<body>

	<div class="row">
		<div class="card col s12">
			<spring:url value="/import/report/verbale/${idRichiesta}"
				var="formAction" />
			<div class="card-content">
				<span class="card-title">Verbale Ispezione</span>
				<div class="row">
					<!-- 
					<div class="input-field col s6 m4 l3">
						<select id="tipoVerbaleIspezione" name="tipoVerbaleIspezione"
							class="validate">
							<option value="1">Prima Stampa</option>
							<option value="2">Duplicato</option>
						</select> <label for="tipoVerbaleIspezione">Tipo verbale
							d'ispezione</label>
					</div>
					-->
					<div class="input-field col s6 m4 l3">
						<input type='text' id="codRichiesta"
							value="${datiVerbaleForm.codRichiesta}" disabled="disabled" /> <label
							for="codRichiesta">Verbale n.</label>
					</div>
				</div>
				<div class="row">
					<ul class="collapsible" data-collapsible="accordion">
						<li>
							<div class="collapsible-header" id="datiIspezione">
								<i class="material-icons">arrow_drop_down</i>Dati Ispezione
							</div>
							<div class="collapsible-body">
								<form:form action="${formAction}" method="post"
									modelAttribute="datiVerbaleForm" accept-charset="utf-8"
									name="datiIspezioneForm">
									<div class="row">

										<div class="input-field col s6 m4">
											<form:input type='text' id="dataIspezione"
												path="dataIspezione" cssClass="datepicker" />
											<form:errors path="dataIspezione" cssClass="error" />
											<label for="dataIspezione">Data ispezione *</label>
										</div>
										
										<div class="input-field col s6 m4">
											<form:input type='text' id="oraInizioIspezione"
												path="oraInizioIspezione" cssClass="timepicker"
												required="required" />
											<form:errors path="oraInizioIspezione" cssClass="error" />
											<label for="oraInizioIspezione">Ora inizio ispezione
												*</label>
										</div>

										<div class="input-field col s6 m4">
											<form:input type='text' id="oraFineIspezione"
												path="oraFineIspezione" cssClass="timepicker"
												required="required" />
											<form:errors path="oraFineIspezione" cssClass="error"/>
											<label for="oraFineIspezione">Ora fine ispezione *</label>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s6 m4">
											<form:input type="text" id="magazzinoDoganale"
												path="magazzinoDoganale" maxlength="100" disabled="true" />
											<form:errors path="magazzinoDoganale" cssClass="error" />
											<label for="magazzinoDoganale">Presso il magazzino
												doganale</label>
										</div>

										<div class="input-field col s6 m4">
											<form:input type="text" id="personaRiferimento"
												path="personaRiferimento" maxlength="100" />
											<form:errors path="personaRiferimento" cssClass="error" />
											<label for="personaRiferimento">Alla presenza del
												Sign.</label>
										</div>

										<div class="input-field col s6 m4">
											<form:input type="text" id="personaRiferimentoRuolo"
												path="personaRiferimentoRuolo" maxlength="100" />
											<form:errors path="personaRiferimentoRuolo" cssClass="error" />
											<label for="personaRiferimentoRuolo">In qualità di</label>
										</div>

									</div>
									<div class="row">
										<button class="btn confirm waves-effect waves-light right"
											type="submit" name="datiIspezione">SALVA</button>
									</div>

								</form:form>
							</div>
						</li>


						<li>
							<div class="collapsible-header">
								<i class="material-icons">arrow_drop_down</i>Dati Spedizione
							</div>
							<div class="collapsible-body">
								<form:form action="${formAction}" method="post"
									modelAttribute="datiVerbaleForm" accept-charset="utf-8"
									name="datiSpedizioneForm">
									<div class="row">

										<div class="col s4 m2">
										  <label>
												<form:checkbox id="tipoProdottoPianteVive" cssClass="right"
													path="tipoProdottoPianteVive" />
												<span>Piante vive</span>
											</label>
										</div>
										<div class="col s4 m2">
										  <label>
												<form:checkbox id="tipoProdottoPartiPianteVive"
													cssClass="right" path="tipoProdottoPartiPianteVive" />
												<span>Parti piante vive</span>
											</label>
										</div>
										<div class="col s4 m2">
										  <label>
												<form:checkbox id="tipoProdottoSementi" cssClass="right"
													path="tipoProdottoSementi" />
												<span>Sementi</span>
											</label>
										</div>
										<div class="col s4 m2">
										  <label>
												<form:checkbox id="tipoProdottoSemi" cssClass="right"
													path="tipoProdottoSemi" />
												<span>Semi</span>
											</label>
										</div>
										<div class="col s4 m2">
										  <label>
												<form:checkbox id="tipoProdottoTerra" cssClass="right"
													path="tipoProdottoTerra" />
												<span>Terra/Terriccio</span>
											</label>
										</div>
										<div class="col s4 m2">
										  <label>
												<form:checkbox id="tipoProdottoCorteccia" cssClass="right"
													path="tipoProdottoCorteccia" />
												<span>Corteccia separata dal tronco</span>
											</label>
										</div>
										<div class="col s4 m2">
										  <label>
												<form:checkbox id="tipoProdottoLegname" cssClass="right"
													path="tipoProdottoLegname" />
												<span>Legname</span>
											</label>
										</div>
										<div class="col s4 m2">
										  <label>
												<form:checkbox id="tipoProdottoFrutti" cssClass="right"
													path="tipoProdottoFrutti" />
												<span>Frutti</span>
											</label>
										</div>
										<div class="col s4 m2">
										  <label>
												<form:checkbox id="tipoProdottoFiori" cssClass="right"
													path="tipoProdottoFiori" />
												<span>Fiori recisi</span>
											</label>
										</div>
										<div class="col s4 m2">
										  <label>
												<form:checkbox id="tipoProdottoImballaggi" cssClass="right"
													path="tipoProdottoImballaggi" />
												<span>Imballaggi in legno</span>
											</label>
										</div>
										<div class="col s4 m4">
											<div class="col s4">
											  <label>
													<form:checkbox id="tipoProdottoAltro" cssClass="right"
														path="tipoProdottoAltro" />
													<span>Altro :</span>
												</label>
											</div>
											<div class="col s8">
												<form:textarea id="tipoProdottoAltroNote"
													path="tipoProdottoAltroNote"
													cssClass="materialize-textarea validate" rows="1"
													data-length="100" maxlength="100" data-position="right"
													placeholder="inserire altra tipologia"
													style="padding:0px" disabled="true" />
												<form:errors path="tipoProdottoAltroNote" cssClass="error" />
											</div>
										</div>
									</div>
									
									<!-- INIZIO TB --> 
								  <div class="row">
				            <table id="tabellaMerci" class="responsive-table bordered display">
				              <colgroup span = "3">
            					<col width = "7%"></col>
            					<col width = "33%"></col>
            					<col width = "10%"></col>
            					<col width = "10%"></col>
            					<col width = "10%"></col>
            					<col width = "10%"></col>
            					<col width = "10%"></col>
            					<col width = "10%"></col>
         					  </colgroup>
				              <thead>
				                <tr>
				                  <th>Numero Partita</th>
				                  <th>Descrizione</th>
				                  <th>Numero colli</th>
				                  <th>Kg</th>
				                  <th>MC</th>
				                  <th>Unità</th>
				                  <th>Quantità analizzata</th>	
				                  <th>Azioni</th>				                  
				                </tr>
				              </thead>
				              <tbody id="bodyTabellaMerci">	             		                	
		                		<c:forEach var="merceRichiesta"
		                  			items="${datiVerbaleForm.listaMerceRichiesta}" varStatus="s">				                  			
                 				<tr style="background-color: #e6e6e6;">
                 
                   				<td>
                   					<form:input type="text" id="idOrdMerce" path="listaMerceRichiesta[${s.index}].idOrdMerce" />
                       				<form:input type="hidden" id="idMerceRichiesta" path="listaMerceRichiesta[${s.index}].idMerceRichiesta" />				                      
                   				</td>
                   
                   				<td>
                   					<form:input type="text" id="descrizioneMerce" path="listaMerceRichiesta[${s.index}].descrizionePartita" />	  
                   					<form:errors path="listaMerceRichiesta[${s.index}].descrizionePartita" cssClass="error" />                    
                   				</td>
                
                        					<td>
                        						<form:input type="text" id="colli" path="listaMerceRichiesta[${s.index}].colli" placeholder="[numero colli]" />
                        						<form:errors path="listaMerceRichiesta[${s.index}].colli" cssClass="error" />
               					</td>
                   
               					<td>
               						<form:input type="text" id="kg" path="listaMerceRichiesta[${s.index}].kg" placeholder="[KG]" />
               						<form:errors path="listaMerceRichiesta[${s.index}].kg" cssClass="error" />
               					</td>
                   
               					<td>
               						<form:input type="text" id="mc" path="listaMerceRichiesta[${s.index}].mc" placeholder="[MC]" />
               						<form:errors path="listaMerceRichiesta[${s.index}].mc" cssClass="error" />
               					</td>
                   
               					<td>
               						<form:input type="text" id="unita" path="listaMerceRichiesta[${s.index}].unita" placeholder="[unità]" />
               						<form:errors path="listaMerceRichiesta[${s.index}].unita" cssClass="error" />
               					</td>
               					
               					<td>
               						<form:input type="text" id="quantitaAnalizzati" path="listaMerceRichiesta[${s.index}].quantitaAnalizzati" placeholder="[quantità analizzata]" />
               						<form:errors path="listaMerceRichiesta[${s.index}].quantitaAnalizzati" cssClass="error" />
               					</td>
               					
               					<td nowrap style="white-spaces: nowrap"><a
                          						id="eliminaMerce" title="Elimina" onClick="eliminaRiga(this)"
                          						class="btn btn-floating btn-floating-medium deep-orange accent-2">
                            					<i class="material-icons red">delete</i>
                        						</a>
                        					</td>  
										 </tr>											
		                  		</c:forEach>
  			             	 </tbody>
				            </table>
				            </div>
				            </br>
				            <div class="row">
				            	<button class="btn confirm waves-effect waves-light right"
                      				type="button" name="aggiungiCampione" onClick="aggiungiRiga()">Aggiungi</button>
                            </div>
									
									<!-- FINE TB -->
									
									
									<div class="row">
                    <div class="input-field col s12">
                      <form:textarea id="altraDocumentazione"
                        path="altraDocumentazione"
                        cssClass="materialize-textarea validate" rows="1"
                        data-length="100" maxlength="100" data-position="right"
                        style="padding:0px" />
                      <label for="altraDocumentazione">Altra documentazione :</label>
                    </div>
                  </div>
									
									<div class="row">
                    <button class="btn confirm waves-effect waves-light right"
                      type="submit" name="datiSpedizione">SALVA</button>
                  </div>
								</form:form>
							</div>
						</li>


						<li>
							<div class="collapsible-header">
								<i class="material-icons">arrow_drop_down</i>Controlli ed Esito
							</div>
							<div class="collapsible-body">
								<form:form action="${formAction}" method="post"
									modelAttribute="datiVerbaleForm" accept-charset="utf-8"
									name="datiControlloEsitoForm">
									<div class="row">
										<div class="col s6">
											<div class="card col s12">
												<div class="row" style="margin-top: 20px;">
													<div class="col s12">
													  <label>
															<form:checkbox id="controlloDocumentale" cssClass="right"
																path="controlloDocumentale" />
															<span>1. Controllo
																documentale conclusosi con esito:</span>
														</label>
													</div>
												</div>
												<div class="row">
													<div class="col s6">
													  <label>
															<form:radiobutton id="controlloDocumentaleFav"
																cssClass="with-gap" path="controlloDocumentaleCB"
																value="S" disabled="true" />
															<span>Favorevole</span>
														</label>
													</div>
												</div>
												<div class="row">
													<div class="col s6">
													  <label>
															<form:radiobutton id="controlloDocumentaleNotFav"
																cssClass="with-gap" path="controlloDocumentaleCB"
																value="N" disabled="true" />
															<span>Non favorevole :</span>
														</label>
													</div>
													<div class="col s6">
														<form:textarea id="controlloDocumentaleNoteNotFav"
															path="noteControlloDocumentaleCB"
															cssClass="materialize-textarea validate" rows="1"
															data-length="100" maxlength="100" data-position="right"
															placeholder="inserire motivazione"
															style="padding:0px" disabled="true" />
														<form:errors path="noteControlloDocumentaleCB"
															cssClass="error" />
													</div>
												</div>
											</div>
										</div>

										<div class="col s6">
											<div class="card col s12">
												<div class="row" style="margin-top: 20px;">
													<div class="col s12">
													  <label>
															<form:checkbox id="controlloIdentita" cssClass="right"
																path="controlloIdentita" />
															<span>2. Controllo di
																identità conclusosi con esito:</span>
														</label>
													</div>
												</div>
												<div class="row">
													<div class="col s6">
													  <label>
															<form:radiobutton id="controlloIdentitaFav"
																cssClass="with-gap" path="controlloIdentitaCB" value="S"
																disabled="true" />
															<span>Favorevole</span>
														</label>
													</div>
												</div>
												<div class="row">
													<div class="col s6">
													  <label>
															<form:radiobutton id="controlloIdentitaNotFav"
																cssClass="with-gap" path="controlloIdentitaCB" value="N"
																disabled="true" />
															<span>Non favorevole :</span>
														</label>
													</div>
													<div class="col s6">
														<form:textarea id="controlloIdentitaNoteNotFav"
															path="noteControlloIdentitaCB"
															cssClass="materialize-textarea validate" rows="1"
															data-length="100" maxlength="100" data-position="right"
															placeholder="inserire motivazione"
															style="padding:0px" disabled="true" />
														<form:errors path="noteControlloIdentitaCB"
															cssClass="error" />
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col s12">
											<div class="card-panel col s12">
												<div class="row" style="margin-top: 20px;">
													<div class="col s12">
													  <label>
															<form:checkbox id="controlloFitosanitario"
																cssClass="right" path="controlloFitosanitario" />
															<span>3. Controllo
																fitosanitario conclusosi con esito complessivo:</span>
														</label>
													</div>
												</div>
												<div class="row">
													<div class="col s3">
													  <label>
															<form:radiobutton id="controlloFitosanitarioFav"
																cssClass="with-gap" path="controlloFitosanitarioCB"
																value="S" />
															<span>Favorevole</span>
														</label>
													</div>
													
													<div class="col s3">
													  <label>
	                            <form:checkbox id="controlloFitosanitarioNotFavIspV"
	                              cssClass="right" path="controlloFitosanitarioIspVisiva" />
	                            <span>Ispezione visiva</span>
                            </label>
                          </div>
												</div>
												<div class="row">
													<div class="col s3">
													  <label>
															<form:radiobutton id="controlloFitosanitarioNotFav"
																cssClass="with-gap" path="controlloFitosanitarioCB"
																value="N" />
															<span>Non favorevole</span>
														</label>
													</div>
													
													<div class="col s4">
													  <label>
	                            <form:checkbox id="controlloFitosanitarioNotFavIspS"
	                              cssClass="right" path="controlloFitosanitarioIspStrum" />
	                            <span>Ispezione strumentale mediante :</span>
                            </label>
                          </div>
													
													<div class="col s5">
                            <form:textarea id="controlloFitosanitarioNoteNotFav"
                              path="noteControlloFitosanitarioCB"
                              cssClass="materialize-textarea validate" rows="1"
                              data-length="100" maxlength="100" data-position="right"
                              placeholder="inserire strumento"
                              style="padding:0px" />
                            <form:errors path="noteControlloFitosanitarioCB"
                              cssClass="error" />
                          </div>
													
												</div>

											</div>
										</div>
									</div>

									<div class="row">
										<div class="col s6">
											<label for="txPrelievo">Prelievi effettuati per la
												ricerca di :</label>
											<form:textarea id="txPrelievo" path="prelievoPerRicerca"
												cssClass="materialize-textarea validate" rows="1"
												data-length="200" maxlength="200" data-position="right"
												style="padding:0px" />
											<form:errors path="prelievoPerRicerca" cssClass="error" />
										</div>
										<div class="col s6">
											<label for="txPrelievo">Codice campione :</label>
											<form:textarea id="txPrelievo" path="codiceCampione"
												cssClass="materialize-textarea validate" rows="1"
												data-length="200" maxlength="200" data-position="right"
												style="padding:0px" />
											<form:errors path="codiceCampione" cssClass="error" />
										</div>
									</div>

									<div class="row">
										<div class="col s12 ">
											<div class="card-panel col s12">
												<div class="row" style="margin-top: 20px;">
													<div class="col s4">
														<label>Il responsabile/delegato della ditta
															dichiara :</label>
													</div>
													<div class="col s4">
													  <label>
															<form:radiobutton id="visioneAnalisiS" cssClass="with-gap"
																path="visioneAnalisiRB" value="N" />
															<span>Di non voler assistere alle analisi</span>
														</label>
													</div>
													<div class="col s4">
													  <label>
															<form:radiobutton id="visioneAnalisiN" cssClass="with-gap"
																path="visioneAnalisiRB" value="S" />
															<span>Di voler assistere alle analisi</span>
														</label>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col s12 ">
											<div class="card-panel col s12">
												<div class="row" style="margin-top: 20px;">
													<div class="col s3">
														<label>Consulto con responsabile fitosanitario :</label>
													</div>
													<div class="col s3">
														<div class="col s6">
														  <label>
																<form:radiobutton id="consultoResponsabileS"
																	cssClass="with-gap" path="consultoResponsabileRB"
																	value="S" />
																<span>Sì</span>
															</label>
														</div>
														<div class="col s6">
														  <label>
																<form:radiobutton id="consultoResponsabileN"
																	cssClass="with-gap" path="consultoResponsabileRB"
																	value="N" />
																<span>No</span>
															</label>
														</div>
													</div>
													<div class="col s3">
														<label>Si allega evidenza :</label>
													</div>
													<div class="col s3">
														<div class="col s6">
														  <label>
																<form:radiobutton id="allegaEvidenzaS"
																	cssClass="with-gap" path="allegaEvidenzaRB" value="S" />
																<span>Sì</span>
															</label>
														</div>
														<div class="col s6">
														  <label>
																<form:radiobutton id="allegaEvidenzaN"
																	cssClass="with-gap" path="allegaEvidenzaRB" value="N" />
																<span>No</span>
															</label>
														</div>
													</div>
												</div>

												<div class="row" style="margin-bottom: 0px;">
													<div class="col s3">
														<label for="allegaEvidenzaS">Al termine
															dell'ispezione :</label>
													</div>
													<div class="col s5">
														<form:select id="termIspezioneNullaOsta"
															path="termIspezioneNullaOsta" cssClass="validate">
															<form:option value="" label="Selezionare" />
															<form:option value="S" label="Rilasciato Nulla osta" />
															<form:option value="N" label="Non rilasciato Nulla osta" />
														</form:select>
														<form:errors path="termIspezioneNullaOsta"
															cssClass="error" />
													</div>
													<div class="col s4">
														<form:textarea id="termIspezioneNullaOstaNote"
															path="termIspezioneNullaOstaNote"
															cssClass="materialize-textarea validate" rows="1"
															data-length="100" maxlength="100" data-position="right"
															style="padding:0px" disabled="true" />
													</div>
												</div>

												<div class="row" style="margin-bottom: 0px;">
													<div class="col s6">
													  <label>
															<form:checkbox id="termIspezioneMisUff" cssClass="right"
																path="termIspezioneMisUff" />
															<span>È stata disposta
																la misura ufficiale di cui alle lettere (A-F),
																motivazione :</span>
														</label>
													</div>
													<div class="col s6">
														<form:textarea id="termIspezioneMisUffNote"
															path="termIspezioneMisUffNote"
															cssClass="materialize-textarea validate" rows="1"
															data-length="100" maxlength="100" data-position="right"
															placeholder="inserire motivazione"
															style="padding:0px" disabled="true" />
														<form:errors path="termIspezioneMisUffNote"
															cssClass="error" />
													</div>
												</div>

											</div>
										</div>
									</div>

									<div class="row">
										<button class="btn confirm waves-effect waves-light right"
											type="submit" name="datiControlloEsito">SALVA</button>
									</div>

								</form:form>
							</div>
						</li>


						<li>
							<div class="collapsible-header">
								<i class="material-icons">arrow_drop_down</i>Misure Ufficiali
							</div>
							<div class="collapsible-body">
								<form:form action="${formAction}" method="post"
									modelAttribute="datiVerbaleForm" accept-charset="utf-8"
									name="datiMisureUfficialiForm">
									<div class="row">
										<div class="col s6">
										  <label>
												<form:checkbox id="misUffA" cssClass="right" path="misUffA" />
												<span>A. Rifiuto di tutta / parte della spedizione :</span>
											</label>
										</div>
										<div class="col s6">
											<form:textarea id="misUffANote" path="misUffANote"
												cssClass="materialize-textarea validate" rows="1"
												data-length="100" maxlength="100" data-position="right"
												placeholder="inserire motivazione"
												style="padding:0px" disabled="true" />
											<form:errors path="misUffANote" cssClass="error" />
										</div>
									</div>

									<div class="row">
										<div class="col s6">
										  <label>
												<form:checkbox id="misUffB" cssClass="right" path="misUffB" />
												<span>B. Trasporto verso una
													destinazione esterna alla Comunità :</span>
											</label>
										</div>
										<div class="col s6">
											<form:textarea id="misUffBNote" path="misUffBNote"
												cssClass="materialize-textarea validate" rows="1"
												data-length="100" maxlength="100" data-position="right"
												placeholder="inserire motivazione"
												style="padding:0px" disabled="true" />

											<form:errors path="misUffBNote" cssClass="error" />
										</div>
									</div>

									<div class="row">
										<div class="col s6">
										  <label>
												<form:checkbox id="misUffC" cssClass="right" path="misUffC" />
												<span>C. Rimozione dalla spedizione
													dei prodotti infetti o infestati :</span>
											</label>
										</div>
										<div class="col s6">
											<form:textarea id="misUffCNote" path="misUffCNote"
												cssClass="materialize-textarea validate" rows="1"
												data-length="100" maxlength="100" data-position="right"
												placeholder="inserire motivazione"
												style="padding:0px" disabled="true" />

											<form:errors path="misUffCNote" cssClass="error" />
										</div>
									</div>

									<div class="row">
										<div class="col s6">
										  <label>
												<form:checkbox id="misUffD" cssClass="right" path="misUffD" />
												<span>D. Distruzione :</span>
											</label>
										</div>
										<div class="col s6">
											<form:textarea id="misUffDNote" path="misUffDNote"
												cssClass="materialize-textarea validate" rows="1"
												data-length="100" maxlength="100" data-position="right"
												placeholder="inserire motivazione"
												style="padding:0px" disabled="true" />
											<form:errors path="misUffDNote" cssClass="error" />
										</div>
									</div>

									<div class="row">
										<div class="col s12">
										  <label>
												<form:checkbox id="misUffE" cssClass="right" path="misUffE" />
												<span>E. Imposizione di un periodo di
													quarantena fino alla conclusione degli esami o delle analisi
													ufficiali</span>
											</label>
										</div>
									</div>

									<div class="row">
										<div class="col s6">
										  <label>
												<form:checkbox id="misUffF" cssClass="right" path="misUffF" />
												<span>F. Trattamento adeguato :</span>
											</label>
										</div>
										<div class="col s6">
											<form:textarea id="misUffFNote" path="misUffFNote"
												cssClass="materialize-textarea validate" rows="1"
												data-length="100" maxlength="100" data-position="right"
												placeholder="inserire motivazione"
												style="padding:0px" disabled="true" />
											<form:errors path="misUffFNote" cssClass="error" />
										</div>
									</div>

									<div class="row">
										<button class="btn confirm waves-effect waves-light right"
											type="submit" name="datiMisureUfficiali">SALVA</button>
									</div>
								</form:form>
							</div>
						</li>


						<li>
							<div class="collapsible-header">
								<i class="material-icons">arrow_drop_down</i>Custodia
							</div>
							<div class="collapsible-body">
								<form:form action="${formAction}" method="post"
									modelAttribute="datiVerbaleForm" accept-charset="utf-8"
									name="datiCustodiaForm">
									<div class="row">
										<div class="input-field col s6 m4">
											<form:input type="text" id="custResponsabileMerce"
												path="custResponsabileMerce" maxlength="100"
												title="Merce oggetto della misura ufficiale lasciata in custodia al Sign." />
											<form:errors path="custResponsabileMerce" cssClass="error" />
											<label for="custResponsabileMerce">Merce in custodia
												al Sign. :</label>
										</div>
										<div class="input-field col s6 m4">
											<form:input type="text" id="custDocumentoRespMerce"
												path="custDocumentoRespMerce" maxlength="100" />
											<form:errors path="custDocumentoRespMerce" cssClass="error" />
											<label for="custDocumentoRespMerce">Identificato
												mediante C.I./PAT n. :</label>
										</div>
										<div class="input-field col s6 m4">
											<form:input type="text" id="custRuoloRespMerce"
												path="custRuoloRespMerce" maxlength="100" />
											<form:errors path="custRuoloRespMerce" cssClass="error" />
											<label for="custRuoloRespMerce">In qualità di :</label>
										</div>
										<div class="input-field col s6 m4">
											<form:input type="text" id="custLocaliMerce"
												path="custLocaliMerce" maxlength="100" />
											<form:errors path="custLocaliMerce" cssClass="error" />
											<label for="custLocaliMerce">Nei locali :</label>
										</div>
									</div>

									<div class="row">
										<button class="btn confirm waves-effect waves-light right"
											type="submit" name="datiCustodia">SALVA</button>
									</div>
								</form:form>
							</div>
						</li>


						<li>
							<div class="collapsible-header">
								<i class="material-icons">arrow_drop_down</i>Dichiarazioni e
								Note
							</div>
							<div class="collapsible-body">
								<form:form action="${formAction}" method="post"
									modelAttribute="datiVerbaleForm" accept-charset="utf-8"
									name="datiDichiarazioniNoteForm">
									<div class="row">
										<div class="input-field col s6 m4">
											<form:input type="text" id="dichNoteRespVerbale"
												path="dichNoteRespVerbale" maxlength="100"
												title="Copia del verbale d’ispezione consegnato al Sign." />
											<form:errors path="dichNoteRespVerbale" cssClass="error" />
											<label for="dichNoteRespVerbale">Copia consegnata al
												Sign. :</label>
										</div>
										<div class="input-field col s6 m4">
											<form:input type="text" id="dichNoteRuoloRespVerbale"
												path="dichNoteRuoloRespVerbale" maxlength="100" />
											<form:errors path="dichNoteRuoloRespVerbale" cssClass="error" />
											<label for="dichNoteRuoloRespVerbale">In qualità di :</label>
										</div>
										<div class="input-field col s6 m4">
											<form:input type="text" id="dichNoteDichiarazioneRespVerb"
												path="dichNoteDichiarazioneRespVerb" maxlength="100" />
											<form:errors path="dichNoteDichiarazioneRespVerb"
												cssClass="error" />
											<label for="dichNoteDichiarazioneRespVerb">Il quale
												dichiara :</label>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s12">
											<form:textarea id="dichNoteNoteRespVerb"
												path="dichNoteNoteRespVerb"
												cssClass="materialize-textarea validate" rows="1"
												data-length="100" maxlength="100" data-position="right"
												style="padding:0px" />
											<label for="dichNoteNoteRespVerb">Note :</label>
										</div>
									</div>
									<div class="row">
										<button class="btn confirm waves-effect waves-light right"
											type="submit" name="datiDichiarazioniNote">SALVA</button>
									</div>
								</form:form>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="card-action">
				<div class="row">

					<div class="right" style="text-align: right">

						<a class="btn green waves-effect waves-light "
							href="${formAction}/stampa" style="display: inline-block">STAMPA
							VERBALE</a>

					</div>
				</div>
			</div>

		</div>

		<div class="col s12 left-align">
			<a href='<spring:url value="/import/richieste/elenco"/>'
				class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
		</div>
	</div>
	<content tag="script"> <script>
    $(document).ready(function() {
      $('.collapsible').collapsible('open', '${datiVerbaleForm.accordionSelezionato}');    
      
      if ("${datiVerbaleForm.termIspezioneNullaOsta}" == "N") {
         $('#termIspezioneNullaOstaNote').attr('disabled', false);
         $('#termIspezioneNullaOstaNote').val("${datiVerbaleForm.termIspezioneNullaOstaNote}");
         $('#termIspezioneNullaOstaNote').text("${datiVerbaleForm.termIspezioneNullaOstaNote}");
      } else if ("${datiVerbaleForm.termIspezioneNullaOsta}" == "S") {
         $('#termIspezioneNullaOstaNote').attr('disabled', true);
         $('#termIspezioneNullaOstaNote').val("${datiVerbaleForm.numeroCertificato}");
         $('#termIspezioneNullaOstaNote').text("${datiVerbaleForm.numeroCertificato}");
      } else {
         $('#termIspezioneNullaOstaNote').attr('disabled', true);
         $('#termIspezioneNullaOstaNote').val(null);
         $('#termIspezioneNullaOstaNote').text(null);
      }
      
      if ("${datiVerbaleForm.termIspezioneMisUff}") {
        $('#termIspezioneMisUffNote').attr('disabled', false);
      }      
      
      if ("${datiVerbaleForm.tipoProdottoAltro}") {
        $('#tipoProdottoAltroNote').attr('disabled', false);
      }

      if ("${datiVerbaleForm.misUffA}") {
        $('#misUffANote').attr('disabled', false);
      }
      if ("${datiVerbaleForm.misUffB}") {
        $('#misUffBNote').attr('disabled', false);
      }
      if ("${datiVerbaleForm.misUffC}") {
        $('#misUffCNote').attr('disabled', false);
      }
      if ("${datiVerbaleForm.misUffD}") {
        $('#misUffDNote').attr('disabled', false);
      }
      if ("${datiVerbaleForm.misUffF}") {
        $('#misUffFNote').attr('disabled', false);
      }     
      
      checkControlli($('#controlloDocumentale'));
      checkControlli($('#controlloIdentita'));
      checkControlli($('#controlloFitosanitario'));
    });

    $('#controlloDocumentale').change(function() {
      checkControlli($(this));
    });

    $('#controlloIdentita').change(function() {
      checkControlli($(this));
    });

    $('#controlloFitosanitario').change(function() {
      checkControlli($(this));
    });

    function checkControlli($checkbox) {
      if ($checkbox.is(":checked")) {
        $('#' + $checkbox.attr('id') + 'Fav').attr('disabled', false);
        $('#' + $checkbox.attr('id') + 'NotFav').attr('disabled', false);
        $('#' + $checkbox.attr('id') + 'NotFavIspS').attr('disabled', false);
        $('#' + $checkbox.attr('id') + 'NotFavIspV').attr('disabled', false);
        
        if ($('#' + $checkbox.attr('id') + 'NotFavIspS')[0]) {
        	if ($('#' + $checkbox.attr('id') + 'NotFavIspS').is(":checked")) {
        		  $('#' + $checkbox.attr('id') + 'NoteNotFav').attr('disabled', false);
        	}else {
        		$('#' + $checkbox.attr('id') + 'NoteNotFav').attr('disabled', true);
        	}
        }else if ($('#' + $checkbox.attr('id') + 'NotFav').is(":checked")) {
        	$('#' + $checkbox.attr('id') + 'NoteNotFav').attr('disabled', false);
        }else {
        	$('#' + $checkbox.attr('id') + 'NoteNotFav').attr('disabled', true);
        }
        
      } else {
        $('#' + $checkbox.attr('id') + 'Fav').attr('disabled', true);
        $('#' + $checkbox.attr('id') + 'NotFav').attr('disabled', true);
        $('#' + $checkbox.attr('id') + 'NotFavIspS').attr('disabled', true);
        $('#' + $checkbox.attr('id') + 'NotFavIspV').attr('disabled', true);
        $('#' + $checkbox.attr('id') + 'NoteNotFav').attr('disabled', true);
      }
    }

    $('#controlloDocumentaleNotFav').change(function() {
      if ($(this).is(":checked") && $('#controlloDocumentale').is(":checked")) {
        $('#controlloDocumentaleNoteNotFav').attr('disabled', false);
      }
    });

    $('#controlloDocumentaleFav').change(function() {
      if ($(this).is(":checked") && $('#controlloDocumentale').is(":checked")) {
        $('#controlloDocumentaleNoteNotFav').attr('disabled', true);
      }
    });

    $('#controlloIdentitaNotFav').change(function() {
      if ($(this).is(":checked") && $('#controlloIdentita').is(":checked")) {
        $('#controlloIdentitaNoteNotFav').attr('disabled', false);
      }
    });

    $('#controlloIdentitaFav').change(function() {
      if ($(this).is(":checked") && $('#controlloIdentita').is(":checked")) {
        $('#controlloIdentitaNoteNotFav').attr('disabled', true);
      }
    });

    $('#controlloFitosanitarioNotFavIspS').change(
        function() {
          if ($(this).is(":checked")
              && $('#controlloFitosanitarioNotFav').is(":checked")
              && $('#controlloFitosanitario').is(":checked")) {
            $('#controlloFitosanitarioNoteNotFav').attr('disabled', false);
          } else {
            $('#controlloFitosanitarioNoteNotFav').attr('disabled', true);
          }
        });

    $('#termIspezioneNullaOsta').change(function() {
       if ($(this).val() == "N") {
          $('#termIspezioneNullaOstaNote').attr('disabled', false);
          $('#termIspezioneNullaOstaNote').val("${datiVerbaleForm.termIspezioneNullaOstaNote}");
       } else if ($(this).val() == "S") {
          $('#termIspezioneNullaOstaNote').attr('disabled', true);
          $('#termIspezioneNullaOstaNote').val("${datiVerbaleForm.numeroCertificato}");
       } else {
          $('#termIspezioneNullaOstaNote').attr('disabled', true);
          $('#termIspezioneNullaOstaNote').val("");
       }
    });
    

    $('#termIspezioneMisUff').change(function() {
      if ($(this).is(":checked")) {
        $('#termIspezioneMisUffNote').attr('disabled', false);
      } else {
        $('#termIspezioneMisUffNote').attr('disabled', true);
      }
    });
    
    $('#tipoProdottoAltro').change(function() {
      if ($(this).is(":checked")) {
        $('#tipoProdottoAltroNote').attr('disabled', false);
      } else {
        $('#tipoProdottoAltroNote').attr('disabled', true);
      }
    });   
    

    $('#misUffA').change(function() {
      checkMisureUfficiali($(this));
    });
    $('#misUffB').change(function() {
      checkMisureUfficiali($(this));
    });
    $('#misUffC').change(function() {
      checkMisureUfficiali($(this));
    });
    $('#misUffD').change(function() {
      checkMisureUfficiali($(this));
    });
    $('#misUffF').change(function() {
      checkMisureUfficiali($(this));
    });

    function checkMisureUfficiali($checkbox) {
      if ($checkbox.is(":checked")) {
        $('#' + $checkbox.attr('id') + 'Note').attr('disabled', false);
      } else {
        $('#' + $checkbox.attr('id') + 'Note').attr('disabled', true);
      }
    }
    
    function eliminaRiga(element) {
      $(element).closest('tr').remove();

      //Nel controllo si considerano anche la riga di intestazione e quella nascosta di template
      if ($('#tabellaMerci tr').length == 0) {
        $('#tabellaMerci').hide();
      }
      var length = $('#tabellaMerci tr').length; 
      var idOrdMerce = 'a';
      $('#tabellaMerci tbody tr').each(function() {
			$(this).find('#idOrdMerce').val(idOrdMerce);
			idOrdMerce = String.fromCharCode(idOrdMerce.charCodeAt() + 1);
      });
    }
    
    function aggiungiRiga() {   
     	var idOrdMerce = $('#tabellaMerci tr').last().find("#idOrdMerce").val();
     	var index = $('#tabellaMerci tr').length;
     	var nextIdOrdMerce = idOrdMerce !== undefined ? String.fromCharCode(idOrdMerce.charCodeAt() + 1) : 'a';
     	var html = '';
     	html = html + '<tr style="background-color: #e6e6e6;">'; 
     	html = html + '<td><input type="text" id="idOrdMerce" name="listaMerceRichiesta['+index+'].idOrdMerce" value="'+nextIdOrdMerce+'"/></td>';
     	html = html + '<td><input type="text" id="descrizioneMerce" name="listaMerceRichiesta['+index+'].descrizionePartita" /></td>';
     	html = html + '<td><input type="text" id="colli" name="listaMerceRichiesta['+index+'].colli" placeholder="[numero colli]" /></td>';
     	html = html + '<td><input type="text" id="kg" name="listaMerceRichiesta['+index+'].kg" placeholder="[KG]" /></td>';
     	html = html + '<td><input type="text" id="mc" name="listaMerceRichiesta['+index+'].mc" placeholder="[MC]" /></td>';
     	html = html + '<td><input type="text" id="unita" name="listaMerceRichiesta['+index+'].unita" placeholder="[unità]" /></td>';
     	html = html + '<td><input type="text" id="quantitaAnalizzati" name="listaMerceRichiesta['+index+'].quantitaAnalizzati" placeholder="[quantità analizzata]" /></td>';
     	html = html + '<td nowrap style="white-spaces: nowrap"><a id="eliminaMerce" title="Elimina" onClick="eliminaRiga(this)" class="btn btn-floating btn-floating-medium deep-orange accent-2"><i class="material-icons red">delete</i></a></td> ';
     	html = html + '</tr>';
     	$('#bodyTabellaMerci').append(html);
     	$('#tabellaMerci').show();
    }

//     $('#datiSpedizioneForm').submit(function() {
// 		var listLength = $('#tabellaMerci tr').length;
//     	validator = $("#datiSpedizioneForm").validate({

//     	});

//     	$('#tabellaMerci tbody tr').each(function() {
//     		$(this).find('#colli').rules('add', {
//     			required : false,
// 				minStrict: 0,
// 				max: 999999999,
// 				number: true,
// 				messages: {
// 					number: "Inserire un valore numerico"
// 				}
//         	});
//     		$(this).find('#kg').rules('add', {
//     			required : false,
// 				minStrict: 0,
// 				max: 999999999,
// 				number: true,
// 				messages: {
// 					number: "Inserire un valore numerico"
// 				}
//         	});
//     		$(this).find('#mc').rules('add', {
//     			required : false,
// 				minStrict: 0,
// 				max: 999999999,
// 				number: true,
// 				messages: {
// 					number: "Inserire un valore numerico"
// 				}
//         	});
//     		$(this).find('#unita').rules('add', {
//     			required : false,
// 				minStrict: 0,
// 				max: 999999999,
// 				number: true,
// 				messages: {
// 					number: "Inserire un valore numerico"
// 				}
//         	});
//     	});

//     	validator.form();

//     });
        
        
        
  </script> </content>
</body>
</html>