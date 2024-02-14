/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { FormGroup, FormBuilder } from "@angular/forms";
import { SelectItem } from "primeng/components/common/selectitem";
import { Observable, Subject } from "rxjs";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { takeUntil } from "rxjs/operators";
import { PfaConfigComponent } from "../../pfa-config";
import { TableHeader } from "src/app/shared/models/table-header";
import { Dettaglio, Content } from "src/app/shared/models/dettaglio";
import { ValidateSoggetto } from "src/app/shared/models/soggetto-data";
import { ProfiloUtenteEnum } from "src/app/shared/enums";

@Component({
  selector: "app-ricerca-pfa",
  templateUrl: "./ricerca-pfa.component.html",
  styleUrls: ["./ricerca-pfa.component.css"],
})
export class RicercaPfaComponent implements OnInit, OnDestroy {
  ricercaForm: FormGroup;
  provinciaDropdown$: Observable<SelectItem[]>;
  comuniDropdownd$: Observable<SelectItem[]>;
  comuneDropdown: SelectItem[];
  denomazioniDropdown$: Observable<SelectItem[]>;
  unsubscribe$ = new Subject<void>();
  pfas: Dettaglio[] = [];
  cols: TableHeader[];
  paging: any;
  totalCount: number;
  eventField: any;
  messageEnable: boolean;
  constructor(private fb: FormBuilder, private pfaService: PfaSampleService) {

    this.fillDropdownMenus();
    this.ricercaForm = this.fb.group({
      provincia: [""],
      comune: [{ value: null, disabled: true }],
      denominazione: [""],
      dataRilevamentoFrom: [""],
      dataRilevamentoTo: [""],
    });
  }

  ngOnInit() {
    this.pfaService.validateData().subscribe((res: ValidateSoggetto) => {
      if ( res === null || res.fkProfiloUtente !== ProfiloUtenteEnum.GESTORE_PFA) {
        this.messageEnable = true;
        
      } else {
        
        this.cols = PfaConfigComponent.getGestitiHeader();
      }
      
    });
  }

  fillDropdownMenus() {
    this.provinciaDropdown$ = this.pfaService.getAllProvincia();
  }

  fillComune() {
    if (this.ricercaForm.get("provincia").value === null) {
      this.ricercaForm.get("comune").disable();
      this.ricercaForm.get("comune").patchValue("");
    } else {
      this.pfaService
        .getComuniByProvincia(this.ricercaForm.get("provincia").value)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((resp: SelectItem[]) => {
          this.comuneDropdown = resp;
          this.ricercaForm.get("comune").enable();
        });
    }
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  getUpdatedTable(event) {
    this.paging = {
      page: event.page,
      limit: event.limit,
    };

    this.eventField = event.field;
    if (this.eventField === "dataApprovazioneString") {
      this.eventField = "dataApprovazione";
    }

    this.pfaService
      .getAllPfa(this.paging.page, this.paging.limit, this.eventField)
      .subscribe((resp: Content<Dettaglio[]>) => {
        this.pfas = resp.content;
        this.totalCount = resp.totalElements;

        this.pfas.forEach((item, index) => {
          this.pfas[index].dataApprovazione = this.pfaService.reverseDate(
            this.pfas[index].dataApprovazione
          );

          this.pfas[index].dataFineValidita = this.pfaService.reverseDate(
            this.pfas[index].dataFineValidita
          );
        });
      });
  }

  ricerca() {
    this.pfaService
      .getPfaSearch(
        this.ricercaForm.get("provincia").value,
        this.ricercaForm.get("comune").value
      )
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((resp) => (this.pfas = resp));
  }

  pulsici() {
    this.ricercaForm.reset();
    this.fillComune();
  }

  reverseDate(date: string) {
    let splitDate = date.split("-");
    let joinDate = splitDate[0];
    return joinDate;
  }

  openMappa(){
    this.pfaService.getCartograficoAllUrl(15)
    .subscribe(
      (response: any) => {
          window.open(response['geecourl'], "_blank");
      }
    );
    return false;
  }
}
