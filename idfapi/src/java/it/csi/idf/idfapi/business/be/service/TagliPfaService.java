/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.exceptions.ServiceException;
import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.util.InstanceStateEnum;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

import java.util.List;

public interface TagliPfaService {


    StepNumber getNumberOfNextStep(Integer idIntervento);

    TagliPfaStep1 getStep1(Integer idIntervento) throws RecordNotFoundException, RecordNotUniqueException;

    PlainSezioneId saveStep1(TagliPfaStep1 body, ConfigUtente loggedConfig) throws RecordNotUniqueException, ValidationException;

    boolean updateStep1(TagliPfaStep1 dto, ConfigUtente loggedConfig, int idIntervento) throws ValidationException, RecordNotUniqueException;

    TagliPfaStep2 getStep2(Integer idIntervento, ConfigUtente loggedConfig) throws RecordNotFoundException, RecordNotUniqueException;

    TagliPfaStep2 saveStep2(TagliPfaStep2 body, ConfigUtente loggedConfig) throws RecordNotUniqueException, ValidationException;

    boolean updateStep2(TagliPfaStep2 dto, ConfigUtente loggedConfig, int idIntervento) throws ValidationException, RecordNotUniqueException;

    boolean cambioUtilizzatore(TagliPfaStep2 dto, ConfigUtente loggedConfig, int idIntervento) throws ValidationException, RecordNotUniqueException;


    TagliPfaStep3 getStep3(Integer idIntervento, ConfigUtente loggedConfig) throws RecordNotFoundException, RecordNotUniqueException;

    TagliPfaStep3 saveStep3(TagliPfaStep3 body, ConfigUtente loggedConfig) throws RecordNotUniqueException, ValidationException;

    boolean updateStep3(TagliPfaStep3 dto, ConfigUtente loggedConfig, int idIntervento) throws ValidationException, RecordNotUniqueException;


    PlainParticelleCatastali insertParticellaTagli(ConfigUtente confUtente, PlainParticelleCatastali particellaCatasto, Integer idIntervento) throws ServiceException;

    TagliPfaStep3 getRicadenze(Integer idIntervento);

    void recalculateParticelleIntervento(Integer idIntervento, ConfigUtente configUtente) throws ServiceException;


    TagliPfaStep4 getStep4(Integer idIntervento, ConfigUtente confUtente);

    TagliPfaStep4 saveStep4(TagliPfaStep4 body, ConfigUtente confUtente);

    boolean updateStep4(TagliPfaStep4 dto, ConfigUtente loggedConfig, int idIntervento) throws ValidationException, RecordNotUniqueException;

    TagliPfaStep5 getStep5(Integer idIntervento, ConfigUtente confUtente);

    TagliPfaStep5 saveStep5(TagliPfaStep5 body, ConfigUtente confUtente) throws RecordNotUniqueException;

    boolean updateStep5(TagliPfaStep5 dto, ConfigUtente loggedConfig, int idIntervento) throws ValidationException, RecordNotUniqueException;


    void deleteIntervento(Integer idIntervento);

    List<FatDocumentoAllegato> getTuttiElencaForIntervento(Integer idIntervento, boolean isGestore);

    InvioIstanzaDTO getDataInvioIstanza(Integer idIntervento);

    void invioIstanzaTagli(Integer idIntervento, ProfessionistaElencoAllegati body, ConfigUtente confUtente) throws RecordNotFoundException, RecordNotUniqueException, Exception;

    void invioIstanzaTagliEmail(Integer idIntervento, ProfessionistaElencoAllegati body, ConfigUtente confUtente) throws RecordNotFoundException, RecordNotUniqueException, Exception;

    GeneratedFileTagliParameters getCeoIstanza(Integer idIntervento);

    List<FatDocumentoAllegato> getDocsGestoreByIntervento(Integer idIntervento);

    void updateTipoIstanza(TipoIstanzaEnum tipoIstanza, int idIntervento, ConfigUtente confUtente);

    void updateIstanzaAutorizzata(AutorizzaIstanza body, ConfigUtente confUtente);

    void updateIstanzaVerificata(AutorizzaIstanza body, ConfigUtente confUtente);

    void updateIstanzaTo(Integer idIntervento, String motivazioneRifiuto, Integer fkConfigUtente, InstanceStateEnum statoIstanza);

    InfoDocsPfa isDrelMancante(Integer idIntervento);

    FatDocumentoAllegato getDrel(Integer idIntervento);

    PlainSezioneId creaVarianteProroga(Integer idIntervento, Boolean isVariante, ConfigUtente confUtente);

    void duplicaDocumento(DocumentoAllegato documentoAllegato, Integer idInterventoNew, ConfigUtente loggedConfig) throws Exception;

    InfoVarianteProroga getInfoVarianteProroga(Integer idIntervento);


    void updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente);

    void updateDataFineInterventoTagli(AutorizzaIstanza body, ConfigUtente confUtente);


    TagliPfaStep5 updateTipologiaIstanza(TagliPfaStep5 body, ConfigUtente confUtente) throws RecordNotUniqueException;
}
