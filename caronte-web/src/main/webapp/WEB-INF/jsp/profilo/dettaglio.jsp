<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Benvenuto in Caronte</title>
</head>
<body>
	<spring:message code="label.welcome" />
	${nomeUtente}!!!
	
	<br/>
	<hr/>
	
	<a href='<spring:url value="/test/read"/>' target="_blank">test permessi READ</a><br/>
	<a href='<spring:url value="/test/write"/>' target="_blank">test permessi WRITE</a><br/>
	<a href='<spring:url value="/test/admin"/>' target="_blank">test gruppo ADMIN</a><br/>
	
	<br/>
	<hr/>
	
	<form name='f' action="logout" method='POST'>
		<table>
			<tr>
				<td><input name="submit" type="submit" value="logout" /></td>
			</tr>
		</table>
	</form>
	
</body>
</html>