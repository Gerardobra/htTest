package it.aizoon.ersaf.dto;

/**
 * @author francesco.giuffrida
 *
 */
import java.util.Date;

@SuppressWarnings("serial")
public class RichiestaDto extends BaseDto {

	private Long idRichiesta;
	private Long idStatoRichiesta;
	private Long idTipoRichiesta;
	private String descTipoRichiesta;
	private String statoRichiesta;
	private String numeroRichiesta;
	private String dogana;
	private String spedizioniere;
	private Date dataInoltro; // car_t_richiesta.data_inoltro
	private Date dataPartenzaMerce; // car_t_richiesta.data_partenza_merce
	private Date dataEsecuzione; // car_t_richiesta.data_esecuzione
	private String documentoMezzo; // Mittente
	private String numeroCertificato; // Mittente
	private String mittente; // Mittente
	private String destinatario; // Destinatario

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

	public String getStatoRichiesta() {
		return statoRichiesta;
	}

	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}

	public String getNumeroRichiesta() {
		return numeroRichiesta;
	}

	public void setNumeroRichiesta(String numeroRichiesta) {
		this.numeroRichiesta = numeroRichiesta;
	}

	public String getDogana() {
		return dogana;
	}

	public void setDogana(String dogana) {
		this.dogana = dogana;
	}

	public String getSpedizioniere() {
		return spedizioniere;
	}

	public void setSpedizioniere(String spedizioniere) {
		this.spedizioniere = spedizioniere;
	}

	public Date getDataInoltro() {
		return dataInoltro;
	}

	public void setDataInoltro(Date dataInoltro) {
		this.dataInoltro = dataInoltro;
	}

	public Date getDataPartenzaMerce() {
    return dataPartenzaMerce;
  }

  public void setDataPartenzaMerce(Date dataPartenzaMerce) {
    this.dataPartenzaMerce = dataPartenzaMerce;
  }

  public Date getDataEsecuzione() {
		return dataEsecuzione;
	}

	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}

	public String getDocumentoMezzo() {
		return documentoMezzo;
	}

	public void setDocumentoMezzo(String documentoMezzo) {
		this.documentoMezzo = documentoMezzo;
	}

	public String getNumeroCertificato() {
		return numeroCertificato;
	}

	public void setNumeroCertificato(String numeroCertificato) {
		this.numeroCertificato = numeroCertificato;
	}

	public String getMittente() {
		return mittente;
	}

	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public Long getIdTipoRichiesta() {
		return idTipoRichiesta;
	}

	public void setIdTipoRichiesta(Long idTipoRichiesta) {
		this.idTipoRichiesta = idTipoRichiesta;
	}

	public String getDescTipoRichiesta() {
		return descTipoRichiesta;
	}

	public void setDescTipoRichiesta(String descTipoRichiesta) {
		this.descTipoRichiesta = descTipoRichiesta;
	}

}