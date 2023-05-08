package it.aizoon.ersaf.form;

/**
 * @author francesco.giuffrida
 *
 */
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.util.CaronteUtils;

public class RicercaRichiestaForm extends BaseForm {

	private Long idRichiesta; // Id Richiesta
	@NumberFormat(style = Style.NUMBER)
	private Long idStatoRichiesta; // Stato Richiesta
	@Pattern(regexp = "^[0-9]*$", message = "${richiesta.numeric.invalid}")
	private String codRichiesta; // N.Richiesta
	private String numeroCertificato; // N. Certificato
	@NumberFormat(style = Style.NUMBER)
	private Long idUfficioDoganaleEntrata; // Dogana
	private String spedizioniere; // Spedizioniere
	private String identifMezzoTrasporto; // Documento Mezzo
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInoltro; // Data Richiesta
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataEsecuzione; // Data Esecuzione
	private String denomMittente; // Mittente
	private String denomDestinatario; // Destinatario
	@NumberFormat(style = Style.NUMBER)
	private Long idUtenteInsert;
	private String codTipoRichiesta;
	private String idTipoRichiesta;
	@NumberFormat(style = Style.NUMBER)
  private Long idProvinciaMittente;
	@NumberFormat(style = Style.NUMBER)
  private Long idIspettoreFirmatario; 

	public Long getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(Long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public Long getIdStatoRichiesta() {
		return idStatoRichiesta;
	}

	public void setIdStatoRichiesta(Long idStatoRichiesta) {
		this.idStatoRichiesta = idStatoRichiesta;
	}

	public String getCodRichiesta() {
		return codRichiesta;
	}

	public void setCodRichiesta(String codRichiesta) {
		this.codRichiesta = CaronteUtils.convertToUpperCase(codRichiesta);
	}

	public String getNumeroCertificato() {
		return numeroCertificato;
	}

	public void setNumeroCertificato(String numeroCertificato) {
		this.numeroCertificato = CaronteUtils.convertToUpperCase(numeroCertificato);
	}

	public Long getIdUfficioDoganaleEntrata() {
		return idUfficioDoganaleEntrata;
	}

	public void setIdUfficioDoganaleEntrata(Long idUfficioDoganaleEntrata) {
		this.idUfficioDoganaleEntrata = idUfficioDoganaleEntrata;
	}

	public String getSpedizioniere() {
		return spedizioniere;
	}

	public void setSpedizioniere(String spedizioniere) {
		this.spedizioniere = CaronteUtils.convertToUpperCase(spedizioniere);
	}

	public String getIdentifMezzoTrasporto() {
		return identifMezzoTrasporto;
	}

	public void setIdentifMezzoTrasporto(String identifMezzoTrasporto) {
		this.identifMezzoTrasporto = CaronteUtils.convertToUpperCase(identifMezzoTrasporto);
	}

	public Date getDataInoltro() {
		return dataInoltro;
	}

	public void setDataInoltro(Date dataInoltro) {
		this.dataInoltro = dataInoltro;
	}

	public Date getDataEsecuzione() {
		return dataEsecuzione;
	}

	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}

	public String getDenomMittente() {
		return denomMittente;
	}

	public void setDenomMittente(String denomMittente) {
		this.denomMittente = CaronteUtils.convertToUpperCase(denomMittente);
	}

	public String getDenomDestinatario() {
		return denomDestinatario;
	}

	public void setDenomDestinatario(String denomDestinatario) {
		this.denomDestinatario = CaronteUtils.convertToUpperCase(denomDestinatario);
	}

	public Long getIdUtenteInsert() {
		return idUtenteInsert;
	}

	public void setIdUtenteInsert(Long idUtenteInsert) {
		this.idUtenteInsert = idUtenteInsert;
	}

	public String getCodTipoRichiesta() {
		return codTipoRichiesta;
	}

	public void setCodTipoRichiesta(String codTipoRichiesta) {
		this.codTipoRichiesta = codTipoRichiesta;
	}

	public String getIdTipoRichiesta() {
		return idTipoRichiesta;
	}

	public void setIdTipoRichiesta(String idTipoRichiesta) {
		this.idTipoRichiesta = idTipoRichiesta;
	}

  public Long getIdProvinciaMittente() {
    return idProvinciaMittente;
  }

  public void setIdProvinciaMittente(Long idProvinciaMittente) {
    this.idProvinciaMittente = idProvinciaMittente;
  }

  public Long getIdIspettoreFirmatario() {
    return idIspettoreFirmatario;
  }

  public void setIdIspettoreFirmatario(Long idIspettoreFirmatario) {
    this.idIspettoreFirmatario = idIspettoreFirmatario;
  }

}