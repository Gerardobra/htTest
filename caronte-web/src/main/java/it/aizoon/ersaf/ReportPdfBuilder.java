package it.aizoon.ersaf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.ReportDTO;

public class ReportPdfBuilder extends BasePdfBuilder {

	@Override
	protected void setHeadersColonne() {
		headers.add("Spedizioniere");
		headers.add("Stato richiesta");
		headers.add("Data esecuzione richiesta");
		headers.add("Documento mezzo trasporto");
		headers.add("N. Certificato");
		headers.add("Mittente");
		headers.add("Destinatario");
		headers.add("Tariffa");

	}

	@Override
	protected <TDTO extends BaseDto> void setListaValori(List<TDTO> items) {
		@SuppressWarnings("unchecked")
		final List<ReportDTO> lista = (List<ReportDTO>) (List<?>) items;
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		for (ReportDTO report : lista) {
			List<String> valori = new ArrayList<String>();
			valori.add(report.getDenomSpedizioniere());
			valori.add(report.getDescStatoRichiesta());
			valori.add(report.getDataEsecuzione() != null ? DATE_FORMAT.format(report.getDataEsecuzione()) : "");
			valori.add(report.getIdentifMezzoTrasporto());
			valori.add(report.getNumeroCertificato());
			valori.add(report.getDenomMittente());
			valori.add(report.getDenomDestinatario());
			valori.add(report.getTariffa() != null ? String.valueOf(report.getTariffa()) : "");
			listaValori.add(valori);
		}
	}

	@Override
	protected void setFileName() {
		fileName = "Elenco_report";
	}

	@Override
	protected void setColumnWidths() {
		// NESSUN VALORE => DEFAULT => equidistanti
		// columnWidths = new float[]{};
	}

	@Override
	protected void setFontSize() {
		// NESSUN VALORE => DEFAULT => DEFAULT_FONT_SIZE impostato in BasePdfBuilder
		// fontSize = 10;
	}

	@Override
	protected Document newDocument() {
		// return new Document(PageSize.A4.rotate());
		return new Document(PageSize.A4);
	}

}
