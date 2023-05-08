package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.GenericExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarRControlloFisicoReqProfExample extends GenericExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public CarRControlloFisicoReqProfExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdControlloFisicoIsNull() {
            addCriterion("id_controllo_fisico is null");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoIsNotNull() {
            addCriterion("id_controllo_fisico is not null");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoEqualTo(Long value) {
            addCriterion("id_controllo_fisico =", value, "idControlloFisico");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoNotEqualTo(Long value) {
            addCriterion("id_controllo_fisico <>", value, "idControlloFisico");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoGreaterThan(Long value) {
            addCriterion("id_controllo_fisico >", value, "idControlloFisico");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoGreaterThanOrEqualTo(Long value) {
            addCriterion("id_controllo_fisico >=", value, "idControlloFisico");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoLessThan(Long value) {
            addCriterion("id_controllo_fisico <", value, "idControlloFisico");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoLessThanOrEqualTo(Long value) {
            addCriterion("id_controllo_fisico <=", value, "idControlloFisico");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoIn(List<Long> values) {
            addCriterion("id_controllo_fisico in", values, "idControlloFisico");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoNotIn(List<Long> values) {
            addCriterion("id_controllo_fisico not in", values, "idControlloFisico");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoBetween(Long value1, Long value2) {
            addCriterion("id_controllo_fisico between", value1, value2, "idControlloFisico");
            return (Criteria) this;
        }

        public Criteria andIdControlloFisicoNotBetween(Long value1, Long value2) {
            addCriterion("id_controllo_fisico not between", value1, value2, "idControlloFisico");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleIsNull() {
            addCriterion("id_requisito_professionale is null");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleIsNotNull() {
            addCriterion("id_requisito_professionale is not null");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleEqualTo(Long value) {
            addCriterion("id_requisito_professionale =", value, "idRequisitoProfessionale");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleNotEqualTo(Long value) {
            addCriterion("id_requisito_professionale <>", value, "idRequisitoProfessionale");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleGreaterThan(Long value) {
            addCriterion("id_requisito_professionale >", value, "idRequisitoProfessionale");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleGreaterThanOrEqualTo(Long value) {
            addCriterion("id_requisito_professionale >=", value, "idRequisitoProfessionale");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleLessThan(Long value) {
            addCriterion("id_requisito_professionale <", value, "idRequisitoProfessionale");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleLessThanOrEqualTo(Long value) {
            addCriterion("id_requisito_professionale <=", value, "idRequisitoProfessionale");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleIn(List<Long> values) {
            addCriterion("id_requisito_professionale in", values, "idRequisitoProfessionale");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleNotIn(List<Long> values) {
            addCriterion("id_requisito_professionale not in", values, "idRequisitoProfessionale");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleBetween(Long value1, Long value2) {
            addCriterion("id_requisito_professionale between", value1, value2, "idRequisitoProfessionale");
            return (Criteria) this;
        }

        public Criteria andIdRequisitoProfessionaleNotBetween(Long value1, Long value2) {
            addCriterion("id_requisito_professionale not between", value1, value2, "idRequisitoProfessionale");
            return (Criteria) this;
        }

        public Criteria andDescLaureaIsNull() {
            addCriterion("desc_laurea is null");
            return (Criteria) this;
        }

        public Criteria andDescLaureaIsNotNull() {
            addCriterion("desc_laurea is not null");
            return (Criteria) this;
        }

        public Criteria andDescLaureaEqualTo(String value) {
            addCriterion("desc_laurea =", value, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaNotEqualTo(String value) {
            addCriterion("desc_laurea <>", value, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaGreaterThan(String value) {
            addCriterion("desc_laurea >", value, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaGreaterThanOrEqualTo(String value) {
            addCriterion("desc_laurea >=", value, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaLessThan(String value) {
            addCriterion("desc_laurea <", value, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaLessThanOrEqualTo(String value) {
            addCriterion("desc_laurea <=", value, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaLike(String value) {
            addCriterion("desc_laurea like", value, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaNotLike(String value) {
            addCriterion("desc_laurea not like", value, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaIn(List<String> values) {
            addCriterion("desc_laurea in", values, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaNotIn(List<String> values) {
            addCriterion("desc_laurea not in", values, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaBetween(String value1, String value2) {
            addCriterion("desc_laurea between", value1, value2, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescLaureaNotBetween(String value1, String value2) {
            addCriterion("desc_laurea not between", value1, value2, "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaIsNull() {
            addCriterion("desc_diploma is null");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaIsNotNull() {
            addCriterion("desc_diploma is not null");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaEqualTo(String value) {
            addCriterion("desc_diploma =", value, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaNotEqualTo(String value) {
            addCriterion("desc_diploma <>", value, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaGreaterThan(String value) {
            addCriterion("desc_diploma >", value, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaGreaterThanOrEqualTo(String value) {
            addCriterion("desc_diploma >=", value, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaLessThan(String value) {
            addCriterion("desc_diploma <", value, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaLessThanOrEqualTo(String value) {
            addCriterion("desc_diploma <=", value, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaLike(String value) {
            addCriterion("desc_diploma like", value, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaNotLike(String value) {
            addCriterion("desc_diploma not like", value, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaIn(List<String> values) {
            addCriterion("desc_diploma in", values, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaNotIn(List<String> values) {
            addCriterion("desc_diploma not in", values, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaBetween(String value1, String value2) {
            addCriterion("desc_diploma between", value1, value2, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaNotBetween(String value1, String value2) {
            addCriterion("desc_diploma not between", value1, value2, "descDiploma");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoIsNull() {
            addCriterion("id_utente_inserimento is null");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoIsNotNull() {
            addCriterion("id_utente_inserimento is not null");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoEqualTo(Long value) {
            addCriterion("id_utente_inserimento =", value, "idUtenteInserimento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoNotEqualTo(Long value) {
            addCriterion("id_utente_inserimento <>", value, "idUtenteInserimento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoGreaterThan(Long value) {
            addCriterion("id_utente_inserimento >", value, "idUtenteInserimento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoGreaterThanOrEqualTo(Long value) {
            addCriterion("id_utente_inserimento >=", value, "idUtenteInserimento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoLessThan(Long value) {
            addCriterion("id_utente_inserimento <", value, "idUtenteInserimento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoLessThanOrEqualTo(Long value) {
            addCriterion("id_utente_inserimento <=", value, "idUtenteInserimento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoIn(List<Long> values) {
            addCriterion("id_utente_inserimento in", values, "idUtenteInserimento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoNotIn(List<Long> values) {
            addCriterion("id_utente_inserimento not in", values, "idUtenteInserimento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoBetween(Long value1, Long value2) {
            addCriterion("id_utente_inserimento between", value1, value2, "idUtenteInserimento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteInserimentoNotBetween(Long value1, Long value2) {
            addCriterion("id_utente_inserimento not between", value1, value2, "idUtenteInserimento");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoIsNull() {
            addCriterion("data_inserimento is null");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoIsNotNull() {
            addCriterion("data_inserimento is not null");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoEqualTo(Date value) {
            addCriterion("data_inserimento =", value, "dataInserimento");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoNotEqualTo(Date value) {
            addCriterion("data_inserimento <>", value, "dataInserimento");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoGreaterThan(Date value) {
            addCriterion("data_inserimento >", value, "dataInserimento");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoGreaterThanOrEqualTo(Date value) {
            addCriterion("data_inserimento >=", value, "dataInserimento");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoLessThan(Date value) {
            addCriterion("data_inserimento <", value, "dataInserimento");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoLessThanOrEqualTo(Date value) {
            addCriterion("data_inserimento <=", value, "dataInserimento");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoIn(List<Date> values) {
            addCriterion("data_inserimento in", values, "dataInserimento");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoNotIn(List<Date> values) {
            addCriterion("data_inserimento not in", values, "dataInserimento");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoBetween(Date value1, Date value2) {
            addCriterion("data_inserimento between", value1, value2, "dataInserimento");
            return (Criteria) this;
        }

        public Criteria andDataInserimentoNotBetween(Date value1, Date value2) {
            addCriterion("data_inserimento not between", value1, value2, "dataInserimento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoIsNull() {
            addCriterion("id_utente_aggiornamento is null");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoIsNotNull() {
            addCriterion("id_utente_aggiornamento is not null");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoEqualTo(Long value) {
            addCriterion("id_utente_aggiornamento =", value, "idUtenteAggiornamento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoNotEqualTo(Long value) {
            addCriterion("id_utente_aggiornamento <>", value, "idUtenteAggiornamento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoGreaterThan(Long value) {
            addCriterion("id_utente_aggiornamento >", value, "idUtenteAggiornamento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoGreaterThanOrEqualTo(Long value) {
            addCriterion("id_utente_aggiornamento >=", value, "idUtenteAggiornamento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoLessThan(Long value) {
            addCriterion("id_utente_aggiornamento <", value, "idUtenteAggiornamento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoLessThanOrEqualTo(Long value) {
            addCriterion("id_utente_aggiornamento <=", value, "idUtenteAggiornamento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoIn(List<Long> values) {
            addCriterion("id_utente_aggiornamento in", values, "idUtenteAggiornamento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoNotIn(List<Long> values) {
            addCriterion("id_utente_aggiornamento not in", values, "idUtenteAggiornamento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoBetween(Long value1, Long value2) {
            addCriterion("id_utente_aggiornamento between", value1, value2, "idUtenteAggiornamento");
            return (Criteria) this;
        }

        public Criteria andIdUtenteAggiornamentoNotBetween(Long value1, Long value2) {
            addCriterion("id_utente_aggiornamento not between", value1, value2, "idUtenteAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoIsNull() {
            addCriterion("data_aggiornamento is null");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoIsNotNull() {
            addCriterion("data_aggiornamento is not null");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoEqualTo(Date value) {
            addCriterion("data_aggiornamento =", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoNotEqualTo(Date value) {
            addCriterion("data_aggiornamento <>", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoGreaterThan(Date value) {
            addCriterion("data_aggiornamento >", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoGreaterThanOrEqualTo(Date value) {
            addCriterion("data_aggiornamento >=", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoLessThan(Date value) {
            addCriterion("data_aggiornamento <", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoLessThanOrEqualTo(Date value) {
            addCriterion("data_aggiornamento <=", value, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoIn(List<Date> values) {
            addCriterion("data_aggiornamento in", values, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoNotIn(List<Date> values) {
            addCriterion("data_aggiornamento not in", values, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoBetween(Date value1, Date value2) {
            addCriterion("data_aggiornamento between", value1, value2, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDataAggiornamentoNotBetween(Date value1, Date value2) {
            addCriterion("data_aggiornamento not between", value1, value2, "dataAggiornamento");
            return (Criteria) this;
        }

        public Criteria andDescLaureaLikeInsensitive(String value) {
            addCriterion("upper(desc_laurea) like", value.toUpperCase(), "descLaurea");
            return (Criteria) this;
        }

        public Criteria andDescDiplomaLikeInsensitive(String value) {
            addCriterion("upper(desc_diploma) like", value.toUpperCase(), "descDiploma");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated do_not_delete_during_merge Wed Dec 23 08:31:16 CET 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table caronte.car_r_controllo_fisico_req_prof
     *
     * @mbg.generated Wed Dec 23 08:31:16 CET 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}