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
	<spring:url value="/admin/generi/modifica" var="formAction" />
	<form:form action="${formAction}" method="post"
		modelAttribute="nuovoGenereForm" accept-charset="utf-8">
		<div class="card">
			<div class="card-content">
				<span class="card-title">Modifica dati genere</span>
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
								<form:input type="text" id="denomGenereLocale"
									path="denomGenereLocale" />
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

							<!------ LABEL::DATA CREAZIONE -------->
						  <fmt:formatDate value="${nuovoGenereForm.dataInsert}" pattern="dd/MM/yyyy" 
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
								<form:input type="text" id="autore" path="descAutoreEppo"
									autocomplete="on" />
								<form:errors path="descAutoreEppo" cssClass="error" />
								<label for="descAutoreEppo">Autore</label>
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
					<div class="col s6 ">
						<a href='<spring:url value="/admin/generi/elenco"/>'
							class="btn waves-effect waves-light">ANNULLA</a>
					</div>
					<div class="col s6 right-align">
						<button class="btn confirm waves-effect waves-light" type="submit"
							name="datiNuovoGenere">SALVA</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>

	<br />
</body>