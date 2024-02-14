/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoInterventoDatiTecniciDAO;
import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.dto.TipoInterventoDettaglio;
import it.csi.idf.idfapi.mapper.TipoInterventoDatiTecniciMapper;
import it.csi.idf.idfapi.mapper.TipoInterventoDettaglioMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class TipoInterventoDatiTecniciDAOImpl implements TipoInterventoDatiTecniciDAO {
	
	private static Logger logger = Logger.getLogger(TipoInterventoDatiTecniciDAOImpl.class);

	private final TipoInterventoDatiTecniciMapper tipoInterventoDatiTecniciMapper = new TipoInterventoDatiTecniciMapper();

	@Override
	public TipoInterventoDatiTecnici findTipoIntervDatiTehnici(Integer idIntervento) {

		StringBuilder sql = new StringBuilder();

		sql.append(
				"SELECT flg_conforme_deroga, nr_progressivo_interv, fk_stato_intervento, stato.descr_stato_intervento, data_presa_in_carico");
		sql.append(
				", interSel.annata_silvana, ARRAY_AGG(parFor.idgeo_pl_particella_forest) AS idgeo_pl_particella_forest");
		sql.append(", evento.id_evento, fk_governo");
		sql.append(", flg_piedilista, interv.descrizione_intervento, interv.localita, interv.superficie_interessata_ha");
		sql.append(", fk_tipo_intervento, fk_finalita_taglio, fk_dest_legname");
		sql.append(" FROM idf_t_interv_selvicolturale interSel");
		sql.append(
				" INNER JOIN idf_d_stato_intervento stato ON interSel.fk_stato_intervento = stato.id_stato_intervento");
		sql.append(" INNER JOIN idf_t_intervento interv ON interSel.id_intervento = interv.id_intervento");
		sql.append(" LEFT JOIN idf_r_partfor_intervento parInterv ON interSel.id_intervento = parInterv.id_intervento");
		sql.append(
				" LEFT JOIN idf_geo_pl_particella_forest parFor ON parInterv.idgeo_pl_particella_forest = parFor.idgeo_pl_particella_forest");
		sql.append(" LEFT JOIN idf_r_intervselv_evento selEvento ON interSel.id_intervento = selEvento.id_intervento");
		sql.append(" LEFT JOIN idf_t_evento evento ON selEvento.id_evento = evento.id_evento");
		sql.append(" WHERE interv.id_intervento = ?");
		sql.append(
				" GROUP BY flg_conforme_deroga, stato.descr_stato_intervento, nr_progressivo_interv, fk_stato_intervento, data_presa_in_carico");
		sql.append(", interSel.annata_silvana, evento.id_evento");
		sql.append(", fk_governo, flg_piedilista, interv.descrizione_intervento, interv.localita");
		sql.append(", interv.superficie_interessata_ha, fk_tipo_intervento");
		sql.append(", fk_finalita_taglio, fk_dest_legname");

		logger.info("findTipoIntervDatiTehnici - sql: " + sql.toString() + " - idIntervento: " + idIntervento);
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), tipoInterventoDatiTecniciMapper, idIntervento);
	}


	@Override
	public TipoInterventoDatiTecnici findTipoIntervDatiTehniciNEW(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT flg_conforme_deroga, nr_progressivo_interv, fk_stato_intervento, stato.descr_stato_intervento, data_presa_in_carico");
		sql.append(", interSel.annata_silvana, evento.id_evento, fk_governo, flg_piedilista, interv.descrizione_intervento");
		sql.append(", interv.localita, interv.superficie_interessata_ha, fk_tipo_intervento, fk_finalita_taglio, fk_dest_legname");
		sql.append(" FROM idf_t_interv_selvicolturale interSel");
		sql.append(" INNER JOIN idf_d_stato_intervento stato ON interSel.fk_stato_intervento = stato.id_stato_intervento");
		sql.append(" INNER JOIN idf_t_intervento interv ON interSel.id_intervento = interv.id_intervento");
		//sql.append(" LEFT JOIN idf_r_partfor_intervento parInterv ON interSel.id_intervento = parInterv.id_intervento");
		sql.append(" LEFT JOIN idf_r_intervselv_evento selEvento ON interSel.id_intervento = selEvento.id_intervento");
		sql.append(" LEFT JOIN idf_t_evento evento ON selEvento.id_evento = evento.id_evento");
		sql.append(" WHERE interv.id_intervento = ?");
		
		logger.info("findTipoIntervDatiTehniciNEW - sql: " + sql.toString() + " - idIntervento: " + idIntervento);
		
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), tipoInterventoDatiTecniciMapper, idIntervento);
	}
	
	
	
	
	@Override
	public TipoInterventoDettaglio getTipoInterventoDettaglio(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT concat(evt.progressivo_evento_pfa ,'/',evt.nome_breve) as evento ");
		sql.append(", nr_progressivo_interv, flg_conforme_deroga, itis.annata_silvana, data_presa_in_carico, data_inizio_intervento , data_fine_intervento ");
		sql.append(", idg.descr_governo as governo, idti.descr_intervento as tipo_di_intervento");
		sql.append(",flg_piedilista, descrizione_intervento as descrizione, interv.localita ,superficie_interessata_ha ,descr_stato_intervento ");
		sql.append(",nr_piante as numero_piante, m3_prelevati, idfa.fascia_altimetica_min || '-' || idfa.fascia_altimetrica_max as fascia_altimetrica");
		sql.append(",idft.descr_finalita_taglio as finalita_del_taglio");
		sql.append(",iddl.descr_dest_legname as destinazione_del_legname, ARRAY_AGG(parFor.idgeo_pl_particella_forest) AS idgeo_pl_particella_forest ");
		sql.append("FROM idf_t_intervento interv ");
		sql.append("LEFT JOIN idf_r_intervento_catfor iric using (id_intervento) ");
		sql.append("LEFT JOIN idf_t_interv_selvicolturale itis using (id_intervento) ");
		sql.append("LEFT JOIN idf_r_partfor_intervento parInterv using (id_intervento) ");
		sql.append("LEFT JOIN idf_geo_pl_particella_forest parFor using (idgeo_pl_particella_forest) ");
		sql.append("LEFT JOIN idf_d_tipo_intervento idti on id_tipo_intervento = itis.fk_tipo_intervento ");
		sql.append("LEFT JOIN idf_d_governo idg on itis.fk_governo = idg.id_governo ");
		sql.append("LEFT JOIN idf_d_stato_intervento idsi on itis.fk_stato_intervento = idsi.id_stato_intervento ");
		sql.append("LEFT JOIN idf_d_fascia_altimetrica idfa on itis.fk_fascia_altimetrica = idfa.id_fascia_altimetrica ");
		sql.append("LEFT JOIN idf_d_finalita_taglio idft on itis.fk_finalita_taglio = idft.id_finalita_taglio ");
		sql.append("LEFT JOIN idf_d_dest_legname iddl on itis.fk_dest_legname = iddl.id_dest_legname ");
		sql.append("LEFT JOIN idf_r_intervselv_evento selEvento using (id_intervento) ");
		sql.append("LEFT JOIN idf_t_evento evt using(id_evento) ");
		sql.append(" WHERE id_intervento = ? ");
		sql.append(" GROUP BY concat(evt.progressivo_evento_pfa ,'/',evt.nome_breve), nr_progressivo_interv, ");
		sql.append("flg_conforme_deroga, itis.annata_silvana, data_presa_in_carico ");
		sql.append(", data_inizio_intervento , data_fine_intervento, idg.descr_governo, idti.descr_intervento ");
		sql.append(", flg_piedilista,  descrizione, interv.localita ,superficie_interessata_ha ,descr_stato_intervento ");
		sql.append(", numero_piante, m3_prelevati, idfa.fascia_altimetica_min || '-' || idfa.fascia_altimetrica_max ");
		sql.append(",finalita_del_taglio, destinazione_del_legname");
		
		logger.info("getTipoInterventoDettaglio - sql: " + sql.toString() + " - idIntervento: " + idIntervento);

		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new TipoInterventoDettaglioMapper(), idIntervento);

	}



}
