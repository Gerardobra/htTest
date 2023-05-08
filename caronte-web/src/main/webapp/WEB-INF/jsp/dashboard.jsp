<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="caronte" uri="/WEB-INF/caronte.tld"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Homepage caronte</title>
<link rel="icon" type="image/png" href="<spring:url value="/resources/img/favicon.png"/>" />
<style>
#content {
	padding-left: 300px;
}

@media only screen and (max-width: 992px) {
	header, main, footer, #content {
		padding-left: 0;
	}
}

.user-icon {
	width: 100px;
	height: 100px;
}

.left-sidenav {
	top: 64px;
}

.profile-image {
	margin: auto;
}

#user-name, #user-role {
	color: white !important;
}

.slim-title {
	font-weight: 300 !important;
}

.user-info-gradient {
	background: #fd746c; /* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #ff9068, #fd746c);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #ff9068, #fd746c);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

ul#desktop-menu {
	z-index: 1000;
}

ul#dashboard-leftside-menu {
	z-index: 996;
}

ul.wrapper-valign-content {
  display: flex;
  flex-direction: column;
}

.block-valign-content {
  display: flex;
  flex-direction: column;
  -ms-flex-pack: center;
  justify-content: center;
}

@media all and (-ms-high-contrast: none), (-ms-high-contrast: active) {
  /* IE10+ CSS styles go here */
  .adjustIconIE {
    top:20px;
  }
}

ol-start {
  counter-reset: item 2
}
ol{
 counter-reset: item
}
li-lista {
  display: block
}
li-lista:before {
  content: counters(item, ".") " ";
  counter-increment: item
}

</style>
</head>
<body>
	
	<aside> <!----------------------------------------------------------------------
							MENU LATERALE A SCOMPARSA 
	 ----------------------------------------------------------------------->
	<caronte:desktop-menu />

	<!----------------------------------------------------------------------
						FINE MENU LATERALE
	 ----------------------------------------------------------------------->


	<!----------------------------------------------------------------------
	 					MOBILE MENU
	 ------------------------------------------------------------------------>
  <caronte:mobile-menu />
  
	<!----------------------------------------------------------------------
	 					FINE MOBILE MENU
	 ------------------------------------------------------------------------>

	<ul class="sidenav sidenav-fixed white left-sidenav"
		id="dashboard-leftside-menu">
		<!--USER STUFF-->
		<li class="user-view center-align"><spring:eval
				expression="T(it.aizoon.ersaf.security.SecurityUtils).getUtenteLoggato()"
				var="utente" />

			<div class="background deep-orange accent-2 white-text user-info-gradient">
			</div>
			<div class="row">
				<img src='<spring:url value="/resources/img/user.png"/>' alt=""
					class="circle responsive-img valign profile-image">
			</div>
			<div class="row">
				<c:if test="${not empty utente}">
					<h5 id="user-name" class="slim-title white-text">${utente.denominazione}</h5>
					<!-- Forse non è necessaria la descrizione 
					<h6 id="user-role">Administrator</h6>-->
				</c:if>
			</div></li>
		<li><a href="<spring:url value="informazioniProfiloUtente"/>" class="grey-text text-darken-1"> <i
				class="material-icons">face</i> Profilo
		</a></li>
		<c:choose>
		  <c:when test="${caronte:hasUserRole('IMPORT')}">
		    <spring:url value="/import/servizi/documentazione/elencoDocumenti" var="urlImpostazioni"/>
      </c:when>
      <c:otherwise>
        <spring:url value="/export/servizi/documentazione/elencoDocumenti" var="urlImpostazioni"/>
      </c:otherwise>
		</c:choose>
		
		<!-- <li><a href="${urlImpostazioni}" class="grey-text text-darken-1"> 
		  <i class="material-icons">settings</i> Impostazioni</a></li>
		  -->
		  <li><a href='<spring:url value="/registrazione/report/privacy"/>' class="grey-text text-darken-1"> <i
				class="material-icons">lock</i> Informativa privacy
		</a></li>		 	   
		   
		<li class="divider"></li>
		<li><a href='<spring:url value="/logout"/>'
			class="red-text text-accent-2"><i class="material-icons">keyboard_tab</i>
				Log out </a></li>
	</ul>
	</aside>

	<section id="content">
		<div class="row">
			<div class="col s12">
				<div class="col s12 m12 l12 item">
				  <h5 class="deep-orange accent-2 white-text" 
				    style="margin-bottom:0rem;margin-top:0.5rem;padding:1rem;">Notifiche recenti</h5>
				  <div class="" style="max-height: 310px; overflow-y: auto">
            <ul class="collection with-header wrapper-valign-content" style="margin-top:0rem;">
						<c:set var="rowCounter" value="0"/>
						<c:if test="${not empty listaMessaggi}">
							<c:forEach items="${listaMessaggi}" var="notifica">
								<c:choose>
									<c:when test="${notifica.idTipoNotifica == 1}">
										<c:set var="backgroundColor" value="grey lighten-3"/>
										<c:if test="${rowCounter%2 == 0}">
											<c:set var="backgroundColor" value=""/>			
										</c:if>
										<c:set var="rowCounter" value="${rowCounter + 1}"/>
										<li class="collection-item avatar linked-div block-valign-content ${backgroundColor}"><i
											class="material-icons circle orange adjustIconIE">message</i>
					                      <span class="title"><b>Notifiche generiche</b></span>
					                      <p>${notifica.messaggio}</p>
									  </li>
									</c:when>
									<c:when test="${notifica.idTipoNotifica == 2}">
										<c:set var="backgroundColor" value="grey lighten-3"/>
										<c:if test="${rowCounter%2 == 0}">	
											<c:set var="backgroundColor" value=""/>						
										</c:if>
										<c:set var="rowCounter" value="${rowCounter + 1}"/>
										<li class="collection-item avatar linked-div block-valign-content ${backgroundColor}"><i
											class="material-icons circle red adjustIconIE">message</i> 
											<span class="title"><b>Notifiche di servizio</b></span>
											<p>${notifica.messaggio}</p>
										</li>
									</c:when>
									<c:otherwise>
										<c:set var="backgroundColor" value="grey lighten-3"/>
										<c:if test="${rowCounter%2 == 0}">	
											<c:set var="backgroundColor" value=""/>						
										</c:if>
										<c:set var="rowCounter" value="${rowCounter + 1}"/>
										<li class="collection-item avatar linked-div block-valign-content ${backgroundColor}"><i
											class="material-icons circle yellow adjustIconIE">message</i> 
											<span class="title"><b>Notifiche relative ai certificati</b></span>
											<p>${notifica.messaggio}</p>
										</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						<c:if test="${not empty utentiDaAutorizzare}">
							<c:forEach items="${utentiDaAutorizzare}" var="utente">
								<fmt:formatDate value="${utente.dataInsert}"
									pattern="dd/MM/yyyy" var="dataInoltro" />
								<c:set var="backgroundColor" value="grey lighten-3"/>
								<c:if test="${rowCounter%2 == 0}">	
									<c:set var="backgroundColor" value=""/>						
								</c:if>
								<c:set var="rowCounter" value="${rowCounter + 1}"/>
								<li class="collection-item avatar linked-div block-valign-content ${backgroundColor}">
									<i class="large material-icons circle grey adjustIconIE">account_circle</i>
									<span>
										L'utente <b>${fn:toUpperCase(utente.denominazione)}</b> si è registrato in data <b>${dataInoltro}</b>
									</span> 
					                <a href='<spring:url value="/admin/utenti/profili/modifica_${utente.idUtente}"/>'><span></span></a>
					           </li>
							</c:forEach>
						</c:if>
						<c:if test="${not empty ispettoriDaAutorizzare}">
							<c:forEach items="${ispettoriDaAutorizzare}" var="ispettore">
								<fmt:formatDate value="${ispettore.dataInsert}"
									pattern="dd/MM/yyyy" var="dataInoltro" />
								<c:set var="backgroundColor" value="grey lighten-3"/>
								<c:if test="${rowCounter%2 == 0}">	
									<c:set var="backgroundColor" value=""/>						
								</c:if>
								<c:set var="rowCounter" value="${rowCounter + 1}"/>
								<li class="collection-item avatar linked-div block-valign-content ${backgroundColor}"><i
									class="large material-icons circle blue adjustIconIE">border_color</i>
									<span>
					                    L'ispettore <b>${fn:toUpperCase(ispettore.denominazione)}</b> si è registrato in data <b>${dataInoltro}</b>
					                  </span>
									<a href='<spring:url value="/admin/utenti/ispettori/modifica_${ispettore.idIspettore}"/>'><span></span></a>
							  </li>
							</c:forEach>					
						</c:if>
						<c:if test="${not empty spedizionenieriDaAutorizzare}">
							<c:forEach items="${spedizionenieriDaAutorizzare}" var="spedizioniere">
							<fmt:formatDate value="${spedizioniere.dataInsert}"
								pattern="dd/MM/yyyy" var="dataInoltro" />
								<c:set var="backgroundColor" value="grey lighten-3"/>
								<c:if test="${rowCounter%2 == 0}">	
									<c:set var="backgroundColor" value=""/>						
								</c:if>
								<c:set var="rowCounter" value="${rowCounter + 1}"/>
								<li class="collection-item avatar linked-div block-valign-content ${backgroundColor}"><i
									class="large material-icons circle green adjustIconIE">flight_takeoff</i>
									<span>
									  L'operatore <b>${fn:toUpperCase(spedizioniere.denominazione)}</b> si è registrato in data <b>${dataInoltro}</b>
									</span>
									<a href='<spring:url value="/admin/utenti/spedizionieri/modifica_${spedizioniere.idSpedizioniere}"/>'><span></span></a>
								</li>
							</c:forEach>					
						</c:if>
						<c:if test="${not empty listaRichiesteRespinte}">
							<c:forEach items="${listaRichiesteRespinte}" var="richiesta">
							<fmt:formatDate value="${richiesta.dataInsert}"
								pattern="dd/MM/yyyy" var="dataInserimento" />
							<fmt:formatDate value="${richiesta.dataUpdate}"
								pattern="dd/MM/yyyy" var="dataAggiornamento" />
							<spring:eval var="statoRestituita"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).DESC_STATO_RICHIESTA_RESTITUITA" />
							<spring:eval var="statoAnnullata"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).DESC_STATO_RICHIESTA_ANNULLATA" />
							<spring:eval var="idStatoRestituita"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_RESTITUITA" />
							<spring:eval var="idStatoAnnullata"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).STATO_RICHIESTA_ANNULLATA" />
							<spring:eval var="idTipoRichiestaImport"
								expression="T(it.aizoon.ersaf.util.CaronteConstants).TIPO_RICHIESTA_IMPORT" />
								<c:set var="backgroundColor" value="grey lighten-3"/>
								<c:if test="${rowCounter%2 == 0}">	
									<c:set var="backgroundColor" value=""/>						
								</c:if>
								<c:set var="rowCounter" value="${rowCounter + 1}"/>
								<li class="collection-item avatar linked-div block-valign-content ${backgroundColor}"><i
									class="large material-icons circle green adjustIconIE">archive</i>
										<span>
											La richiesta n° <b>${richiesta.codRichiesta}</b> del <b>${dataInserimento}</b> è stata 
										<c:choose>
											<c:when test="${richiesta.idStatoRichiesta == idStatoRestituita}">
												${fn:toLowerCase(statoRestituita)}
											</c:when>
											<c:otherwise>
	                      					${fn:toLowerCase(statoAnnullata)}
											</c:otherwise>
										</c:choose>
										in data <b>${dataAggiornamento}</b>
									</span>
									<c:choose>
										<c:when test="${richiesta.idTipoRichiesta == idTipoRichiestaImport}">
											<a href='<spring:url value="/import/richieste/dettaglio/${richiesta.idRichiesta}"/>'><span></span></a>
										</c:when>
										<c:otherwise>
											<a href='<spring:url value="/export/richieste/dettaglio/${richiesta.idRichiesta}"/>'><span></span></a>
										</c:otherwise>
									</c:choose>
								</li>
							</c:forEach>					
						</c:if>
						<c:if test="${not empty listaComunicazioniRespinte}">
			              <c:forEach items="${listaComunicazioniRespinte}" var="comunicazione">
				              <fmt:formatDate value="${comunicazione.dataInserimento}"
				                pattern="dd/MM/yyyy" var="dataInserimento" />
				              <fmt:formatDate value="${comunicazione.dataAggiornamento}"
				                pattern="dd/MM/yyyy" var="dataAggiornamento" />
				              
			                <c:set var="backgroundColor" value="grey lighten-3"/>
			                <c:if test="${rowCounter%2 == 0}">  
			                  <c:set var="backgroundColor" value=""/>           
			                </c:if>
			                <c:set var="rowCounter" value="${rowCounter + 1}"/>
			                <li class="collection-item avatar linked-div block-valign-content ${backgroundColor}"><i
			                  class="large material-icons circle green adjustIconIE">archive</i>
			                  <span>
			                     La comunicazione di tipo <b>${comunicazione.descTipoComunicazione}</b> del <b>${dataInserimento}</b> è stata
			                     ${fn:toLowerCase(comunicazione.descStatoComunicazione)} 
			                     in data <b>${dataAggiornamento}</b>
			                  </span>
			                  <a href='<spring:url value="/vivai/comunicazioni/dettaglio/${comunicazione.idComunicazione}"/>'><span></span></a>
			                </li>
			              </c:forEach>          
			            </c:if>
			            <c:if test="${not empty listaDomandeRespinte}">
			              <c:forEach items="${listaDomandeRespinte}" var="domanda">
				              <fmt:formatDate value="${domanda.dataInserimento}"
				                pattern="dd/MM/yyyy" var="dataInserimento" />
				              <fmt:formatDate value="${domanda.dataAggiornamento}"
				                pattern="dd/MM/yyyy" var="dataAggiornamento" />
				              
			                <c:set var="backgroundColor" value="grey lighten-3"/>
			                <c:if test="${rowCounter%2 == 0}">  
			                  <c:set var="backgroundColor" value=""/>           
			                </c:if>
			                <c:set var="rowCounter" value="${rowCounter + 1}"/>
			                <li class="collection-item avatar linked-div block-valign-content ${backgroundColor}"><i
			                  class="large material-icons circle green adjustIconIE">archive</i>
			                  <span>
			                     La domanda di tipo <b>${domanda.descTipoDomanda}</b> del <b>${dataInserimento}</b> è stata
			                     ${fn:toLowerCase(domanda.descStatoDomanda)} 
			                     in data <b>${dataAggiornamento}</b>, con il seguente motivo : <b>${domanda.motivazione}</b>
			                  </span>
			                  <a href='<spring:url value="/autorizzazioni/comunicazioni/dettaglio/${domanda.idDomanda}"/>'><span></span></a>
			                </li>
			              </c:forEach>          
			            </c:if>
			            <c:if test="${not empty listaDomandeInoltrate}">
			              <c:forEach items="${listaDomandeInoltrate}" var="domanda">
				              <fmt:formatDate value="${domanda.dataInserimento}"
				                pattern="dd/MM/yyyy" var="dataInserimento" />
				              <fmt:formatDate value="${domanda.dataAggiornamento}"
				                pattern="dd/MM/yyyy" var="dataAggiornamento" />
				              
			                <c:set var="backgroundColor" value="grey lighten-3"/>
			                <c:if test="${rowCounter%2 == 0}">  
			                  <c:set var="backgroundColor" value=""/>           
			                </c:if>
			                <c:set var="rowCounter" value="${rowCounter + 1}"/>
			                <li class="collection-item avatar linked-div block-valign-content ${backgroundColor}"><i
			                  class="large material-icons circle green adjustIconIE">archive</i>
			                  <span>
			                     La domanda di tipo <b>${domanda.descTipoDomanda}</b> del <b>${dataInserimento}</b> è stata
			                     ${fn:toLowerCase(domanda.descStatoDomanda)} 
			                     in data <b>${dataAggiornamento}</b>
			                  </span>
			                  <a href='<spring:url value="/autorizzazioni/comunicazioni/dettaglio/${domanda.idDomanda}"/>'><span></span></a>
			                </li>
			              </c:forEach>          
			            </c:if>
						<c:if test="${empty listaMessaggi and empty utentiDaAutorizzare and empty ispettoriDaAutorizzare and empty spedizionenieriDaAutorizzare and empty listaRichiesteRespinte and empty listaComunicazioniRespinte and empty listaDomandeRespinte and empty listaDomandeInoltrate}">
							<li class="collection-item avatar block-valign-content"><i
								class="material-icons circle grey adjustIconIE">announcement</i>
                				<span class="title">Nessuna notifica presente</span>
							</li>
						</c:if>
					</ul>
					</div>
				</div>
			</div>	
	  </div>
	  
		<div class="row">
		  <div class="col s12">
		    <input type="hidden" name="obbligoAccettaPolicy" id="obbligoAccettaPolicy" value="${obbligoAccettaPolicy}" />
		  	<c:if test="${caronte:hasUserRole('ADMIN')}"><c:set var="admin" value='1' /></c:if>
			<c:if test="${caronte:hasUserRole('IMPORT')}"><c:set var="import" value='1' /></c:if>
			<c:if test="${caronte:hasUserRole('EXPORT')}"><c:set var="export" value='1' /></c:if>
			<c:if test="${caronte:hasUserRole('VIVAI')}"><c:set var="vivai" value='1' /></c:if>
			<c:if test="${caronte:hasUserRole('AUTORIZ')}"><c:set var="autoriz" value='1' /></c:if>
			<c:if test="${caronte:hasUserRole('CONTROLLI')}"><c:set var="controlli" value='1' /></c:if>
			<c:set var="nModuli" value="${admin + import + export + vivai + autoriz + controlli}" />
			  <c:choose>
			    <c:when test="${(nModuli == 5) or (nModuli == 4) or (nModuli == 2)}">
			          <c:set var="numColonne" value="m6" />
				</c:when>
				<c:when test="${(nModuli == 6)  or (nModuli == 3)}">
				      <c:set var="numColonne" value="m4" />
				</c:when> 
			    <c:otherwise>
			      <c:set var="numColonne" value="m12" />
			    </c:otherwise>
			  </c:choose>
			  <!-- LA SEZIONE IMPORT NON VIENE USATA -->
			  <!-- 
			  <sec:authorize access="hasRole('READ_IMPORT')">
			  <div class="col ${numColonne}">
			    <div class="card">
						<div
							class="card-header waves-effect waves-block waves-light blue accent-2 white-text title-padding linked-div">
							<h4 class="card-title text-shadow uppercase-title">Import</h4>
							<a href="<spring:url value="/import/richieste/elenco"/>"><span></span></a>
						</div>
						<div class="card-content">
							<p class="item-post-content"><br/><br/></p>
						</div>
						<div class="card-action">
							<a href='<spring:url value="/import/richieste/elenco"/>'><i
								class="material-icons left">reply</i>Vai</a>
						</div>
					</div>
				</div>
				</sec:authorize>
				-->
				
				<sec:authorize access="hasRole('READ_EXPORT')">
				<div class="col ${numColonne}">
	        		<div class="card">
						<div
							class="card-header waves-effect waves-block waves-light teal darken-1 white-text title-padding linked-div">
							<h4 class="card-title text-shadow uppercase-title">Export</h4>
							<a href="<spring:url value="/export/richieste/elenco"/>"><span></span></a>
						</div>
						<div class="card-content">
							<p class="item-post-content"><br/><br/></p>
						</div>
						<div class="card-action">
							<a href='<spring:url value="/export/richieste/elenco"/>'><i
								class="material-icons left">reply</i>Vai</a>
						</div>
					</div>
				</div>
				</sec:authorize>
				
				<sec:authorize access="hasRole('READ_VIVAI')">
		        <div class="col ${numColonne}">
		          <div class="card">
		            <div
		              class="card-header waves-effect waves-block waves-light orange darken-1 white-text title-padding linked-div">
		              <h4 class="card-title text-shadow uppercase-title">Comunicazione Vegetali</h4>
		              <a href="<spring:url value="/vivai/comunicazioni/elenco"/>"><span></span></a>
		            </div>
		            <div class="card-content">
		              <p class="item-post-content"><br/><br/></p>
		            </div>
		            <div class="card-action">
		              <a href='<spring:url value="/vivai/comunicazioni/elenco"/>'><i
		                class="material-icons left">reply</i>Vai</a>
		            </div>
		          </div>
		        </div>
        		</sec:authorize>      
        		  		
        		<sec:authorize access="hasRole('READ_AUTORIZ')">
		        <div class="col ${numColonne}">
		          <div class="card">
		            <div
		              class="card-header waves-effect waves-block waves-light cyan darken-1 white-text title-padding linked-div">
		              <h4 class="card-title text-shadow uppercase-title">REGISTRAZIONI RUOP E RICHIESTE PASSAPORTO</h4>
		              <a href="<spring:url value="/autorizzazioni/comunicazioni/elenco"/>"><span></span></a>
		            </div>
		            <div class="card-content">
		              <p class="item-post-content"><br/><br/></p>
		            </div>
		            <div class="card-action">
		              <a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>'><i
		                class="material-icons left">reply</i>Vai</a>
		            </div>
		          </div>
		        </div>
        		</sec:authorize>
        		
        		<sec:authorize access="hasRole('READ_CONTROLLI')">
		        <div class="col ${numColonne}">
		          <div class="card">
		            <div
		              class="card-header waves-effect waves-block waves-light blue darken-1 white-text title-padding linked-div">
		              <h4 class="card-title text-shadow uppercase-title">Controlli</h4>
		              <a href="<spring:url value="/controlli/elenco"/>"><span></span></a>
		            </div>
		            <div class="card-content">
		              <p class="item-post-content"><br/><br/></p>
		            </div>
		            <div class="card-action">
		              <a href='<spring:url value="/controlli/elenco"/>'><i
		                class="material-icons left">reply</i>Vai</a>
		            </div>
		          </div>
		        </div>
        		</sec:authorize>	
        					
				<!-- AMMINISTRATORE -->
				<sec:authorize access="hasRole('ADMIN')">
				<div class="col ${numColonne}">
				  <div class="card">
			       <div
							class="card-header waves-effect waves-block waves-light red darken-1 white-text title-padding linked-div">
							<h4 class="card-title text-shadow uppercase-title">Amministrazione</h4>
							<a href="<spring:url value="/admin/utenti/spedizionieri/elenco"/>"><span></span></a>
						</div>
						<div class="card-content">
							<p class="item-post-content"><br/><br/></p>
						</div>
						<div class="card-action">
							<a href="<spring:url value="/admin/utenti/spedizionieri/elenco"/>"><i class="material-icons left">reply</i>Vai</a>
						</div>
					</div>
				</div>
				</sec:authorize>
				<!-- FINE AMMINSTRATORE -->
      		</div>
		</div>
	
	</section>
	
	
	<!-- MODALE per l'accettazione della Policy Privacy -->
	  <div class="modal modal-width-80 top-padding-20" id="modalAccettaPrivacy" >
		<div class="modal-content" style="padding-top:0.5em; padding-bottom:0;">		
				<div class="row" style="padding-top: 2em; padding-left: 5em;">
		<div class="col l10">
			<div class="card horizontal">				
				<div class="card-stacked col l6" style="padding-right: 1em;">
					<div class="card-content card-action">
						<h5>INFORMATIVA RELATIVA AL TRATTAMENTO DEI DATI PERSONALI</h5>						
						<form:form action="utente" method="post" modelAttribute="utenteForm" accept-charset="utf-8">														 
							<div class="row">
								<div class="col s12">
								    <div class="col s12 m12 l12 item">
								      <div class="" style="max-height: 310px; overflow-y: auto">										
 										${testoPolicy}
								          <div class="input-field col s6 m4 l12">
										<label>											
											 <input type="checkbox" name="checkPrivacy" id="checkPrivacy" value="S" onclick="accettaPrivacy(${idUtente});">											
											<span>Accetto</span>
										</label>
									</div>
								      </div>
								    </div>
								</div>
							</div>							
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
		</div>
	</div>
	<!-- fine modale per INOLTRA -->
	
	
<content tag="script"> 
  <script type="text/javascript" src='<spring:url value="/resources/js/jquery.materialize-autocomplete.min.js"/>'></script> 
	  <script>	 
	  
	  var obbligoAccettaPolicy = document.getElementById('obbligoAccettaPolicy').value;  
	  
	    $(document).ready(function(){
	    	console.log('-- obbligoAccettaPolicy ='+obbligoAccettaPolicy);
	    	if(obbligoAccettaPolicy == 'S'){
	    	  $('#modalAccettaPrivacy').modal()[0].M_Modal.options.dismissible = false;
		      $('#modalAccettaPrivacy').modal('open');
	    	}
		 });
	     
	    // Gestisce l'accetazione della privacy
		function accettaPrivacy(idUtente){
		  console.log('-- accettaPrivacy');	
		  console.log('idUtente ='+idUtente);
		  document.getElementById("checkPrivacy").disabled = true;
		  var params = {
					"idUtente" : idUtente		
				}
			getSelectAsincrona(
					'errori',
					'<spring:url value="/ajax/setDataAccettazionePrivacy" />',
					params);
		  
		  obbligoAccettaPolicy = 'N';
		  console.log('set dismissible = true');
		  $('#modalAccettaPrivacy').modal()[0].M_Modal.options.dismissible = true;
		  
		}
	  </script> 
</content>	
  
</body>
</html>