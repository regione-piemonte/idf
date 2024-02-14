/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;



public class RespEsitiDoc
{
  
  private DocumentiDTO   input;
  private DocumentiDTO   output;
  private ErrorMessageDTO   esito;
  
  
  public DocumentiDTO getInput()
  {
    return input;
  }
  public void setInput(DocumentiDTO input)
  {
    this.input = input;
  }
  public DocumentiDTO getOutput()
  {
    return output;
  }
  public void setOutput(DocumentiDTO output)
  {
    this.output = output;
  }
  public ErrorMessageDTO getEsito()
  {
    return esito;
  }
  public void setEsito(ErrorMessageDTO esito)
  {
    this.esito = esito;
  }
 
  

  
}
