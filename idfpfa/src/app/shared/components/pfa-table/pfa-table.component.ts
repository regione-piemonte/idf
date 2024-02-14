/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { TableHeader } from "../../models/table-header";

@Component({
  selector: "app-pfa-table",
  templateUrl: "./pfa-table.component.html",
  styleUrls: ["./pfa-table.component.css"]
})
export class PfaTableComponent implements OnInit {
  @Input() values: any[];
  @Input() headers: TableHeader[];
  @Input() selectableField: string;
  @Input() idComune: string;
  @Input() idPopolamento: string;
  @Input() totalItems: number;
  selectedRow: number;
  @Output() rowIdEmiter: EventEmitter<any> = new EventEmitter();
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();
  constructor() {}

  ngOnInit() {}

  getFieldId(
    id: number,
    index: number,
    comuneId: number,
    idPopolamento: number
  ) {
    this.selectedRow = index;
    this.rowIdEmiter.emit({
      id: id,
      idComune: comuneId,
      idPopolamento: idPopolamento
    });
  }

  updateData(event) {
    let data = event;
    if (event.sortOrder === -1) {
      data.field = event.sortField ? "-" + event.sortField : null;
    } else {
      data.field = event.sortField ? event.sortField : null;
    }
    data.page = event.first / event.rows + 1;
    data.limit = event.rows;
    this.updateTableChanges.emit(data);
  }

  getDescr(elem:any){
    let label = this.headers[0].header;
    let key = this.headers[0].field;
    return label.toLocaleLowerCase() + " " + elem[key];
  }
}
