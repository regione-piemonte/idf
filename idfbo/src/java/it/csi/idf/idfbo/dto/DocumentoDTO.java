/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import java.util.List;

public class DocumentoDTO
{
  private String       idPratica;  
  private String       codiceIpaEnte; 
  private List<DocumentiDTO>  documenti;
  
  
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
  public List<DocumentiDTO> getDocumenti()
  {
    return documenti;
  }
  public void setDocumenti(List<DocumentiDTO> documenti)
  {
    this.documenti = documenti;
  }
  
  
  
  
  
}
