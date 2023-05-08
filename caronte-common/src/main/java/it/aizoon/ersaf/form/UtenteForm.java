package it.aizoon.ersaf.form;

/**
 * @author Nicolò Mandrile
 *
 */
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import it.aizoon.ersaf.util.CaronteUtils;

public class UtenteForm extends BaseForm {

	private Long idTipoSpedizioniere;
	private Long idSpedizioniere;
	private String denomSpedizioniere;
	private String cognomeNome;
	private String nome;
	private String cognome;
	private String email;
	private String codice;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInserimentoDa;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInserimentoA;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInserimento;
	private String codiceFiscale;
	private Long idGruppo;
	private Long idRuolo;
	private boolean abilitato;
	private boolean rifiutato;
	private String motivoRifiuto;
	private String note;
	private Long idUtente;
	private String password;
	private String confermaPassword;
	private String oldPassword;
	private String token;
	private String numeroTelefonoUtente;
	private String numeroCellUtente;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascita;
	private Date dataAccettazionePrivacy;
	private String checkAccettazionePrivacy;
	private Long idAssociazioneSezione;
	private String descrAssociazioneSezione;
	private boolean sezioneImport;
	private boolean sezioneExport;
	private boolean sezioneVivai;
	private boolean sezioneAutorizzazioni;
	private boolean sezioneControlli;
	
	public Long getIdTipoSpedizioniere() {
		return idTipoSpedizioniere;
	}

	public void setIdTipoSpedizioniere(Long idTipoSpedizioniere) {
		this.idTipoSpedizioniere = idTipoSpedizioniere;
	}

	public String getCognomeNome() {
		return cognomeNome;
	}

	public void setCognomeNome(String cognomeNome) {
		this.cognomeNome = CaronteUtils.convertToUpperCase(cognomeNome);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = CaronteUtils.convertToUpperCase(email);;
	}

	public Date getDataInserimentoDa() {
		return dataInserimentoDa;
	}

	public void setDataInserimentoDa(Date dataInserimentoDa) {
		this.dataInserimentoDa = dataInserimentoDa;
	}

	public Date getDataInserimentoA() {
		return dataInserimentoA;
	}

	public void setDataInserimentoA(Date dataInserimentoA) {
		this.dataInserimentoA = dataInserimentoA;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = CaronteUtils.convertToUpperCase(codiceFiscale);
	}

	public Long getIdGruppo() {
		return idGruppo;
	}

	public void setIdGruppo(Long idGruppo) {
		this.idGruppo = idGruppo;
	}

	public Long getIdRuolo() {
    return idRuolo;
  }

  public void setIdRuolo(Long idRuolo) {
    this.idRuolo = idRuolo;
  }

  public boolean isAbilitato() {
		return abilitato;
	}

	public void setAbilitato(boolean abilitato) {
		this.abilitato = abilitato;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = CaronteUtils.convertToUpperCase(codice);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = CaronteUtils.convertToUpperCase(nome);
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = CaronteUtils.convertToUpperCase(cognome);
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = CaronteUtils.convertToUpperCase(note);
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public Long getIdSpedizioniere() {
		return idSpedizioniere;
	}

	public void setIdSpedizioniere(Long idSpedizioniere) {
		this.idSpedizioniere = idSpedizioniere;
	}

	public String getDenomSpedizioniere() {
		return denomSpedizioniere;
	}

	public void setDenomSpedizioniere(String denomSpedizioniere) {
		this.denomSpedizioniere = CaronteUtils.convertToUpperCase(denomSpedizioniere);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfermaPassword() {
		return confermaPassword;
	}

	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}

	public Long getIdAssociazioneSezione() {
		return idAssociazioneSezione;
	}

	public void setIdAssociazioneSezione(Long idAssociazioneSezione) {
		this.idAssociazioneSezione = idAssociazioneSezione;
	}

	public String getDescrAssociazioneSezione() {
		return descrAssociazioneSezione;
	}

	public void setDescrAssociazioneSezione(String descrAssociazioneSezione) {
		this.descrAssociazioneSezione = CaronteUtils.convertToUpperCase(descrAssociazioneSezione);
	}

	public boolean isSezioneImport() {
    return sezioneImport;
  }

  public void setSezioneImport(boolean sezioneImport) {
    this.sezioneImport = sezioneImport;
  }

  public boolean isSezioneExport() {
    return sezioneExport;
  }

  public void setSezioneExport(boolean sezioneExport) {
    this.sezioneExport = sezioneExport;
  }

  public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNumeroTelefonoUtente() {
		return numeroTelefonoUtente;
	}

	public void setNumeroTelefonoUtente(String numeroTelefonoUtente) {
		this.numeroTelefonoUtente = numeroTelefonoUtente;
	}

	public boolean isRifiutato() {
		return rifiutato;
	}

	public void setRifiutato(boolean rifiutato) {
		this.rifiutato = rifiutato;
	}

	public String getMotivoRifiuto() {
		return motivoRifiuto;
	}

	public void setMotivoRifiuto(String motivoRifiuto) {
		this.motivoRifiuto = CaronteUtils.convertToUpperCase(motivoRifiuto);
	}

	public boolean isSezioneVivai() {
		return sezioneVivai;
	}

	public void setSezioneVivai(boolean sezioneVivai) {
		this.sezioneVivai = sezioneVivai;
	}

	public boolean isSezioneAutorizzazioni() {
		return sezioneAutorizzazioni;
	}

	public void setSezioneAutorizzazioni(boolean sezioneAutorizzazioni) {
		this.sezioneAutorizzazioni = sezioneAutorizzazioni;
	}

	public String getNumeroCellUtente() {
		return numeroCellUtente;
	}

	public void setNumeroCellUtente(String numeroCellUtente) {
		this.numeroCellUtente = numeroCellUtente;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public boolean isSezioneControlli() {
		return sezioneControlli;
	}

	public void setSezioneControlli(boolean sezioneControlli) {
		this.sezioneControlli = sezioneControlli;
	}

	public Date getDataAccettazionePrivacy() {
		return dataAccettazionePrivacy;
	}

	public void setDataAccettazionePrivacy(Date dataAccettazionePrivacy) {
		this.dataAccettazionePrivacy = dataAccettazionePrivacy;
	}

	public String getCheckAccettazionePrivacy() {
		return checkAccettazionePrivacy;
	}

	public void setCheckAccettazionePrivacy(String checkAccettazionePrivacy) {
		this.checkAccettazionePrivacy = CaronteUtils.convertToUpperCase(checkAccettazionePrivacy);
	}

}