/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.impl;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import it.csi.idf.idfapi.business.be.impl.dao.*;
import it.csi.idf.idfapi.dto.*;

import it.csi.idf.idfapi.util.*;
import it.csi.idf.idfapi.util.report.ReportUtil;
import net.sf.jasperreports.engine.*;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import it.csi.idf.idfapi.business.be.service.FileGenerator;
import it.csi.idf.idfapi.business.be.service.TagliSelvicolturaliService;
import it.csi.idf.idfapi.business.be.thread.RemoveFileRunnable;
import it.csi.idf.idfapi.business.be.vincolo.dao.DocAllegatoVincoloDAO;
import it.csi.idf.idfapi.business.be.vincolo.dao.IdfTIntervVincIdroDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.InfoAllegatiVincolo;
import it.csi.idf.idfapi.business.be.vincolo.pojo.Superfici;
import it.csi.idf.idfapi.business.be.vincolo.service.impl.WrapperVincoloStepsDaoImpl;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRFontNotFoundException;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;

@Component	
public class FileGeneratorImpl extends SpringSupportedResource implements FileGenerator {
	
	private static final String DICHIARAZIONE_FIRMA_DIGITALE = "firmato digitalmente";
	private static final String JASPER_RESOURCES_CLASSPATH = "classpath:resources/jasperReports/dichiarazione/";
	private static final String NIKE_CHECKBOX_PIC_NAME = "NikeCheckbox.png";
	private static final String BOSCO_SUBREPORT_FILE_NAME = "BoscoSubreport";
	private static final String PARTICELLE_SUBREPORT_FILE_NAME = "ParticelleSubreport";
	private static final String ALLEGATI_VINC_SUBREPORT_FILE_NAME = "AllegatiVincSubreport";
	private static final String JASPER_EXTENSION = ".jasper";
	private static final String EXPORT_EXTENSION_PDF = ".pdf";
	private static final String EXPORT_PATH = "docs";
	private static final String SLASH = "/";
	private static final String UNDERSCORE = "_";
	private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final String DICHIARAZIONE_FIRMA_NO_SIGNATURE = "Dichiarazione presentata per via telematica secondo l'art. 65, comma 1, lettera b) del D.Lgs 82/2005 e s.m.i";
	
	private static final String DICHIARAZIONE_FIRMA_AUTOGRAFA = " ";

	private static final String FORMAT_INDIRIZZO = "%s, %s - %s %s (%s)";
	private final String CLASSNAME = this.getClass().getSimpleName();
	public static final String TEMPLATES_XDOC_RESOURCES_CLASSPATH = "classpath:resources/template/";
	public static final String TEMPLATES_XDOC_TAGLI_PATH = "tagli/";
	DecimalFormat twoDigitformat = new DecimalFormat("####0.00");
	
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	private TipoRichiedenteDAO tipoRichiedenteDAO;
	
	@Autowired
	private IstanzaForestDAO istanzaForestDAO;

	@Autowired
	private StatoInterventoDAO statoInterventoDAO;
	@Autowired
	private InterventoDAO interventoDAO;
	@Autowired
	private EventoDAO eventoDAO;
	@Autowired
	private TagliSelvicolturaliService tagliSelvicolturaliService;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private DichiarazioneSummaryDAO dichiarazioneSummaryDAO;
	
	@Autowired
	private PropCatastoDAO propCatastoDAO;
	
	@Autowired
	private SoggettoDAO soggettoDAO;
	
	@Autowired
	private PropcatastoInterventoDAO propcatastoInterventoDAO;
	
	@Autowired
	private IstanzaCompensazioneDAO istanzaCompensazioneDAO;
	
	@Autowired
	private ProvinciaDAO provinciaDAO;
	
	@Autowired
	private ComuneDAO comuneDAO;
	
	@Autowired	
	private IntervTrasfDAO intervTrasfDAO;
	
	@Autowired	
	private IdfTIntervVincIdroDAO idfTIntervVincIdroDAO;
	
	@Autowired
	private CategoriaProfessionaleDAO categoriaProfessionaleDAO;
	
	@Autowired
	ConfigurationDAO configurationDAO;
	
	@Autowired
	DocAllegatoVincoloDAO docAllegatoVincoloDAO;

	@Autowired
	IntervSelvicoulturaleDAO intervSelvicoulturaleDAO;

	@Autowired
	ParametroApplDAO parametroApplDAO;
	
	@Autowired
	InterventoRicadenzaDAO interventoRicadenzaDAO;

	
	@Override
	public File generateDichiarazione(TipoAllegatoEnum tipoAllegato, int idIntervento)
			throws Exception {
		return generateDichiarazione(tipoAllegato, idIntervento, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public File generateDichiarazione(TipoAllegatoEnum tipoAllegato, int idIntervento, ConfigUtente confUtente) throws Exception {
		logger.info("FileGeneratorImpl - tipoAllegato: " + tipoAllegato.getValue() + " - idIntervento: " + idIntervento);
		if(tipoAllegato.getValue() < 1 || (tipoAllegato.getValue() > 3 && tipoAllegato.getValue() < 21)
				|| tipoAllegato.getValue() > 23) {
			throw new IllegalArgumentException("Method : generateDichiarazione does not support provided TipoAllegatoEnum tipoAllegato argument");
		}
//		CODICE PER AGGIORNARE FILE JASPER
//		Resource res2 = resourceLoader.getResource(JASPER_RESOURCES_CLASSPATH.concat("DICHIARAZIONE_AUTOGRAFA").concat(".jrxml"));
//		String absolutePath2 = res2.getFile().getAbsolutePath();
//		Resource res3 = resourceLoader.getResource(JASPER_RESOURCES_CLASSPATH.concat("DICHIARAZIONE_AUTOGRAFA").concat(JASPER_EXTENSION));
//		String absolutePath3 = res3.getFile().getAbsolutePath();
//		logger.info(absolutePath2 + "<-----path");
//		JasperCompileManager.compileReportToFile(
//				absolutePath2, // the path to the jrxml file to compile
//				absolutePath3);
		Resource res = resourceLoader.getResource(JASPER_RESOURCES_CLASSPATH.concat(BOSCO_SUBREPORT_FILE_NAME).concat(JASPER_EXTENSION));
		String absolutePath = res.getFile().getAbsolutePath();
		logger.info("absolute path: " + absolutePath);
		
		String exportPath = absolutePath.substring(0,absolutePath.indexOf("standalone")) + EXPORT_PATH;
		
		logger.info("exportPath: " + exportPath);

		Map<String, Object> param = null;
		
		TipoIstanzaEnum tipoIstanza = intervTrasfDAO.getTipoIntervento(idIntervento);
		
		logger.info("TipoIstanzaEnum: " + tipoIstanza);
		if(TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA == tipoIstanza) {
			param = getMapParametersTrasformazioniBosco(tipoAllegato, idIntervento);
			param.put("BOSCO_SUBREPORT", compileJasperSubReport(BOSCO_SUBREPORT_FILE_NAME));
		}else if(TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_COMUNALE == tipoIstanza 
				|| TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE == tipoIstanza) {
			param = getMapParametersVincoloIdrogeologico(tipoAllegato, tipoIstanza, idIntervento, confUtente);
			param.put("PARTICELLE_SUBREPORT", compileJasperSubReport(PARTICELLE_SUBREPORT_FILE_NAME));
			param.put("ALLEGATI_SUBREPORT", compileJasperSubReport(ALLEGATI_VINC_SUBREPORT_FILE_NAME));
		}
		
		File directory = new File(exportPath);
		logger.info("Can write on " + directory.getAbsolutePath() +  " -> " + Files.isWritable(directory.toPath()));
		if(!directory.exists()) {
			logger.info("make directory: " + directory.getAbsolutePath() +  " -> " + directory.mkdir());
		}else {
			logger.info("direcotory exist: " + exportPath);
		}

		File generated = new File(exportPath,tipoAllegato.toString().
				concat(UNDERSCORE.concat(TIMESTAMP_FORMAT.format(new Date()))
								.concat(EXPORT_EXTENSION_PDF)));
									
		try {
	        JasperExportManager.exportReportToPdfFile(JasperFillManager.fillReport(
					compileJasperReport(tipoAllegato), param,
					new JRBeanCollectionDataSource(Lists.newArrayList(param))), generated.getPath());	
		}catch(JRFontNotFoundException ex) {
			logger.error(ex.getMessage());
			JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", true);
			logger.error("Set ''net.sf.jasperreports.awt.ignore.missing.font:true");
			if(!"Sans Serif".equals(JRProperties.getProperty("net.sf.jasperreports.default.font.name"))) {
				JRProperties.setProperty("net.sf.jasperreports.default.font.name", "Sans Serif");
				generateDichiarazione(tipoAllegato, idIntervento);
			}else {
				ex.printStackTrace();
			}
		}
		
		if(generated.exists()) {
			logger.info("Exist: " + generated.getPath());
		}else {
			logger.info("Not Exist: " + generated.getPath());
		}
			
		//  GeneratedFileBean gfb = new GeneratedFileBean();
		
		Thread t = new Thread(new RemoveFileRunnable(generated.getAbsolutePath()));
		   t.start();
		
		return generated;
	}

	@Override
	public byte[] generateDichiarazioneXdoc(TipoAllegatoPfaEnum tipoAllegato, int idIntervento, ConfigUtente confUtente) throws Exception {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String baseLogInfo = "[" + CLASSNAME + "::" + methodName + "] ";

		String templateName = "";
		Boolean isDigitale = false;
		switch (tipoAllegato) {
			case COMUNICAZIONE_DI_TAGLIO_IN_BOSCO_AUTOG:
				templateName = "comunicazione.docx";
				break;
			case COMUNICAZIONE_DI_TAGLIO_IN_BOSCO_DIGIT:
				templateName = "comunicazione.docx";
				isDigitale = true;
				break;
			case RICHIESTA_DI_AUTORIZZAZIONE_DI_TAGLIO_IN_BOSCO_AUTOG:
				templateName = "autorizzazione.docx";
				break;
			case RICHIESTA_DI_AUTORIZZAZIONE_DI_TAGLIO_IN_BOSCO_DIGIT:
				templateName = "autorizzazione.docx";
				isDigitale = true;
				break;
		}

		logger.debug(baseLogInfo + "BEGIN");
		Resource res = resourceLoader.getResource(TEMPLATES_XDOC_RESOURCES_CLASSPATH.concat(TEMPLATES_XDOC_TAGLI_PATH).concat(templateName));

		String templatePath = res.getFile().getAbsolutePath();
		try (ByteArrayOutputStream out = new ByteArrayOutputStream(); ){
			InputStream in =  FileGeneratorImpl.class.getResourceAsStream(templatePath);
			IXDocReport report;
			if (in == null) {
				in = new FileInputStream(templatePath);
			}
			System.out.println("<-------------------steps-------------------->");
			TagliSelvicolturaliStep1 step1 = tagliSelvicolturaliService.getStep1(idIntervento);
			System.out.println("<-------------------step 1-------------------->");
			TagliSelvicolturaliStep2 step2 = tagliSelvicolturaliService.getStep2(idIntervento, confUtente);
			System.out.println("<-------------------step 2-------------------->");
			TagliSelvicolturaliStep3 step3 = tagliSelvicolturaliService.getStep3(idIntervento, confUtente);
			System.out.println("<-------------------step 3-------------------->");
			TagliSelvicolturaliStep4 step4 = tagliSelvicolturaliService.getStep4(idIntervento, confUtente);
			System.out.println("<-------------------step 4-------------------->");
			TagliSelvicolturaliStep5 step5 = tagliSelvicolturaliService.getStep5(idIntervento, confUtente);
			GeoPLProvincia plProvincia;
			System.out.println("<-------------------steps END-------------------->");
			ParametroAppl urlPrivacy = parametroApplDAO.getParamertroByTipo(TipoParametroApplEnum.URL_PRIVACY);
			
			report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
			
			FieldsMetadata metadata = report.createFieldsMetadata();
			metadata.load( "allegati", FatDocumentoAllegato.class, true );
			metadata.load( "particelle", PlainParticelleCatastali.class, true );
			metadata.load( "specieInteressate", SpecieInterventoReport.class, true );
			metadata.load( "tipiEsbosco", GenericStringObjectForReport.class, true );

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			IContext context = report.createContext();
			//destinatario
			TSoggetto destinatario = dichiarazioneSummaryDAO.getSoggettoCompetenteRegionale(
					idIntervento,
					AmbitoIstanzaEnum.TAGLIO_IN_BOSCO);

			Comune comuneDestinatario = comuneDAO.findComuneByID(destinatario.getFkComune());

            String denominazionePFA = interventoDAO.findDenominazionePianoByID(step5.getIntervSelvicolturale().getIdgeoPlPfa());

			context.put("destinatario", "REGIONE PIEMONTE");
			context.put("destinatarioSettore", "Settore " + destinatario.getDenominazione());
			context.put("destinatarioIndirizzo", destinatario.getIndirizzo() + " n. " + destinatario.getCivico());
			context.put("destinatarioCap", destinatario.getCap());
			context.put("destinatarioComune", comuneDestinatario.getDenominazioneComune());
			context.put("destinatarioTel", destinatario.getNrTelefonico());
			context.put("destinatarioEmail", destinatario.geteMail());
			context.put("destinatarioPec", destinatario.getPec());

			context.put("urlPrivacy", urlPrivacy.getParametroApplChar());
			//Tabella Istanza
			Intervento intervento = step4.getIntervento();
			IntervSelvicolturaleFat intervSelvicolturale = step4.getIntervSelvicolturale();
			StatoIntervento statoIntervento = statoInterventoDAO.getStatoByIdIntervento(intervento.getIdIntervento());
			IstanzaForest istanzaForest = istanzaForestDAO.getByIdIntervento(idIntervento);
			TSoggetto soggCompilatore = soggettoDAO.findSoggettoByIdConfigUtente(intervento.getFkConfigUtenteCompilatore());
			String particelleForest = interventoDAO.getStringParticelleForestaliByIntervento(idIntervento);
			if(particelleForest != null){
				context.put("particelleForest",particelleForest);
			}

			TipoAccreditamentoEnum tipoAcc = TipoAccreditamentoEnum.fromInteger(confUtente.getFkTipoAccreditamento());
			String compilatore = Strings.EMPTY;
			String conforme = step5.getIntervSelvicolturale().getFlgConformeDeroga();

			switch (tipoAcc) {
				case SPORTELLO:
					TSoggetto userSportello = soggettoDAO.findSportelloByIdConfigUtente(confUtente.getIdConfigUtente());
					compilatore = String.format("%s %s - SPORTELLO FORESTALE: %s ",
							soggCompilatore.getCognome(),
							soggCompilatore.getNome(),
							userSportello.getDenominazione());
					break;
				case PROFESSIONISTA:
					compilatore += String.format("%s %s - Professionista delegato dal richiedente (PIVA: %s ) ",
							soggCompilatore.getCognome(),
							soggCompilatore.getNome(),
							soggCompilatore.getPartitaIva());
					break;
				default: {
					compilatore = String.format("%s %s  ",
							soggCompilatore.getCognome(),
							soggCompilatore.getNome());
				}
			}

			TrasformazSelvicolturali trasf = step1.getTrasformazSelvicolturale();
			String strTrasf = trasf == null ? "" : String.format("N: %s / %s / %s / %s / %s ",
					trasf.getNrIstanza(),
					trasf.getAnno(),
					trasf.getRichiedente(),
					trasf.getComune(),
					trasf.getCompensazione()
			);



			context.put("annoIntervento", toStr(istanzaForest.getDataInserimento().getYear()));
			context.put("nrIstanzaForestale", toStr(istanzaForest.getNrIstanzaForestale()));
			context.put("descrizione", "");
			context.put("tipoDiBosco", "");
			context.put("dataCompilazione", intervSelvicolturale.getDataInserimento().format(formatter));
			context.put("dataInvio", "");
			context.put("operatore", compilatore);
			context.put("dataPresuntaInizioIntervento", intervSelvicolturale.getDataPresaInCarico().format(formatter));


			context.put("proprieta",  step1.getProprieta().getDescrProprieta() );
			context.put("annataSilvana",  intervSelvicolturale.getAnnataSilvana() );
			context.put("statoIntervento",  statoIntervento.getDescrStatoIntervento() );
			context.put("interventoCompensativo", strTrasf);
			context.put("categoriaIntervento", step1.getCategoriaSelvicolturale().getDescrCategoriaSelvicolturale());
			context.put("denominazionePFA", denominazionePFA);

			if(conforme != null){
                if (conforme.equals("C")) {
                    context.put("conforme", "Conforme");
                } else {
                    context.put("conforme", "Deroga");
                }
			}

			if(step5.getIntervSelvicolturale().getIdEvento() > 0){
				EventoDTO evento = eventoDAO.findEventoById(step5.getIntervSelvicolturale().getIdEvento());
				context.put("evento", evento.getProgressivoEventoPfa() + "/" + evento.getNomeBreve());
			}

			//Tabella Richiedente
			TipoTitolaritaEnum pdo = step2.getSoggetto().getPersonaDatiOption();
			boolean isPf = pdo.equals(TipoTitolaritaEnum.PF) ||
							pdo.equals(TipoTitolaritaEnum.RF) ||
							pdo.equals(TipoTitolaritaEnum.SF);
			context.put("isPersonaFisica", isPf);
			TipoRichiedente tipoRichiedente = tipoRichiedenteDAO.getTipoRichiedenteById(step2.getTipoRichiedenteId());
			context.put("tipologia", toStr(tipoRichiedente.getDescrTipoRichiedente()));
			PersonaFisGiu soggetto = step2.getSoggetto();
			String ragioneSociale = soggetto.getDenominazione() != null ? soggetto.getDenominazione() : toStr(soggetto.getCognome()) + " " + toStr(soggetto.getNome());
			context.put("ragioneSociale", ragioneSociale);
			context.put("codiceFiscale", toStr(soggetto.getCodiceFiscale()));
			context.put("partitaIva", toStr(soggetto.getPartitaIva()));
			plProvincia = provinciaDAO.findProvinciaByIstatProv(soggetto.getComune().getIstatProv());
			context.put("sedeLegale", String.format(FORMAT_INDIRIZZO, toStr(soggetto.getIndirizzo()), toStr(soggetto.getCivico()),
					toStr(soggetto.getCap()), toStr(soggetto.getComune().getDenominazioneComune()), toStr(plProvincia.getDenominazioneProv())));
			context.put("iscritto", soggetto.getNumAlboForestale() != null ? "SI" : "NO");
			context.put("iscrittoNumero", toStr(soggetto.getNumAlboForestale()));
			context.put("telefono", toStr(soggetto.getNrTelefonico()));
			context.put("email", toStr(soggetto.geteMail()));
			context.put("pec", toStr(soggetto.getPec()));

			//tabella utilizzatore
			TipoUtilizzatoreTagliEnum tipoUtilizzatore = step2.getTipoUtilizzatore();
			context.put("tipoUtilizzatore", toStr(tipoUtilizzatore));
			PersonaFisGiu utilizzatore = step2.getUtilizzatore();
			if (null != utilizzatore) {
				context.put("utilizzatoreRagioneSociale", utilizzatore.getDenominazione() != null ? utilizzatore.getDenominazione() : toStr(utilizzatore.getCognome()) + " " + toStr(utilizzatore.getNome()));
				context.put("utilizzatoreCodiceFiscale", toStr(utilizzatore.getCodiceFiscale()));
				context.put("utilizzatorePartitaIva", toStr(utilizzatore.getPartitaIva()));
				plProvincia = provinciaDAO.findProvinciaByIstatProv(utilizzatore.getComune().getIstatProv());
				context.put("utilizzatoreSedeLegale", String.format(FORMAT_INDIRIZZO,
						toStr(utilizzatore.getIndirizzo()), toStr(utilizzatore.getCivico()),
						toStr(utilizzatore.getCap()), toStr(utilizzatore.getComune().getDenominazioneComune()),
						toStr(plProvincia.getDenominazioneProv())));
				context.put("utilizzatoreIscritto", utilizzatore.getNumAlboForestale() != null ? "SI" : "NO");
				context.put("utilizzatoreIscrittoNumero", toStr(utilizzatore.getNumAlboForestale()));
				context.put("utilizzatoreTelefono", toStr(utilizzatore.getNrTelefonico()));
				context.put("utilizzatoreFax", "");
				context.put("utilizzatoreEmail", toStr(utilizzatore.geteMail()));
				context.put("utilizzatorePec", toStr(utilizzatore.getPec()));
			}


			//LOCALIZZAZIONE, VINCOLI AMBIENTALI E STRUMENTI DI PIANIFICAZIONE
			List<PlainParticelleCatastali> particelleCatastalis = step3.getParticelleCatastali();
			BigDecimal superficieTotaleIntervento = BigDecimal.ZERO;
			BigDecimal superficieTotaleCatastale = BigDecimal.ZERO;
			List<String> comuni = new ArrayList<>();
			if(particelleCatastalis != null) {
				for (PlainParticelleCatastali plainParticelleCatastali : particelleCatastalis) {
					comuni.add(plainParticelleCatastali.getComune().getDenominazioneComune());
					if(plainParticelleCatastali.getSupIntervento() != null) {
						superficieTotaleIntervento = superficieTotaleIntervento.add(plainParticelleCatastali.getSupIntervento());
						superficieTotaleCatastale = superficieTotaleCatastale.add(plainParticelleCatastali.getSupCatastale());
					} else {
						plainParticelleCatastali.setSupIntervento(BigDecimal.ZERO);//superficie intervento nullabile in fase di test
					}
				}	
			}
			Collections.sort(comuni);
			Set comuniUnique = new LinkedHashSet(comuni);
			context.put("comuni", String.join(",", comuniUnique));
			
			context.put("superficieCatastaleTotale", superficieTotaleCatastale);
			context.put("superficieIntervento", superficieTotaleIntervento);
			
			
			context.put("particelle", particelleCatastalis != null ? particelleCatastalis : Collections.EMPTY_LIST);
			
			System.out.println("step3.getFasciaAltimetrica--->");
			System.out.println("step3.getFasciaAltimetrica--->" + step3.getFasciaAltimetrica());
			String localita = interventoDAO.findInterventoByIdIntervento(idIntervento).getLocalita();
			logger.info(localita + "<--------------localita");
			context.put("isLocalita" ,false);
			context.put("localita","localita vuota");
			if(localita != null){
				context.put("isLocalita" ,true);
				context.put("localita",localita);
			}
			if(step3.getFasciaAltimetrica()!=null) {
				context.put("zonaAltimetrica", String.format(" da %s a %s m.s.l.m", step3.getFasciaAltimetrica().getFasciaAltimetricaMIN(), step3.getFasciaAltimetrica().getFasciaAltimetricaMAX()));
			} else {
				context.put("zonaAltimetrica","nessuna");
			}
			
			
			List<String> ricadenze = new ArrayList<>();
			if(step3.getRicadenzaAreeProtette() != null) {
				for (RicadenzaInformazioni info : step3.getRicadenzaAreeProtette()) {
					if(info.getNome() != null) {
						System.out.println(info.toStringRreport());
						ricadenze.add(info.toStringRreport());
					}
				}
			}
			
			context.put("ricadenzaAreeProtette", ricadenze.isEmpty() ? "nessuna" : StringUtils.join(ricadenze, ", "));
			ricadenze.clear();

			if(step3.getRicadenzaNatura2000() != null) {
				for (RicadenzaInformazioni info : step3.getRicadenzaNatura2000()) {
					if(info.getNome() != null) {
						ricadenze.add(info.toStringRreport());
					}
				}
			}
			context.put("ricadenzaNatura2000", ricadenze.isEmpty() ? "nessuna" : StringUtils.join(ricadenze, ", "));
			ricadenze.clear();

			if(step3.getRicadenzaPopolamentiDaSeme() != null) {
				for (RicadenzaInformazioni info : step3.getRicadenzaPopolamentiDaSeme()) {
					if(info.getNome() != null) {
						ricadenze.add(info.toStringRreport());
					}
				}
			}
			context.put("ricadenzaPopolamenti",  ricadenze.isEmpty() ? "nessuna" : StringUtils.join(ricadenze, ", "));
			ricadenze.clear();

			if(step3.getRicadenzaPortaSeme() != null) {
				for (RicadenzaPortaseme info : step3.getRicadenzaPortaSeme()) {
					if(info.getSpecie() != null) {
						ricadenze.add(info.toStringRreport());
					}
				}
			}
			context.put("ricadenzaPortaseme",  ricadenze.isEmpty() ? "nessuna" : StringUtils.join(ricadenze, ", "));
			ricadenze.clear();

			if(step3.getRicadenzaCategorieForestali() != null) {
				for (RicadenzaInformazioni info : step3.getRicadenzaCategorieForestali()) {
					if(info.getNome() != null) {
						ricadenze.add(info.toStringRreport());
					}
				}
			}
			context.put("ricadenzaCategorieForestali", ricadenze.isEmpty() ? "nessuna" : StringUtils.join(ricadenze, ", "));
			ricadenze.clear();

			context.put("ricadenzaPfa",  step3.getRicadenzaPfa().isEmpty() ? "nessuna" : "si");
			ricadenze.clear();
			
			///Ricadenza in Piano Paesaggistico Regionale
			
			
			boolean isPPR=false;
			 System.out.println("step3.getIdIntervento(): " + idIntervento);
				RicadenzaIntervento ricadenzaIntervento = new RicadenzaIntervento();
			List<RicadenzaIntervento>  ricadenzaPianoPaesaggisticoRegionale = interventoRicadenzaDAO.getInterventosByIdIntervento(idIntervento);
			
			
			if(!ricadenzaPianoPaesaggisticoRegionale.isEmpty() && ricadenzaPianoPaesaggisticoRegionale != null) {
				isPPR=true;
				context.put("isRicadenzaPianoPaesaggisticoRegionale", isPPR);
				for (RicadenzaIntervento ricadenza : ricadenzaPianoPaesaggisticoRegionale) {
					ricadenzaIntervento.setId(idIntervento);
					ricadenzaIntervento.setTipoVincolo(ricadenza.getTipoVincolo());
					ricadenzaIntervento.setNomeVincolo(ricadenza.getNomeVincolo());
					ricadenzaIntervento.setProvvedimento(ricadenza.getProvvedimento());
					ricadenzaIntervento.setPercentuale(ricadenza.getPercentuale());
					// aggiungi solo se non esiste già il record
					  System.out.println("ricadenzaPianoPaesaggisticoRegionale: " + ricadenzaIntervento.getPercentuale());
					context.put("ricadenzaPianoPaesaggisticoRegionale", ricadenzaIntervento);
				}
				
			}else {				
				context.put("isRicadenzaPianoPaesaggisticoRegionale", isPPR);
			}
			
			//context.put("ricadenzaPianoPaesaggisticoRegionale",  step3.getRicadenzaPianoPaesaggisticoRegionale()==null ? "nessuna" : "si");
			//ricadenze.clear();

			//DESCRIZIONE INTERVENTO
			context.put("descrizioneIntervento", toStr(step4.getIntervento().getDescrizioneIntervento()));
			String govPrinc = step4.getIntervSelvicolturale().getGovernoPrincipale().getIdGoverno() == 0 ?
					" - " :
					step4.getIntervSelvicolturale().getGovernoPrincipale().getDescrGoverno();
			context.put("governoPrincipale", govPrinc);
			context.put("interventoPrincipale", toStr(step4.getIntervSelvicolturale().getTipoInterventoPrincipale().getDescrTipoIntervento()));
			context.put("superficieInteressataPrincipale", toStr(step4.getIntervSelvicolturale().getSuperficieInt1Ha()));

			String govSec = step4.getIntervSelvicolturale().getGovernoSecondario().getIdGoverno() == 0 ?
					" - " :
					step4.getIntervSelvicolturale().getGovernoSecondario().getDescrGoverno();
			context.put("governoSecondario", govSec);
			context.put("interventoSecondario", toStr(step4.getIntervSelvicolturale().getTipoInterventoSecondario().getDescrTipoIntervento()));
			context.put("superficieInteressataSecondaria", toStr(step4.getIntervSelvicolturale().getSuperficieInt2Ha()));
			//context.put("superficieTotale", toStr(step4.getIntervento().getSuperficieInteressata()));
			context.put("superficieTotale", superficieTotaleIntervento);

			/**
			 * TIPI DI ESBOSCO
			 */
			LinkedList<GenericStringObjectForReport> tipiEsbosco = new LinkedList<>();
			for (TagliHeading tagliHeading : step4.getHeadings()) {
				if (tagliHeading.isChecked()) {
					GenericStringObjectForReport th = new GenericStringObjectForReport();
					th.setS1(tagliHeading.getName());
					tipiEsbosco.add(th);
					for (TagliSubHeading sub : tagliHeading.getSubheadings()) {
						GenericStringObjectForReport thSub = new GenericStringObjectForReport();
						if (sub.isChecked()) {
							thSub.setS2(sub.getDescrizione() + " (" + sub.getCodice() + ") ");
							tipiEsbosco.add(thSub);
						}
					}
				}
			}
			
			context.put("tipiEsbosco", tipiEsbosco.isEmpty() ? "nessuna" : tipiEsbosco);

			String note  = step4.getIntervSelvicolturale().getNoteEsbosco() == null ?
					" " :
						step4.getIntervSelvicolturale().getNoteEsbosco();
			
			context.put("noteEsbosco", note );
			
			/**
			 * SPECIE INTERESSATE
			 */
			Integer totalePiante = 0;
			Float totaleM3 = 0f;
			Float totaleT = 0f;
			Float totaleQ = 0f;

			LinkedList<SpecieInterventoReport> specieInteressate = new LinkedList<>();
			if(step4.getSpecieInteressate() != null) {
				for (SpeciePfaIntervento speciePfaIntervento : step4.getSpecieInteressate()) {
					if(speciePfaIntervento.getNumPiante() != null) {
						totalePiante += speciePfaIntervento.getNumPiante();
					}

					SpecieInterventoReport spr = new SpecieInterventoReport();
					spr.setNome(speciePfaIntervento.getSpecie().getVolgare());
					spr.setNumPiante(speciePfaIntervento.getNumPiante().toString());

					spr.setVolumeM3(ReportUtil.toStringFormat(speciePfaIntervento.getVolumeSpecia()));
					totaleM3 += speciePfaIntervento.getVolumeSpecia();

					Float volT = ReportUtil.m3ToTonF(
							speciePfaIntervento.getVolumeSpecia(),
							speciePfaIntervento.getSpecie().getDensita());
					spr.setVolumeT(ReportUtil.toStringFormat(volT));
					totaleT += volT;

					Float volQ = ReportUtil.m3ToQF(
							speciePfaIntervento.getVolumeSpecia(),
							speciePfaIntervento.getSpecie().getDensita());
					spr.setVolumeQ(ReportUtil.toStringFormat(volQ));
					totaleQ += volQ;

					specieInteressate.add(spr);

					if(speciePfaIntervento.getSpecieFinalita() != null) {
						int currentIteration = 0;
						for (SpecieInterventoFinalita interventoFinalita : speciePfaIntervento.getSpecieFinalita()) {
							currentIteration++;
							logger.info(currentIteration);
							SpecieInterventoReport sprf = new SpecieInterventoReport();
							sprf.setFinalita(interventoFinalita.getDescrFinalitaTaglio());
							sprf.setAutoConsumo(ReportUtil.toStringFormat(interventoFinalita.getPercAutoconsumo()));
							if(currentIteration==7) {
								if(interventoFinalita.getPercCommerciale()==null || interventoFinalita.getPercCommerciale()==0f) {
									sprf.setCommerciale("");
								}else {
									sprf.setCommerciale(ReportUtil.toStringFormat(interventoFinalita.getPercCommerciale()));
									
								}
							}else {
								sprf.setCommerciale(ReportUtil.toStringFormat(interventoFinalita.getPercCommerciale()));
								
							}
							
							Float perc = (interventoFinalita.getPercAutoconsumo() == null ? 0 : interventoFinalita.getPercAutoconsumo()) +
									(interventoFinalita.getPercCommerciale() == null ? 0 : interventoFinalita.getPercCommerciale());


							sprf.setVolumeM3(ReportUtil.toStringFormat(speciePfaIntervento.getVolumeSpecia() / 100 * perc));
							sprf.setVolumeT(ReportUtil.toStringFormat(volT / 100 * perc));
							sprf.setVolumeQ(ReportUtil.toStringFormat(volQ / 100 * perc));

							specieInteressate.add(sprf);
							currentIteration++;
						}
					}
				}
			}
			Integer ramagliaM3 = step4.getIntervSelvicolturale().getVolumeRamagliaM3();//verify a Integer
			Float ramagliaT = ReportUtil.m3ToTonF(ramagliaM3.floatValue(), 1f);
			Float ramagliaQ = ReportUtil.m3ToQF(ramagliaM3.floatValue(), 1f);
			context.put("ramagliaM3", ReportUtil.toStringFormat(ramagliaM3));
			context.put("ramagliaT", ReportUtil.toStringFormat(ramagliaT));
			context.put("ramagliaQ", ReportUtil.toStringFormat(ramagliaQ));

			totaleM3 += ramagliaM3;
			totaleT += ramagliaT;
			totaleQ += ramagliaQ;

			context.put("specieInteressate",  specieInteressate);
			context.put("totalePiante",  toStr(totalePiante));
			context.put("totaleM3",  ReportUtil.toStringFormat(totaleM3));
			context.put("totaleT",  ReportUtil.toStringFormat(totaleT));
			context.put("totaleQ",  ReportUtil.toStringFormat(totaleQ));


			//Tabella PROFESSIONISTA
			PersonaFisGiu professionista = step5.getTecnicoForestale();
			if(professionista != null) {
				context.put("hasTecnicoForestale", true);
				context.put("professionistaNome", professionista.getDenominazione() != null ? professionista.getDenominazione() : toStr(professionista.getCognome()) + " " + toStr(professionista.getNome()));
				context.put("professionistaCF", toStr(professionista.getCodiceFiscale()));
				context.put("professionistaPiva", toStr(professionista.getPartitaIva()));
				plProvincia = provinciaDAO.findProvinciaByIstatProv(professionista.getComune().getIstatProv());
				context.put("professionistaResidenza", String.format(FORMAT_INDIRIZZO, toStr(professionista.getIndirizzo()), toStr(professionista.getCivico()),
						toStr(professionista.getCap()), toStr(professionista.getComune().getDenominazioneComune()), toStr(plProvincia.getDenominazioneProv())));
				context.put("professionistaTelefono", toStr(professionista.getNrTelefonico()));
				context.put("professionistaEmail", toStr(professionista.geteMail()));
				context.put("professionistaPec", toStr(professionista.getPec()));

				String ordine = String.format("%s (%s)  ",
						professionista.getProvIscrizioneOrdine().getDenominazioneProv() ,
						professionista.getProvIscrizioneOrdine().getSiglaProv());
				context.put("professionistaOrdine",  professionista.getNrIscrizioneOrdine() != null ? ordine : "NO");

				context.put("professionistaNrIscrizione", toStr(professionista.getNrIscrizioneOrdine()));
				context.put("professionistaIdMartello",  toStr(professionista.getNrMartelloForestale()));				
			}

			//ALLEGATI
			List<FatDocumentoAllegato> allegati = step5.getAllegati();
			context.put("esistonoAllegati", !allegati.isEmpty());

			if(allegati.size() > 0)
				context.put("allegati", allegati);

			String noteFinaliRichiedente = step5.getNoteFinaliRichiedente();
			if(noteFinaliRichiedente != null && !noteFinaliRichiedente.isEmpty()) {
				context.put("isNoteFinaliRichiedente", true);
				context.put("noteFinaliRichiedente", noteFinaliRichiedente);
			}

			String motivazione = step5.getMotivazione();
			if(motivazione != null && !motivazione.isEmpty()) {
				context.put("isMotivazione", true);
				context.put("motivazione", motivazione);
			}

			context.put("isFirmaDigitale", isDigitale);
			context.put("dataLuogo", LocalDate.now().format(dataFormatter));

			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);

			report.convert(context, options, out);
			in.close();
			return out.toByteArray();
		} catch (Exception e) {
			logger.error(baseLogInfo + " ERROR " ,e);
		} finally {
			logger.debug(baseLogInfo + "END");
		}
		return new byte[0];

	}

	
	public static String toStr(Object obj) {
		if(obj == null) {
			return "";
		} 
		return obj.toString();
	}
	
	private Map<String, Object> getMapParametersTrasformazioniBosco(TipoAllegatoEnum tipoAllegato, Integer idIntervento) throws IOException {
		GeneratedFileTrasfParameters genFileParams = dichiarazioneSummaryDAO.getSummaryTrasformazioni(idIntervento);
		TSoggetto soggettoCompetente = dichiarazioneSummaryDAO.getSoggettoCompetenteRegionale(idIntervento,AmbitoIstanzaEnum.TRASFORMAZIONE_DEL_BOSCO);
		genFileParams.setRegionaleSoggetto(fillSoggettoCompetente(soggettoCompetente));
		genFileParams.setTipoAllegato(tipoAllegato);
		genFileParams.setPropCatasti(propCatastoDAO.getDichPropCatastosByPropcatastoIntervento(
				propcatastoInterventoDAO.getAllPropcatastoByIdIntervento(idIntervento)));
		genFileParams.setIstanzeCompensazione(istanzaCompensazioneDAO.getAllByFkIntervento(idIntervento));
		return fillMapParametersTrasformazioniBosco(genFileParams);
	}
	
	private String fillSoggettoCompetente(TSoggetto soggettoCompetente) {
		StringBuilder sogReg = new StringBuilder(soggettoCompetente.getDenominazione());
		if(soggettoCompetente.getIndirizzo() != null) {
			sogReg.append("\r\n").append(soggettoCompetente.getIndirizzo());
			}
		if(soggettoCompetente.getCap() != null) {
			sogReg.append("\r\n").append(soggettoCompetente.getCap());
		}
		if(soggettoCompetente.getFkComune() != null) {
			Comune comune = comuneDAO.findComuneByID(soggettoCompetente.getFkComune());
			sogReg.append(" - ").append(comune.getDenominazioneComune());
		}
		if(soggettoCompetente.getNrTelefonico() != null) {
			sogReg.append("\r\nTel: ").append(soggettoCompetente.getNrTelefonico());
		}
		if(soggettoCompetente.geteMail() != null) {
			sogReg.append("\r\nEmail: ").append(soggettoCompetente.geteMail());
		}
		if(soggettoCompetente.getPec() != null) {
			sogReg.append("\r\nPec: ").append(soggettoCompetente.getPec());
		}
		return sogReg.toString();
	}
	
	private Map<String, Object> fillMapParametersTrasformazioniBosco(GeneratedFileTrasfParameters genFileParams) throws IOException {
		Map<String, Object> param = new HashMap<>();
		param.put("nikeImg", resourceLoader
				.getResource(JASPER_RESOURCES_CLASSPATH.concat(NIKE_CHECKBOX_PIC_NAME)).getInputStream());

		param.put("richCognome",genFileParams.getRichCognome());
		param.put("richNome",genFileParams.getRichNome());
		
		 if(genFileParams.getRichPartitaIva()!=null) {
			param.put("richRagSociale",genFileParams.getRichRagSociale());
			param.put("richPartitaIva",genFileParams.getRichPartitaIva());			
		 }
		param.put("richCodFiscale",genFileParams.getRichCodiceFiscale());
		param.put("richIndirizzo",genFileParams.getRichIndirizzo());
		param.put("richCivico",genFileParams.getRichCivico());
		param.put("richProv",genFileParams.getRichProvincia());
		param.put("richCap",genFileParams.getRichCap());
		param.put("richComune",genFileParams.getRichComune());
		param.put("richTel",genFileParams.getRichTelefonico());
		


		if(genFileParams.getRichEmail() != null) {
			param.put("richEmail",genFileParams.getRichEmail());
		}
		if(genFileParams.getRichPec() != null){
			param.put("richPec",genFileParams.getRichPec());
		}

		
		String istanzeCompensazione = "(con istanze di taglio n. .................... del ..................);";

		if(SuperficieCompensationEnum.N.equals(genFileParams.getFlgCompensazione())) {
			if(genFileParams.isFlgArt7a() || genFileParams.isFlgArt7b() || genFileParams.isFlgArt7c() || genFileParams.isFlgArt7d()) {
				param.put("flgNonNecessaria", true);
				param.put("flgArt7a", genFileParams.isFlgArt7a());			
				param.put("flgArt7b", genFileParams.isFlgArt7b());			
				param.put("flgArt7c", genFileParams.isFlgArt7c());			
				param.put("flgArt7d", genFileParams.isFlgArt7d());			
				param.put("flgArt7dBis", null);
			}else if(genFileParams.isFlgArt7A21() || genFileParams.isFlgArt7B21() || genFileParams.isFlgArt7C21()
					|| genFileParams.isFlgArt7D21() || genFileParams.isFlgArt7Dter21() 
					|| genFileParams.isFlgArt7Dquater21() || genFileParams.isFlgArt7Dquinquies21()) {
				param.put("flgNonNecessaria21", true);
				param.put("flgArt7a21", genFileParams.isFlgArt7A21());			
				param.put("flgArt7b21", genFileParams.isFlgArt7B21());			
				param.put("flgArt7c21", genFileParams.isFlgArt7C21());			
				param.put("flgArt7d21", genFileParams.isFlgArt7d());			
				param.put("flgArt7dter21", genFileParams.isFlgArt7Dter21());			
				param.put("flgArt7dquater21", genFileParams.isFlgArt7Dquater21());			
				param.put("flgArt7dquinquies21", genFileParams.isFlgArt7Dquinquies21());
			}
						
		} else if(SuperficieCompensationEnum.M.equals(genFileParams.getFlgCompensazione()) ||
				SuperficieCompensationEnum.F.equals(genFileParams.getFlgCompensazione())) {
			param.put("profCognome", genFileParams.getProfCognome());
			param.put("profNome", genFileParams.getProfNome());
			param.put("profCodiceFiscale", genFileParams.getProfCodiceFiscale());
			param.put("profProvinciaOrdine", genFileParams.getProfProvinciaOrdine());
			param.put("profNIscrizione", genFileParams.getProfNIscrizione());
			param.put("profTelefonico", genFileParams.getProfTelefonico());
			param.put("profPec", genFileParams.getProfPec());
			param.put("compenzazioneEuro", genFileParams.getCompenzazioneEuro());
			param.put("compenzazioneEuroReale", genFileParams.getCompenzazioneEuroReale());
			param.put("compensazioneNote", genFileParams.getNoteCompenzazioneEuroReale());
		
			if(SuperficieCompensationEnum.M.equals(genFileParams.getFlgCompensazione())) {
				param.put("flgNecessaria", true);
				param.put("isDichCompMonetaria", true);
			} else if(SuperficieCompensationEnum.F.equals(genFileParams.getFlgCompensazione())) {
				param.put("flgNecessaria", true);
				param.put("isDichCompFisica", true);
				List<IstanzaCompensazione> listIstanze = genFileParams.getIstanzeCompensazione();
				param.put("isDichIstanzeCompensazione", listIstanze != null && listIstanze.size() > 0);
				istanzeCompensazione = getFormatedIstanzi(listIstanze);
			}
		}
		
		param.put("nrIstanza", "" + genFileParams.getNrIstanza());
		
		param.put("istanzeCompensazione", istanzeCompensazione);
		param.put("isFormaGoverno1", genFileParams.isFormaGovernoFlg1());
		param.put("isFormaGoverno2", genFileParams.isFormaGovernoFlg2());
		param.put("isCategForest1", genFileParams.isCategForestFlg1());
		param.put("isCategForest2", genFileParams.isCategForestFlg2());
		param.put("isCategForest3", genFileParams.isCategForestFlg3());
		param.put("isUbicazione1", genFileParams.isUbicazioneFlg1());
		param.put("isUbicazione2", genFileParams.isUbicazioneFlg2());
		param.put("isUbicazione3", genFileParams.isUbicazioneFlg3());
		param.put("isDestVinc1", genFileParams.isDestVincFlg1());
		param.put("isDestVinc2", genFileParams.isDestVincFlg2());
		param.put("isDestVinc3", genFileParams.isDestVincFlg3());
		param.put("isTipologia1", genFileParams.isTipologiaFlg1());
		param.put("isTipologia2", genFileParams.isTipologiaFlg2());
		param.put("isTipologia3", genFileParams.isTipologiaFlg3());
		
		param.put("isDichProprietario", genFileParams.isDichProprietario());
		param.put("isDichNonProprietario", !genFileParams.isDichProprietario());
		param.put("isDichDissenso", genFileParams.isDichDissenso());
		param.put("isDichAutPaesaggistica", genFileParams.isDichAutPaesaggistica());
		param.put("dichDataPaesaggistica", genFileParams.getDichDataPaesaggistica() != null?
				genFileParams.getDichDataPaesaggistica().format(dataFormatter):"");
		param.put("dichNrPaesaggistica", genFileParams.getDichNrPaesaggistica());
		param.put("dichEntePaesaggistica", genFileParams.getDichEntePaesaggistica());
		param.put("isDichAutVidro", genFileParams.isDichAutVidro());
		param.put("dichDataVidro", genFileParams.getDichDataVidro() !=  null? 
				genFileParams.getDichDataVidro().format(dataFormatter):"");
		param.put("dichNrVidro", genFileParams.getDichNrVidro());
		param.put("dichEnteVidro", genFileParams.getDichEnteVidro());
		param.put("isDichAutIncidenza", genFileParams.isDichAutIncidenza());
		param.put("dichDataIncidenza", genFileParams.getDichDataIncidenza() != null ?
				genFileParams.getDichDataIncidenza().format(dataFormatter):"");
		param.put("dichNrIncidenza", genFileParams.getDichNrIncidenza());
		param.put("dichEnteIncidenza", genFileParams.getDichEnteIncidenza());
		param.put("isDichAltriPareri",
				genFileParams.getDichAltriPareri() != null && !genFileParams.getDichAltriPareri().trim().equals(""));
		param.put("dichAltriPareri", genFileParams.getDichAltriPareri());
		
		if(genFileParams.getTipoAllegato().equals(TipoAllegatoEnum.DICHIARAZIONE)) {
			param.put("dataLuogo", (LocalDate.now()).format(dataFormatter));
			param.put("dichiaranteFirma", DICHIARAZIONE_FIRMA_NO_SIGNATURE);
		} else if(genFileParams.getTipoAllegato().equals(TipoAllegatoEnum.DICHIARAZIONE_DIGITALE)) {
			param.put("dataLuogo", LocalDate.now());
			param.put("dichiaranteFirma", DICHIARAZIONE_FIRMA_DIGITALE);
		}else if(genFileParams.getTipoAllegato().equals(TipoAllegatoEnum.DICHIARAZIONE_AUTOGRAFA)) {
			param.put("dataLuogo", LocalDate.now());
			param.put("dichiaranteFirma", DICHIARAZIONE_FIRMA_AUTOGRAFA);
		}
		
		
		
		
		param.putAll(getBoscoSubreportResources(genFileParams.getPropCatasti()));
		param.put("superficieInteressata", genFileParams.getSuperficieInteressata() == null ? null
				: genFileParams.getSuperficieInteressata().toString());
		
		param.put("regionaleSoggetto", genFileParams.getRegionaleSoggetto()); //TODO fill with proper value
		
		return param;
	}
	
	private Map<String, Object> getMapParametersVincoloIdrogeologico(TipoAllegatoEnum tipoAllegato, 
			TipoIstanzaEnum tipoIstanza, Integer idIntervento, ConfigUtente confUtente ) throws Exception {
		Map<String, Object> param = new HashMap<>();
		InputStream imgStream = resourceLoader.
				getResource(JASPER_RESOURCES_CLASSPATH.concat(NIKE_CHECKBOX_PIC_NAME)).getInputStream();
		param.put("nikeImg", imgStream);
		TSoggetto soggettoCompetente = null;
		GeneratedFileVincoloParameters genFileParams = dichiarazioneSummaryDAO.getSummaryVincolo(idIntervento);
		try {
			if(tipoIstanza == TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE) {
					soggettoCompetente = dichiarazioneSummaryDAO.getSoggettoCompetenteRegionale(idIntervento,AmbitoIstanzaEnum.VINCOLO_IDROGEOLOGICO);
			}else {
				soggettoCompetente = dichiarazioneSummaryDAO.getSoggettoCompetenteComunale(idIntervento,AmbitoIstanzaEnum.VINCOLO_IDROGEOLOGICO);
			}
		String destinatario = fillSoggettoCompetente(soggettoCompetente);
		if(genFileParams.getIdTipoIstanza() == TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_COMUNALE.getTypeValue()) {
			destinatario = "AL\r\n" + destinatario;
		}
		param.put("soggettoDestinatario",destinatario);
		
		}catch(Exception ex) {
			ex.printStackTrace();
			param.put("soggettoDestinatario","Destinatario da stabilire");
		}
		
		TSoggetto sottoscritto = soggettoDAO.findSoggettoById(confUtente.getFkSoggetto());
		
		Superfici superfici = WrapperVincoloStepsDaoImpl.getSuperfici(idIntervento, propcatastoInterventoDAO, idfTIntervVincIdroDAO);
		
		List<Integer> listDocs = genFileParams.getAllegatiTypes();
		
		param.put("isRegionale",genFileParams.getIdTipoIstanza() == TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE.getTypeValue());
		
		if(genFileParams.getRichNome() != null && genFileParams.getRichCognome() != null) {
			param.put("richCognome",genFileParams.getRichCognome());
			param.put("richNome",genFileParams.getRichNome());
		}else {
			param.put("richRagSociale",genFileParams.getRichRagSociale());
			param.put("richPartitaIva",genFileParams.getRichPartitaIva());			
		 }
		param.put("richCodFiscale",genFileParams.getRichCodiceFiscale());
		param.put("richIndirizzo",genFileParams.getRichIndirizzo());
		param.put("richCivico",genFileParams.getRichCivico());
		param.put("richProv",genFileParams.getRichProvincia());
		param.put("richCap",genFileParams.getRichCap());
		param.put("richComune",genFileParams.getRichComune());
		param.put("richTel",genFileParams.getRichTelefonico());
		
		String emailPec;
		if(genFileParams.getRichEmail() != null && genFileParams.getRichPec() != null) {
			emailPec =  genFileParams.getRichEmail().concat(SLASH).concat(genFileParams.getRichPec());
		} else if(genFileParams.getRichEmail() != null) {
			emailPec = genFileParams.getRichEmail();
		} else {
			emailPec = genFileParams.getRichPec();
		}
		param.put("richEmailPec", emailPec);
		
		param.put("nrIstanza", "" + genFileParams.getNrIstanza());
		
		//boolean isRecapitoDiverso = sottoscritto.getCodiceFiscale().equals(genFileParams.getRichCodiceFiscale());
		boolean isRecapitoDiverso = confUtente.getFkTipoAccreditamento() == TipoAccreditamentoEnum.PROFESSIONISTA.getValue();
		param.put("sottoscrittoQualita",isRecapitoDiverso?categoriaProfessionaleDAO.getCategoriaProfessionaleById(
				sottoscritto.getFkCategoriaProfessionale()).getDescrCategoriaProfessionale():"");
			param.put("sottoscrittoNome",isRecapitoDiverso?genFileParams.getRichNome() + " " + genFileParams.getRichCognome():"");
			param.put("sottoscrittoIndirizzo",isRecapitoDiverso?genFileParams.getRichIndirizzo()+ ", " + genFileParams.getRichCivico()
					+ " - " + genFileParams.getRichCap() + " - " + genFileParams.getRichComune():"");
			param.put("sottoscrittoTelefono",isRecapitoDiverso?genFileParams.getRichTelefonico():"");
		
		param.put("totSupIntervento",formatNumber(superfici.getTotaleSuperficieTrasf().toString()));
		param.put("totSupVincolo",formatNumber(superfici.getTotaleSuperficieInVincolo().toString()));
		param.put("totSupBoscata",formatNumber(superfici.getTotaleSuperficieBoscata().toString()));
		param.put("totSupBoscataVincolo",formatNumber(superfici.getTotaleSuperficieBoscataInVincolo().toString()));
		param.put("totMovTerra",formatNumber(genFileParams.getMovimentiTerraMc().toString()));
		param.put("totMovVincolo",formatNumber(genFileParams.getMovimentiTerraVincidroMc().toString()));
		param.put("isBoscoCeduo",genFileParams.isFlgGovCeduo());
		param.put("isBoscoFustaia",genFileParams.isFlgGovFustaia());
		param.put("isBoscoMisto",genFileParams.isFlgGovMisto());
		param.put("isBoscoAltro",genFileParams.isFlgGovAltro());
		param.put("isBoscoRobinieti",genFileParams.isFlgGovRobineti());
		param.put("isBoscoCastagneti",genFileParams.isFlgGovCastagneti());
		param.put("tempoPrevisto",genFileParams.getMesiIntervento());
		param.put("isFlagCauzione1",genFileParams.getFlgCauzioneVi() == 1);
		param.put("isFlagCauzione2",genFileParams.getFlgCauzioneVi() == 2);
		param.put("isFlagCauzione3",genFileParams.getFlgCauzioneVi() == 3);
		param.put("isFlagCauzione4",genFileParams.getFlgCauzioneVi() == 4);;
		param.put("isFlagCauzione5",genFileParams.getFlgCauzioneVi() == 5);
		
		param.put("cmTotSup","");
		param.put("cmSupBosc","");
		param.put("cmSupNoBosc","");
		param.put("cmNoBoscEuro","");
		param.put("cmBoscEuro","");
		param.put("cmTotEuro","");
		
		param.put("isFlagAreeDissesto",genFileParams.isFlgAreeDissesto());
		param.put("isFlagAreeEsondazione",genFileParams.isFlgAreeEsondazione());
		
		if("F".equals(genFileParams.getFlgCompensazione())) {
			param.put("isFlagLavori",true);
			param.put("isFlagDepositoCauzione",true);
		}else if("M".equals(genFileParams.getFlgCompensazione())) {
			param.put("isCompensazMonetaria",true);
			Double cmTotSup = genFileParams.getCmSupboscHa() + genFileParams.getCmSupnoboscHa();
			param.put("cmTotSup",formatNumber(cmTotSup.toString()));
			param.put("cmSupBosc",formatNumber(genFileParams.getCmSupboscHa().toString()));
			param.put("cmSupNoBosc",formatNumber(genFileParams.getCmSupnoboscHa().toString()));
			param.put("cmNoBoscEuro",formatEuro(genFileParams.getCmBoscoEuro()));
			param.put("cmBoscEuro",formatEuro(genFileParams.getCmNoBoscoEuro()));
			Double cmTotEuro = genFileParams.getCmBoscoEuro() + genFileParams.getCmNoBoscoEuro();
			param.put("cmTotEuro",formatEuro(cmTotEuro));
		}else if("N".equals(genFileParams.getFlgCompensazione())) {
			param.put("isFlagEsenzione1",genFileParams.isFlgArt9a() ||  genFileParams.isFlgArt9b() || genFileParams.isFlgArt9c());
			param.put("isFlagArt9A",genFileParams.isFlgArt9a());
			param.put("isFlagArt9B",genFileParams.isFlgArt9b());
			param.put("isFlagArt9C",genFileParams.isFlgArt9c());
			param.put("isFlagEsenzione2",genFileParams.isFlgArt7a() || genFileParams.isFlgArt7b() || genFileParams.isFlgArt7c()
					|| genFileParams.isFlgArt7d() || genFileParams.isFlgArt7dBis());
			param.put("isFlagArt7A",genFileParams.isFlgArt7a());
			param.put("isFlagArt7B",genFileParams.isFlgArt7b());
			param.put("isFlagArt7N",genFileParams.isFlgArt7c());
			param.put("isFlagArt7D",genFileParams.isFlgArt7d());
			param.put("isFlagArt7DBis",genFileParams.isFlgArt7dBis());
		}
		param.put("isDichProprietario", genFileParams.isFlgProprieta());
		param.put("isDichNonProprietario", !genFileParams.isFlgProprieta());
		param.put("isDichDissenso", genFileParams.isFlgDissensi());
		
		param.put("isFlagImportoInf",genFileParams.isFlgImporto() == false);
		param.put("isFlagImportoSup",genFileParams.isFlgImporto());
		param.put("isFlagConfServizi",genFileParams.isFlgConfServizi());
		param.put("isFlagSuap",genFileParams.isFlgSuap());
		param.put("isFlagCopiaConforme",genFileParams.isFlgCopiaConforme());
		param.put("isFlagSpeseIstruttoria",genFileParams.isFlgSpeseIstruttoria());
		param.put("isFlagEsenzioneBolloSi",genFileParams.isFlgEsenzioneMarcaBollo());
		param.put("isFlagEsenzioneBolloNo",genFileParams.isFlgEsenzioneMarcaBollo() == false);
		param.put("nMarcaBollo",genFileParams.getnMarcaBollo());
		if(!genFileParams.isFlgEsenzioneMarcaBollo()) {
			String importoMarcaBollo = configurationDAO.getConfigurationNumberByName(Constants.VALORE_MARCA_DA_BOLLO);
			param.put("importoMarcaBollo",importoMarcaBollo.indexOf(",") == -1 ? importoMarcaBollo + ",00": importoMarcaBollo);
		}
		param.put("isAllegato101",listDocs.contains(101));//prog definitivo
		param.put("isAllegato102",listDocs.contains(102));//rel tecnica
		param.put("isAllegato103",listDocs.contains(103));//rel geologica e geotecnica
		param.put("isAllegato104",listDocs.contains(104));//rel spec forestale
		param.put("isAllegato105",listDocs.contains(105));//prog di rimboschimento
		param.put("isAllegato106",listDocs.contains(106));//ricev versam compens monetaria
		param.put("isAllegato107",listDocs.contains(107));//docum fotografica
		param.put("isAllegato108",listDocs.contains(108));//rel nuvologica
		param.put("isAllegato121",listDocs.contains(121));//Allegato libero 1
		param.put("isAllegato120",listDocs.contains(120));//Allegato libero 2
		param.put("isAllegato24",listDocs.contains(24));//Scansione documento di identita
		
		
		param.put("testCheck",true);
		
		param.putAll(getParticelleSubreportResources(propCatastoDAO.getDichPropCatastosByPropcatastoIntervento(
				propcatastoInterventoDAO.getAllPropcatastoByIdIntervento(idIntervento))));
		
		param.putAll(
				getAllegatiVincoloSubreportResources(docAllegatoVincoloDAO.getAllegatiByIdIntervento(idIntervento),
						imgStream)
				);
		return param;
	}
	
	private String formatNumber(String value) {
		if(value != null) {
			return value.replace(".", ",");
		}
		return "";
	}
	
	private String formatEuro(Double value) {
		String euro = twoDigitformat.format(value);
		return formatNumber(euro.toString());
	}

	private JasperReport compileJasperReport(TipoAllegatoEnum allegato) throws JRException, IOException {
		logger.info("Execute compileJasperReport: " + JASPER_RESOURCES_CLASSPATH.concat(allegato.toString()).concat(JASPER_EXTENSION));
		Resource res = resourceLoader.getResource(JASPER_RESOURCES_CLASSPATH.concat(allegato.toString()).concat(JASPER_EXTENSION));
		return (JasperReport)JRLoader.loadObject(res.getInputStream());
	}
	
	private JasperReport compileJasperSubReport(String subreportName) throws JRException, IOException {
		Resource res = resourceLoader.getResource(JASPER_RESOURCES_CLASSPATH.concat(subreportName).concat(JASPER_EXTENSION));
		logger.info("Does file '"+ JASPER_RESOURCES_CLASSPATH.concat(subreportName).concat(JASPER_EXTENSION) +"' exist: " + res.getFile().exists());
		return (JasperReport)JRLoader.loadObject(res.getInputStream());
	}
	
	private Map<String, Object> getBoscoSubreportResources(List<DichPropCatasto> propCatasti) {

		List<HashMap<Object, Object>> elencoBosco = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		result.put("elencoDatiBosco", elencoBosco);

		for(DichPropCatasto catasto : propCatasti) {
			HashMap<Object, Object> catastoProp = new HashMap<>();
			catastoProp.put("comune", catasto.getDenominazioneComune());
			catastoProp.put("provincia", catasto.getDenominazioneProvincia());
			catastoProp.put("sezione", catasto.getSezione());
			catastoProp.put("foglio", catasto.getFoglio() == null ? null : catasto.getFoglio().toString());
			catastoProp.put("particela", catasto.getParticella());
			catastoProp.put("supCatastale", catasto.getSupCatastaleMq() == null ? null : catasto.getSupCatastaleMq().toString());
			catastoProp.put("supTrasformazione", catasto.getSupIntervento() == null ? null : catasto.getSupIntervento().toString());
			
			elencoBosco.add(catastoProp);
		}
		return result;
	}
	
	private Map<String, Object> getParticelleSubreportResources(List<DichPropCatasto> propCatasti) {

		List<HashMap<Object, Object>> elencoBosco = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		result.put("elencoDatiParticelle", elencoBosco);
		

		for(DichPropCatasto catasto : propCatasti) {
			HashMap<Object, Object> catastoProp = new HashMap<>();
			catastoProp.put("comune", catasto.getDenominazioneComune());
			catastoProp.put("sezione", catasto.getSezione());
			catastoProp.put("foglio", catasto.getFoglio() == null ? null : catasto.getFoglio().toString());
			catastoProp.put("particela", catasto.getParticella());
			elencoBosco.add(catastoProp);
		}
		return result;
	}
	
	private Map<String, Object> getAllegatiVincoloSubreportResources(List<InfoAllegatiVincolo> listDocs, 
			InputStream imgStream) {

		List<HashMap<Object, Object>> elencoDocs = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		result.put("elencoAllegati", elencoDocs);
		
		for(InfoAllegatiVincolo doc : listDocs) {
			HashMap<Object, Object> elem = new HashMap<>();
			elem.put("nomeAllegato", doc.getNomeAllegato());
			elem.put("isChecked", doc.getChecked());
			elem.put("nikeImg", imgStream);
			elencoDocs.add(elem);
		}
		return result;
	}
	
	private String getFormatedIstanzi(List<IstanzaCompensazione> istanze) {
		StringBuilder response = new StringBuilder();
		response.append("(con istanze di taglio");
		
		for(int i = 0; i < istanze.size(); i++) {
			response.append(" n. ");			
			response.append(istanze.get(i).getNumIstanzaCompensazione());			
			response.append(" ");			
			if(istanze.get(i).getDataAutorizzazione() != null) {
				response.append("del ");
				response.append(istanze.get(i).getDataAutorizzazione() != null ? 
						istanze.get(i).getDataAutorizzazione().format(dataFormatter):"N.D."	);					
			} else if(istanze.get(i).getDataPresentazione() != null) {
				response.append("del ");
				response.append(istanze.get(i).getDataPresentazione() != null?
						istanze.get(i).getDataPresentazione().format(dataFormatter):"N.D.");	
			}
			if(i != (istanze.size() - 1)) {
				response.append(",");	
			}
		}
		response.append(");");

		return response.toString();
	}

}
