/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.ParametroTrasfApi;
import it.csi.idf.idfapi.business.be.impl.dao.ParametroTrasfDAO;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.TipoParametroTrasfEnum;

public class ParametroTrasfApiServiceImpl  extends SpringSupportedResource implements ParametroTrasfApi {
	
	@Autowired
	private ParametroTrasfDAO parametroTrasfDAO;

	@Override
	public Response getGoverno(TipoParametroTrasfEnum tipoParametroEnum) {
		try {
			return Response.ok(parametroTrasfDAO.getParametroTrasfsByTipoParametro(TipoParametroTrasfEnum.GOVERNO)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getCategoriaForestale(TipoParametroTrasfEnum tipoParametroEnum) {
		try {
			return Response.ok(parametroTrasfDAO.getParametroTrasfsByTipoParametro(TipoParametroTrasfEnum.CATEGORIA_FORESTALE)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getUbicazione(TipoParametroTrasfEnum tipoParametroEnum) {
		try {
			return Response.ok(parametroTrasfDAO.getParametroTrasfsByTipoParametro(TipoParametroTrasfEnum.UBICAZIONE)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getTrasformazione(TipoParametroTrasfEnum tipoParametroEnum) {
		try {
			return Response.ok(parametroTrasfDAO.getParametroTrasfsByTipoParametro(TipoParametroTrasfEnum.TRASFORMAZIONE)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	} 
}
