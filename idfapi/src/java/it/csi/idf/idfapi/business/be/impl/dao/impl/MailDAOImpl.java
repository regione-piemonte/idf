/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.MailDAO;
import it.csi.idf.idfapi.dto.DatiMailInvoIstanza;
import it.csi.idf.idfapi.mapper.DatiMailInvoIstanzaMapper;
import it.csi.idf.idfapi.mapper.EmailDtoMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.mail.EmailDTO;

@Component
public class MailDAOImpl implements MailDAO {

	@Override
	public EmailDTO getConfEmail(int idMail, Integer idconf) {
		StringBuilder sql = new StringBuilder();
		sql.append("select host, porta, oggetto, testo, mittente ");
		sql.append("from idf_cnf_mail a,idf_cnf_tipo_mail b  ");
		sql.append("where b.id_tipo_mail = ? and a.id_mail = ?");
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new EmailDtoMapper(),idMail,idconf);	
	}

	@Override
	public EmailDTO getConfEmail(int idMail) {
		return getConfEmail(idMail,1);
	}	

	@Override
	public DatiMailInvoIstanza getDatiInvioIstanzaTrasformazioni(Integer idIstanza) {
		StringBuilder sql = new StringBuilder();
		sql.append("select itif.id_istanza_intervento, ");
		sql.append("itif.nr_istanza_forestale, ");
		sql.append("its2.e_mail mail_richiedente, ");
		sql.append("its.id_soggetto as id_soggetto_gestore, ");
		sql.append("its.denominazione as denominazione_gestore, ");
		sql.append("its.nr_telefonico as telefono_gestore, ");
		sql.append("its.e_mail as mail_gestore, ");
		sql.append("its.pec as pec_gestore ");
		sql.append("from ");
		sql.append("idf_t_istanza_forest itif ");
		sql.append("join idf_t_soggetto its on itif.fk_sogg_settore_regionale = its.id_soggetto ");
		sql.append("join idf_r_soggetto_intervento irsi on itif.id_istanza_intervento = irsi.id_intervento ");
		sql.append("join idf_t_soggetto its2 on irsi.id_soggetto = its2.id_soggetto ");
		sql.append("where irsi.id_tipo_soggetto = 1 and id_istanza_intervento = ? ");
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), new DatiMailInvoIstanzaMapper(),idIstanza);
	}

}
