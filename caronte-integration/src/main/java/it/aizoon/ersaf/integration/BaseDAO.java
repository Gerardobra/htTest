package it.aizoon.ersaf.integration;

import static it.aizoon.ersaf.util.CaronteConstants.LOGGER_NAME;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import it.aizoon.ersaf.dto.DecodificaDTO;
import it.aizoon.ersaf.dto.internal.LogParameter;
import it.aizoon.ersaf.dto.internal.LogVariable;
import it.aizoon.ersaf.exception.DatabaseAutomationException;
import it.aizoon.ersaf.exception.InternalException;
import it.aizoon.ersaf.exception.InternalUnexpectedException;
import it.aizoon.ersaf.integration.annotation.DBQuery;
import it.aizoon.ersaf.util.CaronteConstants;
import it.aizoon.ersaf.util.CaronteUtils;

public abstract class BaseDAO{

	private static final String THIS_CLASS = BaseDAO.class.getSimpleName();
	protected static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ".integration");
	protected final int PASSO = 500;

	//@Autowired
	//@Inject
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Resource(lookup=CaronteConstants.CARONTE_DS)
	DataSource dataSource;
	//@Autowired
	/*@Inject
  protected ApplicationContext appContext;*/

	@PostConstruct
	protected void initialize() {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public <T> T queryForObject(String query, SqlParameterSource parameters, Class<T> objClass,
			ResultSetExtractor<T> re) {
		return namedParameterJdbcTemplate.query(query, parameters, re);
	}

	public <T> T queryForObject(String query, SqlParameterSource parameters, Class<T> objClass) {
		ResultSetExtractor<T> re = new GenericObjectExtractor<T>(objClass);
		return namedParameterJdbcTemplate.query(query, parameters, re);
	}

	public <T> List<T> queryForList(String query, SqlParameterSource parameters, Class<T> objClass) {
		ResultSetExtractor<List<T>> re = new GenericListEstractor<T>(objClass);
		return namedParameterJdbcTemplate.query(query, parameters, re);
	}

	public <T> T queryForObject(SqlParameterSource parameters, Class<T> objClass)
			throws DatabaseAutomationException {
		DBQuery dbQueryAnnotation = objClass.getAnnotation(DBQuery.class);

		if (dbQueryAnnotation == null) {
			throw new DatabaseAutomationException(
					CaronteUtils.getMessage("error.dbquery.missing_annotation", objClass.getName()));
		}

		ResultSetExtractor<T> re = new GenericObjectExtractor<T>(objClass);
		return namedParameterJdbcTemplate.query(dbQueryAnnotation.value(), parameters, re);
	}

	public <T> List<T> queryForList(SqlParameterSource parameters, Class<T> objClass)
			throws DatabaseAutomationException {
		DBQuery dbQueryAnnotation = objClass.getAnnotation(DBQuery.class);

		if (dbQueryAnnotation == null) {
			throw new DatabaseAutomationException(
					CaronteUtils.getMessage("error.dbquery.missing_annotation", objClass.getName()));
		}

		ResultSetExtractor<List<T>> re = new GenericListEstractor<T>(objClass);
		return namedParameterJdbcTemplate.query(dbQueryAnnotation.value(), parameters, re);
	}

	public boolean update(IPersistent... objUpdate) throws InternalException {
		DatabaseUpdater dbu = new DatabaseUpdater(namedParameterJdbcTemplate);
		dbu.update(objUpdate);
		return true;
	}

	public boolean insert(IPersistent... objUpdate) throws InternalException {
		DatabaseUpdater dbu = new DatabaseUpdater(namedParameterJdbcTemplate);
		dbu.insert(objUpdate);
		return true;
	}

	public int update(String query, MapSqlParameterSource parameters) throws InternalUnexpectedException {
		String THIS_METHOD = "chiudiUltimoStato";
		if (logger.isDebugEnabled()) {
			logger.debug("[" + THIS_CLASS + ":: " + THIS_METHOD + "] BEGIN.");
		}

		try {
			return namedParameterJdbcTemplate.update(query, parameters);
		}catch (Throwable t) {
			InternalUnexpectedException e = new InternalUnexpectedException(t, query, parameters);
			logInternalUnexpectedException(e, "[" + THIS_CLASS + ":: " + THIS_METHOD + "]");
			throw e;
		}finally {
			if (logger.isDebugEnabled()) {
				logger.debug("[" + THIS_CLASS + ":: " + THIS_METHOD + "] END.");
			}
		}
	}

	public String getInCondition(String campo, List<?> vId) {
		int cicli = vId.size() / PASSO;

		if (vId.size() % PASSO != 0) {
			cicli++;
		}

		StringBuilder condition = new StringBuilder(" AND ( ");

		for (int j = 0; j < cicli; j++) {
			if (j != 0) {
				condition.append(" OR ");
			}

			boolean primo = true;

			for (int i = j * PASSO; i < ((j + 1) * PASSO) && i < vId.size(); i++) {
				if (primo) {
					condition.append(" " + campo + " IN (" + getIdFromvId(vId, i));
					primo = false;
				}else {
					condition.append("," + getIdFromvId(vId, i));
				}
			}

			condition.append(")");
		}

		condition.append(")");

		return condition.toString();
	}

	protected String getIdFromvId(List<?> vId, int idx) {
		Object o = vId.get(idx);

		if (o instanceof String) {
			return "'" + (String)o + "'";
		}

		return o.toString();
	}

	public String getInCondition(String campo, Vector<?> vId, boolean andClause) {
		int cicli = vId.size() / PASSO;
		if (vId.size() % PASSO != 0) {
			cicli++;
		}

		StringBuilder condition = new StringBuilder("  ");

		if (andClause) {
			condition.append(" AND ( ");
		}

		for (int j = 0; j < cicli; j++) {
			if (j != 0) {
				condition.append(" OR ");
			}

			boolean primo = true;

			for (int i = j * PASSO; i < ((j + 1) * PASSO) && i < vId.size(); i++) {
				if (primo) {
					condition.append(" " + campo + " IN (" + getIdFromvId(vId, i));
					primo = false;
				}else {
					condition.append("," + getIdFromvId(vId, i));
				}
			}

			condition.append(")");
		}

		if (andClause) {
			condition.append(")");
		}

		return condition.toString();
	}

	public String getNotInCondition(String campo, Vector<?> vId) {
		int cicli = vId.size() / PASSO;

		if (vId.size() % PASSO != 0) {
			cicli++;
		}

		StringBuilder condition = new StringBuilder(" AND ( ");

		for (int j = 0; j < cicli; j++) {
			if (j != 0) {
				condition.append(" OR ");
			}

			boolean primo = true;

			for (int i = j * PASSO; i < ((j + 1) * PASSO) && i < vId.size(); i++) {
				if (primo) {
					condition.append(" " + campo + " NOT IN (" + getIdFromvId(vId, i));
					primo = false;
				}else {
					condition.append("," + getIdFromvId(vId, i));
				}
			}

			condition.append(")");
		}

		condition.append(")");
		return condition.toString();
	}

	protected String getIdFromvId(Vector<?> vId, int idx) {

		Object o = vId.get(idx);

		if (o instanceof String) {
			return "'" + (String)o + "'";
		}else if (o instanceof Long) {
			return ((Long)o).toString();
		}else if (o instanceof BigDecimal) {
			return ((BigDecimal)o).toString();
		}else {
			return o.toString();
		}
	}

	public long getNextSequenceValue(String sequenceName) {
		String query = " SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
		return namedParameterJdbcTemplate.queryForObject(query, (SqlParameterSource)null, Long.class);
	}

	protected void logInternalUnexpectedException(InternalUnexpectedException e, String logHeader) {
		logger.error(logHeader +
				" *********************************** INIZIO DUMP ECCEZIONE  ***********************************");
		logger.error(logHeader + " Query:\n" + e.getQuery() + "\n");
		logger.error(logHeader + " Parametri del metodo:\n" + e.getParameters() + "\n");
		logger.error(logHeader + " Variabili del metodo:\n" + e.getVariables() + "\n");
		logger.error(logHeader + " Stacktrace:\n" + e.getExceptionStackTrace() + "\n");
		logger.error(logHeader +
				" ************************************ FINE DUMP ECCEZIONE *************************************");
	}

	public Long getLongNull(ResultSet rs, String name) throws SQLException {
		String value = rs.getString(name);

		if (value != null) {
			return Long.valueOf(value);
		}

		return null;
	}

	public Integer getIntegerNull(ResultSet rs, String name) throws SQLException {
		String value = rs.getString(name);

		if (value != null) {
			return Integer.valueOf(value);
		}

		return null;
	}

	public Long queryForLong(String query, MapSqlParameterSource mapParameterSource) {
		List<Long> list = namedParameterJdbcTemplate.query(query, mapParameterSource, (rs, index) -> {
			String value = rs.getString(1);
			if (value != null) {
				return Long.valueOf(value);
			}
			return null;
		});

		return list == null || list.isEmpty() ? null : list.get(0);
	}

	public BigDecimal queryForBigDecimal(String query, MapSqlParameterSource mapParameterSource) {
		List<BigDecimal> list =
				namedParameterJdbcTemplate.query(query, mapParameterSource, (rs, index) -> rs.getBigDecimal(1));
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	public int delete(String table, String idName, long idValue) {
		StringBuilder query = new StringBuilder("DELETE FROM ").append(table).append(" WHERE ").append(idName)
				.append(" = ").append(idValue);
		return namedParameterJdbcTemplate.update(query.toString(), (SqlParameterSource)null);
	}

	public Long select(String table, String idName, long idValue) {
		StringBuilder query = new StringBuilder("SELECT ").append(idName).append(" FROM ").append(table)
				.append(" WHERE ").append(idName).append(" = ").append(idValue);
		List<Long> list =
				namedParameterJdbcTemplate.query(query.toString(), (SqlParameterSource)null, (rs, index) -> rs.getLong(1));
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	public Date getSysDate() throws InternalUnexpectedException {
		String THIS_METHOD = "[" + THIS_CLASS + "::getSysDate]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}
		final String QUERY = " SELECT CURRENT_TIMESTAMP \n";
		try {
			return namedParameterJdbcTemplate.queryForObject(QUERY, (MapSqlParameterSource)null, Date.class);
		}catch (Throwable t) {
			InternalUnexpectedException e = new InternalUnexpectedException(t, (LogParameter[])null,
					new LogVariable[] {}, QUERY, (MapSqlParameterSource)null);
			logInternalUnexpectedException(e, THIS_METHOD + "");
			throw e;
		}finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}
	}

	public List<DecodificaDTO<String>> queryForDecodificaString(String query, SqlParameterSource parameters) {
		return namedParameterJdbcTemplate.query(query, parameters, (rs) -> {
			List<DecodificaDTO<String>> list = new ArrayList<DecodificaDTO<String>>();
			while (rs.next()) {
				DecodificaDTO<String> d = new DecodificaDTO<String>();
				d.setCodice(rs.getString("CODICE"));
				d.setDescrizione(rs.getString("DESCRIZIONE"));
				d.setId(rs.getString("ID"));
				list.add(d);
			}
			return list;
		});
	}

	public DecodificaDTO<String> queryForSingleDecodificaString(String query, SqlParameterSource parameters) {
		return namedParameterJdbcTemplate.query(query, parameters, (rs) -> {
			if (rs.next()) {
				DecodificaDTO<String> d = new DecodificaDTO<String>();
				d.setCodice(rs.getString("CODICE"));
				d.setDescrizione(rs.getString("DESCRIZIONE"));
				d.setId(rs.getString("ID"));
				return d;
			}
			return null;
		});
	}

	public List<DecodificaDTO<Long>> queryForDecodificaLong(String query, SqlParameterSource parameters) {
		return namedParameterJdbcTemplate.query(query, parameters, (rs) -> {
			List<DecodificaDTO<Long>> list = new ArrayList<DecodificaDTO<Long>>();
			while (rs.next()) {
				DecodificaDTO<Long> d = new DecodificaDTO<Long>();
				d.setCodice(rs.getString("CODICE"));
				d.setDescrizione(rs.getString("DESCRIZIONE"));
				d.setId(rs.getLong("ID"));
				list.add(d);
			}
			return list;
		});
	}

	public List<DecodificaDTO<Integer>> queryForDecodificaInteger(String query, SqlParameterSource parameters) {
		return namedParameterJdbcTemplate.query(query, parameters, (rs) -> {
			List<DecodificaDTO<Integer>> list = new ArrayList<DecodificaDTO<Integer>>();
			while (rs.next()) {
				DecodificaDTO<Integer> d = new DecodificaDTO<Integer>();
				d.setCodice(rs.getString("CODICE"));
				d.setDescrizione(rs.getString("DESCRIZIONE"));
				d.setId(rs.getInt("ID"));
				list.add(d);
			}
			return list;
		});
	}

	public String queryForString(String query, SqlParameterSource parameters, final String field) {
		return namedParameterJdbcTemplate.query(query, parameters, rs -> {
			String sql = "";
			while (rs.next()) {
				sql = rs.getString(field);
			}
			return sql;
		});
	}

}
