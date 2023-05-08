package it.aizoon.ersaf.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import it.aizoon.ersaf.dto.generati.CarDAssociazioneSezione;
import it.aizoon.ersaf.util.CaronteConstants;

/**
 * @author ff
 */
public class UtenteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String nome;
	private String cognome;
	private Long idSpedizioniere;
	private boolean superUser;
	private Long idIspettore;
	private boolean abilitato;
	private String codiceFiscale;
	private String email;
	private String note;
	private Date dataInserimento;
	private Date dataUpdate;
	private Long idAttore;
	private Long idGruppo;
	private String denomGruppo;
	private Long idRuolo;
	private String denomRuolo;
	private String spedizioniere;
	private String numeroTessera;
	private boolean attivato;
	private String denomSpedizioniere;
	private Long idUtente;
	private String password;
	private boolean passwordImpostata;
	private Date dataToken;
	private String token;
	private Long idAssociazioneSezione;
	private String descrAssociazioneSezione;
	private Long idTipoSpedizioniere;
	private boolean modificatoAdmin;
	private boolean sezioneImport;
	private boolean sezioneExport;
	private boolean sezioneVivai;
	private boolean sezioneAutorizzazioni;
	private boolean sezioneControlli;
	private String telefono;
	private List<GrantDTO> grant = new ArrayList<>();
	private List<CarDAssociazioneSezione> sezioni;
	private boolean autorizPagamPosticip;
	private boolean rifiutato;
	private String motivoRifiuto;
	private String cellulare;
	private boolean flEliminabile;
	private boolean flSuperUser;
	private String spedizionieriSecondari;

	public boolean isAutorizPagamPosticip() {
		return autorizPagamPosticip;
	}

	public void setAutorizPagamPosticip(boolean autorizPagamPosticip) {
		this.autorizPagamPosticip = autorizPagamPosticip;
	}

	// @FF questo DEVE restare così
	@Override
	public String toString() {
		return email != null ? email : "anonymous";
	}

	public void setGrant(List<GrantDTO> grant) {
		this.grant = grant;
	}

	public List<GrantDTO> getGrant() {
		return grant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Long getIdSpedizioniere() {
		return idSpedizioniere;
	}

	public void setIdSpedizioniere(Long idSpedizioniere) {
		this.idSpedizioniere = idSpedizioniere;
	}

	public boolean isSuperUser() {
		return superUser;
	}

	public void setSuperUser(boolean superUser) {
		this.superUser = superUser;
	}

	public Long getIdIspettore() {
		return idIspettore;
	}

	public void setIdIspettore(Long idIspettore) {
		this.idIspettore = idIspettore;
	}

	public String getDenominazione() {
		StringBuilder result = new StringBuilder();

		if (!StringUtils.isEmpty(this.nome)) {
			result.append(this.nome);
		}

		if (!StringUtils.isEmpty(this.cognome)) {
			if (result.length() > 0) {
				result.append(" ");
			}
			result.append(this.cognome);
		}

		return result.toString();
	}

	public boolean isAbilitato() {
		return abilitato;
	}

	public void setAbilitato(boolean abilitato) {
		this.abilitato = abilitato;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataUpdate() {
		return dataUpdate;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	public Long getIdAttore() {
		return idAttore;
	}

	public void setIdAttore(Long idAttore) {
		this.idAttore = idAttore;
	}

	public Long getIdGruppo() {
		return idGruppo;
	}

	public void setIdGruppo(Long idGruppo) {
		this.idGruppo = idGruppo;
	}

	public String getDenomGruppo() {
		return denomGruppo;
	}

	public void setDenomGruppo(String denomGruppo) {
		this.denomGruppo = denomGruppo;
	}

	public Long getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(Long idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getDenomRuolo() {
		return denomRuolo;
	}

	public void setDenomRuolo(String denomRuolo) {
		this.denomRuolo = denomRuolo;
	}

	public String getSpedizioniere() {
		return spedizioniere;
	}

	public void setSpedizioniere(String spedizioniere) {
		this.spedizioniere = spedizioniere;
	}

	public String getNumeroTessera() {
		return numeroTessera;
	}

	public void setNumeroTessera(String numeroTessera) {
		this.numeroTessera = numeroTessera;
	}

	public boolean isAttivato() {
		return attivato;
	}

	public void setAttivato(boolean attivato) {
		this.attivato = attivato;
	}

	public String getDenomSpedizioniere() {
		return denomSpedizioniere;
	}

	public void setDenomSpedizioniere(String denomSpedizioniere) {
		this.denomSpedizioniere = denomSpedizioniere;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPasswordImpostata() {
		return passwordImpostata;
	}

	public void setPasswordImpostata(boolean passwordImpostata) {
		this.passwordImpostata = passwordImpostata;
	}

	public Long getIdAssociazioneSezione() {
		return idAssociazioneSezione;
	}

	public void setIdAssociazioneSezione(Long idAssociazioneSezione) {
		this.idAssociazioneSezione = idAssociazioneSezione;
	}

	public String getDescrAssociazioneSezione() {
		String ret = "";
		boolean importFlag = false;
		boolean exportFlag = false;
		boolean vivaiFlag = false;
		boolean autorizzazioniFlag = false;
		boolean controlliFlag = false;
		if (this.sezioni != null){
			for (CarDAssociazioneSezione s : this.sezioni) {
				if (s.getIdAssociazioneSezione() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT)
					exportFlag = true;
				if (s.getIdAssociazioneSezione() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT)
					importFlag = true;
				if (s.getIdAssociazioneSezione() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI)
					vivaiFlag = true;
				if (s.getIdAssociazioneSezione() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI)
					autorizzazioniFlag = true;
				if (s.getIdAssociazioneSezione() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI)
					controlliFlag = true;
			}
		}	

		
		/*if(importFlag && exportFlag && vivaiFlag && autorizzazioniFlag)
			ret = "Import/Export/Vivai/Autorizzazioni";
		else if(importFlag && !exportFlag && !vivaiFlag && !autorizzazioniFlag)
			ret = "Import";
		else if(!importFlag && exportFlag && !vivaiFlag && !autorizzazioniFlag)
			ret = "Export";
		else if(!importFlag && !exportFlag && !autorizzazioniFlag && vivaiFlag)
			ret = "Vivai";
		else if(importFlag && exportFlag && !vivaiFlag && !autorizzazioniFlag)
			ret ="Import/Export";
		else if(importFlag && !exportFlag && !autorizzazioniFlag && vivaiFlag)
			ret ="Import/Vivai";
		else if(!importFlag && !autorizzazioniFlag && exportFlag && vivaiFlag)
			ret ="Export/Vivai";*/
		
		
		if(importFlag)
			ret+="Import";
		if(exportFlag)
			ret+="/Export";
		if(vivaiFlag)
			ret+="/Comunicazione Vegetali";
		if(autorizzazioniFlag)
			ret+="/Autorizzazioni";
		if(controlliFlag)
			ret+="/Controlli";
		

		return ret;
	}

	public void setDescrAssociazioneSezione(String descrAssociazioneSezione) {
		this.descrAssociazioneSezione = descrAssociazioneSezione;
	}

	public Date getDataToken() {
		return dataToken;
	}

	public void setDataToken(Date dataToken) {
		this.dataToken = dataToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getIdTipoSpedizioniere() {
		return idTipoSpedizioniere;
	}

	public void setIdTipoSpedizioniere(Long idTipoSpedizioniere) {
		this.idTipoSpedizioniere = idTipoSpedizioniere;
	}

	public boolean isModificatoAdmin() {
		return modificatoAdmin;
	}

	public void setModificatoAdmin(boolean modificatoAdmin) {
		this.modificatoAdmin = modificatoAdmin;
	}

	public List<CarDAssociazioneSezione> getSezioni() {
		return sezioni;
	}

	public void setSezioni(List<CarDAssociazioneSezione> sezioni) {
		this.sezioni = sezioni;
	}

	public boolean isSezioneImport() {
		if (this.sezioni != null)
			for (CarDAssociazioneSezione s : this.sezioni) {
				if (s.getIdAssociazioneSezione() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_IMPORT)
					return true;
			}
		return false;
	}

	public void setSezioneImport(boolean sezioneImport) {
		this.sezioneImport = sezioneImport;
	}

	public boolean isSezioneExport() {
		if (this.sezioni != null)
			for (CarDAssociazioneSezione s : this.sezioni) {
				if (s.getIdAssociazioneSezione() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_EXPORT)
					return true;
			}
		return false;
	}

	public void setSezioneExport(boolean sezioneExport) {
		this.sezioneExport = sezioneExport;
	}
	
	public boolean isSezioneVivai() {
		if (this.sezioni != null)
			for (CarDAssociazioneSezione s : this.sezioni) {
				if (s.getIdAssociazioneSezione() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_VIVAI)
					return true;
			}
		return false;
	}

	public void setSezioneVivai(boolean sezioneVivai) {
		this.sezioneVivai = sezioneVivai;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
		this.motivoRifiuto = motivoRifiuto;
	}

	public boolean isSezioneAutorizzazioni() {
		if (this.sezioni != null)
			for (CarDAssociazioneSezione s : this.sezioni) {
				if (s.getIdAssociazioneSezione() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_AUTORIZZAZIONI)
					return true;
			}
		return false;
	}

	public void setSezioneAutorizzazioni(boolean sezioneAutorizzazioni) {
		this.sezioneAutorizzazioni = sezioneAutorizzazioni;
	}
	
	public boolean isSezioneControlli() {
		if (this.sezioni != null)
			for (CarDAssociazioneSezione s : this.sezioni) {
				if (s.getIdAssociazioneSezione() == CaronteConstants.ID_ASSOCIAZIONE_SEZIONE_CONTROLLI)
					return true;
			}
		return false;
	}

	public void setSezioneControlli(boolean sezioneControlli) {
		this.sezioneControlli = sezioneControlli;
	}

	/**
	 * @return the cellulare
	 */
	public String getCellulare() {
		return cellulare;
	}

	/**
	 * @param cellulare the cellulare to set
	 */
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	/**
	 * @return the flEliminabile
	 */
	public boolean isFlEliminabile() {
		return flEliminabile;
	}

	/**
	 * @param flEliminabile the flEliminabile to set
	 */
	public void setFlEliminabile(boolean flEliminabile) {
		this.flEliminabile = flEliminabile;
	}

	/**
	 * @return the flSuperUser
	 */
	public boolean isFlSuperUser() {
		return flSuperUser;
	}

	/**
	 * @param flSuperUser the flSuperUser to set
	 */
	public void setFlSuperUser(boolean flSuperUser) {
		this.flSuperUser = flSuperUser;
	}

	/**
	 * @return the spedizionieriSecondari
	 */
	public String getSpedizionieriSecondari() {
		return spedizionieriSecondari;
	}

	/**
	 * @param spedizionieriSecondari the spedizionieriSecondari to set
	 */
	public void setSpedizionieriSecondari(String spedizionieriSecondari) {
		this.spedizionieriSecondari = spedizionieriSecondari;
	}

	

}
