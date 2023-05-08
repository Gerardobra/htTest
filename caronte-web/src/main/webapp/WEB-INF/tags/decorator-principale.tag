<%@ tag description="Template per decorator SiteMesh" pageEncoding="UTF-8"%>
<%@ attribute name="titleText" required="true" %>
<%@ attribute name="materializeCss" required="true" %>
<%@ attribute name="colorCode" required="true" %>
<%@ attribute name="gradientColor" required="true" %>
<%@ attribute name="navContent" fragment="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="caronte" uri="/WEB-INF/caronte.tld"%>
<c:set var="baseURL" value="${pageContext.request.localName}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${titleText}</title>
<link rel="icon" type="image/png" href="<spring:url value="/resources/img/favicon.png"/>" />

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/${materializeCss}"/>"
  media="screen,projection" />
<!-- Animate CSS -->
<link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/animate.min.css"/>" media="screen" />

<link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/sweetalert2.min.css"/>" media="screen" />

<!--  DataTables -->
<link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/jquery.dataTables.min.css"/>" media="screen" />

<link href="<spring:url value="/resources/css/style.css"/>" rel="stylesheet">

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<style>
thead {
  background-color: ${colorCode} !important;
  color: white;
}

.primary-color {
  color: ${colorCode}!important;
}

.bg-primary-color {
  background-color: ${colorCode}!important;
}

/*PICKER MODIFICHE COLORE*/
.datepicker-date-display {
    background-color: ${colorCode} !important;
}

.datepicker-table thead {
    background: transparent !important;
}

.datepicker-table thead th {
    font-weight: 900 !important;
    color: black !important;
}

.datepicker-table td.is-today:not(.is-selected) button {
    color: #f44336; 
    font-weight: 700!important;
}

.preloader-background {
   display: flex;
   align-items: center;
   justify-content: center;
   background-color: #eee;
   
   position: fixed;
   z-index: 100;
   top: 0;
   left: 0;
   right: 0;
   bottom: 0; 
}

#loader-wrapper {
 position:fixed;
 top:0;
 left:0;
 width:100%;
 height:100%;
 z-index:1000
}
#loader-wrapper .loader-section {
 position:fixed;
 top:0;
 width:51%;
 height:100%;
 background:#eceff1;
 z-index:1000;
 -webkit-transform:translateX(0);
 -ms-transform:translateX(0);
 transform:translateX(0)
}
#loader-wrapper .loader-section.section-left {
 left:0
}
#loader-wrapper .loader-section.section-right {
 right:0
}
#loader {
 display:block;
 position:absolute;
 left:50%;
 top:50%;
 width:150px;
 height:150px;
 margin:-75px 0 0 -75px;
 border-radius:50%;
 border:3px solid transparent;
 border-top-color:#3498db;
 -webkit-animation:spin 2s linear infinite;
 animation:spin 2s linear infinite;
 z-index:1001;
}
#loader:before {
 content:"";
 position:absolute;
 top:5px;
 left:5px;
 right:5px;
 bottom:5px;
 border-radius:50%;
 border:3px solid transparent;
 border-top-color:#e74c3c;
 -webkit-animation:spin 3s linear infinite;
 animation:spin 3s linear infinite
}
#loader:after {
 content:"";
 position:absolute;
 top:15px;
 left:15px;
 right:15px;
 bottom:15px;
 border-radius:50%;
 border:3px solid transparent;
 border-top-color:#f9c922;
 -webkit-animation:spin 1.5s linear infinite;
 animation:spin 1.5s linear infinite
}

@-webkit-keyframes spin {
 0% {
  -webkit-transform:rotate(0deg);
  -ms-transform:rotate(0deg);
  transform:rotate(0deg)
 }
 100% {
  -webkit-transform:rotate(360deg);
  -ms-transform:rotate(360deg);
  transform:rotate(360deg)
 }
}
@keyframes spin {
 0% {
  -webkit-transform:rotate(0deg);
  -ms-transform:rotate(0deg);
  transform:rotate(0deg)
 }
 100% {
  -webkit-transform:rotate(360deg);
  -ms-transform:rotate(360deg);
  transform:rotate(360deg)
 }
}
.loaded #loader-wrapper {
 visibility:hidden;
 -webkit-transform:translateY(-100%);
 -ms-transform:translateY(-100%);
 transform:translateY(-100%);
 -webkit-transition:all 0.3s 1s ease-out;
 transition:all 0.3s 1s ease-out
}
.loaded #loader-wrapper .loader-section.section-left {
 -webkit-transform:translateX(-100%);
 -ms-transform:translateX(-100%);
 transform:translateX(-100%);
 -webkit-transition:all 0.7s 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
 transition:all 0.7s 0.3s cubic-bezier(0.645, 0.045, 0.355, 1)
}
.loaded #loader-wrapper .loader-section.section-right {
 -webkit-transform:translateX(100%);
 -ms-transform:translateX(100%);
 transform:translateX(100%);
 -webkit-transition:all 0.7s 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
 transition:all 0.7s 0.3s cubic-bezier(0.645, 0.045, 0.355, 1)
}
.loaded #loader {
 opacity:0;
 -webkit-transition:all 0.3s ease-out;
 transition:all 0.3s ease-out
}
.progress {
 background-color:rgba(255,64,129,0.22)
}
.no-js #loader-wrapper {
 display:none
}

</style>

<sitemesh:write property='head' />

</head>

<body>

  <!-- div class="preloader-background">
    <div class="preloader-wrapper big active">
      <div class="spinner-layer spinner-blue-only">
        <div class="circle-clipper left">
          <div class="circle"></div>
        </div>
        <div class="gap-patch">
          <div class="circle"></div>
        </div>
        <div class="circle-clipper right">
          <div class="circle"></div>
        </div>
      </div>
    </div>
  </div-->
  
  <header>
    <div class="navbar-fixed">
	    <nav class="nav-extended gradient-45deg-${gradientColor}">
		    <div class="nav-wrapper">
		      <ul class="left hide-on-med-and-down">
		        <li><a class="sidenav-trigger show-on-large" href="#!" data-target="desktop-menu"><i
		            class="material-icons left">menu</i>Menu </a></li>
		      </ul>
		
		      <a href='<spring:url value="/home"/>' class="center brand-logo">${titleText}</a> <a href="#"
		        data-target="mobile-menu" class="sidenav-trigger"><i class="material-icons">menu</i></a>
		      
		      <!-- Dropdown userProfile -->
		      <spring:eval expression="T(it.aizoon.ersaf.security.SecurityUtils).getUtenteLoggato()" var="utente" /> 
		      <ul class="right" style="margin-right:18px;">
		
		        <c:if test="${not empty utente}">
		        <li class="hide-on-med-and-down">
	            <a class="profile-dropdown-trigger" href="#!" style="display: inline-block;" data-target="dropdown1">
	              <i class="material-icons left">account_circle</i> ${utente.denominazione} <i class="material-icons right">arrow_drop_down</i></a>
		        </li>
		        <li class="hide-on-large-only">
	            <a class="profile-dropdown-trigger" href="#!" style="display: inline-block;" data-target="dropdownMedDown">
		          <i class="material-icons left">account_circle</i>&nbsp;<i class="material-icons right">arrow_drop_down</i></a>
		        </li>
		        </c:if>     
		
		      </ul>
		    </div>
		    
		  </nav>
	 
		  <!-- Dropdown userProfile Structure -->
		  <ul id="dropdown1" class="dropdown-content">
		    <li><a href="<spring:url value="/informazioniProfiloUtente"/>"><i class="material-icons left">face</i>Profilo</a></li>
		    
		    <li>		    	
		  		<a href='<spring:url value="/registrazione/report/privacy"/>' class="grey-text text-darken-1">
		  		 <i class="material-icons">lock</i> Informativa privacy
				</a>
	   		</li>
	   
		      <li class="divider"></li>
		    <li><a href='<spring:url value="/logout"/>'><i class="material-icons left">exit_to_app</i>Logout</a></li>
		  </ul>
		  
		  <ul id="dropdownMedDown" class="dropdown-content">
		    <li><a href='<spring:url value="/profilo/home"/>'><i class="material-icons left">face</i>Profilo</a></li>
		      <li class="divider"></li>
		    <li><a href='<spring:url value="/logout"/>'><i class="material-icons left">exit_to_app</i>Logout</a></li>
		  </ul>
		</div>
	
    <caronte:desktop-menu />
	  <caronte:mobile-menu />
	</header>
	
  <main>
	  <div class="container" style="width:100%;">
	    <c:if test="${page_error != null }">
	    <div class="row">
	      <div id="pageError" class="card card-alert red lighten-5">
	        <div class="card-content red-text valign-wrapper">
	          <i class="material-icons large" style="font-size: 36px">error_outline</i><p>&nbsp;${page_error}</p>
	        </div>
	        <button type="button" class="close red-text" aria-label="Chiudi">
	          <span aria-hidden="true">x</span>
	        </button>
	      </div>
	    </div>
	    </c:if>
	    
	    <c:if test="${page_success != null }">
	    <div class="row">
	      <div id="pageSuccess" class="card card-alert green accent-2">
	        <div class="card-content white-text valign-wrapper">
	          <i class="material-icons large" style="font-size: 36px">check_circle</i><p>&nbsp;${page_success}</p>
	        </div>
	        <button type="button" class="close white-text" aria-label="Chiudi">
	          <span aria-hidden="true">x</span>
	        </button>
	      </div>
	    </div>
	    </c:if>
	
	    <sitemesh:write property='body' />
	  </div>
  </main>
  
    <caronte:footer gradientColor="${gradientColor}" />

  <!--Import jQuery before materialize.js-->
  <!-- script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script>
    if (!window.jQuery) {
      document.write('<script src="<spring:url value="/resources/js/jquery-3.2.1.min.js"/>"></script>');
    }
  </script-->
  <script type="text/javascript" src='<spring:url value="/resources/js/jquery-3.2.1.min.js"/>'></script>
  <script type="text/javascript" src='<spring:url value="/resources/js/materialize.min.js"/>'></script>
  
  <script type="text/javascript" src='<spring:url value="/resources/js/pagination.js"/>'></script>
  
  <!-- Polyfill per supporto Promises per IE 11 -->
  <script type="text/javascript" src='<spring:url value="/resources/js/promise-polifyll-core.min.js"/>'></script>
  <script type="text/javascript" src='<spring:url value="/resources/js/sweetalert2.min.js"/>'></script>
  
  <!-- DataTables -->
  <script type="text/javascript" src='<spring:url value="/resources/js/jquery.dataTables.min.js"/>'></script>
  
  <!-- Validate -->
  <script type="text/javascript" src='<spring:url value="/resources/js/jquery.validate.min.js"/>'></script>
  <script type="text/javascript" src='<spring:url value="/resources/js/additional-methods.min.js"/>'></script>
  
  
  <c:set var="locale">${pageContext.response.locale}</c:set>
  <c:if test="${locale != 'en'}">
    <!-- localizzazione js (solo per lingue diverse da inglese) ${pageContext.response.locale} -->
    <script type="text/javascript"
      src='<spring:url value="/resources/js/lang/materialize_${pageContext.response.locale}.js"/>'></script>
    <script type="text/javascript"
      src='<spring:url value="/resources/js/lang/validate_${pageContext.response.locale}.js"/>'></script>
  </c:if>

  <script type="text/javascript" src='<spring:url value="/resources/js/util.js"/>'></script>

  <script>
    $(document).ready(function() {
    	console.log('ON READY DECORATOR', +Date.now());

    	//Inizializzazione collapsible
      $('.collapsible').collapsible();
    	
      //Inizializzazione menù laterale (per risoluzioni medie e piccole)
      $(".sidenav").sidenav({
    	  onCloseEnd : function(el) {
          collapseAll(el[0]);
        }
      });
      
      //Inizializzazione finestre modali
      /*
       *  ATTENZIONE!!!!
       *  L'inizializzazione delle modali deve avvenire prima di quella dei picker, perché anche questi
       *  hanno la classe css modal, e inizializzare le modali dopo di questi li rende automaticamente 
       *  modali
      */
      $('.modal').modal();
      
      //Inizializzazione dei campi data
      $('.datepicker').each(function() {
        $element = $(this);
    
	      $element.datepicker({
	        showMonthAfterYear : true, // Creates a dropdown to control month
	        showClearBtn: true,
	        defaultDate: getDatepickerInitDate($element),
	        setDefaultDate: true,
	        yearRange : 10, // Creates a dropdown of 20 years to control year,
	        autoClose : true, // Close upon selecting a date,
	        firstDay: datepicker_firstDay,
	        format: datepicker_format,
	          i18n : datepicker_i18n,
	          onClose: function () {
	            $(document.activeElement).blur()
	          }
	      });
	    });
      
      $('.datepicker_birthdate').each(function() {
        $element = $(this);
        
        $element.datepicker({
          showMonthAfterYear : true, // Creates a dropdown to control month
	        showClearBtn: true,
	        defaultDate: getDatepickerInitDate($element),
	        setDefaultDate: true,
	        yearRange : 100, // Creates a dropdown of 20 years to control year,
	        autoClose : true, // Close upon selecting a date,
	        maxDate: new Date(), 
	        firstDay: datepicker_firstDay,
	        format: datepicker_format,        
	        i18n : datepicker_i18n,
	        onClose: function () {
	          $(document.activeElement).blur()
	        }
	      });
	    });
      
      $('.timepicker').timepicker({
	      defaultTime: 'now',
	        showClearBtn: true,
	        autoClose: true, // auto close when minute is selected
	        vibrate: true, // vibrate the device when dragging clock hand
	        twelveHour: false, // change to 12 hour AM/PM clock from 24 hour
	        i18n : timepicker_i18n
	    });
      
      //Impostazione delle icone suffisso ai campi di input
      var inputFields = $(".input-field input");
      inputFields.focus(function() {
        var postfix = $(this).siblings('.postfix');
        if (postfix)
          postfix.addClass("active");
      });
      inputFields.blur(function() {
        var postfix = $(this).siblings('.postfix');
        if (postfix)
          postfix.removeClass("active");
      });
      
      console.log('ON READY DECORATOR 7', +Date.now());
      
      //dropdown userProfile
      $(".profile-dropdown-trigger").dropdown({ 
        hover: true,
        coverTrigger: false
      });
      console.log('ON READY DECORATOR 8', +Date.now());
      M.updateTextFields();
      console.log('ON READY DECORATOR 8.5', +Date.now());
      // Inizializzazione delle select (obbligatoria)
      $('select').formSelect();
      
      //Aggiunta alle select multiple della voce Seleziona tutto
      $('select.multiselect').each(function (){
        inizializzaMultiselect($(this));
      });
      
      console.log('ON READY DECORATOR 9', +Date.now());
      // Animate loader off screen
      //$("#loader-container").fadeOut("slow");

      // Inizializzazione dei tab
      $('.tabs').tabs();
      
      // Inizializzazione dei tooltip
      $('.tooltipped').tooltip({
        enterDelay : 50
      });
      
      /*
       * Spostamento dell'input hidden, aggiunto da form:checkbox dopo la checkbox, dopo la label, per fare sì che avvenga la 
       * corretta visualizzazione da parte di Materialize della checkbox, dato che il relativo css usa per la label il 
       * combinatore Adjacent sibling (+) sulla checkbox
       */
      $('input[type=checkbox]').each(function() {
          var name = $(this).attr('name');
          var $label = $(this).parent().find('span');
          var $hidden = $('[name="' + '_' + name + '"]');
          
          if ($label.length && $hidden.length) {
            $hidden.insertAfter($label);
          }
          //$('[name="' + '_' + name + '"]').remove();
      });
      
      /*
       * Fix per evitare che la checkbox dentro l'input-field sia più piccola di un campo di input (testo o select) 
       * e che quindi un altro input-field si possa sistemare direttamente al di sotto del div della checkbox 
       */
      $('div.input-field').has('input[type=checkbox]').addClass('adjust-checkbox');
      
      console.log('PRIMA DI SET TIMEOUT PAGE SUCCESS!!!', +Date.now());
      //Nasconde il messaggio dopo 3 secondi (se esiste nella pagina) 
      if ($('#pageSuccess').length) {
        setTimeout(function() {
          $('#pageSuccess').closest('.row').addClass('animated zoomOut').delay(100).fadeOut();
        }, 3000)
      }
      
      console.log('DOPO SET TIMEOUT PAGE SUCCESS!!!', +Date.now());
      
      // Animate loader off screen
      console.log('Nascondo il loader!', +Date.now());
      $("#loader-container").fadeOut(/*"slow"*/);
      
      //Inizializzazione DataTables
      $('.data-table').DataTable({
        //enterDelay : 50
      });
      
      console.log('FINE ON READY DECORATOR', +Date.now());
    });
    
    /*$.extend($.fn.dataTable.defaults, {
        stripeClasses: ['stripeTableOdd', '']
    });*/
    
    
    /*$.extend( $.fn.pageMe.defaults, {
      activeColor: 'blue',
      } );*/
      
    jQuery.validator.addMethod("twoDecimals", function (value, element) {
    	  //return this.optional(element) || /^(?:\d+|\d+[,.]\d{1,2})$/.test(value);
        return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:[\s\.,]\d{3})+)(?:[\.,]\d{1,2})?$/.test(value);
    }, "Inserire un numero con al massimo due cifre decimali");

    jQuery.validator.addMethod("threeDecimals", function (value, element) {
    	  //return this.optional(element) || /^(?:\d+|\d+[,.]\d{1,3})$/.test(value);
        return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:[\s\.,]\d{3})+)(?:[\.,]\d{1,3})?$/.test(value);
    }, "Inserire un numero con al massimo tre cifre decimali");
    
    jQuery.validator.addMethod("fourDecimals", function (value, element) {
        return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:[\s\.,]\d{3})+)(?:[\.,]\d{1,4})?$/.test(value);
    }, "Inserire un numero con al massimo quattro cifre decimali");

    $.validator.addMethod('minStrict', function (value, element, param) {
    	  var globalizedValue = value.replace(",", ".");
        return this.optional(element) || globalizedValue > param;
    }, $.validator.format( "Inserisci un valore maggiore di {0}" ));
    
    $.validator.methods.range = function (value, element, param) {
    	  var globalizedValue = value.replace(",", ".");
        return this.optional(element) || (globalizedValue >= param[0] && globalizedValue <= param[1]);
    };
    
    $.validator.methods.number = function (value, element) {
    	  return this.optional(element) || /^-?(?:\d+|\d{1,3}(?:[\s\.,]\d{3})+)(?:[\.,]\d+)?$/.test(value);
    };
    
    $.validator.methods.max = function(value, element, param) {
    	  var globalizedValue = value.replace(",", ".");
    	  return this.optional(element) || globalizedValue <= param;
    };
    
    $.validator.setDefaults({
      //onkeyup: false,
      ignore: [],
      errorClass: 'error',
      //validClass: 'valid',
      errorElement : 'div',
      errorPlacement: function(error, element) {
        var placement = $(element).data('error');
        if (placement) {
          $(placement).append(error)
        }else {
          //error.insertAfter(element);
          error.insertAfter($(element).siblings('label:visible'));
        }
      }   
    });
    
    $('form').submit(function() {
    	  $("#loader-container").fadeIn().delay(2000).fadeOut();
        return true;
    });
    
    $.extend($.fn.dataTable.defaults, {
        "searching": false,
        "lengthChange": false,
        "ordering": false,
        "pagingType": "full_numbers",
        "pageLength": 6,
        "language": {
            "url": "<spring:url value="/resources/js/lang/jquery.dataTables_${pageContext.response.locale}.json"/>"
        }
    });
    
  </script>

  <sitemesh:write property='page.script' />
  
  <script>
    $(document).ready(function() {
    	<c:if test="${page_alert_success != null }">
      swal({
        title : '<c:out value = "${page_alert_success}"/>',
        type : 'success',
      });
      </c:if>
    });
  </script>
    
</body>
</html>
