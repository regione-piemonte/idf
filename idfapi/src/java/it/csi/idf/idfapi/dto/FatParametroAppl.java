/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class FatParametroAppl {
	
	private Integer idParametroAppl;
	private Integer ifkTipoParamAppl;
	private Integer parametroApplNum;
	private String parametroApplChar;
	private Integer idTipoParamAppl;
	private String descrTipoParamAppl;
	
	public Integer getIdParametroAppl() {
		return idParametroAppl;
	}
	public void setIdParametroAppl(Integer idParametroAppl) {
		this.idParametroAppl = idParametroAppl;
	}
	public Integer getIfkTipoParamAppl() {
		return ifkTipoParamAppl;
	}
	public void setIfkTipoParamAppl(Integer ifkTipoParamAppl) {
		this.ifkTipoParamAppl = ifkTipoParamAppl;
	}
	public Integer getParametroApplNum() {
		return parametroApplNum;
	}
	public void setParametroApplNum(Integer parametroApplNum) {
		this.parametroApplNum = parametroApplNum;
	}
	public String getParametroApplChar() {
		return parametroApplChar;
	}
	public void setParametroApplChar(String parametroApplChar) {
		this.parametroApplChar = parametroApplChar;
	}
	public Integer getIdTipoParamAppl() {
		return idTipoParamAppl;
	}
	public void setIdTipoParamAppl(Integer idTipoParamAppl) {
		this.idTipoParamAppl = idTipoParamAppl;
	}
	public String getDescrTipoParamAppl() {
		return descrTipoParamAppl;
	}
	public void setDescrTipoParamAppl(String descrTipoParamAppl) {
		this.descrTipoParamAppl = descrTipoParamAppl;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FatParametroAppl [idParametroAppl=");
		builder.append(idParametroAppl);
		builder.append(", ifkTipoParamAppl=");
		builder.append(ifkTipoParamAppl);
		builder.append(", parametroApplNum=");
		builder.append(parametroApplNum);
		builder.append(", parametroApplChar=");
		builder.append(parametroApplChar);
		builder.append(", idTipoParamAppl=");
		builder.append(idTipoParamAppl);
		builder.append(", descrTipoParamAppl=");
		builder.append(descrTipoParamAppl);
		builder.append("]");
		return builder.toString();
	}
}
