/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import it.csi.idf.idfapi.config.LocalDateDeserializer;
import it.csi.idf.idfapi.config.LocalDateSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.time.LocalDate;

public class TagliPfaStep1 {
	private Integer idIntervento;
	private Integer tipoIstanzaId;
	private Integer fkTipoIntervento;
	private Integer fkGoverno;
	private Integer fkStatoIntervento;
	
	private TrasformazSelvicolturali trasformazSelvicolturale;
	private Proprieta proprieta;
	private CategoriaSelvicolturale categoriaSelvicolturale;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dataPresaInCarico;

	private String annataSilvana;

	private Integer fkInterventoPadreVariante;
	private Integer fkInterventoPadreProroga;

	  //GP 20062023
	private String flgConformeDeroga;
	private Integer nrProgressivoInterv;

	// JC - Step1
	private Integer idEvento;
	
	//RCH - Step1 
	private Integer idTipoEvento;
	
	public Integer getNrProgressivoInterv() {
		return nrProgressivoInterv;
	}

	public void setNrProgressivoInterv(Integer nrProgressivoInterv) {
		this.nrProgressivoInterv = nrProgressivoInterv;
	}

	public Integer getIdIntervento() {
		return idIntervento;
	}

	public void setIdIntervento(Integer idIntervento) {
		this.idIntervento = idIntervento;
	}

	public Integer getTipoIstanzaId() {
		return tipoIstanzaId;
	}

	public void setTipoIstanzaId(Integer tipoIstanzaId) {
		this.tipoIstanzaId = tipoIstanzaId;
	}

	public Integer getFkTipoIntervento() {
		return fkTipoIntervento;
	}

	public void setFkTipoIntervento(Integer fkTipoIntervento) {
		this.fkTipoIntervento = fkTipoIntervento;
	}

	public Integer getFkGoverno() {
		return fkGoverno;
	}

	public void setFkGoverno(Integer fkGoverno) {
		this.fkGoverno = fkGoverno;
	}

	public Integer getFkStatoIntervento() {
		return fkStatoIntervento;
	}

	public void setFkStatoIntervento(Integer fkStatoIntervento) {
		this.fkStatoIntervento = fkStatoIntervento;
	}

	public TrasformazSelvicolturali getTrasformazSelvicolturale() {
		return trasformazSelvicolturale;
	}

	public void setTrasformazSelvicolturale(TrasformazSelvicolturali trasformazSelvicolturale) {
		this.trasformazSelvicolturale = trasformazSelvicolturale;
	}

	public Proprieta getProprieta() {
		return proprieta;
	}

	public void setProprieta(Proprieta proprieta) {
		this.proprieta = proprieta;
	}

	public CategoriaSelvicolturale getCategoriaSelvicolturale() {
		return categoriaSelvicolturale;
	}

	public void setCategoriaSelvicolturale(CategoriaSelvicolturale categoriaSelvicolturale) {
		this.categoriaSelvicolturale = categoriaSelvicolturale;
	}

	public LocalDate getDataPresaInCarico() {
		return dataPresaInCarico;
	}

	public void setDataPresaInCarico(LocalDate dataPresaInCarico) {
		this.dataPresaInCarico = dataPresaInCarico;
	}

	public String getAnnataSilvana() {
		return annataSilvana;
	}

	public void setAnnataSilvana(String annataSilvana) {
		this.annataSilvana = annataSilvana;
	}

	public Integer getFkInterventoPadreVariante() {
		return fkInterventoPadreVariante;
	}

	public void setFkInterventoPadreVariante(Integer fkInterventoPadreVariante) {
		this.fkInterventoPadreVariante = fkInterventoPadreVariante;
	}

	public Integer getFkInterventoPadreProroga() {
		return fkInterventoPadreProroga;
	}

	public void setFkInterventoPadreProroga(Integer fkInterventoPadreProroga) {
		this.fkInterventoPadreProroga = fkInterventoPadreProroga;
	}

	public String getFlgConformeDeroga() {
		return flgConformeDeroga;
	}

	public void setFlgConformeDeroga(String flgConformeDeroga) {
		this.flgConformeDeroga = flgConformeDeroga;
	}
	
	public Integer getIdEvento() {
		return this.idEvento;
	}
	
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	//RCH 
	public Integer getIdTipoEvento() {
		return this.idTipoEvento;
	}
	
	public void setIdTipoEvento(Integer idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}
}
