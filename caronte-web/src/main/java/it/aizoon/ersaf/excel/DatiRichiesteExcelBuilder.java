package it.aizoon.ersaf.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.DatiRichiesteDTO;
import it.aizoon.ersaf.dto.MerceRichiestaDto;

public class DatiRichiesteExcelBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "Elenco_dati_richieste";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {

		/* PREPARAZIONE DATI SHEET MERCI INIZIO */
		// Titolo sheet Merci
		String titoloSheetMerci = "Merci";
		// Setto i valori degli headers della sheet delle merci
		List<String> headersSheetMerci = null;
		if (codTipoRichiesta != null && codTipoRichiesta.equalsIgnoreCase("I")) {
			headersSheetMerci = Arrays.asList(new String[] { "Numero richiesta", "Data inoltro", "Tipo merce",
					"Genere", "Unita' misura", "Quantita'" });
		} else {
			headersSheetMerci = Arrays.asList(new String[] { "Numero richiesta", "Data inoltro", "Tipo merce",
					"Tipo merce prodotto", "Specie", "Unità misura", "Quantità" });
		}
		// Recupero e setto il contenuto da scrivere nella sheet delle merci
		ArrayList<List<Object>> listaValoriSheetMerci = getValoriSheetMerci((List<DatiRichiesteDTO>) (List<?>) items);
		/* PREPARAZIONE DATI SHEET MERCI FINE */
		
		/* *********************************************** */

		/* PREPARAZIONE DATI SHEET RICHIESTE INIZIO */
		// Titolo sheet Richieste
		String titoloSheetRichieste = "Richieste";
		// Setto i valori degli headers della sheet delle richieste
		List<String> headersSheetRichieste = null;

		if (codTipoRichiesta != null && codTipoRichiesta.equalsIgnoreCase("I")) {
			headersSheetRichieste = Arrays.asList(new String[] { "Numero Richiesta", "Numero certificato", "Ente",
					"Stato richiesta", "Data inoltro", "Data esecuzione", "Utente esecuzione",
					"Data rilascio certificato", "Matricola funzionario rilascio certificato",
					"Funzionario rilascio certificato", "Mittente nome", "Mittente indirizzo", "Mittente città",
					"Mittente stato", "Destinatario nome", "Destinatario indirizzo", "Destinatario comune",
					"Destinatario provincia", "Stato origine", "Modo trasporto", "Documento trasporto", "Dogana",
					"Entrata dichiarata", "Trattamento", "Trattamento sostanza", "Trattamento durata",
					"Trattamento concentrazione", "Trattamento data", "Trattamento informazioni", "Codice RUP",
					"Regione RUP", "Luogo rilascio", "Tariffa", "Note" });
		} else {
			headersSheetRichieste = Arrays.asList(new String[] { "Numero Richiesta", "Numero certificato", "Ente",
					"Stato richiesta", "Data inoltro", "Data esecuzione", "Utente esecuzione",
					"Data rilascio certificato", "Matricola funzionario rilascio certificato",
					"Funzionario rilascio certificato", "Mittente nome", "Mittente indirizzo", "Mittente città",
					"Mittente stato", "Destinatario nome", "Destinatario indirizzo", "Destinatario comune",
					"Destinatario provincia", "Stato origine", "Modo trasporto", "Documento trasporto", "Dogana",
					"Trattamento", "Trattamento sostanza", "Trattamento durata", "Trattamento concentrazione",
					"Trattamento data", "Trattamento informazioni", "Luogo rilascio", "Tariffa", "Note" });
		}
		// Recupero e setto il contenuto da scrivere nella sheet delle richieste
		ArrayList<List<Object>> listaValoriSheetRichieste = getValoriSheetRichieste((List<DatiRichiesteDTO>) (List<?>) items);
		/* PREPARAZIONE DATI SHEET RICHIESTE FINE */
		
		/* *********************************************** */

		// Aggiungo al documento (indirettamente) la sheet delle merci con Titolo,
		// Headers e Valori recuperati
		addExcelSheet(titoloSheetMerci, headersSheetMerci, listaValoriSheetMerci);
		// Aggiungo al documento (indirettamente) la sheet delle richieste con Titolo,
		// Headers e Valori recuperati
		addExcelSheet(titoloSheetRichieste, headersSheetRichieste, listaValoriSheetRichieste);

	}

	private ArrayList<List<Object>> getValoriSheetMerci(List<DatiRichiesteDTO> items) {
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		for (DatiRichiesteDTO richiesta : items) {
			for (MerceRichiestaDto merce : richiesta.getMerci()) {
				valori = new ArrayList<>();
				valori.add(String.valueOf(richiesta.getNumeroRichiesta()));
				valori.add(richiesta.getDataInoltro() != null ? DATE_FORMAT.format(richiesta.getDataInoltro()) : "");
				valori.add(merce.getDescTipoProdotto()); // TIPO MERCE
				if (codTipoRichiesta != null && !"I".equalsIgnoreCase(codTipoRichiesta)) {
					valori.add(merce.getDescProdotto()); // TIPO MERCE PRODOTTO
					valori.add(merce.getDescGenere()); // GENERE
				} else {
					valori.add(merce.getDescSpecie()); //SPECIE
				}
				valori.add(merce.getDescUnitaMisura());
				//valori.add(merce.getQuantita() != null ? String.valueOf(merce.getQuantita()) : "");
				valori.add(merce.getQuantita() != null ? merce.getQuantita() : "");
				
				listaValori.add(valori);
			}
		}

		return listaValori;
	}

	private ArrayList<List<Object>> getValoriSheetRichieste(List<DatiRichiesteDTO> items) {
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		for (DatiRichiesteDTO richiesta : items) {
			valori = new ArrayList<>();
			valori.add(richiesta.getNumeroRichiesta());
			valori.add(richiesta.getNumeroCertificato());
			valori.add(richiesta.getEnte());
			valori.add(richiesta.getStatoRichiesta());
			valori.add(richiesta.getDataInoltro() != null ? DATE_FORMAT.format(richiesta.getDataInoltro()) : "");
			valori.add(richiesta.getDataEsecuzione() != null ? DATE_FORMAT.format(richiesta.getDataEsecuzione()) : "");
			valori.add(richiesta.getUtenteEsecuzione());
			valori.add(richiesta.getDataRilascioCertificato() != null ? DATE_FORMAT.format(richiesta.getDataRilascioCertificato()) : "");
			valori.add(richiesta.getNumeroTessera());
			valori.add(richiesta.getFunzionarioRilascioCertificato());
			valori.add(richiesta.getMittenteNome());
			valori.add(richiesta.getMittenteIndirizzo());
			valori.add(richiesta.getMittenteCitta());
			valori.add(richiesta.getMittenteStato());
			valori.add(richiesta.getDestinatarioNome());
			valori.add(richiesta.getDestinatarioIndirizzo());
			valori.add(richiesta.getDestinatarioComune() != null ? richiesta.getDestinatarioComune() : "");
			valori.add(richiesta.getDestinatarioProvincia() != null ? richiesta.getDestinatarioProvincia() : "");
			valori.add(richiesta.getStatoOrigine());
			valori.add(richiesta.getModoTrasporto());
			valori.add(richiesta.getDocumentoTrasporto());
			valori.add(richiesta.getDogana() != null ? richiesta.getDogana() : "");
			if (codTipoRichiesta != null && codTipoRichiesta.equalsIgnoreCase("I")) {
				valori.add(richiesta.getEntrataRichiesta());
			}
			valori.add(richiesta.getTrattamento() != null ? richiesta.getTrattamento() : "");
			valori.add(richiesta.getTrattamentoSostanza() != null ? richiesta.getTrattamentoSostanza() : "");
			valori.add(richiesta.getTrattamentoDurata() != null ? richiesta.getTrattamentoDurata() : "");
			valori.add(richiesta.getTrattamentoConcentrazione() != null ? richiesta.getTrattamentoConcentrazione() : "");
			valori.add(richiesta.getTrattamentoData() != null ? DATE_FORMAT.format(richiesta.getTrattamentoData()) : "");
			valori.add(richiesta.getTrattamentoInformazioni() != null ? richiesta.getTrattamentoInformazioni() : "");
			if (codTipoRichiesta != null && codTipoRichiesta.equalsIgnoreCase("I")) {
				valori.add(richiesta.getCodiceRup());
				valori.add(richiesta.getRegioneRup());
			}
			valori.add(richiesta.getLuogoRilascio());
			//valori.add(richiesta.getTariffa() != null ? String.valueOf(richiesta.getTariffa()) : "");
			valori.add(richiesta.getTariffa() != null ? richiesta.getTariffa() : "");
			valori.add(richiesta.getNote() != null ? richiesta.getNote() : "");
			
			listaValori.add(valori);
		}
		return listaValori;
	}

}
