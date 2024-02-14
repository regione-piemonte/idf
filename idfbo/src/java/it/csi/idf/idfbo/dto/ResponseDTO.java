/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;



public class ResponseDTO
{
  
  private String   idPratica;
  private ErrorMessageDTO   errore;
  private String uploadUUID;
  
  
  public String getIdPratica()
  {
    return idPratica;
  }
  public void setIdPratica(String idPratica)
  {
    this.idPratica = idPratica;
  }
  public ErrorMessageDTO getErrore()
  {
    return errore;
  }
  public void setErrore(ErrorMessageDTO errore)
  {
    this.errore = errore;
  }
  public String getUploadUUID()
  {
    return uploadUUID;
  }
  public void setUploadUUID(String uploadUUID)
  {
    this.uploadUUID = uploadUUID;
  }
  
 
  

  
}
