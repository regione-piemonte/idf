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

import { ForestaliService } from "src/app/features/istanze-forestali/services/forestali.service";
import { SoggettoModel } from "../../models/soggetto.model";
import { ComuneModel } from "../../models/comune.model";
import { SelectItem } from "primeng/api";
import { CONST } from "../../constants";
import { TagliService } from "src/app/features/istanze-forestali/services/tagli.service";
import { FatSoggetto } from "src/app/shared/models/fat-soggetto.model";
import { TipoAccreditamento } from "src/app/shared/models/tipo-accreditamento.model";
import { AziendaTAIF } from "./../../models/azienda-taif.model";
import { IstanzaInviata } from "src/app/shared/models/view-instance";

@Component({
  selector: "app-pg-form-tagli",
  templateUrl: "./pg-form-tagli.component.html",
  styleUrls: ["./pg-form-tagli.component.css"],
})
export class PGFormTagliComponent implements OnInit, OnDestroy {
  @Input() isProprietaPubblica:boolean;
  @Input() isProprietaPrivata:boolean;
  @Input() personalOrCompanyForm: FormGroup;
  @Input() set personaDatiOption(personaDatiOption: string) {
    this.personaOption = personaDatiOption;
    this.personaGiurdica =
      personaDatiOption === "RG" ||
      personaDatiOption === "PG" ||
      personaDatiOption === "SG";
  }

  @Input() codiceFiscaleSelectItem: SelectItem[];
  @Input() codiceFiscaleSONOIO: SelectItem[];

  @Input() tipoAccreditamento: string = null;
  @Input() isEntePubblico: boolean = null;

  @Input() loadFromAnagrafica: boolean = false;

  @Input() isTAIF: boolean = false;
  @Input() isUtilizzatore: boolean = false;
  @Input() isFormVisible: boolean = false;

  @Output() changePGEmitter = new EventEmitter<void>();
  @Output() chooseCompanyEmitter = new EventEmitter<any>();
  @Output() resetFormEmitter = new EventEmitter<void>();
  @Output() clearAllEmitter = new EventEmitter<void>();
  @Output() codiceFiscaleEmitter = new EventEmitter<void>();
  @Output() entePubblicoEmitter = new EventEmitter<boolean>();

  comune: ComuneModel[];
  unsubscribe$ = new Subject<void>();
  tableData: SoggettoModel[];
  personaOption = null;
  personaGiurdica = null;

  personaGiuridicaList: FatSoggetto[];
  professionista: any[];

  autoComplete: FormGroup = new FormGroup({ autoProf: new FormControl() });
  emptyMessageAC: string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;

  aziendaTAIFselected: AziendaTAIF = null;
  taifOptions: AziendaTAIF[] = [];
  TipoAccreditamentoEnum: any = TipoAccreditamento;
  showAlboForestale: boolean = true;

  constructor(
    private forestaliService: ForestaliService,
    private tagliService: TagliService
  ) {}

  ngOnInit() {
    console.log(this.personaGiuridicaList);

    if (this.personalOrCompanyForm.get("codiceFiscale").value) {
      this.isFormVisible = true;
    }

    if (
      !this.isFormVisible &&
      this.tipoAccreditamento !== this.TipoAccreditamentoEnum.PROFESSIONISTA
    ) {
      this.changeTipoPG(this.isEntePubblico ? "EN" : "AZ");
    }

    if (this.personalOrCompanyForm.get("taif")) {
      this.personalOrCompanyForm
        .get("taif")
        .setValue(this.isTAIF, { emitEvent: false });
    }

    if (this.isTAIF) {
      this.tagliService.getAziendeTAIF().subscribe((res) => {
        if (res.length > 0) {
          this.taifOptions = res.sort((a, b) =>
            a.ragioneSociale.trim() > b.ragioneSociale.trim() ? 1 : -1
          );
          const sogtaif =
            this.personalOrCompanyForm.get("soggettoTaif").value.value;
          let option = this.taifOptions.find(
            (e) => e.codiceFiscale === sogtaif.codiceFiscale
          );
          option.label =
            option.ragioneSociale +
            " - " +
            option.codiceFiscale +
            " - nr albo " +
            option.numeroAlbo;
          this.personalOrCompanyForm.patchValue({
            soggettoTaif: option,
          });
        } 
      });
    }
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  filterComune(event) {
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

  filterTaif(event){
    console.log(event);
    
    this.tagliService.getAziendeTAIF().subscribe((res) => {
      if (res.length > 0) {
        this.taifOptions = res.sort((a, b) =>
          a.ragioneSociale.trim() > b.ragioneSociale.trim() ? 1 : -1
        );
        if(event.query != ""){
          this.taifOptions = this.taifOptions.filter(el=> {
            const filterString = `${el.ragioneSociale} - ${el.codiceFiscale} - nr albo ${el.numeroAlbo}`
           return filterString.toLocaleLowerCase().includes(event.query.toLocaleLowerCase())
          })
        }
      } 
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

  fillFormOnSelect(event) {
    this.personalOrCompanyForm.reset();
    
    if(event.nrTelefonico != null && event.nrTelefonico.indexOf(" ") > 0){
      event.nrTelefonico = event.nrTelefonico.replaceAll(" ","");
    }
    
    this.personalOrCompanyForm.patchValue(event);
    this.isFormVisible = true;
  }

  filterProfessionista(event) {
    this.forestaliService
      .searchAllProfessionista()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.professionista = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Professionista"
        );
        this.loadFromAnagrafica = true;
      });
  }

  searchAziende(event: any) {
    this.tagliService
      .searchAziendeByCForDenom(event.query, this.isEntePubblico)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: FatSoggetto[]) => {
        this.personaGiuridicaList = res;
      });
  }

  changeTipoPG(choice) {
    this.autoComplete.reset();
    this.personalOrCompanyForm.reset();
    this.isEntePubblico = choice === "EN";

    if (
      this.tipoAccreditamento === this.TipoAccreditamentoEnum.RICHIEDENTE ||
      this.tipoAccreditamento === this.TipoAccreditamentoEnum.PROFESSIONISTA ||
      this.tipoAccreditamento === this.TipoAccreditamentoEnum.SPORTELLO
    ) {
      this.changePGEmitter.emit(choice);
    }
  }

  chooseCompany(item, skipDisable) {
    this.isFormVisible = true;
    this.showAlboForestale = true;
    if (!skipDisable) {
      this.personalOrCompanyForm.get("codiceFiscale").disable();
      this.personalOrCompanyForm.get("partitaIva").disable();
      this.personalOrCompanyForm.get("denominazione").disable();
    }
    this.chooseCompanyEmitter.emit({
      event: item,
      isPubblic: this.isEntePubblico,
    });
  }

  chooseCompanyProf(item) {
    this.isFormVisible = true;
    this.chooseCompanyEmitter.emit({
      event: item,
      isPubblic: this.isEntePubblico,
    });
  }

  enterCompanyData() {
    if (this.tipoAccreditamento === TipoAccreditamento.RICHIEDENTE) {
      this.showAlboForestale = false;
    }
    this.autoComplete.reset();
    this.isFormVisible = true;
    this.personalOrCompanyForm.reset();
    this.personalOrCompanyForm.get("codiceFiscale").enable();
    this.personalOrCompanyForm.get("partitaIva").enable();
    this.personalOrCompanyForm.get("denominazione").enable();
    this.loadFromAnagrafica = false;
    this.changeTipoPG(this.isEntePubblico ? "EN" : "AZ");
  }

  iscrizioneAlboChange(e) {
    this.isTAIF = this.personalOrCompanyForm.get(e.target.name).value;

    if (this.isTAIF) {
      this.tagliService.getAziendeTAIF().subscribe((res: AziendaTAIF[]) => {
        if (res.length > 0) {
          this.taifOptions = res.sort((a, b) =>
            a.ragioneSociale.trim() > b.ragioneSociale.trim() ? 1 : -1
          );
        }
      });
    }
  }

  changeTaif(opt) {
    this.aziendaTAIFselected = opt;
  }
 
  customTaif(item){
    console.log(item);
    
    if(item.ragioneSociale == undefined){
      return "Seleziona azienda"
    }
    return `${item.ragioneSociale} - ${item.codiceFiscale} - nr albo ${item.numeroAlbo}`
  }
 

  clearAll() {
    this.autoComplete.reset();
    this.personalOrCompanyForm.reset();
    this.clearAllEmitter.emit();
    this.loadFromAnagrafica = false;
  }

  resetForm() {
    this.autoComplete.reset();
    this.resetFormEmitter.emit();
  }
}
