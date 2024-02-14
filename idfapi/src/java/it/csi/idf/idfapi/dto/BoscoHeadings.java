/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.List;

public class BoscoHeadings {
	
	private Integer id;
    private String name;
    private boolean visibility;
    private List<BoscoSubHeadings> subheadings;
    
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
	public List<BoscoSubHeadings> getSubheadings() {
		return subheadings;
	}
	public void setSubheadings(List<BoscoSubHeadings> subheadings) {
		this.subheadings = subheadings;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoscoHeadings [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", visibility=");
		builder.append(visibility);
		builder.append(", subheadings=");
		builder.append(subheadings);
		builder.append("]");
		return builder.toString();
	}
}
