/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.CategoriaForestale;
import it.csi.idf.idfapi.dto.InterventoCatfor;

public interface CategoriaForestaleDAO {
	List<CategoriaForestale> findAllCategoriaForestale();
	CategoriaForestale getByCodiceAmministratico(String codiceAmministrativo);
	CategoriaForestale getCategoriaForestaleById(int idCategoriaForestale);
	List<CategoriaForestale> getAllByInterventoCatfors(List<InterventoCatfor> interventoCatfors);
	List<CategoriaForestale> getAllByIdInterventoCatfors(Integer idIntervento);
	Integer calculateCategoriaForestaleByIdIntervento(Integer idIntervento);
}
