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
import it.csi.idf.idfbo.business.service.helper.dto.cosmo.Utente;

import java.io.IOException;
import java.util.Date;
/**
 * Messaggio
 */

@JsonIgnoreProperties
public class Messaggio {
  @SerializedName("utente")
  private Utente utente = null;

  @SerializedName("messaggio")
  private String messaggio = null;

  @SerializedName("timestamp")
  private Date timestamp = null;

  public Messaggio utente(Utente utente) {
    this.utente = utente;
    return this;
  }

   /**
   * Get utente
   * @return utente
  **/
  
  public Utente getUtente() {
    return utente;
  }

  public void setUtente(Utente utente) {
    this.utente = utente;
  }

  public Messaggio messaggio(String messaggio) {
    this.messaggio = messaggio;
    return this;
  }

   /**
   * Get messaggio
   * @return messaggio
  **/
  
  public String getMessaggio() {
    return messaggio;
  }

  public void setMessaggio(String messaggio) {
    this.messaggio = messaggio;
  }

  public Messaggio timestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

   /**
   * Get timestamp
   * @return timestamp
  **/
  
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Messaggio messaggio = (Messaggio) o;
    return Objects.equals(this.utente, messaggio.utente) &&
        Objects.equals(this.messaggio, messaggio.messaggio) &&
        Objects.equals(this.timestamp, messaggio.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(utente, messaggio, timestamp);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Messaggio {\n");
    
    sb.append("    utente: ").append(toIndentedString(utente)).append("\n");
    sb.append("    messaggio: ").append(toIndentedString(messaggio)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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
