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
import it.csi.idf.idfbo.dto.MtdTrasfCosmoDTO;
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

@Component("istanzaBean")
@Transactional
public class IstanzaBean
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
    
    
    
    
    final String THIS_METHOD = "getElencoIstanze TRASFORMAZIONI ";
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
      //String codiceIpaEnte = istanzaDAO.getValueParameter(Constants.PARAMETER.CODICEIPAENTE_COSMO);
      //String codiceTipologia = istanzaDAO.getValueParameter(Constants.PARAMETER.CODICETIPOLOGIA_COSMO);
      
      //step 1
           
      /*PraticaDTO pratica = new PraticaDTO();
      pratica.setIdPratica("foreste_24_02_2022_7");
      pratica.setOggetto("foreste_24_02_2022_7");
      pratica.setCodiceIpaEnte("grp_amb");
      pratica.setCodiceTipologia("IST_FOR_TRASF");
      pratica.setRiassunto("Processo del 24 02 2022");
      MetadatiDTO metadati = new MetadatiDTO();  
      metadati.setNr_istanza_forestale("12A");
      metadati.setData_invio("01/02/2022");
      metadati.setId_soggetto_gestore("aaa23");
      metadati.setRagione_sociale("test ragione sociale");
      metadati.setCognome_nome_dichiarante("Rossi Luca");
      metadati.setSigla_provincia("TO");
      pratica.setMetadati(om.writeValueAsString(metadati));*/
          
      
      PraticaDTO pratica = new PraticaDTO();
      pratica.setIdPratica(""+istanza.getFkIstanza());
      String oggetto = strOggetto+""+IdfBoUtils.DATE.formatDateUnderscore(new Date())+"_"+istanza.getFkIstanza();
      pratica.setOggetto(oggetto);
      pratica.setCodiceIpaEnte(codiceIpaEnte);
      pratica.setCodiceTipologia(codiceTipologia);
      //pratica.setUtenteCreazionePratica("AAAAAA00B77B000F");
      //pratica.setRiassunto(null);  nn valorizzato
      
      MtdTrasfCosmoDTO mtd = istanzaDAO.getDatiTrasfCosmo(Long.valueOf(istanza.getFkIstanza()));
      MetadatiDTO metadati = new MetadatiDTO();
      metadati.setId(""+mtd.getId());
      metadati.setNr_istanza_forestale(""+mtd.getNrIstanzaForestale());
      metadati.setData_invio(IdfBoUtils.DATE.formatDate(new Date()));
      metadati.setIntestatario(mtd.getIntestatario());
      metadati.setCognome_dichiarante(mtd.getCognomeDichiarante());
      metadati.setNome_dichiarante(mtd.getNomeDichiarante());
      metadati.setRagione_sociale(mtd.getRagioneSociale());
      metadati.setSoggetto_gestore(mtd.getSoggettoGestore());        
      metadati.setSigla_provincia(mtd.getSiglaProvincia());
      metadati.setParole_chiave_fascicolo(mtd.getParoleChiaveFascicolo());
      metadati.setOggetto_fascicolo(mtd.getOggettoFascicolo());
      
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
        istanzaDAO.insertBoProcLog(istanza.getFkIstanza(), Constants.STEP.STEP_2, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
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
      
      
       String idIstanza = istanza.getFkIstanza();
       List<AllegatoDTO> lAllegati = istanzaDAO.getElencoAllegati(Long.valueOf(idIstanza), Constants.PARAMETER.DA_INVIARE_COSMO);
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
           istanzaDAO.insertBoProcLog(istanza.getFkIstanza(), Constants.STEP.STEP_3, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
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
      
      String idIstanza = istanza.getFkIstanza();
      DocumentoDTO documento = new DocumentoDTO();
      documento.setIdPratica(""+idIstanza);
      documento.setCodiceIpaEnte(codiceIpaEnte);
      
      List<DocumentiDTO> lDocumenti = new ArrayList<>();
      List<AllegatoDTO> lAllegati = istanzaDAO.getElencoAllegati(Long.valueOf(idIstanza), Constants.PARAMETER.INVIATO_COSMO_FILE);
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
          MtdTrasfCosmoDTO mtd = istanzaDAO.getDatiTrasfCosmo(Long.valueOf(istanza.getFkIstanza()));
          doc.setAutore(mtd.getIntestatario());
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
          istanzaDAO.storicizzaBoprocLog(istanza.getFkIstanza(), jsonInputString);
          istanzaDAO.insertBoProcLog(istanza.getFkIstanza(), Constants.STEP.STEP_4, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
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
      
      
      
      
      String idIstanza = istanza.getFkIstanza();
      ProcessoDTO processo = new ProcessoDTO();
      processo.setIdPratica(""+idIstanza);
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
        istanzaDAO.insertBoProcLog(istanza.getFkIstanza(), Constants.STEP.STEP_5, Constants.AMBITO_ISTANZA.ISTANZA_FORESTE);
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
  
  public boolean updateProtocollo(String idIstanza, String numeroProtocollo, String dataProtocollo, String json) 
  {
    boolean trovato = false;
    int result = istanzaDAO.updateProtocollo(Long.valueOf(idIstanza), numeroProtocollo, dataProtocollo);
    if(result > 0)
    {
      istanzaDAO.storicizzaBoprocLogNoData(Long.valueOf(idIstanza).toString(), json);
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
  
  public void inviaMailProtocollo(long idIstanza) throws ServiceException {
	  logger.info("INVIO 1");
	  
	  IstanzaInfoMailByIstanzaDto istanza = istanzaDAO.findInfoMailByIstanza(idIstanza);
	  logger.info("INVIO 2");
	  MailConfigDto mailConfig = configDAO.findMailConfigByPrimaryKey(new MailConfigPk(Constants.MAIL.MAIL_NO_PEC));
	  logger.info("INVIO 3");
	  TipoMailDto tipoMail = configDAO.findTipoMailByPrimaryKey(new TipoMailPk(Constants.MAIL.PROTOCOLLO));
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
  
  public void chiusuraPraticaJson(String idIstanza, String json) {
    istanzaDAO.chiusuraPraticaJson(idIstanza, json);
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
