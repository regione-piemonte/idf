/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotFoundException;
import it.csi.idf.idfapi.dto.DichPropCatasto;
import it.csi.idf.idfapi.dto.PlainIntDTO;
import it.csi.idf.idfapi.dto.PlainParticelleCat;
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.dto.PlainStringDTO;
import it.csi.idf.idfapi.dto.PropCatasto;
import it.csi.idf.idfapi.dto.PropcatastoIntervento;

public interface PropCatastoDAO {

	List<PropCatasto> findAllCatasti();

	PropCatasto findCatastoByID(Integer idGeoPlPropCatasto) throws RecordNotFoundException;

	List<PropCatasto> getPropCatastosByPropcatastoIntervento(List<PropcatastoIntervento> propcatastoIntervento);
	List<PropCatasto> getPropCatastosByidIntervento(Integer idIntervento);

	List<DichPropCatasto> getDichPropCatastosByPropcatastoIntervento(List<PropcatastoIntervento> propcatastoIntervento);

	List<PlainStringDTO> findSezioniByComune(int fkComune);

	List<PlainIntDTO> findFogliByComune(int fkComune, String sezione);

	List<PlainStringDTO> findParticelleByComuneSezioneFoglio(int fkComune, String sezione, int foglio);
	
	List<PlainStringDTO> findSezioniByComuneAndTipoIstanza(int fkComune, int idTipoIstanza);

	List<PlainIntDTO> findFogliByComuneAndSezioneAndTipoIstnaza(int fkComune, String sezione, int idTipoIstanza);

	List<PlainStringDTO> findParticelleByComuneAndSezioneAndFoglioAndTipoIstanza(int fkComune, String sezione, int foglio, int idTipoIstanza);

	// void updateAllNoteOfOneIntervento(String annotazioni, int idIntervento);
	List<DichPropCatasto> getDichPropCatastosByIdIntervento(Integer idIntervento);

	PropCatasto findPropCatastoByPlainParticelle(Integer idGeoPlPfa, Integer comune, String sezione, Integer foglio,
			String particella);

	List<PropCatasto> getAllPropCatastoByComune(Integer idGeoPlPfa, Integer comune);

	void insertSupInterventoValue(PlainParticelleCatastali plainParticelleCatastali);

	List<PlainParticelleCatastali> getPlainParticelleByIdIntervento(Integer idIntervento);

	List<PlainParticelleCat> findParticelleByAll(int fkComune, String sezione, int foglio, String particella);

	List<PlainParticelleCatastali> findParticelleByComuneFoglioParticella(int fkComune, String sezione, int foglio, String particella);
	
	PlainParticelleCatastali insertParticella(PlainParticelleCatastali particella);
}
