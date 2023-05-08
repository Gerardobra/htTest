<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
</head>
<body>
	<div class="card">
    <div class="card-header bg-primary-color white-text">
      <h4 class="card-title text-shadow uppercase-title title-padding">
        <b>DETTAGLIO COMUNICAZIONE</b>
      </h4>
    </div>
    
    <sec:authorize access="hasRole('SUPERUSER')" var="isSuperUser" />
        
    <div class="card-content">
      <div class="row">
        <div class="input-field col s12 m6">
          <input type="text" id="statoComunicazione"
            value="${fn:escapeXml(dettaglioComunicazione.descStatoComunicazione)}" 
            disabled="disabled" /> 
          <label for="statoComunicazione">Stato comunicazione</label>
        </div>
	      <c:if test="${isSuperUser}">
	      <div class="input-field col s12 m6">
	        <input type="text" id="spedizioniere"
            value="${fn:escapeXml(dettaglioComunicazione.denomSpedizioniere)}" 
            disabled="disabled" /> 
          <label for="spedizioniere">Operatore</label>
	      </div>
	      </c:if>
	      
	      <div class="input-field col s12">
	        <input type="text" id="centroAziendale"
            value="${fn:escapeXml(dettaglioComunicazione.centroAziendale.nomeIndirizzo)}" 
            disabled="disabled" /> 
	        <label for="centroAziendale">Centro aziendale</label>
	      </div>
	    </div>
	    
	    <div class="row">
         <label>
	       <span>La comunicazione dei vegetali va effettuata una sola volta all’anno per ogni centro aziendale entro il 30 aprile, salvo diversa indicazione da parte del servizio fitosanitario. Devono inviare la comunicazione solo gli operatori professionali registrati al RUOP con tipologia attività: "</span><span style="font-weight:bold">Vivaismo</span><span>". Nella comunicazione vanno elencati tutti i vegetali presenti in vivaio o prodotti e già commercializzati alla data della comunicazione e i vegetali che si intendono produrre durante l'anno, successivamente alla data di comunicazione, sulla base di una stima di produzione. Nella comunicazione </span><span style="font-weight:bold">non vanno indicati </span><span>i vegetali acquistati da altri operatori e pronti per la vendita al consumatore finale e le superfici di tappeto erboso.</span>
         </label>
       </div>
      
      <div class="row">
        <table id="tabellaSpecie" class="data-table striped display" 
          data-orderable='false' data-paging="false" data-info="false">
          <thead>
            <tr>
              <th>Genere</th>
              <th>Specie</th>
              <th>Numero piante</th>
            </tr>
          </thead>
          <tbody id="bodyTabellaSpecie">          
            <c:forEach var="genere" items="${dettaglioComunicazione.listaGeneri}">
            <tr>
              <td>${fn:escapeXml(genere.denomGenere)}</td>
              <td>${fn:escapeXml(genere.denomSpecie)}</td>
              <td>${fn:escapeXml(genere.numeroPiante)}</td>
            </tr>
            </c:forEach> 
            <tr>
              <td></td>
              <td></td>
              <td></td>
            </tr>
          </tbody>
        </table>                      
      </div>
    
    </div>
    
	</div>

	<div class="col s12 left-align">
	  <a href='<spring:url value="/vivai/comunicazioni/elenco"/>'
      class="btn waves-effect waves-light">TORNA ALLA RICERCA</a>
	</div>
	<br />

</body>
</html>