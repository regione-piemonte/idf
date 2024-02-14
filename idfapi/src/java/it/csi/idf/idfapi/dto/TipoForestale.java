/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.sql.Timestamp;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class TipoForestale {
	
	private Integer idTipoForestale;
	private Integer fkCategoriaForestale;
	private String codiceAmministrativo;
	private String tipo;
	private String sottotipo;
	private String variante;
	private Timestamp dataModifica;
	private Timestamp dataFineValidita;
	private String codClc;
	private String habitatN2000;
	private Integer fkConfigIpla;

	@JsonProperty("codiceAmministrativo")
	public String getCodiceAmministrativo() {
		return codiceAmministrativo;
	}

	public Integer getIdTipoForestale() {
		return idTipoForestale;
	}

	@JsonProperty("idTipoForestale")
	public void setIdTipoForestale(Integer idTipoForestale) {
		this.idTipoForestale = idTipoForestale;
	}

	public void setCodiceAmministrativo(String codiceAmministrativo) {
		this.codiceAmministrativo = codiceAmministrativo;
	}
	
	@JsonProperty("tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@JsonProperty("sottotipo")
	public String getSottotipo() {
		return sottotipo;
	}

	public void setSottotipo(String sottotipo) {
		this.sottotipo = sottotipo;
	}

	@JsonProperty("variante")
	public String getVariante() {
		return variante;
	}

	public void setVariante(String variante) {
		this.variante = variante;
	}

	@JsonProperty("dataModifica")
	public Timestamp getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(Timestamp dataModifica) {
		this.dataModifica = dataModifica;
	}

	@JsonProperty("dataFineValidita")
	public Timestamp getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Timestamp dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	@JsonProperty("codClc")
	public String getCodClc() {
		return codClc;
	}

	public void setCodClc(String codClc) {
		this.codClc = codClc;
	}

	@JsonProperty("habitatN2000")
	public String getHabitatN2000() {
		return habitatN2000;
	}

	public void setHabitatN2000(String habitatN2000) {
		this.habitatN2000 = habitatN2000;
	}

	@JsonProperty("fkCategoriaForestale")
	public Integer getFkCategoriaForestale() {
		return fkCategoriaForestale;
	}

	public void setFkCategoriaForestale(Integer fkCategoriaForestale) {
		this.fkCategoriaForestale = fkCategoriaForestale;
	}

	@JsonProperty("fkConfigIpla")
	public Integer getFkConfigIpla() {
		return fkConfigIpla;
	}

	public void setFkConfigIpla(Integer fkConfigIpla) {
		this.fkConfigIpla = fkConfigIpla;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		TipoForestale tipoFor = (TipoForestale) o;
		return Objects.equals(idTipoForestale, tipoFor.idTipoForestale) && Objects.equals(fkCategoriaForestale, tipoFor.fkCategoriaForestale)
				&& Objects.equals(codiceAmministrativo, tipoFor.codiceAmministrativo) && Objects.equals(tipo, tipoFor.tipo)
				&& Objects.equals(sottotipo, tipoFor.sottotipo) && Objects.equals(variante, tipoFor.variante)
				&& Objects.equals(dataModifica, tipoFor.dataModifica) && Objects.equals(dataFineValidita, tipoFor.dataFineValidita)
				&& Objects.equals(codClc, tipoFor.codClc)
				&& Objects.equals(habitatN2000, tipoFor.habitatN2000)
				&& Objects.equals(fkConfigIpla, tipoFor.fkConfigIpla);
			
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTipoForestale, fkCategoriaForestale, codiceAmministrativo, tipo, sottotipo, variante, dataModifica, dataFineValidita, codClc, habitatN2000,fkConfigIpla);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TipoForestale {\n");

		sb.append("    idTipoForestale: ").append(idTipoForestale).append("\n");
		sb.append("    fkCategoriaForestale: ").append(fkCategoriaForestale).append("\n");
		sb.append("    codiceAmministrativo: ").append(codiceAmministrativo).append("\n");
		sb.append("    tipo: ").append(tipo).append("\n");
		sb.append("    sottotipo: ").append(sottotipo).append("\n");
		sb.append("    variante: ").append(variante).append("\n");
		sb.append("    dataModifica: ").append(dataModifica).append("\n");
		sb.append("    dataFineValidita: ").append(dataFineValidita).append("\n");
		sb.append("    codClc: ").append(codClc).append("\n");
		sb.append("    habitatN2000: ").append(habitatN2000).append("\n");
		sb.append("    fkConfigIpla: ").append(fkConfigIpla).append("\n");
		sb.append("}");
		return sb.toString();
	}

}
