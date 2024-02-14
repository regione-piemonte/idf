/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class AdsDatiStazionaliTwo {
		
	Long idgeoPtAds;
	String codiceADS;
	Integer dannoPrevalente;
	Integer	defogliazione;
	Integer	defp;
	Integer	desp;
	Integer	destinazione;
	String esbosco;
		
	Integer	ingiallimento;
	Integer	intervento;
	Integer	intesitaDanni;
	Integer	mdp;
	Integer	nPianteMorte;
	Integer	pascolamento;
	Integer	priorita;
	String note;
	
	
	public Long getIdgeoPtAds() {
		return idgeoPtAds;
	}
	public void setIdgeoPtAds(Long idgeoPtAds) {
		this.idgeoPtAds = idgeoPtAds;
	}
	public String getCodiceADS() {
		return codiceADS;
	}
	public void setCodiceADS(String codiceADS) {
		this.codiceADS = codiceADS;
	}
	public Integer getDannoPrevalente() {
		return dannoPrevalente;
	}
	public void setDannoPrevalente(Integer dannoPrevalente) {
		this.dannoPrevalente = dannoPrevalente;
	}
	public Integer getDefogliazione() {
		return defogliazione;
	}
	public void setDefogliazione(Integer defogliazione) {
		this.defogliazione = defogliazione;
	}
	public Integer getDefp() {
		return defp;
	}
	public void setDefp(Integer defp) {
		this.defp = defp;
	}
	public Integer getDesp() {
		return desp;
	}
	public void setDesp(Integer desp) {
		this.desp = desp;
	}
	public Integer getDestinazione() {
		return destinazione;
	}
	public void setDestinazione(Integer destinazione) {
		this.destinazione = destinazione;
	}
	public String getEsbosco() {
		return esbosco;
	}
	public void setEsbosco(String esbosco) {
		this.esbosco = esbosco;
	}
	public Integer getIngiallimento() {
		return ingiallimento;
	}
	public void setIngiallimento(Integer ingiallimento) {
		this.ingiallimento = ingiallimento;
	}
	public Integer getIntervento() {
		return intervento;
	}
	public void setIntervento(Integer intervento) {
		this.intervento = intervento;
	}
	public Integer getIntesitaDanni() {
		return intesitaDanni;
	}
	public void setIntesitaDanni(Integer intesitaDanni) {
		this.intesitaDanni = intesitaDanni;
	}
	public Integer getMdp() {
		return mdp;
	}
	public void setMdp(Integer mdp) {
		this.mdp = mdp;
	}
	public Integer getnPianteMorte() {
		return nPianteMorte;
	}
	public void setnPianteMorte(Integer nPianteMorte) {
		this.nPianteMorte = nPianteMorte;
	}
	public Integer getPascolamento() {
		return pascolamento;
	}
	public void setPascolamento(Integer pascolamento) {
		this.pascolamento = pascolamento;
	}
	public Integer getPriorita() {
		return priorita;
	}
	public void setPriorita(Integer priorita) {
		this.priorita = priorita;
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
		builder.append("AdsDatiStazionaliTwo [idgeoPtAds=");
		builder.append(idgeoPtAds);
		builder.append(", codiceADS=");
		builder.append(codiceADS);
		builder.append(", dannoPrevalente=");
		builder.append(dannoPrevalente);
		builder.append(", defogliazione=");
		builder.append(defogliazione);
		builder.append(", defp=");
		builder.append(defp);
		builder.append(", desp=");
		builder.append(desp);
		builder.append(", destinazione=");
		builder.append(destinazione);
		builder.append(", esbosco=");
		builder.append(esbosco);
		builder.append(", ingiallimento=");
		builder.append(ingiallimento);
		builder.append(", intervento=");
		builder.append(intervento);
		builder.append(", intesitaDanni=");
		builder.append(intesitaDanni);
		builder.append(", mdp=");
		builder.append(mdp);
		builder.append(", nPianteMorte=");
		builder.append(nPianteMorte);
		builder.append(", pascolamento=");
		builder.append(pascolamento);
		builder.append(", priorita=");
		builder.append(priorita);
		builder.append(", note=");
		builder.append(note);
		builder.append("]");
		return builder.toString();
	}
	
}
