/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpClientHolder {
	
	private static OkHttpClient _instance=null;
	
	private OkHttpClientHolder() {
	}
	
	public static OkHttpClient getInstance() {
		if (_instance == null) {
			_instance = new OkHttpClient();
		}
		return _instance; 
	}
	
	public static OkHttpClient getInstance(int timeout) {
		if (_instance == null) {
			_instance = new OkHttpClient.Builder()
				      .connectTimeout(timeout, TimeUnit.MILLISECONDS)
				      .readTimeout(timeout, TimeUnit.MILLISECONDS)
				      .build();
		}
		return _instance; 
	}
	
}
