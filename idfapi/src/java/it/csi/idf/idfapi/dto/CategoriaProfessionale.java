/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class CategoriaProfessionale {
	
	private Integer idCategoriaProfessionale;
	private String descrCategoriaProfessionale;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	

	public Integer getIdCategoriaProfessionale() {
		return idCategoriaProfessionale;
	}
	public void setIdCategoriaProfessionale(Integer idCategoriaProfessionale) {
		this.idCategoriaProfessionale = idCategoriaProfessionale;
	}
	public String getDescrCategoriaProfessionale() {
		return descrCategoriaProfessionale;
	}
	public void setDescrCategoriaProfessionale(String descrCategoriaProfessionale) {
		this.descrCategoriaProfessionale = descrCategoriaProfessionale;
	}
	
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}
	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	public Byte getFlgVisibile() {
		return flgVisibile;
	}
	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoriaProfessionale [idCategoriaProfessionale=");
		builder.append(idCategoriaProfessionale);
		builder.append(", descrCategoriaProfessionale=");
		builder.append(descrCategoriaProfessionale);
		builder.append(", mtdOrdinamento=");
		builder.append(mtdOrdinamento);
		builder.append(", flgVisibile=");
		builder.append(flgVisibile);
		builder.append("]");
		return builder.toString();
	}
	

	
	
}
