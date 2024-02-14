/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.errors;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonProperty;

public class Error   {
  // verra' utilizzata la seguente strategia serializzazione degli attributi: [explicit-as-modeled] 
	
  public Error() {}
  
  public Error(int code, String errorMessage) {
	  this.code = code;
	  this.errorMessage = errorMessage;
  }
  
  private int code;
  private String messaggioCittadino = null;
  private String errorMessage = null;
  private String fields = null;

  @JsonProperty("messaggioCittadino") 
 
  public String getMessaggioCittadino() {
    return messaggioCittadino;
  }
  @JsonProperty("code") 
  public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public void setMessaggioCittadino(String messaggioCittadino) {
    this.messaggioCittadino = messaggioCittadino;
  }

  /**
   **/
  

  @JsonProperty("errorMessage") 
 
  public String getErrorMessage() {
    return errorMessage;
  }
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   **/
  

  @JsonProperty("fields") 
 
  public String getFields() {
    return fields;
  }
  public void setFields(String fields) {
    this.fields = fields;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(code, error.code) &&
        Objects.equals(messaggioCittadino, error.messaggioCittadino) &&
        Objects.equals(errorMessage, error.errorMessage) &&
        Objects.equals(fields, error.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, messaggioCittadino, errorMessage, fields);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    messaggioCittadino: ").append(toIndentedString(messaggioCittadino)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

