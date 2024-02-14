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
import { CONST } from "src/app/shared/constants";

@Component({
  selector: "app-ricerca-pfa",
  templateUrl: "./ricerca-pfa.component.html",
  styleUrls: ["./ricerca-pfa.component.css"],
})
export class RicercaPfaComponent implements OnInit, OnDestroy {
  it: any = CONST.IT;
  currentYear: number = new Date().getFullYear();
  ricercaForm: FormGroup;
  provinciaDropdown$: Observable<SelectItem[]>;
  comuniDropdownd$: Observable<SelectItem[]>;
  comuneDropdown: SelectItem[];
  denominazioniListSaved: any;
  denominazioniList: any;
  denomazioniDropdown$: Observable<SelectItem[]>;
  unsubscribe$ = new Subject<void>();
  pfas: Dettaglio[] = [];
  cols: TableHeader[];
  paging: any= {page: 1,limit: 5};
  totalCount: number;
  eventField: any;
  denominazioneFilter: any = {};
  messageEnable: boolean;
  searchEnable: boolean =false
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
    this.cols = PfaConfigComponent.getGestitiHeader();
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

    this.runRicerca();
  }

  formatDataDettaglio(){
    this.pfas.forEach((item, index) => {
      this.pfas[index].dataApprovazione = this.pfaService.reverseDate(
        this.pfas[index].dataApprovazione
      );
      this.pfas[index].dataFineValidita = this.pfaService.reverseDate(
        this.pfas[index].dataFineValidita
      );
    });
  }

  ricerca(){
    this.paging.page = 1;
    this.searchEnable = true;
    this.runRicerca();
    
  }

  runRicerca() {
    if(this.searchEnable){
      this.pfaService
        .getPfaSearch(this.ricercaForm.value,this.paging)
        .subscribe((resp) => {
          this.pfas = resp.content;
          this.totalCount = resp.totalElements;
          this.formatDataDettaglio();
        });
    }
  }

  pulsici() {
    this.ricercaForm.reset();
    this.fillComune();
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

  filterDenominazione(event) {
    let formValues =  this.ricercaForm.value;
    let istatProv = formValues.provincia?formValues.provincia:'';
    let idComune = formValues.comune?formValues.comune:'';
    if(istatProv == this.denominazioneFilter.istatProv && idComune == this.denominazioneFilter.idComune ){
      this.filterDenominazioneDesc(event?event.query:null);
    }else{
      this.pfaService.filterDenominazione(istatProv, idComune).subscribe((res: any[]) => {
        this.denominazioneFilter.istatProv = istatProv;
        this.denominazioneFilter.idComune = idComune;
        this.denominazioniListSaved = res;
        this.filterDenominazioneDesc(event?event.query:null);
    });
    }
  }

  filterDenominazioneDesc(descr){
    if(descr && descr.length > 0){
      this.denominazioniList =  this.denominazioniListSaved.filter((item) => 
      item['label'].toLowerCase().indexOf(descr) !== -1);
    }else{
      this.denominazioniList =  this.denominazioniListSaved;
    }
    
  }

}
