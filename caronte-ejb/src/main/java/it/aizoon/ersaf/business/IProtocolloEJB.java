package it.aizoon.ersaf.business;

import javax.ejb.Local;

import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.generati.CarTDomanda;
import it.aizoon.ersaf.dto.generati.CarTDomandaExample;
import it.aizoon.ersaf.exception.InternalException;
import it.aizoon.ersaf.integration.rest.protocollo.dto.ProtocolloOutputDto;

@Component
@Local
public interface IProtocolloEJB extends IAbstractEJB<CarTDomanda, CarTDomandaExample> {  

	public ProtocolloOutputDto protocollaDomandaInoltrata(Long idDomanda, byte [] pdfStampaDomanda, String nomeFileStampa) throws InternalException;



}
