/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.business.be.vincolo.pojo.VincoloXls;
import it.csi.idf.idfapi.dto.*;

public interface TrasformazioniXlsDAO {

	List<TrasformazioniXls> getTraformazinoniXlsByIstanze(List<Integer> idsIstanze);
	
	List<TrasformazCatastoXls> getTraformazCatastoXlsByIstanze(List<Integer> idsIstanze);
	
	List<VincoloXls> getVincoloXlsByIstanze(List<Integer> idsIstanze);
	
	List<TrasformazCatastoXls> getVincoloCatastoXlsByIstanze(List<Integer> idsIstanze);

    List<TagliXls> getTagliXlsByIstanze(List<Integer> ids);

	List<TagliSpecieXls> getTagliSpecieXlsByIstanze(List<Integer> ids);

	List<TagliCatastoXls> getTagliCatastoXlsByIstanze(List<Integer> ids);
}
