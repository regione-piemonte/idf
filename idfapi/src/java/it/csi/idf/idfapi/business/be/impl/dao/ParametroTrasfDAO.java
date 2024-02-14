/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.ParametroTrasf;
import it.csi.idf.idfapi.dto.ParametroTrasfResource;
import it.csi.idf.idfapi.dto.ParamtrasfTrasformazion;
import it.csi.idf.idfapi.dto.SceltiParameter;
import it.csi.idf.idfapi.util.TipoParametroTrasfEnum;

public interface ParametroTrasfDAO {
	List<ParametroTrasf> getParametroTrasfs();
	ParametroTrasf getParametroTrasfById(int idParametroTrasf);
	List<SceltiParameter> getSceltoParemeterByParamtrasfTrasformazion(List<ParamtrasfTrasformazion> paramtrasfTrasformazion);
	List<ParametroTrasfResource> getParametroTrasfsByTipoParametro(TipoParametroTrasfEnum tipoParametroEnum);
}
