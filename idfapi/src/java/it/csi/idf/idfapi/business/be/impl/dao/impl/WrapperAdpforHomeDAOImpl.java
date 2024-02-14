/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import static it.csi.idf.idfapi.util.builder.ToStringBuilder.objectToString;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.GenericException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.ConfigUtenteDAO;
import it.csi.idf.idfapi.business.be.impl.dao.DelegaDAO;
import it.csi.idf.idfapi.business.be.impl.dao.IstanzaForestDAO;
import it.csi.idf.idfapi.business.be.impl.dao.SoggettoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.TipoAccreditamentoDAO;
import it.csi.idf.idfapi.business.be.impl.dao.WrapperAdpforHomeDAO;
import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.Delega;
import it.csi.idf.idfapi.dto.PlainAdpforHome;
import it.csi.idf.idfapi.dto.PlainBackOfficeInfo;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.dto.TipoAccreditamento;
import it.csi.idf.idfapi.dto.UserInfo;
import it.csi.idf.idfapi.util.ProceduraEnum;
import it.csi.idf.idfapi.util.ProfiloUtenteEnum;
import it.csi.idf.idfapi.util.TipoAccreditamentoEnum;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;
import it.csi.idf.idfapi.util.ValidationUtil;

@Component
public class WrapperAdpforHomeDAOImpl implements WrapperAdpforHomeDAO {

    static final Logger logger = Logger.getLogger(WrapperAdpforHomeDAOImpl.class);

    private static final String BACK_OFFICE_MESSAGE = "Attenzione! Non esiste nessun profilo di tipo Gestore associato alle tue credenziali. Contattare il referente regionale del Servizio.";

    public static final String LOGGED_CONFIG = "loggedConfig";

    @Autowired
    private ConfigUtenteDAO configUtenteDAO;

    @Autowired
    private TipoAccreditamentoDAO tipoAccreditamentoDAO;

    @Autowired
    private SoggettoDAO soggettoDAO;

    @Autowired
    private DelegaDAO delegaDAO;

    @Autowired
    IstanzaForestDAO istanzaForestDAO;

    @Override
    public PlainAdpforHome getAdpforHome(Integer idTipoIstanza, UserInfo user, Integer profilo) throws RecordNotUniqueException {

        TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
        PlainAdpforHome plainHome = new PlainAdpforHome();

        ConfigUtente configUtente;
        TipoAccreditamento tipoAccreditamento;

        List<ConfigUtente> allConfUtenteCitadino = configUtenteDAO
                .getCofUtenteBySoggIdAnProfOrderByDate(soggetto.getIdSoggetto(), profilo);

        configUtente = allConfUtenteCitadino.get(0);
        if (null != configUtente) {
            plainHome.setFkProfilo(configUtente.getFkProfiloUtente());
        }
        tipoAccreditamento = tipoAccreditamentoDAO.getTipoAccreditamentoById(configUtente.getFkTipoAccreditamento());
        plainHome.setFkTipoAccreditamento(tipoAccreditamento.getDescrTipoAccreditamento());

        List<ConfigUtente> listConfigUtente = configUtenteDAO
                .getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(), profilo);
        if (listConfigUtente != null && listConfigUtente.size() > 0) {
            ConfigUtente configUtenteProf = listConfigUtente.get(0);
            plainHome.setCategoriaProfessionale(soggetto.getFkCategoriaProfessionale());
            List<Delega> deleghe = delegaDAO.getMieiDelegati(configUtenteProf.getIdConfigUtente());
            if (deleghe != null && deleghe.size() > 0) {
                TSoggetto lastSoggettoDelegatoToProfessionista = null;
                for (int i = 0; i < deleghe.size() && lastSoggettoDelegatoToProfessionista == null; i++) {
                    List<TSoggetto> listSoggetto = soggettoDAO.findByPartialCodiceFiscale(deleghe.get(i).getCfDelegante());
                    if (listSoggetto.size() > 0) {
                        lastSoggettoDelegatoToProfessionista = listSoggetto.get(0);
                    }
                }

                soggettoDAO.findSoggettoByCodiceFiscale(deleghe.get(0).getCfDelegante());

                logger.info("lastSoggettoDelegatoToProfessionista: " + (lastSoggettoDelegatoToProfessionista == null ? "null" : lastSoggettoDelegatoToProfessionista.getCodiceFiscale()));
                if (lastSoggettoDelegatoToProfessionista != null) {
                    if (lastSoggettoDelegatoToProfessionista.getPartitaIva() != null
                            && lastSoggettoDelegatoToProfessionista.getNome() == null) {

                        TSoggetto ownerSoggetto = soggettoDAO
                                .getSoggettoPfByPg(lastSoggettoDelegatoToProfessionista.getIdSoggetto());
                        if (ownerSoggetto == null) {
                            plainHome.setCodiceFiscaleDelega(lastSoggettoDelegatoToProfessionista.getCodiceFiscale());

                        } else {
                            plainHome.setCodiceFiscaleDelega(lastSoggettoDelegatoToProfessionista.getCodiceFiscale());
                            plainHome.setOwnerCodiceFiscale(ownerSoggetto.getCodiceFiscale());

                        }
                    } else {

                        plainHome.setCodiceFiscaleDelega(lastSoggettoDelegatoToProfessionista.getCodiceFiscale());
                    }
                }
                if (plainHome.getCodiceFiscaleDelega() == null) {
                    plainHome.setCodiceFiscaleDelega(deleghe.get(0).getCfDelegante());
                }
            }
        }

        plainHome.setPartitaIva(soggetto.getPartitaIva());
        plainHome.setPec(soggetto.getPec());
        plainHome.setNumeroIscrizione(soggetto.getnIscrizioneOrdine());
        plainHome.setProvinciaOrdine(soggetto.getIstatProvIscrizioneOrdine());

        if (null != idTipoIstanza) {
            plainHome.setTipoIstanzaId(idTipoIstanza);
            plainHome.setTipoIstanzaDescr(TipoIstanzaEnum.getEnum(idTipoIstanza).toString());
        } else {
            TipoIstanzaEnum tipoIstanza = istanzaForestDAO.getLastIstanzaTipo(configUtente.getIdConfigUtente());
            if (tipoIstanza != null) {
                plainHome.setTipoIstanzaId(tipoIstanza.getTypeValue());
                plainHome.setTipoIstanzaDescr(tipoIstanza.toString());
            }
        }
         System.out.println("plainHome:-----oo-----> " + objectToString(plainHome));
        //logger.info("Trovato particella----->: " + objectToString(item));
        return plainHome;
    }


    @Override
    public ConfigUtente updateAdpforHome(UserInfo user, PlainAdpforHome body)
            throws RecordNotUniqueException, ValidationException, GenericException {

        plainHomeValidation(body);

        Integer idProfilo = body.getFkProfilo() != null ? body.getFkProfilo() : 1; // se no lo passo, presumo che sia cittadino

        TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
        ConfigUtente configUtente;
        Delega dbDelega = null;

        if (TipoAccreditamentoEnum.PROFESSIONISTA.getText().equalsIgnoreCase(body.getFkTipoAccreditamento())) {
            logger.info("inside: PROFESSIONISTA");
            soggetto.setPartitaIva(body.getPartitaIva());
            soggetto.setPec(body.getPec());
            soggetto.setIstatProvIscrizioneOrdine(body.getProvinciaOrdine());
            soggetto.setnIscrizioneOrdine(body.getNumeroIscrizione());
            if (body.getCategoriaProfessionale() != null) {
                soggetto.setFkCategoriaProfessionale(body.getCategoriaProfessionale());
            }

            List<ConfigUtente> configUtenteList = configUtenteDAO.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(), idProfilo);
            logger.debug("configUtenteList: " + (configUtenteList == null ? "null" : configUtenteList.size()));
            if (configUtenteList == null) {
                logger.info("inside: configUtenteList == null");
                ConfigUtente configUtente2 = new ConfigUtente();
                configUtente2.setDataPrimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
                configUtente2.setDataUltimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
                configUtente2.setFkProfiloUtente(ProfiloUtenteEnum.CITTADINO.getValue());
                configUtente2.setFkSoggetto(soggetto.getIdSoggetto());
                configUtente2.setFkTipoAccreditamento(TipoAccreditamentoEnum.PROFESSIONISTA.getValue());
                configUtente2.setFlgPrivacy((byte) 1);
                configUtente2.setNrAccessi(0);
                configUtente2.setFkSoggettoSportello(null);
                int newConfigId = configUtenteDAO.createConfigUtente(configUtente2);
                configUtente2.setIdConfigUtente(newConfigId);
                soggetto.setFkConfigUtente(newConfigId);
                configUtente = configUtente2;
            } else {
                logger.info("inside: configUtenteList != null");
                configUtente = configUtenteList.get(0);
                configUtente.setFkSoggettoSportello(null);
            }

            soggettoDAO.updateSoggetto(soggetto);

//            if (soggetto.getCodiceFiscale().equals(body.getCodiceFiscaleDelega()))
//                throw new GenericException("You can not be delegate of yourself");
//
//            dbDelega = delegaDAO.getIfActiveByCfDeleganteAndConfigUtente(body.getCodiceFiscaleDelega(), configUtente.getIdConfigUtente());
//            if (dbDelega == null) {
//                logger.info("inside: dbDelega == null - for: " + body.getCodiceFiscaleDelega());
////                TSoggetto soggettoForDelega = soggettoDAO.findSoggettoByCodiceFiscale(body.getCodiceFiscaleDelega());
////                if (soggettoForDelega == null) {
//                    logger.info("inside: soggettoForDelega == null");
//                    Delega newDelega = new Delega();
//                    newDelega.setCfDelegante(body.getCodiceFiscaleDelega());
//                    newDelega.setIdConfigUtente(configUtente.getIdConfigUtente());
//                    newDelega.setDataInizio(LocalDate.now());
//
//                    delegaDAO.createDelega(newDelega);
////                } else {
//                	// non si associa piu la delega al suo owner
////                    logger.info("inside: else soggettoForDelega == null");
////                    TSoggetto owner = soggettoDAO.getSoggettoPfByPg(soggettoForDelega.getIdSoggetto());
////                    if (owner == null) {
////                        logger.info("inside: owner == null");
////                        Delega newDelega = new Delega();
////                        newDelega.setCfDelegante(body.getCodiceFiscaleDelega());
////                        newDelega.setIdConfigUtente(configUtente.getIdConfigUtente());
////                        newDelega.setDataInizio(LocalDate.now());
////                        delegaDAO.createDelega(newDelega);
////                        soggettoForDelega.setDataAggiornamento(LocalDate.now());
////                        soggettoDAO.updateSoggetto(soggettoForDelega);
////                    } else {
////                        logger.info("inside: else owner == null");
////                        dbDelega = delegaDAO.getIfActiveByCfDeleganteAndConfigUtente(owner.getCodiceFiscale(), configUtente.getIdConfigUtente());
////                        if (dbDelega == null) {
////                            logger.info("dbDelega: owner == null");
////                            Delega newDelega = new Delega();
////                            newDelega.setCfDelegante(owner.getCodiceFiscale());
////                            newDelega.setIdConfigUtente(configUtente.getIdConfigUtente());
////                            newDelega.setDataInizio(LocalDate.now());
////                            delegaDAO.createDelega(newDelega);
////                        }
////                        owner.setDataAggiornamento(LocalDate.now());
////                        soggettoDAO.updateSoggetto(owner);
////                    }
////                }
//            }
            configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.PROFESSIONISTA.getValue());
        } else if (TipoAccreditamentoEnum.SPORTELLO.getText().equalsIgnoreCase(body.getFkTipoAccreditamento())) {
            logger.info("inside SPORTELLO");
            List<ConfigUtente> configUtenteList = configUtenteDAO.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(), idProfilo);
            if (configUtenteList == null) {
                ConfigUtente configUtente2 = new ConfigUtente();
                configUtente2.setDataPrimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
                configUtente2.setDataUltimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
                configUtente2.setFkProfiloUtente(ProfiloUtenteEnum.SPORTELLO.getValue());
                configUtente2.setFkSoggetto(soggetto.getIdSoggetto());
                configUtente2.setFkTipoAccreditamento(TipoAccreditamentoEnum.SPORTELLO.getValue());
                configUtente2.setFkSoggettoSportello(body.getFkSoggettoSportello());
                configUtente2.setFlgPrivacy((byte) 1);
                configUtente2.setNrAccessi(0);
                int newConfigId = configUtenteDAO.createConfigUtente(configUtente2);
                configUtente2.setIdConfigUtente(newConfigId);
                soggetto.setFkConfigUtente(newConfigId);
                configUtente = configUtente2;
            } else {
                configUtente = configUtenteList.get(0);
                configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.SPORTELLO.getValue());
                configUtente.setFkSoggettoSportello(body.getFkSoggettoSportello());
            }
        } else {
            logger.info("inside CITTADINO");
            List<ConfigUtente> configUtenteList = configUtenteDAO.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(), idProfilo);
            if (configUtenteList == null) {

                ConfigUtente configUtente2 = new ConfigUtente();
                configUtente2.setDataPrimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
                configUtente2.setDataUltimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
                configUtente2.setFkProfiloUtente(ProfiloUtenteEnum.CITTADINO.getValue());
                configUtente2.setFkSoggetto(soggetto.getIdSoggetto());
                configUtente2.setFkTipoAccreditamento(TipoAccreditamentoEnum.RICHIEDENTE.getValue());
                configUtente2.setFlgPrivacy((byte) 1);
                configUtente2.setNrAccessi(0);
                configUtente2.setFkSoggettoSportello(null);
                int newConfigId = configUtenteDAO.createConfigUtente(configUtente2);
                configUtente2.setIdConfigUtente(newConfigId);
                soggetto.setFkConfigUtente(newConfigId);
                configUtente = configUtente2;

            } else {
                configUtente = configUtenteList.get(0);
                configUtente.setFkSoggettoSportello(null);
            }
            configUtente.setFkTipoAccreditamento(TipoAccreditamentoEnum.RICHIEDENTE.getValue());
        }

        configUtente.setDataUltimoAccesso(Timestamp.valueOf(LocalDateTime.now()));
        configUtente.setNrAccessi(configUtente.getNrAccessi() + 1);
        configUtenteDAO.updateConfigUtente(configUtente);
        //soggetto.setFkConfigUtente(configUtente.getIdConfigUtente());
        soggettoDAO.updateSoggetto(soggetto);
// TODO added insert in idf_r_soggetto_tipo_soggetto
        updateIdfRSoggettoTipoSoggeto(soggetto.getIdSoggetto(), configUtente.getFkTipoAccreditamento());
        return configUtente;

    }

    @Override
    public void updateIdfRSoggettoTipoSoggeto(Integer idSoggetto, Integer fkTipoAccreditamento) {
        //Come da indicazioni di Roberto, l'applicativo non puo scrivere su questa tabellla
//		String sql = "SELECT count(*) FROM idf_r_soggetto_tipo_soggetto WHERE id_soggetto = ? and id_tipo_soggetto = "
//				+ SoggettoTypeEnum.RICHIEDENTE.getValue() + " ;";
//		
//		int count = DBUtil.jdbcTemplate.queryForObject(sql, new Object[] { idSoggetto }, Integer.class);
//		if (count > 0) {
//
//		} else {
//			List<Object> parameters = new ArrayList<>();
//			parameters.add(idSoggetto);
//			parameters.add(SoggettoTypeEnum.RICHIEDENTE.getValue());
//			parameters.add(1);
//
//			sql = "INSERT INTO idf_r_soggetto_tipo_soggetto (id_soggetto, id_tipo_soggetto, flg_visibile) VALUES(?, ?, ?)";
//			DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
//
//		}

    }

    private void plainHomeValidation(PlainAdpforHome plainHome) throws ValidationException {
        if (TipoAccreditamentoEnum.PROFESSIONISTA.getText().equalsIgnoreCase(plainHome.getFkTipoAccreditamento())
                && (plainHome.getPartitaIva().length() > 11 || plainHome.getPec().length() > 50
                || plainHome.getProvinciaOrdine().length() > 3 || plainHome.getNumeroIscrizione().length() > 20
                || !ValidationUtil.isCodiceOk(plainHome.getCodiceFiscaleDelega()))) {
            logger.info("PROFESSIONISTA?: " + (TipoAccreditamentoEnum.PROFESSIONISTA.getText().equalsIgnoreCase(plainHome.getFkTipoAccreditamento())
                    + " - piva: " + (plainHome.getPartitaIva().length() > 11))
                    + " - pec: " + (plainHome.getPec().length() > 50)
                    + " - provincia: " + (plainHome.getProvinciaOrdine().length() > 3)
                    + " - Numero iscrizione: " + (plainHome.getNumeroIscrizione().length() > 20)
                    + " - codFiscale: " + plainHome.getCodiceFiscaleDelega()
                    + " - isCodiceOk: " + (!ValidationUtil.isCodiceOk(plainHome.getCodiceFiscaleDelega())));

            throw new ValidationException();
        }

    }

    @Override
    public PlainBackOfficeInfo getAdpforBackOfficeHome(UserInfo user, int profilo) throws RecordNotUniqueException {

        TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
        List<ConfigUtente> configUtente = null;
        PlainBackOfficeInfo plainBackOfficeInfo = new PlainBackOfficeInfo();
        if (soggetto.getFkConfigUtente() != null) {
            configUtente = configUtenteDAO.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(),
                    ProfiloUtenteEnum.CITTADINO.getValue());
            if (configUtente == null) {
                plainBackOfficeInfo.setAllowed(false);
                plainBackOfficeInfo.setMessage(BACK_OFFICE_MESSAGE);
            } else {
                plainBackOfficeInfo.setAllowed(true);
            }
        } else {
            plainBackOfficeInfo.setAllowed(false);
            plainBackOfficeInfo.setMessage(BACK_OFFICE_MESSAGE);
        }
        return plainBackOfficeInfo;
    }

    @Override
    public ConfigUtente getHomeData(UserInfo user, ProceduraEnum procedura, HttpServletRequest httpRequest)
            throws RecordNotUniqueException {
        TSoggetto soggetto;
        ConfigUtente confutente = null;
        try {
        	System.out.println("getHomeData: " +user.getCodFisc());   
        	
            soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());
            logger.info("CodFisc: " + user.getCodFisc() + " - soggetto is null? " + (soggetto == null));
            System.out.println("getHomeData: " +soggetto.getFkComune());   
            if (soggetto != null) {
                confutente = configUtenteDAO.getCofigUtenteBySoggettoIdAndApplicationType(soggetto.getIdSoggetto(), procedura);
                httpRequest.getSession().setAttribute(LOGGED_CONFIG, confutente);
                logger.info("put user in session: " + confutente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return confutente;
    }

    @Override
    public ConfigUtente getHomeData(UserInfo user, int profilo, HttpServletRequest httpRequest)
            throws RecordNotUniqueException {
        return getHomeData(user, ProceduraEnum.GESTIONE_ISTANZE_FORESTALI, httpRequest);
    }

    @Override
    public PlainAdpforHome createMeAsRichidente(TSoggetto body, @Context SecurityContext securityContext,
                                                @Context HttpHeaders httpHeaders, @Context HttpServletRequest httpRequest) throws RecordNotUniqueException {

        TSoggetto soggetto = soggettoDAO.findSoggettoByCodiceFiscale(body.getCodiceFiscale());
        PlainAdpforHome plainHome = new PlainAdpforHome();
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
            try {

                soggetto = soggettoDAO.findSoggettoById(configUtente.getFkSoggetto());
            } catch (RecordNotFoundException e) {

                e.printStackTrace();
            }
            soggetto.setFkConfigUtente(idConUt);
            soggettoDAO.updateSoggetto(soggetto);
        }
        plainHome.setTipoIstanzaId(TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA.getTypeValue());
        plainHome.setTipoIstanzaDescr(TipoIstanzaEnum.DICHIARAZIONE_SOSTITUTIVA.toString());

        return plainHome;
    }

    @Override
    public ConfigUtente getDataForPFAAccess(UserInfo user, int profilo, HttpServletRequest httpRequest)
            throws RecordNotUniqueException {
        TSoggetto soggetto;
        soggetto = soggettoDAO.findSoggettoByCodiceFiscale(user.getCodFisc());

        List<ConfigUtente> confutente;
        if (soggetto != null) {

            confutente = configUtenteDAO.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(),
                    ProfiloUtenteEnum.GESTORE_PFA.getValue());
            if (confutente == null) {
                confutente = configUtenteDAO.getCofigUtenteBySoggettoIdAnProfilo(soggetto.getIdSoggetto(),
                        ProfiloUtenteEnum.NO_DATA.getValue());
                if (confutente != null) {
                    httpRequest.getSession().setAttribute(LOGGED_CONFIG, confutente.get(0));
                    logger.info("put user in session: " + confutente.get(0));
                    return confutente.get(0);
                }
            } else {
                httpRequest.getSession().setAttribute(LOGGED_CONFIG, confutente.get(0));
                logger.info("put user in session: " + confutente.get(0));
                return confutente.get(0);
            }

        }

        return null;
    }

}
