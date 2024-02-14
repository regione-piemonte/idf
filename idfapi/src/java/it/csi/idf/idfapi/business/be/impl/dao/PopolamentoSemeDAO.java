/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.InterventoPopSeme;
import it.csi.idf.idfapi.dto.PopolamentoSeme;

public interface PopolamentoSemeDAO {
	
	PopolamentoSeme getByCodiceAmministrativo(String codiceAmministrativo);
	List<PopolamentoSeme> getAllByInterventoPopSemes(List<InterventoPopSeme> popolamentoSemes);
}
