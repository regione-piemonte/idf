/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.TipoAllegato;

public interface TipoAllegatoDAO {
	TipoAllegato getTipoById(int idTipoAllegato);
	List<TipoAllegato> getAllTipoAllegato();
	TipoAllegato getAllTipoAllegatoTipoById(Integer idTipoAllegato );
	List<TipoAllegato> getAllTipoAllegatoPfa();
	List<Integer> getIdsTipiAllegatoConFirmaDigitale();
}
