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
    
    public static readonly INFORMATIVA_SULLA : string = "http://www.sistemapiemonte.it/ris/ambiente/autorizzazioni_forestali/dwd/Informativa_dati_personali_Dlgs196-2003_GDPR216-679_TagliBoschivi.pdf";
    public static readonly PATTERN_MAIL : string = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static readonly GESTORE_ACCESS_PFA : number = 6;
}