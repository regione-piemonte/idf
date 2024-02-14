/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;



public class MtdTrasfCosmoDTO
{
  
  
  private long       id;
  private long       nrIstanzaForestale;
  private String     intestatario;
  private String     cognomeDichiarante;
  private String     nomeDichiarante;
  private String     ragioneSociale;
  private String     soggettoGestore;
  private String     siglaProvincia;
  private String     paroleChiaveFascicolo;
  private String     oggettoFascicolo; 
  
  
  public long getId()
  {
    return id;
  }
  public void setId(long id)
  {
    this.id = id;
  }
  public long getNrIstanzaForestale()
  {
    return nrIstanzaForestale;
  }
  public void setNrIstanzaForestale(long nrIstanzaForestale)
  {
    this.nrIstanzaForestale = nrIstanzaForestale;
  }
  public String getIntestatario()
  {
    return intestatario;
  }
  public void setIntestatario(String intestatario)
  {
    this.intestatario = intestatario;
  } 
  public String getRagioneSociale()
  {
    return ragioneSociale;
  }
  public void setRagioneSociale(String ragioneSociale)
  {
    this.ragioneSociale = ragioneSociale;
  }  
  public String getSoggettoGestore()
  {
    return soggettoGestore;
  }
  public void setSoggettoGestore(String soggettoGestore)
  {
    this.soggettoGestore = soggettoGestore;
  }  
  public String getSiglaProvincia()
  {
    return siglaProvincia;
  }
  public void setSiglaProvincia(String siglaProvincia)
  {
    this.siglaProvincia = siglaProvincia;
  }
  public String getCognomeDichiarante()
  {
    return cognomeDichiarante;
  }
  public void setCognomeDichiarante(String cognomeDichiarante)
  {
    this.cognomeDichiarante = cognomeDichiarante;
  }
  public String getNomeDichiarante()
  {
    return nomeDichiarante;
  }
  public void setNomeDichiarante(String nomeDichiarante)
  {
    this.nomeDichiarante = nomeDichiarante;
  }
  public String getParoleChiaveFascicolo()
  {
    return paroleChiaveFascicolo;
  }
  public void setParoleChiaveFascicolo(String paroleChiaveFascicolo)
  {
    this.paroleChiaveFascicolo = paroleChiaveFascicolo;
  }
  public String getOggettoFascicolo()
  {
    return oggettoFascicolo;
  }
  public void setOggettoFascicolo(String oggettoFascicolo)
  {
    this.oggettoFascicolo = oggettoFascicolo;
  }
  
  
  
  
  
  
}
