/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.CustomValidator;
import it.csi.idf.idfapi.business.be.exceptions.MultiErrorException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.AmbitoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DelegaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoAccreditamentoDAO;
import it.csi.idf.idfapi.business.be.vincolo.pojo.PlainAdpVincoloHome;
import it.csi.idf.idfapi.business.be.vincolo.service.WrapperAdpVincoloHomeDAO;
import it.csi.idf.idfapi.dto.AmbitoDto;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.Delega;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TipoAccreditamento;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.errors.ErrorConstants;
import it.csi.idf.idfapi.util.ListUtil;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.TipoAccreditamentoEnum;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

@Component
public class WrapperAdpVincoloHomeDAOImpl implements WrapperAdpVincoloHomeDAO {
	private static final String BACK_OFFICE_MESSAGE = "Attenzione! Non esiste nessun profilo di tipo Gestore associato alle tue credenziali. Contattare il referente regionale del Servizio.";

	public static final String LOGGED_CONFIG = "loggedConfig";

	@Autowired
	private ConfigUtenteDAO configUtenteDAO;

	@Autowired
	private SoggettoDAO soggettoDAO;

	@Autowired
	private DelegaDAO delegaDAO;
	
	@Autowired
	private AmbitoDAO ambitoDAO;
	
	@Autowired
	private TipoAccreditamentoDAO tipoAccreditamentoDAO;

	@Override
	public PlainAdpVincoloHome getAdpforHome(UserInfo user, Integer profilo, int tipoIstanzaId)
			throws MultiErrorException, ValidationException, RecordNotUniqueException {

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		CustomValidator.getValidator(HttpStatus.CONFLICT).errorIfNULL("soggetto", soggetto).go();
		PlainAdpVincoloHome plainHome = new PlainAdpVincoloHome();
		ConfigUtente configUtente;

		List<ConfigUtente> allConfUtenteCitadino = configUtenteDAO.getCofUtenteBySoggIdAnProfOrderByDate(soggetto.getIdSoggetto(), profilo);
		configUtente = allConfUtenteCitadino.get(0);
		//plainHome.setFkTipoAccreditamento(configUtente.getFkTipoAccreditamento());
		TipoAccreditamento tipoAccreditamento = tipoAccreditamentoDAO.getTipoAccreditamentoById(configUtente.getFkTipoAccreditamento());
		plainHome.setFkTipoAccreditamento(tipoAccreditamento.getDescrTipoAccreditamento());

		ConfigUtente configUtenteProf = configUtente;
		
		if(configUtenteProf != null) {
			TSoggetto lastSoggettoWorkedByProfessionista = soggettoDAO.getLastInterventoByConfigUtente(configUtenteProf);
			
			if(lastSoggettoWorkedByProfessionista != null) {
				if(lastSoggettoWorkedByProfessionista.getPartitaIva() != null && lastSoggettoWorkedByProfessionista.getNome() == null) {
					TSoggetto ownerSoggetto = soggettoDAO.getSoggettoPfByPg(lastSoggettoWorkedByProfessionista.getIdSoggetto());
					
					if(ownerSoggetto == null)
						plainHome.setCodiceFiscaleDelega(lastSoggettoWorkedByProfessionista.getCodiceFiscale());
					else {
						plainHome.setCodiceFiscaleDelega(lastSoggettoWorkedByProfessionista.getCodiceFiscale());
						plainHome.setOwnerCodiceFiscale(ownerSoggetto.getCodiceFiscale());
					}
				} else
					plainHome.setCodiceFiscaleDelega(lastSoggettoWorkedByProfessionista.getCodiceFiscale());
			}
		}
		
		//AmbitoDto ambito = ambitoDAO.findAllAmbito().stream().filter(x -> x.getIdAmbitoIstanza() == 9).findFirst().orElse(null);

		plainHome.setPartitaIva(soggetto.getPartitaIva());
		plainHome.setPec(soggetto.getPec());
		plainHome.setNumeroIscrizione(soggetto.getnIscrizioneOrdine());
		plainHome.setProvinciaOrdine(soggetto.getIstatProvIscrizioneOrdine());
		plainHome.setFkProfilioUtente(configUtente.getFkProfiloUtente());
		//plainHome.setTipoIstanzaId(TipoIstanzaEnum.AUTORIZZAZIONE_REGIONALE.getTypeValue());
		plainHome.setTipoIstanzaId(tipoIstanzaId);
		//plainHome.setTipoIstanzaDescr(TipoIstanzaEnum.AUTORIZZAZIONE_REGIONALE.toString());
		plainHome.setTipoIstanzaDescr(TipoIstanzaEnum.getEnum(tipoIstanzaId).toString());
		plainHome.setNome(soggetto.getNome());
		plainHome.setCognome(soggetto.getCognome());
		return plainHome;
	}

	@Override
	public ConfigUtente updateAdpVincoloRichidente(UserInfo user, Integer idProfilo, PlainAdpVincoloHome body)
			throws MultiErrorException, RecordNotUniqueException {

		plainHomeValidationRichidente(body);

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		ConfigUtente configUtente;
		List<ConfigUtente> configUtenteList = configUtenteDAO
				.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(), idProfilo);

		if (ListUtil.isEmpty(configUtenteList)) {

			ConfigUtente configUtente2 = new ConfigUtente();
			configUtente2.setDataPrimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
			configUtente2.setDataUltimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
			configUtente2.setFkProfiloUtente(ProfiloUtenteEnum.CITTADINO.getValue());
			configUtente2.setFkSoggetto(soggetto.getIdSoggetto());
			configUtente2.setFkTipoAccreditamento(TipoAccreditamentoEnum.RICHIEDENTE.getValue());
			configUtente2.setFlgPrivacy((byte) 1);
			configUtente2.setNrAccessi(0);
			int newConfigId = configUtenteDAO.createConfigUtente(configUtente2);
			configUtente2.setIdConfigUtente(newConfigId);
			soggetto.setFkConfigUtente(newConfigId);
			soggettoDAO.updateSoggetto(soggetto);
			configUtente = configUtente2;
		} else {

			CustomValidator.getValidator(HttpStatus.FORBIDDEN)
					.errorIf("user", configUtenteList.size() > 1, ErrorConstants.DB_INTEGRITY_DISTURBED).go();
			configUtente = configUtenteList.get(0);
			configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.RICHIEDENTE.getValue());
			configUtente.setDataUltimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
			configUtente.setNrAccessi(configUtente.getNrAccessi() + 1);
			configUtenteDAO.updateConfigUtente(configUtente);
		}

		return configUtente;
	}

	@Override
	public ConfigUtente updateAdpVincoloProfessionista(UserInfo user, Integer idProfilo, PlainAdpVincoloHome body)
			throws MultiErrorException, RecordNotUniqueException {

		// TODO update Categoria Professionale (rif a tabella
		// IDF_D_CATEGORIA_PROFESSIONALE
		ConfigUtente configUtente = null;

		plainHomeValidationProfes(body);
		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		soggetto.setPartitaIva(body.getPartitaIva());
		soggetto.setPec(body.getPec());
		soggetto.setIstatProvIscrizioneOrdine(body.getProvinciaOrdine());
		soggetto.setnIscrizioneOrdine(body.getNumeroIscrizione());

		List<ConfigUtente> configUtenteList = configUtenteDAO
				.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(), idProfilo);
		if (configUtenteList == null) {

			ConfigUtente configUtente2 = new ConfigUtente();
			configUtente2.setDataPrimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
			configUtente2.setDataUltimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
			configUtente2.setFkProfiloUtente(ProfiloUtenteEnum.CITTADINO.getValue());
			configUtente2.setFkSoggetto(soggetto.getIdSoggetto());
			configUtente2.setFkTipoAccreditamento(TipoAccreditamentoEnum.PROFESSIONISTA.getValue());
			configUtente2.setFlgPrivacy((byte) 1);
			configUtente2.setNrAccessi(0);
			int newConfigId = configUtenteDAO.createConfigUtente(configUtente2);
			configUtente2.setIdConfigUtente(newConfigId);
			configUtente = configUtente2;
			soggetto.setFkConfigUtente(newConfigId);

		} else {
			CustomValidator.getValidator(HttpStatus.FORBIDDEN)
					.errorIf("user", configUtenteList.size() > 1, ErrorConstants.DB_INTEGRITY_DISTURBED).go();
			configUtente = configUtenteList.get(0);
		}

		// soggettoDAO.updateSoggetto(soggetto);

		CustomValidator.getValidator().errorIf("CodiceFiscaleDelega",
				(soggetto.getCodiceFiscale() == body.getCodiceFiscaleDelega()), "You can not be delegate of yourself !")
				.go();

		resolveDelega(body.getCodiceFiscaleDelega(), configUtente);
		configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.PROFESSIONISTA.getValue());
		configUtente.setDataUltimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
		configUtente.setNrAccessi(configUtente.getNrAccessi() + 1);
		configUtenteDAO.updateConfigUtente(configUtente);
		soggetto.setFkConfigUtente(configUtente.getIdConfigUtente());
		soggettoDAO.updateSoggetto(soggetto);

		updateIdfRSoggettoTipoSoggeto(soggetto.getIdSoggetto(), configUtente.getFkTipoAccreditamento());
		return configUtente;

	}

	/*
	 * resolve delega if entered codice for non-existing delega on img01
	 */
	public void resolveDelega(String codice, ConfigUtente configUtente) throws RecordNotUniqueException {
		Delega dbDelega;

		dbDelega = delegaDAO.getIfActiveByCfDeleganteAndConfigUtente(codice, configUtente.getIdConfigUtente());
		if (dbDelega == null) {

			TSoggetto soggettoForDelega = soggettoDAO.findSoggettoByCodiceFiscale(codice);
			if (soggettoForDelega == null) {
				Delega newDelega = new Delega();
				newDelega.setCfDelegante(codice);
				newDelega.setIdConfigUtente(configUtente.getIdConfigUtente());
				newDelega.setDataInizio(LocalDate.now());

				delegaDAO.createDelega(newDelega);

			} else {
				TSoggetto owner = soggettoDAO.getSoggettoPfByPg(soggettoForDelega.getIdSoggetto());
				if (owner == null) {

					Delega newDelega = new Delega();
					newDelega.setCfDelegante(codice);
					newDelega.setIdConfigUtente(configUtente.getIdConfigUtente());
					newDelega.setDataInizio(LocalDate.now());
					delegaDAO.createDelega(newDelega);

					soggettoForDelega.setDataAggiornamento(LocalDate.now());

					soggettoDAO.updateSoggetto(soggettoForDelega);
				} else {
					dbDelega = delegaDAO.getIfActiveByCfDeleganteAndConfigUtente(owner.getCodiceFiscale(),
							configUtente.getIdConfigUtente());
					if (dbDelega == null) {
						Delega newDelega = new Delega();
						newDelega.setCfDelegante(owner.getCodiceFiscale());
						newDelega.setIdConfigUtente(configUtente.getIdConfigUtente());
						newDelega.setDataInizio(LocalDate.now());
						delegaDAO.createDelega(newDelega);
					}
					owner.setDataAggiornamento(LocalDate.now());

					soggettoDAO.updateSoggetto(owner);
				}
			}
		}
	}

	private void plainHomeValidationRichidente(PlainAdpVincoloHome body) {
		// TODO validate richidente in body

	}

	@Override
	public void plainHomeValidationProfes(PlainAdpVincoloHome plainHome) throws MultiErrorException {
//
//		CustomValidator.validate("partita iva", (plainHome.getPartitaIva().length() > 11), "partita iva >11", 1)
//		.validate("TipoAccreditamentoEnum", (TipoAccreditamentoEnum.PROFESSIONISTA.getText().equalsIgnoreCase(plainHome.getFkTipoAccreditamento()),"Accred. "

		CustomValidator.getValidator(HttpStatus.PRECONDITION_FAILED)
				.errorIf("partitaIva", (plainHome.getPartitaIva() == null), ErrorConstants.CAMPO_OBBLIGATORIO_003,
						ErrorConstants.CAMPO_OBBLIGATORIO, "partitaIva length 3 - String")
				.errorIf("TipoAccreditamentoEnum", (plainHome.getPec() == null), "PEC must be string")
				.validate("ProvinciaOrdine", plainHome.getProvinciaOrdine() != null,
						ErrorConstants.CAMPO_OBBLIGATORIO_003, "Provincia ordine length must be string length3")
				.errorIf("NumeroIscrizione", plainHome.getNumeroIscrizione() == null,
						ErrorConstants.CAMPO_OBBLIGATORIO_003, ErrorConstants.CAMPO_OBBLIGATORIO,
						"Numeroincrizione is null Must be String <20")
				.go();

		CustomValidator.getValidator(HttpStatus.CONFLICT)
				.errorIf("partitaIva", (plainHome.getPartitaIva().length() != 11),
						ErrorConstants.BAD_INPUT_PARAMETERS_400, ErrorConstants.BAD_INPUT_PARAMETERS,
						plainHome.getPartitaIva() + " must be 11 long ")
				.errorIf("TipoAccreditamentoEnum", (plainHome.getPec().length() > 50),
						plainHome.getPec() + "-> PEC>50 ")
				.validate("ProvinciaOrdine", plainHome.getProvinciaOrdine().length() > 2,
						ErrorConstants.BAD_INPUT_PARAMETERS_400,
						plainHome.getProvinciaOrdine() + " Provincia ordine length must be >3")
				.errorIf("NumeroIscrizione", plainHome.getNumeroIscrizione().length() > 20,
						plainHome.getNumeroIscrizione() + " Numeroincrizione must be  <20 long")
				.go();

		/*
		 * MultiErrorException customValidator = CustomValidator.getValidator();
		 * customValidator.setStatus(HttpStatus.NOT_ACCEPTABLE);
		 * customValidator.codiceIsValid("CodiceFiscaleDelega",
		 * plainHome.getCodiceFiscaleDelega()).go();
		 */

//
//				if( (plainHome.getPartitaIva().length() > 11 
//						|| plainHome.getPec().length() > 50
//						|| plainHome.getProvinciaOrdine().length() > 3 
//						|| plainHome.getNumeroIscrizione().length() > 20
//						|| plainHome.getCodiceFiscaleDelega().length() > 16)) {
//			throw new ValidationException();

	}

	@Override
	public ConfigUtente getHomeData(UserInfo user, int profilo, HttpServletRequest httpRequest)
			throws MultiErrorException, RecordNotUniqueException {
		TSoggetto soggetto;
		CustomValidator.getValidator(HttpStatus.PRECONDITION_FAILED).errorIfNULL("user", user)
				.errorIfNULL("profilo", profilo).errorIf("profilo", profilo > 4, "wrong profile")
				.errorIf("profilo", profilo < 1, "wrong profile").go();
		List<ConfigUtente> confutente = null;
		soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
		if (soggetto != null) {
			confutente = configUtenteDAO.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(),
					ProfiloUtenteEnum.GESTORE.getValue());
			if (confutente == null) {

				confutente = configUtenteDAO.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(),
						ProfiloUtenteEnum.CITTADINO.getValue());

				if (confutente == null) {

					confutente = configUtenteDAO.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(),
							ProfiloUtenteEnum.NO_DATA.getValue());
				}
			} else {
				httpRequest.getSession().setAttribute(LOGGED_CONFIG, confutente.get(0));
			}
		}
		if (ListUtil.isNotEmpty(confutente)) {
			return confutente.get(0);
		}
		return null;
	}

	@Override
	public PlainAdpVincoloHome createMeAsRichidente(TSoggetto body, SecurityContext securityContext,
			HttpHeaders httpHeaders, HttpServletRequest httpRequest) throws MultiErrorException, RecordNotUniqueException {
		CustomValidator.getValidator(HttpStatus.PRECONDITION_FAILED)
		.errorIf("soggetto", body.getIdSoggetto()==null,
				ErrorConstants.BAD_INPUT_PARAMETERS_400, ErrorConstants.BAD_INPUT_PARAMETERS).go();

		TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getCodiceFiscale());
		PlainAdpVincoloHome plainHome = new PlainAdpVincoloHome();
		if (soggetto == null) {
			ConfigUtente configUtente = new ConfigUtente();
			configUtente.setFkSoggetto(soggettoDAO.createSoggetto(body));
			configUtente.setFkProfiloUtente(ProfiloUtenteEnum.CITTADINO.getValue());
			configUtente.setNrAccessi(0);
			configUtente.setDataPrimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
			configUtente.setDataUltimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
			configUtente.setFlgPrivacy((byte) 1);
			configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.RICHIEDENTE.getValue());

			int idConUt = configUtenteDAO.createConfigUtente(configUtente);
			soggetto = soggettoDAO.findSoggettoById(configUtente.getFkSoggetto());
			soggetto.setFkConfigUtente(idConUt);
			soggettoDAO.updateSoggetto(soggetto);
		}
		plainHome.setTipoIstanzaId(TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE.getTypeValue());
		plainHome.setTipoIstanzaDescr(TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE.toString());

		return plainHome;
	}

	@Override
	public List<AmbitoDto> getAmbito() {
		return ambitoDAO.findAllAmbito();

	}

	@Override
	public void updateIdfRSoggettoTipoSoggeto(Integer idSoggetto, Integer fkTipoAccreditamento) {
		//su richiesta di Roberto il sistema non deve mai aggiornare questa tabella
//		String sql = "SELECT count(*) FROM idf_r_soggetto_tipo_soggetto WHERE id_soggetto = ? and id_tipo_soggetto = "
//				+ SoggettoTypeEnum.RICHIEDENTE.getValue() + " ;";
//		int count = DBUtil.jdbcTemplate.queryForObject(sql, new Object[] { idSoggetto }, Integer.class);
//		if (count > 0) {
//		} else {
//			List<Object> parameters = new ArrayList<>();
//			parameters.add(idSoggetto);
//			parameters.add(SoggettoTypeEnum.RICHIEDENTE.getValue());
//			parameters.add(1);
//			sql = "INSERT INTO idf_r_soggetto_tipo_soggetto (id_soggetto, id_tipo_soggetto, flg_visibile) VALUES(?, ?, ?)";
//			DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
//		}
	}
}
