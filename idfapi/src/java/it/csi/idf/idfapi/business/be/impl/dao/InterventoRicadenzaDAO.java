/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.InterventoPortaSeme;
import it.csi.idf.idfapi.dto.RicadenzaIntervento;

import java.util.List;

public interface InterventoRicadenzaDAO {

	int insertRicadenzaIntervento(RicadenzaIntervento interventoRicadenza);

	List<RicadenzaIntervento> getInterventosByIdIntervento(int idIntervento);

	//void deleteInterventosByIdIntervento(int idIntervento);
}
