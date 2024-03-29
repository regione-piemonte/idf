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
import com.google.gson.annotations.SerializedName;
import it.csi.idf.idfbo.business.service.helper.dto.cosmo.ProtocolloDocumento;
import java.io.IOException;
/**
 * ArchiviazioneDocumento
 */

@JsonIgnoreProperties
public class ArchiviazioneDocumento {
  @SerializedName("protocollo")
  private ProtocolloDocumento protocollo = null;

  @SerializedName("classificazione")
  private String classificazione = null;

  public ArchiviazioneDocumento protocollo(ProtocolloDocumento protocollo) {
    this.protocollo = protocollo;
    return this;
  }

   /**
   * Get protocollo
   * @return protocollo
  **/
  public ProtocolloDocumento getProtocollo() {
    return protocollo;
  }

  public void setProtocollo(ProtocolloDocumento protocollo) {
    this.protocollo = protocollo;
  }

  public ArchiviazioneDocumento classificazione(String classificazione) {
    this.classificazione = classificazione;
    return this;
  }

   /**
   * Get classificazione
   * @return classificazione
  **/
  public String getClassificazione() {
    return classificazione;
  }

  public void setClassificazione(String classificazione) {
    this.classificazione = classificazione;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArchiviazioneDocumento archiviazioneDocumento = (ArchiviazioneDocumento) o;
    return Objects.equals(this.protocollo, archiviazioneDocumento.protocollo) &&
        Objects.equals(this.classificazione, archiviazioneDocumento.classificazione);
  }

  @Override
  public int hashCode() {
    return Objects.hash(protocollo, classificazione);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArchiviazioneDocumento {\n");
    
    sb.append("    protocollo: ").append(toIndentedString(protocollo)).append("\n");
    sb.append("    classificazione: ").append(toIndentedString(classificazione)).append("\n");
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
