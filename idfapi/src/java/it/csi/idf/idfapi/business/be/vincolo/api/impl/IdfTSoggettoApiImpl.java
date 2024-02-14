/*
 * Created on 2020-10-04 ( Time 22:37:06 )
 * Generated by Telosys Tools Generator ( version 3.1.2 )
 */
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

import it.csi.idf.idfapi.business.be.vincolo.api.IdfTSoggettoApi;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTSoggetto;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfTSoggettoService;
import it.csi.idf.idfapi.util.SpringSupportedResource;
/**
 * JAXRS Jersey controller for 'IdfTSoggetto'ApiImpl management.
 */
@Path("/idfTSoggetto")
public class IdfTSoggettoApiImpl extends SpringSupportedResource implements  IdfTSoggettoApi{
	
	@Autowired
	private IdfTSoggettoService idfTSoggettoService ;

	/**
	 * Constructor
	 */
	public IdfTSoggettoApiImpl() {
		super();
		logger.info("Constructor.");
	}

	/**
	 * Get all idfTSoggetto entities.
	 * @return list with all entities found
	 */
	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<IdfTSoggetto> findAll() {
		logger.info("findAll()...");
		return idfTSoggettoService.findAll();
	}

	/**
	 * Retrieves a idfTSoggetto using the given id.
	 * @param idSoggetto idSoggetto
	 * @return 200 + body if found, 404 if not found 
	 */
	@Override
	@GET
	@Path("{idSoggetto}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("idSoggetto") Integer idSoggetto) {
		logger.info("findById("+idSoggetto+")...");
		IdfTSoggetto record = idfTSoggettoService.findById(idSoggetto);
		if ( record != null ) {
			return Response.ok(record).build();
		}
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**
	 * Creates a new idfTSoggetto.
	 * @param idfTSoggetto idfTSoggetto
	 * @return 201 with body if created, 409 conflict if duplicate key 
	 */	
	@Override
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(IdfTSoggetto idfTSoggetto) {
		logger.info("create()...");
		if ( idfTSoggettoService.exists(idfTSoggetto) ) {
			logger.info("create() : already exists -> conflict");
			return Response.status(Status.CONFLICT).build();
		}
		else {
			logger.info("create() : doesn't exist -> create");
			IdfTSoggetto record = idfTSoggettoService.create(idfTSoggetto);
			return Response.status(Status.CREATED).entity(record).build();
		}
	}

//------------------------------------------------------------
	/**
	 * Updates the idfTSoggetto identified by the given id
	 * @param idfTSoggetto idfTSoggetto entity
	 * @param idSoggetto idSoggetto 
	 * @return 200 if found and updated, 404 if not found 
	 */	
	@Override
	@PUT
	@Path("{idSoggetto}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	// public IdfTSoggetto update(IdfTSoggetto idfTSoggetto, @PathParam("idSoggetto") Integer idSoggetto) {
	public Response update(IdfTSoggetto idfTSoggetto, @PathParam("idSoggetto") Integer idSoggetto) {
		logger.info("update()...");
		// force the id (use the id provided by the URL)
		idfTSoggetto.setIdSoggetto( idSoggetto );
		boolean updated = idfTSoggettoService.update(idfTSoggetto);
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
	 * Save (create or update) the given idfTSoggetto.
	 * @param idfTSoggetto idfTSoggetto entity
	 * @return 200 if found and updated, 201 if not found and created
	 */	
	@Override
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response save(IdfTSoggetto idfTSoggetto) {
		logger.info("save()...");
		Status status = Status.OK ; // 200 OK
		if ( ! idfTSoggettoService.exists(idfTSoggetto) ) {
			status = Status.CREATED ; // 201 CREATED
		}
		IdfTSoggetto record = idfTSoggettoService.create(idfTSoggetto);
		// Response ( code 200 or 201 )
		return Response.status(status).entity(record).build();
	}


//------------------------------------------------------------
	/**
	 * Delete a idfTSoggetto.
	 * @param idSoggetto idSoggetto
	 */
	@Override
	@DELETE
	@Path("{idSoggetto}")
	//@Consumes({MediaType.APPLICATION_JSON})
	// public void delete(@PathParam("idSoggetto") Integer idSoggetto) {
	public Response delete(@PathParam("idSoggetto") Integer idSoggetto) {
		logger.info("delete("+idSoggetto+")...");
		// idfTSoggettoService.deleteById(idSoggetto);
		boolean deleted = idfTSoggettoService.deleteById(idSoggetto);
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