/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.CategoriaProfessionale;

public interface CategoriaProfessionaleDAO {

	List<CategoriaProfessionale> findAllCategoriaProfessionale();

	CategoriaProfessionale getCategoriaProfessionaleById(Integer id);

}
