/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.business.service.helper.factory;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public final class AddressFactory {
	
	public static Address[] createAddresses(List<String> destinatari) {
		final List<InternetAddress> list = new ArrayList<InternetAddress>();
		try {
	        if (destinatari != null) {
	            for (final String s : destinatari) {
	                list.add(new InternetAddress(s));
	            }
	        }
		}
	    catch (AddressException e) {
	    	return null;
	    }
        return list.toArray(new Address[list.size()]);
	}
}
