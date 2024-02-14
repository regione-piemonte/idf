/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.TrasformazSelvicolturali;

import java.util.List;

public interface TrasformazioniSelvicolturaliDAO {


    List<TrasformazSelvicolturali> searchTraformazioniXls(String searchParam);

    List<TrasformazSelvicolturali> findByIdIntervento(Integer idIdIntervento);


}
