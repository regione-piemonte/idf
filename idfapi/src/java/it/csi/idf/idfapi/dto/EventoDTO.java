/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;


public class EventoDTO {
	
	private Integer idEvento;
	private Integer idPfa;
	private Integer progressivoEventoPfa;
	private String nomeBreve;
	private LocalDate dataEvento;
	private Integer[] idgeoPlParticelaForest;
	private String[] codParticella;
	private String[] denominazioneParticella;
	private Integer tipoEvento;
	private String descrEvento;
	private String localita;
	private BigDecimal supInteressataHa;
	private Integer percDanno;
	private Integer annataSilvana;
	public Integer getIdEvento() {
		return idEvento;
	}
	public Integer getIdPfa() {
		return idPfa;
	}
	public Integer getProgressivoEventoPfa() {
		return progressivoEventoPfa;
	}
	public String getNomeBreve() {
		return nomeBreve;
	}
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataEvento() {
		return dataEvento;
	}
	public Integer[] getIdgeoPlParticelaForest() {
		return idgeoPlParticelaForest;
	}
	public String[] getCodParticella() {
		return codParticella;
	}
	public String[] getDenominazioneParticella() {
		return denominazioneParticella;
	}
	public Integer getTipoEvento() {
		return tipoEvento;
	}
	public String getDescrEvento() {
		return descrEvento;
	}
	public String getLocalita() {
		return localita;
	}
	public BigDecimal getSupInteressataHa() {
		return supInteressataHa;
	}
	public Integer getPercDanno() {
		return percDanno;
	}
	public Integer getAnnataSilvana() {
		return annataSilvana;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	public void setIdPfa(Integer idPfa) {
		this.idPfa = idPfa;
	}
	public void setProgressivoEventoPfa(Integer progressivoEventoPfa) {
		this.progressivoEventoPfa = progressivoEventoPfa;
	}
	public void setNomeBreve(String nomeBreve) {
		this.nomeBreve = nomeBreve;
	}
	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}
	public void setIdgeoPlParticelaForest(Integer[] idgeoPlParticelaForest) {
		this.idgeoPlParticelaForest = idgeoPlParticelaForest;
	}
	public void setCodParticella(String[] codParticella) {
		this.codParticella = codParticella;
	}
	public void setDenominazioneParticella(String[] denominazioneParticella) {
		this.denominazioneParticella = denominazioneParticella;
	}
	public void setTipoEvento(Integer tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public void setDescrEvento(String descrEvento) {
		this.descrEvento = descrEvento;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	public void setSupInteressataHa(BigDecimal supInteressataHa) {
		this.supInteressataHa = supInteressataHa;
	}
	public void setPercDanno(Integer percDanno) {
		this.percDanno = percDanno;
	}
	public void setAnnataSilvana(Integer annataSilvana) {
		this.annataSilvana = annataSilvana;
	}
	
	
	@Override
	public String toString() {
		return "EventoDTO [idEvento=" + idEvento + ", idPfa=" + idPfa + ", progressivoEventoPfa=" + progressivoEventoPfa
				+ ", nomeBreve=" + nomeBreve + ", dataEvento=" + dataEvento + ", idgeoPlParticelaForest="
				+ Arrays.toString(idgeoPlParticelaForest) + ", codParticella=" + Arrays.toString(codParticella)
				+ ", denominazioneParticella=" + Arrays.toString(denominazioneParticella) + ", tipoEvento=" + tipoEvento
				+ ", descrEvento=" + descrEvento + ", localita=" + localita + ", supInteressataHa=" + supInteressataHa
				+ ", percDanno=" + percDanno + ", annataSilvana=" + annataSilvana + "]";
	}
	
	
}
