<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style>
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
  <spring:url value="/controlli/esito/modifica" var="formAction" />
  <form:form action="${formAction}" method="post" modelAttribute="nuovoControlloForm" accept-charset="utf-8">    
	<div class="card">
  			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>NUOVO CONTROLLO DOCUMENTALE</b></h3>
			</div>			
  			<div class="card-content" style="padding-top:5px;">
  				<div class="row">
   	 				<div class="col s12">
      					<ul class="tabs">        						        				    		        	
			        	    <li class="tab" >
	        					<a href="<spring:url value="/controlli/datiBase/modifica" />" target="_self">Dati generali</a>
	        				</li>
	        				<c:if test="${nuovoControlloForm.tabDocumentale}">
	        					<li class="tab" >
	        						<a href="<spring:url value="/controlli/controllodocumentale/modifica" />" target="_self">Documentale</a>
	        					</li>
	        				</c:if>
	        				<c:if test="${nuovoControlloForm.tabIdentita}">		
	        					<li class="tab" >
	        						<a href="<spring:url value="/controlli/controlloidentita/modifica" />" target="_self">Identità</a>
	        					</li>
	        				</c:if>	
	        				<c:if test="${nuovoControlloForm.tabFisico}">
	        					<li class="tab" >
	        						<a href="<spring:url value="/controlli/controllofisico/modifica" />" target="_self">Fisico</a>
	        					</li>
	        				</c:if>	
	        				<c:if test="${nuovoControlloForm.tabFisico && nuovoControlloForm.flControllo5x23 != null && nuovoControlloForm.flControllo5x23 =='S' }">
		        				<li class="tab" >
		        					<a href="<spring:url value="/controlli/campioni/modifica" />" target="_self">Campioni</a>
		        				</li>
	        				</c:if>
	        				<li class="tab" >
	        					<a href="<spring:url value="/controlli/allegati/modifica" />" target="_self">Allegati</a>
	        				</li>
	        				<c:if test="${false}">
		        				<li class="tab" >
		        					<a href="<spring:url value="/controlli/monitoraggio/modifica" />" target="_self">Monitoraggio cofinanziato</a>
		        				</li>
	        				</c:if>	 
	        				<c:if test="${nuovoControlloForm.misuraUfficiale != null && nuovoControlloForm.misuraUfficiale =='S' }">
	        					<li class="tab" >
        					  		<a href="<spring:url value="/controlli/misura/modifica" />" target="_self">Misura ufficiale</a>
        				    	</li>
        				    </c:if>		        				
	        				<li class="tab" >
        						<a class="active"><b>Esito</b></a>
        					</li>
		        					
      					</ul>
    				</div>
  				</div>
        						  															
				<spring:eval var="versioneBegin"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_CONTROLLO_BEGIN" />	
				<spring:eval var="versione042021"	expression="T(it.aizoon.ersaf.util.CaronteConstants).ID_VERSIONE_CONTROLLO_04_2021" />
				<div class="card-panel">	
        	    	<div class="row">
		  				<p class="center-align"
							style="font-size: 20px; margin-bottom: 5px;">
							<em>Esito Controllo</em>
						</p>
		  		 	</div>		
					<div class="row">
						<div class="input-field col s6 m4 l3">
							<form:input type='text' id="dataChiusuraVerbale" path="dataChiusuraVerbale"
								cssClass="datepicker_birthdate" required="required"/>
							<form:errors path="dataChiusuraVerbale" cssClass="error" />
							<label for="dataChiusuraVerbale">Data chiusura verbale *</label>
						</div>						
					</div>
					<div class="row">
					<div class="col s9 m6 l4"><br><br>Persona alla quale verrà consegnato il verbale: </div>
						<div class="input-field col s6 m4 l3">
								<form:input type="text" id="cognomeResp"
									path="cognomeResp" cssClass="validate"
									required="required" maxlength="50" />
								<form:errors path="cognomeResp" cssClass="error" />
								<label for="cognomeResp">Cognome *</label>
						</div>		
										
						<div class="input-field col s6 m4 l3">
							<form:input type="text" id="nomeResp"
								path="nomeResp" cssClass="validate"
								required="required" maxlength="50" />
							<form:errors path="nomeResp" cssClass="error" />
							<label for="nomeResp">Nome *</label>
						</div>	
					</div>
					
					<div class="row">
						<div class="input-field col s12 m8 l12">
						  <form:textarea id="noteDichiarazione" path="noteDichiarazione" 
							  cssClass="materialize-textarea validate tooltipped" rows="2"
							  data-length="1000" maxlength="1000" data-position="right" 
							  data-tooltip="Note"/>
						  <form:errors path="noteDichiarazione" cssClass="error" />
						  <label for="noteDichiarazione">Dichiarazioni</label>
						</div>
					</div>		
					
					
					<div class="row">
					  <div class="col s9 m6 l12">		
						<p>Sono state accertate irregolarità *</p>	
						<br>					
						  <label class="radio-inline">
						  			<form:errors path="flIrregolarita" cssClass="error" />
									<form:radiobutton id="flIrregolarita"
										cssClass="with-gap" path="flIrregolarita"
										value="S"/>
									<span>Si</span>
						  </label>														
						  <label class="radio-inline">
								<form:radiobutton id="flIrregolarita"
									cssClass="with-gap" path="flIrregolarita"
									value="N"/>
								<span>No</span>
							  </label>	
						</div>	 
						<div class="input-field col s6 m5 l12">
			                <form:textarea id="noteIrregolarita" path="noteIrregolarita"
			                  cssClass="materialize-textarea validate" rows="2"
			                  data-length="300" maxlength="300" />
			                <form:errors path="noteIrregolarita" cssClass="error" />
			                <label for="noteIrregolarita">Note irregolarità</label>
		            	</div> 																																			
					</div>
					
					
			             
			       <c:if test="${nuovoControlloForm.idVersioneControllo == versioneBegin}">      
		              <div class="row">	
			            <div class="col s9 m6 l12">	
			             	<p>Commercializzazione dei vegetali si rilascia parere: *</p>
			             	<br>													
							  <label class="radio-inline">
							  		    <form:errors path="flEsito" cssClass="error" />
										<form:radiobutton id="flEsito"
											cssClass="with-gap" path="flEsito"
											value="S"/>
										<span>Favorevole</span>
							  </label>														
							  <label class="radio-inline">
									<form:radiobutton id="flEsito"
										cssClass="with-gap" path="flEsito"
										value="N"/>
									<span>Non favorevole</span>
							  </label>
						 </div> 																																				
					  </div>
					  					
					  <div class="row">	
					    <div class="col s9 m6 l12">
							<p>Al rilascio del Passaporto delle piante si rilascia parere: *</p>		
							<br>											
							  <label class="radio-inline">
									    <form:errors path="flEsitoPassaporto" cssClass="error" />
										<form:radiobutton id="flEsitoPassaporto"
											cssClass="with-gap" path="flEsitoPassaporto"
											value="S"/>
										<span>Favorevole</span>
							  </label>														
							  <label class="radio-inline">
									<form:radiobutton id="flEsitoPassaporto"
										cssClass="with-gap" path="flEsitoPassaporto"
										value="N"/>
									<span>Non favorevole</span>
							  </label>	
						</div>  																																			
					  </div>	
					</c:if>
					
					<c:if test="${nuovoControlloForm.idVersioneControllo >= versione042021}">
					<br>
					<br>
					  <div class="row">	
						  <div class="col s9 m6 l12">	
			             	<p>Il controllo si è concluso con esito : *</p>
			             	<br>													
							  <label class="radio-inline">
							  		    <form:errors path="flEsito" cssClass="error" />
										<form:radiobutton id="flEsito"
											cssClass="with-gap" path="flEsito"
											value="S"/>
										<span>Favorevole</span>
							  </label>														
							  <label class="radio-inline">
									<form:radiobutton id="flEsito"
										cssClass="with-gap" path="flEsito"
										value="N"/>
									<span>Non favorevole</span>
							  </label>	
						 </div>	 
						 <div class="input-field col s6 m5 l12"> 
						  <form:textarea id="noteEsitoControllo" path="noteEsitoControllo" 
							  cssClass="materialize-textarea validate tooltipped" rows="2"
							  data-length="1000" maxlength="1000" data-position="left" 
							  data-tooltip="Motivo esito controllo non favorevole"/>
							  <label for="noteEsitoControllo">Motivo esito controllo non favorevole</label>
						     <form:errors path="noteEsitoControllo" cssClass="error" />	
						 </div> 					 																												
					   </div>
					   
					  <br>
					  <br>
					  <div class="row">
						  <div class="input-field col s9 m6 l5">																			
	                             <label> 
	                                 <form:checkbox value="S" id="flMisuraUfficiale" path="flMisuraUfficiale" /> 
									 <span>Nel corso del controllo è stata emessa misura ufficiale n. </span>
								</label>
						  </div>		
						  <div class="input-field col s6 m6 l4">
									<form:input type="text" id="numMisuraUfficiale" path="numMisuraUfficiale"/>
									<form:errors path="numMisuraUfficiale" cssClass="error"/>
									<label for="numMisuraUfficiale">Numero misura ufficiale</label>
						  </div>						  						  
					 </div>
					 
					  
					  <br>
					  <br>
					  <div class="row">
						  <div class="input-field col s9 m6 l12">																				
	                             <label> 
	                                 <form:checkbox value="S" id="flMotivoMisuraUfficiale" path="flMotivoMisuraUfficiale" /> 
									 <span>Nel corso del controllo è stata proposta misura ufficiale (specificare il motivo)</span>
								</label>
						  </div>
						  <div class="input-field col s9 m6 l12">	
								<form:textarea id="noteMotivoMisuraUfficiale" path="noteMotivoMisuraUfficiale" 
								  cssClass="materialize-textarea validate tooltipped" rows="2"
								  data-length="1000" maxlength="1000" data-position="left" 
								  data-tooltip="Motivo misura ufficiale"/>
								  <label for="noteMotivoMisuraUfficiale">Motivo misura ufficiale</label>
							    <form:errors path="noteMotivoMisuraUfficiale" cssClass="error" />
						  </div>
					  </div>
					  
					  <br>
					  <br>
					  <div class="row">
						  <div class="input-field col s9 m6 l12">																				
	                             <label> 
	                                 <form:checkbox value="S" id="flSanzioneAmministrativaEmessa" path="flSanzioneAmministrativaEmessa" /> 
									 <span>Nel corso del controllo è stata emessa sanzione amministrativa (specificare il motivo) </span>
								</label>
						   </div>
						   <div class="input-field col s9 m6 l12">		
								<form:textarea id="noteSanzioneAmministrativaEmessa" path="noteSanzioneAmministrativaEmessa" 
								  cssClass="materialize-textarea validate tooltipped" rows="2"
								  data-length="1000" maxlength="1000" data-position="left" 
								  data-tooltip="Motivo sanzione amministrativa emessa"/>
								  <label for="noteSanzioneAmministrativaEmessa">Motivo sanzione amministrativa emessa</label>
							    <form:errors path="noteSanzioneAmministrativaEmessa" cssClass="error" />
						  </div>
					  </div>
					  
					  <br>
					  <br>
					  <div class="row">
						  <div class="input-field col s9 m6 l12">																				
	                             <label> 
	                                 <form:checkbox value="S" id="flSanzioneAmministrativaProposta" path="flSanzioneAmministrativaProposta" /> 
									 <span>Nel corso del controllo è stata proposta sanzione amministrativa (specificare il motivo) </span>
								</label>
						  </div>
						  <div class="input-field col s9 m6 l12">
								<form:textarea id="noteSanzioneAmministrativaProposta" path="noteSanzioneAmministrativaProposta" 
								  cssClass="materialize-textarea validate tooltipped" rows="2"
								  data-length="1000" maxlength="1000" data-position="left" 
								  data-tooltip="Motivo sanzione amministrativa proposta"/>
								<label for="noteSanzioneAmministrativaProposta">Motivo sanzione amministrativa proposta</label>
							    <form:errors path="noteSanzioneAmministrativaProposta" cssClass="error" />
						  </div>
					  </div>
					  
					  <br>
					  <br>
					  <div class="row">
						  <div class="input-field col s9 m6 l12">																				
	                             <label> 
	                                 <form:checkbox value="S" id="flPrescrizioni" path="flPrescrizioni" /> 
									 <span>Nel corso del controllo sono state date le seguenti prescrizioni : </span>
								</label>
						  </div>	
						  <div class="input-field col s9 m6 l12">	
								<form:textarea id="notePrescrizioni" path="notePrescrizioni" 
								  cssClass="materialize-textarea validate tooltipped" rows="2"
								  data-length="1000" maxlength="1000" data-position="left" 
								  data-tooltip="Prescrizioni"/>
								  <label for="notePrescrizioni">Prescrizioni</label>
							    <form:errors path="notePrescrizioni" cssClass="error" />
						  </div>
					  </div>
					</c:if>
					
					<br>
					<br>
					<div class="row">
						<div class="input-field col col s12">						
							<p>Controllo eseguito :</p>		
							<br>			
						   <div class="col l8">
								<label> <form:checkbox value="S" id="misuraUfficiale"
										path="misuraUfficiale" /> <span>Misura ufficiale</span>
								</label>
							</div>
						</div>
				   </div>	
					
					<br>
					<br>
					<div class="row">
					<div class="input-field col s12 m8 l12">
							  <form:textarea id="note" path="note" 
								  cssClass="materialize-textarea validate tooltipped" rows="3"
								  data-length="1000" maxlength="1000" data-position="right" 
								  data-tooltip="Note"/>
							  <form:errors path="note" cssClass="error" />
							  <label for="note">Note</label>
						</div>
					</div>
					
					<br>
					<br>
					<div class="row">	
						<div class="input-field col s6 m6 l6">
								<form:input type="text"
									path="emailInvioVerbale" cssClass="validate"
									required="required" maxlength="50"/>
								<form:errors path="emailInvioVerbale" cssClass="error"/>
								<label for="email">Email invio verbale *</label>
						  </div>
				   </div>		 
				</div>				
  			</div>
  		  <div class="card-action">	
			  <div class="row">
			    <a href='<spring:url value="/controlli/elenco"/>' class="btn waves-effect waves-light">TORNA ALLA RICERCA</a> 
				  <div class="right" style="text-align: right">

					<c:if test="${abilitaStampa}">
						<a href='javascript:void(0);' 
								title="Invia verbale per email"
								onclick="inviaMail();"
								class="btn amber darken-1 waves-effect waves-light"
								style="display: inline-block">INVIA VERBALE VIA MAIL
						</a>
					  	<a href='<spring:url value="stampa/${nuovoControlloForm.idEsito}/${nuovoControlloForm.idControllo}"/>' 
								title="Stampa verbale ispezione"
								class="btn amber darken-1 waves-effect waves-light"
								style="display: inline-block">STAMPA VERBALE
						</a>
					</c:if>				  	   
				    <button id="salvaEsito" type="submit" class="btn confirm waves-effect waves-light" style="display: inline-block"> SALVA </button>
				 </div>
			  </div>
		  </div>
	</div> 
  </form:form>

  
		

<content tag="script">     
	  <script>
	    function inviaMail(){
	    
	    	
	    	Swal.fire({
					title: "Invio verbale",
				    text: "Indirizzo email:",
				    input: 'email',
				    inputValue:'${nuovoControlloForm.emailInvioVerbale}',
				    inputPlaceholder: "@indirizzo",
					  inputAttributes: {
					    autocapitalize: 'off',
					    autofocus: 'off'
					  },
					  showCancelButton: true,
					  confirmButtonText: "Invia!", 
 		         	  cancelButtonText: "Annulla", 
					  showLoaderOnConfirm: true,
					  preConfirm: (email) => {

						  return fetch('inviaMail/${nuovoControlloForm.idEsito}/${nuovoControlloForm.idControllo}?' 
								  + new URLSearchParams({
									    indirizzoMail: email
									}))
					      .then(response => {
					    	if(!response.ok)
					        {
					          throw new Error(response.statusText)
					        }
					        return response.ok;
					      })
					      .catch(error => {
					    	 Swal.fire({
					    		  icon: 'error',
					    		  title: 'Oops...',
					    		  text: 'Si è verificato un errore nell\'invio mail!',
					    		});
					    	 return false;
					      })
					  },
					  allowOutsideClick: () => !Swal.isLoading()
					}).then((result) => {

					  if(result.dismiss == 'cancel')
						  return true;
					  if (result) {
					    Swal.fire({
					      title: 'Mail inviata con successo!'
					    })
					  }
					  
					})
					
	    }
	 			
 	  </script>
 	  
 	 </content>

</body>
</html>