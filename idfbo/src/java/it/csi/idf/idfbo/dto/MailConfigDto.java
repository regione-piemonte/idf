/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.dto;



/**
 * Data transfer object relativo al DAO MailConfigDao.
 * @generated
 */
public class MailConfigDto  extends MailConfigPk{

	private static final long serialVersionUID = 1L;

	/**
	 * store della proprieta' associata alla colonna HOST
	 * @generated
	 */
	protected String host;

	/**
	 * Imposta il valore della proprieta' host associata alla
	 * colonna HOST.
	 * @generated
	 */
	public void setHost(String val) {

		host = val;

	}

	/**
	 * Legge il valore della proprieta' host associata alla
	 * @generated
	 */
	public String getHost() {

		return host;

	}

	/**
	 * store della proprieta' associata alla colonna PORTA
	 * @generated
	 */
	protected java.math.BigDecimal porta;

	/**
	 * Imposta il valore della proprieta' porta associata alla
	 * colonna PORTA.
	 * @generated
	 */
	public void setPorta(java.math.BigDecimal val) {

		porta = val;

	}

	/**
	 * Legge il valore della proprieta' porta associata alla
	 * @generated
	 */
	public java.math.BigDecimal getPorta() {

		return porta;

	}

	/**
	 * store della proprieta' associata alla colonna USER_ID
	 * @generated
	 */
	protected String userId;

	/**
	 * Imposta il valore della proprieta' userId associata alla
	 * colonna USER_ID.
	 * @generated
	 */
	public void setUserId(String val) {

		userId = val;

	}

	/**
	 * Legge il valore della proprieta' userId associata alla
	 * @generated
	 */
	public String getUserId() {

		return userId;

	}

	/**
	 * store della proprieta' associata alla colonna PSW
	 * @generated
	 */
	protected String psw;

	/**
	 * Imposta il valore della proprieta' psw associata alla
	 * colonna PSW.
	 * @generated
	 */
	public void setPsw(String val) {

		psw = val;

	}

	/**
	 * Legge il valore della proprieta' psw associata alla
	 * @generated
	 */
	public String getPsw() {

		return psw;

	}

	/**
	 * store della proprieta' associata alla colonna PROTOCOLLO
	 * @generated
	 */
	protected String protocollo;

	/**
	 * Imposta il valore della proprieta' protocollo associata alla
	 * colonna PROTOCOLLO.
	 * @generated
	 */
	public void setProtocollo(String val) {

		protocollo = val;

	}

	/**
	 * Legge il valore della proprieta' protocollo associata alla
	 * @generated
	 */
	public String getProtocollo() {

		return protocollo;

	}

	/**
	 * store della proprieta' associata alla colonna MITTENTE
	 * @generated
	 */
	protected String mittente;

	/**
	 * Imposta il valore della proprieta' mittente associata alla
	 * colonna MITTENTE.
	 * @generated
	 */
	public void setMittente(String val) {

		mittente = val;

	}

	/**
	 * Legge il valore della proprieta' mittente associata alla
	 * @generated
	 */
	public String getMittente() {

		return mittente;

	}

	/**
	 * store della proprieta' associata alla colonna DES_TIPO_POSTA
	 * @generated
	 */
	protected String desTipoPosta;

	/**
	 * Imposta il valore della proprieta' desTipoPosta associata alla
	 * colonna DES_TIPO_POSTA.
	 * @generated
	 */
	public void setDesTipoPosta(String val) {

		desTipoPosta = val;

	}

	/**
	 * Legge il valore della proprieta' desTipoPosta associata alla
	 * @generated
	 */
	public String getDesTipoPosta() {

		return desTipoPosta;

	}

	/**
	 * Crea una istanza di MailConfigPk a partire dal valore dei campi chiave del DTO
	 * 
	 * @return MailConfigPk
	 * @generated
	 */
	public MailConfigPk createPk() {
		return new MailConfigPk(idMail);
	}

	/**
	 * la semantica dell'equals del DTO e' la stessa della PK
	 * (ovvero della superclasse).
	 * @param other l'oggetto con cui effettuare il confronto
	 * @return true se i due oggetti sono semanticamente da considerarsi uguali
	 */
	public boolean equals(Object other) {
		return super.equals(other);
	}

	/**
	 * la semantica dell'hashCode del DTO e' la stessa della PK
	 * (ovvero della superclasse).
	 * 
	 * @return int
	 */
	public int hashCode() {
		return super.hashCode();
	}

}
