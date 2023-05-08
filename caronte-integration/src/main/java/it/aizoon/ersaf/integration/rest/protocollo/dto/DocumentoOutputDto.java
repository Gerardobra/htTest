package it.aizoon.ersaf.integration.rest.protocollo.dto;

public class DocumentoOutputDto {
	public String nomeFile;
    public String metadocumento;
    public Object metadocumentoAllegato;
    public String oggetto;
    public Object protocollo;
    public Object dataProtocollo;
    public int id;
    public String identificatore;
    public String fileName;
    public String object;    
    
    public Fault fault;
       
    public class Fault{
        public int code;
        public String message;
        public String description;
        
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
        
        
    }  

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}  
    
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
	public Object getMetadocumentoAllegato() {
		return metadocumentoAllegato;
	}
	public void setMetadocumentoAllegato(Object metadocumentoAllegato) {
		this.metadocumentoAllegato = metadocumentoAllegato;
	}
	public String getOggetto() {
		return oggetto;
	}
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	public Object getProtocollo() {
		return protocollo;
	}
	public void setProtocollo(Object protocollo) {
		this.protocollo = protocollo;
	}
	public Object getDataProtocollo() {
		return dataProtocollo;
	}
	public void setDataProtocollo(Object dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
