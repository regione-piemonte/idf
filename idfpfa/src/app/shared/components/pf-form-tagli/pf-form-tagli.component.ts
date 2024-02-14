/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  Input,
  OnDestroy,
  EventEmitter,
  Output,
} from "@angular/core";
import { FormGroup, FormControl } from "@angular/forms";
import { Subject } from "rxjs";
import { takeUntil } from "rxjs/operators";

import { SoggettoModel } from "../../models/soggetto.model";
import { ComuneModel } from "../../models/comune.model";
import { SelectItem } from "primeng/api";
import { CONST } from "../../constants";
import { FatSoggetto } from "src/app/shared/models/fat-soggetto.model";
import { TipoAccreditamento } from "src/app/shared/models/tipo-accreditamento.model";
import { ProvinciaModel } from "src/app/shared/models/provincia.model";
import { ForestaliService } from "src/app/features/pfa/services/forestali.service";
import { TagliService } from "src/app/features/pfa/services/tagli.service";

@Component({
  selector: "app-pf-form-tagli",
  templateUrl: "./pf-form-tagli.component.html",
  styleUrls: ["./pf-form-tagli.component.css"],
})
export class PFFormTagliComponent implements OnInit, OnDestroy {
  @Input() personalOrCompanyForm: FormGroup;
  @Input() set personaDatiOption(personaDatiOption: string) {
    this.personaOption = personaDatiOption;
    this.personaFisica =
      personaDatiOption === "RF" ||
      personaDatiOption === "PF" ||
      personaDatiOption === "SF";
  }

  @Input() codiceFiscaleSelectItem: SelectItem[];
  @Input() codiceFiscaleSONOIO: SelectItem[];
  @Input() tipoAccreditamento: string = null;
  @Input() isUtilizzatore: boolean = false;
  @Input() isTecnicoForestale: boolean = false;

  @Output() resetFormEmitter = new EventEmitter<void>();
  @Output() clearAllEmitter = new EventEmitter<void>();
  @Output() codiceFiscaleEmitter = new EventEmitter<void>();
  @Output() entePubblicoEmitter = new EventEmitter<boolean>();

  comune: ComuneModel[];
  unsubscribe$ = new Subject<void>();

  tableData: SoggettoModel[];

  personaOption = null;
  personaFisica = null;
  personaFisicaList: FatSoggetto[];

  autoComplete: FormGroup = new FormGroup({ autoProf: new FormControl() });
  emptyMessageAC: string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;

  TipoAccreditamentoEnum: any = TipoAccreditamento;
  provincies: ProvinciaModel[];

  constructor(
    private forestaliService: ForestaliService,
    private tagliService: TagliService
  ) {}

  ngOnInit() {}

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  filterComune(event) {
    console.log("filterComune", event);
    this.forestaliService
      .findComuniByNome(event.query)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ComuneModel[]) => {
        this.comune = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Comune"
        );
      });
  }

  getProvincia(event) {
    console.log("geProv", event);
    this.personalOrCompanyForm
      .get("provIscrizioneOrdine")
      .patchValue(undefined);
    this.forestaliService
      .getProvincia()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ProvinciaModel[]) => {
        this.provincies = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Provincia"
        );
      });
  }

  fillFormFields(event) {
    this.forestaliService
      .getfillFormFields(event.value)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: SoggettoModel) => {
        this.personalOrCompanyForm.reset();
        if (res) {
          this.personalOrCompanyForm.patchValue(res);
        }
        this.codiceFiscaleEmitter.emit();
      });
  }

  fillFormOnSelect(event: SoggettoModel) {
    this.personalOrCompanyForm.patchValue(event);
    console.log("event: ", this.personalOrCompanyForm);
  }

  searchSoggetti(event: any) {
    this.tagliService
      .searchSoggettiByCForName(event.query)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: SoggettoModel[]) => {
        console.log("event: ", res);
        this.personaFisicaList = res;
        console.log("event: ", this.personalOrCompanyForm);
      });
  }

  clearAll() {
    this.autoComplete.reset();
    this.personalOrCompanyForm.reset();
    this.clearAllEmitter.emit();
  }

  resetForm() {
    this.autoComplete.reset();
    this.resetFormEmitter.emit();
  }

  myself() {
    this.forestaliService
      .getSoggettiIo()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: SoggettoModel) => {
        console.log("event: ", res);
        this.personalOrCompanyForm.patchValue(res);
        console.log("event: ", this.personalOrCompanyForm);
      });
  }
}
