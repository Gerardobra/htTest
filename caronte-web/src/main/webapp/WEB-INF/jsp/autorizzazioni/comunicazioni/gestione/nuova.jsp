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
	<spring:url value="/autorizzazioni/comunicazioni/gestione/nuova" var="formAction" />
	<spring:url value="/autorizzazioni/comunicazioni" var="autorizzAction" />
	<form:form action="${formAction}" method="post" modelAttribute="nuovaDomandaForm" accept-charset="utf-8">
		<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>NUOVA DOMANDA : ${nuovaDomandaForm.descTipoDomanda}</b>
				<jsp:include page="../datiAnagraficiAzienda.jsp" >
					<jsp:param name="cuaa" value="${nuovaDomandaForm.codFiscaleAz }" />
					<jsp:param name="ruop" value="${nuovaDomandaForm.codiceRuop }" />
					<jsp:param name="ragSociale" value="${nuovaDomandaForm.denomAzienda }" />
				</jsp:include>
				</h3>
			</div>
			<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
  			<div class="card-content" style="padding-top:5px;">
  				<div class="row">
   	 				<div class="col s12">
      					<ul class="tabs">
      						<li class="tab" >
      							<a href="<spring:url value="/autorizzazioni/comunicazioni/anagrafica/nuova" />" target="_self">Anagrafica</a>
      						</li>
        					<li class="tab" >
        						<a href="<spring:url value="/autorizzazioni/comunicazioni/azienda/nuova" />" target="_self">Dati operatore</a>
        					</li>
        					<spring:eval var="idTipoDomandaRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_RUOP" />
        					<spring:eval var="idTipoDomandaVariazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_VARIAZIONE_RUOP" />
        					<spring:eval var="idTipoDomandaCancellazioneRUOP" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_CANCELLAZIONE_DOMANDA_RUOP" />
        					<spring:eval var="idTipoDomandaPassaporto" expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_TIPO_COMUNICAZIONE_DOMANDA_PASSAPORTO" />
        					<c:if test="${idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
        					  <c:if test="${ idTipoDomandaVariazioneRUOP == nuovaDomandaForm.idTipoComunicazione || idTipoDomandaRUOP == nuovaDomandaForm.idTipoComunicazione}">
								  <c:forEach var="ceAz" items="${nuovaDomandaForm.listaCentriAz}">
			        				  <li class="tab" >
			        					<a href="<spring:url value="/autorizzazioni/comunicazioni/centroaziendale/nuova/${ceAz.idCentroAziendale}" />" target="_self">${ceAz.codCentroAziendale}</a>
			        				  </li>
		        				  </c:forEach>
		        				</c:if>
	        				    <c:if test="${nuovaDomandaForm.tabImport}">
		        					<li class="tab" >
		        						<a href="<spring:url value="/autorizzazioni/comunicazioni/impAuto/nuova" />" target="_self">Import</a>
		        					</li>
	        					</c:if>
	        					<c:if test="${nuovaDomandaForm.tabExport}">
		        					<li class="tab" >
		        						<a href="<spring:url value="/autorizzazioni/comunicazioni/expAuto/nuova" />" target="_self">Export</a>
		        					</li>	        					
	        					</c:if>	  
								<c:if test="${nuovaDomandaForm.tabPassaporto}">      					
									  <li class="tab" >
				        			    <a href="<spring:url value="/autorizzazioni/comunicazioni/passaporto/nuova" />" target="_self">Passaporto</a>
				        			  </li>		        				
			        			</c:if>
		        			</c:if>
			        	   <li class="tab" >
	        					<a href="<spring:url value="/autorizzazioni/comunicazioni/allegati/nuova" />" target="_self">Allegati</a>
	        				</li>
			        		<c:if test="${idTipoDomandaCancellazioneRUOP != nuovaDomandaForm.idTipoComunicazione}">
								<li class="tab" >
		        						<a class="active"><b>Gestione</b></a>
		        				</li>	
	        				</c:if>       						       				        					
      					</ul>
    				</div>
  				</div>
     		 <jsp:include page="../includes/boxheader.jsp"></jsp:include>
		     <c:choose>
    			<c:when test="${idTipoDomandaPassaporto != nuovaDomandaForm.idTipoComunicazione}">
        			<div class="card-panel">
		  				<div class="row" >
			  				<div class="input-field col s6 m4 l3">
								<form:input type="text" id="codiceRuop"
									path="codiceRuop" cssClass="validate campiUt"
									required="required" maxlength="20" />
								<form:errors path="codiceRuop" cssClass="error" />
								<label for="codiceRuop">Codice ruop *</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<form:input type='text' id="dataRegistrazioneRuop"
									path="dataRegistrazioneRuop" cssClass="datepicker campiUt"
									required="required"/>
								<form:errors path="dataRegistrazioneRuop" cssClass="error" />
								<label for="dataRegistrazioneRuop">Data registrazione ruop *</label>
							</div>
							<div class="input-field col s6 m4 l6">
									<form:select id="idIspettore" path="idIspettore">
										<form:option value="" label="Selezionare" />
										<form:options items="${listaIspettori}" itemValue="idIspettore"
											itemLabel="descIspettore" />
									</form:select>
									<form:errors path="idIspettore" cssClass="error" />
									<label for="idIspettore">Ispettore o Agente</label>
							</div>
						</div>	
						
		  				<div class="row" >
							<div class="input-field col s12 m8 l6">
								<form:input type="text" id="numeroProtocollo"
									path="numeroProtocollo"	maxlength="20" />
								<form:errors path="numeroProtocollo" cssClass="error" />
								<label for="numeroProtocollo">Numero protocollo</label>
							</div>
							<div class="input-field col s12 m8 l6">
								<form:input type='text' id="dataProtocollo"
									path="dataProtocollo" cssClass="datepicker campiUt"
									/>
								<form:errors path="dataProtocollo" cssClass="error" />
								<label for="dataProtocollo">Data protocollo</label>
							</div>
						</div>	
						
		  				<div class="row" >
							<div class="input-field col s12 m8 l6">
								<form:input type="text" id="codiceFitok"
									path="codiceFitok" maxlength="20" />
								<form:errors path="codiceFitok" cssClass="error" />
								<label for="codiceFitok">Codice fitok</label>
							</div>	
							<div class="input-field col s12 m8 l6">
								<form:input type="text" id="tariffa"
									path="tariffa"  class="validate" pattern="\d+([\.,]\d{1,4})?" maxlength="9" />
								<form:errors path="tariffa" cssClass="error" />
								<label for="tariffa">Tariffa</label>
							</div>		
						</div>	
						
		  				<div class="row" >
							<div class="input-field col s24 m16 l12">
								<form:textarea id="note"
									path="note" data-length="1000" maxlength="1000" cssClass="materialize-textarea validate" rows="2"/>
								<form:errors path="note" cssClass="error" />
								<label for="note">Note</label>
							</div>
					    </div>
					  	<div class="row" >
							<div class="right" style="text-align: right">
								<button class="btn confirm waves-effect waves-light " type="submit" name="datiGestione">SALVA</button>
			  			  	</div>		
					  	</div>	 		
					  </div>
    			</c:when>    			  
    			<c:otherwise>
         			<div class="card-panel">
		  				<div class="row" >
			  				<div class="input-field col s6 m4 l3">
								<form:input type="text" id="codiceRuop"
									path="codiceRuop" readonly="true"/>								
								<label for="codiceRuop">Codice ruop</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<form:input type='text' id="dataRegistrazioneRuop"
									path="dataRegistrazioneRuop" readonly="true"/>							
								<label for="dataRegistrazioneRuop">Data registrazione ruop</label>
							</div>		
							<div class="input-field col s6 m4 l6">
									<form:select id="idIspettore" path="idIspettore">
										<form:option value="" label="Selezionare" />
										<form:options items="${listaIspettori}" itemValue="idIspettore"
											itemLabel="descIspettore" />
									</form:select>
									<form:errors path="idIspettore" cssClass="error" />
									<label for="idIspettore">Ispettore o Agente</label>
							</div>					
						</div>	
						
		  				<div class="row" >
							<div class="input-field col s12 m8 l4">
								<form:input type="text" id="numeroProtocollo"
									path="numeroProtocollo"	maxlength="20" />
								<form:errors path="numeroProtocollo" cssClass="error" />
								<label for="numeroProtocollo">Numero protocollo</label>
							</div>
							<div class="input-field col s12 m8 l4">
								<form:input type='text' id="dataProtocollo"
									path="dataProtocollo" cssClass="datepicker campiUt"
									/>
								<form:errors path="dataProtocollo" cssClass="error" />
								<label for="dataProtocollo">Data protocollo</label>
							</div>
							<div class="input-field col s12 m8 l4">
								<form:input type="text" id="tariffa"
									path="tariffa"  class="validate" pattern="\d+([\.,]\d{1,4})?" maxlength="9" />
								<form:errors path="tariffa" cssClass="error" />
								<label for="tariffa">Tariffa</label>
							</div>	
						</div>								  				
						
		  				<div class="row" >
							<div class="input-field col s24 m16 l12">
								<form:textarea id="note"
									path="note" data-length="1000" maxlength="1000" cssClass="materialize-textarea validate" rows="2"/>
								<form:errors path="note" cssClass="error" />
								<label for="note">Note</label>
							</div>
					    </div>
					  	<div class="row" >
							<div class="right" style="text-align: right">
								<button class="btn confirm waves-effect waves-light " type="submit" name="datiGestione">SALVA</button>
			  			  	</div>		
					  	</div>	 		
					  </div>	        
    			</c:otherwise>
			</c:choose>
             
			 
					
			</div>
		</div>	
		
       	<div class="row"> 
			<a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
       	  	<div class="right" style="text-align: right">   
	       		<c:if test="${nuovaDomandaForm.abilitaInoltra}">						
				<a
						href='#'
						title="Inoltra"
						onclick="return checkAperturaModaleInoltra(event)"
						class="btn amber darken-1 waves-effect waves-light"
						style="display: inline-block"
						>INOLTRA</a>
									
				</c:if>
				<c:if test="${nuovaDomandaForm.abilitaConvalida}">
							<a
								href='<spring:url value="/autorizzazioni/comunicazioni/convalida/${nuovaDomandaForm.idDomanda}"/>'
								title="Autorizza"
								class="btn amber darken-1 waves-effect waves-light"
								style="display: inline-block"
								onclick="return checkConvalidaDomanda(this)">CONVALIDA<i
								class="material-icons right">arrow_forward</i></a>
				</c:if>
						
				<c:if test="${nuovaDomandaForm.abilitaRespingi}">
							<a
								href='<spring:url value="/autorizzazioni/comunicazioni/respingi/${nuovaDomandaForm.idDomanda}"/>'
								title="Respingi" class="btn red darken-4 waves-effect waves-light"
								style="display: inline-block; margin-right: 4px;" 
								onclick="return checkRespingiDomanda(this)">RESPINGI<i
								class="material-icons right">clear</i></a>
				</c:if>
						
				<c:if test="${nuovaDomandaForm.abilitaAnnulla}">
							<a
								href='<spring:url value="/autorizzazioni/comunicazioni/annulla/${nuovaDomandaForm.idDomanda}"/>'
								title="Respingi" class="btn red darken-4 waves-effect waves-light"
								style="display: inline-block; margin-right: 4px;" 
								onclick="return checkAnnullaDomanda(this)">ANNULLA<i
								class="material-icons right">clear</i></a>
				</c:if>
				
	   	  	</div>
	   	</div>
	   
	</form:form>
		   	
		<spring:url value="/autorizzazioni/comunicazioni/inoltraDomandaRuop" var="formAction2" />
	<form:form action="${formAction2}" method="post" modelAttribute="stampaDomandaForm" accept-charset="utf-8" enctype="multipart/form-data">
	
	  <!-- Finestra modale per fare l'INOLTRA della Domanda -->
	  <div class="modal modal-width-80 top-padding-20" id="confermaInoltra" >
		<div class="modal-content" style="padding-top:0.5em; padding-bottom:0;">
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
					<tr><td colspan="2"><hr/></td></tr>
					<tr>
						<td>${testoInoltra2}</td>
						<td>
							<!--input type="file" id="fileid" name="fileStampa" required="required" 
								accept=".pdf,.doc,.docx,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"-->
							<b>Carica la domanda firmata</b><br/>
							<input type="file" id="fileid" name="fileStampa">
						</td>
						
					</tr>
					<tr><td colspan="2"><hr/></td></tr>
					<tr><td colspan="2">${testoInoltra3}</td></tr>
				</table>
			
			<br/><br/>
			<div class="modal-footer">
				<div class="left" style="text-align: left">
					<a
							href='#'
							title="Chiudi"
							class="btn waves-effect waves-light"
							style="display: inline-block"
							onclick="closeModal()">CHIUDI</a>
				</div>
				<div class="right" style="text-align: right">			
					<button class="btn amber darken-1 waves-effect waves-light" type="submit" name="inoltra" value="INOLTRA" onclick="closeModal();">INOLTRA</button>
				</div>		
			</div>
		</div>
	</div>
	<!-- fine modale per INOLTRA -->	  
    </form:form> 	
	<br />
	
	<content tag="script"> 
		<script type="text/javascript" src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script> 
		<script>

		//var modaleInoltra = null;
		var delayTime = null;
		
		function checkAperturaModaleInoltra(e) {	
			console.log('checkAperturaModaleInoltra');
			
			 $.ajax({
				    // Ricerca del centro aziendale su car_t_centro_aziendale	
				    //avevo usato encodeURIComponent(indirizzoCentroAz), ma non funziona correttamente su macchiene unix(problema il charset)
					url : '<spring:url value="/ajax/getElencoTabMancanti_" />'+ ${nuovaDomandaForm.idDomanda},
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
		
		$(document).ready(function() {
			var numeroProtocollo = $("#numeroProtocollo").val();
			console.log('numeroProtocollo ='+numeroProtocollo);
			if ( numeroProtocollo != "") {
				$("#numeroProtocollo").attr("disabled", "disabled").attr("readonly",
				"readonly");
				$("#dataProtocollo").attr("disabled", "disabled").attr("readonly",
				"readonly");
			} else {
				$("#numeroProtocollo").removeAttr("disabled", "disabled").removeAttr("readonly",
				"readonly");
				$("#dataProtocollo").removeAttr("disabled", "disabled").removeAttr("readonly",
				"readonly");
			}			
	});
		
  	  </script> 
	</content>

</body>
</html>