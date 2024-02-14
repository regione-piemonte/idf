/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Pipe, PipeTransform } from "@angular/core";
import { SizesNameEnum } from "../enums";

@Pipe({
  name: "convertKB",
})
export class ConvertKBPipe implements PipeTransform {
  transform(value: number, KB: number, decimals = 2): any {
    if (KB === 0) return "0 KB";
    const k = 1024;
    const dm = decimals < 0 ? 0 : decimals;
    const sizes = SizesNameEnum;
    const i = Math.floor(Math.log(KB) / Math.log(k));
    return parseFloat((KB / Math.pow(k, i)).toFixed(dm)) + " " + sizes[i];
  }
}
