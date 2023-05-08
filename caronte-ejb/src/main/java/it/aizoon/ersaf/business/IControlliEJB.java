package it.aizoon.ersaf.business;



import java.util.List;

import javax.ejb.Local;

import org.springframework.stereotype.Component;

import it.aizoon.ersaf.dto.AllegatoDTO;
import it.aizoon.ersaf.dto.CampioneDTO;
import it.aizoon.ersaf.dto.ControlloDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.IspettoreDTO;
import it.aizoon.ersaf.dto.MisuraDTO;
import it.aizoon.ersaf.dto.MisuraUfficialeDTO;
import it.aizoon.ersaf.dto.MonitCofinanziatoDTO;
import it.aizoon.ersaf.dto.RequisitiProfessionaliDTO;
import it.aizoon.ersaf.dto.SementeDTO;
import it.aizoon.ersaf.dto.SitoProduzioneDTO;
import it.aizoon.ersaf.dto.SpedizioniereDTO;
import it.aizoon.ersaf.dto.TipologiaAttMaterialeDTO;
import it.aizoon.ersaf.dto.generati.CarDConoscenzaProfessionale;
import it.aizoon.ersaf.dto.generati.CarDMateriale;
import it.aizoon.ersaf.dto.generati.CarDMetodoProduzione;
import it.aizoon.ersaf.dto.generati.CarDNormaVerbale;
import it.aizoon.ersaf.dto.generati.CarDOrgNocivo;
import it.aizoon.ersaf.dto.generati.CarDStrutturaAttrezzatura;
import it.aizoon.ersaf.dto.generati.CarDTipoIrrigazione;
import it.aizoon.ersaf.dto.generati.CarDTipologiaControllo;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoOrgNoc;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoReqProf;
import it.aizoon.ersaf.dto.generati.CarRControlloFisicoSpecie;
import it.aizoon.ersaf.dto.generati.CarRControlloIspettore;
import it.aizoon.ersaf.dto.generati.CarRControlloNormaVerbale;
import it.aizoon.ersaf.dto.generati.CarRControlloTipologia;
import it.aizoon.ersaf.dto.generati.CarTAllegatoControllo;
import it.aizoon.ersaf.dto.generati.CarTCentroAziendale;
import it.aizoon.ersaf.dto.generati.CarTControllo;
import it.aizoon.ersaf.dto.generati.CarTControlloDocumentale;
import it.aizoon.ersaf.dto.generati.CarTControlloExample;
import it.aizoon.ersaf.dto.generati.CarTControlloFisico;
import it.aizoon.ersaf.dto.generati.CarTControlloIdentita;
import it.aizoon.ersaf.dto.generati.CarTEsito;
import it.aizoon.ersaf.dto.generati.CarTResponsabilePassaporto;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.ModaliForm;
import it.aizoon.ersaf.form.NuovoControlloForm;
import it.aizoon.ersaf.form.RicercaOperatoreForm;

@Component
@Local
public interface IControlliEJB extends IAbstractEJB<CarTControllo, CarTControlloExample> {

	public ControlloDTO getDettaglioAziendaByIdSpedizioniere(Long idSpedizioniere) throws BusinessException;
	public List<SpedizioniereDTO> getElencoOperatori(RicercaOperatoreForm ricercaOperatore) throws BusinessException;
	public List<ControlloDTO> getElencoControlli(Long idCentroAz) throws BusinessException;
	public void inserisciNuovoControllo(Long idCentroAz, NuovoControlloForm nuovoControlloForm, Long idUtente);
	public int aggiornaDatiAzienda(NuovoControlloForm form, Long idUtente) throws BusinessException;
	public ControlloDTO getDettaglioCentroAzByIdCentroAz(Long idCentroAz) throws BusinessException;
	public void updateNuovoControllo(Long idCentroAz, NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;
	public void inserisciControlloDocumentale(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;
	public void updateControlloDocumentale(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;
	public int aggiornaDatiCentroAziendale(NuovoControlloForm form, Long idUtente) throws BusinessException;
	public void inserisciNuovoControlloFisico(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;
	public void updateNuovoControlloFisico(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;
	public List<AllegatoDTO> getListaAllegatiControllo(Long idControllo) throws BusinessException;
	public void aggiornaDatiAllegati(Long idControllo, List<AllegatoDTO> listaAllegati, Long idUtente)	throws BusinessException;
	public CarTAllegatoControllo getAllegatoControlloById(Long idAllegatoControllo) throws BusinessException;
	public void inserisciElencoSitiProduzioneRuopControlloIdentita(NuovoControlloForm nuovoControlloForm, Long idUtente, List<SitoProduzioneDTO> sitiProdList) throws BusinessException;
	public List<SitoProduzioneDTO> getSitiProduzioneByIdControlloIdentita(Long idControlloIdentita) throws BusinessException;
	public void inserisciSitoProduzioneControlloIdentita(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;
	public Long getMaxIdDomandaValidaByIdCentroAz(Long idCentroAz) throws BusinessException;
	public CarTResponsabilePassaporto getResponsabileFitosanitarioByIdDomanda(Long idDomanda) throws BusinessException;	
	public void eliminaSitoProduzione(Long idSitoProduzione, Long idControlloIdentita, Long idUtente) throws BusinessException;
	public void inserisciControlloIdentita(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;
	public void updateControlloIdentita(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;	
	public byte[] getTemplateTipoStampaControlli(Long idControllo) throws BusinessException;
	public byte[] getTemplateTipoStampaControlloById(Long idTipoStampa) throws BusinessException;
	public List<ControlloDTO> getControlliByIdCentroAziendale(Long idCentroAziendale) throws BusinessException;
	public void inserisciEsitoControllo(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;	
	public void updateEsitoControllo(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;	
	public void inviaVerbaleViaMail(Long idControllo, Long idEsito, String indirizzoMail, byte[] pdf, Long idUtente, Long numeroVerbale) throws BusinessException;	
	public void inserisciCampione(NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;
    public List<CampioneDTO> getListaCampioniByIdControllo(Long idControllo) throws BusinessException;
    public CarTEsito getEsito(Long idEsito) throws BusinessException;
	public CarTControllo getControllo(Long idControllo) throws BusinessException;
	public String salvaStampa(Long idEsito, byte[] pdf, Long idUtente,Long numeroVerbale) throws BusinessException;
	public Long getSalvaNumeroVerbale(Long idControllo, Long idUtente) throws BusinessException;
	public Boolean checkAbilitaStampaVerbaleIsp(Long idControllo) throws BusinessException;
	public void eliminaCampione(Long idCampione) throws BusinessException;
	public Boolean checkAbilitaSementi(Long idCentroAz) throws BusinessException;
	public List<CarRControlloIspettore> getIspettoriByIdControllo(Long idControllo) throws BusinessException;
	public List<CarRControlloNormaVerbale> getNormeVerbaliByIdControllo(Long idControllo) throws BusinessException;
	public List<CarRControlloTipologia> getControlloTipologiaByIdControllo(Long idControllo) throws BusinessException;
	public List<SementeDTO> getSementiByIdControllo(Long idControllo) throws BusinessException;
	public CarTControlloDocumentale getControlloDocumentaleByIdControllo(Long idControllo) throws BusinessException; 
	public CarTControlloIdentita getControlloIdentitaByIdControllo(Long idControllo) throws BusinessException;
	public CarTControlloFisico getControlloFisicoByIdControllo(Long idControllo) throws BusinessException;
	public CarTEsito getEsitoByIdControllo(Long idControllo) throws BusinessException;
	public String[] getStrutturaAttrByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public String[] getMetodoProduzioneByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public String[] getTipoIrrigazioneByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public String[] getConoscenzaProfByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public List<CarRControlloFisicoReqProf> getRequisitoProfByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public List<GenereSpecieDTO> getGenereSpecieByIdControlloFisisco(Long idControlloFisico) throws BusinessException;
	public List<SpedizioniereDTO> getElencoOperatoriAttivi(RicercaOperatoreForm ricercaOperatore) throws BusinessException;
	public void inserisciMisuraUfficiale(NuovoControlloForm nuovoControllo, Long idUtente) throws BusinessException;
	public CarTCentroAziendale getCentroAziendaleByIdControllo(Long idControllo) throws BusinessException;
	public List<CarDNormaVerbale> getListaNormeVerbaliByIdControllo(Long idControllo) throws BusinessException;
	public List<SementeDTO> getListaSementiByIdControllo(Long idControllo) throws BusinessException;
	public List<CarDTipologiaControllo> getListeTipologiaControlloByIdControllo(Long idControllo) throws BusinessException;
	public List<CarDStrutturaAttrezzatura> getListaStruttureAttrezzatureByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public List<CarDMetodoProduzione> getMetodiProduzioneByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public List<CarDTipoIrrigazione> getListaTipiIrrigazioneByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public void updateMisuraUfficiale(NuovoControlloForm nuovoControllo, Long idUtente) throws BusinessException;
	public MisuraUfficialeDTO getMisuraUfficiale(Long idControllo) throws BusinessException; 
	public Boolean checkAbilitaStampaDisposizioneMisuraUff(Long idControllo) throws BusinessException;
	public String salvaStampaDisposizione(Long idMisuraUfficiale, byte[] pdf, Long idUtente, Long numeroVerbaleDisposizione) throws BusinessException;
	public Boolean checkAbilitaStampaConstatazioneMisuraUff(Long idControllo) throws BusinessException;
	public String salvaStampaConstatazione(Long idMisuraUfficiale, byte[] pdf, Long idUtente, Long numeroVerbaleConstatazione) throws BusinessException;
	public List<CarDConoscenzaProfessionale> getListaConoscenzeProfessionaliByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public List<RequisitiProfessionaliDTO> getListaRequisitiProfessionaliByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public List<MisuraDTO> getListaTipologieMisuraByIdMisuraUfficiale(Long idMisuraUfficiale) throws BusinessException;
	public List<IspettoreDTO> getIspettoriDispByIdMisura(Long idMisuraUfficiale) throws BusinessException;
	public List<IspettoreDTO> getIspettoriConstByIdMisura(Long idMisuraUfficiale) throws BusinessException;
	public List<IspettoreDTO> getIspettoriControlloByIdControllo(Long idControllo) throws BusinessException;
	public void inserisciMonitoraggioCofinanziato(NuovoControlloForm nuovoControllo, Long idUtente) throws BusinessException;
	public List<MonitCofinanziatoDTO> getMonitoraggiCofinanziato(Long idControllo) throws BusinessException;
	public List<TipologiaAttMaterialeDTO> getTipologieAttMaterialeDomandeRuop(Long idDomanda) throws BusinessException;
	public void updateCampione(NuovoControlloForm form, ModaliForm modaliForm, Long idUtente) throws BusinessException;
	public Long checkVersioneControllo(Long idControllo) throws BusinessException;
	public List<CarRControlloFisicoOrgNoc> getOrgNocByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public List<CarDOrgNocivo> getListaOrgNocByIdControlloFisico(Long idControlloFisico) throws BusinessException;
	public List<CarDMateriale> getAttivitaSementiByIdDomanda(Long idDomanda) throws BusinessException;	
	public List<CarDMateriale> getAttivitaSementiByIdControllo(Long idControllo) throws BusinessException;
    public void inserisciControlloFisicoSpecie(List<GenereSpecieDTO> listaGenereSpecie, NuovoControlloForm nuovoControlloForm, Long idUtente) throws BusinessException;
	public void aggiungiGenereSpecieOrgNocFisico(NuovoControlloForm  form, Long idUtente) throws BusinessException;
	public void eliminaSpecieControlloFisico(Long idControlloFisicoSpecie) throws BusinessException;
	public CarRControlloFisicoSpecie getControlloFisicoSpecieByIdControlloFisicoSpecie(Long idControlloFisicoSpecie) throws BusinessException;
	public void inserisciCheck(NuovoControlloForm nuovoControlloForm, Long id) throws BusinessException;
	public void salvaModificaOrganismiNocivi(NuovoControlloForm  controlloform,ModaliForm form, Long idUtente) throws BusinessException;
	public void eliminaControllo(Long idControllo, Long idUtente) throws BusinessException;
	public Long getMaxIdDomandaByIdCentroAzPassaporto(Long idCentroAzPassaporto) throws BusinessException;


}
