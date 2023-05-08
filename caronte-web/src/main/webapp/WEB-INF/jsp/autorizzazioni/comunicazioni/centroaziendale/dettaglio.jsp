<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
		   		
	<h5 class="primary-color bold">Dati centro aziendale</h5>		
	<form:form method="post" modelAttribute="datiCentroAziendaleForm" accept-charset="utf-8">	
	<div class="card">
		<div class="card-content">
			<!-- DATI CENTRO AZIENDALE -->
			<div class="row">				
           	
           	   <!-- INIZIO Tipologie produttive CENTRO AZIENDALE -->	
				<div class="card-panel" id="tipologieProdDiv">
				   <p class="center-align" style="font-size:20px;margin-bottom:5px;">
					<em>Tipologie produttive del Centro Aziendale : ${datiCentroAziendaleForm.ragioneSociale}</em>									            	
	            	<div class="row">
				        <table id="tabellaSpecie" class="data-table striped display" data-order='[[ 2, "asc" ]]'  data-paging="false" data-info="false">				        
				          <thead>
				            <tr>				              
				              <th>Tipologia produttiva</th>
				              <th>Note</th>
				              <th>Genere</th>
				              <th>Specie</th>				              
				            </tr>
				          </thead>
				          <tbody id="bodyTabellaSpecie">				            
				            <c:forEach var="tipologia" items="${datiCentroAziendaleForm.tipologieList}">
				              <c:choose>
				                 			<c:when test="${tipologia.specieList != null && tipologia.specieList.size() > 0}">				              
							              		<c:forEach var="specie" items="${tipologia.specieList}">
											   		<tr>																																							
														<td>${tipologia.denomTipologia}</td>
														<td>${tipologia.note}</td>									
														<td>${specie.denomGenere}</td>
														<td>${specie.denomSpecie}</td>									
										      		</tr>
										     	</c:forEach> 
										   	</c:when>
										   	<c:otherwise>
										     <tr>								     																																							
														<td>${tipologia.denomTipologia}</td>	
														<td>${tipologia.note}</td>								
														<td></td>
														<td></td>									
										      </tr>
										   </c:otherwise>
										</c:choose>  
							 </c:forEach>
				          </tbody>				          				          				          
				        </table>                      
			      	</div>  
			    </div>         	            	
	            <!-- FINE Tipologie produttive CENTRO AZIENDALE -->
	            	
	            	
	            <!-- INIZIO Siti di produzione CENTRO AZIENDALE -->
	            	<div class="card-panel" id="sitiProdDiv">
						<p class="center-align" style="font-size:20px;margin-bottom:5px;">
						  <em>Siti di produzione del centro aziendale: ${datiCentroAziendaleForm.ragioneSociale}</em>
						</p>															     
						<div class="row">
					       	<table id="tabellaSitiProduzione" class="data-table striped display" 
					       		data-order='[[ 1, "asc" ], [ 2, "asc" ], [ 3, "asc" ], [ 4, "asc" ]]' data-paging="false" data-info="false">
								<thead>
									<tr>										
										<th>Sito di produzione</th>
										<th>Indirizzo</th>																	
										<th>Comune</th>
										<th>Foglio</th>
										<th>Mappale</th>
										<th>Superficie (mq.)</th>																		
									</tr>
								</thead>
								<tbody id="bodyTabellaSitiProduzione">
								  <c:forEach var="sitoProd" items="${datiCentroAziendaleForm.sitiProduzioneList}">
								   <tr>																																		
									<td>${sitoProd.denominazione}</td>
									<td>${sitoProd.ubicazione}</td>
									<td>${sitoProd.denomComune} (${sitoProd.siglaProvincia})</td>
									<td>${sitoProd.foglio}</td>									
									<td>${sitoProd.mappale}</td>
									<td>${sitoProd.superficie}</td>
							      </tr>
							    </c:forEach>
								</tbody>																
							</table>											
						</div>												   					   					    
	            	</div>	            	            	
	            	<!-- FINE Siti di produzione CENTRO AZIENDALE -->
           									
           		<div class="col s3">
					<a class="btn orange lighten-1 modal-action modal-close waves-effect back"> CHIUDI </a>
				</div>				
				
			</div>
		</div>
		
	</div>
	</form:form>
	
	<script>
	function editInit(idCentroAziendale) {
		console.log('In EDIT INIT modale  - idCentroAziendale: '+idCentroAziendale);
		
		
	}
		
		
		
	</script>
