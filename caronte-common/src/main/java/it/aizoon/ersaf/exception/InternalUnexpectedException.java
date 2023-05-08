package it.aizoon.ersaf.exception;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import it.aizoon.ersaf.dto.internal.LogParameter;
import it.aizoon.ersaf.dto.internal.LogVariable;
import it.aizoon.ersaf.util.CaronteUtils;
import it.aizoon.ersaf.util.DumpUtils;

public class InternalUnexpectedException extends LocalException {

  private static final long serialVersionUID = 1L;
  public static final String MSG_GENERIC_ERROR = CaronteUtils.getMessage("error.internal");
  private String xmlParameters;
  private String xmlVariables;
  private String query;
  private String exceptionStackTrace;

  public InternalUnexpectedException(String message, Throwable cause) {
    super(message, cause);
  }

  public InternalUnexpectedException(Throwable cause) {
    super(MSG_GENERIC_ERROR, cause);
  }

  public InternalUnexpectedException(Throwable cause, LogParameter[] parameters) {
    this(cause, parameters, null, null, (Map<String, ?>)null);
  }

  public InternalUnexpectedException(Throwable cause, String query) {
    this(cause, (LogParameter[])null, (LogVariable[])null, query, (Map<String, ?>)null);
  }

  public InternalUnexpectedException(Throwable cause, LogVariable[] variables) {
    this(cause, null, variables, null, (Map<String, ?>)null);
  }

  public InternalUnexpectedException(Throwable cause, LogParameter[] parameters, LogVariable[] variables) {
    this(cause, parameters, variables, null, (Map<String, ?>)null);
  }

  public InternalUnexpectedException(Throwable cause, String query, MapSqlParameterSource sqlParameters) {
    this(cause, null, null, query, sqlParameters);
  }

  public InternalUnexpectedException(Throwable cause, LogParameter[] parameters, LogVariable[] variables,
      String query, MapSqlParameterSource sqlParameters) {
    this(cause, parameters, variables, query, sqlParameters == null ? null : sqlParameters.getValues());
  }

  public InternalUnexpectedException(Throwable cause, LogParameter[] parameters, LogVariable[] variables,
      String query, MapSqlParameterSource[] sqlParameters) {
    this(cause, parameters, variables, query, sqlParameters == null ? null : toArrayMap(sqlParameters));
  }

  public InternalUnexpectedException(Throwable cause, LogParameter[] parameters, LogVariable[] variables,
      String query, Map<String, ?> mapSqlParameters) {
    super(MSG_GENERIC_ERROR, cause);
    this.xmlParameters = DumpUtils.dump(parameters, "Parametri");
    this.xmlVariables = DumpUtils.dump(variables, "Variabili");
    this.query = query + extractSqlParameter(mapSqlParameters);
    this.exceptionStackTrace = DumpUtils.getExceptionStackTrace(getCause());
  }

  public InternalUnexpectedException(Throwable cause, LogParameter[] parameters, LogVariable[] variables,
      String query, Map<String, ?>[] mapSqlParameters) {
    super(MSG_GENERIC_ERROR, cause);
    this.xmlParameters = DumpUtils.dump(parameters, "Parametri");
    this.xmlVariables = DumpUtils.dump(variables, "Variabili");
    this.query = query + extractSqlParameter(mapSqlParameters);
    this.exceptionStackTrace = DumpUtils.getExceptionStackTrace(getCause());
  }

  private static Map<String, ?>[] toArrayMap(MapSqlParameterSource[] sqlParameters) {
    if (sqlParameters == null) {
      return null;
    }
    @SuppressWarnings("unchecked")
    Map<String, ?>[] array = (Map<String, ?>[])new Map[sqlParameters.length];
    int i = 0;
    for (MapSqlParameterSource mapParameters : sqlParameters) {
      array[i++] = mapParameters.getValues();
    }
    return array;
  }

  private String extractSqlParameter(Map<String, ?>[] arraySqlParameters) {
    StringBuilder sbSqlParameter = new StringBuilder("\nParametri della query:\n");
    if (arraySqlParameters != null) {
      int i = 1;
      for (Map<String, ?> mapSqlParameters : arraySqlParameters) {
        sbSqlParameter.append("Set # ").append(i++).append(":\n");
        for (String key : mapSqlParameters.keySet()) {
          sbSqlParameter.append(key).append(" = ");
          Object value = mapSqlParameters.get(key);
          if (value instanceof String) {
            sbSqlParameter.append('\'').append(value).append('\'');
          }else {
            sbSqlParameter.append(value);
          }
          sbSqlParameter.append("\n");
        }
      }
    }
    return sbSqlParameter.append("\n").toString();
  }

  private String extractSqlParameter(Map<String, ?> mapSqlParameters) {
    StringBuilder sbSqlParameter = new StringBuilder("\nParametri della query:\n");
    if (mapSqlParameters != null) {
      for (String key : mapSqlParameters.keySet()) {
        sbSqlParameter.append(key).append(" = ");
        Object value = mapSqlParameters.get(key);
        if (value instanceof String) {
          sbSqlParameter.append('\'').append(value).append('\'');
        }else {
          sbSqlParameter.append(value);
        }
      }
    }
    return sbSqlParameter.append("\n").toString();
  }

  public String getParameters() {
    return xmlParameters;
  }

  public String getVariables() {
    return xmlVariables;
  }

  public String getQuery() {
    return query;
  }

  public String getExceptionStackTrace() {
    return exceptionStackTrace;
  }

  public void setLogParameters(LogParameter[] parameters) {
    this.xmlParameters = DumpUtils.dump(parameters, "Parametri");
  }

  public void setLogVariables(LogVariable[] variables) {
    this.xmlVariables = DumpUtils.dump(variables, "Variabili");
  }
}
