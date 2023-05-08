<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
		<div class="card-content col l12">
			<div class="card-title">Gestione profilo - Modifica utenza</div>
			<div class="col l12" id="nuovoProfilo">
				<form:form action="${formAction}" method="post"
					modelAttribute="utenteForm" accept-charset="utf-8">
					<div class="col l12">
						<div class="col l12 card-content">
							<div class="card col l12">
								<div class="card-content">
									<div class="row">
										<div class="col l12">
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
											<div class="col s6 m4 l3">
											  <label>
													<form:checkbox id="abilitato" path="abilitato" />
													<span>Abilitato</span>
												</label>
											</div>
											<div class="col s6 m4 l3">
											  <label>
													<form:checkbox id="rifiutato" path="rifiutato" />
													<span>Rifiutato</span>
												</label>
											</div>
											
											<div class="row col l12">
												<form:errors path="rifiutato" cssClass="error" />
											</div>
											
										    <div class="input-field col s6 m4 l3" id="motivoRifiutoContainer">
                								<form:textarea id="motivoRifiuto" path="motivoRifiuto" cssClass="materialize-textarea validate" rows="2"
                    								data-length="200" maxlength="200" />
                  								<form:errors path="motivoRifiuto" cssClass="error" />
                  								<label for="motivoRifiuto">Motivo Rifiuto *</label>
              								</div>
										
										</div>
									</div>
								</div>
							</div>

							<div class="row col l12">
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
								<c:if
									test="${utente.idTipoSpedizioniere!=1 && utente.idTipoSpedizioniere!=5}">
									<div class="input-field col s6 m4 l3">
							              <form:input type="text" name="denomSpedizioniere" placeholder="Selezionare" id="denomSpedizioniere" path="denomSpedizioniere"
							                class="validate autocomplete campiAz" autocomplete="off"/>
							              <form:errors path="denomSpedizioniere" cssClass="error" />
							              <label class="active">Operatore principale *</label>
							    	</div>
							    	<form:hidden path="idTipoSpedizioniere"
										name="idTipoSpedizioniere" id="idTipoSpedizioniere" />
								</c:if>
							</div>
							<div class="row col l12">
								<div class="input-field col s6 m4 l3">
									<form:input type="email" id="email" path="email"
										class="validate" />
									<form:errors path="email" cssClass="error" />
									<label for="email">Email *</label>
									<span class="helper-text" data-error="Indirizzo email non valido"
                    data-success=""></span>
								</div>
								<div class="input-field col s6 m4 l6">
									<form:textarea id="note" data-length="1000" path="note"
										cssClass="materialize-textarea" class="materialize-textarea" />
									<form:errors path="note" cssClass="error" />
									<label for="note">Note</label>
								</div>
							</div>
						</div>
						<div class="divider"></div>
						<div class="row">
							<div class="row">
								<!-- Modal Structure -->
								<div id="modalReimpostaPsw" class="modal">
									<div class="modal-content">
										<h4>Reimposta password</h4>
										<p>Con questa funzionalità verrà inviata una mail
											all'utente con le indicazioni da seguire per reimpostare la
											propria password.</p>
									</div>
									<div class="modal-footer">
										<a href="#!"
											class="modal-action modal-close waves-effect waves-red btn-flat ">Annulla</a>
										<a onclick="sendMail(${utente.idUtente});"
											class="modal-action modal-close waves-effect waves-green btn-flat">Conferma</a>
									</div>
								</div>
								<!-- Modal Trigger -->
								<c:if test="${utente.password!=null && utente.password!='' }">
									<a href="#modalReimpostaPsw" title="Reimposta password"
										class="btn modal-trigger green waves-effect waves-light">
										RIASSEGNA PASSWORD </a>
								</c:if>
							</div>
							<div class="row hide">
								<div class="col-s11">

									<table id="tabellaUtenti"
										class="responsive-table striped display">
										<thead>
											<tr>
												<th>Abilitato</th>
												<th>Profilo</th>
												<th>Cognome e Nome</th>
												<th>Codice Fiscale</th>
												<th>Spedizioniere di appartenenza</th>
												<th>Email</th>
												<th>Data inserimento</th>
												<th>Note</th>
											</tr>
										</thead>
										<tbody id="bodyTabellaUtenti">
											<tr>
												<td><c:choose>
														<c:when test="${utente.abilitato}">
															<a
																class="btn-floating btn-flat btn-floating-medium light-green"
																style="cursor: default"> <i class="material-icons">check</i></a>
														</c:when>
													</c:choose></td>
												<td>${utente.denomRuolo}</td>
												<td>${utente.cognome}&nbsp;${utente.nome}</td>
												<td>${utente.codiceFiscale}</td>
												<td>${utente.denomSpedizioniere}</td>
												<td>${utente.email}</td>
												<td><fmt:formatDate value="${utente.dataInserimento}"
														pattern="dd/MM/yyyy" /></td>
												<td>${utente.note}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>

						<div class="card-action" style="padding-bottom: 2em;">
							<div class="row col s12 right-align">
								<button
									class="btn confirm waves-effect waves-light offset-s1 right-align"
									type="submit" name="datiUtente">SALVA</button>
								<a href='<spring:url value="/admin/utenti/profili/elenco"/>'
									class="btn waves-effect waves-light col">ANNULLA</a>
							</div>
						</div>
					</div>
				</form:form>

			</div>
		</div>
	</div>
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


	
	function sendMail(idUtente){

		$
		.ajax({
			url : '<spring:url value="/admin/utenti/profili/inviaMailReimpostaPsw_"/>'+idUtente,
			success : function(response) {
				if(response="SUCCESS")
					$("#modal_"+idUtente).modal('close');
				else
					alert("Si è verificato un errore durante l'invio della mail.")

			}
		
		});
	}

	$(document).ready(function() {
		$("#rifiutato").click(function() {
			if($("#rifiutato").is(":checked")) {
				$("#motivoRifiutoContainer").fadeIn();
				$("#abilitato").prop("checked",false);
			} else {
				$("#motivoRifiutoContainer").fadeOut();
				$("#motivoRifiuto").val("");
			}
			
		});
		$("#abilitato").click(function() {
			$("#motivoRifiutoContainer").fadeOut();
			$("#rifiutato").prop("checked",false);
			$("#motivoRifiuto").val("");
		});

		if($("#rifiutato").is(":checked")) {
			$("#motivoRifiutoContainer").show();
		} else {
			$("#motivoRifiutoContainer").hide();
		}
	});
		
	</script> </content>
</body>
</html>