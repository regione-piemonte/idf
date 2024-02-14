/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import java.math.BigDecimal;

public class VincoloStep5DTO {
	
    private String flagCompensazione;

    
    private BigDecimal cmSupBosc;
    private BigDecimal cmSupNoBosc;
    private BigDecimal cmNoBoscEuro;
    private BigDecimal cmBoscEuro;
  
    private String flagEsenzione;

    private Boolean flagArt9A;
    private Boolean flagArt9B;
    private Boolean flagArt9C;
  
    private Boolean flagArt7A;
    private Boolean flagArt7B;
    private Boolean flagArt7C;
    private Boolean flagArt7D;
    private Boolean flagArt7DBis;
	public String getFlagCompensazione() {
		return flagCompensazione;
	}
	public void setFlagCompensazione(String flagCompensazione) {
		this.flagCompensazione = flagCompensazione;
	}

	public BigDecimal getCmSupBosc() {
		return cmSupBosc;
	}
	public void setCmSupBosc(BigDecimal cmSupBosc) {
		this.cmSupBosc = cmSupBosc;
	}
	public BigDecimal getCmSupNoBosc() {
		return cmSupNoBosc;
	}
	public void setCmSupNoBosc(BigDecimal cmSupNoBosc) {
		this.cmSupNoBosc = cmSupNoBosc;
	}
	public BigDecimal getCmNoBoscEuro() {
		return cmNoBoscEuro;
	}
	public void setCmNoBoscEuro(BigDecimal cmNoBoscEuro) {
		this.cmNoBoscEuro = cmNoBoscEuro;
	}
	public BigDecimal getCmBoscEuro() {
		return cmBoscEuro;
	}
	public void setCmBoscEuro(BigDecimal cmBoscEuro) {
		this.cmBoscEuro = cmBoscEuro;
	}
	public String getFlagEsenzione() {
		return flagEsenzione;
	}
	public void setFlagEsenzione(String flagEsenzione) {
		this.flagEsenzione = flagEsenzione;
	}
	public Boolean getFlagArt9A() {
		return flagArt9A;
	}
	public void setFlagArt9A(Boolean flagArt9A) {
		this.flagArt9A = flagArt9A;
	}
	public Boolean getFlagArt9B() {
		return flagArt9B;
	}
	public void setFlagArt9B(Boolean flagArt9B) {
		this.flagArt9B = flagArt9B;
	}
	public Boolean getFlagArt9C() {
		return flagArt9C;
	}
	public void setFlagArt9C(Boolean flagArt9C) {
		this.flagArt9C = flagArt9C;
	}
	public Boolean getFlagArt7A() {
		return flagArt7A;
	}
	public void setFlagArt7A(Boolean flagArt7A) {
		this.flagArt7A = flagArt7A;
	}
	public Boolean getFlagArt7B() {
		return flagArt7B;
	}
	public void setFlagArt7B(Boolean flagArt7B) {
		this.flagArt7B = flagArt7B;
	}
	public Boolean getFlagArt7C() {
		return flagArt7C;
	}
	public void setFlagArt7C(Boolean flagArt7C) {
		this.flagArt7C = flagArt7C;
	}
	public Boolean getFlagArt7D() {
		return flagArt7D;
	}
	public void setFlagArt7D(Boolean flagArt7D) {
		this.flagArt7D = flagArt7D;
	}
	public Boolean getFlagArt7DBis() {
		return flagArt7DBis;
	}
	public void setFlagArt7DBis(Boolean flagArt7DBis) {
		this.flagArt7DBis = flagArt7DBis;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VincoloStep5DTO [flagCompensazione=");
		builder.append(flagCompensazione);
		builder.append(", cmSupBosc=");
		builder.append(cmSupBosc);
		builder.append(", cmSupNoBosc=");
		builder.append(cmSupNoBosc);
		builder.append(", cmNoBoscEuro=");
		builder.append(cmNoBoscEuro);
		builder.append(", cmBoscEuro=");
		builder.append(cmBoscEuro);
		builder.append(", flagEsenzione=");
		builder.append(flagEsenzione);
		builder.append(", flagArt9A=");
		builder.append(flagArt9A);
		builder.append(", flagArt9B=");
		builder.append(flagArt9B);
		builder.append(", flagArt9C=");
		builder.append(flagArt9C);
		builder.append(", flagArt7A=");
		builder.append(flagArt7A);
		builder.append(", flagArt7B=");
		builder.append(flagArt7B);
		builder.append(", flagArt7C=");
		builder.append(flagArt7C);
		builder.append(", flagArt7D=");
		builder.append(flagArt7D);
		builder.append(", flagArt7DBis=");
		builder.append(flagArt7DBis);
		builder.append("]");
		return builder.toString();
	}
    
    

}
