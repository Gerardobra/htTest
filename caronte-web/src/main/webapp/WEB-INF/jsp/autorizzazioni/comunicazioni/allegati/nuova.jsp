<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<style>
</style>
</head>
<body>

	<spring:url value="/autorizzazioni/comunicazioni/allegati/nuova"
		var="formAction" />
	<div class="card-panel">
		<div class="row">
			<p class="center-align" style="font-size: 20px; margin-bottom: 5px;">
				<em>Dopo aver inserito tutti gli allegati necessari, premere il tasto inoltra
				per effettuare l'inoltro della domanda.</em>
			</p>
		</div>
	</div>
	<form:form id="formAllegati" action="${formAction}" method="post"
		modelAttribute="allegatiForm" accept-charset="utf-8"
		enctype="multipart/form-data">
		<div class="card">
			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding">
					<b>NUOVA DOMANDA : ${nuovaDomandaForm.descTipoDomanda}</b>
					<jsp:include page="../datiAnagraficiAzienda.jsp">
						<jsp:param name="cuaa" value="${nuovaDomandaForm.codFiscaleAz }" />
						<jsp:param name="ruop" value="${nuovaDomandaForm.codiceRuop }" />
						<jsp:param name="ragSociale"
							value="${nuovaDomandaForm.denomAzienda }" />
					</jsp:include>
				</h3>
			</div>

			<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
			<spring:eval var="statoInBozza"
				expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />
			<div class="card-content" style="padding-top: 5px;">
				<div class="row">
					<div class="col s12">
						<ul class="tabs">
							<li class="tab"><a
								href="<spring:url value="/autorizzazioni/comunicazioni/anagrafica/nuova" />"
								target="_self">Anagrafica</a></li>
							<li class="tab"><a
								href="<spring:url value="/autorizzazioni/comunicazioni/azienda/nuova" />"
								target="_self">Dati operatore</a></li>
							<spring:eval var="idTipoDomandaRUOP"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP" />
							<spring:eval var="idTipoDomandaVariazioneRUOP"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP" />
							<spring:eval var="idTipoDomandaCancellazioneRUOP"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP" />
							<spring:eval var="idTipoDomandaPassaporto"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO" />
							<!-- in caso di Cancellazione non mostro i seguenti tab -->
							<c:if
								test="${idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
								<c:if
									test="${ idTipoDomandaVariazioneRUOP == nuovaDomandaForm.idTipoComunicazione || idTipoDomandaRUOP == nuovaDomandaForm.idTipoComunicazione}">
									<c:forEach var="ceAz" items="${nuovaDomandaForm.listaCentriAz}">
			        				  <li class="tab" >
			        					<a href="<spring:url value="/autorizzazioni/comunicazioni/centroaziendale/nuova/${ceAz.idCentroAziendale}" />" target="_self">${ceAz.codCentroAziendale}</a>
			        				  </li>
		        				  </c:forEach>
								</c:if>
								<c:if test="${nuovaDomandaForm.tabImport}">
									<li class="tab"><a
										href="<spring:url value="/autorizzazioni/comunicazioni/impAuto/nuova" />"
										target="_self">Import</a></li>
								</c:if>
								<c:if test="${nuovaDomandaForm.tabExport}">
									<li class="tab"><a
										href="<spring:url value="/autorizzazioni/comunicazioni/expAuto/nuova" />"
										target="_self">Export</a></li>
								</c:if>
								<c:if test="${nuovaDomandaForm.tabPassaporto}">
									<li class="tab"><a
										href="<spring:url value="/autorizzazioni/comunicazioni/passaporto/nuova" />"
										target="_self">Passaporto</a></li>
								</c:if>
							</c:if>
							<!--  -->
							<li class="tab"><a class="active"><b>Allegati</b></a></li>
						</ul>
					</div>
				</div>

>>>>>>> 992f674cd04a2400b3d7f5219fd646eec3174e91
				<jsp:include page="../includes/boxheader.jsp"></jsp:include>
				<!-- DATI MODULI -->
				<c:if test="${not empty listaModuli}">
					<div class="row">
						<div class="card">
							<div class="card-header white-text">
								<h5
									class="card-title bg-primary-color text-shadow title-padding">
									<b>I moduli proposti sono da scaricare, compilare e
										caricare nell'ultima colonna</b>
								</h5>
							</div>
							<div class="card-content">
								<c:forEach var="modulo" items="${listaModuli}">
									<div class="row valign-wrapper">
										<div class="col s12 m6">
											${fn:escapeXml(modulo.descModulo)}</div>

										<div class="col s3 m1">
											<a
												href="<spring:url value="/autorizzazioni/comunicazioni/modulo/template/${modulo.idTipoModulo}" />"
												class="tooltipped"
												data-tooltip="Scaricare il modulo ${modulo.nomeModulo}">
												<i class="medium material-icons primary-color">file_download</i>
											</a>
										</div>

										<div class="col s3 m1">
											<c:if test="${not empty modulo.idModulo}">
												<a
													href="<spring:url value="/autorizzazioni/comunicazioni/modulo/scarica/${modulo.idModulo}" />"
													class="tooltipped"
													data-tooltip="Scaricare il modulo salvato in precedenza">
													<i class="medium material-icons yellow-text text-darken-4">save</i>
												</a>
											</c:if>
										</div>

										<c:if test="${not empty modulo.idModulo}">
											<div class="col s6 m4">
												<input type="file" id="fileModulo${modulo.idTipoModulo}"
													name="fileModulo" />
											</div>
										</c:if>
										<c:if test="${empty modulo.idModulo}">
											<div class="col s6 m4">
												<input type="file" id="fileModulo${modulo.idTipoModulo}"
													name="fileModulo" required="required" />
											</div>
										</c:if>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:if>
				<!-- DATI ALLEGATI -->
				<c:if test="${not empty listaAllegati}">
					<div class="row">
						<div class="card">
							<div class="card-header white-text">
								<h5
									class="card-title bg-primary-color text-shadow title-padding">
									<b>Allegati per il completamento della domanda</b><b
										style="font-size: 50%;"> (dimensione massima 10 megabyte)</b>
								</h5>
							</div>
							<div class="card-content">
								<c:forEach var="allegato" items="${listaAllegati}">
									<c:if test="${allegato.flagMultiplo}">
										<div class="divider"></div>
									</c:if>
									<div class="row valign-wrapper"
										style="${allegato.flagMultiplo ? 'margin-bottom:0' : ''}">
										<div class="col s3">
											${fn:escapeXml(allegato.descEstesa)}
											<c:if test="${allegato.flagObbligatorio}">
										&nbsp;<b>(<spring:message code="label.mandatory" />)
												</b>
											</c:if>
										</div>

										<c:choose>
											<c:when test="${allegato.flagMultiplo}">
												<div class="col s12">
													<table id="tabellaAllegati${allegato.idTipoAllegato}"
														class="striped display">
														<tbody data-obbligatorio="${allegato.flagObbligatorio}">
															<c:forEach var="allegatoComunicazione"
																items="${allegato.listaAllegati}">
																<tr>
																	<td class="center input-field col s3"><a
																		href="<spring:url value="/autorizzazioni/comunicazioni/allegato/scarica/${allegatoComunicazione.idAllegatoDomanda}" />"
																		class="tooltipped"
																		data-tooltip="Scaricare l'allegato ${fn:escapeXml(allegatoComunicazione.nomeFile)}">
																			<i
																			class="small material-icons yellow-text text-darken-4">save</i>
																	</a> <input type="hidden" name="idTipoAllegato"
																		value="${allegato.idTipoAllegato}" /> <input
																		type="hidden" name="idAllegato"
																		value="${allegatoComunicazione.idAllegatoDomanda}" />
																		<input type="file" name="fileAllegato" value=""
																		style="display: none" /></td>
																	<td class="col s8"><input type="text"
																		id="descAllegato" name="descAllegato"
																		value="${fn:escapeXml(allegatoComunicazione.descAllegato)}" />
																	</td>
																	<td class="center" style="white-spaces: nowrap"><a
																		onClick="eliminaRigaAllegato(this)"
																		class="btn btn-floating btn-floating-medium tooltipped red"
																		data-tooltip="Elimina"> <i class="material-icons">delete</i>
																	</a></td>
																</tr>
															</c:forEach>
															<tr id="rigaTemplate${allegato.idTipoAllegato}"
																style="display: none">
																<td class="left input-field col s6"><input
																	type="file" name="fileAllegato" disabled="disabled" />
																	<input type="hidden" name="idTipoAllegato"
																	value="${allegato.idTipoAllegato}" disabled="disabled" />
																	<input type="hidden" name="idAllegato" value=""
																	disabled="disabled" /></td>
																<td class="col s6"><input type="text"
																	id="descAllegato" name="descAllegato"
																	disabled="disabled" /></td>
																<td class="center" style="white-spaces: nowrap"><a
																	onClick="eliminaRigaAllegato(this)"
																	class="btn btn-floating btn-floating-medium tooltipped red"
																	data-tooltip="Elimina"> <i class="material-icons">delete</i>
																</a></td>
															</tr>
														</tbody>
														<tbody>
															<tr>
																<td></td>
																<td class="center" style="white-spaces: nowrap"><a
																	onclick="aggiungiRigaAllegato(${allegato.idTipoAllegato})"
																	class="btn btn-floating btn-floating-medium tooltipped green"
																	data-tooltip="Aggiungere allegato"> <i
																		class="material-icons">add</i>
																</a></td>
															</tr>
														</tbody>
													</table>
												</div>
											</c:when>
											<c:otherwise>
												<div class="col s1 m1">
													<c:if test="${not empty allegato.listaAllegati}">
														<a
															href="<spring:url value="/autorizzazioni/comunicazioni/allegato/scarica/${allegato.listaAllegati[0].idAllegatoDomanda}" />"
															class="tooltipped"
															data-tooltip="Scaricare l'allegato ${fn:escapeXml(allegato.listaAllegati[0].nomeFile)}">
															<i
															class="medium material-icons yellow-text text-darken-4">save</i>
														</a>
													</c:if>
												</div>

												<div class="col s4">
													<input type="file"
														id="fileAllegato${allegato.idTipoAllegato}"
														name="fileAllegato"
														${empty allegato.listaAllegati and allegato.flagObbligatorio ? 'required ' : ''} />
													<input type="hidden" name="idTipoAllegato"
														value="${allegato.idTipoAllegato}" /> <input
														type="hidden" name="idAllegato"
														value="${empty allegato.listaAllegati ? '' : allegato.listaAllegati[0].idAllegatoDomanda}" />
												</div>
												<div class="col s8">
													<input type="text" id="descAllegato" name="descAllegato"
														value="${empty allegato.listaAllegati ? '' : allegato.listaAllegati[0].descAllegato}"
														maxlength="200" />
												</div>
											</c:otherwise>
										</c:choose>
									</div>
									<c:if test="${allegato.flagMultiplo}">
										<div class="divider" style="margin-bottom: 20px;"></div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:if>

			</div>
		</div>

		<div class="row">
			<a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>'
				class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>

			<div class="right" style="text-align: right">
				<c:if
					test="${(nuovaDomandaForm.abilitaInoltra && idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione) || not empty elencoTabMancanti}">
					<a href='#' title="Inoltra"
						onclick="return checkAperturaModaleInoltra(event)"
						class="btn amber darken-1 waves-effect waves-light"
						style="display: inline-block">INOLTRA</a>

				</c:if>
				<c:if
					test="${nuovaDomandaForm.abilitaInoltra && idTipoDomandaCancellazioneRUOP == nuovaDomandaForm.idTipoComunicazione && empty elencoTabMancanti}">
					<a
						href='<spring:url value="/autorizzazioni/comunicazioni/inoltra"/>'
						title="Inoltra"
						class="btn amber darken-1 waves-effect waves-light"
						style="display: inline-block" onclick="return checkInoltra(this)">INOLTRA</a>
				</c:if>
				<button class="btn confirm waves-effect waves-light" type="submit"
					name="datiComunicazione">salva</button>
			</div>
		</div>

	</form:form>

	<spring:url value="/autorizzazioni/comunicazioni/inoltraDomandaRuop"
		var="formAction2" />
	<form:form action="${formAction2}" method="post"
		modelAttribute="stampaDomandaForm" accept-charset="utf-8"
		enctype="multipart/form-data">

		<!-- Finestra modale per fare l'INOLTRA della Domanda -->
		<div class="modal modal-width-80 top-padding-20" id="confermaInoltra">
			<div class="modal-content"
				style="padding-top: 0.5em; padding-bottom: 0;">
				<h5 class="bg-primary-color white-text bold">Inoltra Domanda</h5>

				<table class="striped">
					<tr>
						<td>${testoInoltra}</td>
						<td>
							<!--
						<a href="<spring:url value="/autorizzazioni/report/domanda/download-${nuovaDomandaForm.idDomanda}" />"
											class="tooltipped" data-tooltip="Scaricare la stampa della domanda">
											<i class="medium material-icons primary-color">file_download</i></a>-->
							<a
							href="<spring:url value="/autorizzazioni/report/domanda/download-${nuovaDomandaForm.idDomanda}" />"
							title="Scaricare la stampa della domanda"
							class="btn cyan darken-1 waves-effect waves-light"
							style="display: inline-block">SCARICA DOMANDA</a>
						</td>
					</tr>
					<tr>
						<td colspan="2"><hr /></td>
					</tr>
					<tr>
						<td>${testoInoltra2}</td>
						<td>
							<!--input type="file" id="fileid" name="fileStampa" required="required" 
								accept=".pdf,.doc,.docx,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"-->
							<b>Carica la domanda firmata</b><br /> <input type="file"
							id="fileid" name="fileStampa">
						</td>

					</tr>
					<tr>
						<td colspan="2"><hr /></td>
					</tr>
					<tr>
						<td colspan="2">${testoInoltra3}</td>
					</tr>
				</table>

				<br />
				<br />
				<div class="modal-footer">
					<div class="left" style="text-align: left">
						<a href='#' title="Chiudi" class="btn waves-effect waves-light"
							style="display: inline-block" onclick="closeModal()">CHIUDI</a>
					</div>
					<div class="right" style="text-align: right">
						<button class="btn amber darken-1 waves-effect waves-light"
							type="submit" name="inoltra" value="INOLTRA"
							onclick="closeModal();">INOLTRA</button>
					</div>
				</div>
			</div>
		</div>
		<!-- fine modale per INOLTRA -->
	</form:form>


	<br />

	<content tag="script"> <script type="text/javascript"
		src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script>
	<script>
			
		// Al caricamento della pagina
		$(document).ready(function() {
			$('[id^=fileModulo]').change(function() {
				if ($(this)[0].files[0].size > 5242880) {
					Swal.fire({
						type: 'error',
						title: 'File troppo grande',
						text: 'Il file supera la dimensione consentita di 5 megabyte'
					})
					    
					$(this).val('');
				};
			});
			
			$('[id^=fileAllegato]').change(function() {
				checkDimensioneAllegato($(this));
				checkRigaObbligatoriaAllegati($(this).closest('tbody'));
			});
			
			$('[id^=tabellaAllegati]').each(function(index) {
				checkRigheTabellaAllegati($(this));
			});
			
			<!--<c:if test="${comunicazioneForm.visualizzaModalInoltra}">
			checkInoltraComunicazione('<spring:url value="/viti/comunicazione/preinoltra"/>');
			</c:if>-->
		})
		
		function checkDimensioneAllegato($input) {
			if ($input[0].files[0].size > 10485760) {
				Swal.fire({
					type: 'error',
					title: 'File troppo grande',
					text: 'Il file supera la dimensione consentita di 10 megabyte'
				})
				    
				$input.val('');
				
				return false;
			};
			
			return true;
		}
		
		function eliminaRigaAllegato(element) {
			$tr = $(element).closest('tr');
			$table = $tr.closest('table');
			
			$tr.find('.tooltipped').tooltip('destroy');
			$tr.remove();

			checkRigheTabellaAllegati($table);
		}
		
		function checkRigheTabellaAllegati($table) {
			//Nel controllo si considerano anche la riga nascosta di template e quella con il pulsante di aggiunta allegato
			if ($table.find('tr').length <= 2) {
				aggiungiRigaAllegato($table.attr('id').match(/\d/g).join(""));
			}
			
			checkRigaObbligatoriaAllegati($table.find('tbody').first());
		}
		
		function aggiungiRigaAllegato(idTipoAllegato) {
			$rigaTemplate = $('#rigaTemplate'+idTipoAllegato);
			
			$riga = $rigaTemplate.clone();
															
			$riga.attr('id', '');
			
			$riga.insertBefore($rigaTemplate);
			
			$riga.find('input').attr('disabled',false);
			
			$riga.find('input[type=file]').change(function() {
				checkDimensioneAllegato($(this));
				checkRigaObbligatoriaAllegati($(this).closest('tbody'));
			});
			
			$riga.find('.tooltipped').tooltip({
				enterDelay : 50
		    });
			
			$riga.show();
			
			checkRigaObbligatoriaAllegati($riga.closest('tbody'));
		}
		
		function checkRigaObbligatoriaAllegati($tbody) {
			
			if ($tbody.data('obbligatorio')) {
				$tbody.find('input[name=fileAllegato]').removeAttr('required');
				
				// Si controlla se esiste un file in visualizzazione nella tabella
				if (!$tbody.find('input[name=idAllegato][value!=""]').length) {
					//Si controlla se esista un input di tipo file valorizzato
					var trovato = false;
					
					$tbody.find('input[name=fileAllegato]').each(function(index) {
						if ($(this).val()) {
							trovato = true;
							return false;
						}
					});
						
					if (!trovato) {
						//Si applica l'attributo 'required' al primo input di tupo file visibile nel tbody
						$tbody.find('input[name=fileAllegato]').filter(":visible").first().attr('required', 'required');
					}
				}
			}
		}


		//var modaleInoltra = null;
		var delayTime = null;
		
		function checkAperturaModaleInoltra(e) {	
			console.log('checkAperturaModaleInoltra');
			
			 $.ajax({
				    // Ricerca del centro aziendale su car_t_centro_aziendale	
				    //avevo usato encodeURIComponent(indirizzoCentroAz), ma non funziona correttamente su macchiene unix(problema il charset)
					url : '<spring:url value="/ajax/getElencoTabMancanti_" />'+ ${idDomanda},
					success : function(response) {	
						console.log("success");
						if(response != ''){
							console.log("ci sono tab non completati");
							$("#elencoTabMancanti").val(response);
							swal({
		                           title: "Impossibile inoltrare la domanda",
		                           html : '<p align="left"> É necessario eseguire le seguenti azioni: ' + response + '</p>',
			       		            type : 'warning',
		                         
		                        }).then(function(){
		                           
		                        });
						}else{
							console.log("tutti i tab sono completati");
							modaleInoltra = $('#confermaInoltra').modal('open');
						}

			}
			 });
				
		}
		
		function closeModal() {
			console.log('closeModal');
			$('#confermaInoltra').modal('close');
			delayTime = 10000;
		}

		//modale inoltro no carico/scarico file
		function checkInoltra() {
	        swal({
	            title : 'I dati sono stati salvati',
	            html : 'Si desidera inoltrare la richiesta?',
	            type : 'question',
	            showCancelButton : true,
	            /*confirmButtonColor: '#3085d6',
	            cancelButtonColor: '#d33',*/
	            confirmButtonText : 'INOLTRA',
	            confirmButtonClass: 'btn amber darken-1 waves-effect waves-light',
	            cancelButtonText : 'ANNULLA',
	            cancelButtonClass: 'btn',
	            focusConfirm: false,
	            buttonsStyling: false,
	        }).then(function(result) {
	            if (result.value) {
	                window.location.href = '<spring:url value="/autorizzazioni/comunicazioni/inoltra"/>';
	            }
	        });
	  
	        return false;
	      }
		
		</script> </content>

</body>
</html>