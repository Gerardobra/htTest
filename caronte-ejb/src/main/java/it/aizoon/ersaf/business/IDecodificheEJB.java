package it.aizoon.ersaf.business;

import java.util.List;

import javax.ejb.Local;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.CentroAziendaleDto;
import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.DomandaDto;
import it.aizoon.ersaf.dto.ModuloDTO;
import it.aizoon.ersaf.dto.TipoDomandaDTO;
import it.aizoon.ersaf.dto.generati.CarDAssociazioneSezione;
import it.aizoon.ersaf.dto.generati.CarDAutoreEppo;
import it.aizoon.ersaf.dto.generati.CarDClasse;
import it.aizoon.ersaf.dto.generati.CarDComune;
import it.aizoon.ersaf.dto.generati.CarDConoscenzaProfessionale;
import it.aizoon.ersaf.dto.generati.CarDCostante;
import it.aizoon.ersaf.dto.generati.CarDGruppoZonaProtetta;
import it.aizoon.ersaf.dto.generati.CarDLingua;
import it.aizoon.ersaf.dto.generati.CarDMateriale;
import it.aizoon.ersaf.dto.generati.CarDMetodoProduzione;
import it.aizoon.ersaf.dto.generati.CarDMezzoPagamento;
import it.aizoon.ersaf.dto.generati.CarDModoTrasporto;
import it.aizoon.ersaf.dto.generati.CarDNaturaCollo;
import it.aizoon.ersaf.dto.generati.CarDNazione;
import it.aizoon.ersaf.dto.generati.CarDNormaVerbale;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDProdotto;
import it.aizoon.ersaf.dto.generati.CarDProvincia;
import it.aizoon.ersaf.dto.generati.CarDProvinciaExample;
import it.aizoon.ersaf.dto.generati.CarDRegione;
import it.aizoon.ersaf.dto.generati.CarDRequisitoProfessionale;
import it.aizoon.ersaf.dto.generati.CarDRuolo;
import it.aizoon.ersaf.dto.generati.CarDStatoAzienda;
import it.aizoon.ersaf.dto.generati.CarDStatoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDStrutturaAttrezzatura;
import it.aizoon.ersaf.dto.generati.CarDTipoAttivita;
import it.aizoon.ersaf.dto.generati.CarDTipoCertificato;
import it.aizoon.ersaf.dto.generati.CarDTipoComunicazione;
import it.aizoon.ersaf.dto.generati.CarDTipoImballaggio;
import it.aizoon.ersaf.dto.generati.CarDTipoIrrigazione;
import it.aizoon.ersaf.dto.generati.CarDTipoModulo;
import it.aizoon.ersaf.dto.generati.CarDTipoProdotto;
import it.aizoon.ersaf.dto.generati.CarDTipoRespAzienda;
import it.aizoon.ersaf.dto.generati.CarDTipoSpedizioniere;
import it.aizoon.ersaf.dto.generati.CarDTipologia;
import it.aizoon.ersaf.dto.generati.CarDTipologiaCampione;
import it.aizoon.ersaf.dto.generati.CarDTipologiaControllo;
import it.aizoon.ersaf.dto.generati.CarDTipologiaMisura;
import it.aizoon.ersaf.dto.generati.CarDTipologiaPassaporto;
import it.aizoon.ersaf.dto.generati.CarDTipologiaSemente;
import it.aizoon.ersaf.dto.generati.CarDTrattamento;
import it.aizoon.ersaf.dto.generati.CarDUfficioDoganale;
import it.aizoon.ersaf.dto.generati.CarDUnitaMisura;
import it.aizoon.ersaf.dto.generati.CarDVoce;
import it.aizoon.ersaf.dto.generati.CarTSpedizioniere;
import it.aizoon.ersaf.exception.BusinessException;

@Component
@Local
public interface IDecodificheEJB extends IAbstractEJB<CarDProvincia, CarDProvinciaExample> {

	public List<CarDUfficioDoganale> getUfficioDoganale() throws BusinessException;  
	
	public List<CarDUfficioDoganale> getUfficioDoganale(boolean utilizzato) throws BusinessException;

	public List<CarDLingua> getListaLingue() throws BusinessException;

	/**
	 * Ottiene l'oggetto CarDLingua dal database sulla base del valore ottenuto dal 
	 * LocaleContextHolder.getLocale().getLanguage()
	 * @param codLingua è il valore identificato con due lettere ottenuto da LocaleContextHolder.getLocale().getLanguage()
	 * @return CarDLingua oggetto ottenuto dal database
	 * @throws BusinessException
	 */
	public CarDLingua getLinguaFromCodice(String codLingua) throws BusinessException;

	public List<CarDTipoCertificato> getListaTipiCertificato() throws BusinessException;
	
	public List<CarDProvincia> getListaProvince() throws BusinessException;

	public List<CarDComune> getListaComuni(Long idProvincia) throws BusinessException;
	
	public List<CarDComune> getListaComuni() throws BusinessException;

	public List<CarDRegione> getListaRegioni() throws BusinessException;

	public List<CarDTipoSpedizioniere> getListaTipiSpedizioniere() throws BusinessException;

	public CarDTipoSpedizioniere getTipoSpedizioniere(Long idTipoSpedizioniere) throws BusinessException;

	public List<CarDNazione> getListaNazioni(boolean escludiItalia) throws BusinessException;

	public List<CarDModoTrasporto> getListaModiTrasporto() throws BusinessException;

	public List<CarDUnitaMisura> getListaUnitaMisura() throws BusinessException;

	public List<CarDTrattamento> getListaTrattamenti() throws BusinessException;

	public List<CarDMezzoPagamento> getListaMezziPagamento() throws BusinessException;

	public List<CarDTipoProdotto> getListaTipiProdotto() throws BusinessException;

	public CarDUnitaMisura getUnitaMisuraTipoProdotto(Long idTipoProdotto) throws BusinessException;

	public CarDCostante getCostante(String codCostante) throws BusinessException;

    public List<CarDClasse> getListaClassi() throws BusinessException;

	public List<CarDRuolo> getTipiRuolo() throws BusinessException;

	public List<CarTSpedizioniere> getListaSpedizionieri() throws BusinessException;

	public List<CarTSpedizioniere> getListaSpedizionieri(Long idAssociazioneSezione) throws BusinessException;
	
	public List<CarDRegione> getListaRegioni(Long idNazione) throws BusinessException;

	public List<CarDProvincia> getListaProvince(Long idRegione) throws BusinessException;
	
	public List<CarDAutoreEppo> getListaAutoriEppo(String denomAutore) throws BusinessException;
	
	public CarDComune getComuneByPrimaryKey(Long idComune)throws BusinessException;

	public List<CarDAssociazioneSezione> getListaSezioni()throws BusinessException;
	
	public List<CarDProdotto> getListaProdotti() throws BusinessException;
	
	public List<CarDNaturaCollo> getListaNaturaColli() throws BusinessException;

	public CarDUnitaMisura getUnitaMisuraProdotto(Long idProdotto) throws BusinessException;
	
	public CarDClasse getClasseProdotto(Long idClasseProdotto) throws BusinessException;
	
	public List<CarDTipoImballaggio> getListaTipiImballaggi() throws BusinessException;
	
  public List<CarDTipoComunicazione> getListaTipiComunicazione() throws BusinessException;
  
  public List<CarDStatoComunicazione> getListaStatiComunicazione() throws BusinessException;
	
  public List<CentroAziendaleDto> getListaCentriAziendali(Long idSpedizioniere) throws BusinessException;
  
  public CarDAutoreEppo getDatiDenomAutoreEppo(String denomAutore) throws BusinessException;
  
  public TipoDomandaDTO getTipoComunicazioneByPrimaryKey(Long idTipoDomanda) throws BusinessException;
  
  public List<CarDTipologia> getListaTipologie() throws BusinessException;
  
  public List<CarDVoce> getVociByIdTipoModello(Long idTipoModello) throws BusinessException;
    
  public List<CarDVoce> getVociByIdTipoModelloGruppo(Long idTipoModello, Long gruppo) throws BusinessException;
  
  public List<CarDGruppoZonaProtetta> getZoneProtette() throws BusinessException;
  
  public List<CarDTipoAttivita> getTipoAttivita() throws BusinessException;
  
  public List<CarDMateriale> getListaMaterialiByIdTipoAttivita(Long idTipoAttivita) throws BusinessException;
  
  public boolean isTipoGenereFamiglia(String denomGenere) throws BusinessException;

  public List<DomandaDto> getListaIspettori() throws BusinessException;
  
  public CarDProvincia getProvinciaByIdProv(Long idProvincia) throws BusinessException;

  public List<CarDStatoAzienda> getListaStatiAzienda() throws BusinessException;
  
  

  public List<ModuloDTO> getListaModuliComunicazione(Long idDomanda) throws BusinessException;

  public CarDTipoModulo getTipoModuloByPrimaryKey(Long idTipoModulo) throws BusinessException;
  
  public List<CarDNormaVerbale> getListaNormeVerbale() throws BusinessException;
  
  public List<CarDOrgNocivo> getListaOrganismiNocivi() throws BusinessException;

 public List<CarDTipologiaSemente> getListaTipologiaSementi() throws BusinessException;

 public List<CarDTipoRespAzienda> getListaTipiRespAzienda() throws BusinessException;

 public List<CarDTipologiaControllo> getListTipologieControlli() throws BusinessException;

  public List<CarDMetodoProduzione> getListaMetodiProduzione() throws BusinessException;

  public List<CarDTipoIrrigazione> getListaTipoIrrigazione() throws BusinessException;

  public List<CarDConoscenzaProfessionale> getListaConoscenzaProfessionale() throws BusinessException;

  public List<CarDRequisitoProfessionale> getListaRequisitoProfessionale() throws BusinessException;

  public List<CarDStrutturaAttrezzatura> getListaStrutturaAttrezzatura() throws BusinessException;
  
  public List<CarDTipologiaCampione> getListaTipologiaCampione() throws BusinessException;
  
  public List<CarDTipologiaMisura> getTipologieMisura() throws BusinessException;
  
  public List<CodeDescriptionDTO> getGeneriByIdOrgNocivo(Long idOrganismoNocivo) throws BusinessException;
  
  public List<CodeDescriptionDTO> getSpecieByIdGeneri(List<Long> idGenereList) throws BusinessException;
  
  public List<CarDTipologiaPassaporto> getListaTipologiaPassaporto() throws BusinessException;

  public String getQualificaByIdTipoRespAzienda(Long idTipoRespAzienda) throws BusinessException;
  
  public List<CodeDescriptionDTO> getListaIspettoriByTipoIspettore(String tipoIspettore) throws BusinessException;
  
  public List<CarDTipologiaSemente> getListaTipologiaSementiByIdVersioneControllo(@Param("idVersioneControllo") Long idVersioneControllo) throws BusinessException;
  
  public CarDCostante getCostanteWithBlob(String codCostante)  throws BusinessException;
  
 

}