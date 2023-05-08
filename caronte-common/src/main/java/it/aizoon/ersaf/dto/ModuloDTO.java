package it.aizoon.ersaf.dto;

import it.aizoon.ersaf.dto.generati.CarDTipoModulo;



@SuppressWarnings("serial")
public class ModuloDTO extends CarDTipoModulo {

	  
	  private Long idModulo;
	  private Long idComunicazione;

	  public Long getIdModulo() {
	    return idModulo;
	  }

	  public void setIdModulo(Long idModulo) {
	    this.idModulo = idModulo;
	  }

	  public Long getIdComunicazione() {
	    return idComunicazione;
	  }

	  public void setIdComunicazione(Long idComunicazione) {
	    this.idComunicazione = idComunicazione;
	  }

}
