/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer
{

  @Override
  protected Class<?>[] getRootConfigClasses()
  {
    return new Class[]
    { BeansConfig.class, DataSourceConfig.class, TransactionConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses()
  {
    return new Class[]
        {};
  }

  @Override
  protected String[] getServletMappings()
  {
    return new String[]
    { "/siappcg/*" };
  }

}
