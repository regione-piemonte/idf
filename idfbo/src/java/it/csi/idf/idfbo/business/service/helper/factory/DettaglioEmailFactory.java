/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.business.service.helper.factory;

import static it.csi.idf.idfbo.util.builder.ToStringBuilder.objectToString;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import it.csi.idf.idfbo.business.service.helper.dto.DettaglioEmail;
import it.csi.idf.idfbo.dto.IstanzaInfoMailByIstanzaDto;
import it.csi.idf.idfbo.dto.MailConfigDto;
import it.csi.idf.idfbo.dto.TipoMailDto;
import it.csi.idf.idfbo.util.Constants;
import it.csi.idf.idfbo.util.ConvertUtil;
import it.csi.idf.idfbo.util.Message;



public final class DettaglioEmailFactory {
	
	protected static final Logger logger     = Logger
		      .getLogger(Constants.LOGGING.LOGGER_NAME + ".business");

	public static DettaglioEmail createDettaglioEmail(MailConfigDto configMail, TipoMailDto mailDto,
			IstanzaInfoMailByIstanzaDto dto) {
		logger.info("INVIO 1");
		final DettaglioEmail result = new DettaglioEmail();
		
		result.setHost(configMail.getHost());
		result.setUserID(configMail.getUserId());
		result.setPassword(configMail.getPsw());
		result.setPort(ConvertUtil.convertToString(configMail.getPorta()));
		result.setProtocol(configMail.getProtocollo());
		result.setMittente(configMail.getMittente());
		
		result.setOggetto(mailDto.getOggetto().replaceFirst("##valueTipoIstanza##",dto.getTipoIstanza()));
		
		logger.info("FACTORY 1 "+objectToString(mailDto));
		
		logger.info("FACTORY 2 "+objectToString(dto));
		
		
		final Message msgCorpo = new Message(mailDto.getTesto(),
				ConvertUtil.convertToString(dto.getItifNrIstanzaForestale()), 
				dto.getDenominazione_gestore(), 
				dto.getTelefono_gestore(),
				dto.getMail_gestore(), 	
				dto.getPec_gestore(),
				dto.getTipoIstanza());
		
		result.setCorpo(msgCorpo.getText());
		
		List<String> destinatari = new ArrayList<String>();
		List<String> destinatariCC = new ArrayList<String>();
		
		destinatari.add(dto.getMail_richiedente());
		
		destinatariCC.add(dto.getMail_gestore());
		
		result.setDestinatari(destinatari);
		result.setDestinatariCC(destinatariCC);
		
		return result;
	}
	
	
	
}
