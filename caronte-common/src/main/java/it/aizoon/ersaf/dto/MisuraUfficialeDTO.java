package it.aizoon.ersaf.dto;


import java.util.List;

import it.aizoon.ersaf.dto.generati.CarRMisuraIspettore;
import it.aizoon.ersaf.dto.generati.CarRMisuraOrgNoc;
import it.aizoon.ersaf.dto.generati.CarRMisuraTipologia;
import it.aizoon.ersaf.dto.generati.CarTMisuraUfficiale;



@SuppressWarnings("serial")
public class MisuraUfficialeDTO {

/*	Tabelle :
 * 
 *  CAR_T_MISURA_UFFICIALE
	CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'D' (Ispettore/i che ha/hanno emesso la misura - Disposizione)
	CAR_R_MISURA_TIPOLOGIA (Misure applicate)
	CAR_R_MISURA_ORG_NOC
	CAR_R_MISURA_ISPETTORE CON TIPO_MISURA = 'C' (Ispettore/i che ha/hanno constatato l'esito della misura - Constatazione)
*/
	CarTMisuraUfficiale misuraUfficiale;
	List<CarRMisuraIspettore> ispettoriDisposizione;
	List<CarRMisuraTipologia> misureApplicate;
	List<CarRMisuraOrgNoc> organismiNociviMisuraUfficiale;
	List<CarRMisuraIspettore> ispettoriConstatazione;
	
	public List<CarRMisuraIspettore> getIspettoriDisposizione() {
		return ispettoriDisposizione;
	}
	public void setIspettoriDisposizione(List<CarRMisuraIspettore> ispettoriDisposizione) {
		this.ispettoriDisposizione = ispettoriDisposizione;
	}
	public List<CarRMisuraTipologia> getMisureApplicate() {
		return misureApplicate;
	}
	public void setMisureApplicate(List<CarRMisuraTipologia> misureApplicate) {
		this.misureApplicate = misureApplicate;
	}
	public List<CarRMisuraOrgNoc> getOrganismiNociviMisuraUfficiale() {
		return organismiNociviMisuraUfficiale;
	}
	public void setOrganismiNociviMisuraUfficiale(List<CarRMisuraOrgNoc> organismiNociviMisuraUfficiale) {
		this.organismiNociviMisuraUfficiale = organismiNociviMisuraUfficiale;
	}
	public List<CarRMisuraIspettore> getIspettoriConstatazione() {
		return ispettoriConstatazione;
	}
	public void setIspettoriConstatazione(List<CarRMisuraIspettore> ispettoriConstatazione) {
		this.ispettoriConstatazione = ispettoriConstatazione;
	}
	public CarTMisuraUfficiale getMisuraUfficiale() {
		return misuraUfficiale;
	}
	public void setMisuraUfficilae(CarTMisuraUfficiale misuraUfficiale) {
		this.misuraUfficiale = misuraUfficiale;
	}


}
