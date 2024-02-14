/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

import it.csi.idf.idfapi.dto.LocalDateFormat;

public class LocalDateDefaultDeserializer extends StdDeserializer<LocalDate> {

    protected LocalDateDefaultDeserializer() {
        super(LocalDate.class);
    }
    
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
    	LocalDateFormat dateMapped = mapper.readValue(jp, LocalDateFormat.class);
        return LocalDate.of(dateMapped.getYear(), Month.of(dateMapped.getMonthValue()), dateMapped.getDayOfMonth());
    }
}
