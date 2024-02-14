/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.time.LocalDate;
import java.util.Objects;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;

public class IstanzaForest {
	
	private Integer idIstanzaIntervento;
	private Integer fkSoggSettoreRegionale;
	private Integer fkStatoIstanza;
	private Integer nrIstanzaForestale;
	private String nrDeterminaAut;
	private LocalDate dataInvio;
	private LocalDate dataVerifica;
	private LocalDate dataProtocollo;
	private String nrProtocollo;
	private LocalDate dataUltAgg;
	private LocalDate dataInserimento;
	private LocalDate dataFineIntervento;
	private LocalDate dataTermineAutorizzazione;
	private Integer fkConfigUtente;
	private Integer fkTipoIstanza;
	private String utenteCompilatore;
	private String utenteApprovatore;
	private String motivazioneRifiuto;
	private LocalDate dataAggiornamento;
	
	// 777 Abisoft
	private Integer nrIntervento;
	private Integer fkStatoIntervento;
	private String codStatoIntervento;
	private String descrStatoIntervento;
	
	private String motivazione;
	
	public String getMotivazione() {
		return motivazione;
	}
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
	
	public Integer getNrIntervento() {
		return nrIntervento;
	}
	public void setNrIntervento(Integer nrIntervento) {
		this.nrIntervento = nrIntervento;
	}
	public Integer getFkStatoIntervento() {
		return fkStatoIntervento;
	}
	public void setFkStatoIntervento(Integer fkStatoIntervento) {
		this.fkStatoIntervento = fkStatoIntervento;
	}
	public String getCodStatoIntervento() {
		return codStatoIntervento;
	}
	public void setCodStatoIntervento(String codStatoIntervento) {
		this.codStatoIntervento = codStatoIntervento;
	}
	public String getDescrStatoIntervento() {
		return descrStatoIntervento;
	}
	public void setDescrStatoIntervento(String descrStatoIntervento) {
		this.descrStatoIntervento = descrStatoIntervento;
	}
	// 777
	
	public String getNrDeterminaAut() {
		return nrDeterminaAut;
	}
	public void setNrDeterminaAut(String nrDeterminaAut) {
		this.nrDeterminaAut = nrDeterminaAut;
	}
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataTermineAutorizzazione() {
		return dataTermineAutorizzazione;
	}
	public void setDataTermineAutorizzazione(LocalDate dataTermineAutorizzazione) {
		this.dataTermineAutorizzazione = dataTermineAutorizzazione;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataFineIntervento() {
		return dataFineIntervento;
	}
	public void setDataFineIntervento(LocalDate dataFineIntervento) {
		this.dataFineIntervento = dataFineIntervento;
	}
	public Integer getIdIstanzaIntervento() {
		return idIstanzaIntervento;
	}
	public void setIdIstanzaIntervento(Integer idIstanzaIntervento) {
		this.idIstanzaIntervento = idIstanzaIntervento;
	}
	public Integer getFkSoggSettoreRegionale() {
		return fkSoggSettoreRegionale;
	}
	public void setFkSoggSettoreRegionale(Integer fkSoggSettoreRegionale) {
		this.fkSoggSettoreRegionale = fkSoggSettoreRegionale;
	}
	public Integer getFkStatoIstanza() {
		return fkStatoIstanza;
	}
	public void setFkStatoIstanza(Integer fkStatoIstanza) {
		this.fkStatoIstanza = fkStatoIstanza;
	}
	public Integer getNrIstanzaForestale() {
		return nrIstanzaForestale;
	}
	public void setNrIstanzaForestale(Integer nrIstanzaForestale) {
		this.nrIstanzaForestale = nrIstanzaForestale;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataInvio() {
		return dataInvio;
	}
	public void setDataInvio(LocalDate dataInvio) {
		this.dataInvio = dataInvio;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataVerifica() {
		return dataVerifica;
	}
	public void setDataVerifica(LocalDate dataVerifica) {
		this.dataVerifica = dataVerifica;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataProtocollo() {
		return dataProtocollo;
	}
	public void setDataProtocollo(LocalDate dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}
	public String getNrProtocollo() {
		return nrProtocollo;
	}
	public void setNrProtocollo(String nrProtocollo) {
		this.nrProtocollo = nrProtocollo;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataUltAgg() {
		return dataUltAgg;
	}
	public void setDataUltAgg(LocalDate dataUltAgg) {
		this.dataUltAgg = dataUltAgg;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	public LocalDate getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(LocalDate dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public Integer getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setFkConfigUtente(Integer fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public Integer getFkTipoIstanza() {
		return fkTipoIstanza;
	}
	public void setFkTipoIstanza(Integer fkTipoIstanza) {
		this.fkTipoIstanza = fkTipoIstanza;
	}
	public String getUtenteCompilatore() {
		return utenteCompilatore;
	}
	public void setUtenteCompilatore(String utenteCompilatore) {
		this.utenteCompilatore = utenteCompilatore;
	}
	public String getUtenteApprovatore() {
		return utenteApprovatore;
	}
	public void setUtenteApprovatore(String utenteApprovatore) {
		this.utenteApprovatore = utenteApprovatore;
	}
	public String getMotivazioneRifiuto() {
		return motivazioneRifiuto;
	}
	public void setMotivazioneRifiuto(String motivazioneRifiuto) {
		this.motivazioneRifiuto = motivazioneRifiuto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAggiornamento == null) ? 0 : dataAggiornamento.hashCode());
		result = prime * result + ((dataInserimento == null) ? 0 : dataInserimento.hashCode());
		result = prime * result + ((dataInvio == null) ? 0 : dataInvio.hashCode());
		result = prime * result + ((dataProtocollo == null) ? 0 : dataProtocollo.hashCode());
		result = prime * result + ((dataUltAgg == null) ? 0 : dataUltAgg.hashCode());
		result = prime * result + ((dataVerifica == null) ? 0 : dataVerifica.hashCode());
		result = prime * result + ((fkConfigUtente == null) ? 0 : fkConfigUtente.hashCode());
		result = prime * result + ((fkSoggSettoreRegionale == null) ? 0 : fkSoggSettoreRegionale.hashCode());
		result = prime * result + ((fkStatoIstanza == null) ? 0 : fkStatoIstanza.hashCode());
		result = prime * result + ((fkTipoIstanza == null) ? 0 : fkTipoIstanza.hashCode());
		result = prime * result + ((idIstanzaIntervento == null) ? 0 : idIstanzaIntervento.hashCode());
		result = prime * result + ((nrIstanzaForestale == null) ? 0 : nrIstanzaForestale.hashCode());
		result = prime * result + ((nrDeterminaAut == null) ? 0 : nrDeterminaAut.hashCode());		
		result = prime * result + ((nrProtocollo == null) ? 0 : nrProtocollo.hashCode());
		result = prime * result + ((dataFineIntervento == null) ? 0 : dataFineIntervento.hashCode());
		result = prime * result + ((dataTermineAutorizzazione == null) ? 0 : dataTermineAutorizzazione.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
		      return true;
		    }
		    if (obj == null || getClass() != obj.getClass()) {
		      return false;
		    }
		    IstanzaForest istanzaForest = (IstanzaForest) obj;
		    
		return Objects.equals(idIstanzaIntervento, istanzaForest.idIstanzaIntervento)
				&& Objects.equals(fkSoggSettoreRegionale, istanzaForest.fkSoggSettoreRegionale)
				&& Objects.equals(fkStatoIstanza, istanzaForest.fkStatoIstanza)
				&& Objects.equals(nrIstanzaForestale, istanzaForest.nrIstanzaForestale)
				&& Objects.equals(dataInvio, istanzaForest.dataInvio)
				&& Objects.equals(dataVerifica, istanzaForest.dataVerifica)
				&& Objects.equals(dataProtocollo, istanzaForest.dataProtocollo)
				&& Objects.equals(nrProtocollo, istanzaForest.nrProtocollo)
				&& Objects.equals(nrDeterminaAut, istanzaForest.nrDeterminaAut)				
				&& Objects.equals(dataUltAgg, istanzaForest.dataUltAgg)
				&& Objects.equals(dataInserimento, istanzaForest.dataInserimento)
				&& Objects.equals(dataAggiornamento, istanzaForest.dataAggiornamento)
				&& Objects.equals(fkConfigUtente, istanzaForest.fkConfigUtente)
				&& Objects.equals(fkTipoIstanza, istanzaForest.fkTipoIstanza)
				&& Objects.equals(dataFineIntervento, istanzaForest.dataFineIntervento)
				&& Objects.equals(dataTermineAutorizzazione, istanzaForest.dataTermineAutorizzazione);
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class IstanzaForest {\n");

		sb.append("    idIstanzaIntervento: ").append(idIstanzaIntervento).append("\n");
		sb.append("    fkSoggSettoreRegionale: ").append(fkSoggSettoreRegionale).append("\n");
		sb.append("    fkStatoIstanza: ").append(fkStatoIstanza).append("\n");
		sb.append("    nrIstanzaForestale: ").append(nrIstanzaForestale).append("\n");
		sb.append("    dataInvio: ").append(dataInvio).append("\n");
		sb.append("    dataVerifica: ").append(dataVerifica).append("\n");
		sb.append("    dataProtocollo: ").append(dataProtocollo).append("\n");
		sb.append("    nrProtocollo: ").append(nrProtocollo).append("\n");
		sb.append("    nrDeterminaAut: ").append(nrDeterminaAut).append("\n");		
		sb.append("    dataUltAgg: ").append(dataUltAgg).append("\n");
		sb.append("    dataInserimento: ").append(dataInserimento).append("\n");
		sb.append("    dataAggiornamento: ").append(dataAggiornamento).append("\n");
		sb.append("    fkConfigUtente: ").append(fkConfigUtente).append("\n");
		sb.append("    fkTipoIstanza: ").append(fkTipoIstanza).append("\n");
		sb.append("    utenteCompilatore: ").append(utenteCompilatore).append("\n");
		sb.append("    utenteApprovatore: ").append(utenteApprovatore).append("\n");
		sb.append("    motivazioneRifiuto: ").append(motivazioneRifiuto).append("\n");
		sb.append("    dataFineIntervento: ").append(dataFineIntervento).append("\n");
		sb.append("    dataTermineAutorizzazione: ").append(dataTermineAutorizzazione).append("\n");		
		
		sb.append("}");
		return sb.toString();
	}
}


