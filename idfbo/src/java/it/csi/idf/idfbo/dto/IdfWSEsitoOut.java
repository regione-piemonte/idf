/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IdfWSEsitoOut
{
  private String       esito;
  private String       descrizioneEsito;
  private TipoAllegatoDTO       tipoAllegato;
  
  
  public String getEsito()
  {
    return esito;
  }
  public void setEsito(String esito)
  {
    this.esito = esito;
  }
  public String getDescrizioneEsito()
  {
    return descrizioneEsito;
  }
  public void setDescrizioneEsito(String descrizioneEsito)
  {
    this.descrizioneEsito = descrizioneEsito;
  }
  public TipoAllegatoDTO getTipoAllegato()
  {
    return tipoAllegato;
  }
  public void setTipoAllegato(TipoAllegatoDTO tipoAllegato)
  {
    this.tipoAllegato = tipoAllegato;
  }
 
  
  

}
