<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title><sitemesh:write property='title'/></title>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!--Import materialize.css-->
<link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/materialize.css"/>"
  media="screen,projection" />
  
<link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/sweetalert2.min.css"/>" media="screen" />

<!--  DataTables -->
<link type="text/css" rel="stylesheet" href="<spring:url value="/resources/css/jquery.dataTables.min.css"/>" media="screen" />
  
<link href="<spring:url value="/resources/css/style.css"/>" rel="stylesheet">

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<sitemesh:write property='head'/>
    
</head>
<body>

  <c:if test="${page_error != null }">
      <div class="alert alert-error">
          <button type="button" class="close" data-dismiss="alert">&times;</button>
          <h4>Error!</h4>
              ${page_error}
      </div>
  </c:if>

  <sitemesh:write property='body'/>
    
 <!--Import jQuery before materialize.js-->
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script>
    if (!window.jQuery) {
      document
          .write('<script src="<spring:url value="/resources/js/jquery-3.2.1.min.js"/>"><\/script>');
    }
  </script>
  <script type="text/javascript" src='<spring:url value="/resources/js/materialize.js"/>'></script>
  <!-- Paginazione -->
  <script type="text/javascript" src='<spring:url value="/resources/js/pagination.js"/>'></script>
  
  <c:set var="locale">${pageContext.response.locale}</c:set>
  <c:if test="${locale != 'en'}">
    <!-- localizzazione js (solo per lingue diverse da inglese) ${pageContext.response.locale} -->
    <script type="text/javascript"
      src='<spring:url value="/resources/js/lang/materialize_${pageContext.response.locale}.js"/>'></script>
  </c:if>
  
  <!-- Polyfill per supporto Promises per IE 11 -->
  <script type="text/javascript" src='<spring:url value="/resources/js/promise-polifyll-core.min.js"/>'></script>
  <script type="text/javascript" src='<spring:url value="/resources/js/sweetalert2.min.js"/>'></script>

  <!-- DataTables -->
  <script type="text/javascript" src='<spring:url value="/resources/js/jquery.dataTables.min.js"/>'></script>
  
  <script type="text/javascript" src='<spring:url value="/resources/js/util.js"/>'></script>

  <script>
    $(document).ready(function() {
      //Inizializzazione delle select (obbligatoria)
      $('select').formSelect();
      
      //Inizializzazione menù laterale (per risoluzioni medie e piccole)
      $(".sidenav").sidenav({
    	  onCloseEnd : function(el) {
          collapseAll(el[0]);
        }
      });

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
      });
      
      /*
       * Fix per evitare che la checkbox dentro l'input-field sia più piccola di un campo di input (testo o select) 
       * e che quindi un altro input-field si possa sistemare direttamente al di sotto del div della checkbox 
       */
      $('div.input-field').has('input[type=checkbox]').addClass('adjust-checkbox');
      
      $('.carousel.carousel-slider').carousel({
    	  fullWidth: true,
    	  duration: 400,
    	  height: 400,
    	  indicators: true
      });
      
      setInterval(function() {
    	  $('.carousel').carousel('next');
    	}, 10000); 
      
      <c:if test="${page_alert_success != null }">
      swal({
        title : '<c:out value = "${page_alert_success}"/>',
        type : 'success',
      });
      </c:if>
      
      //Inizializzazione DataTables
      $('.data-table').DataTable({
        //enterDelay : 50
      });
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

</body>
</html>