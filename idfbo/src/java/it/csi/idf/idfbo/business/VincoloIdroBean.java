/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfbo.business.service.helper.IndexServiceHelper;
import it.csi.idf.idfbo.business.service.helper.MailServiceHelper;
import it.csi.idf.idfbo.business.service.helper.factory.DettaglioEmailFactory;
import it.csi.idf.idfbo.dto.AllegatoDTO;
import it.csi.idf.idfbo.dto.ApiManagerDto;
import it.csi.idf.idfbo.dto.BoprocLogDTO;
import it.csi.idf.idfbo.dto.DocumentiDTO;
import it.csi.idf.idfbo.dto.DocumentoDTO;
import it.csi.idf.idfbo.dto.IstanzaInfoMailByIstanzaDto;
import it.csi.idf.idfbo.dto.MailConfigDto;
import it.csi.idf.idfbo.dto.MailConfigPk;
import it.csi.idf.idfbo.dto.MetadatiDTO;
import it.csi.idf.idfbo.dto.MetadatiVincIdroDTO;
import it.csi.idf.idfbo.dto.MtdTrasfCosmoDTO;
import it.csi.idf.idfbo.dto.MtdVinciIdroCosmoDTO;
import it.csi.idf.idfbo.dto.PraticaDTO;
import it.csi.idf.idfbo.dto.ProcessoDTO;
import it.csi.idf.idfbo.dto.ResponseDTO;
import it.csi.idf.idfbo.dto.TipoAllegatoDTO;
import it.csi.idf.idfbo.dto.TipoMailDto;
import it.csi.idf.idfbo.dto.TipoMailPk;
import it.csi.idf.idfbo.exception.ServiceException;
import it.csi.idf.idfbo.integration.ConfigDAO;
import it.csi.idf.idfbo.integration.IstanzaDAO;
import it.csi.idf.idfbo.util.CallRestJsonUtils;
import it.csi.idf.idfbo.util.Constants;
import it.csi.idf.idfbo.util.IdfBoUtils;

@Component("vincoloIdroBean")
@Transactional
public class VincoloIdroBean
{
  protected static final Logger logger     = Logger
      .getLogger(Constants.LOGGING.LOGGER_NAME + ".business");
  protected static final String THIS_CLASS = IstanzaBean.class
      .getSimpleName();

  @Autowired
  private IstanzaDAO             istanzaDAO;
  
  @Autowired 
  private ConfigDAO configDAO;
  
  @Autowired
  private IndexServiceHelper indexServiceHelper;
  
  @Autowired
  private MailServiceHelper mailServiceHelper;

 
  public TipoAllegatoDTO getTipoAllegato()
      throws Exception
  {
    
    
    ResourceBundle res = ResourceBundle.getBundle("config");

    String urlCosmo = res.getString(Constants.URL.URL_COSMO_PRATICA);        
    
    
    
    
    final String THIS_METHOD = "getTipoAllegato";
    if (logger.isDebugEnabled())
    {
      logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
    }
    try
    {

      TipoAllegatoDTO tipo = istanzaDAO
          .getTipoAllegato();

      

      return tipo;
    }
    finally
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] END.");
      }
    }
  }
  
  public List<BoprocLogDTO> getElencoIstanze(int step, int ambitoIstanza)
      throws Exception
  {
    
    
    //ResourceBundle res = ResourceBundle.getBundle("config");

    //String urlCosmo = res.getString(Constants.URL.URL_COSMO);        
    
    
    
    
    final String THIS_METHOD = "getElencoIstanze VINCOLO ";
    if (logger.isDebugEnabled())
    {
      logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
    }
    try
    {

      List<BoprocLogDTO> lIstanze = istanzaDAO.getElencoIstanze(step, Constants.PARAMETER.NUM_TENTATIVI, ambitoIstanza);
      
      if(lIstanze != null)
      {
        logger.info(THIS_METHOD+ "RESULT size??? Step "+step+" "+lIstanze.size());
      }
      else
      {
        logger.info(THIS_METHOD+ "RESULT size??? Step "+step+" NULL");
      }
      
      return lIstanze;
    }
    finally
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] END.");
      }
    }
  }
  
  public String getValueParameter(String param, int ambitoIstanza)
      throws Exception
  {
    
    
    //ResourceBundle res = ResourceBundle.getBundle("config");

    //String urlCosmo = res.getString(Constants.URL.URL_COSMO);        
    
    
    
    
    final String THIS_METHOD = "getValueParameter";
    if (logger.isDebugEnabled())
    {
      logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
    }
    try
    {

      String str = istanzaDAO.getValueParameter(param, ambitoIstanza);    

      return str;
    }
    finally
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] END.");
      }
    }
  }
  
  public void provaInsertJason()
      throws Exception
  {
    
    
    //ResourceBundle res = ResourceBundle.getBundle("config");
    //String urlCosmo = res.getString(Constants.PARAMETER.URL_COSMO);
    
    
    
    
   
    final String THIS_METHOD = "provaInsertJason";
    if (logger.isDebugEnabled())
    {
      logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
    }
    try
    {

      String json = "{\"idPratica\":\"foreste_24_02_2022_6\",\"codiceIpaEnte\":\"grp_amb\",\"codiceTipologia\":\"IST_FOR_TRASF\",\"oggetto\":\"foreste_24_02_2022_6\",\"riassunto\":\"Processo del 24 02 2022\"";
      
      istanzaDAO.updateBoprocLog(Long.valueOf(31).toString(), 0, null, null, json);
      
    }
    catch(Exception ex)
    {
      String erroreStr = "Problemi con la gestione della pratica step 1: "+ex.getMessage();
      
      logger.error(erroreStr);
    }   
    finally
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] END.");
      }
    }
  }
  
  
  
  public void inviaPraticaStep1(BoprocLogDTO istanza, String codiceIpaEnte, String codiceTipologia,
      String strOggetto)
      throws Exception
  {
    
    
    //ResourceBundle res = ResourceBundle.getBundle("config");
    //String urlCosmo = res.getString(Constants.PARAMETER.URL_COSMO);
    
       
    String jsonInputString = null;
    final String THIS_METHOD = "inviaPraticaStep1";
    if (logger.isDebugEnabled())
    {
      logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
    }
    try
    {

      CallRestJsonUtils call = new CallRestJsonUtils();
      ObjectMapper om = new ObjectMapper();         
      
      String fkIstanzaNum = IdfBoUtils.FORMAT.getFkIstanza(istanza.getFkIstanza());  
      PraticaDTO pratica = new PraticaDTO();      
      pratica.setIdPratica(istanza.getFkIstanza());
      String oggetto = strOggetto+""+IdfBoUtils.DATE.formatDateUnderscore(new Date())+"_"+istanza.getFkIstanza();
      pratica.setOggetto(oggetto);
      pratica.setCodiceIpaEnte(codiceIpaEnte);
      pratica.setCodiceTipologia(codiceTipologia);
      //pratica.setUtenteCreazionePratica("AAAAAA00B77B000F");
      pratica.setRiassunto(fkIstanzaNum);
      
      MtdVinciIdroCosmoDTO mtd = istanzaDAO.getDatiTrasfCosmoVinvoloIdro(Long.valueOf(fkIstanzaNum));
      MetadatiVincIdroDTO metadati = new MetadatiVincIdroDTO();
      metadati.setId(istanza.getFkIstanza());
      metadati.setNr_pratica(mtd.getNumeroPratica());
      metadati.setData_invio_pratica(IdfBoUtils.DATE.formatDate(mtd.getDataInvio()));
      metadati.setAnno_pratica(mtd.getAnnoPratica());
      metadati.setCodice_fiscale_richiedente(mtd.getCodiceFiscaleRichiedente());
      metadati.setPG_denominazione(mtd.getRagioneSociale());
      metadati.setPG_partita_iva(mtd.getPartitaIvaRichiedente());
      metadati.setPG_indirizzo_telematico(mtd.getIndirizzoPecRichiedente());
      metadati.setPF_cognome(mtd.getCognomeRichiedente());
      metadati.setPF_nome(mtd.getNomeRichiedente());
      metadati.setPF_indirizzo_telematico(mtd.getIndirizzoMailPersonaFisica());
      metadati.setSigla_provincia(mtd.getSiglaProvincia());
      metadati.setString_comune(mtd.getComune());
      metadati.setSoggetto_gestore(mtd.getSoggettoGestore());       
      metadati.setOggetto_lavori(mtd.getOggettoLavori());
      
      pratica.setMetadati(om.writeValueAsString(metadati));
  
      jsonInputString = om.writeValueAsString(pratica);   
      logger.info("json step 1: "+jsonInputString);
      ResourceBundle res = ResourceBundle.getBundle("config");

      String urlCosmo = res.getString(Constants.URL.URL_COSMO_PRATICA);
      Thread.sleep(2000);
      ApiManagerDto apiManager = configDAO.getInfoApiManagerInternet();
      ResponseDTO respDTO = call.sendPost(urlCosmo, apiManager, jsonInputString);
      if(respDTO == null || (respDTO !=	null && respDTO.getErrore() != null))
      {
        Long countErrore = istanzaDAO.getCountErrore(istanza.getFkIstanza());
        int countErr = countErrore.intValue()+1;
        if(respDTO != null)
        {
          istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErr, 
            new Integer(respDTO.getErrore().getStatus()), respDTO.getErrore().getTitle(), jsonInputString);
        }
        else
        {
          istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErr, 
             Constants.ERRORS.COD_ERRORE_INTERNO , "step1 - Oggetto riposta null!!!!", jsonInputString);
        }
        
      }
      else
      {
        istanzaDAO.storicizzaBoprocLog(istanza.getFkIstanza(), jsonInputString);
        istanzaDAO.insertBoProcLog(istanza.getFkIstanza(), Constants.STEP.STEP_2, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
      }
      
      
      //{"idPratica":"foreste_24_02_2022_6","codiceIpaEnte":"grp_amb","codiceTipologia":"IST_FOR_TRASF","oggetto":"foreste_24_02_2022_6","riassunto":"Processo del 24 02 2022"}
      
      
      //*******************************
    }
    catch(Exception ex)
    {
      String erroreStr = "Problemi con la gestione della pratica step 1:"+istanza.getFkIstanza()+" - "+ex.getMessage();
      if(erroreStr.length() > 1999)
        erroreStr = erroreStr.substring(0, 1999);
      Long countErrore = istanzaDAO.getCountErrore(istanza.getFkIstanza());
      istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErrore.intValue()+1, 
          Constants.ERRORS.COD_ERRORE_INTERNO, erroreStr, jsonInputString);
      logger.error(erroreStr);
    }   
    finally
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] END.");
      }
    }
  }
  
  public void inviaPraticaStep2(BoprocLogDTO istanza)
      throws Exception
  {
    
    
    //ResourceBundle res = ResourceBundle.getBundle("config");
    //String urlCosmo = res.getString(Constants.PARAMETER.URL_COSMO);
    
    
    
    
    
    final String THIS_METHOD = "inviaPraticaStep2";
    if (logger.isDebugEnabled())
    {
      logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
    }
    try
    {

      CallRestJsonUtils call = new CallRestJsonUtils();
      //ObjectMapper om = new ObjectMapper();
      //String codiceIpaEnte = istanzaDAO.getValueParameter(Constants.PARAMETER.CODICEIPAENTE_COSMO);
      //String codiceTipologia = istanzaDAO.getValueParameter(Constants.PARAMETER.CODICETIPOLOGIA_COSMO);
      
      
      
      //step 2
      
      
       String idIstanza = IdfBoUtils.FORMAT.getFkIstanza(istanza.getFkIstanza());
       List<AllegatoDTO> lAllegati = istanzaDAO.getElencoAllegatiVincIdro(Long.valueOf(idIstanza), Constants.PARAMETER.DA_INVIARE_COSMO);
       boolean erroreAllegato = false;
       if(lAllegati != null && lAllegati.size() > 0)
       {
         for(int j=0;j<lAllegati.size();j++)
         {
           if(!erroreAllegato)
           {
             //** CHIAMATA AD INDEX **/
             
             byte[] fileIndex = this.indexServiceHelper.indexDowloadFileByUid(lAllegati.get(j).getUidIndex());   
             
             // FINE CHIAMATA INDEX
             
             ResourceBundle res = ResourceBundle.getBundle("config");

             String urlCosmo = res.getString(Constants.URL.URL_COSMO_UPLOAD);
             Thread.sleep(2000);
             ApiManagerDto apiManager = configDAO.getInfoApiManagerInternet();
             ResponseDTO respDTO = call.sendFilePost(urlCosmo, apiManager, fileIndex, lAllegati.get(j).getTitolo());
             if(respDTO == null)
             {
               Long countErrore = istanzaDAO.getCountErrore(istanza.getFkIstanza());
               int countErr = countErrore.intValue()+1;
               istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErr, 
                 Constants.ERRORS.COD_ERRORE_INTERNO , "Oggetto riposta null!!!!", null);                   
               
               erroreAllegato = true;
               break;
             }
             else
             {
               if(respDTO.getErrore() != null)
               {
                 Long countErrore = istanzaDAO.getCountErrore(istanza.getFkIstanza());
                 int countErr = countErrore.intValue()+1;
                 istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErr, 
                   new Integer(respDTO.getErrore().getStatus()), "id_documento_allegato - "+lAllegati.get(j).getId()+": "+respDTO.getErrore().getTitle(), null);
                                 
                 erroreAllegato = true;
                 break;
               }
               else
               {
                 if(respDTO.getUploadUUID() != null  && !respDTO.getUploadUUID().equals(""))
                 {
                   istanzaDAO.updateDocumentoAllegato(lAllegati.get(j).getId(), respDTO.getUploadUUID());
                   //resetto nel caso uno fosse andato male in qualche giro precedente
                   istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), 0, null,null, null);
                   
                 }
                 else
                 {
                   Long countErrore = istanzaDAO.getCountErrore(istanza.getFkIstanza());
                   int countErr = countErrore.intValue()+1;
                   istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErr, 
                       Constants.ERRORS.COD_ERRORE_INTERNO , "UploadUUID  null", null);
                   erroreAllegato = true;
                   break;
                 }
               }
             }
           }
         }
         
         if(!erroreAllegato)
         {
           istanzaDAO.storicizzaBoprocLog(istanza.getFkIstanza(), null);
           istanzaDAO.insertBoProcLog(istanza.getFkIstanza(), Constants.STEP.STEP_3, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
         }
       }
       else
       {
         istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), 1, 
             Constants.ERRORS.COD_ERRORE_INTERNO, "Non ci sono allegati", null);
       }
    }
    catch(Exception ex)
    {
      String erroreStr = "Problemi con la gestione della pratica step 2:"+istanza.getFkIstanza()+" - "+ex.getMessage();
      if(erroreStr.length() > 1999)
        erroreStr = erroreStr.substring(0, 1999);
      Long countErrore = istanzaDAO.getCountErrore(istanza.getFkIstanza());
      istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErrore.intValue()+1, 
          Constants.ERRORS.COD_ERRORE_INTERNO, erroreStr, null);
      logger.error(erroreStr);
    }     
    finally
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] END.");
      }
    }
  }
  
  public void inviaPraticaStep3(BoprocLogDTO istanza, String codiceIpaEnte)
      throws Exception
  {
    
    
    //ResourceBundle res = ResourceBundle.getBundle("config");
    //String urlCosmo = res.getString(Constants.PARAMETER.URL_COSMO);
    
    
    
    String jsonInputString = null;
    
    final String THIS_METHOD = "inviaPraticaStep3";
    if (logger.isDebugEnabled())
    {
      logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
    }
    try
    {

      CallRestJsonUtils call = new CallRestJsonUtils();
      ObjectMapper om = new ObjectMapper();
      //String codiceIpaEnte = istanzaDAO.getValueParameter(Constants.PARAMETER.CODICEIPAENTE_COSMO);
      //String codiceTipologia = istanzaDAO.getValueParameter(Constants.PARAMETER.CODICETIPOLOGIA_COSMO);
      
      
      
      //step 3
      
      String idIstanza = IdfBoUtils.FORMAT.getFkIstanza(istanza.getFkIstanza());
      DocumentoDTO documento = new DocumentoDTO();
      documento.setIdPratica(istanza.getFkIstanza());
      documento.setCodiceIpaEnte(codiceIpaEnte);
      
      List<DocumentiDTO> lDocumenti = new ArrayList<>();
      List<AllegatoDTO> lAllegati = istanzaDAO.getElencoAllegatiVincIdro(Long.valueOf(idIstanza), Constants.PARAMETER.INVIATO_COSMO_FILE);
      if(lAllegati != null && lAllegati.size() > 0)
      {
        for(int j=0;j<lAllegati.size();j++)
        {   
      
          DocumentiDTO doc = new DocumentiDTO();
          doc.setId(""+lAllegati.get(j).getId());
          if(lAllegati.get(j).getIdPadre() != null)
            doc.setIdPadre(""+lAllegati.get(j).getIdPadre());
          doc.setCodiceTipo(""+lAllegati.get(j).getCodiceCosmo());
          doc.setTitolo(lAllegati.get(j).getTitolo());
          doc.setDescrizione(lAllegati.get(j).getDescrizione());
          MtdVinciIdroCosmoDTO mtd = istanzaDAO.getDatiTrasfCosmoVinvoloIdro(Long.valueOf(idIstanza));
          doc.setAutore(mtd.getCodiceFiscaleRichiedente());
          doc.setUploadUUID(lAllegati.get(j).getUploaduuidCosmo());
          lDocumenti.add(doc);
        }
      
        documento.setDocumenti(lDocumenti);
        jsonInputString = om.writeValueAsString(documento);    
        logger.debug("json step 3: "+jsonInputString);
    //    ritornoJson = call.sendPostDocumento(Constants.URL.URL_COSMO_DOCUMENTI, jsonInputString);
        ResourceBundle res = ResourceBundle.getBundle("config");

        String urlCosmo = res.getString(Constants.URL.URL_COSMO_DOCUMENTI);
        
        Thread.sleep(2000);
        ApiManagerDto apiManager = configDAO.getInfoApiManagerInternet();
        ResponseDTO respDTO = call.sendPostDocumento(urlCosmo, apiManager, jsonInputString);
        if(respDTO == null || (respDTO != null && respDTO.getErrore() != null))
        {
          Long countErrore = istanzaDAO.getCountErrore(istanza.getFkIstanza());
          int countErr = countErrore.intValue()+1;
          if(respDTO != null)
          {
            istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErr, 
              new Integer(respDTO.getErrore().getStatus()), respDTO.getErrore().getTitle(), jsonInputString);
          }
          else
          {
            istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErr, 
               Constants.ERRORS.COD_ERRORE_INTERNO , "Oggetto riposta null!!!!", jsonInputString);
          }
          
        }
        else
        {
          //aggiorno stato allegati....
          if(lAllegati != null && lAllegati.size() > 0)
          {
            for(int j=0;j<lAllegati.size();j++)
            { 
              istanzaDAO.updateStatoDocumentoAllegato(lAllegati.get(j).getId(), Constants.PARAMETER.INVIATO_COSMO_PRATICA);
            }
          }
          istanzaDAO.storicizzaBoprocLog(istanza.getFkIstanza(), jsonInputString);
          istanzaDAO.insertBoProcLog(istanza.getFkIstanza(), Constants.STEP.STEP_4, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
        }
      }
      else
      {
        istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), 1, 
            Constants.ERRORS.COD_ERRORE_INTERNO, "Non ci sono allegati", jsonInputString);
      }
    }
    catch(Exception ex)
    {
      String erroreStr = "Problemi con la gestione della pratica step 3:"+istanza.getFkIstanza()+" - "+ex.getMessage();
      if(erroreStr.length() > 1999)
        erroreStr = erroreStr.substring(0, 1999);
      Long countErrore = istanzaDAO.getCountErrore(istanza.getFkIstanza());
      istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErrore.intValue()+1, 
          Constants.ERRORS.COD_ERRORE_INTERNO, erroreStr, jsonInputString);
      logger.error(erroreStr);
    }       
    finally
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] END.");
      }
    }
  }
  
  public void inviaPraticaStep4(BoprocLogDTO istanza, String codiceIpaEnte)
      throws Exception
  {
    
    
    //ResourceBundle res = ResourceBundle.getBundle("config");
    //String urlCosmo = res.getString(Constants.PARAMETER.URL_COSMO);
    
    
    
    String jsonInputString = null;
    
    final String THIS_METHOD = "inviaPraticaStep4";
    if (logger.isDebugEnabled())
    {
      logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] BEGIN.");
    }
    try
    {

      CallRestJsonUtils call = new CallRestJsonUtils();
      ObjectMapper om = new ObjectMapper();
      //String codiceIpaEnte = istanzaDAO.getValueParameter(Constants.PARAMETER.CODICEIPAENTE_COSMO);
      //String codiceTipologia = istanzaDAO.getValueParameter(Constants.PARAMETER.CODICETIPOLOGIA_COSMO);
      
      
      
      
      
      ProcessoDTO processo = new ProcessoDTO();
      processo.setIdPratica(istanza.getFkIstanza());
      processo.setCodiceIpaEnte(codiceIpaEnte);
      
      
      jsonInputString = om.writeValueAsString(processo); 
      logger.debug("json step 4: "+jsonInputString);
      
      ResourceBundle res = ResourceBundle.getBundle("config");

      String urlCosmo = res.getString(Constants.URL.URL_COSMO_PROCESSO);
      Thread.sleep(2000);
      ApiManagerDto apiManager = configDAO.getInfoApiManagerInternet();
      ResponseDTO respDTO = call.sendPost(urlCosmo, apiManager, jsonInputString);
      
      if(respDTO == null || (respDTO!= null && respDTO.getErrore() != null))
      {
        Long countErrore = istanzaDAO.getCountErrore(istanza.getFkIstanza());
        int countErr = countErrore.intValue()+1;
        if(respDTO != null)
        {
          istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErr, 
            new Integer(respDTO.getErrore().getStatus()), respDTO.getErrore().getTitle(), jsonInputString);
        }
        else
        {
          istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErr, 
             Constants.ERRORS.COD_ERRORE_INTERNO , "Oggetto riposta null!!!!", jsonInputString);
        }
        
      }
      else
      {
        istanzaDAO.storicizzaBoprocLog(istanza.getFkIstanza(), jsonInputString);
        istanzaDAO.insertBoProcLog(istanza.getFkIstanza(), Constants.STEP.STEP_5, Constants.AMBITO_ISTANZA.VINCOLO_IDRO);
      }
    }
    catch(Exception ex)
    {
      String erroreStr = "Problemi con la gestione della pratica step 4:"+istanza.getFkIstanza()+" - "+ex.getMessage();
      if(erroreStr.length() > 1999)
        erroreStr = erroreStr.substring(0, 1999);
      Long countErrore = istanzaDAO.getCountErrore(istanza.getFkIstanza());
      istanzaDAO.updateBoprocLog(istanza.getFkIstanza(), countErrore.intValue()+1, 
          Constants.ERRORS.COD_ERRORE_INTERNO, erroreStr, jsonInputString);
      logger.error(erroreStr);
    }        
    finally
    {
      if (logger.isDebugEnabled())
      {
        logger.debug("[" + THIS_CLASS + "." + THIS_METHOD + "] END.");
      }
    }
  }
  
  
  public boolean checkInvioIstanza() {
	  //return this.configDAO.isScheduleDateActive()&& this.configDAO.isAnotherScheduleRunning();
	  return this.configDAO.isScheduleDateActive();
  }
  
  public boolean updateProtocollo(String idPratica, String numeroProtocollo, String dataProtocollo, String json) 
  {
    boolean trovato = false;
    String fkIstanzaNum = IdfBoUtils.FORMAT.getFkIstanza(idPratica);
    int result = istanzaDAO.updateProtocollo(Long.valueOf(fkIstanzaNum), numeroProtocollo, dataProtocollo);
    if(result > 0)
    {
      istanzaDAO.storicizzaBoprocLogNoData(idPratica, json);
      trovato = true;
    }
    
    
    return trovato;
  }
  
  public void updateBoprocLog(String idIstanza, int countTentativo, Integer codErrore, String noteErrore,
      String json) {
    istanzaDAO.updateBoprocLog(idIstanza, countTentativo, codErrore, noteErrore, json);
  }
  
  public void storicizzaBoprocLog(String idIstanza, String json) {
    istanzaDAO.storicizzaBoprocLog(idIstanza, json);
  }
  
  public void inviaMailProtocollo(long idIstanza, int idTipoMail) throws ServiceException {
	  logger.info("INVIO 1");
	  
	  IstanzaInfoMailByIstanzaDto istanza = istanzaDAO.findInfoMailByIstanza(idIstanza);
	  logger.info("INVIO 2");
	  MailConfigDto mailConfig = configDAO.findMailConfigByPrimaryKey(new MailConfigPk(Constants.MAIL.MAIL_NO_PEC));
	  logger.info("INVIO 3");
	  TipoMailDto tipoMail = configDAO.findTipoMailByPrimaryKey(new TipoMailPk(idTipoMail));
	  logger.info("INVIO 4");
	  
	  this.mailServiceHelper.invioMail(DettaglioEmailFactory.createDettaglioEmail(mailConfig, tipoMail, istanza));
	  logger.info("INVIO 5");
	  
	  
  }

  
  public boolean startSemaforo(String codiceSemaforo) throws Exception {
	  return this.configDAO.startSemaforo(codiceSemaforo);
  }
  
  public void stopSemaforo(String codiceSemaforo) {
	  this.configDAO.stopSemaforo(codiceSemaforo);
  }
  
  
  public void chiusuraPratica(String idIstanza) {
    istanzaDAO.chiusuraPratica(idIstanza);
  }
  
  public boolean cleanResources()
  {
    return istanzaDAO.testDB();
  }

  public boolean testDB()
  {
    return istanzaDAO.testDB();
  }

}
