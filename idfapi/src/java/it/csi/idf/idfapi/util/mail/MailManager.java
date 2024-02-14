/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.mail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


/**
 * The type Mail manager.
 *
 * @author CSI PIEMONTE
 */
public class MailManager {
    private static final Logger LOGGER = Logger.getLogger(MailManager.class);

    private static final String EMAIL_PROTOCOL = "smtp";

    /**
     * Send mail.
     *
     * @param emailDTO emailDTO
     * @throws Exception Exception
     */
    public static void sendMail(EmailDTO emailDTO) throws Exception {
        LOGGER.debug("[MailManager::sendMail] BEGIN");
        LOGGER.info("[MailManager::sendMail] BEGIN");
        Message msg = null;
        try {
            if (emailDTO == null)
                throw new InvalidParameterException("emaildto is null ...");

            Properties props = new Properties();
            props.put("mail.smtp.host", emailDTO.getHost());
            props.put("mail.smtp.port", emailDTO.getPort());

            props.put("mail.smtp.ssl.enable", emailDTO.getEnableSSL());
            props.put("mail.smtp.starttls.enable", emailDTO.getEnableTLS());
            props.put("mail.smtp.auth", emailDTO.getIsAuth()); // enable authentication

            if (StringUtils.isNotBlank(emailDTO.getUserID())) {
                props.put("mail.smtp.user", emailDTO.getUserID());
            }
            if (StringUtils.isNotBlank(emailDTO.getPassword())) {
                props.put("mail.smtp.password", emailDTO.getPassword());
            }
            if (StringUtils.isNotBlank(emailDTO.getSocketFactoryClass())) {
                props.put("mail.smtp.socketFactory.class", emailDTO.getSocketFactoryClass());
            }
            if (StringUtils.isNotBlank(emailDTO.getSocketFactoryPort())) {
                props.put("mail.smtp.socketFactory.port", emailDTO.getSocketFactoryPort());
            }
            props.put("mail.smtp.socketFactory.fallback", emailDTO.getSocketFactoryFallback());

            props.put("mail.debug", emailDTO.getEnableDebug());
            /**/

            Session session = Session.getInstance(props);
            msg = new MimeMessage(session);
            LOGGER.info("[MailManager::session]"+session.toString());
            
            try {
            	msg.setFrom(new InternetAddress(emailDTO.getMittente()));
            	LOGGER.info("[MailManager::setFrom OK] "+emailDTO.getMittente());
            }
            catch(Exception e){
            	LOGGER.info("[MailManager::setFrom error] "+emailDTO.getMittente());
            }
            // Message TO

            removeNulls(emailDTO.getDestinatari());            
            Address[] addressesTo = new Address[emailDTO.getDestinatari().size()];
            for (int i = 0; i < addressesTo.length; i++) {
                addressesTo[i] = new InternetAddress(emailDTO.getDestinatari().get(i));
            }
            LOGGER.info("[MailManager::addressesTo]"+addressesTo.toString());
            // Message CC
            removeNulls(emailDTO.getDestinatariCC());
            Address[] addressesToCC = new Address[emailDTO.getDestinatariCC().size()];            
            for (int i = 0; i < addressesToCC.length; i++) {
            	try {
            		addressesToCC[i] = new InternetAddress(emailDTO.getDestinatariCC().get(i));
            		LOGGER.info("[MailManager::addressesToCC OK] "+addressesToCC[i].toString());
            	}
            	catch(Exception e) {
            		LOGGER.info("[MailManager::addressesToCC Error] "+e);
            	}
            	
            }
            
            msg.setRecipients(Message.RecipientType.TO, addressesTo);
            LOGGER.info("[MailManager::get setRecipients ok]");
            if(addressesToCC !=null && addressesToCC.length>0) {
            	try {
            		msg.setRecipients(Message.RecipientType.CC, addressesToCC);
            	}
            	catch(Exception e) {
            		LOGGER.info("[MailManager::get setRecipientsCC error] "+e);
            	}
            }
            LOGGER.info("[MailManager::setRecipients CC]");
            // Subject
            msg.setSubject(emailDTO.getOggetto());

            // Message body
            msg.setText(emailDTO.getCorpo());

            Transport transport = session.getTransport(EMAIL_PROTOCOL);
            if (StringUtils.isNotBlank(emailDTO.getUserID())) {
                transport.connect(emailDTO.getHost(), emailDTO.getUserID(), emailDTO.getPassword());
            } else {
                transport.connect();
            }
            LOGGER.info("[MailManager::msg]"+msg.toString());
                        
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
//            Transport.send(msg);
        } catch (Exception e) {
            LOGGER.error("[MailManager::sendMail] ERROR ", e);
            throw e;
        } finally {
            LOGGER.debug("[MailManager::sendMail] END");
        }
    }
    public static<T> void removeNulls(List<T> list)
    {
        Iterator<T> itr = list.iterator();
        while (itr.hasNext())
        {
            T curr = itr.next();
 
            if (curr == null) {
                itr.remove();    // 
            }
        }
    }
}
