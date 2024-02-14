/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.business.be.impl.dao.TrasformazioniSelvicolturaliDAO;
import it.csi.idf.idfapi.dto.TrasformazSelvicolturali;
import it.csi.idf.idfapi.mapper.TrasformazioniSelvicolturaliMapper;
import it.csi.idf.idfapi.util.DBUtil;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringJoiner;

@Component
public class TrasformazioniSelvicolturaliDAOImpl implements TrasformazioniSelvicolturaliDAO {
	
	static final Logger logger = Logger.getLogger(TrasformazioniSelvicolturaliDAOImpl.class);

	@Override
	public List<TrasformazSelvicolturali> searchTraformazioniXls(String searchParam) {
		StringJoiner search = new StringJoiner("", "%", "%");
		search.add(searchParam);

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("search", search.toString());

		String sql ="SELECT \n" +
				"istanza.id_istanza_intervento,\n" +
				"istanza.nr_istanza_forestale,\n" +
				"    to_char(istanza.data_inserimento::timestamp with time zone, 'YYYY'::text) AS anno,\n" +
				"        CASE\n" +
				"            WHEN statoistanza.descr_stato_istanza::text = 'Verificata/ Autorizzata'::text THEN 'Verificata'::text::character varying\n" +
				"            ELSE statoistanza.descr_stato_istanza\n" +
				"        END AS descr_stato_istanza,\n" +
				"    concat(soggetto.codice_fiscale, ' - '::text, soggetto.denominazione, soggetto.cognome, ' '::text, soggetto.nome) AS richiedente,\n" +
				"    tab_string_comune.string_comune,\n" +
				"    public.decode(trasformazione.flg_compensazione, 'N'::character varying, 'NON NECESSARIA'::text, 'M'::character varying, 'MONETARIA'::text, 'F'::character varying, 'FISICA'::text, 'NO DATA'::text) AS compensazione\n" +
				"    FROM idf_t_intervento intervento\n" +
				"     JOIN idf_t_interv_trasformazione trasformazione ON intervento.id_intervento = trasformazione.id_intervento\n" +
				"     JOIN idf_t_istanza_forest istanza ON intervento.id_intervento = istanza.id_istanza_intervento\n" +
				"     JOIN idf_d_stato_istanza statoistanza ON istanza.fk_stato_istanza = statoistanza.id_stato_istanza\n" +
				"     JOIN idf_r_soggetto_intervento r_soggetto_intervento ON intervento.id_intervento = r_soggetto_intervento.id_intervento\n" +
				"     JOIN idf_t_soggetto soggetto ON r_soggetto_intervento.id_soggetto = soggetto.id_soggetto\n" +
				"     JOIN ( SELECT zzz.id_intervento,\n" +
				"            array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune\n" +
				"           FROM ( SELECT DISTINCT r_catasto_intervento.id_intervento,\n" +
				"                    comune.denominazione_comune\n" +
				"                   FROM idf_r_propcatasto_intervento r_catasto_intervento\n" +
				"                     JOIN idf_geo_pl_prop_catasto catasto ON r_catasto_intervento.idgeo_pl_prop_catasto = catasto.idgeo_pl_prop_catasto\n" +
				"                     JOIN idf_geo_pl_comune comune ON catasto.fk_comune = comune.id_comune) zzz\n" +
				"          GROUP BY zzz.id_intervento) tab_string_comune ON intervento.id_intervento = tab_string_comune.id_intervento\n" +
				"          where istanza.fk_stato_istanza in (2,3) and trasformazione.flg_cauzione_cf = 1 \n" +
				"          AND ( LOWER(to_char(istanza.data_inserimento::timestamp with time zone, 'YYYY'::text)) LIKE LOWER(:search)\n" +
				"          OR LOWER(concat(soggetto.codice_fiscale, ' - '::text, soggetto.denominazione, soggetto.cognome, ' '::text, soggetto.nome)) LIKE LOWER(:search)\n" +
				"          OR LOWER(istanza.nr_istanza_forestale::varchar) LIKE LOWER(:search)\n" +
				"          OR LOWER(tab_string_comune.string_comune) LIKE LOWER(:search) )\n" +
				"  ORDER BY istanza.nr_istanza_forestale DESC";

		logger.info("searchTraformazioniXls sql: " + sql);
		return DBUtil.namedJdbcTemplate.query(sql, namedParameters, new TrasformazioniSelvicolturaliMapper());
	}


	@Override
	public List<TrasformazSelvicolturali> findByIdIntervento(Integer idIntervento) {
		String sql ="SELECT \n" +
				"istanza.id_istanza_intervento,\n" +
				"istanza.nr_istanza_forestale,\n" +
				"    to_char(istanza.data_inserimento::timestamp with time zone, 'YYYY'::text) AS anno,\n" +
				"        CASE\n" +
				"            WHEN statoistanza.descr_stato_istanza::text = 'Verificata/ Autorizzata'::text THEN 'Verificata'::text::character varying\n" +
				"            ELSE statoistanza.descr_stato_istanza\n" +
				"        END AS descr_stato_istanza,\n" +
				"    concat(soggetto.codice_fiscale, ' - '::text, soggetto.denominazione, soggetto.cognome, ' '::text, soggetto.nome) AS richiedente,\n" +
				"    tab_string_comune.string_comune,\n" +
				"    public.decode(trasformazione.flg_compensazione, 'N'::character varying, 'NON NECESSARIA'::text, 'M'::character varying, 'MONETARIA'::text, 'F'::character varying, 'FISICA'::text, 'NO DATA'::text) AS compensazione\n" +
				"    FROM idf_t_intervento intervento\n" +
				"     JOIN idf_t_interv_trasformazione trasformazione ON intervento.id_intervento = trasformazione.id_intervento\n" +
				"     JOIN idf_t_istanza_forest istanza ON intervento.id_intervento = istanza.id_istanza_intervento\n" +
				"     JOIN idf_d_stato_istanza statoistanza ON istanza.fk_stato_istanza = statoistanza.id_stato_istanza\n" +
				"     JOIN idf_r_soggetto_intervento r_soggetto_intervento ON intervento.id_intervento = r_soggetto_intervento.id_intervento\n" +
				"     JOIN idf_t_soggetto soggetto ON r_soggetto_intervento.id_soggetto = soggetto.id_soggetto\n" +
				"     JOIN idf_r_int_selv_trasf irist ON irist.id_intervento_trasf = istanza.id_istanza_intervento\n" +
				"     JOIN ( SELECT zzz.id_intervento,\n" +
				"            array_to_string(z_cat(zzz.denominazione_comune), '; '::text) AS string_comune\n" +
				"           FROM ( SELECT DISTINCT r_catasto_intervento.id_intervento,\n" +
				"                    comune.denominazione_comune\n" +
				"                   FROM idf_r_propcatasto_intervento r_catasto_intervento\n" +
				"                     JOIN idf_geo_pl_prop_catasto catasto ON r_catasto_intervento.idgeo_pl_prop_catasto = catasto.idgeo_pl_prop_catasto\n" +
				"                     JOIN idf_geo_pl_comune comune ON catasto.fk_comune = comune.id_comune) zzz\n" +
				"          GROUP BY zzz.id_intervento) tab_string_comune ON intervento.id_intervento = tab_string_comune.id_intervento\n" +
				"          where istanza.fk_stato_istanza in (2,3) and trasformazione.flg_cauzione_cf = 1 \n" +
				"          AND irist.id_intervento_selv  = :idIntervento \n" +
				"  ORDER BY istanza.nr_istanza_forestale DESC";

		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("idIntervento", idIntervento);
		logger.info("find by id Traformazioni sql: " + sql);

		return DBUtil.namedJdbcTemplate.query(sql, namedParameters, new TrasformazioniSelvicolturaliMapper());
	}
}
