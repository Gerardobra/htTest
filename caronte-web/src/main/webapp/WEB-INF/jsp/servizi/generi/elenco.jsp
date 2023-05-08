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
<head>
<style>
.menu-detail>a {
	color: #00b0ff !important;
}

.menu-detail>a:hover {
	background: #00b0ff !important;
	color: #ffffff !important;
}

.menu-edit>a {
	color: #00bfa5 !important;
}

.menu-edit>a:hover {
	background: #00bfa5 !important;
	color: #fff !important;
}

.menu-copy>a {
	color: #ffd600 !important;
}

.menu-copy>a:hover {
	background: #ffd600 !important;
	color: #ffffff !important;
}

.menu-delete>a {
	color: #ff3d00 !important;
}

.menu-delete>a:hover {
	background: #ff3d00 !important;
	color: #ffffff !important;
}

.menu-print>a {
	color: #bdbdbd !important;
}

.menu-print>a:hover {
	background: #bdbdbd !important;
	color: #ffffff !important;
}
</style>
</head>
<body>
	<div class="row">
		<div class="nav-content">
			<ul class="tabs">
				<li class="tab col s2"><a class="active" href="#"><strong>Generi</strong></a></li>
				<li class="tab col s2"><a target="_self"
					href="<spring:url value='/admin/specie/elenco'/>"><strong>Specie</strong></a></li>
			</ul>
		</div>
		<div class="card col s12">
			<spring:url value="/admin/generi/elenco" var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaGeneriForm" accept-charset="utf-8">
				<div class="card-content col s12">
					<span class="card-title">Cerca genere</span>

					<!--****************** FORM DI RICERCA ***************-->
					<div class="row">

						<!------ INPUT::CODICE -------->
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="codice" path="codGenere" />
							<form:errors path="codGenere" cssClass="error" />
							<label for="codGenere">Codice</label>
						</div>

						<!------ INPUT::NOME COMPLETO -------->
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="denomGenere" path="denomGenere" />
							<form:errors path="denomGenere" cssClass="error" />
							<label for="denomGenere">Nome completo</label>
						</div>
						
						<!------ INPUT::NOME LOCALE -------->
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="denomGenereLocale"
								path="denomGenereLocale" />
							<form:errors path="denomGenereLocale" cssClass="error" />
							<label for="denomGenereLocale">Nome italiano</label>
						</div>

						<!------ DATEPICKER::DATA CREAZIONE -------->
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataInsert" path="dataInsert"
								cssClass="datepicker_birthdate" />
							<form:errors path="dataInsert" cssClass="error" />
							<label for="dataInsert">Data creazione</label>
						</div>

						<!------ SELECT::STATO -------->
						<div class="input-field col s6 m4 l3">
							<form:select id="attivo" path="attivo" cssClass="validate">
								<form:option value="" label="Tutti" />
								<form:option value="true" label="Attivo" />
								<form:option value="false" label="Non attivo" />
							</form:select>
							<form:errors path="attivo" cssClass="error" />
							<label for="attivo">Stato</label>
						</div>

					</div>
					<div class="card-action">
						<div class="row">
							<div class="col s6">
								<button type="button" class="btn clear-form waves-effect">PULISCI</button>
							</div>
							<div class="col s6 right-align">
								<button class="btn confirm waves-effect waves-light"
									type="submit" name="cerca">
									CERCA <i class="material-icons right">search</i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<sec:authorize access="hasRole('WRITE')">
		<div class="row">
			<a href='<spring:url value="/admin/generi/nuovo"/>'
				class="btn green waves-effect waves-light">NUOVO GENERE </a>
		</div>
	</sec:authorize>

	<c:choose>
		<c:when test="${not empty elencoGeneri}">
		  <spring:eval var="idUtentePorting" 
		    expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_UTENTE_PORTING" />
		  <spring:url value="/admin/generi" var="generiAction" />
			<div class="row">
				<div class="col-s12">
					<table id="tabellaGeneri" class="data-table striped display">
						<thead>
							<tr>
								<th>Codice</th>
								<th>Stato</th>
								<th>Da preferire</th>
								<th>Nome completo</th>
								<th>Nome italiano</th>
								<th>Data creazione</th>
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody id="bodyTableGeneri">
							<c:forEach var="genere" items="${elencoGeneri}">
								<tr>
									<td>${genere.codGenere}</td>
										<td>
									<c:choose>
										<c:when test="${genere.attivo}">
											Attivo
										</c:when>
										<c:otherwise>
											Non attivo
										</c:otherwise>
									</c:choose>
										</td>
									<td><c:choose>
										<c:when test="${genere.preferred}">
											Si
										</c:when>
										<c:otherwise>
											No
										</c:otherwise>
									</c:choose></td>
									<td>${genere.denomGenere}</td>
									<td>${genere.denomGenereLocale}</td>
									<fmt:formatDate value="${genere.dataInsert}" pattern="dd/MM/yyyy" var="dataInsert" />
									<td>${dataInsert}</td>
									<td nowrap style="white-spaces: nowrap">
									  <a class="dropdown-trigger" href="#!"
										  data-target="menu-dropdown${genere.idGenere}"
										  data-alignment='right' data-constrainwidth="false">
										  <i class="material-icons small">filter_list</i></a>
										<ul id="menu-dropdown${genere.idGenere}" class="dropdown-content ">
											<li class="menu-detail"><a href="<spring:url value="/admin/generi/dettaglio/{id}">
												<spring:param name="id" value="${genere.idGenere}"/>
											</spring:url>" title="Dettaglio">
													<i class="material-icons small">visibility </i>dettaglio
											</a></li>
											<li class="menu-edit"><a
												href="<spring:url value="/admin/generi/modifica/{id}">
												<spring:param name="id" value="${genere.idGenere}"/>
											</spring:url>"
												title="Modifica"> <i class="material-icons">mode_edit</i>modifica
											</a></li>
					            <c:if test="${idUtentePorting != genere.idUtenteInsert.longValue()}">
											<li class="menu-delete">
											  <a href="${generiAction}/elimina/${genere.idGenere}" title="Elimina" 
											    onclick="return checkElimina(this)">
													<i class="material-icons">delete</i>elimina
											  </a></li>
							        </c:if>
										</ul></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="valign-wrapper" style="justify-content: space-between">
				<span class="left" id="total_reg"></span>
				<ul class="pagination pager right" id="myPager"></ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="row">
				<div id="richiesteWarning" class="card card-alert orange lighten-5">
					<div class="card-content orange-text valign-wrapper center"
						style="">
						<i class="material-icons large" style="font-size: 36px">warning</i>
						<p>&nbsp;&nbsp;Non sono stati trovati generi</p>
					</div>
					<button type="button" class="close orange-text"
						data-dismiss="alert" aria-label="Chiudi">
						<span aria-hidden="true">×</span>
					</button>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<content tag="script">
  <c:set var="locale">${pageContext.response.locale}</c:set>
  <script> 
    $(document).ready(function() {

     /* $('#bodyTableGeneri').pageMe({
        pagerSelector : '#myPager',
        activeColor : '#ff5252',
        perPage : 15,
      });*/

    });
    
    function checkElimina(link) {
    	swal({
    		  title: 'Attenzione!',
    		  html:
    			    'Si desidera cancellare il genere <b>' + 
    			    $(link).closest('tr').find('td:nth-child(4)').text() + '</b>?',
    		  type: 'warning',
    		  showCancelButton: true,
    		  confirmButtonText: 'Sì',
    		  cancelButtonText: 'No',
    	}).then(function (result) {
    		  if (result.value) {
    			    window.location.href = $(link).attr('href');
    		  }
    	});
    		
    	return false;
    } 
	 </script>
	</content>

</body>
</html>