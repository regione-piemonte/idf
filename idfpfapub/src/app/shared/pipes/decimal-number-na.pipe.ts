/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Pipe, PipeTransform } from '@angular/core';
import { DecimalPipe } from '@angular/common';

@Pipe({
  name: 'decimalNumberNA'
})
export class DecimalNumberNAPipe implements PipeTransform {
 constructor( private decimalPipe: DecimalPipe) {}
  transform(value: any, args?: any): any {
    if (!value) {return "N/A"}

    return this.decimalPipe.transform(value, args);
  
  }

}
