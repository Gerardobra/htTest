package it.aizoon.ersaf.excel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.ReportDTO;

public class ReportVivaiExcelBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "ElencoSpecie";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {
		String titoloSheetReport = "Comunicazioni";
		List<String> headersSheetDisinte = null;
		logger.debug("-- Setto i titoli da visualizzare nell'Excel");
		headersSheetDisinte = Arrays.asList(new String[] { "Stato comunicazione", "Tipo comunicazione",
						"Ragione sociale", "Codice fiscale", "Partita iva", "Codice ruop", "Codice centro aziendale", "Genere", "Specie", "Numero piante","Data creazione","Data inoltro"});
		ArrayList<List<Object>> listaValoriSheetComunicazioni = getValoriSheetComunicazioni((List<ReportDTO>) (List<?>) items);

		addExcelSheet(titoloSheetReport, headersSheetDisinte, listaValoriSheetComunicazioni);
	}

	private ArrayList<List<Object>> getValoriSheetComunicazioni(List<ReportDTO> items) {
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;		
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		for (ReportDTO comunicaz : items) {
			valori = new ArrayList<Object>();
			valori.add(comunicaz.getDescStatoComunicazione());
			valori.add(comunicaz.getDescTipoComunicazione());
			valori.add(comunicaz.getRagioneSociale());
			valori.add(comunicaz.getCodiceFiscale()!= null ? comunicaz.getCodiceFiscale() : "");
			valori.add(comunicaz.getPartitaIva()!= null ? comunicaz.getPartitaIva() : "");
			valori.add(comunicaz.getCodiceRuop());
			valori.add(comunicaz.getCodCentroAziendale());
			valori.add(comunicaz.getDenomGenere());
			valori.add(comunicaz.getDenomSpecie());									
			valori.add(comunicaz.getNumeroPiante());
			valori.add(comunicaz.getDataCreazione() != null ? DATE_FORMAT.format(comunicaz.getDataCreazione()) : "");
			valori.add(comunicaz.getDataInoltro() != null ? DATE_FORMAT.format(comunicaz.getDataInoltro()) : "");
			
			listaValori.add(valori);
		}

		return listaValori;
	}

}
