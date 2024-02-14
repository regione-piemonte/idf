/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import com.google.gson.annotations.SerializedName;

public class MetadatiVincIdroDTO
{
  
  private String   id;
  private Long     nr_pratica;
  private String   data_invio_pratica;
  private Integer  anno_pratica;
  private String   codice_fiscale_richiedente;
  private String   PG_denominazione;
  private String   PG_partita_iva;
  private String   PG_indirizzo_telematico;
  private String   PF_cognome;
  private String   PF_nome;
  private String   PF_indirizzo_telematico;
  private String   sigla_provincia;
  private String   string_comune;
  private String   oggetto_lavori;
  private String   soggetto_gestore;  
  
  public String getId()
  {
    return id;
  }
  public void setId(String id)
  {
    this.id = id;
  }
  public Long getNr_pratica()
  {
    return nr_pratica;
  }
  public void setNr_pratica(Long nr_pratica)
  {
    this.nr_pratica = nr_pratica;
  }
  public String getData_invio_pratica()
  {
    return data_invio_pratica;
  }
  public void setData_invio_pratica(String data_invio_pratica)
  {
    this.data_invio_pratica = data_invio_pratica;
  }
  public Integer getAnno_pratica()
  {
    return anno_pratica;
  }
  public void setAnno_pratica(Integer anno_pratica)
  {
    this.anno_pratica = anno_pratica;
  }
  public String getCodice_fiscale_richiedente()
  {
    return codice_fiscale_richiedente;
  }
  public void setCodice_fiscale_richiedente(String codice_fiscale_richiedente)
  {
    this.codice_fiscale_richiedente = codice_fiscale_richiedente;
  }
  @JsonProperty("PG_denominazione")
  public String getPG_denominazione()
  {
    return PG_denominazione;
  }
  public void setPG_denominazione(String pG_denominazione)
  {
    PG_denominazione = pG_denominazione;
  }
  @JsonProperty("PG_partita_iva")
  public String getPG_partita_iva()
  {
    return PG_partita_iva;
  }
  public void setPG_partita_iva(String pG_partita_iva)
  {
    PG_partita_iva = pG_partita_iva;
  }
  @JsonProperty("PG_indirizzo_telematico")
  public String getPG_indirizzo_telematico()
  {
    return PG_indirizzo_telematico;
  }
  public void setPG_indirizzo_telematico(String pG_indirizzo_telematico)
  {
    PG_indirizzo_telematico = pG_indirizzo_telematico;
  }
  @JsonProperty("PF_cognome")
  public String getPF_cognome()
  {
    return PF_cognome;
  }
  public void setPF_cognome(String pF_cognome)
  {
    PF_cognome = pF_cognome;
  }
  @JsonProperty("PF_nome")
  public String getPF_nome()
  {
    return PF_nome;
  }
  public void setPF_nome(String pF_nome)
  {
    PF_nome = pF_nome;
  }
  @JsonProperty("PF_indirizzo_telematico")
  public String getPF_indirizzo_telematico()
  {
    return PF_indirizzo_telematico;
  }
  public void setPF_indirizzo_telematico(String pF_indirizzo_telematico)
  {
    PF_indirizzo_telematico = pF_indirizzo_telematico;
  }
  public String getSigla_provincia()
  {
    return sigla_provincia;
  }
  public void setSigla_provincia(String sigla_provincia)
  {
    this.sigla_provincia = sigla_provincia;
  }
  public String getString_comune()
  {
    return string_comune;
  }
  public void setString_comune(String string_comune)
  {
    this.string_comune = string_comune;
  }
  public String getOggetto_lavori()
  {
    return oggetto_lavori;
  }
  public void setOggetto_lavori(String oggetto_lavori)
  {
    this.oggetto_lavori = oggetto_lavori;
  }
  public String getSoggetto_gestore()
  {
    return soggetto_gestore;
  }
  public void setSoggetto_gestore(String soggetto_gestore)
  {
    this.soggetto_gestore = soggetto_gestore;
  }
  
  
  
  
}
