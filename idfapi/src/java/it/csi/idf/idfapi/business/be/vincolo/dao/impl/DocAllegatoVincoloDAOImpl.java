/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.vincolo.dao.DocAllegatoVincoloDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.InfoAllegatiVincolo;
import it.csi.idf.idfapi.mapper.InfoAllegatiVincoloMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class DocAllegatoVincoloDAOImpl implements DocAllegatoVincoloDAO {

	@Override
	public List<InfoAllegatiVincolo> getAllegatiByIdIntervento(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tipo.id_tipo_allegato, tipo.descr_tipo_allegato, rel.flg_obbligatorio,");
		sql.append("alleg.fk_tipo_allegato FROM idf_d_tipo_allegato tipo "); 
		sql.append("JOIN idf_cnf_tipoall_tipoint rel ON tipo.id_tipo_allegato = rel.id_tipo_allegato "); 
		sql.append("inner join idf_t_interv_vinc_idro inter on inter.fk_tipo_intervento = rel.id_tipo_intervento ");
		sql.append("left join idf_t_documento_allegato alleg "); 
		sql.append("on alleg.fk_intervento = inter.id_intervento and alleg.fk_tipo_allegato = tipo.id_tipo_allegato "); 
		sql.append("WHERE inter.id_intervento  = ? "); 
		sql.append("ORDER BY mtd_ordinamento ASC");
		return DBUtil.jdbcTemplate.query(sql.toString(), new InfoAllegatiVincoloMapper(), idIntervento);
	}

}
