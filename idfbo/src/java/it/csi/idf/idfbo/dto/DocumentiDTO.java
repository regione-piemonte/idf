/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;


public class DocumentiDTO
{
  private String id;
  private String idPadre;
  private String codiceTipo;
  private String titolo;
  private String descrizione;
  private String autore;
  private String uploadUUID;
  private String codiceStato;
  private String idPratica;
  
  
  public String getId()
  {
    return id;
  }
  public void setId(String id)
  {
    this.id = id;
  }
  public String getIdPadre()
  {
    return idPadre;
  }
  public void setIdPadre(String idPadre)
  {
    this.idPadre = idPadre;
  }
  public String getCodiceTipo()
  {
    return codiceTipo;
  }
  public void setCodiceTipo(String codiceTipo)
  {
    this.codiceTipo = codiceTipo;
  }
  public String getTitolo()
  {
    return titolo;
  }
  public void setTitolo(String titolo)
  {
    this.titolo = titolo;
  }
  public String getDescrizione()
  {
    return descrizione;
  }
  public void setDescrizione(String descrizione)
  {
    this.descrizione = descrizione;
  }
  public String getAutore()
  {
    return autore;
  }
  public void setAutore(String autore)
  {
    this.autore = autore;
  }
  public String getUploadUUID()
  {
    return uploadUUID;
  }
  public void setUploadUUID(String uploadUUID)
  {
    this.uploadUUID = uploadUUID;
  }
  public String getCodiceStato()
  {
    return codiceStato;
  }
  public void setCodiceStato(String codiceStato)
  {
    this.codiceStato = codiceStato;
  }
  public String getIdPratica()
  {
    return idPratica;
  }
  public void setIdPratica(String idPratica)
  {
    this.idPratica = idPratica;
  }
  
  
  
  
  
}
