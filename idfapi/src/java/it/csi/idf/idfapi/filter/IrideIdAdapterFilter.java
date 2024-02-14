/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import it.csi.idf.idfapi.util.Constants;
import it.csi.iride2.policy.entity.Identita;
import it.csi.iride2.policy.exceptions.MalformedIdTokenException;

/**
 * Inserisce in sessione:
 * <ul>
 * <li>l'identit&agrave; digitale relativa all'utente autenticato.
 * <li>l'oggetto <code>currentUser</code>
 * </ul>
 * Funge da adapter tra il filter del metodo di autenticaizone previsto e la
 * logica applicativa.
 *
 * @author CSIPiemonte
 */
public class IrideIdAdapterFilter implements Filter {

	public static final String IRIDE_ID_SESSIONATTR = "iride2_id";

	public static final String AUTH_ID_MARKER = "Shib-Iride-IdentitaDigitale";

	public static final String USERINFO_SESSIONATTR = "appDatacurrentUser";

	/**  */
	protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME + ".security");

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fchn)
			throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;
		String path = ((HttpServletRequest)hreq).getRequestURL().toString();
		if (hreq.getSession().getAttribute(IRIDE_ID_SESSIONATTR) == null && path.indexOf("/be/public/") == -1) {

			String marker = getToken(hreq);

			//mocking data, using devmode
			//<nome>/<cognome>/<codice fiscale>/<codice provider IRIDE>/<livello di autenticazione>/<timestamp>/<codice di verifica>

//			DEMO 24: GESTORE
//			marker = "AAAAAA00A11E000M/CSI PIEMONTE/DEMO 24/SP//1//";
			
//			DEMO 23: utente associato a UFFICIO TERRITORIALE con competenza su Provincia di Torino
//			marker = "AAAAAA00A11D000L/CSI PIEMONTE/DEMO 23/SP//1//";
			
//			DEMO 25: utente associato a UFFICIO TERRITORIALE con competenza su Province AL e AT
//			marker = "AAAAAA00A11F000N/CSI PIEMONTE/DEMO 25/ACTALIS_EU/20200701155041/16/EJT4EDylzPizr452N3PZoQ==";
			
//			DEMO 35: utente associato a COMUNE con competenza su Comune di Torino
//			marker = "AAAAAA00A11R000Z/CSI PIEMONTE/DEMO 35/SP//1//";
			
//			DEMO 36: utente CONSULTAZIONE
//			 marker = "AAAAAA00A11S000A/CSI PIEMONTE/DEMO 36/SP//1//";
			
//			DEMO 20: utente CITTADINO		
			//marker = "AAAAAA00B77B000F/CSI PIEMONTE/DEMO 20/SP//1//";
			
//			DEMO 22: utente ????		
//			marker = "AAAAAA00A11C000K/CSI PIEMONTE/DEMO 22/SP//1//";

//			DEMO 26: utente SPORTELLO
//			 marker = "AAAAAA00A11G000O/CSI PIEMONTE/DEMO 26/SP//1//";

			//DEMO 29
//			marker = "AAAAAA00A11J000R/CSI PIEMONTE/DEMO 29/SP//1//";

			//DEMO 28
//			marker = "AAAAAA00A11I000Q/CSI PIEMONTE/DEMO 28/SP//1//";

			//DEMO 43
//			marker = "AAAAAA00A11Q000Y/CSI PIEMONTE/DEMO 43/SP//1//";

			//DEMO 30
			//marker = "AAAAAA00A11K000S/CSI PIEMONTE/DEMO 30/SP//1//";
			
			
			if (marker != null) {
				try {
					Identita identita = new Identita(normalizeToken(marker));
					LOG.debug("[IrideIdAdapterFilter::doFilter] Inserito in sessione marcatore IRIDE:" + identita);
					hreq.getSession().setAttribute(IRIDE_ID_SESSIONATTR, identita);
					it.csi.idf.idfapi.dto.UserInfo userInfo = new it.csi.idf.idfapi.dto.UserInfo();
					
					userInfo.setNome(identita.getNome());
					userInfo.setCognome(identita.getCognome());
					userInfo.setCodFisc(identita.getCodFiscale());
					// Added on 02.07.2020 - customer instruction
					userInfo.setCommunity(identita.getIdProvider());
					userInfo.setCodRuolo("--");
					userInfo.setRuolo("---");
					hreq.getSession().setAttribute(USERINFO_SESSIONATTR, userInfo);
					
				} catch (MalformedIdTokenException e) {
					LOG.error("[IrideIdAdapterFilter::doFilter] " + e.toString(), e);
				}
			} else {
				// il marcatore deve sempre essere presente altrimenti e' una
				// condizione di errore (escluse le pagine home e di servizio)
				if (mustCheckPage(hreq.getRequestURI())) {
					LOG.error(
							"[IrideIdAdapterFilter::doFilter] Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
					throw new ServletException(
							"Tentativo di accesso a pagina non home e non di servizio senza token di sicurezza");
				}
			}
		}

		fchn.doFilter(req, resp);

	}

	private boolean mustCheckPage(String requestURI) {

		return true;
	}

	public void destroy() {
		// NOP
	}

	private static final String DEVMODE_INIT_PARAM = "devmode";

	private boolean devmode = false;

	public void init(FilterConfig fc) throws ServletException {
		String sDevmode = fc.getInitParameter(DEVMODE_INIT_PARAM);
		if ("true".equals(sDevmode)) {
			devmode = true;
		} else {
			devmode = false;
		}
	}

	public String getToken(HttpServletRequest httpreq) {
		String marker = (String) httpreq.getHeader(AUTH_ID_MARKER);
		if (marker == null && devmode) {
			return getTokenDevMode(httpreq);
		} else {
			try {
				// gestione dell'encoding
				String decodedMarker = new String(marker.getBytes("ISO-8859-1"), "UTF-8");
				return decodedMarker;
			} catch (java.io.UnsupportedEncodingException e) {
				// se la decodifica non funziona comunque sempre meglio restituire
				// il marker originale non decodificato
				return marker;
			}
		}
	}

	private String getTokenDevMode(HttpServletRequest httpreq) {
		String marker = (String) httpreq.getParameter(AUTH_ID_MARKER);
		return marker;
	}

	private String normalizeToken(String token) {
		return token;
	}

}
