/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.DescrizioneInterventoDAO;
import it.csi.idf.idfapi.dto.DescrizioneIntervento;
import it.csi.idf.idfapi.mapper.DescrizioneInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class DescrizioneInterventoDAOImpl implements DescrizioneInterventoDAO{
	
	private static Logger logger = Logger.getLogger(DescrizioneInterventoDAOImpl.class);

	@Override
	public DescrizioneIntervento getDescrIntervForSummaryInstance(Integer idIntervento) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select descrizione_intervento as descrizione, idg.descr_governo as governo");
		sql.append(", idti.descr_intervento as tipo_di_intervento, STRING_AGG(distinct(idcf.descrizione), ', ') as categorie_forestali");
		sql.append(",localita,  flg_piedilista, nr_piante as numero_piante, idft.descr_finalita_taglio as finalita_del_taglio");
		sql.append(",iddl.descr_dest_legname as destinazione_del_legname,volume_ramaglia_m3 as ramaglia");
		sql.append(", stima_massa_retraibile_m3,  iduv.descr_uso_viabilita as viabilita, ide.descr_esbosco as tipo_di_esbosco");
		sql.append(", note_esbosco, idfa.fascia_altimetica_min || '-' || idfa.fascia_altimetrica_max as fascia_altimetrica");
		sql.append(", string_agg(DISTINCT  its.volgare ,', ' order by its.volgare) as specie_interesate ");
		sql.append(", sup.sup_tagliata_ha, cat.totale_sup_catastale ");
		sql.append(" from idf_t_intervento iti");
		sql.append(" left join idf_r_intervento_catfor iric using (id_intervento) ");
		sql.append(" left join idf_d_categoria_forestale idcf using(id_categoria_forestale) ");
		sql.append(" left join idf_t_interv_selvicolturale itis using (id_intervento) ");
		sql.append(" left join idf_d_tipo_intervento idti on id_tipo_intervento = itis.fk_tipo_intervento ");
		sql.append(" left join idf_d_governo idg on itis.fk_governo = idg.id_governo ");
		sql.append(" left join idf_d_fascia_altimetrica idfa on itis.fk_fascia_altimetrica = idfa.id_fascia_altimetrica ");
		sql.append(" left join idf_d_finalita_taglio idft on itis.fk_finalita_taglio = idft.id_finalita_taglio ");
		sql.append(" left join idf_d_dest_legname iddl on itis.fk_dest_legname = iddl.id_dest_legname ");
		sql.append(" left join idf_r_interventoselv_usoviab iriu using (id_intervento) ");
		sql.append(" left join idf_d_uso_viabilita iduv on iriu.fk_uso_viabilita = iduv.id_uso_viabilita ");
		sql.append(" left join idf_r_intervselv_esbosco irie using (id_intervento) ");
		sql.append(" left join idf_d_esbosco ide using (cod_esbosco) ");
		sql.append(" left join idf_r_specie_pfa_intervento irspi using (id_intervento) ");
		sql.append(" left join idf_t_specie its using (id_specie)");
		sql.append(" left join (select fk_intervento, sum(sup_tagliata_ha) as sup_tagliata_ha"); 
		sql.append(" 	FROM idf_geo_pl_lotto_intervento WHERE fk_intervento = ? group by fk_intervento ) as sup on sup.fk_intervento = iti.id_intervento ");
		sql.append(" left join (select id_intervento, sum(sup_cartografica_ha) as totale_sup_catastale from idf_r_propcatasto_intervento irpi"); 
		sql.append(" 	left join idf_geo_pl_prop_catasto igppc using (idgeo_pl_prop_catasto) ");
		sql.append(" where id_intervento = ? group by id_intervento)cat on cat.id_intervento = iti.id_intervento");
		
		sql.append(" where iti.id_intervento = ? ");
		
		sql.append(" group by descrizione_intervento,idg.descr_governo,idti.descr_intervento,localita");
		sql.append(", cat.totale_sup_catastale, sup.sup_tagliata_ha");
		sql.append(", flg_piedilista, nr_piante,idft.descr_finalita_taglio, iddl.descr_dest_legname,volume_ramaglia_m3 ");
		sql.append(", stima_massa_retraibile_m3, iduv.descr_uso_viabilita, ide.descr_esbosco, note_esbosco");
		sql.append(", idfa.fascia_altimetica_min || '-' || idfa.fascia_altimetrica_max");
		
		Object[] params = new Object[] {idIntervento,idIntervento,idIntervento};
		
		logger.info("getDescrIntervForSummaryInstance - sql: " + sql.toString() + " - idIntervento: " + idIntervento);
		return  DBUtil.jdbcTemplate.queryForObject(sql.toString(), new DescrizioneInterventoMapper(), params);
		
	}

}
