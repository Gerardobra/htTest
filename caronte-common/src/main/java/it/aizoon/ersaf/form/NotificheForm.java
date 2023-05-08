package it.aizoon.ersaf.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class NotificheForm {
	
	private long idMessaggioGenerico;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInserimentoDaGenerico;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInserimentoAGenerico;
	private String messaggioGenerico;
	
	private long idMessaggioServizio;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInserimentoDaServizio;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInserimentoAServizio;
	private String messaggioServizio;
	
	private long idMessaggioCertificato;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInserimentoDaCertificato;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInserimentoACertificato;
	private String messaggioCertificato;
	
	
	public Date getDataInserimentoDaGenerico() {
		return dataInserimentoDaGenerico;
	}
	public void setDataInserimentoDaGenerico(Date dataInserimentoDaGenerico) {
		this.dataInserimentoDaGenerico = dataInserimentoDaGenerico;
	}
	public Date getDataInserimentoAGenerico() {
		return dataInserimentoAGenerico;
	}
	public void setDataInserimentoAGenerico(Date dataInserimentoAGenerico) {
		this.dataInserimentoAGenerico = dataInserimentoAGenerico;
	}
	public String getMessaggioGenerico() {
		return messaggioGenerico;
	}
	public void setMessaggioGenerico(String messaggioGenerico) {
		this.messaggioGenerico = messaggioGenerico;
	}
	public Date getDataInserimentoDaServizio() {
		return dataInserimentoDaServizio;
	}
	public void setDataInserimentoDaServizio(Date dataInserimentoDaServizio) {
		this.dataInserimentoDaServizio = dataInserimentoDaServizio;
	}
	public Date getDataInserimentoAServizio() {
		return dataInserimentoAServizio;
	}
	public void setDataInserimentoAServizio(Date dataInserimentoAServizio) {
		this.dataInserimentoAServizio = dataInserimentoAServizio;
	}
	public String getMessaggioServizio() {
		return messaggioServizio;
	}
	public void setMessaggioServizio(String messaggioServizio) {
		this.messaggioServizio = messaggioServizio;
	}
	public Date getDataInserimentoDaCertificato() {
		return dataInserimentoDaCertificato;
	}
	public void setDataInserimentoDaCertificato(Date dataInserimentoDaCertificato) {
		this.dataInserimentoDaCertificato = dataInserimentoDaCertificato;
	}
	public Date getDataInserimentoACertificato() {
		return dataInserimentoACertificato;
	}
	public void setDataInserimentoACertificato(Date dataInserimentoACertificato) {
		this.dataInserimentoACertificato = dataInserimentoACertificato;
	}
	public String getMessaggioCertificato() {
		return messaggioCertificato;
	}
	public void setMessaggioCertificato(String messaggioCertificato) {
		this.messaggioCertificato = messaggioCertificato;
	}
	public long getIdMessaggioGenerico() {
		return idMessaggioGenerico;
	}
	public void setIdMessaggioGenerico(long idMessaggioGenerico) {
		this.idMessaggioGenerico = idMessaggioGenerico;
	}
	public long getIdMessaggioServizio() {
		return idMessaggioServizio;
	}
	public void setIdMessaggioServizio(long idMessaggioServizio) {
		this.idMessaggioServizio = idMessaggioServizio;
	}
	public long getIdMessaggioCertificato() {
		return idMessaggioCertificato;
	}
	public void setIdMessaggioCertificato(long idMessaggioCertificato) {
		this.idMessaggioCertificato = idMessaggioCertificato;
	}

}
