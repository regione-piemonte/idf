/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.scheduling;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import it.csi.idf.idfbo.business.IstanzaBean;
import it.csi.idf.idfbo.config.BeansConfig;
import it.csi.idf.idfbo.dto.BoprocLogDTO;
import it.csi.idf.idfbo.util.Constants;

@Configuration
@EnableScheduling
public class ScheduleInvioIstanza {

	public final IstanzaBean istanzaBean = (IstanzaBean) BeansConfig.getBean("istanzaBean");
	protected static final Logger logger = Logger.getLogger(Constants.LOGGING.LOGGER_NAME + ".scheduling");
	private static final String THIS_CLASS = ScheduleInvioIstanza.class.getSimpleName();

	@Scheduled(fixedDelay = 300000, initialDelay = 60000)
	public void testScheduleFixedDalay() {

		logger.info("----- TEST SCHEDULE TRASFORMAZIONI ------ "+System.currentTimeMillis());

		final String THIS_METHOD = "testScheduleFixedDalay";
		if (logger.isDebugEnabled()) {
			logger.info("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
		}

		try {
			
			// 
			
			if (this.istanzaBean.checkInvioIstanza()) {
				
				if (!this.istanzaBean.startSemaforo(Constants.COSMO.SEMAFORO_TRASFORMAZIONI)) {
					logger.info("------ Invio pratica gia' in corso -------");
					return;
				}
				
				logger.info("------ SONO ENTRATO -------");

				String codiceIpaEnte = istanzaBean.getValueParameter(Constants.PARAMETER.CODICEIPAENTE_COSMO, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
				String codiceTipologia = istanzaBean.getValueParameter(Constants.PARAMETER.CODICETIPOLOGIA_COSMO, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
				String strOggetto = istanzaBean.getValueParameter(Constants.PARAMETER.OGGETTO_COSMO, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
				List<BoprocLogDTO> lIstanze = istanzaBean.getElencoIstanze(Constants.STEP.STEP_1, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
				if (lIstanze != null && lIstanze.size() > 0) {
					for (int i = 0; i < lIstanze.size(); i++)
						istanzaBean.inviaPraticaStep1(lIstanze.get(i), codiceIpaEnte, codiceTipologia, strOggetto);
				}
				Thread.sleep(5000);
				lIstanze = istanzaBean.getElencoIstanze(Constants.STEP.STEP_2, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
				if (lIstanze != null && lIstanze.size() > 0) {
					for (int i = 0; i < lIstanze.size(); i++)
						istanzaBean.inviaPraticaStep2(lIstanze.get(i));
				}
				Thread.sleep(5000);
				lIstanze = istanzaBean.getElencoIstanze(Constants.STEP.STEP_3, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
				if (lIstanze != null && lIstanze.size() > 0) {
					for (int i = 0; i < lIstanze.size(); i++)
						istanzaBean.inviaPraticaStep3(lIstanze.get(i), codiceIpaEnte);
				}
				Thread.sleep(5000);
				lIstanze = istanzaBean.getElencoIstanze(Constants.STEP.STEP_4, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
				if (lIstanze != null && lIstanze.size() > 0) {
					for (int i = 0; i < lIstanze.size(); i++)
						istanzaBean.inviaPraticaStep4(lIstanze.get(i), codiceIpaEnte);
				}
			}
			else {
				logger.info("------ NON SONO ENTRATO -------");
			}
		} catch (SQLException | org.springframework.dao.DataAccessException e) {
			logger.error(THIS_METHOD, e);
			// response = Response.status(999).entity("Si e' verificato un errore
			// nell'accesso ai dati della stampa richiesta").build();
		} catch (Exception e) {
			logger.error(THIS_METHOD, e);
			// response = Response.status(999).entity("Si e' verificato un errore interno non
			// previsto durante la generazione della stampa").build();
		} finally {
			if (logger.isDebugEnabled()) {
				logger.info("[" + THIS_CLASS + "." + THIS_METHOD + " END.");
			}
			this.istanzaBean.stopSemaforo(Constants.COSMO.SEMAFORO_TRASFORMAZIONI);
		}

	}

}
