package it.aizoon.ersaf.dto;

/**
 * @author alessandro.morra
 */
public class RiexportDatiSianDTO extends BaseDto {

	private static final long serialVersionUID = 1089538039475728101L;

	private long idRichiesta;
	private String numero;
	private String paeseRiesp;
	private String paeseOrigine;
	private String numCertOrigine;

	public long getIdRichiesta() {
		return idRichiesta;
	}

	public void setIdRichiesta(long idRichiesta) {
		this.idRichiesta = idRichiesta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPaeseRiesp() {
		return paeseRiesp;
	}

	public void setPaeseRiesp(String paeseRiesp) {
		this.paeseRiesp = paeseRiesp;
	}

	public String getPaeseOrigine() {
		return paeseOrigine;
	}

	public void setPaeseOrigine(String paeseOrigine) {
		this.paeseOrigine = paeseOrigine;
	}

	public String getNumCertOrigine() {
		return numCertOrigine;
	}

	public void setNumCertOrigine(String numCertOrigine) {
		this.numCertOrigine = numCertOrigine;
	}

}
