/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.TipoForestale;
import it.csi.idf.idfapi.util.IplaEnum;

public interface TipoForestaleDAO {
	List<TipoForestale> findAllTipoForestale();
	List<TipoForestale> findAllTipoForestaleByIpla(IplaEnum ipla);
	List<TipoForestale> findAllTipoByCategoria(Integer categoriaForestale) throws RecordNotFoundException;
	List<TipoForestale> findAllTipoByCategoriaAndIpla(Integer categoriaForestale, IplaEnum ipla) throws RecordNotFoundException;
	TipoForestale getTipoForestaleById(int idTipoForestale);
}
