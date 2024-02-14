/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

public class PlainAdpVincoloHome {
	private Integer tipoAccred;
	private Integer tipoIstanzaId;
	private String tipoIstanzaDescr;
	private Integer categoriaProfesionale;
	private String fkTipoAccreditamento;
	
	private String partitaIva;
	private String pec;
	private String numeroIscrizione;
	private String provinciaOrdine;
	private String codiceFiscaleDelega;
	private String nome ;
	private String cognome;
	private Integer fkProfilioUtente;
	private String ownerCodiceFiscale;

	public PlainAdpVincoloHome() {
		
	}

	public Integer getTipoAccred() {
		return tipoAccred;
	}

	public void setTipoAccred(Integer tipoAccred) {
		this.tipoAccred = tipoAccred;
	}

	public Integer getCategoriaProfesionale() {
		return categoriaProfesionale;
	}

	public void setCategoriaProfesionale(Integer categoriaProfesionale) {
		this.categoriaProfesionale = categoriaProfesionale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Integer getTipoIstanzaId() {
		return tipoIstanzaId;
	}

	public void setTipoIstanzaId(Integer tipoIstanzaId) {
		this.tipoIstanzaId = tipoIstanzaId;
	}

	public String getTipoIstanzaDescr() {
		return tipoIstanzaDescr;
	}

	public void setTipoIstanzaDescr(String tipoIstanzaDescr) {
		this.tipoIstanzaDescr = tipoIstanzaDescr;
	}

	public String getFkTipoAccreditamento() {
		return fkTipoAccreditamento;
	}

	public void setFkTipoAccreditamento(String fkTipoAccreditamento) {
		this.fkTipoAccreditamento = fkTipoAccreditamento;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public String getNumeroIscrizione() {
		return numeroIscrizione;
	}

	public void setNumeroIscrizione(String numeroIscrizione) {
		this.numeroIscrizione = numeroIscrizione;
	}

	public String getProvinciaOrdine() {
		return provinciaOrdine;
	}

	public void setProvinciaOrdine(String provinciaOrdine) {
		this.provinciaOrdine = provinciaOrdine;
	}

	public String getCodiceFiscaleDelega() {
		return codiceFiscaleDelega;
	}

	public void setCodiceFiscaleDelega(String codiceFiscaleDelega) {
		this.codiceFiscaleDelega = codiceFiscaleDelega;
	}

	public String getOwnerCodiceFiscale() {
		return ownerCodiceFiscale;
	}

	public void setOwnerCodiceFiscale(String ownerCodiceFiscale) {
		this.ownerCodiceFiscale = ownerCodiceFiscale;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlainAdpVincoloHome [");
		if (tipoAccred != null) {
			builder.append("tipoAccred=");
			builder.append(tipoAccred);
			builder.append(", ");
		}
		if (tipoIstanzaId != null) {
			builder.append("tipoIstanzaId=");
			builder.append(tipoIstanzaId);
			builder.append(", ");
		}
		if (tipoIstanzaDescr != null) {
			builder.append("tipoIstanzaDescr=");
			builder.append(tipoIstanzaDescr);
			builder.append(", ");
		}
		if (categoriaProfesionale != null) {
			builder.append("categoriaProfesionale=");
			builder.append(categoriaProfesionale);
			builder.append(", ");
		}
		if (fkTipoAccreditamento != null) {
			builder.append("fkTipoAccreditamento=");
			builder.append(fkTipoAccreditamento);
			builder.append(", ");
		}
		if (partitaIva != null) {
			builder.append("partitaIva=");
			builder.append(partitaIva);
			builder.append(", ");
		}
		if (pec != null) {
			builder.append("pec=");
			builder.append(pec);
			builder.append(", ");
		}
		if (numeroIscrizione != null) {
			builder.append("numeroIscrizione=");
			builder.append(numeroIscrizione);
			builder.append(", ");
		}
		if (provinciaOrdine != null) {
			builder.append("provinciaOrdine=");
			builder.append(provinciaOrdine);
			builder.append(", ");
		}
		if (codiceFiscaleDelega != null) {
			builder.append("codiceFiscaleDelega=");
			builder.append(codiceFiscaleDelega);
			builder.append(", ");
		}
		if (nome != null) {
			builder.append("nome=");
			builder.append(nome);
			builder.append(", ");
		}
		if (cognome != null) {
			builder.append("cognome=");
			builder.append(cognome);
			builder.append(", ");
		}
		if (ownerCodiceFiscale != null) {
			builder.append("ownerCodiceFiscale=");
			builder.append(ownerCodiceFiscale);
		}
		builder.append("]");
		return builder.toString();
	}

	public Integer getFkProfilioUtente() {
		return fkProfilioUtente;
	}

	public void setFkProfilioUtente(Integer fkProfilioUtente) {
		this.fkProfilioUtente = fkProfilioUtente;
	}

	


	

	
	
}
