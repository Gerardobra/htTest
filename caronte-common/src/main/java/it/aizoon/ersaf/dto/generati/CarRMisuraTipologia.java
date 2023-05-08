package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;
import java.util.Date;

public class CarRMisuraTipologia extends BaseDto {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_r_misura_tipologia.id_misura_ufficiale
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    private Long idMisuraUfficiale;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_r_misura_tipologia.id_tipologia_misura
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    private Long idTipologiaMisura;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_r_misura_tipologia.note_tipologia
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    private String noteTipologia;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_r_misura_tipologia.id_utente_inserimento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    private Long idUtenteInserimento;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_r_misura_tipologia.data_inserimento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    private Date dataInserimento;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_r_misura_tipologia.id_utente_aggiornamento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    private Long idUtenteAggiornamento;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_r_misura_tipologia.data_aggiornamento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    private Date dataAggiornamento;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_r_misura_tipologia.id_misura_ufficiale
     *
     * @return the value of caronte.car_r_misura_tipologia.id_misura_ufficiale
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public Long getIdMisuraUfficiale() {
        return idMisuraUfficiale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_r_misura_tipologia.id_misura_ufficiale
     *
     * @param idMisuraUfficiale the value for caronte.car_r_misura_tipologia.id_misura_ufficiale
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void setIdMisuraUfficiale(Long idMisuraUfficiale) {
        this.idMisuraUfficiale = idMisuraUfficiale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_r_misura_tipologia.id_tipologia_misura
     *
     * @return the value of caronte.car_r_misura_tipologia.id_tipologia_misura
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public Long getIdTipologiaMisura() {
        return idTipologiaMisura;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_r_misura_tipologia.id_tipologia_misura
     *
     * @param idTipologiaMisura the value for caronte.car_r_misura_tipologia.id_tipologia_misura
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void setIdTipologiaMisura(Long idTipologiaMisura) {
        this.idTipologiaMisura = idTipologiaMisura;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_r_misura_tipologia.note_tipologia
     *
     * @return the value of caronte.car_r_misura_tipologia.note_tipologia
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public String getNoteTipologia() {
        return noteTipologia;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_r_misura_tipologia.note_tipologia
     *
     * @param noteTipologia the value for caronte.car_r_misura_tipologia.note_tipologia
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void setNoteTipologia(String noteTipologia) {
        this.noteTipologia = noteTipologia == null ? null : noteTipologia.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_r_misura_tipologia.id_utente_inserimento
     *
     * @return the value of caronte.car_r_misura_tipologia.id_utente_inserimento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public Long getIdUtenteInserimento() {
        return idUtenteInserimento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_r_misura_tipologia.id_utente_inserimento
     *
     * @param idUtenteInserimento the value for caronte.car_r_misura_tipologia.id_utente_inserimento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void setIdUtenteInserimento(Long idUtenteInserimento) {
        this.idUtenteInserimento = idUtenteInserimento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_r_misura_tipologia.data_inserimento
     *
     * @return the value of caronte.car_r_misura_tipologia.data_inserimento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public Date getDataInserimento() {
        return dataInserimento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_r_misura_tipologia.data_inserimento
     *
     * @param dataInserimento the value for caronte.car_r_misura_tipologia.data_inserimento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_r_misura_tipologia.id_utente_aggiornamento
     *
     * @return the value of caronte.car_r_misura_tipologia.id_utente_aggiornamento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public Long getIdUtenteAggiornamento() {
        return idUtenteAggiornamento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_r_misura_tipologia.id_utente_aggiornamento
     *
     * @param idUtenteAggiornamento the value for caronte.car_r_misura_tipologia.id_utente_aggiornamento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void setIdUtenteAggiornamento(Long idUtenteAggiornamento) {
        this.idUtenteAggiornamento = idUtenteAggiornamento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_r_misura_tipologia.data_aggiornamento
     *
     * @return the value of caronte.car_r_misura_tipologia.data_aggiornamento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public Date getDataAggiornamento() {
        return dataAggiornamento;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_r_misura_tipologia.data_aggiornamento
     *
     * @param dataAggiornamento the value for caronte.car_r_misura_tipologia.data_aggiornamento
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void setDataAggiornamento(Date dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }
}