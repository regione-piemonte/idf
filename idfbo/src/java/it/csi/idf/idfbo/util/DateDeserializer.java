/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.encoding.DeserializationContext;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonDeserializer;

public class DateDeserializer extends JsonDeserializer<Date>
{

	@Override
	public Date deserialize(JsonParser jsonParser, org.codehaus.jackson.map.DeserializationContext arg1) throws IOException, JsonProcessingException
	{
	  
	  
	  
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		 //System.out.println( format.format(new Date()));
		 String date = jsonParser.getText();
     try {
         return format.parse(date);
     } catch (Exception e) {
         //throw new RuntimeException(e);
     }
     
     format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
     
     //System.out.println( format.format(new Date()));
     
     try {
       return format.parse(date);
   } catch (ParseException e) {
       throw new RuntimeException(e);
   }
	}
}

