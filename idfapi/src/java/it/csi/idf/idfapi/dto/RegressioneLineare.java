/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public class RegressioneLineare {
	
	static final Logger logger = Logger.getLogger(RegressioneLineare.class);

	private double m;
	private double q;
	private double r2;
	
	public RegressioneLineare(Double[] diametri,Double[] altezze) {
		super();
		elaboraRegressioneLineare(Arrays.asList(diametri), Arrays.asList(altezze));
	}
	
	public RegressioneLineare(List<Double> listDiametri,List<Double> listAltezze) {
		super();
		elaboraRegressioneLineare(listDiametri, listAltezze);
	}
		
	private void elaboraRegressioneLineare(List<Double> diametri,List<Double> altezze) {
		double Xm = media(diametri); 
		double Ym = media(altezze);
		double sommaSup = 0d;
		double sommaInf = 0d;
		double devianzaStimata = 0d;
		double devianzaTotale = 0d;
		
		String diamStr ="";
		String altezStr = "";
		
		for(int i=0;i<diametri.size();i++) {
			sommaSup+= (diametri.get(i)-Xm)*(altezze.get(i)-Ym);
			sommaInf+= Math.pow(diametri.get(i)-Xm,2);
			diamStr += diametri.get(i) + ", ";
			altezStr += altezze.get(i) + ", ";
		}
		if(sommaInf != 0) {
			this.m = sommaSup/sommaInf;
			this.q = Ym - this.m*Xm;
			
			logger.debug("diametro: " + diamStr);
			logger.debug("altezze: " + altezStr);
			logger.info("m: " + this.m + " - q: " + this.q);
			
			List<Double> altezzeInterp = new ArrayList<Double>();
			for(int i=0;i<diametri.size();i++) {
				altezzeInterp.add(this.m*diametri.get(i)+q);
			}
			double YDevM = media(altezze);
			
			for(int i=0;i<diametri.size();i++) {
				devianzaStimata += Math.pow(altezzeInterp.get(i)-YDevM,2);
				devianzaTotale += Math.pow(altezze.get(i)-Ym,2);
			}
			this.r2 = devianzaStimata/devianzaTotale;
			
		}else {
		this.m =0 ;
		this.q = 0;
		this.r2 = 0;
		}
	}
	public double getM() {
		return m;
	}

	public double getQ() {
		return q;
	}
	
	public double getR2() {
		return r2;
	}
	
	private double media(List<Double> vet) {
		double somma = 0;
		for(Double val:vet) {
			somma+= val;
		}
		return somma/vet.size();
	}
	
	public double getAltezzaByDiametro(Double diametro) {
		return this.m * Math.log(diametro) + this.q;
	}
	
}
