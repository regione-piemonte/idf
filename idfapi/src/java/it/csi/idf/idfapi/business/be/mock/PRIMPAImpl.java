/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.mock;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.IstanzaCompensazioneDAO;
import it.csi.idf.idfapi.business.be.service.PRIMPA;
import it.csi.idf.idfapi.dto.IstanzaTaglio;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class PRIMPAImpl implements PRIMPA {
	
	@Autowired
	private IstanzaCompensazioneDAO istanzaCompensazioneDAO;

	@Override
	public IstanzaTaglio getIstanzeDiTaglio(int numIstanza) {
		//TODO This is mocked data
		IstanzaTaglio istanzaTaglio = new IstanzaTaglio();
		
		
		
		istanzaTaglio.setIdIstanza(getMockedId());
		istanzaTaglio.setAnno(LocalDate.now());
		istanzaTaglio.setNumIstanza(numIstanza);
		istanzaTaglio.setDataPresentazioneIstanza(LocalDate.now());
		istanzaTaglio.setDataAutorizzazioneIstanza(LocalDate.now());
		istanzaTaglio.setDescIntervento("Description intervento");
		istanzaTaglio.setStimaMassaRetraibile(50);
		istanzaTaglio.setUnita("Unita");
		istanzaTaglio.setTipoComunicazione("Tipo comunicazione");
		istanzaTaglio.setStato("BOZZA");
		istanzaTaglio.setGoverno("GOVERNO");
		istanzaTaglio.setTipoIntervento("1");
		istanzaTaglio.setCategoriaIntervento(1);
		
		return istanzaTaglio;
	}
	
	private int getMockedId() {
		String sql1 = "SELECT COALESCE(MAX(id_documento) + 1, 1) FROM idf_temp_index_documento";
		int id = DBUtil.jdbcTemplate.queryForInt(sql1);
		String sql2 = "INSERT INTO idf_temp_index_documento(id_documento) VALUES (?)";
		DBUtil.jdbcTemplate.update(sql2, id);
		return id;
	}
}
