/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.TipoInterventoDatiTecnici;
import it.csi.idf.idfapi.dto.TipoInterventoDettaglio;

public interface TipoInterventoDatiTecniciDAO {

	TipoInterventoDatiTecnici findTipoIntervDatiTehnici(Integer idIntervento);
	TipoInterventoDatiTecnici findTipoIntervDatiTehniciNEW(Integer idIntervento);
	TipoInterventoDettaglio getTipoInterventoDettaglio(Integer idIntervento);
}
