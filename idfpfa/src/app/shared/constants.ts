/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { AmbitoInstanza } from "./models/ambito-instanza.model";
import { Utilizzatore } from "./models/utilizzatore.model";
export class CONST {
  public static readonly CAP_MASK: string = "[0-9]{5}";

  public static readonly IT = {
    firstDayOfWeek: 1,
    dayNames: [
      "Domenica",
      "Lunedì",
      "Martedì",
      "Mercoledì",
      "Giovedì",
      "Venerdì",
      "Sabato",
    ],
    dayNamesShort: ["Dom", "Lun", "Mar", "Mer", "Gio", "Ven", "Sab"],
    dayNamesMin: ["D", "L", "M", "M", "G", "V", "S"],
    monthNames: [
      "Gennaio",
      "Febbraio",
      "Marzo",
      "Aprile",
      "Maggio",
      "Giugno",
      "Luglio",
      "Agosto",
      "Settembre",
      "Ottobre",
      "Novembre",
      "Dicembre",
    ],
    monthNamesShort: [
      "Gen",
      "Feb",
      "Mar",
      "Apr",
      "Mag",
      "Giu",
      "Lug",
      "Ago",
      "Set",
      "Ott",
      "Nov",
      "Dic",
    ],
    today: "Oggi",
    clear: "Annulla",
  };

  public static readonly PATTERN_MAIL: string =
    "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
  public static readonly PATTERN_CAP: string = "\\d{5}";
  public static readonly PATTERN_NUMERIC: string = "^[1-9+]*$";
  public static readonly PATTERN_NUMERIC_WITH_ZERO: string = "^[0-9]*$";
  public static readonly PATTERN_PROVINCIA_ORDINE: string = "^[0-9]{3}$";
  public static readonly PATTERN_MONETARY: string =
    "^(((\\d{1,3})(?:,[0-9]{3}){0,4}|(\\d{1,12}))(\\.[0-9]{1,2})?)?$";
  public static readonly PATTERN_NUMERIC_DECIMAL_COMMA: string =
    "^[0-9]*,?[0-9]*$";
  public static readonly CONFIRM_DELETE_EVENTI: string =
    "Eliminando l'evento, eliminerai anche tutti gli interventi ad esso correlati";
  public static readonly CONFIRM_DELETE_INTERVENTI: string =
    "Vuoi veramente cancellare l'intervento ?";
  public static readonly SEZIONE: string = "sezione";
  public static readonly FOGLIO: string = "foglio";
  public static readonly PARTICELLA: string = "particella";
  public static readonly AUTOCOMPLETE_EMPTY_MESSAGE: string =
    "nessun risultato trovato";
  public static readonly ROLE_CITADINO = 1;
  public static readonly ROLE_GESTORE = 2;
  public static readonly ROLE_UF_TERRIRORIALE = 3;
  public static readonly ROLE_COMUNE = 4;
  public static readonly ROLE_CONSULTAZIONE = 5;
  public static readonly ROLE_SPORTELLO = 7;
  public static readonly ROLE_SPORTELLO_GESTORE = 8;

  public static readonly USER_KEY_STORE = "user";
  public static readonly SPORTELLO_PG_KEY_STORE = "sportelloPG";
  public static readonly TIPO_ISTANZA_ID_KEY_STORE = "tipoIstanzaId";
  public static readonly CODICE_FISCALE_KEY_STORE = "codiceFiscale";

  public static readonly STATO_INTERVENTO = [{
    fkStatoIntervento: 1,
    descrStatoIntervento: "PROGRAMMATO",
  },
  {
    fkStatoIntervento: 3,
    descrStatoIntervento: "IN CORSO",
  },
  {
    fkStatoIntervento: 4,
    descrStatoIntervento: "ESEGUITO",
  },
  {
    fkStatoIntervento: 5,
    descrStatoIntervento: "CONCLUSO",
  }];
  public static readonly ACCORDION_STEP2: string[] = [
    "Disegnare direttamente il poligono aprendo il visualizzatore geografico integrato alla procedura (scelta consigliata)",
    "Ricerca alfanumerica di tutte le particelle catastali su cui ricade il bosco che si intende trasformare. Il sistema verifica l'esistenza della particella ricercata sulla base dati di Sigmater e ne visualizza la superficie in ettari. A partire dalle particelle indicate, il sistema, cliccando su ‘acquisisci particelle e genera poligono’ crea automaticamente un poligono risultante dalle singole superfici catastali. Tale poligono, creato nel sistema di riferimento WGS84, è visualizzato e modificabile nel visualizzatore geografico integrato alla procedura.",
  ];

  public static readonly TIPO_UTILIZZATORE_OPTIONS: Utilizzatore[] = [
    { id: 1, label: "IN_PROPRIO", descr: "In proprio" },
    { id: 2, label: "DA_INDIVIDUARE", descr: "Da individuare" },
    { id: 3, label: "PERSONA_FISICA", descr: "Persona fisica" },
    { id: 4, label: "PERSONA_GIURIDICA", descr: "Persona giuridica" },
  ];
  public static readonly TIPO_ALLEGATO_EXLUDE_IDS = [25, 26, 35, 36, 37, 38];
  public static readonly TIPO_ALLEGATO_COMUNICAZIONE_OPZIONALE = [29,27,30,31];
  public static readonly TIPO_ALLEGATO_OBBLIGATORIO_IDS = [27, 29, 161, 160];
  public static readonly TIPO_ALLEGATO_OBBLIGATORIO_IDS_OBL = [
    27, 29, 161, 160,
  ];
  public static readonly TIPO_ALLEGATO_LIBERO_DESC = "Allegato Libero";
  public static readonly TIPO_ALLEGATO_PIEDILISTA_DESC = "Piedilista";
  public static readonly TIPO_ALLEGATO_PROG_INTERVENTO_DESC = "Progetto di intervento";
  public static readonly NOME_ALLEGATO_DRELL = "Dichiarazione regolare esecuzione lavori (Drel)"

  public static readonly NUMERO_ALLEGATO_PROG_INTERVENTO = 161;
  public static readonly NUMERO_ALLEGATO_PROG_INTERVENTO_REF2 = 29;

  public static readonly TAGLI_FUSTAIA_IDS = [1, 2, 3, 4];

  public static readonly CITADINO_ROUTES: string[] = [
    "visualizza",
    "visualizza-vid",
    "visualizza-tagli",
    "inserisci-nuova",
    "inserisci-nuova-vid",
    "inserisci-nuova-tagli",
    "modifica/:idIntervento",
    "modifica-vid/:idIntervento",
    "modifica-tagli/:idIntervento",
    "return-modifica/:idIntervento",
    "return-modifica-vid/:idIntervento",
    "return-modifica-tagli/:idIntervento",
  ];
  public static readonly SPORTELLO_ROUTES: string[] = [
    "visualizza",
    "visualizza-vid",
    "visualizza-tagli",
    "inserisci-nuova",
    "inserisci-nuova-vid",
    "inserisci-nuova-tagli",
    "modifica/:idIntervento",
    "modifica-vid/:idIntervento",
    "modifica-tagli/:idIntervento",
    "return-modifica/:idIntervento",
    "return-modifica-tagli/:idIntervento",
    "ricerca-archivio-tagli",
  ];
  public static readonly SPORTELLO_GESTORE_ROUTES: string[] = [
    "visualizza",
    "visualizza-vid",
    "visualizza-tagli",
    "inserisci-nuova",
    "inserisci-nuova-vid",
    "inserisci-nuova-tagli",
    "modifica/:idIntervento",
    "modifica-vid/:idIntervento",
    "modifica-tagli/:idIntervento",
    "return-modifica/:idIntervento",
    "return-modifica-tagli/:idIntervento",
    "ricerca-archivio-tagli",
    "ricerca-gestore-sportello-tagli",
  ];
  public static readonly AMBITO_OPZIONE: AmbitoInstanza[] = [
    {
      idAmbitoIstanza: 2,
      descrAmbitoIstanza:
        "Interventi selvicolturali: Comunicazione semplice/Richiesta di Autorizzazione con progetto",
    },
    {
      idAmbitoIstanza: 1,
      descrAmbitoIstanza:
        "Trasformazione del bosco: autocertificazione e dichiarazione d’atto notorio ai sensi del DPR 445/2000",
    },
    {
      idAmbitoIstanza: 4,
      descrAmbitoIstanza:
        "Vincolo Idrogeologico: richiesta di Autorizzazione di intervento di competenza regionale",
    },
    {
      idAmbitoIstanza: 5,
      descrAmbitoIstanza:
        "Vincolo Idrogeologico: richiesta di Autorizzazione di intervento di competenza comunale",
    },
  ];
  public static readonly GESTORE_ROUTES: string[] = [
    "ricerca",
    "boModifica=true",
  ];
  public static readonly UF_TERRITORIALE_ROUTES: string[] = [
    "ricerca",
    "boModifica=true",
  ];
  public static readonly COMUNE_ROUTES: string[] = [
    "ricerca",
    "boModifica=true",
  ];
  public static readonly CONSULTAZIONE_ROUTES: string[] = [
    "ricerca",
    "boModifica=true",
  ];
}
