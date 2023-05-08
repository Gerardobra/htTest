package it.aizoon.ersaf.integration.rest.protocollo.dto;

public class InputJsonProtocolloDto {
	
	/*
	 * {
    "indirizzoMittente": "indirizzoMittente",
    "cittaMittente": "cittaMittente",
    "comuneMittente": "comuneMittente",
    "provinciaMittente": "provinciaMittente",
    "capMittente": "capMittente",
    "regioneMittente": "regioneMittente",
    "descrizioneMittente": "descrizioneMittente",
    "email": "email@email.it",
    "fax": "fax",
    "telefono": "telefono",
    "partitaIva": "partitaIva",
    "codFisc": "cficfh76t54r567u",
    "codiceDestinatario": "SYSTEM_CARONTE",
    "oggettoProtocollo": "oggettoProtocollo",
    "tipoDocumento": "Verbale",
    "regioneMittente": "regioneMittente",
    "tipoAllegati": "Allegati di test",
    "mezzoSpedizione": "Raccomandata",
    "dataArrivo": "2021-04-17",
    "dataDocumento": "2021-04-17",
    "numeroAllegati": 0
	}
	 */
	
	public String indirizzoMittente;
	public String cittaMittente;
	public String comuneMittente;
	public String provinciaMittente;
	public String capMittente;
	public String regioneMittente;
	public String descrizioneMittente;
	public String email;
	public String fax;
	public String telefono;
	public String partitaIva;
	public String codFisc;
	public String codiceDestinatario;
	public String oggettoProtocollo;
	public String tipoDocumento;
	//public String regioneMittente; come creo questo campo??? nel json questo campo è ripetuto
	public String tipoAllegati;
	public String mezzoSpedizione;
	public String dataArrivo;
	public String dataDocumento;
	public int numeroAllegati;
	
	
	public String getIndirizzoMittente() {
		return indirizzoMittente;
	}
	public void setIndirizzoMittente(String indirizzoMittente) {
		this.indirizzoMittente = indirizzoMittente;
	}
	public String getCittaMittente() {
		return cittaMittente;
	}
	public void setCittaMittente(String cittaMittente) {
		this.cittaMittente = cittaMittente;
	}
	public String getComuneMittente() {
		return comuneMittente;
	}
	public void setComuneMittente(String comuneMittente) {
		this.comuneMittente = comuneMittente;
	}
	public String getProvinciaMittente() {
		return provinciaMittente;
	}
	public void setProvinciaMittente(String provinciaMittente) {
		this.provinciaMittente = provinciaMittente;
	}
	public String getCapMittente() {
		return capMittente;
	}
	public void setCapMittente(String capMittente) {
		this.capMittente = capMittente;
	}
	public String getRegioneMittente() {
		return regioneMittente;
	}
	public void setRegioneMittente(String regioneMittente) {
		this.regioneMittente = regioneMittente;
	}
	public String getDescrizioneMittente() {
		return descrizioneMittente;
	}
	public void setDescrizioneMittente(String descrizioneMittente) {
		this.descrizioneMittente = descrizioneMittente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public String getCodFisc() {
		return codFisc;
	}
	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
	}
	public String getCodiceDestinatario() {
		return codiceDestinatario;
	}
	public void setCodiceDestinatario(String codiceDestinatario) {
		this.codiceDestinatario = codiceDestinatario;
	}
	public String getOggettoProtocollo() {
		return oggettoProtocollo;
	}
	public void setOggettoProtocollo(String oggettoProtocollo) {
		this.oggettoProtocollo = oggettoProtocollo;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getTipoAllegati() {
		return tipoAllegati;
	}
	public void setTipoAllegati(String tipoAllegati) {
		this.tipoAllegati = tipoAllegati;
	}
	public String getMezzoSpedizione() {
		return mezzoSpedizione;
	}
	public void setMezzoSpedizione(String mezzoSpedizione) {
		this.mezzoSpedizione = mezzoSpedizione;
	}
	public String getDataArrivo() {
		return dataArrivo;
	}
	public void setDataArrivo(String dataArrivo) {
		this.dataArrivo = dataArrivo;
	}
	public String getDataDocumento() {
		return dataDocumento;
	}
	public void setDataDocumento(String dataDocumento) {
		this.dataDocumento = dataDocumento;
	}
	public int getNumeroAllegati() {
		return numeroAllegati;
	}
	public void setNumeroAllegati(int numeroAllegati) {
		this.numeroAllegati = numeroAllegati;
	}
		
	
	
	
	
}
