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
	<spring:url value="/admin/generi/nuovo" var="formAction" />
	<form:form action="${formAction}" method="post"
		modelAttribute="nuovoGenereForm" accept-charset="utf-8">
		<div class="card">
			<div class="card-content">
				<span class="card-title">Dati genere</span>
				<div class="col s12" id="nuovoGenere">

					<div class="row">
						<div class="row">

							<!------ INPUT::CODICE -------->
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="codice" path="codGenere" />
								<form:errors path="codGenere" cssClass="error" />
								<label for="codGenere">Codice *</label>
							</div>

							<!------ INPUT::NOME COMPLETO -------->
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="denomGenere" path="denomGenere" />
								<form:errors path="denomGenere" cssClass="error" />
								<label for="denomGenere">Nome completo *</label>
							</div>
							
							<!------ INPUT::NOME LINGUA -------->
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="denomGenereLocale" path="denomGenereLocale" />
								<form:errors path="denomGenereLocale" cssClass="error" />
								<label for="denomGenereLocale">Nome italiano</label>
							</div>
							
							<!------ INPUT::NOME COMMERCIALE -------->
							<div class="input-field col s6 m4 l3">
								<form:input type="text" id="denomGenereCommerciale"
									path="denomGenereCommerciale" />
								<form:errors path="denomGenereCommerciale" cssClass="error" />
								<label for="denomGenereCommerciale">Nome commerciale</label>
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

							<!------ AUTOCOMPLETE::AUTORE -------->
							<div class="input-field col s6 m4 l3">
								<input type="text" id="descAutore" name="descAutoreEppo" data-error="#errorTxt3"
								  class="autocomplete" autocomplete="off">
                <label class="active" for="descAutore">Autore</label>
                <div id="errorTxt3" style="height:0px"></div>
								<form:input type="hidden" id="idAutoreEppo" path="idAutoreEppo" data-error="#errorTxt3" />
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="card-action">
				<div class="row">
					<div class="col s6">
						<a href='<spring:url value="/admin/generi/elenco"/>' class="btn waves-effect waves-light">ANNULLA</a>
					</div>
					<div class="col s6 right-align">
						<button class="btn confirm waves-effect waves-light" type="submit"
							name="datiNuovoGenere">SALVA</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>

	<content tag="script"> 
		<script type="text/javascript">
			$(document).ready(function(){
				
				/*
				§ Autocomplete Autore Eppo §
				*/
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