package it.aizoon.ersaf.excel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.ReportDTO;

public class ReportExcelCampioniBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "ElencoCampioni";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {
		String titoloSheetReport = "Comunicazioni";
		List<String> headersSheetDisinte = null;
		logger.debug("-- Setto i titoli da visualizzare nell'Excel");
		
		headersSheetDisinte = Arrays.asList(new String[] { "Ragione Sociale", "Numero ruop", "Numero verbale", "Data controllo", "Codice campione", 
				"Codice centro aziendale ", "Matrice oggetto del prelievo ", "Genere", "Specie", "Esito analisi",  "Data rapporto di prova",  "Numero rapporto di prova",
				"Organismo nocivo", "Ricerca - Accertato", "Note", "Cofinanziato"});
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
			valori.add(comunicaz.getCodiceRuop());
			valori.add(comunicaz.getNumeroVerbale() != null ? comunicaz.getNumeroVerbale() : "");
			valori.add(comunicaz.getDataControllo() != null ? DATE_FORMAT.format(comunicaz.getDataControllo()) : "");
			String codCampione ="";
			if(comunicaz.getCodCampioneFine() != null && !comunicaz.getCodCampioneFine().isEmpty()){
				codCampione = comunicaz.getCodCampioneInizio()+" - "+comunicaz.getCodCampioneFine();
			}
			else{
				codCampione = comunicaz.getCodCampioneInizio();
			}
			valori.add(codCampione);
			//valori.add(comunicaz.getCodCampione());
			valori.add(comunicaz.getCodCentroAziendale());
			valori.add(comunicaz.getDescrizione());
			valori.add(comunicaz.getDenomGenere() != null ? comunicaz.getDenomGenere() : "");
			valori.add(comunicaz.getDenomSpecie() != null ? comunicaz.getDenomSpecie() : "");
			valori.add(comunicaz.getEsitoRdp());
			valori.add(comunicaz.getDataRdp() != null ? DATE_FORMAT.format(comunicaz.getDataRdp()) : "");
			valori.add(comunicaz.getNumRdp() != null ? comunicaz.getNumRdp() : "");
			valori.add(comunicaz.getDescEstesa());
			valori.add(comunicaz.getFlRicercaAccertato());
			valori.add(comunicaz.getNote() != null ? comunicaz.getNote() : "");
			valori.add(comunicaz.getCofinanziato());
			
			listaValori.add(valori);
		}

		return listaValori;
	}

}
