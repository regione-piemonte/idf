/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TavolaDAO;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;

@Component
public class TavolaDAOImpl implements TavolaDAO {

	@Override
	public int saveTavola(AreaDiSaggio areaDiSaggio) {
		StringBuilder sql=new StringBuilder();
		sql.append("INSERT INTO idf_t_all_tav (");
		sql.append(" id_specie, num_tavola_coeff_cub, flg_coniflatif) ");
		sql.append("VALUES(?,?,?)");
	
	
		List<Object> parameters = new ArrayList<>();
		parameters.add(Integer.parseInt(areaDiSaggio.getCodiceADS()));
        parameters.add(areaDiSaggio.getTavola());
        parameters.add("C"); //mocked not null
        

    return AIKeyHolderUtil.updateWithGenKey("id_specie", sql.toString(), parameters);
	}

}
