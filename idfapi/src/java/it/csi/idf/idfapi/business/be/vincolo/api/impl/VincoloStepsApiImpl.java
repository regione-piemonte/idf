/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.api.impl;

import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import it.csi.idf.idfapi.business.be.exceptions.CustomValidator;
import it.csi.idf.idfapi.business.be.exceptions.MultiErrorException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.PlainAdpforHomeApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigurationDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaForestDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ParticellaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PlainSoggettoIstanzaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropCatastoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropcatastoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.RicadenzeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.UserSessionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperIstanzaDAO;
import it.csi.idf.idfapi.business.be.service.helper.ParkApiServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.SigmaterServiceHelper;
import it.csi.idf.idfapi.business.be.vincolo.api.VincoloStepsApi;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfCnfTipoallTipointDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfGeoPlIntervVincidroDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfTIntervVincIdroDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.SkOkVincoloDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIntervVincIdro;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep1DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep2DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep3DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep4DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep5DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep6DTO;
import it.csi.idf.idfapi.business.be.vincolo.service.WrapperAdpVincoloHomeDAO;
import it.csi.idf.idfapi.business.be.vincolo.service.WrapperVincoloStepsDao;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.dto.InfoDocsPagamenti;
import it.csi.idf.idfapi.dto.Intervento;
import it.csi.idf.idfapi.dto.IstanzaForest;
import it.csi.idf.idfapi.dto.IstanzaTaglio;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PlainSecondoSezione;
import it.csi.idf.idfapi.dto.PlainSezioneId;
import it.csi.idf.idfapi.dto.ProfessionistaElencoAllegati;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TipoIntervento;
import it.csi.idf.idfapi.errors.Error;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.rest.BaseResponses;
import it.csi.idf.idfapi.util.AmbitoIstanzaEnum;
import it.csi.idf.idfapi.util.Constants;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.ProceduraEnum;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella;

public class VincoloStepsApiImpl extends SpringSupportedResource implements VincoloStepsApi {
	public static final String LOGGED_CONFIG = "confUtente";
	
	private static final String SOGLIA_SUPERFICI_INTERVENTO_VINCOLO_IDROGEOLOGICO = "SOGLIA_SUPERFICI_INTERVENTO_VINCOLO_IDROGEOLOGICO";
	private static final String SOGLIA_VOLUMI_INTERVENTO_VINCOLO_IDROGEOLOGICO = "SOGLIA_VOLUMI_INTERVENTO_VINCOLO_IDROGEOLOGICO";
	
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
	
	public static RicadenzaInformazioni fillRicadenzaFromString(String ricadenzaStr) {
		RicadenzaInformazioni item = new RicadenzaInformazioni();
		int idx = ricadenzaStr.indexOf(" ");
		item.setCodiceAmministrativo(ricadenzaStr.substring(0,idx));
		item.setNome(ricadenzaStr.substring(idx + 1));
		return item;
	}
	
	@Autowired
	private InterventoDAO interventoDAO;
	
	@Autowired
	private ConfigurationDAO configurationDAO;
	
	@Autowired
	private TipoInterventoDAO tipoInterventoDAO;
	
	@Autowired
	private IstanzaForestDAO istanzaForestDAO;
	
	@Autowired
	private IdfTIntervVincIdroDAO idfTIntervVincIdroDAO;

	@Autowired
	private ParkApiServiceHelper parkApiServiceHelper;
	
	@Autowired
	private PlainSoggettoIstanzaDAO plainSoggettoIstanzaDAO;// to change in VincoloDao type
	
	@Autowired
	private PropCatastoDAO propCatastoDAO;
	
	@Autowired
	private PropcatastoInterventoDAO propcatastoInterventoDAO;
	
	@Autowired
	private RicadenzeDAO ricadenzeDAO;

	@Autowired
	private SigmaterServiceHelper sigmaterServiceHelper;

	@Autowired
	private SoggettoDAO soggettoDAO;
	
	@Autowired
	private UserSessionDAO userSessionDAO;
	
	@Autowired
	private WrapperAdpforHomeDAO wrapperAdpforHomeDAO;

	@Autowired
	private WrapperAdpVincoloHomeDAO wrapperAdpVincoloHomeDAO;

	@Autowired
	private WrapperIstanzaDAO wrapperIstanzaDAO;// to change in VincoloDao type

	@Autowired
	private WrapperVincoloStepsDao wrapperVincoloStepsDao;
	
	@Autowired
	IdfCnfTipoallTipointDAO idfCnfTipoallTipointDAO;
	
	@Autowired
	SkOkVincoloDAO skOkVincoloDAO;
	
	@Autowired
	IdfGeoPlIntervVincidroDAO idfGeoPlIntervVincidroDAO;
	
	@Autowired
	DocumentoAllegatoDAO documentoAllegatoDAO;

	@Autowired
	ParticellaDAO particella;

	public VincoloStepsApiImpl() {
		super();
	}
	
	@Override
	public Response getInfoCauzione(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			InfoDocsPagamenti info = new InfoDocsPagamenti();
			info.setCauzioneMancante(idfTIntervVincIdroDAO.isCauzioneMancante(idIntervento));
			String compensazioneType = idfTIntervVincIdroDAO.findById(new BigDecimal(idIntervento)).getFlgCompensazione();
			info.setCompensazioneType(compensazioneType);
			if("M".equals(compensazioneType)) {
				info.setCompensazioneMonetariaMancante(idfTIntervVincIdroDAO.isCompensazioneMonetariaMancante(idIntervento));
			}else if("F".equals(compensazioneType)) {
				info.setCompensazioneFisicaMancante(idfTIntervVincIdroDAO.isCompensazioneFisicaMancante(idIntervento));
			}
			info.setListDocs(documentoAllegatoDAO.getTipiDocumentoPagamentoVincolo());
			return Response.ok(info).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body,
			HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.associareDocumenti(idIntervento, body, confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response checkAndInsertParticellaVincolo(Integer idIntervento, PlainParticelleCatastali particellaCatasto, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			TSoggetto soggetto = soggettoDAO.findSoggettoById(confUtente.getFkSoggetto());
			/* 777
			InfoParticella[] list = this.sigmaterServiceHelper.getDettaglioDatiTerrenoGeometrie(
					particellaCatasto.getComune().getIstatComune(), 
					particellaCatasto.getSezione(),particellaCatasto.getFoglio().toString(), 
					particellaCatasto.getParticella(), soggetto.getCodiceFiscale());
			
			if(list == null || list.length == 0) {
				Map<String,String> error = new HashMap<String,String>();
				error.put("error","Nessuna particella trovata con i dati inseriti!");
				return Response.ok(error).build();
			}
			InfoParticella infoParticella = list[0];
			logger.info("Trovato particella: " + objectToString(infoParticella));
			*/
			// 777 --- inicio
			JsonNode jsonNode;
			String s = "";
			try {
				s = particella.findParticellaById(particellaCatasto.getComune().getCodiceBelfiore(), particellaCatasto.getSezione(),particellaCatasto.getFoglio().toString(), particellaCatasto.getParticella()); //geoJSON standard PostGIS
				
	            ObjectMapper objectMapper = new ObjectMapper();
	            jsonNode = objectMapper.readTree(s);

				System.out.println("--jsonNode Idrogeologico --");
				System.out.println(jsonNode.get("geometry"));

				ObjectMapper objectMapper2 = new ObjectMapper();
				JsonNode geoJsonNode = objectMapper2.readTree(jsonNode.get("geometry").toString());
					///
				StringBuilder sql = new StringBuilder();
				sql.append("select ST_AsText(ST_Transform(ST_SetSRID(ST_GeomFromGeoJSON(?),4326),32632))");
				String converteString = DBUtil.jdbcTemplate.queryForObject(sql.toString(), String.class, jsonNode.get("geometry").toString());
				System.out.println("converteString-------------: " + converteString);
					////
				////AREA
				
				StringBuilder sqlArea = new StringBuilder();
				sqlArea.append("SELECT ROUND((SELECT (ST_Area(?))/10000)::numeric, 4)");
				BigDecimal areaBigDecimal = DBUtil.jdbcTemplate.queryForObject(sqlArea.toString(), BigDecimal.class, converteString);
				System.out.println("areaBigDecimal-------------: " + areaBigDecimal);

	            JsonNode convertedGeoJson = convertToUTM_333(geoJsonNode);
	            
	            System.out.println("--Convertido a UTM--");
	            System.out.println(convertedGeoJson.toString());
	    		
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
			// 777 --- fin
			
			// 777 porta particellaCatasto.setFkConfigUtente(confUtente.getIdConfigUtente());
			// 777 porta particellaCatasto.setSupCatastale(new BigDecimal(infoParticella.getArea()/10000,MathContext.DECIMAL64));
			// 777 porta particellaCatasto.setGeometry(infoParticella.getGeometriaGML());
			
			particellaCatasto = propCatastoDAO.insertParticella(particellaCatasto);
			wrapperVincoloStepsDao.insertParticlesInPropcatastoIntervento(idIntervento, particellaCatasto, confUtente);
			// 777 idfTIntervVincIdroDAO.addGeometria(idIntervento, infoParticella.getGeometriaGML());
			idfTIntervVincIdroDAO.addGeometria(idIntervento, particellaCatasto.getGeometry());
			
			return Response.ok(particellaCatasto).build();
		} catch(DuplicateKeyException de) {
			de.printStackTrace();
			return Response.serverError().entity(de.getMessage()).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
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
	
	@DELETE
	@Path("/{idIntervento}/delete/{stepNum}")
	// @Consumes({MediaType.APPLICATION_JSON})
	// public void delete(@PathParam("idIntervento") Integer idIntervento) {
	public Response delete(@PathParam("idIntervento") Integer idIntervento, @PathParam("stepNum") Integer stepNum) {
		// wrapperVincoloStepsDao.deleteById(idIntervento);
		boolean deleted = wrapperVincoloStepsDao.deleteStepById(idIntervento, stepNum);
		if (deleted) {
			// Actually deleted (found and deleted) => 204 "No Content" because no body in
			// the response
			return Response.status(Status.NO_CONTENT).build();
		} else {
			// Not deleted with no error => 404 "Not found"
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@Override
	public Response deleteIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		wrapperVincoloStepsDao.deleteIntervento(idIntervento);
		return Response.ok().build();
	}

	@Override
	public List<VincoloStep1DTO> findAll() {
		return wrapperVincoloStepsDao.findAll();
	}

	@Override
	public Response getCeoIstanza(Integer idIntervento, HttpServletRequest httpRequest) {

		try {
			return Response.ok(wrapperIstanzaDAO.getCeoIstanzaVincolo(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getDataInvioIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getDataInvioIstanza(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getIstanzeForConfigUtente(int tipoIstanzaId, int page, int limit, String sort, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {

		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			
			return Response.ok(plainSoggettoIstanzaDAO.getAllIstancesVincolo(confUtente,confUtente.getFkTipoAccreditamento(), 
					AmbitoIstanzaEnum.VINCOLO_IDROGEOLOGICO.getValue(),tipoIstanzaId,  page, limit, sort)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getNumberOfNextStep(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperVincoloStepsDao.getNumberOfNextStep(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getStep1(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperVincoloStepsDao.getStep1(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
	
	@Override
	public Response getStep2(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperVincoloStepsDao.getStep2(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getStep3(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperVincoloStepsDao.getStep3(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getStep4(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperVincoloStepsDao.getStep4(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getStep5(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperVincoloStepsDao.getStep5(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getStep6(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperVincoloStepsDao.getStep6(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getStepMenu(Integer idIntervento, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getTuttiElencaForIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			boolean isGestore = confUtente.getFkProfiloUtente() == ProfiloUtenteEnum.GESTORE.getValue();
			return Response.ok(wrapperVincoloStepsDao.getTuttiElencaForIntervento(idIntervento, isGestore)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getDocsGestoreByIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperVincoloStepsDao.getDocsGestoreByIntervento(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getUtenteForIstanza(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getUtenteForIstanza(idIntervento)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body,
			HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.invioIstanzaVincolo(idIntervento, body,
					soggettoDAO.findSoggettoById(confUtente.getFkSoggetto()),confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response recalculateParticelleIntervento(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			TSoggetto soggetto = soggettoDAO.findSoggettoById(confUtente.getFkSoggetto());
			wrapperVincoloStepsDao.recalculateParticelleIntervento(idIntervento, soggetto.getCodiceFiscale(), confUtente);
			skOkVincoloDAO.deleteByIdIntervento(idIntervento);
			skOkVincoloDAO.insertFlgSez1(idIntervento);
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(idIntervento).build();
	}

	@Override
	public Response returnRicadenze(Integer idIntervento, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) {
		logger.info("returnRicadenze - idIntervento: " + idIntervento);
		List<String> listGeomGML = idfTIntervVincIdroDAO.getGeometrieGmlByFkIntervento(idIntervento);
		
		PlainSecondoSezione secondSezione = new PlainSecondoSezione();
		
		secondSezione.setRicadenzaNatura2000(new ArrayList<RicadenzaInformazioni>());
		secondSezione.setRicadenzaAreeProtette(new ArrayList<RicadenzaInformazioni>());
		for(String geomGML : listGeomGML) {
		
			try {
				String natura2000ListStr = parkApiServiceHelper.getRicadenzaNatura2000(geomGML);
				logger.info("getRicadenzaNatura2000 >" + natura2000ListStr + "<");
				secondSezione.getRicadenzaNatura2000().addAll(fillListRicadenzeFromString(natura2000ListStr));
				String areeProtetteListStr = parkApiServiceHelper.getRicadenzaAreeProtette(geomGML);
				logger.info("getRicadenzaAreeProtette >" + areeProtetteListStr + "<");
				secondSezione.getRicadenzaAreeProtette().addAll(fillListRicadenzeFromString(areeProtetteListStr));
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		secondSezione.setRicadenzaPopolamentiDaSeme(ricadenzeDAO.getRicadenzePopolamentiSeme(idIntervento,ProceduraEnum.GESTIONE_ISTANZE_FORESTALI_VINCOLO));
//		Double areaVincIdro = ricadenzeDAO.getRicadenzeVincoloIdrogeologico(idIntervento,ProceduraEnum.GESTIONE_ISTANZE_FORESTALI_VINCOLO);
//		secondSezione.setRicadenzaVincoloIdrogeologico(areaVincIdro != null && areaVincIdro > 0);
		secondSezione.setRicadenzaCategorieForestali(ricadenzeDAO.getRicadenzeForestali(idIntervento,ProceduraEnum.GESTIONE_ISTANZE_FORESTALI_VINCOLO));
		
		secondSezione.setTotaleSuperficieCatastale((new BigDecimal(propcatastoInterventoDAO.getAreaCatastaleByIdIntervento(idIntervento))).setScale(4, RoundingMode.HALF_DOWN));
		secondSezione.setTotaleSuperficieTrasf((new BigDecimal(idfTIntervVincIdroDAO.getAreaTrasformazioneByFkIntervento(idIntervento))).divide(BigDecimal.valueOf(10000)).setScale(4, RoundingMode.HALF_DOWN));
//		if(secondSezione.getTotaleSuperficieCatastale().compareTo(secondSezione.getTotaleSuperficieTrasf()) < 0){
//			secondSezione.setTotaleSuperficieTrasf(secondSezione.getTotaleSuperficieCatastale());
//		}
		secondSezione.setTotaleSuperficieBoscata((new BigDecimal(idfTIntervVincIdroDAO.getAreaSuperficieBoscataByFkIntervento(idIntervento))).divide(BigDecimal.valueOf(10000)).setScale(4, RoundingMode.HALF_DOWN));
		secondSezione.setTotaleSuperficieNonBoscata(secondSezione.getTotaleSuperficieTrasf().subtract(secondSezione.getTotaleSuperficieBoscata()).setScale(4, RoundingMode.HALF_DOWN));
		secondSezione.setTotaleSuperficieInVincolo((new BigDecimal(idfTIntervVincIdroDAO.getAreaSuperficieInVincoloByFkIntervento(idIntervento))).divide(BigDecimal.valueOf(10000)).setScale(4, RoundingMode.HALF_DOWN));
		secondSezione.setTotaleSuperficieBoscataInVincolo((new BigDecimal(idfTIntervVincIdroDAO.getAreaSuperficieBoscataInVincoloByFkIntervento(idIntervento))).divide(BigDecimal.valueOf(10000)).setScale(4, RoundingMode.HALF_DOWN));
		secondSezione.setTotaleSuperficieNonBoscataInVincolo(secondSezione.getTotaleSuperficieInVincolo().subtract(secondSezione.getTotaleSuperficieBoscataInVincolo()).setScale(4, RoundingMode.HALF_DOWN));
		secondSezione.setRicadenzaVincoloIdrogeologico(secondSezione.getTotaleSuperficieInVincolo() != null && secondSezione.getTotaleSuperficieInVincolo().longValue() > 0);
		return Response.ok(secondSezione).build();
	}

	@Override
	public Response saveStep1(VincoloStep1DTO body, @Context SecurityContext securityContext,
			@Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		
		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
		
		try {
			validateBodyStep1(body, confUtente);
			PlainSezioneId record = wrapperVincoloStepsDao.saveStep1(body, confUtente);
			return Response.status(Status.CREATED).entity(record).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveStep2(@PathParam("idIntervento") Integer idIntervento, VincoloStep2DTO body,
			@Context SecurityContext securityContext, @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {

		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
		
		try {
			if(wrapperVincoloStepsDao.exists(body)) return Response.status(Status.CONFLICT).build();
			else {
				VincoloStep2DTO record = wrapperVincoloStepsDao.saveStep2(body, confUtente, idIntervento);
				return Response.status(Status.CREATED).entity(record).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveStep3(Integer idIntervento, VincoloStep3DTO body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
					throws ValidationException, RecordNotUniqueException{
		
		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
		
		//validazione
		
		Intervento intervento = interventoDAO.findInterventoByIdIntervento(idIntervento);
		if (intervento == null) {
			return Response.serverError().build();
		}
		
		// volumi_scavo > SOGLIA_VOLUMI_INTERVENTO_VINCOLO_IDROGEOLOGICO altrimenti
		// superficie > SOGLIA_SUPERFICI_INTERVENTO_VINCOLO_IDROGEOLOGICO altrimenti
		// comuni.lenght > 1) altrimenti
		// tipologia
		Integer sogliaVolumi = 0;
		Double sogliaSuperfici = 0d;
		String parSogliaVolumi = configurationDAO.getConfigurationNumberByName(SOGLIA_VOLUMI_INTERVENTO_VINCOLO_IDROGEOLOGICO);
		if (parSogliaVolumi != null) {
			try {
				sogliaVolumi = Integer.valueOf(parSogliaVolumi);
			} catch (NumberFormatException e) {
				logger.error("parametro SOGLIA_VOLUMI_INTERVENTO_VINCOLO_IDROGEOLOGICO non configurato");
			}
		}
		String parSogliaSuperfici = configurationDAO.getConfigurationNumberByName(SOGLIA_SUPERFICI_INTERVENTO_VINCOLO_IDROGEOLOGICO);
		if (parSogliaSuperfici != null) {
			try {
				sogliaSuperfici = Double.valueOf(parSogliaSuperfici)/10000;
			} catch (NumberFormatException e) {
				logger.error("parametro SOGLIA_SUPERFICI_INTERVENTO_VINCOLO_IDROGEOLOGICO non configurato");
			}
		}
		
		boolean competenzaComunale = false;
		IstanzaForest istanza = istanzaForestDAO.getByIdIntervento(idIntervento);

		if (! (body.getTotaleTotMovimentiTerra().compareTo(sogliaVolumi) > 0) ) {
			double x = intervento.getSuperficieInteressata().doubleValue() - sogliaSuperfici;
			if (! (intervento.getSuperficieInteressata().compareTo(BigDecimal.valueOf(sogliaSuperfici)) > 0) ) {
				int numComuniInteressati = interventoDAO.getCountLocalita(idIntervento);
				if( numComuniInteressati == 1 ) { //singola località
					Integer idTipoIntervento = body.getTipoIntervento();
					List<TipoIntervento> tipiIntervento = tipoInterventoDAO.findAllTipoInterventoCompetenzaRegionale();
					competenzaComunale = true;
					for (TipoIntervento tipoIntervento : tipiIntervento) {
						if (tipoIntervento.getIdTipoIntervento().equals(idTipoIntervento)){
							competenzaComunale = false;
							break;
						}
					}
				}
			}
		}

		Error err = new Error();
		if ( (istanza.getFkTipoIstanza().equals(TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE.getTypeValue()) && competenzaComunale) 
				||
		     (istanza.getFkTipoIstanza().equals(TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_COMUNALE.getTypeValue()) && !competenzaComunale) ){
			err.setCode(ErrorConstants.BAD_INPUT_PARAMETERS_400);
			err.setErrorMessage(ErrorConstants.COMPETENZA_NON_CONGRUENTE);
		}
		
		try {
			VincoloStep3DTO record = wrapperVincoloStepsDao.saveStep3(idIntervento, confUtente, body);
			return Response.status(Status.OK).entity(err == null? record:err).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response saveStep4(Integer idIntervento, VincoloStep4DTO body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {

		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
		
		try {
			VincoloStep4DTO record = wrapperVincoloStepsDao.saveStep4(idIntervento, confUtente, body);
			return Response.status(Status.OK).entity(record).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response saveStep5(Integer idIntervento, VincoloStep5DTO body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {

		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
		
		try {
			VincoloStep5DTO record = wrapperVincoloStepsDao.saveStep5(idIntervento, confUtente, body);
			return Response.status(Status.OK).entity(record).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	
	@Override
	public Response saveStep6(Integer idIntervento, VincoloStep6DTO body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {

		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
		
		try {
			VincoloStep6DTO record = wrapperVincoloStepsDao.saveStep6(idIntervento, confUtente, body);
			return Response.status(Status.OK).entity(record).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}


	@Override
	public Response updateIstanzaRifiutata(Integer idIntervento, String motivazioneRifiuto, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.updateIstanzaRifiutata(idIntervento,motivazioneRifiuto,
					soggettoDAO.findSoggettoById(confUtente.getFkSoggetto()),confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response updateIstanzaVerificata(Integer idIntervento, HttpServletRequest httpRequest) {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

			wrapperIstanzaDAO.updateIstanzaVerificata(idIntervento,
					soggettoDAO.findSoggettoById(confUtente.getFkSoggetto()),confUtente);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response updateStep1(Integer idIntervento, VincoloStep1DTO body,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException, RecordNotUniqueException {
		// force the id (use the id provided by the URL)
		try {
			body.setIdIntervento(idIntervento);
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			validateBodyStep1(body, confUtente);

			boolean updated = wrapperVincoloStepsDao.updateStep1(body, confUtente, idIntervento);
		} catch (MultiErrorException e) {
			e.printStackTrace();
			return BaseResponses.errorResponse(e);
			// Not updated with no error => 404 not found
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} // Actually updated (found and updated) => 200 OKeturn
		return Response.status(Status.OK).build();
	}
	
	@Override
	public Response updateStep2(@PathParam("idIntervento") Integer idIntervento,
			VincoloStep2DTO vincoloDto, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException {
		ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);

		vincoloDto.setIdIntervento(idIntervento);
		wrapperVincoloStepsDao.saveStep2(vincoloDto, confUtente, idIntervento);
		return Response.status(Status.OK).build();
	}
	
	@Override
	public Response getSuperfici(@PathParam("idIntervento") Integer idIntervento,
			SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException {
		try {
			return Response.ok(wrapperVincoloStepsDao.getSuperfici(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getCauzione(@PathParam("idIntervento") Integer idIntervento,
			SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws ValidationException, RecordNotUniqueException {
		try {
			return Response.ok(wrapperVincoloStepsDao.getCauzione(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getParametro(@PathParam("parametro") String parametro,
			SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws RecordNotUniqueException {
		try {
			switch(parametro) {
				case Constants.BASE_CALCOLO_EURO_CAUZIONE_VINCOLO_IDROGEOLOGICO:
					return Response.ok(wrapperVincoloStepsDao.getCoefficienteCalcoloSupNonBoscata()).build();
				
				case Constants.COEFF_CALCOLO_EURO_SUP_NON_BOSCATA_LR45:
					return Response.ok(wrapperVincoloStepsDao.getCoefficienteCalcoloSupNonBoscata()).build();
				
				case Constants.VALORE_MARCA_DA_BOLLO:
					return Response.ok(wrapperVincoloStepsDao.getValoreMarcaBollo()).build();
					
				default: 
					return Response.serverError().build();
			}
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	public void validateBodyStep1(VincoloStep1DTO body, ConfigUtente confUtente) {
		MultiErrorException errCheck = CustomValidator.getValidator(HttpStatus.BAD_REQUEST)
				.errorIfNULL("ConfigUtente", confUtente).codiceIsValid("codiceFiscaleDelega", body.getCodiceFiscale())
				.errorIfNULL("TipoIstanzaId", body.getTipoIstanzaId())
				.errorIfNULL("TipoIstanzaDescr", body.getTipoIstanzaDescr())
				.errorIfNULL("PersonaDatiOption", body.getPersonaDatiOption());
		
		if(body.getPersonaDatiOption().toString().endsWith("F")) // Persona fisica.
			errCheck.errorIfNULL("Cognome", body.getCognome())
				.errorIfNULL("Nome", body.getNome())
				.errorIfNULL("CodiceFiscale", body.getCodiceFiscale());
		else // Persona giuridica.
			errCheck.errorIfNULL("PartitaIva", body.getPartitaIva())
				.errorIf("PartitaIva", body.getPartitaIva().length() > 11, "Partita IVA conflict")
				.errorIfNULL("Pec", body.getPec())
				.errorIf("body.getPec().length() > 50", body.getPec().length() > 50, "Pec length() > 50")
				.errorIfNULL("Denominazione", body.getDenominazione())
				.errorIf("Denominazione", body.getDenominazione().length() > 200, "Denominazione length > 200");
		
		errCheck.errorIfNULL("Indirizzo", body.getIndirizzo())
				.errorIfNULL("NrTelefonico", body.getNrTelefonico())
				.errorIfNULL("Email", body.geteMail())
				.errorIfNULL("Civico", body.getCivico())
				.errorIfNULL("Cap", body.getCap())
				.errorIfNULL("Comune", body.getComune())
				.errorIf("Indirizzo", body.getIndirizzo().length() > 50, "Indirizzo length >50")
				.errorIf("Civico", body.getCivico().length() > 10, "Civico length > 10")
				.errorIf("Cap", body.getCap().length() != 5, "Cap length > 10")
				.errorIf("NrTelefonico", body.getNrTelefonico().length() > 20, "NrTelefonico length > 20")
				.errorIf("Email", body.geteMail().length() > 50, "Email length > 50").go();
	}

	public Response getPlainHome(Integer idTipoIstanza, SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperAdpVincoloHomeDAO.getAdpforHome(userSessionDAO.getUser(httpRequest),1,idTipoIstanza)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@Override
	public Response getTipiDocumentoByIdIntervento(@PathParam("idIntervento") Integer idIntervento,
			SecurityContext securityContext, HttpHeaders httpHeaders,
			HttpServletRequest httpRequest) throws ValidationException {
		
		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDAO.findById(new BigDecimal(idIntervento));

		if (idfTIntervVincIdro != null) {
			try {
				return Response.ok(idfCnfTipoallTipointDAO.getTipiDocumentoByIdTipoIntervento(idfTIntervVincIdro.getFkTipoIntervento())).build();
			} catch(Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		} else {
			return Response.serverError().build();
		}

	}

	@Override
	public Response creaVarianteProroga(Integer idIntervento, Boolean isVariante,  Boolean isCompetenzaRegionale,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest) 
			throws ValidationException {
		Integer idInterventoNew = null;
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			
			VincoloStep1DTO step1 = wrapperVincoloStepsDao.getStep1(idIntervento);
			step1.setIdIntervento(null);
			if(isVariante) {
				step1.setFkInterventoPadreVariante(idIntervento);
			}else {
				step1.setFkInterventoPadreProroga(idIntervento);
			}
			TipoIstanzaEnum tipoIstanza = isCompetenzaRegionale? 
					TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE:TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_COMUNALE;
			step1.setTipoIstanzaId(tipoIstanza.getTypeValue());
			PlainSezioneId res = wrapperVincoloStepsDao.saveStep1(step1, confUtente);
			idInterventoNew = res.getIstanzaId();
			logger.info("creaVarianteProroga - idInterventoNew: " + idInterventoNew);
			idfGeoPlIntervVincidroDAO.duplicateIntervento(idIntervento, idInterventoNew, confUtente.getIdConfigUtente());
			
			VincoloStep2DTO step2 = wrapperVincoloStepsDao.getStep2(idIntervento);
			step2.setIdIntervento(idInterventoNew);
			wrapperVincoloStepsDao.saveStep2(step2, confUtente, idInterventoNew);
			VincoloStep3DTO step3 = wrapperVincoloStepsDao.getStep3(idIntervento);
			wrapperVincoloStepsDao.saveStep3(idInterventoNew, confUtente, step3);
			VincoloStep4DTO step4 = wrapperVincoloStepsDao.getStep4(idIntervento);
			wrapperVincoloStepsDao.saveStep4(idInterventoNew, confUtente, step4);
			VincoloStep5DTO step5 = wrapperVincoloStepsDao.getStep5(idIntervento);
			wrapperVincoloStepsDao.saveStep5(idInterventoNew, confUtente, step5);
			VincoloStep6DTO step6 = wrapperVincoloStepsDao.getStep6(idIntervento);
			wrapperVincoloStepsDao.saveStep6(idInterventoNew, confUtente, step6);
			
			//duplica allegati
			if(!isVariante) {
				List<DocumentoAllegato> listDocs = documentoAllegatoDAO.findDocumentiByIdIntervento(idIntervento);
				for(DocumentoAllegato doc : listDocs) {
					try {
						if(doc.getFkTipoAllegato() != TipoAllegatoEnum.RICHIESTA_AUTORIZZAZIONE_AUTOGRAFA.getValue() 
								&& doc.getFkTipoAllegato() != TipoAllegatoEnum.RICHIESTA_AUTORIZZAZIONE_DIGITALE.getValue()
								&& doc.getFkTipoAllegato() != TipoAllegatoEnum.DOCUMENTO_IDENTITA.getValue()) {
							wrapperVincoloStepsDao.duplicaDocumento(doc, idInterventoNew, confUtente);
						}
					} catch(Exception ex) {
						logger.error("Errore nella dupplicazione del file id: " + doc.getIdDocumentoAllegato() 
						+ " - idTipoAllegato: " + doc.getFkTipoAllegato() );
					}
				}
			}
			
			if(isVariante) {
				skOkVincoloDAO.initVariante(idInterventoNew);
			}

			return Response.ok(res).build();
		} catch(Exception e) {
			e.printStackTrace();
			if(idInterventoNew != null) {
				wrapperVincoloStepsDao.deleteIntervento(idInterventoNew);
			}
			return Response.serverError().build();
		}
	}

	@Override
	public Response getInfoVarianteProroga(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws ValidationException {
		try {
			return Response.ok(idfTIntervVincIdroDAO.getInfoVarianteProroga(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException {
		try {
			idfTIntervVincIdroDAO.updateTitolaritaIntervento(idIntervento, idConfUtente);
			return Response.ok(wrapperIstanzaDAO.getIstanzaInviata(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response getIstanzeTaglioByIdIntervento(Integer idIntervento, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) {
		try {
			return Response.ok(wrapperIstanzaDAO.getIstanzeTaglioByIdIntervento(idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@Override
	public Response saveOrDeleteIstanzaTaglio(Integer idIntervento, IstanzaTaglio istanzaTaglio,
			SecurityContext securityContext, HttpHeaders httpHeaders, HttpServletRequest httpRequest)
			throws ValidationException {
		try {
			ConfigUtente confUtente = PlainAdpforHomeApiServiceImpl.getConfUtente(httpRequest, wrapperAdpforHomeDAO, userSessionDAO);
			return Response.ok(wrapperVincoloStepsDao.
					saveOrDeleteIstanzaTaglio(istanzaTaglio, confUtente.getIdConfigUtente(), idIntervento)).build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}



	
}
