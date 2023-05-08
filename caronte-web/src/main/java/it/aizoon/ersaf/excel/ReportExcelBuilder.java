package it.aizoon.ersaf.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.ReportDTO;

public class ReportExcelBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "Elenco_report";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {
		String titoloSheetReport = "Distinte";
		List<String> headersSheetDisinte = null;
		headersSheetDisinte = Arrays.asList(new String[] { "Spedizioniere", "Stato richiesta", "Data esecuzione richiesta",
						"Documento mezzo trasporto", "N. Certificato", "Mittente", "Destinatario", "Tariffa" });
		ArrayList<List<Object>> listaValoriSheetDistinte = getValoriSheetDistinte((List<ReportDTO>) (List<?>) items);

		addExcelSheet(titoloSheetReport, headersSheetDisinte, listaValoriSheetDistinte);
	}

	private ArrayList<List<Object>> getValoriSheetDistinte(List<ReportDTO> items) {
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		for (ReportDTO distinta : items) {
			valori = new ArrayList<Object>();
			valori.add(distinta.getDenomSpedizioniere());
			valori.add(distinta.getDescStatoRichiesta());
			valori.add(distinta.getDataEsecuzione() != null ? DATE_FORMAT.format(distinta.getDataEsecuzione()) : "");
			valori.add(distinta.getIdentifMezzoTrasporto());
			valori.add(distinta.getNumeroCertificato());
			valori.add(distinta.getDenomMittente());
			valori.add(distinta.getDenomDestinatario());
			valori.add(distinta.getTariffa() != null ? String.valueOf(distinta.getTariffa()) : "");
			listaValori.add(valori);
		}

		return listaValori;
	}

}
