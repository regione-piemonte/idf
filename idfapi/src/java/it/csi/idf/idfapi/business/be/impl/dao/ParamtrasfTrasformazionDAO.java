/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.ParamtrasfTrasformazion;

public interface ParamtrasfTrasformazionDAO {
	void insert(ParamtrasfTrasformazion paramtrasfTrasformazion);
	List<ParamtrasfTrasformazion> getParamtrasfTrasformazionsByIdIntervento(int idIntervento);
	ParamtrasfTrasformazion getParamtrasfTrasformazionByIdInterventoAndIdParametroTrasf(int idIntervento, int idParametroTrasf);
	void deleteByIdInterventoAndIdParametroTrasf(int idIntervento, int idParametroTrasf);
	void deleteByIdIntervento(int idIntervento);
}
