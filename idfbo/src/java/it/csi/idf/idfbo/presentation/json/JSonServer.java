/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.presentation.json;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1")
public class JSonServer extends Application
{
  private Set<Object>   singletons = new HashSet<Object>();
  private Set<Class<?>> empty      = new HashSet<Class<?>>();

  public JSonServer()
  {
    singletons.add(new JSonService());
  }

  @Override
  public Set<Class<?>> getClasses()
  {
    return empty;
  }

  @Override
  public Set<Object> getSingletons()
  {
    return singletons;
  }
}
