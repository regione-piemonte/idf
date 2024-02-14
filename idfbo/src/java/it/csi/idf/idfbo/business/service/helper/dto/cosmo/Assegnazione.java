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
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.csi.idf.idfbo.business.service.helper.dto.cosmo.Utente;
import java.io.IOException;
/**
 * utente o gruppo assegnatario dell&#x27;attività
 */
public class Assegnazione {
  @SerializedName("utente")
  private Utente utente = null;

  @SerializedName("gruppo")
  private String gruppo = null;

  public Assegnazione utente(Utente utente) {
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

  public Assegnazione gruppo(String gruppo) {
    this.gruppo = gruppo;
    return this;
  }

   /**
   * Get gruppo
   * @return gruppo
  **/
  public String getGruppo() {
    return gruppo;
  }

  public void setGruppo(String gruppo) {
    this.gruppo = gruppo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Assegnazione assegnazione = (Assegnazione) o;
    return Objects.equals(this.utente, assegnazione.utente) &&
        Objects.equals(this.gruppo, assegnazione.gruppo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(utente, gruppo);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Assegnazione {\n");
    
    sb.append("    utente: ").append(toIndentedString(utente)).append("\n");
    sb.append("    gruppo: ").append(toIndentedString(gruppo)).append("\n");
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