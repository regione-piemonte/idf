/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UtilService {

  constructor() { }

  format4Decimals(valueStr:string){
    return this.formatDecimals(valueStr, 4);
  }

  formatDecimals(valueStr:string,decimalRequest:number){
    let decimals = 0;
    let pointPosition = valueStr.indexOf('.');
    if(pointPosition > -1){
      decimals = valueStr.length - pointPosition - 1; 
    }else{
      valueStr+='.'
    }
    let missingDecimals = decimalRequest - decimals;
    if(missingDecimals > 0){
      for(let i= 0; i<missingDecimals; i++){
        valueStr+='0';
      }
    }
    return valueStr;
  }
}
