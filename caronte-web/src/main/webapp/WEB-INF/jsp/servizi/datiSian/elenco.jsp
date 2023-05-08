<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

		<div class="col m8 l12 hide-on-small-only">
			<div class="card-panel hoverable row">
				<i class="material-icons primary-color small">info</i> Sezione
				dedicata all'esportazione dei dati presenti nel sistema, dopo aver
				inidicato un periodo di riferimento. Le sotto sezioni riguardano: <br />
				<ul style="list-style-type: circle">
					<li>- Dati secondo il tracciato della fornitura SIAN <br />
						Consente di scaricare i dati dei certificati emessi e numerati in
						un file in formato Excel per la fornitura periodica dei dati al
						SIAN, secondo il tracciato record fornito dal SIAN.
					</li>
					<li>- Dati delle richieste eseguite o liquidate: <br />
						Consente di scaricare i dati delle richieste eseguite o liquidate.
					</li>
				</ul>
			</div>
		</div>

		<div class="nav-content">
			<ul class="tabs">
				<li class="tab col s6 m4 l3"><a class="active" href="#">Dati
						tracciato fornitura Sian</a></li>
				<li class="tab col s6 m4 l3"><a target="_self"
					href="<spring:url value='/import/servizi/datiRichieste/elencoRichieste'/>">Dati
						richieste eseguite/liquidate</a></li>
			</ul>
		</div>

		<div class="card col s12">
			<spring:url value="/import/servizi/datiSian/esportaDatiSian"
				var="formAction" />
			<form:form action="${formAction}" method="get"
				modelAttribute="ricercaDatiSianForm" accept-charset="utf-8">
				<!--****************** FORM DI RICERCA ***************-->
				<div class="card-content col s12">
				<div class="card-title">
						Ricerca ed esporta dati tracciato fornitura Sian
					</div>
					<div class="row">
						<div class="row">
							<div class="input-field col s6 m5 l3">
								<!------ DATEPICKER::DATA PERIODO DA -->
								<label for="idPeriodoDa">Periodo di riferimento: da</label>
								<form:input type='text' id="idPeriodoDa" path="periodoDa"
									cssClass="datepicker" />
								<form:errors path="periodoDa" cssClass="error" />
							</div>
							<div class="input-field col s6 m4 l3">
								<!------ DATEPICKER::DATA PERIODO A -->
								<label for="idPeriodoA">Periodo di riferimento: a</label>
								<form:input type='text' id="idPeriodoA" path="periodoA"
									cssClass="datepicker" />
								<form:errors path="periodoA" cssClass="error" />
							</div>
							<div class="col m4 l6 hide-on-small-only">
								<div class="card-panel hoverable">Selezionare il periodo di
									riferimento di cui si desidera generare e scaricare il file
									excel contenente i dati dei certificati emessi con la relativa
									numerazione.</div>
							</div>
						</div>
						<div class="row">
							<div class="col s12 m6 l6">
								<div class="row">
									<h5 class="header primary-color" style="font-weight: 300;">Filtri
										sul tracciato: Certificati</h5>
									<div class="divider primary-color"></div>
								</div>
								<div class="row">
									<!------ SELECT::NAZIONE MITTENTE -->
									<div class="input-field col s12 m8 l6">
										<form:select id="idNazioneMittente" path="nazioneMittente"
											cssClass="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaNazioni}" itemValue="idNazione"
												itemLabel="denomNazione" />
										</form:select>
										<form:errors path="nazioneMittente" cssClass="error" />
										<label for="idNazioneMittente">Nazione Mittente</label>
									</div>
									<!------ SELECT::NAZIONE DESTINATARIO -->
									<div class="input-field col s12 m8 l6">
										<form:select id="idNazioneDestinatario"
											path="nazioneDestinatario" cssClass="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaNazioni}" itemValue="idNazione"
												itemLabel="denomNazione" />
										</form:select>
										<form:errors path="nazioneDestinatario" cssClass="error" />
										<label for="idNazioneDestinatario">Nazione
											Destinatario</label>
									</div>
								</div>

								<div class="row">
									<!------ SELECT::DOGANA DI ARRIVO -->
									<div class="input-field col s12 m8 l6">
										<form:select id="idDoganaArrivo" path="doganaArrivo"
											cssClass="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${ufficioDoganale}"
												itemValue="idUfficioDoganale"
												itemLabel="denomUfficioDoganale" />
										</form:select>
										<form:errors path="doganaArrivo" cssClass="error" />
										<label for="idDoganaArrivo">Dogana di arrivo</label>
									</div>

									<!------ SELECT::LUOGO ORIGINE -->
									<div class="input-field col s12 m8 l6">
										<form:select id="idluogoOrigineMerce" path="luogoOrigineMerce"
											cssClass="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaNazioni}" itemValue="idNazione"
												itemLabel="denomNazione" />
										</form:select>
										<form:errors path="luogoOrigineMerce" cssClass="error" />
										<label for="idluogoOrigineMerce">Luogo di origine
											della merce (Nazione)</label>
									</div>
								</div>

								<div class="row">
									<!------ SELECT::LUOGO RILASCIO CERTIFICATO -->
									<div class="input-field col s12 m8 l6">
										<form:select id="idLuogoRilascioCertificato"
											path="luogoRilascioCertificato" cssClass="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${ufficioDoganale}"
												itemValue="idUfficioDoganale"
												itemLabel="denomUfficioDoganale" />
										</form:select>
										<form:errors path="luogoRilascioCertificato" cssClass="error" />
										<label for="idLuogoRilascioCertificato">Luogo rilascio
											certificato</label>
									</div>
								</div>
							</div>
							<div class="col s12 m6 l6">
								<div class="row">
									<h5 class="header primary-color" style="font-weight: 300;">Filtri
										sul tracciato: Merci</h5>
									<div class="divider primary-color"></div>
								</div>
								<div class="row">
									<!------ INPUT::CODICE PRODOTTO -->
									<div class="input-field col s12 m8 l6">
										<form:input type="text" id="idCodiceProdotto"
											path="codiceProdotto" />
										<form:errors path="codiceProdotto" cssClass="error" />
										<label for="idCodiceProdotto">Codice Prodotto</label>
									</div>

									<!------ INPUT::CLASSE PRODOTTO -->
									<div class="input-field col s12 m8 l6">
										<form:select id="idClasseProdotto" path="classeProdotto"
											cssClass="validate">
											<form:option value="" label="Selezionare" />
											<form:options items="${listaClassi}" itemValue="idClasse"
												itemLabel="denomClasse" />
										</form:select>
										<form:errors path="classeProdotto" cssClass="error" />
										<label for="idClasseProdotto">Classe prodotto</label>
									</div>
								</div>
								<div class="row">
									<!------ INPUT::MARCHIO PRODOTTO -->
									<div class="input-field col s12 m8 l6">
										<form:input type="text" id="idMarchioProdotto"
											path="marchioProdotto" />
										<form:errors path="marchioProdotto" cssClass="error" />
										<label for="idMarchioProdotto">Marchio Prodotto</label>
									</div>

									<!------ INPUT::CODICE BAYER -->
									<div class="input-field col s12 m8 l6">
										<form:input type="text" id="idCodiceBayer" path="codiceBayer" />
										<form:errors path="codiceBayer" cssClass="error" />
										<label for="idCodiceBayer">Codice Bayer</label>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="card-action">
						<div class="row">
							<div class="col s6 ">
								<button type="button" class="btn clear-form waves-effect">PULISCI</button>
							</div>
							<div class="col s6 right-align">
								<button class="btn confirm waves-effect waves-light"
									type="submit" name="cercaSian">
									CERCA ED ESPORTA<i class="material-icons right">search</i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>