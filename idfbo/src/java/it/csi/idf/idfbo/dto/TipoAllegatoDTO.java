/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

public class TipoAllegatoDTO
{
  private long       idTipoAllegato;

  private String     descrTipoAllegato;
  private int     fkOrigineAllegato;
  private Integer     mtdOrdinamento;
  private int     flgVisibile;
  
  
  public long getIdTipoAllegato()
  {
    return idTipoAllegato;
  }
  public void setIdTipoAllegato(long idTipoAllegato)
  {
    this.idTipoAllegato = idTipoAllegato;
  }
  public String getDescrTipoAllegato()
  {
    return descrTipoAllegato;
  }
  public void setDescrTipoAllegato(String descrTipoAllegato)
  {
    this.descrTipoAllegato = descrTipoAllegato;
  }
  public int getFkOrigineAllegato()
  {
    return fkOrigineAllegato;
  }
  public void setFkOrigineAllegato(int fkOrigineAllegato)
  {
    this.fkOrigineAllegato = fkOrigineAllegato;
  }
  public Integer getMtdOrdinamento()
  {
    return mtdOrdinamento;
  }
  public void setMtdOrdinamento(Integer mtdOrdinamento)
  {
    this.mtdOrdinamento = mtdOrdinamento;
  }
  public int getFlgVisibile()
  {
    return flgVisibile;
  }
  public void setFlgVisibile(int flgVisibile)
  {
    this.flgVisibile = flgVisibile;
  }
  
  
  
  
}
