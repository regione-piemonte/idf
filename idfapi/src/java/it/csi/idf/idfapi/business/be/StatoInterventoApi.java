/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import it.csi.idf.idfapi.dto.StatoIntervento;

@Path("/private/statiInterventi")
@Produces({ "application/json" })
public interface StatoInterventoApi {
	
	@GET
	@Produces({ "application/json" })
	List<StatoIntervento> getStatoList();
	
	@GET
	@Path("/{idIntervento}")
	@Produces({ "application/json" })
	Map<String,Object> getStatoByIdIntervento(@PathParam("idIntervento") Integer idIntervento);
}
