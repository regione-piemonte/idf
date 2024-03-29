/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.util.oauth2;

/**
 * Struttra associata alla risposta dell'api /token
 * Esempio:
 * {"scope":"am_application_scope default","token_type":"bearer","expires_in":485,"access_token":"7556c37bb18d17df41496c1034fa2bd0"}
 * @author User
 *
 */
public class GetTokenResponse {
	public String scope;
	public String token_type;
	public String bearer;
	public Long expires_in;
	public String access_token;
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getBearer() {
		return bearer;
	}
	public void setBearer(String bearer) {
		this.bearer = bearer;
	}
	public Long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	} 
}
