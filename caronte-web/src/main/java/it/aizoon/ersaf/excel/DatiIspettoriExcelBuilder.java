package it.aizoon.ersaf.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.IspettoreDTO;

public class DatiIspettoriExcelBuilder extends BaseExcelBuilder {

	@Override
	protected void setFileName() {
		fileName = "Elenco_dati_ispettori";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <TDTO extends BaseDto> void setPagineExcel(List<TDTO> items) {

		/* PREPARAZIONE DATI PRIMA SHEET INIZIO */
		// Titolo sheet Merci
		String titoloSheetIspettori = "Dati ispettori";
		// Setto i valori degli headers della sheet degli ispettori
		List<String> headersSheetIspettori = null;
		headersSheetIspettori = Arrays.asList(new String[] { "regione", "tipoOperazione", "codice", "dataInizio",
				"dataFine", "codiceFiscale", "cognome", "nome", "sesso", "dataNascita", "codiceComuneNascita",
				"comuneNascita", "provinciaNascita", "nazioneNascita", "titoloStudio", "indirUfficio", "CAPUfficio",
				"codiceComuneUfficio", "comuneUfficio", "provinciaUfficio" });
		// Recupero e setto il contenuto da scrivere nella sheet delle merci
		ArrayList<List<Object>> listaValoriSheetIspettori = getValoriSheetIspettori((List<IspettoreDTO>) (List<?>) items);
		/* PREPARAZIONE DATI PRIMA SHEET FINE */

		// Aggiungo al documento (indirettamente) la sheet degli ispettori con Titolo,
		// Headers e Valori recuperati
		addExcelSheet(titoloSheetIspettori, headersSheetIspettori, listaValoriSheetIspettori);

	}

	private ArrayList<List<Object>> getValoriSheetIspettori(List<IspettoreDTO> items) {
		ArrayList<List<Object>> listaValori = new ArrayList<>();
		ArrayList<Object> valori = null;
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		for (IspettoreDTO ispettore : items) {
			valori = new ArrayList<>();
			valori.add(ispettore.getCodRegioneServizio());
			valori.add(ispettore.getTipoOperazione());
			valori.add(ispettore.getNumeroTessera());
			valori.add(ispettore.getDataInizio() != null ? DATE_FORMAT.format(ispettore.getDataInizio()) : "");
			valori.add(ispettore.getDataFine() != null ? DATE_FORMAT.format(ispettore.getDataFine()) : "");
			valori.add(ispettore.getCodiceFiscale());
			valori.add(ispettore.getCognome());
			valori.add(ispettore.getNome());
			valori.add(ispettore.getSesso());
			valori.add(ispettore.getDataNascita() != null ? DATE_FORMAT.format(ispettore.getDataNascita()) : "");
			valori.add(ispettore.getCodComuneNascita());
			valori.add(ispettore.getDenomComuneNascita());
			valori.add(ispettore.getCodProvinciaNascita());
			valori.add(ispettore.getCodNazioneNascita());
			valori.add(ispettore.getTitoloStudio());
			valori.add(ispettore.getIndirizzoUfficio());
			valori.add(ispettore.getCapUfficio());
			valori.add(ispettore.getCodComuneUfficio());
			valori.add(ispettore.getDenomComuneUfficio());
			valori.add(ispettore.getCodProvinciaUfficio());

			listaValori.add(valori);
		}
		return listaValori;
	}

}
