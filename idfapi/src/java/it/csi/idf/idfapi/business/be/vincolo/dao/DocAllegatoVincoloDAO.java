/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.InfoAllegatiVincolo;

public interface DocAllegatoVincoloDAO {

	List<InfoAllegatiVincolo> getAllegatiByIdIntervento(Integer idIntervento);
}
