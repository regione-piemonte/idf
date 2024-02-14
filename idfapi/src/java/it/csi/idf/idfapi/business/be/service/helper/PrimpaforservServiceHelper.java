/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang3.ArrayUtils;

import it.csi.idf.idfapi.util.ConvertUtil;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.CSIException;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.CampoObbligatorioException;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.Comune;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.Istanza;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.PrimpaforservIstanzaServiceLocator;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.PrimpaforservIstanza_PortType;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.Specie;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.SystemException;
import it.csi.idf.idfapi.util.service.integration.primpaforserv.UnrecoverableException;

public class PrimpaforservServiceHelper extends AbstractServiceHelper {
	
	private PrimpaforservIstanza_PortType port;
	
	protected String urlService = null;
	
	public PrimpaforservServiceHelper(String urlService) {
		this.urlService = urlService;
	}
	
	private PrimpaforservIstanza_PortType getService()throws it.csi.idf.idfapi.business.be.exceptions.ServiceException {
		LOGGER.debug("[PrimpaforservServiceHelper::getService] BEGIN");
		PrimpaforservIstanzaServiceLocator sl = new PrimpaforservIstanzaServiceLocator();
		try {
			 port = sl.getprimpaforservIstanza(new URL(this.urlService));
		} catch (MalformedURLException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PRIMPAFORSERV getService MalformedURLException] "+e.getMessage());
		} catch (ServiceException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PRIMPAFORSERV getService ServiceException] "+e.getMessage());
		}
		LOGGER.debug("[PrimpaforservServiceHelper::getService] END");
		return port;
	}
	
	public Istanza getDettaglioIstanzaPrimpa(Integer idIstanza) throws it.csi.idf.idfapi.business.be.exceptions.ServiceException {
		LOGGER.debug("[PrimpaforservServiceHelper::getDettaglioIstanzaPrimpa] BEGIN");
		Istanza result = null;
		try {
			result = this.getService().cercaIstanzePerNumeroIstanza(idIstanza);
			if(result!=null) {
				result.setStrElencoComuniInteressati(this.parseElencoComuni(result.getComuniInteressati()));
				result.setStrElencoSpecieCoinvolte(this.parseElencoSpecie(result.getSpecieCoinvolte()));
			}
		} catch (CampoObbligatorioException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PRIMPAFORSERV getDettaglioIstanzaPrimpa CampoObbligatorioException] "+e.getMessage());
		} catch (UnrecoverableException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PRIMPAFORSERV getDettaglioIstanzaPrimpa UnrecoverableException] "+e.getMessage());
		} catch (SystemException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PRIMPAFORSERV getDettaglioIstanzaPrimpa SystemException] "+e.getMessage());
		} catch (CSIException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PRIMPAFORSERV getDettaglioIstanzaPrimpa CSIException] "+e.getMessage());
		} catch (RemoteException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PRIMPAFORSERV getDettaglioIstanzaPrimpa RemoteException] "+e.getMessage());
		} catch (it.csi.idf.idfapi.business.be.exceptions.ServiceException e) {
			throw new it.csi.idf.idfapi.business.be.exceptions.ServiceException("[Errore PRIMPAFORSERV getDettaglioIstanzaPrimpa ServiceException] "+e.getMessage());
		}
		LOGGER.debug("[PrimpaforservServiceHelper::getDettaglioIstanzaPrimpa] END");
		return result;
	}

	private String parseElencoSpecie(Specie[] specieCoinvolte) {
		StringBuilder sb = new StringBuilder();
		if(specieCoinvolte!=null && specieCoinvolte.length>0) {
			for (int i = 0; i < specieCoinvolte.length; i++) {
				if(i!=0) {
					sb.append(", ");
				}
				sb.append(specieCoinvolte[i].getNomeVolgare());
				
			}
		}
		return sb.toString();
	}

	private String parseElencoComuni(Comune[] comuniInteressati) {
		StringBuilder sb = new StringBuilder();
		if(comuniInteressati!=null && comuniInteressati.length>0) {
			for (int i = 0; i < comuniInteressati.length; i++) {
				if(i!=0) {
					sb.append(", ");
				}
				sb.append(comuniInteressati[i].getDsComune());
				
			}
		}
		return sb.toString();
	}
	
}
