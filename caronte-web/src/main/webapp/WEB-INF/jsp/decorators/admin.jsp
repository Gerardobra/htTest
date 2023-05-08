<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="caronte" uri="/WEB-INF/caronte.tld"%>
<caronte:decorator titleText="Caronte - Amministrazione" materializeCss="materialize_admin.css" 
  colorCode="#ff5252" gradientColor="red-darken">
  <jsp:attribute name="navContent">
    <li class="tab col s2">
      <c:choose>
        <c:when test="${fn:contains(requestScope['javax.servlet.forward.request_uri'], 'admin/utenti/')}">
        <a class="active" href='<spring:url value="/admin/utenti/spedizionieri/elenco"/>' target="_self">Profili, Ispettori e Operatori</a>
        </c:when>
        <c:otherwise>
        <a href='<spring:url value="/admin/utenti/spedizionieri/elenco"/>' target="_self">Profili, Ispettori e Operatori</a>
        </c:otherwise>
      </c:choose>
    </li>
    <li class="tab col s2">
      <c:choose>
        <c:when test="${fn:contains(requestScope['javax.servlet.forward.request_uri'], '/admin/generi/') or 
          fn:contains(requestScope['javax.servlet.forward.request_uri'], '/admin/specie/')}">
        <a class="active" href='<spring:url value="/admin/generi/elenco"/>' target="_self">Generi e specie</a>
        </c:when>
        <c:otherwise>
        <a href='<spring:url value="/admin/generi/elenco"/>' target="_self">Generi e specie</a>
        </c:otherwise>
      </c:choose>
    </li>
    <li class="tab col s2">
      <c:choose>
        <c:when test="${fn:contains(requestScope['javax.servlet.forward.request_uri'], 'admin/organisminocivi/')}">
        <a class="active" href='<spring:url value="/admin/organisminocivi/elenco"/>' target="_self">Organismi nocivi</a>
        </c:when>
        <c:otherwise>
        <a href='<spring:url value="/admin/organisminocivi/elenco"/>' target="_self">Organismi nocivi</a>
        </c:otherwise>
      </c:choose>
    </li>     
    <!-- <li class="tab col s2">
      <c:choose>
        <c:when test="${fn:contains(requestScope['javax.servlet.forward.request_uri'], '/admin/messaggistica/')}">
        <a class="active" href='<spring:url value="/admin/messaggistica/configura"/>' target="_self">Impostazioni</a>
        </c:when>
        <c:otherwise>
        <a href='<spring:url value="/admin/messaggistica/configura"/>' target="_self">Impostazioni</a>
        </c:otherwise>
      </c:choose>
    </li>-->
  </jsp:attribute>
</caronte:decorator>