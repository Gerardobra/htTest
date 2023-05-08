<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="caronte" uri="/WEB-INF/caronte.tld"%>

<caronte:decorator titleText="Caronte - Controlli" materializeCss="materialize_controlli.css" 
  colorCode="#1e88e5" gradientColor="blue">
  <jsp:attribute name="navContent">
    <li class="tab col s2">
	    <c:choose>
	      <c:when test="${not fn:contains(requestScope['javax.servlet.forward.request_uri'], '/report/')}">
	      <a class="active" href='<spring:url value="/controlli/elenco"/>' target="_self">Controlli</a>
	      </c:when>
	      <c:otherwise>
	      <a href='<spring:url value="/controlli/elenco"/>' target="_self">Controlli</a>
	      </c:otherwise>
	    </c:choose>
    </li>
    <sec:authorize access="hasRole('ADMIN')">
    <li class="tab col s2">
	    <c:choose>
	      <c:when test="${fn:contains(requestScope['javax.servlet.forward.request_uri'], '/report/')}">
	      <a class="active" href='<spring:url value="/controlli/report/elenco"/>' target="_self">Report</a>
	      </c:when>
	      <c:otherwise>
	      <a href='<spring:url value="/controlli/report/elenco"/>' target="_self">Report</a>
	      </c:otherwise>
	    </c:choose>
    </li>
    </sec:authorize>
    <!-- li class="tab col s2">
      <c:choose>
        <c:when test="${caronte:hasUserRole('ADMIN')}">
          <spring:url value="/export/servizi/datiSian/elencoSian" var="urlServizi" />
        </c:when>
        <c:otherwise>
          <spring:url value="/export/servizi/documentazione/elencoDocumenti" var="urlServizi"/>
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
    </li-->
  </jsp:attribute>      
</caronte:decorator>