/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		
		if (enableCors) {
			HttpServletResponse res = (HttpServletResponse) servletResponse;
						   
			res.setHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PATCH, PUT, DELETE");
			res.setHeader("Access-Control-Expose-Headers","Content-Length, Content-Disposition, X-JSON");
			res.setHeader("Access-Control-Allow-Headers", "content-type, X-XSRF-TOKEN");
			res.setHeader("Access-Control-Allow-Credentials", "true");
			
			if (request.getMethod().equals("OPTIONS")) {
	            res.setStatus(HttpServletResponse.SC_ACCEPTED);
	            return;
	        }
		}
		
		chain.doFilter(servletRequest, servletResponse);

	}

	private static final String ENABLECORS_INIT_PARAM = "enablecors";
	private boolean enableCors = false;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String sEnableCors = filterConfig.getInitParameter(ENABLECORS_INIT_PARAM);
		if ("true".equals(sEnableCors)) {
			enableCors = true;
		} else {
			enableCors = false;
		}
	}

	@Override
	public void destroy() {
	}

}
