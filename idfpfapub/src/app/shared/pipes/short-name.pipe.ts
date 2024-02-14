/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Pipe, PipeTransform } from "@angular/core";
import { PfaNameEnum } from "../enums";

@Pipe({
  name: "shortName",
})
export class ShortNamePipe implements PipeTransform {
  transform(value: string, originalValue: string, replaceValue: string): any {
    if (value == undefined) {
      return value;
    }

    return value.replace(PfaNameEnum.full, PfaNameEnum.short);
  }
}
