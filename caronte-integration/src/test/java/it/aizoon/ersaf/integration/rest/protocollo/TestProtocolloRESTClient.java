package it.aizoon.ersaf.integration.rest.protocollo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.aizoon.ersaf.exception.InternalException;
import it.aizoon.ersaf.integration.rest.protocollo.dto.DocumentoOutputDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.InputJsonDocumentoDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.InputJsonProtocolloDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.ProtocolloOutputDto;
import it.aizoon.ersaf.integration.rest.protocollo.dto.TokenOutputDto;
import junit.framework.TestCase;

public class TestProtocolloRESTClient extends TestCase {
		

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	// TEST OK
	/* @Test
	  public final void testGetToken() {
		TokenOutputDto tokenOutput = null;
		try {
			System.out.println("- chiamata a getToken");
			tokenOutput = ProtocolloServiceFactory.getRestServiceClient().getToken();
			if(tokenOutput != null){
				System.out.println("-- Access_token ="+tokenOutput.getAccess_token());
				System.out.println("-- Expires_in ="+tokenOutput.getExpires_in());
				System.out.println("-- Scope ="+tokenOutput.getScope());
				System.out.println("-- Token_type ="+tokenOutput.getToken_type());					
			}
		} 
		catch (InternalException e) {		
			e.printStackTrace();
		}
	}*/
	
	/*@Test
	public final void testCreaDocumentoDaProtocollare(){
		try {
			TokenOutputDto tokenOutput = ProtocolloServiceFactory.getRestServiceClient().getToken();
			DocumentoOutputDto documento =  ProtocolloServiceFactory.getRestServiceClient().creaDocumentoDaProtocollare(tokenOutput.getAccess_token());
			if(documento != null){
				System.out.println("-- id ="+documento.getId());
				System.out.println("-- Filename ="+documento.getFileName());				
				System.out.println("-- Nome file ="+documento.getNomeFile());				
			}
			
		}
		catch (InternalException e) {		
			e.printStackTrace();
		}
	}*/
	
	@Test
	public final void testCreaProtocolloDelDocumento(){
		try {
			TokenOutputDto tokenOutput = ProtocolloServiceFactory.getRestServiceClient().getToken();
			
			InputJsonDocumentoDto inputJsonDoc = new InputJsonDocumentoDto();
			inputJsonDoc.setNomeFile("NuovaRichiesta.pdf");
			inputJsonDoc.setMetadocumento("ALLEGATO_CARONTE");
			inputJsonDoc.setMetadocumentoAllegato(null);
			inputJsonDoc.setOggetto("OGGETTO_MM");
			inputJsonDoc.setProtocollo(null);
			inputJsonDoc.setDataProtocollo(null);
			inputJsonDoc.setId(null);
			inputJsonDoc.setFileName("NuovaRichiesta.pdf");
			inputJsonDoc.setObject("OBJECT MM");	
			
			byte [] fileDaProtocollare = null;// provvisorio			
			String nomeFile = null;
			DocumentoOutputDto documento =  ProtocolloServiceFactory.getRestServiceClient().creaDocumentoDaProtocollare(tokenOutput.getAccess_token(),inputJsonDoc, fileDaProtocollare, nomeFile);
			
			InputJsonProtocolloDto inputJsonProt = new InputJsonProtocolloDto();
			inputJsonProt.setIndirizzoMittente("indirizzoMittente");
			inputJsonProt.setCittaMittente("cittaMittente");
			inputJsonProt.setComuneMittente("comuneMittente");
			inputJsonProt.setProvinciaMittente("provinciaMittente");
			inputJsonProt.setCapMittente("capMittente");
			inputJsonProt.setRegioneMittente("regioneMittente");
			inputJsonProt.setDescrizioneMittente("descrizioneMittente");
			inputJsonProt.setEmail("email@email.it");
			inputJsonProt.setFax("fax");
			inputJsonProt.setTelefono("telefono");
			inputJsonProt.setPartitaIva("partitaIva");
			inputJsonProt.setCodFisc("cficfh76t54r567u");
			inputJsonProt.setCodiceDestinatario("SYSTEM_CARONTE");
			inputJsonProt.setOggettoProtocollo("oggettoProtocollo");			
			inputJsonProt.setTipoDocumento("Verbale");
			//inputJsonProt.setRegioneMittente("regioneMittente"); ?? non ha dato errori senza questo campo
			inputJsonProt.setTipoAllegati("Allegati di test");
			inputJsonProt.setMezzoSpedizione("Raccomandata");
			inputJsonProt.setDataArrivo("2021-04-17");
			inputJsonProt.setDataDocumento("2021-04-17");
			inputJsonProt.setNumeroAllegati(0);				
			
			ProtocolloOutputDto protocolloOutputDto = ProtocolloServiceFactory.getRestServiceClient().protocollaDocumentoByIdDocumento(tokenOutput.getAccess_token(), ""+documento.getId(), inputJsonProt);			
			if(protocolloOutputDto != null){
				System.out.println("-- protocollo ="+protocolloOutputDto.getProtocollo());			
				System.out.println("-- stringDataProtocollo( ="+protocolloOutputDto.getStringDataProtocollo());
			}
		}
		catch (InternalException e) {		
			e.printStackTrace();
		}
	}

}
