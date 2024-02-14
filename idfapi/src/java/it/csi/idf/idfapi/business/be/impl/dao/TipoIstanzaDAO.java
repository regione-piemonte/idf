/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.TipoIstanzaResource;

public interface TipoIstanzaDAO {
	List<TipoIstanzaResource> getTrasformazioneTipo();
	List<TipoIstanzaResource> getTipiIstanzaByUser(Integer idCnfUtente);

	List<TipoIstanzaResource> getTipiIstanzaAttiveAmbito(Integer idAmbito);
	List<TipoIstanzaResource> getAllTipoIstanza();
}
