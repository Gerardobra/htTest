package it.aizoon.ersaf.business;

import java.util.List;

import javax.ejb.Local;
import javax.validation.Valid;

import org.springframework.stereotype.Component;


import it.aizoon.ersaf.dto.DatiRichiesteDTO;
import it.aizoon.ersaf.dto.DatiSianDTO;
import it.aizoon.ersaf.dto.MerceRichiestaDatiSianDTO;
import it.aizoon.ersaf.dto.ProfiloNonAutorizzatoDTO;
import it.aizoon.ersaf.dto.RiexportDatiSianDTO;
import it.aizoon.ersaf.dto.generati.CarDAllegato;
import it.aizoon.ersaf.dto.generati.CarTCertificato;
import it.aizoon.ersaf.dto.generati.CarTCertificatoExample;
import it.aizoon.ersaf.dto.generati.CarTNotifica;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.NotificheForm;
import it.aizoon.ersaf.form.RicercaDatiRichiesteForm;
import it.aizoon.ersaf.form.RicercaDatiSianForm;

@Component
@Local
public interface IServiziEJB extends IAbstractEJB<CarTCertificato, CarTCertificatoExample> {

	List<DatiRichiesteDTO> getElencoDatiRichieste(RicercaDatiRichiesteForm richiesteForm) throws BusinessException;

	List<DatiSianDTO> getElencoDatiSian(RicercaDatiSianForm sianForm) throws BusinessException;

	List<MerceRichiestaDatiSianDTO> getMerciPerDatiSian(RicercaDatiSianForm sianForm) throws BusinessException;

	List<RiexportDatiSianDTO> getDatiRiexportSian(RicercaDatiSianForm sianForm) throws BusinessException;

	List<CarTNotifica> getElencoMessaggi() throws BusinessException;
	
	List<ProfiloNonAutorizzatoDTO> getUtentiDaAutorizzare() throws BusinessException;

	int salvaMessaggi(List<CarTNotifica> listaMessaggi, long idUtente) throws BusinessException;

	List<CarDAllegato> getDocumentazione() throws BusinessException;

	CarDAllegato getDocumento(Long idAllegato) throws BusinessException;

}
