/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.ParametroTrasf;
import it.csi.idf.idfapi.dto.ParametroTrasfResource;
import it.csi.idf.idfapi.dto.ParamtrasfTrasformazion;
import it.csi.idf.idfapi.dto.SceltiParameter;
import it.csi.idf.idfapi.util.TipoParametroTrasfEnum;

public interface ParametroVincoloDAO {
	List<ParametroTrasf> getParametroTrasfs();
	List<ParamtrasfTrasformazion> getParamtrasfTrasformazionsByIdIntervento(int idIntervento);
	ParametroTrasf getParametroTrasfById(int idParametroTrasf);
	List<SceltiParameter> getSceltoParemeterByParamtrasfTrasformazion(List<ParamtrasfTrasformazion> paramtrasfTrasformazion);
	List<ParametroTrasfResource> getParametroTrasfsByTipoParametro(TipoParametroTrasfEnum tipoParametroEnum);
}
