/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import org.apache.log4j.Logger;

import it.csi.idf.idfapi.util.Constants;

/**
 * Classe base di tutti i row mapper utilizzati nello strato DAO.
 * @generated
 */
public class BaseDaoRowMapper {
	/**
	 * @generated
	 */
	protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME);

	/**
	 * Contiene l'elenco di tutte le colonne da leggere.
	 * Viene inizializzato nelle singole sottoclassi con l'elenco effettivo
	 * di colonne della colonna relativa.
	 * Serve poiche', pe rogni DAO, e' possibile utilizzare lo stesso mapper in piu' 
	 * contesti di finder:
	 * - in un finder che restituisce tutte le colonne
	 * - in un finder che restituisce (popola) solo un sottoinsieme di colonne
	 * Serve per evitare la proliferazione di classi Mapper: una singola classe
	 * puo' essere utilizzata in differenti contesti.
	 * @generated
	 */
	public final java.util.Map<String, String> columnsToReadMap = new java.util.HashMap<String, String>();

	/**
	 * questo flag, se impostato a true nell'istanza del mapper, fa in modo che
	 * siano lette tutte le colonne.
	 * Serve per ottimizzare la logica ed evitare che vengano testate inutilmente
	 * le condizioni sulle varie colonne effettive.
	 * @generated
	 */
	public boolean mapAllColumns = true;

	/**
	 * La classe del DTO che il mapper deve istanziare durante la ricostruzione del
	 * risultato
	 * @generated
	 */
	public Class dtoClass;

	/**
	 * Costruttore del RowMapper. 
	 * Permette di specificare:
	 * - l'elenco delle colonne da in cludere nel mapping (per query incomplete,
	 *   esempio: distinct, custom select, ....)
	 * - la classe del DTO da istanziare al momento della ricostruzione del risultato
	 * @generated
	 * @param columnsToRead elenco delle colonne da includere nel mapping (per query
	 *        incomplete, esempio distinct, custom select...)
	 * @param dtoClass la classe del DTO da istanziare al momento della ricostruzione del 
	 *        risultato
	 */
	public BaseDaoRowMapper(String[] columnsToRead, Class dtoClass) {
		if (columnsToRead != null) {
			mapAllColumns = false;
			for (int i = 0; i < columnsToRead.length; i++)
				columnsToReadMap.put(columnsToRead[i], columnsToRead[i]);
		}
		this.dtoClass = dtoClass;
	}
	/**
	 * Istanzia un DTO nuovo (vuoto) del tipo specificato al momento della creazione del 
	 * RowMapper
	 * @return l'oggetto istanziato (del tipo specificato in dtoClass)
	 * @generated
	 */
	public Object getNewDto() {
		Object dtoInstance = null;

		try {
			dtoInstance = dtoClass.newInstance();
			return dtoInstance;
		} catch (InstantiationException e) {
			LOG.error("Si e' verificato un errore nella chiamata al dao supplier: ", e);
			throw new RuntimeException("Impossibile istanziare la classe " + dtoClass.getName() + " ," + e.getCause());
		} catch (IllegalAccessException e) {
			LOG.error("Si e' verificato un errore nella chiamata al dao supplier: ", e);
			throw new RuntimeException("Impossibile accedere alla classe " + dtoClass.getName() + " ," + e.getCause());
		}
	}

	// there is no specific code for POSGRESQL

}
