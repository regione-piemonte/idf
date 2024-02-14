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

public class PlainSecondoPfaEvento {
	
	private Integer idEvento;
	private Integer progressivoEventoPfa;
	private String nomeBreve;
	private LocalDate dataEvento;
	private String[] codParticelle;
	private Integer[] idgeoPlParticelaForest;
	private String[] denominazioneParticella;
	private Integer tipoEvento;
	private String descrEvento;
	private String localita;
	private BigDecimal supInteressataHa;
	private Integer percDanno;
	private Integer annataSilvana;
	
	
	public String[] getCodParticelle() {
		return codParticelle;
	}
	public void setCodParticelle(String[] codParticelle) {
		this.codParticelle = codParticelle;
	}
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	public Integer getProgressivoEventoPfa() {
		return progressivoEventoPfa;
	}
	public void setProgressivoEventoPfa(Integer progressivoEventoPfa) {
		this.progressivoEventoPfa = progressivoEventoPfa;
	}
	public String getNomeBreve() {
		return nomeBreve;
	}
	public void setNomeBreve(String nomeBreve) {
		this.nomeBreve = nomeBreve;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataEvento() {
		return dataEvento;
	}
	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}
	public Integer[] getIdgeoPlParticelaForest() {
		return idgeoPlParticelaForest;
	}
	public void setIdgeoPlParticelaForest(Integer[] idgeoPlParticelaForest) {
		this.idgeoPlParticelaForest = idgeoPlParticelaForest;
	}
	public String[] getDenominazioneParticella() {
		return denominazioneParticella;
	}
	public void setDenominazioneParticella(String[] denominazioneParticella) {
		this.denominazioneParticella = denominazioneParticella;
	}
	public Integer getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(Integer tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public String getDescrEvento() {
		return descrEvento;
	}
	public void setDescrEvento(String descrEvento) {
		this.descrEvento = descrEvento;
	}
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	public BigDecimal getSupInteressataHa() {
		return supInteressataHa;
	}
	public void setSupInteressataHa(BigDecimal supInteressataHa) {
		this.supInteressataHa = supInteressataHa;
	}
	public Integer getPercDanno() {
		return percDanno;
	}
	public void setPercDanno(Integer percDanno) {
		this.percDanno = percDanno;
	}
	public Integer getAnnataSilvana() {
		return annataSilvana;
	}
	public void setAnnataSilvana(Integer annataSilvana) {
		this.annataSilvana = annataSilvana;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainSecondoPfaEvento [idEvento=");
		builder.append(idEvento);
		builder.append(", progressivoEventoPfa=");
		builder.append(progressivoEventoPfa);
		builder.append(", nomeBreve=");
		builder.append(nomeBreve);
		builder.append(", dataEvento=");
		builder.append(dataEvento);
		builder.append(", idgeoPlParticelaForest=");
		builder.append(Arrays.toString(idgeoPlParticelaForest));
		builder.append(", denominazioneParticella=");
		builder.append(Arrays.toString(denominazioneParticella));
		builder.append(", tipoEvento=");
		builder.append(tipoEvento);
		builder.append(", descrEvento=");
		builder.append(descrEvento);
		builder.append(", localita=");
		builder.append(localita);
		builder.append(", supInteressataHa=");
		builder.append(supInteressataHa);
		builder.append(", percDanno=");
		builder.append(percDanno);
		builder.append(", annataSilvana=");
		builder.append(annataSilvana);
		builder.append("]");
		return builder.toString();
	}	
}
