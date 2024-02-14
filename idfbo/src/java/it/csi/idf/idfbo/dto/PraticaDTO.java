/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;


public class PraticaDTO
{
  private String       idPratica;
  private String       oggetto;
  private String       codiceIpaEnte;
  private String       codiceTipologia;
  private String       riassunto;
  private String       metadati;
  
  //private String utenteCreazionePratica;
  
  
  
  public String getIdPratica()
  {
    return idPratica;
  }
  public void setIdPratica(String idPratica)
  {
    this.idPratica = idPratica;
  }
  public String getOggetto()
  {
    return oggetto;
  }
  public void setOggetto(String oggetto)
  {
    this.oggetto = oggetto;
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
  public String getRiassunto()
  {
    return riassunto;
  }
  public void setRiassunto(String riassunto)
  {
    this.riassunto = riassunto;
  }
  public String getMetadati()
  {
    return metadati;
  }
  public void setMetadati(String metadati)
  {
    this.metadati = metadati;
  }
 /* public String getUtenteCreazionePratica()
  {
    return utenteCreazionePratica;
  }
  public void setUtenteCreazionePratica(String utenteCreazionePratica)
  {
    this.utenteCreazionePratica = utenteCreazionePratica;
  }*/
  
  

  
}
