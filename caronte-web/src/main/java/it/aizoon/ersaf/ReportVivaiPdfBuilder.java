package it.aizoon.ersaf;

import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.ReportDTO;

public class ReportVivaiPdfBuilder extends BasePdfBuilder {

	@Override
	protected void setHeadersColonne() {		
		headers.add("Stato comunicazione");
		headers.add("Tipo comunicazione");
		headers.add("Ragione sociale");
		headers.add("Codice fiscale");
		headers.add("Partita iva");
		headers.add("Codice ruop");
		headers.add("Codice centro aziendale");
		headers.add("Genere");
		headers.add("Specie");
		headers.add("Numero piante");
	}

	@Override
	protected <TDTO extends BaseDto> void setListaValori(List<TDTO> items) {
		@SuppressWarnings("unchecked")
		final List<ReportDTO> lista = (List<ReportDTO>) (List<?>) items;		
		for (ReportDTO comunicaz : lista) {
			List<String> valori = new ArrayList<String>();
			valori.add(comunicaz.getDescStatoComunicazione());
			valori.add(comunicaz.getDescTipoComunicazione());
			valori.add(comunicaz.getRagioneSociale());
			valori.add(comunicaz.getCodiceFiscale());
			valori.add(comunicaz.getPartitaIva());
			valori.add(comunicaz.getCodiceRuop());
			valori.add(comunicaz.getCodCentroAziendale());
			valori.add(comunicaz.getDenomGenere());
			valori.add(comunicaz.getDenomSpecie());									
			valori.add(comunicaz.getNumeroPiante() != null ? String.valueOf(comunicaz.getNumeroPiante()) : "");
			listaValori.add(valori);
		}
	}

	@Override
	protected void setFileName() {
		fileName = "Elenco_reportVivai";
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
