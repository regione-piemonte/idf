/*
 * Created on 2020-10-04 ( Time 22:37:05 )
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

import it.csi.idf.idfapi.business.be.vincolo.api.IdfDCategoriaProfessionaleApi;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfDCategoriaProfessionale;
import it.csi.idf.idfapi.business.be.vincolo.service.IdfDCategoriaProfessionaleService;
import it.csi.idf.idfapi.util.SpringSupportedResource;
/**
 * JAXRS Jersey controller for 'IdfDCategoriaProfessionale'ApiImpl management.
 */
@Path("/idfDCategoriaProfessionale")
public class IdfDCategoriaProfessionaleApiImpl extends SpringSupportedResource implements  IdfDCategoriaProfessionaleApi{
	
	@Autowired
	private IdfDCategoriaProfessionaleService idfDCategoriaProfessionaleService ;

	/**
	 * Constructor
	 */
	public IdfDCategoriaProfessionaleApiImpl() {
		super();
		logger.info("Constructor.");
	}

	/**
	 * Get all idfDCategoriaProfessionale entities.
	 * @return list with all entities found
	 */
	@Override
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<IdfDCategoriaProfessionale> findAll() {
		logger.info("findAll()...");
		return idfDCategoriaProfessionaleService.findAll();
	}

	/**
	 * Retrieves a idfDCategoriaProfessionale using the given id.
	 * @param idCategoriaProfessionale idCategoriaProfessionale
	 * @return 200 + body if found, 404 if not found 
	 */
	@Override
	@GET
	@Path("{idCategoriaProfessionale}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("idCategoriaProfessionale") BigDecimal idCategoriaProfessionale) {
		logger.info("findById("+idCategoriaProfessionale+")...");
		IdfDCategoriaProfessionale record = idfDCategoriaProfessionaleService.findById(idCategoriaProfessionale);
		if ( record != null ) {
			return Response.ok(record).build();
		}
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**
	 * Creates a new idfDCategoriaProfessionale.
	 * @param idfDCategoriaProfessionale idfDCategoriaProfessionale
	 * @return 201 with body if created, 409 conflict if duplicate key 
	 */	
	@Override
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(IdfDCategoriaProfessionale idfDCategoriaProfessionale) {
		logger.info("create()...");
		if ( idfDCategoriaProfessionaleService.exists(idfDCategoriaProfessionale) ) {
			logger.info("create() : already exists -> conflict");
			return Response.status(Status.CONFLICT).build();
		}
		else {
			logger.info("create() : doesn't exist -> create");
			IdfDCategoriaProfessionale record = idfDCategoriaProfessionaleService.create(idfDCategoriaProfessionale);
			return Response.status(Status.CREATED).entity(record).build();
		}
	}

//------------------------------------------------------------
	/**
	 * Updates the idfDCategoriaProfessionale identified by the given id
	 * @param idfDCategoriaProfessionale idfDCategoriaProfessionale entity
	 * @param idCategoriaProfessionale idCategoriaProfessionale 
	 * @return 200 if found and updated, 404 if not found 
	 */	
	@Override
	@PUT
	@Path("{idCategoriaProfessionale}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	// public IdfDCategoriaProfessionale update(IdfDCategoriaProfessionale idfDCategoriaProfessionale, @PathParam("idCategoriaProfessionale") BigDecimal idCategoriaProfessionale) {
	public Response update(IdfDCategoriaProfessionale idfDCategoriaProfessionale, @PathParam("idCategoriaProfessionale") BigDecimal idCategoriaProfessionale) {
		logger.info("update()...");
		// force the id (use the id provided by the URL)
		idfDCategoriaProfessionale.setIdCategoriaProfessionale( idCategoriaProfessionale );
		 boolean updated = idfDCategoriaProfessionaleService.update(idfDCategoriaProfessionale);
		if ( updated) {
			// Actually updated (found and updated) => 200 OK
			return Response.status(Status.OK).build();
		}
		else {
			// Not updated with no error => 404 not found
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	/**
	 * Save (create or update) the given idfDCategoriaProfessionale.
	 * @param idfDCategoriaProfessionale idfDCategoriaProfessionale entity
	 * @return 200 if found and updated, 201 if not found and created
	 */	
	@Override
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response save(IdfDCategoriaProfessionale idfDCategoriaProfessionale) {
		logger.info("save()...");
		Status status = Status.OK ; // 200 OK
		if ( ! idfDCategoriaProfessionaleService.exists(idfDCategoriaProfessionale) ) {
			status = Status.CREATED ; // 201 CREATED
		}
		IdfDCategoriaProfessionale record = idfDCategoriaProfessionaleService.create(idfDCategoriaProfessionale);
		// Response ( code 200 or 201 )
		return Response.status(status).entity(record).build();
	}


//------------------------------------------------------------
	/**
	 * Delete a idfDCategoriaProfessionale.
	 * @param idCategoriaProfessionale idCategoriaProfessionale
	 */
	@Override
	@DELETE
	@Path("{idCategoriaProfessionale}")
	//@Consumes({MediaType.APPLICATION_JSON})
	// public void delete(@PathParam("idCategoriaProfessionale") BigDecimal idCategoriaProfessionale) {
	public Response delete(@PathParam("idCategoriaProfessionale") BigDecimal idCategoriaProfessionale) {
		logger.info("delete("+idCategoriaProfessionale+")...");
		// idfDCategoriaProfessionaleService.deleteById(idCategoriaProfessionale);
		boolean deleted = idfDCategoriaProfessionaleService.deleteById(idCategoriaProfessionale);
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
