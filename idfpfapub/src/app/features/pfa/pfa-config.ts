/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component } from "@angular/core";
import { TableHeader, FieldType } from "src/app/shared/models/table-header";
import { retry } from "rxjs/operators";

@Component({
  selector: "app-pfa-config",
  template: "",
})
export class PfaConfigComponent {
  constructor() {}

  static getGestitiHeader() {
    const gestitiHeader: TableHeader[] = [
      {
        field: "denominazione",
        header: "Denominazione",
      },
      {
        field: "denominazioneProvincie",
        header: "Provincia",
      },
      {
        field: "denominazioneComuni",
        header: "Comuni interessati",
      },
      {
        field: "dataApprovazione",
        header: "Data approvazione ",
      },
      {
        field: "dataFineValidita",
        header: "Data scadenza",
      },
      {
        field: "detailo",
        header: "Dettaglio",
      },
    ];
    return gestitiHeader;
  }

  static getInterventiHeader() {
    const interventiHeader: TableHeader[] = [
      {
        field: "nrProgressivoInterv",
        header: "N. Intervento",
        tooltipHeader: "N. Intervento ",
      },
      {
        field: "annataSilvana",
        header: "Annata silvana",
        tooltipHeader: "Annata silvana",
      },
      {
        field: "nParticelaForestale",
        header: "N. Particella Forestale",
        tooltipHeader: "N. Particella Forestale ",
      },
      {
        field: "dataInizioString",
        header: "Data inizio",
        tooltipHeader: "Data inizio",
      },
      {
        field: "dataFineString",
        header: "Data fine",
        tooltipHeader: "Data fine",
      },
      {
        field: "descrizione",
        header: "Descrizione",
        tooltipHeader: "Descrizione",
      },
      {
        field: "localita",
        header: "Località",
        tooltipHeader: "Località",
      },
      {
        field: "superficieInteressata",
        header: "Superficie interessata (ha)",
        tooltipHeader: "Superficie interessata (ha)",
      },
      {
        field: "m3Prelevati",
        header: "m³ prelevati",
        tooltipHeader: "merti cubi prelevati",
      },
      // {
      //   field: "name",
      //   header: "Tipologia di evento/intervento"
      // },
      {
        field: "descrStatoIntervento",
        header: "Stato intervento",
        tooltipHeader: "Stato intervento",
      },
      {
        field: "comunicazioneDiTaglio",
        header: "Comunicazione di taglio",
        tooltipHeader: "Comunicazione di taglio",
      },
    ];

    return interventiHeader;
  }
  static getEventsHeader() {
    const eventHeader: TableHeader[] = [
      {
        field: "progressivoEventoPfa",
        header: "N. evento",
        tooltipHeader: "N. evento",
      },
      {
        field: "nomeBreve",
        header: "Nome breve",
        tooltipHeader: "Nome breve",
      },
      {
        field: "dataEvento",
        header: "Data evento",
        tooltipHeader: "Data evento",
      },
      {
        field: "idgeoPlParticelaForest",
        header: "N. particella forestale",
        tooltipHeader: "N. particella forestale",
      },
      {
        field: "descrTipoEvento",
        header: "Tipo evento",
        tooltipHeader: "Tipo evento",
      },
      {
        field: "descrEvento",
        header: "descrizione",
        tooltipHeader: "descrizione",
      },
      {
        field: "localita",
        header: "Località",
        tooltipHeader: "Località",
      },
      {
        field: "supInteressataHa",
        header: "Superficie interessata (ha)",
        tooltipHeader: "Superficie interessata (ha)",
      },
      {
        field: "percentualeDanno",
        header: "% danno",
        tooltipHeader: "% danno",
      }
    ];

    return eventHeader;
  }

  static getCatastaliHeader() {
    const catastaliHeader: TableHeader[] = [
      {
        field: "comune",
        header: "COMUNE",
      },
      {
        field: "sezione",
        header: "SEZIONE",
      },
      {
        field: "foglio",
        header: "FOGLIO",
      },
      {
        field: "particella",
        header: "PARTICELLA",
      },
      {
        field: "supCatastale",
        header: "SUP. CATASTALE",
      },
    ];
    return catastaliHeader;
  }

  static getRicadeHeader() {
    const ricadeHeader: TableHeader[] = [
      {
        field: "comune",
        header: "COMUNE",
      },
      {
        field: "sezione",
        header: "SEZIONE",
      },
      {
        field: "foglio",
        header: "FOGLIO",
      },
      {
        field: "particella",
        header: "PARTICELLA",
      },
      {
        field: "supCatastale",
        header: "SUP. CATASTALE",
      },
      {
        field: "supIntervento",
        header: "SUP. INTERVENTO",
      },
    ];
    return ricadeHeader;
  }

  static getTaglioHeader() {
    const taglioHeader: TableHeader[] = [
      {
        field: "comune",
        header: "COMUNE",
      },
      {
        field: "sezione",
        header: "SEZIONE",
      },
      {
        field: "foglio",
        header: "FOGLIO",
      },
      {
        field: "particella",
        header: "PARTICELLA",
      },
      {
        field: "subalt",
        header: "SUBALTERNO",
      },
      {
        field: "supPart",
        header: "superifice particella (ha)",
      },
      {
        field: "supTagl",
        header: "superficie tagliata (ha)",
      },
    ];
    return taglioHeader;
  }

  static getDocumentiHeader() {
    const documentiHeader: TableHeader[] = [
      {
        field: "descrTipoAllegato",
        header: "Tipo allegato",
      },
      {
        field: "nomeAllegato",
        header: "Nome file",
      },
      {
        field: "brand",
        header: "Note",
      },
      {
        field: "dimensioneAllegatoKB",
        header: "Dimensione",
      },
    ];
    return documentiHeader;
  }

  static getAllegatoHeaders() {
    const allegatoHeader: TableHeader[] = [
      {
        field: "descrTipoAllegato",
        header: "Tipo allegato",
      },
      {
        field: "nomeAllegato",
        header: "Nome file",
      },
      {
        field: "brand",
        header: "Note",
      },
      {
        field: "dimensioneAllegatoKB",
        header: "Dimensione",
      },
    ];
    return allegatoHeader;
  }

  static getSpecieHeaders() {
    const specieHeader: TableHeader[] = [
      {
        field: "specie",
        header: "Specie",
      },
      {
        field: "priorita",
        header: "Priorita",
      },
      {
        field: "volume",
        header: "Volume",
      },
      {
        field: "unit",
        header: "Unita di misura",
      },
    ];

    return specieHeader;
  }

  static getListOfParcelsHeaders() {
    const tableHeaders: TableHeader[] = [
      {
        field: "comune",
        header: "Comune",
      },
      {
        field: "sezione",
        header: "Sezione",
      },
      {
        field: "foglio",
        header: "Foglio",
      },
      {
        field: "particella",
        header: "Particella",
      },
      {
        field: "supCatastale",
        header: "Sup. Catastale",
      },
    ];

    return tableHeaders;
  }
  static getParticeleCatastralHeaders() {
    const tableHeaders: TableHeader[] = [
      {
        field: "denominazioneComune",
        header: "Comune",
      },
      {
        field: "sezione",
        header: "Sezione",
      },
      {
        field: "foglio",
        header: "Foglio",
      },
      {
        field: "particella",
        header: "Particella",
      },
      {
        field: "supCatastale",
        header: "Sup. Catastale",
      },
      {
        field: "supIntervento",
        header: "Sup. Intervento",
      }
    ];

    return tableHeaders;
  }
  static chiudiHeader() {
    const tableHeaders: TableHeader[] = [
      {
        field: "codParticellaFor",
        header: "N. Particella Forestale",
      },
      {
        field: "ripresaInter",
        header: "Ripresa intervento da registro",
      },
      {
        field: "ripresaReale",
        header: "Ripresa reale a fine intervento (mc)",
      },
      {
        field: "ripresaResidua",
        header: "Ripresa residua",
      },
    ];
    return tableHeaders;
  }
  static getShootingMirrorHeader() {
    const tableHeaders: TableHeader[] = [
      {
        field: "codParticellaFor",
        header: "N. Particella Forestale",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: true
      },
      {
        field: "ettari",
        header: "Superficie totale particella",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: true
      },
      {
        field: "supTagliataHa",
        header: "Superficie intervento",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 4,
        visible: true
      },
      {
        field: "provvigioneHa",
        header: "Provvigione (m³/ha)",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: true
      },
      {
        field: "provvigioneReale",
        header: "Provvigione totale (m³)",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: true
      },
      {
        field: "percTassoRipresaPot",
        header: "Tasso di ripresa potenziale (%)",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 1,
        visible: true
      },
      {
        field: "ripresaTotHa",
        header: "Ripresa totale (m³)",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: true
      },
      {
        field: "ripresaAttuale",
        header: "Ripresa attuale",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: true
      },
      {
        field: "ripresaIntervento",
        header: "Ripresa intervento",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 2,
        fieldOfSummaryColumns: ['totale'],
        visible: true
      },
      {
        field: "ripresaResidua",
        header: "Ripresa residua",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: true
      },
    ];
    return tableHeaders;
  }

  static getShootingCompletaHeader() {
    const tableHeaders: TableHeader[] = [
      {
        field: "idgeoPlParticellaForest",
        header: "N. Particella Forestale",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: false
      },
      {
        field: "codParticellaFor",
        header: "N. Particella Forestale",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: true
      },
      {
        field: "ripresaAttuale",
        header: "Ripresa intervento da registro",
        disabled: false,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: true
      },
      {
        field: "ripresaIntervento",
        header: "Ripresa reale a fine intervento (mc)",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        fieldOfSummaryColumns: ['totale'],
        visible: true
      },
      {
        field: "ripresaResidua",
        header: "Ripresa residua",
        disabled: true,
        fieldType: FieldType.NUMBER,
        decimalPlaces: 0,
        visible: true
      },
    ];
    return tableHeaders;
  }
}
