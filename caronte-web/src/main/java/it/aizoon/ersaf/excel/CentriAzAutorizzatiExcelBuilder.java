package it.aizoon.ersaf.excel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.ReportDTO;

public class CentriAzAutorizzatiExcelBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "ElencoCentriAziendaliAutorizzati";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {
		String titoloSheetReport = "Centri Aziendali";
		List<String> headersSheetDisinte = null;
		logger.debug("-- Setto i titoli da visualizzare nell'Excel");
		headersSheetDisinte = Arrays.asList(new String[] { "Stato", "Ragione sociale", "CUAA", "Partita IVA", "Provincia", "Comune", "Indirizzo", 
				"Codice centro aziendale","Denominazione centro aziendale", "Numero ruop","Cellulare", "Email", "Ispettore", "Tipo passaporto", "Tariffa",
				"Data variazione centro aziendale", "Data richiesta passaporto", "Data Variazione passaporto","Numero Protocollo","Data Protocollo" });
		ArrayList<List<Object>> listaValoriSheetAziende = getValoriSheetAziende((List<ReportDTO>) (List<?>) items);

		addExcelSheet(titoloSheetReport, headersSheetDisinte, listaValoriSheetAziende);
	}	

	private ArrayList<List<Object>> getValoriSheetAziende(List<ReportDTO> items) {
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;		
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		for (ReportDTO centroAz : items) {
			valori = new ArrayList<Object>();
			valori.add(centroAz.getDescStatoAzienda());
			valori.add(centroAz.getRagioneSociale());
			valori.add(centroAz.getCuaa());
			valori.add(centroAz.getPartitaIva() != null ? centroAz.getPartitaIva() : "");
			valori.add(centroAz.getSiglaProvincia());
			valori.add(centroAz.getDenomComune());
			valori.add(centroAz.getIndirizzo() != null ? centroAz.getIndirizzo() : "");
			valori.add(centroAz.getCodCentroAziendale());
			valori.add(centroAz.getDenomCentroAziendale() != null ? centroAz.getDenomCentroAziendale() : "");
			valori.add(centroAz.getCodiceRuop() != null ? centroAz.getCodiceRuop() : "");
			valori.add(centroAz.getCellulare() != null ? centroAz.getCellulare() : "");
			valori.add(centroAz.getEmail() != null ? centroAz.getEmail() : "");
			valori.add(centroAz.getReferenteIspettore() != null ? centroAz.getReferenteIspettore() : "");
			valori.add(centroAz.getDescTipologiaPassaporto() != null ? centroAz.getDescTipologiaPassaporto() : "");		
			valori.add(centroAz.getTariffa() != null ? centroAz.getTariffa() : "");
			valori.add(centroAz.getDataVariazioneStato()!= null ? DATE_FORMAT.format(centroAz.getDataVariazioneStato()) : ""); // DATA VARIAZIONE CENTRO AZIENDALE
			valori.add(centroAz.getDataRichiestaPassaporto()!= null ? DATE_FORMAT.format(centroAz.getDataRichiestaPassaporto()) : "");
			valori.add(centroAz.getDataVariazionePassaporto()!= null ? DATE_FORMAT.format(centroAz.getDataVariazionePassaporto()) : "");	
			valori.add(centroAz.getNumeroProtocollo() != null ? centroAz.getNumeroProtocollo() : "");
			valori.add(centroAz.getDataProtocollo() != null ? DATE_FORMAT.format(centroAz.getDataProtocollo()) : "");
			
			listaValori.add(valori);
		}

		return listaValori;
	}

}
