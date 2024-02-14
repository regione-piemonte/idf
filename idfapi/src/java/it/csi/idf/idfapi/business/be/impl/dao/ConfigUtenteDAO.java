/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.util.ProceduraEnum;

public interface ConfigUtenteDAO {
	
	ConfigUtente getCofigUtenteById(int configUtenteId);
	
	int createConfigUtente(ConfigUtente configUtente);
	
	void updateConfigUtente(ConfigUtente configUtente);

	ConfigUtente getCofigUtenteBySoggettoId(int id);

	public ConfigUtente getCofigUtenteBySoggettoIdAndApplicationType(int id,ProceduraEnum proceduraType);

	List<ConfigUtente> getCofigUtenteBySoggettoIdAnProfilo(int id, int profilo);

	ConfigUtente getCofigUtenteBySoggettoIdProfiloAndTipoAccreditamento(Integer id, Integer prof, Integer accred);

	List<ConfigUtente> getCofUtenteBySoggIdAnProfOrderByDate(int id, int profilo);

	public ConfigUtente getConfigUtenteIstafor(int fkUtente);
	
	boolean isUserEnabledEditIstanza(ConfigUtente configUtente, int idIntervento);

}
