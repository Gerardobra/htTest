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
ol-start {
	counter-reset: item 3
}

ol {
	counter-reset: item
}

li-lista {
	display: block
}

li-lista:before {
	content: counters(item, ".") " ";
	counter-increment: item
}
</style>
</head>
<body>
	<spring:url value="/controlli/controlloidentita/modifica" var="formAction" />	
	<form:form action="${formAction}" method="post"
		modelAttribute="nuovoControlloForm" accept-charset="utf-8">
		<div class="card">
			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding">
					<b>MODIFICA CONTROLLO DI IDENTITA'</b>
				</h3>
			</div>
			<div class="card-content" style="padding-top: 5px;">
				<div class="row">
					<div class="col s12">
						<ul class="tabs">
							<li class="tab"><a
								href="<spring:url value="/controlli/datiBase/modifica" />"
								target="_self">Dati generali</a></li>
							<c:if test="${nuovoControlloForm.tabDocumentale}">	
								<li class="tab"><a
									href="<spring:url value="/controlli/controllodocumentale/modifica" />"
									target="_self">Documentale</a>
								</li>
							</c:if>	
							<li class="tab"><a class="active"><b>Identità</b></a></li>
							<c:if test="${nuovoControlloForm.tabFisico}">
								<li class="tab"><a
									href="<spring:url value="/controlli/controllofisico/modifica" />"
									target="_self">Fisico</a>
								</li>
							</c:if>	
							  <c:if test="${nuovoControlloForm.tabFisico && nuovoControlloForm.flControllo5x23 != null && nuovoControlloForm.flControllo5x23 =='S' }">
		        					<li class="tab" >
		        						<a href="<spring:url value="/controlli/campioni/modifica" />" target="_self">Campioni</a>
		        					</li>
								</c:if>
							<li class="tab"><a
								href="<spring:url value="/controlli/allegati/modifica" />"
								target="_self">Allegati</a></li>
							<c:if test="${false}">
		        				<li class="tab" >
		        					<a href="<spring:url value="/controlli/monitoraggio/modifica" />" target="_self">Monitoraggio cofinanziato</a>
		        				</li>
	        				</c:if>	 
	        				<c:if test="${nuovoControlloForm.misuraUfficiale != null && nuovoControlloForm.misuraUfficiale =='S' }">
	        					<li class="tab" >
        					  		<a href="<spring:url value="/controlli/misura/modifica" />" target="_self">Misura ufficiale</a>
        				    	</li>
        				    </c:if>						
							<li class="tab"><a
								href="<spring:url value="/controlli/esito/modifica" />"
								target="_self">Esito</a></li>

						</ul>
					</div>
				</div>

				<spring:eval var="versioneBegin"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_CONTROLLO_BEGIN" />	
				<spring:eval var="versione042021"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_CONTROLLO_04_2021" />	
				
				<div class="card-panel">
					<div class="row">
						<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Controllo di identità</em>
						</p>
					</div>
					<div class="row">
						<ol-start> <!-- 4 --> <li-lista>
						<b>Controllo di identità</b>
						<ol style="list-style-type: decimal;">
							<!-- 4.1 -->
							<li-lista>
							<b>Le piante presenti nel corso dei controlli presso il Centro Aziendale corrispondono alla tipologia produttiva dichiarata annualmente dall’OP </b>
							<div class="row">
								<label class="radio-inline"> <form:radiobutton
										id="flControllo4x1" cssClass="with-gap" path="flControllo4x1"
										value="S" /> <span>Si</span>
								</label> 
								<label class="radio-inline"> <form:radiobutton
										id="flControllo4x1" cssClass="with-gap" path="flControllo4x1"
										value="N" /> <span>No</span>
								</label> 
								<label class="radio-inline"> <form:radiobutton
										id="flControllo4x1" cssClass="with-gap" path="flControllo4x1"
										value="NA" /> <span>NA</span>
								</label> 
							</div>
							</li-lista>
							<!-- 4.2 -->
							<li-lista>
							<b>I mappali di coltivazione coincidono con i mappali
								dichiarati a vivaio nel fascicolo aziendale</b>
							<div class="row">
								<label class="radio-inline"> <form:radiobutton
										id="flControllo4x2" cssClass="with-gap" path="flControllo4x2"
										value="S" /> <span>Si</span>
								</label> <label class="radio-inline"> <form:radiobutton
										id="flControllo4x2" cssClass="with-gap" path="flControllo4x2"
										value="N" /> <span>No</span>
								</label>
								</label> <label class="radio-inline"> <form:radiobutton
										id="flControllo4x2" cssClass="with-gap" path="flControllo4x2"
										value="NA" /> <span>NA</span>
								</label>
							</div>
							</li-lista>
						</ol>
					</div>

					<!-- Siti di produzione -->
					<div class="row s12">
						<ul class="collapsible" id="collapsible" data-collapsible="accordion">
							<li>
								<div class="collapsible-header" id="datiIspezione">
									<i class="material-icons">arrow_drop_down</i>Aggiungi Siti di
									produzione
								</div>
								<div class="collapsible-body">
									<div class="input-field col s12 m8 l6">
										<form:input type="text" name="descSitoProduzione"
											id="descSitoProduzione" path="descSitoProduzione"
											maxlength="100" />
										<form:errors path="descSitoProduzione" cssClass="error" />
										<label for="descSitoProduzione">Sito di produzione</label>
									</div>
									<div class="input-field col s12 m8 l6">
										<form:input type="text" name="ubicazione" id="ubicazione"
											path="ubicazione" maxlength="50" />
										<form:errors path="ubicazione" cssClass="error" />
										<label for="ubicazione">Indirizzo</label>
									</div>
									<div class="input-field col s12 m8 l4">
										<form:select id="provinciaSitoProd" name="provinciaSitoProd"
											path="provinciaSitoProd" required="required" class="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaProvinceSitoProd}"
												itemValue="idProvincia" itemLabel="denomProvincia" />
										</form:select>
										<form:errors path="provinciaSitoProd" cssClass="error" />
										<label for="provinciaSitoProd">Provincia *</label>
									</div>
									<div class="input-field col s12 m8 l4">
										<form:select id="comuneSitoProd" name="comuneSitoProd"
											path="comuneSitoProd" required="required" class="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaComuniSitoProd}"
												itemValue="idComune" itemLabel="denomComune" />
										</form:select>
										<form:errors path="comuneSitoProd" cssClass="error" />
										<label for="comuneSitoProd">Comune *</label>
									</div>
									<div class="input-field col s6 m8 l4">
										<form:input name="foglio" id="foglio" path="foglio"
											required="required" class="validate" pattern="\d*"
											maxlength="4"
											oninvalid="this.setCustomValidity('Foglio non valido')"
											oninput="this.setCustomValidity('')" />
										<form:errors path="foglio" cssClass="error" />
										<label for="foglio">Foglio *</label>
									</div>
									<div class="input-field col s6 m8 l4">
										<form:input name="mappale" id="mappale" path="mappale"
											required="required" class="validate" pattern="\d*"
											maxlength="8"
											oninvalid="this.setCustomValidity('Mappale non valida')"
											oninput="this.setCustomValidity('')" />
										<form:errors path="mappale" cssClass="error" />
										<label for="mappale">Mappale *</label>
									</div>
									<div class="input-field col s6 m4 l4">
										<form:input name="superficie" id="superficie"
											path="superficie" required="required" class="validate"
											pattern="\d+([\.,]\d{1,2})?" maxlength="10"
											oninvalid="this.setCustomValidity('Superficie non valida')"
											oninput="this.setCustomValidity('')" />
										<form:errors path="superficie" cssClass="error" />
										<label for="superficie">Superficie (SAU)(mq.) *</label>
									</div>
									<div class="row">
										<button class="btn green waves-effect waves-light" style=""
											type="submit" name="aggiungiSito" id="aggiungiSito"
											formnovalidate="formnovalidate">AGGIUNGI SITO</button>
									</div>

								</div> <!-- FINE collapsible-body -->
							</li>
						</ul>
					</div>

					<div class="row">
						<table id="tabellaSitiProduzione"
							class="data-table striped display" data-paging="false"
							data-info="false">
							<thead>
								<tr>
									<th></th>
									<th>Sito di produzione</th>
									<th>Indirizzo</th>
									<th>Comune</th>
									<th>Foglio</th>
									<th>Mappale</th>
									<th>Superficie (SAU)(mq.)</th>
								</tr>
							</thead>
							<tbody id="bodyTabellaSitiProduzione">
								<c:forEach var="sitoProd" items="${sitiProduzioneDb}">
									<tr>
										<td>
										<a href='<spring:url value="/controlli/modifica/controlloidentita/eliminaSitoProd/${sitoProd.idSitoProduzione}" />'
											title="Elimina"
											class="btn btn-floating btn-floating-medium deep-orange accent-2"
											onclick="return eliminaSitoProd(this)"> <i
												class="material-icons">delete</i>
										</a></td>
										<td>${sitoProd.denominazione}</td>
										<td>${sitoProd.ubicazione}</td>
										<td>${sitoProd.denomComune}(${sitoProd.siglaProvincia})</td>
										<td>${sitoProd.foglio}</td>
										<td>${sitoProd.mappale}</td>
										<td>${sitoProd.superficie}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- FINE Siti di produzione -->
				</div>
			</div>
			<div class="card-action">
				<div class="row">
					<a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
					<div class="right" style="text-align: right">
						<button id="salvaControlloIdentita" name="salvaControlloIdentita" type="submit" class="btn confirm waves-effect waves-light" style="display: inline-block" formnovalidate="formnovalidate">SALVA</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>




	<content tag="script"> <script>
	
		$(document).ready(function() {	
	
			if('${hasErrorsSitoProdIdentita}'=='true'){
				M.Collapsible.getInstance($("#collapsible")).open();
				
				$("label[for='provinciaSitoProd']").addClass("active");
				$("label[for='comuneSitoProd']").addClass("active");
			}
			
		});
	
		// Alla selezione della Provincia nella modale Sito Produzione
		$('#provinciaSitoProd').change(
				function() {
					// ------ Popolamento combo comune
					console.log('Popolamento combo comune sito produzione');
					var params = {
						"idProvincia" : $(this).val()
					}

					getSelectAsincrona('comuneSitoProd',
							'<spring:url value="/ajax/getComuniProvincia" />',
							params, getOptionComune, 1);
				});

		function getOptionComune(comune) {
			return getOption(comune.idComune, comune.denomComune);
		}

		// Evento ELIMINA sulla tabella dei Siti produzione        
		function eliminaSitoProd(link) {
			console.log('eliminaSitoProd');

			var sitoProduz;
			var ubicazione;

			$riga = $(link).closest('tr');
			sitoProduz = $riga.find('td:nth-of-type(2)').text();
			ubicazione = $riga.find('td:nth-of-type(3)').text();

			var messaggio;
			if (sitoProduz != '' && ubicazione != '') {
				messaggio = 'Si desidera eliminare il sito produzione : <b>'
						+ sitoProduz + '</b>, con indirizzo : <b>' + ubicazione
						+ '</b>?'
			} else {
				if (sitoProduz == '' && ubicazione == '') {
					messaggio = 'Si desidera eliminare il sito produzione?';
				} else {
					if (sitoProduz != '') {
						messaggio = 'Si desidera eliminare il sito produzione : <b>'
								+ sitoProduz + '</b>?';
					} else if (ubicazione != '') {
						messaggio = 'Si desidera eliminare il sito produzione con indirizzo : <b>'
								+ ubicazione + '</b>?';
					}
				}
			}
			swal({
				title : 'Attenzione!',
				html : messaggio,
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
	</script> </content>

</body>
</html>