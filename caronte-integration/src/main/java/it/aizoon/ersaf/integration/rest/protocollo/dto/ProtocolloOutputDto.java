package it.aizoon.ersaf.integration.rest.protocollo.dto;

public class ProtocolloOutputDto {
		
	private String nomeFile;
    private String metadocumento;
    private String metadocumentoAllegato = null;
    private String oggetto;
    private String protocollo;
    private float dataProtocollo;
    private String stringDataProtocollo;
    private float id;
    private String identificatore;
    private String object;
    private String fileName;
	
	
	public Fault fault;
    
    public class Fault {
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


   // Getter Methods 

    public String getNomeFile() {
      return nomeFile;
    }

    public String getMetadocumento() {
      return metadocumento;
    }

    public String getMetadocumentoAllegato() {
      return metadocumentoAllegato;
    }

    public String getOggetto() {
      return oggetto;
    }

    public String getProtocollo() {
      return protocollo;
    }

    public float getDataProtocollo() {
      return dataProtocollo;
    }

    public String getStringDataProtocollo() {
      return stringDataProtocollo;
    }

    public float getId() {
      return id;
    }

    public String getIdentificatore() {
      return identificatore;
    }

    public String getObject() {
      return object;
    }

    public String getFileName() {
      return fileName;
    }

   // Setter Methods 

    public void setNomeFile( String nomeFile ) {
      this.nomeFile = nomeFile;
    }

    public void setMetadocumento( String metadocumento ) {
      this.metadocumento = metadocumento;
    }

    public void setMetadocumentoAllegato( String metadocumentoAllegato ) {
      this.metadocumentoAllegato = metadocumentoAllegato;
    }

    public void setOggetto( String oggetto ) {
      this.oggetto = oggetto;
    }

    public void setProtocollo( String protocollo ) {
      this.protocollo = protocollo;
    }

    public void setDataProtocollo( float dataProtocollo ) {
      this.dataProtocollo = dataProtocollo;
    }

    public void setStringDataProtocollo( String stringDataProtocollo ) {
      this.stringDataProtocollo = stringDataProtocollo;
    }

    public void setId( float id ) {
      this.id = id;
    }

    public void setIdentificatore( String identificatore ) {
      this.identificatore = identificatore;
    }

    public void setObject( String object ) {
      this.object = object;
    }

    public void setFileName( String fileName ) {
      this.fileName = fileName;
    }
    
	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}

}
