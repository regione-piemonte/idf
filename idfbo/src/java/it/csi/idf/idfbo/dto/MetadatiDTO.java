/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;



public class MetadatiDTO
{
  
  private String id;
  private String   nr_istanza_forestale;
  private String   data_invio;
  private String   intestatario;
  private String   soggetto_gestore;
  private String   ragione_sociale;
  private String   cognome_dichiarante;
  private String   nome_dichiarante;
  private String   sigla_provincia;
  private String   parole_chiave_fascicolo;
  private String   oggetto_fascicolo;
  
  
  
  
  public String getId()
  {
    return id;
  }
  public void setId(String id)
  {
    this.id = id;
  }
  public String getNr_istanza_forestale()
  {
    return nr_istanza_forestale;
  }
  public void setNr_istanza_forestale(String nr_istanza_forestale)
  {
    this.nr_istanza_forestale = nr_istanza_forestale;
  }
  public String getData_invio()
  {
    return data_invio;
  }
  public void setData_invio(String data_invio)
  {
    this.data_invio = data_invio;
  }  
  public String getIntestatario()
  {
    return intestatario;
  }
  public void setIntestatario(String intestatario)
  {
    this.intestatario = intestatario;
  }  
  public String getSoggetto_gestore()
  {
    return soggetto_gestore;
  }
  public void setSoggetto_gestore(String soggetto_gestore)
  {
    this.soggetto_gestore = soggetto_gestore;
  }
  public String getRagione_sociale()
  {
    return ragione_sociale;
  }
  public void setRagione_sociale(String ragione_sociale)
  {
    this.ragione_sociale = ragione_sociale;
  }    
  public String getSigla_provincia()
  {
    return sigla_provincia;
  }
  public void setSigla_provincia(String sigla_provincia)
  {
    this.sigla_provincia = sigla_provincia;
  }
  public String getCognome_dichiarante()
  {
    return cognome_dichiarante;
  }
  public void setCognome_dichiarante(String cognome_dichiarante)
  {
    this.cognome_dichiarante = cognome_dichiarante;
  }
  public String getNome_dichiarante()
  {
    return nome_dichiarante;
  }
  public void setNome_dichiarante(String nome_dichiarante)
  {
    this.nome_dichiarante = nome_dichiarante;
  }
  public String getParole_chiave_fascicolo()
  {
    return parole_chiave_fascicolo;
  }
  public void setParole_chiave_fascicolo(String parole_chiave_fascicolo)
  {
    this.parole_chiave_fascicolo = parole_chiave_fascicolo;
  }
  public String getOggetto_fascicolo()
  {
    return oggetto_fascicolo;
  }
  public void setOggetto_fascicolo(String oggetto_fascicolo)
  {
    this.oggetto_fascicolo = oggetto_fascicolo;
  }
  

  
}
