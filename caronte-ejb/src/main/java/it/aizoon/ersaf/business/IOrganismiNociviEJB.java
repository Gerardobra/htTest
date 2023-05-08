package it.aizoon.ersaf.business;

import java.util.Date;
import java.util.List;

import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.GenereSpecieOrgNocivoDTO;
import it.aizoon.ersaf.dto.OrgNocivoGenereSpecieDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.dto.generati.CarDGenere;
import it.aizoon.ersaf.dto.generati.CarDGenereExample;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivoExample;
import it.aizoon.ersaf.dto.generati.CarDTipoOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDTipoProdotto;
import it.aizoon.ersaf.dto.generati.CarDZonaProtetta;
import it.aizoon.ersaf.dto.generati.CarRLinguaGenere;
import it.aizoon.ersaf.dto.generati.CarRLinguaSpecie;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NuovaSpecieForm;
import it.aizoon.ersaf.form.NuovoGenereForm;
import it.aizoon.ersaf.form.NuovoOrganismoNocivoForm;
import it.aizoon.ersaf.form.RicercaGeneriForm;
import it.aizoon.ersaf.form.RicercaOrganismiNociviForm;
import it.aizoon.ersaf.form.RicercaSpecieForm;

public interface IOrganismiNociviEJB extends IAbstractEJB<CarDOrgNocivo, CarDOrgNocivoExample> {

	List<OrgNocivoGenereSpecieDTO> getElencoOrganismiNocivi(RicercaOrganismiNociviForm ricercaOrganismiNocivi)
			throws BusinessException;

	List<CarDOrgNocivo> getOrganismiNocivi() throws BusinessException;

	List<CarDTipoOrgNocivo> getTipiOrganismiNocivi() throws BusinessException;

	List<CarDTipoProdotto> getTipiProdotto(Long idTipoProdotto) throws BusinessException;

	void cancellaOrganismoNocivoByIdOrganismoNocivo(Long idOrganismoNocivo) throws BusinessException;

	void cancellaZonaProtettaByIdZonaProtetta(Long idZonaProtetta) throws BusinessException;

	List<CarRLinguaGenere> getGeneri() throws BusinessException;
	
	List<CarRLinguaSpecie> getSpecie() throws BusinessException;

	void inserisciNuovoOrganismoNocivo(NuovoOrganismoNocivoForm form) throws BusinessException;

	List<CarDZonaProtetta> getZonaProtettaByNuovoONForm(NuovoOrganismoNocivoForm form) throws BusinessException;
	
	OrgNocivoGenereSpecieDTO getDatiOrganismoNocivoByIdOrgNoc(Long idOrganismoNocivo) throws BusinessException;
	
	List<GenereSpecieOrgNocivoDTO> getGenereSpecieOrgNocivoByIdOrgNoc(Long idOrganismoNocivo) throws BusinessException;

	CarDOrgNocivo getOrganismoNocivoByCodiceEppoDescrOrgNoc(String codiceEppo, String descrOrgNoc) throws BusinessException;
	
	void aggiunugiGenereSpecieOrgNocivo(NuovoOrganismoNocivoForm form) throws BusinessException;
   
	void salvaModificaOrgNocivo(NuovoOrganismoNocivoForm form) throws BusinessException;

	void cancellaFisicoZonaProtettaByIdZonaProtetta(Long idZonaProtetta) throws BusinessException;

	CarDOrgNocivo getOrganismoNocivoByIdOrgNoc(Long idOrgNoc) throws BusinessException;

	void updateFineValiditaOrgNocivo(Long idOrgNocivo, Date fineValidita) throws BusinessException;
  
}
