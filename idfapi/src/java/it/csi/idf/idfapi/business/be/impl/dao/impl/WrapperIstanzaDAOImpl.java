/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import it.csi.idf.idfapi.business.be.impl.dao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

import it.csi.idf.idfapi.business.be.exceptions.DuplicateRecordException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.service.GSAREPORT;
import it.csi.idf.idfapi.business.be.service.MailService;
import it.csi.idf.idfapi.business.be.service.RicadenzaService;
import it.csi.idf.idfapi.business.be.service.SIGMATER;
import it.csi.idf.idfapi.business.be.service.helper.PrimpaforservServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.SigmaterServiceHelper;
import it.csi.idf.idfapi.business.be.service.impl.FileGeneratorImpl;
import it.csi.idf.idfapi.business.be.vincolo.pojo.AutorizzaVincolo;
import it.csi.idf.idfapi.dto.BOSearchResult;
import it.csi.idf.idfapi.dto.BoOwnerIstanze;
import it.csi.idf.idfapi.dto.BoProcLog;
import it.csi.idf.idfapi.dto.BoscoHeadings;
import it.csi.idf.idfapi.dto.BoscoSubHeadings;
import it.csi.idf.idfapi.dto.CategoriaForestale;
import it.csi.idf.idfapi.dto.CompensationForesteDTO;
import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.Delega;
import it.csi.idf.idfapi.dto.DichAltriPareri;
import it.csi.idf.idfapi.dto.DichAutorizzazione;
import it.csi.idf.idfapi.dto.DichCompensazione;
import it.csi.idf.idfapi.dto.DichDissensi;
import it.csi.idf.idfapi.dto.DichIstanzaTaglio;
import it.csi.idf.idfapi.dto.DichNota;
import it.csi.idf.idfapi.dto.DichProprieta;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.GeneratedFileTrasfParameters;
import it.csi.idf.idfapi.dto.GeneratedFileVincoloParameters;
import it.csi.idf.idfapi.dto.GeoLnLottoIntervento;
import it.csi.idf.idfapi.dto.GeoPlLottoIntervento;
import it.csi.idf.idfapi.dto.GeoPtLottoIntervento;
import it.csi.idf.idfapi.dto.IntervSelvicolturale;
import it.csi.idf.idfapi.dto.IntervTrasf;
import it.csi.idf.idfapi.dto.IntervTrasformazione;
import it.csi.idf.idfapi.dto.Intervento;
import it.csi.idf.idfapi.dto.InterventoAapp;
import it.csi.idf.idfapi.dto.InterventoCatfor;
import it.csi.idf.idfapi.dto.InterventoPopSeme;
import it.csi.idf.idfapi.dto.InterventoRn2000;
import it.csi.idf.idfapi.dto.InvioIstanzaDTO;
import it.csi.idf.idfapi.dto.IstanzaCompensazione;
import it.csi.idf.idfapi.dto.IstanzaForest;
import it.csi.idf.idfapi.dto.IstanzaTaglio;
import it.csi.idf.idfapi.dto.ParametroAppl;
import it.csi.idf.idfapi.dto.ParametroTrasf;
import it.csi.idf.idfapi.dto.ParamtrasfTrasformazion;
import it.csi.idf.idfapi.dto.PfPg;
import it.csi.idf.idfapi.dto.PfaLottoLocalizza;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PlainPrimaSezione;
import it.csi.idf.idfapi.dto.PlainQuintaSezione;
import it.csi.idf.idfapi.dto.PlainSecondoSezione;
import it.csi.idf.idfapi.dto.PlainSestoSezione;
import it.csi.idf.idfapi.dto.PlainSezioneId;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanzaTagli;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanzaVincolo;
import it.csi.idf.idfapi.dto.PlainTerzaSezione;
import it.csi.idf.idfapi.dto.PopolamentoSeme;
import it.csi.idf.idfapi.dto.ProfessionistaElencoAllegati;
import it.csi.idf.idfapi.dto.PropCatasto;
import it.csi.idf.idfapi.dto.PropcatastoIntervento;
import it.csi.idf.idfapi.dto.RicadenzaInformazioni;
import it.csi.idf.idfapi.dto.SceltiParameter;
import it.csi.idf.idfapi.dto.SoggettoIntervento;
import it.csi.idf.idfapi.dto.StepNumber;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TerzaSezioneSaveResult;
import it.csi.idf.idfapi.dto.TipoParametroTrasf;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.mapper.StringMapper;
import it.csi.idf.idfapi.util.AmbitoIstanzaEnum;
import it.csi.idf.idfapi.util.ConformeDerogaEnum;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.EnumUtil;
import it.csi.idf.idfapi.util.GeomUtil;
import it.csi.idf.idfapi.util.HeadingsDescriptionEnum;
import it.csi.idf.idfapi.util.InstanceStateEnum;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.PlPfaEnum;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SoggettoTypeEnum;
import it.csi.idf.idfapi.util.StringUtils;
import it.csi.idf.idfapi.util.SuperficieCompensationEnum;
import it.csi.idf.idfapi.util.TipoAccreditamentoEnum;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;
import it.csi.idf.idfapi.util.TipoParametroApplEnum;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;
import it.csi.idf.idfapi.util.ValidationUtil;
import it.csi.idf.idfapi.util.VincIdroEnum;
import it.csi.idf.idfapi.util.mail.MailEnum;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.Istanza;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella;

@Component
public class WrapperIstanzaDAOImpl implements WrapperIstanzaDAO {
	
	public static final Logger logger = Logger.getLogger(WrapperIstanzaDAOImpl.class);

	private static final String SEZIONE_1_VALID_MSG = "Sezione 1 deve essere completata";
	private static final String SEZIONE_2_VALID_MSG = "Sezione 2 deve essere completata";
	private static final String SEZIONE_3_VALID_MSG = "Sezione 3 deve essere completata";
	private static final String SEZIONE_4_VALID_MSG = "Sezione 4 deve essere completata";
	private static final String SEZIONE_5_VALID_MSG = "Sezione 5 deve essere completata";
	private static final BigDecimal BIGDECIMAL500 = new BigDecimal(500);
	
	@Autowired
	DichiarazioneSummaryDAO dichiarazioneSummaryDAO;

	@Autowired
	private CategoriaForestaleDAO categoriaForestaleDAO;

	@Autowired
	private ParticellaDAO particella;

	@Autowired
	private ComuneDAO comuneDAO;

	@Autowired
	private DocumentoAllegatoDAO documentoAllegatoDAO;

	@Autowired
	private GeoLnLottoInterventoDAO geoLnLottoInterventoDAO;

	@Autowired
	private GeoPlLottoInterventoDAO geoPlLottoInterventoDAO;

	@Autowired
	private GeoPtLottoInterventoDAO geoPtLottoInterventoDAO;

	@Autowired
	private GSAREPORT gsareport;

	@Autowired
	private InterventoDAO interventoDAO;

	@Autowired
	private IntervPfaDAO intervPfaDAO;

	@Autowired
	private IntervTrasformazioneDAO intervTrasformazioneDAO;

	@Autowired
	private IntervTrasfDAO intervTrasfDAO;

	@Autowired
	private InterventoAappDAO interventoAappDAO;

	@Autowired
	private InterventoCatforDAO interventoCatforDAO;

	@Autowired
	private InterventoPopSemeDAO interventoPopSemeDAO;

	@Autowired
	private InterventoRn2000DAO interventoRn2000DAO;

	@Autowired
	private IntervSelvicoulturaleDAO intervSelvicoulturaleDAO;

	@Autowired
	private IstanzaForestDAO istanzaForestDAO;

	@Autowired
	private IstanzaCompensazioneDAO istanzaCompensazioneDAO;

	@Autowired
	private ParametroApplDAO parametroApplDAO;

	@Autowired
	private ParametroTrasfDAO parametroTrasfDAO;

	@Autowired
	private ParamtrasfTrasformazionDAO paramtrasfTrasformazionDAO;

	@Autowired
	private PfPgDAO pfPgDAO;

	@Autowired
	private PopolamentoSemeDAO popolamentoSemeDAO;

	@Autowired
	private PropCatastoDAO propCatastoDAO;

	@Autowired
	private PropcatastoInterventoDAO propcatastoInterventoDAO;

	@Autowired
	private MacroCatforDAO macroCatforDAO;

	@Autowired
	private RicadenzaService ricadenzaService;

	@Autowired
	private SkOkTrasfDAO skOkTrasfDAO;

	@Autowired
	private SkOkSelvDAO skOkSelvDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private SoggettoInterventoDAO soggettoInterventoDAO;

	@Autowired
	private SupForestaleDAO supForestaleDAO;

	@Autowired
	private TipoForestaleDAO tipoForestaleDAO;

	@Autowired
	private TipoParametroTrasfDAO tipoParametroTrasfDAO;

	@Autowired
	private ZonaAltimetricaDAO zonaAltimetricaDAO;

	@Autowired
	private PartforInterventoDAO partforInterventoDAO;
	
	@Autowired	
	FileGeneratorImpl fileGeneratorImpl;
	
	@Autowired
	DelegaDAO delegaDao;
	
	@Autowired
	WrapperInterventoDAO wrapperInterventoDAO;
	
	@Autowired
	private SigmaterServiceHelper sigmaterServiceHelper;
	
	@Autowired
	private PrimpaforservServiceHelper primpaforservServiceHelper;

	@Autowired
	SIGMATER sigmater;
	
	@Autowired
	private ConfigUtenteDAO configUtenteDAO;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	CnfBoprocLogDAO cnfBoprocLogDAO; 

	@Override
	public StepNumber getNumberOfNextStep(Integer idIntervento) {
		return new StepNumber(skOkTrasfDAO.sumFlgSeziones(idIntervento));
	}

	@Override
	public PlainPrimaSezione getPrimaSezione(int idIntervento)
			throws RecordNotFoundException, RecordNotUniqueException {

		PlainPrimaSezione plainPrimaSezione = new PlainPrimaSezione();

		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
		ConfigUtente cnfUtenteIntervento = configUtenteDAO.getCofigUtenteById(soggetoIntervento.getFkConfigUtente());

		TSoggetto soggetto = soggettoDAO.findSoggettoById(soggetoIntervento.getIdSoggetto());
		

		if(cnfUtenteIntervento.getFkTipoAccreditamento() == 2) {//professionista
			plainPrimaSezione.setPersonaDatiOption(StringUtils.isEmpty(soggetto.getDenominazione())?TipoTitolaritaEnum.PF:TipoTitolaritaEnum.PG);
		}else {
			plainPrimaSezione.setPersonaDatiOption(StringUtils.isEmpty(soggetto.getDenominazione())?TipoTitolaritaEnum.RF:TipoTitolaritaEnum.RG);
		}
		logger.info("Tipo accreditamento: " + (cnfUtenteIntervento.getFkTipoAccreditamento() == 2 ?"Professionista":"Richiedente") 
				+ " - PersonaDatiOption: " + plainPrimaSezione.getPersonaDatiOption());
		
		plainPrimaSezione.setCodiceFiscale(soggetto.getCodiceFiscale());
		plainPrimaSezione.setNome(soggetto.getNome());
		plainPrimaSezione.setCognome(soggetto.getCognome());
		plainPrimaSezione.setDenominazione(soggetto.getDenominazione());
		plainPrimaSezione.setIndirizzo(soggetto.getIndirizzo());
		plainPrimaSezione.setCivico(soggetto.getCivico());
		plainPrimaSezione.setCap(soggetto.getCap());
		plainPrimaSezione.setNrTelefonico(soggetto.getNrTelefonico());
		plainPrimaSezione.seteMail(soggetto.geteMail());
		plainPrimaSezione.setPartitaIva(soggetto.getPartitaIva());

		plainPrimaSezione.setPec(soggetto.getPec());

		TSoggetto ownerSoggetto = soggettoDAO.getSoggettoPfByPg(soggetto.getIdSoggetto());
		if (ownerSoggetto != null) {
			plainPrimaSezione.setOwnerCodiceFiscale(ownerSoggetto.getCodiceFiscale());
		}

		if (soggetto.getFkComune() != null) {
			plainPrimaSezione.setComune(comuneDAO.findComuneResourceByID(soggetto.getFkComune()));
		}

		IstanzaForest istanzaForest = istanzaForestDAO.getByIdIntervento(idIntervento);

		plainPrimaSezione.setTipoIstanzaId(istanzaForest.getFkTipoIstanza());
		plainPrimaSezione.setTipoIstanzaDescr(TipoIstanzaEnum.getEnum(istanzaForest.getFkTipoIstanza()).toString());

		return plainPrimaSezione;
	}

	@Override
	public PlainSezioneId savePrimaSezione(PlainPrimaSezione body, ConfigUtente loggedConfig)
			throws RecordNotUniqueException, ValidationException {

		primaSezioneValidation(body);
		
		registraDelegaSeNecessario(body.getCodiceFiscale(),loggedConfig);

		int idIntervento = interventoDAO.createInterventoWithConfigUtente(loggedConfig.getIdConfigUtente());

		intervTrasformazioneDAO.createIntervTrasformazioneWithConfigUtente(idIntervento,
				loggedConfig.getIdConfigUtente());

		istanzaForestDAO.createIstanzaForest(getIstanzaForest(loggedConfig.getIdConfigUtente(), body, idIntervento));

		TSoggetto soggetto = null; 
		if(( body.getDenominazione() == null || "".equals(body.getDenominazione()))
				&& (body.getPartitaIva() == null || "".equals(body.getPartitaIva()))){
			soggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getCodiceFiscale());
		}else {
			soggetto = soggettoDAO.findAziendaByCodiceFiscale(body.getCodiceFiscale());
		}

		if (soggetto != null) {

			updateSoggettoPrimaSez(body, soggetto);

			associateNaturalLegalPerson(loggedConfig.getIdConfigUtente(), body, soggetto.getIdSoggetto(), idIntervento);

		} else {

			int idNewSog = insertSoggetoPrimaSez(loggedConfig.getIdConfigUtente(), body);
			//soggettoDAO.insertIntoSoggettoTipoSoggetto(idNewSog, SoggettoTypeEnum.RICHIEDENTE.getValue());
			if (body.getOwnerCodiceFiscale() != null ) {
				TSoggetto ownerSoggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getOwnerCodiceFiscale());
				if(ownerSoggetto != null) {
					associatePersonCompanyIfRG(loggedConfig, ownerSoggetto.getIdSoggetto(), idNewSog);
				}
			}

			associateNaturalLegalPerson(loggedConfig.getIdConfigUtente(), body, idNewSog, idIntervento);

		}

		skOkTrasfDAO.insertFlgSez1(idIntervento);

		return new PlainSezioneId(idIntervento);
	}
	
	void registraDelegaSeNecessario(String cfDelegante, ConfigUtente confUtenteDelegato) throws RecordNotUniqueException {
		if(confUtenteDelegato.getFkTipoAccreditamento() == 2) {//se professionista gestire eventuale nuova delega
			logger.info("Is professionista: " + cfDelegante + " - " + confUtenteDelegato.getIdConfigUtente());
			Delega delega = delegaDao.getByCfDeleganteAndConfigUtente(cfDelegante, confUtenteDelegato.getIdConfigUtente());
			if(delega == null){
				logger.info("Delega non presente");
				delega = new Delega();
				delega.setCfDelegante(cfDelegante);
				delega.setIdConfigUtente(confUtenteDelegato.getIdConfigUtente());
				delega.setDataInizio(LocalDate.now());				
				delegaDao.createDelega(delega);
				logger.info("Delega inserita ... o almeno credo");
			}else {
				logger.info("Delega gia presente con dati ");
			}
		}else {
			logger.info("No professionista no delega");
		}
	}

	@Override
	public void updatePrimaSezione(PlainPrimaSezione body, ConfigUtente loggedConfig, int idIntervento)
			throws ValidationException, RecordNotUniqueException {

		if (!skOkTrasfDAO.isFlgSez1Done(idIntervento)) {
			throw new ValidationException(SEZIONE_1_VALID_MSG);
		}

		primaSezioneValidation(body);
		
		registraDelegaSeNecessario(body.getCodiceFiscale(),loggedConfig);

		TSoggetto soggetto = null; 
		if(( body.getDenominazione() == null || "".equals(body.getDenominazione()))
				&& (body.getPartitaIva() == null || "".equals(body.getPartitaIva()))){
			soggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getCodiceFiscale());
		}else {
			soggetto = soggettoDAO.findAziendaByCodiceFiscale(body.getCodiceFiscale());
		}

		logger.info("OwnerCodiceFiscale" + body.getOwnerCodiceFiscale());
		if (soggetto != null) {

			updateSoggettoPrimaSez(body, soggetto);
			updateNaturalLegalPerson(soggetto.getIdSoggetto(), idIntervento);
		} else {			
			int idNewSog = insertSoggetoPrimaSez(loggedConfig.getIdConfigUtente(), body);
			if (body.getOwnerCodiceFiscale() != null && body.getOwnerCodiceFiscale() != body.getCodiceFiscale()) {
				TSoggetto ownerSoggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getOwnerCodiceFiscale());
				associatePersonCompanyIfRG(loggedConfig, ownerSoggetto.getIdSoggetto(), idNewSog);
			}

			//associateNaturalLegalPerson(loggedConfig.getIdConfigUtente(), body, idNewSog, idIntervento);
			updateNaturalLegalPerson(idNewSog, idIntervento);

		}

	}

	@Override
	public PlainSecondoSezione getSecondoSezione(Integer idIntervento)
			throws RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez1Done(idIntervento)) {
			throw new ValidationException(SEZIONE_1_VALID_MSG);
		}

		BigDecimal superficieInteresata =  interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata();
		BigDecimal superficie;
		if (superficieInteresata != null) {
			superficie = superficieInteresata;
		} else {
			superficie = null;
		}

		PlainSecondoSezione plainSecondoSezione = new PlainSecondoSezione();
		plainSecondoSezione.setTotaleSuperficieCatastale(superficie);
		plainSecondoSezione.setTotaleSuperficieTrasf(superficie);

		List<RicadenzaInformazioni> ricadenzaNatura2000 = retrieveRicadenzaNatura2000Sic(idIntervento);
		ricadenzaNatura2000.addAll(retrieveRicadenzaNatura2000Zps(idIntervento));

		plainSecondoSezione.setRicadenzaAreeProtette(retrieveRicadenzaAreeProtette(idIntervento));
		plainSecondoSezione.setRicadenzaNatura2000(ricadenzaNatura2000);

		plainSecondoSezione.setRicadenzaPopolamentiDaSeme(retrieveRicadenzaPopolamentiDaSeme(idIntervento));
		plainSecondoSezione.setRicadenzaCategorieForestali(retrieveRicadenzaCategorieForestali(idIntervento));

		IntervTrasformazione intervTrasformazione = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);

		if (intervTrasformazione != null) {
			plainSecondoSezione.setRicadenzaVincoloIdrogeologico(intervTrasformazione.getFlgVincIdro() == 1);
		}
		List<PropCatasto> propCatastos = propCatastoDAO.getPropCatastosByPropcatastoIntervento(
				propcatastoInterventoDAO.getAllPropcatastoByIdIntervento(idIntervento));

		plainSecondoSezione.setParticelleCatastali(mapPropCatastosToParticelleCatastali(propCatastos));

		if (!propCatastos.isEmpty()) {
			plainSecondoSezione.setAnnotazioni(propCatastos.get(0).getNote());
		}

		return plainSecondoSezione;
	}

	@Override
	public void saveSecondoSezione(PlainSecondoSezione body, ConfigUtente loggedConfig, int idIntervento)
			throws RecordNotUniqueException, ValidationException, RecordNotFoundException {

		if (!skOkTrasfDAO.isFlgSez1Done(idIntervento)) {
			throw new ValidationException(SEZIONE_1_VALID_MSG);
		}

		insertOrDeleteParticelleCatastale(body.getParticelleCatastali(), idIntervento, loggedConfig.getIdConfigUtente(),
				body.getAnnotazioni());

		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, body.getTotaleSuperficieTrasf());
		
		interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);
		interventoRn2000DAO.deleteInterventosByIdIntervento(idIntervento);
		interventoPopSemeDAO.deleteInterventosByIdIntervento(idIntervento);
		interventoCatforDAO.deleteInterventosById(idIntervento);

		saveRicadenzeAreeProtette(body.getRicadenzaAreeProtette(), idIntervento);
		saveRicadenzeNature2000(body.getRicadenzaNatura2000(), idIntervento);
		saveRicadenzePopolamentiSeme(body.getRicadenzaPopolamentiDaSeme(), idIntervento);
		saveRicadenzeCategorieForestale(body.getRicadenzaCategorieForestali(), idIntervento,
				loggedConfig.getIdConfigUtente());
		saveRicadenzaVincoloIdrogeologico(body.isRicadenzaVincoloIdrogeologico(), idIntervento);

		saveCompetenzaTerritoriale(idIntervento);

		skOkTrasfDAO.updateFlgSez2(idIntervento);

	}

	@Override
	public PlainTerzaSezione getTerzaSezione(int idIntervento) throws RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez2Done(idIntervento)) {
			throw new ValidationException(SEZIONE_2_VALID_MSG);
		}

		PlainTerzaSezione plainTerzaSezione = new PlainTerzaSezione();
		List<BoscoHeadings> boscoHeadingList = new ArrayList<>();
		List<TipoParametroTrasf> headings = tipoParametroTrasfDAO.getTipoParametroTrasfs();
		List<ParametroTrasf> subHeadings = parametroTrasfDAO.getParametroTrasfs();

		for (TipoParametroTrasf heading : headings) {
			List<BoscoSubHeadings> boscoSubHeadingList = new ArrayList<>();
			for (ParametroTrasf subHeading : subHeadings) {
				if (subHeading.getFkTipoParametroTrasf().equals(heading.getIdTipoParametroTrasf())
						&& subHeading.getFlgVisible() == 1) {
					boscoSubHeadingList.add(getBoscoSubHeadings(subHeading));
				}
			}
			if (heading.getFlgVisible() == 1) {
				boscoHeadingList.add(getBoscoHeading(heading, boscoSubHeadingList));
			}
		}
		plainTerzaSezione.setHeadings(boscoHeadingList);
		preselectTheRightOptions(idIntervento, plainTerzaSezione);

		return plainTerzaSezione;
	}

	@Override
	public TerzaSezioneSaveResult saveTerzaSezione(int idIntervento, ConfigUtente loggedConfig, PlainTerzaSezione body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez2Done(idIntervento)) {
			throw new ValidationException(SEZIONE_2_VALID_MSG);
		}

		TerzaSezioneSaveResult response = new TerzaSezioneSaveResult();

		response.setWarning(preselectedOptionsWarning(idIntervento, body.getHeadings()));

		BigDecimal calculation = getBaseCalculation(idIntervento);
		logger.info("Base calculation: " + calculation);
		List<ParamtrasfTrasformazion> paramtrasfTrasformazions = paramtrasfTrasformazionDAO
				.getParamtrasfTrasformazionsByIdIntervento(idIntervento);
		List<ParamtrasfTrasformazion> checkedParamtrasf = new ArrayList<>();
		for (BoscoHeadings heading : body.getHeadings()) {
			List<BoscoSubHeadings> checkedSubHeadings = getSubHeadingsfWithCheckedValue(heading);

			if (!checkedSubHeadings.isEmpty()) {
				BoscoSubHeadings filteredSubHeading = getFilteredCheckedSubHeadings(heading);
				calculation = calculation
						.multiply(filteredSubHeading != null ? filteredSubHeading.getValore() : BigDecimal.ONE);
				logger.info("multiply calculation: " + calculation);
				for (BoscoSubHeadings checkedSubHeading : checkedSubHeadings) {
					checkedParamtrasf.add(mapSubheadingToParatrasf(idIntervento, loggedConfig.getIdConfigUtente(),
							checkedSubHeading.getId()));
				}
			}
		}
		executeParamtrasfTrasformazion(paramtrasfTrasformazions, checkedParamtrasf);

		intervTrasformazioneDAO.setCompenzazioneEuro(calculation, idIntervento);
		skOkTrasfDAO.updateFlgSez3(idIntervento);

		return response;
	}

	@Override
	public CompensationForesteDTO getQuartaSezione(Integer idIntervento)
			throws RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez3Done(idIntervento)) {
			throw new ValidationException(SEZIONE_3_VALID_MSG);
		}

		IntervTrasformazione intervTrasf = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
		if (intervTrasf != null) {
			CompensationForesteDTO compensationForesteDTO = new CompensationForesteDTO();
			// Error report (error 11/12)

			compensationForesteDTO.setNecessaria(false);
			compensationForesteDTO.setNonNecessaria(false);
			int z = interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata().compareTo(BIGDECIMAL500);
			if (intervTrasf.getFlgCompensazione() == null && (z<0)){
					
				compensationForesteDTO.setNecessaria(true);
//				compensationForesteDTO.setNonNecessaria(true);
//				compensationForesteDTO.setFlgA(true);
			} else if (intervTrasf.getFlgCompensazione() != null) {
				switch (EnumUtil.stringToEnum(intervTrasf.getFlgCompensazione(), SuperficieCompensationEnum.class)) {
				case F:
					compensationForesteDTO.setNecessaria(true);
					compensationForesteDTO.setCompFisica(true);
					break;
				case M:
					compensationForesteDTO.setNecessaria(true);
					compensationForesteDTO.setCompMonetaria(true);
					break;
				case N:
					boolean isArt7_21 = setFlgArtsIsArt21(idIntervento, intervTrasf, compensationForesteDTO);
					compensationForesteDTO.setNonNecessaria(!isArt7_21);
					compensationForesteDTO.setNonNecessaria21(isArt7_21);
					break;
				default:
				}
			}

			return compensationForesteDTO;
		}
		return null;
	}

	@Override
	public void saveQuartaSezione(Integer idIntervento, CompensationForesteDTO compensationForesteDTO)
			throws ValidationException, RecordNotUniqueException {

		if (!skOkTrasfDAO.isFlgSez3Done(idIntervento)) {
			throw new ValidationException(SEZIONE_3_VALID_MSG);
		}

		if ((!compensationForesteDTO.isNecessaria() && !compensationForesteDTO.isNonNecessaria() && !compensationForesteDTO.isNonNecessaria21())
				|| (!compensationForesteDTO.isFlgA() && !compensationForesteDTO.isFlgB()
						&& !compensationForesteDTO.isFlgC() && !compensationForesteDTO.isFlgD()
						
						&& !compensationForesteDTO.isFlgA21() && !compensationForesteDTO.isFlgB21()
						&& !compensationForesteDTO.isFlgC21() && !compensationForesteDTO.isFlgD21()
						&& !compensationForesteDTO.isFlgDter21() && !compensationForesteDTO.isFlgDquater21()
						&& !compensationForesteDTO.isFlgDquinquies21()
						
						&& !compensationForesteDTO.isCompFisica() && !compensationForesteDTO.isCompMonetaria())
				|| idIntervento == null) {
			throw new IllegalArgumentException("Illegal parameters");
		}

		if (skOkTrasfDAO.sumFlgSeziones(idIntervento) >= 4
				&& !SuperficieCompensationEnum.N.toString()
						.equals(intervTrasformazioneDAO.getIntervTrasfById(idIntervento).getFlgCompensazione())
				&& compensationForesteDTO.isNecessaria()) {
			skOkTrasfDAO.resetFlgToStep4(idIntervento);
		}

		skOkTrasfDAO.updateFlgSez4(idIntervento);

		if (compensationForesteDTO.isNecessaria()) {
			if (compensationForesteDTO.isCompFisica()) {
				intervTrasformazioneDAO.updateCompensazioneNecessaria(SuperficieCompensationEnum.F, idIntervento);
			} else {
				intervTrasformazioneDAO.updateCompensazioneNecessaria(SuperficieCompensationEnum.M, idIntervento);
			}
		}else {
			interventoDAO.updateInterventoWithFkSoggettoProfess(idIntervento, null);
			intervTrasformazioneDAO.setCompenzazioneRealeEuro(null, null, idIntervento);
		}
		skOkTrasfDAO.resetFlgToStep4(idIntervento);

		if (compensationForesteDTO.isNonNecessaria() || compensationForesteDTO.isNonNecessaria21()) {
			intervTrasformazioneDAO.updateCompensazioneNonNecessaria(compensationForesteDTO, idIntervento);
			skOkTrasfDAO.updateFlgSez5(idIntervento);
		}

	}

	@Override
	public PlainQuintaSezione getQuintaSezione(Integer idIntervento)
			throws RecordNotUniqueException, DuplicateRecordException, RecordNotFoundException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez4Done(idIntervento)) {
			throw new ValidationException(SEZIONE_4_VALID_MSG);
		}

		List<SceltiParameter> sceltiParametri = parametroTrasfDAO.getSceltoParemeterByParamtrasfTrasformazion(
				paramtrasfTrasformazionDAO.getParamtrasfTrasformazionsByIdIntervento(idIntervento));

		PlainQuintaSezione plainQuintaSezione = new PlainQuintaSezione();
		plainQuintaSezione.setBaseValue(Long.valueOf(
				parametroApplDAO.getParamertroByTipo(TipoParametroApplEnum.BASE_CALCOLO_EURO).getParametroApplNum()));
		plainQuintaSezione.setSuperficie(interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata());
		plainQuintaSezione.setSceltiParametri(filterSceltiParametri(sceltiParametri));
		IntervTrasformazione intervTrasf = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
		if (intervTrasf != null) {
			plainQuintaSezione.setTotale(intervTrasf.getCompenzazioneEuro());
			plainQuintaSezione.setValoreReale(intervTrasf.getCompenzazioneEuroReale());
			plainQuintaSezione.setNote(intervTrasf.getNoteCompenzazione());
		}
		plainQuintaSezione.setSoggettoProfess(getFkSoggettoProfess(idIntervento));

		return plainQuintaSezione;
	}

	@Override
	public void saveQuintaSezione(Integer idIntervento, PlainQuintaSezione quintaSezione)
			throws RecordNotUniqueException, ValidationException {
		if (!skOkTrasfDAO.isFlgSez4Done(idIntervento)) {
			throw new ValidationException(SEZIONE_4_VALID_MSG);
		}
		TSoggetto soggetto = quintaSezione.getSoggettoProfess();
		validateQuintaSezione(soggetto);
		int fkSoggettoProfess;

		TSoggetto soggettoFromDB = soggettoDAO.findSoggettoByCodiceFiscale(soggetto.getCodiceFiscale());

		if (soggettoFromDB == null) {
			logger.info("New soggetto");
            soggetto.setDataInserimento(LocalDate.now());
            soggetto.setFlgSettoreRegionale((byte) 0); // NOT NULL constraint
            int idSoggetto = soggettoDAO.createSoggetto(soggetto);
            
//            ConfigUtente configUtente = new ConfigUtente();
//			Timestamp now = new Timestamp(System.currentTimeMillis());
//			configUtente.setDataPrimoAccesso(now);
//			configUtente.setDataUltimoAccesso(now);
//			configUtente.setNrAccessi(1);
//			configUtente.setFkProfiloUtente(ProfiloUtenteEnum.CITTADINO.getValue());
//			configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.PROFESSIONISTA.getValue());
//			configUtente.setFlgPrivacy((byte) 1);
//			configUtente.setFkSoggetto(idSoggetto);
//			soggettoDAO.updateFkConfigUtente(idSoggetto, configUtenteDAO.createConfigUtente(configUtente));
			
			fkSoggettoProfess = idSoggetto;
		} else {
			logger.info("Existing soggetto");
			if (!areTwoSoggettosTheSame(soggetto, soggettoFromDB)) {
				logger.info("Update Existing soggetto");
				soggetto.setIdSoggetto(soggettoFromDB.getIdSoggetto());
				soggettoDAO.updateSoggettoProfess(soggetto);
			}
			fkSoggettoProfess = soggettoFromDB.getIdSoggetto();
		}

		interventoDAO.updateInterventoWithFkSoggettoProfess(idIntervento, fkSoggettoProfess);
		intervTrasformazioneDAO.setCompenzazioneRealeEuro(quintaSezione.getValoreReale(), quintaSezione.getNote(), idIntervento);
		skOkTrasfDAO.updateFlgSez5(idIntervento);

	}

	@Override
	public PlainSestoSezione getSestoSezione(Integer idIntervento) throws RecordNotUniqueException {

		List<ParametroAppl> parametroAppl = parametroApplDAO.getParamertriByTipo(TipoParametroApplEnum.DICHIARAZIONI);
		IntervTrasformazione intervTrasformazione = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
		PlainSestoSezione plainSestoSezione = new PlainSestoSezione();
		logger.info("idIntervento" + idIntervento);
		Boolean isSez6Done = skOkTrasfDAO.isFlgSez6Done(idIntervento);
		
		plainSestoSezione.setEtichetta(parametroAppl.get(0).getParametroApplChar());
		plainSestoSezione.setProprieta(retrieveDichProprieta(parametroAppl.get(1), parametroAppl.get(2),
				isSez6Done?intervTrasformazione.getFlgProprieta():1));//default propriotario
		plainSestoSezione
				.setDissensi(retrieveDichDissensi(parametroAppl.get(3), intervTrasformazione.getFlgDissensi()));
		plainSestoSezione.setPaesaggistica(retrieveDichPaesaggistica(parametroAppl.get(4), intervTrasformazione, isSez6Done));
		plainSestoSezione
				.setVincIdrogeologico(retrieveDichVincIdrogeologico(parametroAppl.get(5), intervTrasformazione, isSez6Done));
		plainSestoSezione.setValIncidenza(retrieveDichValIncidenza(parametroAppl.get(6), intervTrasformazione,
				interventoRn2000DAO.getInterventosByIdIntervento(idIntervento), isSez6Done));
		plainSestoSezione.setAltriPareri(retrieveDichAltriPareri(parametroAppl.get(7), intervTrasformazione));
		plainSestoSezione.setCompFisica(retrieveDichCompFisica(parametroAppl.get(8), intervTrasformazione));
		plainSestoSezione.setCompMonetaria(retrieveDichCompMonetaria(parametroAppl.get(9), intervTrasformazione));
		plainSestoSezione.setAltroAllega(retrieveDichAltroAllega());
		List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_FISICA);
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_MONETARIA);
		tipoAllegati.add(TipoAllegatoEnum.ALTRO);
		plainSestoSezione.setAllegati(documentoAllegatoDAO.findDocumentiByIdAndTipos(idIntervento, tipoAllegati));
		plainSestoSezione.setIstanzi(retrieveDichIstanzi(parametroAppl.get(10), idIntervento, intervTrasformazione));
		plainSestoSezione.setNota(retrieveDichNota(intervTrasformazione));

		return plainSestoSezione;
	}

	@Override
	public void saveSestoSezione(Integer idIntervento, ConfigUtente loggedConfig, PlainSestoSezione plainSestoSezione)
			throws RecordNotUniqueException, ValidationException {

		if (!skOkTrasfDAO.isFlgSez5Done(idIntervento)) {
			throw new ValidationException(SEZIONE_5_VALID_MSG);
		}

		// TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(codFiscale);

		List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_FISICA);
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_MONETARIA);
		tipoAllegati.add(TipoAllegatoEnum.ALTRO);
		documentoAllegatiManipulation(plainSestoSezione.getAllegati(), loggedConfig.getIdConfigUtente(), idIntervento,
				tipoAllegati);
		sestoSezioneValidation(plainSestoSezione);
		intervTrasformazioneDAO.updateWithDichiarazioni(retrieveIntervTrasformazioneForDich(plainSestoSezione),
				idIntervento);

		istanzaCompensazioneManipulation(plainSestoSezione.getIstanzi(), loggedConfig.getIdConfigUtente(), idIntervento);
		skOkTrasfDAO.updateFlgSez6(idIntervento);

	}

	@Override
	public PaginatedList<BOSearchResult> backOfficeSearch(Map<String, String> queryParams, ConfigUtente configUtente)
			throws ParseException, RecordNotUniqueException, ValidationException {

		TSoggetto soggetto;
		try {
			soggetto = soggettoDAO.findSoggettoById(configUtente.getFkSoggetto());
			validateSearchParameters(queryParams, configUtente, soggetto);
			return istanzaForestDAO.backOfficeSearch(queryParams,configUtente);
		} catch (RecordNotFoundException e) {
			throw new ValidationException();
		} finally {
		}

	}
	
	@Override
	public PaginatedList<PlainSoggettoIstanzaVincolo> backOfficeVincoloSearch(Map<String, String> queryParams, ConfigUtente configUtente)
			throws ParseException, RecordNotUniqueException, ValidationException {

		TSoggetto soggetto;
		try {
			soggetto = soggettoDAO.findSoggettoById(configUtente.getFkSoggetto());
			validateSearchParameters(queryParams, configUtente, soggetto);
			return istanzaForestDAO.backOfficeVincoloSearch(queryParams,configUtente);
		} catch (RecordNotFoundException e) {
			throw new ValidationException();
		} finally {
		}

	}

	@Override
	public List<FatDocumentoAllegato> getTuttiElencaForIntervento(Integer idIntervento, boolean isGestore) {

		Integer statoIstanza = istanzaForestDAO.getByIdIntervento(idIntervento).getFkStatoIstanza();
		
		List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_FISICA);
		tipoAllegati.add(TipoAllegatoEnum.COMPENSAZIONE_MONETARIA);
		tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE);
		tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_AUTOGRAFA);
		tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.DOCUMENTO_IDENTITA);
		if(!isGestore || statoIstanza > 2) {
			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
		}
		return documentoAllegatoDAO.findDocumentiByIdAndTipos(idIntervento, tipoAllegati);
	}
	
	@Override
	public List<FatDocumentoAllegato> getDocsGestoreByIntervento(Integer idIntervento) {
		List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
		tipoAllegati.add(TipoAllegatoEnum.ALTRO);
		return documentoAllegatoDAO.findDocumentiByIdAndTipos(idIntervento, tipoAllegati);
	}

	@Override
	public InvioIstanzaDTO getDataInvioIstanza(Integer idIntervento) {
		return new InvioIstanzaDTO(istanzaForestDAO.getByIdIntervento(idIntervento).getDataInvio());
	}

	@Override
	public void invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto soggetto,ConfigUtente confUtente)
			throws RecordNotFoundException, RecordNotUniqueException, Exception {
		
		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.TECNICO_FORESTALE.getValue()) {
			logger.info("invioIstanza TECNICO_FORESTALE");
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
			tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_DIGITALE);
			tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_AUTOGRAFA);
			tipoAllegati.add(TipoAllegatoEnum.DOCUMENTO_IDENTITA);

			documentoAllegatiManipulation(body.getAllegati(), soggetto.getFkConfigUtente(), idIntervento, tipoAllegati);

		}
		istanzaForestDAO.updateInvioIstanza(idIntervento, "NONE");
		
		BoProcLog procLog = new BoProcLog();
		procLog.setFkIstanza(idIntervento.toString());
		procLog.setFkAmbitoIstanza(AmbitoIstanzaEnum.TRASFORMAZIONE_DEL_BOSCO.getValue());
		procLog.setFkStepBoproc(1);
		procLog.setCountTentativo(0);
		
		cnfBoprocLogDAO.create(procLog);
		
		mailService.sendMailTrasformazioniByIdIntervento(idIntervento, MailEnum.INVIO_ISTANZA_TRASFORMAZIONI);
		
	}
	
	@Override
	public void invioIstanzaVincolo(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto soggetto,
			ConfigUtente confUtente) throws RecordNotFoundException, RecordNotUniqueException, Exception {
		
		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
//		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.RICHIEDENTE.getValue() 
//				&& TipoAccreditamentoEnum.RICHIEDENTE.getValue() == confUtente.getFkTipoAccreditamento()) {
//			logger.info("invioIstanza RICHIEDENTE");
//
//			TipoAllegatoEnum tipoAllegato = TipoAllegatoEnum.RICHIESTA_AUTORIZZAZIONE;
//			File dichiarazionePdf = fileGeneratorImpl.generateDichiarazione(tipoAllegato , idIntervento);
//			byte[] fileInByte = FileUtils.readFileToByteArray(dichiarazionePdf);
//			FileUploadForm form = new FileUploadForm();
//			form.setData(fileInByte);
//			form.setName(dichiarazionePdf.getName());
//			
//			FatDocumentoAllegato docAllegato = documentoAllegatoDAO.uploadDocumento(idIntervento, tipoAllegato.getValue(), 
//					form, confUtente);
//			logger.info("uidIndex: " + docAllegato.getUidIndex() + " - Nome: " + docAllegato.getNomeAllegato() 
//				+ " - IdTipoAllegato: " + docAllegato.getIdTipoAllegato());
////			List<FatDocumentoAllegato> allegatiList = new ArrayList<FatDocumentoAllegato>();
////			allegatiList.add(docAllegato);
////			
////			List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
////			tipoAllegati.add(tipoAllegato);
////
////			documentoAllegatiManipulation(allegatiList, soggetto.getFkConfigUtente(), idIntervento, tipoAllegati);
//			
//			// TODO Send e-mail
		
		
		
		
		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.TECNICO_FORESTALE.getValue()
				|| TipoAccreditamentoEnum.PROFESSIONISTA.getValue() == confUtente.getFkTipoAccreditamento()) {
			logger.info("invioIstanza TECNICO_FORESTALE");
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
			tipoAllegati.add(TipoAllegatoEnum.RICHIESTA_AUTORIZZAZIONE);
			tipoAllegati.add(TipoAllegatoEnum.RICHIESTA_AUTORIZZAZIONE_AUTOGRAFA);
			tipoAllegati.add(TipoAllegatoEnum.DOCUMENTO_IDENTITA);

//			documentoAllegatiManipulation(body.getAllegati(), soggetto.getFkConfigUtente(), idIntervento, tipoAllegati);

			
//		} else {
//			throw new IllegalArgumentException("Not allowed to proceed with this action");
		}
		istanzaForestDAO.updateInvioIstanza(idIntervento, "NONE");
		mailService.sendMailVincoloByIdIntervento(idIntervento, MailEnum.INVIO_ISTANZA_VINCOLO);
		//protocolazione
		BoProcLog procLog = new BoProcLog();
		procLog.setFkIstanza(idIntervento + "-1");
		procLog.setFkAmbitoIstanza(AmbitoIstanzaEnum.VINCOLO_IDROGEOLOGICO.getValue());
		procLog.setFkStepBoproc(1);
		procLog.setCountTentativo(0);
		cnfBoprocLogDAO.create(procLog);
	}

	@Transactional
	@Override
	public PlainSezioneId saveLocalizzaLotto(PfaLottoLocalizza body, ConfigUtente loggedConfig, Integer idGeoPlPfa)
			throws RecordNotUniqueException {

		int idIntervento = interventoDAO.createInterventoWithConfigUtente(loggedConfig.getIdConfigUtente());

		intervSelvicoulturaleDAO.insertIntervSelvicolturale(
				retrieveIntervSelvicolturaleForInsert(idGeoPlPfa, loggedConfig, idIntervento), idIntervento);

		try {
			createSoggIntervento(loggedConfig.getFkSoggetto(), idIntervento, loggedConfig);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		insertOrDeleteParticelleCatastale(body.getParticelleCatastali(), idIntervento, loggedConfig.getIdConfigUtente(),
				idGeoPlPfa, null);
		
		//partforInterventoDAO.updatePartforInterventoAfterEditing(idIntervento);

		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, body.getTotaleSuperficieCatastale());

		saveRicadenzeAreeProtette(body.getRicadenzaAreeProtette(), idIntervento);
		saveRicadenzeNature2000(body.getRicadenzaNatura2000(), idIntervento);
		saveRicadenzePopolamentiSeme(body.getRicadenzaPopolamentiDaSeme(), idIntervento);

		saveRicadenzeCategorieForestale(body.getRicadenzaCategorieForestali(), idIntervento,
				loggedConfig.getIdConfigUtente());

		saveRicadenzaVincoloIdrogeologico(body.isRicadenzaVincoloIdrogeologico(), idIntervento);

		skOkSelvDAO.insertFlgSez1(idIntervento);

		return new PlainSezioneId(idIntervento);
	}
	
	@Override
	public IstanzaTaglio getIstanzaTaglioByNumIstanza(Integer numIstanza) throws ServiceException {
		Istanza istanzaWs = primpaforservServiceHelper.getDettaglioIstanzaPrimpa(numIstanza);
		IstanzaTaglio istanzaTaglio = new IstanzaTaglio();
		if(istanzaWs != null) {
			istanzaTaglio.setNumIstanza(istanzaWs.getNumIstanza());
			istanzaTaglio.setDataPresentazioneIstanza(istanzaWs.getDataPresentazioneIstanza().getTime().toInstant().atZone(ZoneOffset.UTC).toLocalDate());
			istanzaTaglio.setStato(istanzaWs.getStato());
			istanzaTaglio.setTipoIstanza(istanzaWs.getTipoComunicazione());
			istanzaTaglio.setDescIntervento(istanzaWs.getDescIntervento());
			istanzaTaglio.setSpecieCoinvolte(istanzaWs.getStrElencoSpecieCoinvolte());
			istanzaTaglio.setStimaMassaRetraibile(istanzaWs.getStimaMassaRetraibile());
			istanzaTaglio.setUnita(istanzaWs.getUnitaMisura());
			istanzaTaglio.setComuniInteressati(istanzaWs.getStrElencoComuniInteressati());
			istanzaTaglio.setGoverno(istanzaWs.getGoverno());
			istanzaTaglio.setTipoIntervento(istanzaWs.getTipoIntervento());
		}
		return istanzaTaglio;
	}

	private void createSoggIntervento(Integer idSoggetto, int idIntervento, ConfigUtente loggedConfig) {
		SoggettoIntervento soggettoIntervento = new SoggettoIntervento();

		soggettoIntervento.setIdIntervento(idIntervento);
		soggettoIntervento.setIdSoggetto(idSoggetto);
		soggettoIntervento.setIdTipoSoggetto(SoggettoTypeEnum.RICHIEDENTE.getValue());
		soggettoIntervento.setFkConfigUtente(loggedConfig.getIdConfigUtente());
		soggettoIntervento.setDataInizioValidita(LocalDate.now());

		soggettoInterventoDAO.createSoggettoIntervento(soggettoIntervento);

	}

	private void insertOrDeleteParticelleCatastale(List<PlainParticelleCatastali> particelleCatastali, int idIntervento,
			Integer fkConfigUtente, Integer idGeoPlPfa, String note) throws RecordNotUniqueException {
		for (PlainParticelleCatastali particella : particelleCatastali) {

			if (particella.isToDelete() && !particella.getId().equals(0) && particella.getId() != null) {
				propcatastoInterventoDAO.deletePropcatastoIntervento(particella.getId(), idIntervento);
				// propCatastoDAO.deletePropCatasto(particella.getId());
			} else if ((particella.getId() == null || particella.getId().equals(0)) && !particella.isToDelete()) {

				PropcatastoIntervento propcatastoIntervento = new PropcatastoIntervento();
				propcatastoIntervento.setIdgeoPlPropCatasto(particella.getId());
				propcatastoIntervento.setIdIntervento(idIntervento);
				propcatastoIntervento.setFkConfigUtente(fkConfigUtente);
				propcatastoIntervento.setDataInserimento(LocalDate.now());
				// MK mocked value of sup_intervento_ha -> should be returned from geography
				propcatastoIntervento.setSupIntervento(particella.getSupCatastale().multiply(BigDecimal.valueOf(0.30)));
				propcatastoInterventoDAO.insertPropcatastoIntervento(propcatastoIntervento);
			} else {
				// propCatastoDAO.updateAllNoteOfOneIntervento(note, idIntervento);
			}
		}

	}

	private GeoLnLottoIntervento retireveGeoLnLottoInterventoForInsert(int idIntervento) {
		GeoLnLottoIntervento geoLnLottoIntervento = new GeoLnLottoIntervento();
		geoLnLottoIntervento.setDatainserimento(LocalDate.now());
		geoLnLottoIntervento.setIdIntervento(idIntervento);
		return geoLnLottoIntervento;
	}

	private GeoPtLottoIntervento retireveGeoPtLottoInterventoForInsert(int idIntervento) {
		GeoPtLottoIntervento geoPtLottoIntervento = new GeoPtLottoIntervento();
		geoPtLottoIntervento.setDatainserimento(LocalDate.now());
		geoPtLottoIntervento.setIdIntervento(idIntervento);
		return geoPtLottoIntervento;
	}

	private GeoPlLottoIntervento retireveGeoPlLottoInterventoForInsert(int idIntervento, PfaLottoLocalizza body) {
		GeoPlLottoIntervento geoPlLottoIntervento = new GeoPlLottoIntervento();
		geoPlLottoIntervento.setDatainserimento(LocalDate.now());
		geoPlLottoIntervento.setFkIntervento(idIntervento);
		// TODO MK mocked total sup tagliata to be 30% of superficie catastale -> should
		// be returned from geographical layer
		geoPlLottoIntervento.setSupTagliata(body.getTotaleSuperficieCatastale().multiply(BigDecimal.valueOf(0.30)));

		return geoPlLottoIntervento;
	}

	private void primaSezioneValidation(PlainPrimaSezione body) throws ValidationException {

		if (body.getTipoIstanzaId() == null || body.getPersonaDatiOption() == null
				|| body.getCodiceFiscale() == null) {
			throw new ValidationException();
		}

		if (body.getPartitaIva() != null) {
			if ((body.getPartitaIva().length() > 11 || body.getPec() == null || body.getPec().length() > 200
					|| body.getDenominazione() == null || body.getDenominazione().length() > 200)) {
				throw new ValidationException();
			}

		} else {

			if ((body.getCognome() == null || body.getNome() == null)) {
				throw new ValidationException();
			}
		}

		if (((body.getIndirizzo() == null || body.getNrTelefonico() == null || body.geteMail() == null
				|| body.getCivico() == null || body.getCap() == null || body.getComune() == null)
				|| (body.getIndirizzo().length() > 50 || body.getCivico().length() > 10 || body.getCap().length() != 5
						|| body.getNrTelefonico().length() > 20 || body.geteMail().length() > 100)
				|| !ValidationUtil.isEMail(body.geteMail()))) {
			throw new ValidationException();
		}

	}

	private void updateSoggettoPrimaSez(PlainPrimaSezione body, TSoggetto soggetto) throws RecordNotUniqueException {

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

		ComuneResource comuneResource = comuneDAO.findComuneResourceByID(body.getComune().getIdComune());
		if (comuneResource != null) {
			soggetto.setFkComune(comuneResource.getIdComune());
		}

		soggettoDAO.updateSoggetto(soggetto);
	}

	private int insertSoggetoPrimaSez(int compilerSoggettoConfigUtente, PlainPrimaSezione body)
			throws RecordNotUniqueException {
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
		newSoggetto.setFkConfigUtente(compilerSoggettoConfigUtente);

		ComuneResource comuneResource = comuneDAO.findComuneResourceByID(body.getComune().getIdComune());
		if (comuneResource != null) {
			newSoggetto.setFkComune(comuneResource.getIdComune());
		}
		return soggettoDAO.createSoggetto(newSoggetto);
	}

	private IstanzaForest getIstanzaForest(int compilerSoggettoConfigUtente, PlainPrimaSezione body, int idIntervento) {
		IstanzaForest istanzaForest = new IstanzaForest();

		istanzaForest.setIdIstanzaIntervento(idIntervento);
		istanzaForest.setDataInserimento(LocalDate.now());
		istanzaForest.setFkStatoIstanza(InstanceStateEnum.BOZZA.getStateValue());
		istanzaForest.setFkTipoIstanza(body.getTipoIstanzaId());
		istanzaForest.setNrIstanzaForestale(istanzaForestDAO.getNumberOfInstanceType(body.getTipoIstanzaId()) + 1);
		istanzaForest.setFkConfigUtente(compilerSoggettoConfigUtente);
		return istanzaForest;
	}

	private void associateNaturalLegalPerson(int compilerSoggettoConfigUtente, PlainPrimaSezione body, int idSoggetto,
			int idIntervento) {
		SoggettoIntervento soggettoIntervento = new SoggettoIntervento();

		soggettoIntervento.setIdIntervento(idIntervento);
		soggettoIntervento.setIdSoggetto(idSoggetto);

		if (body.getPartitaIva() == null) {
			soggettoIntervento.setIdTipoSoggetto(1);
		} else {

			soggettoIntervento.setIdTipoSoggetto(1);
		}

		soggettoIntervento.setFkConfigUtente(compilerSoggettoConfigUtente);
		soggettoIntervento.setDataInizioValidita(LocalDate.now());

		soggettoInterventoDAO.createSoggettoIntervento(soggettoIntervento);
	}

	private void associatePersonCompanyIfRG(ConfigUtente loggedConfig, int owner, int idSoggetto)
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

	private void updateNaturalLegalPerson(int idSoggetto, int idIntervento) {
		soggettoInterventoDAO.updateWithIdSoggetto(idSoggetto, idIntervento);
	}

	@Override
	public List<RicadenzaInformazioni> retrieveRicadenzaAreeProtette(Integer idIntervento) {
		List<InterventoAapp> interventoAapps = interventoAappDAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> ricadenzeAapp = gsareport.cercaTutteLePotettePerFiltri();
		List<RicadenzaInformazioni> response = new ArrayList<>();
		for (InterventoAapp interventoAapp : interventoAapps) {
			RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(interventoAapp.getCodiceAapp());
			if (ricadenzeAapp.contains(ricadenzaInformazioni)) {
				response.add(ricadenzeAapp.get(ricadenzeAapp.indexOf(ricadenzaInformazioni)));
			}
		}

		return response;
	}

	@Override
	public List<RicadenzaInformazioni> retrieveRicadenzaNatura2000Sic(Integer idIntervento) {
		List<InterventoRn2000> interventoNaturas2000 = interventoRn2000DAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> ricadenzeNature = gsareport.cercaTuttiSic();
		List<RicadenzaInformazioni> response = new ArrayList<>();
		for (InterventoRn2000 interventoNatura : interventoNaturas2000) {
			RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(interventoNatura.getCodiceRn2000());
			if (ricadenzeNature.contains(ricadenzaInformazioni)) {
				response.add(ricadenzeNature.get(ricadenzeNature.indexOf(ricadenzaInformazioni)));
			}
		}

		return response;
	}

	@Override
	public List<RicadenzaInformazioni> retrieveRicadenzaNatura2000Zps(Integer idIntervento) {
		List<InterventoRn2000> interventoNaturas2000 = interventoRn2000DAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> ricadenzeNature = gsareport.cercaTuttiZps();
		List<RicadenzaInformazioni> response = new ArrayList<>();
		for (InterventoRn2000 interventoNatura : interventoNaturas2000) {
			RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(interventoNatura.getCodiceRn2000());
			if (ricadenzeNature.contains(ricadenzaInformazioni)) {
				response.add(ricadenzeNature.get(ricadenzeNature.indexOf(ricadenzaInformazioni)));
			}
		}
		return response;
	}

	@Override
	public List<RicadenzaInformazioni> retrieveRicadenzaPopolamentiDaSeme(Integer idIntervento) {
		List<InterventoPopSeme> interventoPopSemes = interventoPopSemeDAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> response = new ArrayList<>();
		if (!interventoPopSemes.isEmpty()) {
			List<PopolamentoSeme> poplamentoSemes = popolamentoSemeDAO.getAllByInterventoPopSemes(interventoPopSemes);
			List<RicadenzaInformazioni> ricadenzePopSeme = ricadenzaService.cercaTuttiPopolamentiDaSeme();

			for (PopolamentoSeme poplamentoSeme : poplamentoSemes) {
				RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(
						poplamentoSeme.getCodiceAmministrativo());
				if (ricadenzePopSeme.contains(ricadenzaInformazioni)) {
					response.add(ricadenzePopSeme.get(ricadenzePopSeme.indexOf(ricadenzaInformazioni)));
				}
			}
		}
		return response;
	}

	@Override
	public List<RicadenzaInformazioni> retrieveRicadenzaCategorieForestali(Integer idIntervento) {
		List<InterventoCatfor> interventoCatfors = interventoCatforDAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> response = new ArrayList<>();
		logger.info("interventoCatfors size: " + (interventoCatfors == null?"null":interventoCatfors.size()));
		
		if (!interventoCatfors.isEmpty()) {
			List<RicadenzaInformazioni> ricadenzeCatForest = ricadenzaService.cercaTutteCategorieForestali();
			logger.info("interventoCatfors size: " + (ricadenzeCatForest == null?"null":ricadenzeCatForest.size()));
			List<CategoriaForestale> categorieForestale = categoriaForestaleDAO
					.getAllByInterventoCatfors(interventoCatfors);
			logger.info("categorieForestale size: " + (categorieForestale == null?"null":categorieForestale.size()));
			for (CategoriaForestale categoria : categorieForestale) {
				RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(
						categoria.getCodiceAmministrativo());
				logger.info("getCodiceAmministrativo: " + categoria.getCodiceAmministrativo());
				if (ricadenzeCatForest.contains(ricadenzaInformazioni)) {
					response.add(ricadenzeCatForest.get(ricadenzeCatForest.indexOf(ricadenzaInformazioni)));
				}
			}
		}
		logger.info("response size: " + (response == null?"null":response.size()));
		return response;
	}

	public List<PlainParticelleCatastali> mapPropCatastosToParticelleCatastali(List<PropCatasto> propCatastos)
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
			particelleCatastali.setToDelete(false);
			//7777
			particelleCatastali.setGeometry(propCatasto.getGeometria());
			//
			response.add(particelleCatastali);
		}

		return response;
	}

	private void insertOrDeleteParticelleCatastale(List<PlainParticelleCatastali> particelleCatastali, int idIntervento,
			int fkConfigUtente, String note) throws RecordNotUniqueException {
		for (PlainParticelleCatastali particella : particelleCatastali) {

			if (particella.isToDelete() && !particella.getId().equals(0) && particella.getId() != null) {
				propcatastoInterventoDAO.deletePropcatastoIntervento(particella.getId(), idIntervento);
				// propCatastoDAO.deletePropCatasto(particella.getId());

			} else if ((particella.getId() == null || particella.getId().equals(0)) && !particella.isToDelete()) {

				PropcatastoIntervento propcatastoIntervento = new PropcatastoIntervento();
				propcatastoIntervento.setIdgeoPlPropCatasto(particella.getId());
				propcatastoIntervento.setIdIntervento(idIntervento);
				propcatastoIntervento.setFkConfigUtente(fkConfigUtente);
				propcatastoIntervento.setDataInserimento(LocalDate.now());
				propcatastoInterventoDAO.insertPropcatastoIntervento(propcatastoIntervento);
			} else {
				// DM field NOTE is removed from idf_geo_pl_prop_catasto in MONFOR
				// propCatastoDAO.updateAllNoteOfOneIntervento(note, idIntervento);
			}
		}
	}

	private void saveRicadenzaVincoloIdrogeologico(boolean isVincoloIdrogeologico, int idIntervento) {
		if (isVincoloIdrogeologico) {
			intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.YES.getValue(), idIntervento);
		} else {
			intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.NO.getValue(), idIntervento);
		}
	}

	private void saveRicadenzeCategorieForestale(List<RicadenzaInformazioni> ricadenze, int idIntervento,
			int idConfigUtente) {
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoCatfor interventoCatfor = new InterventoCatfor();
			interventoCatfor.setIdIntervento(idIntervento);
			interventoCatfor.setIdCategoriaForestale(categoriaForestaleDAO
					.getByCodiceAmministratico(ricadenza.getCodiceAmministrativo()).getIdCategoriaForestale());
			interventoCatfor.setFkConfigUtente(idConfigUtente);
			interventoCatforDAO.insertInterventoCatfor(interventoCatfor);
		}
	}

	private void saveRicadenzePopolamentiSeme(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoPopSeme interventoPopSeme = new InterventoPopSeme();
			interventoPopSeme.setIdIntervento(idIntervento);
			interventoPopSeme.setIdPopolamentoSeme(popolamentoSemeDAO
					.getByCodiceAmministrativo(ricadenza.getCodiceAmministrativo()).getIdPopolamentoSeme());
			interventoPopSemeDAO.insertInterventoPopSeme(interventoPopSeme);
		}
	}

	private void saveRicadenzeNature2000(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoRn2000 interventoRn2000 = new InterventoRn2000();
			interventoRn2000.setIdIntervento(idIntervento);
			interventoRn2000.setCodiceRn2000(ricadenza.getCodiceAmministrativo());
			interventoRn2000DAO.insertInterventoRn2000(interventoRn2000);
		}
	}

	private void saveRicadenzeAreeProtette(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoAapp interventoAapp = new InterventoAapp();
			interventoAapp.setIdIntervento(idIntervento);
			interventoAapp.setCodiceAapp(ricadenza.getCodiceAmministrativo());
			interventoAappDAO.insertInterventoAapp(interventoAapp);
		}
	}
	
	private void saveParticelleToIntervTrasf(List<PlainParticelleCatastali> listParticelle,SIGMATER sigmater,
			IntervTrasfDAO intervTrasfDAO, int idIntervento ) {
		
		//delete particelle 
		int deleted = intervTrasfDAO.deleteIntervTrasfByFkIntervento(idIntervento);
		logger.info("intervTrasf deleted: " + deleted);
		
		IntervTrasf intervTrasf = new IntervTrasf();
		intervTrasf.setFkIntervento(idIntervento);
		intervTrasf.setDataInserimento(LocalDate.now());

		for (PlainParticelleCatastali part : listParticelle) {
			//intervTrasf.setGeometria(sigmater.getGeometryFromParticelleCatastali(part));
			
			//7777
			if(part.getGeometry()!=null){
				intervTrasf.setGeometria(part.getGeometry());
			}//
			
			intervTrasfDAO.insertIntervTrasf(intervTrasf);
		}
		//intervTrasfDAO.insertIntervTrasf(intervTrasf);
	}
	
	private void saveCompetenzaTerritoriale(int idIntervento) {
		TSoggetto sogCompetenza = soggettoDAO.findSoggettoCompetenzaIstanza(idIntervento);
		istanzaForestDAO.updateEnteCompetente(idIntervento, sogCompetenza.getIdSoggetto());
	}

	private BoscoHeadings getBoscoHeading(TipoParametroTrasf heading, List<BoscoSubHeadings> boscoSubHeadingList) {
		BoscoHeadings boscoHeading = new BoscoHeadings();

		boscoHeading.setId(heading.getIdTipoParametroTrasf());
		boscoHeading.setName(heading.getTipoParemetroTrasf());
		boscoHeading.setVisibility(true);
		boscoHeading.setSubheadings(boscoSubHeadingList);
		return boscoHeading;
	}

	private BoscoSubHeadings getBoscoSubHeadings(ParametroTrasf subHeading) {
		BoscoSubHeadings boscoSubHeading = new BoscoSubHeadings();

		boscoSubHeading.setId(subHeading.getIdParametroTrasf());
		boscoSubHeading.setName(subHeading.getDescParametroTrasf());
		boscoSubHeading.setVisibility(true);
		boscoSubHeading.setValore(subHeading.getValore());
		boscoSubHeading.setChecked(false);
		return boscoSubHeading;
	}

	private void preselectTheRightOptions(Integer idIntervento, PlainTerzaSezione plainTersaSezione)
			throws RecordNotUniqueException {
		List<ParamtrasfTrasformazion> paramtrasfs = paramtrasfTrasformazionDAO
				.getParamtrasfTrasformazionsByIdIntervento(idIntervento);
		if (!paramtrasfs.isEmpty()) {
			preselectOptionsFromDatabase(idIntervento, plainTersaSezione.getHeadings(), paramtrasfs);
		}

		int locationSubHeadingId = getLocationSubHeadingId(idIntervento);
		Integer categoryForestId = categoriaForestaleDAO.calculateCategoriaForestaleByIdIntervento(idIntervento);
		logger.info("zona altimetrica: " + locationSubHeadingId + " - categoria forestale: " + categoryForestId);
		for (BoscoHeadings heading : plainTersaSezione.getHeadings()) {
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.C.toString())
						&& subHeading.getId() == locationSubHeadingId) {
					subHeading.setChecked(true);
				}
				
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.B.toString())
						&& subHeading.getId() == categoryForestId) {
					subHeading.setChecked(true);
				}
			}
			setDestinazioniOption(idIntervento, heading);
		}
	}

	private void preselectOptionsFromDatabase(Integer idIntervento, List<BoscoHeadings> headings,
			List<ParamtrasfTrasformazion> paramtrasfs) {
		for (BoscoHeadings heading : headings) {
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (paramtrasfs.contains(new ParamtrasfTrasformazion(subHeading.getId(), idIntervento))) {
					subHeading.setChecked(true);
				}
			}
		}
	}

	private String preselectedOptionsWarning(int idIntervento, List<BoscoHeadings> headings)
			throws RecordNotUniqueException {

		Integer locationSubHeadingId = getLocationSubHeadingId(idIntervento);

		StringBuilder sb = new StringBuilder();

		for (BoscoHeadings heading : headings) {
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.B.toString())
						&& !isSetCategoryForestOption(idIntervento, subHeading)) {
					sb.append("CATEGORIA FORESTALE - preselezione del sistema modificata;\r\n");
				}
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.C.toString())
						&& subHeading.getId() == locationSubHeadingId && !subHeading.isChecked()) {
					sb.append("UBICAZIONE - preselezione del sistema modificata;\r\n");
				}
			}
			if (!isSetDestinazioniOption(idIntervento, heading)) {
				sb.append("DESTINAZIONI, FUNZIONI, VINCOLI - preselezione del sistema modificata;");
			}
		}

		if (sb.toString().length() != 0) {
			return sb.toString();
		}
		return null;
	}

	private BigDecimal getBaseCalculation(int idIntervento) {
		ParametroAppl parametroAppl = parametroApplDAO.getParamertroByTipo(TipoParametroApplEnum.BASE_CALCOLO_EURO);
		return BigDecimal.valueOf(parametroAppl.getParametroApplNum()).multiply(interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata());
	}

	private List<BoscoSubHeadings> getSubHeadingsfWithCheckedValue(BoscoHeadings heading) {
		List<BoscoSubHeadings> subHeadings = new ArrayList<>();
		for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
			if (subHeading.isChecked()) {
				subHeadings.add(subHeading);
			}
		}
		return subHeadings;
	}

	private BoscoSubHeadings getFilteredCheckedSubHeadings(BoscoHeadings heading) {
		if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.D.toString())
				|| heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.E.toString())) {
			BigDecimal maxValue = BigDecimal.ZERO;
			BoscoSubHeadings responseSubHeading = null;
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (subHeading.isChecked() && maxValue.compareTo(subHeading.getValore()) < 0) {
					maxValue = subHeading.getValore();
					responseSubHeading = subHeading;
				}
			}
			return responseSubHeading;
		} else {
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (subHeading.isChecked()) {
					return subHeading;
				}
			}
		}
		return null;
	}

	private void executeParamtrasfTrasformazion(List<ParamtrasfTrasformazion> paramtrasfTrasformazions,
			List<ParamtrasfTrasformazion> checkedParamtrasfs) {
		List<ParamtrasfTrasformazion> paramsfToDelete = new ArrayList<>();
		if (paramtrasfTrasformazions.isEmpty()) {
			for (ParamtrasfTrasformazion checkedParam : checkedParamtrasfs) {
				paramtrasfTrasformazionDAO.insert(checkedParam);
			}
		} else {
			for (ParamtrasfTrasformazion checkedParam : checkedParamtrasfs) {
				if (!paramtrasfTrasformazions.contains(checkedParam)) {
					paramtrasfTrasformazionDAO.insert(checkedParam);
				}
			}
			for (ParamtrasfTrasformazion paramtrasfTrasformazion : paramtrasfTrasformazions) {
				if (!checkedParamtrasfs.contains(paramtrasfTrasformazion)) {
					paramsfToDelete.add(paramtrasfTrasformazion);
				}
			}
		}
		removeParamtrasfTrasformazionsFromDB(paramsfToDelete);
	}

	private Integer getLocationSubHeadingId(Integer idIntervento) {
		return zonaAltimetricaDAO.getZonaAltimentricaByIdIntervento(idIntervento);
	}

//	private void setCategoryForestOption(Integer idIntervento, BoscoSubHeadings subHeading) {
//		int intersection = supForestaleDAO.getTipoForestaleIntersectPropCatasto(idIntervento);
//		if (intersection != -1) {
//			ParametroTrasf parametroTrasf = parametroTrasfDAO
//					.getParametroTrasfById(macroCatforDAO.getMacroCatforById(categoriaForestaleDAO
//							.getCategoriaForestaleById(
//									tipoForestaleDAO.getTipoForestaleById(intersection).getFkCategoriaForestale())
//							.getFkParamMacroCatfor()).getIdParamMacroCatfor());
//
//			if (subHeading.getId().equals(parametroTrasf.getIdParametroTrasf())) {
//				subHeading.setChecked(true);
//			}
//		}
//	}

	private void setDestinazioniOption(Integer idIntervento, BoscoHeadings heading) throws RecordNotUniqueException {
		if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.D.toString())) {
			// First item is always checked
			heading.getSubheadings().get(0).setChecked(true);
			IntervTrasformazione intervTrasf = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
			if (intervTrasf != null && (intervTrasf.getFlgVincIdro() == 1)) {
				heading.getSubheadings().get(1).setChecked(true);
			}

			if (!interventoAappDAO.getInterventosByIdIntervento(idIntervento).isEmpty()
					|| !interventoRn2000DAO.getInterventosByIdIntervento(idIntervento).isEmpty()
					|| !interventoPopSemeDAO.getInterventosByIdIntervento(idIntervento).isEmpty()) {
				heading.getSubheadings().get(2).setChecked(true);
			}
		}
	}

	private boolean isSetCategoryForestOption(Integer idIntervento, BoscoSubHeadings subHeading) {
		int intersection = supForestaleDAO.getTipoForestaleIntersectPropCatasto(idIntervento);

		if (intersection != -1) {
			ParametroTrasf parametroTrasf = parametroTrasfDAO
					.getParametroTrasfById(macroCatforDAO.getMacroCatforById(categoriaForestaleDAO
							.getCategoriaForestaleById(
									tipoForestaleDAO.getTipoForestaleById(intersection).getFkCategoriaForestale())
							.getFkParamMacroCatfor()).getIdParamMacroCatfor());

			return !(subHeading.getId().equals(parametroTrasf.getIdParametroTrasf()) && !subHeading.isChecked());
		} else {
			return true;
		}
	}

	private boolean isSetDestinazioniOption(Integer idIntervento, BoscoHeadings heading)
			throws RecordNotUniqueException {
		if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.D.toString())) {
			// First item is always checked
			if (!heading.getSubheadings().get(0).isChecked()) {
				throw new IllegalArgumentException(
						"'Vincolo Paesaggistico' option must be selected");
			}
			IntervTrasformazione intervTrasf = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);
			if (intervTrasf != null
					&& (intervTrasf.getFlgVincIdro() == 1 && !heading.getSubheadings().get(1).isChecked())) {
				return false;
			}

			if ((!interventoAappDAO.getInterventosByIdIntervento(idIntervento).isEmpty()
					|| !interventoRn2000DAO.getInterventosByIdIntervento(idIntervento).isEmpty()
					|| !interventoPopSemeDAO.getInterventosByIdIntervento(idIntervento).isEmpty())
					&& !heading.getSubheadings().get(2).isChecked()) {
				return false;
			}
		}
		return true;
	}

	private boolean setFlgArtsIsArt21(Integer idIntervento, IntervTrasformazione intervTrasf,
			CompensationForesteDTO compensationForesteDTO) {
		boolean isArt7_21 = false;
		
		if (intervTrasf.getFlgArt7A21() == 1) {
			isArt7_21 = true;
			compensationForesteDTO.setFlgA21(true);
		}
		if (intervTrasf.getFlgArt7B21() == 1) {
			isArt7_21 = true;
			compensationForesteDTO.setFlgB21(true);
		}
		if (intervTrasf.getFlgArt7C21() == 1) {
			isArt7_21 = true;
			compensationForesteDTO.setFlgC21(true);
		}
		if (intervTrasf.getFlgArt7D21() == 1) {
			isArt7_21 = true;
			compensationForesteDTO.setFlgD21(true);
		}
		if (intervTrasf.getFlgArt7DTer21() == 1) {
			isArt7_21 = true;
			compensationForesteDTO.setFlgDter21(true);
		}
		if (intervTrasf.getFlgArt7DQuater21() == 1) {
			isArt7_21 = true;
			compensationForesteDTO.setFlgDquater21(true);
		}
		if (intervTrasf.getFlgArt7DQuinquies21() == 1) {
			isArt7_21 = true;
			compensationForesteDTO.setFlgDquinquies21(true);
		}
		
		if (intervTrasf.getFlgArt7A() == 1 
				||(!isArt7_21 && (interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata().compareTo(BIGDECIMAL500)<0))) {

			compensationForesteDTO.setFlgA(true);
		}
		if (intervTrasf.getFlgArt7B() == 1) {
			compensationForesteDTO.setFlgB(true);
		}
		if (intervTrasf.getFlgArt7C() == 1) {
			compensationForesteDTO.setFlgC(true);
		}
		if (intervTrasf.getFlgArt7D() == 1) {
			compensationForesteDTO.setFlgD(true);
		}
		
		return isArt7_21;
	}
	
	

	private void removeParamtrasfTrasformazionsFromDB(List<ParamtrasfTrasformazion> paramsfToDelete) {
		if (!paramsfToDelete.isEmpty()) {
			for (ParamtrasfTrasformazion paramtrasfTrasformazion : paramsfToDelete) {
				paramtrasfTrasformazionDAO.deleteByIdInterventoAndIdParametroTrasf(
						paramtrasfTrasformazion.getIdIntervento(), paramtrasfTrasformazion.getIdParametroTrasf());
			}
		}
	}

	private ParamtrasfTrasformazion mapSubheadingToParatrasf(Integer idIntervento, Integer fkConfigUtente,
			int idSubheading) {
		ParamtrasfTrasformazion paramtrasfTrasformazion = new ParamtrasfTrasformazion();

		paramtrasfTrasformazion.setIdParameroTrasf(idSubheading);
		paramtrasfTrasformazion.setIdIntervento(idIntervento);
		paramtrasfTrasformazion.setFkConfigUtente(fkConfigUtente);
		paramtrasfTrasformazion.setDataInserimento(LocalDate.now());

		return paramtrasfTrasformazion;
	}

	private boolean areTwoSoggettosTheSame(TSoggetto soggetto, TSoggetto soggettoFromDB) {
		return Objects.equals(soggetto.getCodiceFiscale(), soggettoFromDB.getCodiceFiscale())
				&& Objects.equals(soggetto.getPartitaIva(), soggettoFromDB.getPartitaIva())
				&& Objects.equals(soggetto.getCognome(), soggettoFromDB.getCognome())
				&& Objects.equals(soggetto.getNome(), soggettoFromDB.getNome())
				&& Objects.equals(soggetto.getFkComune(), soggettoFromDB.getFkComune())
				&& Objects.equals(soggetto.getIndirizzo(), soggettoFromDB.getIndirizzo())
				&& Objects.equals(soggetto.getCivico(), soggettoFromDB.getCivico())
				&& Objects.equals(soggetto.getCap(), soggettoFromDB.getCap())
				&& Objects.equals(soggetto.getNrTelefonico(), soggettoFromDB.getNrTelefonico())
				&& Objects.equals(soggetto.geteMail(), soggettoFromDB.geteMail())
				&& Objects.equals(soggetto.getPec(), soggettoFromDB.getPec());
	}

	private List<SceltiParameter> filterSceltiParametri(List<SceltiParameter> sceltiParametri) {
		List<SceltiParameter> sceltiResponse = new ArrayList<>();
		for (int i = 0; i < sceltiParametri.size(); i++) {
			boolean moreThanOne = false;
			for (int j = i + 1; j < sceltiParametri.size(); j++) {
				if (sceltiParametri.get(i).getNome().equals(sceltiParametri.get(j).getNome())) {
					moreThanOne = true;
				}
			}
			if (!moreThanOne) {
				sceltiResponse.add(sceltiParametri.get(i));
			}
		}
		return sceltiResponse;
	}

	private TSoggetto getFkSoggettoProfess(Integer idIntervento) throws RecordNotFoundException {
		int fkSoggettoProfess = interventoDAO.findInterventoByIdIntervento(idIntervento).getFkSoggettoProfess();
		if (fkSoggettoProfess != 0) {
			// DM removeAdressInfo(soggettoDAO.findSoggettoById(fkSoggettoProfess));
			return soggettoDAO.findSoggettoById(fkSoggettoProfess);
		}
		return null;
	}


	private void validateQuintaSezione(TSoggetto soggetto) {

		if (soggetto == null || soggetto.getCodiceFiscale() == null || soggetto.getPartitaIva() == null
				|| soggetto.getCognome() == null || soggetto.getNome() == null 
			//|| soggetto.getFkComune() == null || soggetto.getFkComune() < 1  || soggetto.getCivico() == null || soggetto.getCap() == null
				|| soggetto.getNrTelefonico() == null || soggetto.geteMail() == null
				|| soggetto.getPec() == null) {
			throw new ValidationException(ErrorConstants.OGGETTO_OBBLIGATORIO);
		}

		if (soggetto.getCodiceFiscale().length() > 16 || soggetto.getPartitaIva().length() > 11
				|| soggetto.getCognome().length() > 100 || soggetto.getNome().length() > 50
			    //|| soggetto.getCivico().length() > 10 || soggetto.getCap().length() != 5 
				|| soggetto.getNrTelefonico().length() > 20
				|| soggetto.geteMail().length() > 100 || soggetto.getPec().length() > 200) {
			throw new ValidationException(ErrorConstants.STRINGA_DIGITATA_ERROR);
		}

		if (!ValidationUtil.isEMail(soggetto.geteMail())) {
			throw new ValidationException(ErrorConstants.MAIL_NON_VALIDO);
		}

	}

	private DichProprieta retrieveDichProprieta(ParametroAppl parametroAppl1, ParametroAppl parametroAppl2,
			Byte flgProprieta) {
		DichProprieta dichProprieta = new DichProprieta();
		dichProprieta.setEtichetta1(parametroAppl1.getParametroApplChar());
		dichProprieta.setEtichetta2(parametroAppl2.getParametroApplChar());
		dichProprieta.setOwner(flgProprieta == 1);
		return dichProprieta;
	}

	private DichDissensi retrieveDichDissensi(ParametroAppl parametroAppl, Byte flgDissensi) {
		DichDissensi dichDissensi = new DichDissensi();
		dichDissensi.setEtichetta(parametroAppl.getParametroApplChar());
		dichDissensi.setHaDissenso(flgDissensi == 1);
		return dichDissensi;
	}

	private DichAutorizzazione retrieveDichPaesaggistica(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione, Boolean isSez6Done) {
		DichAutorizzazione dichAutorizzazione = new DichAutorizzazione();
		dichAutorizzazione.setEtichetta(parametroAppl.getParametroApplChar());
		dichAutorizzazione.setVisible(true);
		dichAutorizzazione.setChecked(isSez6Done? intervTrasformazione.getFlgAutPaesaggistica() == 1:true);
		dichAutorizzazione.setRequired(true);
		if (intervTrasformazione.getFlgAutPaesaggistica() == 1) {
			dichAutorizzazione.setNumeroAutorizzazione(intervTrasformazione.getNrAutPaesaggistica());
			dichAutorizzazione.setDataAutorizzazione(intervTrasformazione.getDataAutPaesaggistica());
			dichAutorizzazione.setRilasciataAutorizzazione(intervTrasformazione.getEnteAutPaesaggistica());
		}
		return dichAutorizzazione;
	}

	private DichAutorizzazione retrieveDichVincIdrogeologico(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione, Boolean isSez6Done) {
		DichAutorizzazione dichAutorizzazione = new DichAutorizzazione();
		dichAutorizzazione.setEtichetta(parametroAppl.getParametroApplChar());
		dichAutorizzazione.setVisible(intervTrasformazione.getFlgVincIdro() == 1);
		dichAutorizzazione.setChecked(isSez6Done?
				intervTrasformazione.getFlgAutVidro()==1:intervTrasformazione.getFlgVincIdro() == 1);
		dichAutorizzazione.setRequired(false);
		if (intervTrasformazione.getFlgVincIdro() == 1 && intervTrasformazione.getFlgAutVidro() == 1) {
			dichAutorizzazione.setNumeroAutorizzazione(intervTrasformazione.getNrAutVidro());
			dichAutorizzazione.setDataAutorizzazione(intervTrasformazione.getDataAutVidro());
			dichAutorizzazione.setRilasciataAutorizzazione(intervTrasformazione.getEnteAutVidro());
		}
		logger.info("isSez6Done: " + isSez6Done + " - FlgAutVidro: " + intervTrasformazione.getFlgVincIdro() + 
				" - FlgVincIdro: " + intervTrasformazione.getFlgVincIdro() +
				" - checked: " + dichAutorizzazione.isChecked());
		return dichAutorizzazione;
	}

	private DichAutorizzazione retrieveDichValIncidenza(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione, List<InterventoRn2000> interventos, Boolean isSez6Done) {
		boolean isInterventos2000Empty = interventos.isEmpty();
		DichAutorizzazione dichAutorizzazione = new DichAutorizzazione();
		dichAutorizzazione.setEtichetta(parametroAppl.getParametroApplChar());
		dichAutorizzazione.setVisible(!isInterventos2000Empty);
		dichAutorizzazione.setChecked(isSez6Done?
				intervTrasformazione.getFlgAutIncidenza() == 1:!isInterventos2000Empty);
		dichAutorizzazione.setRequired(false);
		if (!isInterventos2000Empty && intervTrasformazione.getFlgAutIncidenza() == 1) {
			dichAutorizzazione.setNumeroAutorizzazione(intervTrasformazione.getNrAutIncidenza());
			dichAutorizzazione.setDataAutorizzazione(intervTrasformazione.getDataAutIncidenza());
			dichAutorizzazione.setRilasciataAutorizzazione(intervTrasformazione.getEnteAutIncidenza());
		}
		logger.info("isSez6Done: " + isSez6Done + " - FlgAutIncidenza: " + intervTrasformazione.getFlgAutIncidenza() + 
				" - !isInterventos2000Empty: " + isInterventos2000Empty +
				" - checked: " + dichAutorizzazione.isChecked());
		return dichAutorizzazione;
	}

	private DichAltriPareri retrieveDichAltriPareri(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		DichAltriPareri dichAltriPareri = new DichAltriPareri();
		dichAltriPareri.setEtichetta(parametroAppl.getParametroApplChar());
		dichAltriPareri.setChecked(intervTrasformazione.getAltriPareri() != null);
		if (intervTrasformazione.getAltriPareri() != null) {
			dichAltriPareri.setTesto(intervTrasformazione.getAltriPareri());
		}
		return dichAltriPareri;
	}

	private DichCompensazione retrieveDichCompFisica(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		boolean required = SuperficieCompensationEnum.F.toString().equals(intervTrasformazione.getFlgCompensazione());
		DichCompensazione dichCompensazione = new DichCompensazione();
		dichCompensazione.setEtichetta(parametroAppl.getParametroApplChar().replace("{$DEPOSIT$}",
				intervTrasformazione.getCompenzazioneEuroReale() == null?" - ":intervTrasformazione.getCompenzazioneEuroReale().toString()));
		dichCompensazione.setVisible(required);
		dichCompensazione.setChecked(required);
		dichCompensazione.setRequired(required);
		return dichCompensazione;
	}

	private DichCompensazione retrieveDichCompMonetaria(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		boolean isChecked = SuperficieCompensationEnum.M.toString().equals(intervTrasformazione.getFlgCompensazione());
		DichCompensazione dichCompensazione = new DichCompensazione();
		dichCompensazione.setEtichetta(parametroAppl.getParametroApplChar().replace("{$DEPOSIT$}",
				intervTrasformazione.getCompenzazioneEuroReale() == null?" - ":intervTrasformazione.getCompenzazioneEuroReale().toString()));
		dichCompensazione.setVisible(isChecked);
		dichCompensazione.setChecked(isChecked);
		dichCompensazione.setRequired(isChecked);
		return dichCompensazione;
	}

	private DichCompensazione retrieveDichAltroAllega() {
		DichCompensazione dichCompensazione = new DichCompensazione();
		dichCompensazione.setVisible(true);
		dichCompensazione.setChecked(false);
		dichCompensazione.setRequired(false);
		return dichCompensazione;
	}

	private DichIstanzaTaglio retrieveDichIstanzi(ParametroAppl parametroAppl, int idIntervento,
			IntervTrasformazione intervTrasformazione) {
		boolean required = SuperficieCompensationEnum.F.toString().equals(intervTrasformazione.getFlgCompensazione());
		DichIstanzaTaglio dichIstanzaTaglio = new DichIstanzaTaglio();
		dichIstanzaTaglio.setEtichetta(parametroAppl.getParametroApplChar());
		dichIstanzaTaglio.setVisible(required);
		dichIstanzaTaglio.setRequired(required);
		dichIstanzaTaglio.setChecked(required);
		if (required) {
			dichIstanzaTaglio.setIstanziList(getIstanzeTaglioByIdIntervento(idIntervento));
		} else {
			dichIstanzaTaglio.setIstanziList(null);
		}

		return dichIstanzaTaglio;
	}
	
	@Override
	public List<IstanzaTaglio> getIstanzeTaglioByIdIntervento(int idIntervento) {
		return mapToIstanziTaglioList(istanzaCompensazioneDAO.getAllByFkIntervento(idIntervento));
	}

	@Override
	public List<IstanzaTaglio> mapToIstanziTaglioList(List<IstanzaCompensazione> istanzas) {
		List<IstanzaTaglio> istanziTaglio = new ArrayList<>();
		IstanzaTaglio istTaglio;
		for (IstanzaCompensazione istanzaComp : istanzas) {
			try {
				istTaglio = getIstanzaTaglioByNumIstanza(istanzaComp.getNumIstanzaCompensazione());
				istTaglio.setIdIstanza(istanzaComp.getFkIntervento());
				istanziTaglio.add(istTaglio);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
		return istanziTaglio;
	}

	private DichNota retrieveDichNota(IntervTrasformazione intervTrasformazione) {
		DichNota dichNota = new DichNota();
		dichNota.setEtichetta(
				"Annotazioni/dichiarazioni/chiarimenti relativi alle dichiarazioni riportate, da sottoporre a chi valutera la domanda");
		dichNota.setTesto(intervTrasformazione.getNoteDichiarazione());
		return dichNota;
	}

	private void sestoSezioneValidation(PlainSestoSezione plainSestoSezione) throws ValidationException {

		validateAutPaesaggistica(plainSestoSezione);

		validateAutVidro(plainSestoSezione);

		validateVidroIncidenza(plainSestoSezione);

		if (plainSestoSezione.getAltriPareri().isChecked()
				&& plainSestoSezione.getAltriPareri().getTesto().length() > 1000) {
			throw new ValidationException();
		}

		validateCompensazione(plainSestoSezione);

		for (FatDocumentoAllegato documento : plainSestoSezione.getAllegati()) {
			if (documento.getIdDocumentoAllegato() == null || documento.getIdDocumentoAllegato().equals(0)) {
				throw new ValidationException();
			}
		}

		for (IstanzaTaglio istanzaTaglio : plainSestoSezione.getIstanzi().getIstanziList()) {
			if (istanzaTaglio.getNumIstanza() == null || istanzaTaglio.getNumIstanza().equals(0)) {
				throw new ValidationException();
			}
		}

		if (plainSestoSezione.getNota() == null || plainSestoSezione.getNota().getTesto().length() > 1000) {
			throw new ValidationException();
		}
	}

	private void validateAutPaesaggistica(PlainSestoSezione plainSestoSezione) throws ValidationException {
		boolean flgAutPaesaggistica = plainSestoSezione.getPaesaggistica().isChecked();
		if (flgAutPaesaggistica) {
			if (plainSestoSezione.getPaesaggistica().getDataAutorizzazione() == null
					|| plainSestoSezione.getPaesaggistica().getNumeroAutorizzazione() == null
					|| plainSestoSezione.getPaesaggistica().getNumeroAutorizzazione().length() > 50
					|| plainSestoSezione.getPaesaggistica().getRilasciataAutorizzazione() == null
					|| plainSestoSezione.getPaesaggistica().getRilasciataAutorizzazione().length() > 200) {
				throw new ValidationException();
			}
//		} else {
//			throw new ValidationException();
		}
	}

	private void validateAutVidro(PlainSestoSezione plainSestoSezione) throws ValidationException {
		boolean flgAutVidro = plainSestoSezione.getVincIdrogeologico().isChecked();
		if (flgAutVidro && (plainSestoSezione.getVincIdrogeologico().getDataAutorizzazione() == null
				|| plainSestoSezione.getVincIdrogeologico().getNumeroAutorizzazione() == null
				|| plainSestoSezione.getVincIdrogeologico().getNumeroAutorizzazione().length() > 50
				|| plainSestoSezione.getVincIdrogeologico().getRilasciataAutorizzazione() == null
				|| plainSestoSezione.getVincIdrogeologico().getRilasciataAutorizzazione().length() > 200)) {
			throw new ValidationException();
		}
	}

	private void validateVidroIncidenza(PlainSestoSezione plainSestoSezione) throws ValidationException {
		boolean flgAutVidroIncidenza = plainSestoSezione.getValIncidenza().isChecked();
		if (flgAutVidroIncidenza && (plainSestoSezione.getValIncidenza().getDataAutorizzazione() == null
				|| plainSestoSezione.getValIncidenza().getNumeroAutorizzazione() == null
				|| plainSestoSezione.getValIncidenza().getNumeroAutorizzazione().length() > 50
				|| plainSestoSezione.getValIncidenza().getRilasciataAutorizzazione() == null
				|| plainSestoSezione.getValIncidenza().getRilasciataAutorizzazione().length() > 200)) {
			throw new ValidationException();
		}
	}

	private void validateCompensazione(PlainSestoSezione plainSestoSezione) throws ValidationException {
		if ((plainSestoSezione.getCompFisica().isChecked() && plainSestoSezione.getCompMonetaria().isChecked())
				|| (plainSestoSezione.getCompFisica().isChecked() && !plainSestoSezione.getIstanzi().isChecked())
				|| (plainSestoSezione.getCompMonetaria().isChecked() && plainSestoSezione.getIstanzi().isChecked())) {
			throw new ValidationException();
		}
	}

	private IntervTrasformazione retrieveIntervTrasformazioneForDich(PlainSestoSezione plainSestoSezione) {
		IntervTrasformazione intervTrasformazione = new IntervTrasformazione();

		intervTrasformazione.setFlgProprieta(plainSestoSezione.getProprieta().isOwner() ? (byte) 1 : (byte) 0);
		intervTrasformazione.setFlgDissensi(plainSestoSezione.getDissensi().haDissenso() ? (byte) 1 : (byte) 0);

		byte flgAutPaesaggistica = plainSestoSezione.getPaesaggistica().isChecked() ? (byte) 1 : (byte) 0;
		if (flgAutPaesaggistica == 1) {
			intervTrasformazione.setFlgAutPaesaggistica(flgAutPaesaggistica);
			intervTrasformazione.setDataAutPaesaggistica(plainSestoSezione.getPaesaggistica().getDataAutorizzazione());
			intervTrasformazione.setNrAutPaesaggistica(plainSestoSezione.getPaesaggistica().getNumeroAutorizzazione());
			intervTrasformazione
					.setEnteAutPaesaggistica(plainSestoSezione.getPaesaggistica().getRilasciataAutorizzazione());
		} else {
			intervTrasformazione.setFlgAutPaesaggistica((byte) 0);
			intervTrasformazione.setDataAutPaesaggistica(null);
			intervTrasformazione.setNrAutPaesaggistica(null);
			intervTrasformazione.setEnteAutPaesaggistica(null);
		}

		byte flgAutVidro = plainSestoSezione.getVincIdrogeologico().isChecked() ? (byte) 1 : (byte) 0;
		if (flgAutVidro == 1) {
			intervTrasformazione.setFlgAutVidro(flgAutVidro);
			intervTrasformazione.setDataAutVidro(plainSestoSezione.getVincIdrogeologico().getDataAutorizzazione());
			intervTrasformazione.setNrAutVidro(plainSestoSezione.getVincIdrogeologico().getNumeroAutorizzazione());
			intervTrasformazione
					.setEnteAutVidro(plainSestoSezione.getVincIdrogeologico().getRilasciataAutorizzazione());
		} else {
			intervTrasformazione.setFlgAutVidro((byte) 0);
			intervTrasformazione.setDataAutVidro(null);
			intervTrasformazione.setNrAutVidro(null);
			intervTrasformazione.setEnteAutVidro(null);
		}

		byte flgAutVidroIncidenza = plainSestoSezione.getValIncidenza().isChecked() ? (byte) 1 : (byte) 0;
		if (flgAutVidroIncidenza == 1) {
			intervTrasformazione.setFlgAutIncidenza(flgAutVidroIncidenza);
			intervTrasformazione.setDataAutIncidenza(plainSestoSezione.getValIncidenza().getDataAutorizzazione());
			intervTrasformazione.setNrAutIncidenza(plainSestoSezione.getValIncidenza().getNumeroAutorizzazione());
			intervTrasformazione.setEnteAutIncidenza(plainSestoSezione.getValIncidenza().getRilasciataAutorizzazione());
		} else {
			intervTrasformazione.setFlgAutIncidenza((byte) 0);
			intervTrasformazione.setDataAutIncidenza(null);
			intervTrasformazione.setNrAutIncidenza(null);
			intervTrasformazione.setEnteAutIncidenza(null);
		}

		if (plainSestoSezione.getAltriPareri().isChecked()) {
			intervTrasformazione.setAltriPareri(plainSestoSezione.getAltriPareri().getTesto());
		} else {
			intervTrasformazione.setAltriPareri(null);
		}

		intervTrasformazione.setFlgCauzioneCf(plainSestoSezione.getCompFisica().isChecked() ? (byte) 1 : (byte) 0);
		intervTrasformazione.setFlgVersamentoCm(plainSestoSezione.getCompMonetaria().isChecked() ? (byte) 1 : (byte) 0);
		intervTrasformazione.setNoteDichiarazione(plainSestoSezione.getNota().getTesto());
		
		logger.info("intervTrasformazione.getFlgAutVidro():" + intervTrasformazione.getFlgAutVidro());

		return intervTrasformazione;
	}

	private void documentoAllegatiManipulation(List<FatDocumentoAllegato> documenti, int fkConfigUtente,
			Integer idIntervento, List<TipoAllegatoEnum> tipoAllegati) {

		List<FatDocumentoAllegato> allDocumenti = documentoAllegatoDAO.findDocumentiByIdAndTipos(idIntervento,
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

	private void istanzaCompensazioneManipulation(DichIstanzaTaglio istanzi, int fkConfigUtente, int fkIntervento) {
		
		if (istanzi.isChecked()) {
			for (IstanzaTaglio istanzaTaglio : istanzi.getIstanziList()) {
				if (istanzaTaglio.isDeleted() && istanzaTaglio.getIdIstanza() != null) {
					istanzaCompensazioneDAO.deleteIstanza(istanzaTaglio.getNumIstanza());
				} else if (istanzaTaglio.getIdIstanza() == null) {
					istanzaCompensazioneDAO.insertIstanzaCompensazione(
							mapToIstanzaCompensazione(istanzaTaglio, fkConfigUtente, fkIntervento));
				}
			}
		}
	}

	private IstanzaCompensazione mapToIstanzaCompensazione(IstanzaTaglio istanzaTaglio, int fkConfigUtente,
			int fkIntervento) {
		IstanzaCompensazione istanzaCompensazione = new IstanzaCompensazione();
		istanzaCompensazione.setNumIstanzaCompensazione(istanzaTaglio.getNumIstanza());
		istanzaCompensazione.setDataInserimento(LocalDate.now());
		istanzaCompensazione.setFkConfigUtente(fkConfigUtente);
		istanzaCompensazione.setFkIntervento(fkIntervento);
		istanzaCompensazione.setDataPresentazione(istanzaTaglio.getDataPresentazioneIstanza());
		istanzaCompensazione.setDataAutorizzazione(istanzaTaglio.getDataAutorizzazioneIstanza());
		return istanzaCompensazione;
	}

	private void validateSearchParameters(Map<String, String> queryParams, ConfigUtente configUtente,
			TSoggetto soggetto) throws ValidationException, RecordNotUniqueException {
		validateSearchNullParams(queryParams);

		String annoIstanza = queryParams.get("annoIstanza");
		if (annoIstanza != null && annoIstanza.length() != 4 && !ValidationUtil.isNumber(annoIstanza)) {
			throw new ValidationException();
		}
		validateSearchDates(queryParams);
		//validateSearchComuneAndProvincia(queryParams.get("prov"), queryParams.get("comune"), configUtente, soggetto);
		validateCalcoloEuro(queryParams.get("calcEconDa"), queryParams.get("calcEconA"));

	}

	private void validateSearchNullParams(Map<String, String> queryParams) throws ValidationException {
		boolean allNull = true;
		for (Map.Entry<String, String> entry : queryParams.entrySet()) {
			if (entry.getValue() == null) {
				allNull = false;
			}
		}
		if (allNull) {
			throw new ValidationException();
		}
	}

	private void validateSearchDates(Map<String, String> queryParams) throws ValidationException {
		String dataDaString = queryParams.get("dataPresDa");
		String dataAString = queryParams.get("dataPresA");
		if (dataDaString != null && dataAString != null) {
			LocalDate dataDa = checkAndReturnDate(dataDaString);
			LocalDate dataA = checkAndReturnDate(dataAString);
			if (dataDa.isAfter(dataA)) {
				throw new ValidationException();
			}
		} else if (dataDaString != null) {
			checkAndReturnDate(dataDaString);
		} else if (dataAString != null) {
			checkAndReturnDate(dataAString);
		}
	}

	private LocalDate checkAndReturnDate(String date) throws ValidationException {
		try {
			return LocalDate.parse(date);
		} catch (DateTimeParseException dtpe) {
			throw new ValidationException();
		}
	}

	@Deprecated //il controllo e' piu complesso e avviene tutto su sql
	private void validateSearchComuneAndProvincia(String provincia, String comune, ConfigUtente configUtente,
			TSoggetto soggetto) throws RecordNotUniqueException, ValidationException {
		int ufficioTerProfilo = ProfiloUtenteEnum.UFFICIO_TERRITORIALE.getValue();
		int comuneProfilo = ProfiloUtenteEnum.COMUNE.getValue();
		if (provincia != null && configUtente.getFkProfiloUtente() == ufficioTerProfilo
				&& !provincia.equals(comuneDAO.findComuneResourceByID(soggetto.getFkComune()).getIstatProv())) {
			throw new ValidationException();
		}

		if (comune != null && configUtente.getFkProfiloUtente() == comuneProfilo
				&& soggetto.getFkComune() != Integer.parseInt(comune)) {
			throw new ValidationException();
		}
	}

	private void validateCalcoloEuro(String calcDa, String calcA) throws ValidationException {
		if (calcDa != null && calcA != null) {
			if (!ValidationUtil.isBigDecimal(calcDa) || !ValidationUtil.isBigDecimal(calcA)
					|| BigDecimal.valueOf(Double.parseDouble(calcDa))
							.compareTo(BigDecimal.valueOf(Double.parseDouble(calcA))) > -1) {
				throw new ValidationException();
			}
		} else if (calcDa != null && !ValidationUtil.isBigDecimal(calcDa)) {
			throw new ValidationException();
		} else if (calcA != null && !ValidationUtil.isBigDecimal(calcA)) {
			throw new ValidationException();
		}
	}

	private IntervSelvicolturale retrieveIntervSelvicolturaleForInsert(Integer idGeoPlPfa, ConfigUtente configUtente,
			int idIntervento) {
		IntervSelvicolturale intervSelvicolturale = new IntervSelvicolturale();
		intervSelvicolturale.setIdIntervento(idIntervento);
		intervSelvicolturale.setNrProgressivoInterv(intervSelvicoulturaleDAO.getProssimoNrProgInterv(1)); // mocked
																											// tipoIntervento
		intervSelvicolturale.setIdgeoPlPfa(idGeoPlPfa);
		intervSelvicolturale.setFkConfigUtente(configUtente.getIdConfigUtente());
		intervSelvicolturale.setFlgConformeDeroga(ConformeDerogaEnum.C.toString()); // predefined
		// SET NOT NULL CONSTRAINT FIELDS, ALL MOCKED
		intervSelvicolturale.setFkTipoIntervento(1); // composite
		intervSelvicolturale.setFkConfigIpla(0); // composite
		intervSelvicolturale.setFkStatoIntervento(1);
		intervSelvicolturale.setFkFinalitaTaglio(1);
		intervSelvicolturale.setFkDestLegname(1);
		intervSelvicolturale.setFkFasciaAltimetrica(1);
		intervSelvicolturale.setFlgIntervNonPrevisto((byte) 0);
		intervSelvicolturale.setFlgIstanzaTaglio((byte) 0);
		intervSelvicolturale.setFlgPiedilista((byte) 0);
		intervSelvicolturale.setNoteFinaliRichiedente("---TEST---");

		return intervSelvicolturale;
	}

	@Override
	public void updateIstanzaVerificata(Integer idIntervento, TSoggetto soggetto,ConfigUtente configUtente)
			throws RecordNotFoundException, RecordNotUniqueException {
		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.RICHIEDENTE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.TECNICO_FORESTALE.getValue()) {
		} else {
			throw new IllegalArgumentException("Not allowed to proceed with this action");
		}
		istanzaForestDAO.updateIstanzaTo(idIntervento, null, configUtente.getIdConfigUtente(), InstanceStateEnum.VERIFICATA);
	}

	@Override
	public void updateIstanzaRifiutata(Integer idIntervento, String motivazioneRifiuto, TSoggetto soggetto,ConfigUtente configUtente)
			throws RecordNotFoundException, RecordNotUniqueException {
		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.RICHIEDENTE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.TECNICO_FORESTALE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else {
			throw new IllegalArgumentException("Not allowed to proceed with this action");
		}
		istanzaForestDAO.updateIstanzaTo(idIntervento, motivazioneRifiuto, configUtente.getIdConfigUtente(), InstanceStateEnum.RIFIUTATA);

	}
	
	@Override
	public void updateIstanzaVincoloAutorizzata(AutorizzaVincolo body, ConfigUtente confUtente){
		if(body.getDataFineIntervento() != null) {
			interventoDAO.updateDataFineIntervento(body.getDataFineIntervento(), body.getIdIntervento());
		}
		istanzaForestDAO.updateIstanzaVincoloAutorizzata(body, confUtente);
	}
	
	@Override
	public void updateDataFineIntervento(AutorizzaVincolo body){
		interventoDAO.updateDataFineIntervento(body.getDataFineIntervento(), body.getIdIntervento());
	}

	@Override
	public BoOwnerIstanze getUtenteForIstanza(Integer idIntervento) {
		ConfigUtente cnfUttente = istanzaForestDAO.getUtenteForIstanzaById(idIntervento);

		try {
			TSoggetto sog = soggettoDAO.findSoggettoById(cnfUttente.getFkSoggetto());
			return new BoOwnerIstanze(cnfUttente, sog);
		} catch (RecordNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public GeneratedFileTrasfParameters getCeoIstanza(Integer idIntervento) {

		GeneratedFileTrasfParameters genFileParams = dichiarazioneSummaryDAO.getSummaryTrasformazioni(idIntervento);
		return genFileParams;
	}
	
	@Override
	public GeneratedFileVincoloParameters getCeoIstanzaVincolo(Integer idIntervento) {
		GeneratedFileVincoloParameters genFileParams = dichiarazioneSummaryDAO.getSummaryVincolo(idIntervento);
		return genFileParams;
	}

	@Override
	public void associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body, ConfigUtente loggedConfig)
			throws RecordNotFoundException, RecordNotUniqueException {

		Integer profilutente = loggedConfig.getFkProfiloUtente();
		List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();

		switch (profilutente) {
		case 1:

			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}

			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
			documentoAllegatiManipulation(body.getAllegati(), loggedConfig.getIdConfigUtente(), idIntervento,
					tipoAllegati);
			break;
		case 2:

			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
			documentoAllegatiManipulation(body.getAllegati(), loggedConfig.getIdConfigUtente(), idIntervento,
					tipoAllegati);
			break;
		case 3:

			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
			documentoAllegatiManipulation(body.getAllegati(), loggedConfig.getIdConfigUtente(), idIntervento,
					tipoAllegati);
			break;
		case 4:
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
			documentoAllegatiManipulation(body.getAllegati(), loggedConfig.getIdConfigUtente(), idIntervento,
					tipoAllegati);
			break;
		case 5:
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
			tipoAllegati.add(TipoAllegatoEnum.ALTRO);
			documentoAllegatiManipulation(body.getAllegati(), loggedConfig.getIdConfigUtente(), idIntervento,
					tipoAllegati);
			break;
		case 6:

			throw new IllegalArgumentException("Not allowed to proceed with this action");

		}

	}
	
	public Iterable<String> whenFilterUsingMultiplePredicates_thenFiltered(List<String> names) {
	   // List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
	    Collection<String> result = Collections2.filter(names, 
	      Predicates.or(Predicates.containsPattern("J"), 
	      Predicates.not(Predicates.containsPattern("a"))));
	    return result;
	}
	
	@Override
	public void recalculateParticelleIntervento(Integer idIntervento, String cfUtente, ConfigUtente configUtente) throws ServiceException {
		propcatastoInterventoDAO.deleteAllByIdIntervento(idIntervento);
		
		// 777 la funcion ya fue corregida
		List<String> geometryList = intervTrasfDAO.getGeometrieGmlByFkIntervento(idIntervento);
		
		//String sql1 = "SELECT ST_AsGML(ST_SetSRID(ST_AsText(ST_SetSRID(geometria,4326)),32632)) as geometriagml FROM idf_geo_pl_interv_trasf WHERE fk_intervento = ?";
		//List<String> geometryList =  DBUtil.jdbcTemplate.query(sql1.toString(), new Object[] {idIntervento}, new StringMapper());
		
		System.out.println("Longitud recuperadas y transformadas &&&&: ");
		System.out.println(geometryList.size());
		if(geometryList != null && geometryList.size() > 0) {
			List<InfoParticella>  particelleList = new ArrayList<InfoParticella>();
			//InfoParticella[] particelleList;
			Map<String,InfoParticella> mapParticelle = new HashMap<String,InfoParticella>();
			String key;
			for(String geometryGML : geometryList) {
				logger.info("Forest:: idIntervento: " + idIntervento + " - recalculateParticelleIntervento - getParticelleByGeometry: " + geometryGML );

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


				// 777 geometryGML = GeomUtil.getGeometryGmlExtend(geometryGML);  // ya no se necesitara 
				// 777 logger.info("geom Extend (antes GML): " + geometryGML);
				
				/* 777 particelleList = sigmaterServiceHelper.getParticelleByGeometry(geometryGML, cfUtente);
				if(particelleList != null && particelleList.length > 0) {
					logger.info("Trovare " + particelleList.length + " tramite getParticelleByGeometry");
					for(InfoParticella infoPar : particelleList) {
						key = infoPar.getCodIstatComune()+ "-" + infoPar.getSezione()+ "-" + infoPar.getFoglio() + "-" + infoPar.getNumero();
						if(mapParticelle.get(key) == null) {
							mapParticelle.put(key, infoPar);
						}else {
							mapParticelle.get(key).setArea(mapParticelle.get(key).getArea() + infoPar.getArea());
						}
					}
				}else {
					logger.info("Nessuna particella trovata tramite getParticelleByGeometry");
				}*/

		        // 777 begin
		        try {


					String particellasInter = particella.findParticellaByGML(jsonObject);
					//String particellasInter = particella.findParticellaByGMLTSF(geometryGML);
					System.out.println("Particellas: " + particellasInter);
					ObjectMapper objectMapper77 = new ObjectMapper();
			        JsonNode jsonNode77 = null;
		            try {
						jsonNode77 = objectMapper77.readTree(particellasInter);
						LocalDate fechaHoy = LocalDate.now();

			            JsonNode elementosNode = jsonNode77.get("features");
			            if (elementosNode.isArray()) {
			                for (JsonNode elemento : elementosNode) {
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
					            //double area = calculateArea(convertedGeoJson.toString());
								double divisor = 10000.0;
								//double resultHa = area / divisor;

			                	System.out.println("Elemento: " + elemento);
			                    InfoParticella particella1  = new InfoParticella(
			                    		elemento.get("properties").get("allegato").asText(),
			                    		areaBigDecimalTsf,
			                    		elemento.get("properties").get("cod_com_belfiore").asText(),
			                    		elemento.get("properties").get("cod_com_istat").asText(),
			                    		fechaHoy.toString(),
			                    		elemento.get("properties").get("foglio").asText(),
			                    		converteString,
			                    		elemento.get("properties").get("particella").asText(),
			                    		elemento.get("properties").get("sezione").asText(),
			                    		elemento.get("properties").get("particella").asText());
			                    particelleList.add(particella1);

			                }
			                // 777 begin
		                    // if(particelleList != null && particelleList.size() > 0) {
		    				// 	logger.info("Trovare " + particelleList.size() + " tramite getParticelleByGeometry");
		    				// 	for(InfoParticella infoPar : particelleList) {
		    				// 		key = infoPar.getCodIstatComune()+ "-" + infoPar.getSezione()+ "-" + infoPar.getFoglio() + "-" + infoPar.getNumero();
		    				// 		if(mapParticelle.get(key) == null) {
		    				// 			mapParticelle.put(key, infoPar);
		    				// 		}else {
		    				// 			mapParticelle.get(key).setArea(mapParticelle.get(key).getArea() + infoPar.getArea());
		    				// 		}
		    				// 	}
		    				// }else {
		    				// 	logger.info("Nessuna particella trovata tramite getParticelleByGeometry");
		    				// }
		                    // 777 end
			            }
							////
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
		        // 777 end
			}

			if(particelleList != null && particelleList.size() > 0) {
				logger.info("Trovare " + particelleList.size() + " tramite getParticelleByGeometry");
				for(InfoParticella infoPar : particelleList) {
					key = infoPar.getCodIstatComune()+ "-" + infoPar.getSezione()+ "-" + infoPar.getFoglio() + "-" + infoPar.getNumero();
					if(mapParticelle.get(key) == null) {
						mapParticelle.put(key, infoPar);
					}else {
						mapParticelle.get(key).setArea(infoPar.getArea());
					}
				}
			}
			else {
				logger.info("Nessuna particella trovata tramite getParticelleByGeometry");
				List<String> listCodiceistat = geoPlLottoInterventoDAO.getIntersezioneGeometriaComuneTrasf(idIntervento);

				for(String codIstat : listCodiceistat){
					InfoParticella particellaFinta = new InfoParticella(null,0,null,codIstat,LocalDate.now().toString(),"0",null,"0","_",null);
					PlainParticelleCatastali itemFinto;
					try {
						itemFinto = getPlainParticelleCatastaliFromInfoParticella(particellaFinta, comuneDAO, configUtente);
					} catch (RecordNotUniqueException e) {
						e.printStackTrace();
						throw new ServiceException("RecordNotUniqueException");
					}

					List<PlainParticelleCatastali> existParticella = propCatastoDAO.findParticelleByComuneFoglioParticella(itemFinto.getComune().getIdComune(),itemFinto.getSezione(),itemFinto.getFoglio(),itemFinto.getParticella());
					logger.info("exist particella ------------>"+existParticella != null && existParticella.size()>0);
					if(existParticella != null && existParticella.size()>0){
						itemFinto = existParticella.get(0);
						itemFinto.setId(itemFinto.getIdGeoPlPropCatasto());
					} else {
						itemFinto = propCatastoDAO.insertParticella(itemFinto);
					}

					Double areaIntersect = intervTrasfDAO.getAreaItersecWithParticella(idIntervento, itemFinto.getIdGeoPlPropCatasto());
					itemFinto.setSupIntervento(new BigDecimal(areaIntersect / 10000));// da metri a ettari
					List<PropcatastoIntervento> exist = propcatastoInterventoDAO.getPropcatastoByIdInterventoAndByIdGeo(idIntervento,itemFinto.getIdGeoPlPropCatasto());
					if(exist.isEmpty()){
						wrapperInterventoDAO.insertParticlesInPropcatastoIntervento(idIntervento, itemFinto, configUtente);
					}

				}
			}

			PlainParticelleCatastali item;
			Double areaIntersect = null;
			int i = 1;
			//for(InfoParticella infoPar : mapParticelle.values()) {
			for(InfoParticella infoPar : particelleList) {
				try {

					item = getPlainParticelleCatastaliFromInfoParticella(infoPar, comuneDAO, configUtente);
					//PlainParticelleCatastali finallyItem = propCatastoDAO.insertParticella(item);
					//intervTrasfDAO.addGeometria(idIntervento, item.getGeometry().toString()); // 777 .getGeometriaGML());
					// System.out.println("getPlainParticelleCatastaliFromInfoParticella: OK");
					// System.out.println("getPlainParticelleCatastaliFromInfoParticella: OK"+ finallyItem.toString());
				} catch (RecordNotUniqueException e) {
					e.printStackTrace();
					throw new ServiceException("RecordNotUniqueException");
				}
				logger.info("Trovato particella----->: " + objectToString(item));

	

				if(item.getComune()!=null) {
					item = propCatastoDAO.insertParticella(item);
					StringBuilder sqlBonificaGeom1 = new StringBuilder();

					sqlBonificaGeom1.append("update idf_geo_pl_prop_catasto set geometria = ST_CollectionHomogenize(ST_MakeValid(geometria)) where idgeo_pl_prop_catasto = ?");
					DBUtil.jdbcTemplate.update(sqlBonificaGeom1.toString(), item.getIdGeoPlPropCatasto());

					StringBuilder sqlBonificaGeom2 = new StringBuilder();

					sqlBonificaGeom2.append("update idf_geo_pl_prop_catasto set geometria = ST_CollectionExtract(geometria, 3) where idgeo_pl_prop_catasto = ? and ST_GeometryType(geometria) = 'ST_GeometryCollection'");
					DBUtil.jdbcTemplate.update(sqlBonificaGeom2.toString(), item.getIdGeoPlPropCatasto());

					StringBuilder sqlBonificaGeom3 = new StringBuilder();

					sqlBonificaGeom3.append("update idf_geo_pl_prop_catasto a set geometria = (select ST_Union(geometria) from(select (ST_Dump(a.geometria)).geom as geometria) x where ST_Area(geometria) >= 1 ) where idgeo_pl_prop_catasto = ? and ST_GeometryType(geometria) = 'ST_MultiPolygon'");
					DBUtil.jdbcTemplate.update(sqlBonificaGeom3.toString(), item.getIdGeoPlPropCatasto());
				}
				logger.info("Trovato particella (bosco): " + objectToString(item));
				//item = propCatastoDAO.insertParticella(item);
				//inserire su item il la superficie effettivamente oggetto di intervento 
				//calcolata come intersezione della particella con la geom dell'intervento su idf_geo_pl_interv_trasf

				if(item.getIdGeoPlPropCatasto()!=null) {

					areaIntersect = intervTrasfDAO.getAreaItersecWithParticella(idIntervento,item.getIdGeoPlPropCatasto());
					if (areaIntersect >= 1) {
						item.setSupIntervento(new BigDecimal(areaIntersect / 10000));// da metri a ettari
						wrapperInterventoDAO.insertParticlesInPropcatastoIntervento(idIntervento, item, configUtente);
					} else {
						StringBuilder sqldeletecatasto1 = new StringBuilder();
						sqldeletecatasto1.append("delete from idf_geo_pl_prop_catasto where idgeo_pl_prop_catasto=?");
						//logger.info("<---- sqldeletecatasto" + sqldeletecatasto.toString());
						DBUtil.jdbcTemplate.update(sqldeletecatasto1.toString(),item.getIdGeoPlPropCatasto());

						List<PropcatastoIntervento> propcatastoInterventoList = propcatastoInterventoDAO.getAllPropcatastoByIdIntervento(idIntervento);
						if(propcatastoInterventoList.isEmpty() && particelleList.size() == i) {
							List<String> listCodiceistat = geoPlLottoInterventoDAO.getIntersezioneGeometriaComuneTrasf(idIntervento);

							for (String codIstat : listCodiceistat) {
								InfoParticella particellaFinta = new InfoParticella(null, 0, null, codIstat, LocalDate.now().toString(), "0", null, "0", "_", null);
								PlainParticelleCatastali itemFinto;
								try {
									itemFinto = getPlainParticelleCatastaliFromInfoParticella(particellaFinta, comuneDAO, configUtente);
								} catch (RecordNotUniqueException e) {
									e.printStackTrace();
									throw new ServiceException("RecordNotUniqueException");
								}

								List<PlainParticelleCatastali> existParticella = propCatastoDAO.findParticelleByComuneFoglioParticella(itemFinto.getComune().getIdComune(), itemFinto.getSezione(), itemFinto.getFoglio(), itemFinto.getParticella());
								logger.info("exist particella ------------>" + existParticella != null && existParticella.size() > 0);
								if (existParticella != null && existParticella.size() > 0) {
									itemFinto = existParticella.get(0);
									itemFinto.setId(item.getIdGeoPlPropCatasto());
								} else {
									itemFinto = propCatastoDAO.insertParticella(itemFinto);
								}

								areaIntersect = intervTrasfDAO.getAreaItersecWithParticella(idIntervento, itemFinto.getIdGeoPlPropCatasto());
								itemFinto.setSupIntervento(new BigDecimal(areaIntersect / 10000));// da metri a ettari
								List<PropcatastoIntervento> exist = propcatastoInterventoDAO.getPropcatastoByIdInterventoAndByIdGeo(idIntervento,itemFinto.getIdGeoPlPropCatasto());
								if(exist.isEmpty()){
									wrapperInterventoDAO.insertParticlesInPropcatastoIntervento(idIntervento, itemFinto, configUtente);
								}
							}
						}
					}
				}


				// if(item.getIdGeoPlPropCatasto()!=null) {
					//areaIntersect = intervTrasfDAO.getAreaItersecWithParticella(idIntervento,item.getIdGeoPlPropCatasto());
				// } 
				//areaIntersect = intervTrasfDAO.getAreaItersecWithParticella(idIntervento, item.getIdGeoPlPropCatasto());
				System.out.println("areaIntersect: " + areaIntersect.toString());
				// if(areaIntersect >= 1) {
				// 	System.out.println("Intersec: ok");
				// 	item.setSupIntervento(new BigDecimal(areaIntersect/10000));//da metri a ettari
				// 	wrapperInterventoDAO.insertParticlesInPropcatastoIntervento(idIntervento, item, configUtente);
				// } else {
				// 	System.out.println("Intersec: fail");
				// }
				i++;
			}
		}
	}
	
	public static PlainParticelleCatastali getPlainParticelleCatastaliFromInfoParticella(InfoParticella infoPar, ComuneDAO comuneDAO, ConfigUtente configUtente) throws RecordNotUniqueException {
		PlainParticelleCatastali item = new PlainParticelleCatastali();
		item.setComune(comuneDAO.findComuneResourceByIstatComune(infoPar.getCodIstatComune()));
		item.setSezione(infoPar.getSezione());
		item.setFoglio(Integer.parseInt(infoPar.getFoglio()));
		item.setParticella(infoPar.getNumero());
		item.setFkConfigUtente(configUtente.getIdConfigUtente());
		item.setSupCatastale(new BigDecimal(infoPar.getArea()/10000,MathContext.DECIMAL64));
		item.setGeometry(infoPar.getGeometriaGML());
		return item;
	}

	@Override
	@Transactional
	public void deleteIntervento(Integer idIntervento) {
		List<Integer> idsDocs = documentoAllegatoDAO.getAllDocumentsByIdIntervento(idIntervento);
		for(int id : idsDocs) {
			try {
			documentoAllegatoDAO.deleteDocumentoById(id);
			}catch(Exception ex){
				logger.info("Errore durante delete documenti id: " + id);
			}
		}
		skOkTrasfDAO.deleteByIdIntervento(idIntervento);
		soggettoInterventoDAO.deleteByIdIntervento(idIntervento);
		istanzaForestDAO.deleteById(idIntervento);
		geoLnLottoInterventoDAO.deleteGeoLnLottoIntervento(idIntervento);
		geoPlLottoInterventoDAO.deleteGeoPlLottoIntervento(idIntervento);
		geoPtLottoInterventoDAO.deleteGeoPtLottoIntervento(idIntervento);
		propcatastoInterventoDAO.deleteAllByIdIntervento(idIntervento);
		intervTrasfDAO.deleteIntervTrasfByFkIntervento(idIntervento);
		paramtrasfTrasformazionDAO.deleteByIdIntervento(idIntervento);
		istanzaCompensazioneDAO.deleteIstanzaByIntervento(idIntervento);
		intervTrasformazioneDAO.deleteByIdIntervento(idIntervento);
		interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);
		interventoCatforDAO.deleteInterventosById(idIntervento);
		interventoPopSemeDAO.deleteInterventosByIdIntervento(idIntervento);
		interventoRn2000DAO.deleteInterventosByIdIntervento(idIntervento);
		intervSelvicoulturaleDAO.deleteIntervSelvicolturale(idIntervento);

		List<IstanzaTaglio> allIstanzi = mapToIstanziTaglioList(
				istanzaCompensazioneDAO.getAllByFkIntervento(idIntervento));
		for (IstanzaTaglio istanzaTaglio : allIstanzi) {
			istanzaCompensazioneDAO.deleteIstanza(istanzaTaglio.getIdIstanza());
		}
		interventoDAO.deleteIntervento(idIntervento);
	}

	@Override
	public IstanzaForest getIstanzaInviata(Integer idIntervento) {
		
		
		
		IstanzaForest istanza = istanzaForestDAO.getByIdIntervento(idIntervento);
		Intervento intervento = interventoDAO.findInterventoByIdIntervento(idIntervento);
		istanza.setDescrStatoIntervento(intervPfaDAO.getStatoInterventoByIdIntervento(idIntervento));
		istanza.setDataFineIntervento(intervento.getDataFineIntervento());
		TSoggetto sogCompilatore = interventoDAO.getUserCompilatoreByIdIntervento(idIntervento);
		istanza.setUtenteCompilatore(sogCompilatore.getCognome() + " " + sogCompilatore.getNome() + " - " + sogCompilatore.getCodiceFiscale());
		if(istanza.getFkConfigUtente() !=  null) {
			ConfigUtente confUtente = configUtenteDAO.getCofigUtenteById(istanza.getFkConfigUtente());
			TSoggetto sogApprovatore = soggettoDAO.findSoggettoById(confUtente.getFkSoggetto());
			istanza.setUtenteApprovatore(sogApprovatore.getCognome() + " " + sogApprovatore.getNome());
		}
		return istanza;
		
		
		/*
		IstanzaForest istanza = istanzaForestDAO.getByIdIntervento(idIntervento);
		try {
			
			logger.info("<-----getIstanzaInviata istanza "+ istanza+" Intervento: "+idIntervento+ "-->");
			Intervento intervento = interventoDAO.findInterventoByIdIntervento(idIntervento);
			logger.info("<-----getIstanzaInviata intervento "+ intervento+" ----");
			IntervSelvicolturale interv_selvicolt = intervSelvicoulturaleDAO.findInterventoSelvicolturale(idIntervento);
			logger.info("<-----getIstanzaInviata interv_selvicolt "+ interv_selvicolt+" ----");
			
			if(intervento.getDataFineIntervento()!=null)
					 {
					istanza.setDataFineIntervento(intervento.getDataFineIntervento());
			}
			// 777 AbiSoft
			//istanza.setNrIntervento(intervento.getIdIntervento());
			//istanza.setFkStatoIstanza(istanza.getFkStatoIstanza());
			logger.info("<----- interv_selvicolt "+ interv_selvicolt.getIdIntervento());
			logger.info("<----- interv_selvicolt "+ interv_selvicolt.getNrProgressivoInterv());
			logger.info("<----- interv_selvicolt "+ interv_selvicolt.getFkStatoIntervento());
			
			istanza.setNrIntervento(interv_selvicolt.getNrProgressivoInterv());
			logger.info("<----- interv_selvicolt "+ interv_selvicolt.getFkStatoIntervento());
			istanza.setFkStatoIntervento(interv_selvicolt.getFkStatoIntervento());
			logger.info("<----- interv_selvicolt "+ interv_selvicolt.getCodStatoIntervento());
			istanza.setCodStatoIntervento(interv_selvicolt.getCodStatoIntervento());
			logger.info("<----- interv_selvicolt "+ interv_selvicolt.getDescrStatoIntervento());
			istanza.setDescrStatoIntervento(interv_selvicolt.getDescrStatoIntervento());
			// 777
			TSoggetto sogCompilatore = interventoDAO.getUserCompilatoreByIdIntervento(idIntervento);
			istanza.setUtenteCompilatore(sogCompilatore.getCognome() + " " + sogCompilatore.getNome() + " - " + sogCompilatore.getCodiceFiscale());
			if(istanza.getFkConfigUtente() !=  null) {
				ConfigUtente confUtente = configUtenteDAO.getCofigUtenteById(istanza.getFkConfigUtente());
				TSoggetto sogApprovatore = soggettoDAO.findSoggettoById(confUtente.getFkSoggetto());
				istanza.setUtenteApprovatore(sogApprovatore.getCognome() + " " + sogApprovatore.getNome());
			}
		}
		catch(Exception e) {
			logger.info("<------------ERROR getIstanzaInviata");
			e.printStackTrace();
			throw e;
			
		}
		
		return istanza;
		*/
	}

	@Override
	public PaginatedList<PlainSoggettoIstanzaTagli> backOfficeTagliSearch(Map<String, String> queryParams, ConfigUtente configUtente)
					throws ParseException, RecordNotUniqueException, ValidationException {

			TSoggetto soggetto;
			try {
				soggetto = soggettoDAO.findSoggettoById(configUtente.getFkSoggetto());
				validateSearchParameters(queryParams, configUtente, soggetto);
				return istanzaForestDAO.backOfficeTagliSearch(queryParams,configUtente);
			} catch (RecordNotFoundException e) {
				throw new ValidationException();
			} finally {
			}
	}
}
