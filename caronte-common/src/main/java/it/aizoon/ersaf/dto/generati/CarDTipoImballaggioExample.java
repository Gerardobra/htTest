package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.GenericExample;
import java.util.ArrayList;
import java.util.List;

public class CarDTipoImballaggioExample extends GenericExample {
    /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected String orderByClause;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected boolean distinct;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected List<Criteria> oredCriteria;

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public CarDTipoImballaggioExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public String getOrderByClause() {
    return orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public boolean isDistinct() {
    return distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
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
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_imballaggio
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table caronte.car_d_tipo_imballaggio
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

    public Criteria andIdTipoImballaggioIsNull() {
      addCriterion("id_tipo_imballaggio is null");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioIsNotNull() {
      addCriterion("id_tipo_imballaggio is not null");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioEqualTo(Long value) {
      addCriterion("id_tipo_imballaggio =", value, "idTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioNotEqualTo(Long value) {
      addCriterion("id_tipo_imballaggio <>", value, "idTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioGreaterThan(Long value) {
      addCriterion("id_tipo_imballaggio >", value, "idTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioGreaterThanOrEqualTo(Long value) {
      addCriterion("id_tipo_imballaggio >=", value, "idTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioLessThan(Long value) {
      addCriterion("id_tipo_imballaggio <", value, "idTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioLessThanOrEqualTo(Long value) {
      addCriterion("id_tipo_imballaggio <=", value, "idTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioIn(List<Long> values) {
      addCriterion("id_tipo_imballaggio in", values, "idTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioNotIn(List<Long> values) {
      addCriterion("id_tipo_imballaggio not in", values, "idTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioBetween(Long value1, Long value2) {
      addCriterion("id_tipo_imballaggio between", value1, value2, "idTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andIdTipoImballaggioNotBetween(Long value1, Long value2) {
      addCriterion("id_tipo_imballaggio not between", value1, value2, "idTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioIsNull() {
      addCriterion("desc_tipo_imballaggio is null");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioIsNotNull() {
      addCriterion("desc_tipo_imballaggio is not null");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioEqualTo(String value) {
      addCriterion("desc_tipo_imballaggio =", value, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioNotEqualTo(String value) {
      addCriterion("desc_tipo_imballaggio <>", value, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioGreaterThan(String value) {
      addCriterion("desc_tipo_imballaggio >", value, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioGreaterThanOrEqualTo(String value) {
      addCriterion("desc_tipo_imballaggio >=", value, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioLessThan(String value) {
      addCriterion("desc_tipo_imballaggio <", value, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioLessThanOrEqualTo(String value) {
      addCriterion("desc_tipo_imballaggio <=", value, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioLike(String value) {
      addCriterion("desc_tipo_imballaggio like", value, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioNotLike(String value) {
      addCriterion("desc_tipo_imballaggio not like", value, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioIn(List<String> values) {
      addCriterion("desc_tipo_imballaggio in", values, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioNotIn(List<String> values) {
      addCriterion("desc_tipo_imballaggio not in", values, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioBetween(String value1, String value2) {
      addCriterion("desc_tipo_imballaggio between", value1, value2, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioNotBetween(String value1, String value2) {
      addCriterion("desc_tipo_imballaggio not between", value1, value2, "descTipoImballaggio");
      return (Criteria) this;
    }

    public Criteria andDescTipoImballaggioLikeInsensitive(String value) {
      addCriterion("upper(desc_tipo_imballaggio) like", value.toUpperCase(), "descTipoImballaggio");
      return (Criteria) this;
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table caronte.car_d_tipo_imballaggio
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
     * This class corresponds to the database table caronte.car_d_tipo_imballaggio
     *
     * @mbg.generated do_not_delete_during_merge Mon Jun 25 14:38:26 CEST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}