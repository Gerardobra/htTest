<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage caronte</title>
<style type="text/css">
.home-nav-ext {
	padding: 20px;
	padding-right: 40px;
	background: transparent;
	box-shadow: none;
}

select[required] {
	z-index: -5;
}

.dropdown-content li>span {
	color: #26a69a;
}

body {
	/*background: linear-gradient(#42ADE2, #80d8ff);*/
	/*background: #42c4ac;*/
	/*background: radial-gradient(ellipse farthest-side at 100% 100%,#dbf6c8 20%,#42c4ac 50%,#4caf50 110%);*/
	/*background: linear-gradient(#42ADE2, #80d8ff);*/
	background-image: url('<spring:url value="/resources/img/home/home2.jpg"/>');
	background-size: cover;
	-webkit-animation: 20s linear 0s normal none infinite animate;
	-moz-animation: 20s linear 0s normal none infinite animate;
	-ms-animation: 20s linear 0s normal none infinite animate;
	-o-animation: 20s linear 0s normal none infinite animate;
	animation: 20s linear 0s normal none infinite animate;
	animation-direction: alternate;
}

a.nav-sign-up {
	box-shadow: none;
	border-radius: 2pc;
	border: 1px solid white;
	padding: 0 20px;
	background: white;
	color: black;
}

.page-footer {
	background: transparent !important;
}

.page-footer {
	background: transparent !important;
}

@
-webkit-keyframes animate {
	from {background-position: 0 0;
}

to {
	background-position: -500px 0;
}

}
@
-moz-keyframes animate {
	from {background-position: 0 0;
}

to {
	background-position: -500px 0;
}

}
@
-ms-keyframes animate {
	from {background-position: 0 0;
}

to {
	background-position: -500px 0;
}

}
@
-o-keyframes animate {
	from {background-position: 0 0;
}

to {
	background-position: -500px 0;
}

}
@
keyframes animate {
	from {background-position: 0 0;
}

to {
	background-position: -500px 0;
}
}
</style>

</head>
<body>
	<div id="backgroung-overlay"></div>
	<header> <nav class="nav-extended home-nav-ext">
	<div class="nav-wrapper">
		<a href="#!" class="brand-logo center">CARONTE</a>
		<ul class="right hide-on-med-and-down">
			<li style="margin-right: 5px"><a
				href='<spring:url value="/login"/>' class="nav-sign-up"> <i
					class="material-icons left">person_outline</i>Accedi
			</a></li>
		</ul>
	</div>
	</nav> </header>
	<main>
	<div class="container">
		<div class="col l12">
			<div class="card-panel expand-panel">
				<div class="card-title">
					<h5>REGISTRAZIONE UTENTE</h5>
					<p>Per registrarsi, compilare i seguenti dati. I campi con *
						sono obbligatori; dopo che l’utente avrà selezionato il tipo di
						Organizzazione di appartenenza (Società, Ente, Cooperativa, Ditta
						individuale o Utente privato), il sistema mostrerà in automatico
						anche il filtro CUAA / CF, in modo da ricercare l’Organizzazione
						se già censita. I dati dell’Organizzazione sono obbligatori per
						completare la registrazione stessa in caso di Organizzazione non
						ancora presente nel sistema.
						Con la registrazione l'utente verrà abilitato alla parte che permette
						di inserire una nuova domanda di Autorizzazione.						
						Quando la domanda verrà autorizzata, la registrazione sarà attiva anche per le parti richieste in fase di compilazione 
						della domanda. L’utente riceverà quindi una comunicazione dell’avvenuta registrazione all’indirizzo mail indicato nella
						richiesta.</p>
				</div>
				<form:form action="${formAction}" method="post"
					modelAttribute="spedizioniereForm" accept-charset="utf-8">
					<div class="card-content" id="registrazioneUtente">
						<div class="section">
							<h6 class="blue-text">DATI UTENTE</h6>
							<div class="divider"></div>
							<!-- UTENTE -->
							<div class="row" style="padding-top: 2em;">
								<div class="row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="cognome" path="cognome"
											required="required" cssClass="myDisabledUt"/>
										<form:errors path="cognome" cssClass="error" />
										<label for="cognome">Cognome *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="nome" path="nome"
											required="required" cssClass="myDisabledUt"/>
										<form:errors path="nome" cssClass="error" />
										<label for="nome">Nome *</label>
									</div>
								</div>
								<div class="row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="codiceFiscale" required="required"
											path="codiceFiscale" maxlength="16" onchange="checkIfUtenteExists();"/>
										<form:errors path="codiceFiscale" cssClass="error" />
										<label for="codiceFiscale">Codice Fiscale *</label>
									</div>
									<div class="input-field col s6 m4 l3">
										<form:input type='text' id="dataNascita"
											path="dataNascita" cssClass="datepicker_birthdate"
											required="required" />
										<form:errors path="dataNascita" cssClass="error" />
										<label for="dataNascita">Data nascita *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="email" id="email" path="email"
											required="required" class="validate myDisabledUt" />
										<form:errors path="email" cssClass="error" />
										<label for="email">Email *</label>
										<span class="helper-text" data-error="Indirizzo email non valido"
										  data-success=""></span>
									</div>
								</div>
								<div class="row col l12">
									<div class="left-align input-field col l6">
										<form:password id="password" path="password" class="validate tooltipped myDisabledUt"
											required="required" 
											data-tooltip="La password deve contenere almeno 8 caratteri e deve contenere almeno una lettera maiuscola, una minuscola, un numero e un carattere speciale" />
										<form:errors path="password" cssClass="error" />
										<label for="password">Password *</label>
									</div>
									<div class="input-field col l6">
										<form:password id="confermaPassword" path="confermaPassword"
											class="validate" required="required" />
										<form:errors path="confermaPassword" cssClass="error myDisabledUt" />
										<label for="confermaPassword">Conferma Password *</label>
										<span class="helper-text" data-error="Le password non coincidono"
                      						data-success=""></span>
									</div>
								</div>
								<div class="row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="numeroTelefonoUtente"
											path="numeroTelefonoUtente" cssClass="myDisabledUt" />
										<form:errors path="numeroTelefonoUtente" cssClass="error" />
										<label for="numeroTelefonoUtente">Telefono (Es. 0245673467)</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="numeroCellUtente"
											path="numeroCellUtente" required="required" cssClass="myDisabledUt" />
										<form:errors path="numeroCellUtente" cssClass="error" />
										<label for="numeroCellUtente">Cellulare (Es. 3471234567)*</label>
									</div>
									<div class="input-field col s6 m4 l12">
										<form:textarea id="note" path="note" data-length="1000"
											placeholder="Inserire una breve descrizione dell'attività svolta."
											cssClass="materialize-textarea myDisabledUt" class="materialize-textarea" />
										<form:errors path="note" cssClass="error" />
										<label for="note">Note</label>
									</div>
								</div>
							</div>
						</div>
						<!-- End SECTION DATI UTENTE -->

						<!-- ORGANIZZAZIONE -->
						<div class="section">
							<h6 class="blue-text">DATI ORGANIZZAZIONE DI APPARTENENZA</h6>
							<div class="divider" style="margin-bottom: 2em;"></div>
							<div class="row">
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
											required="required" onchange="checkIfSpedizioniereExists();" />
										<form:errors path="cuaa" cssClass="error" />
										<label for="cuaa">CUAA / CF *</label>
									</div>
								</div>

								<div class=" hiddenField row col l12">								
									  <% /* %>
										<div class="input-field col s6 m4 l6">
											<form:select id="idAssociazioneSezione" required="required"
												cssClass="myRequired myDisabled validate" path="idAssociazioneSezione">
												<form:option value="" label="Selezionare" />
												<form:options items="${listaSezioni}"
													itemValue="idAssociazioneSezione"
													itemLabel="descAssociazioneSezione" />
											</form:select>
											<form:errors path="idAssociazioneSezione" cssClass="error" />
										</div>
										<% */ %>
									<!-- 					
					                  <div class="col s6 m3">
					                    <label>
						                    <form:checkbox id="sezioneImport" path="sezioneImport" cssClass="myDisabled" />
						                    <span>Import</span>
					                    </label>
					                  </div>					                 
					                  <div class="col s6 m3">
					                    <label>
						                    <form:checkbox id="sezioneExport" path="sezioneExport" cssClass="myDisabled" />
						                    <span>Export</span>
					                    </label>
					                  </div>
					                  <div class="col s6 m3">
					                    <label>
						                    <form:checkbox id="sezioneVivai" path="sezioneVivai" cssClass="myDisabled" />
						                    <span>Vivai</span>
					                    </label>
					                  </div>	
					                    -->
					                    <div id="inputTipoSpedizioniereAltro" class="input-field col s6 m4 l6">
										<form:input type="text" id="tipoSpedizioniereAltro" required="required"
											readonly="true" cssClass="myRequired myDisabled"
											maxlength="100" path="tipoSpedizioniereAltro" />
										<form:errors path="tipoSpedizioniereAltro" cssClass="error" />
											<label id="labelTipoSpedizioniereAltro" for="tipoSpedizioniereAltro">Descrizione tipologia Altro *</label>
										</div>
					                  <div id="inputPartitaIVA" class="input-field col s6 m4 l6">
										<form:input type="text" id="partitaIVA" required="required"
											readonly="true" cssClass="myRequired myDisabled"
											maxlength="11" path="partitaIVA" />
										<form:errors path="partitaIVA" cssClass="error" />
											<label id="labelPartitaIVA" for="partitaIVA">Partita IVA *</label>
										</div>								
					                  <div id="inputDenomSpedizioniere"
											class="input-field col s6 m4 l6">
											<form:input type="text" id="denomSpedizioniere"
												required="required" readonly="true"
												cssClass="myRequired myDisabled" path="denomSpedizioniere" />
											<form:errors path="denomSpedizioniere" cssClass="error" />
											<label id="labelDenominazione" for="denomSpedizioniere">Denominazione
												/ Ragione sociale *</label>
										</div>
										<div id="inputNomeDitta" class="input-field col s6 m4 l3">
											<form:input type="text" id="nomeDitta" required="required"
												readonly="true" cssClass="myRequired myDisabled"
												path="nomeDitta" />
											<form:errors path="nomeDitta" cssClass="error" />
											<label id="labelNome" for="nomeDitta">Nome *</label>
										</div>
										<div id="inputCognomeDitta" class="input-field col s6 m4 l3">
											<form:input type="text" id="cognomeDitta" required="required"
												readonly="true" cssClass="myRequired myDisabled"
												path="cognomeDitta" />
											<form:errors path="cognomeDitta" cssClass="error" />
											<label id="labelCognome" for="cognomeDitta">Cognome *</label>
										</div>
	
									  <!-- 
									  <div class="row col s12 m6">
					                    <form:errors path="sezioneImport" cssClass="error" />
					                  </div>
					                  -->
					                  <!-- <div class="row col s12 m6">
					                    <form:errors path="sezioneExport" cssClass="error" />
					                  </div>
					                  -->								
				                </div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:select id="idProvinciaSedeSociale" required="required"
											onchange="getComuni();controllaProvinciaSedeLegale()" path="idProvinciaSedeSociale"
											cssClass="validate myRequired myDisabled">
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
											path="idComuneSedeSociale"
											cssClass="validate myRequired myDisabled ">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaComuni}" itemValue="idComune"
												readonly="true" itemLabel="denomComune" />
										</form:select>
										<form:errors path="idComuneSedeSociale" cssClass="error" />
										<label id="labelComune" for="idComuneSedeSociale">Comune
											sede legale *</label>
									</div>
								</div>

								<div class="hiddenField row col l12">
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="capSedeSociale"
											required="required" path="capSedeSociale" readonly="true"
											cssClass="myRequired myDisabled" />
										<form:errors path="capSedeSociale" cssClass="error" />
										<label id="labelCap" for="capSedeSociale">CAP sede
											legale *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="indirizzoSedeSociale"
											required="required" readonly="true"
											cssClass="myRequired myDisabled" path="indirizzoSedeSociale" />
										<form:errors path="indirizzoSedeSociale" cssClass="error" />
										<label id="labelIndirizzo" for="indirizzoSedeSociale">Indirizzo
											sede legale (Es. Via Roma, 24)*</label>
									</div>
								</div>

                 			 	<div class="row col l12">							
                 					<div class="input-field col s6 m4 l6">
										<form:input type="email" id="emailSpedizioniere"
											cssClass="myDisabled myRequired" path="emailSpedizioniere"
											class="validate" readonly="true" />
										<form:errors path="emailSpedizioniere" cssClass="error" />
										<label for="emailSpedizioniere">Email *</label>
										<span class="helper-text" data-error="Indirizzo email non valido"
	                     						data-success="Indirizzo email valido"></span>
								    </div>
									<div class="input-field col s6 m4 l6">
										<form:input type="email" id="pec"
											cssClass="myDisabled myRequired" path="pec" class="validate"
											readonly="true" />
										<form:errors path="pec" cssClass="error" />
										<label id="labelPec" for="pec">PEC *</label>
										<span class="helper-text" data-error="Indirizzo email non valido"
	                     							data-success="Indirizzo email valido"></span>
									</div>
							  </div>		
								
								<div id="datiCodiceRuop" class="row col l12" style="display:none">	
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="codiceRUOP"
											path="codiceRUOP" cssClass="validate"
											 maxlength="10" />
										<form:errors path="codiceRUOP" cssClass="error" />
										<label for="codiceRUOP">Codice Ruop *</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type='text' id="dataRegistrazioneRuop"
											path="dataRegistrazioneRuop" cssClass="datepicker validate"
											/>
										<form:errors path="dataRegistrazioneRuop" cssClass="error" />
										<label for="dataRegistrazioneRuop">Data registrazione Ruop *</label>
									</div>
								</div>
								
								<div class="row col l12">						
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="numeroTelefono"
											cssClass="myDisabled" path="numeroTelefono" readonly="true" />
										<form:errors path="numeroTelefono" cssClass="error" />
										<label for="numeroTelefono">Numero di telefono (Es. 0245673467)</label>
									</div>
									<div class="input-field col s6 m4 l6">
										<form:input type="text" id="numeroCellulare"
											cssClass="myDisabled" path="numeroCellulare" readonly="true" />
										<form:errors path="numeroCellulare" cssClass="error" />
										<label for="numeroCellulare">Numero di cellulare (Es. 3471234567)</label>
									</div>
								</div>
								
								<div class="row col l12">	
									<div class="input-field col s6 m4 l6">
										<form:textarea id="motivoRichiesta" path="motivoRichiesta"
											data-length="1000" cssClass="materialize-textarea myDisabled"
											class="materialize-textarea" />
										<form:errors path="motivoRichiesta" cssClass="error" />
										<label for="note">Motivo della richiesta</label>
									</div>
								</div>
								
								<div class="row col l12">								  
									<a href='<spring:url value="/registrazione/report/privacy"/>'>Visualizza l'informativa relativa al trattamento dei dati personali</a><br/>									
								</div>
								<div class="row col l12">								    
										<label> <form:checkbox value="S"
												id="privacyPolicy" path="privacyPolicy" />
											<span>Dichiaro di aver preso visione dell'informativa sul trattamento dei dati personali</span>
										</label>	
										<form:errors path="privacyPolicy" cssClass="error" />								
						  		</div>
							</div>
						</div>

						<div class="card-action">
							<div class="row col s12">
								<div class="col l12">
									<button type="button" class="btn clear-form waves-effect left"
										onclick="pulisci();">PULISCI</button>
									<button id="confirmButton"
										class="btn confirm waves-effect waves-light right"
										type="submit" name="datiSpedizioniere"
										style="margin-left: 2em;">SALVA</button>											
									<a href='<spring:url value="/home"/>'
										class="btn waves-effect waves-light col right">ANNULLA</a>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	</main>
	<footer class="page-footer">
	<div class="row center-align">© 2017 Copyright Text</div>
	</footer>

	<content tag="script"> <script type="text/javascript">
	
	    var visualizzaAutorizzazioni = false;
	
		$(document).ready(
				function() {
					console.log('ricarica correttamente quando ritorna in pagina');
					//ricarica correttamente quando ritorna in pagina 
					$("select").formSelect();

					//disabilito le select
					$(".select-dropdown").attr("disabled", "disabled");
					$(".select-dropdown").attr("readonly", "readonly");
					$("select").attr("disabled", "disabled");
					$("select").attr("readonly", "readonly");

					checkIfSpedizioniereExists();
					visualizzaNascondiCampi();

					//se la pagina è pulita
					if ($("#cuaa").val() == null || $("#cuaa").val() == "") {
						//nascondo i campi dell'organizzazione a parte la tipologia ed il cuaa
						$(".hiddenField").hide();
					}
					$("#idTipoSpedizioniere").removeAttr("disabled").removeAttr("readonly");
					$("#idTipoSpedizioniere").formSelect();
					
					checkIfUtenteExists();
					
					console.log('visualizzaAutorizzazioni ='+visualizzaAutorizzazioni);
					<c:if test="${not empty visualizzaAutorizzazioni}">
					  visualizzaAutorizzazioni = true;
					  console.log('visualizzaAutorizzazioni ='+visualizzaAutorizzazioni);
			        </c:if>
			        
			        
			        // Visualizza o meno i campi : codice Ruop e data registrazione Ruop, in base alla provincia selezionata
			        controllaProvinciaSedeLegale();
			        
				});

		function visualizzaCampiAzienda() {

			//cambio label
			$("#labelProvincia").text("Provincia sede legale *");
			$("#labelComune").text("Comune sede legale *");
			$("#labelIndirizzo").text("Indirizzo sede legale *");
			$("#labelCap").text("CAP sede legale *");
			$("#labelPec").text("PEC *");

			//mostro/nascondo campi
			$("#inputPartitaIVA").show();
			$("#inputDenomSpedizioniere").show();
			$("#inputNomeDitta").hide();
			$("#inputCognomeDitta").hide();
			$("#inputNomeDitta").removeAttr("required");
			$("#inputCognomeDitta").removeAttr("required");
			$("#cuaa").removeAttr("disabled").removeAttr("readonly");
			$("#inputTipoSpedizioniereAltro").hide();
			$("#tipoSpedizioniereAltro").val('');

			//sistemo i required
			$(".myRequired").attr("required", "required");
			$("#cognomeDitta").removeAttr("required");
			$("#nomeDitta").removeAttr("required");
			$("#tipoSpedizioniereAltro").removeAttr("required");

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
				$("#inputDenomSpedizioniere").show();
				$("#inputDenomSpedizioniere").removeAttr("required");				

			} else { //Altro - ex - utente privato
				$("#labelProvincia").text("Provincia residenza *");
				$("#labelComune").text("Comune residenza *");
				$("#labelIndirizzo").text("Indirizzo residenza *");
				$("#labelCap").text("CAP residenza *");
				$("#labelPec").text("PEC (facoltativa)");
				$("#pec").removeAttr("required");
				// solo per la tipologia Altro idTipoAzienda = 1
				$("#tipoSpedizioniereAltro").text("Descrizione tipologia Altro");
				$("#inputTipoSpedizioniereAltro").show();
				$("#tipoSpedizioniereAltro").attr("required","required");
				$("#tipoSpedizioniereAltro").removeAttr("readonly");
				
				$("#inputDenomSpedizioniere").hide();
				$("#inputDenomSpedizioniere").removeAttr("required");

			}

			//mostro/nasocndo campi
			
			//$("#inputPartitaIVA").hide();
			//$("#inputPartitaIVA").removeAttr("required");			
			$("#inputNomeDitta").show();
			$("#inputCognomeDitta").show();

			//COPIO VALORI UTENTE E DISABILITO CAMPI che sono uguali a quelli dell'utente
			var cognome = $("#cognome").val();
			if (cognome != "") {
				$("#cognomeDitta").val(cognome);
				$("label[for='cognomeDitta']").addClass("active");

			}
			$("#cognomeDitta").attr("disabled", "disabled").attr("readonly",
					"readonly");

			var nome = $("#nome").val();
			if (nome != "") {
				$("#nomeDitta").val(nome);
				$("label[for='nomeDitta']").addClass("active");
			}
			$("#nomeDitta").attr("disabled", "disabled").attr("readonly",
					"readonly");
			
			var congnomeNome = cognome + ' '+ nome;
			if (congnomeNome != "") {
				$("#denomSpedizioniere").val(congnomeNome);
				$("label[for='denomSpedizioniere']").addClass("active");
			}
			$("#denomSpedizioniere").removeAttr("disabled").removeAttr("readonly");

			var numeroTelefono = $("#numeroTelefonoUtente").val();
			if (numeroTelefono != "") {
				$("#numeroTelefono").val(numeroTelefono);
				$("label[for='numeroTelefono']").addClass("active");
			}
			$("#numeroTelefono").attr("disabled", "disabled").attr("readonly",
					"readonly");
			
			var numeroCell = $("#numeroCellUtente").val();
			if (numeroCell != "") {
				$("#numeroCellulare").val(numeroCell);
				$("label[for='numeroCellulare']").addClass("active");
			}
			$("#numeroCellulare").attr("disabled", "disabled").attr("readonly","readonly");

			var email = $("#email").val();
			if (email != "") {
				$("#emailSpedizioniere").val(email);
				$("label[for='emailSpedizioniere']").addClass("active");
			}
			$("#emailSpedizioniere").attr("disabled", "disabled").attr("readonly", "readonly");
			

			var codiceFiscale = $("#codiceFiscale").val();
			if (codiceFiscale != "") {
				$("#cuaa").val(codiceFiscale);
				$("label[for='cuaa']").addClass("active");
			}
			$("#cuaa").attr("disabled", "disabled")
					.attr("readonly", "readonly");
			
			
			
			//abilito gli altri campi
			//$("#codiceRuop").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
			$("#pec").removeAttr("disabled", "disabled").removeAttr("readonly", "readonly");
			$("#capSedeSociale").removeAttr("disabled", "disabled").removeAttr(
					"readonly", "readonly");
			$("#indirizzoSedeSociale").removeAttr("disabled", "disabled")
					.removeAttr("readonly", "readonly");
			$("#motivoRichiesta").removeAttr("disabled", "disabled")
					.removeAttr("readonly", "readonly");
			$("#partitaIVA").removeAttr("disabled").removeAttr("readonly");

			//tolgo i required ai campi disabilitati
			$("#denomSpedizioniere").removeAttr("required");
			//$("#partitaIVA").removeAttr("required");
			$("#cognomeDitta").removeAttr("required");
			$("#cuaa").removeAttr("required");
			$("#nomeDitta").removeAttr("required");
			$("#numeroTelefono").removeAttr("required");
			$("#numeroCellulare").removeAttr("required");
			$("#emailSpedizioniere").removeAttr("required");
			$("#motivoRichiesta").removeAttr("required");
			
			enableSelect();
		}

		function enableSelect() {

			$(".select-dropdown").removeAttr("disabled", "disabled")
					.removeAttr("readonly", "readonly");

			$("#idProvinciaSedeSociale").removeAttr("disabled").removeAttr(
					"readonly");
			$("#idComuneSedeSociale").removeAttr("disabled").removeAttr(
					"readonly");
			/*$("#idAssociazioneSezione").removeAttr("disabled").removeAttr(
					"readonly");
					"readonly");*/
			$("#idProvinciaSedeSociale").formSelect();
			$("#idComuneSedeSociale").formSelect();
			//$("#idAssociazioneSezione").formSelect();
			$("#idTipoSpedizioniere").formSelect();

		}

		//controlla l'idTipoSpedizioniere e mostra/nasconde i campi corretti
		function visualizzaNascondiCampi() {

			var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();

			$(".hiddenField").show();
			$("select").formSelect();

			if (idTipoSpedizioniere !== undefined
					&& idTipoSpedizioniere != null) {
				if (idTipoSpedizioniere == 4 || idTipoSpedizioniere == 2
						|| idTipoSpedizioniere == 3) {
					visualizzaCampiAzienda();
				} // Altro (ex-utente privato) e azienda individuale (ex-ditta inidividuale)
				else if (idTipoSpedizioniere == 1 || idTipoSpedizioniere == 5) {
					visualizzaCampiAziendaIndividuale();
					$("#cuaa").attr("disabled", "disabled").attr("readonly",
							"readonly");
				}
			} else {
				visualizzaCampiAzienda();
				$("#cuaa").removeAttr("disabled").removeAttr("readonly");
			}
		}

		function pulisci() {

			//pulisci form di default e riporto alla situazione iniziale
			$("#idTipoSpedizioniere").removeAttr("disabled").removeAttr(
					"readonly");
			$("#idTipoSpedizioniere").formSelect();

			$("#cuaa").removeAttr("disabled").removeAttr("readonly");

			//ridisabilito ciò che inizialmente era disabilitato
			$(".myDisabled").attr("readonly", "readonly");
			
			/*
			$("#sezioneVivai").prop("disabled", "disabled");			
			$("#sezioneImport").prop("disabled", "disabled");
			$("#sezioneExport").prop("disabled", "disabled");*/
			
			$("#email").val("");
			$("#emailSpedizioniere").val("");
			$("#tipoSpedizionireAltro").val("");
			
			//rimuovo elementi di validazione ed errori
			$(".valid").removeClass("valid");
			$(".invalid").removeClass("invalid");

			$(".error").remove();

			//nascondo i campi per riportare la pagina alla situazione iniziale
			$(".hiddenField").hide();

		}

		function getComuni() {

			$("#idComuneSedeSociale").removeAttr("readonly").removeAttr(
					"disabled");
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

		
		// Richiamata all'onchange del campo codice fiscale per precaricare i dati se gia presenti su car_t_utente
		function checkIfUtenteExists(){
			console.log('checkIfUtenteExists');
			var codiceFiscaleInput = $("#codiceFiscale").val();
			console.log('codiceFiscaleInput ='+codiceFiscaleInput);
			if (codiceFiscaleInput !== undefined && codiceFiscaleInput != null && codiceFiscaleInput.trim().length == 16) {
				$.ajax({
					url : '<spring:url value="/registrazione/checkIfcheckIfUtenteExists_"/>'+ codiceFiscaleInput.trim(),
					success : function(response) {
						if (response.indexOf("NOTEXISTS") == -1) {
							console.log('utente trovato su car_t_utente');
						    
							// valorizzo i campi con i valori trovati e disabilito il campo password e conferma password
							var splittedStr = response.split("&&&");
						    
						    var idUtente = splittedStr[0];
						    var codiceFiscale = splittedStr[1];
						    var cognome = splittedStr[2];
						    console.log('cognome ='+cognome);
						    var email = splittedStr[3];
						    console.log('email ='+email);
						    var nome = splittedStr[4];
						    console.log('nome ='+nome);
						    var note = splittedStr[5];
						    console.log('note ='+note);
						    var password = splittedStr[6];
						    console.log('password ='+password);
						    var telefono = splittedStr[7];
						    console.log('telefono ='+telefono);
						    var cell = splittedStr[8];
						    console.log('cell ='+cell);
						    
						    //svuoto i campi
							$(".myDisabledUt").val("");
						    
						    $("#codiceFiscale").val(codiceFiscale);
						    
						    if(cognome !== undefined && cognome != null && cognome != 'null'){
						      $("#cognome").val(cognome);
						    }
						    
						    if(email !== undefined && email != null && email != 'null'){
							  $("#email").val(email);
							}  
						    
						    if(nome !== undefined && nome != null && nome != 'null'){
						      $("#nome").val(nome);
						    }
						    
						    if(note !== undefined && note != null && note != 'null'){
							  $("#note").val(note);
							} 
						    
						    if(password !== undefined && password != null && password != 'null'){
						    	$("#password").val(password);
						    	$("#password").prop('disabled', 'disabled');
						    	$("#password").removeAttr("required");
						    }	
						    						    
						    if(password !== undefined && password != null && password != 'null'){
						      $("#confermaPassword").val(password);
						      $("#confermaPassword").prop('disabled', 'disabled');
						      $("#confermaPassword").removeAttr("required");
						    }
						    						    						    
						    if(telefono !== undefined && telefono != null && telefono != 'null'){
						      $("#numeroTelefonoUtente").val(telefono);						      						     
						    }
						    
						    if(cell !== undefined && cell != null && cell != 'null'){
							  $("#numeroCellUtente").val(cell);						      						     
							}
						    
						    // abilito le label, altrimenti si sovrappongono ai valori
						    $(".myDisabledUt:not(.select-wrapper)").next().addClass("active");
						}
						// riabilito i campi per inserimento password
						else {							
					      console.log('utente non trovato su car_t_utente');
					      $("#password").val("");
					      $("#password").removeAttr("disabled");
						  $("#password").attr("required", "required");
						  
						  $("#confermaPassword").val("");
						  $("#confermaPassword").removeAttr("disabled");
						  $("#confermaPassword").attr("required", "required");
						  
					      /*$("#codiceFiscale").val(codiceFiscaleInput);
						  $("#cognome").val("");						    
						  $("#email").val("");							  
						  $("#nome").val("");						    
						  $("#note").val("");							
						  $("#password").val("");
						  $("#password").removeAttr("disabled");
						  $("#password").attr("required", "required");
						  $("#confermaPassword").val("");
						  $("#confermaPassword").removeAttr("disabled");						  						  						 
						  $("#numeroTelefonoUtente").val("");*/						  				
						}
					}
					
				});
			}
			// se svuota il campo del codice fiscale, ripulisco anche gli altri campi, nel caso prima avesse indicato un codice fiscale
			else{
			  $("#codiceFiscale").val(codiceFiscaleInput);
			  $("#cognome").val("");						    
			  $("#email").val("");							  
			  $("#nome").val("");						    
			  $("#note").val("");							
			  $("#password").val("");
			  $("#password").removeAttr("disabled");
			  $("#password").attr("required", "required");
			  $("#confermaPassword").val("");
			  $("#confermaPassword").removeAttr("disabled");						  						  						 
			  $("#numeroTelefonoUtente").val("");
			  $("#numeroCellUtente").val("");
			}
		}
		
		
		function checkIfSpedizioniereExists() {
			console.log('checkIfSpedizioniereExists');
			var cuaa = $("#cuaa").val();
			console.log('cuaa ='+cuaa);

			if (cuaa !== undefined && cuaa != null
					&& (cuaa.length == 11 || cuaa.length == 16)) {

				$.ajax({
					url : '<spring:url value="/registrazione/checkIfSpedizioniereExists_"/>'+ cuaa,
					success : function(response) {

						if (response.indexOf("NOTEXISTS") == -1) {
							console.log('Lo spedizioniere esiste sul db');
							//OK ESISTE; RIEMPIO I CAMPI
							var splittedStr = response.split("&&&");
							var idTipoSpedizioniere = splittedStr[0];
							var denominazione = splittedStr[1];
							var idProvinciaSedeSociale = splittedStr[2];
							var idComuneSedeSociale = splittedStr[3];
							var denomComuneSedeSociale = splittedStr[4];
							var indirizzoSedeSociale = splittedStr[5];
							var email = splittedStr[6];
							var numeroTelefono = splittedStr[7];
							var codiceRuop = splittedStr[8];
							var pec = splittedStr[9];
							var capSedeSociale = splittedStr[10];
							var partitaIVA = splittedStr[11];
							var sezioneImport = splittedStr[12];
							var sezioneExport = splittedStr[13];
							var sezioneVivai = splittedStr[14];
							var numeroCellulare = splittedStr[15];
							var comuni = splittedStr[16];
							var tipoSpedizioniereAltro = splittedStr[17];

							//svuoto i campi
							$(".myDisabled").val("");

							//abilito le select per poterle modificare
							$(".select-dropdown").removeAttr("disabled");
							$(".select-dropdown").removeAttr("readonly");
							$("select").removeAttr("disabled");
							$("select").removeAttr("readonly");

							//Riempio i campi con i valori ricevuti dal server se ci sono
							$("#idTipoSpedizioniere").val(idTipoSpedizioniere);

							/*if (sezioneImport == 'true') {
								$("#sezioneImport").prop('checked', true);
							}else {
								$("#sezioneImport").prop('checked', false);
							}
							if (sezioneExport == 'true') {
                				$("#sezioneExport").prop('checked', true);
              				}else {
                				$("#sezioneExport").prop('checked', false);
              				}
							if (sezioneVivai == 'true') {
				                $("#sezioneVivai").prop('checked', true);
				            }else {
				                $("#sezioneVivai").prop('checked', false);
				            }*/
				            
							$("#denomSpedizioniere").val(denominazione);

							if (idProvinciaSedeSociale != null
									&& idProvinciaSedeSociale !== undefined
									&& idProvinciaSedeSociale !== "null") {
								$("#idComuneSedeSociale").html(comuni);
								$('#idComuneSedeSociale').formSelect();
								$("#idProvinciaSedeSociale").val(idProvinciaSedeSociale);
							}

							if (idComuneSedeSociale != null) {
								$("#idComuneSedeSociale").val(idComuneSedeSociale);
							}

							if (indirizzoSedeSociale !== undefined
									&& indirizzoSedeSociale != null
									&& indirizzoSedeSociale != 'null')
								$("#indirizzoSedeSociale").val(indirizzoSedeSociale);

							if (email !== undefined && email != null
									&& email != 'null')
								$("#emailSpedizioniere").val(email);

							if (numeroTelefono !== undefined
									&& numeroTelefono != null
									&& numeroTelefono != 'null')
								$("#numeroTelefono").val(numeroTelefono);
							
							
							if (numeroCellulare !== undefined && numeroCellulare != null && numeroCellulare != 'null'){
							  $("#numeroCellulare").val(numeroCellulare);
							}	

							/*if (codiceRuop !== undefined && codiceRuop != null && codiceRuop != 'null') {
								  $("#codiceRuop").val(codiceRuop);
							}*/
							
							if (pec !== undefined && pec != null
									&& pec != 'null')
								$("#pec").val(pec);

							if (capSedeSociale !== undefined
									&& capSedeSociale != null
									&& capSedeSociale != 'null')
								$("#capSedeSociale").val(capSedeSociale);

							if (partitaIVA !== undefined && partitaIVA != null
									&& partitaIVA != 'null')
								$("#partitaIVA").val(partitaIVA);
							
							if (tipoSpedizioniereAltro !== undefined && tipoSpedizioniereAltro != null
									&& tipoSpedizioniereAltro != 'null')
								$("#tipoSpedizioniereAltro").val(tipoSpedizioniereAltro);

							//attivo le label se no si sovrappongono le label ed i valori inseriti 
							//(non quelle delle select che sono già in posizione corretta)
							$(".myDisabled:not(.select-wrapper)").next()
									.addClass("active");

							
							$(".select-dropdown").removeAttr("readonly").removeAttr("disabled");
							$("select").removeAttr("readonly").removeAttr("disabled");

							//re-inizializzo le select
							$("select").formSelect();

							//toglo il readonly
							$(".myDisabled").removeAttr("readonly").removeAttr("disabled");

							//AGGIUNGO IL REQUIRED
							$(".myRequired").attr("required", "required");
							
							/*$("#sezioneImport").prop('disabled', 'disabled');
							$("#sezioneExport").prop('disabled', 'disabled');							
							$("#sezioneVivai").prop('disabled', 'disabled');*/
							
							visualizzaNascondiCampi();

						} 
						else {
							console.log('Lo spedizioniere NON esiste sul db');
							//Non esiste ancora uno spedizioniere con il cuaa inserito dall'utente
							//quindi abilito i campi in modo che l'utente possa inserirli
							$(".select-dropdown").removeAttr("readonly").removeAttr("disabled");
							$("select").removeAttr("readonly").removeAttr("disabled");

							//re-inizializzo le select
							$("select").formSelect();

							//toglo il readonly
							$(".myDisabled").removeAttr("readonly").removeAttr(
									"disabled");

							//AGGIUNGO IL REQUIRED
							$(".myRequired").attr("required", "required");

							visualizzaNascondiCampi();
							enableSelect();

						}
					}
				});
			}

		}

		//in caso di ditta individuale o utente privato se cambia un campo dell'utente, automaticamente
		//cambia anche il relativo campo dello spedizioniere
		$("#nome").on("change", function(e) {

			var idTipoSpedizioniere = $("#idTipoSpedizioniere")
					.val();
			if (idTipoSpedizioniere !== undefined
					&& idTipoSpedizioniere != null
					&& (idTipoSpedizioniere == 1 || idTipoSpedizioniere == 5)) {
				$("#nomeDitta").val($("#nome").val());
				$("label[for='nomeDitta']").addClass("active");
			}

		});

		$("#cognome").on("focusout", function(e) {
			var idTipoSpedizioniere = $("#idTipoSpedizioniere")
					.val();
			if (idTipoSpedizioniere !== undefined
					&& idTipoSpedizioniere != null
					&& (idTipoSpedizioniere == 1 || idTipoSpedizioniere == 5)) {
				$("#cognomeDitta").val($("#cognome").val());
				$("label[for='cognomeDitta']").addClass(
						"active");
			}

		});

		$("#numeroTelefonoUtente").on("change", function(e) {
			var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();
			
			if (idTipoSpedizioniere !== undefined
					&& idTipoSpedizioniere != null
					&& (idTipoSpedizioniere == 1 || idTipoSpedizioniere == 5)) {
				$("#numeroTelefono").val($("#numeroTelefonoUtente").val());
				$("label[for='numeroTelefono']").addClass("active");
			}

		});
		
		$("#numeroCellUtente").on("change", function(e) {
			var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();
			
			if (idTipoSpedizioniere !== undefined
					&& idTipoSpedizioniere != null
					&& (idTipoSpedizioniere == 1 || idTipoSpedizioniere == 5)) {
				$("#numeroCellulare").val($("#numeroCellUtente").val());
				$("label[for='numeroCellulare']").addClass("active");
			}

		});

		$("#email").on("change", function(e) {
			var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();
			
			if (idTipoSpedizioniere !== undefined
					&& idTipoSpedizioniere != null
					&& (idTipoSpedizioniere == 1 || idTipoSpedizioniere == 5)) {
				$("#emailSpedizioniere").val($("#email").val());
				$("label[for='emailSpedizioniere']").addClass("active");
			}

		});

		$("#codiceFiscale").on("change", function(e) {
			var idTipoSpedizioniere = $("#idTipoSpedizioniere").val();
			
			if (idTipoSpedizioniere !== undefined
					&& idTipoSpedizioniere != null
					&& (idTipoSpedizioniere == 1 || idTipoSpedizioniere == 5)) {
				$("#cuaa").val($("#codiceFiscale").val());
				$("label[for='cuaa']").addClass("active");
			}
		});

		//check password
		$("#password").on("focusout", function(e) {
			if ($(this).val() != $("#confermaPassword").val()) {
				$("#confermaPassword").removeClass("valid").addClass("invalid");
			} else {
				$("#confermaPassword").removeClass("invalid").addClass("valid");
			}
		});

		$("#confermaPassword").on("keyup", function(e) {
			if ($("#password").val() != $(this).val()) {
				$(this).removeClass("valid").addClass("invalid");
			} else {
				$(this).removeClass("invalid").addClass("valid");
			}
		});
		
		/* Controlla se la provincia della sede legale dell'azienda selezionata è fuori regione Lombardia :
			 - se è fuori Regione Lombardia si devono far comparire i campi 'Codice Ruop' e 'Data iscrizione Ruop' obbligatori
		*/
		function controllaProvinciaSedeLegale(){
			console.log('BEGIN controllaProvinciaSedeLegale')
			var idProvinciaSel = $("#idProvinciaSedeSociale").val();
			console.log('idProvinciaSel ='+idProvinciaSel);			
			if(idProvinciaSel !== undefined && idProvinciaSel != null && idProvinciaSel != 'null' && idProvinciaSel != ''){
				$.ajax({
					url : '<spring:url value="/registrazione/isProvFuoriRegLombardia_"/>' + idProvinciaSel,
					async : false,
					success : function(response) {
						console.log('-response ='+response);
						if(response == 'S'){
						  console.log('Far comparire i campi legati al codice ruop')
						  $("#datiCodiceRuop").show();						
						}
						else{
						  $("#datiCodiceRuop").hide();
						}
					}
				});
			}
			else{
			  console.log('non e stata sel la provincia, nascondo i campi');	
			  $("#datiCodiceRuop").hide();	
			}
		}
		
	</script></content>
</body>
</html>