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

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfSkOkVincIdro;


/**
 * JAXRS Jersey controller for 'IdfCnfSkOkVincIdro'Api management.
 */
@Path("/idfCnfSkOkVincIdro")
public interface IdfCnfSkOkVincIdroApi {
	
	/**
	 * Get all idfCnfSkOkVincIdro entities.
	 * @return list with all entities found
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<IdfCnfSkOkVincIdro> findAll() ;

	/**
	 * Retrieves a idfCnfSkOkVincIdro using the given id.
	 * @param idIntervento idIntervento
	 * @return 200 + body if found, 404 if not found 
	 */
	@GET
	@Path("{idIntervento}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("idIntervento") BigDecimal idIntervento) ;
	/**
	 * Creates a new idfCnfSkOkVincIdro.
	 * @param idfCnfSkOkVincIdro idfCnfSkOkVincIdro
	 * @return 201 with body if created, 409 conflict if duplicate key 
	 */	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) ;

//------------------------------------------------------------
	
	/**
	 * Updates the idfCnfSkOkVincIdro identified by the given id
	 * @param idfCnfSkOkVincIdro idfCnfSkOkVincIdro entity
	 * @param idIntervento idIntervento 
	 * @return 200 if found and updated, 404 if not found 
	 */	
	@PUT
	@Path("{idIntervento}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	// public IdfCnfSkOkVincIdro update(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro, @PathParam("idIntervento") BigDecimal idIntervento) {
	public Response update(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro, @PathParam("idIntervento") BigDecimal idIntervento); 
		// force the id (use the id provided by the URL)
		// idfCnfSkOkVincIdro.setIdIntervento( idIntervento );
		
	/**
	 * Save (create or update) the given idfCnfSkOkVincIdro.
	 * @param idfCnfSkOkVincIdro idfCnfSkOkVincIdro entity
	 * @return 200 if found and updated, 201 if not found and created
	 */	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response save(IdfCnfSkOkVincIdro idfCnfSkOkVincIdro) ;

//------------------------------------------------------------
	/**
	 * Delete a idfCnfSkOkVincIdro.
	 * @param idIntervento idIntervento
	 */
	@DELETE
	@Path("{idIntervento}")
	//@Consumes({MediaType.APPLICATION_JSON})
	// public void delete(@PathParam("idIntervento") BigDecimal idIntervento) {
	public Response delete(@PathParam("idIntervento") BigDecimal idIntervento) ;
		// idfCnfSkOkVincIdroService.deleteById(idIntervento);
		

	

}
