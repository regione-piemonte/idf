/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.business.be.impl.dao.InterventoPopSemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoPortaSemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoRicadenzaDAO;
import it.csi.idf.idfapi.dto.InterventoPopSeme;
import it.csi.idf.idfapi.dto.InterventoPortaSeme;
import it.csi.idf.idfapi.dto.RicadenzaIntervento;
import it.csi.idf.idfapi.mapper.InterventoAappMapper;
import it.csi.idf.idfapi.mapper.InterventoPopSemeMapper;
import it.csi.idf.idfapi.mapper.InterventoPortaSemeMapper;
import it.csi.idf.idfapi.mapper.RicadenzaInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.InstanceStateEnum;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InterventoRicadenzaDAOImpl implements InterventoRicadenzaDAO {

	@Override
	public int insertRicadenzaIntervento(RicadenzaIntervento interventoRicadenza) {
		String sql = "INSERT INTO idf_r_intervento_ppr(id_intervento, tipo_vincolo, nome_vincolo, provvedimento, percentuale_ricadenza) VALUES (?,?, ?, ?, ?)";
		List<Object> parameters = new ArrayList<>();
		parameters.add(interventoRicadenza.getId());
		parameters.add(interventoRicadenza.getTipoVincolo());
		parameters.add(interventoRicadenza.getNomeVincolo());
		parameters.add(interventoRicadenza.getProvvedimento());
		parameters.add(interventoRicadenza.getPercentuale());
		int cout=DBUtil.jdbcTemplate.update(sql, parameters.toArray());
		int countInsrt=cout++;
	       System.out.println("Se han inserto " + countInsrt + " registros.");
	       
		return countInsrt;
	}

	@Override
	public List<RicadenzaIntervento> getInterventosByIdIntervento(int idIntervento) {
		String sql = "SELECT * FROM idf_r_intervento_ppr WHERE id_intervento = ?";
		return DBUtil.jdbcTemplate.query(sql, new RicadenzaInterventoMapper(), idIntervento);
	}

}
