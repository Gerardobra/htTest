package it.aizoon.ersaf.form;

import java.util.List;

import it.aizoon.ersaf.util.CaronteUtils;

public class RicercaOperatoreForm extends BaseForm {

	Long idAssociazioneSezione;
	
	Long idIspettore;  
	Long idProvincia;
	Long idTipoAttivita;
	Long idGenere;
	String genere;
	Long idOrgNocivo;
	String ragioneSociale;
	String cuaa;
	List<Integer> idSpecie;
	List<String> specie;
	Long idUtente;

	public Long getIdIspettore() {
		return idIspettore;
	}
	public void setIdIspettore(Long idIspettore) {
		this.idIspettore = idIspettore;
	}
	public Long getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}
	public Long getIdTipoAttivita() {
		return idTipoAttivita;
	}
	public void setIdTipoAttivita(Long idTipoAttivita) {
		this.idTipoAttivita = idTipoAttivita;
	}
	public Long getIdGenere() {
		return idGenere;
	}
	public void setIdGenere(Long idGenere) {
		this.idGenere = idGenere;
	}	
	public Long getIdOrgNocivo() {
		return idOrgNocivo;
	}
	public void setIdOrgNocivo(Long idOrgNocivo) {
		this.idOrgNocivo = idOrgNocivo;
	}		
	public Long getIdAssociazioneSezione() {
		return idAssociazioneSezione;
	}
	public void setIdAssociazioneSezione(Long idAssociazioneSezione) {
		this.idAssociazioneSezione = idAssociazioneSezione;
	}
	public String getCuaa() {
		return cuaa;
	}
	public void setCuaa(String cuaa) {
		this.cuaa = CaronteUtils.convertToUpperCase(cuaa);
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = CaronteUtils.convertToUpperCase(ragioneSociale);
	}	
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = CaronteUtils.convertToUpperCase(genere);
	}
	public List<String> getSpecie() {
		return specie;
	}
	public void setSpecie(List<String> specie) {
		this.specie = specie;
	}
	public List<Integer> getIdSpecie() {
		return idSpecie;
	}
	public void setIdSpecie(List<Integer> idSpecie) {
		this.idSpecie = idSpecie;
	}
	public Long getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	
}
