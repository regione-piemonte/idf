/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.business.be.impl.dao.CategoriaSelvicolturaleDAO;
import it.csi.idf.idfapi.dto.CategoriaSelvicolturale;
import it.csi.idf.idfapi.mapper.CategoriaSelvicolturaleMapper;
import it.csi.idf.idfapi.util.DBUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaSelvicolturaleDAOImpl implements CategoriaSelvicolturaleDAO {


    private final CategoriaSelvicolturaleMapper categoriaInterventoMapper = new CategoriaSelvicolturaleMapper();

    @Override
    public List<CategoriaSelvicolturale> findAll() {
        String sql = "SELECT * FROM idf_d_categ_interv_selvicolturale WHERE flg_visibile = 1 ORDER BY mtd_ordinamento";
        return DBUtil.jdbcTemplate.query(sql, categoriaInterventoMapper);
    }

    @Override
    public CategoriaSelvicolturale findById(Integer idIntervento) {
        String sql = "SELECT * FROM idf_d_categ_interv_selvicolturale WHERE id_categ_intervento = ? AND flg_visibile = 1 ";
        return DBUtil.jdbcTemplate.queryForObject(sql.toString(), categoriaInterventoMapper, idIntervento);
    }
}
