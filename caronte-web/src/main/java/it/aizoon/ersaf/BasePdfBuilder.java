package it.aizoon.ersaf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import it.aizoon.ersaf.dto.BaseDto;

public abstract class BasePdfBuilder extends AbstractITextPdfView {

	protected List<String> headers = null;
	protected float[] columnWidths = null;
	protected ArrayList<List<String>> listaValori = null;
	protected String fileName;
	protected Rectangle pageSize;
	protected boolean isPageHorizontal = false;
	protected int fontSize;
	private final static int DEFAULT_FONT_SIZE = 8;

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		headers = new ArrayList<String>();
		listaValori = new ArrayList<List<String>>();
		fileName = "";
		setHeadersColonne();
		setColumnWidths();
		setListaValori((List<BaseDto>) model.get("elenco"));
		setFileName();
		setFontSize();

		/* Non è carino scaricare un file senza nome e con solo l'estensione */
		if (fileName == null || fileName.trim().isEmpty()) {
			fileName = "documentoPdf";
		}
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");

		PdfPTable table = new PdfPTable(headers.size());
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(10);
		table.setHeaderRows(1); // imposta la ripetizione della riga di header ogni volta che si cambia pagina

		/*
		 * Se sono state valorizzate le lunghezze delle colonne AND queste sono pari al
		 * numero delle colonne di header specificate: le setto. Altrimenti:
		 * equidistanti
		 */
		if (columnWidths != null && columnWidths.length == headers.size()) {
			table.setWidths(columnWidths);
		}

		/* Font per l'header: bold bianco su sfondo blu */
		Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA, fontSize > 0 ? fontSize : DEFAULT_FONT_SIZE,
				Font.BOLD);
		fontHeader.setColor(BaseColor.WHITE);
		PdfPCell headerCell = new PdfPCell();
		headerCell.setBackgroundColor(new BaseColor(66, 139, 202));
		headerCell.setPadding(5);
		headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);

		/* Font per il body: normale nero su sfondo bianco */
		Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, fontSize > 0 ? fontSize : DEFAULT_FONT_SIZE);
		fontBody.setColor(BaseColor.BLACK);
		PdfPCell bodyCell = new PdfPCell();
		bodyCell.setPadding(5);

		/* Intestazioni tabella */
		for (String header : headers) {
			headerCell.setPhrase(new Phrase(header, fontHeader));
			table.addCell(headerCell);
		}

		/*
		 * Corpo della tabella. Se la lista dei valori passati è vuota scrivo lo stesso
		 * una riga vuota. Altrimenti itext si arrabbia quando tenta di chiudere il
		 * documento: "The document has no pages". o_o bah ..
		 */
		if (listaValori.isEmpty()) {
			for (int i = 0; i < headers.size(); i++) {
				bodyCell.setPhrase(new Phrase("", fontBody));
				table.addCell(bodyCell);
			}
		} else {
			for (List<String> riga : listaValori) {
				for (String colonna : riga) {
					bodyCell.setPhrase(new Phrase(colonna, fontBody));
					table.addCell(bodyCell);
				}
			}
		}

		document.add(table);

	}

	/** Setta gli headers della tabella nella List<String> "headers" */
	protected abstract void setHeadersColonne();

	/**
	 * Permette di valorizzare nell'array "columnWidths" l'attributo width di ogni
	 * colonna della tabella. Default = colonne equidistanti
	 */
	protected abstract void setColumnWidths();

	/**
	 * Setta i dati del body della tabella nella List<List<String>> "listaValori"
	 */
	protected abstract <TDTO extends BaseDto> void setListaValori(List<TDTO> items);

	/** Setta il nome del file nella variabile "fileName" */
	protected abstract void setFileName();

	/** Setta il font size di tutto il documento nella variabile "fontSize" */
	protected abstract void setFontSize();

}
