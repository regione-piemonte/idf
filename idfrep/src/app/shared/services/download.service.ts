/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import * as fileSaver from "file-saver";
import { HttpClient } from "@angular/common/http";
import { ConfigService } from "./config.service";
import { takeUntil } from "rxjs/operators";
import { Subject } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class DownloadService {
  unsubscribe$ = new Subject<void>()
  constructor(private http: HttpClient, 
    private config: ConfigService) {}

  downloadExcelFile(plansParams: any) {
    return this.http.post(
      `${this.config.getBERootUrl(false)}/public/geoPlPfi/searchExcel`,
      plansParams,
      { observe: "response", responseType: "blob" }
    );
  }

  saveFile(data: any, filename: any) {
    fileSaver.saveAs(data.body, filename);
  }

  downloadExcel(plansParams: any) {
    return this.http.post(
      `${this.config.getBERootUrl(false)}/report/csv/excel/table`,
      plansParams,
      { observe: "response", responseType: "blob" }
    ).pipe(takeUntil(this.unsubscribe$))
    .subscribe(resp => {
      let contentDisposition = resp.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];

      this.saveFile(resp, filename);
    });
  }

  downloadExcelGet(plansParams: any) {
    return this.http.get(
      `${this.config.getBERootUrl(false)}/report/csv/excel/get?title=` + plansParams.title
      + `&dataCsv=` + plansParams.dataCsv,
      { observe: "response", responseType: "blob" }
    ).pipe(takeUntil(this.unsubscribe$))
    .subscribe(resp => {
      let contentDisposition = resp.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];

      this.saveFile(resp, filename);
    });
  }

  getCsv(headers:string[], data:any){
    let br = '\r\n';
    let csv = '';
    for(var i = 0;i<headers.length;i++){
      csv+= headers[i].replace(/;/g,' - ')+ ';';
    }
    console.log(data);
    for(var k = 0;k<data.length;k++){
      csv+=br;
      for(var key in headers){
        csv+= data[k][headers[key]]+ ';';
      }
    }
    console.log(csv);
    return csv;
  }
}
