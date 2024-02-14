/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;


public class ProcessoDTO
{
  private String       idPratica;  
  private String       codiceIpaEnte;   
  
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
  
  
}
