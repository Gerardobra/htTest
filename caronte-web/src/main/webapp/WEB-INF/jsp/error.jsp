<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="title.error" /></title>
</head>
<body>
<h1><spring:message code="title.error" /></h1>
<p><spring:message code="error.generic" /></p>
<!--
    Failed URL: ${url}
    Exception:  ${exception.message}
    <c:forEach items="${exception.stackTrace}" var="ste">    
      ${ste} 
    </c:forEach>
-->
</body>
</html>