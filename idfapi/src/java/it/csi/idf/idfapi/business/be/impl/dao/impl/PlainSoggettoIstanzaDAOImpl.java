/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import it.csi.idf.idfapi.dto.PlainSoggettoIstanzaTagli;
import it.csi.idf.idfapi.mapper.PlainSoggettoIstanzaTagliMapper;
import it.csi.idf.idfapi.util.AmbitoIstanzaEnum;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.PlainSoggettoIstanzaDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanza;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanzaVincolo;
import it.csi.idf.idfapi.mapper.PlainSoggettoIstanzaMapper;
import it.csi.idf.idfapi.mapper.PlainSoggettoIstanzaVincoloMapper;
import it.csi.idf.idfapi.util.GenericDAO;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;

@Component
public class PlainSoggettoIstanzaDAOImpl extends GenericDAO implements PlainSoggettoIstanzaDAO {
	
	public static Logger logger = Logger.getLogger(PlainSoggettoIstanzaDAOImpl.class);
	
	private static final String ASCENDING = "ASC";
	private static final String DESCENDING = "DESC";
	private static final char ORDER = '-';

	@Override
	public PaginatedList<PlainSoggettoIstanza> getAllIstances(ConfigUtente configUtente, Integer tipoAccreditamento, 
			int ambitoIstanza,int page,	int limit, String sort) {
		StringBuilder sql = new StringBuilder();
		List<Object> parameters = new ArrayList<>();
		
		if(tipoAccreditamento == 2) {
			sql.append("SELECT distinct fi.id_istanza_intervento,fi.nr_istanza_forestale,fi.data_invio,si.descr_stato_istanza, ");
			sql.append("sog.id_soggetto,sog.codice_fiscale,sog.partita_iva,sog.nome,sog.cognome,sog.denominazione, ");
			sql.append("tab_string_comune.string_comune as denominazione_comune, fi.fk_stato_istanza, fi.fk_tipo_istanza ");
			sql.append("FROM idf_t_istanza_forest fi ");
			sql.append("JOIN idf_t_intervento intt ON fi.id_istanza_intervento=intt.id_intervento ");
			sql.append("JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append("JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append("JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append("JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto ");
			sql.append("JOIN idf_d_tipo_istanza ti on fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append("JOIN idf_cnf_delega icd on icd.id_config_utente = cu.id_config_utente ");
			sql.append("and sog.codice_fiscale = icd.cf_delegante and (icd.data_fine is null or icd.data_fine > now())");
			sql.append("LEFT JOIN ( ");
			sql.append("      SELECT zzz.id_intervento, ");
			sql.append("          array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune ");
			sql.append("      FROM ( ");
			sql.append("      SELECT DISTINCT ");
			sql.append("      r_catasto_intervento.id_intervento, comune.denominazione_comune ");
			sql.append("      FROM ");
			sql.append("      idf_r_propcatasto_intervento as r_catasto_intervento ");
			sql.append("      JOIN idf_geo_pl_prop_catasto as catasto ON r_catasto_intervento.idgeo_pl_prop_catasto=catasto.idgeo_pl_prop_catasto ");
			sql.append("      JOIN idf_geo_pl_comune as comune ON catasto.fk_comune=comune.id_comune ");
			sql.append("      ) as zzz ");
			sql.append("      GROUP BY zzz.id_intervento ");
			sql.append("      ) as tab_string_comune ON intt.id_intervento=tab_string_comune.id_intervento ");
			sql.append("WHERE ");
			sql.append("sint.id_tipo_soggetto = 1 ");
			sql.append("AND intt.fk_config_utente_compilatore = ? ");
			sql.append("AND cu.fk_profilo_utente = ? ");
			sql.append("AND ti.fk_ambito_istanza = ? ");
		
//			sql.append("SELECT distinct fi.id_istanza_intervento, fi.nr_istanza_forestale, fi.data_invio, c.denominazione_comune, si.descr_stato_istanza");
//			sql.append(", sog.id_soggetto, sog.codice_fiscale, sog.partita_iva, sog.nome, sog.cognome, sog.denominazione");
//			sql.append(", c.id_comune, c.istat_comune, c.istat_prov, c.denominazione_comune");
//			sql.append(" FROM idf_t_istanza_forest fi");
//			sql.append(" JOIN idf_cnf_config_utente cu ON fi.fk_config_utente = cu.id_config_utente");
//			sql.append(" JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza");
//			sql.append(" JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento");
//			sql.append(" JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto");
//			sql.append(" JOIN idf_geo_pl_comune c ON sog.fk_comune = c.id_comune");
//			sql.append(" JOIN idf_d_tipo_istanza ti on fi.fk_tipo_istanza = id_tipo_istanza");
//			sql.append(" JOIN idf_cnf_delega icd on icd.id_config_utente = cu.id_config_utente and sog.codice_fiscale = icd.cf_delegante");
//			sql.append(" WHERE fi.fk_config_utente = ?  ");	
//			sql.append(" AND cu.fk_profilo_utente = ? AND ti.fk_ambito_istanza = ?");
			
			sql.append(getQuerySortingSegment(sort));
			
			parameters.add(configUtente.getIdConfigUtente());
			parameters.add(configUtente.getFkProfiloUtente());
			parameters.add(ambitoIstanza);
			
			logger.info("getAllIstances sql: " + sql.toString());
			logger.info("getAllIstances sql parameters: " + " - " + configUtente.getFkSoggetto() 
				+ " - " + ProfiloUtenteEnum.CITTADINO.getValue() + " - " + ambitoIstanza);
		}else {
			sql.append("SELECT distinct fi.id_istanza_intervento,fi.nr_istanza_forestale,fi.data_invio,si.descr_stato_istanza, ");
			sql.append("sog.id_soggetto,sog.codice_fiscale,sog.partita_iva,sog.nome,sog.cognome,sog.denominazione, ");
			sql.append("tab_string_comune.string_comune as denominazione_comune, fi.fk_stato_istanza, fi.fk_tipo_istanza ");
			sql.append("FROM idf_t_istanza_forest fi ");
			sql.append("JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento ");
			sql.append("JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append("JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append("JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append("JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto ");
			sql.append("JOIN idf_d_tipo_istanza ti on fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append("LEFT JOIN ( ");
			sql.append("      SELECT zzz.id_intervento, ");
			sql.append("          array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune ");
			sql.append("      FROM ( ");
			sql.append("      SELECT DISTINCT ");
			sql.append("      r_catasto_intervento.id_intervento, comune.denominazione_comune ");
			sql.append("      FROM ");
			sql.append("      idf_r_propcatasto_intervento as r_catasto_intervento ");
			sql.append("      JOIN idf_geo_pl_prop_catasto as catasto ON r_catasto_intervento.idgeo_pl_prop_catasto=catasto.idgeo_pl_prop_catasto ");
			sql.append("      JOIN idf_geo_pl_comune as comune ON catasto.fk_comune=comune.id_comune ");
			sql.append("      ) as zzz ");
			sql.append("      GROUP BY zzz.id_intervento ");
			sql.append("      ) as tab_string_comune ON intt.id_intervento=tab_string_comune.id_intervento ");
			sql.append("WHERE ");
			sql.append("sint.id_tipo_soggetto = 1 ");
			sql.append("AND intt.fk_config_utente_compilatore = ? ");
			sql.append("AND (sog.id_soggetto= ? OR sog.id_soggetto in (SELECT pfg.id_soggetto_pg FROM idf_r_pf_pg pfg WHERE pfg.id_soggetto_pf = ?))");
			sql.append("AND cu.fk_profilo_utente = ? ");
			sql.append("AND ti.fk_ambito_istanza = ? ");
			
			
//			sql.append("SELECT distinct fi.id_istanza_intervento, fi.nr_istanza_forestale, fi.data_invio, c.denominazione_comune, si.descr_stato_istanza");
//			sql.append(", sog.id_soggetto, sog.codice_fiscale, sog.partita_iva, sog.nome, sog.cognome, sog.denominazione");
//			sql.append(", c.id_comune, c.istat_comune, c.istat_prov, c.denominazione_comune");
//			sql.append(" FROM idf_t_istanza_forest fi");
//			sql.append(" JOIN idf_cnf_config_utente cu ON fi.fk_config_utente = cu.id_config_utente");
//			sql.append(" JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza");
//			sql.append(" JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento");
//			sql.append(" JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto");
//			sql.append(" JOIN idf_geo_pl_comune c ON sog.fk_comune = c.id_comune");
//			sql.append(" JOIN idf_d_tipo_istanza ti on fi.fk_tipo_istanza = id_tipo_istanza");
//			sql.append(" WHERE fi.fk_config_utente = ? AND sog.id_soggetto= ? ");
//			sql.append(" AND cu.fk_profilo_utente = ? AND ti.fk_ambito_istanza = ?");	
		
			sql.append(getQuerySortingSegment(sort));
			
			parameters.add(configUtente.getIdConfigUtente());
			parameters.add(configUtente.getFkSoggetto());
			parameters.add(configUtente.getFkSoggetto());
			parameters.add(configUtente.getFkProfiloUtente());
			parameters.add(ambitoIstanza);
			
			logger.info("getAllIstances sql: " + sql.toString());
			logger.info("getAllIstances sql parameters: " + configUtente.getIdConfigUtente()
			+ " - " + configUtente.getFkSoggetto() + " - " + ProfiloUtenteEnum.CITTADINO.getValue()
			+ " - " + ambitoIstanza);
		}

		return super.paginatedList(sql.toString(), parameters, new PlainSoggettoIstanzaMapper(), page, limit);
	}


	@Override
	public PaginatedList<PlainSoggettoIstanzaTagli> getAllIstancesTagli(ConfigUtente configUtente, int page, int limit, String sort) {
		StringBuilder sql = new StringBuilder();
		List<Object> parameters = new ArrayList<>();
		Integer tipoAccreditamento = configUtente.getFkTipoAccreditamento();
		Integer ambitoIstanza = AmbitoIstanzaEnum.TAGLIO_IN_BOSCO.getValue();

		if(tipoAccreditamento == 2) {
			sql.append(" SELECT ");
			sql.append(" 	DISTINCT fi.id_istanza_intervento,fi.nr_istanza_forestale,fi.data_invio,si.descr_stato_istanza, ");
			sql.append(" 	sog.id_soggetto, sog.codice_fiscale, sog.partita_iva,sog.nome,sog.cognome,sog.denominazione,  ");
			sql.append(" 	tab_string_comune.string_comune as string_comune, ");
			sql.append("	intt.id_intervento,  ");
			sql.append("	concat (date_part('year'::text, intt.data_inserimento),'/',fi.nr_istanza_forestale) as nr_istanza,  ");
			sql.append("	ti.descr_dett_tipoistanza,  ");
			sql.append("	concat(sog.codice_fiscale,' - ', sog.cognome,' ', sog.nome, sog.denominazione) as richiedente,  ");
			sql.append("	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ',  ");
			sql.append("		case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento,  ");
			sql.append("	intt.descrizione_intervento, intt.data_inserimento,  ");
			sql.append("	CASE  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 2::numeric THEN 'Verificata'::text::character varying  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying  ");
			sql.append("	    ELSE si.descr_stato_istanza  ");
			sql.append("	END AS stato_istanza, fi.fk_stato_istanza, fi.fk_tipo_istanza, fi.fk_tipo_istanza 	 ");
			sql.append(" FROM idf_t_istanza_forest fi ");
			sql.append(" JOIN idf_d_tipo_istanza ti ON fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append(" JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_t_interv_selvicolturale itis ON itis.id_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append(" JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append(" LEFT JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append(" LEFT JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto ");
			sql.append(" JOIN idf_cnf_delega icd on icd.id_config_utente = cu.id_config_utente ");
			sql.append(" and sog.codice_fiscale = icd.cf_delegante and (icd.data_fine is null or icd.data_fine > now()) ");
			sql.append(" LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento  ");
			sql.append(" LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento ");
			sql.append( "LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento ");
			sql.append(" LEFT JOIN (  ");
			sql.append(" 	 SELECT zzz.id_intervento, array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune  ");
			sql.append(" 	 FROM (  ");
			sql.append(" 		 SELECT DISTINCT r_catasto_intervento.id_intervento, comune.denominazione_comune  ");
			sql.append(" 		 FROM idf_r_propcatasto_intervento as r_catasto_intervento  ");
			sql.append(" 		 JOIN idf_geo_pl_prop_catasto as catasto ON r_catasto_intervento.idgeo_pl_prop_catasto=catasto.idgeo_pl_prop_catasto  ");
			sql.append(" 		 JOIN idf_geo_pl_comune as comune ON catasto.fk_comune=comune.id_comune  ");
			sql.append(" 		 ) as zzz  ");
			sql.append(" 	 GROUP BY zzz.id_intervento ) as tab_string_comune ON intt.id_intervento=tab_string_comune.id_intervento  ");
			sql.append(" WHERE ");
			sql.append(" 	ti.fk_ambito_istanza = 2  AND itis.idgeo_pl_pfa = 0 ");
			sql.append("	AND intt.fk_config_utente_compilatore = ? AND intt.fk_tipo_accreditamento = 2	");
			sql.append(" 	AND (sog.id_soggetto IS NULL OR sint.id_tipo_soggetto = 1)	 ");
			sql.append(" union	 ");
			sql.append(" SELECT ");
			sql.append(" 	DISTINCT fi.id_istanza_intervento,fi.nr_istanza_forestale,fi.data_invio,si.descr_stato_istanza, ");
			sql.append(" 	sog.id_soggetto,sog.codice_fiscale,	sog.partita_iva,sog.nome,sog.cognome,sog.denominazione, ");
			sql.append(" 	tab_string_comune.string_comune AS string_comune, ");
			sql.append("	intt.id_intervento,  ");
			sql.append("	concat (date_part('year'::text, intt.data_inserimento),'/',fi.nr_istanza_forestale) as nr_istanza,  ");
			sql.append("	ti.descr_dett_tipoistanza,  ");
			sql.append("	concat(sog.codice_fiscale,' - ', sog.cognome,' ', sog.nome, sog.denominazione) as richiedente,  ");
			sql.append("	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ',  ");
			sql.append("		case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento,  ");
			sql.append("	intt.descrizione_intervento, intt.data_inserimento,  ");
			sql.append("	CASE  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 2::numeric THEN 'Verificata'::text::character varying  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying  ");
			sql.append("	    ELSE si.descr_stato_istanza  ");
			sql.append("	END AS stato_istanza, fi.fk_stato_istanza, fi.fk_tipo_istanza, fi.fk_tipo_istanza	 ");
			sql.append(" FROM idf_t_istanza_forest fi ");
			sql.append(" JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento ");
			sql.append(" JOIN idf_t_interv_selvicolturale itis ON itis.id_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append(" JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append(" JOIN idf_cnf_delega icd ON icd.id_config_utente = cu.id_config_utente and (icd.data_fine is null or icd.data_fine > now()) ");
			sql.append(" JOIN idf_d_tipo_istanza ti ON fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append(" JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append(" JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto AND sog.codice_fiscale = icd.cf_delegante ");
			sql.append(" LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento  ");
			sql.append(" LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento ");
			sql.append( "LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento ");
			sql.append(" JOIN ( ");
			sql.append(" 	SELECT zzz.id_intervento,array_to_string(z_cat(zzz.denominazione_comune), '; '::TEXT) AS string_comune ");
			sql.append(" 	FROM ");
			sql.append(" 		( ");
			sql.append(" 		SELECT DISTINCT r_catasto_intervento.id_intervento,	comune.denominazione_comune ");
			sql.append(" 		FROM idf_r_propcatasto_intervento AS r_catasto_intervento ");
			sql.append(" 		JOIN idf_geo_pl_prop_catasto AS catasto ON r_catasto_intervento.idgeo_pl_prop_catasto = catasto.idgeo_pl_prop_catasto ");
			sql.append(" 		JOIN idf_geo_pl_comune AS comune ON catasto.fk_comune = comune.id_comune ) AS zzz ");
			sql.append(" 	GROUP BY zzz.id_intervento ) AS tab_string_comune ON intt.id_intervento = tab_string_comune.id_intervento ");
			sql.append(" WHERE ");
			sql.append(" 	sint.id_tipo_soggetto = 1 AND itis.idgeo_pl_pfa = 0  ");
			sql.append(" 	AND cu.fk_profilo_utente = ? ");
			sql.append(" 	AND ti.fk_ambito_istanza = 2 ");
			sql.append(getQuerySortingSegment(sort));

			parameters.add(configUtente.getIdConfigUtente());
			parameters.add(configUtente.getIdConfigUtente());

			logger.info("getAllIstancesTagli sql: " + sql.toString());
			logger.info("getAllIstancesTagli sql parameters: " + " - " + configUtente.getIdConfigUtente() +	" - " + ambitoIstanza);

		}else if (tipoAccreditamento == 3){
			sql.append(" SELECT ");
			sql.append(" 	DISTINCT fi.id_istanza_intervento, ");
			sql.append(" 	fi.nr_istanza_forestale, ");
			sql.append(" 	fi.data_invio, ");
			sql.append(" 	si.descr_stato_istanza, ");
			sql.append(" 	sog.id_soggetto, sog.codice_fiscale, sog.partita_iva, ");
			sql.append(" 	sog.nome,sog.cognome,sog.denominazione,  ");
			sql.append(" 	tab_string_comune.string_comune as string_comune, ");
			sql.append("	intt.id_intervento,  ");
			sql.append("	concat (date_part('year'::text, intt.data_inserimento),'/',fi.nr_istanza_forestale) as nr_istanza,  ");
			sql.append("	ti.descr_dett_tipoistanza,  ");
			sql.append("	concat(sog.codice_fiscale,' - ', sog.cognome,' ', sog.nome, sog.denominazione) as richiedente,  ");
			sql.append("	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ',  ");
			sql.append("		case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento,  ");
			sql.append("	intt.descrizione_intervento, intt.data_inserimento,  ");
			sql.append("	CASE  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 2::numeric THEN 'Verificata'::text::character varying  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying  ");
			sql.append("	    ELSE si.descr_stato_istanza  ");
			sql.append("	END AS stato_istanza, fi.fk_stato_istanza, fi.fk_tipo_istanza, fi.fk_tipo_istanza 	 ");
			sql.append(" FROM idf_t_istanza_forest fi ");
			sql.append(" JOIN idf_d_tipo_istanza ti ON fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append(" JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_t_interv_selvicolturale itis ON itis.id_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append(" JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append(" LEFT JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento AND sint.id_tipo_soggetto = 1 ");
			sql.append(" LEFT JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto ");
			sql.append(" LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento  ");
			sql.append(" LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento ");
			sql.append( "LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento ");
			sql.append(" LEFT JOIN (  ");
			sql.append(" 	 SELECT zzz.id_intervento,  ");
			sql.append(" 	     array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune  ");
			sql.append(" 	 FROM (  ");
			sql.append(" 	 SELECT DISTINCT  ");
			sql.append(" 	 r_catasto_intervento.id_intervento, comune.denominazione_comune  ");
			sql.append(" 	 FROM  ");
			sql.append(" 	 idf_r_propcatasto_intervento as r_catasto_intervento  ");
			sql.append(" 	 JOIN idf_geo_pl_prop_catasto as catasto ON r_catasto_intervento.idgeo_pl_prop_catasto=catasto.idgeo_pl_prop_catasto  ");
			sql.append(" 	 JOIN idf_geo_pl_comune as comune ON catasto.fk_comune=comune.id_comune  ");
			sql.append(" 	 ) as zzz  ");
			sql.append(" 	 GROUP BY zzz.id_intervento  ");
			sql.append(" 	 ) as tab_string_comune ON intt.id_intervento=tab_string_comune.id_intervento  ");
			sql.append(" WHERE ");
			sql.append(" 	intt.fk_tipo_accreditamento = ? ");
			sql.append(" 	AND intt.fk_soggetto_sportello = ? ");
			sql.append(" 	AND itis.idgeo_pl_pfa = 0 ");
			sql.append(" 	AND ti.fk_ambito_istanza = 2 and fi.fk_stato_istanza = 1 ");

			sql.append(getQuerySortingSegment(sort));

			parameters.add(configUtente.getFkTipoAccreditamento());
			parameters.add(configUtente.getFkSoggettoSportello());

			logger.info("getAllIstancesTagli sql: " + sql.toString());
			logger.info("getAllIstancesTagli sql parameters: IDCONF: " + configUtente.getIdConfigUtente()
					+ " - FKSOGG: " + configUtente.getFkSoggetto() + " - PROF: " +configUtente.getFkProfiloUtente()
					+ " - ACC: " + configUtente.getFkTipoAccreditamento() + " - SPORT: " +configUtente.getFkSoggettoSportello()
					+ " - AMB: " + ambitoIstanza);


		}else {
			sql.append(" SELECT  ");
			sql.append(" 	DISTINCT fi.id_istanza_intervento, fi.nr_istanza_forestale, fi.data_invio,	si.descr_stato_istanza,  ");
			sql.append(" 	sog.id_soggetto, sog.codice_fiscale, sog.partita_iva, sog.nome, sog.cognome, sog.denominazione,  ");
			sql.append(" 	tab_string_comune.string_comune AS string_comune,	  ");
			sql.append(" 	intt.id_intervento,   ");
			sql.append(" 	concat (date_part('year'::text, intt.data_inserimento),'/',fi.nr_istanza_forestale) as nr_istanza,   ");
			sql.append(" 	ti.descr_dett_tipoistanza,   ");
			sql.append(" 	concat(sog.codice_fiscale,' - ', sog.cognome,' ', sog.nome, sog.denominazione) as richiedente,   ");
			sql.append(" 	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ',   ");
			sql.append(" 		case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento,   ");
			sql.append(" 	intt.descrizione_intervento, intt.data_inserimento,   ");
			sql.append(" 	CASE   ");
			sql.append(" 	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 2::numeric THEN 'Verificata'::text::character varying   ");
			sql.append(" 	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying   ");
			sql.append(" 	    ELSE si.descr_stato_istanza   ");
			sql.append(" 	END AS stato_istanza, fi.fk_stato_istanza, fi.fk_tipo_istanza, fi.fk_tipo_istanza 	  ");
			sql.append(" FROM idf_t_istanza_forest fi  ");
			sql.append(" JOIN idf_d_tipo_istanza ti ON fi.fk_tipo_istanza = id_tipo_istanza  ");
			sql.append(" JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_t_interv_selvicolturale itis ON itis.id_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente  ");
			sql.append(" JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza  ");
			sql.append(" LEFT JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento  ");
			sql.append(" LEFT JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto  ");
			sql.append(" LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento   ");
			sql.append(" LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento  ");
			sql.append(" LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento  ");
			sql.append(" LEFT JOIN (  ");
			sql.append(" 	SELECT zzz.id_intervento, array_to_string(z_cat(zzz.denominazione_comune), '; '::TEXT) AS string_comune  ");
			sql.append(" 	FROM (	SELECT	DISTINCT r_catasto_intervento.id_intervento, comune.denominazione_comune  ");
			sql.append(" 		FROM idf_r_propcatasto_intervento AS r_catasto_intervento  ");
			sql.append(" 		JOIN idf_geo_pl_prop_catasto AS catasto ON r_catasto_intervento.idgeo_pl_prop_catasto = catasto.idgeo_pl_prop_catasto  ");
			sql.append(" 		JOIN idf_geo_pl_comune AS comune ON	catasto.fk_comune = comune.id_comune ) AS zzz  ");
			sql.append(" 	GROUP BY zzz.id_intervento ) AS tab_string_comune ON intt.id_intervento = tab_string_comune.id_intervento  ");
			sql.append(" WHERE  ");
			sql.append(" 	intt.fk_config_utente_compilatore = ? AND itis.idgeo_pl_pfa = 0 AND ti.fk_ambito_istanza = 2  ");
			sql.append(" 	AND intt.fk_tipo_accreditamento = 1  ");
			sql.append(" 	AND (sint.id_soggetto is null or sint.id_tipo_soggetto  = 1) ");
			sql.append(" UNION  ");
			sql.append(" SELECT  ");
			sql.append(" 	DISTINCT fi.id_istanza_intervento, fi.nr_istanza_forestale, fi.data_invio,	si.descr_stato_istanza,  ");
			sql.append(" 	sog.id_soggetto, sog.codice_fiscale, sog.partita_iva, sog.nome, sog.cognome, sog.denominazione,  ");
			sql.append(" 	tab_string_comune.string_comune AS string_comune,	  ");
			sql.append(" 	intt.id_intervento,   ");
			sql.append(" 	concat (date_part('year'::text, intt.data_inserimento),'/',fi.nr_istanza_forestale) as nr_istanza,   ");
			sql.append(" 	ti.descr_dett_tipoistanza,   ");
			sql.append(" 	concat(sog.codice_fiscale,' - ', sog.cognome,' ', sog.nome, sog.denominazione) as richiedente,   ");
			sql.append(" 	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ',   ");
			sql.append(" 		case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento,   ");
			sql.append(" 	intt.descrizione_intervento, intt.data_inserimento,   ");
			sql.append(" 	CASE   ");
			sql.append(" 	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 2::numeric THEN 'Verificata'::text::character varying   ");
			sql.append(" 	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying   ");
			sql.append(" 	    ELSE si.descr_stato_istanza   ");
			sql.append(" 	END AS stato_istanza, fi.fk_stato_istanza, fi.fk_tipo_istanza, fi.fk_tipo_istanza ");
			sql.append(" FROM idf_t_istanza_forest fi  ");
			sql.append(" JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_t_interv_selvicolturale itis ON itis.id_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente  ");
			sql.append(" JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza  ");
			sql.append(" JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento  ");
			sql.append(" JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto  ");
			sql.append(" JOIN idf_d_tipo_istanza ti ON fi.fk_tipo_istanza = id_tipo_istanza  ");
			sql.append(" LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento   ");
			sql.append(" LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento  ");
			sql.append(" LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento  ");
			sql.append(" LEFT JOIN (  ");
			sql.append(" 	SELECT zzz.id_intervento, array_to_string(z_cat(zzz.denominazione_comune), '; '::TEXT) AS string_comune  ");
			sql.append(" 	FROM (	SELECT	DISTINCT r_catasto_intervento.id_intervento, comune.denominazione_comune  ");
			sql.append(" 		FROM idf_r_propcatasto_intervento AS r_catasto_intervento  ");
			sql.append(" 		JOIN idf_geo_pl_prop_catasto AS catasto ON r_catasto_intervento.idgeo_pl_prop_catasto = catasto.idgeo_pl_prop_catasto  ");
			sql.append(" 		JOIN idf_geo_pl_comune AS comune ON	catasto.fk_comune = comune.id_comune ) AS zzz  ");
			sql.append(" 	GROUP BY zzz.id_intervento ) AS tab_string_comune ON intt.id_intervento = tab_string_comune.id_intervento  ");
			sql.append(" WHERE sint.id_tipo_soggetto = 1 AND itis.idgeo_pl_pfa = 0 AND ti.fk_ambito_istanza = 2   ");
			sql.append(" 	AND (sog.id_soggetto = ? ");
			sql.append(" 	OR sog.id_soggetto IN ( SELECT pfg.id_soggetto_pg FROM idf_r_pf_pg pfg	WHERE pfg.id_soggetto_pf = ?))   ");

			sql.append(getQuerySortingSegment(sort));

			parameters.add(configUtente.getIdConfigUtente());
			parameters.add(configUtente.getFkSoggetto());
			parameters.add(configUtente.getFkSoggetto());

			logger.info("getAllIstancesTagli sql: " + sql.toString());
			logger.info("getAllIstancesTagli sql parameters: IDCONF: " + configUtente.getIdConfigUtente()
					+ " - FKSOGG: " + configUtente.getFkSoggetto() + " - PROF: " +configUtente.getFkProfiloUtente()
					+ " - ACC: " + configUtente.getFkTipoAccreditamento() + " - SPORT: " +configUtente.getFkSoggettoSportello()
					+ " - AMB: " + ambitoIstanza);
		}

		return super.paginatedList(sql.toString(), parameters, new PlainSoggettoIstanzaTagliMapper(), page, limit);


	}

	@Override
	public PaginatedList<PlainSoggettoIstanzaTagli> getAllIstancesTagli_old(ConfigUtente configUtente,
																			Integer tipoAccreditamento,
																			int ambitoIstanza,
																			int tipoIstanzaId, int page, int limit, String sort) {

		StringBuilder sql = new StringBuilder();
		List<Object> parameters = new ArrayList<>();

		if(tipoAccreditamento == 2) {
			sql.append(" SELECT ");
			sql.append(" 	DISTINCT fi.id_istanza_intervento,fi.nr_istanza_forestale,fi.data_invio,si.descr_stato_istanza, ");
			sql.append(" 	sog.id_soggetto, sog.codice_fiscale, sog.partita_iva,sog.nome,sog.cognome,sog.denominazione,  ");
			sql.append(" 	tab_string_comune.string_comune as string_comune, ");
			sql.append("	intt.id_intervento,  ");
			sql.append("	concat (date_part('year'::text, intt.data_inserimento),'/',fi.nr_istanza_forestale) as nr_istanza,  ");
			sql.append("	ti.descr_dett_tipoistanza,  ");
			sql.append("	concat(sog.codice_fiscale,' - ', sog.cognome,' ', sog.nome, sog.denominazione) as richiedente,  ");
			sql.append("	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ',  ");
			sql.append("		case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento,  ");
			sql.append("	intt.descrizione_intervento, intt.data_inserimento,  ");
			sql.append("	CASE  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 2::numeric THEN 'Verificata'::text::character varying  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying  ");
			sql.append("	    ELSE si.descr_stato_istanza  ");
			sql.append("	END AS stato_istanza, fi.fk_stato_istanza, fi.fk_tipo_istanza 	 ");
			sql.append(" FROM idf_t_istanza_forest fi ");
			sql.append(" JOIN idf_d_tipo_istanza ti ON fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append(" JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_t_interv_selvicolturale itis ON itis.id_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append(" JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append(" LEFT JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append(" LEFT JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto ");
			sql.append(" LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento  ");
			sql.append(" LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento ");
			sql.append( "LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento ");
			sql.append(" LEFT JOIN (  ");
			sql.append(" 	 SELECT zzz.id_intervento, array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune  ");
			sql.append(" 	 FROM (  ");
			sql.append(" 		 SELECT DISTINCT r_catasto_intervento.id_intervento, comune.denominazione_comune  ");
			sql.append(" 		 FROM idf_r_propcatasto_intervento as r_catasto_intervento  ");
			sql.append(" 		 JOIN idf_geo_pl_prop_catasto as catasto ON r_catasto_intervento.idgeo_pl_prop_catasto=catasto.idgeo_pl_prop_catasto  ");
			sql.append(" 		 JOIN idf_geo_pl_comune as comune ON catasto.fk_comune=comune.id_comune  ");
			sql.append(" 		 ) as zzz  ");
			sql.append(" 	 GROUP BY zzz.id_intervento ) as tab_string_comune ON intt.id_intervento=tab_string_comune.id_intervento  ");
			sql.append(" WHERE ");
			sql.append(" 	intt.fk_config_utente_compilatore = ?  ");
			sql.append(" 	AND ti.fk_ambito_istanza = ?  AND sog.id_soggetto IS null ");
			sql.append(" 	AND itis.idgeo_pl_pfa = 0 AND si.id_stato_istanza = 1 ");
			sql.append(" union	 ");
			sql.append(" SELECT ");
			sql.append(" 	DISTINCT fi.id_istanza_intervento,fi.nr_istanza_forestale,fi.data_invio,si.descr_stato_istanza, ");
			sql.append(" 	sog.id_soggetto,sog.codice_fiscale,	sog.partita_iva,sog.nome,sog.cognome,sog.denominazione, ");
			sql.append(" 	tab_string_comune.string_comune AS string_comune, ");
			sql.append("	intt.id_intervento,  ");
			sql.append("	concat (date_part('year'::text, intt.data_inserimento),'/',fi.nr_istanza_forestale) as nr_istanza,  ");
			sql.append("	ti.descr_dett_tipoistanza,  ");
			sql.append("	concat(sog.codice_fiscale,' - ', sog.cognome,' ', sog.nome, sog.denominazione) as richiedente,  ");
			sql.append("	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ',  ");
			sql.append("		case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento,  ");
			sql.append("	intt.descrizione_intervento, intt.data_inserimento,  ");
			sql.append("	CASE  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 2::numeric THEN 'Verificata'::text::character varying  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying  ");
			sql.append("	    ELSE si.descr_stato_istanza  ");
			sql.append("	END AS stato_istanza, fi.fk_stato_istanza, fi.fk_tipo_istanza 	 ");
			sql.append(" FROM idf_t_istanza_forest fi ");
			sql.append(" JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento ");
			sql.append(" JOIN idf_t_interv_selvicolturale itis ON itis.id_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append(" JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append(" JOIN idf_cnf_delega icd ON icd.id_config_utente = cu.id_config_utente ");
			sql.append(" JOIN idf_d_tipo_istanza ti ON fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append(" JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append(" JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto AND sog.codice_fiscale = icd.cf_delegante ");
			sql.append(" LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento  ");
			sql.append(" LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento ");
			sql.append( "LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento ");
			sql.append(" JOIN ( ");
			sql.append(" 	SELECT zzz.id_intervento,array_to_string(z_cat(zzz.denominazione_comune), '; '::TEXT) AS string_comune ");
			sql.append(" 	FROM ");
			sql.append(" 		( ");
			sql.append(" 		SELECT DISTINCT r_catasto_intervento.id_intervento,	comune.denominazione_comune ");
			sql.append(" 		FROM idf_r_propcatasto_intervento AS r_catasto_intervento ");
			sql.append(" 		JOIN idf_geo_pl_prop_catasto AS catasto ON r_catasto_intervento.idgeo_pl_prop_catasto = catasto.idgeo_pl_prop_catasto ");
			sql.append(" 		JOIN idf_geo_pl_comune AS comune ON catasto.fk_comune = comune.id_comune ) AS zzz ");
			sql.append(" 	GROUP BY zzz.id_intervento ) AS tab_string_comune ON intt.id_intervento = tab_string_comune.id_intervento ");
			sql.append(" WHERE ");
			sql.append(" 	sint.id_tipo_soggetto = 1 AND itis.idgeo_pl_pfa = 0  ");
			sql.append(" 	AND cu.fk_profilo_utente = ? ");
			sql.append(" 	AND ti.fk_ambito_istanza = ? ");
			sql.append(getQuerySortingSegment(sort));

			parameters.add(configUtente.getIdConfigUtente());
			parameters.add(ambitoIstanza);
			parameters.add(configUtente.getIdConfigUtente());
			parameters.add(ambitoIstanza);

			logger.info("getAllIstancesTagli sql: " + sql.toString());
			logger.info("getAllIstancesTagli sql parameters: " + " - " + configUtente.getIdConfigUtente() +	" - " + ambitoIstanza);

		}else if (tipoAccreditamento == 3){
			sql.append(" SELECT ");
			sql.append(" 	DISTINCT fi.id_istanza_intervento, ");
			sql.append(" 	fi.nr_istanza_forestale, ");
			sql.append(" 	fi.data_invio, ");
			sql.append(" 	si.descr_stato_istanza, ");
			sql.append(" 	sog.id_soggetto, sog.codice_fiscale, sog.partita_iva, ");
			sql.append(" 	sog.nome,sog.cognome,sog.denominazione,  ");
			sql.append(" 	tab_string_comune.string_comune as string_comune, ");
			sql.append("	intt.id_intervento,  ");
			sql.append("	concat (date_part('year'::text, intt.data_inserimento),'/',fi.nr_istanza_forestale) as nr_istanza,  ");
			sql.append("	ti.descr_dett_tipoistanza,  ");
			sql.append("	concat(sog.codice_fiscale,' - ', sog.cognome,' ', sog.nome, sog.denominazione) as richiedente,  ");
			sql.append("	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ',  ");
			sql.append("		case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento,  ");
			sql.append("	intt.descrizione_intervento, intt.data_inserimento,  ");
			sql.append("	CASE  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 2::numeric THEN 'Verificata'::text::character varying  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying  ");
			sql.append("	    ELSE si.descr_stato_istanza  ");
			sql.append("	END AS stato_istanza, fi.fk_stato_istanza, fi.fk_tipo_istanza 	 ");
			sql.append(" FROM idf_t_istanza_forest fi ");
			sql.append(" JOIN idf_d_tipo_istanza ti ON fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append(" JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_t_interv_selvicolturale itis ON itis.id_intervento = intt.id_intervento  ");
			sql.append(" JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append(" JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append(" LEFT JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append(" LEFT JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto ");
			sql.append(" LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento  ");
			sql.append(" LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento ");
			sql.append( "LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento ");
			sql.append(" LEFT JOIN (  ");
			sql.append(" 	 SELECT zzz.id_intervento,  ");
			sql.append(" 	     array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune  ");
			sql.append(" 	 FROM (  ");
			sql.append(" 	 SELECT DISTINCT  ");
			sql.append(" 	 r_catasto_intervento.id_intervento, comune.denominazione_comune  ");
			sql.append(" 	 FROM  ");
			sql.append(" 	 idf_r_propcatasto_intervento as r_catasto_intervento  ");
			sql.append(" 	 JOIN idf_geo_pl_prop_catasto as catasto ON r_catasto_intervento.idgeo_pl_prop_catasto=catasto.idgeo_pl_prop_catasto  ");
			sql.append(" 	 JOIN idf_geo_pl_comune as comune ON catasto.fk_comune=comune.id_comune  ");
			sql.append(" 	 ) as zzz  ");
			sql.append(" 	 GROUP BY zzz.id_intervento  ");
			sql.append(" 	 ) as tab_string_comune ON intt.id_intervento=tab_string_comune.id_intervento  ");
			sql.append(" WHERE ");
			sql.append(" 	intt.fk_config_utente_compilatore = ? ");
			sql.append(" 	AND itis.idgeo_pl_pfa = 0  AND sog.id_soggetto IS null ");
			sql.append(" 	AND ti.fk_ambito_istanza = ? ");
			//sql.append(" 	AND fi.fk_tipo_istanza = ? ");


			sql.append(getQuerySortingSegment(sort));

			parameters.add(configUtente.getIdConfigUtente());
			parameters.add(ambitoIstanza);
			//parameters.add(tipoIstanzaId);

			logger.info("getAllIstancesTagli sql: " + sql.toString());
			logger.info("getAllIstancesTagli sql parameters: " + configUtente.getIdConfigUtente()
					+ " - " + configUtente.getFkSoggetto() + " - " +configUtente.getFkProfiloUtente()
					+ " - " + ambitoIstanza);


		}else {
			sql.append("SELECT ");
			sql.append("	DISTINCT fi.id_istanza_intervento, fi.nr_istanza_forestale, fi.data_invio,	si.descr_stato_istanza, ");
			sql.append("	sog.id_soggetto, sog.codice_fiscale, sog.partita_iva, sog.nome, sog.cognome, sog.denominazione, ");
			sql.append("	tab_string_comune.string_comune AS string_comune,	 ");
			sql.append("	intt.id_intervento,  ");
			sql.append("	concat (date_part('year'::text, intt.data_inserimento),'/',fi.nr_istanza_forestale) as nr_istanza,  ");
			sql.append("	ti.descr_dett_tipoistanza,  ");
			sql.append("	concat(sog.codice_fiscale,' - ', sog.cognome,' ', sog.nome, sog.denominazione) as richiedente,  ");
			sql.append("	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ',  ");
			sql.append("		case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento,  ");
			sql.append("	intt.descrizione_intervento, intt.data_inserimento,  ");
			sql.append("	CASE  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 2::numeric THEN 'Verificata'::text::character varying  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying  ");
			sql.append("	    ELSE si.descr_stato_istanza  ");
			sql.append("	END AS stato_istanza, fi.fk_stato_istanza, fi.fk_tipo_istanza 	 ");
			sql.append("FROM idf_t_istanza_forest fi ");
			sql.append("JOIN idf_d_tipo_istanza ti ON fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append("JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento ");
			sql.append("JOIN idf_t_interv_selvicolturale itis ON itis.id_intervento = intt.id_intervento ");
			sql.append("JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append("JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append("LEFT JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append("LEFT JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto ");
			sql.append("LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento  ");
			sql.append("LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento ");
			sql.append("LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento ");
			sql.append("LEFT JOIN ( ");
			sql.append("	SELECT zzz.id_intervento, array_to_string(z_cat(zzz.denominazione_comune), '; '::TEXT) AS string_comune ");
			sql.append("	FROM (	SELECT	DISTINCT r_catasto_intervento.id_intervento, comune.denominazione_comune ");
			sql.append("		FROM idf_r_propcatasto_intervento AS r_catasto_intervento ");
			sql.append("		JOIN idf_geo_pl_prop_catasto AS catasto ON r_catasto_intervento.idgeo_pl_prop_catasto = catasto.idgeo_pl_prop_catasto ");
			sql.append("		JOIN idf_geo_pl_comune AS comune ON	catasto.fk_comune = comune.id_comune ) AS zzz ");
			sql.append("	GROUP BY zzz.id_intervento ) AS tab_string_comune ON intt.id_intervento = tab_string_comune.id_intervento ");
			sql.append("WHERE ");
			sql.append("	intt.fk_config_utente_compilatore = ? ");
			sql.append("	AND itis.idgeo_pl_pfa = 0 ");
			//sql.append("	AND sog.id_soggetto IS NULL ");
			sql.append("	AND ti.fk_ambito_istanza = ? ");
			sql.append("UNION ");
			sql.append("SELECT ");
			sql.append("	DISTINCT fi.id_istanza_intervento, fi.nr_istanza_forestale, fi.data_invio,	si.descr_stato_istanza, ");
			sql.append("	sog.id_soggetto, sog.codice_fiscale, sog.partita_iva, sog.nome, sog.cognome, sog.denominazione, ");
			sql.append("	tab_string_comune.string_comune AS string_comune,	 ");
			sql.append("	intt.id_intervento,  ");
			sql.append("	concat (date_part('year'::text, intt.data_inserimento),'/',fi.nr_istanza_forestale) as nr_istanza,  ");
			sql.append("	ti.descr_dett_tipoistanza,  ");
			sql.append("	concat(sog.codice_fiscale,' - ', sog.cognome,' ', sog.nome, sog.denominazione) as richiedente,  ");
			sql.append("	concat(idcis.descr_categ_intervento, ' - ',idti2.descr_intervento, ' ',  ");
			sql.append("		case when idti3.id_tipo_intervento = 0 then '' else  concat ('+ ',idti3.descr_intervento) end ) as tipo_intervento,  ");
			sql.append("	intt.descrizione_intervento, intt.data_inserimento,  ");
			sql.append("	CASE  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 2::numeric THEN 'Verificata'::text::character varying  ");
			sql.append("	    WHEN fi.fk_stato_istanza = 3::numeric AND fi.fk_tipo_istanza = 3::numeric THEN 'Autorizzata'::text::character varying  ");
			sql.append("	    ELSE si.descr_stato_istanza  ");
			sql.append("	END AS stato_istanza, fi.fk_stato_istanza, fi.fk_tipo_istanza, fi.fk_tipo_istanza 	 ");
			sql.append("FROM idf_t_istanza_forest fi ");
			sql.append("JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento ");
			sql.append("JOIN idf_t_interv_selvicolturale itis ON itis.id_intervento = intt.id_intervento ");
			sql.append("JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append("JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append("JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append("JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto ");
			sql.append("JOIN idf_d_tipo_istanza ti ON fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append("LEFT JOIN idf_d_categ_interv_selvicolturale idcis ON itis.fk_categ_intervento = idcis.id_categ_intervento  ");
			sql.append("LEFT JOIN idf_d_tipo_intervento idti2 ON itis.fk_tipo_intervento = idti2.id_tipo_intervento ");
			sql.append("LEFT join idf_d_tipo_intervento idti3 on itis.fk_tipo_intervento2 = idti3.id_tipo_intervento ");
			sql.append("LEFT JOIN ( ");
			sql.append("	SELECT zzz.id_intervento, array_to_string(z_cat(zzz.denominazione_comune), '; '::TEXT) AS string_comune ");
			sql.append("	FROM (	SELECT	DISTINCT r_catasto_intervento.id_intervento, comune.denominazione_comune ");
			sql.append("		FROM idf_r_propcatasto_intervento AS r_catasto_intervento ");
			sql.append("		JOIN idf_geo_pl_prop_catasto AS catasto ON r_catasto_intervento.idgeo_pl_prop_catasto = catasto.idgeo_pl_prop_catasto ");
			sql.append("		JOIN idf_geo_pl_comune AS comune ON	catasto.fk_comune = comune.id_comune ) AS zzz ");
			sql.append("	GROUP BY zzz.id_intervento ) AS tab_string_comune ON intt.id_intervento = tab_string_comune.id_intervento ");
			sql.append("WHERE sint.id_tipo_soggetto = 1  ");
			sql.append("	AND itis.idgeo_pl_pfa = 0  ");
			sql.append("	AND (sog.id_soggetto = ?  ");
			sql.append("	OR sog.id_soggetto IN ( SELECT pfg.id_soggetto_pg FROM idf_r_pf_pg pfg	WHERE pfg.id_soggetto_pf = ?))  ");
			sql.append("	AND ti.fk_ambito_istanza = ? ");
			sql.append(getQuerySortingSegment(sort));

			parameters.add(configUtente.getIdConfigUtente());
			parameters.add(ambitoIstanza);

			parameters.add(configUtente.getFkSoggetto());
			parameters.add(configUtente.getFkSoggetto());
			parameters.add(ambitoIstanza);

			logger.info("getAllIstancesTagli sql: " + sql.toString());
			logger.info("getAllIstancesTagli sql parameters: " + configUtente.getIdConfigUtente()
					+ " - " + configUtente.getFkSoggetto() + " - " +configUtente.getFkProfiloUtente()
					+ " - " + ambitoIstanza);


		}

		return super.paginatedList(sql.toString(), parameters, new PlainSoggettoIstanzaTagliMapper(), page, limit);
	}


	@Override
	public PaginatedList<PlainSoggettoIstanzaVincolo> getAllIstancesVincolo(ConfigUtente configUtente, Integer tipoAccreditamento, 
			int ambitoIstanza, int tipoIstanzaId, int page,	int limit, String sort) {
		StringBuilder sql = new StringBuilder();
		List<Object> parameters = new ArrayList<>();
		
		if(tipoAccreditamento == 2) {
			sql.append("SELECT distinct fi.id_istanza_intervento,fi.nr_istanza_forestale,fi.data_invio,si.descr_stato_istanza, ");
			sql.append("sog.id_soggetto,sog.codice_fiscale,sog.partita_iva,sog.nome,sog.cognome,sog.denominazione, ");
			sql.append("tab_string_comune.string_comune as denominazione_comune, fi.fk_stato_istanza, fi.fk_tipo_istanza, ");
			
			sql.append("CASE ");
			sql.append("WHEN invi.flg_cauzione_vi IS NULL THEN 'Ricevuta Cauzione INDETERMINATO; ' ");
			sql.append("WHEN invi.flg_cauzione_vi IN (2,3,4,5) THEN 'Ricevuta Cauzione ESENTE; ' "); 
			sql.append("WHEN invi.flg_cauzione_vi = 1 AND doc106.id_documento_allegato IS NULL THEN 'Ricevuta Cauzione MANCANTE; ' "); 
			sql.append("WHEN invi.flg_cauzione_vi = 1 AND doc106.id_documento_allegato IS NOT NULL THEN 'Ricevuta Cauzione PRESENTE; ' "); 
			sql.append("END stato_cauzione, ");
			
			sql.append("CASE ");
			sql.append("WHEN invi.flg_compensazione IS NULL THEN 'Ricevuta compensazione INDETERMINATO' ");
			sql.append("WHEN invi.flg_compensazione = 'N' THEN 'Ricevuta compensazione ESENTE' "); 
			sql.append("WHEN invi.flg_compensazione = 'F' AND doc152.id_documento_allegato IS NULL THEN 'Ricevuta dep. Comp. Fisica MANCANTE' "); 
			sql.append("WHEN invi.flg_compensazione = 'F' AND doc152.id_documento_allegato IS NOT NULL THEN 'Ricevuta dep. Comp. Fisica PRESENTE' "); 
			sql.append("WHEN invi.flg_compensazione = 'M' AND doc153.id_documento_allegato IS NULL THEN 'Ricevuta dep. Comp. Monetaria MANCANTE' "); 
			sql.append("WHEN invi.flg_compensazione = 'M' AND doc153.id_documento_allegato IS NOT NULL THEN 'Ricevuta dep. Comp. Monetaria PRESENTE' "); 
			sql.append("END stato_compensazione, ");
			
			sql.append("CASE ");
			sql.append("WHEN invi.fk_intervento_padre_variante IS NOT NULL THEN 'VARIANTE' ");
			sql.append("WHEN invi.fk_intervento_padre_proroga IS NOT NULL THEN 'PROROGA' ");
			sql.append("WHEN ");
			sql.append("( ");
			sql.append("invi.id_intervento IN (SELECT fk_intervento_padre_variante FROM idf_t_interv_vinc_idro WHERE id_intervento != invi.id_intervento ) ");
			sql.append("OR ");
			sql.append("invi.id_intervento IN (SELECT fk_intervento_padre_proroga FROM idf_t_interv_vinc_idro WHERE id_intervento != invi.id_intervento ) ");
			sql.append(") ");
			sql.append("THEN 'SI' ");
			sql.append("WHEN invi.fk_intervento_padre_variante IS NULL AND invi.fk_intervento_padre_proroga IS NULL THEN 'NO' ");
			sql.append("END variante_proroga, ");
			sql.append("null as competenza, null as rimboschimento ");
			sql.append("FROM idf_t_istanza_forest fi ");
			sql.append("JOIN idf_t_intervento intt ON fi.id_istanza_intervento=intt.id_intervento ");
			sql.append("JOIN idf_t_interv_vinc_idro invi ON fi.id_istanza_intervento = invi.id_intervento ");
			sql.append("JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append("JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append("JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append("JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto ");
			sql.append("JOIN idf_d_tipo_istanza ti on fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append("JOIN idf_cnf_delega icd on icd.id_config_utente = cu.id_config_utente ");
			sql.append("and sog.codice_fiscale = icd.cf_delegante and (icd.data_fine is null or icd.data_fine > now())");

			sql.append("LEFT JOIN ( ");
			sql.append("      SELECT zzz.id_intervento, ");
			sql.append("          array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune ");
			sql.append("      FROM ( ");
			sql.append("      SELECT DISTINCT ");
			sql.append("      r_catasto_intervento.id_intervento, comune.denominazione_comune ");
			sql.append("      FROM ");
			sql.append("      idf_r_propcatasto_intervento as r_catasto_intervento ");
			sql.append("      JOIN idf_geo_pl_prop_catasto as catasto ON r_catasto_intervento.idgeo_pl_prop_catasto=catasto.idgeo_pl_prop_catasto ");
			sql.append("      JOIN idf_geo_pl_comune as comune ON catasto.fk_comune=comune.id_comune ");
			sql.append("      ) as zzz ");
			sql.append("      GROUP BY zzz.id_intervento ");
			sql.append("      ) as tab_string_comune ON intt.id_intervento=tab_string_comune.id_intervento ");
			sql.append("LEFT JOIN idf_t_documento_allegato doc106 on fi.id_istanza_intervento = doc106.fk_intervento and doc106.fk_tipo_allegato=106 ");
			sql.append("LEFT JOIN idf_t_documento_allegato doc152 on fi.id_istanza_intervento = doc152.fk_intervento and doc152.fk_tipo_allegato=152 ");
			sql.append("LEFT JOIN idf_t_documento_allegato doc153 on fi.id_istanza_intervento = doc153.fk_intervento and doc153.fk_tipo_allegato=153 ");
			sql.append("WHERE ");
			sql.append("sint.id_tipo_soggetto = 1 ");
			sql.append("AND intt.fk_config_utente_compilatore = ? ");
			sql.append("AND cu.fk_profilo_utente = ? ");
			sql.append("AND ti.fk_ambito_istanza = ? ");
			sql.append("AND fi.fk_tipo_istanza = ? ");

			sql.append(getQuerySortingSegment(sort));
			
			parameters.add(configUtente.getIdConfigUtente());
			parameters.add(ProfiloUtenteEnum.CITTADINO.getValue());
			parameters.add(ambitoIstanza);
			parameters.add(tipoIstanzaId);
			
			logger.info("getAllIstances sql: " + sql.toString());
			logger.info("getAllIstances sql parameters: " + " - " + configUtente.getFkSoggetto() 
				+ " - " + ProfiloUtenteEnum.CITTADINO.getValue() + " - " + ambitoIstanza);
		}else {
			sql.append("SELECT distinct fi.id_istanza_intervento,fi.nr_istanza_forestale,fi.data_invio,si.descr_stato_istanza, ");
			sql.append("sog.id_soggetto,sog.codice_fiscale,sog.partita_iva,sog.nome,sog.cognome,sog.denominazione, ");
			sql.append("tab_string_comune.string_comune as denominazione_comune, fi.fk_stato_istanza, fi.fk_tipo_istanza, ");
			
			sql.append("CASE ");
			sql.append("WHEN invi.flg_cauzione_vi IS NULL THEN '' ");
			sql.append("WHEN invi.flg_cauzione_vi IN (2,3,4,5) THEN 'Ricevuta Cauzione ESENTE; ' "); 
			sql.append("WHEN invi.flg_cauzione_vi = 1 AND doc106.id_documento_allegato IS NULL THEN 'Ricevuta Cauzione MANCANTE; ' "); 
			sql.append("WHEN invi.flg_cauzione_vi = 1 AND doc106.id_documento_allegato IS NOT NULL THEN 'Ricevuta Cauzione PRESENTE; ' "); 
			sql.append("END stato_cauzione, ");
			
			sql.append("CASE ");
			sql.append("WHEN invi.flg_compensazione IS NULL THEN '' ");
			sql.append("WHEN invi.flg_compensazione = 'N' THEN 'Ricevuta compensazione ESENTE;' "); 
			sql.append("WHEN invi.flg_compensazione = 'F' AND doc152.id_documento_allegato IS NULL THEN 'Ricevuta dep. Comp. Fisica MANCANTE;' "); 
			sql.append("WHEN invi.flg_compensazione = 'F' AND doc152.id_documento_allegato IS NOT NULL THEN 'Ricevuta dep. Comp. Fisica PRESENTE;' "); 
			sql.append("WHEN invi.flg_compensazione = 'M' AND doc153.id_documento_allegato IS NULL THEN 'Ricevuta dep. Comp. Monetaria MANCANTE;' "); 
			sql.append("WHEN invi.flg_compensazione = 'M' AND doc153.id_documento_allegato IS NOT NULL THEN 'Ricevuta dep. Comp. Monetaria PRESENTE;' "); 
			sql.append("END stato_compensazione, ");
			
			sql.append("CASE ");
			sql.append("WHEN invi.fk_intervento_padre_variante IS NOT NULL THEN 'VARIANTE' ");
			sql.append("WHEN invi.fk_intervento_padre_proroga IS NOT NULL THEN 'PROROGA' ");
			sql.append("WHEN ");
			sql.append("( ");
			sql.append("invi.id_intervento IN (SELECT fk_intervento_padre_variante FROM idf_t_interv_vinc_idro WHERE id_intervento != invi.id_intervento ) ");
			sql.append("OR ");
			sql.append("invi.id_intervento IN (SELECT fk_intervento_padre_proroga FROM idf_t_interv_vinc_idro WHERE id_intervento != invi.id_intervento ) ");
			sql.append(") ");
			sql.append("THEN 'SI' ");
			sql.append("WHEN invi.fk_intervento_padre_variante IS NULL AND invi.fk_intervento_padre_proroga IS NULL THEN 'NO' ");
			sql.append("END variante_proroga, ");
			sql.append("null as competenza, null as rimboschimento ");
			
			sql.append("FROM idf_t_istanza_forest fi ");
			sql.append("JOIN idf_t_intervento intt ON fi.id_istanza_intervento = intt.id_intervento ");
			sql.append("JOIN idf_t_interv_vinc_idro invi ON fi.id_istanza_intervento = invi.id_intervento ");
			sql.append("JOIN idf_cnf_config_utente cu ON intt.fk_config_utente_compilatore = cu.id_config_utente ");
			sql.append("JOIN idf_d_stato_istanza si ON fi.fk_stato_istanza = si.id_stato_istanza ");
			sql.append("JOIN idf_r_soggetto_intervento sint ON fi.id_istanza_intervento = sint.id_intervento ");
			sql.append("JOIN idf_t_soggetto sog ON sint.id_soggetto = sog.id_soggetto ");
			sql.append("JOIN idf_d_tipo_istanza ti on fi.fk_tipo_istanza = id_tipo_istanza ");
			sql.append("LEFT JOIN ( ");
			sql.append("      SELECT zzz.id_intervento, ");
			sql.append("          array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune ");
			sql.append("      FROM ( ");
			sql.append("      SELECT DISTINCT ");
			sql.append("      r_catasto_intervento.id_intervento, comune.denominazione_comune ");
			sql.append("      FROM ");
			sql.append("      idf_r_propcatasto_intervento as r_catasto_intervento ");
			sql.append("      JOIN idf_geo_pl_prop_catasto as catasto ON r_catasto_intervento.idgeo_pl_prop_catasto=catasto.idgeo_pl_prop_catasto ");
			sql.append("      JOIN idf_geo_pl_comune as comune ON catasto.fk_comune=comune.id_comune ");
			sql.append("      ) as zzz ");
			sql.append("      GROUP BY zzz.id_intervento ");
			sql.append("      ) as tab_string_comune ON intt.id_intervento=tab_string_comune.id_intervento ");
			sql.append("LEFT JOIN idf_t_documento_allegato doc106 on fi.id_istanza_intervento = doc106.fk_intervento and doc106.fk_tipo_allegato=106 ");
			sql.append("LEFT JOIN idf_t_documento_allegato doc152 on fi.id_istanza_intervento = doc152.fk_intervento and doc152.fk_tipo_allegato=152 ");
			sql.append("LEFT JOIN idf_t_documento_allegato doc153 on fi.id_istanza_intervento = doc153.fk_intervento and doc153.fk_tipo_allegato=153 ");
			sql.append("WHERE ");
			sql.append("sint.id_tipo_soggetto = 1 ");
			sql.append("AND intt.fk_config_utente_compilatore = ? ");
			sql.append("AND (sog.id_soggetto= ? OR sog.id_soggetto in (SELECT pfg.id_soggetto_pg FROM idf_r_pf_pg pfg WHERE pfg.id_soggetto_pf = ?))");
			sql.append("AND cu.fk_profilo_utente = ? ");
			sql.append("AND ti.fk_ambito_istanza = ? ");
			sql.append("AND fi.fk_tipo_istanza = ? ");
		
			sql.append(getQuerySortingSegment(sort));
			
			parameters.add(configUtente.getIdConfigUtente());
			parameters.add(configUtente.getFkSoggetto());
			parameters.add(configUtente.getFkSoggetto());
			parameters.add(ProfiloUtenteEnum.CITTADINO.getValue());
			parameters.add(ambitoIstanza);
			parameters.add(tipoIstanzaId);
			
			logger.info("getAllIstances sql: " + sql.toString());
			logger.info("getAllIstances sql parameters: " + configUtente.getIdConfigUtente()
			+ " - " + configUtente.getFkSoggetto() + " - " + ProfiloUtenteEnum.CITTADINO.getValue()
			+ " - " + ambitoIstanza);
		}

		return super.paginatedList(sql.toString(), parameters, new PlainSoggettoIstanzaVincoloMapper(), page, limit);
	}
	
	private static String getQuerySortingSegment(String sort) {
		String sortField = null;
		String sortOrder = null;

		StringBuilder sql = new StringBuilder();
		if (sort != null && !sort.isEmpty()) {
			sortOrder = sort.charAt(0) == ORDER ? ASCENDING:DESCENDING;
			sortField = sortOrder.equals(DESCENDING) ? sort.substring(1) : sort;
			if (sortField != null && !sortField.isEmpty()) {
				sql.append(" ORDER BY ");
				sql.append(translateFieldNameForBackOffice(sortField)).append(" ").append(sortOrder);
			}
		}
		return sql.toString();
	}

	private static String translateFieldNameForBackOffice(String fieldName) {
		switch (fieldName) {
			case "nrIstanzaForestale":
				return "nr_istanza_forestale";
			case "richiedente":
				return "codice_fiscale";
			case "dataInvio":
				return "data_invio";
			case "comune":
				return "denominazione_comune";
			case "stato":
				return "descr_stato_istanza";
			default:
				return "nr_istanza_forestale";
		}
	}

}
