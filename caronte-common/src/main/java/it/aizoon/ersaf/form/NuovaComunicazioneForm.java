package it.aizoon.ersaf.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import it.aizoon.ersaf.dto.ComunicazioneDto;
import it.aizoon.ersaf.dto.GenereDTO;
import it.aizoon.ersaf.dto.GenereSpecieDTO;
import it.aizoon.ersaf.dto.SpecieDTO;
import it.aizoon.ersaf.util.CaronteUtils;

/**
 * @author Ivan Morra
 *
 */
public class NuovaComunicazioneForm extends BaseForm {

	private Long idComunicazione;
	private Long idTipoComunicazione;
	private Long idStatoComunicazione;

	@NumberFormat(style = Style.NUMBER)
	private Long idSpedizioniere;
	@NumberFormat(style = Style.NUMBER)
	private Long idCentroAziendale;
	private String[] idSpecie;
	private String[] numeroPiante;

	private List<SpecieDTO> specieList;
	
	private List<GenereSpecieDTO> listaGeneri;
	private String[] denomGenere;
	
	//FLAG ABILITAZIONE INOLTRA
	private boolean abilitaInoltra;
	private boolean abilitaAnnulla;
	private boolean abilitaRespingi;
	private boolean abilitaAutorizza;
	private String spedizioniereRuop;

	public List<SpecieDTO> getSpecieList() {
		return specieList;
	}

	public void setSpecieList(List<SpecieDTO> specieList) {
		this.specieList = specieList;
	}

	public String[] getNumeroPiante() {
		return numeroPiante;
	}

	public void setNumeroPiante(String[] numeroPiante) {
		this.numeroPiante = numeroPiante;
	}
	

	public Long getIdComunicazione() {
		return idComunicazione;
	}

	public void setIdComunicazione(Long idComunicazione) {
		this.idComunicazione = idComunicazione;
	}

	public Long getIdTipoComunicazione() {
		return idTipoComunicazione;
	}

	public void setIdTipoComunicazione(Long idTipoComunicazione) {
		this.idTipoComunicazione = idTipoComunicazione;
	}

	public Long getIdStatoComunicazione() {
		return idStatoComunicazione;
	}

	public void setIdStatoComunicazione(Long idStatoComunicazione) {
		this.idStatoComunicazione = idStatoComunicazione;
	}

	public Long getIdSpedizioniere() {
		return idSpedizioniere;
	}

	public void setIdSpedizioniere(Long idSpedizioniere) {
		this.idSpedizioniere = idSpedizioniere;
	}

	public Long getIdCentroAziendale() {
		return idCentroAziendale;
	}

	public void setIdCentroAziendale(Long idCentroAziendale) {
		this.idCentroAziendale = idCentroAziendale;
	}

	public String[] getIdSpecie() {
		return idSpecie;
	}

	public void setIdSpecie(String[] idSpecie) {
		this.idSpecie = idSpecie;
	}

	public List<GenereSpecieDTO> getListaGeneri() {
		return listaGeneri;
	}

	public void setListaGeneri(List<GenereSpecieDTO> listaGeneri) {
		this.listaGeneri = listaGeneri;
	}

	public boolean isAbilitaInoltra() {
		return abilitaInoltra;
	}

	public void setAbilitaInoltra(boolean abilitaInoltra) {
		this.abilitaInoltra = abilitaInoltra;
	}

	public List<String> convertiIdSpecie() {
		Set<String> setIdSpecie = new HashSet<>();

		if (idSpecie != null) {
			for (String elementoIdSpecie : idSpecie) {
				String[] idSpecieArray = elementoIdSpecie.split(",");
				setIdSpecie.addAll(Arrays.asList(idSpecieArray));
			}
		}

		return new ArrayList<>(setIdSpecie);
	}

	public void setDatiComunicazione(ComunicazioneDto comunicazione) {
		if (comunicazione != null) {
			setIdComunicazione(comunicazione.getIdComunicazione());
			setIdTipoComunicazione(comunicazione.getIdTipoComunicazione());
			setIdStatoComunicazione(comunicazione.getIdStatoComunicazione());
			setIdSpedizioniere(comunicazione.getIdSpedizioniere());
			setIdCentroAziendale(comunicazione.getCentroAziendale().getIdCentroAziendale());
			setListaGeneri(comunicazione.getListaGeneri());
		}
	}


	public boolean isAbilitaAnnulla() {
		return abilitaAnnulla;
	}

	public void setAbilitaAnnulla(boolean abilitaAnnulla) {
		this.abilitaAnnulla = abilitaAnnulla;
	}

	public boolean isAbilitaRespingi() {
		return abilitaRespingi;
	}

	public void setAbilitaRespingi(boolean abilitaRespingi) {
		this.abilitaRespingi = abilitaRespingi;
	}

	public boolean isAbilitaAutorizza() {
		return abilitaAutorizza;
	}

	public void setAbilitaAutorizza(boolean abilitaAutorizza) {
		this.abilitaAutorizza = abilitaAutorizza;
	}

	public String getSpedizioniereRuop() {
		return spedizioniereRuop;
	}

	public void setSpedizioniereRuop(String spedizioniereRuop) {
		this.spedizioniereRuop = CaronteUtils.convertToUpperCase(spedizioniereRuop);
	}

	public String[] getDenomGenere() {
		return denomGenere;
	}

	public void setDenomGenere(String[] denomGenere) {
		this.denomGenere = denomGenere;
	}

}
