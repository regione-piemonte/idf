/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.impl;


//import it.csi.ecogis.util.conversion.GeoJSONGeometryConverter;
//import it.csi.ecogis.util.conversion.GeoJSONGeometryConverter;
import it.csi.idf.idfapi.business.be.exceptions.*;
import it.csi.idf.idfapi.business.be.impl.PlainAdpforHomeApiServiceImpl;
import it.csi.idf.idfapi.business.be.impl.dao.*;
import it.csi.idf.idfapi.business.be.impl.dao.impl.ParticellaDAOImpl;
import it.csi.idf.idfapi.business.be.service.MailService;
import it.csi.idf.idfapi.business.be.service.RicadenzaService;
import it.csi.idf.idfapi.business.be.service.TAIFService;
import it.csi.idf.idfapi.business.be.service.TagliPfaService;
import it.csi.idf.idfapi.business.be.service.helper.IndexServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.ParkApiServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.SigmaterServiceHelper;
import it.csi.idf.idfapi.business.be.vincolo.pojo.*;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.mapper.InfoVarianteProrogaMapper;
import it.csi.idf.idfapi.util.*;
import it.csi.idf.idfapi.util.mail.MailEnum;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.core.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;

@Component
public class TagliPfaServiceImpl extends SpringSupportedResource implements TagliPfaService {

	private static final String SEZIONE_1_VALID_MSG = "Sezione 1 deve essere completata";
	private static final String SEZIONE_2_VALID_MSG = "Sezione 2 deve essere completata";
	private static final String SEZIONE_3_VALID_MSG = "Sezione 3 deve essere completata";
	private static final String SEZIONE_4_VALID_MSG = "Sezione 4 deve essere completata";
	private static final String SEZIONE_5_VALID_MSG = "Sezione 5 deve essere completata";

	@Autowired
	private InterventoDAO interventoDAO;

	@Autowired
	private IntervselEventoDAO intervselEventoDAO;

	@Autowired
	private PartforInterventoDAO partforInterventoDAO;

	@Autowired
	private ParticellaDAO particella;

	@Autowired
	private SigmaterServiceHelper sigmaterServiceHelper;
	
	@Autowired
	private ParkApiServiceHelper parkApiServiceHelper;

	@Autowired
	private IstanzaForestDAO istanzaForestDAO;

	@Autowired
	private SkOkSelvDAO skOkSelvDAO;

	@Autowired
	private SoggettoInterventoDAO soggettoInterventoDAO;

	@Autowired
	private ConfigUtenteDAO configUtenteDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private IntervPfaDAO intervPfaDAO;

	@Autowired
	private WrapperPfaDAO wrapperPfaDAO;

	@Autowired
	private IntervSelvicoulturaleTraferimentiDAO intervSelvicoulturaleTraferimentiDAO;

	@Autowired
	private CategoriaSelvicolturaleDAO categoriaSelvicolturaleDAO;

	@Autowired
	private ProprietaDAO proprietaDAO;

	@Autowired
	private TrasformazioniSelvicolturaliDAO trasformazioniSelvicolturaliDAO;

	@Autowired
	private ComuneDAO comuneDAO;

	@Autowired
	private PfPgDAO pfPgDAO;

	@Autowired
	private DelegaDAO delegaDao;

	@Autowired
	private PropcatastoInterventoDAO propcatastoInterventoDAO;

	@Autowired
	private InterventoAappDAO interventoAappDAO;

	@Autowired
	private InterventoCatforDAO interventoCatforDAO;

	@Autowired
	private InterventoPopSemeDAO interventoPopSemeDAO;

	@Autowired
	private InterventoRn2000DAO interventoRn2000DAO;

	@Autowired
	private PopolamentoSemeDAO popolamentoSemeDAO;

	@Autowired
	private InterventoPortaSemeDAO interventoPortaSemeDAO;
	
	@Autowired
	private InterventoRicadenzaDAO interventoRicadenzaDAO;

	@Autowired
	private PropCatastoDAO propCatastoDAO;

	@Autowired
	private CategoriaForestaleDAO categoriaForestaleDAO;

	@Autowired
	private RicadenzaService ricadenzaService;

	@Autowired
	private IntervTrasformazioneDAO intervTrasformazioneDAO;

	@Autowired
	private GeoPlLottoInterventoDAO geoPlLottoInterventoDAO;

	@Autowired
	private UsoViabilitaDAO usoViabilitaDAO;

	@Autowired
	private TipoViabilitaDAO tipoViabilitaDAO;

	@Autowired
	private EsboscoDAO esboscoDAO;
	@Autowired
	private UsoviabInterventoselvDAO usoviabInterventoselvDAO;

	@Autowired
	private TipoViabUsoviabInterventoDAO tipoViabUsoviabInterventoDAO;

	@Autowired
	private EsboscoIntervselvDAO esboscoIntervselvDAO;

	@Autowired
	SpeciePfaInterventoDAO speciePfaInterventoDao;

	@Autowired
	private SpecieInterventoFinalitaDAO specieInterventoFinalitaDAO;

	@Autowired
	private SpecieDAO specieDAO;

	@Autowired
	private FasciaAltimetricaDAO fasciaAltimetricaDAO;

	@Autowired
	TipoInterventoDAO tipoInterventoDAO;

	@Autowired
	private DocumentoAllegatoDAO documentoAllegatoDAO;

	@Autowired
	private MailService mailService;

	@Autowired
	CnfBoprocLogDAO cnfBoprocLogDAO;

	@Autowired
	DichiarazioneSummaryDAO dichiarazioneSummaryDAO;

	@Autowired
	TipoAllegatoDAO tipoAllegatoDAO;

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	private TAIFService taifService;

	@Override
	public StepNumber getNumberOfNextStep(Integer idIntervento) {
		return new StepNumber(skOkSelvDAO.sumFlgSeziones(idIntervento));
	}

	@Override
	@Transactional
	public void deleteIntervento(Integer idIntervento) {

		List<Integer> idsDocs = documentoAllegatoDAO.getAllDocumentsByIdIntervento(idIntervento);
		for (int id : idsDocs) {
			try {
				documentoAllegatoDAO.deleteDocumentoById(id);
			} catch (Exception ex) {
				logger.info("Errore durante delete documenti id: " + id);
			}
		}

		skOkSelvDAO.deleteByIdIntervento(idIntervento);
		propcatastoInterventoDAO.deleteAllByIdIntervento(idIntervento);
		geoPlLottoInterventoDAO.deleteGeoPlLottoIntervento(idIntervento);
		partforInterventoDAO.deletePartforIntervento(idIntervento);
		deleteViablitaIntervento(idIntervento);
		deleteSpecieInteressate(idIntervento);

		interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);
		interventoAappDAO.deleteAllInterventoPPRByIdIntervento(idIntervento);
		interventoRn2000DAO.deleteInterventosByIdIntervento(idIntervento);
		interventoPopSemeDAO.deleteInterventosByIdIntervento(idIntervento);
		interventoPortaSemeDAO.deleteInterventosByIdIntervento(idIntervento);
		interventoCatforDAO.deleteInterventosById(idIntervento);

		intervSelvicoulturaleTraferimentiDAO.deleteByIdInterventoSelv(idIntervento);
		soggettoInterventoDAO.deleteByIdIntervento(idIntervento);
		istanzaForestDAO.deleteById(idIntervento);
		intervselEventoDAO.deleteIntervselEvento(idIntervento);
		intervPfaDAO.deleteIntervSelvicolturale(idIntervento);
		interventoDAO.deleteIntervento(idIntervento);

	}

    @Override
    public TagliPfaStep1 getStep1(Integer idIntervento) //throws RecordNotFoundException, RecordNotUniqueException {
    {

    	logger.info("Paso getStep1: "+idIntervento);
    	TagliPfaStep1 step1 = new TagliPfaStep1();
    	try {
    	
        IstanzaForest istanzaForest = istanzaForestDAO.getByIdIntervento(idIntervento);
        logger.info("<---- istanzaForest --");
        IntervPfa intSelv = intervPfaDAO.findInterventoSelvicolturaleTagliNew(idIntervento);
        logger.info("<---- intSelvOBJ --"+ intSelv.toString());
        logger.info("<---- intSelv --");
     
                
        step1.setIdIntervento(intSelv.getIdIntervento());
        step1.setFkGoverno(intSelv.getFkGoverno());
        step1.setTipoIstanzaId(0);
        step1.setDataPresaInCarico(intSelv.getDataPresaInCarico());
        step1.setFkTipoIntervento(intSelv.getFkTipoIntervento());

        step1.setAnnataSilvana(intSelv.getAnnataSilvana());
        step1.setFkStatoIntervento(intSelv.getFkStatoIntervento());

        if(intSelv.getFkCategoriaIntervento()!=0) {
	        logger.info("<------ INT cat "+intSelv.getFkCategoriaIntervento()+  "---->");
	        CategoriaSelvicolturale cat = categoriaSelvicolturaleDAO.findById(intSelv.getFkCategoriaIntervento());
	        logger.info("<------ cat :"+cat.toString()+" ---->");	        
	        step1.setCategoriaSelvicolturale(cat);	               
    	}
        if(intSelv.getFkProprieta()!=0) {
        	Proprieta propr = proprietaDAO.findById(intSelv.getFkProprieta());
        	logger.info("<------ propr :"+propr.toString()+" ---->");       
        	step1.setProprieta(propr);        
        }
        List<TrasformazSelvicolturali> trasf = trasformazioniSelvicolturaliDAO.findByIdIntervento(idIntervento);
        if (CollectionUtils.isNotEmpty(trasf)) {
            step1.setTrasformazSelvicolturale(trasf.get(0));
        }  
        step1.setFlgConformeDeroga(intSelv.getFlgConformeDeroga());
        step1.setNrProgressivoInterv(intSelv.getNrProgressivoInterv());
        step1.setIdEvento(intSelv.getIdEvento());
        step1.setIdTipoEvento(intSelv.getIdTipoEvento());
        return step1;
        }
    	catch(Exception e) {
    		e.printStackTrace();
    		logger.info("Error TagliSelvicolturaliStep1 "+ e);
    	}
    	return step1;
    }

    @Override
    @Transactional
    public PlainSezioneId saveStep1(TagliPfaStep1 body, ConfigUtente loggedConfig) throws RecordNotUniqueException, ValidationException {

//        int idIntervento = interventoDAO.createInterventoWithConfigUtente(loggedConfig.getIdConfigUtente());
        int idIntervento = interventoDAO.createInterventoTagliWithConfigUtente(loggedConfig);
        istanzaForestDAO.createIstanzaForest(getIstanzaForest(loggedConfig.getIdConfigUtente(), idIntervento));

        IntervPfa entity = new IntervPfa();
        entity.setIdIntervento(idIntervento);
        entity.setFkTipoIntervento(body.getFkTipoIntervento());
        entity.setFkGoverno(body.getFkGoverno());
        entity.setFkCategoriaIntervento(body.getCategoriaSelvicolturale().getIdCategoriaSelvicolturale());
        entity.setFkProprieta(body.getProprieta().getIdProprieta());
        entity.setFkStatoIntervento(body.getFkStatoIntervento());
        entity.setDataPresaInCarico(body.getDataPresaInCarico());
        entity.setAnnataSilvana(body.getAnnataSilvana());
        entity.setFkConfigUtente(loggedConfig.getIdConfigUtente());

        entity.setFkInterventoPadreProroga(body.getFkInterventoPadreProroga());
        entity.setFkInterventoPadreVariante(body.getFkInterventoPadreVariante());
        
        // GP
        entity.setFlgConformeDeroga(body.getFlgConformeDeroga());
        
        // JC - Step1
        entity.setIdEvento(body.getIdEvento());

        // RCH
        entity.setIdTipoEvento(body.getIdTipoEvento());
        
        intervPfaDAO.insertStep1(entity, idIntervento);

        // insert into flag config
        skOkSelvDAO.insertFlgSez1(idIntervento);

        // insert into R_INT_SELV_TRASF
        if (null != body.getTrasformazSelvicolturale() ) {
            intervSelvicoulturaleTraferimentiDAO.insert(idIntervento, body.getTrasformazSelvicolturale().getIdIstanza(), loggedConfig);
        }
        return new PlainSezioneId(idIntervento);
    }

	@Override
	@Transactional
	public boolean updateStep1(TagliPfaStep1 dto, ConfigUtente loggedConfig, int idIntervento)
			throws ValidationException, RecordNotUniqueException {

		IntervPfa intSelv = intervPfaDAO.findInterventoSelvicolturaleTagliNew(idIntervento);

		intSelv.setFkCategoriaIntervento(dto.getCategoriaSelvicolturale().getIdCategoriaSelvicolturale());
		intSelv.setFkProprieta(dto.getProprieta().getIdProprieta());
		intSelv.setAnnataSilvana(dto.getAnnataSilvana());
		intSelv.setDataPresaInCarico(dto.getDataPresaInCarico());
		intSelv.setFkStatoIntervento(dto.getFkStatoIntervento());
		intSelv.setFkGoverno(dto.getFkGoverno());
		intSelv.setFkConfigUtente(loggedConfig.getIdConfigUtente());

        intSelv.setFlgConformeDeroga(dto.getFlgConformeDeroga());
        intSelv.setNrProgressivoInterv(dto.getNrProgressivoInterv());
        
        intSelv.setIdTipoEvento(dto.getIdTipoEvento());
        intSelv.setIdEvento(dto.getIdEvento());
        
		int result = intervPfaDAO.updateStep1(intSelv, idIntervento);
		        
		// delete and insert into R_INT_SELV_TRASF
		intervSelvicoulturaleTraferimentiDAO.deleteByIdInterventoSelv(idIntervento);
		if (null != dto.getTrasformazSelvicolturale()) {
			intervSelvicoulturaleTraferimentiDAO.insert(idIntervento, dto.getTrasformazSelvicolturale().getIdIstanza(),
					loggedConfig);
		}

		// update flasgs step1
		//skOkSelvDAO.updateFlgSez1(idIntervento);
		return result != 0;
	}

	@Override
	public TagliPfaStep2 getStep2(Integer idIntervento, ConfigUtente loggedConfig)
			throws RecordNotUniqueException {
		TagliPfaStep2 step2 = new TagliPfaStep2();

		PersonaFisGiu personaFisGiu = new PersonaFisGiu();
		PersonaFisGiu utilizzatore = new PersonaFisGiu();

		TSoggetto soggetto;
		// check if soggetto intervento exists
		SoggettoIntervento soggettoIntervento = null;

		try {
			soggettoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
		} catch (RecordNotFoundException e) {
			logger.info("soggetto intervento non ancora inserito ");
		}

		if (null != soggettoIntervento) {
			// step2.setUpdateMode(true);
			step2.setTipoRichiedenteId(soggettoIntervento.getFkTipoRichiedente());

			ConfigUtente cnfUtenteIntervento = configUtenteDAO
					.getCofigUtenteById(soggettoIntervento.getFkConfigUtente());
			step2.setTipoAccreditamento(
					TipoAccreditamentoEnum.fromInteger(cnfUtenteIntervento.getFkTipoAccreditamento()).getText());
			soggetto = soggettoDAO.findSoggettoById(soggettoIntervento.getIdSoggetto());
			soggetto.setNrAlboForestale(soggettoIntervento.getNrAlboForestale());
			if (cnfUtenteIntervento.getFkTipoAccreditamento() == 2) {// professionista
				personaFisGiu
						.setPersonaDatiOption(StringUtils.isEmpty(soggetto.getDenominazione()) ? TipoTitolaritaEnum.PF
								: TipoTitolaritaEnum.PG);
			} else if (cnfUtenteIntervento.getFkTipoAccreditamento() == 3) {
				personaFisGiu
						.setPersonaDatiOption(StringUtils.isEmpty(soggetto.getDenominazione()) ? TipoTitolaritaEnum.SF
								: TipoTitolaritaEnum.SG);
			} else {
				personaFisGiu
						.setPersonaDatiOption(StringUtils.isEmpty(soggetto.getDenominazione()) ? TipoTitolaritaEnum.RF
								: TipoTitolaritaEnum.RG);
			}
			logger.info("Tipo accreditamento: "
					+ (cnfUtenteIntervento.getFkTipoAccreditamento() == 2 ? "Professionista" : "Richiedente")
					+ " - PersonaDatiOption: " + personaFisGiu.getPersonaDatiOption());

			// get utilizzatore
			SoggettoIntervento utilizzIntervento = null;
			try {
				utilizzIntervento = soggettoInterventoDAO
						.getSoggettoInterventoByIdInterventoAndIdTipoSoggetto(idIntervento, 2);
			} catch (RecordNotFoundException e) {
				logger.info("Utilizzatore non trovato");
			}

			if (null == utilizzIntervento) {
				// utilizzatore da definire
				step2.setTipoUtilizzatore(TipoUtilizzatoreTagliEnum.DA_INDIVIDUARE);
			} else {
				TSoggetto utilizzatoreSoggetto = soggettoDAO.findSoggettoById(utilizzIntervento.getIdSoggetto());

				utilizzatoreSoggetto.setNrAlboForestale(utilizzIntervento.getNrAlboForestale());

				if (soggettoIntervento.getIdSoggetto().intValue() == utilizzatoreSoggetto.getIdSoggetto().intValue()) {
					step2.setTipoUtilizzatore(TipoUtilizzatoreTagliEnum.IN_PROPRIO);
				} else if (StringUtils.isEmpty(utilizzatoreSoggetto.getDenominazione())) {
					step2.setTipoUtilizzatore(TipoUtilizzatoreTagliEnum.PERSONA_FISICA);
				} else {
					step2.setTipoUtilizzatore(TipoUtilizzatoreTagliEnum.PERSONA_GIURIDICA);
				}

				// build utilizzatore
				buildPersonaFisGiu(utilizzatore, soggetto, utilizzatoreSoggetto);

				// set iscrizione albo forestale
				utilizzatore.setNumAlboForestale(utilizzIntervento.getNrAlboForestale());
				utilizzatore.setTAIF(null != utilizzIntervento.getNrAlboForestale());

				if (utilizzatore.getTAIF()) {
					TAIFAnagraficaAzienda taif = null;
					try {
						taif = taifService.findByCodiceFiscale(utilizzatore.getCodiceFiscale());
						utilizzatore.setSoggettoTaif(taif);
					} catch (Exception e) {
						logger.error("Errore nel recupero del cf da TAIF ", e);
					}
				}

				step2.setUtilizzatore(utilizzatore);
			}

		} else {
			step2.setTipoUtilizzatore(TipoUtilizzatoreTagliEnum.NO_DATA);

			soggetto = soggettoDAO.findSoggettoById(loggedConfig.getFkSoggetto());
			step2.setTipoAccreditamento(
					TipoAccreditamentoEnum.fromInteger(loggedConfig.getFkTipoAccreditamento()).getText());
			if (loggedConfig.getFkTipoAccreditamento() == 2) {// professionista
				personaFisGiu
						.setPersonaDatiOption(StringUtils.isEmpty(soggetto.getDenominazione()) ? TipoTitolaritaEnum.PF
								: TipoTitolaritaEnum.PG);
			} else {
				personaFisGiu
						.setPersonaDatiOption(StringUtils.isEmpty(soggetto.getDenominazione()) ? TipoTitolaritaEnum.RF
								: TipoTitolaritaEnum.RG);
			}
		}

		buildPersonaFisGiu(personaFisGiu, soggetto, soggetto);
		step2.setSoggetto(personaFisGiu);

		IntervPfaFat inteSelv = intervPfaDAO.findInterventoSelvicolturaleTagli(idIntervento);
		step2.setFkCategoriaIntervento(inteSelv.getFkCategoriaIntervento());

		IstanzaForest istanzaForest = istanzaForestDAO.getByIdIntervento(idIntervento);

		step2.setTipoIstanzaId(istanzaForest.getFkTipoIstanza());
		step2.setTipoIstanzaDescr(TipoIstanzaEnum.getEnum(istanzaForest.getFkTipoIstanza()).toString());

		return step2;
	}

	@Override
	public List<FatDocumentoAllegato> getTuttiElencaForIntervento(Integer idIntervento, boolean isGestore) {
		List<TipoAllegatoPfaEnum> tipiAllegato = new ArrayList<>();
		tipiAllegato.add(TipoAllegatoPfaEnum.COMUNICAZIONE_DI_TAGLIO_IN_BOSCO_DIGIT);
		tipiAllegato.add(TipoAllegatoPfaEnum.RICHIESTA_DI_AUTORIZZAZIONE_DI_TAGLIO_IN_BOSCO_DIGIT);
		tipiAllegato.add(TipoAllegatoPfaEnum.CARTOGRAFIA_AREA_DI_INTERVENTO);
		tipiAllegato.add(TipoAllegatoPfaEnum.CARTOGRAFIA_AREA_DI_INTERVENTO_E_PIANO_PLURIENNALE_DEI_TAGLI);
		tipiAllegato.add(TipoAllegatoPfaEnum.PROGETTO_DI_INTERVENTO);
		tipiAllegato.add(TipoAllegatoPfaEnum.ELABORATI_PROGETTUALI_STRADE_E_PISTE_FORESTALI);
		tipiAllegato.add(TipoAllegatoPfaEnum.VERBALE_DI_SOPRALLUOGO_PREVENTIVO);
		tipiAllegato.add(TipoAllegatoPfaEnum.ALLEGATO_LIBERO_1);
		tipiAllegato.add(TipoAllegatoPfaEnum.ALLEGATO_LIBERO_2);
		tipiAllegato.add(TipoAllegatoPfaEnum.PIEDILISTA);
		tipiAllegato.add(TipoAllegatoPfaEnum.DICHIARAZIONE_DREL);
		tipiAllegato.add(TipoAllegatoPfaEnum.COMUNICAZIONE_DI_TAGLIO_IN_BOSCO_AUTOG);
		tipiAllegato.add(TipoAllegatoPfaEnum.RICHIESTA_DI_AUTORIZZAZIONE_DI_TAGLIO_IN_BOSCO_AUTOG);
		tipiAllegato.add(TipoAllegatoPfaEnum.SCANSIONE_DOC_IDENTITA);
		if (!isGestore) {
			tipiAllegato.add(TipoAllegatoPfaEnum.ALTRO);
		}

		return documentoAllegatoDAO.findDocumentiByIdAndTiposPfaGestoreNew(idIntervento, tipiAllegato);
	}

	@Override
	public InvioIstanzaDTO getDataInvioIstanza(Integer idIntervento) {
		return new InvioIstanzaDTO(istanzaForestDAO.getByIdIntervento(idIntervento).getDataInvio());
	}

	@Override
	public void invioIstanzaTagli(Integer idIntervento, ProfessionistaElencoAllegati body, ConfigUtente confUtente)
			throws RecordNotFoundException, RecordNotUniqueException, Exception {

		intervPfaDAO.updateIntervSelvicolturaleWithStatoIntervento(3,idIntervento);
		istanzaForestDAO.updateInvioIstanza(idIntervento, "NONE");
		mailService.sendMailTagliByIdIntervento(idIntervento, MailEnum.INVIO_ISTANZA_TAGLI);

		// protocollazione
		BoProcLog procLog = new BoProcLog();
		// procLog.setFkIstanza(idIntervento.toString());
		procLog.setFkIstanza(idIntervento + "-1");
		procLog.setFkAmbitoIstanza(AmbitoIstanzaEnum.TAGLIO_IN_BOSCO.getValue());
		procLog.setFkStepBoproc(1);
		procLog.setCountTentativo(0);
		cnfBoprocLogDAO.create(procLog);
	}

	public void invioIstanzaTagliEmail(Integer idIntervento, ProfessionistaElencoAllegati body, ConfigUtente confUtente)
			throws RecordNotFoundException, RecordNotUniqueException, Exception {
		istanzaForestDAO.updateInvioIstanza(idIntervento, "MODIFICA_UTILIZZATORE_TAGLI");
		mailService.sendMailTagliByIdIntervento(idIntervento, MailEnum.MODIFICA_UTILIZZATORE_TAGLI);

	}

	@Override
	public GeneratedFileTagliParameters getCeoIstanza(Integer idIntervento) {

		GeneratedFileTagliParameters genFileParams = dichiarazioneSummaryDAO.getSummaryTagli(idIntervento);
		SoggettoIntervento soggettoIntervento = null;

		try {
			soggettoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
			genFileParams.setFkTipoRichiedente(soggettoIntervento.getFkTipoRichiedente());

			ConfigUtente cnfUtenteIntervento = configUtenteDAO
					.getCofigUtenteById(soggettoIntervento.getFkConfigUtente());
			if (cnfUtenteIntervento.getFkTipoAccreditamento() == 2) {// professionista
				genFileParams.setTipoTitolarita(
						StringUtils.isEmpty(genFileParams.getRichRagSociale()) ? TipoTitolaritaEnum.PF
								: TipoTitolaritaEnum.PG);
			} else {
				genFileParams.setTipoTitolarita(
						StringUtils.isEmpty(genFileParams.getRichRagSociale()) ? TipoTitolaritaEnum.RF
								: TipoTitolaritaEnum.RG);
			}

		} catch (RecordNotFoundException | RecordNotUniqueException e) {
			logger.info("soggetto intervento non ancora inserito ");
		}

		return genFileParams;
	}

	@Override
	public List<FatDocumentoAllegato> getDocsGestoreByIntervento(Integer idIntervento) {
		List<TipoAllegatoPfaEnum> tipoAllegati = new ArrayList<>();
		tipoAllegati.add(TipoAllegatoPfaEnum.ALTRO);
		return documentoAllegatoDAO.findDocumentiByIdAndTiposPfa(idIntervento, tipoAllegati);
	}

	private void documentoAllegatiManipulation(List<FatDocumentoAllegato> documenti, int fkConfigUtente,
			Integer idIntervento, List<TipoAllegatoPfaEnum> tipoAllegati) {

		List<FatDocumentoAllegato> allDocumenti = documentoAllegatoDAO.findDocumentiByIdAndTiposPfa(idIntervento,
				tipoAllegati);

		for (FatDocumentoAllegato documento : documenti) {

			if (documento.isDeleted() && allDocumenti.contains(documento)) {
				documentoAllegatoDAO.deleteDocumentoAllegatoByID(documento.getIdDocumentoAllegato());

			} else if (!allDocumenti.contains(documento)) {

				documento.setIdGeoPlPfa(PlPfaEnum.FONTE_FINAZ.getValue());
				documento.setDataIniziValidita(LocalDate.now());
				documento.setFkConfigUtente(fkConfigUtente);
				int a = documentoAllegatoDAO.createDocumentoAllegato(documento);
				documento.setIdDocumentoAllegato(a);
			}
		}
	}

	private void buildPersonaFisGiu(PersonaFisGiu personaFoG, TSoggetto soggetto, TSoggetto soggettoIntervento)
			throws RecordNotUniqueException {
		personaFoG.setIdSoggetto(soggettoIntervento.getIdSoggetto());
		personaFoG.setCodiceFiscale(soggettoIntervento.getCodiceFiscale());
		personaFoG.setNome(soggettoIntervento.getNome());
		personaFoG.setCognome(soggettoIntervento.getCognome());
		personaFoG.setDenominazione(soggettoIntervento.getDenominazione());
		personaFoG.setIndirizzo(soggettoIntervento.getIndirizzo());
		personaFoG.setCivico(soggettoIntervento.getCivico());
		personaFoG.setCap(soggettoIntervento.getCap());
		personaFoG.setNrTelefonico(soggettoIntervento.getNrTelefonico());
		personaFoG.seteMail(soggettoIntervento.geteMail());
		personaFoG.setPartitaIva(soggettoIntervento.getPartitaIva());

		personaFoG.setPec(soggettoIntervento.getPec());

		personaFoG.setFlgEntePubblico(soggettoIntervento.getFlgEntePubblco() != 0);

		TSoggetto ownerSoggetto = soggettoDAO.getSoggettoPfByPg(soggettoIntervento.getIdSoggetto());
		if (ownerSoggetto != null)
			personaFoG.setOwnerCodiceFiscale(ownerSoggetto.getCodiceFiscale());

		if (soggettoIntervento.getFkComune() != null)
			personaFoG.setComune(comuneDAO.findComuneResourceByID(soggettoIntervento.getFkComune()));
	}

	@Override
	@Transactional
	public TagliPfaStep2 saveStep2(TagliPfaStep2 body, ConfigUtente loggedConfig)
			throws RecordNotUniqueException, ValidationException {

		int idIntervento = body.getIdIntervento();

		// caso richiedetnte == gestore
		if (body.getTipoRichiedenteId() == TipoRichiedenteEnum.GESTORE.getValue()) {

			// associo l'id gestore come richiedente
			associazionePFIntervento(loggedConfig.getIdConfigUtente(), body.getTipoRichiedenteId(), body.getGestoreId(),
					idIntervento);

			// se cittadino inserisco l'associazione con la persona gg
			if (loggedConfig.getFkProfiloUtente() == ProfiloUtenteEnum.CITTADINO.getValue()) {
				associateSoggettoInterventoPG(loggedConfig, loggedConfig.getIdConfigUtente(), body.getGestoreId());
			}

			// utilizzatore
			insertUtilizzatore(body, body.getGestoreId(), idIntervento, loggedConfig.getIdConfigUtente());

		} else {
			PersonaFisGiu newSogg = body.getSoggetto();
			registraDelegaSeNecessario(newSogg.getCodiceFiscale(), loggedConfig);

			TSoggetto soggetto;
			if (StringUtils.isNotBlank(newSogg.getDenominazione())) {
				soggetto = soggettoDAO.findAziendaByCodiceFiscale(newSogg.getCodiceFiscale());
			} else {
				soggetto = soggettoDAO.findSoggettoByCodiceFiscale(newSogg.getCodiceFiscale());
			}

			Integer idSoggetto;
			if (soggetto != null) {
				updateSoggettoStep2(newSogg, soggetto);
				idSoggetto = soggetto.getIdSoggetto();
			} else {
				idSoggetto = insertSoggettoStep2(loggedConfig.getIdConfigUtente(), newSogg);
				if (newSogg.getOwnerCodiceFiscale() != null) {
					TSoggetto ownerSoggetto = soggettoDAO.findSoggettoByCodiceFiscale(newSogg.getOwnerCodiceFiscale());
					associateSoggettoInterventoPG(loggedConfig, ownerSoggetto.getIdSoggetto(), idSoggetto);
				}
			}
			if (StringUtils.isNotBlank(newSogg.getDenominazione())) {
				String nrAlbo = null;
				try {
					// get albo from taif
					TAIFAnagraficaAzienda aziendTaif = taifService.findByCodiceFiscale(newSogg.getCodiceFiscale());
					nrAlbo = aziendTaif.getNumeroAlbo();
				} catch (Exception e) {
					logger.error("Errore nel recupero azienda dal servizio TAIF ", e);
				}
				associazionePGIntervento(loggedConfig.getIdConfigUtente(), body.getTipoRichiedenteId(), idSoggetto,
						idIntervento, nrAlbo);
			} else {
				associazionePFIntervento(loggedConfig.getIdConfigUtente(), body.getTipoRichiedenteId(), idSoggetto,
						idIntervento);
			}

			// utilizzatore
			insertUtilizzatore(body, idSoggetto, idIntervento, loggedConfig.getIdConfigUtente());
		}

		skOkSelvDAO.updateFlgSez2(idIntervento);
		return body;
	}

	@Override
	@Transactional
	public boolean updateStep2(TagliPfaStep2 body, ConfigUtente loggedConfig, int idIntervento)
			throws ValidationException, RecordNotUniqueException {

		// delete old values
		soggettoInterventoDAO.deleteByIdIntervento(idIntervento);

		// caso richiedetnte == gestore
		if (body.getTipoRichiedenteId() == TipoRichiedenteEnum.GESTORE.getValue()) {

			// associo l'id gestore come richiedente
			associazionePFIntervento(loggedConfig.getIdConfigUtente(), body.getTipoRichiedenteId(), body.getGestoreId(),
					idIntervento);

			// se cittadino inserisco l'associazione con la persona gg
			if (loggedConfig.getFkProfiloUtente() == ProfiloUtenteEnum.CITTADINO.getValue()) {
				associateSoggettoInterventoPG(loggedConfig, loggedConfig.getIdConfigUtente(), body.getGestoreId());
			}

			// utilizzatore
			insertUtilizzatore(body, body.getGestoreId(), idIntervento, loggedConfig.getIdConfigUtente());

		} else {
			PersonaFisGiu newSogg = body.getSoggetto();
			registraDelegaSeNecessario(newSogg.getCodiceFiscale(), loggedConfig);

			TSoggetto soggetto;
			if (StringUtils.isNotBlank(newSogg.getDenominazione())) {
				soggetto = soggettoDAO.findAziendaByCodiceFiscale(newSogg.getCodiceFiscale());
			} else {
				soggetto = soggettoDAO.findSoggettoByCodiceFiscale(newSogg.getCodiceFiscale());
			}

			Integer idSoggetto;
			if (soggetto != null) {
				updateSoggettoStep2(newSogg, soggetto);
				idSoggetto = soggetto.getIdSoggetto();
			} else {
				idSoggetto = insertSoggettoStep2(loggedConfig.getIdConfigUtente(), newSogg);
				if (newSogg.getOwnerCodiceFiscale() != null) {
					TSoggetto ownerSoggetto = soggettoDAO.findSoggettoByCodiceFiscale(newSogg.getOwnerCodiceFiscale());
					associateSoggettoInterventoPG(loggedConfig, ownerSoggetto.getIdSoggetto(), idSoggetto);
				}
			}
			if (StringUtils.isNotBlank(newSogg.getDenominazione())) {
				String nrAlbo = newSogg.getNumAlboForestale();
				associazionePGIntervento(loggedConfig.getIdConfigUtente(), body.getTipoRichiedenteId(), idSoggetto,
						idIntervento, nrAlbo);
			} else {
				associazionePFIntervento(loggedConfig.getIdConfigUtente(), body.getTipoRichiedenteId(), idSoggetto,
						idIntervento);
			}

			// utilizzatore
			insertUtilizzatore(body, idSoggetto, idIntervento, loggedConfig.getIdConfigUtente());
		}
		// update flasgs step2
		skOkSelvDAO.updateFlgSez2(idIntervento);
		return true;
	}

	@Override
	public boolean cambioUtilizzatore(TagliPfaStep2 dto, ConfigUtente loggedConfig, int idIntervento)
			throws ValidationException, RecordNotUniqueException {

		Integer idSoggetto = dto.getSoggetto().getIdSoggetto();
		soggettoInterventoDAO.deleteUtilizzatoreByIdIntervento(idIntervento);
		insertUtilizzatore(dto, idSoggetto, idIntervento, loggedConfig.getIdConfigUtente());
		return true;
	}

	@Override
	public TagliPfaStep3 getStep3(Integer idIntervento, ConfigUtente loggedConfig)
			throws RecordNotFoundException, RecordNotUniqueException {
		if (!skOkSelvDAO.isFlgSez2Done(idIntervento))
			throw new ValidationException(SEZIONE_2_VALID_MSG);

		CustomValidator.getValidator(HttpStatus.CONFLICT)
				.errorIf("Sezione2", !skOkSelvDAO.isFlgSez2Done(idIntervento), SEZIONE_2_VALID_MSG).go();

		//TagliSelvicolturaliStep3 step3 = this.getRicadenzeFromDb(idIntervento);
		TagliPfaStep3 step3 = this.getRicadenze(idIntervento);

		IntervPfaFat interventoSelv = intervPfaDAO.findInterventoSelvicolturaleTagli(idIntervento);
		if (null != interventoSelv && 0 != interventoSelv.getFkFasciaAltimetrica()) {			
			FasciaAltimetrica fascia = fasciaAltimetricaDAO
					.getFasciaAltimetrica(interventoSelv.getFkFasciaAltimetrica());				
				step3.setFasciaAltimetrica(fascia);					
		}

		List<PropCatasto> propCatastos = propCatastoDAO.getPropCatastosByidIntervento(idIntervento);

		step3.setParticelleCatastali(mapPropCatastosToParticelleCatastali(propCatastos));

		IntervTrasformazione intervTrasformazione = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);

		if (intervTrasformazione != null) {
			step3.setRicadenzaVincoloIdrogeologico(intervTrasformazione.getFlgVincIdro() == 1);
		}


		 System.out.println("getstep3 TagliPfaStep3 :------------------> " + objectToString(step3));
		return step3;
	}

	@Override
	public TagliPfaStep4 getStep4(Integer idIntervento, ConfigUtente confUtente) {

		CustomValidator.getValidator(HttpStatus.CONFLICT)
				.errorIf("Sezione3", !skOkSelvDAO.isFlgSez3Done(idIntervento), SEZIONE_3_VALID_MSG).go();

		TagliPfaStep4 step4 = new TagliPfaStep4();

		//step4.setIntervSelvicolturale(intervPfaDAO.findInterventoSelvicolturaleTagli(idIntervento)); // check TODO ABISOFT

		step4.setIntervPfaFat(intervPfaDAO.findInterventoSelvicolturaleTagli(idIntervento));
		step4.setIntervento(interventoDAO.findInterventoByIdIntervento(idIntervento));

		step4.setHeadings(buildTagliHeadings(idIntervento));

		// specie interessate
		List<SpeciePfaIntervento> species = buildSpecieInteresaste(idIntervento);
		step4.setSpecieInteressate(species);

		return step4;
	}

	private List<SpeciePfaIntervento> buildSpecieInteresaste(Integer idIntervento) {
		List<SpeciePfaIntervento> specieList = new ArrayList<>();
		List<SpecieInterventoVolumes> species = speciePfaInterventoDao.getSpecieByInterventoId(idIntervento);

		for (SpecieInterventoVolumes spvol : species) {
			SpeciePfaIntervento sp = new SpeciePfaIntervento();
			sp.setNumPiante(spvol.getNumPiante());
			sp.setFlgSpeciePriorita(spvol.getPriorita());
			sp.setIdIntervento(idIntervento);
			sp.setVolumeSpecia(spvol.getVolumeSpecie());
			sp.setIdSpecie(spvol.getIdSpecie());
			sp.setFkUdm(spvol.getFkUdm());

			TSpecie specie = specieDAO.getSpecie(spvol.getIdSpecie());
			sp.setSpecie(specie);

			List<SpecieInterventoFinalita> finalitaList = specieInterventoFinalitaDAO
					.getAllSpecieByInterventoAndIdSpecie(idIntervento, spvol.getIdSpecie());
			sp.setSpecieFinalita(finalitaList);
			specieList.add(sp);
		}
		return specieList;
	}

	@Override
	@Transactional
	public TagliPfaStep4 saveStep4(TagliPfaStep4 step4, ConfigUtente confUtente) {

		int idIntervento = step4.getIntervento().getIdIntervento();

		if (!skOkSelvDAO.isFlgSez3Done(idIntervento))
			throw new ValidationException(SEZIONE_3_VALID_MSG);

		// update intervento selvicolturale
		intervPfaDAO.updateIntervSelvicolturaletagliStep4(step4.getIntervPfaFat(), idIntervento); // check TODO ABISOFT

		// update intervento
		interventoDAO.updateInterventoDescrizione(step4.getIntervento().getDescrizioneIntervento(), idIntervento,
				confUtente.getIdConfigUtente());

		// save viabilità
		saveViablitaIntervento(step4.getHeadings(), idIntervento);

		// save species
		saveSpecieInteressate(step4.getSpecieInteressate(), idIntervento, confUtente.getIdConfigUtente());

		wrapperPfaDAO.updateRipresaInterventoPfa(idIntervento);

		// update flasgs step4
		skOkSelvDAO.updateFlgSez4(idIntervento);

		// determinate and update tipo istanza
		this.updateTipoIstanza(null, idIntervento, confUtente);

		return step4;
	}

	@Override
	public void updateTipoIstanza(TipoIstanzaEnum tipoIstanza, int idIntervento, ConfigUtente confUtente) {
		// save tipoistanza autorizzazione | comunicazione
		if (tipoIstanza == null) {
			Intervento intervento = interventoDAO.findInterventoByIdIntervento(idIntervento);
			IntervPfaFat intSelv = intervPfaDAO.findInterventoSelvicolturaleTagli(idIntervento);
			tipoIstanza = this.determinaTipoIstanza(intervento, intSelv);
		}
		// update tipo istanza
		istanzaForestDAO.updateTipoIstanza(tipoIstanza.getTypeValue(), confUtente.getIdConfigUtente(), idIntervento);
	}

	@Override
	public void updateIstanzaAutorizzata(AutorizzaIstanza body, ConfigUtente confUtente) {
		if (body.getDataFineIntervento() != null) {
			interventoDAO.updateDataFineIntervento(body.getDataFineIntervento(), body.getIdIntervento());
		}
		istanzaForestDAO.updateIstanzaTagliAutorizzata(body, confUtente);
	}

	@Override
	public void updateIstanzaVerificata(AutorizzaIstanza body, ConfigUtente confUtente) {
		istanzaForestDAO.updateIstanzaTo(body.getIdIntervento(), null, confUtente.getIdConfigUtente(),
				InstanceStateEnum.VERIFICATA);
	}

	@Override
	public void updateIstanzaTo(Integer idIntervento, String motivazioneRifiuto, Integer fkConfigUtente,
			InstanceStateEnum statoIstanza) {
		istanzaForestDAO.updateIstanzaTo(idIntervento, motivazioneRifiuto, fkConfigUtente, statoIstanza);
	}

	@Override
	public TagliPfaStep5 getStep5(Integer idIntervento, ConfigUtente confUtente) {

		IntervPfaFat intSelv = intervPfaDAO.findInterventoSelvicolturaleTagli(idIntervento);
		Intervento intervento = interventoDAO.findInterventoByIdIntervento(idIntervento);
		IstanzaForest istanzaForestale = istanzaForestDAO.getByIdIntervento(idIntervento);

		TagliPfaStep5 step = new TagliPfaStep5();
		step.setIntervento(intervento);
		step.setIntervPfaFat(intSelv); //CHECK TODO ABISOFT
		step.setAllegati(this.findAllegati(idIntervento));
		step.setDocumentazioneAllegata(this.findDocumentazioneAllegata(idIntervento));
		step.setTecnicoForestale(this.findTecnicoForestale(idIntervento));
		step.setTipoIstanzaProposta(this.determinaTipoIstanza(intervento, intSelv)); //CHECK TODO ABISOFT
		step.setTipoIstanza(TipoIstanzaEnum.getEnum(istanzaForestale.getFkTipoIstanza()));
		step.setTipoIstanzaId(istanzaForestale.getFkTipoIstanza());
		step.setMotivazione(istanzaForestale.getMotivazione());
		step.setNoteFinaliRichiedente(intSelv.getNoteFinaliRichiedente());
		logger.info(intSelv.getNoteFinaliRichiedente() + "---NOTE FINALI INTSELV---");
		logger.info(step.getNoteFinaliRichiedente() + "---NOTE FINALI TAGLI SELV SERV IMPL---");
		return step;
	}

	@Override
	@Transactional
	public TagliPfaStep5 saveStep5(TagliPfaStep5 body, ConfigUtente confUtente)
			throws RecordNotUniqueException {

		int idIntervento = body.getIntervento().getIdIntervento();

		if (null != body.getTecnicoForestale().getIdSoggetto()) {
			// save tecnico forestale if present
			this.insertTecnicoForestale(body.getTecnicoForestale(), idIntervento, confUtente.getIdConfigUtente());
		} else {
			interventoDAO.updateInterventoSoggettoProfess(null, idIntervento, confUtente.getIdConfigUtente());
		}

		// update tipo istanza
		istanzaForestDAO.updateTipoIstanza(body.getTipoIstanza().getTypeValue(), confUtente.getIdConfigUtente(),
				idIntervento);

		// set soggetto regionale
		TSoggetto soggettoCompetente = dichiarazioneSummaryDAO.getSoggettoCompetenteRegionale(idIntervento,
				AmbitoIstanzaEnum.TAGLIO_IN_BOSCO);
		istanzaForestDAO.updateEnteCompetente(idIntervento, soggettoCompetente.getIdSoggetto());

		// set Motivazione cambio
		String motivazione = body.getMotivazione();
		logger.info("---motivazione--- " + motivazione);
		istanzaForestDAO.updateMotivazione(idIntervento, motivazione);
		
		String noteFinaliRichiedente = body.getNoteFinaliRichiedente();
		logger.info("---noteFinaliRichiedente---" + noteFinaliRichiedente);
		intervPfaDAO.updateNoteFinaliRichiedente(idIntervento, noteFinaliRichiedente);

		skOkSelvDAO.updateFlgSez5(idIntervento);

		return body;
	}

	@Override
	public TagliPfaStep5 updateTipologiaIstanza(TagliPfaStep5 body, ConfigUtente confUtente)
			throws RecordNotUniqueException {
		int idIntervento = body.getIntervento().getIdIntervento();
		// update tipo istanza
		istanzaForestDAO.updateTipoIstanza(body.getTipoIstanza().getTypeValue(), confUtente.getIdConfigUtente(),
				idIntervento);
		return this.getStep5(idIntervento, confUtente);
	}

	@Override
	public boolean updateStep5(TagliPfaStep5 dto, ConfigUtente loggedConfig, int idIntervento)
			throws ValidationException, RecordNotUniqueException {
		throw new NotImplementedException();
		// return false;
	}

	@Override
	public InfoDocsPfa isDrelMancante(Integer idIntervento) {
		InfoDocsPfa info = new InfoDocsPfa();
		info.setDocType(TipoAllegatoPfaEnum.DICHIARAZIONE_DREL);
		TipoAllegato tpAll = tipoAllegatoDAO.getTipoById(TipoAllegatoPfaEnum.DICHIARAZIONE_DREL.getValue());
		info.setMissing(intervPfaDAO.isDrelMancante(idIntervento));
		info.setDocDescription(tpAll.getDescrTipoAllegato());
		return info;
	}

	@Override
	public FatDocumentoAllegato getDrel(Integer idIntervento) {
		List<TipoAllegatoPfaEnum> tipos = Arrays.asList(TipoAllegatoPfaEnum.DICHIARAZIONE_DREL);
		List<FatDocumentoAllegato> allegati = documentoAllegatoDAO.findDocumentiByIdAndTiposPfa(idIntervento, tipos);
		return allegati != null ? allegati.get(0) : null;
	}

	@Override
	public PlainSezioneId creaVarianteProroga(Integer idIntervento, Boolean isVariante, ConfigUtente confUtente) {
		Integer idInterventoNew = null;
		try {

			TagliPfaStep1 step1 = this.getStep1(idIntervento);
			step1.setIdIntervento(null);
			if (isVariante) {
				step1.setFkInterventoPadreVariante(idIntervento);
			} else {
				step1.setFkInterventoPadreProroga(idIntervento);
			}

			PlainSezioneId res = this.saveStep1(step1, confUtente);
			idInterventoNew = res.getIstanzaId();
			logger.info("creaVarianteProroga - idInterventoNew: " + idInterventoNew);
			IntervPfaFat newSelv = intervPfaDAO
					.findInterventoSelvicolturaleTagli(idInterventoNew);

			TagliPfaStep2 step2 = this.getStep2(idIntervento, confUtente);
			step2.setIdIntervento(idInterventoNew);
			this.saveStep2(step2, confUtente);

			TagliPfaStep3 step3 = this.getStep3(idIntervento, confUtente);
			step3.setIdIntervento(idInterventoNew);
			this.saveStep3(step3, confUtente);

			Intervento newInt = interventoDAO.findInterventoByIdIntervento(idInterventoNew);

			TagliPfaStep4 step4 = this.getStep4(idIntervento, confUtente);
			//step4.setIntervSelvicolturale(newSelv); //check TODO ABISOFT
			step4.setIntervento(newInt);
			this.saveStep4(step4, confUtente);

			TagliPfaStep5 step5 = this.getStep5(idIntervento, confUtente);
			step5.setIntervento(newInt);
			this.saveStep5(step5, confUtente);

			geoPlLottoInterventoDAO.duplicateIntervento(idIntervento, idInterventoNew, confUtente.getIdConfigUtente());

			// duplica allegati
			if (!isVariante) {
				List<DocumentoAllegato> listDocs = documentoAllegatoDAO.findDocumentiByIdIntervento(idIntervento);
				for (DocumentoAllegato doc : listDocs) {
					try {
						if (doc.getFkTipoAllegato() != TipoAllegatoPfaEnum.RICHIESTA_DI_AUTORIZZAZIONE_DI_TAGLIO_IN_BOSCO_AUTOG
								.getValue()
								&& doc.getFkTipoAllegato() != TipoAllegatoPfaEnum.RICHIESTA_DI_AUTORIZZAZIONE_DI_TAGLIO_IN_BOSCO_DIGIT
										.getValue()
								&& doc.getFkTipoAllegato() != TipoAllegatoPfaEnum.COMUNICAZIONE_DI_TAGLIO_IN_BOSCO_AUTOG
										.getValue()
								&& doc.getFkTipoAllegato() != TipoAllegatoPfaEnum.COMUNICAZIONE_DI_TAGLIO_IN_BOSCO_DIGIT
										.getValue()
								&& doc.getFkTipoAllegato() != TipoAllegatoPfaEnum.SCANSIONE_DOC_IDENTITA.getValue()) {

							this.duplicaDocumento(doc, idInterventoNew, confUtente);
						}
					} catch (Exception ex) {
						logger.error("Errore nella dupplicazione del file id: " + doc.getIdDocumentoAllegato()
								+ " - idTipoAllegato: " + doc.getFkTipoAllegato());
					}
				}
			}

			if (isVariante) {
				skOkSelvDAO.initVariante(idInterventoNew);
			}

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			if (idInterventoNew != null) {
				this.deleteIntervento(idInterventoNew);
			}
			return null;
		}
	}

	@Override
	public void duplicaDocumento(DocumentoAllegato documentoAllegato, Integer idInterventoNew,
			ConfigUtente loggedConfig) throws Exception {
		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
		byte[] contDownloaded = indexSrvHelper
				.indexDownloadFileVincolo(documentoAllegato.getIdDocumentoAllegato().toString());
		FileUploadForm form = new FileUploadForm();
		form.setName(documentoAllegato.getNomeAllegato());
		form.setNote(documentoAllegato.getNote());
		form.setData(contDownloaded);
		documentoAllegatoDAO.uploadDocumentoTagli(idInterventoNew, documentoAllegato.getFkTipoAllegato(), form,
				loggedConfig);
	}

	@Override
	public InfoVarianteProroga getInfoVarianteProroga(Integer idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select itis.fk_intervento_padre_proroga, itis.fk_intervento_padre_variante,  ");
		sql.append(
				" (select count(id_intervento ) as dim from idf_t_interv_selvicolturale where fk_intervento_padre_proroga = ?) as proroghe, ");
		sql.append(
				" (select count(id_intervento ) as dim from idf_t_interv_selvicolturale where fk_intervento_padre_variante = ?) as varianti  ");
		sql.append(" from idf_t_interv_selvicolturale itis   ");
		sql.append(" where id_intervento = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(idIntervento);
		params.add(idIntervento);
		params.add(idIntervento);
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), params.toArray(), new InfoVarianteProrogaMapper());
	}

	@Override
	public void updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente) {
		intervPfaDAO.updateTitolaritaIntervento(idIntervento, idConfUtente);
	}

	@Override
	public void updateDataFineInterventoTagli(AutorizzaIstanza body, ConfigUtente confUtente) {
		interventoDAO.updateDataFineIntervento(body.getDataFineIntervento(), body.getIdIntervento());
	}

	private PersonaFisGiu findTecnicoForestale(Integer idIntervento) {
		PersonaFisGiu tecnico = soggettoDAO.getTecnicoForestaleByIdIntervento(idIntervento);
		return tecnico;
	}

	private List<DocumentoAllegato> findDocumentazioneAllegata(Integer idIntervento) {
		return new ArrayList<>();
	}

	private List<FatDocumentoAllegato> findAllegati(Integer idIntervento) {
		List<FatDocumentoAllegato> docs = documentoAllegatoDAO.findFatDocumentiByIdIntervento(idIntervento);
		return docs;
	}

	private TipoIstanzaEnum determinaTipoIstanza(Intervento intervento, IntervPfaFat selv) {
		int cat = selv.getFkCategoriaIntervento();
		int fkIntPri = selv.getFkTipoIntervento();
		int fkIntSec = selv.getFkTipoIntervento2();
		String flagConformeDeroga = selv.getFlgConformeDeroga();
		BigDecimal sup = intervento.getSuperficieInteressata();

		TipoIstanzaEnum tipoComunicazione = TipoIstanzaEnum.COMUNICAZIONE;

		switch (cat) {
		case 1:
			// IN BOSCO
			/*
			 * BOSCO PUBBLICA => PROPRIETA: PUBBLICA id 39, 40 ,41 TAGLI DI UTILIZZAZIONE =
			 * tipi intervento CS / CM / SU / TB / SC / SG / AF {id: 1,2,3,4,7,8,9}
			 * SOSTITUZIONE SPECIE = tipo intervento TR id :{15 } RIPRISTINO BOSCHI
			 * DANNEGGIATI O DISTRUTTI = tipi intervento RR / RS id : {11,12} ISCRIZIONE
			 * ALL’ALBO = Utilizzatore con valorizzato il Nr_albo_forestale sulla
			 * R_SOGGETTO_INTERVENTO
			 */
//			int[] publicPropertyIds = { 39, 40, 41 };
//			int fkProprieta = selv.getFkProprieta();
//			boolean isPubblicProprieta = ArrayUtils.contains(publicPropertyIds, fkProprieta);
//
//			int[] publicTipoInterventoIds = { 1, 2, 3, 4, 7, 8, 9 };
//			boolean isPublicTipoIntervento = (ArrayUtils.contains(publicTipoInterventoIds, fkIntPri)
//					|| ArrayUtils.contains(publicTipoInterventoIds, fkIntSec));
//			// BLOCCO 1 [bosco pubblico e utilizzazioni]
//			if (isPubblicProprieta && isPublicTipoIntervento) {
//				tipoComunicazione = (sup.doubleValue() > 0.5) ? TipoIstanzaEnum.AUTORIZZAZIONE
//						: TipoIstanzaEnum.COMUNICAZIONE;
//				break;
//			}
//
//			// BLOCCO 2 [sostituzione specie o ripristino boschi danneggiati]
//			int[] sostSpecieOrRipristinoBoschiIds = { 11, 12, 15 };
//			boolean isSostOrRipristino = (ArrayUtils.contains(sostSpecieOrRipristinoBoschiIds, fkIntPri)
//					|| ArrayUtils.contains(sostSpecieOrRipristinoBoschiIds, fkIntSec));
//			if (isSostOrRipristino) {
//				tipoComunicazione = (sup.doubleValue() > 1) ? TipoIstanzaEnum.AUTORIZZAZIONE
//						: TipoIstanzaEnum.COMUNICAZIONE;
//				break;
//			}
//
//			// BLOCCO 3
//			tipoComunicazione = getBloccoIscrizioneAlbo(intervento, sup);
			tipoComunicazione = flagConformeDeroga.equals("C") ? TipoIstanzaEnum.COMUNICAZIONE : TipoIstanzaEnum.AUTORIZZAZIONE;


			break;

		case 2:
			// ARBORICOLTURA
			tipoComunicazione = TipoIstanzaEnum.COMUNICAZIONE;
			break;
		case 3:
			// CASTAGNETI E NOCCIOLETI DA FRUTTO
			tipoComunicazione = TipoIstanzaEnum.AUTORIZZAZIONE;
			break;
		case 4:
			// SITUAZIONI SPECIALI
			// 89 Interventi di manutenzione nelle aree di pertinenza dei corpi idrici
			// 90 Interventi di manutenzione nelle aree di pertinenza di reti tecnologiche
			if (fkIntPri == 89 || fkIntSec == 89) {
				tipoComunicazione = getBloccoIscrizioneAlbo(intervento, sup);
				break;
			}
			break;
		}

		return tipoComunicazione;
	}

	private TipoIstanzaEnum getBloccoIscrizioneAlbo(Intervento intervento, BigDecimal sup) {
		SoggettoIntervento si = null;
		try {
			si = soggettoInterventoDAO.getSoggettoInterventoByIdInterventoAndIdTipoSoggetto(
					intervento.getIdIntervento(), SoggettoTypeEnum.UTILIZZATORE.getValue());
		} catch (RecordNotUniqueException e) {
			throw new RuntimeException(e);
		}

		if (null != si && StringUtils.isNotBlank(si.getNrAlboForestale())) {
			return (sup.doubleValue() > 10) ? TipoIstanzaEnum.AUTORIZZAZIONE : TipoIstanzaEnum.COMUNICAZIONE;
		} else {
			return (sup.doubleValue() > 5) ? TipoIstanzaEnum.AUTORIZZAZIONE : TipoIstanzaEnum.COMUNICAZIONE;
		}
	}

	private void deleteSpecieInteressate(int idIntervento) {
		specieInterventoFinalitaDAO.deleteAllByIdIntervento(idIntervento);
		speciePfaInterventoDao.deleteAllSpeciePfaInterventoByIdIntervento(idIntervento);
	}

	private void saveSpecieInteressate(List<SpeciePfaIntervento> specieInteressate, Integer idIntervento,
			Integer fkConfigUtente) {
		for (SpeciePfaIntervento sp : specieInteressate) {

			speciePfaInterventoDao.createSpeciePfaIntervento(sp, idIntervento);

			List<SpecieInterventoFinalita> finalita = sp.getSpecieFinalita();
			for (SpecieInterventoFinalita fin : finalita) {
				specieInterventoFinalitaDAO.createSpecieInterventoFinalita(fin, idIntervento, fkConfigUtente);
			}
		}
	}

	private void deleteViablitaIntervento(Integer idIntervento) {
		tipoViabUsoviabInterventoDAO.deleteTipoViabIntervento(idIntervento);
		esboscoIntervselvDAO.deleteIntervselvEsbosco(idIntervento);
		usoviabInterventoselvDAO.deleteIntervselUsovib(idIntervento);
	}

	private void saveViablitaIntervento(List<TagliHeading> headings, Integer idIntervento) {
		for (TagliHeading heading : headings) {
			if (!heading.isChecked())
				continue;
			Integer id = heading.getId();
			// save parent
			usoviabInterventoselvDAO.createIntervselUsovib(id, idIntervento);

			switch (id) {
			case 1:
			case 3: {
				for (TagliSubHeading item : heading.getSubheadings()) {
					if (!item.isChecked())
						continue;
					tipoViabUsoviabInterventoDAO.createTipoViabIntervento(Integer.valueOf(item.getId()), idIntervento,
							id);
				}
				break;
			}
			case 2:
				for (TagliSubHeading item : heading.getSubheadings()) {
					if (!item.isChecked())
						continue;
					esboscoIntervselvDAO.createIntervselvEsbosco(item.getId() == null ? item.getCodice() : item.getId(),
							idIntervento);
				}
				break;
			}
		}
	}

	@Override
	public void recalculateParticelleIntervento(Integer idIntervento, ConfigUtente configUtente)
			throws ServiceException {

		List<String> idfGeoPlPropCatastoArray = this.getIdfRProCatastoIntervMethod(idIntervento);
		propcatastoInterventoDAO.deleteAllByIdIntervento(idIntervento);
		StringBuilder sqldeletecatasto = new StringBuilder();
		if(idfGeoPlPropCatastoArray.size()>0) {

			sqldeletecatasto.append("delete from idf_geo_pl_prop_catasto where idgeo_pl_prop_catasto  IN(");

			for (int i = 0; i < idfGeoPlPropCatastoArray.size(); i++) {
				sqldeletecatasto.append(idfGeoPlPropCatastoArray.get(i));
				if (i != idfGeoPlPropCatastoArray.size() - 1) {
					sqldeletecatasto.append(", ");
				}
			}
			sqldeletecatasto.append(")");
			logger.info("<---- sqldeletecatasto" + sqldeletecatasto.toString());
			DBUtil.jdbcTemplate.update(sqldeletecatasto.toString());
		}

try{
		TSoggetto soggetto = soggettoDAO.findSoggettoById(configUtente.getFkSoggetto());
		List<LottoIntervento> lottoInterventos = geoPlLottoInterventoDAO.getGeometrieGmlByFkIntervento(idIntervento);
		if (lottoInterventos != null && lottoInterventos.size() > 0) {
			List<InfoParticella>  particelleList = new ArrayList<InfoParticella>();
			Map<String, InfoParticella> mapParticelle = new HashMap<String, InfoParticella>();
			String key;
			for (LottoIntervento intervento : lottoInterventos) {
				String geometryGML = intervento.getGeometriaGml();
				logger.info("idIntervento: " + idIntervento
						+ " - recalculateParticelleIntervento - getParticelleByGeometry: " + geometryGML);

				// 777 aqui esta el laboro, intersectar deberia ser 2do paso
				/*particelleList = sigmaterServiceHelper.getParticelleByGeometry(geometryGML,
						soggetto.getCodiceFiscale());*/
				ObjectMapper objectMapper3 = new ObjectMapper();
		        ObjectNode jsonObject3 = objectMapper3.createObjectNode();

		        ObjectMapper objectMapper2 = new ObjectMapper();
		        JsonNode jsonNode2 = null;
	            try {
					jsonNode2 = objectMapper2.readTree(geometryGML);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		        ObjectMapper objectMapper1 = new ObjectMapper();
		        ObjectNode jsonObject1 = objectMapper1.createObjectNode();
		        jsonObject1.put("type", "Feature");
		        jsonObject1.putPOJO("properties", jsonObject3);
		        jsonObject1.putPOJO("geometry", jsonNode2);
				ObjectMapper objectMapper = new ObjectMapper();
		        ObjectNode jsonObject = objectMapper.createObjectNode();
		        jsonObject.putPOJO("feature", jsonObject1);

				try {
					String particellasInter = particella.findParticellaByGML(jsonObject);
					System.out.println("SAlioooooooooooooo: " + particellasInter);
					ObjectMapper objectMapper77 = new ObjectMapper();
			        JsonNode jsonNode77 = null;
		            try {
						jsonNode77 = objectMapper77.readTree(particellasInter);
						LocalDate fechaHoy = LocalDate.now();

			            JsonNode elementosNode = jsonNode77.get("features");
			            if (elementosNode.isArray()) {
			                for (JsonNode elemento : elementosNode) {
								// String elementoTexto = elemento.asText();
								/*ObjectMapper objectMapper22 = new ObjectMapper();
								ObjectMapper objectMapper22 = new ObjectMapper();
								JsonNode geoJsonNode = objectMapper22.readTree(elemento.get("geometry").toString());
								
								JsonNode convertedGeoJson = convertToUTM_333(geoJsonNode);
								*/
								//System.out.println("--Convertido a UTM--");
								//System.out.println(convertedGeoJson.toString());

								///
								StringBuilder sql = new StringBuilder();
								sql.append("select ST_AsText(ST_Transform(ST_SetSRID(ST_GeomFromGeoJSON(?),4326),32632))");
								String converteString = DBUtil.jdbcTemplate.queryForObject(sql.toString(), String.class, elemento.get("geometry").toString());
								System.out.println("converteString-------------: " + converteString);
								////AREA								
								StringBuilder sqlArea = new StringBuilder();
								sqlArea.append("SELECT ROUND((SELECT (ST_Area(?)))::numeric, 4)");
								BigDecimal areaBigDecimal = DBUtil.jdbcTemplate.queryForObject(sqlArea.toString(), BigDecimal.class, converteString);
								System.out.println("areaBigDecimal-------------: " + areaBigDecimal);
								double areaBigDecimalTsf = areaBigDecimal.doubleValue(); // The double you want								

		                           ///TODO: area con funcion en base
//					            double area = calculateArea(convertedGeoJson.toString());
								double divisor = 10000.0;
//								double resultHa = area; //area / divisor;

			                	System.out.println("Elemento: " + elemento);
			                    InfoParticella particella1  = new InfoParticella(
			                    		elemento.get("properties").get("allegato").asText(),
			                    		areaBigDecimalTsf,
			                    		elemento.get("properties").get("cod_com_belfiore").asText(),
			                    		elemento.get("properties").get("cod_com_istat").asText(),
			                    		fechaHoy.toString(),
			                    		elemento.get("properties").get("foglio").asText(),
			                    		//convertedGeoJson.toString(),
			                    		converteString,
			                    		elemento.get("properties").get("particella").asText(),
			                    		elemento.get("properties").get("sezione").asText(),
			                    		elemento.get("properties").get("particella").asText());
			                	System.out.println("Particellas uno a uno  : "+ objectToString(particella1));

			                    particelleList.add(particella1);
			                }
			            }



							////




					//particelleList[0] = part icella1;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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

				System.out.println("lista llena a guardar "+ objectToString(particelleList));

				if (particelleList != null && particelleList.size() > 0) {

					logger.info("Trovare " + particelleList.size() + " tramite getParticelleByGeometry");
					for (InfoParticella infoPar : particelleList) {
						key = infoPar.getCodIstatComune() + "-" + infoPar.getSezione() + "-" + infoPar.getFoglio() + "-"
								+ infoPar.getNumero()+ "-" +infoPar.getArea();
						if (mapParticelle.get(key) == null) {
							mapParticelle.put(key, infoPar);
						} else {
							mapParticelle.get(key).setArea(mapParticelle.get(key).getArea() + infoPar.getArea());
						}
					}
				} else {
					logger.info("Nessuna particella trovata tramite getParticelleByGeometry");
				}
			}
			PlainParticelleCatastali item;
			Double areaIntersect = null;
			for (InfoParticella infoPar : mapParticelle.values()) {
				try {
					item = getPlainParticelleCatastaliFromInfoParticella(infoPar, comuneDAO, configUtente);
				} catch (RecordNotUniqueException e) {
					e.printStackTrace();
					throw new ServiceException("RecordNotUniqueException");
				}

				logger.info("Trovato particella----->: " + objectToString(item));
				if(item.getComune()!=null) {
					item = propCatastoDAO.insertParticella(item);
				}
				//item = propCatastoDAO.insertParticella(item);
				// inserire su item il la superficie effettivamente oggetto di intervento
				// calcolata come intersezione della particella con la geom dell'intervento su
				// idf_geo_pl_interv_trasf
				Integer isValidGeom=0;
				Boolean result=false;

				if(item.getIdGeoPlPropCatasto()!=null) {
//				areaIntersect = geoPlLottoInterventoDAO.getAreaItersecWithParticella(idIntervento,
//						item.getIdGeoPlPropCatasto());
					try {
						List<Object> parameters1 = new ArrayList<>();
						//parameters1.add(idIntervento);
						//parameters1.add(item.getIdGeoPlPropCatasto());
						parameters1.add(item.getGeometry().toString());
						logger.info("isValidGeom-------------: " + isValidGeom + "--" +idIntervento+"--"+item.getIdGeoPlPropCatasto());
						StringBuilder sqlValidGeom = new StringBuilder();
						//sqlValidGeom.append("SELECT case when ST_IsValid(ST_AsText(?))=true then 1 else 0 end as geom FROM idf_geo_pl_prop_catasto igppc join idf_r_propcatasto_intervento irpi on igppc.idgeo_pl_prop_catasto=irpi.idgeo_pl_prop_catasto WHERE irpi.id_intervento = ? and igppc.idgeo_pl_prop_catasto = ?");
						sqlValidGeom.append("SELECT ST_IsValid(ST_AsText(?))");
						//isValidGeom = DBUtil.jdbcTemplate.queryForInt(sqlValidGeom.toString(), parameters1.toArray());
						logger.info("<------ sql ST_IsValid: " + item.getGeometry().toString());

						result =DBUtil.jdbcTemplate.queryForObject(sqlValidGeom.toString(), Boolean.class, item.getGeometry().toString());
						logger.info("<------ Boolean "+result.toString());

						//isValidGeom = DBUtil.jdbcTemplate.queryForInt(sqlValidGeom.toString(), item.getGeometry().toString());

					}
					catch(Exception e) {
						e.printStackTrace();
						logger.info("<-------- ERROR insertParticellaTagli "+ e);
					}
					logger.info("isValidGeom Result: -------------: " + result);
					if(result) {
						areaIntersect = geoPlLottoInterventoDAO.getAreaItersecWithParticella(idIntervento,
								item.getIdGeoPlPropCatasto());
						if (areaIntersect >= 1) {
							item.setSupIntervento(new BigDecimal(areaIntersect / 10000));// da metri a ettari
							propcatastoInterventoDAO.insertParticelleInPropcatastoIntervento(idIntervento, item, configUtente);

						}
						else
						{
							StringBuilder sqldeletecatasto1 = new StringBuilder();
							sqldeletecatasto1.append("delete from idf_geo_pl_prop_catasto where idgeo_pl_prop_catasto=?");

							logger.info("<---- sqldeletecatasto" + sqldeletecatasto1.toString());
							DBUtil.jdbcTemplate.update(sqldeletecatasto1.toString(),item.getIdGeoPlPropCatasto());
						}
					}
					else {

						StringBuilder sqldeletecatasto1 = new StringBuilder();
						sqldeletecatasto1.append("delete from idf_geo_pl_prop_catasto where idgeo_pl_prop_catasto=?");

						logger.info("<---- sqldeletecatasto" + sqldeletecatasto.toString());
						DBUtil.jdbcTemplate.update(sqldeletecatasto1.toString(),item.getIdGeoPlPropCatasto());

						//areaIntersect= item.getSupCatastale().doubleValue();
						//item.setSupIntervento(new BigDecimal(areaIntersect));// da metri a ettari
						//propcatastoInterventoDAO.insertParticelleInPropcatastoIntervento(idIntervento, item, configUtente);

					}
					logger.info("<--------AREA INTERSECT : " + areaIntersect);
				}
				//areaIntersect = geoPlLottoInterventoDAO.getAreaItersecWithParticella(idIntervento,item.getIdGeoPlPropCatasto());
//				if (areaIntersect >= 1) {
//					item.setSupIntervento(new BigDecimal(areaIntersect / 10000));// da metri a ettari
//					propcatastoInterventoDAO.insertParticelleInPropcatastoIntervento(idIntervento, item, configUtente);
//				}
			}
		}}
catch(Exception e)
{
	e.printStackTrace();
	logger.info("<---------------- ERROR recalculateParticelleIntervento " + e);
}

	}



	private List<String> getIdfRProCatastoIntervMethod(Integer idIntervento) {
		logger.info("INIT getIdfRProCatastoIntervMethod ----------");
		StringBuilder sqlIdfRProCatastoInterv = new StringBuilder();
		sqlIdfRProCatastoInterv.append("select i.idgeo_pl_prop_catasto as id  from IDF_R_PROPCATASTO_INTERVENTO i where id_intervento = ?");

		List<String> resultIdfRProCatastoInterv = DBUtil.jdbcTemplate.queryForList(sqlIdfRProCatastoInterv.toString(), String.class, idIntervento);
		logger.info("result getIdfRProCatastoIntervMethod ----------" + resultIdfRProCatastoInterv.toString());
		return resultIdfRProCatastoInterv;
	}

	private void deleteIdfGeoPlPropCatastoMethod(String idGeoPlPropCatasto) {
		logger.info("INIT deleteIdfGeoPlPropCatastoMethod ----------");
		StringBuilder sqlGeoPlPropCatasto = new StringBuilder();
		sqlGeoPlPropCatasto.append("delete from idf_geo_pl_prop_catasto igpc where igpc.idgeo_pl_prop_catasto = ?");
		logger.info("sqlGeoPlPropCatasto deleteIdfGeoPlPropCatastoMethod ----------" + idGeoPlPropCatasto);
		int resultDelete = DBUtil.jdbcTemplate.update(sqlGeoPlPropCatasto.toString(), Integer.parseInt(idGeoPlPropCatasto));
		logger.info("result deleteIdfGeoPlPropCatastoMethod ----------" + resultDelete);
	}


	@Override
	@Transactional
	public TagliPfaStep3 saveStep3(TagliPfaStep3 body, ConfigUtente loggedConfig)
			throws RecordNotUniqueException, ValidationException {

		int idIntervento = body.getIdIntervento();

		if (!skOkSelvDAO.isFlgSez2Done(idIntervento))
			throw new ValidationException(SEZIONE_2_VALID_MSG);

		insertOrDeleteParticelleCatastale(body.getParticelleCatastali(), idIntervento,
				loggedConfig.getIdConfigUtente());
		
		System.out.println("--------<saveStep3: "+body.toString()+" ------>");
		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, body.getTotaleSuperficieCatastale());
		
		saveRicadenzeAreeProtette(body.getRicadenzaAreeProtette(), idIntervento);
		saveRicadenzeNature2000(body.getRicadenzaNatura2000(), idIntervento);
		saveRicadenzePopolamentiSeme(body.getRicadenzaPopolamentiDaSeme(), idIntervento);
		saveRicadenzePortaSeme(body.getRicadenzaPortaSeme(), idIntervento, loggedConfig.getIdConfigUtente());
		saveRicadenzeCategorieForestale(body.getRicadenzaCategorieForestali(), idIntervento,
				loggedConfig.getIdConfigUtente());

		
		
		if(body.getRicadenzaIntervento()!=null) {
			try{
				saveRicadenzeIntervento(body.getRicadenzaIntervento(), idIntervento);
			}
		catch(Exception e) {
			logger.info("RECORD già esistente");
		}
		}
		// update intervento selvicolturale with fascia altimetrica
		if (null != body.getFasciaAltimetrica()) {
			intervPfaDAO.updateIntervSelvicolturaleWithFasciaAltimetrica(
					body.getFasciaAltimetrica().getIdFasciaAltimetrica(), idIntervento);
		}

		skOkSelvDAO.updateFlgSez3(idIntervento);

		return body;
	}

	@Override
	@Transactional
	public boolean updateStep3(TagliPfaStep3 body, ConfigUtente loggedConfig, int idIntervento)
			throws ValidationException, RecordNotUniqueException {

		insertOrDeleteParticelleCatastale(body.getParticelleCatastali(), idIntervento,
				loggedConfig.getIdConfigUtente());

		System.out.println("--------<updateStep3: "+body.toString()+" ------>");
		//interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, body.getTotaleSuperficieCatastale());
		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, body.getTotaleSuperficieIntervento());

		saveRicadenzeAreeProtette(body.getRicadenzaAreeProtette(), idIntervento);
		saveRicadenzeNature2000(body.getRicadenzaNatura2000(), idIntervento);
		saveRicadenzePopolamentiSeme(body.getRicadenzaPopolamentiDaSeme(), idIntervento);
		if(body.getRicadenzaPortaSeme()!=null) {
		saveRicadenzePortaSeme(body.getRicadenzaPortaSeme(), idIntervento, loggedConfig.getIdConfigUtente());
		}
		saveRicadenzeCategorieForestale(body.getRicadenzaCategorieForestali(), idIntervento,
				loggedConfig.getIdConfigUtente());

		// update intervento selvicolturale with fascia altimetrica
		intervPfaDAO.updateIntervSelvicolturaleWithFasciaAltimetrica(
				body.getFasciaAltimetrica().getIdFasciaAltimetrica(), idIntervento);

		if(body.getRicadenzaIntervento()!=null) {
			try{
				saveRicadenzeIntervento(body.getRicadenzaIntervento(), idIntervento);
			}
		catch(Exception e) {
			logger.info("RECORD già esistente");
		}
		}
		
		
		// update flasgs step3
		skOkSelvDAO.updateFlgSez3(idIntervento);

		return true;
	}

	@Override
	@Transactional
	public boolean updateStep4(TagliPfaStep4 step4, ConfigUtente loggedConfig, int idIntervento)
			throws ValidationException, RecordNotUniqueException {

		// update intervento selvicolturale
		intervPfaDAO.updateIntervSelvicolturaletagliStep4(step4.getIntervPfaFat(), idIntervento);

		// update intervento
		interventoDAO.updateInterventoDescrizione(step4.getIntervento().getDescrizioneIntervento(), idIntervento,
				loggedConfig.getIdConfigUtente());

		// save viabilità
		deleteViablitaIntervento(idIntervento);
		saveViablitaIntervento(step4.getHeadings(), idIntervento);


		// save species
		deleteSpecieInteressate(idIntervento);
		saveSpecieInteressate(step4.getSpecieInteressate(), idIntervento, loggedConfig.getIdConfigUtente());

		wrapperPfaDAO.updateRipresaInterventoPfa(idIntervento);

		this.updateTipoIstanza(null, idIntervento, loggedConfig);
		// update flasgs step4
		skOkSelvDAO.updateFlgSez4(idIntervento);

		return true;
	}

	@Override
	public TagliPfaStep3 getRicadenze(Integer idIntervento) {
		logger.info("returnRicadenze - idIntervento: " + idIntervento);
		//List<LottoIntervento> listGeomGML = geoPlLottoInterventoDAO.getGeometrieGmlByFkIntervento(idIntervento);
		List<LottoIntervento> listGeomGML = geoPlLottoInterventoDAO.getGeometrieGmlByFkInterventoNew(idIntervento);

		TagliPfaStep3 step3 = new TagliPfaStep3();

		step3.setRicadenzaNatura2000(new ArrayList<RicadenzaInformazioni>());
		step3.setRicadenzaAreeProtette(new ArrayList<RicadenzaInformazioni>());
		

		for (LottoIntervento lotto : listGeomGML) {
			try {
				String geomGML = lotto.getGeometriaGml();
				logger.info("<geomGML: ---"+geomGML+"----->");
				
				//logger.error("<---"+geomGML+"----->");
				
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
				step3.getRicadenzaNatura2000().addAll(fillListRicadenzeFromString(natura2000ListStr));
				
				String areeProtetteListStr = parkApiServiceHelper.getRicadenzaAreeProtette(geomGML);
				logger.info("getRicadenzaAreeProtette >" + areeProtetteListStr + "<");
				step3.getRicadenzaAreeProtette().addAll(fillListRicadenzeFromString(areeProtetteListStr));
				logger.info("natura2000ListStr:------------------> " + step3.getRicadenzaNatura2000());
				logger.info("areeProtetteListStr:------------------> " + step3.getRicadenzaAreeProtette());
			  
			} catch (Exception e) {
				logger.error("errore nel recupero delle ricadenze dal servizio esterno: "+e.getMessage());
			}
		}
		step3.setRicadenzaPopolamentiDaSeme(
				ricadenzaService.getRicadenzePopolamentiSeme(idIntervento, ProceduraEnum.PIANI_FORESTALI_AZIENDALI));
		step3.setRicadenzaIntervento(ricadenzaService.getRicadenzaIntervento(idIntervento));
		  System.out.println("setRicadenzaPopolamentiDaSeme:------------------> " + step3.getRicadenzaPopolamentiDaSeme());
		  
		
		List<RicadenzaInformazioni> ricForest = ricadenzaService.getRicadenzeForestali(idIntervento,
				ProceduraEnum.PIANI_FORESTALI_AZIENDALI);
		this.integrateRicadenzeForestaliFromDB(ricForest, idIntervento);
		step3.setRicadenzaCategorieForestali(ricForest);

		 //step3.setTotaleSuperficieTrasf((new
		 //BigDecimal(geoPlLottoInterventoDAO.getAreaTrasformazioneByFkIntervento(idIntervento)/10000)).setScale(4,
		// RoundingMode.HALF_DOWN));
		step3.setTotaleSuperficieCatastale(
				(new BigDecimal(propcatastoInterventoDAO.getAreaCatastaleByIdIntervento(idIntervento))).setScale(4,
						RoundingMode.HALF_DOWN));
		step3.setTotaleSuperficieIntervento(
				(new BigDecimal(geoPlLottoInterventoDAO.getAreaInterventoByIdIntervento(idIntervento))).setScale(4,
						RoundingMode.HALF_DOWN));
		// GET portaseme
		step3.setRicadenzaPortaSeme(
				ricadenzaService.getRicadenzePortaSeme(idIntervento, ProceduraEnum.PIANI_FORESTALI_AZIENDALI));

		// GET PFA
		step3.setRicadenzaPfa(ricadenzaService.getRicadenzePFA(idIntervento, ProceduraEnum.PIANI_FORESTALI_AZIENDALI));
		//logger.info("Trovato particella----->: " + objectToString(item));
		
		 System.out.println("step3 en complemmento richenza :------------------> " + objectToString(step3));
		return step3;
	}

	@Override
	public PlainParticelleCatastali insertParticellaTagli(ConfigUtente confUtente,
			PlainParticelleCatastali particellaCatasto, Integer idIntervento) throws ServiceException { 
		Random random = new Random();

		TSoggetto soggetto = soggettoDAO.findSoggettoById(confUtente.getFkSoggetto());
		//System.out.println("soggetto: " +objectToString(soggetto));
        //System.out.println("particellaCatasto: " +objectToString(particellaCatasto));
        
		/*InfoParticella[] list = this.sigmaterServiceHelper.getDettaglioDatiTerrenoGeometrie(
				particellaCatasto.getComune().getIstatComune(), particellaCatasto.getSezione(),
				particellaCatasto.getFoglio().toString(), particellaCatasto.getParticella(),
				soggetto.getCodiceFiscale());  // DEvuelve GML por eso necesitabamos (en tiempo pasado) conversion de GML a geoJSON

		if (list == null || list.length == 0) {
			return null;
		}*/
        JsonNode jsonNode;
		String s = "";
		try {
			s = particella.findParticellaById(particellaCatasto.getComune().getIstatComune(), particellaCatasto.getSezione(),particellaCatasto.getFoglio().toString(), particellaCatasto.getParticella()); //geoJSON standar de PostGIS, aqui no tiene el dato de area
			
            ObjectMapper objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readTree(s);

			System.out.println("--jsonNode--");
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
    		//InfoParticella infoParticella = list[0]; // pendiente solo sirve para un poligono <------------------
    		
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

		particellaCatasto = propCatastoDAO.insertParticella(particellaCatasto);

		propcatastoInterventoDAO.insertParticelleInPropcatastoIntervento(idIntervento, particellaCatasto, confUtente);
		geoPlLottoInterventoDAO.addGeometria(idIntervento, particellaCatasto.getIdGeoPlPropCatasto());

		return particellaCatasto;
	}
    
	public static double calculateArea(String coordinates) {
        double area = 0.0;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(coordinates);

            JsonNode coordinatesNode = jsonNode.get("coordinates").get(0);

            int numPoints = coordinatesNode.size();
            double[] x = new double[numPoints];
            double[] y = new double[numPoints];

            for (int i = 0; i < numPoints; i++) {
                JsonNode pointNode = coordinatesNode.get(i);
                x[i] = pointNode.get(0).asDouble();
                y[i] = pointNode.get(1).asDouble();
            }

            for (int i = 0; i < numPoints - 1; i++) {
                area += (x[i] * y[i + 1]) - (x[i + 1] * y[i]);
            }
            area = Math.abs(area) / 2.0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return area;
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
    	
	private static String convertToGML(JsonNode geoJsonNode) {
        StringBuilder gmlBuilder = new StringBuilder();
        gmlBuilder.append("<gml:Polygon srsName=\"EPSG:4326\">");

        JsonNode coordinatesNode = geoJsonNode.get("coordinates");
        if (coordinatesNode != null) {
            gmlBuilder.append("<gml:exterior><gml:LinearRing><gml:posList>");

            for (JsonNode coordinate : coordinatesNode.get(0)) {
                double longitude = coordinate.get(0).asDouble();
                double latitude = coordinate.get(1).asDouble();
                gmlBuilder.append(latitude).append(" ").append(longitude).append(" ");
            }
            gmlBuilder.append("</gml:posList></gml:LinearRing></gml:exterior>");
        }
        gmlBuilder.append("</gml:Polygon>");

        return gmlBuilder.toString();
    }
	
	private void saveRicadenzeCategorieForestale(List<RicadenzaInformazioni> ricadenze, int idIntervento,
			int idConfigUtente) {
		interventoCatforDAO.deleteInterventosById(idIntervento);
		if (ricadenze != null && !ricadenze.isEmpty()) {
			for (RicadenzaInformazioni ricadenza : ricadenze) {
				InterventoCatfor interventoCatfor = new InterventoCatfor();
				interventoCatfor.setIdIntervento(idIntervento);
				interventoCatfor.setIdCategoriaForestale(categoriaForestaleDAO
						.getByCodiceAmministratico(ricadenza.getCodiceAmministrativo()).getIdCategoriaForestale());
				interventoCatfor.setFkConfigUtente(idConfigUtente);
				interventoCatforDAO.insertInterventoCatfor(interventoCatfor);
			}
		}
	}

	private void saveRicadenzeNature2000(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		interventoRn2000DAO.deleteInterventosByIdIntervento(idIntervento);
		if (ricadenze != null && !ricadenze.isEmpty()) {
			for (RicadenzaInformazioni ricadenza : ricadenze) {
				InterventoRn2000 interventoRn2000 = new InterventoRn2000();
				interventoRn2000.setIdIntervento(idIntervento);
				interventoRn2000.setCodiceRn2000(ricadenza.getCodiceAmministrativo());
				interventoRn2000DAO.insertInterventoRn2000(interventoRn2000);
			}
		}

	}

	private void saveRicadenzePopolamentiSeme(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		interventoPopSemeDAO.deleteInterventosByIdIntervento(idIntervento);
		if (ricadenze != null && !ricadenze.isEmpty()) {
			for (RicadenzaInformazioni ricadenza : ricadenze) {
				InterventoPopSeme interventoPopSeme = new InterventoPopSeme();
				interventoPopSeme.setIdIntervento(idIntervento);
				interventoPopSeme.setIdPopolamentoSeme(popolamentoSemeDAO
						.getByCodiceAmministrativo(ricadenza.getCodiceAmministrativo()).getIdPopolamentoSeme());
				interventoPopSemeDAO.insertInterventoPopSeme(interventoPopSeme);
			}
		}
	}

	private void saveRicadenzePortaSeme(List<RicadenzaPortaseme> ricadenze, int idIntervento, int idUtente) {
		interventoPortaSemeDAO.deleteInterventosByIdIntervento(idIntervento);
		//System.out.println("ricadenze:----> " + ricadenze);
		if (ricadenze != null && !ricadenze.isEmpty()) {
			for (RicadenzaPortaseme ricadenza : ricadenze) {

				InterventoPortaSeme interventoPopSeme = new InterventoPortaSeme();
				interventoPopSeme.setIdIntervento(idIntervento);
				interventoPopSeme.setIdPortaSeme(ricadenza.getId());
				interventoPopSeme.setFkConfigUtente(idUtente);
				interventoPortaSemeDAO.insertInterventoPortaSeme(interventoPopSeme);
			}
		}
	}

	private void saveRicadenzeAreeProtette(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);
		if (ricadenze != null && !ricadenze.isEmpty()) {
			for (RicadenzaInformazioni ricadenza : ricadenze) {
				InterventoAapp interventoAapp = new InterventoAapp();
				interventoAapp.setIdIntervento(idIntervento);
				interventoAapp.setCodiceAapp(ricadenza.getCodiceAmministrativo());
				interventoAappDAO.insertInterventoAapp(interventoAapp);
			}
		}

	}

	private void insertOrDeleteParticelleCatastale(List<PlainParticelleCatastali> particelleCatastali, int idIntervento,
			int fkConfigUtente) throws RecordNotUniqueException {
		for (PlainParticelleCatastali particella : particelleCatastali) {

			if (particella.isToDelete() && !particella.getId().equals(0) && particella.getId() != null) {
				propcatastoInterventoDAO.deletePropcatastoIntervento(particella.getId(), idIntervento);
			} else if ((particella.getId() == null || particella.getId().equals(0)) && !particella.isToDelete()) {

				PropcatastoIntervento propcatastoIntervento = new PropcatastoIntervento();
				propcatastoIntervento.setIdgeoPlPropCatasto(particella.getId());
				propcatastoIntervento.setIdIntervento(idIntervento);
				propcatastoIntervento.setFkConfigUtente(fkConfigUtente);
				propcatastoIntervento.setDataInserimento(LocalDate.now());
				propcatastoInterventoDAO.insertPropcatastoIntervento(propcatastoIntervento);

			}
		}
	}
	
	
	private void saveRicadenzeIntervento(List<RicadenzaIntervento> ricadenze, int idIntervento) {
		interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);
		interventoAappDAO.deleteAllInterventoPPRByIdIntervento(idIntervento);
		RicadenzaIntervento ricadenzaIntervento = new RicadenzaIntervento();
		List<RicadenzaIntervento> ricadenzaInterventoEsistente = interventoRicadenzaDAO
				.getInterventosByIdIntervento(idIntervento);

		for (RicadenzaIntervento ricadenza : ricadenze) {
			ricadenzaIntervento.setId(idIntervento);
			ricadenzaIntervento.setTipoVincolo(ricadenza.getTipoVincolo());
			ricadenzaIntervento.setNomeVincolo(ricadenza.getNomeVincolo());
			ricadenzaIntervento.setProvvedimento(ricadenza.getProvvedimento());
			ricadenzaIntervento.setPercentuale(ricadenza.getPercentuale());
			interventoRicadenzaDAO.insertRicadenzaIntervento(ricadenzaIntervento);
			// aggiungi solo se non esiste già il record
			//if (!ricadenzaInterventoEsistente.contains(ricadenzaIntervento)) {

				
			//}
		}
	}
	

	private void insertUtilizzatore(TagliPfaStep2 body, Integer idSoggetto, int idIntervento,
			Integer idConfigUtente) throws RecordNotUniqueException {
		TipoUtilizzatoreTagliEnum tipo = body.getTipoUtilizzatore();
		TSoggetto soggetto;
		Integer newUtilizzatoreId;
		switch (tipo) {
		case IN_PROPRIO:
			// CASO 1 -- in proprio
			// associate richiedente as utilizzatore
			associateSoggettoInterventoUtilizzatore(idConfigUtente, body.getTipoRichiedenteId(), idSoggetto,
					idIntervento);
			break;
		case PERSONA_FISICA:
			soggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getUtilizzatore().getCodiceFiscale());
			if (soggetto != null) {
				updateSoggettoStep2(body.getUtilizzatore(), soggetto);
				newUtilizzatoreId = soggetto.getIdSoggetto();
			} else {
				newUtilizzatoreId = insertSoggettoStep2(idConfigUtente, body.getUtilizzatore());
			}
			associateSoggettoInterventoUtilizzatore(idConfigUtente, body.getTipoRichiedenteId(), newUtilizzatoreId,
					idIntervento);
			logger.info("new persona fisica");
			break;
		case PERSONA_GIURIDICA:

			// if is taif
			Boolean boolTaif = null;
			if (body != null && body.getUtilizzatore() != null) {
				boolTaif = body.getUtilizzatore().getTAIF();
			}

			if (boolTaif != null && boolTaif.booleanValue()) {
				TAIFAnagraficaAzienda taif = body.getUtilizzatore().getSoggettoTaif();
				PersonaFisGiu taifSoggetto = taifService.fillPersonaGiuridicaByTaifService(taif);
				soggetto = soggettoDAO.findAziendaByCodiceFiscale(taifSoggetto.getCodiceFiscale());
				if (soggetto != null) {
					updateSoggettoStep2(taifSoggetto, soggetto);
					newUtilizzatoreId = soggetto.getIdSoggetto();
				} else {
					newUtilizzatoreId = insertSoggettoStep2(idConfigUtente, taifSoggetto);
				}
				associateSoggettoInterventoUtilizzatoreTaif(idConfigUtente, body.getTipoRichiedenteId(),
						newUtilizzatoreId, idIntervento, taifSoggetto.getNumAlboForestale());
			} else {
				soggetto = soggettoDAO.findAziendaByCodiceFiscale(body.getUtilizzatore().getCodiceFiscale());
				if (soggetto != null) {
					updateSoggettoStep2(body.getUtilizzatore(), soggetto);
					newUtilizzatoreId = soggetto.getIdSoggetto();
				} else {
					newUtilizzatoreId = insertSoggettoStep2(idConfigUtente, body.getUtilizzatore());
				}
				associateSoggettoInterventoUtilizzatore(idConfigUtente, body.getTipoRichiedenteId(), newUtilizzatoreId,
						idIntervento);
			}
			logger.info("new persona giuridica");
			break;
		case DA_INDIVIDUARE:
		default:
			break;
		}
	}

	private void insertTecnicoForestale(PersonaFisGiu tecnico, int idIntervento, Integer idConfigUtente)
			throws RecordNotUniqueException {
		Integer tecnicoForestaleId;

		if (null == tecnico.getCodiceFiscale()) {
			return;
		}

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(tecnico.getCodiceFiscale());

		if (soggetto != null) {
			updateTecnicoForestale(tecnico, soggetto);
			tecnicoForestaleId = soggetto.getIdSoggetto();
		} else {
			tecnicoForestaleId = createTecnicoForestale(idConfigUtente, tecnico);
		}
		// add tecnico to Intervento
		interventoDAO.updateInterventoSoggettoProfess(tecnicoForestaleId, idIntervento, idConfigUtente);
		logger.info("new persona fisica");
	}

	private void associateSoggettoInterventoPG(ConfigUtente loggedConfig, int owner, int idSoggetto)
			throws RecordNotUniqueException {

		if (loggedConfig.getFkTipoAccreditamento() == TipoAccreditamentoEnum.RICHIEDENTE.getValue()) {
			PfPg pfPgFromDB = pfPgDAO.getBySoggettoPfAndSoggettoPg(owner, idSoggetto);
			if (pfPgFromDB == null) {
				PfPg pfPg = new PfPg();
				pfPg.setIdSoggettoPf(owner);
				pfPg.setIdSoggettoPg(idSoggetto);
				pfPg.setDataInserimento(LocalDate.now());
				pfPg.setDataInizioValidita(LocalDate.now());
				pfPg.setFkConfigUtente(loggedConfig.getIdConfigUtente());
				pfPgDAO.createPfPg(pfPg);
			}
		}
	}

	private int insertSoggettoStep2(Integer idConfigUtente, PersonaFisGiu body) throws RecordNotUniqueException {
		TSoggetto newSoggetto = new TSoggetto();

		newSoggetto.setCodiceFiscale(body.getCodiceFiscale());
		newSoggetto.setIndirizzo(body.getIndirizzo());
		newSoggetto.setNrTelefonico(body.getNrTelefonico());
		newSoggetto.seteMail(body.geteMail());
		newSoggetto.setDataInserimento(LocalDate.now());
		newSoggetto.setCivico(body.getCivico());
		newSoggetto.setCap(body.getCap());
		newSoggetto.setFlgSettoreRegionale((byte) 0);

		newSoggetto.setCognome(body.getCognome());
		newSoggetto.setNome(body.getNome());
		newSoggetto.setPartitaIva(body.getPartitaIva());
		newSoggetto.setDenominazione(body.getDenominazione());
		newSoggetto.setPec(body.getPec());
		newSoggetto.setFkConfigUtente(idConfigUtente);

		if (body.getFlgEntePubblico() != null) {
			newSoggetto.setFlgEntePubblco((byte) (body.getFlgEntePubblico() ? 1 : 0));
		}

		if (null != body.getComune()) {
			ComuneResource comuneResource = comuneDAO.findComuneResourceByID(body.getComune().getIdComune());
			if (comuneResource != null) {
				newSoggetto.setFkComune(comuneResource.getIdComune());
			}
		}
		return soggettoDAO.createSoggetto(newSoggetto);

	}

	private Integer createTecnicoForestale(Integer idConfigUtente, PersonaFisGiu body) throws RecordNotUniqueException {
		TSoggetto newSoggetto = new TSoggetto();

		newSoggetto.setCodiceFiscale(body.getCodiceFiscale());
		newSoggetto.setIndirizzo(body.getIndirizzo());
		newSoggetto.setNrTelefonico(body.getNrTelefonico());
		newSoggetto.seteMail(body.geteMail());
		newSoggetto.setDataInserimento(LocalDate.now());
		newSoggetto.setCivico(body.getCivico());
		newSoggetto.setCap(body.getCap());
		newSoggetto.setFlgSettoreRegionale((byte) 0);

		newSoggetto.setCognome(body.getCognome());
		newSoggetto.setNome(body.getNome());
		newSoggetto.setPartitaIva(body.getPartitaIva());
		newSoggetto.setFkConfigUtente(idConfigUtente);

		newSoggetto.setnIscrizioneOrdine(body.getNrIscrizioneOrdine());
		// provincia albo
		if (null != body.getProvIscrizioneOrdine()) {
			newSoggetto.setIstatProvIscrizioneOrdine(body.getProvIscrizioneOrdine().getIstatProv());
		}
		// num martello
		newSoggetto.setNrMartelloForestale(body.getNrMartelloForestale());

		ComuneResource comuneResource = comuneDAO.findComuneResourceByID(body.getComune().getIdComune());
		if (comuneResource != null) {
			newSoggetto.setFkComune(comuneResource.getIdComune());
		}
		return soggettoDAO.createSoggetto(newSoggetto);
	}

	private void associazionePFIntervento(Integer idConfigUtente, Integer idTipoRichidente, Integer idSoggetto,
			int idIntervento) {
		SoggettoIntervento soggettoIntervento = getSoggettoIntervento(idConfigUtente, idTipoRichidente, idSoggetto,
				idIntervento);
		soggettoInterventoDAO.createSoggettoIntervento(soggettoIntervento);
	}

	private void associazionePGIntervento(Integer idConfigUtente, Integer idTipoRichiedente, Integer idSoggetto,
			int idIntervento, String nrAlbo) {
		SoggettoIntervento soggettoIntervento = getSoggettoIntervento(idConfigUtente, idTipoRichiedente, idSoggetto,
				idIntervento);
		soggettoIntervento.setNrAlboForestale(nrAlbo);
		soggettoInterventoDAO.createSoggettoIntervento(soggettoIntervento);
	}

	private void associateSoggettoInterventoUtilizzatore(Integer idConfigUtente, Integer idTipoRichiedente,
			Integer idSoggetto, int idIntervento) {
		SoggettoIntervento soggettoIntervento = getSoggettoIntervento(idConfigUtente, idTipoRichiedente, idSoggetto,
				idIntervento);
		// override id tipo soggetto
		soggettoIntervento.setIdTipoSoggetto(2);
		soggettoIntervento.setFkTipoRichiedente(0);
		soggettoInterventoDAO.createSoggettoIntervento(soggettoIntervento);
	}

	private void associateSoggettoInterventoUtilizzatoreTaif(Integer idConfigUtente, Integer idTipoRichiedente,
			Integer idSoggetto, int idIntervento, String nrAlbo) {
		SoggettoIntervento soggettoIntervento = getSoggettoIntervento(idConfigUtente, idTipoRichiedente, idSoggetto,
				idIntervento);
		// override id tipo soggetto
		soggettoIntervento.setIdTipoSoggetto(2);
		soggettoIntervento.setFkTipoRichiedente(0);
		soggettoIntervento.setNrAlboForestale(nrAlbo);
		soggettoInterventoDAO.createSoggettoIntervento(soggettoIntervento);
	}

	private SoggettoIntervento getSoggettoIntervento(Integer idConfigUtente, Integer idTipoRichiedente,
			Integer idSoggetto, int idIntervento) {
		SoggettoIntervento soggettoIntervento = new SoggettoIntervento();
		soggettoIntervento.setIdIntervento(idIntervento);
		soggettoIntervento.setIdSoggetto(idSoggetto);
		soggettoIntervento.setFkConfigUtente(idConfigUtente);
		soggettoIntervento.setDataInizioValidita(LocalDate.now());
		soggettoIntervento.setFkTipoRichiedente(idTipoRichiedente);
		soggettoIntervento.setIdTipoSoggetto(1);
		return soggettoIntervento;
	}

	private void updateSoggettoStep2(PersonaFisGiu body, TSoggetto soggetto) throws RecordNotUniqueException {
		soggetto.setCodiceFiscale(body.getCodiceFiscale());
		soggetto.setIndirizzo(body.getIndirizzo());
		soggetto.setNrTelefonico(body.getNrTelefonico());
		soggetto.seteMail(body.geteMail());
		soggetto.setDataAggiornamento(LocalDate.now());

		soggetto.setCivico(body.getCivico());
		soggetto.setCap(body.getCap());

		soggetto.setCognome(body.getCognome());
		soggetto.setNome(body.getNome());
		soggetto.setPartitaIva(body.getPartitaIva());
		soggetto.setDenominazione(body.getDenominazione());
		soggetto.setPec(body.getPec());

		if (body.getFlgEntePubblico() != null) {
			soggetto.setFlgEntePubblco((byte) (body.getFlgEntePubblico() ? 1 : 0));
		}

		ComuneResource comuneResource = comuneDAO.findComuneResourceByID(body.getComune().getIdComune());
		if (comuneResource != null) {
			soggetto.setFkComune(comuneResource.getIdComune());
		}

		soggettoDAO.updateSoggetto(soggetto);

	}

	private void updateTecnicoForestale(PersonaFisGiu body, TSoggetto soggetto) throws RecordNotUniqueException {
		soggetto.setCodiceFiscale(body.getCodiceFiscale());
		soggetto.setIndirizzo(body.getIndirizzo());
		soggetto.setNrTelefonico(body.getNrTelefonico());
		soggetto.seteMail(body.geteMail());
		soggetto.setPec(body.getPec());
		soggetto.setDataAggiornamento(LocalDate.now());

		soggetto.setCivico(body.getCivico());
		soggetto.setCap(body.getCap());

		soggetto.setCognome(body.getCognome());
		soggetto.setNome(body.getNome());
		soggetto.setPartitaIva(body.getPartitaIva());

		// iscirizone albo
		soggetto.setnIscrizioneOrdine(body.getNrIscrizioneOrdine());
		// provincia albo
		if (null != body.getProvIscrizioneOrdine()) {
			soggetto.setIstatProvIscrizioneOrdine(body.getProvIscrizioneOrdine().getIstatProv());
		}
		// num martello
		soggetto.setNrMartelloForestale(body.getNrMartelloForestale());

		ComuneResource comuneResource = comuneDAO.findComuneResourceByID(body.getComune().getIdComune());
		if (comuneResource != null) {
			soggetto.setFkComune(comuneResource.getIdComune());
		}

		soggettoDAO.updateSoggetto(soggetto);

	}

	private void registraDelegaSeNecessario(String codiceFiscale, ConfigUtente loggedConfig)
			throws RecordNotUniqueException {
		if (loggedConfig.getFkTipoAccreditamento() == 2
				&& loggedConfig.getFkProfiloUtente() == ProfiloUtenteEnum.CITTADINO.getValue()) {
			// se professionista gestire eventuale nuova delega ed e' profilo cittadino
			Delega delega = delegaDao.getByCfDeleganteAndConfigUtente(codiceFiscale, loggedConfig.getIdConfigUtente());
			if (delega == null) {
				logger.debug("Delega non presente");
				delega = new Delega();
				delega.setCfDelegante(codiceFiscale);
				delega.setIdConfigUtente(loggedConfig.getIdConfigUtente());
				delega.setDataInizio(LocalDate.now());
				delegaDao.createDelega(delega);
				logger.debug("Delega inserit");
			} else {
				logger.debug("Delega gia presente con dati ");
			}
		} else {
			logger.debug("No professionista no delega");
		}
	}

	private IstanzaForest getIstanzaForest(int compilerSoggettoConfigUtente, int idIntervento) {
		IstanzaForest istanzaForest = new IstanzaForest();

		istanzaForest.setIdIstanzaIntervento(idIntervento);
		istanzaForest.setDataInserimento(LocalDate.now());
		istanzaForest.setFkStatoIstanza(InstanceStateEnum.BOZZA.getStateValue());
		istanzaForest.setFkTipoIstanza(TipoIstanzaEnum.COMUNICAZIONE.getTypeValue());
		istanzaForest.setNrIstanzaForestale(istanzaForestDAO.getNumberOfInstanceTagli() + 1);
		istanzaForest.setFkConfigUtente(compilerSoggettoConfigUtente);
		return istanzaForest;
	}

	private List<PlainParticelleCatastali> mapPropCatastosToParticelleCatastali(List<PropCatasto> propCatastos)
			throws RecordNotUniqueException {
		List<PlainParticelleCatastali> response = new ArrayList<>();

		for (PropCatasto propCatasto : propCatastos) {
			PlainParticelleCatastali particelleCatastali = new PlainParticelleCatastali();
			particelleCatastali.setId(propCatasto.getIdGeoPlPropCatasto());

			ComuneResource comune = comuneDAO.findComuneResourceByID(propCatasto.getFkComune());
			particelleCatastali.setComune(comune);

			particelleCatastali.setSezione(propCatasto.getSezione());
			particelleCatastali.setFoglio(propCatasto.getFoglio());
			particelleCatastali.setParticella(propCatasto.getParticella());
			particelleCatastali.setSupCatastale(propCatasto.getSupCatastaleMq());
			particelleCatastali.setSupIntervento(propCatasto.getSupInterventoHa());
			particelleCatastali.setToDelete(false);

			response.add(particelleCatastali);
		}

		return response;
	}

	private List<RicadenzaInformazioni> fillListRicadenzeFromString(String serviceResponse) {
		List<RicadenzaInformazioni> listRicadenze = new ArrayList<RicadenzaInformazioni>();
		if (serviceResponse != null && serviceResponse.indexOf(" ") > 0) {
			String[] ricadenzeStr = serviceResponse.split("; ");
			for (String ricadenzaStr : ricadenzeStr) {
				listRicadenze.add(fillRicadenzaFromString(ricadenzaStr));
			}
		}
		return listRicadenze;
	}

	private RicadenzaInformazioni fillRicadenzaFromString(String ricadenzaStr) {
		RicadenzaInformazioni item = new RicadenzaInformazioni();
		int idx = ricadenzaStr.indexOf(" ");
		item.setCodiceAmministrativo(ricadenzaStr.substring(0, idx));
		item.setNome(ricadenzaStr.substring(idx + 1));
		return item;
	}

	private PlainParticelleCatastali getPlainParticelleCatastaliFromInfoParticella(InfoParticella infoPar,
			ComuneDAO comuneDAO, ConfigUtente configUtente) throws RecordNotUniqueException {
		PlainParticelleCatastali item = new PlainParticelleCatastali();

		item.setComune(comuneDAO.findComuneResourceByIstatComune(infoPar.getCodIstatComune()));
		item.setSezione(infoPar.getSezione());
		item.setFoglio(Integer.parseInt(infoPar.getFoglio()));
		item.setParticella(infoPar.getNumero());
		item.setFkConfigUtente(configUtente.getIdConfigUtente());
		item.setSupCatastale(new BigDecimal(infoPar.getArea() / 10000, MathContext.DECIMAL64));
		item.setGeometry(infoPar.getGeometriaGML());
		return item;
	}

	private List<TagliHeading> buildTagliHeadings(Integer idIntervento) {
		List<UsoViabilita> usoViabilitaList = usoViabilitaDAO.findAllUsoViabilita();
		// find checked
		List<Integer> checked = usoviabInterventoselvDAO.getAllByIntervento(idIntervento);

		List<TagliHeading> headings = new ArrayList<>();
		for (UsoViabilita uso : usoViabilitaList) {
			boolean isChecked = checked.contains(uso.getIdUsoViabilita());
			headings.add(getTaglioHeading(uso, idIntervento, isChecked));
		}
		return headings;
	}

	private TagliHeading getTaglioHeading(UsoViabilita uso, Integer idIntervento, boolean isChecked) {

		TagliHeading head = new TagliHeading();
		head.setId(uso.getIdUsoViabilita());
		head.setName(uso.getDescrUsoViabilita());
		head.setMtdOrdinamento(uso.getMtdOrdinamento());
		head.setVisibility(uso.getFlgVisible() == 1);
		head.setChecked(isChecked);

		List<TagliSubHeading> subHeadings = new ArrayList<>();
		switch (uso.getIdUsoViabilita()) {
		case 1:
		case 3: {
			List<TipoViabilita> tipoViabilitaList = tipoViabilitaDAO.findAllByConfigIpla(3);
			List<Integer> ids = tipoViabUsoviabInterventoDAO.findAllIdsByInterventoAndUsoViab(idIntervento,
					uso.getIdUsoViabilita());
			for (TipoViabilita item : tipoViabilitaList) {
				boolean exist = ids.contains(item.getIdTipoViabilita());
				TagliSubHeading sh = getTagliSubHeading(item, exist);
				subHeadings.add(sh);
			}
			break;
		}
		case 2:
			List<Esbosco> esboscoList = esboscoDAO.findAllEsbosco();
			List<String> codes = esboscoIntervselvDAO.findAllCodesByIntervento(idIntervento);
			for (Esbosco item : esboscoList) {
				boolean exist = codes.contains(item.getCodEsbosco());
				TagliSubHeading sh = getTagliSubHeading(item, exist);
				subHeadings.add(sh);
			}
			break;
		}

		head.setSubheadings(subHeadings);
		return head;
	}

	private TagliSubHeading getTagliSubHeading(Esbosco item, boolean isChecked) {
		TagliSubHeading sh = new TagliSubHeading();
		sh.setCodice(item.getCodEsbosco());
		sh.setDescrizione(item.getDescrEsbosco());
		sh.setVisibility(item.getFlgVisibile() == 1);
		sh.setOrdinamento(item.getMtdOrdinamento());
		sh.setChecked(isChecked);
		return sh;
	}

	private TagliSubHeading getTagliSubHeading(TipoViabilita item, boolean isChecked) {
		TagliSubHeading sh = new TagliSubHeading();
		sh.setId(item.getIdTipoViabilita() != null ? item.getIdTipoViabilita().toString() : null);
		sh.setCodice(item.getCodiceTipoViabilita());
		sh.setDescrizione(item.getTipoViabilita());
		sh.setVisibility(item.getFlgVisible() == 1);
		sh.setOrdinamento(item.getMtdOrdinamento());
		sh.setChecked(isChecked);
		return sh;
	}

	private void integrateRicadenzeForestaliFromDB(List<RicadenzaInformazioni> ricForest, Integer idIntervento) {

		List<CategoriaForestale> categorieForestale = categoriaForestaleDAO.getAllByIdInterventoCatfors(idIntervento);
		for (CategoriaForestale categoria : categorieForestale) {
			RicadenzaInformazioni ric = new RicadenzaInformazioni(categoria.getCodiceAmministrativo());

			if (!ricForest.contains(ric)) {
				ric.setNome(categoria.getDescrizione());
				ricForest.add(ric);
			}
		}
	}

//	private void convertGeoJson (PlainParticelleCatastali particellaCatasto) {
//	Object a = new Object();
//	String geometry=particellaCatasto.getGeometry().toString();	
//	Geometry nuovo;
//	//GeoJSONGeometryConverter.geoJsonToGeometry(geometry);
//
//	}
}
