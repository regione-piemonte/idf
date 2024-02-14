/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.IstanzaCompensazione;

public interface IstanzaCompensazioneDAO {
	void insertIstanzaCompensazione(IstanzaCompensazione istanzaCompensazione);
	List<IstanzaCompensazione> getAllByFkIntervento(int fkIntervento);
	IstanzaCompensazione getByNumIstanza(int numIstanza);
	void deleteIstanza(int numIstanzaCompensazione);
	void deleteIstanzaByIntervento(int idIntervento);
}
