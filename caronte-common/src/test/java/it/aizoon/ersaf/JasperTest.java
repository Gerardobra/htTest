package it.aizoon.ersaf;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.aizoon.ersaf.dto.stampe.NullaOstaDTO;
import junit.framework.TestCase;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class JasperTest extends TestCase {

	protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".test");
	
	public void setUp() {

	}

	public void tearDown() {

	}
	
	@Test
	public void testStampaNullaOsta() {
		
		NullaOstaDTO nullaOsta = new NullaOstaDTO();
		nullaOsta.setColli("pippo");
		nullaOsta.setDogana("pluto");
		
		
		try {
			JasperReport jasperReport = caricaTemplate();
			ByteArrayOutputStream byteArrayOutputStream = saveToPdf(jasperReport, nullaOsta, null);
			String filename = "C:\\Users\\federico.farinetto\\workspace8\\caronte\\caronte-common\\src\\main\\resources\\jasper\\generated\\test.pdf";
			FileOutputStream output = new FileOutputStream(filename);
			output.write(byteArrayOutputStream.toByteArray());
			output.close();
			
		} catch(Exception e) {
			
		}
	}


	private JasperReport caricaTemplate() throws JRException {

//		PcvDTemplateStampaSelector example = new PcvDTemplateStampaSelector();
//
//		example.createCriteria().andCodiceEqualTo(tipoTemplate);
//
//		List<PcvDTemplateStampa> template = this.getPcvDTemplateStampaMapper().selectByExample(example);

//		if (null == template || template.size() != 1) {
//			throw new JRException("Errore durante il recupero dei template di stampa. Record trovati "
//					+ (null != template ? template.size() : "0"));
//		}

		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(new File("C:\\Users\\federico.farinetto\\workspace8\\caronte\\caronte-common\\src\\main\\resources\\jasper\\sources\\allegatoB-Nullaosta.jasper"));

		return jasperReport;
	}



	private ByteArrayOutputStream saveToPdf(JasperReport jasperReport, NullaOstaDTO nullaOsta,
			HashMap<String, Object> additionalParameters) throws Exception {

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (additionalParameters != null) {
			for (Entry<String, Object> entry : additionalParameters.entrySet()) {
				parameters.put(entry.getKey(), entry.getValue());
			}
		}
		
		logger.info("nullaOsta: " + nullaOsta.toString());
		Collection<NullaOstaDTO> list = new ArrayList<NullaOstaDTO>();
		list.add(nullaOsta);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
				new JRBeanCollectionDataSource(list));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		
		return outputStream;

	}

	
}
