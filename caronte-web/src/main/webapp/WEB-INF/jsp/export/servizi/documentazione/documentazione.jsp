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
	<c:if test="${fn:length(docList) > 0}">
		<div class="row">
			<div class="col s12">
				<div class="card">
					<div class="card-content">
						<span class="card-title" style="margin-bottom: 5%"><b>Documentazione</b></span>
						<div class="row">
							<c:forEach items="${docList}" var="document">
								<div class="col s6 m4">
									<div class="card">
										<div class="card center">
											<div class="card-image"
												style="width: 50%; height: 50%; margin-left: 25%">
												<c:choose>
													<c:when test="${fn:contains(document.nomeFile,'pdf')}">
														<img class="responsive-img"
															src='<spring:url value="/resources/img/pdf-page.png"/>'>
													</c:when>
													<c:when test="${fn:contains(document.nomeFile,'doc')}">
														<img class="responsive-img"
															src='<spring:url value="/resources/img/doc1.png"/>'>
													</c:when>
													<c:otherwise>
													  <img class="responsive-img"
                              src='<spring:url value="/resources/img/generic_document.png"/>'>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="card-content">
												<span class="card-title" style="color: black;">${document.nomeFile}</span>
											</div>
											<div class="card-action">
												<spring:url
													value="/import/servizi/documentazione/scarica/${document.idAllegato}"
													var="pathDocumento" />
												<a href="${pathDocumento}">Download</a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${fn:length(docList) == 0}">
		<div class="row">
			<div id="documentiWarning" class="card card-alert orange lighten-5">
				<div class="card-content orange-text valign-wrapper center" style="">
					<i class="material-icons large" style="font-size: 36px">warning</i>
					<p>&nbsp;&nbsp;Non ci sono documenti disponibili</p>
				</div>
				<button type="button" class="close orange-text" data-dismiss="alert"
					aria-label="Chiudi">
					<span aria-hidden="true">×</span>
				</button>
			</div>
		</div>
	</c:if>





</body>