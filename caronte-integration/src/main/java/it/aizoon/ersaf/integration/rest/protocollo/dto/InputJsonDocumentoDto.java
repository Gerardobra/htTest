package it.aizoon.ersaf.integration.rest.protocollo.dto;

public class InputJsonDocumentoDto {
	
	/* 
	 * 
	 * { "nomeFile": "NuovaRichiesta.pdf",
	    "metadocumento": "ALLEGATO_CARONTE",
	    "metadocumentoAllegato": null,
	    "oggetto": "OGGETTO MM",
	    "protocollo": null,
	    "dataProtocollo": null,
	    "id": null,
	    "fileName": "NuovaRichiesta.pdf",
	    "object": "OBJECT MM"
	}
	*/
	
	public String nomeFile;
    public String metadocumento;
    public String metadocumentoAllegato;
    public String oggetto;
    public String protocollo;
    public String dataProtocollo;
    public String id;
    public String identificatore;
    public String fileName;
    public String object;
    
    
    
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	public String getMetadocumento() {
		return metadocumento;
	}
	public void setMetadocumento(String metadocumento) {
		this.metadocumento = metadocumento;
	}
	public String getMetadocumentoAllegato() {
		return metadocumentoAllegato;
	}
	public void setMetadocumentoAllegato(String metadocumentoAllegato) {
		this.metadocumentoAllegato = metadocumentoAllegato;
	}
	public String getOggetto() {
		return oggetto;
	}
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	public String getProtocollo() {
		return protocollo;
	}
	public void setProtocollo(String protocollo) {
		this.protocollo = protocollo;
	}
	public String getDataProtocollo() {
		return dataProtocollo;
	}
	public void setDataProtocollo(String dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdentificatore() {
		return identificatore;
	}
	public void setIdentificatore(String identificatore) {
		this.identificatore = identificatore;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
    
    
	
    
	
}
