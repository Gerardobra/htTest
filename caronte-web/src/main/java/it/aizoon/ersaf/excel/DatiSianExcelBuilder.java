package it.aizoon.ersaf.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.DatiSianDTO;
import it.aizoon.ersaf.dto.MerceRichiestaDatiSianDTO;
import it.aizoon.ersaf.dto.RiexportDatiSianDTO;

public class DatiSianExcelBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "Elenco_dati_certificati";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {
		
		/* PREPARAZIONE DATI SHEET CERTIFICATI INIZIO */
		// Titolo sheet Certificati
		String titoloSheetCertificati = "Tracciato Rec.1 - Certificati";
		// Setto i valori degli headers della sheet dei certificati
		List<String> headersSheetCertificati = Arrays.asList(
				new String[] { "Tipo operazione", "Numero", "Regione", "Tipo", "Mittente", "Numero RUOP Mittente",
						"Indirizzo Mitt", "CAP Mittente", "Codice Comune Mitt", "Comune Mitt", "Provincia Mitt",
						"Città Mitt", "Nazione Mitt", "Numero RUP Destinatario", "Regione RUP", "Stato RUP",
						"Destinatario", "Indirizzo Dest", "CAP Destinatario", "Codice Comune Dest", "Comune Dest",
						"Provincia Dest", "Città Dest", "Nazione Dest", "Dogana Arrivo", "Paese Servizio Destinatario",
						"Luogo origine", "Modo Trasporto", "Mezzo Trasporto", "Id Mezzo", "Punto entrata",
						"Supplemento", "Trattamento", "Prov Rilascio", "Luogo Rilascio", "Data", "Ispettore" });
		// Recupero e setto il contenuto da scrivere nella sheet dei certificati
		ArrayList<List<Object>> listaValoriSheetCertificati = getValoriSheetCertificati((List<DatiSianDTO>) items);
		/* PREPARAZIONE DATI SHEET CERTIFICATI FINE */
		
		/* *********************************************************** */
		
		/* PREPARAZIONE DATI SHEET RIEXPORT INIZIO */
		// Titolo sheet Riexport
		String titoloSheetRiexport = "Tracciato Rec.2 - Riesport";
		// Setto i valori degli headers della sheet del riexport
		List<String> headersSheetRiexport = null;
		headersSheetRiexport = Arrays.asList(new String[] { "numero", "paeseRiesp", "paeseOrigine", "numCertOrigine" });
		// Recupero e setto il contenuto da scrivere nella sheet del riexport
		ArrayList<List<Object>> listaValoriSheetRiexport = getValoriSheetRiexport((List<RiexportDatiSianDTO>) model.get("listaRiexport"));
		/* PREPARAZIONE DATI SHEET RIEXPORT FINE */

		/* *********************************************************** */

		/* PREPARAZIONE DATI SHEET MERCI INIZIO */
		// Titolo sheet Certificati
		String titoloSheetMerci = "Tracciato Rec.3 - Merci";
		// Setto i valori degli headers della sheet delle merci
		List<String> headersSheetMerci = null;
		headersSheetMerci = Arrays.asList(new String[] { "numero", "marchio", "numColli", "natura", "classe",
				"codiceProdotto", "codiceBayer", "paeseOrigine", "um", "quantità" });
		// Recupero e setto il contenuto da scrivere nella sheet dei certificati

		ArrayList<List<Object>> listaValoriSheetMerci = getValoriSheetMerci((List<MerceRichiestaDatiSianDTO>) model.get("listaMerci"));
		/* PREPARAZIONE DATI SHEET CERTIFICATI FINE */
		

		// Aggiungo al documento (indirettamente) la sheet dei certificati con Titolo,
		// Headers e Valori recuperati
		addExcelSheet(titoloSheetCertificati, headersSheetCertificati, listaValoriSheetCertificati);
		
		// Aggiungo al documento (indirettamente) la sheet del riexport con Titolo, Headers e Valori recuperati
		addExcelSheet(titoloSheetRiexport, headersSheetRiexport, listaValoriSheetRiexport);

		// Aggiungo al documento (indirettamente) la sheet delle merci con Titolo, Headers e Valori recuperati
		addExcelSheet(titoloSheetMerci, headersSheetMerci, listaValoriSheetMerci);

	}

	private ArrayList<List<Object>> getValoriSheetCertificati(List<DatiSianDTO> items) {
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		for (DatiSianDTO datiSian : items) {
			valori = new ArrayList<>();
			valori.add(datiSian.getTipoOperazione());
			valori.add(datiSian.getNumero());
			valori.add(datiSian.getRegione());
			valori.add(datiSian.getTipo());
			valori.add(datiSian.getMittente());
			valori.add(datiSian.getNumeroRupMittente() != null ? datiSian.getNumeroRupMittente() : "");
			valori.add(datiSian.getIndirizzoMitt());
			valori.add(datiSian.getCapMittente());
			valori.add(datiSian.getCodComuMitt());
			valori.add(datiSian.getComuneMitt());
			valori.add(datiSian.getProvinciaMitt());
			valori.add(datiSian.getCittaMitt() != null ? datiSian.getCittaMitt() : "");
			valori.add(datiSian.getNazioneMitt());
			valori.add(datiSian.getNumeroRupDestinatario() != null ? datiSian.getNumeroRupDestinatario() : "");
			valori.add(datiSian.getRegioneRup() != null ? datiSian.getRegioneRup() : "");
			valori.add(datiSian.getStatoRup() != null ? datiSian.getStatoRup() : "");
			valori.add(datiSian.getDestinatario());
			valori.add(datiSian.getIndirizzoDest());
			valori.add(datiSian.getCapDestinatario());
			valori.add(datiSian.getCodComuDest() != null ? datiSian.getCodComuDest() : "");
			valori.add(datiSian.getComuneDest() != null ? datiSian.getComuneDest() : "");
			valori.add(datiSian.getProvinciaDest() != null ? datiSian.getProvinciaDest() : "");
			valori.add(datiSian.getCittaDest());
			valori.add(datiSian.getNazioneDest());
			valori.add(datiSian.getDoganaArrivo() != null ? datiSian.getDoganaArrivo() : "");
			valori.add(datiSian.getPaeseServizioDestinatario());
			valori.add(datiSian.getLuogoOrigine());
			valori.add(datiSian.getModoTrasporto());
			valori.add(datiSian.getMezzoTrasporto());
			valori.add(datiSian.getIdMezzo() != null ? datiSian.getIdMezzo() : "");
			valori.add(datiSian.getPuntoEntrata());
			valori.add(datiSian.getSupplemento());
			valori.add(datiSian.getTrattamento());
			valori.add(datiSian.getProvRilascio() != null ? datiSian.getProvRilascio() : "");
			valori.add(datiSian.getLuogoRilascio());
			valori.add(datiSian.getData() != null ? DATE_FORMAT.format(datiSian.getData()) : "");
			valori.add(datiSian.getIspettore());
			listaValori.add(valori);
		}
		return listaValori;
	}

	private ArrayList<List<Object>> getValoriSheetMerci(List<MerceRichiestaDatiSianDTO> merci) {
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;
		for (MerceRichiestaDatiSianDTO merce : merci) {
			valori = new ArrayList<>();
			valori.add(merce.getNumero());
			valori.add(merce.getMarchio() != null ? merce.getMarchio() : "");
			valori.add(merce.getNumColli() != null ? merce.getNumColli() : "");
			valori.add(merce.getNatura() != null ? merce.getNatura() : "");
			valori.add(merce.getClasse());
			valori.add(merce.getCodiceProdotto());
			valori.add(merce.getCodiceBayer());
			valori.add(merce.getCodNazione());
			valori.add(merce.getUm());
			valori.add(merce.getQuantita());
			listaValori.add(valori);
		}
		return listaValori;
	}
	
	private ArrayList<List<Object>> getValoriSheetRiexport(List<RiexportDatiSianDTO> listaRiexport) {
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;
		for (RiexportDatiSianDTO riexport : listaRiexport) {
			valori = new ArrayList<>();
			valori.add(riexport.getNumero());
			valori.add(riexport.getPaeseRiesp());
			valori.add(riexport.getPaeseOrigine());
			valori.add(riexport.getNumCertOrigine());
			listaValori.add(valori);
		}
		return listaValori;
	}

}
