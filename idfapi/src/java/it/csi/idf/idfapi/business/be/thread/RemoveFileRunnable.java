/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.thread;

import java.io.File;
public class RemoveFileRunnable implements Runnable {

	public RemoveFileRunnable(String fileName) {
		super();
		this.fileName = fileName;
	}

	private String fileName;
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			File f = new File(fileName);
			if(f.exists()) {
				f.delete();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
