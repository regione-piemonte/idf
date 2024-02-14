/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl;

import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.opengis.geometry.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import it.csi.idf.idfapi.business.be.PlainSezioniApi;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GeoPlLottoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaCompensazioneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ParticellaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropCatastoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropcatastoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.RicadenzeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperIstanzaDAO;
import it.csi.idf.idfapi.business.be.service.AAEP;
import it.csi.idf.idfapi.business.be.service.GSAREPORT;
import it.csi.idf.idfapi.business.be.service.SIGMATER;
import it.csi.idf.idfapi.business.be.service.helper.ParkApiServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.SigmaterServiceHelper;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.IntervTrasf;
import it.csi.idf.idfapi.dto.IstanzaCompensazione;
import it.csi.idf.idfapi.dto.IstanzaTaglio;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PlainSecondoSezione;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.ProceduraEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella;

public class PlainSezioniApiServiceImpl extends SpringSupportedResource implements PlainSezioniApi {

	public static final String LOGGED_CONFIG = "loggedConfig";
	
	@Autowired
	private AAEP aaep;

	@Autowired
	private SIGMATER sigmater;

	@Autowired
	private GSAREPORT gsaReport;

	@Autowired
	private ComuneDAO comuneDao;
	
	@Autowired
	private WrapperInterventoDAO wrapperInterventoDAO;
	
	@Autowired
	private WrapperIstanzaDAO wrapperIstanzaDAO;
	
	@Autowired
	private GeoPlLottoInterventoDAO geoPlLottoInterventoDAO;
	
	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private SoggettoDAO soggettoDao;
	
	@Autowired
	private SigmaterServiceHelper sigmaterServiceHelper;
	
	@Autowired
	private ParkApiServiceHelper parkApiServiceHelper;
	
	@Autowired
	private PropCatastoDAO propCatastoDAO;
	
	@Autowired
	PropcatastoInterventoDAO propcatastoInterventoDAO;
	
	@Autowired
	private ParticellaDAO particella;
	
	@Autowired
	IntervTrasfDAO intervTrasfDAO;
	
	@Autowired
	RicadenzeDAO ricadenzeDAO;
	
	@Autowired
	IstanzaCompensazioneDAO istanzaCompensazioneDAO;

	@Override
	public Response returnRicadenze(Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		logger.info("returnRicadenze - idIntervento: " + idIntervento);
		List<String> ricadenzaInfo = intervTrasfDAO.getGeometrieGmlByFkIntervento(idIntervento);
		
		PlainSecondoSezione secondSezione = new PlainSecondoSezione();
		
		secondSezione.setRicadenzaNatura2000(new ArrayList<RicadenzaInformazioni>());
		secondSezione.setRicadenzaAreeProtette(new ArrayList<RicadenzaInformazioni>());
		for(String geomGML : ricadenzaInfo) {
		
			try {
				
				//-------------------
				StringBuilder sql = new StringBuilder();
				sql.append("select ST_AsText(ST_Transform(ST_SetSRID(ST_GeomFromGeoJSON(?),4326),32632))");
				String converteString = DBUtil.jdbcTemplate.queryForObject(sql.toString(), String.class, geomGML);
				logger.info("converteString<-------------: " + converteString+"----->");
				//geomGML=converteString;
				StringBuilder sqlgml = new StringBuilder();
				sqlgml.append("select ST_AsGML(ST_SetSRID(ST_GeomFromText(?),32632),32632)");
				String converteStringgml = DBUtil.jdbcTemplate.queryForObject(sqlgml.toString(), String.class, converteString);
				logger.info("converteStringgml<-------------: " + converteStringgml.toString()+"----->");
				geomGML=converteStringgml;
				//geomGML="<gml:Polygon srsName='EPSG:32632'><gml:outerBoundaryIs><gml:LinearRing><gml:coordinates>425715.637700000021141,4993659.99199999962002 425728.505200000014156,4993165.881599999964237 426153.131300000008196,4993158.161100000143051 426150.557800000009593,4993676.719600000418723 425715.637700000021141,4993659.99199999962002</gml:coordinates></gml:LinearRing></gml:outerBoundaryIs></gml:Polygon>";
				//--------------
				
				
				String natura2000ListStr = parkApiServiceHelper.getRicadenzaNatura2000(geomGML);
				logger.info("getRicadenzaNatura2000 >" + natura2000ListStr + "<");
				secondSezione.getRicadenzaNatura2000().addAll(fillListRicadenzeFromString(natura2000ListStr));
				String areeProtetteListStr = parkApiServiceHelper.getRicadenzaAreeProtette(geomGML);
				logger.info("getRicadenzaAreeProtette >" + areeProtetteListStr + "<");
				secondSezione.getRicadenzaAreeProtette().addAll(fillListRicadenzeFromString(areeProtetteListStr));
			} catch (Exception e) {
//				e.printStackTrace();
//				return Response.serverError().build();
				logger.error("errore nel recupero delle ricadenze dal servizio esterno: "+e.getMessage());
			}
		}
		
		secondSezione.setRicadenzaPopolamentiDaSeme(ricadenzeDAO.getRicadenzePopolamentiSeme(idIntervento,ProceduraEnum.GESTIONE_ISTANZE_FORESTALI));
		Double areaVincIdro = ricadenzeDAO.getRicadenzeVincoloIdrogeologico(idIntervento,ProceduraEnum.GESTIONE_ISTANZE_FORESTALI);
		secondSezione.setRicadenzaVincoloIdrogeologico(areaVincIdro != null && areaVincIdro > 0);
		secondSezione.setRicadenzaCategorieForestali(ricadenzeDAO.getRicadenzeForestali(idIntervento,ProceduraEnum.GESTIONE_ISTANZE_FORESTALI));
		secondSezione.setTotaleSuperficieTrasf((new BigDecimal(intervTrasfDAO.getAreaTrasformazioneByFkIntervento(idIntervento)/10000)).setScale(4, RoundingMode.HALF_DOWN));
		secondSezione.setTotaleSuperficieCatastale((new BigDecimal(propcatastoInterventoDAO.getAreaCatastaleByIdIntervento(idIntervento))).setScale(4, RoundingMode.HALF_DOWN));
//		if(secondSezione.getTotaleSuperficieCatastale().compareTo(secondSezione.getTotaleSuperficieTrasf()) < 0){
//			secondSezione.setTotaleSuperficieTrasf(secondSezione.getTotaleSuperficieCatastale());
//		}

		return Response.ok(secondSezione).build();
	}
	
	public static List<RicadenzaInformazioni> fillListRicadenzeFromString(String serviceResponse){
		List<RicadenzaInformazioni> listRicadenze = new ArrayList<RicadenzaInformazioni>();
		if(serviceResponse != null && serviceResponse.indexOf(" ") > 0) {
			String[] ricadenzeStr = serviceResponse.split("; ");
			for(String ricadenzaStr : ricadenzeStr) {
				listRicadenze.add(fillRicadenzaFromString(ricadenzaStr));
			}
		}
		return listRicadenze;
	}
	
	private static RicadenzaInformazioni fillRicadenzaFromString(String ricadenzaStr) {
		RicadenzaInformazioni item = new RicadenzaInformazioni();
		int idx = ricadenzaStr.indexOf(" ");
		item.setCodiceAmministrativo(ricadenzaStr.substring(0,idx));
		item.setNome(ricadenzaStr.substring(idx + 1));
		return item;
	}

	@Override
	public Response getParticellaCatastale(String istatComune, String sezione, Integer foglio, String particella,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			PlainParticelleCatastali particelleCatastali = new PlainParticelleCatastali();

			particelleCatastali.setComune(comuneDao.findComuneResourceByIstatComune(istatComune));
			particelleCatastali.setSezione(sezione);
			particelleCatastali.setFoglio(foglio);
			particelleCatastali.setParticella(particella);

			return Response.ok(sigmater.getParticelleCatastali(particelleCatastali)).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@Override
	public Response getSocietaLegale(SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		return Response.ok(aaep.getCompaniesForRichiedenteLegale()).build();
	}

	@Override
	public Response getIstanzaTaglio(Integer numIstanza, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {

		try {
			IstanzaCompensazione ic = istanzaCompensazioneDAO.getByNumIstanza(numIstanza);
			IstanzaTaglio istanzaTaglio = wrapperIstanzaDAO.getIstanzaTaglioByNumIstanza(numIstanza);
			if(ic != null) {
				istanzaTaglio.setIdIstanza(ic.getFkIntervento());
			}
			return Response.ok(istanzaTaglio).build();
		} catch (ServiceException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	private Geometry mergeGeometries(List<Geometry> geometries) {
		// TODO: MOCKED - return null
		return null;
	}

	@Override
	public Response insertParticellaPFA(Integer idIntervento,PlainParticelleCatastali particella, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest,  wrapperAdpforHomeDAO, userSessionDAO,
					ProceduraEnum.PIANI_FORESTALI_AZIENDALI);
			// TODO MK simulating postgis function which inserts record in propcatasto_intervento table
			wrapperInterventoDAO.insertParticlesInPropcatastoIntervento(idIntervento, particella, confUtente);
			
			
			logger.info("addGeometria - idIntervento: " + idIntervento + " - idgeoPlPropCatasto:" + particella.getId());
			geoPlLottoInterventoDAO.addGeometria(idIntervento, particella.getId());
			wrapperInterventoDAO.updateRipresaInterventoPfa(idIntervento);
			return Response.ok().build();
		}catch (DuplicateKeyException de) {
			return Response.serverError().entity(de.getMessage()).build();
		} 
		catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response checkAndInsertParticellaISTAFOR(Integer idIntervento,PlainParticelleCatastali particellaCatasto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			TSoggetto soggetto = soggettoDao.findSoggettoById(confUtente.getFkSoggetto());
			
			// 777 Transformazione del Bosco
			// 777 InfoParticella[] list = this.sigmaterServiceHelper.getDettaglioDatiTerrenoGeometrie(
			// 777 		particellaCatasto.getComune().getIstatComune(), 
			// 777 		particellaCatasto.getSezione(),particellaCatasto.getFoglio().toString(), 
			// 777 		particellaCatasto.getParticella(), soggetto.getCodiceFiscale());
			/* 777
			if(list == null || list.length == 0) {
				Map<String,String> error = new HashMap<String,String>();
				error.put("error","Nessuna particella trovata con i dati inseriti!");
				return Response.ok(error).build();
			}
			InfoParticella infoParticella = list[0];
			
			logger.info("Trovato particella: " + objectToString(infoParticella));
			 */
			
			// 777 begin
			JsonNode jsonNode;
			String s = "";
			try {
				logger.info("--findParticellaById--");
				s = particella.findParticellaById(particellaCatasto.getComune().getCodiceBelfiore(), particellaCatasto.getSezione(),particellaCatasto.getFoglio().toString(), particellaCatasto.getParticella()); //geoJSON standar de PostGIS, aqui no tiene el dato de area
				
	            ObjectMapper objectMapper = new ObjectMapper();
	            jsonNode = objectMapper.readTree(s);

				logger.info("--jsonNode--");
				logger.info(jsonNode.get("geometry"));

				ObjectMapper objectMapper2 = new ObjectMapper();
				JsonNode geoJsonNode = objectMapper2.readTree(jsonNode.get("geometry").toString());

				StringBuilder sql = new StringBuilder();
				sql.append("select ST_AsText(ST_Transform(ST_SetSRID(ST_GeomFromGeoJSON(?),4326),32632))");
				String converteString = DBUtil.jdbcTemplate.queryForObject(sql.toString(), String.class, jsonNode.get("geometry").toString());
				logger.info("converteString-------------: " + converteString);

				
				StringBuilder sqlArea = new StringBuilder();
				sqlArea.append("SELECT ROUND((SELECT (ST_Area(?))/10000)::numeric, 4)");
				BigDecimal areaBigDecimal = DBUtil.jdbcTemplate.queryForObject(sqlArea.toString(), BigDecimal.class, converteString);
				logger.info("areaBigDecimal-------------: " + areaBigDecimal);

	            JsonNode convertedGeoJson = convertToUTM_333(geoJsonNode);
	            
	            logger.info("--Convertido a UTM--");
	            logger.info(convertedGeoJson.toString());
	    		
				particellaCatasto.setFkConfigUtente(confUtente.getIdConfigUtente());	
				particellaCatasto.setSupCatastale(areaBigDecimal);
				particellaCatasto.setGeometry(converteString);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 777 end
			
			// 777particellaCatasto.setFkConfigUtente(confUtente.getIdConfigUtente());
			// 777 particellaCatasto.setSupCatastale(new BigDecimal(infoParticella.getArea()/10000,MathContext.DECIMAL64));
			// particellaCatasto.setGeometry(infoParticella.getGeometriaGML());
			logger.info("checkAndInsertParticellaISTAFOR ");
			// Eduard saveParticelleToIntervTrasf(particellaCatasto, idIntervento);
			particellaCatasto = propCatastoDAO.insertParticella(particellaCatasto);
			intervTrasfDAO.addGeometriaGML(idIntervento, particellaCatasto.getGeometry());
			wrapperInterventoDAO.insertParticlesInPropcatastoIntervento(idIntervento, particellaCatasto, confUtente);

			
			return Response.ok(particellaCatasto).build();
		}catch (DuplicateKeyException de) {
			return Response.serverError().entity(de.getMessage()).build();
		} 
		catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	
	private void saveParticelleToIntervTrasf(PlainParticelleCatastali particelle, int idIntervento ) {
		
		//delete particelle 
		//int deleted = intervTrasfDAO.deleteIntervTrasfByFkIntervento(idIntervento);
		//logger.info("intervTrasf deleted: " + deleted);		
		IntervTrasf intervTrasf = new IntervTrasf();
		intervTrasf.setFkIntervento(idIntervento);
		intervTrasf.setDataInserimento(LocalDate.now());
		logger.info("saveParticelleToIntervTrasf " + objectToString(particelle));
		
		//for (PlainParticelleCatastali part : listParticelle) {
			// intervTrasf.setGeometria(sigmater.getGeometryFromParticelleCatastali(part));
			intervTrasf.setGeometria(particelle.getGeometry());
			intervTrasfDAO.insertIntervTrasf(intervTrasf);
			//logger.info("intervTrasf deleted: " + deleted);		
		//}
			
		//intervTrasfDAO.insertIntervTrasf(intervTrasf);
	}
	
	
	private static JsonNode convertToUTM_333(JsonNode geoJsonNode) {
        ArrayNode coordinatesArray = (ArrayNode) geoJsonNode.get("coordinates").get(0);
        ArrayNode newCoordinatesArray = JsonNodeFactory.instance.arrayNode();

        // Recorre todas las coordenadas del GeoJSON y aplica la transformación punto a punto
        for (JsonNode coordinate : coordinatesArray) {
            double longitude = coordinate.get(0).asDouble();
            double latitude = coordinate.get(1).asDouble();

            // Aplica la proyección Transversa de Mercator (UTM) para convertir a UTM
            double k0 = 0.9996; // Factor de escala
            double a = 6378137.0; // Semieje mayor de WGS84
            double e = 0.081819191; // Excentricidad de WGS84
            double n = 0.725607765053267; // Radio de curvatura de primer vertical

            double latRad = Math.toRadians(latitude);
            double lonRad = Math.toRadians(longitude);
            double lon0Rad = Math.toRadians(9.0); // Meridiano central de la zona UTM 32N

            double cosLat = Math.cos(latRad);
            double sinLat = Math.sin(latRad);
            double tanLat = Math.tan(latRad);

            double N = a / Math.sqrt(1 - e * e * sinLat * sinLat);
            double T = tanLat * tanLat;
            double C = e * e * cosLat * cosLat / (1 - e * e);
            double A = (lonRad - lon0Rad) * cosLat;
            double M = a * ((1 - e * e / 4 - 3 * e * e * e * e / 64 - 5 * e * e * e * e * e * e / 256) * latRad
                    - (3 * e * e / 8 + 3 * e * e * e * e / 32 + 45 * e * e * e * e * e * e / 1024) * Math.sin(2 * latRad)
                    + (15 * e * e * e / 256 + 45 * e * e * e * e * e / 1024) * Math.sin(4 * latRad)
                    - (35 * e * e * e * e * e * e / 3072) * Math.sin(6 * latRad));

            double Easting = k0 * N * (A + (1 - T + C) * A * A * A / 6
                    + (5 - 18 * T + T * T + 72 * C - 58 * n) * A * A * A * A * A / 120) + 500000;

            double Northing = k0 * (M + N * tanLat * (A * A / 2
                    + (5 - T + 9 * C + 4 * C * C) * A * A * A * A / 24
                    + (61 - 58 * T + T * T + 600 * C - 330 * n) * A * A * A * A * A * A / 720));

            // Crea un nuevo ArrayNode con las coordenadas UTM y agrégalo al nuevo ArrayNode de coordenadas
            ArrayNode newCoordinate = JsonNodeFactory.instance.arrayNode();
            newCoordinate.add(Easting);
            newCoordinate.add(Northing);
            newCoordinatesArray.add(newCoordinate);
        }

        // Crea un nuevo objeto JsonNode con el ArrayNode de coordenadas transformadas
        ObjectNode convertedGeoJson = JsonNodeFactory.instance.objectNode();
        convertedGeoJson.put("type", geoJsonNode.get("type").asText());
        convertedGeoJson.set("coordinates", JsonNodeFactory.instance.arrayNode().add(newCoordinatesArray));

        return convertedGeoJson;
    }
}
