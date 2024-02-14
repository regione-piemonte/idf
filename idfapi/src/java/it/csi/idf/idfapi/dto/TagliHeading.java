/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagliHeading {
	
	private Integer id;
    private String name;
	private Integer mtdOrdinamento;
    private boolean visibility;

    private boolean checked;

    private List<TagliSubHeading> subheadings;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMtdOrdinamento() {
        return mtdOrdinamento;
    }

    public void setMtdOrdinamento(Integer mtdOrdinamento) {
        this.mtdOrdinamento = mtdOrdinamento;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public List<TagliSubHeading> getSubheadings() {
        return subheadings;
    }

    public void setSubheadings(List<TagliSubHeading> subheadings) {
        this.subheadings = subheadings;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
