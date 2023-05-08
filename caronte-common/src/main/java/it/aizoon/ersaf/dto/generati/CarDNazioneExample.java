package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.GenericExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CarDNazioneExample extends GenericExample {
    /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected String orderByClause;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected boolean distinct;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected List<Criteria> oredCriteria;

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public CarDNazioneExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public String getOrderByClause() {
    return orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public boolean isDistinct() {
    return distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
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

    protected void addCriterionForJDBCDate(String condition, Date value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      addCriterion(condition, new java.sql.Date(value.getTime()), property);
    }

    protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
      if (values == null || values.size() == 0) {
        throw new RuntimeException("Value list for " + property + " cannot be null or empty");
      }
      List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
      Iterator<Date> iter = values.iterator();
      while (iter.hasNext()) {
        dateList.add(new java.sql.Date(iter.next().getTime()));
      }
      addCriterion(condition, dateList, property);
    }

    protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
    }

    public Criteria andIdNazioneIsNull() {
      addCriterion("id_nazione is null");
      return (Criteria) this;
    }

    public Criteria andIdNazioneIsNotNull() {
      addCriterion("id_nazione is not null");
      return (Criteria) this;
    }

    public Criteria andIdNazioneEqualTo(Long value) {
      addCriterion("id_nazione =", value, "idNazione");
      return (Criteria) this;
    }

    public Criteria andIdNazioneNotEqualTo(Long value) {
      addCriterion("id_nazione <>", value, "idNazione");
      return (Criteria) this;
    }

    public Criteria andIdNazioneGreaterThan(Long value) {
      addCriterion("id_nazione >", value, "idNazione");
      return (Criteria) this;
    }

    public Criteria andIdNazioneGreaterThanOrEqualTo(Long value) {
      addCriterion("id_nazione >=", value, "idNazione");
      return (Criteria) this;
    }

    public Criteria andIdNazioneLessThan(Long value) {
      addCriterion("id_nazione <", value, "idNazione");
      return (Criteria) this;
    }

    public Criteria andIdNazioneLessThanOrEqualTo(Long value) {
      addCriterion("id_nazione <=", value, "idNazione");
      return (Criteria) this;
    }

    public Criteria andIdNazioneIn(List<Long> values) {
      addCriterion("id_nazione in", values, "idNazione");
      return (Criteria) this;
    }

    public Criteria andIdNazioneNotIn(List<Long> values) {
      addCriterion("id_nazione not in", values, "idNazione");
      return (Criteria) this;
    }

    public Criteria andIdNazioneBetween(Long value1, Long value2) {
      addCriterion("id_nazione between", value1, value2, "idNazione");
      return (Criteria) this;
    }

    public Criteria andIdNazioneNotBetween(Long value1, Long value2) {
      addCriterion("id_nazione not between", value1, value2, "idNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneIsNull() {
      addCriterion("cod_nazione is null");
      return (Criteria) this;
    }

    public Criteria andCodNazioneIsNotNull() {
      addCriterion("cod_nazione is not null");
      return (Criteria) this;
    }

    public Criteria andCodNazioneEqualTo(String value) {
      addCriterion("cod_nazione =", value, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneNotEqualTo(String value) {
      addCriterion("cod_nazione <>", value, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneGreaterThan(String value) {
      addCriterion("cod_nazione >", value, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneGreaterThanOrEqualTo(String value) {
      addCriterion("cod_nazione >=", value, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneLessThan(String value) {
      addCriterion("cod_nazione <", value, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneLessThanOrEqualTo(String value) {
      addCriterion("cod_nazione <=", value, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneLike(String value) {
      addCriterion("cod_nazione like", value, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneNotLike(String value) {
      addCriterion("cod_nazione not like", value, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneIn(List<String> values) {
      addCriterion("cod_nazione in", values, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneNotIn(List<String> values) {
      addCriterion("cod_nazione not in", values, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneBetween(String value1, String value2) {
      addCriterion("cod_nazione between", value1, value2, "codNazione");
      return (Criteria) this;
    }

    public Criteria andCodNazioneNotBetween(String value1, String value2) {
      addCriterion("cod_nazione not between", value1, value2, "codNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneIsNull() {
      addCriterion("denom_nazione is null");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneIsNotNull() {
      addCriterion("denom_nazione is not null");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneEqualTo(String value) {
      addCriterion("denom_nazione =", value, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneNotEqualTo(String value) {
      addCriterion("denom_nazione <>", value, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneGreaterThan(String value) {
      addCriterion("denom_nazione >", value, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneGreaterThanOrEqualTo(String value) {
      addCriterion("denom_nazione >=", value, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneLessThan(String value) {
      addCriterion("denom_nazione <", value, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneLessThanOrEqualTo(String value) {
      addCriterion("denom_nazione <=", value, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneLike(String value) {
      addCriterion("denom_nazione like", value, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneNotLike(String value) {
      addCriterion("denom_nazione not like", value, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneIn(List<String> values) {
      addCriterion("denom_nazione in", values, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneNotIn(List<String> values) {
      addCriterion("denom_nazione not in", values, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneBetween(String value1, String value2) {
      addCriterion("denom_nazione between", value1, value2, "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneNotBetween(String value1, String value2) {
      addCriterion("denom_nazione not between", value1, value2, "denomNazione");
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
      addCriterionForJDBCDate("inizio_validita =", value, "inizioValidita");
      return (Criteria) this;
    }

    public Criteria andInizioValiditaNotEqualTo(Date value) {
      addCriterionForJDBCDate("inizio_validita <>", value, "inizioValidita");
      return (Criteria) this;
    }

    public Criteria andInizioValiditaGreaterThan(Date value) {
      addCriterionForJDBCDate("inizio_validita >", value, "inizioValidita");
      return (Criteria) this;
    }

    public Criteria andInizioValiditaGreaterThanOrEqualTo(Date value) {
      addCriterionForJDBCDate("inizio_validita >=", value, "inizioValidita");
      return (Criteria) this;
    }

    public Criteria andInizioValiditaLessThan(Date value) {
      addCriterionForJDBCDate("inizio_validita <", value, "inizioValidita");
      return (Criteria) this;
    }

    public Criteria andInizioValiditaLessThanOrEqualTo(Date value) {
      addCriterionForJDBCDate("inizio_validita <=", value, "inizioValidita");
      return (Criteria) this;
    }

    public Criteria andInizioValiditaIn(List<Date> values) {
      addCriterionForJDBCDate("inizio_validita in", values, "inizioValidita");
      return (Criteria) this;
    }

    public Criteria andInizioValiditaNotIn(List<Date> values) {
      addCriterionForJDBCDate("inizio_validita not in", values, "inizioValidita");
      return (Criteria) this;
    }

    public Criteria andInizioValiditaBetween(Date value1, Date value2) {
      addCriterionForJDBCDate("inizio_validita between", value1, value2, "inizioValidita");
      return (Criteria) this;
    }

    public Criteria andInizioValiditaNotBetween(Date value1, Date value2) {
      addCriterionForJDBCDate("inizio_validita not between", value1, value2, "inizioValidita");
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
      addCriterionForJDBCDate("fine_validita =", value, "fineValidita");
      return (Criteria) this;
    }

    public Criteria andFineValiditaNotEqualTo(Date value) {
      addCriterionForJDBCDate("fine_validita <>", value, "fineValidita");
      return (Criteria) this;
    }

    public Criteria andFineValiditaGreaterThan(Date value) {
      addCriterionForJDBCDate("fine_validita >", value, "fineValidita");
      return (Criteria) this;
    }

    public Criteria andFineValiditaGreaterThanOrEqualTo(Date value) {
      addCriterionForJDBCDate("fine_validita >=", value, "fineValidita");
      return (Criteria) this;
    }

    public Criteria andFineValiditaLessThan(Date value) {
      addCriterionForJDBCDate("fine_validita <", value, "fineValidita");
      return (Criteria) this;
    }

    public Criteria andFineValiditaLessThanOrEqualTo(Date value) {
      addCriterionForJDBCDate("fine_validita <=", value, "fineValidita");
      return (Criteria) this;
    }

    public Criteria andFineValiditaIn(List<Date> values) {
      addCriterionForJDBCDate("fine_validita in", values, "fineValidita");
      return (Criteria) this;
    }

    public Criteria andFineValiditaNotIn(List<Date> values) {
      addCriterionForJDBCDate("fine_validita not in", values, "fineValidita");
      return (Criteria) this;
    }

    public Criteria andFineValiditaBetween(Date value1, Date value2) {
      addCriterionForJDBCDate("fine_validita between", value1, value2, "fineValidita");
      return (Criteria) this;
    }

    public Criteria andFineValiditaNotBetween(Date value1, Date value2) {
      addCriterionForJDBCDate("fine_validita not between", value1, value2, "fineValidita");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertIsNull() {
      addCriterion("denom_nazione_cert is null");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertIsNotNull() {
      addCriterion("denom_nazione_cert is not null");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertEqualTo(String value) {
      addCriterion("denom_nazione_cert =", value, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertNotEqualTo(String value) {
      addCriterion("denom_nazione_cert <>", value, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertGreaterThan(String value) {
      addCriterion("denom_nazione_cert >", value, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertGreaterThanOrEqualTo(String value) {
      addCriterion("denom_nazione_cert >=", value, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertLessThan(String value) {
      addCriterion("denom_nazione_cert <", value, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertLessThanOrEqualTo(String value) {
      addCriterion("denom_nazione_cert <=", value, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertLike(String value) {
      addCriterion("denom_nazione_cert like", value, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertNotLike(String value) {
      addCriterion("denom_nazione_cert not like", value, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertIn(List<String> values) {
      addCriterion("denom_nazione_cert in", values, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertNotIn(List<String> values) {
      addCriterion("denom_nazione_cert not in", values, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertBetween(String value1, String value2) {
      addCriterion("denom_nazione_cert between", value1, value2, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertNotBetween(String value1, String value2) {
      addCriterion("denom_nazione_cert not between", value1, value2, "denomNazioneCert");
      return (Criteria) this;
    }

    public Criteria andCodNazioneLikeInsensitive(String value) {
      addCriterion("upper(cod_nazione) like", value.toUpperCase(), "codNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneLikeInsensitive(String value) {
      addCriterion("upper(denom_nazione) like", value.toUpperCase(), "denomNazione");
      return (Criteria) this;
    }

    public Criteria andDenomNazioneCertLikeInsensitive(String value) {
      addCriterion("upper(denom_nazione_cert) like", value.toUpperCase(), "denomNazioneCert");
      return (Criteria) this;
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table caronte.car_d_nazione
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
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

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table caronte.car_d_nazione
     *
     * @mbg.generated do_not_delete_during_merge Wed Feb 14 19:16:19 CET 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}