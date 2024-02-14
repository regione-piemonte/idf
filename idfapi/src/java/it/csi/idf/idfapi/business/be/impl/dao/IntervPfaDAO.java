/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.*;

public interface IntervPfaDAO {
	
	void insertIntervSelvicolturale(IntervPfa intervPfa, Integer idIntervento);
	
	void insertIntervSelvicolturaleNEW(IntervPfa intervPfa, Integer idGeoPlPfa, ConfigUtente loggedConfig, Integer idIntervento);
	
	void updateIntervSelvicolturale(IntervPfa intervPfa, Integer idIntervento );

	void insertStep1(IntervPfa intervPfa, Integer idIntervento);
	
	int updateStep1(IntervPfa intervPfa, Integer idIntervento);

	void updateIntervSelvicolturaleWithChiusuraData(InterventoRiepilogo interventoRiepilogo, Integer idIntervento, ConfigUtente loggedConfig);
	
	void deleteIntervSelvicolturale(Integer idIntervento);
	
	void updateNoteFinaliRichiedente(Integer idIntervento, String oteFinaliRichiedente);
	
	int getProssimoNrProgInterv(int idTipoIntervento);
	
	IntervPfa intervPfa(Integer idIntervento);
	IntervPfaFat findInterventoSelvicolturaleTagli(Integer idIntervento);
	
	IntervPfa findInterventoSelvicolturaleTagliNew(Integer idIntervento);

	DettagliDiTaglio getDettaglioDiTaglioByInterventoId(Integer idIntervento);
	
	RicadenzaInfo getRicadenzaInfo(Integer idIntervento);

	void cambiamentoStato(Integer idIntervento, String statoIntervento);
	
	Integer getStimaMassaByIntervento(Integer idIntervento);
	
	String getStatoInterventoByIdIntervento(Integer idIntervento);
	
	Integer getIdgeoPlPfaByIdIntervento(Integer idIntervento);

	int updateIntervSelvicolturaleWithFasciaAltimetrica(Integer idFasciaAltimetrica, Integer idIntervento);

	int updateIntervSelvicolturaleWithLocalita(String localita, Integer idIntervento);

	void updateIntervSelvicolturaleWithStatoIntervento(Integer fkStatoIntervento, Integer idIntervento);

	int updateIntervSelvicolturaletagliStep4(IntervPfaFat dto, Integer idIntervento);


    Boolean isDrelMancante(Integer idIntervento);

	FatDocumentoAllegato getDrel(Integer idIntervento);

    void updateTitolaritaIntervento(Integer idIntervento, Integer idConfUtente);

//	String getNoteFinaliRichiedente(Integer idIntervento);
}
