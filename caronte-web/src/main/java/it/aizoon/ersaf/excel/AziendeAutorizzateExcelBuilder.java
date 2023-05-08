package it.aizoon.ersaf.excel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.ReportDTO;

public class AziendeAutorizzateExcelBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "ElencoAziendeAutorizzate";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {
		String titoloSheetReport = "Aziende";
		List<String> headersSheetDisinte = null;
		logger.debug("-- Setto i titoli da visualizzare nell'Excel");
		headersSheetDisinte = Arrays.asList(new String[] { "Stato azienda", "Tipo azienda","Ragione sociale", "Codice fiscale / CUAA", "Partita IVA", "Provincia", "Comune",
									 "Indirizzo","Tipologia attività", "Tipologia attività annotazioni","Codice ruop", "Data registrazione RUOP", "Codice fitok",
									"Email", "PEC", "Cellulare", "Numero Protocollo","Data Protocollo"});
		ArrayList<List<Object>> listaValoriSheetAziende = getValoriSheetAziende((List<ReportDTO>) (List<?>) items);

		addExcelSheet(titoloSheetReport, headersSheetDisinte, listaValoriSheetAziende);
	}	

	private ArrayList<List<Object>> getValoriSheetAziende(List<ReportDTO> items) {
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;		
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		for (ReportDTO azienda : items) {
			valori = new ArrayList<Object>();
			valori.add(azienda.getDescStatoAzienda());
			valori.add(azienda.getDenomTipoSpedizioniere());
			valori.add(azienda.getRagioneSociale());
			if (azienda.getCodiceFiscale() != null) {
				valori.add(azienda.getCodiceFiscale());
			} else if (azienda.getCuaa() != null) {
				valori.add(azienda.getCuaa());
			} else {
				valori.add("");
			}				
			valori.add(azienda.getPartitaIva() != null ? azienda.getPartitaIva() : "");		
			valori.add(azienda.getSiglaProvincia());
			valori.add(azienda.getDenomComune());
			valori.add(azienda.getIndirizzo());
			valori.add(azienda.getTipologiaAttivita() != null ? azienda.getTipologiaAttivita() : "");
			valori.add(azienda.getTipologiaAttivitaAnnotazioni() != null ? azienda.getTipologiaAttivitaAnnotazioni() : "");
			valori.add(azienda.getCodiceRuop());
			valori.add(azienda.getDataRegistrazioneRuop() != null ? DATE_FORMAT.format(azienda.getDataRegistrazioneRuop()) : "");
			valori.add(azienda.getCodiceFitok() != null ? azienda.getCodiceFitok() : "");
			valori.add(azienda.getEmail() != null ? azienda.getEmail() : "");
			valori.add(azienda.getPec() != null ? azienda.getPec() : "");
			valori.add(azienda.getCellulare() != null ? azienda.getCellulare() : "");	
			valori.add(azienda.getNumeroProtocollo() != null ? azienda.getNumeroProtocollo() : "");
			valori.add(azienda.getDataProtocollo() != null ? DATE_FORMAT.format(azienda.getDataProtocollo()) : "");
			
			
			listaValori.add(valori);
		}

		return listaValori;
	}

}
