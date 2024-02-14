/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.text.ParseException;
import java.util.Map;

import it.csi.idf.idfapi.business.be.vincolo.pojo.AutorizzaVincolo;
import it.csi.idf.idfapi.dto.*;
import it.csi.idf.idfapi.util.InstanceStateEnum;
import it.csi.idf.idfapi.util.PaginatedList;
import it.csi.idf.idfapi.util.TipoIstanzaEnum;

public interface IstanzaForestDAO {
	int createIstanzaForest(IstanzaForest istanzaForest);
	
	int getNumberOfInstanceType(int instanceTypeId);

	int getNumberOfAllInstanceType();

	int getNumberOfInstanceTagli();

	IstanzaForest getByIdIntervento(int idIntervento);

	PaginatedList<BOSearchResult> backOfficeSearch(Map<String, String> queryParams, ConfigUtente configUtente) throws ParseException;

	PaginatedList<PlainSoggettoIstanzaTagli> backOfficeTagliSearch(Map<String, String> queryParams, ConfigUtente configUtente) throws ParseException;

	PaginatedList<PlainSoggettoIstanzaVincolo> backOfficeVincoloSearch(Map<String, String> queryParams, ConfigUtente configUtente) throws ParseException;

	void updateInvioIstanza(Integer idIntervento, String modificaUtilizzatore);
	
	int updateTipoIstanza(Integer idTipoIstanza, Integer fkConfigUtente, Integer idIntervento);

	void updateIstanzaTo(Integer idIntervento, String motivazioneRifiuto,Integer fkConfigUtente, InstanceStateEnum verificata);

	void updateIstanzaVincoloAutorizzata(AutorizzaVincolo body, ConfigUtente confUtente);

    void updateIstanzaTagliAutorizzata(AutorizzaIstanza autorizzazione, ConfigUtente confUtente);

    void updateEnteCompetente(Integer idIntervento, Integer idSoggetto);
    
    void updateMotivazione(Integer idIntervento, String motivazione);
	
	ConfigUtente getUtenteForIstanzaById(Integer idIntervento);
	
	IstanzaDetails getIstanzaDetailsByIdIntervento(Integer idIntervento);
	
	void deleteById(Integer idIntervento);
	
	TipoIstanzaEnum getLastIstanzaTipo(Integer fkConfigUtente);
	
	int updateToAccoltaInSilenzio();

	Integer findNrIstanzaForestByIdIntervento(Integer idIntervento);
}
