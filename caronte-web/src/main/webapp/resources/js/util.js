function collapseAll(element) {
	console.log('COLLAPSE ALL - ELEMENT: '+element);
	if (element) {
		$("#" + element.id + " .collapsible-header").removeClass(function() {
			return "active";
		});
		$("#" + element.id + " .collapsible").collapsible({
			accordion : true
		});
		$("#" + element.id + " .collapsible").collapsible({
			accordion : false
		});
	}
}

/*$.extend($.fn.dataTable.defaults, {
	searching : false,
	ordering : false,
	paging : true,
	pageLength : 10,
});*/

/*$.extend( $.fn.pageMe.defaults, {
 showPrevNext: true,
 nextText: 'Succ.',
 prevText: 'Prec.',
 hidePageNumbers: false,
 } );*/

$('.card-alert .close').click(function() {
	$(this).closest(".card-alert").fadeOut("slow", function() {
	});
});

//Funzione per svuotare i campi del form
$.fn.clearForm = function() {
	$(this).find('input').filter(':text, :password, :file').val('').end()
		.filter(':checkbox, :radio').removeAttr('checked').end().end()
		.find('div.picker').prev('input:hidden').val('').end().end()
		.find('textarea').val('').end()
		.find('select').find('option:selected').removeAttr('selected').end()
		.prop("selectedIndex", 0);
	return this;
};

$('button.clear-form').click(function() {
	$(this).closest('form').clearForm();
	M.updateTextFields();
	$('select').formSelect();
});

function getSelectAsincrona(selectId, url, params, getOptionCallback, firstElementIndex) {
	console.log('getSelectAsincrona');
	var selectRef = $('#'+selectId);
	var options = selectRef.children('option');
	
	console.log('url ='+url);
	console.log('params ='+params);
	
	$.getJSON(url, params, function(responseJSON) {
		//Se è definito il parametro firstElementIndex, indica gli elementi iniziali della select da mantenere
		if (firstElementIndex && !isNaN(firstElementIndex)) {
			if (options.length > firstElementIndex) {
				selectRef[0].options.length = firstElementIndex;
			}
		}else {
			options.remove();
		}
		
		$.each(responseJSON, function(index, item) {
			if (getOptionCallback) {
				$('#'+selectId).append(getOptionCallback(item));
			}
		});
		
		//Reinizializzazione della select Materialize
		$('#'+selectId).formSelect();
		
		/*
		 *  Si propaga l'evento di change della select appena ripopolata, dato che non viene propagato automaticamente dalle operazioni precedenti
		 */
		$('#'+selectId).trigger('change');
		
	}).fail(function(jqxhr, textStatus, error) {
		if (window.console) {
			console.log( "Errore in reperimento dati select in getSelectAsincrona: " + textStatus + ", " + error);
		}         
	});
	
}

function getSelectAsincronaRef($selectRef, url, params, getOptionCallback, firstElementIndex, successCallback) {
	console.log('getSelectAsincronaRef');
	var options = $selectRef.children('option');
	
	console.log('$selectRef ='+$selectRef);
	console.log('url ='+url);
	console.log('params ='+params);
	/*console.log('getOptionCallback ='+getOptionCallback);
	console.log('firstElementIndex ='+firstElementIndex);
	console.log('successCallback ='+successCallback);*/
	
	$.getJSON(url, params, function(responseJSON) {		
		//Se è definito il parametro firstElementIndex, indica gli elementi iniziali della select da mantenere
		if (firstElementIndex && !isNaN(firstElementIndex)) {
			if (options.length > firstElementIndex) {
				$selectRef[0].options.length = firstElementIndex;
			}
		}else {
			options.remove();
		}
		
		$.each(responseJSON, function(index, item) {
			if (getOptionCallback) {
				$selectRef.append(getOptionCallback(item));
			}
		});
		
		//Reinizializzazione della select Materialize
		$selectRef.formSelect();
		
		/*
		 *  Si propaga l'evento di change della select appena ripopolata, dato che non viene propagato automaticamente dalle operazioni precedenti
		 */
		$selectRef.trigger('change');
		
		if (successCallback) {
			successCallback($selectRef);
		}
		
	}).fail(function(jqxhr, textStatus, error) {
		if (window.console) {
			console.log( "Errore in reperimento dati select: " + textStatus + ", " + error);
		}         
	});
	
}

function getOption(value, label) {
	var option = new Option(label, value);
	$(option).html(label);
	return option; 
	//return $('<option>').text(label).attr('value', value);
}

function setValoreAsincrono(fieldId, url, params, toStringCallback, reset, successCallback) {
	console.log('setValoreAsincrono');	
	if (toStringCallback) {
		console.log('toStringCallback');
		if (reset) {
			console.log('reset');
			$('#'+fieldId).val('');
			
			//Reinizializzazione dei campi di testo (utile se si sta valorizzando un campo disabilitato)
			M.updateTextFields();
			
			return;
		}
		
		console.log('url ='+url);
		console.log('params ='+params);
		$.getJSON(url, params, function(responseJSON) {
			console.log('fieldId ='+fieldId);
			
			$('#'+fieldId).val(toStringCallback(responseJSON));
			
			//Reinizializzazione dei campi di testo (utile se si sta valorizzando un campo disabilitato)
			console.log('Reinizializzazione dei campi di testo');
			M.updateTextFields(); 
			
			if (successCallback) {
				console.log('successCallback');
				successCallback();
			}
			
		}).fail(function(jqxhr, textStatus, error) {
			if (window.console) {
				console.log( "Errore in reperimento oggetto asincrono: " + textStatus + ", " + error);
			}         
		});
	}	
}

function checkStatoRichiesta(link, stato) {
	swal({
		title: 'Attenzione!',
		html: 'Si desidera modificare lo stato della richiesta in <b>' + stato + '</b>?',
		type: 'warning',
		showCancelButton: true,
		/*confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',*/
		confirmButtonText: 'Sì',
		cancelButtonText: 'No',
	}).then(function (result) {
		if (result.value) {
			console.log('************** THEN: '+result.value);
			window.location.href = $(link).attr('href');
		}else {
			console.log('************** DISMISS: ' + result.dismiss);
		}
	});
   
	return false;
}

function checkStatoRichiestaMotivazione(link, stato) {
	swal({
		title: 'Attenzione!',
		html: 'Si desidera modificare lo stato della richiesta in <b>' + stato + '</b>?',
		type: 'warning',
		input: 'textarea',
		inputPlaceholder: 'Inserire una motivazione',
		showCancelButton: true,
		confirmButtonText: 'Sì',
		cancelButtonText: 'No',
		preConfirm: function(motivazione) {
			return new Promise(function(resolve) {
				if (!motivazione || motivazione.trim() == '') {
					swal.showValidationError('Inserire una motivazione');  
			    }
				  
				resolve();
			})
		},
		onOpen: function() {
			document.activeElement.blur();
		},
		
	}).then(function(result) {
		if (result && result.value && result.value.trim() != '') {
			post($(link).attr('href'), {motivazione: result.value});
		}
		
		//window.location.href = $(link).attr('href');
	}, function (dismiss) {
	});
		 
	return false;
}

function checkInoltra(link) {
	return checkStatoRichiesta(link, 'INOLTRATA');
}

function checkEsegui(link) {
	return checkStatoRichiesta(link, 'ESEGUITA');
}

function checkAnnulla(link) {
	return checkStatoRichiestaMotivazione(link, 'ANNULLATA');
}

function checkRestituisci(link) {
	return checkStatoRichiestaMotivazione(link, 'RESTITUITA');
}

function checkLiquida(link) {
	return checkStatoRichiesta(link, 'LIQUIDATA');
}

function registerOutsideClickHandler($outsideElement, $insideElement, handler) {
	
	$outsideElement.on("click", function(event) {
		
		/*if (!$insideElement.is(event.target) && $insideElement.has(event.target).length == 0) {
			handler();
		}*/
		
		if (!checkIfEventInsideElement(event, $insideElement)) {
			handler(event);
		}
		
	});
}

function checkIfEventInsideElement(event, $element) {
	return $element.is(event.target) || $element.has(event.target).length > 0;
} 


//Esempio di utilizzo: post('/dettaglio/', {idRichiesta: '3'});
function post(path, parameters) {
	var $form = $('<form></form>');

    $form.attr("method", "post");
    $form.attr("action", path);

    $.each(parameters, function(key, value) {
    	console.log('CHIAVE POST: '+key+' con valore '+value);
        var $field = $('<input></input>');

        $field.attr("type", "hidden");
        $field.attr("name", key);
        $field.attr("value", value);

        $form.append($field);
    });

    $(document.body).append($form);
    $form.submit();
}

$('.datepicker').on('mousedown', function(event) {
	/*
	 * Work-around per risolvere il problema di apertura della modale su Chrome
	 */
	event.preventDefault();
})

$('.timepicker').on('mousedown', function(event) {
	/*
	 * Work-around per risolvere il problema di apertura della modale su Chrome
	 */
	event.preventDefault();
})

Array.prototype.isArray = true;

function inizializzaMultiselect($selectRef) {
	$selectRef.siblings('ul').prepend('<li id=sm_select_all><span>Seleziona tutto</span></li>');
	$selectRef.siblings('ul').find('li#sm_select_all').on('click', function () {
  	  var $elemento = $(this), 
  	  $elemento_span = $elemento.find('span'),
  	  isSeleziona = $elemento_span.text() == 'Seleziona tutto',
  	  testo = isSeleziona ? 'Cancella selezione' : 'Seleziona tutto';
  	  $elemento_span.text(testo);
  	  $elemento.siblings('li').filter(function() {
  		  return $(this).find('input').prop('checked') != isSeleziona;
  		}).click();
  	});
}


function checkConvalidaComunicazione(link) {
	return checkStatoComunicazione(link, 'CONVALIDATA');
}

function checkAutorizzaComunicazione(link) {
	return checkStatoComunicazione(link, 'AUTORIZZATA');
}

function checkRespingiComunicazione(link) {
	return checkStatoComunicazioneMotivazione(link, 'RESPINTA');
}

function checkAnnullaComunicazione(link) {
	return checkStatoComunicazioneMotivazione(link, 'ANNULLATA');
}

function checkStatoComunicazione(link, stato) {
	swal({
		title: 'Attenzione!',
		html: 'Si desidera modificare lo stato della comunicazione in <b>' + stato + '</b>?',
		type: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Sì',
		cancelButtonText: 'No',
	}).then(function (result) {
		if (result.value) {
			console.log('************** THEN: '+result.value);
			window.location.href = $(link).attr('href');
		}else {
			console.log('************** DISMISS: ' + result.dismiss);
		}
	});
   
	return false;
}

function checkStatoComunicazioneMotivazione(link, stato) {
	swal({
		title: 'Attenzione!',
		html: 'Si desidera modificare lo stato della comunicazione in <b>' + stato + '</b>?',
		type: 'warning',
		input: 'textarea',
		inputPlaceholder: 'Inserire una motivazione',
		showCancelButton: true,
		confirmButtonText: 'Sì',
		cancelButtonText: 'No',
		preConfirm: function(motivazione) {
			return new Promise(function(resolve) {
				if (!motivazione || motivazione.trim() == '') {
					swal.showValidationError('Inserire una motivazione');  
			    }
				  
				resolve();
			})
		},
		onOpen: function() {
			document.activeElement.blur();
		},
		
	}).then(function(result) {
		if (result && result.value && result.value.trim() != '') {
			post($(link).attr('href'), {motivazione: result.value});
		}
		
	}, function (dismiss) {
	});
		 
	return false;
}

function checkRespingiDomanda(link) {
	return checkStatoDomandaMotivazione(link, 'RESPINTA');
}

function checkAnnullaDomanda(link) {
	return checkStatoComunicazioneMotivazione(link, 'ANNULLATA');
}

function checkConvalidaDomanda(link) {
	return checkStatoDomanda(link, 'CONVALIDATA');
}

function checkStatoDomanda(link, stato) {
	swal({
		title: 'Attenzione!',
		html: 'Si desidera modificare lo stato della domanda in <b>' + stato + '</b>?',
		type: 'warning',
		showCancelButton: true,
		confirmButtonText: 'Sì',
		cancelButtonText: 'No',
	}).then(function (result) {
		if (result.value) {
			console.log('************** THEN: '+result.value);
			window.location.href = $(link).attr('href');
		}else {
			console.log('************** DISMISS: ' + result.dismiss);
		}
	});
   
	return false;
}

function checkStatoDomandaMotivazione(link, stato) {
	swal({
		title: 'Attenzione!',
		html: 'Si desidera modificare lo stato della domanda in <b>' + stato + '</b>?',
		type: 'warning',
		input: 'textarea',
		inputPlaceholder: 'Inserire una motivazione',
		showCancelButton: true,
		confirmButtonText: 'Sì',
		cancelButtonText: 'No',
		preConfirm: function(motivazione) {
			return new Promise(function(resolve) {
				if (!motivazione || motivazione.trim() == '') {
					swal.showValidationError('Inserire una motivazione');  
			    }
				  
				resolve();
			})
		},
		onOpen: function() {
			document.activeElement.blur();
		},
		
	}).then(function(result) {
		if (result && result.value && result.value.trim() != '') {
			post($(link).attr('href'), {motivazione: result.value});
		}
		
	}, function (dismiss) {
	});
		 
	return false;
}

function disableFields(){
	$(document).ready(function(){
		var allInputs = $("input");
		setDisabled(allInputs);
		var nestedInputs = $("div input");
		setDisabled(nestedInputs);
	});
}

function setDisabled(elements){
	for(var i = 0; i < elements.length; i++) {
		  elements[i].disabled = true;
		}
}

/*Disabilito tutti i campi e rimuovo i bottoni relativi alla tabella 
tabellaAttivita*/
function disableAndHide(){
	var input = document.querySelectorAll('input');

	// seleziona tutti gli elementi del form
	//var formElements = form.elements;

	// scorri gli elementi del form e disabilita ciascuno
	for (var i = 0; i < input.length; i++) {
	  input[i].disabled = true;
	}
	
	const table = document.getElementById('tabellaAttivita');
	/*const btnElements = table.querySelectorAll('a');
	for(var x = 0; x < btnElements.length; x++){
		btnElements[i].disabled = true;
	}*/
	$(".action").hide();
	$("#aggiungiAttivita").hide();

}

function saveCheckboxStateOnChange(checkboxId){
	const checkbox = document.getElementById(checkboxId);
	checkbox.addEventListener('change', function() {
	  localStorage.setItem('checkboxState', this.checked);
	});
}

function setCheckboxState(checkboxId){
	const checkbox = document.getElementById(checkboxId);
	const checkboxState = localStorage.getItem('checkboxState');

	if (checkboxState) {
	  checkbox.checked = JSON.parse(checkboxState);
	}
}

