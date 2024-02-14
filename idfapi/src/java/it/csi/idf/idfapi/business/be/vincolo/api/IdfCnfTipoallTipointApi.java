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

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfCnfTipoallTipoint;

/**
 * JAXRS Jersey controller for 'IdfCnfTipoallTipoint'Api management.
 */
@Path("/idfCnfTipoallTipoint")
public interface IdfCnfTipoallTipointApi {
	
	/**
	 * Get all idfCnfTipoallTipoint entities.
	 * @return list with all entities found
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<IdfCnfTipoallTipoint> findAll() ;

	/**
	 * Retrieves a idfCnfTipoallTipoint using the given id.
	 * @param idTipoAllegato idTipoAllegato
	 * @param idTipoIntervento idTipoIntervento
	 * @return 200 + body if found, 404 if not found 
	 */
	@GET
	@Path("{idTipoAllegato}/{idTipoIntervento}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("idTipoAllegato") Integer idTipoAllegato, @PathParam("idTipoIntervento") BigDecimal idTipoIntervento) ;
	/**
	 * Creates a new idfCnfTipoallTipoint.
	 * @param idfCnfTipoallTipoint idfCnfTipoallTipoint
	 * @return 201 with body if created, 409 conflict if duplicate key 
	 */	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(IdfCnfTipoallTipoint idfCnfTipoallTipoint) ;

//------------------------------------------------------------
	
	/**
	 * Updates the idfCnfTipoallTipoint identified by the given id
	 * @param idfCnfTipoallTipoint idfCnfTipoallTipoint entity
	 * @param idTipoAllegato idTipoAllegato 
	 * @param idTipoIntervento idTipoIntervento 
	 * @return 200 if found and updated, 404 if not found 
	 */	
	@PUT
	@Path("{idTipoAllegato}/{idTipoIntervento}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	// public IdfCnfTipoallTipoint update(IdfCnfTipoallTipoint idfCnfTipoallTipoint, @PathParam("idTipoAllegato") Integer idTipoAllegato, @PathParam("idTipoIntervento") BigDecimal idTipoIntervento) {
	public Response update(IdfCnfTipoallTipoint idfCnfTipoallTipoint, @PathParam("idTipoAllegato") Integer idTipoAllegato, @PathParam("idTipoIntervento") BigDecimal idTipoIntervento); 
		// force the id (use the id provided by the URL)
		// idfCnfTipoallTipoint.setIdTipoAllegato( idTipoAllegato );
		// idfCnfTipoallTipoint.setIdTipoIntervento( idTipoIntervento );
		
	/**
	 * Save (create or update) the given idfCnfTipoallTipoint.
	 * @param idfCnfTipoallTipoint idfCnfTipoallTipoint entity
	 * @return 200 if found and updated, 201 if not found and created
	 */	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response save(IdfCnfTipoallTipoint idfCnfTipoallTipoint) ;

//------------------------------------------------------------
	/**
	 * Delete a idfCnfTipoallTipoint.
	 * @param idTipoAllegato idTipoAllegato
	 * @param idTipoIntervento idTipoIntervento
	 */
	@DELETE
	@Path("{idTipoAllegato}/{idTipoIntervento}")
	//@Consumes({MediaType.APPLICATION_JSON})
	// public void delete(@PathParam("idTipoAllegato") Integer idTipoAllegato, @PathParam("idTipoIntervento") BigDecimal idTipoIntervento) {
	public Response delete(@PathParam("idTipoAllegato") Integer idTipoAllegato, @PathParam("idTipoIntervento") BigDecimal idTipoIntervento) ;
		// idfCnfTipoallTipointService.deleteById(idTipoAllegato, idTipoIntervento);
		



}
