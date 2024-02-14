/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;

import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import it.csi.idf.idfapi.business.be.exceptions.CustomValidator;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.CategoriaForestaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ComuneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigurationDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DelegaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DichiarazioneSummaryDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DocumentoAllegatoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.GovernoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasformazioneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoAappDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoCatforDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoPopSemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoRn2000DAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaCompensazioneDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaForestDAO;
import it.csi.idf.idfapi.business.be.impl.dao.MacroCatforDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ParametroApplDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ParametroTrasfDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ParamtrasfTrasformazionDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PfPgDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PopolamentoSemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropCatastoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.PropcatastoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SupForestaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoForestaleDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperInterventoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperIstanzaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ZonaAltimetricaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.impl.ParticellaDAOImpl;
import it.csi.idf.idfapi.business.be.service.GSAREPORT;
import it.csi.idf.idfapi.business.be.service.RicadenzaService;
import it.csi.idf.idfapi.business.be.service.helper.IndexServiceHelper;
import it.csi.idf.idfapi.business.be.service.helper.SigmaterServiceHelper;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfGeoPlIntervVincidroDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfRIntervincidroGovernoDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfTIntervVincIdroDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.SkOkVincoloDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.impl.IdfGeoPlIntervVincidroDaoImpl;
import it.csi.idf.idfapi.business.be.vincolo.pojo.CauzioneVincoloDTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfGeoPlIntervVincidro;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfRIntervincidroGoverno;
import it.csi.idf.idfapi.business.be.vincolo.pojo.IdfTIntervVincIdro;
import it.csi.idf.idfapi.business.be.vincolo.pojo.Superfici;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloHeading;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep1DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep2DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep3DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep4DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep5DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloStep6DTO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloSubHeading;
import it.csi.idf.idfapi.business.be.vincolo.service.WrapperVincoloStepsDao;
import it.csi.idf.idfapi.dto.BOSearchResult;
import it.csi.idf.idfapi.dto.BoOwnerIstanze;
import it.csi.idf.idfapi.dto.BoscoHeadings;
import it.csi.idf.idfapi.dto.BoscoSubHeadings;
import it.csi.idf.idfapi.dto.CategoriaForestale;
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
import it.csi.idf.idfapi.dto.DocumentoAllegato;
import it.csi.idf.idfapi.dto.FatDocumentoAllegato;
import it.csi.idf.idfapi.dto.FileUploadForm;
import it.csi.idf.idfapi.dto.GeneratedFileTrasfParameters;
import it.csi.idf.idfapi.dto.Governo;
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
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PlainSestoSezione;
import it.csi.idf.idfapi.dto.PlainSezioneId;
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
import it.csi.idf.idfapi.dto.TipoParametroTrasf;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.AmbitoIstanzaEnum;
import it.csi.idf.idfapi.util.Constants;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.HeadingsDescriptionEnum;
import it.csi.idf.idfapi.util.InstanceStateEnum;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.PlPfaEnum;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.SoggettoTypeEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.SuperficieCompensationEnum;
import it.csi.idf.idfapi.util.TipoAccreditamentoEnum;
import it.csi.idf.idfapi.util.TipoAllegatoEnum;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;
import it.csi.idf.idfapi.util.TipoParametroApplEnum;
import it.csi.idf.idfapi.util.TipoTitolaritaEnum;
import it.csi.idf.idfapi.util.ValidationUtil;
import it.csi.idf.idfapi.util.VincIdroEnum;
import it.csi.idf.idfapi.util.service.integration.sigmater.sigwgssrv.InfoParticella;

@Component
public class WrapperVincoloStepsDaoImpl implements WrapperVincoloStepsDao {

	public static Logger logger = Logger.getLogger(SpringSupportedResource.class);
	private static final String SEZIONE_1_VALID_MSG = "Sezione 1 deve essere completata";
	private static final String SEZIONE_2_VALID_MSG = "Sezione 2 deve essere completata";
	private static final String SEZIONE_3_VALID_MSG = "Sezione 3 deve essere completata";
	private static final String SEZIONE_4_VALID_MSG = "Sezione 4 deve essere completata";
	private static final String SEZIONE_5_VALID_MSG = "Sezione 5 deve essere completata";
	

    
	@Autowired
	private CategoriaForestaleDAO categoriaForestaleDAO;

	@Autowired
	private ComuneDAO comuneDAO;
	
	@Autowired
	private DelegaDAO delegaDao;
	
	@Autowired
	private ConfigurationDAO configurationDAO;
	
	@Autowired
	ParticellaDAOImpl particella;
	
	@Autowired
	private ConfigUtenteDAO configUtenteDAO;

	@Autowired
	private DichiarazioneSummaryDAO dichiarazioneSummaryDAO;

	@Autowired
	private DocumentoAllegatoDAO documentoAllegatoDAO;

	@Autowired
	private GovernoDAO governoDAO;

	@Autowired
	private GSAREPORT gsareport;

	@Autowired
	private IdfRIntervincidroGovernoDAO idfRIntervincidroGovernoDAO;

	@Autowired
	private IdfTIntervVincIdroDAO idfTIntervVincIdroDAO;

	@Autowired
	private InterventoAappDAO interventoAappDAO;

	@Autowired
	private InterventoCatforDAO interventoCatforDAO;

	@Autowired
	private InterventoDAO interventoDAO;

	@Autowired
	private InterventoPopSemeDAO interventoPopSemeDAO;

	@Autowired
	private InterventoRn2000DAO interventoRn2000DAO;

	@Autowired
	private IntervTrasformazioneDAO intervTrasformazioneDAO;

	@Autowired
	private IstanzaCompensazioneDAO istanzaCompensazioneDAO;

	@Autowired
	private IstanzaForestDAO istanzaForestDAO;

	@Autowired
	private MacroCatforDAO macroCatforDAO;

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
	private RicadenzaService ricadenzaService;

	@Autowired
	private SigmaterServiceHelper sigmaterServiceHelper;

	@Autowired
	private SkOkVincoloDAO skOkVincoloDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private SoggettoInterventoDAO soggettoInterventoDAO;

	@Autowired
	private SupForestaleDAO supForestaleDAO;

	@Autowired
	private TipoForestaleDAO tipoForestaleDAO;

	@Autowired
	private WrapperInterventoDAO wrapperInterventoDAO;

	@Autowired
	private ZonaAltimetricaDAO zonaAltimetricaDAO;
	
	@Autowired
	private IdfGeoPlIntervVincidroDAO idfGeoPlIntervVincidroDAO;
	
	@Autowired
	private IdfGeoPlIntervVincidroDaoImpl idfGeoPlIntervVincidroDaoImpl;
	
	@Autowired
	private WrapperIstanzaDAO wrapperIstanzaDAO;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	IntervTrasfDAO intervTrasfDAO;

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

	@Override
	public void associareDocumenti(Integer idIntervento, ProfessionistaElencoAllegati body, ConfigUtente loggedConfig)
			throws RecordNotFoundException, RecordNotUniqueException {
		// TODO Auto-generated method stub

	}

	private void associateNaturalLegalPerson(int compilerSoggettoConfigUtente, VincoloStep1DTO body, int idSoggetto,
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

	@Override
	public PaginatedList<BOSearchResult> backOfficeSearch(Map<String, String> queryParams, ConfigUtente configUtente)
			throws ParseException, RecordNotUniqueException, ValidationException {

		TSoggetto soggetto;
		try {
			soggetto = soggettoDAO.findSoggettoById(configUtente.getFkSoggetto());
			validateSearchParameters(queryParams, configUtente, soggetto);
			return istanzaForestDAO.backOfficeSearch(queryParams, configUtente);
		} catch (RecordNotFoundException e) {
			throw new ValidationException();
		} finally {
		}

	}

	private LocalDate checkAndReturnDate(String date) throws ValidationException {
		try {
			return LocalDate.parse(date);
		} catch (DateTimeParseException dtpe) {
			throw new ValidationException();
		}
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

	@Override // segnalibro: ancora da fare.
	@Transactional
	public void deleteIntervento(Integer idIntervento) {
	
		  List<Integer> idsDocs =
		  documentoAllegatoDAO.getAllDocumentsByIdIntervento(idIntervento); 
		  for(int id : idsDocs) 
		  { 
			  try { 
				  documentoAllegatoDAO.deleteDocumentoById(id);
			  }catch(Exception ex)
			  	{ logger.info("Errore durante delete documenti id: " + id); 
			  } 
		  } 
		  propcatastoInterventoDAO.deleteAllByIdIntervento(idIntervento);
		  soggettoInterventoDAO.deleteByIdIntervento(idIntervento);
		  istanzaForestDAO.deleteById(idIntervento);		  
		  idfRIntervincidroGovernoDAO.deleteByIdIntervento(idIntervento);
		  idfGeoPlIntervVincidroDaoImpl.deleteByFkIntervento(idIntervento);
		  skOkVincoloDAO.deleteByIdIntervento(idIntervento);
		  idfTIntervVincIdroDAO.deleteById(idIntervento);
		  interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);
		  interventoCatforDAO.deleteInterventosById(idIntervento);
		  interventoPopSemeDAO.deleteInterventosByIdIntervento(idIntervento);
		  interventoRn2000DAO.deleteInterventosByIdIntervento(idIntervento);
		  interventoDAO.deleteIntervento(idIntervento);
		 
	}

	@Override
	public boolean deleteStepById(Integer idIntervento, Integer stepNum) {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public boolean exists(VincoloStep1DTO body) {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(VincoloStep2DTO body) {
		// TODO Auto-generated method stub ?? is this for something or...
		return false;
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

	@Override
	public List<VincoloStep1DTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private BigDecimal getBaseCalculation(int idIntervento) {
		ParametroAppl parametroAppl = parametroApplDAO.getParamertroByTipo(TipoParametroApplEnum.BASE_CALCOLO_EURO);
		return BigDecimal.valueOf(parametroAppl.getParametroApplNum())
				.multiply(interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata());
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

	@Override
	public GeneratedFileTrasfParameters getCeoIstanza(Integer idIntervento) {

		GeneratedFileTrasfParameters genFileParams = dichiarazioneSummaryDAO.getSummaryTrasformazioni(idIntervento);
		return genFileParams;
	}

	@Override
	public InvioIstanzaDTO getDataInvioIstanza(Integer idIntervento) {
		return new InvioIstanzaDTO(istanzaForestDAO.getByIdIntervento(idIntervento).getDataInvio());
	}

	private VincoloSubHeading getFilteredCheckedSubHeadings(VincoloHeading heading) {
		if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.D.toString())
				|| heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.E.toString())) {
			BigDecimal maxValue = BigDecimal.ZERO;
			VincoloSubHeading responseSubHeading = null;

			for (VincoloSubHeading subHeading : heading.getSubheadings()) {
				if (subHeading.isChecked() && maxValue.compareTo(new BigDecimal(subHeading.getValore())) < 0) {
					maxValue = new BigDecimal(subHeading.getValore());
					responseSubHeading = subHeading;
				}
			}
			return responseSubHeading;
		} else {
			for (VincoloSubHeading subHeading : heading.getSubheadings()) {
				if (subHeading.isChecked()) {
					return subHeading;
				}
			}
		}
		return null;
	}

	private TSoggetto getFkSoggettoProfess(Integer idIntervento) throws RecordNotFoundException {
		int fkSoggettoProfess = interventoDAO.findInterventoByIdIntervento(idIntervento).getFkSoggettoProfess();
		if (fkSoggettoProfess != 0) {
			// DM removeAdressInfo(soggettoDAO.findSoggettoById(fkSoggettoProfess));
			return soggettoDAO.findSoggettoById(fkSoggettoProfess);
		}
		return null;
	}

	private String getGeometryGmlExtend(String geom) {
		geom = geom.replace("srsName=\"EPSG:32632\"",
				"srsName=\"EPSG:32632\" xmlns:gml=\"http://www.opengis.net/gml\"");
		geom = geom.replace("<gml:coordinates", "<gml:coordinates decimal=\".\" cs=\",\" ts=\" \"");
		return geom;
	}

	private IstanzaForest getIstanzaForest(int compilerSoggettoConfigUtente, VincoloStep1DTO body, int idIntervento) {
		IstanzaForest istanzaForest = new IstanzaForest();

		istanzaForest.setIdIstanzaIntervento(idIntervento);
		istanzaForest.setDataInserimento(LocalDate.now());
		istanzaForest.setFkStatoIstanza(InstanceStateEnum.BOZZA.getStateValue());
		istanzaForest.setFkTipoIstanza(body.getTipoIstanzaId());
		istanzaForest.setNrIstanzaForestale(istanzaForestDAO.getNumberOfInstanceType(body.getTipoIstanzaId()) + 1);
		istanzaForest.setFkConfigUtente(compilerSoggettoConfigUtente);
		return istanzaForest;
	}

	private IdfTIntervVincIdro getIstanzaVincolo(int compilerSoggettoConfigUtente, VincoloStep1DTO body,
			int idIntervento) {
		IdfTIntervVincIdro idfTIntervVincIdro = new IdfTIntervVincIdro();
		idfTIntervVincIdro.setIdIntervento(new BigDecimal(idIntervento));
		if(body.getFkInterventoPadreProroga() != null) {
			idfTIntervVincIdro.setFkInterventoPadreProroga(new BigDecimal(body.getFkInterventoPadreProroga()));
		}
		if(body.getFkInterventoPadreVariante() != null) {
			idfTIntervVincIdro.setFkInterventoPadreVariante(new BigDecimal(body.getFkInterventoPadreVariante()));
		}
		idfTIntervVincIdro.setDataInserimento(LocalDate.now());
		// idfTIntervVincIdro.setDataInserimento(new Date(System.currentTimeMillis()));
		idfTIntervVincIdro.setFkTipoIntervento(new BigDecimal(0));
		idfTIntervVincIdro.setFkConfigUtente(compilerSoggettoConfigUtente);
		return idfTIntervVincIdro;
	}

	private int getLocationSubHeadingId(Integer idIntervento) {
		return zonaAltimetricaDAO.getZonaAltimentricaByIdIntervento(idIntervento);
	}

	@Override
	public StepNumber getNumberOfNextStep(Integer idIntervento) {
		return new StepNumber(skOkVincoloDAO.sumFlgSeziones(idIntervento));
	}

	private PlainParticelleCatastali getPlainParticelleCatastaliFromInfoParticella(InfoParticella infoPar,
			ConfigUtente configUtente) throws RecordNotUniqueException {
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

	@Override
	public VincoloStep1DTO getStep1(Integer idIntervento) throws RecordNotFoundException, RecordNotUniqueException {
		
	
		VincoloStep1DTO vincoloStep1DTO = new VincoloStep1DTO();

		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
		ConfigUtente cnfUtenteIntervento = configUtenteDAO.getCofigUtenteById(soggetoIntervento.getFkConfigUtente());

		TSoggetto soggetto = soggettoDAO.findSoggettoById(soggetoIntervento.getIdSoggetto());
		if(cnfUtenteIntervento.getFkTipoAccreditamento() == 2) {//professionista
			vincoloStep1DTO.setPersonaDatiOption(StringUtils.isEmpty(soggetto.getDenominazione())?TipoTitolaritaEnum.PF:TipoTitolaritaEnum.PG);
		}else {
			vincoloStep1DTO.setPersonaDatiOption(StringUtils.isEmpty(soggetto.getDenominazione())?TipoTitolaritaEnum.RF:TipoTitolaritaEnum.RG);
		}
		logger.info("Tipo accreditamento: " + (cnfUtenteIntervento.getFkTipoAccreditamento() == 2 ?"Professionista":"Richiedente") 
				+ " - PersonaDatiOption: " + vincoloStep1DTO.getPersonaDatiOption());
		
		vincoloStep1DTO.setIdSoggetto(soggetto.getIdSoggetto());
		vincoloStep1DTO.setCodiceFiscale(soggetto.getCodiceFiscale());
		vincoloStep1DTO.setNome(soggetto.getNome());
		vincoloStep1DTO.setCognome(soggetto.getCognome());
		vincoloStep1DTO.setDenominazione(soggetto.getDenominazione());
		vincoloStep1DTO.setIndirizzo(soggetto.getIndirizzo());
		vincoloStep1DTO.setCivico(soggetto.getCivico());
		vincoloStep1DTO.setCap(soggetto.getCap());
		vincoloStep1DTO.setNrTelefonico(soggetto.getNrTelefonico());
		vincoloStep1DTO.seteMail(soggetto.geteMail());
		vincoloStep1DTO.setPartitaIva(soggetto.getPartitaIva());

		vincoloStep1DTO.setPec(soggetto.getPec());

		TSoggetto ownerSoggetto = soggettoDAO.getSoggettoPfByPg(soggetto.getIdSoggetto());
		if (ownerSoggetto != null)
			vincoloStep1DTO.setOwnerCodiceFiscale(ownerSoggetto.getCodiceFiscale());

		if (soggetto.getFkComune() != null)
			vincoloStep1DTO.setComune(comuneDAO.findComuneResourceByID(soggetto.getFkComune()));

		IstanzaForest istanzaForest = istanzaForestDAO.getByIdIntervento(idIntervento);

		vincoloStep1DTO.setTipoIstanzaId(istanzaForest.getFkTipoIstanza());
		vincoloStep1DTO.setTipoIstanzaDescr(TipoIstanzaEnum.getEnum(istanzaForest.getFkTipoIstanza()).toString());

		return vincoloStep1DTO;
	}

	@Override
	public VincoloStep2DTO getStep2(Integer idIntervento) throws RecordNotUniqueException, ValidationException {

		if (!skOkVincoloDAO.isFlgSez1Done(idIntervento))
			throw new ValidationException(SEZIONE_1_VALID_MSG);

		CustomValidator.getValidator(HttpStatus.CONFLICT)
				.errorIf("Sezione1", !skOkVincoloDAO.isFlgSez1Done(idIntervento), SEZIONE_1_VALID_MSG).go();

		BigDecimal superficie = interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata();

		VincoloStep2DTO vincoloStep2DTO = new VincoloStep2DTO();
		vincoloStep2DTO.setTotaleSuperficieCatastale(superficie);
		vincoloStep2DTO.setTotaleSuperficieTrasf(superficie);

		List<RicadenzaInformazioni> ricadenzaNatura2000 = retrieveRicadenzaNatura2000Sic(idIntervento);
		ricadenzaNatura2000.addAll(retrieveRicadenzaNatura2000Zps(idIntervento));

		vincoloStep2DTO.setRicadenzaAreeProtette(retrieveRicadenzaAreeProtette(idIntervento));
		vincoloStep2DTO.setRicadenzaNatura2000(ricadenzaNatura2000);
		vincoloStep2DTO.setRicadenzaPopolamentiDaSeme(retrieveRicadenzaPopolamentiDaSeme(idIntervento));
		vincoloStep2DTO.setRicadenzaCategorieForestali(retrieveRicadenzaCategorieForestali(idIntervento));

		IntervTrasformazione intervTrasformazione = intervTrasformazioneDAO.getIntervTrasfById(idIntervento);

		if (intervTrasformazione != null)
			vincoloStep2DTO.setRicadenzaVincoloIdrogeologico(intervTrasformazione.getFlgVincIdro() == 1);

		List<PropCatasto> propCatastos = propCatastoDAO.getPropCatastosByPropcatastoIntervento(
				propcatastoInterventoDAO.getAllPropcatastoByIdIntervento(idIntervento));

		vincoloStep2DTO.setParticelleCatastali(mapPropCatastosToParticelleCatastali(propCatastos));

		if (!propCatastos.isEmpty())
			vincoloStep2DTO.setAnnotazioni(propCatastos.get(0).getNote());

		return vincoloStep2DTO;
	}

	@Override
	public Superfici getSuperfici(Integer idIntervento) throws RecordNotUniqueException, ValidationException {

		return getSuperfici(idIntervento, propcatastoInterventoDAO, idfTIntervVincIdroDAO);
	}
	
	public static Superfici getSuperfici(Integer idIntervento, PropcatastoInterventoDAO  propcatastoInterventoDAO, IdfTIntervVincIdroDAO idfTIntervVincIdroDAO) throws RecordNotUniqueException, ValidationException {
		Superfici superfici = new Superfici();
		superfici.setTotaleSuperficieCatastale((new BigDecimal(propcatastoInterventoDAO.getAreaCatastaleByIdIntervento(idIntervento))).setScale(2, RoundingMode.HALF_DOWN));
		superfici.setTotaleSuperficieTrasf((new BigDecimal(idfTIntervVincIdroDAO.getAreaTrasformazioneByFkIntervento(idIntervento))).divide(BigDecimal.valueOf(10000)).setScale(4, RoundingMode.HALF_DOWN));
		superfici.setTotaleSuperficieBoscata((new BigDecimal(idfTIntervVincIdroDAO.getAreaSuperficieBoscataByFkIntervento(idIntervento))).divide(BigDecimal.valueOf(10000)).setScale(4, RoundingMode.HALF_DOWN));
		superfici.setTotaleSuperficieNonBoscata(superfici.getTotaleSuperficieTrasf().subtract(superfici.getTotaleSuperficieBoscata()).setScale(4, RoundingMode.HALF_DOWN));
		superfici.setTotaleSuperficieInVincolo((new BigDecimal(idfTIntervVincIdroDAO.getAreaSuperficieInVincoloByFkIntervento(idIntervento))).divide(BigDecimal.valueOf(10000)).setScale(4, RoundingMode.HALF_DOWN));
		superfici.setTotaleSuperficieBoscataInVincolo((new BigDecimal(idfTIntervVincIdroDAO.getAreaSuperficieBoscataInVincoloByFkIntervento(idIntervento))).divide(BigDecimal.valueOf(10000)).setScale(4, RoundingMode.HALF_DOWN));
		superfici.setTotaleSuperficieNonBoscataInVincolo(superfici.getTotaleSuperficieInVincolo().subtract(superfici.getTotaleSuperficieBoscataInVincolo()).setScale(4, RoundingMode.HALF_DOWN));

		return superfici;
	}
	
	@Override
	public BigDecimal getCauzione(Integer idIntervento) throws RecordNotUniqueException, ValidationException {

		//BigDecimal base = BigDecimal.ZERO;
		BigDecimal cauzioneDefault = BigDecimal.valueOf(1000L);
		BigDecimal cauzione = getCauzione();
		Intervento intervento = interventoDAO.findInterventoByIdIntervento(idIntervento);
		if (intervento != null) {
			BigDecimal superficieInteressata = intervento.getSuperficieInteressata();
			if (superficieInteressata == null) {
				superficieInteressata = BigDecimal.ZERO;
			}
			cauzione = superficieInteressata.multiply(cauzione);
		}
		if (cauzione.compareTo(cauzioneDefault) < 0) {
			cauzione = cauzioneDefault;
		}
		return cauzione;
	}
	
	@Override
	public BigDecimal getValoreMarcaBollo() throws RecordNotUniqueException, ValidationException {

		BigDecimal marca = BigDecimal.ZERO;
		String conf = configurationDAO.getConfigurationNumberByName(Constants.VALORE_MARCA_DA_BOLLO);
		if (StringUtils.isNotBlank(conf)) {
			try {
				marca = BigDecimal.valueOf(Double.parseDouble(conf));
			} catch (Exception e) {
				logger.error("Parametro VALORE_MARCA_DA_BOLLO non settato correttamente");
			}
		}
		return marca;
	}
	
	@Override
	public BigDecimal getCauzione() throws RecordNotUniqueException, ValidationException {

		BigDecimal cauzione = BigDecimal.ZERO;
		String conf = configurationDAO.getConfigurationNumberByName(Constants.BASE_CALCOLO_EURO_CAUZIONE_VINCOLO_IDROGEOLOGICO);
		if (StringUtils.isNotBlank(conf)) {
			try {
				cauzione = BigDecimal.valueOf(Double.parseDouble(conf));
			} catch (Exception e) {
				logger.error("Parametro BASE_CALCOLO_EURO_CAUZIONE_VINCOLO_IDROGEOLOGICO non settato correttamente");
			}
		}
		return cauzione;
	}
	
	@Override
	public BigDecimal getCoefficienteCalcoloSupNonBoscata() throws RecordNotUniqueException, ValidationException {

		BigDecimal marca = BigDecimal.ZERO;
		String conf = configurationDAO.getConfigurationNumberByName(Constants.COEFF_CALCOLO_EURO_SUP_NON_BOSCATA_LR45);
		if (StringUtils.isNotBlank(conf)) {
			try {
				marca = BigDecimal.valueOf(Double.parseDouble(conf));
			} catch (Exception e) {
				logger.error("Parametro COEFF_CALCOLO_EURO_SUP_NON_BOSCATA_LR45 non settato correttamente");
			}
		}
		return marca;
	}

	@Override
	public VincoloStep3DTO getStep3(int idIntervento) throws RecordNotUniqueException, ValidationException {
		VincoloStep3DTO vincoloStep3DTO = new VincoloStep3DTO();
		List<VincoloHeading> vincoloHeadings = new ArrayList<>();

		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDAO.findById(new BigDecimal(idIntervento));
        
		if (idfTIntervVincIdro != null) {
			
			Intervento intervento = interventoDAO.findInterventoByIdIntervento(idIntervento);
			if (intervento!=null) {
				vincoloStep3DTO.setDescrizioneIntervento(intervento.getDescrizioneIntervento());
			}
			
			Integer tipoIntervento = idfTIntervVincIdro.getFkTipoIntervento() != null
					? Integer.valueOf(idfTIntervVincIdro.getFkTipoIntervento().intValue())
					: null;
			Integer tempoPrevisto = idfTIntervVincIdro.getMesiIntervento() != null
					? Integer.parseInt(idfTIntervVincIdro.getMesiIntervento().trim())
					: null;
			boolean presenzaAreeDissesto = idfTIntervVincIdro.getFlgAreeDissesto() != null
					&& idfTIntervVincIdro.getFlgAreeDissesto().intValue() == 1 ? true : false;
			boolean presenzaAreeEsondazione = idfTIntervVincIdro.getFlgAreeEsondazione() != null
					&& idfTIntervVincIdro.getFlgAreeEsondazione().intValue() == 1 ? true : false;

			vincoloStep3DTO.setAltroTipoIntervento(idfTIntervVincIdro.getDescTipoIntervAltro());
			vincoloStep3DTO.setTipoIntervento(tipoIntervento);
			vincoloStep3DTO.setTotaleTotMovimentiTerra(idfTIntervVincIdro.getMovimentiTerraMc());
			vincoloStep3DTO.setTotaleTotMovimentiTerraVincolo(idfTIntervVincIdro.getMovimentiTerraVincidroMc());
			vincoloStep3DTO.setTempoPrevisto(tempoPrevisto);
			vincoloStep3DTO.setPresenzaAreeDissesto(presenzaAreeDissesto);
			vincoloStep3DTO.setPresenzaAreeEsondazione(presenzaAreeEsondazione);

			// Intestazione sezioni.
			List<TipoParametroTrasf> headings = Arrays.asList(new TipoParametroTrasf[] {
					new TipoParametroTrasf(1, "C - Copertura vegetale interessata", 2, Byte.parseByte("1")) // C =
																											// checkbox.
			});

			// Intestazione elementi di ciascuna sezione.
			List<Governo> governoList = governoDAO.findAllGoverni();
			List<ParametroTrasf> subHeadings = new ArrayList<>();
			for (int i = 0; i < governoList.size(); i++)
				subHeadings.add(new ParametroTrasf(governoList.get(i).getIdGoverno(), Integer.valueOf(1),
						governoList.get(i).getDescrGoverno(), Integer.valueOf(i), Byte.parseByte("1"), null));

			for (TipoParametroTrasf heading : headings) {
				List<VincoloSubHeading> vincoloSubHeadings = new ArrayList<>();

				for (ParametroTrasf subHeading : subHeadings) {
					if (subHeading.getFkTipoParametroTrasf().equals(heading.getIdTipoParametroTrasf())
							&& subHeading.getFlgVisible() == 1) {
						IdfRIntervincidroGoverno idfRIntervincidroGoverno = idfRIntervincidroGovernoDAO
								.findById(new BigDecimal(idIntervento), subHeading.getIdParametroTrasf());

						if (idfRIntervincidroGoverno != null) { // Elementi già associati all'intervento e checkati.
							VincoloSubHeading vincoloSubHeadingFromIntervento = new VincoloSubHeading();
							vincoloSubHeadingFromIntervento.setId(subHeading.getIdParametroTrasf());
							vincoloSubHeadingFromIntervento.setName(subHeading.getDescParametroTrasf());
							vincoloSubHeadingFromIntervento.setVisibility(true);
							vincoloSubHeadingFromIntervento.setValore(
									subHeading.getValore() != null ? subHeading.getValore().toString() : null);
							vincoloSubHeadingFromIntervento.setChecked(true);
							vincoloSubHeadings.add(vincoloSubHeadingFromIntervento);
						} else // Elementi non associati all'intervento o non checkati.
							vincoloSubHeadings.add(getVincoloSubHeadings(subHeading));
					}
				}

				if (heading.getFlgVisible() == 1)
					vincoloHeadings.add(getVincoloHeading(heading, vincoloSubHeadings));
			}

			vincoloStep3DTO.setHeadings(vincoloHeadings);

			return vincoloStep3DTO;
		}

		return vincoloStep3DTO;
	}

	@Override
	public VincoloStep4DTO getStep4(int idIntervento) throws RecordNotUniqueException, ValidationException {

		if (!skOkVincoloDAO.isFlgSez3Done(idIntervento))
			throw new ValidationException(SEZIONE_3_VALID_MSG);

		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDAO.findById(new BigDecimal(idIntervento));

		if (idfTIntervVincIdro != null && idfTIntervVincIdro.getFlgCauzioneVi() != null) {
			VincoloStep4DTO vincoloStep4DTO = new VincoloStep4DTO();
			vincoloStep4DTO.setFlagCauzione(Integer.valueOf(idfTIntervVincIdro.getFlgCauzioneVi().intValue()));

			return vincoloStep4DTO;
		}

		return null;
	}

	private List<VincoloSubHeading> getSubHeadingsfWithCheckedValue(VincoloHeading heading) {
		List<VincoloSubHeading> subHeadings = new ArrayList<>();
		for (VincoloSubHeading subHeading : heading.getSubheadings()) {
			if (subHeading.isChecked()) {
				subHeadings.add(subHeading);
			}
		}
		return subHeadings;
	}

	@Override
	public List<FatDocumentoAllegato> getTuttiElencaForIntervento(Integer idIntervento, boolean isGestore) {

		List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
		tipoAllegati.add(TipoAllegatoEnum.RICHIESTA_AUTORIZZAZIONE);
		tipoAllegati.add(TipoAllegatoEnum.RICHIESTA_AUTORIZZAZIONE_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.RICHIESTA_AUTORIZZAZIONE_AUTOGRAFA);
		tipoAllegati.add(TipoAllegatoEnum.SCANSIONE_DOCUMENTO_IDENTITA);
		tipoAllegati.add(TipoAllegatoEnum.PROGETTO_DEFINITIVO);
		tipoAllegati.add(TipoAllegatoEnum.RELAZIONE_TECNICA);
		tipoAllegati.add(TipoAllegatoEnum.RELAZIONE_GEOLOGICA_E_GEOTECNICA);
		tipoAllegati.add(TipoAllegatoEnum.RELAZIONE_SPECIALISTICA_FORESTALE);
		tipoAllegati.add(TipoAllegatoEnum.PROGETTO_DI_RIMBOSCHIMENTO);
		tipoAllegati.add(TipoAllegatoEnum.DOCUMENTAZIONE_FOTOGRAFICA);
		tipoAllegati.add(TipoAllegatoEnum.RELAZIONE_NIVOLOGICA);
		tipoAllegati.add(TipoAllegatoEnum.ESTRATTO_PLANIMETRICO);
		tipoAllegati.add(TipoAllegatoEnum.SCHEDA_TECNICA);
		tipoAllegati.add(TipoAllegatoEnum.PLANIMETRIA_CATASTALE);
		tipoAllegati.add(TipoAllegatoEnum.ESTRATTO_AEROFOTOGRAMMETRICO);
		tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_SOSTITUTIVA_ATTO_DI_NOTORIETA);
		tipoAllegati.add(TipoAllegatoEnum.ALLEGATO_LIBERO_1);
		tipoAllegati.add(TipoAllegatoEnum.ALLEGATO_LIBERO_2);
		tipoAllegati.add(TipoAllegatoEnum.PROGETTO_DEFINITIVO_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.RELAZIONE_TECNICA_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.RELAZIONE_GEOLOGICA_E_GEOTECNICA_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.RELAZIONE_SPECIALISTICA_FORESTALE_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.PROGETTO_DI_RIMBOSCHIMENTO_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.DOCUMENTAZIONE_FOTOGRAFICA_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.RELAZIONE_NIVOLOGICA_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.ESTRATTO_PLANIMETRICO_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.SCHEDA_TECNICA_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.PLANIMETRIA_CATASTALE_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.ESTRATTO_AEROFOTOGRAMMETRICO_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_SOSTITUTIVA_ATTO_DI_NOTORIETA_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.ALLEGATO_LIBERO_DIGITALE);
		tipoAllegati.add(TipoAllegatoEnum.RICEVUTA_PAGAMENTO_DIRITTI_ISTRUTTORIA);
		tipoAllegati.add(TipoAllegatoEnum.RICEVUTA_VERSAMENTO_DEPOSITO_CAUZIONALE);
		tipoAllegati.add(TipoAllegatoEnum.RICEVUTA_VERSAMENTO_COMPENSAZIONE_FISICA);
		tipoAllegati.add(TipoAllegatoEnum.RICEVUTA_VERSAMENTO_COMPENSAZIONE_MONETARIA);
		if(!isGestore) {
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

	private VincoloHeading getVincoloHeading(TipoParametroTrasf heading,
			List<VincoloSubHeading> vincoloSubHeadingList) {
		VincoloHeading vincoloHeading = new VincoloHeading();

		vincoloHeading.setId(heading.getIdTipoParametroTrasf());
		vincoloHeading.setName(heading.getTipoParemetroTrasf());
		vincoloHeading.setVisibility(true);
		vincoloHeading.setSubheadings(vincoloSubHeadingList);
		return vincoloHeading;
	}

	private VincoloSubHeading getVincoloSubHeadings(ParametroTrasf subHeading) {
		VincoloSubHeading vincoloSubHeading = new VincoloSubHeading();

		vincoloSubHeading.setId(subHeading.getIdParametroTrasf());
		vincoloSubHeading.setName(subHeading.getDescParametroTrasf());
		vincoloSubHeading.setVisibility(true);
		vincoloSubHeading.setValore(subHeading.getValore() != null ? subHeading.getValore().toString() : null);
		vincoloSubHeading.setChecked(false);
		return vincoloSubHeading;
	}

	private void insertOrDeleteParticelleCatastale(List<PlainParticelleCatastali> particelleCatastali, int idIntervento,
			int fkConfigUtente, String note) throws RecordNotUniqueException {
		for (PlainParticelleCatastali particella : particelleCatastali) {

			if (particella.isToDelete() && !particella.getId().equals(0) && particella.getId() != null) {
				propcatastoInterventoDAO.deletePropcatastoIntervento(particella.getId(), idIntervento);
				// propCatastoDAO.deletePropCatasto(particella.getId());
			} else if ((particella.getId() == null || particella.getId().equals(0)) && !particella.isToDelete()) {
				PropCatasto propCatasto = new PropCatasto();
				// !! Modifica per aggancio nuovo servizio GisCata che usa il Codice Belfiore al posto del Codice Istat
				//propCatasto.setFkComune(comuneDAO.findComuneResourceByIstatComune(particella.getComune().getIstatComune()).getIdComune());
				propCatasto.setFkComune(comuneDAO.findComuneResourceByCodiceBelfiore(particella.getComune().getCodiceBelfiore()).getIdComune());
				propCatasto.setSezione(particella.getSezione());
				propCatasto.setFoglio(particella.getFoglio());
				propCatasto.setParticella(particella.getParticella());
				propCatasto.setSupCatastaleMq(particella.getSupCatastale());
				propCatasto.setSupCartograficaHa(particella.getSupCatastale());
				propCatasto.setNote(note);
				// required fields
				propCatasto.setFkProprieta(23);
				propCatasto.setFlgLivellari((byte) 0);
				propCatasto.setFlgPossessiContest((byte) 0);
				propCatasto.setFlgUsiCivici((byte) 0);
				propCatasto.setDataInizioValidita(LocalDate.now());

				PropcatastoIntervento propcatastoIntervento = new PropcatastoIntervento();
				propcatastoIntervento.setIdgeoPlPropCatasto(particella.getId());
				propcatastoIntervento.setIdIntervento(idIntervento);
				propcatastoIntervento.setFkConfigUtente(fkConfigUtente);
				propcatastoIntervento.setDataInserimento(LocalDate.now());
				// MK mocked value of sup_intervento -> should be returned from geography
				propcatastoIntervento.setSupIntervento(particella.getSupCatastale().multiply(BigDecimal.valueOf(0.30)));
				propcatastoInterventoDAO.insertPropcatastoIntervento(propcatastoIntervento);
			} else {
				// propCatastoDAO.updateAllNoteOfOneIntervento(note, idIntervento);
			}
		}
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
				// MK mocked value of sup_intervento -> should be returned from geography
				propcatastoIntervento.setSupIntervento(particella.getSupCatastale().multiply(BigDecimal.valueOf(0.30)));
				propcatastoInterventoDAO.insertPropcatastoIntervento(propcatastoIntervento);
			} else {
				// propCatastoDAO.updateAllNoteOfOneIntervento(note, idIntervento);
			}
		}

	}

	@Override
	public void insertParticlesInPropcatastoIntervento(Integer idIntervento, PlainParticelleCatastali particella,
			ConfigUtente loggedConfig) throws DuplicateKeyException {

		propcatastoInterventoDAO.insertParticelleInPropcatastoIntervento(idIntervento, particella, loggedConfig);
	}

	private int insertSoggetoPrimaSez(int compilerSoggettoConfigUtente, VincoloStep1DTO body)
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

	@Override
	public void invioIstanza(Integer idIntervento, ProfessionistaElencoAllegati body, TSoggetto soggetto)
			throws RecordNotFoundException, RecordNotUniqueException {

		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);

		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.RICHIEDENTE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.TECNICO_FORESTALE.getValue()) {
			if (body.getAllegati().isEmpty()) {
				throw new IllegalArgumentException("Elenco allegati non puo essere vuoto");
			}
//			List<TipoAllegatoEnum> tipoAllegati = new ArrayList<>();
//			tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_DIGITALE);
//			tipoAllegati.add(TipoAllegatoEnum.DICHIARAZIONE_AUTOGRAFA);
//			tipoAllegati.add(TipoAllegatoEnum.DOCUMENTO_IDENTITA);
//
//			documentoAllegatiManipulation(body.getAllegati(), soggetto.getFkConfigUtente(), idIntervento, tipoAllegati);

			// TODO Send e-mail
		} else {
			throw new IllegalArgumentException("Not allowed to proceed with this action");
		}
		istanzaForestDAO.updateInvioIstanza(idIntervento, "NONE");

	}

	private boolean isSetCategoryForestOption(Integer idIntervento, VincoloSubHeading subHeading) {
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

	private boolean isSetDestinazioniOption(Integer idIntervento, VincoloHeading heading)
			throws RecordNotUniqueException {
		if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.D.toString())) {
			// First item is always checked
			if (!heading.getSubheadings().get(0).isChecked()) {
				throw new IllegalArgumentException("'Vincolo Paesaggistico' option must be selected");
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

	@Override
	public List<IstanzaTaglio> saveOrDeleteIstanzaTaglio(IstanzaTaglio istanzaTaglio, int fkConfigUtente, int fkIntervento) {
		List<IstanzaTaglio> allIstanzi = wrapperIstanzaDAO.mapToIstanziTaglioList(
				istanzaCompensazioneDAO.getAllByFkIntervento(fkIntervento));

		if (istanzaTaglio.isDeleted()) {
			if(allIstanzi.contains(istanzaTaglio)) {
				istanzaCompensazioneDAO.deleteIstanza(istanzaTaglio.getNumIstanza());
			}
		} else if (!allIstanzi.contains(istanzaTaglio)) {
			istanzaCompensazioneDAO.insertIstanzaCompensazione(
					mapToIstanzaCompensazione(istanzaTaglio, fkConfigUtente, fkIntervento));
		}
		
		return wrapperIstanzaDAO.mapToIstanziTaglioList(istanzaCompensazioneDAO.getAllByFkIntervento(fkIntervento));
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

			response.add(particelleCatastali);
		}

		return response;
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


	private String preselectedOptionsWarning(int idIntervento, List<VincoloHeading> headings)
			throws RecordNotUniqueException {

		int locationSubHeadingId = getLocationSubHeadingId(idIntervento);

		StringBuilder sb = new StringBuilder();

		for (VincoloHeading heading : headings) {
			for (VincoloSubHeading subHeading : heading.getSubheadings()) {
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.B.toString())
						&& !isSetCategoryForestOption(idIntervento, subHeading)) {
					sb.append("CATEGORIA FORESTALE - preselezione del sistema modificata;\r\n");
				}
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.C.toString())
						&& subHeading.getId() == locationSubHeadingId && !subHeading.isChecked()) {
					sb.append("UBICAZIONE:la preselezione del sistema e stata modificata \r\n");
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

	private void preselectTheRightOptions(Integer idIntervento, PlainTerzaSezione plainTersaSezione)
			throws RecordNotUniqueException {
		List<ParamtrasfTrasformazion> paramtrasfs = paramtrasfTrasformazionDAO
				.getParamtrasfTrasformazionsByIdIntervento(idIntervento);
		if (!paramtrasfs.isEmpty()) {
			preselectOptionsFromDatabase(idIntervento, plainTersaSezione.getHeadings(), paramtrasfs);
		}

		int locationSubHeadingId = getLocationSubHeadingId(idIntervento);
		for (BoscoHeadings heading : plainTersaSezione.getHeadings()) {
			for (BoscoSubHeadings subHeading : heading.getSubheadings()) {
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.B.toString())) {
					setCategoryForestOption(idIntervento, subHeading);
				}
				if (heading.getName().substring(0, 1).equals(HeadingsDescriptionEnum.C.toString())
						&& subHeading.getId() == locationSubHeadingId) {
					subHeading.setChecked(true);
				}
			}
			setDestinazioniOption(idIntervento, heading);
		}
	}

	@Override
	public void recalculateParticelleIntervento(Integer idIntervento, String cfUtente, ConfigUtente configUtente)
			throws ServiceException {
		propcatastoInterventoDAO.deleteAllByIdIntervento(idIntervento);

		List<String> geometryList = idfTIntervVincIdroDAO.getGeometrieGeoJSONByFkIntervento(idIntervento);
		if (geometryList != null && geometryList.size() > 0) {
			List<InfoParticella> particelleList = new ArrayList<InfoParticella>();
			Map<String, InfoParticella> mapParticelle = new HashMap<String, InfoParticella>();
			String key;
			for (String geometry : geometryList) {
				logger.info("idIntervento: " + idIntervento + " - recalculateParticelleIntervento - getParticelleByGeometry: " + geometry);
				
				ObjectMapper objectMapper3 = new ObjectMapper();
		        ObjectNode jsonObject3 = objectMapper3.createObjectNode();
		        ObjectMapper objectMapper2 = new ObjectMapper();
		        JsonNode jsonNode2 = null;

				try {
					jsonNode2 = objectMapper2.readTree(geometry);
					ObjectMapper objectMapper1 = new ObjectMapper();
					ObjectNode jsonObject1 = objectMapper1.createObjectNode();
							   jsonObject1.put("type", "Feature");
		        			   jsonObject1.putPOJO("properties", jsonObject3);
		        			   jsonObject1.putPOJO("geometry", jsonNode2);
					ObjectMapper objectMapper = new ObjectMapper();
		        	ObjectNode jsonObject = objectMapper.createObjectNode();
		        	           jsonObject.putPOJO("feature", jsonObject1);

					String particelleInter = particella.findParticellaByString(jsonObject.toString());
					logger.info("particelleList ---- " + particelleList);

					ObjectMapper objectMapper77 = new ObjectMapper();
			        JsonNode jsonNode77 = null;
							 jsonNode77 = objectMapper77.readTree(particelleInter);
					LocalDate fechaHoy = LocalDate.now();
					JsonNode elementosNode = jsonNode77.get("features");
					
					if (elementosNode.isArray()) {
						for (JsonNode elemento : elementosNode) {
							StringBuilder sql = new StringBuilder();
										  sql.append("select ST_AsText(ST_Transform(ST_SetSRID(ST_GeomFromGeoJSON(?),4326),32632))");
							String converteString = DBUtil.jdbcTemplate.queryForObject(sql.toString(), String.class, elemento.get("geometry").toString());

							////AREA								
							StringBuilder sqlArea = new StringBuilder();
							sqlArea.append("SELECT ROUND((SELECT (ST_Area(?)))::numeric, 4)");
							BigDecimal areaBigDecimal = DBUtil.jdbcTemplate.queryForObject(sqlArea.toString(), BigDecimal.class, converteString);
							System.out.println("areaBigDecimal-------------: " + areaBigDecimal);				            
							double areaBigDecimalTsf = areaBigDecimal.doubleValue(); // The double you want

							double divisor = 10000.0;

							System.out.println("Element: " + elemento);
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
							System.out.println("Particellas uno a uno  : "+ objectToString(particella1));

							particelleList.add(particella1);
						}
					}

				} catch (JsonParseException jpe) {
					jpe.printStackTrace();
				} catch (JsonMappingException jme) {
					jme.printStackTrace();
				} catch(IOException ie) {
					ie.printStackTrace();
				} 

				System.out.println("---list to save---" + objectToString(particelleList));

				if (particelleList != null && particelleList.size() > 0) {
					logger.info("Trovate " + particelleList.size() + " tramite getParticelleByGeometry");
					for (InfoParticella infoPar : particelleList) {
						key = infoPar.getCodIstatComune() + "-" + infoPar.getSezione() + "-" + infoPar.getFoglio() + "-" + infoPar.getNumero();
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
					item = getPlainParticelleCatastaliFromInfoParticella(infoPar, configUtente);
				} catch (RecordNotUniqueException e) {
					e.printStackTrace();
					throw new ServiceException("RecordNotUniqueException");
				}

				logger.info("Trovato particella: " + objectToString(item));
				item = propCatastoDAO.insertParticella(item);
				// inserire su item la superficie effettivamente oggetto di intervento
				// calcolata come intersezione della particella con la geom dell'intervento su
				// idf_geo_pl_interv_trasf
				areaIntersect = idfTIntervVincIdroDAO.getAreaItersecWithParticella(idIntervento,
						item.getIdGeoPlPropCatasto());
				if (areaIntersect >= 1) {
					item.setSupIntervento(new BigDecimal(areaIntersect / 10000));// da metri a ettari
					wrapperInterventoDAO.insertParticlesInPropcatastoIntervento(idIntervento, item, configUtente);
				}
			}
		}
	}

	private void removeParamtrasfTrasformazionsFromDB(List<ParamtrasfTrasformazion> paramsfToDelete) {
		if (!paramsfToDelete.isEmpty()) {
			for (ParamtrasfTrasformazion paramtrasfTrasformazion : paramsfToDelete) {
				paramtrasfTrasformazionDAO.deleteByIdInterventoAndIdParametroTrasf(
						paramtrasfTrasformazion.getIdIntervento(), paramtrasfTrasformazion.getIdParametroTrasf());
			}
		}
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

	private DichCompensazione retrieveDichAltroAllega() {
		DichCompensazione dichCompensazione = new DichCompensazione();
		dichCompensazione.setVisible(true);
		dichCompensazione.setChecked(false);
		dichCompensazione.setRequired(false);
		return dichCompensazione;
	}

	private DichCompensazione retrieveDichCompFisica(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		boolean required = SuperficieCompensationEnum.F.toString().equals(intervTrasformazione.getFlgCompensazione());
		DichCompensazione dichCompensazione = new DichCompensazione();
		dichCompensazione.setEtichetta(parametroAppl.getParametroApplChar().replace("{$DEPOSIT$}",
				intervTrasformazione.getCompenzazioneEuro().toString()));
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
				intervTrasformazione.getCompenzazioneEuro().toString()));
		dichCompensazione.setVisible(isChecked);
		dichCompensazione.setChecked(isChecked);
		dichCompensazione.setRequired(isChecked);
		return dichCompensazione;
	}

	private DichDissensi retrieveDichDissensi(ParametroAppl parametroAppl, Byte flgDissensi) {
		DichDissensi dichDissensi = new DichDissensi();
		dichDissensi.setEtichetta(parametroAppl.getParametroApplChar());
		dichDissensi.setHaDissenso(flgDissensi == 1);
		return dichDissensi;
	}

	private DichIstanzaTaglio retrieveDichIstanzi(ParametroAppl parametroAppl, int idIntervento,
			IntervTrasformazione intervTrasformazione) {
		boolean required = SuperficieCompensationEnum.F.toString().equals(intervTrasformazione.getFlgCompensazione());
		DichIstanzaTaglio dichIstanzaTaglio = new DichIstanzaTaglio();
		dichIstanzaTaglio.setEtichetta(parametroAppl.getParametroApplChar());
		dichIstanzaTaglio.setVisible(required);
		dichIstanzaTaglio.setRequired(required);
		dichIstanzaTaglio.setChecked(required);
		List<IstanzaCompensazione> istanzas = istanzaCompensazioneDAO.getAllByFkIntervento(idIntervento);
		if (required) {
			dichIstanzaTaglio.setIstanziList(wrapperIstanzaDAO.mapToIstanziTaglioList(istanzas));
		} else {
			dichIstanzaTaglio.setIstanziList(null);
		}

		return dichIstanzaTaglio;
	}

	private DichNota retrieveDichNota(IntervTrasformazione intervTrasformazione) {
		DichNota dichNota = new DichNota();
		dichNota.setEtichetta(
				"Annotazioni/dichiarazioni/chiarimenti relativi alle dichiarazioni riportate, da sottoporre a chi valutera la domanda");
		dichNota.setTesto(intervTrasformazione.getNoteDichiarazione());
		return dichNota;
	}

	private DichAutorizzazione retrieveDichPaesaggistica(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		DichAutorizzazione dichAutorizzazione = new DichAutorizzazione();
		dichAutorizzazione.setEtichetta(parametroAppl.getParametroApplChar());
		dichAutorizzazione.setVisible(true);
		dichAutorizzazione.setChecked(true);
		dichAutorizzazione.setRequired(true);
		if (intervTrasformazione.getFlgAutPaesaggistica() == 1) {
			dichAutorizzazione.setNumeroAutorizzazione(intervTrasformazione.getNrAutPaesaggistica());
			dichAutorizzazione.setDataAutorizzazione(intervTrasformazione.getDataAutPaesaggistica());
			dichAutorizzazione.setRilasciataAutorizzazione(intervTrasformazione.getEnteAutPaesaggistica());
		}
		return dichAutorizzazione;
	}

	private DichProprieta retrieveDichProprieta(ParametroAppl parametroAppl1, ParametroAppl parametroAppl2,
			Byte flgProprieta) {
		DichProprieta dichProprieta = new DichProprieta();
		dichProprieta.setEtichetta1(parametroAppl1.getParametroApplChar());
		dichProprieta.setEtichetta2(parametroAppl2.getParametroApplChar());
		dichProprieta.setOwner(flgProprieta == 1);
		return dichProprieta;
	}

	private DichAutorizzazione retrieveDichValIncidenza(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione, List<InterventoRn2000> interventos) {
		boolean isInterventos2000Empty = interventos.isEmpty();
		DichAutorizzazione dichAutorizzazione = new DichAutorizzazione();
		dichAutorizzazione.setEtichetta(parametroAppl.getParametroApplChar());
		dichAutorizzazione.setVisible(!isInterventos2000Empty);
		dichAutorizzazione.setChecked(!isInterventos2000Empty);
		dichAutorizzazione.setRequired(false);
		if (!isInterventos2000Empty && intervTrasformazione.getFlgAutIncidenza() == 1) {
			dichAutorizzazione.setNumeroAutorizzazione(intervTrasformazione.getNrAutIncidenza());
			dichAutorizzazione.setDataAutorizzazione(intervTrasformazione.getDataAutIncidenza());
			dichAutorizzazione.setRilasciataAutorizzazione(intervTrasformazione.getEnteAutIncidenza());
		}
		return dichAutorizzazione;
	}

	private DichAutorizzazione retrieveDichVincIdrogeologico(ParametroAppl parametroAppl,
			IntervTrasformazione intervTrasformazione) {
		DichAutorizzazione dichAutorizzazione = new DichAutorizzazione();
		dichAutorizzazione.setEtichetta(parametroAppl.getParametroApplChar());
		dichAutorizzazione.setVisible(intervTrasformazione.getFlgVincIdro() == 1);
		dichAutorizzazione.setChecked(intervTrasformazione.getFlgVincIdro() == 1);
		dichAutorizzazione.setRequired(false);
		if (intervTrasformazione.getFlgVincIdro() == 1 && intervTrasformazione.getFlgAutVidro() == 1) {
			dichAutorizzazione.setNumeroAutorizzazione(intervTrasformazione.getNrAutVidro());
			dichAutorizzazione.setDataAutorizzazione(intervTrasformazione.getDataAutVidro());
			dichAutorizzazione.setRilasciataAutorizzazione(intervTrasformazione.getEnteAutVidro());
		}
		return dichAutorizzazione;
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

		return intervTrasformazione;
	}

	private List<RicadenzaInformazioni> retrieveRicadenzaAreeProtette(Integer idIntervento) {
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

	private List<RicadenzaInformazioni> retrieveRicadenzaCategorieForestali(Integer idIntervento) {
		List<InterventoCatfor> interventoCatfors = interventoCatforDAO.getInterventosByIdIntervento(idIntervento);
		List<RicadenzaInformazioni> response = new ArrayList<>();

		if (!interventoCatfors.isEmpty()) {
			List<RicadenzaInformazioni> ricadenzePopSeme = ricadenzaService.cercaTutteCategorieForestali();
			List<CategoriaForestale> categorieForestale = categoriaForestaleDAO
					.getAllByInterventoCatfors(interventoCatfors);

			for (CategoriaForestale categoria : categorieForestale) {
				RicadenzaInformazioni ricadenzaInformazioni = new RicadenzaInformazioni(
						categoria.getCodiceAmministrativo());
				if (ricadenzePopSeme.contains(ricadenzaInformazioni)) {
					response.add(ricadenzePopSeme.get(ricadenzePopSeme.indexOf(ricadenzaInformazioni)));
				}
			}
		}
		return response;
	}

	private List<RicadenzaInformazioni> retrieveRicadenzaNatura2000Sic(Integer idIntervento) {
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

	private List<RicadenzaInformazioni> retrieveRicadenzaNatura2000Zps(Integer idIntervento) {
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

	private List<RicadenzaInformazioni> retrieveRicadenzaPopolamentiDaSeme(Integer idIntervento) {
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

	private void saveRicadenzaVincoloIdrogeologico(boolean isVincoloIdrogeologico, int idIntervento) {
		if (isVincoloIdrogeologico) {
			intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.YES.getValue(), idIntervento);
		} else {
			intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.NO.getValue(), idIntervento);
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

	private void saveRicadenzeNature2000(List<RicadenzaInformazioni> ricadenze, int idIntervento) {
		for (RicadenzaInformazioni ricadenza : ricadenze) {
			InterventoRn2000 interventoRn2000 = new InterventoRn2000();
			interventoRn2000.setIdIntervento(idIntervento);
			interventoRn2000.setCodiceRn2000(ricadenza.getCodiceAmministrativo());
			interventoRn2000DAO.insertInterventoRn2000(interventoRn2000);
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

	@Override
	public PlainSezioneId saveStep1(VincoloStep1DTO body, ConfigUtente loggedConfig)
			throws RecordNotUniqueException, ValidationException {

	
		registraDelegaSeNecessario(body.getCodiceFiscale(),loggedConfig);
		
		logger.info("tipo accreditamento: " + (loggedConfig.getFkTipoAccreditamento() == 1?"Richiedente":"Professionista"));
		
		int idIntervento = interventoDAO.createInterventoWithConfigUtente(loggedConfig.getIdConfigUtente());
		istanzaForestDAO.createIstanzaForest(getIstanzaForest(loggedConfig.getIdConfigUtente(), body, idIntervento));
		idfTIntervVincIdroDAO.create(getIstanzaVincolo(loggedConfig.getIdConfigUtente(), body, idIntervento));

		TSoggetto soggetto = null; 
		if(body.getDenominazione() != null && !"".equals(body.getDenominazione())
				&& body.getPartitaIva() != null && !"".equals(body.getPartitaIva())){
			soggetto = soggettoDAO.findAziendaByCodiceFiscale(body.getCodiceFiscale());
		}else {
			soggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getCodiceFiscale());
		}
		

		if (soggetto != null) {
			updateSoggettoPrimaSez(body, soggetto);
			associateNaturalLegalPerson(loggedConfig.getIdConfigUtente(), body, soggetto.getIdSoggetto(), idIntervento);
		} else {
			int idNewSog = insertSoggetoPrimaSez(loggedConfig.getIdConfigUtente(), body);

			if (body.getOwnerCodiceFiscale() != null) {
				TSoggetto ownerSoggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getOwnerCodiceFiscale());
				associatePersonCompanyIfRG(loggedConfig, ownerSoggetto.getIdSoggetto(), idNewSog);
			}

			associateNaturalLegalPerson(loggedConfig.getIdConfigUtente(), body, idNewSog, idIntervento);
		}

		skOkVincoloDAO.insertFlgSez1(idIntervento);

		return new PlainSezioneId(idIntervento);
	}

	@Override
	public VincoloStep2DTO saveStep2(VincoloStep2DTO body, ConfigUtente loggedConfig, Integer idIntervento)
			throws RecordNotUniqueException, ValidationException {

		if (!skOkVincoloDAO.isFlgSez1Done(idIntervento))
			throw new ValidationException(SEZIONE_1_VALID_MSG);

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

		IdfGeoPlIntervVincidro idfGeoPlIntervVincidro = new IdfGeoPlIntervVincidro();
		idfGeoPlIntervVincidro.setFkIntervento(idIntervento);
		idfGeoPlIntervVincidro.setDataInserimento(LocalDate.now());

		/*
		 * for(PlainParticelleCatastali part : body.getParticelleCatastali()) {
		 * idfGeoPlIntervVincidro.setGeometria(sigmater.
		 * getGeometryFromParticelleCatastali(part));
		 * idfGeoPlIntervVincidroDAO.insertIntervVincidro(idfGeoPlIntervVincidro); }
		 */

		// intervTrasfDAO.insertIntervTrasf(intervTrasf);
		TipoIstanzaEnum tipoIstanza = intervTrasfDAO.getTipoIntervento(idIntervento);
		if(tipoIstanza == TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE) {
			saveCompetenzaTerritoriale(idIntervento);
		}
		
		skOkVincoloDAO.updateFlgSez2(idIntervento);
		return body;
	}
	
	private void saveCompetenzaTerritoriale(int idIntervento) {
		TSoggetto sogCompetenza = dichiarazioneSummaryDAO.getSoggettoCompetenteRegionale(idIntervento,AmbitoIstanzaEnum.VINCOLO_IDROGEOLOGICO);
		istanzaForestDAO.updateEnteCompetente(idIntervento, sogCompetenza.getIdSoggetto());
	}

	@Override
	public VincoloStep3DTO saveStep3(int idIntervento, ConfigUtente loggedConfig, VincoloStep3DTO body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException {

		if (!skOkVincoloDAO.isFlgSez2Done(idIntervento))
			throw new ValidationException(SEZIONE_2_VALID_MSG);

		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDAO.findById(BigDecimal.valueOf(idIntervento));

		if (idfTIntervVincIdro != null) {
			if (body.getTipoIntervento() != null) {
				idfTIntervVincIdro.setFkTipoIntervento(new BigDecimal(body.getTipoIntervento()));
			}
			idfTIntervVincIdro.setDescTipoIntervAltro(body.getAltroTipoIntervento());
			idfTIntervVincIdro.setMovimentiTerraMc(body.getTotaleTotMovimentiTerra());
			idfTIntervVincIdro.setMovimentiTerraVincidroMc(body.getTotaleTotMovimentiTerraVincolo());
			idfTIntervVincIdro.setMesiIntervento(body.getTempoPrevisto().toString());
			idfTIntervVincIdro.setFlgAreeDissesto(new BigDecimal(body.isPresenzaAreeDissesto() ? 1 : 0));
			idfTIntervVincIdro.setFlgAreeEsondazione(new BigDecimal(body.isPresenzaAreeEsondazione() ? 1 : 0));

			if (idfTIntervVincIdroDAO.update(idfTIntervVincIdro)) {
				for (VincoloHeading heading : body.getHeadings()) // Copertura vegetale.
					for (VincoloSubHeading subHeading : heading.getSubheadings())
						if (subHeading.isChecked()) { // Inserisco in idf_r_intervincidro_governo solo se è selezionato.
							IdfRIntervincidroGoverno idfRIntervincidroGoverno = idfRIntervincidroGovernoDAO
									.findById(new BigDecimal(idIntervento), subHeading.getId());
							if (idfRIntervincidroGoverno == null) { // Inserisco in idf_r_intervincidro_governo solo se
																	// non è già presente.
								idfRIntervincidroGoverno = new IdfRIntervincidroGoverno(new BigDecimal(idIntervento),
										subHeading.getId(), new Date(System.currentTimeMillis()),
										loggedConfig.getFkProfiloUtente());
								idfRIntervincidroGovernoDAO.create(idfRIntervincidroGoverno);
							}
						}

				skOkVincoloDAO.updateFlgSez3(idIntervento);
			}

			interventoDAO.updateInterventoDescrizione(body.getDescrizioneIntervento(), idIntervento,
					loggedConfig.getIdConfigUtente());
		}

		return body;
	}

	@Override
	public VincoloStep4DTO saveStep4(int idIntervento, ConfigUtente loggedConfig, VincoloStep4DTO body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException {

		if (!skOkVincoloDAO.isFlgSez3Done(idIntervento))
			throw new ValidationException(SEZIONE_3_VALID_MSG);

		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDAO.findById(BigDecimal.valueOf(idIntervento));

		if (idfTIntervVincIdro != null) {
			idfTIntervVincIdro.setFlgCauzioneVi(new BigDecimal(body.getFlagCauzione()));

			if (idfTIntervVincIdroDAO.update(idfTIntervVincIdro))
				skOkVincoloDAO.updateFlgSez4(idIntervento);
		}

		return body;

		/*
		 * IdfTIntervVincIdro idfTIntervVincIdro =
		 * idfTIntervVincIdroDAO.findById(BigDecimal.valueOf(idIntervento));
		 * 
		 * if(idfTIntervVincIdro != null) {
		 * idfTIntervVincIdro.setDescTipoIntervAltro(body.getDescrizioneIntervento());
		 * idfTIntervVincIdro.setMovimentiTerraMc(body.getTotaleTotMovimentiTerra());
		 * idfTIntervVincIdro.setMovimentiTerraVincidroMc(body.
		 * getTotaleTotMovimentiTerraVincolo());
		 * idfTIntervVincIdro.setMesiIntervento(body.getTempoPrevisto().toString());
		 * idfTIntervVincIdro.setFlgAreeDissesto(new
		 * BigDecimal(body.isPresenzaAreeDissesto() ? 1 : 0));
		 * idfTIntervVincIdro.setFlgAreeEsondazione(new
		 * BigDecimal(body.isPresenzaAreeEsondazione() ? 1 : 0));
		 * 
		 * if(idfTIntervVincIdroDAO.update(idfTIntervVincIdro)) { for(VincoloHeading
		 * heading : body.getHeadings()) // Copertura vegetale. for(VincoloSubHeading
		 * subHeading : heading.getSubheadings()) if(subHeading.isChecked()) { //
		 * Inserisco in idf_r_intervincidro_governo solo se è selezionato.
		 * IdfRIntervincidroGoverno idfRIntervincidroGoverno =
		 * idfRIntervincidroGovernoDAO.findById(new BigDecimal(idIntervento),
		 * subHeading.getId()); if(idfRIntervincidroGoverno == null) { // Inserisco in
		 * idf_r_intervincidro_governo solo se non è già presente.
		 * idfRIntervincidroGoverno = new IdfRIntervincidroGoverno(new
		 * BigDecimal(idIntervento), subHeading.getId(), new
		 * Date(System.currentTimeMillis()), loggedConfig.getFkProfiloUtente());
		 * idfRIntervincidroGovernoDAO.create(idfRIntervincidroGoverno); } }
		 * 
		 * skOkVincoloDAO.updateFlgSez3(idIntervento); } }
		 * 
		 * return body;
		 */
	}
	
	void registraDelegaSeNecessario(String cfDelegante, ConfigUtente confUtente) throws RecordNotUniqueException {
		if(confUtente.getFkTipoAccreditamento() == 2 && confUtente.getFkProfiloUtente() == ProfiloUtenteEnum.CITTADINO.getValue()) {
			//se professionista gestire eventuale nuova delega ed e' profilo cittadino
			Delega delega = delegaDao.getByCfDeleganteAndConfigUtente(cfDelegante, confUtente.getIdConfigUtente());
			if(delega == null){
				logger.debug("Delega non presente");
				delega = new Delega();
				delega.setCfDelegante(cfDelegante);
				delega.setIdConfigUtente(confUtente.getIdConfigUtente());
				delega.setDataInizio(LocalDate.now());				
				delegaDao.createDelega(delega);
				logger.debug("Delega inserit");
			}else {
				logger.debug("Delega gia presente con dati ");
			}
		}else {
			logger.debug("No professionista no delega");
		}
	}
	
	
	@Override
	public VincoloStep5DTO saveStep5(int idIntervento, ConfigUtente loggedConfig, VincoloStep5DTO body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException {

		if (!skOkVincoloDAO.isFlgSez4Done(idIntervento))
			throw new ValidationException(SEZIONE_4_VALID_MSG);

		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDAO.findById(BigDecimal.valueOf(idIntervento));

		if (idfTIntervVincIdro != null) {
			
			idfTIntervVincIdro.setFlgCompensazione(body.getFlagCompensazione());

			idfTIntervVincIdro.setCmSupboscHa(body.getCmSupBosc());
			idfTIntervVincIdro.setCmSupnoboscHa(body.getCmSupNoBosc());
			idfTIntervVincIdro.setCmNoboscEuro(body.getCmNoBoscEuro());
			idfTIntervVincIdro.setCmBoscEuro(body.getCmBoscEuro());
		  
		
			idfTIntervVincIdro.setFlgArt9A(body.getFlagArt9A()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgArt9B(body.getFlagArt9B()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgArt9C(body.getFlagArt9C()?BigDecimal.ONE:BigDecimal.ZERO);
		  
			idfTIntervVincIdro.setFlgArt7A(body.getFlagArt7A()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgArt7B(body.getFlagArt7B()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgArt7C(body.getFlagArt7C()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgArt7D(body.getFlagArt7D()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgArt7DBis(body.getFlagArt7DBis()?BigDecimal.ONE:BigDecimal.ZERO);
		    

			if (idfTIntervVincIdroDAO.update(idfTIntervVincIdro))
				skOkVincoloDAO.updateFlgSez5(idIntervento);
		}

		return body;

		
	}
	
	
	@Override
	public VincoloStep6DTO saveStep6(int idIntervento, ConfigUtente loggedConfig, VincoloStep6DTO body)
			throws RecordNotFoundException, RecordNotUniqueException, ValidationException {

		if (!skOkVincoloDAO.isFlgSez5Done(idIntervento))
			throw new ValidationException(SEZIONE_5_VALID_MSG);

		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDAO.findById(BigDecimal.valueOf(idIntervento));

		if (idfTIntervVincIdro != null) {
	
			idfTIntervVincIdro.setFlgProprieta(body.isFlagProprieta()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgDissensi(body.isFlagDissensi()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgImporto(body.isFlagImporto()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgCopiaConforme(body.isFlagCopiaConforme()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgConfServizi(body.isFlagConfServizi()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgSuap(body.isFlagSuap()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setAnnotazioni(body.getAnnotazioni());
			idfTIntervVincIdro.setFlgSpeseIstruttoria(body.isFlagSpeseIstruttoria()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setFlgEsenzioneMarcaBollo(body.isFlagEsenzioneMarcaBollo()?BigDecimal.ONE:BigDecimal.ZERO);
			idfTIntervVincIdro.setnMarcaBollo(body.getnMarcaBollo());

			if (idfTIntervVincIdroDAO.update(idfTIntervVincIdro))
				skOkVincoloDAO.updateFlgSez6(idIntervento);
		}

		return body;

		
	}

	

	private void setCategoryForestOption(Integer idIntervento, BoscoSubHeadings subHeading) {
		int intersection = supForestaleDAO.getTipoForestaleIntersectPropCatasto(idIntervento);
		if (intersection != -1) {
			ParametroTrasf parametroTrasf = parametroTrasfDAO
					.getParametroTrasfById(macroCatforDAO.getMacroCatforById(categoriaForestaleDAO
							.getCategoriaForestaleById(
									tipoForestaleDAO.getTipoForestaleById(intersection).getFkCategoriaForestale())
							.getFkParamMacroCatfor()).getIdParamMacroCatfor());

			if (subHeading.getId().equals(parametroTrasf.getIdParametroTrasf())) {
				subHeading.setChecked(true);
			}
		}
	}

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

	private void setFlgArts(Integer idIntervento, IdfTIntervVincIdro idfTIntervVincIdro,
			CauzioneVincoloDTO cauzioneVincoloDTO) {
		if (idfTIntervVincIdro.getFlgArt7A() == new BigDecimal(1)
				|| interventoDAO.findInterventoByIdIntervento(idIntervento).getSuperficieInteressata().intValue() < 500)
			cauzioneVincoloDTO.setFlgA(true);

		if (idfTIntervVincIdro.getFlgArt7B() == new BigDecimal(1))
			cauzioneVincoloDTO.setFlgB(true);

		if (idfTIntervVincIdro.getFlgArt7C() == new BigDecimal(1))
			cauzioneVincoloDTO.setFlgC(true);

		if (idfTIntervVincIdro.getFlgArt7D() == new BigDecimal(1))
			cauzioneVincoloDTO.setFlgD(true);
	}

	@Override
	public void updateIstanzaRifiutata(Integer idIntervento, TSoggetto soggetoFromUser)
			throws RecordNotFoundException, RecordNotUniqueException {
		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.RICHIEDENTE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.TECNICO_FORESTALE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else {
			throw new IllegalArgumentException("Not allowed to proceed with this action");
		}
		// istanzaForestDAO.updateIstanzaTo(idIntervento, InstanceStateEnum.RIFIUTATA);

	}

	@Override
	public void updateIstanzaVerificata(Integer idIntervento, TSoggetto soggetto)
			throws RecordNotFoundException, RecordNotUniqueException {
		SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
		if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.RICHIEDENTE.getValue()) {
			// TODO Generate Jasper and send e-mail
		} else if (soggetoIntervento.getIdTipoSoggetto() == SoggettoTypeEnum.TECNICO_FORESTALE.getValue()) {
		} else {
			throw new IllegalArgumentException("Not allowed to proceed with this action");
		}
		// istanzaForestDAO.updateIstanzaTo(idIntervento, InstanceStateEnum.VERIFICATA);
	}

	private void updateNaturalLegalPerson(int idSoggetto, int idIntervento) {
		soggettoInterventoDAO.updateWithIdSoggetto(idSoggetto, idIntervento);
	}

	private void updateSoggettoPrimaSez(VincoloStep1DTO body, TSoggetto soggetto) throws RecordNotUniqueException {

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
	

	@Override
	public boolean updateStep1(VincoloStep1DTO body, ConfigUtente loggedConfig, int idIntervento)
			throws RecordNotUniqueException {

		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDAO.findById(BigDecimal.valueOf(idIntervento));
		CustomValidator.getValidator(HttpStatus.CONFLICT)
		.errorIf("Sezione1", !skOkVincoloDAO.isFlgSez1Done(idIntervento) 
				&& idfTIntervVincIdro.getFkInterventoPadreVariante() == null
				, SEZIONE_1_VALID_MSG).go();

		registraDelegaSeNecessario(body.getCodiceFiscale(),loggedConfig);

		TSoggetto soggetto = null; 
		if(( body.getDenominazione() == null || "".equals(body.getDenominazione()))
				&& (body.getPartitaIva() == null || "".equals(body.getPartitaIva()))){
			soggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getCodiceFiscale());
		}else {
			soggetto = soggettoDAO.findAziendaByCodiceFiscale(body.getCodiceFiscale());
		}

		if (soggetto != null) {
			updateSoggettoPrimaSez(body, soggetto);
			updateNaturalLegalPerson(soggetto.getIdSoggetto(), idIntervento);
		} else {
			int idNewSog = insertSoggetoPrimaSez(loggedConfig.getIdConfigUtente(), body);
			if (body.getOwnerCodiceFiscale() != null && body.getOwnerCodiceFiscale() != body.getCodiceFiscale()) {
				TSoggetto ownerSoggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getOwnerCodiceFiscale());
				associatePersonCompanyIfRG(loggedConfig, ownerSoggetto.getIdSoggetto(), idNewSog);
			}
			updateNaturalLegalPerson(idNewSog, idIntervento);
		}
		return true;

	}

//	@Override
//	public boolean updateStep2(VincoloStep2DTO body, ConfigUtente loggedConfig, Integer idIntervento)
//			throws RecordNotUniqueException, ValidationException {
//		if (!skOkVincoloDAO.isFlgSez1Done(idIntervento)) {
//			throw new ValidationException(SEZIONE_1_VALID_MSG);
//		}
//
//		// TSoggetto soggetto =
//		// soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
//
//		insertOrDeleteParticelleCatastale(body.getParticelleCatastali(), idIntervento, loggedConfig.getIdConfigUtente(),
//				body.getAnnotazioni());
//
//		interventoDAO.updateInterventoWithSuperficieInteressata(idIntervento, body.getTotaleSuperficieCatastale());
//
//		interventoAappDAO.deleteAllInterventoAappsByIdIntervento(idIntervento);
//		for (RicadenzaInformazioni ricadenza : body.getRicadenzaAreeProtette()) {
//			InterventoAapp interventoAapp = new InterventoAapp();
//			interventoAapp.setIdIntervento(idIntervento);
//			interventoAapp.setCodiceAapp(ricadenza.getCodiceAmministrativo());
//			interventoAappDAO.insertInterventoAapp(interventoAapp);
//		}
//
//		interventoRn2000DAO.deleteInterventosByIdIntervento(idIntervento);
//		for (RicadenzaInformazioni ricadenza : body.getRicadenzaNatura2000()) {
//			InterventoRn2000 interventoRn2000 = new InterventoRn2000();
//			interventoRn2000.setIdIntervento(idIntervento);
//			interventoRn2000.setCodiceRn2000(ricadenza.getCodiceAmministrativo());
//			interventoRn2000DAO.insertInterventoRn2000(interventoRn2000);
//		}
//
//		interventoPopSemeDAO.deleteInterventosByIdIntervento(idIntervento);
//		for (RicadenzaInformazioni ricadenza : body.getRicadenzaPopolamentiDaSeme()) {
//			InterventoPopSeme interventoPopSeme = new InterventoPopSeme();
//			interventoPopSeme.setIdIntervento(idIntervento);
//			interventoPopSeme.setIdPopolamentoSeme(popolamentoSemeDAO
//					.getByCodiceAmministrativo(ricadenza.getCodiceAmministrativo()).getIdPopolamentoSeme());
//			interventoPopSemeDAO.insertInterventoPopSeme(interventoPopSeme);
//		}
//
//		interventoCatforDAO.deleteInterventosById(idIntervento);
//		for (RicadenzaInformazioni ricadenza : body.getRicadenzaCategorieForestali()) {
//			InterventoCatfor interventoCatfor = new InterventoCatfor();
//			interventoCatfor.setIdIntervento(idIntervento);
//			interventoCatfor.setIdCategoriaForestale(categoriaForestaleDAO
//					.getByCodiceAmministratico(ricadenza.getCodiceAmministrativo()).getIdCategoriaForestale());
//			interventoCatfor.setFkConfigUtente(loggedConfig.getIdConfigUtente());
//			interventoCatforDAO.insertInterventoCatfor(interventoCatfor);
//		}
//
//		if (body.isRicadenzaVincoloIdrogeologico()) {
//			intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.YES.getValue(), idIntervento);
//		} else {
//			intervTrasformazioneDAO.setFlgVincIdro(VincIdroEnum.NO.getValue(), idIntervento);
//		}
//
//		skOkVincoloDAO.updateFlgSez2(idIntervento);
//
//		return true;
//
//	}

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
		} else {
			throw new ValidationException();
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

	private void validateCompensazione(PlainSestoSezione plainSestoSezione) throws ValidationException {
		if ((plainSestoSezione.getCompFisica().isChecked() && plainSestoSezione.getCompMonetaria().isChecked())
				|| (plainSestoSezione.getCompFisica().isChecked() && !plainSestoSezione.getIstanzi().isChecked())
				|| (plainSestoSezione.getCompMonetaria().isChecked() && plainSestoSezione.getIstanzi().isChecked())) {
			throw new ValidationException();
		}
	}

	private void validateQuintaSezione(TSoggetto soggetto) {

		if (soggetto == null || soggetto.getCodiceFiscale() == null || soggetto.getPartitaIva() == null
				|| soggetto.getCognome() == null || soggetto.getNome() == null || soggetto.getFkComune() == null
				|| soggetto.getFkComune() < 1 || soggetto.getIndirizzo() == null || soggetto.getCivico() == null
				|| soggetto.getCap() == null || soggetto.getNrTelefonico() == null || soggetto.geteMail() == null
				|| soggetto.getPec() == null) {
			throw new ValidationException(ErrorConstants.OGGETTO_OBBLIGATORIO);
		}

		if (soggetto.getCodiceFiscale().length() > 16 || soggetto.getPartitaIva().length() > 11
				|| soggetto.getCognome().length() > 100 || soggetto.getNome().length() > 50
				|| soggetto.getIndirizzo().length() > 50 || soggetto.getCivico().length() > 10
				|| soggetto.getCap().length() != 5 || soggetto.getNrTelefonico().length() > 20
				|| soggetto.geteMail().length() > 50 || soggetto.getPec().length() > 50) {
			throw new ValidationException(ErrorConstants.STRINGA_DIGITATA_ERROR);
		}

		if (!ValidationUtil.isEMail(soggetto.geteMail())) {
			throw new ValidationException(ErrorConstants.MAIL_NON_VALIDO);
		}

	}

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

	private void validateSearchParameters(Map<String, String> queryParams, ConfigUtente configUtente,
			TSoggetto soggetto) throws ValidationException, RecordNotUniqueException {
		validateSearchNullParams(queryParams);

		String annoIstanza = queryParams.get("annoIstanza");
		if (annoIstanza != null && annoIstanza.length() != 4 && !ValidationUtil.isNumber(annoIstanza)) {
			throw new ValidationException();
		}
		validateSearchDates(queryParams);
		validateSearchComuneAndProvincia(queryParams.get("prov"), queryParams.get("comune"), configUtente, soggetto);
		validateCalcoloEuro(queryParams.get("calcEconDa"), queryParams.get("calcEconA"));

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

	@Override
	public VincoloStep5DTO getStep5(int idIntervento) throws RecordNotUniqueException, ValidationException {
		if (!skOkVincoloDAO.isFlgSez4Done(idIntervento))
			throw new ValidationException(SEZIONE_4_VALID_MSG);

		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDAO.findById(new BigDecimal(idIntervento));

		if (idfTIntervVincIdro != null && StringUtils.isNotBlank(idfTIntervVincIdro.getFlgCompensazione())) {
			VincoloStep5DTO vincoloStep5DTO = new VincoloStep5DTO();
			
			vincoloStep5DTO.setFlagCompensazione(idfTIntervVincIdro.getFlgCompensazione());

			vincoloStep5DTO.setCmSupBosc(idfTIntervVincIdro.getCmSupboscHa());
			vincoloStep5DTO.setCmSupNoBosc(idfTIntervVincIdro.getCmSupnoboscHa());
			vincoloStep5DTO.setCmNoBoscEuro(idfTIntervVincIdro.getCmNoboscEuro());
			vincoloStep5DTO.setCmBoscEuro(idfTIntervVincIdro.getCmBoscEuro());
		  
		
			vincoloStep5DTO.setFlagArt9A(BigDecimal.ONE.equals(idfTIntervVincIdro.getFlgArt9A()));
			vincoloStep5DTO.setFlagArt9B(BigDecimal.ONE.equals(idfTIntervVincIdro.getFlgArt9B()));
			vincoloStep5DTO.setFlagArt9C(BigDecimal.ONE.equals(idfTIntervVincIdro.getFlgArt9C()));
			if(vincoloStep5DTO.getFlagArt9A() || vincoloStep5DTO.getFlagArt9B() || vincoloStep5DTO.getFlagArt9C()) {
				vincoloStep5DTO.setFlagEsenzione("1");
			}
		  
			vincoloStep5DTO.setFlagArt7A(BigDecimal.ONE.equals(idfTIntervVincIdro.getFlgArt7A()));
			vincoloStep5DTO.setFlagArt7B(BigDecimal.ONE.equals(idfTIntervVincIdro.getFlgArt7B()));
			vincoloStep5DTO.setFlagArt7C(BigDecimal.ONE.equals(idfTIntervVincIdro.getFlgArt7C()));
			vincoloStep5DTO.setFlagArt7D(BigDecimal.ONE.equals(idfTIntervVincIdro.getFlgArt7D()));
			vincoloStep5DTO.setFlagArt7DBis(BigDecimal.ONE.equals(idfTIntervVincIdro.getFlgArt7DBis()));
			if(vincoloStep5DTO.getFlagArt7A() || vincoloStep5DTO.getFlagArt7B() || vincoloStep5DTO.getFlagArt7C()
					 || vincoloStep5DTO.getFlagArt7D() || vincoloStep5DTO.getFlagArt7DBis()) {
				vincoloStep5DTO.setFlagEsenzione("2");
			}

			return vincoloStep5DTO;
		}
		
		return null;
	}
	
	
	@Override
	public VincoloStep6DTO getStep6(int idIntervento) throws RecordNotUniqueException, ValidationException {
		if (!skOkVincoloDAO.isFlgSez5Done(idIntervento))
			throw new ValidationException(SEZIONE_5_VALID_MSG);

		IdfTIntervVincIdro idfTIntervVincIdro = idfTIntervVincIdroDAO.findById(new BigDecimal(idIntervento));
		VincoloStep6DTO vincoloStep6DTO = new VincoloStep6DTO();
		if (idfTIntervVincIdro != null && idfTIntervVincIdro.getFlgProprieta() != null) {
			vincoloStep6DTO.setFlagProprieta(idfTIntervVincIdro.getFlgProprieta().equals(BigDecimal.ONE));
			vincoloStep6DTO.setFlagDissensi(idfTIntervVincIdro.getFlgDissensi().equals(BigDecimal.ONE));
			vincoloStep6DTO.setFlagImporto(idfTIntervVincIdro.getFlgImporto().equals(BigDecimal.ONE));
			vincoloStep6DTO.setFlagCopiaConforme(idfTIntervVincIdro.getFlgCopiaConforme().equals(BigDecimal.ONE));
			vincoloStep6DTO.setFlagConfServizi(idfTIntervVincIdro.getFlgConfServizi().equals(BigDecimal.ONE));
			vincoloStep6DTO.setFlagSuap(idfTIntervVincIdro.getFlgSuap().equals(BigDecimal.ONE));
			vincoloStep6DTO.setAnnotazioni(idfTIntervVincIdro.getAnnotazioni());
			vincoloStep6DTO.setFlagSpeseIstruttoria(idfTIntervVincIdro.getFlgSpeseIstruttoria().equals(BigDecimal.ONE));
			vincoloStep6DTO.setFlagEsenzioneMarcaBollo(idfTIntervVincIdro.getFlgEsenzioneMarcaBollo().equals(BigDecimal.ONE));
			vincoloStep6DTO.setnMarcaBollo(idfTIntervVincIdro.getNMarcaBollo());
		}
		List<FatDocumentoAllegato> allegati = documentoAllegatoDAO.findFatDocumentiByIdIntervento(idIntervento);
		vincoloStep6DTO.setDocumentiAllegati(allegati);
		
		return vincoloStep6DTO;
		
	}

	@Override
	public PlainSezioneId creaVarianteProroga(Integer idIntervento, boolean isVariante, ConfigUtente loggedConfig) throws Exception{
		VincoloStep1DTO step1 = getStep1(idIntervento);
		step1.setIdIntervento(null);
		if(isVariante) {
			step1.setFkInterventoPadreVariante(idIntervento);
		}else {
			step1.setFkInterventoPadreProroga(idIntervento);
		}
		PlainSezioneId res = saveStep1(step1, loggedConfig);
		int idInterventoNew = res.getIstanzaId();
		logger.info("creaVarianteProroga - idInterventoNew: " + idInterventoNew);
		idfGeoPlIntervVincidroDAO.duplicateIntervento(idIntervento, idInterventoNew, loggedConfig.getIdConfigUtente());
		
		VincoloStep2DTO step2 = getStep2(idIntervento);
		step2.setIdIntervento(idInterventoNew);
		saveStep2(step2, loggedConfig, idInterventoNew);
		VincoloStep3DTO step3 = getStep3(idIntervento);
		saveStep3(idInterventoNew, loggedConfig, step3);
		VincoloStep4DTO step4 = getStep4(idIntervento);
		saveStep4(idInterventoNew, loggedConfig, step4);
		VincoloStep5DTO step5 = getStep5(idIntervento);
		saveStep5(idInterventoNew, loggedConfig, step5);
		
		
		
		VincoloStep6DTO step6 = getStep6(idIntervento);
		saveStep6(idInterventoNew, loggedConfig, step6);
		
		if(isVariante) {
			skOkVincoloDAO.initVariante(idInterventoNew);
		}
		return res;
	}
	
	@Override
	public void duplicaDocumento(DocumentoAllegato documentoAllegato, Integer idInterventoNew,  ConfigUtente loggedConfig) 
			throws Exception {
		IndexServiceHelper indexSrvHelper = (IndexServiceHelper) applicationContext.getBean("indexServiceHelper");
		byte[] contDownloaded = indexSrvHelper
				.indexDownloadFileVincolo(documentoAllegato.getIdDocumentoAllegato().toString());
		FileUploadForm form = new FileUploadForm();
		form.setName(documentoAllegato.getNomeAllegato());
		form.setNote(documentoAllegato.getNote());
		form.setData(contDownloaded);
		documentoAllegatoDAO.uploadDocumentoVincolo(idInterventoNew, documentoAllegato.getFkTipoAllegato(), 
				form, loggedConfig);
	}

	@Override
	public void updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente) {
		
		idfTIntervVincIdroDAO.updateTitolaritaIntervento(idIntervento, idConfUtente);
	}

	
}
