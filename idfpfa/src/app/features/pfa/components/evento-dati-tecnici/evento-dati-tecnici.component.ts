/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { FormGroup, FormBuilder } from "@angular/forms";
import { Router, ActivatedRoute } from "@angular/router";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { RouteParams } from "src/app/shared/models/routeParams";
import { TipoEventi } from "src/app/shared/models/tipoEventi";
import { CONST } from "src/app/shared/constants";

@Component({
  selector: "app-evento-dati-tecnici",
  templateUrl: "./evento-dati-tecnici.component.html",
  styleUrls: ["./evento-dati-tecnici.component.css"]
})
export class EventoDatiTecniciComponent implements OnInit {
  @Input() eventoForm: FormGroup;
  @Input() tipoEventi: TipoEventi[];
  // @Input() routerParam: RouteParams;
  @Output() emitBack: EventEmitter<void> = new EventEmitter();
  @Output() emitForm: EventEmitter<void> = new EventEmitter();
  @Output() enableFields: EventEmitter<void> = new EventEmitter();
  @Input() isFormValid: boolean;
  @Input() editMode : boolean;
  @Input() disabledFields :boolean = true;
  currentYear: number = new Date().getFullYear();
  it: any = CONST.IT;
  constructor(
    private fb: FormBuilder,
    private routerService: Router,
    private pfaService: PfaSampleService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    // this.activatedRoute.queryParams.subscribe((resp: RouteParams) => {
    //   this.routerParam = resp;
    // });
  }

  backToTabs() {
    this.emitBack.emit();
  }

  saveForm() {
    this.emitForm.emit();
  }

  enableButtons(){
    this.enableFields.emit();
  }
}
