package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.Date;
import java.util.List;

import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.AllegatoDTO;
import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.CampioneDTO;
import it.aizoon.ersaf.dto.CodeDescriptionDTO;
import it.aizoon.ersaf.dto.ControlloDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.IspettoreDTO;
import it.aizoon.ersaf.dto.MisuraDTO;
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
import it.aizoon.ersaf.dto.generati.CarTResponsabilePassaporto;
import it.aizoon.ersaf.form.RicercaOperatoreForm;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ControlloMapper extends IBaseDao<BaseDto, GenericExample> {


	ControlloDTO getDettaglioAziendaByIdSpedizioniere(Long idSpedizioniere);
	
	List<SpedizioniereDTO> getElencoOperatori(RicercaOperatoreForm ricercaOperatore);

	List<ControlloDTO> getElencoControlli(Long idCentroAz);

	ControlloDTO getDettaglioCentroAzByIdCentroAz(Long idCentroAz);

	void deleteRControlloTipologiaByIdControllo(Long idControllo);

	void deleteRControlloTipoSementeByIdControllo(Long idControllo);

	void deleteRControlloNormaVerbaleByIdControllo(Long idControllo);

	void deleteRControlloIspettoreByIdControllo(Long idControllo);
	
    List<SitoProduzioneDTO> getSitiProduzioneByIdControlloIdentita(Long idControlloIdentita);
    
    Long getMaxIdDomandaValidaByIdCentroAz(Long idCentroAz);

	List<AllegatoDTO> getListaAllegatiControllo(Long idControllo);

    CarTResponsabilePassaporto getResponsabileFitosanitarioByIdDomanda(Long idDomanda);

	List<ControlloDTO> getControlliByIdCentroAziendale(Long idCentroAziendale);
    List<CampioneDTO> getListaCampioniByIdControllo(Long idControllo);

    Long generaNumeroVerbale();	
	Long lockControllo(Long idControllo);

	List<SementeDTO> getSementiByIdControllo(Long idControllo);  
	
	List<GenereSpecieDTO> getGenereSpecieByIdControlloFisisco(Long idControlloFisico);
	
	List<SpedizioniereDTO> getElencoOperatoriAttivi(RicercaOperatoreForm ricercaOperatore); 
	
	Long generaNumeroVerbaleMf();
	
	Long generaNumeroVerbaleCo();

	List<CarDNormaVerbale> getListaNormeVerbaliByIdControllo(Long idControllo);

	List<SementeDTO> getListaSementiByIdControllo(Long idControllo);

	List<CarDTipologiaControllo> getListeTipologiaControlloByIdControllo(Long idControllo);

	List<CarDStrutturaAttrezzatura> getListaStruttureAttrezzatureByIdControlloFisico(Long idControlloFisico);

	List<CarDMetodoProduzione> getMetodiProduzioneByIdControlloFisico(Long idControlloFisico);

	List<CarDTipoIrrigazione> getListaTipiIrrigazioneByIdControlloFisico(Long idControlloFisico);

	List<CarDConoscenzaProfessionale> getListaConoscenzeProfessionaliByIdControlloFisico(Long idControlloFisico);

	List<RequisitiProfessionaliDTO> getListaRequisitiProfessionaliByIdControlloFisico(Long idControlloFisico);

	List<MisuraDTO> getListaTipologieMisuraByIdMisuraUfficiale(Long idMisuraUfficiale);

	List<IspettoreDTO> getIspettoriDispByIdMisura(Long idMisuraUfficiale);

	List<IspettoreDTO> getIspettoriConstByIdMisura(Long idMisuraUfficiale);

	List<IspettoreDTO> getIspettoriControlloByIdControllo(Long idControllo);
	
	List<TipologiaAttMaterialeDTO> getTipologieAttMaterialeDomandeRuop(Long idDomanda);
	
	List<CodeDescriptionDTO> getDenomTipologieProduttiveDomandeRuop(@Param("idDomanda") Long idDomanda, @Param("idTipoModello") Long idTipoModello, @Param("gruppo") Long gruppo);
	
	Long getVersioneControlloByDataInserimento(@Param("dataInserimento") Date dataInserimento);

	List<CarDOrgNocivo> getListaOrgNocByIdControlloFisico(Long idControlloFisico);

	List<CarDMateriale> getAttivitaSementiByIdDomanda(Long idDomanda);
	
	void deleteRControlloMaterialeByIdControllo(Long idControllo);
	
	List<CarDMateriale> getAttivitaSementiByIdControllo(Long idControllo);
	
	Long getMaxIdDomandaByIdCentroAzPassaporto(@Param("idCentroAzPassaporto") Long idCentroAzPassaporto);
	

}
