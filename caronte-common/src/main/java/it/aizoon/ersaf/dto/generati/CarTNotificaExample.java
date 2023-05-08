package it.aizoon.ersaf.dto.generati;

import it.aizoon.ersaf.dto.GenericExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CarTNotificaExample extends GenericExample {
    /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  protected String orderByClause;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  protected boolean distinct;
  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  protected List<Criteria> oredCriteria;

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public CarTNotificaExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public String getOrderByClause() {
    return orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public boolean isDistinct() {
    return distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
   */
  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
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

    public Criteria andIdNotificaIsNull() {
      addCriterion("id_notifica is null");
      return (Criteria) this;
    }

    public Criteria andIdNotificaIsNotNull() {
      addCriterion("id_notifica is not null");
      return (Criteria) this;
    }

    public Criteria andIdNotificaEqualTo(Long value) {
      addCriterion("id_notifica =", value, "idNotifica");
      return (Criteria) this;
    }

    public Criteria andIdNotificaNotEqualTo(Long value) {
      addCriterion("id_notifica <>", value, "idNotifica");
      return (Criteria) this;
    }

    public Criteria andIdNotificaGreaterThan(Long value) {
      addCriterion("id_notifica >", value, "idNotifica");
      return (Criteria) this;
    }

    public Criteria andIdNotificaGreaterThanOrEqualTo(Long value) {
      addCriterion("id_notifica >=", value, "idNotifica");
      return (Criteria) this;
    }

    public Criteria andIdNotificaLessThan(Long value) {
      addCriterion("id_notifica <", value, "idNotifica");
      return (Criteria) this;
    }

    public Criteria andIdNotificaLessThanOrEqualTo(Long value) {
      addCriterion("id_notifica <=", value, "idNotifica");
      return (Criteria) this;
    }

    public Criteria andIdNotificaIn(List<Long> values) {
      addCriterion("id_notifica in", values, "idNotifica");
      return (Criteria) this;
    }

    public Criteria andIdNotificaNotIn(List<Long> values) {
      addCriterion("id_notifica not in", values, "idNotifica");
      return (Criteria) this;
    }

    public Criteria andIdNotificaBetween(Long value1, Long value2) {
      addCriterion("id_notifica between", value1, value2, "idNotifica");
      return (Criteria) this;
    }

    public Criteria andIdNotificaNotBetween(Long value1, Long value2) {
      addCriterion("id_notifica not between", value1, value2, "idNotifica");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaIsNull() {
      addCriterion("id_tipo_notifica is null");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaIsNotNull() {
      addCriterion("id_tipo_notifica is not null");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaEqualTo(Long value) {
      addCriterion("id_tipo_notifica =", value, "idTipoNotifica");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaNotEqualTo(Long value) {
      addCriterion("id_tipo_notifica <>", value, "idTipoNotifica");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaGreaterThan(Long value) {
      addCriterion("id_tipo_notifica >", value, "idTipoNotifica");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaGreaterThanOrEqualTo(Long value) {
      addCriterion("id_tipo_notifica >=", value, "idTipoNotifica");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaLessThan(Long value) {
      addCriterion("id_tipo_notifica <", value, "idTipoNotifica");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaLessThanOrEqualTo(Long value) {
      addCriterion("id_tipo_notifica <=", value, "idTipoNotifica");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaIn(List<Long> values) {
      addCriterion("id_tipo_notifica in", values, "idTipoNotifica");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaNotIn(List<Long> values) {
      addCriterion("id_tipo_notifica not in", values, "idTipoNotifica");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaBetween(Long value1, Long value2) {
      addCriterion("id_tipo_notifica between", value1, value2, "idTipoNotifica");
      return (Criteria) this;
    }

    public Criteria andIdTipoNotificaNotBetween(Long value1, Long value2) {
      addCriterion("id_tipo_notifica not between", value1, value2, "idTipoNotifica");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneIsNull() {
      addCriterion("id_associazione_sezione is null");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneIsNotNull() {
      addCriterion("id_associazione_sezione is not null");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneEqualTo(Long value) {
      addCriterion("id_associazione_sezione =", value, "idAssociazioneSezione");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneNotEqualTo(Long value) {
      addCriterion("id_associazione_sezione <>", value, "idAssociazioneSezione");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneGreaterThan(Long value) {
      addCriterion("id_associazione_sezione >", value, "idAssociazioneSezione");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneGreaterThanOrEqualTo(Long value) {
      addCriterion("id_associazione_sezione >=", value, "idAssociazioneSezione");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneLessThan(Long value) {
      addCriterion("id_associazione_sezione <", value, "idAssociazioneSezione");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneLessThanOrEqualTo(Long value) {
      addCriterion("id_associazione_sezione <=", value, "idAssociazioneSezione");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneIn(List<Long> values) {
      addCriterion("id_associazione_sezione in", values, "idAssociazioneSezione");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneNotIn(List<Long> values) {
      addCriterion("id_associazione_sezione not in", values, "idAssociazioneSezione");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneBetween(Long value1, Long value2) {
      addCriterion("id_associazione_sezione between", value1, value2, "idAssociazioneSezione");
      return (Criteria) this;
    }

    public Criteria andIdAssociazioneSezioneNotBetween(Long value1, Long value2) {
      addCriterion("id_associazione_sezione not between", value1, value2, "idAssociazioneSezione");
      return (Criteria) this;
    }

    public Criteria andMessaggioIsNull() {
      addCriterion("messaggio is null");
      return (Criteria) this;
    }

    public Criteria andMessaggioIsNotNull() {
      addCriterion("messaggio is not null");
      return (Criteria) this;
    }

    public Criteria andMessaggioEqualTo(String value) {
      addCriterion("messaggio =", value, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioNotEqualTo(String value) {
      addCriterion("messaggio <>", value, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioGreaterThan(String value) {
      addCriterion("messaggio >", value, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioGreaterThanOrEqualTo(String value) {
      addCriterion("messaggio >=", value, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioLessThan(String value) {
      addCriterion("messaggio <", value, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioLessThanOrEqualTo(String value) {
      addCriterion("messaggio <=", value, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioLike(String value) {
      addCriterion("messaggio like", value, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioNotLike(String value) {
      addCriterion("messaggio not like", value, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioIn(List<String> values) {
      addCriterion("messaggio in", values, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioNotIn(List<String> values) {
      addCriterion("messaggio not in", values, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioBetween(String value1, String value2) {
      addCriterion("messaggio between", value1, value2, "messaggio");
      return (Criteria) this;
    }

    public Criteria andMessaggioNotBetween(String value1, String value2) {
      addCriterion("messaggio not between", value1, value2, "messaggio");
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

    public Criteria andIdUtenteInsertIsNull() {
      addCriterion("id_utente_insert is null");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertIsNotNull() {
      addCriterion("id_utente_insert is not null");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertEqualTo(Long value) {
      addCriterion("id_utente_insert =", value, "idUtenteInsert");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertNotEqualTo(Long value) {
      addCriterion("id_utente_insert <>", value, "idUtenteInsert");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertGreaterThan(Long value) {
      addCriterion("id_utente_insert >", value, "idUtenteInsert");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertGreaterThanOrEqualTo(Long value) {
      addCriterion("id_utente_insert >=", value, "idUtenteInsert");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertLessThan(Long value) {
      addCriterion("id_utente_insert <", value, "idUtenteInsert");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertLessThanOrEqualTo(Long value) {
      addCriterion("id_utente_insert <=", value, "idUtenteInsert");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertIn(List<Long> values) {
      addCriterion("id_utente_insert in", values, "idUtenteInsert");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertNotIn(List<Long> values) {
      addCriterion("id_utente_insert not in", values, "idUtenteInsert");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertBetween(Long value1, Long value2) {
      addCriterion("id_utente_insert between", value1, value2, "idUtenteInsert");
      return (Criteria) this;
    }

    public Criteria andIdUtenteInsertNotBetween(Long value1, Long value2) {
      addCriterion("id_utente_insert not between", value1, value2, "idUtenteInsert");
      return (Criteria) this;
    }

    public Criteria andDataInsertIsNull() {
      addCriterion("data_insert is null");
      return (Criteria) this;
    }

    public Criteria andDataInsertIsNotNull() {
      addCriterion("data_insert is not null");
      return (Criteria) this;
    }

    public Criteria andDataInsertEqualTo(Date value) {
      addCriterion("data_insert =", value, "dataInsert");
      return (Criteria) this;
    }

    public Criteria andDataInsertNotEqualTo(Date value) {
      addCriterion("data_insert <>", value, "dataInsert");
      return (Criteria) this;
    }

    public Criteria andDataInsertGreaterThan(Date value) {
      addCriterion("data_insert >", value, "dataInsert");
      return (Criteria) this;
    }

    public Criteria andDataInsertGreaterThanOrEqualTo(Date value) {
      addCriterion("data_insert >=", value, "dataInsert");
      return (Criteria) this;
    }

    public Criteria andDataInsertLessThan(Date value) {
      addCriterion("data_insert <", value, "dataInsert");
      return (Criteria) this;
    }

    public Criteria andDataInsertLessThanOrEqualTo(Date value) {
      addCriterion("data_insert <=", value, "dataInsert");
      return (Criteria) this;
    }

    public Criteria andDataInsertIn(List<Date> values) {
      addCriterion("data_insert in", values, "dataInsert");
      return (Criteria) this;
    }

    public Criteria andDataInsertNotIn(List<Date> values) {
      addCriterion("data_insert not in", values, "dataInsert");
      return (Criteria) this;
    }

    public Criteria andDataInsertBetween(Date value1, Date value2) {
      addCriterion("data_insert between", value1, value2, "dataInsert");
      return (Criteria) this;
    }

    public Criteria andDataInsertNotBetween(Date value1, Date value2) {
      addCriterion("data_insert not between", value1, value2, "dataInsert");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateIsNull() {
      addCriterion("id_utente_update is null");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateIsNotNull() {
      addCriterion("id_utente_update is not null");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateEqualTo(Long value) {
      addCriterion("id_utente_update =", value, "idUtenteUpdate");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateNotEqualTo(Long value) {
      addCriterion("id_utente_update <>", value, "idUtenteUpdate");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateGreaterThan(Long value) {
      addCriterion("id_utente_update >", value, "idUtenteUpdate");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateGreaterThanOrEqualTo(Long value) {
      addCriterion("id_utente_update >=", value, "idUtenteUpdate");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateLessThan(Long value) {
      addCriterion("id_utente_update <", value, "idUtenteUpdate");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateLessThanOrEqualTo(Long value) {
      addCriterion("id_utente_update <=", value, "idUtenteUpdate");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateIn(List<Long> values) {
      addCriterion("id_utente_update in", values, "idUtenteUpdate");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateNotIn(List<Long> values) {
      addCriterion("id_utente_update not in", values, "idUtenteUpdate");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateBetween(Long value1, Long value2) {
      addCriterion("id_utente_update between", value1, value2, "idUtenteUpdate");
      return (Criteria) this;
    }

    public Criteria andIdUtenteUpdateNotBetween(Long value1, Long value2) {
      addCriterion("id_utente_update not between", value1, value2, "idUtenteUpdate");
      return (Criteria) this;
    }

    public Criteria andDataUpdateIsNull() {
      addCriterion("data_update is null");
      return (Criteria) this;
    }

    public Criteria andDataUpdateIsNotNull() {
      addCriterion("data_update is not null");
      return (Criteria) this;
    }

    public Criteria andDataUpdateEqualTo(Date value) {
      addCriterion("data_update =", value, "dataUpdate");
      return (Criteria) this;
    }

    public Criteria andDataUpdateNotEqualTo(Date value) {
      addCriterion("data_update <>", value, "dataUpdate");
      return (Criteria) this;
    }

    public Criteria andDataUpdateGreaterThan(Date value) {
      addCriterion("data_update >", value, "dataUpdate");
      return (Criteria) this;
    }

    public Criteria andDataUpdateGreaterThanOrEqualTo(Date value) {
      addCriterion("data_update >=", value, "dataUpdate");
      return (Criteria) this;
    }

    public Criteria andDataUpdateLessThan(Date value) {
      addCriterion("data_update <", value, "dataUpdate");
      return (Criteria) this;
    }

    public Criteria andDataUpdateLessThanOrEqualTo(Date value) {
      addCriterion("data_update <=", value, "dataUpdate");
      return (Criteria) this;
    }

    public Criteria andDataUpdateIn(List<Date> values) {
      addCriterion("data_update in", values, "dataUpdate");
      return (Criteria) this;
    }

    public Criteria andDataUpdateNotIn(List<Date> values) {
      addCriterion("data_update not in", values, "dataUpdate");
      return (Criteria) this;
    }

    public Criteria andDataUpdateBetween(Date value1, Date value2) {
      addCriterion("data_update between", value1, value2, "dataUpdate");
      return (Criteria) this;
    }

    public Criteria andDataUpdateNotBetween(Date value1, Date value2) {
      addCriterion("data_update not between", value1, value2, "dataUpdate");
      return (Criteria) this;
    }

    public Criteria andMessaggioLikeInsensitive(String value) {
      addCriterion("upper(messaggio) like", value.toUpperCase(), "messaggio");
      return (Criteria) this;
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table caronte.car_t_notifica
   * @mbg.generated  Wed Oct 31 16:30:01 CET 2018
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
     * This class corresponds to the database table caronte.car_t_notifica
     *
     * @mbg.generated do_not_delete_during_merge Thu Feb 15 16:07:23 CET 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}