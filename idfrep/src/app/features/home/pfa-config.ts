/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component } from "@angular/core";
import { TableHeader } from "src/app/shared/models/table-header";

@Component({
  selector: "app-pfa-config",
  template: ""
})
export class PfaConfigComponent {
  constructor() {}

  static getGestitiHeader() {
    const gestitiHeader: TableHeader[] = [
      {
        field: "denominazione",
        header: "Denominazione"
      },
      {
        field: "denominazioneProvincie",
        header: "Provincia"
      },
      {
        field: "denominazioneComuni",
        header: "Comuni interessati"
      },
      {
        field: "dataApprovazione",
        header: "Data approvazione "
      },
      {
        field: "dataFineValidita",
        header: "Data scadenza"
      },
      {
        field: "detailo",
        header: "Dettaglio"
      }
    ];
    return gestitiHeader;
  }

  static getDocumentiHeader() {
    const documentiHeader: TableHeader[] = [
      {
        field: "fkTipoAleggato",
        header: "Tipo allegato"
      },
      {
        field: "nomeAllegato",
        header: "Nome file"
      },
      {
        field: "brand",
        header: "Note"
      },
      {
        field: "dimensioneAllegatoKB",
        header: "Kb"
      }
    ];
    return documentiHeader;
  }
}
