/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.smrcomms.commws.presentation.rest.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import it.csi.smrcomms.commws.dto.StampeJrWSEsitoOut;

public class TestService
{

  public static void main(String[] args)
  {
    CommwsJasperClient client = new CommwsJasperClient("http://localhost:8080/commws/json/layer/");
    
    long idProcedimento = 64;
    String codiceStampa = "GRAPE_PRATICA";
    
    String json = "{  \"pratica\": { \"prt_protocollo\": \"123456\", \"prt_presentata_da\": \"Giuseppe Peppe\", \"prt_num_domanda\": \"7891011\", \"prt_desc_bando\": \"Dichiarazione di estirpo\", \"quadro_dati_identificativi\": { \"az_cuaa\": \"BNDAGS89E25H792C\", \"az_p_iva\": \"01782694269\", \"az_denominazione\": \"Benedetto Augusto vini grappoli\", \"az_intestazione\": \"Intestata a Benedetto\", \"az_indirizzo\": \"via del piave 28, Torino sud\", \"az_pec\": \"benedetto.augusto@pec.it\", \"az_email\": \"benedetto.augusto@csi.it\", \"az_telefono\": \"0113169795\", \"az_num_registro\": \"00000\", \"az_anno_registro\": \"2008\", \"tit_cognome\": \"Benedetto\", \"tit_nome\": \"Augusto\", \"tit_cf\": \"BNDAGS89E25H792C\", \"tit_telefono\": \"0113169795\", \"tit_email\": \"benedetto.augusto@csi.it\" }, \"quadro_dichiarazioni\": [ { \"dichiarazione\": \"di essere consapevole che il reimpianto andra effettuato entro la campagna in corso\" }, { \"dichiarazione\": \"che la presente domanda sara firmata e archiviata tramite Archivio SIAP o direttamente da un CAA\" }, { \"dichiarazione\": \"di avere sufficienti autorizzazioni al reimpianto\" } ], \"quadro_impegni\": { \"impegno\": \"a non intralciare l'attivita di controllo dei funzionari istruttori\" }, \"quadro_allegati\": { \"allegato\": \"carta identita\" }, \"quadro_autorizzazioni\": [ { \"numero\": \"A0010000004834\", \"tipo\": \"Autorizzazione da conversione diritto\", \"autEstirpo\": \"93716\", \"dataRilascio\": \"26/05/2016\", \"dataScadenza\": \"31/12/2023\", \"dataLimite\": \"31/12/2020\", \"idoneita\": \"\" , \"supIniziale\": \"0,0685\", \"supResidua\": \"0,0585\", \"supAssociata\": \"0,0200\" }, { \"numero\": \"A0010000004834\", \"tipo\": \"Autorizzazione da conversione diritto\", \"autEstirpo\": \"93716\", \"dataRilascio\": \"26/05/2016\", \"dataScadenza\": \"31/12/2023\", \"dataLimite\": \"31/12/2020\", \"idoneita\": \"\", \"supIniziale\": \"0,0685\", \"supResidua\": \"0,0585\", \"supAssociata\": \"0,0200\" } ], \"quadro_diritti\": [ { \"numero\": \"A0010000004834\", \"tipo\": \"Autorizzazione da conversione diritto\", \"autEstirpo\": \"93716\", \"dataOrigine\": \"26/05/2016\", \"dataConferma\": \"31/12/2023\", \"idoneita\": \" - \", \"supIniziale\": \"0,0685\", \"supResidua\": \"0,0585\", \"supAssociata\": \"0,0200\" }, { \"numero\": \"A0010000004834\", \"tipo\": \"Autorizzazione da conversione diritto\", \"autEstirpo\": \"93716\", \"dataOrigine\": \"26/05/2016\", \"dataConferma\": \"31/12/2023\", \"idoneita\": \" - \", \"supIniziale\": \"0,0685\", \"supResidua\": \"0,0585\", \"supAssociata\": \"0,0200\" } ], \"quadro_idoneita\": [ { \"numero\": \"A0010000004834\", \"tipo\": \"Idoneita\", \"domanda\": \"77473\", \"dataRilascio\": \"26/05/2016\", \"dataScadenza\": \"31/12/2023\", \"idoneita\": \" BAROLO \", \"supIniziale\": \"0,0685\", \"supResidua\": \"0,0585\", \"supAssociata\": \"0,0200\" } ], \"quadro_unita_vitate\": [ { \"comune\": \"ALBUGNANO\", \"sezione\": \"A\", \"foglio\": \"8\", \"particella\": \"106\", \"subalterno\": \" - \", \"progressivo\": \" - \", \"destinazione\": \"DA VINO\", \"occupazione\": \"BUSSANELLO B.\", \"uso\": \" - \", \"qualita\": \" - \", \"varieta\": \"BUSSANELLO\", \"tipologia\": \"CALOSSO\", \"dataImpianto\": \"26/05/2016\", \"dataEstirpo\": \"31/12/2018\", \"dataSovrainnesto\": \"31/12/2019\", \"superficie\": \"0,3000\" }, { \"comune\": \"ALBUGNANO\", \"sezione\": \"A\", \"foglio\": \"8\", \"particella\": \"106\", \"subalterno\": \" - \", \"progressivo\": \" - \", \"destinazione\": \"DA VINO\", \"occupazione\": \"BUSSANELLO B.\", \"uso\": \" - \", \"qualita\": \" - \", \"varieta\": \"BUSSANELLO\", \"tipologia\": \"CALOSSO\", \"dataImpianto\": \"26/05/2016\", \"dataEstirpo\": \"31/12/2018\", \"dataSovrainnesto\": \"31/12/2019\", \"superficie\": \"0,3000\" } ], \"quadro_firma\": [ { } ]  } }";
    
    StampeJrWSEsitoOut out = client.generaStampa(idProcedimento, codiceStampa, json);
    
    File fileImage = new File("K:\\STAMPE\\Results\\"+ "res"+(( new Date() )).getTime()+".pdf");
    try
    {
    if (!fileImage.exists())
    {
      fileImage.createNewFile();
    }
    FileOutputStream fop = new FileOutputStream(fileImage);
    fop.write(out.getFileReport());
    fop.flush();
      fop.close();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
