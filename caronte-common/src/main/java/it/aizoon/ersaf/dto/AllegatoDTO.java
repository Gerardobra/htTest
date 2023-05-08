package it.aizoon.ersaf.dto;

import java.util.List;

import it.aizoon.ersaf.dto.generati.CarDTipoAllegato;
import it.aizoon.ersaf.dto.generati.CarTAllegatoControllo;
import it.aizoon.ersaf.dto.generati.CarTAllegatoDomanda;


@SuppressWarnings("serial")
public class AllegatoDTO extends CarDTipoAllegato {

	private Boolean flagObbligatorio;
	private Boolean flagMultiplo;
	private List<CarTAllegatoDomanda> listaAllegati;
	private List<CarTAllegatoControllo> listaAllegatiControllo;
	private String descAllegato;

	public Boolean getFlagObbligatorio() {
		return flagObbligatorio;
	}
	public void setFlagObbligatorio(Boolean flagObbligatorio) {
		this.flagObbligatorio = flagObbligatorio;
	}
	public Boolean getFlagMultiplo() {
		return flagMultiplo;
	}
	public void setFlagMultiplo(Boolean flagMultiplo) {
		this.flagMultiplo = flagMultiplo;
	}
	public List<CarTAllegatoDomanda> getListaAllegati() {
		return listaAllegati;
	}
	public void setListaAllegati(List<CarTAllegatoDomanda> listaAllegati) {
		this.listaAllegati = listaAllegati;
	}
	public String getDescAllegato() {
		return descAllegato;
	}
	public void setDescAllegato(String descAllegato) {
		this.descAllegato = descAllegato;
	}
	public List<CarTAllegatoControllo> getListaAllegatiControllo() {
		return listaAllegatiControllo;
	}
	public void setListaAllegatiControllo(List<CarTAllegatoControllo> listaAllegatiControllo) {
		this.listaAllegatiControllo = listaAllegatiControllo;
	}

}
