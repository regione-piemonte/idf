/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import it.csi.idf.idfapi.util.TipoParametroTrasfEnum;

@Path("/private/parametriTrasf")
@Produces({ "application/json" })
public interface ParametroTrasfApi {
	
	@GET
	@Path("/governo")
	Response getGoverno(TipoParametroTrasfEnum tipoParametroEnum);
	
	@GET
	@Path("/categoriaForestale")
	Response getCategoriaForestale(TipoParametroTrasfEnum tipoParametroEnum);
	
	@GET
	@Path("/ubicazione")
	Response getUbicazione(TipoParametroTrasfEnum tipoParametroEnum);
	
	@GET
	@Path("/trasformazione")
	Response getTrasformazione(TipoParametroTrasfEnum tipoParametroEnum);
}
