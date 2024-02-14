/*
 * StatoPratica
 * Aggiornamento dello stato pratica per IDF
 *
 * OpenAPI spec version: 1.0
 * Contact: info@cosmo.csi.it
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package it.csi.idf.idfbo.business.service.helper.dto.cosmo;

import java.util.Objects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Arrays;
import com.google.gson.TypeAdapter;

import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
/**
 * ProtocolloDocumento
 */

@JsonIgnoreProperties
public class ProtocolloDocumento {
  @SerializedName("numero")
  private String numero = null;

  @SerializedName("data")
  private String data = null;

  public ProtocolloDocumento numero(String numero) {
    this.numero = numero;
    return this;
  }

   /**
   * Get numero
   * @return numero
  **/
  
  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public ProtocolloDocumento data(String data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  
  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProtocolloDocumento protocolloDocumento = (ProtocolloDocumento) o;
    return Objects.equals(this.numero, protocolloDocumento.numero) &&
        Objects.equals(this.data, protocolloDocumento.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numero, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProtocolloDocumento {\n");
    
    sb.append("    numero: ").append(toIndentedString(numero)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}