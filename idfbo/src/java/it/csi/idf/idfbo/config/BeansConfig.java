/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages =
{ "it.csi.idf.idfbo" })
public class BeansConfig implements ApplicationContextAware
{

  protected static final int        CACHE_PERIOD = 31556926; // one year
  @Autowired
  private static ApplicationContext applicationContext;

  public static Object getBean(String name)
  {
    return applicationContext.getBean(name);
  }

  @Override
  public void setApplicationContext(ApplicationContext context)
      throws BeansException
  {
    applicationContext = context;
  }

}
