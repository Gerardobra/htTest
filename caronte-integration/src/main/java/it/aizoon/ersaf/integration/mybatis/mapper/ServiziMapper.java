package it.aizoon.ersaf.integration.mybatis.mapper;

import java.util.List;

import org.mybatis.cdi.Mapper;

import it.aizoon.ersaf.dto.BaseDto;
import it.aizoon.ersaf.dto.DatiRichiesteDTO;
import it.aizoon.ersaf.dto.DatiSianDTO;
import it.aizoon.ersaf.dto.GenericExample;
import it.aizoon.ersaf.dto.MerceRichiestaDatiSianDTO;
import it.aizoon.ersaf.dto.ProfiloNonAutorizzatoDTO;
import it.aizoon.ersaf.dto.RiexportDatiSianDTO;
import it.aizoon.ersaf.form.RicercaDatiRichiesteForm;
import it.aizoon.ersaf.form.RicercaDatiSianForm;

/**
 * @author alessandro.morra
 */
@Mapper
public interface ServiziMapper extends it.aizoon.ersaf.integration.mybatis.mapper.IBaseDao<BaseDto, GenericExample> {

	List<DatiRichiesteDTO> getElencoDatiRichieste(RicercaDatiRichiesteForm richiesteForm);

	List<DatiSianDTO> getElencoDatiSian(RicercaDatiSianForm sianForm);

	List<MerceRichiestaDatiSianDTO> getMerciPerDatiSian(RicercaDatiSianForm sianForm);

	List<RiexportDatiSianDTO> getDatiRiexportSian(RicercaDatiSianForm sianForm);
	
	List<ProfiloNonAutorizzatoDTO> getUtentiDaAbilitare();

}
