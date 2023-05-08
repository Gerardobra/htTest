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
			<div class="card-title">Gestione profili - dati nuova utenza</div>
			<div class="col l12" id="modificaProfilo">
				<form:form action="${formAction}" method="post"
					modelAttribute="utenteForm" accept-charset="utf-8">
					<div class="row">
						<div class="card-content" style="padding-top: 2em;">
							<div class="card col s12 m12">
								<div class="card-content">
									<div class="row">
										<div class="col s12 m12">
											<div class="input-field col s6 m4 l3">
												<!-- i class="material-icons md-36 prefix">view_list</i-->
												<form:select id="ruolo" path="idRuolo" cssClass="validate">
													<form:option value="" label="Selezionare" />
													<form:options items="${tipiProfilo}" itemValue="idRuolo"
														itemLabel="denomRuolo" />
												</form:select>
												<form:errors path="idRuolo" cssClass="error" />
												<label for="ruolo">Profilo</label>
											</div>
										
										</div>
									</div>
								</div>
							</div>
							<div class="row">
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
									<form:input type="text" id="codiceFiscale" path="codiceFiscale"
										data-length="16" />
									<form:errors path="codiceFiscale" cssClass="error" />
									<label for="codiceFiscale">Codice Fiscale *</label>
								</div>
								<div class="input-field col s6 m4 l3">
							              <form:input type="text" name="denomSpedizioniere" placeholder="Selezionare" id="denomSpedizioniere" path="denomSpedizioniere"
							                class="validate autocomplete campiAz" autocomplete="off"/>
							              <form:errors path="denomSpedizioniere" cssClass="error" />
							              <label class="active">Operatore principale *</label>
							    </div>   
							</div>
							<div class="row">
								<div class="input-field col s6 m4 l3">
									<form:input type="email" id="email" path="email"
										class="validate" />
									<form:errors path="email" cssClass="error" />
									<label for="email">Email *</label>
									<span class="helper-text" data-error="Indirizzo email non valido"
                    data-success=""></span>
								</div>

								<div class="input-field col s6 m4 l4">
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
								type="submit" name="datiUtente">SALVA</button>

							<a href='<spring:url value="/admin/utenti/profili/elenco"/>'
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


		
		</script>
	</content>
</body>
</html>