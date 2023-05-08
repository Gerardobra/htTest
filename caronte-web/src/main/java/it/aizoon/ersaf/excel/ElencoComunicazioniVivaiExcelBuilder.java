package it.aizoon.ersaf.excel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.ReportDTO;

public class ElencoComunicazioniVivaiExcelBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "ElencoComunicazioni";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {
		String titoloSheetReport = "Comunicazioni";
		List<String> headersSheetDisinte = null;
		logger.debug("-- Setto i titoli da visualizzare nell'Excel");
		headersSheetDisinte = Arrays.asList(new String[] { "Stato comunicazione", "Tipo comunicazione",
						"Ragione sociale", "Codice fiscale", "Partita iva", "Codice ruop", "Codice centro aziendale", "Data creazione"});
		ArrayList<List<Object>> listaValoriSheetComunicazioni = getValoriSheetComunicazioni((List<ReportDTO>) (List<?>) items);

		addExcelSheet(titoloSheetReport, headersSheetDisinte, listaValoriSheetComunicazioni);
	}
//Stato comunicazione Tipo comunicazione Operatore Centro aziendale data creazione
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
			valori.add(comunicaz.getDataCreazione() != null ? DATE_FORMAT.format(comunicaz.getDataCreazione()) : "");
			
			listaValori.add(valori);
		}

		return listaValori;
	}

}
