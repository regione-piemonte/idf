/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import it.csi.idf.idfbo.util.Constants;

/**
 * Depending active spring profile, lookup RDBMS DataSource from JNDI or from an
 * embbeded H2 database.
 */
@Configuration
public class DataSourceConfig
{
  @Bean
  public JndiObjectFactoryBean dataSource() throws IllegalArgumentException
  {
    JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
    dataSource.setExpectedType(DataSource.class);
    dataSource.setJndiName(Constants.DATABASE.JNDINAME);
    return dataSource;
  }
}
