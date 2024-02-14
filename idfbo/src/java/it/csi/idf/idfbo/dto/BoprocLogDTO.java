/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import java.util.Date;

public class BoprocLogDTO
{
  
  
  private long       idCnfBoprocLog;
  private String       fkIstanza;
  private Date       dataInizio; 
  
  
  public long getIdCnfBoprocLog()
  {
    return idCnfBoprocLog;
  }
  public void setIdCnfBoprocLog(long idCnfBoprocLog)
  {
    this.idCnfBoprocLog = idCnfBoprocLog;
  }
  public String getFkIstanza()
  {
    return fkIstanza;
  }
  public void setFkIstanza(String fkIstanza)
  {
    this.fkIstanza = fkIstanza;
  }
  public Date getDataInizio()
  {
    return dataInizio;
  }
  public void setDataInizio(Date dataInizio)
  {
    this.dataInizio = dataInizio;
  }
  

  
  
  
  
  
  
  
  
}
