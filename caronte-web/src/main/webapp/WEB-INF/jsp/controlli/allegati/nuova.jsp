<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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

	<spring:url value="/controlli/allegati/nuova" var="formAction" />

	<form:form id="formAllegati" action="${formAction}" method="post" modelAttribute="allegatiForm" accept-charset="utf-8" enctype="multipart/form-data">
		<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>ALLEGATI</b></h3>
			</div>
			<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />	
  			<spring:eval var="statoInBozza"	expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />	
  			<div class="card-content" style="padding-top:5px;">
  				<div class="row">
   	 				<div class="col s12">
      					<ul class="tabs">        						        				    		        	
			        	    <li class="tab" >
	        					<a href="<spring:url value="/controlli/datiBase/nuova" />" target="_self">Dati generali</a>
	        				</li>
	        				<c:if test="${nuovoControlloForm.tabDocumentale}">	
	        					<li class="tab" >
	        				   		<a href="<spring:url value="/controlli/controllodocumentale/nuova" />" target="_self">Documentale</a>
        						</li>
        					</c:if>	
        					<c:if test="${nuovoControlloForm.tabIdentita}">
        						<li class="tab" >
	        						<a href="<spring:url value="/controlli/controlloidentita/nuova" />" target="_self">Identità</a>
	        					</li>
	        				</c:if>	
	        				<c:if test="${nuovoControlloForm.tabFisico}">
	        					<li class="tab" >
	        						<a href="<spring:url value="/controlli/controllofisico/nuova" />" target="_self">Fisico</a>
	        					</li>
	        				</c:if>	
	        				<c:if test="${nuovoControlloForm.tabFisico && nuovoControlloForm.flControllo5x23 != null && nuovoControlloForm.flControllo5x23 =='S' }">
		        				<li class="tab" >
		        					<a href="<spring:url value="/controlli/campioni/nuova" />" target="_self">Campioni</a>
		        				</li>
	        				</c:if>
	        				<li class="tab" >
	        					<a class="active"><b>Allegati</b></a>
	        				</li>
	        				<c:if test="${false}">
		        				<li class="tab" >
		        					<a href="<spring:url value="/controlli/monitoraggio/modifica" />" target="_self">Monitoraggio cofinanziato</a>
		        				</li>
	        				</c:if>	 
	        				<c:if test="${nuovoControlloForm.misuraUfficiale != null && nuovoControlloForm.misuraUfficiale =='S' }">
	        					<li class="tab" >
        					  		<a href="<spring:url value="/controlli/misura/nuova" />" target="_self">Misura ufficiale</a>
        				    	</li>
        				    </c:if>				        					        				        			
	        				<li class="tab" >
	        					<a href="<spring:url value="/controlli/esito/nuova" />" target="_self">Esito</a>
	        				</li>
		        					
      					</ul>
    				</div>
  				</div>
        						  															
				
				<!-- DATI MODULI -->
				<c:if test="${not empty listaModuli}">
				<div class="row">
					<div class="card">
						<div class="card-header white-text">
							<h5 class="card-title bg-primary-color text-shadow title-padding"><b>I moduli proposti sono da scaricare, compilare e caricare nell'ultima colonna</b></h5>
						</div>
						<div class="card-content">
							<c:forEach var="modulo" items="${listaModuli}">
							<div class="row valign-wrapper">
								<div class="col s12 m6">
									${fn:escapeXml(modulo.descModulo)}
								</div>
								
								<div class="col s3 m1">
									<a href="<spring:url value="/autorizzazioni/comunicazioni/modulo/template/${modulo.idTipoModulo}" />"
										class="tooltipped" data-tooltip="Scaricare il modulo ${modulo.nomeModulo}">
										<i class="medium material-icons primary-color">file_download</i></a>
								</div>
								
								<div class="col s3 m1">
									<c:if test="${not empty modulo.idModulo}">
									<a href="<spring:url value="/autorizzazioni/comunicazioni/modulo/scarica/${modulo.idModulo}" />"
										class="tooltipped" data-tooltip="Scaricare il modulo salvato in precedenza">
										<i class="medium material-icons yellow-text text-darken-4">save</i></a>
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
											name="fileModulo" required="required"/>
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
							<h5 class="card-title bg-primary-color text-shadow title-padding"><b>Allegati per il completamento dei controlli</b><b style="font-size: 50%;"> (dimensione massima 10 megabyte)</b></h5>
						</div>
						<div class="card-content">
							<c:forEach var="allegato" items="${listaAllegati}">
							<c:if test="${allegato.flagMultiplo}">
							<div class="divider"></div>
							</c:if>
							<div class="row valign-wrapper" style="${allegato.flagMultiplo ? 'margin-bottom:0' : ''}">
								<div class="col s3">
									${fn:escapeXml(allegato.descEstesa)}
									<c:if test="${allegato.flagObbligatorio}">
										&nbsp;<b>(<spring:message code="label.mandatory" />)</b>
									</c:if>
								</div>
								
								<c:choose>
								<c:when test="${allegato.flagMultiplo}">
								<div class="col s12">
									<table id="tabellaAllegati${allegato.idTipoAllegato}" 
										class="striped display">
										<tbody data-obbligatorio="${allegato.flagObbligatorio}">
											<c:forEach var="allegatoControllo" items="${allegato.listaAllegatiControllo}" >
											<tr>
												<td class="center input-field col s3">
													<a href="<spring:url value="/controlli/allegato/scarica/${allegatoControllo.idAllegatoControllo}" />"
														class="tooltipped" data-tooltip="Scaricare l'allegato ${fn:escapeXml(allegatoControllo.nomeFile)}">
														<i class="small material-icons yellow-text text-darken-4">save</i></a>
													<input type="hidden" name="idTipoAllegato" value="${allegato.idTipoAllegato}" />
													<input type="hidden" name="idAllegato" value="${allegatoControllo.idAllegatoControllo}" />									  			
													<input type="file" name="fileAllegato" value="" style="display:none" />
												</td>
												<td class="col s8" >
													<input type="text" id="descAllegato" name="descAllegato" value="${fn:escapeXml(allegatoControllo.descAllegato)}"/>
												</td>												
												<td class="center" style="white-spaces: nowrap">
													<a onClick="eliminaRigaAllegato(this)"
														class="btn btn-floating btn-floating-medium tooltipped red"
														data-tooltip="Elimina">
														<i class="material-icons">delete</i>
													</a>
												</td>
											</tr>
											</c:forEach>
											<tr id="rigaTemplate${allegato.idTipoAllegato}" style="display: none">
								   				<td class="left input-field col s6">
								   					<input type="file" name="fileAllegato" disabled="disabled" />
								   					<input type="hidden" name="idTipoAllegato" 
								   						value="${allegato.idTipoAllegato}" disabled="disabled" />
													<input type="hidden" name="idAllegato" value="" disabled="disabled" />
								   				</td>
								   				<td class="col s6">
													<input type="text" id="descAllegato" name="descAllegato" disabled="disabled" />
								   				</td>
								   				<td class="center" style="white-spaces: nowrap">
								   	  				<a onClick="eliminaRigaAllegato(this)"
														class="btn btn-floating btn-floating-medium tooltipped red"
														data-tooltip="Elimina">
														<i class="material-icons">delete</i>
													</a>
												</td>
											</tr>
										</tbody>
										<tbody>
											<tr>
												<td></td>
												<td class="center" style="white-spaces: nowrap">
			 										<a onclick="aggiungiRigaAllegato(${allegato.idTipoAllegato})" 
			 											class="btn btn-floating btn-floating-medium tooltipped green" 
			 											data-tooltip="Aggiungere allegato">
														<i class="material-icons">add</i>
													</a>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								</c:when>
								<c:otherwise>
								<div class="col s1 m1">
									<c:if test="${not empty allegato.listaAllegatiControllo}">
										<a href="<spring:url value="/controlli/allegato/scarica/${allegato.listaAllegatiControllo[0].idAllegatoControllo}" />"
											class="tooltipped" data-tooltip="Scaricare l'allegato ${fn:escapeXml(allegato.listaAllegatiControllo[0].nomeFile)}">
										<i class="medium material-icons yellow-text text-darken-4">save</i></a>
									</c:if>
								</div>
								
								<div class="col s4">
									<input type="file" id="fileAllegato${allegato.idTipoAllegato}" 
										name="fileAllegato"
										${empty allegato.listaAllegatiControllo and allegato.flagObbligatorio ? 'required ' : ''} />
									<input type="hidden" name="idTipoAllegato" value="${allegato.idTipoAllegato}" />
									<input type="hidden" name="idAllegato" value="${empty allegato.listaAllegatiControllo ? '' : allegato.listaAllegatiControllo[0].idAllegatoControllo}" />
								</div>
								<div class="col s8">
									<input type="text" id="descAllegato" name="descAllegato" value="${empty allegato.listaAllegatiControllo ? '' : allegato.listaAllegatiControllo[0].descAllegato}"  maxlength="200" />
								</div>
								</c:otherwise>
								</c:choose>
							</div>
							<c:if test="${allegato.flagMultiplo}">
							<div class="divider" style="margin-bottom:20px;"></div>
							</c:if>
							</c:forEach>
						</div>
					</div>	
				</div>
				</c:if>
				
			</div>
		</div>	
		
       	<div class="row">
			<a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
			
       	  	<div class="right" style="text-align: right">   
	       	  	<button class="btn confirm waves-effect waves-light" type="submit" name="datiControllo">salva</button>
			</div>
	   	</div>
	   
	</form:form>
		
	
	   		
	<br />
	
	<content tag="script"> <script type="text/javascript" src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script> 
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
			//if (modaleInoltra) {				
				modaleInoltra = $('#confermaInoltra').modal('open');
				return false;
			//}
			
			//return true;
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
		
		</script> 
	</content>

</body>
</html>