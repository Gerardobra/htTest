package it.aizoon.ersaf.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

public class GenereDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idGenere;
	private String codGenere;
	private String denomGenere;
	private String denomGenereCommerciale;
	private String denomGenereLocale;
	private Long idAutoreEppo;
	private String descAutoreEppo;
	private Long idNazione;
	private Long idUtenteInsert;
	private Date dataInsert;
	private Date dataUpdate;
	private boolean attivo;
	private boolean preferred;
	private String denomNazione;
	private String descLingua;
	private Long idLingua;
	private Long idLinguaLocale;
	
	private List<SpecieDTO> listaSpecie;
	
	public Long getIdGenere() {
		return idGenere;
	}
	public void setIdGenere(Long idGenere) {
		this.idGenere = idGenere;
	}
	public String getCodGenere() {
		return codGenere;
	}
	public void setCodGenere(String codGenere) {
		this.codGenere = codGenere;
	}
	public String getDenomGenere() {
		return denomGenere;
	}
	public void setDenomGenere(String denomGenere) {
		this.denomGenere = denomGenere;
	}
	public Long getIdAutoreEppo() {
		return idAutoreEppo;
	}
	public void setIdAutoreEppo(Long idAutoreEppo) {
		this.idAutoreEppo = idAutoreEppo;
	}
	public Long getIdNazione() {
		return idNazione;
	}
	public void setIdNazione(Long idNazione) {
		this.idNazione = idNazione;
	}
	public Long getIdUtenteInsert() {
    return idUtenteInsert;
  }
  public void setIdUtenteInsert(Long idUtenteInsert) {
    this.idUtenteInsert = idUtenteInsert;
  }
  public Date getDataInsert() {
		return dataInsert;
	}
	public void setDataInsert(Date dataInsert) {
		this.dataInsert = dataInsert;
	}
	public Date getDataUpdate() {
		return dataUpdate;
	}
	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}
	public boolean isAttivo() {
		return attivo;
	}
	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}
	public String getDenomGenereCommerciale() {
		return denomGenereCommerciale;
	}
	public void setDenomGenereCommerciale(String denomGenereCommerciale) {
		this.denomGenereCommerciale = denomGenereCommerciale;
	}
	public String getDescAutoreEppo() {
		return descAutoreEppo;
	}
	public void setDescAutoreEppo(String descAutoreEppo) {
		this.descAutoreEppo = descAutoreEppo;
	}
	public boolean isPreferred() {
		return preferred;
	}
	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}
	public String getDenomNazione() {
		return denomNazione;
	}
	public void setDenomNazione(String denomNazione) {
		this.denomNazione = denomNazione;
	}
	public String getDescLingua() {
		return descLingua;
	}
	public void setDescLingua(String descLingua) {
		this.descLingua = descLingua;
	}
	public String getDenomGenereLocale() {
		return denomGenereLocale;
	}
	public void setDenomGenereLocale(String denomGenereLocale) {
		this.denomGenereLocale = denomGenereLocale;
	}
	public Long getIdLingua() {
		return idLingua;
	}
	public void setIdLingua(Long idLingua) {
		this.idLingua = idLingua;
	}
	public Long getIdLinguaLocale() {
		return idLinguaLocale;
	}
	public void setIdLinguaLocale(Long idLinguaLocale) {
		this.idLinguaLocale = idLinguaLocale;
	}
  public List<SpecieDTO> getListaSpecie() {
    return listaSpecie;
  }
  public void setListaSpecie(List<SpecieDTO> listaSpecie) {
    this.listaSpecie = listaSpecie;
  }
  
  public String getIdSpecieConcatenati() {
    StringJoiner result = new StringJoiner(",");
    
    if (getListaSpecie() != null) {
      for (SpecieDTO specie : getListaSpecie()) {
        result.add(""+specie.getIdSpecie());
      }
    }
    
    return result.toString();
  }
}
