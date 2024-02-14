/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class CONST {
  public static readonly CAP_MASK: string = "[0-9]{5}";

  public static readonly IT = {
    firstDayOfWeek: 1,
    dayNames: [ "Domenica","Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato" ],
    dayNamesShort: [ "Dom","Lun","Mar","Mer","Gio","Ven","Sab"],
    dayNamesMin: [ "D","L","M","M","G","V","S" ],
    monthNames: [ "Gennaio","Febbraio","Marzo","Aprile","Maggio","Giugno","Luglio","Agosto","Settembre","Ottobre","Novembre","Dicembre" ],
    monthNamesShort: [ "Gen","Feb","Mar","Apr","Mag","Giu","Lug","Ago","Set","Ott","Nov","Dic" ],
    today: 'Oggi',
    clear: 'Annulla'
}

  public static readonly PATTERN_MAIL: string =
    "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
  public static readonly PATTERN_CAP: string = "\\d{5}";
  public static readonly PATTERN_NUMERIC: string = "^[1-9+]*$";
  public static readonly PATTERN_NUMERIC_WITH_ZERO: string = "^[0-9]*$";
  public static readonly PATTERN_PROVINCIA_ORDINE: string = "^[0-9]{3}$";
  public static readonly PATTERN_MONETARY: string =
    "^(((\\d{1,3})(?:,[0-9]{3}){0,4}|(\\d{1,12}))(\\.[0-9]{1,2})?)?$";
  public static readonly CONFIRM_DELETE_EVENTI: string = "Eliminando l'evento, eliminerai anche tutti gli interventi ad esso correlati"  
  public static readonly CONFIRM_DELETE_INTERVENTI: string = "Vuoi veramente cancellare l'intervento ?";
  public static readonly SEZIONE: string = "sezione";
  public static readonly FOGLIO: string = "foglio";
  public static readonly PARTICELLA: string = "particella";
  public static readonly AUTOCOMPLETE_EMPTY_MESSAGE: string = "nessun risultato trovato"
}
