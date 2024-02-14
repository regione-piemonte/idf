/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ConfigService } from "./config.service";
import * as fileSaver from "file-saver";

@Injectable({
  providedIn: "root"
})
export class DownloadService {
  constructor(private http: HttpClient, private config: ConfigService) {}

  downloadExcelFile(plansParams: any) {
    
    
    return this.http.post(
      `${this.config.getBERootUrl(false)}/geoPlPfi/searchExcel/`,
      plansParams, 
      { observe: "response", responseType: "blob" }
    );
  }

  downloadEventiExcel(eventiExcel: any) {
    return this.http.post(
      `${this.config.getBERootUrl(false)}/eventi/eventoExcel`,
      eventiExcel,
      { observe: "response", responseType: "blob" }
    );
  }

  downloadInterventiExcel(interventiExcel: any) {
    return this.http.post(
      `${this.config.getBERootUrl(false)}/interventi/interventiExcel`,
      interventiExcel,
      { observe: "response", responseType: "blob" }
    );
  }

  saveFile(data: any, filename: any) {
    fileSaver.saveAs(data.body, filename);
  }
}
