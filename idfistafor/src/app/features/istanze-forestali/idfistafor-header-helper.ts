/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component } from "@angular/core";
import { TableHeader } from "src/app/shared/models/table-header";

@Component({
  selector: "",
  template: "",
})
export class IdfistaforHeaderHelper {
  constructor() {}

  static getParticleCadastralHeader() {
    const articleCadastralHeader: TableHeader[] = [
        { field: 'comune', header: 'Comune'},
        { field: 'sezione', header: 'Sezione'},
        { field: 'foglio', header: 'Foglio' },
        { field: 'particella', header: 'Particella'},
        { field: 'supCatastale', header: 'Sup. Catastale (ha)'}
    ];
    return articleCadastralHeader;
  }

  static getAllegatiTableHeaders() {
    const allegatiTableHeaders: TableHeader[] = [
        { field: "descrTipoAllegato", header: "Tipo allegato" },
        { field: "nomeAllegato", header: "Nome file" },
        //{ field: "note", header: "Note" },
        { field: "dimensioneAllegatoKB", header: "Kb" }
    ];
    return allegatiTableHeaders;
  }
}
