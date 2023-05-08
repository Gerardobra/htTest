package it.aizoon.ersaf.excel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.ReportDTO;

public class ReportExcelControlliBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "ElencoControlli";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {
		String titoloSheetReport = "Comunicazioni";
		List<String> headersSheetDisinte = null;
		logger.debug("-- Setto i titoli da visualizzare nell'Excel");
		
		headersSheetDisinte = Arrays.asList(new String[] { "Ragione Sociale", "Cuaa", "Partita iva", "Codice centro aziendale", "Indirizzo centro aziendale", 
				"Provincia ", "Comune ", "Tipologia attività", "Tipologia attività annotazione", "Numero ruop", "Data controllo", "Numero verbale", "Ispettore", 
				"Organismo nocivo", "Genere", "Specie", "Numero piante", "Esito controllo", "Note", "Campioni prelevati"});
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
			valori.add(comunicaz.getCuaa());
			valori.add(comunicaz.getPartitaIva());
			valori.add(comunicaz.getCodCentroAziendale());
			valori.add(comunicaz.getIndirizzo());
			valori.add(comunicaz.getProvincia());
			valori.add(comunicaz.getComune());
			valori.add(comunicaz.getTipologiaAttivita() != null ? comunicaz.getTipologiaAttivita() : "");
			valori.add(comunicaz.getTipologiaAttivitaDom() != null ? comunicaz.getTipologiaAttivitaDom() : "");
			valori.add(comunicaz.getCodiceRuop());
			valori.add(comunicaz.getDataControllo() != null ? DATE_FORMAT.format(comunicaz.getDataControllo()) : "");
			valori.add(comunicaz.getNumeroVerbale() != null ? comunicaz.getNumeroVerbale() : "");
			valori.add(comunicaz.getIspettore());
			valori.add(comunicaz.getOrganismoNocivo() != null ? comunicaz.getOrganismoNocivo() : "");
			valori.add(comunicaz.getDenomGenere() != null ? comunicaz.getDenomGenere() : "");
			valori.add(comunicaz.getDenomSpecie() != null ? comunicaz.getDenomSpecie() : "");
			valori.add(comunicaz.getNumeroPiante() != null ? comunicaz.getNumeroPiante() : "");
			valori.add(comunicaz.getEsito() != null ? comunicaz.getEsito() : "");
			valori.add(comunicaz.getNote() != null ? comunicaz.getNote() : "");
			valori.add(comunicaz.getCampioniPrelevati());
			
			listaValori.add(valori);
		}

		return listaValori;
	}

}
