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
				<div id="reportElencoControlli" class="card small hoverable">
					<div class="card-header bg-primary-color white-text linked-div">
						<h4 class="card-title text-shadow uppercase-title title-padding"><b>Elenco controlli</b></h4><br><br><br>
						<a href='<spring:url value="/controlli/report/elencoControlli"/>'><span></span></a>
					</div>
					<div class="card-content">
						<p>Estrazione elenco controlli</p>
					</div>
					<div class="card-action">
						<a href='<spring:url value="/vivai/report/elencoControlli"/>'><i class="material-icons left">add_circle</i>Seleziona</a>
					</div>
				</div>
			</div>
			
			<div class="col s6 m4 l3">
				<div id="reportElencoCampioni" class="card small hoverable">
					<div class="card-header bg-primary-color white-text linked-div">
						<h4 class="card-title text-shadow uppercase-title title-padding"><b>Elenco campioni</b></h4><br><br><br>
						<a href='<spring:url value="/controlli/report/elencoCampioni"/>'><span></span></a>
					</div>
					<div class="card-content">
						<p>Estrazione elenco dei campioni</p>
					</div>
					<div class="card-action">
						<a href='<spring:url value="/controlli/report/elencoCampioni"/>'><i class="material-icons left">add_circle</i>Seleziona</a>
					</div>
				</div>
			</div>	
			
			
			<div class="col s6 m4 l3">
				<div id="reportElencoMisure" class="card small hoverable">
					<div class="card-header bg-primary-color white-text linked-div">
						<h4 class="card-title text-shadow uppercase-title title-padding"><b>Elenco misure ufficiali</b><br></h4><br>
						<a href='<spring:url value="/controlli/report/elencoMisure"/>'><span></span></a>
					</div>
					<div class="card-content">
						<p>Estrazione elenco delle misure</p>
					</div>
					<div class="card-action">
						<a href='<spring:url value="/controlli/report/elencoMisure"/>'><i class="material-icons left">add_circle</i>Seleziona</a>
					</div>
				</div>
			</div>
			<c:if test="${false}">
			<div class="col s6 m4 l3">
				<div id="reportElencoMonitoraggi" class="card small hoverable">
					<div class="card-header bg-primary-color white-text linked-div">
						<h4 class="card-title text-shadow uppercase-title title-padding"><b>Elenco monitoraggi coofinanziati</b></h4>
						<a href='<spring:url value="/controlli/report/elencoMonitoraggi"/>'><span></span></a>
					</div>
					<div class="card-content">
						<p>Estrazione elenco dei monitoraggi</p>
					</div>
					<div class="card-action">
						<a href='<spring:url value="/controlli/report/elencoMonitoraggi"/>'><i class="material-icons left">add_circle</i>Seleziona</a>
					</div>
				</div>
			</div>	
			</c:if>
			
						
	    </div>
	</div>

	<div class="col s12 left-align">
		<a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA
			ALLA RICERCA</a>
	</div>
	<br />

</body>
</html>