/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.business.service.helper.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public final class DettaglioEmail implements Serializable {

    private static final long serialVersionUID = 4629342431354570784L;

    /**
     * ID dell'email.
     */
    private String idEmail;

    /**
     * Mittente dell'email.
     */
    private String mittente;

    /**
     * Indirizzo per <code>reply-to</code>.
     */
    private String replyTo;

    /**
     * Corpo dell'email.
     */
    private String corpo;

    /**
     * Oggetto dell'email.
     */
    private String oggetto;

    /**
     * Destinatari dell'email.
     */
    private List<String> destinatari = new ArrayList<String>();

    /**
     * Destinatari in copia carbone dell'email.
     */
    private List<String> destinatariCC = new ArrayList<String>();

    /**
     * Host.
     */
    private String host;

    /**
     * Porta.
     */
    private String port;

    /**
     * User ID.
     */
    private String userID;

    /**
     * Password.
     */
    private String password;

    /**
     * Protocol.
     */
    private String protocol;

    
    /**
     * eMail della'azienda
     */
    private String emailAzienda;

    /**
     * @return the idEmail
     */
    public String getIdEmail() {
        return this.idEmail;
    }

    /**
     * @param idEmail the idEmail to set
     */
    public void setIdEmail(String idEmail) {
        this.idEmail = idEmail;
    }

    /**
     * @return the mittente
     */
    public String getMittente() {
        return this.mittente;
    }

    /**
     * @param mittente the mittente to set
     */
    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    /**
     * @return the replyTo
     */
    public String getReplyTo() {
        return this.replyTo;
    }

    /**
     * @param replyTo the replyTo to set
     */
    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    /**
     * @return the corpo
     */
    public String getCorpo() {
        return this.corpo;
    }

    /**
     * @param corpo the corpo to set
     */
    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    /**
     * @return the oggetto
     */
    public String getOggetto() {
        return this.oggetto;
    }

    /**
     * @param oggetto the oggetto to set
     */
    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    /**
     * @return the destinatari
     */
    public List<String> getDestinatari() {
        return this.destinatari;
    }

    /**
     * @param destinatari the destinatari to set
     */
    public void setDestinatari(List<String> destinatari) {
        this.destinatari = destinatari;
    }

    /**
     * @return the destinatariCC
     */
    public List<String> getDestinatariCC() {
        return this.destinatariCC;
    }

    /**
     * @param destinatariCC the destinatariCC to set
     */
    public void setDestinatariCC(List<String> destinatariCC) {
        this.destinatariCC = destinatariCC;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return this.host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return this.port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return this.protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

   
    /**
     * @return the emailAzienda
     */
    public String getEmailAzienda() {
        return emailAzienda;
    }

    /**
     * @param emailAzienda the emailAzienda to set
     */
    public void setEmailAzienda(String emailAzienda) {
        this.emailAzienda = emailAzienda;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                    .append(this.corpo)
                    .append(this.destinatari)
                    .append(this.destinatariCC)
                    .append(this.host)
                    .append(this.idEmail)
                    .append(this.mittente)
                    .append(this.oggetto)
                    .append(this.password)
                    .append(this.port)
                    .append(this.protocol)
                    .append(this.replyTo)
                    .append(this.emailAzienda)
                    .append(this.userID)
                    .toHashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        return this.getEqualsBuilder((DettaglioEmail) obj).isEquals();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
//    @Override
//    public String toString() {
//        return ToStringBuilder.objectToString(this);
//    }

    /**
     * Returns a new configured {@link EqualsBuilder} instance, ready to use.
     *
     * @param other the "other" {@link DettaglioEmail} instance
     * @return a new configured {@link EqualsBuilder} instance
     */
    private EqualsBuilder getEqualsBuilder(DettaglioEmail other) {
        return new EqualsBuilder()
                    .append(this.corpo, other.corpo)
                    .append(this.destinatari, other.destinatari)
                    .append(this.destinatariCC, other.destinatariCC)
                    .append(this.host, other.host)
                    .append(this.idEmail, other.idEmail)
                    .append(this.mittente, other.mittente)
                    .append(this.oggetto, other.oggetto)
                    .append(this.password, other.password)
                    .append(this.port, other.port)
                    .append(this.protocol, other.protocol)
                    .append(this.replyTo, other.replyTo)
                    .append(this.emailAzienda, other.emailAzienda)
                    .append(this.userID, other.userID);
    }

}
