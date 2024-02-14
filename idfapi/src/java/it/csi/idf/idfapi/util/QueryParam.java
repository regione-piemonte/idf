/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

public class QueryParam {
	Object value;
	String fieldName;
	String comparisonOperator;
	
	public QueryParam(Object value, String fieldName) {
		super();
		this.value = value;
		this.fieldName = fieldName;
		this.comparisonOperator = "=";
	}
	
	public QueryParam(Object value, String fieldName, String comparisonOperator) {
		super();
		this.value = value;
		this.fieldName = fieldName;
		this.comparisonOperator = comparisonOperator;
	}

	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getComparisonOperator() {
		return comparisonOperator;
	}
	public void setComparisonOperator(String comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
	}

	
	
}
