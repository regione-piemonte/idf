/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class ConverstionVolume {

  static m3ToTon(m3s: string, densita: number) {
    let m3 = parseFloat(m3s.replace(',','.'));
    return this.format((m3 * densita).toFixed(2));
  }
  static m3ToQ(m3s: string, densita: number) {
    let m3 = parseFloat(m3s.replace(',','.'));
    return this.format((m3 * densita * 10).toFixed(2));
  }

  static tonToM3(tonS: string, densita: number) {
    let ton = parseFloat(tonS.replace(',','.'));
    return this.format((ton /densita).toFixed(2));
  }
  static tonToQ(tonS: string) {
    let ton = parseFloat(tonS.replace(',','.'));
    return this.format(((ton * 10).toFixed(2)));
  }
  static qToM3(qS: string, densita: number) {
    let q = parseFloat(qS.replace(',','.'));
    return this.format((q / densita/10).toFixed(2));
  }
  static qToTon(qS: string) {
    let q = parseFloat(qS.replace(',','.'));
      return this.format((q /10).toFixed(2));
  }
  static format(value:string){
    return value.replace('.',',');
  }
}
