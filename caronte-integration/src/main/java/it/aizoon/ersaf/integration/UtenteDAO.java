package it.aizoon.ersaf.integration;

import java.sql.Types;

import javax.enterprise.inject.Model;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

//@Component
@Model
public class UtenteDAO extends BaseDAO {

  private static final String THIS_CLASS = UtenteDAO.class.getSimpleName();
  
  public String getUtenteById(int idUtente) {

	String query = "SELECT DENOMINAZIONE FROM crnt_utente WHERE ID_UTENTE = :ID_UTENTE";

    MapSqlParameterSource source = new MapSqlParameterSource();
    source.addValue("ID_UTENTE", idUtente, Types.NUMERIC);
    
    return namedParameterJdbcTemplate.query(query, source, 
        rs -> { 
          if (rs.next()) {
            return rs.getString("DENOMINAZIONE");
          } 
          return "";
        });
  }
  
}
