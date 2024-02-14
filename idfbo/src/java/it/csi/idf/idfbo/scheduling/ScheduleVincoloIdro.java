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


import it.csi.idf.idfbo.business.VincoloIdroBean;
import it.csi.idf.idfbo.config.BeansConfig;
import it.csi.idf.idfbo.dto.BoprocLogDTO;
import it.csi.idf.idfbo.util.Constants;

@Configuration
@EnableScheduling
public class ScheduleVincoloIdro {

	public final VincoloIdroBean vincoloIdroBean = (VincoloIdroBean) BeansConfig.getBean("vincoloIdroBean");
	protected static final Logger logger = Logger.getLogger(Constants.LOGGING.LOGGER_NAME + ".scheduling");
	private static final String THIS_CLASS = ScheduleInvioIstanza.class.getSimpleName();

	@Scheduled(fixedDelay = 300000, initialDelay = 90000)
	public void testScheduleFixedDalay() {

		logger.info("----- TEST SCHEDULE VINCOLO ------ "+System.currentTimeMillis());

		final String THIS_METHOD = "testScheduleFixedDalay";
		if (logger.isDebugEnabled()) {
			logger.info("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
		}

		try {
			
			// 
			
			if (this.vincoloIdroBean.checkInvioIstanza()) {
				
				if (!this.vincoloIdroBean.startSemaforo(Constants.COSMO.SEMAFORO_VINCOLO)) {
					logger.info("------ Invio pratica gia' in corso -------");
					return;
				}
				
				logger.info("------ SONO ENTRATO -------");

				String codiceIpaEnte = vincoloIdroBean.getValueParameter(Constants.PARAMETER.CODICEIPAENTE_COSMO, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
				String codiceTipologia = vincoloIdroBean.getValueParameter(Constants.PARAMETER.CODICETIPOLOGIA_COSMO, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
				String strOggetto = vincoloIdroBean.getValueParameter(Constants.PARAMETER.OGGETTO_COSMO, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
				List<BoprocLogDTO> lIstanze = vincoloIdroBean.getElencoIstanze(Constants.STEP.STEP_1, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
				if (lIstanze != null && lIstanze.size() > 0) {
					for (int i = 0; i < lIstanze.size(); i++)
						vincoloIdroBean.inviaPraticaStep1(lIstanze.get(i), codiceIpaEnte, codiceTipologia, strOggetto);
				}
				Thread.sleep(5000);
				lIstanze = vincoloIdroBean.getElencoIstanze(Constants.STEP.STEP_2, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
				if (lIstanze != null && lIstanze.size() > 0) {
					for (int i = 0; i < lIstanze.size(); i++)
						vincoloIdroBean.inviaPraticaStep2(lIstanze.get(i));
				}
				Thread.sleep(5000);
				lIstanze = vincoloIdroBean.getElencoIstanze(Constants.STEP.STEP_3, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
				if (lIstanze != null && lIstanze.size() > 0) {
					for (int i = 0; i < lIstanze.size(); i++)
						vincoloIdroBean.inviaPraticaStep3(lIstanze.get(i), codiceIpaEnte);
				}
				Thread.sleep(5000);
				lIstanze = vincoloIdroBean.getElencoIstanze(Constants.STEP.STEP_4, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
				if (lIstanze != null && lIstanze.size() > 0) {
					for (int i = 0; i < lIstanze.size(); i++)
						vincoloIdroBean.inviaPraticaStep4(lIstanze.get(i), codiceIpaEnte);
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
			this.vincoloIdroBean.stopSemaforo(Constants.COSMO.SEMAFORO_VINCOLO);
		}

	}

}
