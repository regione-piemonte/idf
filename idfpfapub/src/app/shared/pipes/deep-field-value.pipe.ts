/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
  name: "deepFieldValue",
})
export class DeepFieldValuePipe implements PipeTransform {
  transform(value: any, args?: any): any {
    const arr = args ? args.split(".") : [];
    let tmp = value;
    while (arr.length && (tmp = tmp[arr.shift()])) {}

    return tmp;
  }
}
