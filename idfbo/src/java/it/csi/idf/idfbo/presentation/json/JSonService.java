/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.presentation.json;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import it.csi.idf.idfbo.business.IstanzaBean;
import it.csi.idf.idfbo.business.SelvicolturaliTagliBean;
import it.csi.idf.idfbo.business.VincoloIdroBean;
import it.csi.idf.idfbo.business.service.helper.dto.cosmo.AggiornaStatoPraticaRequest;
import it.csi.idf.idfbo.config.BeansConfig;
import it.csi.idf.idfbo.dto.BoprocLogDTO;
import it.csi.idf.idfbo.dto.IdfWSEsitoInput;
import it.csi.idf.idfbo.dto.IdfWSEsitoOut;
import it.csi.idf.idfbo.util.Constants;
import it.csi.idf.idfbo.util.IdfBoUtils;

@Consumes({ "application/json" })
@Produces({ "application/json" })
@Path("/")
public class JSonService {
	public final IstanzaBean istanzaBean = (IstanzaBean) BeansConfig.getBean("istanzaBean");
	public final VincoloIdroBean vincoloIdroBean = (VincoloIdroBean) BeansConfig.getBean("vincoloIdroBean");
	public final SelvicolturaliTagliBean selvicolturaliTagliBean = (SelvicolturaliTagliBean) BeansConfig
			.getBean("selvicolturaliTagliBean");
	protected static final Logger logger = Logger.getLogger(Constants.LOGGING.LOGGER_NAME + ".presentation");
	private static final String THIS_CLASS = JSonService.class.getSimpleName();

	/******************************************************************************
	 * API IN LETTURA *
	 ******************************************************************************/

	@PUT
	@Path("pratica/stato-pratica/{idPratica}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putStatoPraticaTrasformazioni(@PathParam("idPratica") String idPratica,
			@RequestBody AggiornaStatoPraticaRequest payload, @Context HttpServletRequest request) throws Exception {
		final String THIS_METHOD = "stato-pratica";
		if (logger.isDebugEnabled()) {
			logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
		}

		Response response = null;
		// IdfWSEsitoOut esito = new IdfWSEsitoOut();
		// esito.setEsito("0");

		/*
		 * if(payload == null) { logger.error("[" + THIS_CLASS + "." + THIS_METHOD +
		 * "I dati inviati non sono formalmente corretti per la pratica -"+idPratica);
		 * response = Response.status(HttpStatus.BAD_REQUEST.value()).
		 * entity("I dati inviati non sono formalmente corretti per la pratica -"
		 * +idPratica).build(); return response; }
		 */

		logger.info("idPratica = " + idPratica);

		try {
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(payload);

			logger.info("idPratica = " + idPratica + " " + json);

			String codiceTipologiaVincolo = istanzaBean.getValueParameter(Constants.PARAMETER.CODICETIPOLOGIA_COSMO,
					Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
			String codiceTipologiaForeste = istanzaBean.getValueParameter(Constants.PARAMETER.CODICETIPOLOGIA_COSMO,
					Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
			String codiceTipologiaSelvicolturali = istanzaBean.getValueParameter(
					Constants.PARAMETER.CODICETIPOLOGIA_COSMO, Constants.AMBITO_ISTANZA.VINCOLO_TAGLISELV);

			if (payload != null && payload.getDocumenti() != null && payload.getDocumenti().size() > 0
					&& payload.getTipo() != null) {
				if (payload.getTipo().getCodice().equalsIgnoreCase(codiceTipologiaForeste)) {
					boolean trovato = false;
					for (int i = 0; i < payload.getDocumenti().size(); i++) {
						// Da prendere quello che nn ha idPadre valorizzato....
						// payload.getDocumenti().get(i).getIdPadre() == null o stringa vuota
						if (payload.getDocumenti().get(i).getIdPadre() == null
								|| (payload.getDocumenti().get(i).getIdPadre() != null
										&& payload.getDocumenti().get(i).getIdPadre().equals(""))) {
							if (payload.getDocumenti().get(i).getArchiviazione() != null
									&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo() != null
									&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo()
											.getData() != null
									&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo()
											.getNumero() != null) {
								trovato = true;
								boolean updateOK = istanzaBean.updateProtocollo(idPratica,
										payload.getDocumenti().get(i).getArchiviazione().getProtocollo().getNumero(),
										payload.getDocumenti().get(i).getArchiviazione().getProtocollo().getData(),
										json);

								if (!updateOK) {
									logger.error("[" + THIS_CLASS + "." + THIS_METHOD + "La pratica -" + idPratica
											+ " non e' stata trovata");
									response = Response.status(HttpStatus.NOT_FOUND.value())
											.entity("La pratica -" + idPratica + " non e' stata trovata").build();
									return response;
								}

								// istanzaBean.storicizzaBoprocLog(idPratica, json);

								// invio mail...
								try {
									istanzaBean.inviaMailProtocollo(Long.valueOf(idPratica));
									istanzaBean.chiusuraPratica(idPratica);
								} catch (Exception e) {
									logger.error("[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:" + idPratica
											+ " non e' stato possibile inviare la mail-" + e.getMessage());

									String erroreStr = "[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:"
											+ idPratica + " non e' stato possibile inviare la mail-" + e.getMessage();
									logger.error(erroreStr);
									if (erroreStr.length() > 1999)
										erroreStr = erroreStr.substring(0, 1999);

									istanzaBean.updateBoprocLog(idPratica, 1, Constants.ERRORS.COD_ERRORE_INTERNO,
											erroreStr, json);
								}
							}
						}
					}

					if (!trovato) {
						logger.error("[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:" + idPratica
								+ " non e' stato trovato il documento padre o i dati del protocollo sono nulli.");
						response = Response.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).entity("per la pratica:"
								+ idPratica + " non e' stato trovato il padre o i dati del protocollo sono nulli.")
								.build();

						istanzaBean.updateBoprocLog(idPratica, 1, Constants.ERRORS.COD_ERRORE_INTERNO, "per la pratica:"
								+ idPratica
								+ " non e' stato trovato il documento padre o i dati del protocollo sono nulli.", json);

					}
				}

				if (payload.getTipo().getCodice().equalsIgnoreCase(codiceTipologiaVincolo)) {
					boolean trovato = false;
					for (int i = 0; i < payload.getDocumenti().size(); i++) {
						String fkIstanzaNum = IdfBoUtils.FORMAT.getFkIstanza(idPratica);
						String numInvioIstanza = IdfBoUtils.FORMAT.getNumInvioFkIstanza(idPratica);
						// Da prendere quello che nn ha idPadre valorizzato....
						// payload.getDocumenti().get(i).getIdPadre() == null o stringa vuota
						if (payload.getDocumenti().get(i).getIdPadre() == null
								|| (payload.getDocumenti().get(i).getIdPadre() != null
										&& payload.getDocumenti().get(i).getIdPadre().equals(""))) {

							if (numInvioIstanza.equalsIgnoreCase("1")) {
								if (payload.getDocumenti().get(i).getArchiviazione() != null
										&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo() != null
										&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo()
												.getData() != null
										&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo()
												.getNumero() != null) {
									trovato = true;
									boolean updateOK = vincoloIdroBean.updateProtocollo(idPratica,
											payload.getDocumenti().get(i).getArchiviazione().getProtocollo()
													.getNumero(),
											payload.getDocumenti().get(i).getArchiviazione().getProtocollo().getData(),
											json);

									if (!updateOK) {
										logger.error("[" + THIS_CLASS + "." + THIS_METHOD + "La pratica -" + idPratica
												+ " non e' stata trovata");
										response = Response.status(HttpStatus.NOT_FOUND.value())
												.entity("La pratica -" + idPratica + " non e' stata trovata").build();
										return response;
									}

									// istanzaBean.storicizzaBoprocLog(idPratica, json);

									// invio mail...
									try {
										vincoloIdroBean.inviaMailProtocollo(Long.valueOf(fkIstanzaNum),
												Constants.MAIL.VINCOLO_IDRO_PROTOCOLLO);
										istanzaBean.chiusuraPratica(idPratica);
									} catch (Exception e) {
										logger.error(
												"[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:" + idPratica
														+ " non e' stato possibile inviare la mail-" + e.getMessage());

										String erroreStr = "[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:"
												+ idPratica + " non e' stato possibile inviare la mail-"
												+ e.getMessage();
										logger.error(erroreStr);
										if (erroreStr.length() > 1999)
											erroreStr = erroreStr.substring(0, 1999);

										istanzaBean.updateBoprocLog(idPratica, 1, Constants.ERRORS.COD_ERRORE_INTERNO,
												erroreStr, json);
									}
								}
							}
						}

						if (!numInvioIstanza.equalsIgnoreCase("1")) {
							trovato = true;
							try {
								vincoloIdroBean.inviaMailProtocollo(Long.valueOf(fkIstanzaNum),
										Constants.MAIL.VINCOLO_IDRO_ALTRO);
								istanzaBean.storicizzaBoprocLog(idPratica, json);
							} catch (Exception e) {
								logger.error("[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:" + idPratica
										+ " non e' stato possibile inviare la mail-" + e.getMessage());

								String erroreStr = "[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:" + idPratica
										+ " non e' stato possibile inviare la mail-" + e.getMessage();
								logger.error(erroreStr);
								if (erroreStr.length() > 1999)
									erroreStr = erroreStr.substring(0, 1999);

								istanzaBean.updateBoprocLog(idPratica, 1, Constants.ERRORS.COD_ERRORE_INTERNO,
										erroreStr, json);
							}
						}
					}

					if (!trovato) {
						logger.error("[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:" + idPratica
								+ " non e' stato trovato il documento padre o i dati del protocollo sono nulli.");
						response = Response.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).entity("per la pratica:"
								+ idPratica + " non e' stato trovato il padre o i dati del protocollo sono nulli.")
								.build();

						istanzaBean.updateBoprocLog(idPratica, 1, Constants.ERRORS.COD_ERRORE_INTERNO, "per la pratica:"
								+ idPratica
								+ " non e' stato trovato il documento padre o i dati del protocollo sono nulli.", json);

					}
				}
					//IF per Selvicolturali
				if (payload.getTipo().getCodice().equalsIgnoreCase(codiceTipologiaSelvicolturali)) {
					boolean trovato = false;
					for (int i = 0; i < payload.getDocumenti().size(); i++) {
						String fkIstanzaNum = IdfBoUtils.FORMAT.getFkIstanza(idPratica);
						String numInvioIstanza = IdfBoUtils.FORMAT.getNumInvioFkIstanza(idPratica);
						// Da prendere quello che nn ha idPadre valorizzato....
						// payload.getDocumenti().get(i).getIdPadre() == null o stringa vuota
						if (payload.getDocumenti().get(i).getIdPadre() == null
								|| (payload.getDocumenti().get(i).getIdPadre() != null
										&& payload.getDocumenti().get(i).getIdPadre().equals(""))) {

							if (numInvioIstanza.equalsIgnoreCase("1")) {
								if (payload.getDocumenti().get(i).getArchiviazione() != null
										&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo() != null
										&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo()
												.getData() != null
										&& payload.getDocumenti().get(i).getArchiviazione().getProtocollo()
												.getNumero() != null) {
									trovato = true;
									boolean updateOK = selvicolturaliTagliBean.updateProtocollo(idPratica,
											payload.getDocumenti().get(i).getArchiviazione().getProtocollo()
													.getNumero(),
											payload.getDocumenti().get(i).getArchiviazione().getProtocollo().getData(),
											json);

									if (!updateOK) {
										logger.error("[" + THIS_CLASS + "." + THIS_METHOD + "La pratica -" + idPratica
												+ " non e' stata trovata");
										response = Response.status(HttpStatus.NOT_FOUND.value())
												.entity("La pratica -" + idPratica + " non e' stata trovata").build();
										return response;
									}

									// istanzaBean.storicizzaBoprocLog(idPratica, json);

									// invio mail...
									try {
										selvicolturaliTagliBean.inviaMailProtocollo(Long.valueOf(fkIstanzaNum),
												Constants.MAIL.VINCOLO_SELVICOLTURALI_1_PROTOCOLLO);
										istanzaBean.chiusuraPratica(idPratica);
									} catch (Exception e) {
										logger.error(
												"[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:" + idPratica
														+ " non e' stato possibile inviare la mail-" + e.getMessage());

										String erroreStr = "[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:"
												+ idPratica + " non e' stato possibile inviare la mail-"
												+ e.getMessage();
										logger.error(erroreStr);
										if (erroreStr.length() > 1999)
											erroreStr = erroreStr.substring(0, 1999);

										istanzaBean.updateBoprocLog(idPratica, 1, Constants.ERRORS.COD_ERRORE_INTERNO,
												erroreStr, json);
									}
								}
							}
						}

						if (!numInvioIstanza.equalsIgnoreCase("1")) {
							trovato = true;
							try {
								selvicolturaliTagliBean.inviaMailProtocollo(Long.valueOf(fkIstanzaNum),
										Constants.MAIL.VINCOLO_SELVICOLTURALI_2_PROTOCOLLO);
								istanzaBean.storicizzaBoprocLog(idPratica, json);
							} catch (Exception e) {
								logger.error("[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:" + idPratica
										+ " non e' stato possibile inviare la mail-" + e.getMessage());

								String erroreStr = "[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:" + idPratica
										+ " non e' stato possibile inviare la mail-" + e.getMessage();
								logger.error(erroreStr);
								if (erroreStr.length() > 1999)
									erroreStr = erroreStr.substring(0, 1999);

								istanzaBean.updateBoprocLog(idPratica, 1, Constants.ERRORS.COD_ERRORE_INTERNO,
										erroreStr, json);
							}
						}
					}

					if (!trovato) {
						logger.error("[" + THIS_CLASS + "." + THIS_METHOD + "per la pratica:" + idPratica
								+ " non e' stato trovato il documento padre o i dati del protocollo sono nulli.");
						response = Response.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).entity("per la pratica:"
								+ idPratica + " non e' stato trovato il padre o i dati del protocollo sono nulli.")
								.build();

						istanzaBean.updateBoprocLog(idPratica, 1, Constants.ERRORS.COD_ERRORE_INTERNO, "per la pratica:"
								+ idPratica
								+ " non e' stato trovato il documento padre o i dati del protocollo sono nulli.", json);

					}
				}

			} else {
				logger.error("[" + THIS_CLASS + "." + THIS_METHOD
						+ "I dati inviati non sono formalmente corretti per la pratica -" + idPratica);
				response = Response.status(HttpStatus.BAD_REQUEST.value())
						.entity("I dati inviati non sono formalmente corretti per la pratica -" + idPratica).build();

				istanzaBean.updateBoprocLog(idPratica, 1, Constants.ERRORS.COD_ERRORE_INTERNO,
						"La lista dei documenti per la pratica-" + idPratica + " sono nulli.", json);
			}

			// Non ci sono stati errori
			if (response == null)
				response = Response.ok(HttpStatus.OK).build();
		} catch (org.springframework.dao.DataAccessException e) {
			logger.error(THIS_METHOD, e);
			response = Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.entity("Si e' verificato un errore interno non previsto").build();
		} catch (Exception e) {
			logger.error(THIS_METHOD, e);
			response = Response.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.entity("Si e' verificato un errore interno non previsto").build();
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + " END.");
			}
		}
		return response;
	}

	@POST
	@Path("/trasformazioni/pratica/invioPratica")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response invioPraticaTrasformazioni(IdfWSEsitoInput input, @Context HttpServletRequest request)
			throws Exception {
		final String THIS_METHOD = "invioPraticaTrasformazioni";
		if (logger.isDebugEnabled()) {
			logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
		}

		Response response = null;
		IdfWSEsitoOut esito = new IdfWSEsitoOut();
		esito.setEsito("0");

		long idIstanza = input.getIdIstanza();

		if (logger.isDebugEnabled()) {
			logger.debug("idIstanza = " + idIstanza);
		}

		try {

			// istanzaBean.provaInsertJason();

			String codiceIpaEnte = istanzaBean.getValueParameter(Constants.PARAMETER.CODICEIPAENTE_COSMO,
					Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
			String codiceTipologia = istanzaBean.getValueParameter(Constants.PARAMETER.CODICETIPOLOGIA_COSMO,
					Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
			String strOggetto = istanzaBean.getValueParameter(Constants.PARAMETER.OGGETTO_COSMO,
					Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
			List<BoprocLogDTO> lIstanze = istanzaBean.getElencoIstanze(Constants.STEP.STEP_1,
					Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
			if (lIstanze != null && lIstanze.size() > 0) {
				for (int i = 0; i < lIstanze.size(); i++)
					istanzaBean.inviaPraticaStep1(lIstanze.get(i), codiceIpaEnte, codiceTipologia, strOggetto);
			}
			lIstanze = istanzaBean.getElencoIstanze(Constants.STEP.STEP_2, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
			if (lIstanze != null && lIstanze.size() > 0) {
				for (int i = 0; i < lIstanze.size(); i++)
					istanzaBean.inviaPraticaStep2(lIstanze.get(i));
			}
			lIstanze = istanzaBean.getElencoIstanze(Constants.STEP.STEP_3, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
			if (lIstanze != null && lIstanze.size() > 0) {
				for (int i = 0; i < lIstanze.size(); i++)
					istanzaBean.inviaPraticaStep3(lIstanze.get(i), codiceIpaEnte);
			}
			lIstanze = istanzaBean.getElencoIstanze(Constants.STEP.STEP_4, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
			if (lIstanze != null && lIstanze.size() > 0) {
				for (int i = 0; i < lIstanze.size(); i++)
					istanzaBean.inviaPraticaStep4(lIstanze.get(i), codiceIpaEnte);
			}

			response = Response.ok(esito).build();
		} catch (SQLException | org.springframework.dao.DataAccessException e) {
			logger.error(THIS_METHOD, e);
			response = Response.status(999)
					.entity("Si e' verificato un errore nell'accesso ai dati della stampa richiesta").build();
		} catch (Exception e) {
			logger.error(THIS_METHOD, e);
			response = Response.status(999)
					.entity("Si e' verificato un errore interno non previsto durante la generazione della stampa")
					.build();
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + " END.");
			}
		}
		return response;
	}

	@GET
	@Path("/pratica/testResources")
	public boolean testResources() {
		return istanzaBean.testDB();
	}
}
