/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class CONST {

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

    public static readonly PATTERN_MAIL : string = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static readonly PATTERN_CAP : string = "\\d{5}";
    public static readonly PATTERN_NUMERIC : string = "^[1-9+]*$";
    public static readonly PATTERN_ZERO : string = "^[0-9+]*$";
    public static readonly PATTERN_NUMERIC_POSITIVE : string = "^[1-9+][0-9]*$";
    public static readonly PATTERN_DECIMAL : string = "^\\d{1,10}(\\.\\d{1,})?$";
    public static readonly PATTERN_DECIMAL_1 : string = "^([1-9]{0,3})(\.[0-9]{1})?$"
    public static readonly PATTERN_FATTORE_RELASCOPICA : string = "[124]"

}