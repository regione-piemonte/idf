/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.TipoViabilita;
import it.csi.idf.idfapi.dto.UsoViabilita;

import java.util.List;

public interface TipoViabilitaDAO {

	List<TipoViabilita> findAll();

	List<TipoViabilita> findAllByConfigIpla(Integer fkConfigIpla);

}
