package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.GenericExample;
import java.util.ArrayList;
import java.util.List;

public class CarDTipoRichiestaExample extends GenericExample {
    /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected String orderByClause;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected boolean distinct;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected List<Criteria> oredCriteria;

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public CarDTipoRichiestaExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public String getOrderByClause() {
    return orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public boolean isDistinct() {
    return distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
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
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_richiesta
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table caronte.car_d_tipo_richiesta
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

    public Criteria andIdTipoRichiestaIsNull() {
      addCriterion("id_tipo_richiesta is null");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaIsNotNull() {
      addCriterion("id_tipo_richiesta is not null");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaEqualTo(Long value) {
      addCriterion("id_tipo_richiesta =", value, "idTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaNotEqualTo(Long value) {
      addCriterion("id_tipo_richiesta <>", value, "idTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaGreaterThan(Long value) {
      addCriterion("id_tipo_richiesta >", value, "idTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaGreaterThanOrEqualTo(Long value) {
      addCriterion("id_tipo_richiesta >=", value, "idTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaLessThan(Long value) {
      addCriterion("id_tipo_richiesta <", value, "idTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaLessThanOrEqualTo(Long value) {
      addCriterion("id_tipo_richiesta <=", value, "idTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaIn(List<Long> values) {
      addCriterion("id_tipo_richiesta in", values, "idTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaNotIn(List<Long> values) {
      addCriterion("id_tipo_richiesta not in", values, "idTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaBetween(Long value1, Long value2) {
      addCriterion("id_tipo_richiesta between", value1, value2, "idTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andIdTipoRichiestaNotBetween(Long value1, Long value2) {
      addCriterion("id_tipo_richiesta not between", value1, value2, "idTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaIsNull() {
      addCriterion("cod_tipo_richiesta is null");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaIsNotNull() {
      addCriterion("cod_tipo_richiesta is not null");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaEqualTo(String value) {
      addCriterion("cod_tipo_richiesta =", value, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaNotEqualTo(String value) {
      addCriterion("cod_tipo_richiesta <>", value, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaGreaterThan(String value) {
      addCriterion("cod_tipo_richiesta >", value, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaGreaterThanOrEqualTo(String value) {
      addCriterion("cod_tipo_richiesta >=", value, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaLessThan(String value) {
      addCriterion("cod_tipo_richiesta <", value, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaLessThanOrEqualTo(String value) {
      addCriterion("cod_tipo_richiesta <=", value, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaLike(String value) {
      addCriterion("cod_tipo_richiesta like", value, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaNotLike(String value) {
      addCriterion("cod_tipo_richiesta not like", value, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaIn(List<String> values) {
      addCriterion("cod_tipo_richiesta in", values, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaNotIn(List<String> values) {
      addCriterion("cod_tipo_richiesta not in", values, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaBetween(String value1, String value2) {
      addCriterion("cod_tipo_richiesta between", value1, value2, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaNotBetween(String value1, String value2) {
      addCriterion("cod_tipo_richiesta not between", value1, value2, "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaIsNull() {
      addCriterion("desc_tipo_richiesta is null");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaIsNotNull() {
      addCriterion("desc_tipo_richiesta is not null");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaEqualTo(String value) {
      addCriterion("desc_tipo_richiesta =", value, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaNotEqualTo(String value) {
      addCriterion("desc_tipo_richiesta <>", value, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaGreaterThan(String value) {
      addCriterion("desc_tipo_richiesta >", value, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaGreaterThanOrEqualTo(String value) {
      addCriterion("desc_tipo_richiesta >=", value, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaLessThan(String value) {
      addCriterion("desc_tipo_richiesta <", value, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaLessThanOrEqualTo(String value) {
      addCriterion("desc_tipo_richiesta <=", value, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaLike(String value) {
      addCriterion("desc_tipo_richiesta like", value, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaNotLike(String value) {
      addCriterion("desc_tipo_richiesta not like", value, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaIn(List<String> values) {
      addCriterion("desc_tipo_richiesta in", values, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaNotIn(List<String> values) {
      addCriterion("desc_tipo_richiesta not in", values, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaBetween(String value1, String value2) {
      addCriterion("desc_tipo_richiesta between", value1, value2, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaNotBetween(String value1, String value2) {
      addCriterion("desc_tipo_richiesta not between", value1, value2, "descTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andCodTipoRichiestaLikeInsensitive(String value) {
      addCriterion("upper(cod_tipo_richiesta) like", value.toUpperCase(), "codTipoRichiesta");
      return (Criteria) this;
    }

    public Criteria andDescTipoRichiestaLikeInsensitive(String value) {
      addCriterion("upper(desc_tipo_richiesta) like", value.toUpperCase(), "descTipoRichiesta");
      return (Criteria) this;
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table caronte.car_d_tipo_richiesta
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
     * This class corresponds to the database table caronte.car_d_tipo_richiesta
     *
     * @mbg.generated do_not_delete_during_merge Wed Feb 14 19:16:19 CET 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}