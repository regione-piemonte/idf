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

public class EventoPiano {
	
	private String pfaDenominazione;
	private Integer idEvento;
	private Integer progressivoEventoPfa;
	private String nomeBreve;
	private LocalDate dataEvento;
	private Integer[] idgeoPlParticelaForest;
	private String[] codParticelaForest;
	private Integer tipoEvento;
	private String[] denominazioneParticella;
	private String descrEvento;
	private String localita;
	private BigDecimal supInteressataHa;
	private BigDecimal percentualeDanno;
	private String descrTipoEvento;
	
	
	public String[] getCodParticelaForest() {
		return codParticelaForest;
	}
	public void setCodParticelaForest(String[] codParticelaForest) {
		this.codParticelaForest = codParticelaForest;
	}
	public String getPfaDenominazione() {
		return pfaDenominazione;
	}
	public void setPfaDenominazione(String pfaDenominazione) {
		this.pfaDenominazione = pfaDenominazione;
	}
	
	public Integer getIdEvento() {
		return idEvento;
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
	public Integer getTipoEvento() {
		return tipoEvento;
	}
	public String[] getDenominazioneParticella() {
		return denominazioneParticella;
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
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
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
	public void setTipoEvento(Integer tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public void setDenominazioneParticella(String[] denominazioneParticella) {
		this.denominazioneParticella = denominazioneParticella;
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
	public BigDecimal getPercentualeDanno() {
		return percentualeDanno;
	}
	public void setPercentualeDanno(BigDecimal percentualeDanno) {
		this.percentualeDanno = percentualeDanno;
	}
	public String getDescrTipoEvento() {
		return descrTipoEvento;
	}
	public void setDescrTipoEvento(String descrTipoEvento) {
		this.descrTipoEvento = descrTipoEvento;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventoPiano [pfaDenominazione=");
		builder.append(pfaDenominazione);
		builder.append(", idEvento=");
		builder.append(idEvento);
		builder.append(", progressivoEventoPfa=");
		builder.append(progressivoEventoPfa);
		builder.append(", nomeBreve=");
		builder.append(nomeBreve);
		builder.append(", dataEvento=");
		builder.append(dataEvento);
		builder.append(", idgeoPlParticelaForest=");
		builder.append(Arrays.toString(idgeoPlParticelaForest));
		builder.append(", tipoEvento=");
		builder.append(tipoEvento);
		builder.append(", denominazioneParticella=");
		builder.append(Arrays.toString(denominazioneParticella));
		builder.append(", descrEvento=");
		builder.append(descrEvento);
		builder.append(", localita=");
		builder.append(localita);
		builder.append(", supInteressataHa=");
		builder.append(supInteressataHa);
		builder.append(", percentualeDanno=");
		builder.append(percentualeDanno);
		builder.append(", descrTipoEvento=");
		builder.append(descrTipoEvento);
		builder.append("]");
		return builder.toString();
	}
	
	
}
