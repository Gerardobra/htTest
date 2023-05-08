package it.aizoon.ersaf.bean.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.aizoon.ersaf.dto.AllegatoDTO;
import it.aizoon.ersaf.dto.ModuloDTO;
import it.aizoon.ersaf.dto.generati.CarTAllegatoControllo;
import it.aizoon.ersaf.dto.generati.CarTAllegatoDomanda;
import it.aizoon.ersaf.exception.BusinessException;
import it.aizoon.ersaf.form.BaseForm;


public class AllegatiControlliForm extends BaseForm {
  
  //private List<MultipartFile> fileModulo;
  private List<MultipartFile> fileAllegato;
  private List<String> idTipoAllegato;
  private List<String> idAllegato;
  private List<String> descAllegato;


  public List<MultipartFile> getFileAllegato() {
    return fileAllegato;
  }
  
  public void setFileAllegato(List<MultipartFile> fileAllegato) {
    this.fileAllegato = fileAllegato;
  }

  public List<String> getIdTipoAllegato() {
    return idTipoAllegato;
  }

  public void setIdTipoAllegato(List<String> idTipoAllegato) {
    this.idTipoAllegato = idTipoAllegato;
  }

  public List<String> getIdAllegato() {
    return idAllegato;
  }

  public void setIdAllegato(List<String> idAllegato) {
    this.idAllegato = idAllegato;
  }

  public List<String> getDescAllegato() {
    return descAllegato;
  }

  public void setDescAllegato(List<String> descAllegato) {
    this.descAllegato = descAllegato;
  }

  

  public List<AllegatoDTO> convertiFileAllegati() {
    Map<String, AllegatoDTO> mappaAllegati = new HashMap<>();

    if (fileAllegato != null && idTipoAllegato != null && idAllegato != null) {

      for (int i = 0; i < idTipoAllegato.size(); i++) {
        String idTipo = idTipoAllegato.get(i);
        AllegatoDTO allegatoMappa = mappaAllegati.get(idTipo);
        CarTAllegatoControllo allegato = new CarTAllegatoControllo();

        if (allegatoMappa == null) {
          allegatoMappa = new AllegatoDTO();
          allegatoMappa.setIdTipoAllegato(Long.valueOf(idTipo));
          allegatoMappa.setListaAllegatiControllo(new ArrayList<>());

          if (descAllegato != null && !StringUtils.isEmpty(descAllegato.get(i))) {
            allegatoMappa.setDescAllegato(String.valueOf(descAllegato.get(i)));
          }

          mappaAllegati.put(idTipo, allegatoMappa);
        }

        MultipartFile multipartFile = fileAllegato.get(i);

        if (StringUtils.isEmpty(multipartFile.getOriginalFilename()) && StringUtils.isEmpty(idAllegato.get(i))) {
          continue;
        }

        try {
          allegato.setNomeFile(multipartFile.getOriginalFilename());
          allegato.setAllegato(multipartFile.getBytes());
        } catch (IOException e) {
          logger.error("Errore nell'upload del contenuto dell'allegato", e);
        }

        if (!StringUtils.isEmpty(idAllegato.get(i))) {
          allegato.setIdAllegatoControllo(Long.valueOf(idAllegato.get(i)));
        }

        if (!StringUtils.isEmpty(descAllegato.get(i))) {
          allegato.setDescAllegato(String.valueOf(descAllegato.get(i)));
        }

        allegatoMappa.getListaAllegatiControllo().add(allegato);
      }
    }

    return new ArrayList<>(mappaAllegati.values());
  }

  /*PARTE RELATIVA AI MODULI
public void convertiFileModuli(List<ModuloDTO> listaModuli)  throws BusinessException {
	if (fileModulo != null) {
		  if (!listaModuli.isEmpty()) {
			  if (listaModuli.size() < fileModulo.size()) {
				  /*
				   * Non dovrebbe verificarsi a meno di modifiche manuali sul db, si mette
				   * il controllo solo per evitare nel caso errori java
				   */
	/*			  throw new BusinessException("Errore di configurazione nella scansione dei moduli");
			  }

			  Iterator<ModuloDTO> iteratorModuli = listaModuli.iterator();
			  
			  for (int i = 0; iteratorModuli.hasNext(); i++) {
				  ModuloDTO modulo = iteratorModuli.next();
				  MultipartFile multipartFile = fileModulo.get(i);
				  
				  try {
					  if (multipartFile != null && multipartFile.getBytes() != null && multipartFile.getBytes().length>0) {
						  modulo.setModulo(multipartFile.getBytes());
						  modulo.setNomeFile(multipartFile.getOriginalFilename());
					  }else {
						  iteratorModuli.remove();
					  }
				  } catch (IOException e) {
					  logger.error("Errore nell'upload del contenuto del modulo", e);
				  }
			  }
		  }
	  }
}

public List<MultipartFile> getFileModulo() {
	return fileModulo;
}

public void setFileModulo(List<MultipartFile> fileModulo) {
	this.fileModulo = fileModulo;
}
*/
}
