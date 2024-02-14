/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.api.impl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import it.csi.idf.idfapi.business.be.vincolo.api.IdfCnfDelegaApi;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfDelega;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfCnfDelegaService;
import it.csi.idf.idfapi.util.SpringSupportedResource;

/**
 * JAXRS Jersey controller for 'IdfCnfDelega'ApiImpl management.
 */
@Path("/idfCnfDelega")
public class IdfCnfDelegaApiImpl extends SpringSupportedResource implements IdfCnfDelegaApi {

	@Autowired
	private IdfCnfDelegaService idfCnfDelegaService;

	/**
	 * Constructor
	 */
	public IdfCnfDelegaApiImpl() {
		super();
		logger.info("Constructor.");
	}

	/**
	 * Get all idfCnfDelega entities.
	 * 
	 * @return list with all entities found
	 */
	@Override
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<IdfCnfDelega> findAll() {
		logger.info("findAll()...");
		return idfCnfDelegaService.findAll();
	}

	/**
	 * Retrieves a idfCnfDelega using the given id.
	 * 
	 * @param cfDelegante    cfDelegante
	 * @param idConfigUtente idConfigUtente
	 * @return 200 + body if found, 404 if not found
	 */
	@Override
	@GET
	@Path("{cfDelegante}/{idConfigUtente}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findById(@PathParam("cfDelegante") String cfDelegante,
			@PathParam("idConfigUtente") Integer idConfigUtente) {
		logger.info("findById(" + cfDelegante + idConfigUtente + ")...");
		IdfCnfDelega record = idfCnfDelegaService.findById(cfDelegante, idConfigUtente);
		if (record != null) {
			return Response.ok(record).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**
	 * Creates a new idfCnfDelega.
	 * 
	 * @param idfCnfDelega idfCnfDelega
	 * @return 201 with body if created, 409 conflict if duplicate key
	 */
	@Override
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response create(IdfCnfDelega idfCnfDelega) {
		logger.info("create()...");
		if (idfCnfDelegaService.exists(idfCnfDelega)) {
			logger.info("create() : already exists -> conflict");
			return Response.status(Status.CONFLICT).build();
		} else {
			logger.info("create() : doesn't exist -> create");
			IdfCnfDelega record = idfCnfDelegaService.create(idfCnfDelega);
			return Response.status(Status.CREATED).entity(record).build();
		}
	}

//------------------------------------------------------------
	/**
	 * Updates the idfCnfDelega identified by the given id
	 * 
	 * @param idfCnfDelega   idfCnfDelega entity
	 * @param cfDelegante    cfDelegante
	 * @param idConfigUtente idConfigUtente
	 * @return 200 if found and updated, 404 if not found
	 */
	@Override
	@PUT
	@Path("{cfDelegante}/{idConfigUtente}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	// public IdfCnfDelega update(IdfCnfDelega idfCnfDelega,
	// @PathParam("cfDelegante") String cfDelegante, @PathParam("idConfigUtente")
	// Integer idConfigUtente) {
	public Response update(IdfCnfDelega idfCnfDelega, @PathParam("cfDelegante") String cfDelegante,
			@PathParam("idConfigUtente") Integer idConfigUtente) {
		logger.info("update()...");
		// force the id (use the id provided by the URL)
		idfCnfDelega.setCfDelegante(cfDelegante);
		idfCnfDelega.setIdConfigUtente(idConfigUtente);
		 boolean updated = idfCnfDelegaService.update(idfCnfDelega);
		if (updated ) {
			// Actually updated (found and updated) => 200 OK
			return Response.status(Status.OK).build();
		} else {
			// Not updated with no error => 404 not found
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**
	 * Save (create or update) the given idfCnfDelega.
	 * 
	 * @param idfCnfDelega idfCnfDelega entity
	 * @return 200 if found and updated, 201 if not found and created
	 */
	@Override
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response save(IdfCnfDelega idfCnfDelega) {
		logger.info("save()...");
		Status status = Status.OK; // 200 OK
		if (!idfCnfDelegaService.exists(idfCnfDelega)) {
			status = Status.CREATED; // 201 CREATED
		}
		IdfCnfDelega record = idfCnfDelegaService.create(idfCnfDelega);
		// Response ( code 200 or 201 )
		return Response.status(status).entity(record).build();
	}

//------------------------------------------------------------
	/**
	 * Delete a idfCnfDelega.
	 * 
	 * @param cfDelegante    cfDelegante
	 * @param idConfigUtente idConfigUtente
	 */
	@Override
	@DELETE
	@Path("{cfDelegante}/{idConfigUtente}")
	// @Consumes({MediaType.APPLICATION_JSON})
	// public void delete(@PathParam("cfDelegante") String cfDelegante,
	// @PathParam("idConfigUtente") Integer idConfigUtente) {
	public Response delete(@PathParam("cfDelegante") String cfDelegante,
			@PathParam("idConfigUtente") Integer idConfigUtente) {
		logger.info("delete(" + cfDelegante + idConfigUtente + ")...");
		
		 boolean deleted = idfCnfDelegaService.deleteById(cfDelegante, idConfigUtente);
		if (deleted ) {
			// Actually deleted (found and deleted) => 204 "No Content" because no body in
			// the response
			return Response.status(Status.NO_CONTENT).build();
		} else {
			// Not deleted with no error => 404 "Not found"
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	

}
