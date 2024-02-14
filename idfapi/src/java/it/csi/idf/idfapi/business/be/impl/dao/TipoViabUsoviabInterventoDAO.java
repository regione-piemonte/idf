/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

public interface TipoViabUsoviabInterventoDAO {

	int createTipoViabIntervento(Integer idTipoViabilita, Integer idIntervento, Integer fkUsoViabilita);

	void deleteTipoViabIntervento(Integer idIntervento);

	boolean existsTipoViabilitaIntervento(Integer idIntervento, Integer idTipoViabilita, Integer fkUsoViabilita);


	List<Integer> findAllIdsByInterventoAndUsoViab(Integer idIntervento, Integer fkUsoViabilita);

}
