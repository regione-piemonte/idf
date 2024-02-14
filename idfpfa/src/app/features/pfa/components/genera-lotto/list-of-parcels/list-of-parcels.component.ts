/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  OnDestroy,
  OnChanges,
} from "@angular/core";
import { Subject } from "rxjs";
import { ShowParcel } from "src/app/shared/models/particle-cadastral";
import { FormGroup, FormBuilder } from "@angular/forms";
import { TableHeader } from "src/app/shared/models/table-header";
import { PfaConfigComponent } from "../../../pfa-config";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { PropCatastos } from "src/app/shared/models/data-taglio";
import { ComuniModel } from "src/app/shared/models/comune.model";
import { SearchForParcelsComponent } from "../search-for-parcels/search-for-parcels.component";
import { PfaInterventoService } from "src/app/shared/services/pfa-intervento.service";

@Component({
  selector: "app-list-of-parcels",
  templateUrl: "./list-of-parcels.component.html",
  styleUrls: ["./list-of-parcels.component.css"],
})
export class ListOfParcelsComponent implements OnInit, OnChanges {
  @Input() tableData: ShowParcel[];
  selectedRow: number;
  sortedColumn = "comune";

  tableHeaders: TableHeader[];

  @Output() deleteRowEmitter = new EventEmitter<number>();
  @Output() emitAcquisici = new EventEmitter();
  @Input() disableAcquisisci: boolean;
  @Input() idIntervento: number;
  disableGeeco: boolean = false;

  constructor(
    private pfaInterventoService: PfaInterventoService,
    private pfaService: PfaSampleService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.tableHeaders = PfaConfigComponent.getParticeleCatastralHeaders();
  }

  ngOnChanges(){
    console.log('listOfParticels change!!!')
  }

  getRowId(event) {}
  getUpdatedTable(event) {}

  deleteRow(event) {
    this.deleteRowEmitter.emit(event);
  }
  
  acquisisciParticelle() {
    if(this.disableAcquisisci == false){
      this.emitAcquisici.emit();
    }
  }
}
