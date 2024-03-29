/*
 * Created on 2020-10-04 ( Time 22:37:05 )
 * Generated by Telosys Tools Generator ( version 3.1.2 )
 */
package it.csi.idf.idfapi.business.be.vincolo.api;

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

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfDCategoriaProfessionale;

/**
 * JAXRS Jersey controller for 'IdfDCategoriaProfessionale'Api management.
 */
@Path("/idfDCategoriaProfessionale")
public interface IdfDCategoriaProfessionaleApi {
	
	/**
	 * Get all idfDCategoriaProfessionale entities.
	 * @return list with all entities found
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<IdfDCategoriaProfessionale> findAll() ;

	/**
	 * Retrieves a idfDCategoriaProfessionale using the given id.
	 * @param idCategoriaProfessionale idCategoriaProfessionale
	 * @return 200 + body if found, 404 if not found 
	 */
	@GET
	@Path("{idCategoriaProfessionale}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("idCategoriaProfessionale") BigDecimal idCategoriaProfessionale) ;
	/**
	 * Creates a new idfDCategoriaProfessionale.
	 * @param idfDCategoriaProfessionale idfDCategoriaProfessionale
	 * @return 201 with body if created, 409 conflict if duplicate key 
	 */	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(IdfDCategoriaProfessionale idfDCategoriaProfessionale) ;

//------------------------------------------------------------
	
	/**
	 * Updates the idfDCategoriaProfessionale identified by the given id
	 * @param idfDCategoriaProfessionale idfDCategoriaProfessionale entity
	 * @param idCategoriaProfessionale idCategoriaProfessionale 
	 * @return 200 if found and updated, 404 if not found 
	 */	
	@PUT
	@Path("{idCategoriaProfessionale}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	// public IdfDCategoriaProfessionale update(IdfDCategoriaProfessionale idfDCategoriaProfessionale, @PathParam("idCategoriaProfessionale") BigDecimal idCategoriaProfessionale) {
	public Response update(IdfDCategoriaProfessionale idfDCategoriaProfessionale, @PathParam("idCategoriaProfessionale") BigDecimal idCategoriaProfessionale); 
		// force the id (use the id provided by the URL)
		// idfDCategoriaProfessionale.setIdCategoriaProfessionale( idCategoriaProfessionale );
		
	/**
	 * Save (create or update) the given idfDCategoriaProfessionale.
	 * @param idfDCategoriaProfessionale idfDCategoriaProfessionale entity
	 * @return 200 if found and updated, 201 if not found and created
	 */	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response save(IdfDCategoriaProfessionale idfDCategoriaProfessionale) ;

//------------------------------------------------------------
	/**
	 * Delete a idfDCategoriaProfessionale.
	 * @param idCategoriaProfessionale idCategoriaProfessionale
	 */
	@DELETE
	@Path("{idCategoriaProfessionale}")
	//@Consumes({MediaType.APPLICATION_JSON})
	// public void delete(@PathParam("idCategoriaProfessionale") BigDecimal idCategoriaProfessionale) {
	public Response delete(@PathParam("idCategoriaProfessionale") BigDecimal idCategoriaProfessionale) ;
		// idfDCategoriaProfessionaleService.deleteById(idCategoriaProfessionale);
		

	

}
