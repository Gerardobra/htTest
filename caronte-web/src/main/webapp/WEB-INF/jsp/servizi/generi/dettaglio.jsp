<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<body>
	<div class="card">
		<div class="card-content">
			<span class="card-title">Dati genere</span>
			<div class="col s12" id="nuovoGenere">

				<div class="row">
					<div class="row">

						<!------ INPUT::CODICE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="codice"
								value="${fn:escapeXml(dettaglioGenere.codGenere)}"
								disabled="disabled" /> <label for="codGenere">Codice*</label>
						</div>

						<!------ INPUT::NOME COMPLETO -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="denomGenere"
								value="${fn:escapeXml(dettaglioGenere.denomGenere)}"
								disabled="disabled" /> <label for="denomGenere">Nome
								completo*</label>
						</div>

						<!------ INPUT::NOME LINGUA -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="denomGenereLocale"
								value="${fn:escapeXml(dettaglioGenere.denomGenereLocale)}"
								disabled="disabled" /> <label for="denomGenereLocale">Nome
								italiano</label>
						</div>

						<!------ INPUT::NOME COMMERCIALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="denomGenereCommerciale"
								value="${fn:escapeXml(dettaglioGenere.denomGenereCommerciale)}"
								disabled="disabled" /> <label for="denomGenereCommerciale">Nome
								commerciale</label>
						</div>

						<!------ LABEL::DATA CREAZIONE -------->
						<div class="input-field col s6 m4 l3">
							<fmt:formatDate value="${dettaglioGenere.dataInsert}"
								pattern="dd/MM/yyyy" var="dataIns" />
							<input type="text" value="${dataIns}" id="dataInsert"
								disabled="disabled" /> <label for="dataInsert">Data
								creazione</label>
						</div>

						<!------ SELECT::NAZIONE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="denomNazione"
								value="${fn:escapeXml(dettaglioGenere.denomNazione)}"
								disabled="disabled" /> <label for="denomNazione">Nazione</label>

						</div>

						<!------ SELECT::AUTORE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="autore"
								value="${fn:escapeXml(dettaglioGenere.descAutoreEppo)}"
								autocomplete="on" disabled="disabled" /> <label
								for="descAutoreEppo">Autore</label>
						</div>

						<!------ SELECT::STATO -------->
						<div class="input-field col s6 m4 l3">
							<c:choose>
								<c:when test="${dettaglioGenere.attivo}">
									<input type='text' id="attivo" value="Attivo"
										disabled="disabled" />
								</c:when>
								<c:otherwise>
									<input type='text' id="attivo" value="Non attivo"
										disabled="disabled" />
								</c:otherwise>
							</c:choose>
							<label for="attivo">Stato</label>
						</div>
					</div>
				</div>

			</div>
			<div class="card-action">
				<div class="row">
					<div class="col s12 left-align">
						<a href='<spring:url value="/admin/generi/elenco"/>'
							class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>