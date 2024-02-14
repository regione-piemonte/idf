/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.vincolo.pojo;

import java.util.List;

public class VincoloHeading {
	
	private Integer id;
    private String name;
    private boolean visibility;
    private String type;
    private List<VincoloSubHeading> subheadings;
    
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
	
	public boolean isVisibility() {
		return visibility;
	}
	
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	
	public List<VincoloSubHeading> getSubheadings() {
		return subheadings;
	}
	
	public void setSubheadings(List<VincoloSubHeading> subheadings) {
		this.subheadings = subheadings;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
