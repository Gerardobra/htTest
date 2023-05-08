package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.GenericExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarRMisuraIspettoreExample extends GenericExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public CarRMisuraIspettoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
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
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
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

        public Criteria andIdMisuraUfficialeIsNull() {
            addCriterion("id_misura_ufficiale is null");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeIsNotNull() {
            addCriterion("id_misura_ufficiale is not null");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeEqualTo(Long value) {
            addCriterion("id_misura_ufficiale =", value, "idMisuraUfficiale");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeNotEqualTo(Long value) {
            addCriterion("id_misura_ufficiale <>", value, "idMisuraUfficiale");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeGreaterThan(Long value) {
            addCriterion("id_misura_ufficiale >", value, "idMisuraUfficiale");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeGreaterThanOrEqualTo(Long value) {
            addCriterion("id_misura_ufficiale >=", value, "idMisuraUfficiale");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeLessThan(Long value) {
            addCriterion("id_misura_ufficiale <", value, "idMisuraUfficiale");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeLessThanOrEqualTo(Long value) {
            addCriterion("id_misura_ufficiale <=", value, "idMisuraUfficiale");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeIn(List<Long> values) {
            addCriterion("id_misura_ufficiale in", values, "idMisuraUfficiale");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeNotIn(List<Long> values) {
            addCriterion("id_misura_ufficiale not in", values, "idMisuraUfficiale");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeBetween(Long value1, Long value2) {
            addCriterion("id_misura_ufficiale between", value1, value2, "idMisuraUfficiale");
            return (Criteria) this;
        }

        public Criteria andIdMisuraUfficialeNotBetween(Long value1, Long value2) {
            addCriterion("id_misura_ufficiale not between", value1, value2, "idMisuraUfficiale");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreIsNull() {
            addCriterion("id_ispettore is null");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreIsNotNull() {
            addCriterion("id_ispettore is not null");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreEqualTo(Long value) {
            addCriterion("id_ispettore =", value, "idIspettore");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreNotEqualTo(Long value) {
            addCriterion("id_ispettore <>", value, "idIspettore");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreGreaterThan(Long value) {
            addCriterion("id_ispettore >", value, "idIspettore");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreGreaterThanOrEqualTo(Long value) {
            addCriterion("id_ispettore >=", value, "idIspettore");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreLessThan(Long value) {
            addCriterion("id_ispettore <", value, "idIspettore");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreLessThanOrEqualTo(Long value) {
            addCriterion("id_ispettore <=", value, "idIspettore");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreIn(List<Long> values) {
            addCriterion("id_ispettore in", values, "idIspettore");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreNotIn(List<Long> values) {
            addCriterion("id_ispettore not in", values, "idIspettore");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreBetween(Long value1, Long value2) {
            addCriterion("id_ispettore between", value1, value2, "idIspettore");
            return (Criteria) this;
        }

        public Criteria andIdIspettoreNotBetween(Long value1, Long value2) {
            addCriterion("id_ispettore not between", value1, value2, "idIspettore");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraIsNull() {
            addCriterion("tipo_misura is null");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraIsNotNull() {
            addCriterion("tipo_misura is not null");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraEqualTo(String value) {
            addCriterion("tipo_misura =", value, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraNotEqualTo(String value) {
            addCriterion("tipo_misura <>", value, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraGreaterThan(String value) {
            addCriterion("tipo_misura >", value, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraGreaterThanOrEqualTo(String value) {
            addCriterion("tipo_misura >=", value, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraLessThan(String value) {
            addCriterion("tipo_misura <", value, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraLessThanOrEqualTo(String value) {
            addCriterion("tipo_misura <=", value, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraLike(String value) {
            addCriterion("tipo_misura like", value, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraNotLike(String value) {
            addCriterion("tipo_misura not like", value, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraIn(List<String> values) {
            addCriterion("tipo_misura in", values, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraNotIn(List<String> values) {
            addCriterion("tipo_misura not in", values, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraBetween(String value1, String value2) {
            addCriterion("tipo_misura between", value1, value2, "tipoMisura");
            return (Criteria) this;
        }

        public Criteria andTipoMisuraNotBetween(String value1, String value2) {
            addCriterion("tipo_misura not between", value1, value2, "tipoMisura");
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

        public Criteria andTipoMisuraLikeInsensitive(String value) {
            addCriterion("upper(tipo_misura) like", value.toUpperCase(), "tipoMisura");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated do_not_delete_during_merge Mon Feb 01 18:18:59 CET 2021
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table caronte.car_r_misura_ispettore
     *
     * @mbg.generated Mon Feb 01 18:18:59 CET 2021
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