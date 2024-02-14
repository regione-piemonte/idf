/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class TSpecie {
	
	private Integer idSpecie;
	private String codice;
	private String codGruppo;
	private String codicePignatti;
	private String latino;
	private String volgare;
	private String linkFoto;
	private String flg386;
	private String linkScheda;
	private Integer mtdOrdinamento;
	private Byte flgVisibile;
	private String flgConiflatif;
	private Float densita;
	

	@JsonProperty("idSpecie")
	public Integer getIdSpecie() {
		return idSpecie;
	}

	public void setIdSpecie(Integer idSpecie) {
		this.idSpecie = idSpecie;
	}

	@JsonProperty("codice")
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	@JsonProperty("codGruppo")
	public String getCodGruppo() {
		return codGruppo;
	}

	public void setCodGruppo(String codGruppo) {
		this.codGruppo = codGruppo;
	}

	@JsonProperty("codicePignatti")
	public String getCodicePignatti() {
		return codicePignatti;
	}

	public void setCodicePignatti(String codicePignatti) {
		this.codicePignatti = codicePignatti;
	}

	@JsonProperty("latino")
	public String getLatino() {
		return latino;
	}

	public void setLatino(String latino) {
		this.latino = latino;
	}

	@JsonProperty("volgare")
	public String getVolgare() {
		return volgare;
	}

	public void setVolgare(String volgare) {
		this.volgare = volgare;
	}

	@JsonProperty("linkFoto")
	public String getLinkFoto() {
		return linkFoto;
	}

	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
	}

	@JsonProperty("flg386")
	public String getFlg386() {
		return flg386;
	}

	public void setFlg386(String flg386) {
		this.flg386 = flg386;
	}

	@JsonProperty("linkScheda")
	public String getLinkScheda() {
		return linkScheda;
	}

	public void setLinkScheda(String linkScheda) {
		this.linkScheda = linkScheda;
	}

	
	@JsonProperty("mtdOrdinamento")
	public Integer getMtdOrdinamento() {
		return mtdOrdinamento;
	}

	public void setMtdOrdinamento(Integer mtdOrdinamento) {
		this.mtdOrdinamento = mtdOrdinamento;
	}
	
	@JsonProperty("flgVisibile")
	public Byte getFlgVisibile() {
		return flgVisibile;
	}

	public void setFlgVisibile(Byte flgVisibile) {
		this.flgVisibile = flgVisibile;
	}

	public String getFlgConiflatif() {
		return flgConiflatif;
	}

	public void setFlgConiflatif(String flgConiflatif) {
		this.flgConiflatif = flgConiflatif;
	}
	
	public Float getDensita() {
		return densita;
	}

	public void setDensita(Float densita) {
		this.densita = densita;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codGruppo == null) ? 0 : codGruppo.hashCode());
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		result = prime * result + ((codicePignatti == null) ? 0 : codicePignatti.hashCode());
		result = prime * result + ((densita == null) ? 0 : densita.hashCode());
		result = prime * result + ((flg386 == null) ? 0 : flg386.hashCode());
		result = prime * result + ((flgConiflatif == null) ? 0 : flgConiflatif.hashCode());
		result = prime * result + ((flgVisibile == null) ? 0 : flgVisibile.hashCode());
		result = prime * result + ((idSpecie == null) ? 0 : idSpecie.hashCode());
		result = prime * result + ((latino == null) ? 0 : latino.hashCode());
		result = prime * result + ((linkFoto == null) ? 0 : linkFoto.hashCode());
		result = prime * result + ((linkScheda == null) ? 0 : linkScheda.hashCode());
		result = prime * result + ((mtdOrdinamento == null) ? 0 : mtdOrdinamento.hashCode());
		result = prime * result + ((volgare == null) ? 0 : volgare.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TSpecie other = (TSpecie) obj;
		if (codGruppo == null) {
			if (other.codGruppo != null)
				return false;
		} else if (!codGruppo.equals(other.codGruppo))
			return false;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		if (codicePignatti == null) {
			if (other.codicePignatti != null)
				return false;
		} else if (!codicePignatti.equals(other.codicePignatti))
			return false;
		if (densita == null) {
			if (other.densita != null)
				return false;
		} else if (!densita.equals(other.densita))
			return false;
		if (flg386 == null) {
			if (other.flg386 != null)
				return false;
		} else if (!flg386.equals(other.flg386))
			return false;
		if (flgConiflatif == null) {
			if (other.flgConiflatif != null)
				return false;
		} else if (!flgConiflatif.equals(other.flgConiflatif))
			return false;
		if (flgVisibile == null) {
			if (other.flgVisibile != null)
				return false;
		} else if (!flgVisibile.equals(other.flgVisibile))
			return false;
		if (idSpecie == null) {
			if (other.idSpecie != null)
				return false;
		} else if (!idSpecie.equals(other.idSpecie))
			return false;
		if (latino == null) {
			if (other.latino != null)
				return false;
		} else if (!latino.equals(other.latino))
			return false;
		if (linkFoto == null) {
			if (other.linkFoto != null)
				return false;
		} else if (!linkFoto.equals(other.linkFoto))
			return false;
		if (linkScheda == null) {
			if (other.linkScheda != null)
				return false;
		} else if (!linkScheda.equals(other.linkScheda))
			return false;
		if (mtdOrdinamento == null) {
			if (other.mtdOrdinamento != null)
				return false;
		} else if (!mtdOrdinamento.equals(other.mtdOrdinamento))
			return false;
		if (volgare == null) {
			if (other.volgare != null)
				return false;
		} else if (!volgare.equals(other.volgare))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class TSpecie {\n");

		sb.append("    idSpecie: ").append(idSpecie).append("\n");
		sb.append("    codice: ").append(codice).append("\n");
		sb.append("    codGruppo: ").append(codGruppo).append("\n");
		sb.append("    codicePignatti: ").append(codicePignatti).append("\n");
		sb.append("    latino: ").append(latino).append("\n");
		sb.append("    volgare: ").append(volgare).append("\n");
		sb.append("    linkScheda: ").append(linkScheda).append("\n");
		sb.append("    linkFoto: ").append(linkFoto).append("\n");
		sb.append("    flg386: ").append(flg386).append("\n");
		sb.append("    mtdOrdinamento: ").append(mtdOrdinamento).append("\n");
		sb.append("    flgVisibile: ").append(flgVisibile).append("\n");
		sb.append("    flgConiflatif: ").append(flgConiflatif).append("\n");
		sb.append("    densita: ").append(densita).append("\n");
		
		sb.append("}");
		return sb.toString();
	}

}
