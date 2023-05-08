<div id="datiAnagrafici">
	<%if(request.getParameter("cuaa") != null && !request.getParameter("cuaa").isEmpty()){ %>
	CUAA: ${param.cuaa },
	<%}%>
	<%if(request.getParameter("ruop") != null && !request.getParameter("ruop").isEmpty()){ %>
	CODICE RUOP: ${param.ruop },
	<%}%> 
	<%if(request.getParameter("ragSociale") != null && !request.getParameter("ragSociale").isEmpty()){ %>
	RAGIONE SOCIALE: ${param.ragSociale }
	<%}%>
</div>