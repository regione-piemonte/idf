/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

public interface SkOkSelvDAO {

	void insertFlgSez1(int idIntervento);

	boolean isFlgSez1Done(int idIntervento);

	boolean isFlgSez2Done(int idIntervento);

	boolean isFlgSez3Done(int idIntervento);

	boolean isFlgSez4Done(int idIntervento);

	boolean isFlgSez5Done(int idIntervento);

	void updateFlgSez1(int idIntervento);

	void updateFlgSez2(int idIntervento);

	void updateFlgSez3(int idIntervento);

	void updateFlgSez4(int idIntervento);

	void updateFlgSez5(int idIntervento);
	
	int sumFlgSeziones(int idIntervento);
	
	void deleteByIdIntervento(int idIntervento);


    void initVariante(Integer idInterventoNew);
}
