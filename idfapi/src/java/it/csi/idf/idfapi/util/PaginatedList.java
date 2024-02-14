/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

import java.util.List;

public class PaginatedList<T> {
	
	private List<T> content;
	private int totalElements;
	private int limit;
	private int page;
	private String sortField;
	private String sortOrder;

	
	public PaginatedList(List<T> content, int totalElements, int limit, int page, String sortField,
			String sortOrder) {
		this.content = content;
		this.totalElements = totalElements;
		this.limit = limit;
		this.page = page;
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}
	
	public PaginatedList(List<T> content, int totalElements, int limit, int page) {
		this.content = content;
		this.totalElements = totalElements;
		this.limit = limit;
		this.page = page;
	}
	
	public PaginatedList(List<T> content, int totalElements) {
		this.content = content;
		this.totalElements = totalElements;
		
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
