/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

public class Priorita {

	private Integer idPriorita;
    private String descrPriorita;
    private String codPriorita;
    private Integer fkConfigIpla;
    private Integer mtd_ordinamento;
    private Byte flg_visibile;
	
    public Priorita() {	
    	
    }

	public Integer getIdPriorita() {
		return idPriorita;
	}

	public void setIdPriorita(Integer idPriorita) {
		this.idPriorita = idPriorita;
	}

	public String getDescrPriorita() {
		return descrPriorita;
	}

	public void setDescrPriorita(String descrPriorita) {
		this.descrPriorita = descrPriorita;
	}

	public String getCodPriorita() {
		return codPriorita;
	}

	public void setCodPriorita(String codPriorita) {
		this.codPriorita = codPriorita;
	}

	public Integer getFkConfigIpla() {
		return fkConfigIpla;
	}

	public void setFkConfigIpla(Integer fkConfigIpla) {
		this.fkConfigIpla = fkConfigIpla;
	}

	public Integer getMtd_ordinamento() {
		return mtd_ordinamento;
	}

	public void setMtd_ordinamento(Integer mtd_ordinamento) {
		this.mtd_ordinamento = mtd_ordinamento;
	}

	public Byte getFlg_visibile() {
		return flg_visibile;
	}

	public void setFlg_visibile(Byte flg_visibile) {
		this.flg_visibile = flg_visibile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPriorita == null) ? 0 : codPriorita.hashCode());
		result = prime * result + ((descrPriorita == null) ? 0 : descrPriorita.hashCode());
		result = prime * result + ((fkConfigIpla == null) ? 0 : fkConfigIpla.hashCode());
		result = prime * result + ((flg_visibile == null) ? 0 : flg_visibile.hashCode());
		result = prime * result + ((idPriorita == null) ? 0 : idPriorita.hashCode());
		result = prime * result + ((mtd_ordinamento == null) ? 0 : mtd_ordinamento.hashCode());
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
		Priorita other = (Priorita) obj;
		if (idPriorita == null) {
			if (other.idPriorita != null)
				return false;
		} else if (!idPriorita.equals(other.idPriorita))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Priorita [idPriorita=");
		builder.append(idPriorita);
		builder.append(", descrPriorita=");
		builder.append(descrPriorita);
		builder.append(", codPriorita=");
		builder.append(codPriorita);
		builder.append(", fkConfigIpla=");
		builder.append(fkConfigIpla);
		builder.append(", mtd_ordinamento=");
		builder.append(mtd_ordinamento);
		builder.append(", flg_visibile=");
		builder.append(flg_visibile);
		builder.append("]");
		return builder.toString();
	}
        
}
