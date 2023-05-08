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
<body>
	<spring:url value="/admin/specie/nuovo" var="formAction" />
	<form:form action="${formAction}" method="post"
		modelAttribute="nuovaSpecieForm" accept-charset="utf-8">
		<div class="card">
			<div class="card-content">
				<span class="card-title">Dati specie</span>
				<div class="col s12" id="nuovoSpecie">
						<div class="row">
							
							<!------ INPUT::CODICE -------->
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="codice" path="codSpecie" required="required" maxlength="10" />
								<form:errors path="codSpecie" cssClass="error" />
								<label for="codSpecie">Codice *</label>
							</div>

							<!------ INPUT::NOME COMPLETO -------->
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="denomSpecie" path="denomSpecie" required="required" maxlength="50" />
								<form:errors path="denomSpecie" cssClass="error" />
								<label for="denomSpecie">Nome completo *</label>
							</div>
							
							<!------ INPUT::NOME LINGUA -------->
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="denomSpecieLocale" path="denomSpecieLocale" maxlength="200" />
								<form:errors path="denomSpecieLocale" cssClass="error" />
								<label for="denomSpecieLocale">Nome italiano</label>
							</div>
							
							<!------ INPUT::NOME COMMERCIALE -------->
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="denomSpecieCommerciale"
									path="denomSpecieCommerciale" maxlength="200" />
								<form:errors path="denomSpecieCommerciale" cssClass="error" />
								<label for="denomSpecieCommerciale">Nome commerciale</label>
							</div>
						
							<!------ INPUT::GENERE -------->
							<div class="input-field col s6 m4 l3">
                <form:input type="text" id="denomGenere" path="denomGenere" placeholder="Selezionare" 
                  class="autocomplete" autocomplete="off" 
                  required="required" maxlength="200" />
								<label class="active" for="denomGenere">Genere *</label>
								<form:errors path="denomGenere" cssClass="error" />
								<form:input type="hidden" id="idGenere" name="idGenere" path="idGenere" />
							</div>

							<!------ DATEPICKER::DATA CREAZIONE -------->
							<div class="input-field col s6 m4 l3">
								<form:input type='text' id="dataInsert" path="dataInsert"
									cssClass="datepicker_birthdate" />
								<form:errors path="dataInsert" cssClass="error" />
								<label for="dataInsert">Data creazione *</label>
							</div>

							<!------ SELECT::NAZIONE -------->
							<div class="input-field col s6 m4 l3">
								<form:select id="nazione" path="idNazione" cssClass="validate">
									<form:option value="" label="Selezionare" />
									<form:options items="${elencoNazioni}" itemValue="idNazione"
									itemLabel="denomNazione" />
								</form:select>
								<form:errors path="idNazione" cssClass="error" />
								<label for="idNazione">Nazione</label>
							</div>

							<!------ SELECT::AUTORE -------->
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="descAutore" path="descAutoreEppo" 
								  placeholder="Selezionare" class="autocomplete"
                  autocomplete="off" maxlength="150" />
                <label class="active" for="descAutore">Autore</label>
                <form:input type="hidden" id="idAutoreEppo" name="idAutoreEppo" path="idAutoreEppo" />
							</div>
							 
					</div>

				</div>
			</div>
			<div class="card-action">
				<div class="row">
					<div class="col s6">
							<a href='<spring:url value="/admin/specie/elenco"/>' class="btn waves-effect waves-light">ANNULLA</a>
					</div>
					<div class="col s6 right-align">
						<button class="btn confirm waves-effect waves-light" type="submit"
							name="datiNuovaSpecie">SALVA</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
		
	<content tag="script"> 
		<script type="text/javascript">
			$(document).ready(function(){
				
				/*
				§ Autocomplete Genere §
				*/
				$('#denomGenere').on('input', function(event) {
          popolaListaGeneri($(this));
          $('#idGenere').val('0');
        });
        
        $('#denomGenere').autocomplete({
          limit: 50,
          minLength: 2,
          onAutocomplete: function(value) {
            caricaSpecieGenere();
          }
        });
        
        $('#descAutore').on('input', function(event) {
          popolaListaAutori($(this));
          $('#idAutoreEppo').val('0');
        });
              
        $('#descAutore').autocomplete({
          limit: 50,
          minLength: 2,
          onAutocomplete: function(value) {
            caricaIdAutore();
          }
        });
				
			});
			
			function popolaListaGeneri($input) {
        $input.autocomplete("close");
        
        if ($input.val().length > 1) {
          var params = {
                "nomeGenere" : $input.val()
          };
          
          $.getJSON(
              '<spring:url value="/ajax/getListaGeneri" />',
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

      function caricaSpecieGenere() {
        var params = {
          "denomGenere" : $("#denomGenere").val()
        }

        setValoreAsincrono(
          'idGenere',
          '<spring:url value="/ajax/getDatiDenomGenere" />',
          params,
          toStringIdGenere);
      }
      
      function toStringIdGenere(genere) {
          return genere.idGenere;
      }
      
      function popolaListaAutori($input) {
        $input.autocomplete("close");
        
        if ($input.val().length > 1) {
          var params = {
              "nomeAutore" : $input.val()
          };
          
          $.getJSON(
              '<spring:url value="/ajax/getListaAutoriEppo" />',
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

      function caricaIdAutore() {
        var params = {
          "nomeAutore" : $("#descAutore").val()
        }

        setValoreAsincrono(
          'idAutoreEppo',
          '<spring:url value="/ajax/getDatiDenomAutoreEppo" />',
          params,
          toStringIdAutore);
      }
      
      function toStringIdAutore(autore) {
    	  return autore.idAutoreEppo;
      }
		</script>
		</content>
</body>

