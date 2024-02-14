/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.exceptions;

import it.csi.idf.idfapi.errors.Error;

public class FieldError {
	private String fieldName;
	private Error error;
	private String arg;

	public FieldError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FieldError(String fieldName, Error error, String arg) {
		super();
		this.fieldName = fieldName;
		this.error = error;
		this.arg = arg;
	}

	public FieldError(String fieldName, int errorCode,String messageKey, String[] args) {
		super();
		this.fieldName = fieldName;
		this.error = new Error(errorCode, messageKey);
		StringBuilder builder = new StringBuilder("[");
		for(String s : args) {
		    builder.append(s).append(",");
		}
		 builder.append("]");
		
		this.arg = builder.toString();
	}

	



	public FieldError(String fieldName, int errorCode, String messageKey, String arg) {
		super();
		this.fieldName = fieldName;
		this.error = new Error(errorCode, messageKey);
		this.arg = arg;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FieldError [");
		if (fieldName != null) {
			builder.append("Error on field name = ");
			builder.append(fieldName);
			builder.append(", ");
		}
		if (error != null) {
			builder.append("error=");
			builder.append(error);
			builder.append(", ");
		}
		if (arg != null) {
			builder.append("for arg=");
			builder.append(arg);
		}
		builder.append("]");
		return builder.toString();
	}

	

}
