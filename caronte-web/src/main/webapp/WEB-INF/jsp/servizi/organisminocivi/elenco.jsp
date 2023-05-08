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
	
		<div class="card col s12">			
			<spring:url value="/admin/organisminocivi/elenco" var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaOrganismiNociviForm" accept-charset="utf-8">
				<div class="card-content col s12">
					<span class="card-title"> Cerca organismo nocivo</span>

					<!--****************** FORM DI RICERCA ***************-->
					<div class="row">

					
						
						<!------ SELECT::STATO -------->
						<div class="input-field col s6 m4 l3">
							<form:select id="attivo" path="attivo" cssClass="validate">
								<form:option value="" label="Tutti" />
								<form:option value="true" label="ATTIVO" />
								<form:option value="false" label="NON ATTIVO" />
							</form:select>
							<form:errors path="attivo" cssClass="error" />
							<label for="attivo">Stato</label>
						</div>
						
						<div class="input-field col s6 m4 l3">
			              <form:input type="text" id="codiceEppo" path="codiceEppo" maxlength="20"/>
			              <form:errors path="codiceEppo" cssClass="error" />
			              <label for="codiceEppo">Codice Eppo</label>
			            </div>
			            
			            <div class="input-field col s6 m4 l6">
			              <form:input type="text" id="descOn" path="descOn" maxlength="40"/>
			              <form:errors path="descOn" cssClass="error" />
			              <label for="descOn">Organismo Nocivo</label>
			            </div>
			            
			   
	
						<!------ SELECT::ORGANISMO NOCIVO -------->
						<!-- div class="input-field col s6 m4 l9">		vecchia select					
							<form:select id="idOrgNocivo" path="idOrgNocivo" cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${selectOrganismiNocivi}" itemValue="idOrgNocivo"
									itemLabel="descBreve" />
							</form:select>
							<form:errors path="idOrgNocivo" cssClass="error" />
							<label for="idOrgNocivo">Organismo Nocivo</label>
						</div>-->
						
						
						
						<!------ SELECT::TIPO ORGANISMO NOCIVO ------
						<div class="input-field col s6 m4 l6">							
							<form:select id="idTipoOrgNocivo" path="idTipoOrgNocivo" cssClass="validate">
								<form:option value="" label="Selezionare" />
								<form:options items="${selectTipiON}" itemValue="idTipoOrgNocivo"
									itemLabel="descBreve" />
							</form:select>
							<form:errors path="idTipoOrgNocivo" cssClass="error" />
							<label for="idTipoOrgNocivo">Tipo organismo Nocivo</label>
						</div>-->
												

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
			<a href='<spring:url value="/admin/organisminocivi/nuovo"/>'
				class="btn green waves-effect waves-light">NUOVO ORGANISMO NOCIVO</a>
		</div>
	</sec:authorize>

	<c:choose>
		<c:when test="${not empty elencoON}">
		  
			<div class="row">
				<div class="col-s12">
					<table id="tabellaON" class="data-table striped display">
						<thead>
							<tr>
								<th>Attivo</th>								
								<th>Codice Eppo</th>			
								<th>Organismo Nocivo</th>											
								<th>Azioni</th>
							</tr>
						</thead>
						<tbody id="bodyTabellaON">
							<c:forEach var="ON" items="${elencoON}">
								<tr>
									<td>
										<c:if
						                    test="${ON.attivo}">
											<a
												class="btn-floating btn-flat btn-floating-medium light-green accent-4"
												style="cursor: default"></a>
										</c:if>
										<c:if
						                    test="${not ON.attivo}">
											<a
												class="btn-floating btn-flat btn-floating-medium red"
												style="cursor: default"></a>
										</c:if>
									</td>									
									<td>${ON.codiceEppo}</td>
									<td>${ON.descBreveON}</td>																				
									<td>
										<a
											href="<spring:url value="/admin/organisminocivi/modifica/idOrganismoNocivo/${ON.idOrganismoNocivo}"/>"
											title="Modifica"
											class="btn btn-floating btn-floating-medium light-green accent-3">
												<i class="material-icons">mode_edit</i>
										</a>
									</td>									
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
						<p>&nbsp;&nbsp;Non sono stati trovati organismi nocivi</p>
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

    /*  $('#bodyTabellaON').pageMe({
        pagerSelector : '#myPager',
        activeColor : '#ff5252',
        perPage : 15,
      });*/

    });
    

    
    	/*function confermaCancellazione(link) {
		   console.log('BEGIN confermaCancellazione');

		   var genere;
		   var specie;	
		   
				swal({
					title : 'Attenzione!',
					html : 'Si desidera eliminare questa riga di associazione?',
					type : 'warning',
					showCancelButton : true,
					confirmButtonText : 'Sì',
					cancelButtonText : 'No',
				}).then(function(result) {
					if (result.value) {
						window.location.href = $(link).attr('href');
					}
				});
				return false;
		}*/
    
	 </script>
	</content>

</body>
</html>