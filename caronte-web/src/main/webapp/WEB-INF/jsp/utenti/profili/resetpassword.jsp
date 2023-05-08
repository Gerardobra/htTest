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
			<div class="card-title">Gestione profilo - Reset password</div>
			<div class="col l12" id="nuovoProfilo">
				<form:form action="${formAction}" method="post" modelAttribute="utenteForm" accept-charset="utf-8">
				   <input type="hidden" id="passwordHidden" name="passwordHidden" value="${utenteForm.password}"/>
					<div class="col l12">
						<div class="col l12 card-content">
							<div class="row col l12">
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="cognome" path="cognome" disabled="true"/>									
									<label for="cognome">Cognome</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="nome" path="nome" disabled="true" />
									<form:errors path="nome" cssClass="error" />
									<label for="nome">Nome</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="codiceFiscale" path="codiceFiscale" disabled="true"/>
									<label for="codiceFiscale">Codice Fiscale</label>
								</div>
								<div class="input-field col s6 m4 l3">
									<form:input type="text" id="denomSpedizioniere" path="denomSpedizioniere" disabled="true"/>
									<label for="denomSpedizioniere">Spedizioniere di appartenenza</label>
								</div>							
							</div>							
							<div class="input-field col s6 m4 l3">
									<form:input type="email" id="email" path="email" disabled="true"/>									
									<label for="email">Email</label>									
							</div>	
							<div class="input-field col s6 m4 l3" id="divPassword" style="display:none">
									<!--<form:input type="text" id="password" path="password" disabled="true"/>-->									
									<label for="password">Password generata : ${utenteForm.password}</label>									
							</div>
							<div class="card-action" style="padding-bottom: 2em; display:none" id="divCopia" >
										<a
									href='#'
									title="Copia password"
									onclick="return copia(event)"
									class="btn confirm waves-effect light-blue lighten-3 offset-s1 left-align">
									<i class="material-icons">content_copy</i>
									</a>					
							</div>											
						</div>											
						<div class="card-action" style="padding-bottom: 2em;">
							<div class="row col s12 right-align">
								<button
									class="btn confirm waves-effect waves-light offset-s1 right-align" type="submit" name="generaNuovaPassword" onclick="showDiv()">RESET PASSWORD</button>
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

	
	  function copia() {
		console.log('copia');
		var input = document.createElement('input');
		var area = document.getElementById("passwordHidden").value;
		input.setAttribute('value', area);
		document.body.appendChild(input);
		input.select();
		var risultato = document.execCommand('copy');
		document.body.removeChild(input);
		console.log('testo copiato: '+ area);
		return risultato;
	 }
	  
	  function showDiv(){
		  console.log('showDiv');
		  $('#divPassword').show();
		  $('#divCopia').show();
	  }
	  
	  
	  // Al caricamento della pagina
	  $(document).ready(function() {
		  // se la password è valorizzata, visualizzare i div
		  var pwd = document.getElementById("passwordHidden").value;
		  console.log('pwd ='+pwd);
		  if(pwd != null && pwd != ''){
			  console.log('visualizzare i div');
			  $('#divPassword').show();
			  $('#divCopia').show();
		  }
	    })
		
	    
	    
	
		
	</script> </content>
</body>
</html>