/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Email dto.
 *
 * @author CSI PIEMONTE
 */
public class EmailDTO {

    private String mittente;

    private String replyTo;

    private String corpo;

    private String oggetto;

    private List<String> destinatari = new ArrayList<>();

    private List<String> destinatariCC = new ArrayList<>();

    //parametri di configurazione
    private String host;

    private String port;

    private String userID;

    private String password;

    private String isAuth = "false";

    private String enableSSL = "false";

    private String enableTLS = "false";

    private String enableDebug = "false";

    private String socketFactoryFallback = "false";

    private String socketFactoryClass = ""; //javax.net.ssl.SSLSocketFactory

    private String socketFactoryPort = "";

    /**
     * Gets mittente.
     *
     * @return the mittente
     */
    public String getMittente() {
        return mittente;
    }

    /**
     * Sets mittente.
     *
     * @param mittente the mittente
     */
    public void setMittente(String mittente) {
        this.mittente = mittente;
    }

    /**
     * Gets reply to.
     *
     * @return the reply to
     */
    public String getReplyTo() {
        return replyTo;
    }

    /**
     * Sets reply to.
     *
     * @param replyTo the reply to
     */
    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    /**
     * Gets corpo.
     *
     * @return the corpo
     */
    public String getCorpo() {
        return corpo;
    }

    /**
     * Sets corpo.
     *
     * @param corpo the corpo
     */
    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    /**
     * Gets oggetto.
     *
     * @return the oggetto
     */
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Sets oggetto.
     *
     * @param oggetto the oggetto
     */
    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    /**
     * Gets destinatari.
     *
     * @return the destinatari
     */
    public List<String> getDestinatari() {
        return destinatari;
    }

    /**
     * Sets destinatari.
     *
     * @param destinatari the destinatari
     */
    public void setDestinatari(List<String> destinatari) {
        this.destinatari = destinatari;
    }

    /**
     * Gets destinatari cc.
     *
     * @return the destinatari cc
     */
    public List<String> getDestinatariCC() {
        return destinatariCC;
    }

    /**
     * Sets destinatari cc.
     *
     * @param destinatariCC the destinatari cc
     */
    public void setDestinatariCC(List<String> destinatariCC) {
        this.destinatariCC = destinatariCC;
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets host.
     *
     * @param host the host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * Sets port.
     *
     * @param port the port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets is auth.
     *
     * @return the is auth
     */
    public String getIsAuth() {
        return isAuth;
    }

    /**
     * Sets is auth.
     *
     * @param isAuth the is auth
     */
    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    /**
     * Gets enable ssl.
     *
     * @return the enable ssl
     */
    public String getEnableSSL() {
        return enableSSL;
    }

    /**
     * Sets enable ssl.
     *
     * @param enableSSL the enable ssl
     */
    public void setEnableSSL(String enableSSL) {
        this.enableSSL = enableSSL;
    }

    /**
     * Gets enable tls.
     *
     * @return the enable tls
     */
    public String getEnableTLS() {
        return enableTLS;
    }

    /**
     * Sets enable tls.
     *
     * @param enableTLS the enable tls
     */
    public void setEnableTLS(String enableTLS) {
        this.enableTLS = enableTLS;
    }

    /**
     * Gets enable debug.
     *
     * @return the enable debug
     */
    public String getEnableDebug() {
        return enableDebug;
    }

    /**
     * Sets enable debug.
     *
     * @param enableDebug the enable debug
     */
    public void setEnableDebug(String enableDebug) {
        this.enableDebug = enableDebug;
    }

    /**
     * Gets socket factory fallback.
     *
     * @return the socket factory fallback
     */
    public String getSocketFactoryFallback() {
        return socketFactoryFallback;
    }

    /**
     * Sets socket factory fallback.
     *
     * @param socketFactoryFallback the socket factory fallback
     */
    public void setSocketFactoryFallback(String socketFactoryFallback) {
        this.socketFactoryFallback = socketFactoryFallback;
    }

    /**
     * Gets socket factory class.
     *
     * @return the socket factory class
     */
    public String getSocketFactoryClass() {
        return socketFactoryClass;
    }

    /**
     * Sets socket factory class.
     *
     * @param socketFactoryClass the socket factory class
     */
    public void setSocketFactoryClass(String socketFactoryClass) {
        this.socketFactoryClass = socketFactoryClass;
    }

    /**
     * Gets socket factory port.
     *
     * @return the socket factory port
     */
    public String getSocketFactoryPort() {
        return socketFactoryPort;
    }

    /**
     * Sets socket factory port.
     *
     * @param socketFactoryPort the socket factory port
     */
    public void setSocketFactoryPort(String socketFactoryPort) {
        this.socketFactoryPort = socketFactoryPort;
    }

    /**
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(corpo, destinatari, destinatariCC, enableDebug, enableSSL, enableTLS, host, isAuth, mittente, oggetto, password, port, replyTo, socketFactoryClass, socketFactoryFallback, socketFactoryPort, userID);
    }

    /**
     * @param obj Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmailDTO other = (EmailDTO) obj;
        return Objects.equals(corpo, other.corpo) && Objects.equals(destinatari, other.destinatari) && Objects.equals(destinatariCC, other.destinatariCC) && Objects.equals(enableDebug, other.enableDebug) && Objects.equals(enableSSL, other.enableSSL) && Objects.equals(enableTLS, other.enableTLS) && Objects.equals(host, other.host) && Objects.equals(isAuth, other.isAuth) && Objects.equals(mittente, other.mittente) && Objects.equals(oggetto, other.oggetto) && Objects.equals(password, other.password) && Objects.equals(port, other.port) && Objects.equals(replyTo, other.replyTo) && Objects.equals(socketFactoryClass, other.socketFactoryClass) && Objects.equals(socketFactoryFallback, other.socketFactoryFallback) && Objects.equals(socketFactoryPort, other.socketFactoryPort) && Objects.equals(userID, other.userID);
    }

    /**
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EmailDTO [mittente=").append(mittente).append("\n  replyTo=")
        .append(replyTo).append("\n  corpo=").append(corpo).append("\n  oggetto=")
        .append(oggetto).append("\n  destinatari=").append(destinatari)
        .append("\n  destinatariCC=").append(destinatariCC).append("\n  host=")
        .append(host).append("\n  port=").append(port).append("\n  userID=")
        .append(userID).append("\n  password=").append(password).append("\n  isAuth=")
        .append(isAuth).append("\n  enableSSL=").append(enableSSL).append("\n  enableTLS=")
        .append(enableTLS).append("\n  enableDebug=").append(enableDebug)
        .append("\n  socketFactoryFallback=").append(socketFactoryFallback)
        .append("\n  socketFactoryClass=").append(socketFactoryClass)
        .append("\n  socketFactoryPort=").append(socketFactoryPort).append("]");
        return builder.toString();
    }

}
