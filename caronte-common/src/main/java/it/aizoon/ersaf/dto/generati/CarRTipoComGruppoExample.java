package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.GenericExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarRTipoComGruppoExample extends GenericExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    public CarRTipoComGruppoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
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
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
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

        public Criteria andIdTipoComunicazioneIsNull() {
            addCriterion("id_tipo_comunicazione is null");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneIsNotNull() {
            addCriterion("id_tipo_comunicazione is not null");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneEqualTo(Long value) {
            addCriterion("id_tipo_comunicazione =", value, "idTipoComunicazione");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneNotEqualTo(Long value) {
            addCriterion("id_tipo_comunicazione <>", value, "idTipoComunicazione");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneGreaterThan(Long value) {
            addCriterion("id_tipo_comunicazione >", value, "idTipoComunicazione");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneGreaterThanOrEqualTo(Long value) {
            addCriterion("id_tipo_comunicazione >=", value, "idTipoComunicazione");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneLessThan(Long value) {
            addCriterion("id_tipo_comunicazione <", value, "idTipoComunicazione");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneLessThanOrEqualTo(Long value) {
            addCriterion("id_tipo_comunicazione <=", value, "idTipoComunicazione");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneIn(List<Long> values) {
            addCriterion("id_tipo_comunicazione in", values, "idTipoComunicazione");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneNotIn(List<Long> values) {
            addCriterion("id_tipo_comunicazione not in", values, "idTipoComunicazione");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneBetween(Long value1, Long value2) {
            addCriterion("id_tipo_comunicazione between", value1, value2, "idTipoComunicazione");
            return (Criteria) this;
        }

        public Criteria andIdTipoComunicazioneNotBetween(Long value1, Long value2) {
            addCriterion("id_tipo_comunicazione not between", value1, value2, "idTipoComunicazione");
            return (Criteria) this;
        }

        public Criteria andIdGruppoIsNull() {
            addCriterion("id_gruppo is null");
            return (Criteria) this;
        }

        public Criteria andIdGruppoIsNotNull() {
            addCriterion("id_gruppo is not null");
            return (Criteria) this;
        }

        public Criteria andIdGruppoEqualTo(Long value) {
            addCriterion("id_gruppo =", value, "idGruppo");
            return (Criteria) this;
        }

        public Criteria andIdGruppoNotEqualTo(Long value) {
            addCriterion("id_gruppo <>", value, "idGruppo");
            return (Criteria) this;
        }

        public Criteria andIdGruppoGreaterThan(Long value) {
            addCriterion("id_gruppo >", value, "idGruppo");
            return (Criteria) this;
        }

        public Criteria andIdGruppoGreaterThanOrEqualTo(Long value) {
            addCriterion("id_gruppo >=", value, "idGruppo");
            return (Criteria) this;
        }

        public Criteria andIdGruppoLessThan(Long value) {
            addCriterion("id_gruppo <", value, "idGruppo");
            return (Criteria) this;
        }

        public Criteria andIdGruppoLessThanOrEqualTo(Long value) {
            addCriterion("id_gruppo <=", value, "idGruppo");
            return (Criteria) this;
        }

        public Criteria andIdGruppoIn(List<Long> values) {
            addCriterion("id_gruppo in", values, "idGruppo");
            return (Criteria) this;
        }

        public Criteria andIdGruppoNotIn(List<Long> values) {
            addCriterion("id_gruppo not in", values, "idGruppo");
            return (Criteria) this;
        }

        public Criteria andIdGruppoBetween(Long value1, Long value2) {
            addCriterion("id_gruppo between", value1, value2, "idGruppo");
            return (Criteria) this;
        }

        public Criteria andIdGruppoNotBetween(Long value1, Long value2) {
            addCriterion("id_gruppo not between", value1, value2, "idGruppo");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaIsNull() {
            addCriterion("inizio_validita is null");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaIsNotNull() {
            addCriterion("inizio_validita is not null");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaEqualTo(Date value) {
            addCriterion("inizio_validita =", value, "inizioValidita");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaNotEqualTo(Date value) {
            addCriterion("inizio_validita <>", value, "inizioValidita");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaGreaterThan(Date value) {
            addCriterion("inizio_validita >", value, "inizioValidita");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaGreaterThanOrEqualTo(Date value) {
            addCriterion("inizio_validita >=", value, "inizioValidita");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaLessThan(Date value) {
            addCriterion("inizio_validita <", value, "inizioValidita");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaLessThanOrEqualTo(Date value) {
            addCriterion("inizio_validita <=", value, "inizioValidita");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaIn(List<Date> values) {
            addCriterion("inizio_validita in", values, "inizioValidita");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaNotIn(List<Date> values) {
            addCriterion("inizio_validita not in", values, "inizioValidita");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaBetween(Date value1, Date value2) {
            addCriterion("inizio_validita between", value1, value2, "inizioValidita");
            return (Criteria) this;
        }

        public Criteria andInizioValiditaNotBetween(Date value1, Date value2) {
            addCriterion("inizio_validita not between", value1, value2, "inizioValidita");
            return (Criteria) this;
        }

        public Criteria andFineValiditaIsNull() {
            addCriterion("fine_validita is null");
            return (Criteria) this;
        }

        public Criteria andFineValiditaIsNotNull() {
            addCriterion("fine_validita is not null");
            return (Criteria) this;
        }

        public Criteria andFineValiditaEqualTo(Date value) {
            addCriterion("fine_validita =", value, "fineValidita");
            return (Criteria) this;
        }

        public Criteria andFineValiditaNotEqualTo(Date value) {
            addCriterion("fine_validita <>", value, "fineValidita");
            return (Criteria) this;
        }

        public Criteria andFineValiditaGreaterThan(Date value) {
            addCriterion("fine_validita >", value, "fineValidita");
            return (Criteria) this;
        }

        public Criteria andFineValiditaGreaterThanOrEqualTo(Date value) {
            addCriterion("fine_validita >=", value, "fineValidita");
            return (Criteria) this;
        }

        public Criteria andFineValiditaLessThan(Date value) {
            addCriterion("fine_validita <", value, "fineValidita");
            return (Criteria) this;
        }

        public Criteria andFineValiditaLessThanOrEqualTo(Date value) {
            addCriterion("fine_validita <=", value, "fineValidita");
            return (Criteria) this;
        }

        public Criteria andFineValiditaIn(List<Date> values) {
            addCriterion("fine_validita in", values, "fineValidita");
            return (Criteria) this;
        }

        public Criteria andFineValiditaNotIn(List<Date> values) {
            addCriterion("fine_validita not in", values, "fineValidita");
            return (Criteria) this;
        }

        public Criteria andFineValiditaBetween(Date value1, Date value2) {
            addCriterion("fine_validita between", value1, value2, "fineValidita");
            return (Criteria) this;
        }

        public Criteria andFineValiditaNotBetween(Date value1, Date value2) {
            addCriterion("fine_validita not between", value1, value2, "fineValidita");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated do_not_delete_during_merge Fri May 08 17:20:59 CEST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table caronte.car_r_tipo_com_gruppo
     *
     * @mbg.generated Fri May 08 17:20:59 CEST 2020
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