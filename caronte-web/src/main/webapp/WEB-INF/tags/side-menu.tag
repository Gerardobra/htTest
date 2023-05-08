<%@ tag description="Template per menù laterale" pageEncoding="UTF-8"%>
<%@ attribute name="menuId" required="true" %>
<%@ attribute name="menuStyle" required="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<ul class="sidenav" id="${menuId}" style="${menuStyle}">
	<li>
		<div id="sideMenuLabel" class="user-view">
			<div class="background">
				<img src='<spring:url value="/resources/img/dolomites.png"/>'>
			</div>
			<h4 class="white-text text-darken-4">Menu</h4>
		</div>
	</li>

	<li class="no-padding">
		<ul class="collapsible collapsible-accordion">
		  <li><a class="collapsible-header" href='<spring:url value="/home"/>'>Pagina iniziale</a></li>
		  
		  <!-- LA SEZIONE IMPORT NON VIENE USATA -->
		  <!-- 
		  <sec:authorize access="hasRole('READ_IMPORT')">
			<li><a href="#!" class="collapsible-header">Import<i class="material-icons right">arrow_drop_down</i></a>
				<div class="collapsible-body">
					<ul>
						<li><a class="waves-effect" href='<spring:url value="/import/richieste/elenco"/>'>Richieste</a></li>
						<sec:authorize access="hasRole('ADMIN')">
						<li><a class="waves-effect" href='<spring:url value="/import/report/elenco"/>'>Report</a></li>
						</sec:authorize>
						<li>
							<ul class="collapsible collapsible-accordion">
								<li><a class="collapsible-header">Servizi<i class="material-icons right">arrow_drop_down</i></a>
									<div class="collapsible-body">
										<ul>
										  <sec:authorize access="hasRole('ADMIN')">
											<li><a class="waves-effect" href="<spring:url value="/import/servizi/datiSian/elencoSian"/>" style="padding-left: 46px;">Scarico Dati</a></li>
											</sec:authorize>
											<li><a class="waves-effect" href="<spring:url value="/import/servizi/documentazione/elencoDocumenti"/>" style="padding-left: 46px;">Documentazione</a></li>
											<sec:authorize access="hasRole('SUPERUSER')">
											<li><a class="waves-effect" href='<spring:url value="/resources/docs/rptCertificatoManuale_Unico.rtf"/>' 
											  style="padding-left: 46px;">Certificati manuali</a></li>
											</sec:authorize>
										</ul>
									</div></li>
							</ul>
						</li>
						
					</ul>
				</div>
			</li>
			</sec:authorize>
			-->
			
			<sec:authorize access="hasRole('READ_EXPORT')">
			<li><a href="#!" class="collapsible-header">Export<i class="material-icons right">arrow_drop_down</i></a>
				<div class="collapsible-body">
					<ul>
						<li><a class="waves-effect" href='<spring:url value="/export/richieste/elenco"/>'>Richieste</a></li>
						<sec:authorize access="hasRole('ADMIN')">
		        <li><a class="waves-effect" href='<spring:url value="/export/report/elenco"/>'>Report</a></li>
		        </sec:authorize>
		        <li>
			       <ul class="collapsible collapsible-accordion">
			         <li><a class="collapsible-header">Servizi<i class="material-icons right">arrow_drop_down</i></a>
				        <div class="collapsible-body">
				        <ul>
				          <sec:authorize access="hasRole('ADMIN')">
									<li><a class="waves-effect" href="<spring:url value="/export/servizi/datiSian/elencoSian"/>" style="padding-left: 46px;">Scarico Dati</a></li>
									</sec:authorize>
									<li><a class="waves-effect" href="<spring:url value="/export/servizi/documentazione/elencoDocumenti"/>" style="padding-left: 46px;">Documentazione</a></li>
								  </ul>
								</div></li>
							</ul>
		        </li>
					</ul>
				</div>
			</li>
			</sec:authorize>
			
	<sec:authorize access="hasRole('READ_VIVAI')">
      <li><a href="#!" class="collapsible-header">Comunicazione Vegetali<i class="material-icons right">arrow_drop_down</i></a>
        <div class="collapsible-body">
          <ul>
            <li><a class="waves-effect" href='<spring:url value="/vivai/comunicazioni/elenco"/>'>Comunicazioni</a></li>
            <sec:authorize access="hasRole('ADMIN')">
		      <ul class="collapsible collapsible-accordion">
			      <li>
			      	  <a class="collapsible-header" >Report<i class="material-icons right">arrow_drop_down</i></a>
			      	  <div class="collapsible-body">
				      	  <ul>
				      		<li><a class="waves-effect" href="<spring:url value="/vivai/report/elencoSpecie"/>" style="padding-left: 46px;">Specie</a></li>
							<li><a class="waves-effect" href="<spring:url value="/vivai/report/elencoComunicazioni"/>" style="padding-left: 46px;">Comunicazioni</a></li>
						  </ul>
					  </div>
				</li>
			</ul>		
		    </sec:authorize>
          </ul>
        </div>
      </li>
    </sec:authorize>
    
    <sec:authorize access="hasRole('READ_AUTORIZ')">
      <li><a href="#!" class="collapsible-header">Registrazioni RUOP<i class="material-icons right">arrow_drop_down</i></a>
        <div class="collapsible-body">
          <ul>
            <li><a class="waves-effect" href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>'>Domande</a></li> 
            
            <sec:authorize access="hasRole('ADMIN')">
		      <ul class="collapsible collapsible-accordion">
			      <li>
			      	  <a class="collapsible-header" >Report<i class="material-icons right">arrow_drop_down</i></a>
			      	  <div class="collapsible-body">
				      	  <ul>
				      		<li><a class="waves-effect" href="<spring:url value="/autorizzazioni/report/elencoAziende"/>" style="padding-left: 46px;">Aziende</a></li>
							<li><a class="waves-effect" href="<spring:url value="/autorizzazioni/report/elencoCentriAziendali"/>" style="padding-left: 46px;">Centri Aziendali</a></li>
						  </ul>
					  </div>
				</li>
			</ul>		
		    </sec:authorize>
		               
          </ul>
        </div>
      </li>
    </sec:authorize>
    
    <sec:authorize access="hasRole('READ_CONTROLLI')">
      <li><a href="#!" class="collapsible-header">Controlli<i class="material-icons right">arrow_drop_down</i></a>
        <div class="collapsible-body">
          <ul>
            <li><a class="waves-effect" href='<spring:url value="/controlli/elenco"/>'>Controlli</a></li>  
            
             <sec:authorize access="hasRole('ADMIN')">
		      <ul class="collapsible collapsible-accordion">
			      <li>
			      	  <a class="collapsible-header" >Report<i class="material-icons right">arrow_drop_down</i></a>
			      	  <div class="collapsible-body">
				      	  <ul>
				      		<li><a class="waves-effect" href="<spring:url value="/controlli/report/elencoControlli"/>" style="padding-left: 46px;">Controlli</a></li>
							<li><a class="waves-effect" href="<spring:url value="/controlli/report/elencoCampioni"/>" style="padding-left: 46px;">Campioni</a></li>
							<li><a class="waves-effect" href="<spring:url value="/controlli/report/elencoMisure"/>" style="padding-left: 46px;">Misure</a></li>
						  </ul>
					  </div>
				</li>
			</ul>		
		    </sec:authorize>
		              
          </ul>
        </div>
      </li>
    </sec:authorize>
			
	<sec:authorize access="hasRole('ADMIN')">
	  <li><a href="#!" class="collapsible-header">Amministrazione<i class="material-icons right">arrow_drop_down</i></a>
        <div class="collapsible-body">
          <ul>
            <li><a class="waves-effect" href="<spring:url value="/admin/utenti/spedizionieri/elenco"/>" style="padding-left: 46px;">Profili, Ispettori e Operatori</a></li>
            <li><a class="waves-effect" href="<spring:url value="/admin/generi/elenco"/>" style="padding-left: 46px;">Generi e Specie</a></li>
            <li><a class="waves-effect" href="<spring:url value="/admin/organisminocivi/elenco"/>" style="padding-left: 46px;">Organismi nocivi</a></li>
            <li><a class="waves-effect" href="<spring:url value="/admin/messaggistica/configura"/>" style="padding-left: 46px;">Impostazioni</a></li>
          </ul>
        </div>
      </li>
	</sec:authorize>

		</ul>
	</li>
</ul>