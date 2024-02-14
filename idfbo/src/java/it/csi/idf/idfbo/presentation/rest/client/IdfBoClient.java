/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.presentation.rest.client;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import it.csi.idf.idfbo.dto.IdfWSEsitoInput;
import it.csi.idf.idfbo.dto.IdfWSEsitoOut;

public class IdfBoClient
{

  private String              restServiceUrl  = null;

  private Logger              logger;

  public final static String POSITIVO        = "000";
  public final static String ERRORE_GENERICO = "001";
  public final static String ERRORE_JSON     = "002";

  private static class MESSAGGIO
  {
    private final static String MESSAGGIO_POSITIVO = null;
    private final static String MESSAGGIO_ERRORE   = "Si è verificato un errore nella connessione al servizio stampe.";
    private final static String MESSAGGIO_JSON     = "Si è verificato un errore nella generazione dell'input json.";

  }

  public static final ObjectMapper JACKSON_MAPPER    = (new ObjectMapper())
      .configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
          false);

  private static final String      THIS_CLASS        = it.csi.idf.idfbo.presentation.rest.client.IdfBoClient.class
      .getSimpleName();

  private int                      timeout           = 120000;
  private int                      connectionTimeout = 120000;

  public IdfBoClient(String url)
  {
    this.restServiceUrl = url;
  }
  
  
  
  /*public IdfWSEsitoOut trovaAllegato(long idIstanza) {

    boolean bool = (this.logger != null && this.logger.isDebugEnabled()) ? true
        : false;

    if (bool)
      this.logger.debug(" [" + THIS_CLASS + ".trovaAllegato BEGIN] ");

    IdfWSEsitoOut result = new IdfWSEsitoOut();

    try
    {
      String datiStampa = "";
      
      

      IdfWSEsitoInput input = new IdfWSEsitoInput();

      input.setIdIstanza(idIstanza);
      
      
      String json = JACKSON_MAPPER.writeValueAsString(input);

      InputStream localInputStream = null;

      HttpPost post = new HttpPost(this.restServiceUrl + "trovaAllegato");
      post.addHeader("content-type", "application/json");
      // send a JSON data
      post.setEntity(new StringEntity(json, "UTF-8"));

      RequestConfig config = RequestConfig.custom()
          .setConnectTimeout(connectionTimeout * 1000)
          .setConnectionRequestTimeout(connectionTimeout * 1000)
          .setSocketTimeout(timeout * 1000).build();
      
      CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
      CloseableHttpResponse response = httpClient.execute(post);

      HttpEntity responseEntity = response.getEntity();

      localInputStream = responseEntity.getContent();
      
      int status = response.getStatusLine().getStatusCode();
      
      if(status == 200) {
        
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = localInputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
     
        buffer.flush();
        byte[] byteArray = buffer.toByteArray();
        
        result.setEsito(POSITIVO);
        result.setDescrizioneEsito(MESSAGGIO.MESSAGGIO_POSITIVO);
        
        if (byteArray != null)
        {
          int size = byteArray.length;
          result.setFileReport(byteArray);
          result.setDimensioneFileReport((long) size);
        }
        
      }else {
        if (this.logger != null)
          this.logger
              .error("Richiamo dei servizi stampe jasper con errore http, status = "
                  + status + ", url = " + post + ", messaggio = "
                  + response.getStatusLine().getReasonPhrase());
        
        String localObject1 = null;
        try
        {
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          responseEntity.writeTo(baos);
          localObject1=new String (baos.toByteArray());
          localObject1=JACKSON_MAPPER.readValue(localObject1, String.class);
        }
        catch(Exception e)
        {
          localObject1 = MESSAGGIO.MESSAGGIO_ERRORE;
        }
        result.setEsito(ERRORE_GENERICO);
        result.setDescrizioneEsito(localObject1);
     
      }
    }
    catch (IOException e)
    {
      if (this.logger != null)
        this.logger.error(MESSAGGIO.MESSAGGIO_JSON);
      result.setEsito(ERRORE_JSON);
      result.setDescrizioneEsito(MESSAGGIO.MESSAGGIO_JSON);
    }
    catch (Exception e)
    {
      if (this.logger != null)
        this.logger.error(MESSAGGIO.MESSAGGIO_ERRORE);
      result.setEsito(ERRORE_GENERICO);
      result.setDescrizioneEsito(MESSAGGIO.MESSAGGIO_ERRORE);
    }
    if (bool)
      this.logger.debug(" [" + THIS_CLASS + ".generaStampa END] ");

    return result;
  }*/
  
  
  public IdfWSEsitoOut trovaAllegato(IdfWSEsitoInput input) throws Exception 
  {
    return callServiceUsingPostMethod("trovaAllegato", IdfWSEsitoOut.class, new Param("input", input));
  }
  
  
  @SuppressWarnings("unchecked")
  protected <T> T callServiceUsingPostMethod(String serviceName, Class<T> clazz, Param... params) throws Exception
  {
  String inputStream = null;
    try
    {
      String finalUrl = restServiceUrl + serviceName;
      boolean debugEnabled = logger != null && logger.isDebugEnabled();
      if (debugEnabled)
      {
        logger.debug("[" + THIS_CLASS + ".callServiceUsingPostMethod] calling papuaserv rest service at url: " + finalUrl);
      }
      PostMethod method = new PostMethod(finalUrl);
      HttpClient client = new HttpClient();
      if (params != null)
      {
      for (Param param : params)
        {
          if(param.value != null)
          {
            param.setHttpParam(method);
          }
        }
      }
      int status = client.executeMethod(method);
      if (debugEnabled)
      {
        logger.debug("[" + THIS_CLASS + ".callServiceUsingPostMethod] return code " + status);
      }
      if (status == 200)
      {
        inputStream = method.getResponseBodyAsString();
        if (inputStream==null || inputStream.length()==0)
        {
          return null;
        }
        return JACKSON_MAPPER.readValue(inputStream, clazz);
      }
      else
      {
        inputStream = method.getResponseBodyAsString();
        Map<String, String> map = (Map<String, String>)JACKSON_MAPPER.readValue(inputStream, HashMap.class);
        String exceptionType = map.get("exceptionType");
        Class<Exception> exceptionClass = null;
        if (exceptionType != null)
        {
          try
          {
            exceptionClass = (Class<Exception>) Class.forName(exceptionType);
          }
          catch (ClassNotFoundException e)
          {
            exceptionClass = Exception.class; // Non ho la classe nel mio classpath e quindi la leggo come un'eccezione
                                              // generica
          }
        }
        else
        {
          exceptionClass = Exception.class; // Non conosco il tipo e quindi la leggo come un'eccezione generica
        }
        throw (Exception) JACKSON_MAPPER.readValue(inputStream, exceptionClass);
      }
    }
    catch (Exception e)
    {
      throw e;
    }    
  }
  
  
  protected class Param
  {
    String name;
    Object value;

    public Param(String name, Object value)
    {
      this.name = name;
      this.value = value;
    }

    public String toHttpParam() throws UnsupportedEncodingException
    {
      StringBuilder sb = new StringBuilder();
      if (value.getClass().isArray())
      {
        int len = java.lang.reflect.Array.getLength(value);
        boolean amp = false;
        for (int i = 0; i < len; ++i)
        {
          Object item = java.lang.reflect.Array.get(value, i);
          if (!amp)
          {
            amp = true;
          }
          else
          {
            sb.append("&");
          }
          sb.append(name).append("=");
          sb.append(java.net.URLEncoder.encode(stringOf(item), "ISO-8859-1"));
        }
      }
      else
      {
        sb.append(name).append("=");
        sb.append(java.net.URLEncoder.encode(stringOf(value), "ISO-8859-1"));
      }
      return sb.toString();
    }

    public void setHttpParam(PostMethod method) throws UnsupportedEncodingException
    {
      if (value.getClass().isArray())
      {
        int len = java.lang.reflect.Array.getLength(value);
        for (int i = 0; i < len; ++i)
        {
          Object item = java.lang.reflect.Array.get(value, i);
          method.addParameter(name, java.net.URLEncoder.encode(stringOf(item), "ISO-8859-1"));
        }
      }
      else
      {
        method.setParameter(name, java.net.URLEncoder.encode(stringOf(value), "ISO-8859-1"));
      }
    }
  }
  
  protected String stringOf(Object value)
  {
    return value == null ? "" : value.toString();
  }

  public void setLogger(Logger logger) {
    this.logger = logger;
  }
  
  public Logger getLogger() {
    return logger;
  }

  public void setRestServiceUrl(String restServiceUrl) {
    this.restServiceUrl = restServiceUrl;
  }
  
  public String getRestServiceUrl() {
    return restServiceUrl;
  }
  
}
