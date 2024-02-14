/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';

import { TableHeader } from 'src/app/shared/models/table-header';

@Component({
  selector: 'app-list-of-attachments',
  templateUrl: './list-of-attachments.component.html',
  styleUrls: ['./list-of-attachments.component.css']
})
export class ListOfAttachmentsComponent implements OnInit {

  allegatiTableData = [
    { tipoAllegato: "a", nomeFile: "a", note: "a", kb: 23, id: 1 },
    { tipoAllegato: "b", nomeFile: "b", note: "b", kb: 23, id: 2 },
    { tipoAllegato: "c", nomeFile: "c", note: "c", kb: 23, id: 3 },
    { tipoAllegato: "d", nomeFile: "d", note: "d", kb: 23, id: 4 }
  ];

  allegatiTableHeaders: TableHeader[] = [
    { field: "tipoAllegato", header: "Tipo allegato" },
    { field: "nomeFile", header: "Nome file" },
    //{ field: "note", header: "Note" },
    { field: "kb", header: "Kb" }
  ];

  constructor() { }

  ngOnInit() {
  }

  fieldToDelete(event) {}
  fieldToDownload(event) {}

}
