/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.integration;

import java.sql.Types;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import it.csi.idf.idfbo.dto.AllegatoDTO;
import it.csi.idf.idfbo.dto.BoprocLogDTO;
import it.csi.idf.idfbo.dto.IstanzaInfoMailByIstanzaDto;
import it.csi.idf.idfbo.dto.MailConfigDto;
import it.csi.idf.idfbo.dto.MailConfigPk;
import it.csi.idf.idfbo.dto.MtdSelvcolturaliTagliCosmoDTO;
import it.csi.idf.idfbo.dto.MtdTrasfCosmoDTO;
import it.csi.idf.idfbo.dto.MtdVinciIdroCosmoDTO;
import it.csi.idf.idfbo.dto.TipoAllegatoDTO;
import it.csi.idf.idfbo.integration.mapper.IstanzaDaoRowMapper;
import it.csi.idf.idfbo.util.Constants;

@Component
public class IstanzaDAO extends BaseDAO {
	protected static final Logger logger = Logger.getLogger(Constants.LOGGING.LOGGER_NAME + ".integration");
	public final String THIS_CLASS = IstanzaDAO.class.getSimpleName();

	protected IstanzaDaoRowMapper infoMailByIstanzaRowMapper = new IstanzaDaoRowMapper(null,
			IstanzaInfoMailByIstanzaDto.class, this);
	
	

	public boolean testDB() {
		String THIS_METHOD = "[" + THIS_CLASS + "::testDB]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}
		try {

			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			final String QUERY = "    SELECT 'TEST' AS TST FROM DUAL    \n";
			String str = queryForString(QUERY, mapSqlParameterSource, "TST");
			if (StringUtils.isNotBlank(str)) {
				return true;
			}
			return false;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}
	}

	public TipoAllegatoDTO getTipoAllegato() {
		String THIS_METHOD = "[" + THIS_CLASS + "::getTipoAllegato]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SELECT id_tipo_allegato, " + "       descr_tipo_allegato, " + "       fk_origine_allegato, "
				+ "       mtd_ordinamento, " + "       flg_visibile " + "FROM   idf.idf_d_tipo_allegato "
				+ "WHERE  flg_visibile = 1 ");

		// mapSqlParameterSource.addValue("EXT_ID_PROCEDIMENTO", idProcedimento,
		// Types.NUMERIC);
		// mapSqlParameterSource.addValue("CODICE_STAMPA", codiceStampa,
		// Types.VARCHAR);

		try {
			return queryForObject(strBuf.toString(), mapSqlParameterSource, TipoAllegatoDTO.class);
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}

	}

	public List<BoprocLogDTO> getElencoIstanze(int step, int numTentativo, int ambitoIstanza) {
		String THIS_METHOD = "[" + THIS_CLASS + "::getElencoIstanze]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SELECT IC.ID_CNF_BOPROC_LOG, " + "       IC.FK_ISTANZA, " + "       IC.DATA_INIZIO "
				+ "FROM   IDF.IDF_CNF_BOPROC_LOG IC " + "WHERE  IC.DATA_FINE IS NULL "
				+ "AND    IC.FK_AMBITO_ISTANZA = :AMBITO_ISTANZA " + "AND    IC.FK_STEP_BOPROC = :STEP "
				+ "AND    IC.COUNT_TENTATIVO <= :NUM_TENTATIVO " +
				// "LIMIT 1 ");
				"ORDER BY IC.DATA_INIZIO ");

		mapSqlParameterSource.addValue("AMBITO_ISTANZA", ambitoIstanza, Types.NUMERIC);
		mapSqlParameterSource.addValue("STEP", step, Types.NUMERIC);
		mapSqlParameterSource.addValue("NUM_TENTATIVO", numTentativo, Types.NUMERIC);

		try {
			List<BoprocLogDTO> result = queryForList(strBuf.toString(), mapSqlParameterSource, BoprocLogDTO.class);
			if(result!=null)
				logger.debug("RESULT getElencoIstanze SIZE ? "+result.size());
			else
				logger.debug("RESULT getElencoIstanze NULL");
			
			return result;
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}

	}

	public String getValueParameter(String codice, int ambitoIstanza) {
		String THIS_METHOD = "[" + THIS_CLASS + "::getValueParameter]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

		final String QUERY = 
		    " SELECT P.VALORE " + 
		    " FROM IDF.IDF_CNF_PARAM_ACTA P " + 
		    " WHERE P.CODICE = :CODICE " +
		    " AND   P.FK_AMBITO_ISTANZA = :AMBITO_ISTANZA";
		
		mapSqlParameterSource.addValue("CODICE", codice, Types.VARCHAR);
		mapSqlParameterSource.addValue("AMBITO_ISTANZA", ambitoIstanza, Types.NUMERIC);
		try {
			return queryForString(QUERY, mapSqlParameterSource, "VALORE");
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}

	}

	public MtdTrasfCosmoDTO getDatiTrasfCosmo(long idIstanza) {
		String THIS_METHOD = "[" + THIS_CLASS + "::getDatiTrasfCosmo]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SELECT VM.ID, " + "       VM.NR_ISTANZA_FORESTALE, " + "       VM.INTESTATARIO, "
				+ "       VM.COGNOME_DICHIARANTE, " + "       VM.NOME_DICHIARANTE, " + "       VM.SOGGETTO_GESTORE, "
				+ "       VM.SIGLA_PROVINCIA, " + "       VM.RAGIONE_SOCIALE, " + "       VM.PAROLE_CHIAVE_FASCICOLO, "
				+ "       VM.OGGETTO_FASCICOLO " + "FROM   IDF.IDF_V_MTD_TRASF_COSMO VM "
				+ "WHERE  VM.ID = :ID_ISTANZA ");

		mapSqlParameterSource.addValue("ID_ISTANZA", idIstanza, Types.NUMERIC);

		try {
			return queryForObject(strBuf.toString(), mapSqlParameterSource, MtdTrasfCosmoDTO.class);
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}

	}
	
	public MtdVinciIdroCosmoDTO getDatiTrasfCosmoVinvoloIdro(long idIstanza) {
    String THIS_METHOD = "[" + THIS_CLASS + "::getDatiTrasfCosmo]";
    if (logger.isDebugEnabled()) {
      logger.debug(THIS_METHOD + " BEGIN.");
    }

    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

    StringBuffer strBuf = new StringBuffer();
    strBuf.append(
      "SELECT VM.ID_PRATICA, " + 
      "       VM.NUMERO_PRATICA, " + 
      "       VM.DATA_INVIO, " +
      "       VM.ANNO_PRATICA, " + 
      "       VM.CODICE_FISCALE_RICHIEDENTE, " + 
      "       VM.RAGIONE_SOCIALE, " +
      "       VM.PARTITA_IVA_RICHIEDENTE, " + 
      "       VM.INDIRIZZO_PEC_RICHIEDENTE, " + 
      "       VM.COGNOME_RICHIEDENTE, " + 
      "       VM.NOME_RICHIEDENTE, " + 
      "       VM.INDIRIZZO_MAIL_PERSONA_FISICA, " +
      "       VM.SIGLA_PROVINCIA, " +
      "       VM.COMUNE, " +
      "       VM.OGGETTO_LAVORI, " +
      "       VM.SOGGETTO_GESTORE " +
      "FROM   IDF.IDF_V_MTD_VINCIDRO_COSMO VM " + 
      "WHERE  VM.ID_PRATICA = :ID_ISTANZA ");

    mapSqlParameterSource.addValue("ID_ISTANZA", idIstanza, Types.NUMERIC);

    try {
      return queryForObject(strBuf.toString(), mapSqlParameterSource, MtdVinciIdroCosmoDTO.class);
    } catch (RuntimeException e) {
      logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
      throw e;
    } finally {
      if (logger.isDebugEnabled()) {
        logger.debug(THIS_METHOD + " END.");
      }
    }

  }
	public MtdVinciIdroCosmoDTO getDatiTrasfCosmoTagliSelv(long idIstanza) {
	    String THIS_METHOD = "[" + THIS_CLASS + "::getDatiTrasfCosmo]";
	    if (logger.isDebugEnabled()) {
	      logger.debug(THIS_METHOD + " BEGIN.");
	    }

	    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

	    StringBuffer strBuf = new StringBuffer();
	    strBuf.append(
	      "SELECT VM.ID_PRATICA, " + 
	      "       VM.NUMERO_PRATICA, " + 
	      "       VM.DATA_INVIO_PRATICA, " +
	      "       VM.ANNO_PRATICA, " + 
	      "       VM.DENOMINAZIONE_RICHIEDENTE, " + 
	      "       VM.COGNOME_RICHIEDENTE, " + 
	      "       VM.NOME_RICHIEDENTE, " + 
	      "       VM.PG_PARTITA_IVA_RICHIEDENTE, " + 
	      "       VM.PG_CODICE_FISCALE_RICHIEDENTE, " + 
	      "       VM.PG_INDIRIZZO_PEC_RICHIEDENTE, " + 
	      "       VM.PF_CODICE_FISCALE_RICHIEDENTE, " + 
	      "       VM.PF_INDIRIZZO_MAIL, " +
	      "       VM.SIGLA_PROVINCIA, " +
	      "       VM.COMUNE " +
	      "FROM   IDF.IDF_V_MTD_INT_SELV_COSMO VM " + 
	      "WHERE  VM.ID_PRATICA = :ID_ISTANZA ");

	    mapSqlParameterSource.addValue("ID_ISTANZA", idIstanza, Types.NUMERIC);

	    try {
	      return queryForObject(strBuf.toString(), mapSqlParameterSource, MtdVinciIdroCosmoDTO.class);
	    } catch (RuntimeException e) {
	      logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
	      throw e;
	    } finally {
	      if (logger.isDebugEnabled()) {
	        logger.debug(THIS_METHOD + " END.");
	      }
	    }

	  }
	
	//metodo customizzato
	public MtdSelvcolturaliTagliCosmoDTO getDatiTrasfCosmoSelvicolturaliTagli(long idIstanza) {
	    String THIS_METHOD = "[" + THIS_CLASS + "::getDatiTrasfCosmo]";
	    if (logger.isDebugEnabled()) {
	      logger.debug(THIS_METHOD + " BEGIN.");
	    }

	    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

	    StringBuffer strBuf = new StringBuffer();
	    strBuf.append(
	      "SELECT VM.ID_PRATICA, " + 
	      "       VM.NUMERO_PRATICA, " + 
	      "       VM.DATA_INVIO_PRATICA, " +
	      "       VM.ANNO_PRATICA, " + 
	      "       VM.DENOMINAZIONE_RICHIEDENTE, " + 
	      "       VM.COGNOME_RICHIEDENTE, " +
	      "       VM.NOME_RICHIEDENTE, " + 
	      "       VM.PG_PARTITA_IVA_RICHIEDENTE, " + 
	      "       VM.PG_CODICE_FISCALE_RICHIEDENTE, " + 
	      "       VM.PG_INDIRIZZO_PEC_RICHIEDENTE, " + 
	      "       VM.PF_CODICE_FISCALE_RICHIEDENTE, " +
	      "       VM.PF_INDIRIZZO_MAIL, " +
	      "       VM.SIGLA_PROVINCIA, " +
	      "       VM.COMUNE " +
	     // "       VM.SOGGETTO_GESTORE " +
	      "FROM   IDF.IDF_V_MTD_INT_SELV_COSMO VM " + 
	      "WHERE  VM.ID_PRATICA = :ID_ISTANZA ");

	    mapSqlParameterSource.addValue("ID_ISTANZA", idIstanza, Types.NUMERIC);

	    try {
	      return queryForObject(strBuf.toString(), mapSqlParameterSource, MtdSelvcolturaliTagliCosmoDTO.class);
	    } catch (RuntimeException e) {
	      logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
	      throw e;
	    } finally {
	      if (logger.isDebugEnabled()) {
	        logger.debug(THIS_METHOD + " END.");
	      }
	    }

	  }
	
	
	

	public void insertBoProcLog(String idIstanza, int step, int ambitoIstanza) {

		String THIS_METHOD = "[" + THIS_CLASS + "::insertBoProcLog]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		final String INSERT = " INSERT INTO IDF_CNF_BOPROC_LOG ( " + "     ID_CNF_BOPROC_LOG, " + "     FK_ISTANZA, "
				+ "     FK_AMBITO_ISTANZA, " + "     DATA_INIZIO, " + "     FK_STEP_BOPROC, " + "     COUNT_TENTATIVO "
				+ " ) VALUES ( " + "     :ID_CNF_BOPROC_LOG, " + "     :FK_ISTANZA, " + "     :FK_AMBITO_ISTANZA, "
				+ "     DATE_TRUNC('second', NOW()), " + "     :FK_STEP_BOPROC, " + "     :COUNT_TENTATIVO " + " ) ";

		try {
			MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
			mapParameterSource.addValue("ID_CNF_BOPROC_LOG", getNextSequenceValue("seq_idf_cnf_boproc_log"),
					Types.NUMERIC);
			mapParameterSource.addValue("FK_ISTANZA", idIstanza, Types.VARCHAR);
			mapParameterSource.addValue("FK_AMBITO_ISTANZA", ambitoIstanza, Types.NUMERIC);
			mapParameterSource.addValue("FK_STEP_BOPROC", step, Types.NUMERIC);
			mapParameterSource.addValue("COUNT_TENTATIVO", 0, Types.NUMERIC);

			namedParameterJdbcTemplate.update(INSERT, mapParameterSource);
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}
	}

	public void storicizzaBoprocLog(String idIstanza, String json) {
		String THIS_METHOD = "[" + THIS_CLASS + "::storicizzaBoprocLog]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		StringBuffer strBuf = new StringBuffer();
		strBuf.append(
				"UPDATE IDF_CNF_BOPROC_LOG " + " SET DATA_FINE = DATE_TRUNC('second', NOW()), " + "     ESITO = 'OK' ");
		if (json != null) {
			strBuf.append("     ,JSON_BOPROC = to_json(:JSON_BOPROC) ");
		}
		strBuf.append(" WHERE FK_ISTANZA = :FK_ISTANZA " + " AND   DATA_FINE IS NULL ");

		try {
			MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
			if (json != null) {
				mapParameterSource.addValue("JSON_BOPROC", json, Types.OTHER);
			}
			mapParameterSource.addValue("FK_ISTANZA", idIstanza, Types.VARCHAR);

			namedParameterJdbcTemplate.update(strBuf.toString(), mapParameterSource);
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}
	}
	
	public void storicizzaBoprocLogNoData(String idIstanza, String json) {
    String THIS_METHOD = "[" + THIS_CLASS + "::storicizzaBoprocLog]";
    if (logger.isDebugEnabled()) {
      logger.debug(THIS_METHOD + " BEGIN.");
    }

    StringBuffer strBuf = new StringBuffer();
    strBuf.append(
        "UPDATE IDF_CNF_BOPROC_LOG " + " SET ESITO = 'OK' ");
    if (json != null) {
      strBuf.append("     ,JSON_BOPROC = to_json(:JSON_BOPROC) ");
    }
    strBuf.append(" WHERE FK_ISTANZA = :FK_ISTANZA AND DATA_FINE IS NULL ");

    try {
      MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
      if (json != null) {
        mapParameterSource.addValue("JSON_BOPROC", json, Types.OTHER);
      }
      mapParameterSource.addValue("FK_ISTANZA", idIstanza, Types.VARCHAR);

      namedParameterJdbcTemplate.update(strBuf.toString(), mapParameterSource);
    } catch (RuntimeException e) {
      logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
      throw e;
    } finally {
      if (logger.isDebugEnabled()) {
        logger.debug(THIS_METHOD + " END.");
      }
    }
  }
	
	public void chiusuraPratica(String idIstanza) {
    String THIS_METHOD = "[" + THIS_CLASS + "::storicizzaBoprocLog]";
    if (logger.isDebugEnabled()) {
      logger.debug(THIS_METHOD + " BEGIN.");
    }

    StringBuffer strBuf = new StringBuffer();
    strBuf.append(
        "UPDATE IDF_CNF_BOPROC_LOG " + " SET DATA_FINE = DATE_TRUNC('second', NOW()) " +
        " WHERE FK_ISTANZA = :FK_ISTANZA AND DATA_FINE IS NULL ");

    try {
      MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();      
      mapParameterSource.addValue("FK_ISTANZA", idIstanza, Types.VARCHAR);

      namedParameterJdbcTemplate.update(strBuf.toString(), mapParameterSource);
    } catch (RuntimeException e) {
      logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
      throw e;
    } finally {
      if (logger.isDebugEnabled()) {
        logger.debug(THIS_METHOD + " END.");
      }
    }
  }
	
	public void chiusuraPraticaJson(String idIstanza, String json) {
    String THIS_METHOD = "[" + THIS_CLASS + "::storicizzaBoprocLog]";
    if (logger.isDebugEnabled()) {
      logger.debug(THIS_METHOD + " BEGIN.");
    }

    StringBuffer strBuf = new StringBuffer();
    strBuf.append(
        "UPDATE IDF_CNF_BOPROC_LOG " + 
        " SET DATA_FINE = DATE_TRUNC('second', NOW()), " +
        "     JSON_BOPROC = to_json(:JSON_BOPROC) " +
        " WHERE FK_ISTANZA = :FK_ISTANZA AND DATA_FINE IS NULL ");

    try {
      MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
      mapParameterSource.addValue("JSON_BOPROC", json, Types.OTHER);
      mapParameterSource.addValue("FK_ISTANZA", idIstanza, Types.VARCHAR);

      namedParameterJdbcTemplate.update(strBuf.toString(), mapParameterSource);
    } catch (RuntimeException e) {
      logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
      throw e;
    } finally {
      if (logger.isDebugEnabled()) {
        logger.debug(THIS_METHOD + " END.");
      }
    }
  }

	public int updateProtocollo(long idIstanza, String numeroProtocollo, String dataProtocollo) {
		String THIS_METHOD = "[" + THIS_CLASS + "::storicizzaBoprocLog]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		int result = 0;
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(
				"UPDATE IDF_T_ISTANZA_FOREST " + " SET DATA_PROTOCOLLO = TO_DATE(:DATA_PROTOCOLLO,'DD/MM/YYYY'), "
						+ "     NR_PROTOCOLLO = :NR_PROTOCOLLO " + " WHERE ID_ISTANZA_INTERVENTO = :FK_ISTANZA "); // to-do

		try {
			MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();

			mapParameterSource.addValue("DATA_PROTOCOLLO", dataProtocollo, Types.VARCHAR);
			mapParameterSource.addValue("NR_PROTOCOLLO", numeroProtocollo, Types.VARCHAR);
			mapParameterSource.addValue("FK_ISTANZA", idIstanza, Types.NUMERIC);

			result = namedParameterJdbcTemplate.update(strBuf.toString(), mapParameterSource);
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}
		
		return result;
	}

	public Long getCountErrore(String idIstanza) {
		String THIS_METHOD = "[" + THIS_CLASS + "::getCountErrore]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

		final String QUERY = " SELECT COUNT_TENTATIVO " + " FROM IDF_CNF_BOPROC_LOG "
				+ " WHERE FK_ISTANZA = :FK_ISTANZA AND DATA_FINE IS NULL ";

		mapSqlParameterSource.addValue("FK_ISTANZA", idIstanza, Types.VARCHAR);
		try {
			return queryForLong(QUERY, mapSqlParameterSource);
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}

	}

	public void updateBoprocLog(String idIstanza, int countTentativo, Integer codErrore, String noteErrore, String json) {
		String THIS_METHOD = "[" + THIS_CLASS + "::updateBoprocLog]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		StringBuffer strBuf = new StringBuffer();
		strBuf.append(
				"UPDATE IDF_CNF_BOPROC_LOG " + " SET ESITO = 'KO', " + "     NOTE_ERRORE_BOPROC = :NOTE_ERRORE_BOPROC, "
						+ "     COD_ERRORE_BOPROC = :COD_ERRORE_BOPROC, " + "     COUNT_TENTATIVO = :COUNT_TENTATIVO ");
		if (json != null) {
			strBuf.append("     ,JSON_BOPROC = to_json(:JSON_BOPROC) ");
		}
		strBuf.append(" WHERE FK_ISTANZA = :FK_ISTANZA AND DATA_FINE IS NULL ");

		try {
			MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
			mapParameterSource.addValue("NOTE_ERRORE_BOPROC", noteErrore, Types.VARCHAR);
			mapParameterSource.addValue("COD_ERRORE_BOPROC", codErrore, Types.NUMERIC);
			mapParameterSource.addValue("COUNT_TENTATIVO", countTentativo, Types.NUMERIC);
			mapParameterSource.addValue("FK_ISTANZA", idIstanza, Types.VARCHAR);
			if (json != null) {
				mapParameterSource.addValue("JSON_BOPROC", json, Types.OTHER);
			}

			namedParameterJdbcTemplate.update(strBuf.toString(), mapParameterSource);
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}
	}

	public List<AllegatoDTO> getElencoAllegati(long idIstanza, Integer inviato) {
		String THIS_METHOD = "[" + THIS_CLASS + "::getElencoAllegati]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SELECT ITDA.ID_DOCUMENTO_ALLEGATO AS ID, " + "       ITDA.FK_INTERVENTO AS ID_ISTANZA, "
				+ "       ITDA.FK_TIPO_ALLEGATO AS ID_TIPO_ALLEGATO, "
				+ "       IDTA.DESCR_TIPO_ALLEGATO AS DESCRIZIONE, " +
				// " IDTA.FLG_DOCUMENTO_PADRE, " +
				"CASE WHEN IDTA.FLG_DOCUMENTO_PADRE = 1 THEN NULL "
				+ "     WHEN IDTA.FLG_DOCUMENTO_PADRE = 0 THEN TAB1.ID_DOCUMENTO_ALLEGATO " + "END AS ID_PADRE, "
				+ "CASE WHEN TAB1.FK_TIPO_ALLEGATO  = 2 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_digi') "
				+ "     WHEN TAB1.FK_TIPO_ALLEGATO  = 3  AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_auto') "
				+ "     ELSE IDTA.CODICE_COSMO " + "END AS CODICE_COSMO, " + "       ITDA.NOME_ALLEGATO AS TITOLO, "
				+ "       ITDA.UID_INDEX, " + "       ITDA.UPLOADUUID_COSMO, " + "       ITDA.FLG_INVIO_COSMO "
				+ "FROM   IDF_T_DOCUMENTO_ALLEGATO ITDA "
				+ "  JOIN IDF_D_TIPO_ALLEGATO IDTA ON ITDA.FK_TIPO_ALLEGATO = IDTA.ID_TIPO_ALLEGATO " + "  LEFT JOIN ( "
				+ "      SELECT ITDA1.ID_DOCUMENTO_ALLEGATO, " + "             ITDA1.FK_TIPO_ALLEGATO, "
				+ "             ITDA1.FK_INTERVENTO " + "      FROM   IDF_T_DOCUMENTO_ALLEGATO ITDA1 "
				+ "        JOIN IDF_D_TIPO_ALLEGATO IDTA1 ON ITDA1.FK_TIPO_ALLEGATO = IDTA1.ID_TIPO_ALLEGATO "
				+ "      WHERE IDTA1.FLG_DOCUMENTO_PADRE = 1) AS TAB1 "
				+ "       ON ITDA.FK_INTERVENTO  = TAB1.FK_INTERVENTO " + "WHERE ITDA.FK_INTERVENTO = :FK_ISTANZA AND IDTA.FLG_VISIBILE = 1 ");
		if (inviato != null) {
			strBuf.append("AND   ITDA.FLG_INVIO_COSMO = :FLG_INVIO_COSMO ");
		}
		strBuf.append("ORDER BY ID_PADRE NULLS FIRST ");

		mapSqlParameterSource.addValue("FK_ISTANZA", idIstanza, Types.NUMERIC);
		if (inviato != null) {
			mapSqlParameterSource.addValue("FLG_INVIO_COSMO", inviato, Types.NUMERIC);
		}

		try {
			return queryForList(strBuf.toString(), mapSqlParameterSource, AllegatoDTO.class);
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}

	}
	
	
	public List<AllegatoDTO> getElencoAllegatiVincIdro(long idIstanza, Integer inviato) {
    String THIS_METHOD = "[" + THIS_CLASS + "::getElencoAllegati]";
    if (logger.isDebugEnabled()) {
      logger.debug(THIS_METHOD + " BEGIN.");
    }

    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

    StringBuffer strBuf = new StringBuffer();
    strBuf.append(
      "SELECT DISTINCT ITDA.ID_DOCUMENTO_ALLEGATO AS ID, " +
      "       ITDA.FK_INTERVENTO AS ID_ISTANZA, " +
      "       ITDA.FK_TIPO_ALLEGATO AS ID_TIPO_ALLEGATO, " +
      "       IDTA.DESCR_TIPO_ALLEGATO AS DESCRIZIONE, " +
      "       IDTA.FLG_DOCUMENTO_PADRE, " +
      "       CASE WHEN IDTA.FLG_DOCUMENTO_PADRE = 1 THEN NULL " +
      "            WHEN IDTA.FLG_DOCUMENTO_PADRE = 0 THEN TAB1.ID_DOCUMENTO_ALLEGATO " +
      "       END AS ID_PADRE, " +
      "       CASE WHEN TAB1.FK_TIPO_ALLEGATO  = 22 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_digi') " +
      "            WHEN TAB1.FK_TIPO_ALLEGATO  = 23  AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_auto') " +
      "            ELSE IDTA.CODICE_COSMO " +
      "       END AS CODICE_COSMO, " +
      "       ITDA.NOME_ALLEGATO AS TITOLO, " +
      "       ITDA.DIMENSIONE_ALLEGATO_KB, " +
      "       ITDA.UID_INDEX, " +
      "       ITDA.FLG_INVIO_COSMO, " +
      "       ITDA.FLG_INVIO_ACTA, " +
      "       ITDA.UPLOADUUID_COSMO " +
      "FROM   IDF_T_DOCUMENTO_ALLEGATO ITDA " + 
      "       JOIN IDF_D_TIPO_ALLEGATO IDTA ON ITDA.FK_TIPO_ALLEGATO = IDTA.ID_TIPO_ALLEGATO  " +
      "       LEFT JOIN (SELECT ITDA1.ID_DOCUMENTO_ALLEGATO, " +
      "                         ITDA1.FK_TIPO_ALLEGATO, " +
      "                         ITDA1.FK_INTERVENTO " +
      "                  FROM   IDF_T_DOCUMENTO_ALLEGATO ITDA1 " +
      "                  JOIN IDF_D_TIPO_ALLEGATO IDTA1 ON ITDA1.FK_TIPO_ALLEGATO = IDTA1.ID_TIPO_ALLEGATO " +
      "                  WHERE IDTA1.FLG_DOCUMENTO_PADRE = 1) AS TAB1 " +
      "        ON ITDA.FK_INTERVENTO  = TAB1.FK_INTERVENTO " +
      "WHERE ITDA.FK_INTERVENTO = :FK_ISTANZA AND IDTA.FLG_VISIBILE = 1 ");

    if (inviato != null) {
      strBuf.append("AND   ITDA.FLG_INVIO_COSMO = :FLG_INVIO_COSMO ");
    }
    strBuf.append("ORDER BY ID_PADRE NULLS FIRST ");

    mapSqlParameterSource.addValue("FK_ISTANZA", idIstanza, Types.NUMERIC);
    if (inviato != null) {
      mapSqlParameterSource.addValue("FLG_INVIO_COSMO", inviato, Types.NUMERIC);
    }

    try {
      return queryForList(strBuf.toString(), mapSqlParameterSource, AllegatoDTO.class);
    } catch (RuntimeException e) {
      logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
      throw e;
    } finally {
      if (logger.isDebugEnabled()) {
        logger.debug(THIS_METHOD + " END.");
      }
    }

  }
	
	public List<AllegatoDTO> getElencoAllegatiTagliSelvStep2(long idIstanza, Integer inviato) {
	    String THIS_METHOD = "[" + THIS_CLASS + "::getElencoAllegati]";
	    if (logger.isDebugEnabled()) {
	      logger.debug(THIS_METHOD + " BEGIN.");
	    }

	    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

	    StringBuffer strBuf = new StringBuffer();
	    strBuf.append(
	      "SELECT DISTINCT " +
	      " 	  ITDA.ID_DOCUMENTO_ALLEGATO AS ID, " +
	      "       ITDA.FK_INTERVENTO AS ID_ISTANZA, " +
	      "       ITDA.FK_TIPO_ALLEGATO AS ID_TIPO_ALLEGATO, " +
	      "       IDTA.DESCR_TIPO_ALLEGATO AS DESCRIZIONE, " +
	      "       IDTA.FLG_DOCUMENTO_PADRE, " +
	      "       CASE WHEN IDTA.FLG_DOCUMENTO_PADRE = 1 THEN NULL " +
	      "            WHEN IDTA.FLG_DOCUMENTO_PADRE = 0 THEN TAB1.ID_DOCUMENTO_ALLEGATO " +
	      "       END AS ID_PADRE, " +
	      "       CASE WHEN TAB1.FK_TIPO_ALLEGATO  = 25 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_comunicazione_semplice') " +
	      "            WHEN TAB1.FK_TIPO_ALLEGATO  = 36 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_comunicazione_semplice') " +
	      "            WHEN TAB1.FK_TIPO_ALLEGATO  = 26 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_autorizzazione_progetto') " +
	      "            WHEN TAB1.FK_TIPO_ALLEGATO  = 37 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_autorizzazione_progetto') " +
	      "            WHEN TAB1.FK_TIPO_ALLEGATO  = 35 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_drel') " +
	      "            ELSE IDTA.CODICE_COSMO " +
	      "       END AS CODICE_COSMO, " +
          "       ITDA.NOME_ALLEGATO AS TITOLO, " +	     
	      "            ITDA.DIMENSIONE_ALLEGATO_KB, " +
	      "            ITDA.UID_INDEX, " +
	      "            ITDA.FLG_INVIO_COSMO, " +
	      "            ITDA.FLG_INVIO_ACTA, " +
	      "            ITDA.UPLOADUUID_COSMO " +
	      "FROM   IDF_T_DOCUMENTO_ALLEGATO ITDA " + 
	      "       JOIN IDF_D_TIPO_ALLEGATO IDTA ON ITDA.FK_TIPO_ALLEGATO = IDTA.ID_TIPO_ALLEGATO  " +
	      "       LEFT JOIN (SELECT ITDA1.ID_DOCUMENTO_ALLEGATO, " +
	      "                         ITDA1.FK_TIPO_ALLEGATO, " +
	      "                         ITDA1.FK_INTERVENTO " +
	      "                  FROM   IDF_T_DOCUMENTO_ALLEGATO ITDA1 " +
	      "                  JOIN 	IDF_D_TIPO_ALLEGATO IDTA1 ON ITDA1.FK_TIPO_ALLEGATO = IDTA1.ID_TIPO_ALLEGATO " +
	      "                  WHERE 	IDTA1.FLG_DOCUMENTO_PADRE = 1) AS TAB1 ON ITDA.FK_INTERVENTO = TAB1.FK_INTERVENTO " +
	      //"WHERE ITDA.FK_INTERVENTO = :FK_ISTANZA AND IDTA.FLG_VISIBILE = 1 ");
	      "WHERE ITDA.FK_INTERVENTO = :FK_ISTANZA ");

	    if (inviato != null) {
	      strBuf.append(" AND   ITDA.FLG_INVIO_COSMO = :FLG_INVIO_COSMO ");
	    }
	    strBuf.append("ORDER BY ID_PADRE NULLS FIRST ");

	    mapSqlParameterSource.addValue("FK_ISTANZA", idIstanza, Types.NUMERIC);
	    if (inviato != null) {
	      mapSqlParameterSource.addValue("FLG_INVIO_COSMO", inviato, Types.NUMERIC);
	    }

	    try {
	      return queryForList(strBuf.toString(), mapSqlParameterSource, AllegatoDTO.class);
	    } catch (RuntimeException e) {
	      logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
	      throw e;
	    } finally {
	      if (logger.isDebugEnabled()) {
	        logger.debug(THIS_METHOD + " END.");
	      }
	    }

	  }

	
	
	
	
	public List<AllegatoDTO> getElencoAllegatiTagliSelv(long idIstanza, Integer inviato) {
	    String THIS_METHOD = "[" + THIS_CLASS + "::getElencoAllegati]";
	    if (logger.isDebugEnabled()) {
	      logger.debug(THIS_METHOD + " BEGIN.");
	    }

	    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();

	    StringBuffer strBuf = new StringBuffer();
	    strBuf.append(
	      "SELECT DISTINCT " +
	      " 	  ITDA.ID_DOCUMENTO_ALLEGATO AS ID, " +
	      "       ITDA.FK_INTERVENTO AS ID_ISTANZA, " +
	      "       ITDA.FK_TIPO_ALLEGATO AS ID_TIPO_ALLEGATO, " +
	      "       IDTA.DESCR_TIPO_ALLEGATO AS DESCRIZIONE, " +
	      "       IDTA.FLG_DOCUMENTO_PADRE, " +
	      "       CASE WHEN IDTA.FLG_DOCUMENTO_PADRE = 1 THEN NULL " +
	      "            WHEN IDTA.FLG_DOCUMENTO_PADRE = 0 THEN TAB1.ID_DOCUMENTO_ALLEGATO " +
	      "       END AS ID_PADRE, " +
	      "       CASE WHEN TAB1.FK_TIPO_ALLEGATO  = 25 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_comunicazione_semplice') " +
	      "            WHEN TAB1.FK_TIPO_ALLEGATO  = 36 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_comunicazione_semplice') " +
	      "            WHEN TAB1.FK_TIPO_ALLEGATO  = 26 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_autorizzazione_progetto') " +
	      "            WHEN TAB1.FK_TIPO_ALLEGATO  = 37 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_autorizzazione_progetto') " +
	      "            WHEN TAB1.FK_TIPO_ALLEGATO  = 35 AND IDTA.FLG_DOCUMENTO_PADRE = 0 THEN CONCAT (IDTA.CODICE_COSMO,'_drel') " +
	      "            ELSE IDTA.CODICE_COSMO " +
	      "       END AS CODICE_COSMO, " +
	      "       CASE WHEN ITDA.FK_TIPO_ALLEGATO = 25 THEN 'Comunicazione semplice' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 26 THEN 'Richiesta di autorizzazione con progetto' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 27 THEN 'Cartografia - area di intervento' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 29 THEN 'Progetto di intervento' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 30 THEN 'Elaborati progettuali strade e piste forestali' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 31 THEN 'Verbale di sopralluogo preventivo' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 32 THEN 'Allegato generico' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 33 THEN 'Allegato generico' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 34 THEN 'Piedilista' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 35 THEN 'Dichiarazione regolare esecuzione lavori (Drel)' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 36 THEN 'Comunicazione semplice' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 37 THEN 'Richiesta di autorizzazione con progetto' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 38 THEN 'Scansione documento di identita' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 160 THEN 'Cartografia - area di intervento' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 161 THEN 'Progetto di intervento' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 162 THEN 'Elaborati progettuali strade e piste forestali' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 163 THEN 'Verbale di sopralluogo preventivo' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 164 THEN 'Allegato generico' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 165 THEN 'Allegato generico' "+
	      "            WHEN ITDA.FK_TIPO_ALLEGATO = 166 THEN 'Piedilista' "+
	      "		  END AS TITOLO,"+
	      "            ITDA.DIMENSIONE_ALLEGATO_KB, " +
	      "            ITDA.UID_INDEX, " +
	      "            ITDA.FLG_INVIO_COSMO, " +
	      "            ITDA.FLG_INVIO_ACTA, " +
	      "            ITDA.UPLOADUUID_COSMO " +
	      "FROM   IDF_T_DOCUMENTO_ALLEGATO ITDA " + 
	      "       JOIN IDF_D_TIPO_ALLEGATO IDTA ON ITDA.FK_TIPO_ALLEGATO = IDTA.ID_TIPO_ALLEGATO  " +
	      "       LEFT JOIN (SELECT ITDA1.ID_DOCUMENTO_ALLEGATO, " +
	      "                         ITDA1.FK_TIPO_ALLEGATO, " +
	      "                         ITDA1.FK_INTERVENTO " +
	      "                  FROM   IDF_T_DOCUMENTO_ALLEGATO ITDA1 " +
	      "                  JOIN 	IDF_D_TIPO_ALLEGATO IDTA1 ON ITDA1.FK_TIPO_ALLEGATO = IDTA1.ID_TIPO_ALLEGATO " +
	      "                  WHERE 	IDTA1.FLG_DOCUMENTO_PADRE = 1) AS TAB1 ON ITDA.FK_INTERVENTO = TAB1.FK_INTERVENTO " +
	      //"WHERE ITDA.FK_INTERVENTO = :FK_ISTANZA AND   IDTA.FLG_VISIBILE = 1 ");
	      "WHERE ITDA.FK_INTERVENTO = :FK_ISTANZA");

	    if (inviato != null) {
	      strBuf.append(" AND   ITDA.FLG_INVIO_COSMO = :FLG_INVIO_COSMO ");
	    }
	    strBuf.append("ORDER BY ID_PADRE NULLS FIRST ");

	    mapSqlParameterSource.addValue("FK_ISTANZA", idIstanza, Types.NUMERIC);
	    if (inviato != null) {
	      mapSqlParameterSource.addValue("FLG_INVIO_COSMO", inviato, Types.NUMERIC);
	    }

	    try {
	      return queryForList(strBuf.toString(), mapSqlParameterSource, AllegatoDTO.class);
	    } catch (RuntimeException e) {
	      logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
	      throw e;
	    } finally {
	      if (logger.isDebugEnabled()) {
	        logger.debug(THIS_METHOD + " END.");
	      }
	    }

	  }

	public void updateDocumentoAllegato(long idDocumentoAllegato, String uuidCosmo) {
		String THIS_METHOD = "[" + THIS_CLASS + "::updateBoprocLog]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		final String UPDATE = "UPDATE IDF_T_DOCUMENTO_ALLEGATO " + " SET FLG_INVIO_COSMO = 1, "
				+ "     UPLOADUUID_COSMO = :UPLOADUUID_COSMO "
				+ " WHERE ID_DOCUMENTO_ALLEGATO = :ID_DOCUMENTO_ALLEGATO ";

		try {
			MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
			mapParameterSource.addValue("UPLOADUUID_COSMO", uuidCosmo, Types.VARCHAR);
			mapParameterSource.addValue("ID_DOCUMENTO_ALLEGATO", idDocumentoAllegato, Types.NUMERIC);

			namedParameterJdbcTemplate.update(UPDATE, mapParameterSource);
		} catch (RuntimeException e) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
			throw e;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}
	}
	
	public void updateStatoDocumentoAllegato(long idDocumentoAllegato, int statoInvioCosmo) {
    String THIS_METHOD = "[" + THIS_CLASS + "::updateBoprocLog]";
    if (logger.isDebugEnabled()) {
      logger.debug(THIS_METHOD + " BEGIN.");
    }

    final String UPDATE = "UPDATE IDF_T_DOCUMENTO_ALLEGATO " + " SET FLG_INVIO_COSMO = :STATO_INVIO_COSMO "
        + " WHERE ID_DOCUMENTO_ALLEGATO = :ID_DOCUMENTO_ALLEGATO ";

    try {
      MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
      mapParameterSource.addValue("STATO_INVIO_COSMO", statoInvioCosmo, Types.NUMERIC);
      mapParameterSource.addValue("ID_DOCUMENTO_ALLEGATO", idDocumentoAllegato, Types.NUMERIC);

      namedParameterJdbcTemplate.update(UPDATE, mapParameterSource);
    } catch (RuntimeException e) {
      logger.error(THIS_METHOD + "Errore nel richiamo del metodo", e);
      throw e;
    } finally {
      if (logger.isDebugEnabled()) {
        logger.debug(THIS_METHOD + " END.");
      }
    }
  }
	
	@SuppressWarnings("unchecked")
	public IstanzaInfoMailByIstanzaDto findInfoMailByIstanza(Long idIstanza) {
		String THIS_METHOD = "[" + THIS_CLASS + "::findInfoMailByIstanza]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT itif.ID_ISTANZA_INTERVENTO, itif.NR_ISTANZA_FORESTALE, its2.E_MAIL as mail_richiedente, its.ID_SOGGETTO as is_soggetto_gestore, its.DENOMINAZIONE as denominazione_gestore, its.NR_TELEFONICO as telefono_gestore, its.E_MAIL as mail_gestore, its.PEC as pec_gestore, ");
		sql.append(" CASE WHEN itif.fk_tipo_istanza = 2 THEN 'Comunicazione semplice' ELSE 'Autorizzazione con progetto' END as tipo_istanza ");

		sql.append(
				" FROM IDF_T_ISTANZA_FOREST itif, IDF_T_SOGGETTO its, IDF_R_SOGGETTO_INTERVENTO irsi, IDF_T_SOGGETTO its2");

		sql.append(" WHERE ");

		sql.append(
				"itif.FK_SOGG_SETTORE_REGIONALE = its.ID_SOGGETTO AND itif.ID_ISTANZA_INTERVENTO = irsi.ID_INTERVENTO AND irsi.ID_SOGGETTO = its2.ID_SOGGETTO");

		sql.append(" AND ");

		sql.append("irsi.id_tipo_soggetto = 1 and itif.id_istanza_intervento = :idIstanza");

		MapSqlParameterSource mapParameterSource = new MapSqlParameterSource();
		mapParameterSource.addValue("idIstanza", idIstanza, Types.NUMERIC);

		List<IstanzaInfoMailByIstanzaDto> list =  queryForList(sql.toString(), mapParameterSource, infoMailByIstanzaRowMapper);
		return list.isEmpty() ? null : list.get(0);
	}
	
}
