/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.util.builder;

/**
 *
 * @author 71740 (Simone Cornacchia)
 *
 * @param <T> built type
 */
public interface GenericBuilder<T> {

    /**
     *
     * @return
     */
    T build();

}
