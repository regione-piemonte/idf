/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.business;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.csi.idf.idfgeoapi.business.service.impl.SaveFeatureApiImpl;
import it.csi.idf.idfgeoapi.util.SpringInjectorInterceptor;
import it.csi.idf.idfgeoapi.util.SpringSupportedResource;

@ApplicationPath("api")
public class AppRestApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public AppRestApplication() {
        singletons.add(new SpringInjectorInterceptor());
        singletons.add(SaveFeatureApiImpl.class);
        for (Object c : singletons) {
            if (c instanceof SpringSupportedResource) {
                SpringApplicationContextHelper.registerRestEasyController(c);
            }
        }
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

}
