/*
 * Created on 2020-10-04 ( Time 22:37:06 )
 * Generated by Telosys Tools Generator ( version 3.1.2 )
 */
package it.csi.idf.idfapi.business.be.vincolo.api.impl;

import java.math.BigDecimal;
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

import it.csi.idf.idfapi.business.be.vincolo.api.IdfRIntervincidroGovernoApi;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfRIntervincidroGoverno;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfRIntervincidroGovernoService;
import it.csi.idf.idfapi.util.SpringSupportedResource;
/**
 * JAXRS Jersey controller for 'IdfRIntervincidroGoverno'ApiImpl management.
 */
@Path("/idfRIntervincidroGoverno")
public class IdfRIntervincidroGovernoApiImpl extends SpringSupportedResource implements  IdfRIntervincidroGovernoApi{
	
	@Autowired
	private IdfRIntervincidroGovernoService idfRIntervincidroGovernoService ;

	/**
	 * Constructor
	 */
	public IdfRIntervincidroGovernoApiImpl() {
		super();
		logger.info("Constructor.");
	}

	/**
	 * Get all idfRIntervincidroGoverno entities.
	 * @return list with all entities found
	 */
	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<IdfRIntervincidroGoverno> findAll() {
		logger.info("findAll()...");
		return idfRIntervincidroGovernoService.findAll();
	}

	/**
	 * Retrieves a idfRIntervincidroGoverno using the given id.
	 * @param idIntervento idIntervento
	 * @param idGoverno idGoverno
	 * @return 200 + body if found, 404 if not found 
	 */
	@Override
	@GET
	@Path("{idIntervento}/{idGoverno}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("idIntervento") BigDecimal idIntervento, @PathParam("idGoverno") Integer idGoverno) {
		logger.info("findById("+idIntervento+idGoverno+")...");
		IdfRIntervincidroGoverno record = idfRIntervincidroGovernoService.findById(idIntervento, idGoverno);
		if ( record != null ) {
			return Response.ok(record).build();
		}
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**
	 * Creates a new idfRIntervincidroGoverno.
	 * @param idfRIntervincidroGoverno idfRIntervincidroGoverno
	 * @return 201 with body if created, 409 conflict if duplicate key 
	 */	
	@Override
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(IdfRIntervincidroGoverno idfRIntervincidroGoverno) {
		logger.info("create()...");
		if ( idfRIntervincidroGovernoService.exists(idfRIntervincidroGoverno) ) {
			logger.info("create() : already exists -> conflict");
			return Response.status(Status.CONFLICT).build();
		}
		else {
			logger.info("create() : doesn't exist -> create");
			IdfRIntervincidroGoverno record = idfRIntervincidroGovernoService.create(idfRIntervincidroGoverno);
			return Response.status(Status.CREATED).entity(record).build();
		}
	}

//------------------------------------------------------------
	/**
	 * Updates the idfRIntervincidroGoverno identified by the given id
	 * @param idfRIntervincidroGoverno idfRIntervincidroGoverno entity
	 * @param idIntervento idIntervento 
	 * @param idGoverno idGoverno 
	 * @return 200 if found and updated, 404 if not found 
	 */	
	@Override
	@PUT
	@Path("{idIntervento}/{idGoverno}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	// public IdfRIntervincidroGoverno update(IdfRIntervincidroGoverno idfRIntervincidroGoverno, @PathParam("idIntervento") BigDecimal idIntervento, @PathParam("idGoverno") Integer idGoverno) {
	public Response update(IdfRIntervincidroGoverno idfRIntervincidroGoverno, @PathParam("idIntervento") BigDecimal idIntervento, @PathParam("idGoverno") Integer idGoverno) {
		logger.info("update()...");
		// force the id (use the id provided by the URL)
		idfRIntervincidroGoverno.setIdIntervento( idIntervento );
		idfRIntervincidroGoverno.setIdGoverno( idGoverno );
		boolean updated = idfRIntervincidroGovernoService.update(idfRIntervincidroGoverno);
		if ( updated ) {
			// Actually updated (found and updated) => 200 OK
			return Response.status(Status.OK).build();
		}
		else {
			// Not updated with no error => 404 not found
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**
	 * Save (create or update) the given idfRIntervincidroGoverno.
	 * @param idfRIntervincidroGoverno idfRIntervincidroGoverno entity
	 * @return 200 if found and updated, 201 if not found and created
	 */	
	@Override
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response save(IdfRIntervincidroGoverno idfRIntervincidroGoverno) {
		logger.info("save()...");
		Status status = Status.OK ; // 200 OK
		if ( ! idfRIntervincidroGovernoService.exists(idfRIntervincidroGoverno) ) {
			status = Status.CREATED ; // 201 CREATED
		}
		IdfRIntervincidroGoverno record = idfRIntervincidroGovernoService.create(idfRIntervincidroGoverno);
		// Response ( code 200 or 201 )
		return Response.status(status).entity(record).build();
	}


//------------------------------------------------------------
	/**
	 * Delete a idfRIntervincidroGoverno.
	 * @param idIntervento idIntervento
	 * @param idGoverno idGoverno
	 */
	@Override
	@DELETE
	@Path("{idIntervento}/{idGoverno}")
	//@Consumes({MediaType.APPLICATION_JSON})
	// public void delete(@PathParam("idIntervento") BigDecimal idIntervento, @PathParam("idGoverno") Integer idGoverno) {
	public Response delete(@PathParam("idIntervento") BigDecimal idIntervento, @PathParam("idGoverno") Integer idGoverno) {
		logger.info("delete("+idIntervento+idGoverno+")...");
		// idfRIntervincidroGovernoService.deleteById(idIntervento, idGoverno);
		boolean deleted = idfRIntervincidroGovernoService.deleteById(idIntervento, idGoverno);
		if ( deleted ) {
			// Actually deleted (found and deleted) => 204 "No Content" because no body in the response 
			return Response.status(Status.NO_CONTENT).build();
		}
		else {
			// Not deleted with no error => 404 "Not found"
			return Response.status(Status.NOT_FOUND).build();
		}
}

	

}
