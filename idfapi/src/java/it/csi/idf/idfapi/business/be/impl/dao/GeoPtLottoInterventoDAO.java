/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.GeecoIvnTagliFeature;
import it.csi.idf.idfapi.dto.GeoPtLottoIntervento;

public interface GeoPtLottoInterventoDAO {
	
	int insertGeoPtLottoIntervento(GeecoIvnTagliFeature geoGeecoIvnTagliFeature);
	
	void deleteGeoPtLottoIntervento(int idIntervento);

	//int insertGeoPtLottoIntervento(GeecoIvnTagliFeature geoGeecoIvnTagliFeature);
}
