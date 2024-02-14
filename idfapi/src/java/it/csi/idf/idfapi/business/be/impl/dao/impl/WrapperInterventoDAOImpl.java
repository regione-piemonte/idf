/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.csi.idf.idfapi.business.be.impl.dao.*;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.PlainSezioniApiServiceImpl;
import it.csi.idf.idfapi.business.be.service.GEECO;
import it.csi.idf.idfapi.business.be.service.SIGMATER;
import it.csi.idf.idfapi.business.be.service.helper.ParkApiServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.SigmaterServiceHelper;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.validation.AllStepsValidator;
import it.csi.idf.idfapi.validation.PfaAllegatiValidator;
import it.csi.idf.idfapi.validation.PfaDatiTecniciValidator;
import it.csi.idf.idfapi.validation.PfaUtilizzatoreETecnicoValidator;
import it.csi.idf.idfapi.validation.StepValidationErrors;

@Component
public class WrapperInterventoDAOImpl implements WrapperInterventoDAO {
	
	private static Logger logger = Logger.getLogger(WrapperInterventoDAOImpl.class);

	private static final String SEZIONE_1_VALID_MSG = "Sezione 1 deve essere completata";
	private static final String SEZIONE_2_VALID_MSG = "Sezione 2 deve essere completata";
	private static final String SEZIONE_3_VALID_MSG = "Sezione 3 deve essere completata";
	private static final String SEZIONE_4_VALID_MSG = "Sezione 4 deve essere completata";
	private static final String STATO_PROGRAMMATO = "Programmato";
	private static final String STATO_CONCLUSO = "Concluso";
	private static final String STATO_ESEGUITO = "Eseguito";
	private static final String STATO_IN_CORSO = "In corso";
	private static final String STATO_IN_CORSO_DI_TRASMISSIONE = "In corso di trasmissione";

	@Autowired
	private IntervSelvicoulturaleDAO intervSelvicoulturaleDAO;
	@Autowired
	private IntervPfaDAO intervPfaDAO;
	@Autowired
	private PartforInterventoDAO partforInterventoDAO;
	@Autowired
	private IntervselEventoDAO intervselEventoDAO;
	@Autowired
	private InterventoDAO interventoDAO;
	@Autowired
	private UsoviabInterventoselvDAO usoviabInterventoselvDAO;
	@Autowired
	private EsboscoIntervselvDAO esboscoIntervselvDAO;
	@Autowired
	private SkOkSelvDAOImpl skOkSelvDAO;
	@Autowired
	private SoggettoDAOImpl soggettoDAO;
	@Autowired
	private TipoInterventoDatiTecniciDAOImpl tipoInterventoDatiTecniciDAO;
	@Autowired
	private ShootingMirrorDAO shootingMirrorDAO;
	@Autowired
	private TipoInterventoDAO tipoInterventoDAO;
	@Autowired
	private SpeciePfaInterventoDAO speciePfaInterventoDAO;
	@Autowired
	private IstanzaForestDAO istanzaForestDAO;
	@Autowired
	private DescrizioneInterventoDAO descrizioneInterventoDAO;
	@Autowired
	private PropCatastoDAO propCatastoDAO;
	@Autowired
	private SoggettoInterventoDAO soggettoInterventoDAO;
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	SIGMATER sigmater;
	@Autowired
	private GeoLnLottoInterventoDAO geoLnLottoInterventoDAO;
	@Autowired
	private GeoPlLottoInterventoDAO geoPlLottoInterventoDAO;
	@Autowired
	private GeoPtLottoInterventoDAO geoPtLottoInterventoDAO;
	@Autowired
	private PopolamentoSemeDAO popolamentoSemeDAO;
	@Autowired
	private InterventoAappDAO interventoAappDAO;
	@Autowired
	private InterventoRn2000DAO interventoRn2000DAO;
	@Autowired
	private InterventoCatforDAO interventoCatforDAO;
	@Autowired
	private InterventoPopSemeDAO interventoPopSemeDAO;
	@Autowired
	private CategoriaForestaleDAO categoriaForestaleDAO;
	@Autowired
	private ComuneDAO comuneDao;
	@Autowired
	private PropcatastoInterventoDAO propcatastoInterventoDAO;
	@Autowired
	private GEECO geeco;
	@Autowired
	private DocumentoAllegatoDAO documentoAllegatoDAO;
	@Autowired
	private WrapperIstanzaDAO wrapperIstanzaDAO;
	@Autowired
	private IntervTrasfDAO intervTrasfDAO;
	@Autowired
	private ParkApiServiceHelper parkApiServiceHelper;
	@Autowired
	private RicadenzeDAO ricadenzeDAO;
	@Autowired
	private GeoPlParticellaForestDAO geoPlParticellaForestDAO;
	@Autowired
	private SigmaterServiceHelper sigmaterServiceHelper;
	@Autowired
	private ComuneDAO comuneDAO;
	
	

	@Override
	public void saveSecondStep(InterventoDatiTehnici interventoDatiTehnici, ConfigUtente loggedConfig,
			Integer idIntervento) throws RecordNotFoundException, RecordNotUniqueException, ValidationException {

		if (!skOkSelvDAO.isFlgSez1Done(idIntervento)) {
			throw new ValidationException(SEZIONE_1_VALID_MSG);
		}

		validateTipoIntervento(interventoDatiTehnici.getTipoIntervento());
		validateIntervSelvicolturale(interventoDatiTehnici.getIntervSelvicolturale());

		try {
			interventoDAO.updateIntervento(interventoDatiTehnici.getTipoIntervento(), idIntervento,
					loggedConfig.getIdConfigUtente());

			intervSelvicoulturaleDAO.updateIntervSelvicolturale(interventoDatiTehnici.getIntervSelvicolturale(),
					idIntervento);
			
			updateRipresaInterventoPfa(idIntervento);

			intervselEventoDAO.createIntervselEvento(interventoDatiTehnici.getTipoIntervento().getIdEventoCorelato(),
					idIntervento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		try {
			speciePfaInterventoDAO.batchCreate(interventoDatiTehnici.getSpeciePfaIntervento(), idIntervento);
		} catch (Exception e) {
			throw new ValidationException("SPECIE ALREADY INSERTED");
		}
		Integer idUsoViabilita = interventoDatiTehnici.getIntervSelvicolturale().getIdUsoViabilita();
		if(idUsoViabilita != null && idUsoViabilita > 0) {
			usoviabInterventoselvDAO.createIntervselUsovib(idUsoViabilita, idIntervento);
		}
		esboscoIntervselvDAO.createIntervselvEsbosco(interventoDatiTehnici.getIntervSelvicolturale().getCodEsbosco(),
				idIntervento);

		try {
			istanzaForestDAO.createIstanzaForest(getIstanzaForest(loggedConfig.getIdConfigUtente(),
					interventoDatiTehnici.getIdTipoIstanza(), idIntervento));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	@Transactional
	@Override
	public PlainSezioneId saveDatiTecniciNEW(InterventoDatiTehnici interventoDatiTehnici, ConfigUtente loggedConfig,
			Integer idGeoPlPfa) throws RecordNotFoundException, RecordNotUniqueException, ValidationException {

//		validateTipoIntervento(interventoDatiTehnici.getTipoIntervento());
//		validateIntervSelvicolturale(interventoDatiTehnici.getIntervSelvicolturale());

		int idIntervento = interventoDAO.createInterventoNEW(interventoDatiTehnici.getTipoIntervento(), loggedConfig);

		intervSelvicoulturaleDAO.insertIntervSelvicolturaleNEW(interventoDatiTehnici.getIntervSelvicolturale(),
				idGeoPlPfa, loggedConfig, idIntervento);
		
		updateRipresaInterventoPfa(idIntervento);

		if(interventoDatiTehnici.getTipoIntervento().getIdEventoCorelato() != null) {
			intervselEventoDAO.createIntervselEventoNEW(interventoDatiTehnici.getTipoIntervento().getIdEventoCorelato(),
				idIntervento, loggedConfig.getIdConfigUtente());
		}

		try {
			speciePfaInterventoDAO.batchCreate(interventoDatiTehnici.getSpeciePfaIntervento(), idIntervento);
		} catch (Exception e) {
			throw new ValidationException("SPECIE ALREADY INSERTED");
		}

		try {
			istanzaForestDAO.createIstanzaForest(getIstanzaForest(loggedConfig.getIdConfigUtente(),	interventoDatiTehnici.getIdTipoIstanza(), idIntervento));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

//		usoviabInterventoselvDAO.createIntervselUsovib(
//				interventoDatiTehnici.getIntervSelvicolturale().getIdUsoViabilita(), idIntervento);
//		esboscoIntervselvDAO.createIntervselvEsbosco(interventoDatiTehnici.getIntervSelvicolturale().getCodEsbosco(),
//				idIntervento);

		skOkSelvDAO.insertFlgSez1(idIntervento);
		//skOkSelvDAO.updateFlgSez2(idIntervento);

		return new PlainSezioneId(idIntervento);

	}
	
	@Override
	public List<StepValidationErrors> getErrorsInterventoPfa(Integer idIntervento) throws Exception {
		AllStepsValidator allStepsValidator = new AllStepsValidator();
		int currentStep = skOkSelvDAO.sumFlgSeziones(idIntervento);
		
		if(currentStep > 0) {
			InterventoDatiTehnici datiTecnici = getDatiTechiciForEditNEW(idIntervento);
			allStepsValidator.addStepValidator(new PfaDatiTecniciValidator(datiTecnici));
			if(currentStep > 1) {
				InterventoUtilizzatore interventoUtilizzatore = getUtilizzatoreETecnico(idIntervento);
				allStepsValidator.addStepValidator(new PfaUtilizzatoreETecnicoValidator(interventoUtilizzatore));
				if(currentStep > 2) {
					List<FatDocumentoAllegato> listAllegati = documentoAllegatoDAO.findFatDocumentiByIdIntervento(idIntervento);
					TipoInterventoDatiTecnici tipoIntervDatiTechici = datiTecnici.getTipoIntervento();
					allStepsValidator.addStepValidator(new PfaAllegatiValidator(listAllegati,tipoIntervDatiTechici));
				}
			}
		}
		allStepsValidator.validateAllSteps();
		return allStepsValidator.getStepValidatorErrors();
		
		
	}

	private void validateIntervSelvicolturale(IntervSelvicolturale intervSelvicolturale) {
		if (intervSelvicolturale.getFkGoverno() == null || intervSelvicolturale.getFkTipoIntervento() == null
				|| intervSelvicolturale.getFkFasciaAltimetrica() == null
				|| intervSelvicolturale.getFkFinalitaTaglio() == null
				|| intervSelvicolturale.getFkDestLegname() == null) {
			logger.info("Error validateIntervSelvicolturale: " + 
					(intervSelvicolturale.getFkGoverno() == null?"FkGoverno is null":"") +
					(intervSelvicolturale.getFkTipoIntervento() == null?"FkTipoIntervento is null":"") +
					(intervSelvicolturale.getFkFasciaAltimetrica() == null?"FkFasciaAltimetrica is null":"") +
					(intervSelvicolturale.getFkFinalitaTaglio() == null?"FkFinalitaTaglio is null":"") +
					(intervSelvicolturale.getFkDestLegname() == null?"FkDestLegname is null":"") );
				throw new ValidationException(ErrorConstants.CAMPO_OBBLIGATORIO);
		}

	}

	private void validateTipoIntervento(TipoInterventoDatiTecnici tipoIntervento) {

		if (tipoIntervento.getDataPresuntaIntervento() == null
				|| tipoIntervento.getLocalita() == null || tipoIntervento.getDescrizione() == null) {
			logger.info("Error validateTipoIntervento: " + 
				(tipoIntervento.getDataPresuntaIntervento() == null?"DataPresuntaIntervento is null":"") +
				(tipoIntervento.getLocalita() == null?"Localita is null":"") +
				(tipoIntervento.getDescrizione() == null?"Descrizione is null":"") );
			throw new ValidationException(ErrorConstants.CAMPO_OBBLIGATORIO);
		}
	}

	@Transactional
	@Override
	public List<StepValidationErrors> updateDatiTecniciNEW(Integer idIntervento, InterventoDatiTehnici intervDatiTehnici,
			ConfigUtente loggedConfig, Integer idGeoPlPfa) throws RecordNotFoundException {

		interventoDAO.updateInterventoAtDatiTecnici(intervDatiTehnici.getTipoIntervento(), idIntervento,
				loggedConfig.getIdConfigUtente());
		
		IntervSelvicolturale intSelv = intervDatiTehnici.getIntervSelvicolturale();
		if(intSelv.getVolumeRamagliaM3() != null && intSelv.getStimaMassaRetraibileM3() != null) {
			intSelv.setM3Prelevati(intSelv.getVolumeRamagliaM3() + intSelv.getStimaMassaRetraibileM3());
		}

		intervSelvicoulturaleDAO.updateIntervSelvicolturale(intervDatiTehnici.getIntervSelvicolturale(), idIntervento);
		
		updateRipresaInterventoPfa(idIntervento);
		
		if(intervDatiTehnici.getTipoIntervento().getIdEventoCorelato() != null) {
			if(intervselEventoDAO.exitsInterventoEvento(idIntervento)) {
				intervselEventoDAO.updateIntervselEvento(intervDatiTehnici.getTipoIntervento().getIdEventoCorelato(),idIntervento);
			}else {
				intervselEventoDAO.createIntervselEvento(intervDatiTehnici.getTipoIntervento().getIdEventoCorelato(),idIntervento);
			}
		}

		try {
			speciePfaInterventoDAO.deleteAllSpeciePfaInterventoByIdIntervento(idIntervento);
			speciePfaInterventoDAO.batchCreate(intervDatiTehnici.getSpeciePfaIntervento(), idIntervento);
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
		Integer idUsoViabilita = intervDatiTehnici.getIntervSelvicolturale().getIdUsoViabilita();
		if(idUsoViabilita != null && idUsoViabilita > 0) {
			if(usoviabInterventoselvDAO.isInterventoPresente(idIntervento)) {
				usoviabInterventoselvDAO.updateIntervselUsovib(intervDatiTehnici.getIntervSelvicolturale().getIdUsoViabilita(),	idIntervento);
			}else {
				usoviabInterventoselvDAO.createIntervselUsovib(intervDatiTehnici.getIntervSelvicolturale().getIdUsoViabilita(),	idIntervento);
			}
		}
		String codEsbosco = intervDatiTehnici.getIntervSelvicolturale().getCodEsbosco();
		if(codEsbosco != null) {
			if(esboscoIntervselvDAO.isInterventoPresente(idIntervento)) {
				esboscoIntervselvDAO.updateIntervselvEsbosco(codEsbosco,idIntervento);
			}else {
				esboscoIntervselvDAO.createIntervselvEsbosco(codEsbosco,idIntervento);
			}
		}
		
		try {
			if(istanzaForestDAO.getByIdIntervento(idIntervento) == null) {
				IstanzaForest istanza = getIstanzaForest(loggedConfig.getIdConfigUtente(),intervDatiTehnici.getIdTipoIstanza(), idIntervento);
				istanzaForestDAO.createIstanzaForest(istanza);
			}else {
				istanzaForestDAO.updateTipoIstanza(intervDatiTehnici.getIdTipoIstanza(),loggedConfig.getIdConfigUtente(), idIntervento);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		skOkSelvDAO.updateFlgSez2(idIntervento);
		
		AllStepsValidator allStepsValidator = new AllStepsValidator();
		InterventoDatiTehnici datiTecnici = getDatiTechiciForEditNEW(idIntervento);
		allStepsValidator.addStepValidator(new PfaDatiTecniciValidator(datiTecnici));
		allStepsValidator.validateAllSteps();
		return allStepsValidator.getStepValidatorErrors();

	}

	@Transactional
	@Override
	public void deleteIntervento(Integer idIntervento) throws RecordNotFoundException, ValidationException {

		String statoIntervento = intervSelvicoulturaleDAO.getStatoInterventoByIdIntervento(idIntervento);

		if (statoIntervento.equals(STATO_PROGRAMMATO)) {

			speciePfaInterventoDAO.deleteAllSpeciePfaInterventoByIdIntervento(idIntervento);
			partforInterventoDAO.deletePartforIntervento(idIntervento);
			intervselEventoDAO.deleteIntervselEvento(idIntervento);
			esboscoIntervselvDAO.deleteIntervselvEsbosco(idIntervento);
			usoviabInterventoselvDAO.deleteIntervselUsovib(idIntervento);
			geoLnLottoInterventoDAO.deleteGeoLnLottoIntervento(idIntervento);
			geoPlLottoInterventoDAO.deleteGeoPlLottoIntervento(idIntervento);
			geoPtLottoInterventoDAO.deleteGeoPtLottoIntervento(idIntervento);
			skOkSelvDAO.deleteByIdIntervento(idIntervento);
			intervSelvicoulturaleDAO.deleteIntervSelvicolturale(idIntervento);

			istanzaForestDAO.deleteById(idIntervento);
			propcatastoInterventoDAO.deleteAllByIdIntervento(idIntervento);
			soggettoInterventoDAO.deleteByIdIntervento(idIntervento);
			interventoRn2000DAO.deleteInterventosByIdIntervento(idIntervento);
			interventoPopSemeDAO.deleteInterventosByIdIntervento(idIntervento);
			interventoCatforDAO.deleteInterventosById(idIntervento);
			interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);

			documentoAllegatoDAO.deleteDocumentoAllegatoByIdIntervento(idIntervento);

			interventoDAO.deleteIntervento(idIntervento);
		} else {
			throw new ValidationException(ErrorConstants.BAD_INPUT_PARAMETERS);
		}

	}
	
	@Transactional
	@Override
	public PlainSecondoSezione getDataLotto(Integer idIntervento)
			throws RecordNotUniqueException, ValidationException {

		logger.info("getDataLotto - idIntervento: " + idIntervento);
		BigDecimal superficieInteresata =  interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata();
		BigDecimal superficie;
		if (superficieInteresata==null) {
			superficie = superficieInteresata;
		} else {
			superficie = null;
		}

		PlainSecondoSezione plainSecondoSezione = new PlainSecondoSezione();
		plainSecondoSezione.setTotaleSuperficieCatastale(superficie);
		plainSecondoSezione.setTotaleSuperficieTrasf(superficie);

		List<RicadenzaInformazioni> ricadenzaNatura2000 = wrapperIstanzaDAO.retrieveRicadenzaNatura2000Sic(idIntervento);
		ricadenzaNatura2000.addAll(wrapperIstanzaDAO.retrieveRicadenzaNatura2000Zps(idIntervento));

		plainSecondoSezione.setRicadenzaAreeProtette(wrapperIstanzaDAO.retrieveRicadenzaAreeProtette(idIntervento));
		plainSecondoSezione.setRicadenzaNatura2000(ricadenzaNatura2000);

		plainSecondoSezione.setRicadenzaPopolamentiDaSeme(wrapperIstanzaDAO.retrieveRicadenzaPopolamentiDaSeme(idIntervento));
		plainSecondoSezione.setRicadenzaCategorieForestali(wrapperIstanzaDAO.retrieveRicadenzaCategorieForestali(idIntervento));

		//TODO SPECIFICO PER PFA
//		IntervTrasformazione intervTrasformazione = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
//
//		if (intervTrasformazione != null) {
//			plainSecondoSezione.setRicadenzaVincoloIdrogeologico(intervTrasformazione.getFlgVincIdro() == 1);
//		}
		
		List<PropCatasto> propCatastos = propCatastoDAO.getPropCatastosByPropcatastoIntervento(
				propcatastoInterventoDAO.getAllPropcatastoByIdIntervento(idIntervento));

		plainSecondoSezione.setParticelleCatastali(wrapperIstanzaDAO.mapPropCatastosToParticelleCatastali(propCatastos));

		if (!propCatastos.isEmpty()) {
			plainSecondoSezione.setAnnotazioni(propCatastos.get(0).getNote());
		}

		return plainSecondoSezione;
	}

	@Override
	public StepNumber getNumberOfNextStep(Integer idIntervento) {
		return new StepNumber(skOkSelvDAO.sumFlgSeziones(idIntervento));
	}

	@Override
	public InterventoDatiTehnici getDatiTechiciForStep2(Integer idIntervento) {
		InterventoDatiTehnici interventoDatiTehnici = new InterventoDatiTehnici();

		TipoInterventoDatiTecnici tipoIntervDatiTechici = tipoInterventoDatiTecniciDAO
				.findTipoIntervDatiTehnici(idIntervento);

		IntervSelvicolturale intervSelvicolturale = intervSelvicoulturaleDAO.findInterventoSelvicolturale(idIntervento);

		interventoDatiTehnici.setTipoIntervento(tipoIntervDatiTechici);
		interventoDatiTehnici.setIntervSelvicolturale(intervSelvicolturale);

		return interventoDatiTehnici;
	}

	@Override
	public InterventoDatiTehnici getDatiTechiciForEditNEW(Integer idIntervento) {
		InterventoDatiTehnici interventoDatiTehnici = new InterventoDatiTehnici();

		TipoInterventoDatiTecnici tipoIntervDatiTechici = tipoInterventoDatiTecniciDAO
				.findTipoIntervDatiTehniciNEW(idIntervento);

		IntervSelvicolturale intervSelvicolturale = intervSelvicoulturaleDAO.findInterventoSelvicolturale(idIntervento);

		List<SpecieInterventoVolumes> specieInterventoVolumes = speciePfaInterventoDAO
				.getSpecieByInterventoId(idIntervento);
		List<SpeciePfaIntervento> speciePfaInterventos = new ArrayList<SpeciePfaIntervento>();
		for (SpecieInterventoVolumes specieInterventoVolume : specieInterventoVolumes) {
			SpeciePfaIntervento spi = new SpeciePfaIntervento();
			spi.setIdSpecie(specieInterventoVolume.getIdSpecie());
			spi.setFlgSpeciePriorita(specieInterventoVolume.getPriorita());
			spi.setVolumeSpecia(specieInterventoVolume.getVolumeSpecie());
			
			speciePfaInterventos.add(spi);
		}

		SpeciePfaIntervento[] specPfa = speciePfaInterventos
				.toArray(new SpeciePfaIntervento[specieInterventoVolumes.size()]);

		interventoDatiTehnici.setTipoIntervento(tipoIntervDatiTechici);
		interventoDatiTehnici.setIntervSelvicolturale(intervSelvicolturale);
		interventoDatiTehnici.setSpeciePfaIntervento(specPfa);

		return interventoDatiTehnici;
	}

	@Transactional
	@Override
	public void updateDatiTechici(Integer idIntervento, InterventoDatiTehnici intervDatiTehnici,
			ConfigUtente loggedConfig) throws RecordNotUniqueException, RecordNotFoundException, DuplicateKeyException {

		if (!skOkSelvDAO.isFlgSez1Done(idIntervento)) {
			throw new ValidationException(SEZIONE_1_VALID_MSG);
		}

		validateTipoIntervento(intervDatiTehnici.getTipoIntervento());
		validateIntervSelvicolturale(intervDatiTehnici.getIntervSelvicolturale());

		// interventoDAO.updateInterventoWithoutDates(intervDatiTehnici.getTipoIntervento(),
		// idIntervento,
		// loggedConfig.getIdConfigUtente());

		intervSelvicoulturaleDAO.updateIntervSelvicolturale(intervDatiTehnici.getIntervSelvicolturale(), idIntervento);
		intervselEventoDAO.updateIntervselEvento(intervDatiTehnici.getTipoIntervento().getIdEventoCorelato(),
				idIntervento);
		updateRipresaInterventoPfa(idIntervento);

		try {
			speciePfaInterventoDAO.deleteAllSpeciePfaInterventoByIdIntervento(idIntervento);
			speciePfaInterventoDAO.batchCreate(intervDatiTehnici.getSpeciePfaIntervento(), idIntervento);
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
		usoviabInterventoselvDAO.updateIntervselUsovib(intervDatiTehnici.getIntervSelvicolturale().getIdUsoViabilita(),
				idIntervento);
		esboscoIntervselvDAO.updateIntervselvEsbosco(intervDatiTehnici.getIntervSelvicolturale().getCodEsbosco(),
				idIntervento);

	}

	@Override
	public List<ShootingMirrorDTO> getDataForShootingMirror(Integer idIntervento) {
		return shootingMirrorDAO.getAllParticlesForShootingMirrorNEW(idIntervento);
	}

	@Override
	public TSoggetto getDataForSavingGiurdicaAtUtilizzatoreStep(String codiceFiscale) throws RecordNotUniqueException {

		TSoggetto sogg = soggettoDAO.findAziendaByCodiceFiscale(codiceFiscale);
		if (sogg != null && ValidationUtil.isCodiceOk(codiceFiscale) && codiceFiscale.length() == 11) {
			return sogg;
			// TODO involve AAEP service search for Persona giurdica
			// AaepServiceHelper aaepService = (AaepServiceHelper)
			// applicationContext.getBean("aaepServiceHelper");
			/*
			 * try { AziendaAAEP aziendaAAEP =
			 * aaepService.getAziendaByCodiceFiscale(codiceFiscale);
			 *  return null; } catch
			 * (ServiceException e) { e.printStackTrace(); // TODO: Remove this line afrer
			 * testing return null; }
			 */
		}

		return null;
	}

	@Override
	public TSoggetto getDataForSavingFisicaAtUtilizzatoreStep(String codiceFiscale) throws RecordNotUniqueException {

		TSoggetto sogg = soggettoDAO.findSoggettoByCodiceFiscale(codiceFiscale);
		if (sogg != null && ValidationUtil.isCodiceOk(codiceFiscale) && codiceFiscale.length() == 16) {
			return sogg;
			// TODO involve AAEP service search for Persona fisica
			// AaepServiceHelper aaepService = (AaepServiceHelper)
			// applicationContext.getBean("aaepServiceHelper");
			/*
			 * try { AziendaAAEP aziendaAAEP =
			 * aaepService.getAziendaByCodiceFiscale(codiceFiscale);
			 *  return null; } catch
			 * (ServiceException e) { e.printStackTrace(); // TODO: Remove this line afrer
			 * testing return null; }
			 */
		}

		return null;
	}

	@Transactional
	@Override
	public List<StepValidationErrors> saveUtilizzatoreETecnico(InterventoUtilizzatore interventoUtilizzatore, ConfigUtente loggedConfig,
			Integer idIntervento) throws RecordNotUniqueException, ValidationException {

//		try { // il soggetto richiedende non si salva piu', sta gia' nella tabella R_PFA_PG
//			createSoggettoTipoSoggeto(loggedConfig.getFkSoggetto(), SoggettoTypeEnum.RICHIEDENTE.getValue());
//			createSoggInterventoNEW(loggedConfig.getFkSoggetto(), idIntervento, loggedConfig, SoggettoTypeEnum.RICHIEDENTE);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException();
//		}

		// if IN PROPRIO is chosen - applicant is also utilizzatore
		if (interventoUtilizzatore.getTipoDiUtilizzatore() == TipoDiUtilizzatoreEnum.IN_PROPRIO.getValue()) {
			try {
				createSoggettoTipoSoggeto(loggedConfig.getFkSoggetto(), SoggettoTypeEnum.UTILIZZATORE.getValue());
				createSoggInterventoWithTipoSoggetto(idIntervento, loggedConfig.getFkSoggetto(),
						SoggettoTypeEnum.UTILIZZATORE, loggedConfig);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}

		// RICERCA
		if (interventoUtilizzatore.getTipoDiUtilizzatore() == TipoDiUtilizzatoreEnum.RICERCA.getValue()) {

			int idSoggetto;

			TSoggetto soggetto = fillSoggettoFromInterventoUtilizzatore(interventoUtilizzatore);

			// check if codice fiscale(soggetto) from form exist in db , if so -> update
			// soggetto, if not -> create new one
			TSoggetto soggettoFromDb = getSoggettoFromInterverntoUtilizzatore(interventoUtilizzatore);
			
			if (soggettoFromDb == null) {

				// check if codice fiscale is in valid format for Italian circumstances
				if (ValidationUtil.isCodiceOk(soggetto.getCodiceFiscale())) {
					idSoggetto = soggettoDAO.createSoggetto(soggetto);
				} else {
					logger.info("codiceFiscale not valid: " + soggetto.getCodiceFiscale());
					throw new ValidationException(ErrorConstants.BAD_INPUT_PARAMETERS);
				}

			} else {
				idSoggetto = soggettoFromDb.getIdSoggetto();
				soggetto.setIdSoggetto(idSoggetto);
				soggettoDAO.updateSoggetto(soggetto);
			}

			try {
				createSoggettoTipoSoggeto(idSoggetto, SoggettoTypeEnum.UTILIZZATORE.getValue());
				createSoggInterventoWithTipoSoggetto(idIntervento, idSoggetto, SoggettoTypeEnum.UTILIZZATORE,
						loggedConfig);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}

		}

		// if there is chosen tecnico forestale, insert it in idf_soggetto_intervento
		if (interventoUtilizzatore.getIdTecnicoForestale() != null) {

			try {
				createSoggettoTipoSoggeto(interventoUtilizzatore.getIdTecnicoForestale(),
						SoggettoTypeEnum.TECNICO_FORESTALE.getValue());
				createSoggInterventoWithTipoSoggetto(idIntervento, interventoUtilizzatore.getIdTecnicoForestale(),
						SoggettoTypeEnum.TECNICO_FORESTALE, loggedConfig);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		skOkSelvDAO.updateFlgSez3(idIntervento);
		return  getValidationPfaUtilizzatoreETecnico(getUtilizzatoreETecnico(idIntervento));

	}
	
	private TSoggetto fillSoggettoFromInterventoUtilizzatore(InterventoUtilizzatore interventoUtilizzatore) {
		TSoggetto soggetto = new TSoggetto();

		soggetto.setCodiceFiscale(interventoUtilizzatore.getCodiceFiscale());
		soggetto.setCognome(interventoUtilizzatore.getCognome());
		soggetto.setNome(interventoUtilizzatore.getNome());
		soggetto.setCap(interventoUtilizzatore.getCap());
		soggetto.setCivico(interventoUtilizzatore.getCivico());
		soggetto.setFkComune(interventoUtilizzatore.getComune().getIdComune());
		soggetto.setIndirizzo(interventoUtilizzatore.getIndirizzo());
		soggetto.setNrTelefonico(interventoUtilizzatore.getNrTelefonico());
		soggetto.seteMail(interventoUtilizzatore.geteMail());
		soggetto.setDataInserimento(LocalDate.now());
		soggetto.setFlgSettoreRegionale((byte) 0);
		// giurdica attributes
		soggetto.setPartitaIva(interventoUtilizzatore.getPartitaIva());
		soggetto.setPec(interventoUtilizzatore.getPec());
		soggetto.setDenominazione(interventoUtilizzatore.getDenominazione());
		return soggetto;
	}

	private void createSoggettoTipoSoggeto(Integer idSoggetto, Integer idTipoSoggetto) {
		if (!checkIfSoggettoExistsInIntermediaryTable(idSoggetto, idTipoSoggetto)) {
			soggettoDAO.insertIntoSoggettoTipoSoggetto(idSoggetto, idTipoSoggetto);
		}
	}

	private boolean checkIfSoggettoExistsInIntermediaryTable(Integer idSoggetto, Integer idTipoSoggetto) {
		return soggettoDAO.checkIfSoggettoExistsInIntermediaryTable(idSoggetto, idTipoSoggetto);
	}

	private void validateSoggetoFisica(InterventoUtilizzatore interventoUtilizzatore) {
		if (interventoUtilizzatore.getCodiceFiscale() == null || interventoUtilizzatore.getCognome() == null
				|| interventoUtilizzatore.getNome() == null || interventoUtilizzatore.getNrTelefonico() == null
				|| interventoUtilizzatore.getComune() == null || interventoUtilizzatore.getIndirizzo() == null
				|| interventoUtilizzatore.getCivico() == null || interventoUtilizzatore.getCap() == null
				|| interventoUtilizzatore.geteMail() == null) {
			throw new ValidationException(ErrorConstants.OGGETTO_OBBLIGATORIO);
		}

		if (interventoUtilizzatore.getNrTelefonico().length() > 20 || interventoUtilizzatore.geteMail().length() > 50) {
			throw new ValidationException(ErrorConstants.STRINGA_DIGITATA_ERROR);
		}

		if (!ValidationUtil.isEMail(interventoUtilizzatore.geteMail())) {
			throw new ValidationException(ErrorConstants.MAIL_NON_VALIDO);
		}

	}

	private void validateSoggetoGiuridica(InterventoUtilizzatore interventoUtilizzatore) {
		if (interventoUtilizzatore.getCodiceFiscale() == null || interventoUtilizzatore.getPartitaIva() == null
				|| interventoUtilizzatore.getDenominazione() == null || interventoUtilizzatore.getComune() == null
				|| interventoUtilizzatore.getCap() == null || interventoUtilizzatore.getIndirizzo() == null
				|| interventoUtilizzatore.getCivico() == null || interventoUtilizzatore.getPec() == null
				|| interventoUtilizzatore.getNrTelefonico() == null || interventoUtilizzatore.geteMail() == null) {
			throw new ValidationException(ErrorConstants.OGGETTO_OBBLIGATORIO);
		}

		if (interventoUtilizzatore.getNrTelefonico().length() > 20 || interventoUtilizzatore.geteMail().length() > 50) {
			throw new ValidationException(ErrorConstants.STRINGA_DIGITATA_ERROR);
		}

		if (!ValidationUtil.isEMail(interventoUtilizzatore.geteMail())) {
			throw new ValidationException(ErrorConstants.MAIL_NON_VALIDO);
		}

	}

	@Override
	public IstanzaRiepilogo getDataForRiepilogo(Integer idIntervento) {

		IstanzaRiepilogo istanzaRiepilogo = new IstanzaRiepilogo();

		istanzaRiepilogo.setComuneResource(comuneDao.findAllComuneByIntervento(idIntervento));
		istanzaRiepilogo.setIstanzaDetails(istanzaForestDAO.getIstanzaDetailsByIdIntervento(idIntervento));
		istanzaRiepilogo.setDescrizioneIntervento(descrizioneInterventoDAO.getDescrIntervForSummaryInstance(idIntervento));
		istanzaRiepilogo.setPropCatastos(propCatastoDAO.getDichPropCatastosByIdIntervento(idIntervento));
		istanzaRiepilogo.setUtilizzatoreDetails(getUtilizzatoreForIntervento(idIntervento));
		istanzaRiepilogo.setRicadenzaInfo(intervSelvicoulturaleDAO.getRicadenzaInfo(idIntervento));

		return istanzaRiepilogo;
	}

	@Override
	public TipoInterventoDettaglio getTipoInterventoDettaglioByIdIntervento(Integer idIntervento) {

		return tipoInterventoDatiTecniciDAO.getTipoInterventoDettaglio(idIntervento);
	}

	@Override
	public ParticelleInterventoDettaglio getParticelleInterventoDettaglioByIdIntervento(Integer idIntervento) 
			throws ServiceException {

		ParticelleInterventoDettaglio particelleInterventoDettaglio = new ParticelleInterventoDettaglio();

		particelleInterventoDettaglio.setPropCatastos(propCatastoDAO.getDichPropCatastosByIdIntervento(idIntervento));
		
		
		PlainSecondoSezione ricadenze = getRicadenzePfa(idIntervento);
		
		particelleInterventoDettaglio.setRicadenzaAreeProtette(ricadenze.getRicadenzaAreeProtette());
		particelleInterventoDettaglio.setRicadenzaCategorieForestali(ricadenze.getRicadenzaCategorieForestali());
		particelleInterventoDettaglio.setRicadenzaNatura2000(ricadenze.getRicadenzaNatura2000());
		particelleInterventoDettaglio.setRicadenzaPopolamentiDaSeme(ricadenze.getRicadenzaPopolamentiDaSeme());

		return particelleInterventoDettaglio;
	}

	@Override
	public SpecieInterventoDetails getSpecieInterventoDettaglioByIdIntervento(Integer idIntervento) {
		SpecieInterventoDetails specieInterventoDetails = new SpecieInterventoDetails();

		specieInterventoDetails.setSpecies(speciePfaInterventoDAO.getSpecieByInterventoId(idIntervento));
		specieInterventoDetails
				.setDettagliDitaglio(intervSelvicoulturaleDAO.getDettaglioDiTaglioByInterventoId(idIntervento));

		return specieInterventoDetails;
	}

	@Override
	public UtilizzatoreDetails getUtilizzatoreForIntervento(Integer idIntervento) {

		UtilizzatoreDetails utilizzatoreDetails = new UtilizzatoreDetails();
		SoggettoResource  richiedente = new SoggettoResource();
		richiedente.setDenominazione(soggettoDAO.getRichiedentiInterventoPfa(idIntervento));
		utilizzatoreDetails.setRichiedente(richiedente);
		utilizzatoreDetails.setUtilizzatore(soggettoDAO.getSoggettoByIdInterventoAndTipoSoggetto(idIntervento,
				SoggettoTypeEnum.UTILIZZATORE.getValue()));
		utilizzatoreDetails.setTecnicoForestale(soggettoDAO.getSoggettoByIdInterventoAndTipoSoggetto(idIntervento,
				SoggettoTypeEnum.TECNICO_FORESTALE.getValue()));

		return utilizzatoreDetails;

	}

	@Transactional
	@Override
	public void chiudiIntervento(Integer idIntervento, InterventoRiepilogo interventoRiepilogo,
			ConfigUtente loggedConfig) throws ValidationException {	

		if (interventoRiepilogo.getChiusuraInformazioni().getDataInizio() == null
				|| interventoRiepilogo.getChiusuraInformazioni().getDataFine() == null) {
			throw new ValidationException(ErrorConstants.CAMPO_OBBLIGATORIO);
		}
	
		soggettoInterventoDAO.updateSoggetoIntervento(idIntervento, SoggettoTypeEnum.UTILIZZATORE.getValue(),
				interventoRiepilogo.getUtilizzatore().getIdSoggetto());
		 partforInterventoDAO.updatePartforInterventoWithChiusuraDates(idIntervento,
		 interventoRiepilogo);
		intervSelvicoulturaleDAO.updateIntervSelvicolturaleWithChiusuraData(interventoRiepilogo, idIntervento,
				loggedConfig);
		interventoDAO.updateInterventoWithChiusuraData(interventoRiepilogo, idIntervento);

		if (interventoRiepilogo.getShootingMirrorInformazioni() != null) {
			for (ShootingMirrorDTO mirror : interventoRiepilogo.getShootingMirrorInformazioni()) {
				partforInterventoDAO.updatePartforInterventoAtCompleta(idIntervento,
						mirror.getIdgeoPlParticellaForest(), mirror.getRipresaAttuale());
			}
		}
		intervSelvicoulturaleDAO.cambiamentoStato(idIntervento, STATO_CONCLUSO);
	}

	@Transactional
	@Override
	public void salvaIntervento(Integer idIntervento, InterventoRiepilogo interventoRiepilogo,
			ConfigUtente loggedConfig) {

		if (interventoRiepilogo.getChiusuraInformazioni().getDataInizio() == null
				|| interventoRiepilogo.getChiusuraInformazioni().getDataFine() == null) {
			throw new ValidationException(ErrorConstants.CAMPO_OBBLIGATORIO);
		}

		soggettoInterventoDAO.updateSoggetoIntervento(idIntervento, SoggettoTypeEnum.UTILIZZATORE.getValue(),
				interventoRiepilogo.getUtilizzatore().getIdSoggetto());
		intervSelvicoulturaleDAO.updateIntervSelvicolturaleWithChiusuraData(interventoRiepilogo, idIntervento,
				loggedConfig);
		 partforInterventoDAO.updatePartforInterventoWithChiusuraDates(idIntervento,
				 interventoRiepilogo);
		interventoDAO.updateInterventoWithChiusuraData(interventoRiepilogo, idIntervento);

		if (interventoRiepilogo.getShootingMirrorInformazioni() != null) {
			for (ShootingMirrorDTO mirror : interventoRiepilogo.getShootingMirrorInformazioni()) {

				partforInterventoDAO.updatePartforInterventoAtCompleta(idIntervento,
						mirror.getIdgeoPlParticellaForest(), mirror.getRipresaAttuale());
			}

		}
		intervSelvicoulturaleDAO.cambiamentoStato(idIntervento, STATO_ESEGUITO);

	}

	@Override
	public InterventoRiepilogo getDataForChiusura(Integer idIntervento) {

		InterventoRiepilogo interventoRiepilogo = new InterventoRiepilogo();

		interventoRiepilogo
				.setChiusuraInformazioni(partforInterventoDAO.getChiusuraInformazioniByIdIntervento(idIntervento));
		interventoRiepilogo.setUtilizzatore(soggettoDAO.getSoggettoByIdInterventoAndTipoSoggetto(idIntervento,
				SoggettoTypeEnum.UTILIZZATORE.getValue()));
		interventoRiepilogo
				.setShootingMirrorInformazioni(shootingMirrorDAO.getAllParticlesForShootingMirrorNEW(idIntervento));

		return interventoRiepilogo;

	}

	@Override
	public PropCatasto retreiveDataForParticle(Integer idGeoPlPfa, Integer comune, String sezione, Integer foglio,
			String particella) {

		return propCatastoDAO.findPropCatastoByPlainParticelle(idGeoPlPfa, comune, sezione, foglio, particella);
	}

	@Override
	public void saveLocalizzaLottoNEW(Integer idIntervento, PfaLottoLocalizza body, ConfigUtente loggedConfig,
			Integer idGeoPlPfa) throws DuplicateKeyException {
		
		createPartForIntervento(idIntervento, body, idGeoPlPfa);
		
		Double superficieInteressata = geoPlLottoInterventoDAO.getAreaTrasformazioneByFkIntervento(idIntervento);
		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, new BigDecimal(superficieInteressata));
		updateRipresaInterventoPfa(idIntervento);
		saveRicadenzeAreeProtette(body.getRicadenzaAreeProtette(), idIntervento);
		saveRicadenzeNature2000(body.getRicadenzaNatura2000(), idIntervento);
		saveRicadenzePopolamentiSeme(body.getRicadenzaPopolamentiDaSeme(), idIntervento);
		saveRicadenzeCategorieForestale(body.getRicadenzaCategorieForestali(), idIntervento,
				loggedConfig.getIdConfigUtente());
		saveRicadenzaVincoloIdrogeologico(body.isRicadenzaVincoloIdrogeologico(), idIntervento);

		skOkSelvDAO.insertFlgSez1(idIntervento);
	}
	
	private void createPartForIntervento(Integer idIntervento, PfaLottoLocalizza body, Integer idGeoPlPfa) {
		partforInterventoDAO.deletePartforIntervento(idIntervento);
		List<GeoPlParticellaForest> forestParticelle = geoPlParticellaForestDAO.getForestParticlesByGeomIntervento(idIntervento);
		if(forestParticelle.size() == 0) {
			GeoPlParticellaForest itemFuori = new GeoPlParticellaForest();
			itemFuori.setIdgeoPlParticellaForest(0);
			forestParticelle.add(itemFuori);
		}
		Integer stimaMassaRetraibile = intervSelvicoulturaleDAO.getStimaMassaByIntervento(idIntervento);
		for (GeoPlParticellaForest geoPlParticellaForest : forestParticelle) {
			partforInterventoDAO.createParforInterv(geoPlParticellaForest.getIdgeoPlParticellaForest(), idIntervento);

			BigDecimal shareInCut = BigDecimal.valueOf(1 / (double) forestParticelle.size());
			BigDecimal ripresaIntervento;
			if(stimaMassaRetraibile !=null){
				ripresaIntervento = BigDecimal.valueOf(stimaMassaRetraibile).multiply(shareInCut);
			} else {
				ripresaIntervento = BigDecimal.valueOf(0).multiply(shareInCut);
			}

			partforInterventoDAO.updatePartforInterventoWithRipresaIntervento(idIntervento,
					geoPlParticellaForest.getIdgeoPlParticellaForest(), ripresaIntervento);

		}
	}
	
	@Transactional
	@Override
	public void updateLocalizzaLotto(PfaLottoLocalizza body, ConfigUtente loggedConfig, Integer idIntervento,
			Integer idGeoPlPfa) {
		
		createPartForIntervento(idIntervento, body, idGeoPlPfa);
		
		Double superficieInteressata = geoPlLottoInterventoDAO.getAreaTrasformazioneByFkIntervento(idIntervento);
		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, new BigDecimal(superficieInteressata));
		updateRipresaInterventoPfa(idIntervento);
		saveRicadenzeAreeProtette(body.getRicadenzaAreeProtette(), idIntervento);
		saveRicadenzeNature2000(body.getRicadenzaNatura2000(), idIntervento);
		saveRicadenzePopolamentiSeme(body.getRicadenzaPopolamentiDaSeme(), idIntervento);
		saveRicadenzeCategorieForestale(body.getRicadenzaCategorieForestali(), idIntervento,
				loggedConfig.getIdConfigUtente());
//		saveRicadenzaVincoloIdrogeologico(body.isRicadenzaVincoloIdrogeologico(), idIntervento);

		if(!skOkSelvDAO.isFlgSez1Done(idIntervento)) {
			skOkSelvDAO.insertFlgSez1(idIntervento);
		}

	}

	private void createSoggInterventoNEW(Integer idSoggetto, int idIntervento, 
			ConfigUtente loggedConfig, SoggettoTypeEnum tipoSoggetto) {
		SoggettoIntervento soggettoIntervento = new SoggettoIntervento();

		soggettoIntervento.setIdIntervento(idIntervento);
		soggettoIntervento.setIdSoggetto(idSoggetto);
		soggettoIntervento.setIdTipoSoggetto(tipoSoggetto.getValue());
		soggettoIntervento.setFkConfigUtente(loggedConfig.getIdConfigUtente());
		soggettoIntervento.setDataInizioValidita(LocalDate.now());

		soggettoInterventoDAO.createSoggettoIntervento(soggettoIntervento);

	}

	private void saveRicadenzaVincoloIdrogeologico(boolean isVincoloIdrogeologico, int idIntervento) {
		if (isVincoloIdrogeologico) {
			//TODO ... QUESTO VALE SOLO PER ISTAFOR
			//intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.YES.getValue(), idIntervento);
		} else {
			//intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.NO.getValue(), idIntervento);
		}
	}

	private void saveRicadenzeCategorieForestale(List<RicadenzaInformazioni> ricadenze, int idIntervento,
			int idConfigUtente) {
		interventoCatforDAO.deleteInterventosById(idIntervento);
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoCatfor interventoCatfor = new InterventoCatfor();
			interventoCatfor.setIdIntervento(idIntervento);
			interventoCatfor.setIdCategoriaForestale(Integer.parseInt(ricadenza.getCodiceAmministrativo()));
			interventoCatfor.setFkConfigUtente(idConfigUtente);
			interventoCatforDAO.insertInterventoCatfor(interventoCatfor);
		}
	}

	private void saveRicadenzePopolamentiSeme(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		interventoPopSemeDAO.deleteInterventosByIdIntervento(idIntervento);
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoPopSeme interventoPopSeme = new InterventoPopSeme();
			interventoPopSeme.setIdIntervento(idIntervento);
			interventoPopSeme.setIdPopolamentoSeme(popolamentoSemeDAO
					.getByCodiceAmministrativo(ricadenza.getCodiceAmministrativo()).getIdPopolamentoSeme());
			interventoPopSemeDAO.insertInterventoPopSeme(interventoPopSeme);
		}
	}

	private void saveRicadenzeNature2000(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		interventoRn2000DAO.deleteInterventosByIdIntervento(idIntervento);
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoRn2000 interventoRn2000 = new InterventoRn2000();
			interventoRn2000.setIdIntervento(idIntervento);
			interventoRn2000.setCodiceRn2000(ricadenza.getCodiceAmministrativo());
			interventoRn2000DAO.insertInterventoRn2000(interventoRn2000);
		}
	}

	private void saveRicadenzeAreeProtette(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoAapp interventoAapp = new InterventoAapp();
			interventoAapp.setIdIntervento(idIntervento);
			interventoAapp.setCodiceAapp(ricadenza.getCodiceAmministrativo());
			interventoAappDAO.insertInterventoAapp(interventoAapp);
		}
	}

	private GeoLnLottoIntervento retrieveGeoLnLottoInterventoForInsertNEW(int idIntervento) {
		GeoLnLottoIntervento geoLnLottoIntervento = new GeoLnLottoIntervento();
		geoLnLottoIntervento.setDatainserimento(LocalDate.now());
		geoLnLottoIntervento.setIdIntervento(idIntervento);
		return geoLnLottoIntervento;
	}

	private GeoPtLottoIntervento retrieveGeoPtLottoInterventoForInsertNEW(int idIntervento) {
		GeoPtLottoIntervento geoPtLottoIntervento = new GeoPtLottoIntervento();
		geoPtLottoIntervento.setDatainserimento(LocalDate.now());
		geoPtLottoIntervento.setIdIntervento(idIntervento);
		return geoPtLottoIntervento;
	}

	private GeoPlLottoIntervento retrieveGeoPlLottoInterventoForInsertNEW(int idIntervento) {
		GeoPlLottoIntervento geoPlLottoIntervento = new GeoPlLottoIntervento();
		geoPlLottoIntervento.setDatainserimento(LocalDate.now());
		geoPlLottoIntervento.setFkIntervento(idIntervento);

		return geoPlLottoIntervento;
	}

	private IstanzaForest getIstanzaForest(Integer fkConfigUtente, Integer idTipoIstanza, Integer idIntervento) {

		IstanzaForest istanzaForest = new IstanzaForest();

		istanzaForest.setIdIstanzaIntervento(idIntervento);
		istanzaForest.setFkTipoIstanza(idTipoIstanza);
		istanzaForest.setFkStatoIstanza(InstanceStateEnum.BOZZA.getStateValue());
		istanzaForest.setDataInserimento(LocalDate.now());
		istanzaForest.setNrIstanzaForestale(istanzaForestDAO.getNumberOfInstanceTagli() + 1); ///777 line comment
		//istanzaForestDAO.getNumberOfInstanceType(idTipoIstanza) + 1 
		istanzaForest.setFkConfigUtente(fkConfigUtente);

		return istanzaForest;

	}

	private void createSoggInterventoWithTipoSoggetto(Integer idIntervento, Integer idSoggetto,
			SoggettoTypeEnum soggettoType, ConfigUtente loggedConfig) {
		SoggettoIntervento soggettoIntervento = new SoggettoIntervento();

		soggettoIntervento.setIdIntervento(idIntervento);
		soggettoIntervento.setIdSoggetto(idSoggetto);
		soggettoIntervento.setIdTipoSoggetto(soggettoType.getValue());
		soggettoIntervento.setFkConfigUtente(loggedConfig.getIdConfigUtente());
		soggettoIntervento.setDataInizioValidita(LocalDate.now());

		soggettoInterventoDAO.createSoggettoIntervento(soggettoIntervento);

	}

	@Override
	public InterventoUtilizzatore getUtilizzatoreETecnico(Integer idIntervento) throws RecordNotUniqueException {

		SoggettoResource soggettoUtilizzatore = soggettoDAO.getSoggettoByIdInterventoAndTipoSoggetto(idIntervento,
				SoggettoTypeEnum.UTILIZZATORE.getValue());

		SoggettoResource soggettoRichiedente = soggettoDAO.getSoggettoByIdInterventoAndTipoSoggetto(idIntervento,
				SoggettoTypeEnum.RICHIEDENTE.getValue());

		InterventoUtilizzatore interventoUtilizzatore = new InterventoUtilizzatore();

		SoggettoResource soggettoTecnico = soggettoDAO.getSoggettoByIdInterventoAndTipoSoggetto(idIntervento,
				SoggettoTypeEnum.TECNICO_FORESTALE.getValue());
		if (soggettoTecnico != null) {
			interventoUtilizzatore.setIdTecnicoForestale(soggettoTecnico.getIdSoggetto());
		}

		logger.info("soggettoUtilizzatore:" + (soggettoUtilizzatore==null?"null":soggettoUtilizzatore.getIdSoggetto()));
		logger.info("soggettoRichiedente:" + (soggettoRichiedente==null?"null":soggettoRichiedente.getIdSoggetto()));
		if (soggettoUtilizzatore != null && 
				(soggettoRichiedente ==  null || soggettoUtilizzatore.getIdSoggetto() != soggettoRichiedente.getIdSoggetto())) {
			TSoggetto soggetto = soggettoDAO.findSoggettoById(soggettoUtilizzatore.getIdSoggetto());

			ComuneResource comune = comuneDao.findComuneResourceByID(soggetto.getFkComune());

			interventoUtilizzatore.setNome(soggetto.getNome());
			interventoUtilizzatore.setCognome(soggetto.getCognome());
			interventoUtilizzatore.setCodiceFiscale(soggetto.getCodiceFiscale());
			interventoUtilizzatore.setComune(comune);
			interventoUtilizzatore.setCivico(soggetto.getCivico());
			interventoUtilizzatore.setCap(soggetto.getCap());
			interventoUtilizzatore.setNrTelefonico(soggetto.getNrTelefonico());
			interventoUtilizzatore.seteMail(soggetto.geteMail());
			interventoUtilizzatore.setDenominazione(soggetto.getDenominazione());
			interventoUtilizzatore.setPartitaIva(soggetto.getPartitaIva());
			interventoUtilizzatore.setPec(soggetto.getPec());
			interventoUtilizzatore.setIndirizzo(soggetto.getIndirizzo());
			interventoUtilizzatore.setTipoDiUtilizzatore(TipoDiUtilizzatoreEnum.RICERCA.getValue());
			if (checkIfSoggettoIsPersonaGiuridica(interventoUtilizzatore)) {
				interventoUtilizzatore
						.setTipoUtilizzatoreRicerca(TipoUtilizzatoreRicercaEnum.PERSONA_GIURIDICA.getValue());
			} else {
				interventoUtilizzatore
						.setTipoUtilizzatoreRicerca(TipoUtilizzatoreRicercaEnum.PERSONA_FISICA.getValue());
			}
		} else if(soggettoUtilizzatore == null && (soggettoUtilizzatore != null || soggettoTecnico != null)) {
			interventoUtilizzatore.setTipoDiUtilizzatore(TipoDiUtilizzatoreEnum.DA_INDIVIDUARE.getValue());
		} else {
			interventoUtilizzatore.setTipoDiUtilizzatore(TipoDiUtilizzatoreEnum.IN_PROPRIO.getValue());

		}

		return interventoUtilizzatore;
	}
	
	private List<StepValidationErrors> getValidationPfaUtilizzatoreETecnico(InterventoUtilizzatore interventoUtilizzatore) {
		AllStepsValidator allStepsValidator = new AllStepsValidator();
		allStepsValidator.addStepValidator(new PfaUtilizzatoreETecnicoValidator(interventoUtilizzatore));
		allStepsValidator.validateAllSteps();
		return allStepsValidator.getStepValidatorErrors();
	}

	public boolean checkIfSoggettoIsPersonaGiuridica(InterventoUtilizzatore interventoUtilizzatore) {
		return interventoUtilizzatore.getDenominazione() != null && interventoUtilizzatore.getDenominazione().length() > 0;
	}

	@Transactional
	@Override
	public List<StepValidationErrors> updateUtilizzatoreETecnico(InterventoUtilizzatore interventoUtilizzatore, ConfigUtente loggedConfig,
			Integer idIntervento) throws RecordNotUniqueException {

		soggettoInterventoDAO.updateSoggetoIntervento(idIntervento, SoggettoTypeEnum.RICHIEDENTE.getValue(),
				loggedConfig.getFkSoggetto());

		// RICERCA
		if (interventoUtilizzatore.getTipoDiUtilizzatore() == TipoDiUtilizzatoreEnum.RICERCA.getValue()) {

			int idSoggetto;

			TSoggetto soggetto = fillSoggettoFromInterventoUtilizzatore(interventoUtilizzatore);

			// check if codice fiscale(soggetto) from form exist in db , if so -> update
			// soggetto, if not -> create new one
			TSoggetto soggettoFromDb = getSoggettoFromInterverntoUtilizzatore(interventoUtilizzatore);
			
			if (soggettoFromDb == null) {

				if (ValidationUtil.isCodiceOk(soggetto.getCodiceFiscale())) {
					idSoggetto = soggettoDAO.createSoggetto(soggetto);

				} else {
					throw new ValidationException(ErrorConstants.BAD_INPUT_PARAMETERS);
				}

			} else {
				idSoggetto = soggettoFromDb.getIdSoggetto();
				soggetto.setIdSoggetto(idSoggetto);
				soggettoDAO.updateSoggetto(soggetto);
			}

			if (soggettoDAO.getSoggettoByIdInterventoAndTipoSoggetto(idIntervento,
					SoggettoTypeEnum.UTILIZZATORE.getValue()) == null) {
				
				createSoggettoTipoSoggeto(idSoggetto, SoggettoTypeEnum.UTILIZZATORE.getValue());
				createSoggInterventoWithTipoSoggetto(idIntervento, idSoggetto, SoggettoTypeEnum.UTILIZZATORE,
						loggedConfig);
			} else {
				createSoggettoTipoSoggeto(idSoggetto, SoggettoTypeEnum.UTILIZZATORE.getValue());
			}
			soggettoInterventoDAO.updateSoggetoIntervento(idIntervento, SoggettoTypeEnum.UTILIZZATORE.getValue(),
					idSoggetto);
			
			//in proprio
		} else if (interventoUtilizzatore.getTipoDiUtilizzatore() == TipoDiUtilizzatoreEnum.IN_PROPRIO.getValue()) {
			if(soggettoInterventoDAO.updateSoggetoIntervento(idIntervento, SoggettoTypeEnum.UTILIZZATORE.getValue(),
					loggedConfig.getFkSoggetto()) < 1) {				
				createSoggInterventoNEW(loggedConfig.getFkSoggetto(), idIntervento, loggedConfig, SoggettoTypeEnum.UTILIZZATORE);
			}
			// da individuare
		} else if (interventoUtilizzatore.getTipoDiUtilizzatore() == TipoDiUtilizzatoreEnum.DA_INDIVIDUARE.getValue()) {
			soggettoInterventoDAO.deleteUtilizzatoreByIdIntervento(idIntervento);
		}

		// if there is chosen tecnico forestale, insert/update it in
		// idf_soggetto_intervento
		if (interventoUtilizzatore.getIdTecnicoForestale() != null) {

			// if there was record in database already - do insert, otherwise - do update
			if (soggettoDAO.getSoggettoByIdInterventoAndTipoSoggetto(idIntervento,
					SoggettoTypeEnum.TECNICO_FORESTALE.getValue()) == null) {
				createSoggInterventoWithTipoSoggetto(idIntervento, interventoUtilizzatore.getIdTecnicoForestale(),
						SoggettoTypeEnum.TECNICO_FORESTALE, loggedConfig);
			} else {
				soggettoInterventoDAO.updateSoggetoIntervento(idIntervento,
						SoggettoTypeEnum.TECNICO_FORESTALE.getValue(), interventoUtilizzatore.getIdTecnicoForestale());
			}
		}
		return  getValidationPfaUtilizzatoreETecnico(getUtilizzatoreETecnico(idIntervento));
	}
	
	private TSoggetto getSoggettoFromInterverntoUtilizzatore(InterventoUtilizzatore interventoUtilizzatore) 
			throws RecordNotUniqueException {
		TSoggetto soggettoFromDb = null;
		if(interventoUtilizzatore.getCodiceFiscale() !=null && interventoUtilizzatore.getDenominazione() != null
				&& interventoUtilizzatore.getDenominazione().length() > 0) {
			soggettoFromDb = soggettoDAO.findAziendaByCodiceFiscale(interventoUtilizzatore.getCodiceFiscale());
			
		}else if(interventoUtilizzatore.getCodiceFiscale() !=null && interventoUtilizzatore.getNome() != null
				&& interventoUtilizzatore.getNome().length() > 0 && interventoUtilizzatore.getCognome() != null
				&& interventoUtilizzatore.getCognome().length() > 0) {
			soggettoFromDb = soggettoDAO.findSoggettoByCodiceFiscale(interventoUtilizzatore.getCodiceFiscale());
		}else {
			throw new ValidationException("Campi utilizzatore non compilati");
		}
		return soggettoFromDb;
	}

	@Override
	public void trasmettiAPrimpa(Integer idIntervento) throws Exception {
		
		InterventoDatiTehnici intervDatiTehnici = getDatiTechiciForEditNEW(idIntervento);
		validateTipoIntervento(intervDatiTehnici.getTipoIntervento());
		validateIntervSelvicolturale(intervDatiTehnici.getIntervSelvicolturale());
		
		InterventoUtilizzatore interventoUtilizzatore = getUtilizzatoreETecnico(idIntervento);
		if (interventoUtilizzatore.getTipoUtilizzatoreRicerca() == TipoUtilizzatoreRicercaEnum.PERSONA_FISICA.getValue()) {
			validateSoggetoFisica(interventoUtilizzatore);
		}
		if (interventoUtilizzatore.getTipoUtilizzatoreRicerca() == TipoUtilizzatoreRicercaEnum.PERSONA_GIURIDICA.getValue()) {
			validateSoggetoGiuridica(interventoUtilizzatore);
		}

		intervSelvicoulturaleDAO.cambiamentoStato(idIntervento, STATO_IN_CORSO_DI_TRASMISSIONE);

	}

	@Override
	public List<PlainParticelleCatastali> getCadastralParticlesForEdit(Integer idIntervento) {

		return propCatastoDAO.getPlainParticelleByIdIntervento(idIntervento);

	}
	
	@Override
	public List<PlainParticelleCatastali> getLocalizzaLottoFromGeeco(Integer idIntervento, ConfigUtente loggedConfig) 
			throws ServiceException {
		List<PlainParticelleCatastali> particelleCatastali = propCatastoDAO.getPlainParticelleByIdIntervento(idIntervento);
		//delete old
		for (PlainParticelleCatastali particella : particelleCatastali) {
			propcatastoInterventoDAO.deletePropcatastoIntervento(particella.getId(), idIntervento);
		}
		particelleCatastali = propcatastoInterventoDAO.getPfaAllPropcatastoByGeomGeeco(idIntervento);
		if(particelleCatastali != null && particelleCatastali.size() > 0) {
			for(PlainParticelleCatastali particella : particelleCatastali) {
				propcatastoInterventoDAO.insertParticelleInPropcatastoIntervento(idIntervento, particella, loggedConfig);
			}
		}
		geoPlLottoInterventoDAO.updateSupTagliataByIntervento(idIntervento);
		partforInterventoDAO.updatePartforInterventoAfterEditing(idIntervento);
		
		Double superficieInteressata = geoPlLottoInterventoDAO.getAreaTrasformazioneByFkIntervento(idIntervento);
		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, new BigDecimal(superficieInteressata));
		updateRipresaInterventoPfa(idIntervento);
		
		return propCatastoDAO.getPlainParticelleByIdIntervento(idIntervento);
	}

	@Transactional
	@Override
	public void sendParticlesToGeeco(Integer idIntervento, List<PlainParticelleCatastali> particelleCatastali,
			ConfigUtente loggedConfig) {
		List<PlainParticelleCatastali> list = propCatastoDAO.getPlainParticelleByIdIntervento(idIntervento);

		if (!list.isEmpty() && !particelleCatastali.equals(list)) {

			geoPlLottoInterventoDAO.deleteGeoPlLottoIntervento(idIntervento);
			interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);
			interventoRn2000DAO.deleteInterventosByIdIntervento(idIntervento);
			interventoPopSemeDAO.deleteInterventosByIdIntervento(idIntervento);
			interventoCatforDAO.deleteInterventosById(idIntervento);
			partforInterventoDAO.deletePartforIntervento(idIntervento);

			for (PlainParticelleCatastali particella : particelleCatastali) {
				propcatastoInterventoDAO.deletePropcatastoIntervento(particella.getId(), idIntervento);
			}

			for (PlainParticelleCatastali particella : particelleCatastali) {
				geeco.insertSupInterventoValue(idIntervento, particella, loggedConfig);
			}

		}

	}

	@Override
	public List<PlainParticelleCatastali> retreiveDataFromGeeco(Integer idIntervento) {

		return propCatastoDAO.getPlainParticelleByIdIntervento(idIntervento);
	}

	@Override
	public void insertParticlesInPropcatastoIntervento(Integer idIntervento, PlainParticelleCatastali particella,
			ConfigUtente loggedConfig) throws DuplicateKeyException {

		propcatastoInterventoDAO.insertParticelleInPropcatastoIntervento(idIntervento, particella, loggedConfig);
	}
	
	@Override
	public PlainSecondoSezione getRicadenzePfa(Integer idIntervento) throws ServiceException {
		logger.info("returnRicadenzePfa - idIntervento: " + idIntervento);
		List<LottoIntervento> listGeomGML = geoPlLottoInterventoDAO.getGeometrieGmlByFkIntervento(idIntervento);
		logger.info("returnRicadenzePfa - listGeomGML size: " + (listGeomGML == null?"null": listGeomGML.size()));
		PlainSecondoSezione secondSezione = new PlainSecondoSezione();
		
		List<RicadenzaInformazioni> listRicadenzeNatura2000 = new ArrayList<RicadenzaInformazioni>();
		List<RicadenzaInformazioni> listRicadenzeAreeProtete = new ArrayList<RicadenzaInformazioni>();
		List<RicadenzaInformazioni> items;
		
		Map<String,RicadenzaInformazioni> mapNatura2000 = new HashMap<String,RicadenzaInformazioni>();
		Map<String,RicadenzaInformazioni> mapAreeProtete = new HashMap<String,RicadenzaInformazioni>();
		
		secondSezione.setRicadenzaNatura2000(listRicadenzeNatura2000);
		secondSezione.setRicadenzaAreeProtette(listRicadenzeAreeProtete);
		for(LottoIntervento lotto : listGeomGML) {

			String geomGML = lotto.getGeometriaGml();
			//String geomGML = "{\"type\":\"Polygon\",\"coordinates\":[[[7.2780405,45.3270061],[7.2788183,45.3257038],[7.2788183,45.3257038],[7.279913,45.3238565],[7.2802614,45.3232019],[7.2802614,45.3232019],[7.2807551,45.3222221],[7.2807551,45.3222221],[7.28091980000001,45.3222063],[7.2815144,45.3221493],[7.2815164,45.3221598],[7.2818063,45.3225016],[7.2817727,45.3230121],[7.281771,45.323374],[7.28169929999999,45.323618],[7.2816951,45.3236551],[7.2816401,45.3240399],[7.2815163,45.3249375],[7.2815486,45.325477],[7.2815708,45.3258797],[7.2816306,45.3265292],[7.2816289,45.3266296],[7.28159550000001,45.3268035],[7.28150150000001,45.3274172],[7.281404,45.3276966],[7.28123950000001,45.327949],[7.2809525,45.3284149],[7.2808373,45.3285662],[7.28054409999999,45.3289386],[7.27920910000001,45.3282434],[7.27833110000001,45.3271879],[7.2780405,45.3270061]]]}";
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




			String areeProtetteListStr = parkApiServiceHelper.getRicadenzaAreeProtette(geomGML);
			logger.info("getRicadenzaAreeProtette >" + areeProtetteListStr + "<");
			if(areeProtetteListStr.length() > 0) {
				items = PlainSezioniApiServiceImpl.fillListRicadenzeFromString(areeProtetteListStr);
				setRicadenza(mapAreeProtete, items, listRicadenzeAreeProtete, lotto);
			}
			String natura2000ListStr = parkApiServiceHelper.getRicadenzaNatura2000(geomGML);
			logger.info("getRicadenzaNatura2000 >" + natura2000ListStr + "<");
			if(natura2000ListStr.length() > 0) {
				items = PlainSezioniApiServiceImpl.fillListRicadenzeFromString(natura2000ListStr);
				setRicadenza(mapNatura2000, items, listRicadenzeNatura2000, lotto);
			}
		}
		
		secondSezione.setRicadenzaPopolamentiDaSeme(ricadenzeDAO.getRicadenzePopolamentiSeme(idIntervento,ProceduraEnum.PIANI_FORESTALI_AZIENDALI));
		secondSezione.setRicadenzaCategorieForestali(ricadenzeDAO.getRicadenzeCategForestaliPfa(idIntervento));
		secondSezione.setRicadenzaPortaSeme(ricadenzeDAO.getRicadenzePortaSeme(idIntervento, ProceduraEnum.PIANI_FORESTALI_AZIENDALI));
		secondSezione.setRicadenzaIntervento(ricadenzeDAO.getRicadenzeIntervento(idIntervento));
		IntervPfaFat interventoSelv = intervPfaDAO.findInterventoSelvicolturaleTagli(idIntervento);
		Intervento interv = interventoDAO.findInterventoByIdIntervento(idIntervento);
		secondSezione.setLocalita(interv.getLocalita() != null ? interv.getLocalita() : null);
		secondSezione.setFkFasciaAltimetrica(interventoSelv.getFkFasciaAltimetrica() != null ? interventoSelv.getFkFasciaAltimetrica() : null);

		return secondSezione;
	}
	
	private void setRicadenza(Map<String,RicadenzaInformazioni> map, List<RicadenzaInformazioni> items, 
			List<RicadenzaInformazioni> list, LottoIntervento lotto) {
		for(RicadenzaInformazioni item : items) {
			setPercentualeRicadenza(item);
			BigDecimal percent = new BigDecimal((lotto.getSuperficeLottoHa() * item.getPercentualeRicadenza())/lotto.getTotSuperficeIntervento());
			if(map.get(item.getNome()) != null){
				item = map.get(item.getNome());
				item.setPercentualeRicadenza(item.getPercentualeRicadenza() + percent.floatValue());
			}else {
				item.setPercentualeRicadenza(percent.floatValue());
				list.add(item);
				map.put(item.getNome(),item);
			}
		}
	}
	
	private void setPercentualeRicadenza(RicadenzaInformazioni item) {
		String denom = item.getNome();
		int idx = denom.indexOf("%");
		if(idx > -1) {
			idx = denom.lastIndexOf(" ");
			String percent = denom.substring(idx, denom.length() - 1);
			denom = denom.substring(0,idx);
			item.setNome(denom);
			item.setPercentualeRicadenza(Float.parseFloat(percent));
		}
	}

	@Override
	public List<DrawedGeometryInfo> getDrawedGeometryPfaList(Integer idIntervento) {
		return interventoDAO.getDrawedGeometryPfaList(idIntervento);
	}

	@Override
	public void updateRipresaInterventoPfa(Integer idIntervento) {
		Double areaInterventoHa = geoPlLottoInterventoDAO.getAreaTrasformazioneByFkIntervento(idIntervento);
		partforInterventoDAO.updateRipresaInterventoPfa(idIntervento, areaInterventoHa);
	}

}
