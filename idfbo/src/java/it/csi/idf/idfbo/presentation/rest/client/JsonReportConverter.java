/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.presentation.rest.client;

import java.io.IOException;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonReportConverter
{

  public static final ObjectMapper JACKSON_MAPPER = (new ObjectMapper())
      .configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
          false);

  public static String objectToJson(String rootName, Object obj) throws IOException
  {

    String result = "";
    String json = JACKSON_MAPPER.writeValueAsString(obj);
    result = "{\"" + rootName + "\":" + json + "}";
    return result;

  }

  public static String objectToJson(Object obj) throws IOException
  {
    
    String className = obj.getClass().getSimpleName();
    return objectToJson(className.toLowerCase(), obj);
  }

}
