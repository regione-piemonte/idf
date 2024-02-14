/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
  name: "correctDate"
})
export class CorrectDatePipe implements PipeTransform {
  transform(value?: string, args?: any): any {
    if (value) {
      let splitDate = value.split("-");
      let reverseDate = splitDate.reverse();
      let joinDate = reverseDate.join("/");
      return joinDate;
    }
  }
}
