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
			<span class="card-title">Dettaglio specie</span>
			<div class="col s12" id="nuovoSpecie">

				<div class="row">
					<div class="row">

						<!------ INPUT::CODICE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="codice"
								value="${fn:escapeXml(dettaglioSpecie.codSpecie)}"
								disabled="disabled" /> <label for="codSpecie">Codice*</label>
						</div>

						<!------ INPUT::NOME COMPLETO -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="denomSpecie"
								value="${fn:escapeXml(dettaglioSpecie.denomSpecie)}"
								disabled="disabled" /> <label for="denomSpecie">Nome
								completo*</label>
						</div>

						<!------ INPUT::NOME LINGUA -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="denomSpecieLocale"
								value="${fn:escapeXml(dettaglioSpecie.denomSpecieLocale)}"
								disabled="disabled" /> <label for="denomSpecieLocale">Nome
								italiano</label>
						</div>

						<!------ INPUT::NOME COMMERCIALE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="denomSpecieCommerciale"
								value="${fn:escapeXml(dettaglioSpecie.denomSpecieCommerciale)}"
								disabled="disabled" /> <label for="denomSpecieCommerciale">Nome
								commerciale</label>
						</div>

						<!------ INPUT::GENERE -------->
						<div class="input-field col s6 m4 l3">
							<input type="text" id="denomGenere" name="denomGenere"
								value="${fn:escapeXml(dettaglioSpecie.denomGenere)}"
								disabled="disabled" /> <label class="active" for="denomGenere">Genere
								*</label>
						</div>
						<!------ DATEPICKER::DATA CREAZIONE -------->
						<div class="input-field col s6 m4 l3">
							<fmt:formatDate value="${dettaglioSpecie.dataInsert}"
								pattern="dd/MM/yyyy" var="dataIns" />
							<input type='text' id="dataInsert" value="${dataIns}" disabled="disabled" /> 
							<label for="dataInsert">Data creazione*</label>
						</div>

						<!------ SELECT::NAZIONE -------->
						<div class="input-field col s6 m4 l3">
							<input type='text' id="idNazione"
								value="${fn:escapeXml(dettaglioSpecie.denomNazione)}"
								disabled="disabled" /> <label for="idNazione">Nazione</label>
						</div>

						<!------ SELECT::AUTORE -------->
						<div class="input-field col s6 m4 l3">
							<input type='text' id="descAutore"
								value="${fn:escapeXml(dettaglioSpecie.descAutoreEppo)}"
								disabled="disabled" /> <label class="active" for="descAutore">Autore</label>
						</div>


						<!------ SELECT::STATO -------->
						<div class="input-field col s6 m4 l3">
							<c:choose>
								<c:when test="${dettaglioSpecie.attivo}">
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
						<a href='<spring:url value="/admin/specie/elenco"/>'
							class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>