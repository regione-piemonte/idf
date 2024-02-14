/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.tika.Tika;

/**
 * Classe astratta per le funzioni di utilità sulle stringhe. La classe è abstract perchè non deve essere usata direttamente ma solo dalla sua implementazione
 * nella costante Utils.STRING
 * 
 * @author Luca Diana (Matr. 70399)
 * 
 */
public abstract class FileUtils
{
  public static String getMimeTypeFromFileName(String fileName) {
    Tika tika = new Tika();
    return tika.detect(fileName);
  }

}
