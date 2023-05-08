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
</style>
</head>
<body>

	<!-- div class="row" -->
		<div class="card">
			<div class="card-header bg-primary-color white-text">
				<h3 class="card-title text-shadow uppercase-title title-padding"><b>NUOVA DOMANDA</b></h3>
			</div>
			
			<div class="card-content row">
				<div class="col s12">
					<div class="card-panel hoverable"><h6>Si sta creando una nuova Domanda, selezionare il Tipo di Domanda che si desidera inserire.</h6></div>
				</div>
				
				<c:forEach var="tipoComunicazione" items="${listaTipiComunicazioni}">
				<div class="col s6 m4 l3">
					<div id="tipoComunicazione${tipoComunicazione.idTipoComunicazione}" class="card small hoverable">
						<div class="card-header bg-primary-color white-text linked-div">
							<h4 class="card-title text-shadow uppercase-title title-padding"><b>${tipoComunicazione.descBreve}</b></h4>
							<a href="javascript:selezionaTipoComunicazione(${tipoComunicazione.idTipoComunicazione})"><span></span></a>
						</div>
						<div class="card-content">
							<p>${tipoComunicazione.descEstesa}</p>
						</div>
						<div class="card-action">
							<a href='javascript:selezionaTipoComunicazione(${tipoComunicazione.idTipoComunicazione})'><i
								class="material-icons left">add_circle</i>Seleziona</a>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		</div>

		<div class="col s12 left-align">
			<a href='<spring:url value="/autorizzazioni/comunicazioni/elenco"/>' class="btn waves-effect waves-light">TORNA
				ALLA RICERCA</a>
		</div>
		<br />
	<!-- /div-->

	<content tag="script"> 
		<script>
		// questo script permette di sfumare le card nel caso fossero piu di una
		$(document).ready(function() {
			var targetRgb = [245, 127, 23];
			var $tipiComunicazione = $('[id^=tipoComunicazione] > .card-header'); 
			
			$tipiComunicazione.each(function(index) {
				rgb = $(this).css("background-color").match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
				rgb.shift();
				
				for (var i = 0; i < 3; ++i) {
					//Il Math.max è stato aggiunto per evitare, nel caso di un solo tipo comunicazione, l'errore Divide by zero
					var increment = Math.round((targetRgb[i] - rgb[i]) * index / (Math.max($tipiComunicazione.length - 1, 1)));
			        rgb[i] = ("0" + (parseInt(rgb[i]) + increment).toString(16)).slice(-2);
			    }

				$(this).css('background-color', '#' + rgb.join('')).removeClass('yellow-green');
			});
		});
		
		function selezionaTipoComunicazione(idTipoComunicazione) {
			console.log('idTipoComunicazione selezionata = '+idTipoComunicazione);
			var $card = $('#tipoComunicazione'+idTipoComunicazione);
			var descTipoComunicazione = $card.find('.card-title').text();
			var istruzioni = $card.find('.card-content').text();
			if(idTipoComunicazione == 2){
				istruzioni += " <br/> <br/>NOTA: Se si desidera creare una variazione di una domanda in stato convalidata, è necessario andare nella pagina di elenco e selezionare il pulsante relativo alla domanda di variazione RUOP, posizionato sotto la colonna \"Azioni\"."
				}
			
			swal({
				title : 'Si desidera creare una Domanda : '+ descTipoComunicazione + '?',
				html : istruzioni,
				type : 'warning',
				showCancelButton : true,						
				confirmButtonText : 'CONTINUA',										
				cancelButtonText : 'ANNULLA',
		  }).then(function(result) {
			  if (result.value) {
				  window.location.href = '<spring:url value="/autorizzazioni/comunicazioni/anagrafica/nuova/"/>' + idTipoComunicazione;
			  }
          });
		}
		
			
		</script>
    </content>

</body>
</html>