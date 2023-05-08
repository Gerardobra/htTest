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

li>img {
	display: none;
	display: hidden;
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
	<div class="card row">
		<div class="card-content">
			<div class="card-title">Gestione ispettori - dati nuovo
				ispettore</div>
			<div class="col l12" id="modificaIspettore">
				<form:form action="${formAction}" method="post" id="myForm"
					modelAttribute="ispettoreForm" accept-charset="utf-8">
					<div class="row">
						<div class="card-content" style="padding-top: 2em;">
							<div class="card">
								<div class="card-content">
									<div class="row">
										<div class="input-field col s6 m4 l3">
											<form:input type="email" id="email" path="email"
												class="validate" onchange="checkIfUtenteExists();" />
											<form:errors path="email" cssClass="error" />
											<label for="email">Email *</label>
											<span class="helper-text" data-error="Indirizzo email non valido"
                        data-success=""></span>
										</div>
										<div class="input-field col s6 m4 l3">
											<form:select id="idRuolo" path="idRuolo" cssClass="validate">
												<form:option value="" label="Selezionare" />
												<form:options items="${tipiProfilo}" itemValue="idRuolo"
													itemLabel="denomRuolo" />
											</form:select>
											<form:errors path="idRuolo" cssClass="error" />
											<label for="idRuolo">Profilo *</label>
										</div>
										<div class="row col s6 m4 l12">
											<!-- 
											<div class=" col l4">
											  <label>
													<form:checkbox id="sezioneImport" path="sezioneImport" />
													<span>Import</span>
												</label>
											</div>
											-->
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneExport" path="sezioneExport" />
													<span>Export</span>
												</label>
											</div>
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneVivai" path="sezioneVivai" />
													<span>Comunicazione vegetali</span>
												</label>
											</div>
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneAutorizzazioni" path="sezioneAutorizzazioni" />
													<span>Autorizzazioni</span>
												</label>
											</div>
											<div class=" col l3">
											  <label>
													<form:checkbox id="sezioneControlli" path="sezioneControlli" />
													<span>Controlli</span>
												</label>
											</div>
											<div class="row">
												<form:errors path="sezioneImport" cssClass="error" />
											</div>
										</div>
									</div>

								</div>
							</div>

							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="cognome" path="cognome" />
								<form:errors path="cognome" cssClass="error" />
								<label for="cognome">Cognome *</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="nome" path="nome" />
								<form:errors path="nome" cssClass="error" />
								<label for="nome">Nome *</label>
							</div>
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="codiceFiscale" data-length="16"
									path="codiceFiscale" />
								<form:errors path="codiceFiscale" cssClass="error" />
								<label for="codiceFiscale">Codice Fiscale *</label>
							</div>
								<div class="input-field col s6 m4 l3">
							              <form:input type="text" name="denomSpedizioniere" placeholder="Selezionare" id="denomSpedizioniere" path="denomSpedizioniere"
							                class="validate autocomplete campiAz" autocomplete="off"/>
							              <form:errors path="denomSpedizioniere" cssClass="error" />
							              <label class="active">Operatore *</label>
							    	</div>
							<div class="row col l12">
							

								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="numeroTessera" path="numeroTessera" />
									<form:errors path="numeroTessera" cssClass="error" />
									<label for="numeroTessera">Numero Tessera *</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:select id="sesso" path="sesso" cssClass="validate">
										<form:option value="" label="Selezionare" />
										<form:option value="M" label="Maschio" />
										<form:option value="F" label="Femmina" />
									</form:select>
									<form:errors path="sesso" cssClass="error" />
									<label for="sesso">Sesso *</label>
								</div>

								<div class="input-field col s6 m4 l3">
									<!-- i class="material-icons md-18 prefix">today</i-->
									<form:input type='text' id="dataNascita" path="dataNascita"
										cssClass="datepicker_birthdate" required="required" />
									<form:errors path="dataNascita" cssClass="error" />
									<label for="dataNascita">Data di nascita *</label>
								</div>

								<div class="input-field col s6 m4 l3">
									<form:select id="idNazioneNascita" path="idNazioneNascita"
										onchange="checkIfITA();" cssClass="validate">
										<form:option value="" label="Selezionare" />
										<form:options items="${listaNazioni}" itemValue="idNazione"
											itemLabel="denomNazione" />
									</form:select>
									<form:errors path="idNazioneNascita" cssClass="error" />
									<label for="idNazioneNascita">Nazione di nascita</label>
								</div>

								<div id="cittaItaliana" class="input-field col s6 m4 l3">
									<form:input type="hidden" path="idComuneNascita"
										id="idComuneNascita" name="idComuneNascita" />
									<input type='text' id="denomComuneNascitaStr"
										class="autocomplete nascita" autocomplete="off" />
									<form:input type='hidden' id="denomComuneNascita"
										path="denomComuneNascita" cssClass="autocomplete" />
									<form:errors path="denomComuneNascita" cssClass="error" />
									<label for="denomComuneNascita">Comune di nascita:</label>
								</div>
								<div id="cittaStraniera" style="display: none;"
									class="input-field col s6 m4 l3">
									<form:input type='text' id="cittaNascita" path="cittaNascita"
										cssClass="autocomplete" />
									<form:errors path="cittaNascita" cssClass="error" />
									<label for="cittaNascita">Comune di nascita:</label>
								</div>

								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="titoloStudio" path="titoloStudio" />
									<form:errors path="titoloStudio" cssClass="error" />
									<label for="titoloStudio">Titolo di studio</label>
								</div>

								<div class="input-field col s6 m4 l3">
									<form:input type="hidden" path="idComuneUfficio"
										id="idComuneUfficio" name="idComuneUfficio" />
									<input type='text' id="denomComuneUfficioStr"
										class="autocomplete ufficio" autocomplete="off" />
									<form:input type="hidden" id="denomComuneUfficio"
										path="denomComuneUfficio" />
									<form:errors path="denomComuneUfficio" cssClass="error" />
									<label for="denomComuneUfficio">Comune ufficio</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="capUfficio" path="capUfficio" />
									<form:errors path="capUfficio" cssClass="error" />
									<label for="capUfficio">CAP Ufficio</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="indirizzoUfficio"
										path="indirizzoUfficio" />
									<form:errors path="indirizzoUfficio" cssClass="error" />
									<label for="indirizzoUfficio">Indirizzo ufficio (Es. Via Roma, 24)</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:textarea id="note" path="note" data-length="1000"
										cssClass="materialize-textarea" class="materialize-textarea" />
									<form:errors path="note" cssClass="error" />
									<label for="note">Note</label>
								</div>
							</div>
						</div>
					</div>

					<div class="card-action">

						<div class="row col s12 right-align">
							<button
								class="btn  confirm waves-effect waves-light offset-s1 right-align"
								type="submit" name="datiIspettore">SALVA</button>

							<a href='<spring:url value="/admin/utenti/ispettori/elenco"/>'
								class="btn waves-effect waves-light col">ANNULLA</a>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<br />
	<content tag="script"> 
	<script type="text/javascript" src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script>
	<script>


	// Al caricamento della pagina
	$(document).ready(function() {			
		// Reperimento dei generi e popolazione Specie	
	
        $('input[name=denomSpedizioniere]').each(function() {
        	let $self = $(this);
        	$self.autocomplete({
        		limit: 50,
        		minLength: 2	        		
        	});
        	
        	$self.on("input", function () {
        		popolaListaDenomSpedizionieri($(this));
        	});	 
        });	
	});

	// Gestione combo Operatore
	function popolaListaDenomSpedizionieri($input) {
			$input.autocomplete("close");
			
			if ($input.val().length > 1 ) {
			
				var params = {
						  "denomSpedizioniere" : $input.val()
				};
				
				$.getJSON(
						'<spring:url value="/ajax/getListaDenomSpedizionieri" />',
						params,
						function(responseJSON) {
							$input.autocomplete("updateData", responseJSON);								
							$input.autocomplete("open");
						}
				).fail(function(jqxhr, textStatus,error) {
					  if (window.console) {
						  console.log("Errore in reperimento valore asincrono: " + textStatus + ", " + error);
		        }
				});					
			}
		}


	
		function checkIfITA() {
			if ($("#idNazioneNascita").val() == 1) {
				$("#cittaItaliana").show();
				$("#cittaStraniera").hide();
			} else {
				$("#cittaItaliana").hide();
				$("#cittaStraniera").show();
			}

		}

		$('#myForm').submit(
				function() {
					$("#denomComuneUfficio").val($("#denomComuneUfficioStr").val());
					$("#denomComuneNascita").val($("#denomComuneNascitaStr").val());

					if ($("#denomComuneNascitaStr").val() === undefined || $("#denomComuneNascitaStr").val() == null
							|| $("#denomComuneNascitaStr").val() == "") {
						$("#idComuneNascita").val("");
						$("#denomComuneNascita").val($("#denomComuneNascitaStr").val())
					}
					if ($("#denomComuneUfficioStr").val() === undefined || $("#denomComuneUfficioStr").val() == null
							|| $("#denomComuneUfficioStr").val() == "") {
						$("#idComuneUfficio").val("");
						$("#denomComuneUfficio").val($("#denomComuneUfficioStr").val())
					}
				});

		$(document).ready(function() {

			$("#abilitato").next().remove();
			$("#denomComuneUfficioStr").val($("#denomComuneUfficio").val());
			$("#denomComuneNascitaStr").val($("#denomComuneNascita").val());

			$(function() {

				$.ajax({
					url : '<spring:url value="/admin/utenti/ispettori/getListaComuni"/>',
					success : function(response) {
						var arr = $.parseJSON(response);

						var datas = {};
						for (var i = 0; i < arr.length; i++) {
							datas[arr[i].name] = arr[i].id; //countryArray[i].abc or null
						}

						$('input.autocomplete.nascita').autocomplete({
							data : datas,
							limit : 8, // The max amount of results that can be shown at once. Default: Infinity.
							onAutocomplete : function(val) {
								// Callback function when value is autcompleted.
								$("#idComuneNascita").val(datas[val]);
								$("#denomComuneNascitaStr").val(val);

							},
							minLength : 1
						});

						$('input.autocomplete.ufficio').autocomplete({
							data : datas,
							limit : 8, // The max amount of results that can be shown at once. Default: Infinity.
							onAutocomplete : function(val) {
								// Callback function when value is autcompleted.
								$("#idComuneUfficio").val(datas[val]);
								$("#denomComuneUfficioStr").val(val);

							},
							minLength : 1
						});
					}
				});

			});
			checkIfITA();

		})

		function checkIfUtenteExists() {

			var email = $("#email").val();
			if (email !== undefined && email != null) {

				email
				$.ajax({
					url : '<spring:url value="./checkIfUtenteExists_"/>' + email.replace(/\./g, "&&&"),
					success : function(response) {

						if (response.indexOf("NOTEXISTS") == -1) {
							var splittedStr = response.split("&&&");
							var nome = splittedStr[0];
							var cognome = splittedStr[1];
							var codiceFiscale = splittedStr[2];
							var idRuolo = splittedStr[3];
							var idSpedizioniere = splittedStr[4];
							var sezioneImport = splittedStr[5];
							var sezioneExport = splittedStr[6];

							if (nome!="null" && nome != "") {
								$("#nome").val(nome);
								$("label[for='nome']").addClass("active");
							}
							if (cognome!="null" && cognome != "") {
								$("#cognome").val(cognome);
								$("label[for='cognome']").addClass("active");
							}
							if (codiceFiscale!="null" && codiceFiscale != "") {
								$("#codiceFiscale").val(codiceFiscale);
								$("label[for='codiceFiscale']").addClass("active");
							}

							$("#idRuolo").val(idRuolo);
							$("#idRuolo").formSelect();
							$("#idSpedizioniere").val(idSpedizioniere);
							$("#idSpedizioniere").formSelect();
							
							$("#sezioneImport").attr("checked", sezioneImport == "true");
							$("#sezioneExport").attr("checked", sezioneExport == "true");
							
						} else {
							$("#nome").removeAttr("readonly");
							$("#cognome").removeAttr("readonly");
							$("#codiceFiscale").removeAttr("readonly");
							$("#numeroTessera").attr("readonly");
							$("#idRuolo").removeAttr("readonly");

							//abilito le select
							$(".select-dropdown").removeAttr("disabled");
							$("select").removeAttr("readonly");
						}
					}
				});
			}
		}
	</script></content>
</body>
</html>