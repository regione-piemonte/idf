/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.business.service.helper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import it.csi.idf.idfbo.business.service.helper.dto.DettaglioEmail;
import it.csi.idf.idfbo.business.service.helper.factory.AddressFactory;
import it.csi.idf.idfbo.exception.ServiceException;

@Component
public class MailServiceHelper extends AbstractServiceHelper {
	
	
	public void invioMail(DettaglioEmail emailVO) throws ServiceException {
		LOGGER.debug("[ServiceMgr::invioMail] BEGIN");
		// Create a mail session
        final Properties props = new Properties();
        props.put("mail.smtp.host", emailVO.getHost());
        props.put("mail.smtp.port", emailVO.getPort());
        final Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        final Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(emailVO.getMittente()));

            msg.setRecipients(javax.mail.Message.RecipientType.TO, AddressFactory.createAddresses(emailVO.getDestinatari()));
            if (CollectionUtils.isNotEmpty(emailVO.getDestinatariCC())) {
                msg.setRecipients(javax.mail.Message.RecipientType.CC, AddressFactory.createAddresses(emailVO.getDestinatariCC()));
            }

            msg.setSubject(emailVO.getOggetto());

            // il corpo del messaggio si attacca dopo
            final MimeMultipart mp  = new MimeMultipart();
            final MimeBodyPart html = new MimeBodyPart();
            html.setText(emailVO.getCorpo());

            mp.addBodyPart(html);
            // add the Multipart to the message
            msg.setContent(mp);
            // Send the message
            Transport.send(msg);
        } catch(Exception e) {
            LOGGER.error("[MailServiceHelper::invioMail] Eccezione: " + e.getMessage());

            throw new ServiceException("[Errore Mail invioMail Generic Exception] "+e.getMessage());
        }
        LOGGER.debug("[MailServiceHelper::invioMail] END");
	}
	
	
}
