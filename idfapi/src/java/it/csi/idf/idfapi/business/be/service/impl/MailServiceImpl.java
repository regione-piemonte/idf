/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.*;
import it.csi.idf.idfapi.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.service.MailService;
import it.csi.idf.idfapi.util.AmbitoIstanzaEnum;
import it.csi.idf.idfapi.util.SpringSupportedResource;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;
import it.csi.idf.idfapi.util.mail.EmailDTO;
import it.csi.idf.idfapi.util.mail.MailEnum;
import it.csi.idf.idfapi.util.mail.MailManager;

@Component
public class MailServiceImpl extends SpringSupportedResource implements MailService {

    @Autowired
    IstanzaForestDAO istanzaForestDAO;

    @Autowired
    InterventoDAO interventoDAO;

    @Autowired
    DichiarazioneSummaryDAO dichiarazioneSummaryDAO;

    @Autowired
    SoggettoInterventoDAO soggettoInterventoDAO;

    @Autowired
    SoggettoDAO soggettoDAO;

    @Autowired
    MailDAO mailDAO;

    @Override
    public void sendMailTrasformazioniByIdIntervento(int idIntervento, MailEnum mailType) {
        try {
            EmailDTO email = mailDAO.getConfEmail(mailType.getValue());
            DatiMailInvoIstanza datiMail = mailDAO.getDatiInvioIstanzaTrasformazioni(idIntervento);
            String corpo = email.getCorpo().replace("##valueNroIstanza##", datiMail.getNrIstanzaForestale().toString())
                    .replace("##valueSoggettogestoreDenominazione##", datiMail.getDenominazioneGestore())
                    .replace("##valueSoggettogestoreTelefono##", getNotNullValue(datiMail.getTelefonoGestore()))
                    .replace("##valueSoggettogestoreMail##", getNotNullValue(datiMail.getMailGestore()))
                    .replace("##valueSoggettogestorePec##", getNotNullValue(datiMail.getPecGestore()));
            email.setCorpo(corpo);
            List<String> destinatari = new ArrayList<String>();
            if(datiMail.getMailRichiedente()!=null) {
	            destinatari.add(datiMail.getMailRichiedente());
	            email.setDestinatari(destinatari);
            }
            if(datiMail.getMailGestore()!=null) {
	            destinatari = new ArrayList<String>();
	            destinatari.add(datiMail.getMailGestore());
	            email.setDestinatariCC(destinatari);
            }
            logger.info(email.toString());
            MailManager.sendMail(email);
        } catch (Exception e) {
            logger.error("sendMailTrasformazioniByIdIntervento", e);
        }
    }

    @Override
    public void sendMailVincoloByIdIntervento(int idIntervento, MailEnum mailType) {
        EmailDTO email = mailDAO.getConfEmail(mailType.getValue());

        TSoggetto soggettoCompetente = null;
        try {

            IstanzaForest istanzaForest = istanzaForestDAO.getByIdIntervento(idIntervento);

            if (istanzaForest.getFkTipoIstanza() == TipoIstanzaEnum.VINCOLO_AUTORIZZAZIONE_REGIONALE.getTypeValue()) {
                soggettoCompetente = dichiarazioneSummaryDAO.getSoggettoCompetenteRegionale(idIntervento, AmbitoIstanzaEnum.VINCOLO_IDROGEOLOGICO);
            } else {
                soggettoCompetente = dichiarazioneSummaryDAO.getSoggettoCompetenteComunale(idIntervento, AmbitoIstanzaEnum.VINCOLO_IDROGEOLOGICO);
            }

            SoggettoIntervento soggetoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);
            TSoggetto richiedente = soggettoDAO.findSoggettoById(soggetoIntervento.getIdSoggetto());


            String corpo = email.getCorpo().replace("##valueNroIstanza##", istanzaForest.getNrIstanzaForestale().toString())
                    .replace("##valueSoggettogestoreDenominazione##", soggettoCompetente.getDenominazione())
                    .replace("##valueSoggettogestoreTelefono##", getNotNullValue(soggettoCompetente.getNrTelefonico()))
                    .replace("##valueSoggettogestoreMail##", getNotNullValue(soggettoCompetente.geteMail()))
                    .replace("##valueSoggettogestorePec##", getNotNullValue(soggettoCompetente.getPec()))
                    ///7777
                    .replace("##valueSoggettoRichiedenteDenominazione##", getNotNullValue(soggettoCompetente.getDenominazione()))
                    .replace("##valueSoggettoRichiedenteTelefono##", getNotNullValue(soggettoCompetente.getNrTelefonico()))
                    .replace("##valueSoggettoRichiedenteMail##", getNotNullValue(soggettoCompetente.geteMail()))
                    .replace("##valueSoggettoRichiedentePec##", getNotNullValue(soggettoCompetente.getPec()));
            
            email.setCorpo(corpo);
            List<String> destinatari = new ArrayList<String>();
            if(richiedente.geteMail()!=null) {
	            destinatari.add(richiedente.geteMail());            
	            email.setDestinatari(destinatari);
            }
            if(soggettoCompetente.geteMail()!=null) {
	            destinatari = new ArrayList<String>();
	            destinatari.add(soggettoCompetente.geteMail());
	            email.setDestinatariCC(destinatari);
            }
            logger.info(email.toString());

            MailManager.sendMail(email);
        } catch (Exception e) {
            logger.error("sendMailVincoloByIdIntervento", e);
        }
    }

    @Override
    public void sendMailTagliByIdIntervento(int idIntervento, MailEnum mailType) {
        EmailDTO email = mailDAO.getConfEmail(mailType.getValue());
        logger.info("<---- mailType: "+ mailType.getValue()+" ---->");
        TSoggetto soggettoCompetente = null;

        try {

            IstanzaForest istanzaForest = istanzaForestDAO.getByIdIntervento(idIntervento);

            Intervento intervento = interventoDAO.findInterventoByIdIntervento(idIntervento);

            SoggettoIntervento soggettoIntervento = soggettoInterventoDAO.getSoggettoInterventoById(idIntervento);

            TSoggetto richiedente = soggettoDAO.findSoggettoById(soggettoIntervento.getIdSoggetto());

            //ufficio competente [gestore]
            soggettoCompetente = dichiarazioneSummaryDAO.getSoggettoCompetenteRegionale(
                    idIntervento,
                    AmbitoIstanzaEnum.TAGLIO_IN_BOSCO);
            
            String tipoIstanza = istanzaForest.getFkTipoIstanza() == TipoIstanzaEnum.COMUNICAZIONE.getTypeValue() ?
                    "Comunicazione semplice" :"Autorizzazione con progetto";

            String subject = email.getOggetto().replace("##valueTipoIstanza##",tipoIstanza);

            email.setOggetto(subject);

            String corpo = email.getCorpo()
                    .replace("##valueTipoIstanza##", tipoIstanza)
                    .replace("##valueNroIstanza##", istanzaForest.getNrIstanzaForestale().toString())
                    .replace("##valueSoggettogestoreDenominazione##", soggettoCompetente.getDenominazione())
                    .replace("##valueSoggettogestoreTelefono##", getNotNullValue(soggettoCompetente.getNrTelefonico()))
                    .replace("##valueSoggettogestoreMail##", getNotNullValue(soggettoCompetente.geteMail()))
                    .replace("##valueSoggettogestorePec##", getNotNullValue(soggettoCompetente.getPec()))
                    ///7777
                    .replace("##valueSoggettoRichiedenteDenominazione##", getNotNullValue(soggettoCompetente.getDenominazione()))
                    .replace("##valueSoggettoRichiedenteTelefono##", getNotNullValue(soggettoCompetente.getNrTelefonico()))
                    .replace("##valueSoggettoRichiedenteMail##", getNotNullValue(soggettoCompetente.geteMail()))
                    .replace("##valueSoggettoRichiedentePec##", getNotNullValue(soggettoCompetente.getPec()));
                                
            email.setCorpo(corpo);
            List<String> destinatari = new ArrayList<String>();


            switch (mailType) {
                case INVIO_ISTANZA_TAGLI:
                    // richiedente
                	if(richiedente.geteMail()!=null) {
	                    destinatari.add(richiedente.geteMail());
	                    email.setDestinatari(destinatari);
                    }
                    // gestore
                	if(soggettoCompetente.geteMail()!=null) {
	                    destinatari = new ArrayList<String>();
	                    destinatari.add(soggettoCompetente.geteMail());
                	}
                    //area protetta
                    List<String> area2000 = soggettoDAO.findEmailFromAreaProtetta2000ByIdIntervento(idIntervento);
                    if (!area2000.isEmpty()) {
                        destinatari.addAll(area2000);
                    }
                    List<String> areaProtetta = soggettoDAO.findEmailFromAreaProtettaByIdIntervento(idIntervento);
                    if (!areaProtetta.isEmpty()) {
                        destinatari.addAll(areaProtetta);
                    }
                    email.setDestinatariCC(destinatari);
                    break;
                case ANNULLAMENTO_ISTANZA_TAGLI:
                    // gestore
                	if(soggettoCompetente.geteMail()!=null) {
	                    destinatari.add(soggettoCompetente.geteMail());
	                    email.setDestinatari(destinatari);
                	}
                    break;
                case CONFERMA_INVIO_INTEGRAZIONE_TAGLI:
                    // richiedente
                	if(richiedente.geteMail()!=null) {
	                    destinatari.add(richiedente.geteMail());
	                    email.setDestinatari(destinatari);
                	}
                	// gestore
                	if(soggettoCompetente.geteMail()!=null) {
	                    destinatari = new ArrayList<String>();
	                    destinatari.add(soggettoCompetente.geteMail());
	                    email.setDestinatariCC(destinatari);
                	}  
                    break;
                case CONFERMA_PROTOCOLLAZIONE_TAGLI:
                    // richiedente
                	if(richiedente.geteMail()!=null) {
	                    destinatari.add(richiedente.geteMail());
	                    email.setDestinatari(destinatari);
                	}
                    // gestore
                	if(soggettoCompetente.geteMail()!=null) {
	                    destinatari = new ArrayList<String>();
	                    destinatari.add(soggettoCompetente.geteMail());
	                    email.setDestinatariCC(destinatari);
                	}                	
                    break;
                case CONFERMA_PROTOCOLLAZIONE_INTEGRAZIONE_TAGLI:
                    // richiedente
                	if(richiedente.geteMail()!=null) {
	                    destinatari.add(richiedente.geteMail());
	                    email.setDestinatari(destinatari);
                	}
                    // gestore
                	if(soggettoCompetente.geteMail()!=null) {
	                    destinatari = new ArrayList<String>();
	                    destinatari.add(soggettoCompetente.geteMail());
	                    email.setDestinatariCC(destinatari);
                	}
                    break;
                case MODIFICA_UTILIZZATORE_TAGLI:
                    // richiedente
                	if(richiedente.geteMail()!=null) {
	                    destinatari.add(richiedente.geteMail());
	                    email.setDestinatari(destinatari);  
                	}
                    // gestore
                	if(soggettoCompetente.geteMail()!=null) {
	                    destinatari = new ArrayList<String>();
	                    destinatari.add(soggettoCompetente.geteMail());
	                    email.setDestinatariCC(destinatari);
                	}
                    break;    
            
            }
            
            
            
            
            logger.info("<---- sendMailTagliByIdIntervento: "+email.toString()+"---->");
            MailManager.sendMail(email);
        } catch (Exception e) {
            logger.error("sendMailTagliByIdIntervento", e);
        }
    }

    String getNotNullValue(String value) {
        if (value == null) {
            return "-";
        }
        return value;
    }

}
