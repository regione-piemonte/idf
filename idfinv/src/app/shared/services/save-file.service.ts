/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';
import * as fileSaver from 'file-saver';

@Injectable({
  providedIn: 'root'
})
export class SaveFileService {

  constructor() { }

  saveFile(data: any, filename?: string) {
    console.log('SAVING FILE', filename);
		fileSaver.saveAs(data.body, filename);
  }
  
  dataURItoBlob(dataURI) {
    
    const byteString = atob(dataURI);

    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);

    for (let i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }

    const blob = new Blob([ab], { type: 'text/csv; charset=utf-8' });

    return blob;
  }

}
