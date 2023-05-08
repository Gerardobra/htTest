<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
</head>
<body>

	<div class="card">
		<div class="card-header bg-primary-color white-text">
			<h3 class="card-title text-shadow uppercase-title title-padding"><b>Report</b></h3>
		</div>
			
		<div class="card-content row">
			<div class="col s12">
				<div class="card-panel hoverable"><h6>Selezionare il report da eseguire.</h6></div>
			</div>				
			
			<div class="col s6 m4 l3">
				<div id="reportElencoSpecie" class="card small hoverable">
					<div class="card-header bg-primary-color white-text linked-div">
						<h4 class="card-title text-shadow uppercase-title title-padding"><b>Elenco Aziende</b></h4>
						<a href='<spring:url value="/autorizzazioni/report/elencoAziende"/>'><span></span></a>
					</div>
					<div class="card-content">
						<p>Estrazione elenco aziende che hanno richiesto l'autorizzazione RUOP</p>
					</div>
					<div class="card-action">
						<a href='<spring:url value="/autorizzazioni/report/elencoAziende"/>'><i class="material-icons left">add_circle</i>Seleziona</a>
					</div>
				</div>
			</div>
			
			<div class="col s6 m4 l3">
				<div id="reportElencoComunicazioni" class="card small hoverable">
					<div class="card-header bg-primary-color white-text linked-div">
						<h4 class="card-title text-shadow uppercase-title title-padding"><b>Elenco Centri Aziendali</b></h4>
						<a href='<spring:url value="/autorizzazioni/report/elencoCentriAziendali"/>'><span></span></a>
					</div>
					<div class="card-content">
						<p>Estrazione elenco Centri Aziendali che hanno richiesto l'autorizzazione RUOP</p>
					</div>
					<div class="card-action">
						<a href='<spring:url value="/autorizzazioni/report/elencoCentriAziendali"/>'><i class="material-icons left">add_circle</i>Seleziona</a>
					</div>
				</div>
			</div>				
	    </div>
	</div>

	<div class="col s12 left-align">
		<a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>' class="btn waves-effect waves-light">TORNA
			ALLA RICERCA</a>
	</div>
	<br />

</body>
</html>