/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.integration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class BaseDAO
{
  NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  protected final int                  PASSO      = 900;

  
  @Autowired
  public void setDataSource(DataSource dataSource)
  {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  protected <T> T queryForNullableObject(String query, MapSqlParameterSource mapSqlParameterSource, Class<T> clazz)
  {
    try
    {
      return namedParameterJdbcTemplate.queryForObject(query, mapSqlParameterSource, clazz);
    }
    catch (Exception e)
    {
      return null;
    }
  }

  public <T> T queryForObject(String query, SqlParameterSource parameters, Class<T> objClass, ResultSetExtractor<T> re)
  {
    return namedParameterJdbcTemplate.query(query, parameters, re);
  }

  public <T> T queryForObject(String query, SqlParameterSource parameters, Class<T> objClass)
  {
    ResultSetExtractor<T> re = new GenericObjectExtractor<T>(objClass);
    return namedParameterJdbcTemplate.query(query, parameters, re);
  }

  public String queryForString(String query, SqlParameterSource parameters, final String field)
  {
    return namedParameterJdbcTemplate.query(query, parameters, new ResultSetExtractor<String>()
    {
      @Override
      public String extractData(ResultSet rs) throws SQLException, DataAccessException
      {
        String sql = "";
        while (rs.next())
        {
          sql = rs.getString(field);
        }
        return sql;
      }
    });
  }

  /**
   * Assicurarsi che i nomi dei campi del DTO siano UGUALI ai campi (o alias)
   * richiesti nella query ma in camel case senza spazi e punteggiatura,
   * <b>rispettando anche i tipi.</b> (es: "TIPO_RICHIESTA" sul db =>
   * "tipoRichiesta" sul dto)
   */
  public <T> List<T> queryForList(String query, SqlParameterSource parameters, Class<T> objClass)
  {
    ResultSetExtractor<List<T>> re = new GenericListEstractor<T>(objClass);
    return namedParameterJdbcTemplate.query(query, parameters, re);
  }
  
  
  public String getInCondition(String campo, List<?> vId)
  {
    int cicli = vId.size() / PASSO;
    if (vId.size() % PASSO != 0)
      cicli++;

    StringBuffer condition = new StringBuffer(" AND ( ");
    for (int j = 0; j < cicli; j++)
    {
      if (j != 0)
      {
        condition.append(" OR ");
      }
      boolean primo = true;
      for (int i = j * PASSO; i < ((j + 1) * PASSO) && i < vId.size(); i++)
      {
        if (primo)
        {
          condition.append(" " + campo + " IN (" + getIdFromvId(vId, i));
          primo = false;
        }
        else
        {
          condition.append("," + getIdFromvId(vId, i));
        }
      }
      condition.append(")");
    }
    condition.append(")");

    return condition.toString();

  } 

  public String getInCondition(String campo, Vector<?> vId, boolean andClause)
  {
    int cicli = vId.size() / PASSO;
    if (vId.size() % PASSO != 0)
      cicli++;
    StringBuffer condition = new StringBuffer("  ");

    if (andClause)
      condition.append(" AND ( ");

    for (int j = 0; j < cicli; j++)
    {
      if (j != 0)
      {
        condition.append(" OR ");
      }
      boolean primo = true;
      for (int i = j * PASSO; i < ((j + 1) * PASSO) && i < vId.size(); i++)
      {
        if (primo)
        {
          condition.append(" " + campo + " IN (" + getIdFromvId(vId, i));
          primo = false;
        }
        else
        {
          condition.append("," + getIdFromvId(vId, i));
        }
      }
      condition.append(")");
    }

    if (andClause)
      condition.append(")");

    return condition.toString();

  }

  public String getNotInCondition(String campo, List<?> vId)
  {
    int cicli = vId.size() / PASSO;
    if (vId.size() % PASSO != 0)
      cicli++;

    StringBuffer condition = new StringBuffer(" AND ( ");
    for (int j = 0; j < cicli; j++)
    {
      if (j != 0)
      {
        condition.append(" OR ");
      }
      boolean primo = true;
      for (int i = j * PASSO; i < ((j + 1) * PASSO) && i < vId.size(); i++)
      {
        if (primo)
        {
          condition.append(" " + campo + " NOT IN (" + getIdFromvId(vId, i));
          primo = false;
        }
        else
        {
          condition.append("," + getIdFromvId(vId, i));
        }
      }
      condition.append(")");
    }
    condition.append(")");
    return condition.toString();
  }

  protected String getIdFromvId(List<?> vId, int idx)
  {

    Object o = vId.get(idx);

    if (o instanceof String)
    {
      return "'" + (String) o + "'";
    }
    else
      return o.toString();
  }
  
  public Long queryForLong(String query, MapSqlParameterSource mapParameterSource)
  {
    return namedParameterJdbcTemplate.query(query, mapParameterSource, new ResultSetExtractor<Long>()
    {

      @Override
      public Long extractData(ResultSet rs) throws SQLException, DataAccessException
      {
        if (rs.next())
        {
          return rs.getLong(1);
        }
        else
        {
          return null;
        }
      }

    });
  }

  /*@SuppressWarnings("deprecation")
  public long getNextSequenceValue(String sequenceName)
  {
    String query = " SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
    return namedParameterJdbcTemplate.queryForLong(query, (SqlParameterSource) null);
  }*/
  
  
  public long getNextSequenceValue(String sequenceName)
  {
    String query = " SELECT NEXTVAL('" + sequenceName + "') AS NEXTVAL FROM DUAL";
    return queryForLong(query, null);
  }
  
  public Boolean queryForBoolean(String query, MapSqlParameterSource mapParameterSource)
  {
    return namedParameterJdbcTemplate.query(query, mapParameterSource, new ResultSetExtractor<Boolean>()
    {

      @Override
      public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException
      {
        if (rs.next())
        {
          return rs.getBoolean(1);
        }
        else
        {
          return null;
        }
      }

    });
  }
  
  public <T> List<T> queryForList(String query, SqlParameterSource parameters, RowMapper<T> mapper)
  {
    return namedParameterJdbcTemplate.query(query, parameters, mapper);
  } 
}
