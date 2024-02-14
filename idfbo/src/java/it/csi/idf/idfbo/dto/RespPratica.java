/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class RespPratica
{
  
  private String   idPratica;
  private String   codiceIpaEnte;
  private String   codiceTipologia;
  private String   oggetto;
  private String   riassunto;
  private String   utenteCreazionePratica;
  private String   uploadUUID;
  private String   idProcesso;
  private String   idPraticaCosmo;
  
  
  public String getIdPratica()
  {
    return idPratica;
  }
  public void setIdPratica(String idPratica)
  {
    this.idPratica = idPratica;
  }
  public String getCodiceIpaEnte()
  {
    return codiceIpaEnte;
  }
  public void setCodiceIpaEnte(String codiceIpaEnte)
  {
    this.codiceIpaEnte = codiceIpaEnte;
  }
  public String getCodiceTipologia()
  {
    return codiceTipologia;
  }
  public void setCodiceTipologia(String codiceTipologia)
  {
    this.codiceTipologia = codiceTipologia;
  }
  public String getOggetto()
  {
    return oggetto;
  }
  public void setOggetto(String oggetto)
  {
    this.oggetto = oggetto;
  }
  public String getRiassunto()
  {
    return riassunto;
  }
  public void setRiassunto(String riassunto)
  {
    this.riassunto = riassunto;
  }
  public String getUtenteCreazionePratica()
  {
    return utenteCreazionePratica;
  }
  public void setUtenteCreazionePratica(String utenteCreazionePratica)
  {
    this.utenteCreazionePratica = utenteCreazionePratica;
  }
  public String getUploadUUID()
  {
    return uploadUUID;
  }
  public void setUploadUUID(String uploadUUID)
  {
    this.uploadUUID = uploadUUID;
  }
  public String getIdProcesso()
  {
    return idProcesso;
  }
  public void setIdProcesso(String idProcesso)
  {
    this.idProcesso = idProcesso;
  }
  public String getIdPraticaCosmo()
  {
    return idPraticaCosmo;
  }
  public void setIdPraticaCosmo(String idPraticaCosmo)
  {
    this.idPraticaCosmo = idPraticaCosmo;
  }
  
  
  

  
}
