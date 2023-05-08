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
.modal { 
	width: 80% !important; 
	max-height: 95% !important; 
	top: 2px !important;
	overflow: visible;
} 
</style>
</head>
<body>
    
	<spring:url value="/controlli/azienda/modifica" var="formAction" />
	<form:form action="${formAction}" method="post" modelAttribute="nuovoControlloForm" accept-charset="utf-8">
		<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>AZIENDA : 
					<c:if test="${not empty nuovoControlloForm.denomSpedizioniere}">
						${nuovoControlloForm.denomSpedizioniere}  
					</c:if>
					<c:if test="${empty nuovoControlloForm.denomSpedizioniere}">
						${nuovoControlloForm.cognome} ${nuovoControlloForm.nome} 
					</c:if>
				</b></h3>
			</div>
			<sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
  			<spring:eval var="statoInBozza"	expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_COMUNICAZIONE_BOZZA" />	
  			<div class="card-content" style="padding-top:5px;">
  				<div class="row">
   	 				<div class="col s12">
      					<ul class="tabs">
      						
        					<li class="tab" >
        						<a class="active"><b>Dati operatore</b></a>
        					</li> 
        					
        					<li class="tab" >
									<a href="<spring:url value="/controlli/centroaziendale/modifica/${idCentroAziendale}" />" target="_self">Centro Aziendale</a>
			        		</li>
			        		
			        <!-- 	<c:forEach var="centroAziendale" items="${listaCentriAziendali}">
				        		<li class="tab" >
									<a href="<spring:url value="/controlli/centroaziendale/modifica/${centroAziendale.idCentroAziendale}" />" target="_self">C. Aziendale ${centroAziendale.codCentroAziendale}</a>
			        			</li>
		        			</c:forEach>	 -->
							      					
      					</ul>
    				</div>
  				</div>
        	  <div class="card-panel">	
        	     <div class="row">
		  					<p class="center-align"
									style="font-size: 20px; margin-bottom: 5px;">
									<em>Dati dell'azienda</em>
							</p>
		  		 </div>			
        		  															
				<!-- DATI ANAGRAFICI AZIENDA (CAR_T_SPEDIZIONIERE)-->
				
				<div class="row">
					<div class="input-field col s6 m4 l3">
						<input type="text" id="denomTipoSpedizioniere" 
							value="${fn:escapeXml(azienda.denomTipoSpedizioniere)}" disabled="disabled" /> 
						<label for="denomTipoSpedizioniere">Tipo azienda</label>	
					</div>
					<div class="input-field col s6 m4 l3">
						<input type="text" id="tipoSpedizioniereAltro" 
							value="${fn:escapeXml(azienda.tipoSpedizioniereAltro)}" disabled="disabled" />						
						<label for="tipoSpedizioniereAltro">Tipologia Altro</label>
					  </div>
					<div class="input-field col s6 m4 l3">
						<input type="text" id="denomSpedizioniere" 
							value="${fn:escapeXml(azienda.denomSpedizioniere)}" disabled="disabled" /> 
						<label for="denomSpedizioniere">Ragione sociale</label>	
					</div>					
					<div class="input-field col s6 m4 l3">
						<input type="text" id="cuaa" 
							value="${fn:escapeXml(azienda.cuaa)}" disabled="disabled" />						
						<label for="cuaa">CUAA / Codice fiscale</label>
					</div>					
					<div class="input-field col s6 m4 l3">
						<input type="text" id=partitaIva 
							value="${fn:escapeXml(azienda.partitaIva)}" disabled="disabled" />						
						<label for="partitaIva">Partita iva</label>
					  </div>
					<div class="input-field col s6 m4 l3">
						<input type="text" id="denomProvincia" 
							value="${fn:escapeXml(azienda.denomProvincia)}" disabled="disabled" /> 
						<label for="comSedeLegale">Provincia Sede Legale</label>	
					</div>
					<div class="input-field col s6 m4 l3">
						<input type="text" id="denomComune" 
							value="${fn:escapeXml(azienda.denomComune)}"	disabled="disabled" /> 
						<label for="denomComune">Comune Sede Legale</label>	
					</div>
					<div class="input-field col s6 m4 l3">
						<input type="text" id="capSped" 
							value="${fn:escapeXml(azienda.cap)}"	disabled="disabled" /> 
						<label for="cap">Cap Sede Legale</label>	
					</div>													
					<div class="input-field col s6 m4 l3">
						<input type="text" id="indirizzo" 
							value="${fn:escapeXml(azienda.indirizzo)}" disabled="disabled" /> 
						<label for="indirizzo">Indirizzo sede legale (Es. Via Roma, 24)</label>	
					</div>
					
					<div class="input-field col s6 m4 l3">
						<input type="text" id="fax" 
							value="${fn:escapeXml(azienda.fax)}" disabled="disabled" /> 
						<label for="fax">Numero fax sede legale (Es. 0298765432)</label>	
					</div>
						<div class="input-field col s12 m8 l6">
							<form:input type="text" id="codiceFitok"
								path="codiceFitok" maxlength="20" />
							<form:errors path="codiceFitok" cssClass="error" />
							<label for="codiceFitok">Codice fitok</label>
						</div>	
					
				</div>
				<div class="row">
				  
					<div class="hiddenField row col l12">
						<div class="input-field col s6 m4 l6">
							<form:input type="text" id="telefono"
								path="telefono" cssClass="campiAz"  maxlength="40" />
							<form:errors path="telefono" cssClass="error" />
							<label for="telefono">Telefono sede legale (Es. 0245673467)</label>
						</div>
						<div class="input-field col s6 m4 l6">
							<form:input type="text" id="cellulare"
								path="cellulare" cssClass="campiAz"  maxlength="30" />
							<form:errors path="cellulare" cssClass="error" />
							<label for="cellulare">Cellulare sede legale (Es. 3471234567)</label>
						</div>
						
					</div>
							
					<div class="hiddenField row col l12">																		
						<div class="input-field col s6 m4 l6">
										<form:input type="email" id="email"
											cssClass="myRequired campiAz" path="email"
											class="validate"  />
										<form:errors path="email" cssClass="error" />
										<label for="labelEmail">Email *</label>
										<span class="helper-text" data-error="Indirizzo email non valido"
                      											  data-success="Indirizzo email valido"></span>
									</div>
						<div class="input-field col s6 m4 l6">
										<form:input type="email" id="pec"
											cssClass="myRequired campiAz" path="pec" class="validate"
											 />
										<form:errors path="pec" cssClass="error" />
										<label id="labelPec">PEC *</label>
										<span class="helper-text" data-error="Indirizzo email non valido"
                      											  data-success="Indirizzo email valido"></span>
						</div>
						<div class="input-field col s12 m8 l12">
								<form:textarea id="tipologiaAttivita" path="tipologiaAttivita"
										cssClass="materialize-textarea validate" rows="2"
										data-length="1000" maxlength="1000" />
									<form:errors path="tipologiaAttivita" cssClass="error" />
									<label for="tipologiaAttivita">Tipologia Attività</label>
						</div>												
					</div>					
				</div>
				
				<!-- FINE DATI ANAGRAFICI AZIENDA -->
			 </div>	
			
		  	 
				 	 		
			</div>
		</div>	
		
       	<div class="row">
			<a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
			<button class="btn confirm waves-effect waves-light right" type="submit" name="datiAzienda">SALVA</button>
	   	</div>
	   
	</form:form>  		
	
	
	<br />
	 
	<content tag="script"> 
		<script type="text/javascript" src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script> 
		<script>
			
		// Popolamento dinamico della select Comune
		$('#idProvincia').change(function() {
			var params = {
				"idProvincia" : $(this).val()			
			}

			getSelectAsincrona(
				'idComune',
				'<spring:url value="/ajax/getComuniProvincia" />',
				params,
				getOptionComune, 1);
		});
			
		function getOptionComune(comune) {
			return getOption(comune.idComune, comune.denomComune);
		}
		
		
		
		function visualizzaCampiAzienda() {
			console.log('visualizzaCampiAzienda');
			//cambio label
			$("#labelProvincia").text("Provincia sede legale *");
			$("#labelComune").text("Comune sede legale *");
			$("#labelIndirizzo").text("Indirizzo sede legale *");
			$("#labelCap").text("CAP sede legale *");
			$("#labelPec").text("PEC *");
			$("#labelEmail").text("Email *");			
			$("#labelTelefono").text("Telefono");
			$("#labelCellulare").text("Cellulare");
			$("#fax").text("Fax");
			

			$("#idProvincia").removeAttr("disabled").removeAttr("readonly");
			$("#idComune").removeAttr("disabled").removeAttr("readonly");
			$("#idProvincia").formSelect();
			$("#idComune").formSelect();
			
			//mostro/nascondo campi
			$("#inputDenomSpedizioniere").show();
			$("#inputNome").hide();
			$("#inputCognome").hide();
			$("#inputNome").removeAttr("required");
			$("#inputCognome").removeAttr("required");
			$("#inputTipoSpedizioniereAltro").hide();			
			$("#tipoSpedizioniereAltro").val("");  

			//sistemo i required
			$(".myRequired").attr("required", "required");
			$("#cognome").removeAttr("required");
			$("#nome").removeAttr("required");
			$("#denom").attr("required", "required");
			$("#partitaIva").attr("required", "required");
			$("#tipoSpedizioniereAltro").removeAttr("required");
			
			
			// abilito le label, altrimenti si sovrappongono ai valori
		    $(".campiUt:not(.select-wrapper)").next().addClass("active");
		}

		function visualizzaCampiAziendaIndividuale() {
			console.log('visualizzaCampiAziendaIndividuale');
			var idTipoAzienda = $("#idTipoAzienda").val();
			console.log('idTipoAzienda ='+idTipoAzienda);

			//cambio label campi comuni
			if (idTipoAzienda == 5) {
				$("#labelProvincia").text("Provincia Azienda *");
				$("#labelComune").text("Comune Azienda *");
				$("#labelIndirizzo").text("Indirizzo Azienda *");
				$("#labelCap").text("CAP Azienda *");
				$("#labelPec").text("PEC *");	
				$("#labelEmail").text("Email *");
				$("#labelTelefono").text("Telefono");				
				
				$("#pec").attr("required","required");
				$("#email").attr("required","required");
				
				$("#inputTipoSpedizioniereAltro").hide();
				$("#tipoSpedizioniereAltro").removeAttr("required");
				$("#tipoSpedizioniereAltro").val("");  
							
			} 
			else {
				$("#labelProvincia").text("Provincia residenza *");
				$("#labelComune").text("Comune residenza *");
				$("#labelIndirizzo").text("Indirizzo residenza *");
				$("#labelCap").text("CAP *");
				$("#labelPec").text("PEC (facoltativa)");
				$("#labelTelefono").text("Telefono");
				// solo per la tipologia Altro idTipoAzienda = 1
				$("#tipoSpedizioniereAltro").text("Specificare tipologia Altro");
				$("#inputTipoSpedizioniereAltro").show();
				$("#tipoSpedizioniereAltro").attr("required","required");
								
				$("#pec").removeAttr("required");
				$("#email").removeAttr("required");
			}

			//mostro/nasocndo campi
			$("#inputDenomSpedizioniere").hide();
			$("#inputDenomSpedizioniere").removeAttr("required");
			
			$("#inputNome").show();
			$("#nome").attr("required","required");
			
			$("#inputCognome").show();
			$("#cognome").attr("required","required");

			//abilito gli altri campi
			$("#pec").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
			$("#cap").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
			$("#indirizzo").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");			

			//tolgo i required ai campi disabilitati
			$("#denom").removeAttr("required");
			//$("#partitaIva").removeAttr("required");

			enableSelect();
			
			// abilito le label, altrimenti si sovrappongono ai valori
		    $(".campiUt:not(.select-wrapper)").next().addClass("active");
		}

		function enableSelect() {
			console.log('enableSelect');
			$(".select-dropdown").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");

			$("#idProvincia").removeAttr("disabled").removeAttr("readonly");
			$("#idComune").removeAttr("disabled").removeAttr("readonly");			
			$("#idTipoSpedizioniere").removeAttr("disabled").removeAttr("readonly");

			$("#idProvincia").formSelect();
			$("#idComune").formSelect();			
			$("#idTipoSpedizioniere").formSelect();
		}

		//controlla l'idTipoAzienda e mostra/nasconde i campi corretti
		function visualizzaNascondiCampi() {
			console.log('visualizzaNascondiCampi');
			var idTipoAzienda = $("#idTipoSpedizioniere").val();
			console.log('idTipoSpedizioniere = '+idTipoAzienda);

			$(".hiddenField").show();
			$("select").formSelect();

			if (idTipoAzienda !== undefined && idTipoAzienda != null) {
				if (idTipoAzienda == 4 || idTipoAzienda == 2 || idTipoAzienda == 3) {
					visualizzaCampiAzienda();
				} 
				//Altro (ex-Utente Privato)  e  Azienda individuale (ex-Ditta individuale)
				else if (idTipoAzienda == 1 || idTipoAzienda == 5) {
					visualizzaCampiAziendaIndividuale();
				}
			} 
			else {
				visualizzaCampiAzienda();
			}
		}		
		
	
			
		</script> 
	</content>

</body>
</html>