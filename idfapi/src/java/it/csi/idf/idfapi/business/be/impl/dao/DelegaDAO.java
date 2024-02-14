/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.dto.Delega;

public interface DelegaDAO {
	Delega getByCfDeleganteAndConfigUtente(String cfDelegante, Integer fkConfigUtente) throws RecordNotUniqueException;
	List<Delega> getAllDelegaByIdConfigUtente(Integer idConfigUtente);
	Delega getByUidIndex(String uidIndex) throws RecordNotUniqueException;
	int createDelega(Delega delega);
	List<Delega> getMieiDelegati(Integer fkConfigUtente);
	Delega getIfActiveByCfDeleganteAndConfigUtente(String cfDelegante, Integer fkConfigUtente)
			throws RecordNotUniqueException;
	int disabilita(String cfDelegante, Integer fkConfigUtente);
	int riabilita(Delega delega);
}
