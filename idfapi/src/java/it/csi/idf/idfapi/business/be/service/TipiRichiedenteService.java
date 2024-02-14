/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service;

import it.csi.idf.idfapi.dto.TipoRichiedente;

import javax.ws.rs.core.Response;
import java.util.List;

public interface TipiRichiedenteService {

    TipoRichiedente getTipoRichiedenteById(int tipoRichiedente);

    List<TipoRichiedente> getAll();


}
