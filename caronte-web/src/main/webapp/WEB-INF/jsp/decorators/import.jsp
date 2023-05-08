<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="caronte" uri="/WEB-INF/caronte.tld"%>

<caronte:decorator titleText="Caronte - Import" materializeCss="materialize_import.css" 
  colorCode="#0277bd" gradientColor="light-blue-cyan">
  <jsp:attribute name="navContent">
    <li class="tab col s2">
	    <c:choose>
	      <c:when test="${fn:contains(requestScope['javax.servlet.forward.request_uri'], '/richieste/')}">
	      <a class="active" href='<spring:url value="/import/richieste/elenco"/>' target="_self">Richieste</a>
	      </c:when>
	      <c:otherwise>
	      <a href='<spring:url value="/import/richieste/elenco"/>' target="_self">Richieste</a>
	      </c:otherwise>
	    </c:choose>
    </li>
    <sec:authorize access="hasRole('ADMIN')">
    <li class="tab col s2">
	    <c:choose>
	      <c:when test="${fn:contains(requestScope['javax.servlet.forward.request_uri'], '/report/')}">
	      <a class="active" href='<spring:url value="/import/report/elenco"/>' target="_self">Report</a>
	      </c:when>
	      <c:otherwise>
	      <a href='<spring:url value="/import/report/elenco"/>' target="_self">Report</a>
	      </c:otherwise>
	    </c:choose>
    </li>
    </sec:authorize>
    <li class="tab col s2">
      <c:choose>
	      <c:when test="${caronte:hasUserRole('ADMIN')}">
          <spring:url value="/import/servizi/datiSian/elencoSian" var="urlServizi" />
	      </c:when>
	      <c:otherwise>
	        <spring:url value="/import/servizi/documentazione/elencoDocumenti" var="urlServizi"/>
	      </c:otherwise>
      </c:choose>
      
	    <c:choose>
	      <c:when test="${fn:contains(requestScope['javax.servlet.forward.request_uri'], '/servizi/')}">
	      <a class="active" href='${urlServizi}' target="_self">Servizi</a>
	      </c:when>
	      <c:otherwise>
	      <a href='${urlServizi}' target="_self">Servizi</a>
	      </c:otherwise>
	    </c:choose>
    </li>
  </jsp:attribute>
</caronte:decorator>