/*
 * Created on 2020-10-04 ( Time 22:37:06 )
 * Generated by Telosys Tools Generator ( version 3.1.2 )
 */
package it.csi.idf.idfapi.business.be.vincolo.api;

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

import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfGeoPlIntervVincidro;

/**
 * JAXRS Jersey controller for 'IdfGeoPlIntervVincidro'Api management.
 */
@Path("/idfGeoPlIntervVincidro")
public interface IdfGeoPlIntervVincidroApi {
	
	/**
	 * Get all idfGeoPlIntervVincidro entities.
	 * @return list with all entities found
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<IdfGeoPlIntervVincidro> findAll() ;

	/**
	 * Retrieves a idfGeoPlIntervVincidro using the given id.
	 * @param idGeoPlIntervVincidro idGeoPlIntervVincidro
	 * @return 200 + body if found, 404 if not found 
	 */
	@GET
	@Path("{idGeoPlIntervVincidro}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response findById(@PathParam("idGeoPlIntervVincidro") Integer idGeoPlIntervVincidro) ;
	/**
	 * Creates a new idfGeoPlIntervVincidro.
	 * @param idfGeoPlIntervVincidro idfGeoPlIntervVincidro
	 * @return 201 with body if created, 409 conflict if duplicate key 
	 */	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response create(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) ;

//------------------------------------------------------------
	
	/**
	 * Updates the idfGeoPlIntervVincidro identified by the given id
	 * @param idfGeoPlIntervVincidro idfGeoPlIntervVincidro entity
	 * @param idGeoPlIntervVincidro idGeoPlIntervVincidro 
	 * @return 200 if found and updated, 404 if not found 
	 */	
	@PUT
	@Path("{idGeoPlIntervVincidro}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	// public IdfGeoPlIntervVincidro update(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro, @PathParam("idGeoPlIntervVincidro") Integer idGeoPlIntervVincidro) {
	public Response update(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro, @PathParam("idGeoPlIntervVincidro") Integer idGeoPlIntervVincidro); 
		// force the id (use the id provided by the URL)
		// idfGeoPlIntervVincidro.setIdGeoPlIntervVincidro( idGeoPlIntervVincidro );
		
	/**
	 * Save (create or update) the given idfGeoPlIntervVincidro.
	 * @param idfGeoPlIntervVincidro idfGeoPlIntervVincidro entity
	 * @return 200 if found and updated, 201 if not found and created
	 */	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response save(IdfGeoPlIntervVincidro idfGeoPlIntervVincidro) ;

//------------------------------------------------------------
	/**
	 * Delete a idfGeoPlIntervVincidro.
	 * @param idGeoPlIntervVincidro idGeoPlIntervVincidro
	 */
	@DELETE
	@Path("{idGeoPlIntervVincidro}")
	//@Consumes({MediaType.APPLICATION_JSON})
	// public void delete(@PathParam("idGeoPlIntervVincidro") Integer idGeoPlIntervVincidro) {
	public Response delete(@PathParam("idGeoPlIntervVincidro") Integer idGeoPlIntervVincidro) ;
		// idfGeoPlIntervVincidroService.deleteById(idGeoPlIntervVincidro);
		

	

}
