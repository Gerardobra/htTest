<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
			<spring:url value="/autorizzazioni/comunicazioni/elenco" var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaDomandaForm" accept-charset="utf-8">
				<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
				<div class="card-content col s12">
					<span class="card-title">Cerca domande</span>
					<div class="row">
					
					  <c:if test="${isSuperUser}">
					    <div class="input-field col s6 m4 l3">
			              <form:input type="text" id="nomeOperatore" path="nomeOperatore" />
			              <form:errors path="nomeOperatore" cssClass="error" />
			              <label for="nomeOperatore">Nome operatore professionale</label>
			            </div>
			            <div class="input-field col s6 m4 l3">
			              <form:input type="text" id="cognomeOperatore" path="cognomeOperatore" />
			              <form:errors path="cognomeOperatore" cssClass="error" />
			              <label for="cognomeOperatore">Cognome operatore professionale</label>
			            </div>
						 <div class="input-field col s6 m4 l3">
			              <form:input type="text" id="azienda" path="azienda" />
			              <form:errors path="azienda" cssClass="error" />
			              <label for="azienda">Azienda</label>
			            </div>
					  </c:if>
					  
					  <div class="input-field col s6 m4 l6">
			              <form:select id="tipoComunicazione" path="idTipoComunicazione"
			                cssClass="validate">
			                <form:option value="" label="Selezionare" />
			                <form:options items="${tipiComunicazione}"
			                  itemValue="idTipoComunicazione" itemLabel="descBreve" />
			              </form:select>
              			<form:errors path="idTipoComunicazione" cssClass="error" />
              			<label for="tipoComunicazione">Tipo domanda</label>
            		  </div>
					  <div class="input-field col s6 m4 l3">
							<form:select id="statoComunicazione" path="idStatoComunicazione"
								cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${statiComunicazione}"
									itemValue="idStatoComunicazione" itemLabel="descStato" />
							</form:select>
							<form:errors path="idStatoComunicazione" cssClass="error" />
							<label for="statoComunicazione">Stato domanda</label>
					  </div>
					  <div class="input-field col s6 m4 l3">
			              <form:input type="text" id="codiceDomanda" path="codiceDomanda" />
			              <form:errors path="codiceDomanda" cssClass="error" />
			              <label for="codiceDomanda">Codice domanda</label>
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
			<a href='<spring:url value="/autorizzazioni/comunicazioni/nuova"/>'
				class="btn green waves-effect waves-light">NUOVA DOMANDA</a>
		</div>
	</sec:authorize>
	
	<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />

	<c:choose>
		<c:when test="${not empty elencoDomande}">

			<div class="row">
				<div class="col-s12">

					<table id="tabellaDomande" class="data-table striped display">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th>Stato</th>
								<th>Tipo</th>
								<th>Operatore</th>
								<th>Azienda</th>
								<th>Codice domanda</th>								
								<th>Data creazione</th>
								<th>Numero protocollo</th>
								<th>Data protocollo</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody id="bodyTabellaDomande">
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
				          <spring:eval var="descTipoDomandaRUOP"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).DESC_TIPO_COMUNICAZIONE_DOMANDA_RUOP" />
						  <spring:eval var="descTipoDomandaVariazione"
				                expression="T(it.aizoon.ersaf.util.CaronteConstants).DESC_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP" />
										
				                
						     <c:forEach var="domanda" items="${elencoDomande}">
								<tr>
									<td>
									<c:choose>
					                  <c:when
					                    test="${statoInoltrata == domanda.idStatoComunicazione.longValue()}">
										<a
											class="btn-floating btn-flat btn-floating-medium yellow accent-2"
											style="cursor: default"></a>
									</c:when>
									<c:when test="${statoConvalidata == domanda.idStatoComunicazione}">
										<a
											class="btn-floating btn-flat btn-floating-medium light-green accent-4"
											style="cursor: default"></a>
									</c:when>
									<c:when
										test="${statoRespinta == domanda.idStatoComunicazione}">
										<a
											class="btn-floating btn-flat btn-floating-medium red darken-4"
											style="cursor: default"> <i class="material-icons">arrow_back</i>
										</a>
									</c:when>
									<c:when
										test="${statoAnnullata == domanda.idStatoComunicazione}">
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
									<td>${domanda.idStatoComunicazione} - ${domanda.descStatoDomanda}</td>
									<td>${domanda.descTipoDomanda}</td>
									<td>${domanda.utenteCognome} ${domanda.utenteNome}</td>
									<td>${domanda.denomSpedizioniere}</td>	
									<td>${domanda.codDomanda}</td>									
									<td><fmt:formatDate value="${domanda.dataInserimento}"
											pattern="dd/MM/yyyy" /></td>
									<td>${domanda.numeroProtocollo}</td>
									<td><fmt:formatDate value="${domanda.dataProtocollo}"
											pattern="dd/MM/yyyy" /></td>		
									
									<spring:url value="/autorizzazioni/comunicazioni" var="domandeAction" />
									<td nowrap style="white-spaces: nowrap">
                    				    <a href="${domandeAction}/dettaglio/${domanda.idDomanda}"
										  title="Visualizza"
										  class="btn btn-floating btn-floating-medium light-blue accent-3">
											<i class="material-icons">visibility</i>
					                    </a>
					                    <c:if test="${statoAnnullata != domanda.idStatoComunicazione.longValue()}">
					                      <a href="${domandeAction}/modifica/${domanda.idDomanda}"
					                        title="Modifica"
					                        class="btn btn-floating btn-floating-medium light-green accent-3">
					                        <i class="material-icons">mode_edit</i>
										  </a>
										</c:if> 
										<!-- Modale per visualizzare i flussi della comunicazione -->
										<c:if test="${domanda.numDomandeFlusso > 1}">
											<a 
											    href='#'
												title="Flusso"
												onclick="return openIterModal(${domanda.idDomanda})"
												class="btn btn-floating btn-floating-medium light-orange accent-3">
												<i class="material-icons">list</i>
											</a>
										</c:if>
																				
										<!-- Creazione nuova domanda nel flusso -->
										
										<c:if test="${domanda.nuovaDomandaFlusso}">
											<a 
											    href="${domandeAction}/crea/${domanda.idDomanda}"
												title="${domanda.descTipoDomSuccessiva}"								
												class="btn btn-floating btn-floating-medium orange accent-4">
												<i class="material-icons">add_to_photos</i>
											</a>
										</c:if>
										
										<c:if test="${statoConvalidata == domanda.idStatoComunicazione and descTipoDomandaVariazione == domanda.descTipoDomanda and domanda.maxIdDomandaFlusso == domanda.idDomanda}">
											<a 
											    href="${domandeAction}/variazione/${domanda.idDomanda}"
												title="Variazione Domanda di Registrazione RUOP"								
												class="btn btn-floating btn-floating-medium orange accent-4">
												<i class="material-icons">add_to_photos</i>
											</a>
										</c:if>	
										<c:if test="${descTipoDomandaRUOP == domanda.descTipoDomanda and statoConvalidata == domanda.idStatoComunicazione}">
											<a
											href='<spring:url value="/autorizzazioni/comunicazioni/cancella/${domanda.idDomanda}"/>'	
											title="Richiesta di cancellazione RUOP"
											class="btn btn-floating btn-floating-medium red lighten-1">
												<i class="large material-icons">cancel</i>
											</a>
										</c:if>
										<!-------->
										<c:if test="${domanda.idTipoStampa != null and statoInBozza != domanda.idStatoComunicazione}">
											<a
											href='<spring:url value="/autorizzazioni/report/domanda/${domanda.idDomanda}"/>'
											title="Stampa Comunicazione"
											class="btn btn-floating btn-floating-medium teal lighten-1">
												<i class="material-icons">print</i>
											</a>
										</c:if>
										
										<c:if test="${flSuperUser and statoInBozza == domanda.idStatoComunicazione}">
										<a href='javascript:confermaEliminaComunicaz(${domanda.idDomanda})'
												title="Elimina"
												class="btn btn-floating btn-floating-medium red darken-3">
													<i class="material-icons">delete</i>
											</a>
										</c:if>
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
						<p>&nbsp;&nbsp;Non sono state trovate domande</p>
					</div>
					<button type="button" class="close orange-text"
						data-dismiss="alert" aria-label="Chiudi">
						<span aria-hidden="true">×</span>
					</button>
				</div>
			</div>

		</c:otherwise>
	</c:choose>
	
	
	<!-- Finestra modale per visualizzare il FLUSSO -->
	  <div class="modal modal-70 top-padding-20" id="visualizzaFlusso" >
		<div class="modal-content" style="padding-top:0.5em; padding-bottom:0;">
			<h5 class="card title bg-primary-color white-text bold" style="padding: 2px 0px 5px 2px" >Flusso domande</h5>
		
			<table id="tabellaFlusso" class="data-table striped display" 
		       		 data-order="false" data-paging="false" data-info="false">
					<thead>
						<tr>
							
							<th data-orderable="false">Progressivo</th>
							<th data-orderable="false">Stato</th>
							<th data-orderable="false">Tipo</th>
							<th data-orderable="false">Data modifica</th>
							<th data-orderable="false">Num Protocollo</th>
							<th data-orderable="false"></th>
							
						</tr>
					</thead>
					<tbody id="bodyTabellaFlusso"></tbody>
					<tr id="rigaTemplateFlusso" style="display:none;">
						<td class="center"></td>
						<td></td>
						<td></td>
						<td class="center"></td>
						<td class="center"></td>
						<td nowrap style="white-spaces: nowrap">
							<a href='<spring:url value="/autorizzazioni/comunicazioni/dettaglio/PLACEHOLDER_ID_DOMANDA"/>'
								title="Visualizza"
								class="btn btn-floating btn-floating-medium light-blue accent-3">
								<i class="material-icons">visibility</i>
							</a>
						</td>
					</tr>				
				</table>						
			
			
			<br/><br/>
			
							
				
		<div class="card-action" style="border-top: 1px solid rgba(160, 160, 160, 0.2);">
			<div class="row">
				<div class="modal-footer right-align">
						
							<a
									href='#'
									title="Chiudi"
									class="btn waves-effect waves-light"
									style="display: inline-block"
									onclick="indietro()">CHIUDI</a>
							
				</div>
			</div>
		</div>
	  </div>
	</div>
	<!-- fine modale per FLUSSO -->

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
		
		
		// Visualizzazione Iter Flusso Domanda
		function openIterModal(idDomanda) {	
			console.log('openIterModal');
			popolaTabella('<spring:url value="/ajax/getFlussoDomanda" />', idDomanda);
		    
			$('#visualizzaFlusso').modal('open');
		 };
		 
		 function indietro(){
			 console.log('indietro');
			 $('#visualizzaFlusso').modal('close');
		 }
		 
		 function popolaTabella(url, idDomanda) {
			console.log('BEGIN popolaTabella');
			var param = {	"idDomanda" : idDomanda	}
			 
			$.getJSON(url, param, function(responseJSON) {
					$tbody = $('#bodyTabellaFlusso');
					$rigaTemplate = $('#rigaTemplateFlusso');
					$tbody.closest('table').DataTable().rows().remove();
					$.each(responseJSON, function(index, domanda) {
						console.log("id domanda: " + domanda.idDomanda );
						aggiungiRigaFlusso(domanda, $rigaTemplate, $tbody, idDomanda);
					});
			}).fail(function(jqxhr, textStatus, error) {
					if (window.console) {
						console.log( "Errore in reperimento dati select: " + textStatus + ", " + error);
					}         
			   });				
			}
		 
			function aggiungiRigaFlusso(domanda, $rigaTemplate, $appendTo, idDomandaSelezionata) {
				console.log('BEGIN aggiungiRigaFlusso');
				console.log('idDomandaSelezionata ='+idDomandaSelezionata);
				$riga = $rigaTemplate.clone();
				$riga.prop('id', '');
				
				$riga.find('td:nth-of-type(1)').text(domanda.progressivo);
				$riga.find('td:nth-of-type(2)').text(domanda.descStatoDomanda.toUpperCase() );
				$riga.find('td:nth-of-type(3)').text(domanda.descTipoDomanda.toUpperCase()	);
				$riga.find('td:nth-of-type(4)').text(domanda.dataAggiornamento ? formatDate(new Date(domanda.dataAggiornamento)) : '');	
				
				if (domanda.numeroProtocollo) {
					console.log('il numero di protocollo e presente ='+domanda.numeroProtocollo);
					var numeroProtocollo = domanda.numeroProtocollo.toUpperCase();
					if (domanda.dataProtocollo) {
						console.log('la data protocollo e presente ='+domanda.dataProtocollo);
						numeroProtocollo += ' - ' + (domanda.dataProtocollo ? formatDate(new Date(domanda.dataProtocollo)) : '');
					}
					$riga.find('td:nth-of-type(5)').text(numeroProtocollo);
				}
				$cellaLink = $riga.find('td:nth-of-type(6)');
				$cellaLink.html($cellaLink.html().replace(/PLACEHOLDER_ID_DOMANDA/g, domanda.idDomanda));
				
				if (idDomandaSelezionata == domanda.idDomanda) {
					$riga.addClass("light-yellow-background");
				}
				
				$riga.appendTo($appendTo);
				$riga.show();
				
				$table = $riga.closest('table');
				$table.filter(':hidden').show();
				
				var dataTable = $table.DataTable();
				dataTable.row.add($riga);
				dataTable.draw();
			}

			function confermaEliminaComunicaz(idDomanda) {
				console.log('idDomanda selezionata = ' + idDomanda);
				
					swal({
								title : 'Attenzione',
								html : 'Si desidera eliminare definitivamente la domanda selezionata? L&#39; operazione è irreversibile.',
								type : 'warning',
								showCancelButton : true,
								confirmButtonText : 'CONTINUA',
								cancelButtonText : 'ANNULLA',
							})
							.then(
									function(result) {
										if (result.value) {
											window.location.href = '<spring:url value="/autorizzazioni/comunicazioni/eliminaDomanda_"/>'
													+ idDomanda;
										}
									});
				

			}
		
	</script> </content>

</body>

</html>