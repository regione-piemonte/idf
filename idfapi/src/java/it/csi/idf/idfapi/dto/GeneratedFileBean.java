/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;

import it.csi.idf.idfapi.util.TipoAllegatoEnum;

public class GeneratedFileBean {
	
	private long idDocumento;
	private int fkIntervento;
	private TipoAllegatoEnum fkTipoAllegato;
	private int dimensioneKb;
	private String nome;
	private int fkConfigUtente;
	private LocalDate dataInserimento;
	private String note;
	private String idIstanza;
	private String mimeType;
	private byte[] content;
	private String checksum;
	private File file;
	
	public long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public int getFkIntervento() {
		return fkIntervento;
	}
	public void setFkIntervento(int fkIntervento) {
		this.fkIntervento = fkIntervento;
	}
	public TipoAllegatoEnum getFkTipoAllegato() {
		return fkTipoAllegato;
	}
	public void setFkTipoAllegato(TipoAllegatoEnum fkTipoAllegato) {
		this.fkTipoAllegato = fkTipoAllegato;
	}
	public int getDimensioneKb() {
		return dimensioneKb;
	}
	public void setDimensioneKb(int dimensioneKb) {
		this.dimensioneKb = dimensioneKb;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getFkConfigUtente() {
		return fkConfigUtente;
	}
	public void setFkConfigUtente(int fkConfigUtente) {
		this.fkConfigUtente = fkConfigUtente;
	}
	public LocalDate getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getIdIstanza() {
		return idIstanza;
	}
	public void setIdIstanza(String idIstanza) {
		this.idIstanza = idIstanza;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeneratedFileBean [idDocumento=");
		builder.append(idDocumento);
		builder.append(", fkIntervento=");
		builder.append(fkIntervento);
		builder.append(", fkTipoAllegato=");
		builder.append(fkTipoAllegato);
		builder.append(", dimensioneKb=");
		builder.append(dimensioneKb);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", fkConfigUtente=");
		builder.append(fkConfigUtente);
		builder.append(", dataInserimento=");
		builder.append(dataInserimento);
		builder.append(", note=");
		builder.append(note);
		builder.append(", idIstanza=");
		builder.append(idIstanza);
		builder.append(", mimeType=");
		builder.append(mimeType);
		builder.append(", content=");
		builder.append(Arrays.toString(content));
		builder.append(", checksum=");
		builder.append(checksum);
		builder.append(", file=");
		builder.append(file);
		builder.append("]");
		return builder.toString();
	}
}
