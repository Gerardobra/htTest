package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.GenericExample;
import java.util.ArrayList;
import java.util.List;

public class CarDTipoControlloTariffaExample extends GenericExample {
    /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected String orderByClause;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected boolean distinct;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected List<Criteria> oredCriteria;

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public CarDTipoControlloTariffaExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public String getOrderByClause() {
    return orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public boolean isDistinct() {
    return distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
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
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_d_tipo_controllo_tariffa
   * @mbg.generated  Tue Mar 10 17:11:28 CET 2020
   */
  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table caronte.car_d_tipo_controllo_tariffa
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

    public Criteria andIdTipoControlloTariffaIsNull() {
      addCriterion("id_tipo_controllo_tariffa is null");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaIsNotNull() {
      addCriterion("id_tipo_controllo_tariffa is not null");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaEqualTo(Long value) {
      addCriterion("id_tipo_controllo_tariffa =", value, "idTipoControlloTariffa");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaNotEqualTo(Long value) {
      addCriterion("id_tipo_controllo_tariffa <>", value, "idTipoControlloTariffa");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaGreaterThan(Long value) {
      addCriterion("id_tipo_controllo_tariffa >", value, "idTipoControlloTariffa");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaGreaterThanOrEqualTo(Long value) {
      addCriterion("id_tipo_controllo_tariffa >=", value, "idTipoControlloTariffa");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaLessThan(Long value) {
      addCriterion("id_tipo_controllo_tariffa <", value, "idTipoControlloTariffa");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaLessThanOrEqualTo(Long value) {
      addCriterion("id_tipo_controllo_tariffa <=", value, "idTipoControlloTariffa");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaIn(List<Long> values) {
      addCriterion("id_tipo_controllo_tariffa in", values, "idTipoControlloTariffa");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaNotIn(List<Long> values) {
      addCriterion("id_tipo_controllo_tariffa not in", values, "idTipoControlloTariffa");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaBetween(Long value1, Long value2) {
      addCriterion("id_tipo_controllo_tariffa between", value1, value2, "idTipoControlloTariffa");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloTariffaNotBetween(Long value1, Long value2) {
      addCriterion("id_tipo_controllo_tariffa not between", value1, value2, "idTipoControlloTariffa");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloIsNull() {
      addCriterion("id_tipo_controllo is null");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloIsNotNull() {
      addCriterion("id_tipo_controllo is not null");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloEqualTo(Long value) {
      addCriterion("id_tipo_controllo =", value, "idTipoControllo");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloNotEqualTo(Long value) {
      addCriterion("id_tipo_controllo <>", value, "idTipoControllo");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloGreaterThan(Long value) {
      addCriterion("id_tipo_controllo >", value, "idTipoControllo");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloGreaterThanOrEqualTo(Long value) {
      addCriterion("id_tipo_controllo >=", value, "idTipoControllo");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloLessThan(Long value) {
      addCriterion("id_tipo_controllo <", value, "idTipoControllo");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloLessThanOrEqualTo(Long value) {
      addCriterion("id_tipo_controllo <=", value, "idTipoControllo");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloIn(List<Long> values) {
      addCriterion("id_tipo_controllo in", values, "idTipoControllo");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloNotIn(List<Long> values) {
      addCriterion("id_tipo_controllo not in", values, "idTipoControllo");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloBetween(Long value1, Long value2) {
      addCriterion("id_tipo_controllo between", value1, value2, "idTipoControllo");
      return (Criteria) this;
    }

    public Criteria andIdTipoControlloNotBetween(Long value1, Long value2) {
      addCriterion("id_tipo_controllo not between", value1, value2, "idTipoControllo");
      return (Criteria) this;
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
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table caronte.car_d_tipo_controllo_tariffa
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
     * This class corresponds to the database table caronte.car_d_tipo_controllo_tariffa
     *
     * @mbg.generated do_not_delete_during_merge Wed Feb 14 19:16:19 CET 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}