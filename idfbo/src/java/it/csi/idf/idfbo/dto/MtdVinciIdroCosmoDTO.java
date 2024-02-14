/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;

import java.util.Date;

public class MtdVinciIdroCosmoDTO
{
  
  
  private long       idPratica;
  private long       numeroPratica;
  private Date       dataInvio;
  private Integer     annoPratica;
  private String     codiceFiscaleRichiedente;
  private String     ragioneSociale;
  private String     partitaIvaRichiedente;
  private String     indirizzoPecRichiedente;
  private String     cognomeRichiedente;
  private String     nomeRichiedente;
  private String     indirizzoMailPersonaFisica;
  private String     siglaProvincia;
  private String     comune;
  private String     oggettoLavori;
  private String     soggettoGestore;
  
  
  public long getIdPratica()
  {
    return idPratica;
  }
  public void setIdPratica(long idPratica)
  {
    this.idPratica = idPratica;
  }
  public long getNumeroPratica()
  {
    return numeroPratica;
  }
  public void setNumeroPratica(long numeroPratica)
  {
    this.numeroPratica = numeroPratica;
  }
  public Date getDataInvio()
  {
    return dataInvio;
  }
  public void setDataInvio(Date dataInvio)
  {
    this.dataInvio = dataInvio;
  }
  public Integer getAnnoPratica()
  {
    return annoPratica;
  }
  public void setAnnoPratica(Integer annoPratica)
  {
    this.annoPratica = annoPratica;
  }
  public String getCodiceFiscaleRichiedente()
  {
    return codiceFiscaleRichiedente;
  }
  public void setCodiceFiscaleRichiedente(String codiceFiscaleRichiedente)
  {
    this.codiceFiscaleRichiedente = codiceFiscaleRichiedente;
  }
  public String getRagioneSociale()
  {
    return ragioneSociale;
  }
  public void setRagioneSociale(String ragioneSociale)
  {
    this.ragioneSociale = ragioneSociale;
  }  
  public String getPartitaIvaRichiedente()
  {
    return partitaIvaRichiedente;
  }
  public void setPartitaIvaRichiedente(String partitaIvaRichiedente)
  {
    this.partitaIvaRichiedente = partitaIvaRichiedente;
  }
  public String getIndirizzoPecRichiedente()
  {
    return indirizzoPecRichiedente;
  }
  public void setIndirizzoPecRichiedente(String indirizzoPecRichiedente)
  {
    this.indirizzoPecRichiedente = indirizzoPecRichiedente;
  }
  public String getCognomeRichiedente()
  {
    return cognomeRichiedente;
  }
  public void setCognomeRichiedente(String cognomeRichiedente)
  {
    this.cognomeRichiedente = cognomeRichiedente;
  }
  public String getNomeRichiedente()
  {
    return nomeRichiedente;
  }
  public void setNomeRichiedente(String nomeRichiedente)
  {
    this.nomeRichiedente = nomeRichiedente;
  }
  public String getIndirizzoMailPersonaFisica()
  {
    return indirizzoMailPersonaFisica;
  }
  public void setIndirizzoMailPersonaFisica(String indirizzoMailPersonaFisica)
  {
    this.indirizzoMailPersonaFisica = indirizzoMailPersonaFisica;
  }
  public String getSiglaProvincia()
  {
    return siglaProvincia;
  }
  public void setSiglaProvincia(String siglaProvincia)
  {
    this.siglaProvincia = siglaProvincia;
  }
  public String getComune()
  {
    return comune;
  }
  public void setComune(String comune)
  {
    this.comune = comune;
  }
  public String getOggettoLavori()
  {
    return oggettoLavori;
  }
  public void setOggettoLavori(String oggettoLavori)
  {
    this.oggettoLavori = oggettoLavori;
  }
  public String getSoggettoGestore()
  {
    return soggettoGestore;
  }
  public void setSoggettoGestore(String soggettoGestore)
  {
    this.soggettoGestore = soggettoGestore;
  }
  
  
  
  
}
