package it.aizoon.ersaf.excel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.ReportDTO;

public class ReportExcelMisureBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "ElencoMisure";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {
		String titoloSheetReport = "Comunicazioni";
		List<String> headersSheetDisinte = null;
		logger.debug("-- Setto i titoli da visualizzare nell'Excel");
		
		headersSheetDisinte = Arrays.asList(new String[] { "Ragione Sociale", "Cuaa", "Partita iva", "Codice ruop", "Codice centro aziendale", 
				"Provincia", "Comune", "Numero verbale misura ufficale", "Data misura uffciale", "Ispettore", "Misura applicata", "Organismo nocivo", 
				"Genere", "Specie", "Numero piante", "Numero verbale constatazione", "Data constatazione", "Esito", "Note"});
		ArrayList<List<Object>> listaValoriSheetComunicazioni = getValoriSheetComunicazioni((List<ReportDTO>) (List<?>) items);

		addExcelSheet(titoloSheetReport, headersSheetDisinte, listaValoriSheetComunicazioni);
	}


	private ArrayList<List<Object>> getValoriSheetComunicazioni(List<ReportDTO> items) {
		
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;		
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		for (ReportDTO comunicaz : items) {
			valori = new ArrayList<Object>();
			valori.add(comunicaz.getRagioneSociale() != null ? comunicaz.getRagioneSociale() : "");
			valori.add(comunicaz.getCuaa() != null ? comunicaz.getCuaa() : "");
			valori.add(comunicaz.getPartitaIva() != null ? comunicaz.getPartitaIva() : "");
			valori.add(comunicaz.getCodiceRuop() != null ? comunicaz.getCodiceRuop() : "");
			valori.add(comunicaz.getCodCentroAziendale() != null ? comunicaz.getCodCentroAziendale() : "");
			valori.add(comunicaz.getProvincia() != null ? comunicaz.getProvincia() : "");
			valori.add(comunicaz.getComune() != null ? comunicaz.getComune() : "");
			valori.add(comunicaz.getNumeroVerbale() != null ? comunicaz.getNumeroVerbale() : "");
			valori.add(comunicaz.getDataMisura() != null ? DATE_FORMAT.format(comunicaz.getDataMisura()) : "");
			valori.add(comunicaz.getIspettore() != null ? comunicaz.getIspettore() : "");
			valori.add(comunicaz.getMisuraApplicata() != null ? comunicaz.getMisuraApplicata() : "");
			valori.add(comunicaz.getDescOrgNocivo() != null ? comunicaz.getDescOrgNocivo() : "");
			valori.add(comunicaz.getDenomGenere() != null ? comunicaz.getDenomGenere() : "");
			valori.add(comunicaz.getDenomSpecie() != null ? comunicaz.getDenomSpecie() : "");
			valori.add(comunicaz.getNumeroPiante() != null ? comunicaz.getNumeroPiante() : "");
			valori.add(comunicaz.getNumeroVerbaleCo() != null ? comunicaz.getNumeroVerbaleCo() : "");
			valori.add(comunicaz.getDataConstatazione() != null ? DATE_FORMAT.format(comunicaz.getDataConstatazione()) : "");
			valori.add(comunicaz.getEsitoConstatazione() != null ? comunicaz.getEsitoConstatazione() : "");
			valori.add(comunicaz.getNoteConstatazione() != null ? comunicaz.getNoteConstatazione() : "");
		
			listaValori.add(valori);
		}

		return listaValori;
	}

}
