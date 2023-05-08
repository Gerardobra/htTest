package it.aizoon.ersaf.form;

import java.util.List;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.GenereSpecieOrgNocivoDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.util.CaronteUtils;

public class NuovoOrganismoNocivoForm extends BaseForm{
	
	@NumberFormat(style= Style.NUMBER)
	public Long idOrgNocivo;	
	private Long idTipoOrgNocivo;
	private Long idTipoProdotto;
	private String nuovoCodiceEppo;
	private String descrizioneOrganismoNocivo;
	private Long idGenere;
	private Long idSpecieLong;
	private String[] specie;
	
	private String[] idSpecie;
	private List<SpecieDTO> specieList;
	private List<GenereSpecieDTO> listaGeneri;
	private String[] denomGenere;
	
	private List<GenereSpecieOrgNocivoDTO> generiSpecieOrgNoc;
	private String orgNocInZonaProtetta;
	
	public Long getIdOrgNocivo() {
		return idOrgNocivo;
	}
	
	public void setIdOrgNocivo(Long idOrgNocivo) {
		this.idOrgNocivo = idOrgNocivo;
	}
	
	
	
	/**
	 * @return the idTipoOrgNocivo
	 */
	public Long getIdTipoOrgNocivo() {
		return idTipoOrgNocivo;
	}
	/**
	 * @param idTipoOrgNocivo the idTipoOrgNocivo to set
	 */
	public void setIdTipoOrgNocivo(Long idTipoOrgNocivo) {
		this.idTipoOrgNocivo = idTipoOrgNocivo;
	}
	/**
	 * @return the idTipoProdotto
	 */
	public Long getIdTipoProdotto() {
		return idTipoProdotto;
	}
	/**
	 * @param idTipoProdotto the idTipoProdotto to set
	 */
	public void setIdTipoProdotto(Long idTipoProdotto) {
		this.idTipoProdotto = idTipoProdotto;
	}

	/**
	 * @return the nuovoCodiceEppo
	 */
	public String getNuovoCodiceEppo() {
		return nuovoCodiceEppo;
	}

	/**
	 * @param nuovoCodiceEppo the nuovoCodiceEppo to set
	 */
	public void setNuovoCodiceEppo(String nuovoCodiceEppo) {
		this.nuovoCodiceEppo = CaronteUtils.convertToUpperCase(nuovoCodiceEppo);
	}

	

	/**
	 * @return the idGenere
	 */
	public Long getIdGenere() {
		return idGenere;
	}

	/**
	 * @param idGenere the idGenere to set
	 */
	public void setIdGenere(Long idGenere) {
		this.idGenere = idGenere;
	}

	/**
	 * @return the descrizioneOrganismoNocivo
	 */
	public String getDescrizioneOrganismoNocivo() {
		return descrizioneOrganismoNocivo;
	}

	/**
	 * @param descrizioneOrganismoNocivo the descrizioneOrganismoNocivo to set
	 */
	public void setDescrizioneOrganismoNocivo(String descrizioneOrganismoNocivo) {
		this.descrizioneOrganismoNocivo = CaronteUtils.convertToUpperCase(descrizioneOrganismoNocivo);
	}

	/**
	 * @return the specie
	 */
	public String[] getSpecie() {
		return specie;
	}

	/**
	 * @param specieZP the specie to set
	 */
	public void setSpecie(String[] specie) {
		this.specie = specie;
	}


	public String getOrgNocInZonaProtetta() {
		return orgNocInZonaProtetta;
	}

	public void setOrgNocInZonaProtetta(String orgNocInZonaProtetta) {
		this.orgNocInZonaProtetta = CaronteUtils.convertToUpperCase(orgNocInZonaProtetta);
	}

	public List<GenereSpecieOrgNocivoDTO> getGeneriSpecieOrgNoc() {
		return generiSpecieOrgNoc;
	}

	public void setGeneriSpecieOrgNoc(List<GenereSpecieOrgNocivoDTO> generiSpecieOrgNoc) {
		this.generiSpecieOrgNoc = generiSpecieOrgNoc;
	}

	/**
	 * @return the specieList
	 */
	public List<SpecieDTO> getSpecieList() {
		return specieList;
	}

	/**
	 * @param specieList the specieList to set
	 */
	public void setSpecieList(List<SpecieDTO> specieList) {
		this.specieList = specieList;
	}

	/**
	 * @return the idSpecie
	 */
	public String[] getIdSpecie() {
		return idSpecie;
	}

	/**
	 * @param idSpecie the idSpecie to set
	 */
	public void setIdSpecie(String[] idSpecie) {
		this.idSpecie = idSpecie;
	}

	/**
	 * @return the listaGeneri
	 */
	public List<GenereSpecieDTO> getListaGeneri() {
		return listaGeneri;
	}

	/**
	 * @param listaGeneri the listaGeneri to set
	 */
	public void setListaGeneri(List<GenereSpecieDTO> listaGeneri) {
		this.listaGeneri = listaGeneri;
	}

	/**
	 * @return the denomGenere
	 */
	public String[] getDenomGenere() {
		return denomGenere;
	}

	/**
	 * @param denomGenere the denomGenere to set
	 */
	public void setDenomGenere(String[] denomGenere) {
		this.denomGenere = denomGenere;
	}

	/**
	 * @return the idSpecieLong
	 */
	public Long getIdSpecieLong() {
		return idSpecieLong;
	}

	/**
	 * @param idSpecieLong the idSpecieLong to set
	 */
	public void setIdSpecieLong(Long idSpecieLong) {
		this.idSpecieLong = idSpecieLong;
	}

	

}
