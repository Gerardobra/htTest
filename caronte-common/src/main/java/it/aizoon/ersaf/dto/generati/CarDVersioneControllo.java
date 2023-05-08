package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.BaseDto;
import java.util.Date;

public class CarDVersioneControllo extends BaseDto {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_d_versione_controllo.id_versione_controllo
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    private Long idVersioneControllo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_d_versione_controllo.data_inizio_vesione
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    private Date dataInizioVesione;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_d_versione_controllo.data_fine_verisione
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    private Date dataFineVerisione;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column caronte.car_d_versione_controllo.note
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    private String note;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_d_versione_controllo.id_versione_controllo
     *
     * @return the value of caronte.car_d_versione_controllo.id_versione_controllo
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    public Long getIdVersioneControllo() {
        return idVersioneControllo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_d_versione_controllo.id_versione_controllo
     *
     * @param idVersioneControllo the value for caronte.car_d_versione_controllo.id_versione_controllo
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    public void setIdVersioneControllo(Long idVersioneControllo) {
        this.idVersioneControllo = idVersioneControllo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_d_versione_controllo.data_inizio_vesione
     *
     * @return the value of caronte.car_d_versione_controllo.data_inizio_vesione
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    public Date getDataInizioVesione() {
        return dataInizioVesione;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_d_versione_controllo.data_inizio_vesione
     *
     * @param dataInizioVesione the value for caronte.car_d_versione_controllo.data_inizio_vesione
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    public void setDataInizioVesione(Date dataInizioVesione) {
        this.dataInizioVesione = dataInizioVesione;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_d_versione_controllo.data_fine_verisione
     *
     * @return the value of caronte.car_d_versione_controllo.data_fine_verisione
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    public Date getDataFineVerisione() {
        return dataFineVerisione;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_d_versione_controllo.data_fine_verisione
     *
     * @param dataFineVerisione the value for caronte.car_d_versione_controllo.data_fine_verisione
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    public void setDataFineVerisione(Date dataFineVerisione) {
        this.dataFineVerisione = dataFineVerisione;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column caronte.car_d_versione_controllo.note
     *
     * @return the value of caronte.car_d_versione_controllo.note
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column caronte.car_d_versione_controllo.note
     *
     * @param note the value for caronte.car_d_versione_controllo.note
     *
     * @mbg.generated Mon Apr 12 09:32:57 CEST 2021
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}