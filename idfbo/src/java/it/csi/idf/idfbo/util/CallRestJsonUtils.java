/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.util;

import static it.csi.idf.idfbo.util.builder.ToStringBuilder.objectToString;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import it.csi.idf.idfbo.dto.ApiManagerDto;
import it.csi.idf.idfbo.dto.ErrorMessageDTO;
import it.csi.idf.idfbo.dto.RespDocumento;
import it.csi.idf.idfbo.dto.RespPratica;
import it.csi.idf.idfbo.dto.ResponseDTO;
import it.csi.idf.idfbo.util.oauth2.InternalOauthHelper;

public class CallRestJsonUtils {

	protected static final Logger logger = Logger.getLogger(Constants.LOGGING.LOGGER_NAME + ".util");

	public static final String AUTHORIZATION_HEADER_PARAM = "Authorization";
	public static final String AUTHORIZATION_HEADER_PARAM_VALUE_PREFIX = "Bearer ";
	
	

	public ResponseDTO sendPost(String wsUri, ApiManagerDto apiManager, String jsonInputString) throws Exception {
		logger.info("[CallRestJsonUtils.sendPost] BEGIN.");
		StringBuilder response = new StringBuilder();
		URL url = new URL(wsUri);
		ObjectMapper om = new ObjectMapper();
		RespPratica respPratica = null;
		ErrorMessageDTO error = null;
		ResponseDTO respDTO = new ResponseDTO();

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(Constants.WS.POST_TIME_OUT); // millisec
		connection.setReadTimeout(Constants.WS.POST_TIME_OUT);

		//String userpass = "foreste" + ":" + "foreste";
		//String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
		// connection.setRequestProperty (" ", basicAuth);
		
		ResourceBundle res = ResourceBundle.getBundle("config");
    String urlToken = res.getString(Constants.URL.URL_TOKEN); 
		
		logger.info("----- 1a ");	
		//ApiManagerDto apiManager = configDAO.getInfoApiManagerInternet();
		
		logger.info(objectToString(apiManager));
		logger.info("urlToken  --> "+urlToken);
		
//		InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
//				"rx8AGlZQybBMHqxBmqARapfHeTca", "pyvLvggbIY8iYMCbnCjDVipl78Ia");
		InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
				apiManager.getConsumerKey(), apiManager.getConsumerSecret());
		
		logger.info("----- 2a");
		try {
			String token = oauthHelper.getToken();
			logger.info("----- 3a");
			connection.setRequestProperty(AUTHORIZATION_HEADER_PARAM, AUTHORIZATION_HEADER_PARAM_VALUE_PREFIX + token);
		} catch (Throwable ex) {
			logger.info("----- 4a");
			//String prova = null;
			ex.printStackTrace();
		}
		
		logger.info("----- 5a");
		try (OutputStream os = connection.getOutputStream()) {
			
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
			os.flush();
			logger.info("----- 6a");
			InputStream in = null;
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpStatus.SC_OK || responseCode == HttpStatus.SC_CREATED) {
				logger.info("----- 7a");
				in = connection.getInputStream();
				respPratica = om.readValue(in, RespPratica.class);
				respDTO.setIdPratica(respPratica.getIdPratica());
				/*
				 * BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8")) ;
				 * String responseLine = null; while ((responseLine = br.readLine()) != null) {
				 * response.append(responseLine.trim()); }
				 */
			} else {
				logger.info("----- 8a");
				in = connection.getErrorStream();
				if (in != null) {
					in = connection.getErrorStream();
					error = om.readValue(in, ErrorMessageDTO.class);
					respDTO.setErrore(error);
					logger.error(error.getCode() + " - " + error.getStatus() + " - " + error.getTitle());
				} else {
					throw new Exception("Impossibile leggere lo stream di output del servizio!!!");
				}
			}

			/*
			 * try (BufferedReader br = new BufferedReader(new
			 * InputStreamReader(connection.getInputStream(), "utf-8"))) {
			 * 
			 * String responseLine = null; while ((responseLine = br.readLine()) != null) {
			 * response.append(responseLine.trim()); } logger.debug(response.toString()); }
			 */
			logger.debug(response.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
			logger.info("[CallRestJsonUtils.sendPost] END.");
			
		}

		return respDTO;

	}

	public ResponseDTO sendFilePost(String wsUri, ApiManagerDto apiManager, byte[] fileByte, String nomeFile) throws Exception {
		StringBuilder response = new StringBuilder();
		RespPratica respPratica = null;
		ErrorMessageDTO error = null;
		ResponseDTO respDTO = new ResponseDTO();
		URL url = new URL(wsUri);
		DataOutputStream dos = null;
		// DataInputStream inStream = null;
		// definisco un boundary
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		// String strResponse = null;

		// File inFile = new File("C:\\Users\\admin\\Desktop\\Yana-make-up.jpg");
		// FileInputStream fis = null;

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		// Il Content-Type della form
		
		conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
		conn.setConnectTimeout(Constants.WS.POST_TIME_OUT); // millisec
		conn.setReadTimeout(Constants.WS.POST_TIME_OUT);
		//String userpass = "foreste" + ":" + "foreste";
		//String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
		//conn.setRequestProperty("Authorization", basicAuth);
		
		ResourceBundle res = ResourceBundle.getBundle("config");
		String urlToken = res.getString(Constants.URL.URL_TOKEN); 
		logger.info("----- 1a");
    //InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
        //"rx8AGlZQybBMHqxBmqARapfHeTca", "pyvLvggbIY8iYMCbnCjDVipl78Ia");
		InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
        apiManager.getConsumerKey(), apiManager.getConsumerSecret());
    logger.info("----- 2a");
    try {
      String token = oauthHelper.getToken();
      logger.info("----- 3a");
      conn.setRequestProperty(AUTHORIZATION_HEADER_PARAM, AUTHORIZATION_HEADER_PARAM_VALUE_PREFIX + token);
    } catch (Throwable ex) {
      logger.info("----- 4a");
      //String prova = null;
      ex.printStackTrace();
    }
		
		

		try {
			// Apro lo streaming verso la servlet
			dos = new DataOutputStream(conn.getOutputStream());

			// Scrivo la prima riga
			dos.writeBytes(twoHyphens + boundary + lineEnd);
			dos.writeBytes(
					"Content-Disposition: form-data; name=\"file\";" + " filename=\"" + nomeFile + "\"" + lineEnd);
			String mimeType = IdfBoUtils.FILE.getMimeTypeFromFileName(nomeFile);
	    logger.info("mimeType: "+mimeType);
	    
			dos.writeBytes("Content-Type: "+mimeType + lineEnd);
			dos.writeBytes(lineEnd);

			// Leggo il file di testo che devo inviare, e lo metto in un buffer.
			// Nell'esempio qui sotto, viene definito un maxBufferSize,
			// per limitare la grandezza del file che viene inviato.

			int maxBufferSize = 1 * 1024 * 1024;
			// FileInputStream fileInputStream = new FileInputStream( new
			// File("C:\\Users\\luca.diana\\Desktop\\Cosmo\\stampaFascicolo.pdf") );
			InputStream targetStream = new ByteArrayInputStream(fileByte);

			// Creo un buffer con dimensione minima fra quella del file e quella impostata
			// come massima.
			// bytesAvailable = fileInputStream.available();
			bytesAvailable = targetStream.available();
			bufferSize = Math.min(bytesAvailable, maxBufferSize);
			buffer = new byte[bufferSize];

			// leggo il file e invio i byte alla servlet
			// bytesRead = fileInputStream.read(buffer, 0, bufferSize);
			bytesRead = targetStream.read(buffer, 0, bufferSize);
			while (bytesRead > 0) {

				dos.write(buffer, 0, bufferSize);
				// bytesAvailable = fileInputStream.available();
				bytesAvailable = targetStream.available();
				// bufferSize = Math.min(bytesAvailable, maxBufferSize);bytesRead =
				// fileInputStream.read(buffer, 0, bufferSize);
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = targetStream.read(buffer, 0, bufferSize);

			}

			// Chiudo il file
			// fileInputStream.close();
			targetStream.close();
			// Invio il boundary per delimitare la fine del file
			dos.writeBytes(lineEnd);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

			// A questo punto devo rimanere in attesa della risposta della servlet, perch�
			// altrimenti il file non sar� caricato

			ObjectMapper om = new ObjectMapper();

			InputStream in = null;
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpStatus.SC_OK || responseCode == HttpStatus.SC_CREATED) {

				in = conn.getInputStream();
				respPratica = om.readValue(in, RespPratica.class);
				respDTO.setUploadUUID(respPratica.getUploadUUID());
				/*
				 * BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8")) ;
				 * String responseLine = null; while ((responseLine = br.readLine()) != null) {
				 * response.append(responseLine.trim()); }
				 */
			} else {

				in = conn.getErrorStream();
				if (in != null) {
					in = conn.getErrorStream();
					error = om.readValue(in, ErrorMessageDTO.class);
					respDTO.setErrore(error);
					logger.error(error.getCode() + " - " + error.getStatus() + " - " + error.getTitle());
				} else {
					throw new Exception("Impossibile leggere lo stream di output del servizio!!!");
				}
			}
			// response.append(om.readValue(conn.getInputStream(), ErrorMessageDTO.class));
			/*
			 * inStream = new DataInputStream ( conn.getInputStream() ); while ((
			 * strResponse = inStream.readLine()) != null) {
			 * logger.debug("Server response is: "+strResponse); } inStream.close();
			 */

			logger.debug("Server response is: " + response.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

		return respDTO;

	}

	public ResponseDTO sendPostDocumento(String wsUri, ApiManagerDto apiManager, String jsonInputString) throws Exception {
		StringBuilder response = new StringBuilder();
		URL url = new URL(wsUri);
		ObjectMapper om = new ObjectMapper();
		RespDocumento respDocumento = null;
		ErrorMessageDTO error = null;
		ResponseDTO respDTO = new ResponseDTO();

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestMethod("POST");
		connection.setConnectTimeout(Constants.WS.POST_TIME_OUT); // millisec
		connection.setReadTimeout(Constants.WS.POST_TIME_OUT);

		//String userpass = "foreste" + ":" + "foreste";
		//String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
		//connection.setRequestProperty("Authorization", basicAuth);
		
		
		ResourceBundle res = ResourceBundle.getBundle("config");
    String urlToken = res.getString(Constants.URL.URL_TOKEN); 
		
		logger.info("----- 1a");
    //InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
        //"rx8AGlZQybBMHqxBmqARapfHeTca", "pyvLvggbIY8iYMCbnCjDVipl78Ia");
		InternalOauthHelper oauthHelper = new InternalOauthHelper(urlToken,
        apiManager.getConsumerKey(), apiManager.getConsumerSecret());
    logger.info("----- 2a");
    try {
      String token = oauthHelper.getToken();
      logger.info("----- 3a");
      connection.setRequestProperty(AUTHORIZATION_HEADER_PARAM, AUTHORIZATION_HEADER_PARAM_VALUE_PREFIX + token);
    } catch (Throwable ex) {
      logger.info("----- 4a");
      //String prova = null;
      ex.printStackTrace();
    }

		try (OutputStream os = connection.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
			os.flush();

			InputStream in = null;
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpStatus.SC_OK || responseCode == HttpStatus.SC_CREATED) {

				in = connection.getInputStream();
				respDocumento = om.readValue(in, RespDocumento.class);
				if (respDocumento != null) {
					if (respDocumento.getEsiti() != null && respDocumento.getEsiti().size() > 0) {
						for (int i = 0; i < respDocumento.getEsiti().size(); i++) {
							if (respDocumento.getEsiti().get(i).getEsito() != null
									&& respDocumento.getEsiti().get(i).getEsito().getStatus() != null) {
								if (new Integer(respDocumento.getEsiti().get(i).getEsito().getStatus())
										.intValue() != HttpStatus.SC_OK
										&& new Integer(respDocumento.getEsiti().get(i).getEsito().getStatus())
												.intValue() != HttpStatus.SC_CREATED) {
									respDTO.setErrore(respDocumento.getEsiti().get(i).getEsito());
									break;
								}
							}
						}
					}
				}
				// respDTO.setIdPratica(respPratica.getIdPratica());
				/*
				 * BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8")) ;
				 * String responseLine = null; while ((responseLine = br.readLine()) != null) {
				 * response.append(responseLine.trim()); }
				 */
			} else {

				in = connection.getErrorStream();
				if (in != null) {
					in = connection.getErrorStream();
					error = om.readValue(in, ErrorMessageDTO.class);
					respDTO.setErrore(error);
					logger.error(error.getStatus() + " - " + error.getCode() + " - " + error.getTitle());
				} else {
					throw new Exception("Impossibile leggere lo stream di output del servizio!!!");
				}
			}

			/*
			 * try (BufferedReader br = new BufferedReader(new
			 * InputStreamReader(connection.getInputStream(), "utf-8"))) {
			 * 
			 * String responseLine = null; while ((responseLine = br.readLine()) != null) {
			 * response.append(responseLine.trim()); } logger.debug(response.toString()); }
			 */
			logger.debug(response.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}

		return respDTO;

	}

	/*public String sendPut(String wsUri, String jsonInputString, String user, String password) throws Exception {
		StringBuilder response = new StringBuilder();
		URL url = new URL(wsUri);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestMethod("PUT");
		connection.setConnectTimeout(Constants.WS.POST_TIME_OUT); // millisec
		connection.setReadTimeout(Constants.WS.POST_TIME_OUT);

		String userpass = user + ":" + password;
		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
		connection.setRequestProperty("Authorization", basicAuth);

		try (OutputStream os = connection.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
			os.flush();

			try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {

				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				// 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		connection.disconnect();

		return response.toString();

	}*/

	/*public String sendPut(String wsUri, String jsonInputString) throws Exception {
		StringBuilder response = new StringBuilder();
		URL url = new URL(wsUri);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json; utf-8");
		connection.setRequestMethod("PUT");
		connection.setConnectTimeout(Constants.WS.POST_TIME_OUT); // millisec
		connection.setReadTimeout(Constants.WS.POST_TIME_OUT);

		try (OutputStream os = connection.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
			os.flush();

			try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {

				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		connection.disconnect();

		return response.toString();

	}*/

}
