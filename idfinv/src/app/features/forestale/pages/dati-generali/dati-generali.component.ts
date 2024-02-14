/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy, Input } from "@angular/core";
import { ForestaleSampleService } from "src/app/shared/services/forestale-sample.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Soggetto } from "src/app/shared/models/soggetto";
import { DialogService } from 'src/app/shared/services/dialog.service';
import { DialogButtons, ButtonType, DialogIconType } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Subject } from "rxjs";
import { takeUntil, map } from "rxjs/operators";
import { Router, ActivatedRoute } from "@angular/router";
import { User } from "src/app/shared/models/user";
import { SelectItem } from "primeng/components/common/selectitem";
import { SearchService } from "src/app/services/search.service";
import { GeneraliService } from "src/app/services/generali.service";
import { AreaDiSaggioSave } from "src/app/shared/models/area-di-saggio-save";
import { CONST } from "src/app/shared/constants";
import { AreaDiSaggio } from "src/app/shared/models/area-di-saggio";
import { AmbitoRilievo } from "src/app/shared/models/ambito-rilievo";
import { TabsService } from "src/app/services/tabs.service";

@Component({
  selector: "app-dati-generali",
  templateUrl: "./dati-generali.component.html",
  styleUrls: ["./dati-generali.component.css"]
})
export class DatiGeneraliComponent implements OnInit, OnDestroy {
  datiGeneraliForm: FormGroup;
  userData: User;
  unsubscribe$: Subject<void> = new Subject<void>();
  allDropdownMenus: any;
  testUser: any;
  comuneDropdown: SelectItem[] = [];
  dettaglioAmbitoDropdown: SelectItem[] = [];
  ambitoSpecifico: boolean;
  items: AmbitoRilievo[] = [];
  datiGenerali: AreaDiSaggio;
  idgeoPtAds: number = 48;
  basicInfoSubmitted = false;
  obj: AmbitoRilievo;
  constructor(
    private home : TabsService,
    private forestaleService: ForestaleSampleService,
    private fb: FormBuilder,
    private router: Router,
    private searchService: SearchService,
    private generaliService: GeneraliService,
    private dialogService: DialogService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {

    this.idgeoPtAds = this.route.snapshot.params['id'];

    this.testUser = {
      codiceFiscale: "123123",
      cognome: "marko",
      nome: "marko",
      ruolo: "test",
      provider: "test"
    };
    this.getUser();
    
    if (this.idgeoPtAds) {
      this.forestaleService
        .postUser(this.userData)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response: User) => {
          console.log("RESPONSE POST", response);
          this.userData = response;
          if (response) {
            this.loadDatiGenerali();
          }
        });
      
    } else {
      this.datiGenerali = new AreaDiSaggio();
      
      this.buildGeneraliForm(null);
    }


    this.allDropdownMenus = this.fillDropdownMenus();


  }

loadDatiGenerali(){
  this.generaliService.getDatiGenerali(this.idgeoPtAds).subscribe(
    (response: AreaDiSaggio) => {
      this.datiGenerali = response;
      this.buildGeneraliForm(this.datiGenerali);
    }
  );
}

getUser(){
  this.userData = JSON.parse(sessionStorage.getItem("user"));
  
}
  buildGeneraliForm(datiAreaDiSaggio: AreaDiSaggio) {
    datiAreaDiSaggio = datiAreaDiSaggio?datiAreaDiSaggio:new AreaDiSaggio();
    this.datiGeneraliForm = this.fb.group({
      idgeoPtAds: [datiAreaDiSaggio.idgeoPtAds ? datiAreaDiSaggio.idgeoPtAds : ""],
      codiceADS: [datiAreaDiSaggio.codiceADS ? datiAreaDiSaggio.codiceADS : "",[Validators.required, Validators.maxLength(20), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      tipologiaDiRilievo: [{ value: datiAreaDiSaggio.tipologia ? datiAreaDiSaggio.tipologia : "",disabled : false }, Validators.required],
      ambitoDiRilevamento: [{ value: datiAreaDiSaggio.ambitoDiRilevamento ? datiAreaDiSaggio.ambitoDiRilevamento : null, disabled: true }, Validators.required],
      idDettaglioAmbito: [{ value: datiAreaDiSaggio.dettaglioAmbito ? datiAreaDiSaggio.dettaglioAmbito : null, disabled: true }],
      ambitoSpecifico: [{ value: datiAreaDiSaggio.ambitoSpecifico ? datiAreaDiSaggio.ambitoSpecifico : null, disabled: true }],
      dataRilevamento: [{ value: datiAreaDiSaggio.dataRilevamento ? new Date(datiAreaDiSaggio.dataRilevamento) : null, disabled: true }, Validators.required],
      rilevatore: [{ value: this.userData ? this.userData.nome : "", disabled: true }],
      comune: [datiAreaDiSaggio.comune ? datiAreaDiSaggio.comune : { value: null, disabled: true }, this.isTipologiaRelascopica(datiAreaDiSaggio.tipologia)?[]: [Validators.required]],
      provincia: [datiAreaDiSaggio.provincia ? datiAreaDiSaggio.provincia : "", this.isTipologiaRelascopica(datiAreaDiSaggio.tipologia)?[]: [Validators.required]],
      utmEST: [datiAreaDiSaggio.utmEst ? datiAreaDiSaggio.utmEst : "", this.getUtmValidators(datiAreaDiSaggio.tipologia)],
      fkSoggettoPg: ["1"],
      fkSoggettoPf: ["1"],
      utmNORD: [datiAreaDiSaggio.utmNord ? datiAreaDiSaggio.utmNord : "", this.getUtmValidators(datiAreaDiSaggio.tipologia)],
      quota: [{ value: datiAreaDiSaggio.quota ? datiAreaDiSaggio.quota : null, disabled: true }, [Validators.required, Validators.maxLength(4), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      espozione: [{ value: datiAreaDiSaggio.espozione ? datiAreaDiSaggio.espozione : null, disabled: true }, Validators.required],
      inclinazione: [{ value: datiAreaDiSaggio.inclinazione ? datiAreaDiSaggio.inclinazione : null, disabled: true }, [Validators.required,Validators.maxLength(2), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      particellaForestale: [{ value: datiAreaDiSaggio.particellaForestale ? datiAreaDiSaggio.particellaForestale : null, disabled: true }, [Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      proprieta: [{ value: datiAreaDiSaggio.proprieta ? datiAreaDiSaggio.proprieta : null, disabled: true }, Validators.required]
    });
    this.fillComune();
    this.fillDettaglioAmbito();
    console.log(this.datiGeneraliForm.value);
    this.basicInfoSubmitted = false;
  }

  isTipologiaRelascopica(tipologiaRilievo: String){
    return tipologiaRilievo == '2' || tipologiaRilievo == '3'
  }

  getUtmValidators(tipologiaRilievo: String){
    if(this.isTipologiaRelascopica(tipologiaRilievo)){
      return [Validators.pattern(CONST.PATTERN_DECIMAL)];
    }
    return [Validators.required, Validators.pattern(CONST.PATTERN_DECIMAL)]
  }

  tipologiDiRilievoChange(){
    let values = this.datiGeneraliForm.value
    values.tipologia = values.tipologiaDiRilievo;
    this.buildGeneraliForm(values);
    console.log(this.buildGeneraliForm)
  }

  fillDropdownMenus() {
    
    const obj = {
      tipologiaDiRilievo: this.searchService.getTipoAdsCleaned(),

      ambitoDiRilievo: this.forestaleService.getAmbitoRilievoSpecificareRaw().pipe(map((response: AmbitoRilievo[]) => {
        this.items = response;
        const ambitoToDropdown: SelectItem[] = [];
        response.forEach((item, i) => {
          ambitoToDropdown.push({
            label: item.descrAmbito,
            value: item.idAmbito,
          });
        });
        return ambitoToDropdown;
      })),

      // dettaglioAmbito: this.forestaleService.getDropdownTest(),
      dataDiRilievo: this.forestaleService.getDropdownTest(),
      provincia: this.forestaleService.getAllProvincia(),
      // comune: this.forestaleService.getDropdownTest(),
      esposizione: this.generaliService.getEsposizione(),
      proprieta: this.generaliService.getProprieta()
    };

    return obj;
  }

  resetForm(){
    this.buildGeneraliForm(this.datiGenerali);
    this.enableInputComponents(true);
  }

  components = ["codiceADS","tipologiaDiRilievo", "ambitoDiRilevamento", "idDettaglioAmbito", 
    "ambitoSpecifico", "dataRilevamento",    "comune", "provincia", "utmEST", "fkSoggettoPg",
     "fkSoggettoPf", "utmNORD", "quota", "espozione",    "inclinazione", "particellaForestale", "proprieta"]

  enableInputComponents(firstPart:boolean) {
    console.log('Enabling compos');

    let inputFirstPart = ['tipologiaDiRilievo','codiceADS','utmEST','utmNORD','comune','provincia']
    
    this.components.forEach(component => {
      if(inputFirstPart.includes(component)){
        firstPart==true?this.datiGeneraliForm.get(component).enable():this.datiGeneraliForm.get(component).enable();
      }else{
        firstPart==true?this.datiGeneraliForm.get(component).disable():this.datiGeneraliForm.get(component).enable();
      }
      
    });
    
    // if (!firstPart && this.datiGeneraliForm.get("ambitoDiRilevamento").value) 
    //   { this.datiGeneraliForm.get("idDettaglioAmbito").enable() }
  }

  saveBasicInfo(event: AreaDiSaggioSave) {
    console.log('SAVING BASIC INFO:');
    const areaDiSaggioSave: AreaDiSaggioSave = this.createAreaDiSagio(event);

    this.generaliService.saveDatiGenerali(areaDiSaggioSave)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: any) => {
          if(response.error){
            this.dialogService.showDialog(true, response.error, 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
              if (buttonId === ButtonType.OK_BUTTON) {
              }
            }, DialogIconType.WARNING);
            return;
          }
          if (!this.idgeoPtAds) {
            this.idgeoPtAds = response;
            this.datiGeneraliForm.get("idgeoPtAds").patchValue(this.idgeoPtAds);
          }
          this.basicInfoSubmitted = true; 
          this.enableInputComponents(false);
        });


  }

  fillComune() {
    if (!this.datiGeneraliForm.get("provincia").value) {
      //this.datiGeneraliForm.get("comune").disable();
      this.datiGeneraliForm.get("comune").patchValue("");
    } else {
      console.log('provincia:', this.datiGeneraliForm.get("provincia").value);

      this.forestaleService
        .getComuniByProvincia(this.datiGeneraliForm.get("provincia").value)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response: SelectItem[]) => {
          this.comuneDropdown = response;
          // this.datiGeneraliForm.get("comune").patchValue(this.datiGeneraliForm.get("comune").value);
          //this.datiGeneraliForm.get("comune").enable();
          console.log('form:', this.datiGeneraliForm.value);

        });
    }
  }

  fillDettaglioAmbito() {
    const dettaglioAmbitoDropdown: SelectItem[] = [];
    console.log('idDettaglioAmbito:', this.datiGeneraliForm.get("idDettaglioAmbito").value);
    if (this.datiGeneraliForm.get("ambitoDiRilevamento").value === null) {
      this.datiGeneraliForm.get("idDettaglioAmbito").patchValue("");
    } else {
      this.forestaleService
        .getDettaglioAmbitoByIdAmbito(this.datiGeneraliForm.get("ambitoDiRilevamento").value)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response: SelectItem[]) => {
          let item: SelectItem[] = response;
          this.dettaglioAmbitoDropdown = item;
          this.dettaglioAmbitoDropdown.unshift({ value: -1,label: 'Inserisci nuovo'})
          // item.forEach(item => this.obj = item.value);
          // const index = item.findIndex(dettaglioAmbito => {
          //   console.log("ambito", dettaglioAmbito.value);
          //   let dettaglioAmbitoValue = this.datiGeneraliForm.get("idDettaglioAmbito");
          //   return dettaglioAmbito.value.idAmbito == dettaglioAmbitoValue? dettaglioAmbitoValue.value.idAmbito:null;
          // });
          // console.log("index: ", index);
          // if (index >= 0) {
          //   this.datiGeneraliForm.get("idDettaglioAmbito").patchValue(item[index].value);
          // }
          // this.datiGeneraliForm.get("idDettaglioAmbito").enable();

        });
    }

  }

  enableAmbitoSpecifico() {
    let cmpDettaglioAmbito = this.datiGeneraliForm.get("idDettaglioAmbito");
    let cmpAmbitoSpecifico = this.datiGeneraliForm.get("ambitoSpecifico");
    console.log("sta je ovo? ", cmpDettaglioAmbito.value);

    cmpAmbitoSpecifico.patchValue("");
    if (cmpDettaglioAmbito.value == -1) {
      cmpAmbitoSpecifico.enable()
    } else {
      cmpAmbitoSpecifico.disable();
    }
  }
  sendGeneraliForm(event) {
    event.dataRilevamento = this.forestaleService.formatDate(event.dataRilevamento);
    const areaDiSaggioSave: AreaDiSaggioSave = this.createAreaDiSagio(event);
    this.generaliService.saveDatiGenerali(areaDiSaggioSave)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: any) => {
          if(response.error){
            this.dialogService.showDialog(true, response.error, 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
              if (buttonId === ButtonType.OK_BUTTON) {
              }
            }, DialogIconType.WARNING);
          }else{
            if (event.tipologiaDiRilievo === 1) {
              this.router.navigate(["/forestale", "steps", this.idgeoPtAds]);
            } else if (event.tipologiaDiRilievo === 2) {
              this.router.navigate(["/forestale", "modifica", this.idgeoPtAds]);
            } else if (event.tipologiaDiRilievo === 3) {
              this.router.navigate(["/forestale", "modifica", this.idgeoPtAds]);
            }
          }
        }
      );
    // if (event.tipologiaDiRilievo === 1) {
    //   this.router.navigate(["/forestale", "steps"]);
    // } else if (event.tipologiaDiRilievo === 2) {
    //   this.router.navigate(["/forestale", "modifica"]);
    // } else if (event.tipologiaDiRilievo === 3) {
    //   this.router.navigate(["/forestale", "modifica"]);
    // }
  }

  createAreaDiSagio(event: any): AreaDiSaggioSave {
    event.dataRilevamento = this.forestaleService.formatDate(event.dataRilevamento);
    const areaDiSaggioSave: AreaDiSaggioSave = {
      idgeoPtAds: event.idgeoPtAds,
      codiceADS: event.codiceADS,
      tipologiaDiRilievo: event.tipologiaDiRilievo,
      ambitoDiRilevamento: event.ambitoDiRilevamento,
      idDettaglioAmbito: event.idDettaglioAmbito ? event.idDettaglioAmbito : null,
      ambitoSpecifico: event.ambitoSpecifico,
      dataRilevamento: event.dataRilevamento ? event.dataRilevamento : null,
      comune: event.comune,
      utmEST: event.utmEST,
      // fkSoggettoPg: event.fkSoggettoPg,
      // fkSoggettoPf: event.fkSoggettoPf,
      utmNORD: event.utmNORD,
      quota: event.quota,
      espozione: event.espozione,
      inclinazione: event.inclinazione,
      particellaForestale: event.particellaForestale,
      proprieta: event.proprieta
    };
    return areaDiSaggioSave;
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
}
