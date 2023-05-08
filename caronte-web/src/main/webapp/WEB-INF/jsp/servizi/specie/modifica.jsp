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
	<spring:url value="/admin/specie/modifica" var="formAction" />
	<form:form action="${formAction}" method="post"
		modelAttribute="nuovaSpecieForm" accept-charset="utf-8">
		<div class="card">
			<div class="card-content">
				<span class="card-title">Modifica dati specie</span>
				<div class="col s12" id="nuovoSpecie">

					<div class="row">
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
                <input type="text" id="denomGenere" name="denomGenere" 
                  value="${nuovaSpecieForm.denomGenere}" data-error="#errorTxt3"
									class="autocomplete" autocomplete="off" 
									required="required" maxlength="200" />
                <label class="active" for="denomGenere">Genere *</label>
                <div id="errorTxt3" style="height:0px"></div>
								<form:input type="hidden" id="idGenere" name="idGenere" data-error="#errorTxt3" path="idGenere"/>
							</div>

              <!------ LABEL::DATA CREAZIONE -------->
              <fmt:formatDate value="${nuovaSpecieForm.dataInsert}" pattern="dd/MM/yyyy" 
                var="dataCreazione" />
              <div class="input-field col s6 m4 l3">
                <input type="text" value="${dataCreazione}" id="dataCreazione" disabled="disabled" /> 
                <label for="dataCreazione">Data creazione</label>
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
							    data-error="#errorTxt3" placeholder="Selezionare" 
							    class="autocomplete" autocomplete="off" maxlength="150" />
							  <label class="active" for="descAutore">Autore</label>
                <div id="errorTxt3" style="height:0px"></div>
                <form:input type="hidden" id="idAutoreEppo" path="idAutoreEppo" data-error="#errorTxt3" />
							</div>
							
							<!------ SELECT::STATO -------->
							<div class="input-field col s6 m4 l3">
								<form:select id="attivo" path="attivo" cssClass="validate">
									<form:option value="" label="Tutti" />
									<form:option value="true" label="Attivo" />
									<form:option value="false" label="Non attivo" />
								</form:select>
								<form:errors path="attivo" cssClass="error" />
								<label for="attivo">Stato</label>
							</div>
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
			$(document).ready(function() {
				
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

