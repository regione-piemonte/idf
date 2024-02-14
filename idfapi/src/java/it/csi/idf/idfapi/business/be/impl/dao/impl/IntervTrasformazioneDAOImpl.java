/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.RecordNotUniqueException;
import it.csi.idf.idfapi.business.be.impl.dao.IntervTrasformazioneDAO;
import it.csi.idf.idfapi.dto.CompensationForesteDTO;
import it.csi.idf.idfapi.dto.IntervTrasformazione;
import it.csi.idf.idfapi.mapper.IntervTrasformazioneMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.SuperficieCompensationEnum;

@Component
public class IntervTrasformazioneDAOImpl implements IntervTrasformazioneDAO {
	
	public static final Logger logger = Logger.getLogger(IntervTrasformazioneDAOImpl.class);

	@Override
	public int createIntervTrasformazioneWithConfigUtente(int idIntervento, int fkConfigUtente) {
		StringBuilder sql = new StringBuilder("INSERT INTO idf_t_interv_trasformazione(");
		sql.append("id_intervento, data_inserimento, fk_config_utente");
		sql.append(") VALUES(?, ?, ?)");
	
		return DBUtil.jdbcTemplate.update(sql.toString(), idIntervento, Date.valueOf(LocalDate.now()), fkConfigUtente);
	}

	@Override
	public void setFlgVincIdro(byte flagVincIdro, Integer idIntervento) {
		String sql = "UPDATE idf_t_interv_trasformazione SET flg_vinc_idro = ? WHERE id_intervento = ?";
		
		DBUtil.jdbcTemplate.update(sql, flagVincIdro, idIntervento);
	}

	@Override
	public void setCompenzazioneEuro(BigDecimal compenzazione, Integer idIntervento) {
		String sql = "UPDATE idf_t_interv_trasformazione SET compensazione_euro_teorica = ? WHERE id_intervento = ?";
		logger.debug("setCompenzazioneEuro - sql: " + sql);
		logger.debug("params-> compenzazione: " + compenzazione + " - idIntervento: " + idIntervento);
		DBUtil.jdbcTemplate.update(sql, compenzazione, idIntervento);
	}
	
	@Override
	public void setCompenzazioneRealeEuro(BigDecimal compenzazioneReale, String note, Integer idIntervento) {
		String sql = "UPDATE idf_t_interv_trasformazione SET compensazione_euro_reale = ?, "
				+ " compensazione_note = ? WHERE id_intervento = ?";
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(compenzazioneReale);
		parameters.add(note);
		parameters.add(idIntervento);
		DBUtil.jdbcTemplate.update(sql, parameters.toArray());
	}

	@Override
	public void updateCompensazioneNecessaria(SuperficieCompensationEnum superficieCompensationEnum, Integer idIntervento) {
		String sql = "UPDATE idf_t_interv_trasformazione SET flg_compensazione = ? WHERE id_intervento = ?";

		DBUtil.jdbcTemplate.update(sql, superficieCompensationEnum.toString(), idIntervento);
	}

	@Override
	public void updateCompensazioneNonNecessaria(CompensationForesteDTO compensationForeste, Integer idIntervento) {
		StringBuilder sql = new StringBuilder("UPDATE idf_t_interv_trasformazione SET");
		sql.append(" flg_compensazione = ?, flg_art7_a = ?, flg_art7_b = ?, flg_art7_c = ?, flg_art7_d = ?");
		sql.append(", flg_art7_a_21 = ?, flg_art7_b_21 = ?, flg_art7_c_21 = ?, flg_art7_d_21 = ?");
		sql.append(", flg_art7_dter_21 = ?, flg_art7_dquater_21 = ?, flg_art7_dquinquies_21 = ? ");
		sql.append(" WHERE id_intervento = ?");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(SuperficieCompensationEnum.N.toString());
		parameters.add(compensationForeste.isFlgA() ? 1 : 0);
		parameters.add(compensationForeste.isFlgB() ? 1 : 0);
		parameters.add(compensationForeste.isFlgC() ? 1 : 0);
		parameters.add(compensationForeste.isFlgD() ? 1 : 0);
		parameters.add(compensationForeste.isFlgA21() ? 1 : 0);
		parameters.add(compensationForeste.isFlgB21() ? 1 : 0);
		parameters.add(compensationForeste.isFlgC21() ? 1 : 0);
		parameters.add(compensationForeste.isFlgD21() ? 1 : 0);
		parameters.add(compensationForeste.isFlgDter21() ? 1 : 0);
		parameters.add(compensationForeste.isFlgDquater21() ? 1 : 0);
		parameters.add(compensationForeste.isFlgDquinquies21() ? 1 : 0);
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public IntervTrasformazione getIntervTrasfById(int idIntervento) throws RecordNotUniqueException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_intervento, flg_compensazione, flg_art7_a, flg_art7_b, flg_art7_c, flg_art7_d");
		sql.append(", flg_art7_a_21, flg_art7_b_21, flg_art7_c_21, flg_art7_d_21");
		sql.append(", flg_art7_dter_21, flg_art7_dquater_21, flg_art7_dquinquies_21");
		sql.append(", flg_proprieta, flg_dissensi, flg_aut_paesaggistica, data_aut_paesaggistica, nr_aut_paesaggistica");
		sql.append(", ente_aut_paesaggistica, flg_vinc_idro, flg_aut_vidro, data_aut_vidro, nr_aut_vidro, flg_aut_incidenza");
		sql.append(", data_aut_incidenza, nr_aut_incidenza, ente_aut_incidenza, ente_aut_vidro, flg_cauzione_cf, flg_versamento_cm");
		sql.append(", altri_pareri, note_dichiarazione, compensazione_euro_teorica, compensazione_euro_reale, compensazione_note");
		sql.append(", data_inserimento, data_aggiornamento, fk_config_utente");
		sql.append(" FROM idf_t_interv_trasformazione WHERE id_intervento = ?");

		logger.debug("getIntervTrasfById sql: " + sql.toString() + " - param: " + idIntervento);
		
		List<IntervTrasformazione> intervTrasfs = DBUtil.jdbcTemplate.query(sql.toString(), new IntervTrasformazioneMapper(), idIntervento);
		
		if(intervTrasfs.isEmpty()) {
			return null;
		} else if (intervTrasfs.size() == 1) {
			return intervTrasfs.get(0);
		} else {
			throw new RecordNotUniqueException();			
		}
	}

	@Override
	public void updateWithDichiarazioni(IntervTrasformazione intervTrasformazione, int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_t_interv_trasformazione SET");
		sql.append(" flg_proprieta = ?, flg_dissensi = ?, flg_aut_paesaggistica = ?, data_aut_paesaggistica = ?");
		sql.append(", nr_aut_paesaggistica = ?, ente_aut_paesaggistica = ?, flg_aut_vidro = ?, data_aut_vidro = ?");
		sql.append(", nr_aut_vidro = ?, ente_aut_vidro = ?, flg_aut_incidenza = ?, data_aut_incidenza = ?, nr_aut_incidenza = ?");
		sql.append(", ente_aut_incidenza = ?, altri_pareri = ?, flg_cauzione_cf = ?, flg_versamento_cm = ?, note_dichiarazione = ?");
		sql.append(" WHERE id_intervento = ?");
		
		List<Object> parameters = new ArrayList<>();
		parameters.add(intervTrasformazione.getFlgProprieta());
		parameters.add(intervTrasformazione.getFlgDissensi());
		parameters.add(intervTrasformazione.getFlgAutPaesaggistica());
		parameters.add(intervTrasformazione.getDataAutPaesaggistica() == null ? null
				: Date.valueOf(intervTrasformazione.getDataAutPaesaggistica()));
		parameters.add(intervTrasformazione.getNrAutPaesaggistica());
		parameters.add(intervTrasformazione.getEnteAutPaesaggistica());
		parameters.add(intervTrasformazione.getFlgAutVidro());
		parameters.add(intervTrasformazione.getDataAutVidro() == null ? null
				: Date.valueOf(intervTrasformazione.getDataAutVidro()));
		parameters.add(intervTrasformazione.getNrAutVidro());
		parameters.add(intervTrasformazione.getEnteAutVidro());
		parameters.add(intervTrasformazione.getFlgAutIncidenza());
		parameters.add(intervTrasformazione.getDataAutIncidenza() == null ? null
				: Date.valueOf(intervTrasformazione.getDataAutIncidenza()));
		parameters.add(intervTrasformazione.getNrAutIncidenza());
		parameters.add(intervTrasformazione.getEnteAutIncidenza());
		parameters.add(intervTrasformazione.getAltriPareri());
		parameters.add(intervTrasformazione.getFlgCauzioneCf());
		parameters.add(intervTrasformazione.getFlgVersamentoCm());
		parameters.add(intervTrasformazione.getNoteDichiarazione());
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void deleteByIdIntervento(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM idf_t_interv_trasformazione");
		sql.append(" WHERE id_intervento = ?");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
		
	}

}
