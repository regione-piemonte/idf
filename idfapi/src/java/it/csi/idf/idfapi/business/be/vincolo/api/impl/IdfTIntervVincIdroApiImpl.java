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

import it.csi.idf.idfapi.business.be.vincolo.api.IdfTIntervVincIdroApi;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIntervVincIdro;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfTIntervVincIdroService;
import it.csi.idf.idfapi.util.SpringSupportedResource;
/**
 * JAXRS Jersey controller for 'IdfTIntervVincIdro'ApiImpl management.
 */
@Path("/idfTIntervVincIdro")
public class IdfTIntervVincIdroApiImpl extends SpringSupportedResource implements  IdfTIntervVincIdroApi{
	
	@Autowired
	private IdfTIntervVincIdroService idfTIntervVincIdroService ;

	/**
	 * Constructor
	 */
	public IdfTIntervVincIdroApiImpl() {
		super();
		logger.info("Constructor.");
	}

	/**
	 * Get all idfTIntervVincIdro entities.
	 * @return list with all entities found
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<IdfTIntervVincIdro> findAll() {
		logger.info("findAll()...");
		return idfTIntervVincIdroService.findAll();
	}

	/**
	 * Retrieves a idfTIntervVincIdro using the given id.
	 * @param idIntervento idIntervento
	 * @return 200 + body if found, 404 if not found 
	 */
	@GET
	@Path("{idIntervento}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("idIntervento") BigDecimal idIntervento) {
		logger.info("findById("+idIntervento+")...");
		IdfTIntervVincIdro record = idfTIntervVincIdroService.findById(idIntervento);
		if ( record != null ) {
			return Response.ok(record).build();
		}
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**
	 * Creates a new idfTIntervVincIdro.
	 * @param idfTIntervVincIdro idfTIntervVincIdro
	 * @return 201 with body if created, 409 conflict if duplicate key 
	 */	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(IdfTIntervVincIdro idfTIntervVincIdro) {
		logger.info("create()...");
		if ( idfTIntervVincIdroService.exists(idfTIntervVincIdro) ) {
			logger.info("create() : already exists -> conflict");
			return Response.status(Status.CONFLICT).build();
		}
		else {
			logger.info("create() : doesn't exist -> create");
			IdfTIntervVincIdro record = idfTIntervVincIdroService.create(idfTIntervVincIdro);
			return Response.status(Status.CREATED).entity(record).build();
		}
	}

//------------------------------------------------------------
	/**
	 * Updates the idfTIntervVincIdro identified by the given id
	 * @param idfTIntervVincIdro idfTIntervVincIdro entity
	 * @param idIntervento idIntervento 
	 * @return 200 if found and updated, 404 if not found 
	 */	
	@PUT
	@Path("{idIntervento}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	// public IdfTIntervVincIdro update(IdfTIntervVincIdro idfTIntervVincIdro, @PathParam("idIntervento") BigDecimal idIntervento) {
	public Response update(IdfTIntervVincIdro idfTIntervVincIdro, @PathParam("idIntervento") BigDecimal idIntervento) {
		logger.info("update()...");
		// force the id (use the id provided by the URL)
		idfTIntervVincIdro.setIdIntervento( idIntervento );
		boolean updated = idfTIntervVincIdroService.update(idfTIntervVincIdro);
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
	 * Save (create or update) the given idfTIntervVincIdro.
	 * @param idfTIntervVincIdro idfTIntervVincIdro entity
	 * @return 200 if found and updated, 201 if not found and created
	 */	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response save(IdfTIntervVincIdro idfTIntervVincIdro) {
		logger.info("save()...");
		Status status = Status.OK ; // 200 OK
		if ( ! idfTIntervVincIdroService.exists(idfTIntervVincIdro) ) {
			status = Status.CREATED ; // 201 CREATED
		}
		IdfTIntervVincIdro record = idfTIntervVincIdroService.create(idfTIntervVincIdro);
		// Response ( code 200 or 201 )
		return Response.status(status).entity(record).build();
	}


//------------------------------------------------------------
	/**
	 * Delete a idfTIntervVincIdro.
	 * @param idIntervento idIntervento
	 */
	@DELETE
	@Path("{idIntervento}")
	//@Consumes({MediaType.APPLICATION_JSON})
	// public void delete(@PathParam("idIntervento") BigDecimal idIntervento) {
	public Response delete(@PathParam("idIntervento") BigDecimal idIntervento) {
		logger.info("delete("+idIntervento+")...");
		// idfTIntervVincIdroService.deleteById(idIntervento);
		boolean deleted = idfTIntervVincIdroService.deleteById(idIntervento);
		if ( deleted) {
			// Actually deleted (found and deleted) => 204 "No Content" because no body in the response 
			return Response.status(Status.NO_CONTENT).build();
		}
		else {
			// Not deleted with no error => 404 "Not found"
			return Response.status(Status.NOT_FOUND).build();
		}
}

	

}
