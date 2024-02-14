/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class AreaDISaggioDataStazionaliTwo {

	private String codiceADS;
	private Integer destinazione;
	private Integer tipoIntervento;
	private Integer priorita;
	private Integer danno;	
		//table superficia nota	
	private String codEsbosco;
	private Integer distEsboscoFuoriPista;
	private Integer distEsboscoSuPista;
	private Integer minDistanzaPlanimetrica; 
	private Integer danniPerc;
	private Integer nrPianteMorte; 
	private Integer percDefogliazione;
	private Integer percIngiallimento; 
	private Byte flgPascolamento;
	private String note;
		
	public String getCodiceADS() {
		return codiceADS;
	}
	public void setCodiceADS(String codiceADS) {
		this.codiceADS = codiceADS;
	}
	
	public Integer getDestinazione() {
		return destinazione;
	}
	public void setDestinazione(Integer destinazione) {
		this.destinazione = destinazione;
	}
	
	public Integer getTipoIntervento() {
		return tipoIntervento;
	}
	public void setTipoIntervento(Integer tipoIntervento) {
		this.tipoIntervento = tipoIntervento;
	}
	
	public Integer getPriorita() {
		return priorita;
	}
	public void setPriorita(Integer priorita) {
		this.priorita = priorita;
	}
	
	public Integer getDanno() {
		return danno;
	}
	public void setDanno(Integer danno) {
		this.danno = danno;
	}
	
	public String getCodEsbosco() {
		return codEsbosco;
	}
	public void setCodEsbosco(String codEsbosco) {
		this.codEsbosco = codEsbosco;
	}
	
	public Integer getDistEsboscoFuoriPista() {
		return distEsboscoFuoriPista;
	}
	public void setDistEsboscoFuoriPista(Integer distEsboscoFuoriPista) {
		this.distEsboscoFuoriPista = distEsboscoFuoriPista;
	}
	
	public Integer getDistEsboscoSuPista() {
		return distEsboscoSuPista;
	}
	public void setDistEsboscoSuPista(Integer distSsboscoSuPista) {
		this.distEsboscoSuPista = distSsboscoSuPista;
	}
	
	public Integer getMinDistanzaPlanimetrica() {
		return minDistanzaPlanimetrica;
	}
	public void setMinDistanzaPlanimetrica(Integer minDistanzaPlanimetrica) {
		this.minDistanzaPlanimetrica = minDistanzaPlanimetrica;
	}
	
	public Integer getDanniPerc() {
		return danniPerc;
	}
	public void setDanniPerc(Integer danniPerc) {
		this.danniPerc = danniPerc;
	}
	
	public Integer getNrPianteMorte() {
		return nrPianteMorte;
	}
	public void setNrPianteMorte(Integer nrPianteMorte) {
		this.nrPianteMorte = nrPianteMorte;
	}
	
	public Integer getPercDefogliazione() {
		return percDefogliazione;
	}
	public void setPercDefogliazione(Integer percDefogliazione) {
		this.percDefogliazione = percDefogliazione;
	}
	
	public Integer getPercIngiallimento() {
		return percIngiallimento;
	}
	public void setPercIngiallimento(Integer percIngiallimento) {
		this.percIngiallimento = percIngiallimento;
	}
	
	public Byte getFlgPascolamento() {
		return flgPascolamento;
	}
	public void setFlgPascolamento(Byte flgPascolamento) {
		this.flgPascolamento = flgPascolamento;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AreaDISaggioDataStazionaliTwo [codiceADS=");
		builder.append(codiceADS);
		builder.append(", destinazione=");
		builder.append(destinazione);
		builder.append(", tipoIntervento=");
		builder.append(tipoIntervento);
		builder.append(", priorita=");
		builder.append(priorita);
		builder.append(", danno=");
		builder.append(danno);
		builder.append(", codEsbosco=");
		builder.append(codEsbosco);
		builder.append(", distEsboscoFuoriPista=");
		builder.append(distEsboscoFuoriPista);
		builder.append(", distSsboscoSuPista=");
		builder.append(distEsboscoSuPista);
		builder.append(", minDistanzaPlanimetrica=");
		builder.append(minDistanzaPlanimetrica);
		builder.append(", danniPerc=");
		builder.append(danniPerc);
		builder.append(", nrPianteMorte=");
		builder.append(nrPianteMorte);
		builder.append(", percDefogliazione=");
		builder.append(percDefogliazione);
		builder.append(", percIngiallimento=");
		builder.append(percIngiallimento);
		builder.append(", flgPascolamento=");
		builder.append(flgPascolamento);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}
	
	
	

	
	
	
	
	
	
	
}
