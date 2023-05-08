package it.aizoon.ersaf.dto;


import java.util.List;

import it.aizoon.ersaf.dto.generati.CarTCampione;



@SuppressWarnings("serial")
public class CampioneDTO extends CarTCampione {

	List<CodeDescriptionDTO> orgNociviDaRicercareList;
	public String descrElencoOrgNocDaRicercare;
	public String descTipologiaCampione;
	public String descGenere;
	public String descSpecie;
	List<CodeDescriptionDTO> orgNociviAccertatiList;
	public String descrElencoOrgNocAccertati;
	public String descTipoCampione;

	public List<CodeDescriptionDTO> getOrgNociviDaRicercareList() {
		return orgNociviDaRicercareList;
	}

	public void setOrgNociviDaRicercareList(List<CodeDescriptionDTO> orgNociviDaRicercareList) {
		this.orgNociviDaRicercareList = orgNociviDaRicercareList;
	}

	public String getDescrElencoOrgNocDaRicercare() {
		return descrElencoOrgNocDaRicercare;
	}

	public void setDescrElencoOrgNocDaRicercare(String descrElencoOrgNocDaRicercare) {
		this.descrElencoOrgNocDaRicercare = descrElencoOrgNocDaRicercare;
	}

	public String getDescTipologiaCampione() {
		return descTipologiaCampione;
	}

	public void setDescTipologiaCampione(String descTipologiaCampione) {
		this.descTipologiaCampione = descTipologiaCampione;
	}

	public String getDescGenere() {
		return descGenere;
	}

	public void setDescGenere(String descGenere) {
		this.descGenere = descGenere;
	}

	public String getDescSpecie() {
		return descSpecie;
	}

	public void setDescSpecie(String descSpecie) {
		this.descSpecie = descSpecie;
	}

	public List<CodeDescriptionDTO> getOrgNociviAccertatiList() {
		return orgNociviAccertatiList;
	}

	public void setOrgNociviAccertatiList(List<CodeDescriptionDTO> orgNociviAccertatiList) {
		this.orgNociviAccertatiList = orgNociviAccertatiList;
	}

	public String getDescrElencoOrgNocAccertati() {
		return descrElencoOrgNocAccertati;
	}

	public void setDescrElencoOrgNocAccertati(String descrElencoOrgNocAccertati) {
		this.descrElencoOrgNocAccertati = descrElencoOrgNocAccertati;
	}

	public String getDescTipoCampione() {
		return descTipoCampione;
	}

	public void setDescTipoCampione(String descTipoCampione) {
		this.descTipoCampione = descTipoCampione;
	}


}
