/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.util.service.integration.aaep.AziendaAAEP;
import it.csi.idf.idfapi.util.service.integration.aaep.JavaServiceDesc;
import it.csi.idf.idfapi.util.service.integration.aaep.JavaServiceDescServiceLocator;
import it.csi.idf.idfapi.util.service.integration.aaep.ListaAttEconProd;
import it.csi.idf.idfapi.util.service.integration.aaep.UserException;
import it.csi.wso2.apiman.oauth2.helper.GenericWrapperFactoryBean;


public class AaepServiceHelper extends AbstractServiceHelper{

	private static final String AAEP_CERCA_PER_CODICE_FISCALE_EXCEPTION_MSG = "it.csi.csi.wrapper.UserException:cercaPerCodiceFiscaleAAEP: Nessun record trovato";
	private static final String AAEP_CERCA_PER_FILTRI_EXCEPTION_MSG = "it.csi.csi.wrapper.UserException:cercaPerFiltri: Nessun record trovato";
	

	private JavaServiceDesc service;


	private ApiManagerServiceHelper apiManagerServiceHelper;

	/**
	 * @return the apiManagerServiceHelper
	 */
	public ApiManagerServiceHelper getApiManagerServiceHelper() {
		return apiManagerServiceHelper;
	}

	/**
	 * @param apiManagerServiceHelper
	 *            the apiManagerServiceHelper to set
	 */
	public void setApiManagerServiceHelper(ApiManagerServiceHelper apiManagerServiceHelper) {
		this.apiManagerServiceHelper = apiManagerServiceHelper;
	}

	protected String urlService = null;

	public AaepServiceHelper(String urlService) {
		this.urlService = urlService;
	}

	private JavaServiceDesc getService()
			throws ServiceException, IOException, ClassNotFoundException, javax.xml.rpc.ServiceException {
		LOGGER.debug("[AaepServiceHelper::getService] BEGIN");

		// if (this.service == null) {
		JavaServiceDescServiceLocator serviceLoc = new JavaServiceDescServiceLocator();

		JavaServiceDesc port = serviceLoc.getAAEPCSI();

		GenericWrapperFactoryBean gwfb = new GenericWrapperFactoryBean();
		LOGGER.debug("url service " + this.urlService);
		gwfb.setEndPoint(this.urlService);
		gwfb.setWrappedInterface(JavaServiceDesc.class);
		gwfb.setPort(port);
		gwfb.setTokenRetryManager(this.apiManagerServiceHelper.getTokenRetryManagerAxis1());
		this.service = (JavaServiceDesc) gwfb.create();

		LOGGER.debug("[AaepServiceHelper::getService] Service 'JavaServiceDesc' INITIALIZED");
		LOGGER.debug("[AaepServiceHelper::getService] END");

		return this.service;
	}
	
	/**
     * Restituisce l'<em>elenco delle aziende</em>, istanza di tipo {@link ListaAttEconProd},
     * per il codice fiscale del soggetto.
     * <p><code>Nel caso non sia stata trovata alcuna <em>Azienda</em> per i parametri dati,
     * il servizio <code>AAEP</code> solleva una <strong>eccezione</strong> di tipo {@link UserException},
     * la cui propriet&agrave; {@link UserException#getMessage()} vale: <strong>{@value #AAEP_CERCA_PER_CODICE_FISCALE_EXCEPTION_MSG}</strong>
     * Questa particolare occorrenza viene intepretata in modo da restituire {@code null}</code>.</p>
     *
     * @param codiceFiscaleSoggetto codice fiscale del soggetto
     * @return array di tipo <em>ListaAttEconProd</em>, di tipo {@link ListaAttEconProd}
     * @throws ServiceException
     */
	public ListaAttEconProd[] cercaAziendeAAEPByCodiceFiscale(String codiceFiscaleSoggetto)
			throws ServiceException {
		LOGGER.debug("[AaepServiceHelper::cercaAziendeAAEPByCodiceFiscale] BEGIN");
		ListaAttEconProd[] result = null;
		/*
		 * Stando alla documentazione ufficiale i 15 parametri di ricerca sono: 1
		 * sottosistema (AAEP, INFOCAMERE o FP) obbligatorio 2 stato cessazione (0, 1,
		 * 2) obbligatorio 3 codice fiscale azienda 4 tipo codice fiscale 5 ragione
		 * sociale 6 tipo ragione sociale 7 carattere di ricerca jolly 8 codice ATECO 9
		 * tipo codice ATECO 10 codice fiscale persona 11 codice natura giuridica 12
		 * sigla provincia 13 codice comune 14 nome localita' 15 tipo sede
		 */
		try {
			result = this.getService().cercaPerFiltri("AAEP", "0", "", "", "", "", "", "", "", codiceFiscaleSoggetto,
					"", "", "", "", "");

		} catch (Exception e) {
			if (StringUtils.startsWithIgnoreCase(e.getMessage(), AAEP_CERCA_PER_FILTRI_EXCEPTION_MSG)) {
				// Come insegna Magister Cornacchia, e' una soluzione orrenda ma AAEP gestisce
				// cosi' gli errori
				return null;
			} else {
				throw new ServiceException("[Errore AAEP cercaPerFiltri] "+e.getMessage());
			}
		} finally {
			LOGGER.debug("[AaepServiceHelper::cercaAziendeAAEPByCodiceFiscale] END");
		}
		return result;
	}
	
	/**
     * Restituisce l'<em>Azienda</em>, istanza di tipo {@link AziendaAAEP},
     * per il codice fiscale dell'azienda dato.
     * <p><code>Nel caso non sia stata trovata alcuna <em>Azienda</em> per i parametri dati,
     * il servizio <code>AAEP</code> solleva una <strong>eccezione</strong> di tipo {@link UserException},
     * la cui propriet&agrave; {@link UserException#getMessage()} vale: <strong>{@value #AAEP_CERCA_PER_CODICE_FISCALE_EXCEPTION_MSG}</strong>
     * Questa particolare occorrenza viene intepretata in modo da restituire {@code null}</code>.</p>
     *
     * @param codiceFiscaleSoggetto codice fiscale del soggetto
     * @return l'<em>Azienda</em>, istanza di tipo {@link AziendaAAEP}
     * @throws ServiceException
     */
    public AziendaAAEP getAziendaByCodiceFiscale(String codiceFiscaleAzienda) throws ServiceException {
        LOGGER.debug("[AaepServiceHelper::getAziendaByCodiceFiscaleAaep] BEGIN");

        try {
            return this.getService().cercaPerCodiceFiscaleAAEP(codiceFiscaleAzienda, null, null);
        } catch (Exception e) {
            // :-X this code not only smells, it really stinks: but we can't fare any better due to AAEP service inherent design flaws...
            if (StringUtils.startsWithIgnoreCase(e.getMessage(), AAEP_CERCA_PER_CODICE_FISCALE_EXCEPTION_MSG)) {
                return null;
            } else {
            	throw new ServiceException("[Errore AAEP cercaPerCodiceFiscaleAAEP] "+e.getMessage());
            }
        } finally {
            LOGGER.debug("[AaepServiceHelper::getAziendaByCodiceFiscaleAaep] END");
        }
    }
}
