/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, EventEmitter, Output, Input } from "@angular/core";
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormArray,
  FormControl,
} from "@angular/forms";
import { Observable, Subject } from "rxjs";
import { EventoCorrelato } from "src/app/shared/models/progressiviNomi";
import { DestLegnami } from "src/app/shared/models/destLegnami";
import { FinalitaTaglie } from "src/app/shared/models/finalTaglie";
import { TipoInterventi } from "src/app/shared/models/tipoInterventi";
import { Governi } from "src/app/shared/models/governi";
import { Esbosco } from "src/app/shared/models/esbosco";
import { UsoViabilita } from "src/app/shared/models/viabilita";
import { FasciaAltimetrica } from "src/app/shared/models/fascia-altimetrica";
import { Specie } from "src/app/shared/models/specie";
import { Priorita } from "src/app/shared/models/priorita";
import { ConformeDerogaEnum } from "src/app/shared/enums";
import {
  DatiTehnici,
  IntervSelvicolturale,
  TipoIntervento,
  SpeciePfaIntervento,
} from "src/app/shared/models/dati-tehnici";
import { StatoIntervento } from "src/app/shared/models/stato-intervento";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { CONST } from "src/app/shared/constants";
import { ShootingMirrorModel } from "src/app/shared/models/shooting-mirror";
import { PfaConfigComponent } from "../../pfa-config";
import { TableHeader } from "src/app/shared/models/table-header";
import { ConverstionVolume } from "src/app/shared/models/util-conversion";
import { StepErrors } from "src/app/shared/components/steps-with-errors/steps-with-errors.component";
import { DialogService } from "src/app/shared/services/dialog.service";
import { ButtonType, DialogButtons } from "src/app/shared/models/dialog.model";

@Component({
  selector: "app-dati-tecnici",
  templateUrl: "./dati-tecnici.component.html",
  styleUrls: ["./dati-tecnici.component.css"],
})
export class DatiTecniciComponent implements OnInit {
  @Input() tipoForm: FormGroup;
  @Input() specieForm: FormGroup;
  @Input() plantsForm: FormGroup;
  @Input() eventoCorrelato$: Observable<EventoCorrelato>;
  @Input() destLegnami$: Observable<DestLegnami>;
  @Input() finaliteTaglie$: Observable<FinalitaTaglie>;
  @Input() tipoInterventi$: Observable<TipoInterventi>;
  @Input() governi$: Observable<Governi>;
  @Input() esbosco$: Observable<Esbosco>;
  @Input() usoViabilita$: Observable<UsoViabilita>;
  @Input() fasciaAltimetrica$: Observable<FasciaAltimetrica>;
  @Input() specie$: Observable<Specie>;
  // @Input() priorita$: Observable<Priorita>;
  @Input() statoIntervento$: Observable<StatoIntervento>;
  @Input() idIntervento: number;
  @Input() editMode: boolean;
  @Input() idInterventoForEdit: number;
  // @Input() idGeoPFA: number;
  @Input() pfaPlanId: number;
  @Input() lastCompletedStep: number;
  @Input() conformeDerogaValue: string;
  unsubscribe$ = new Subject<void>();
  conformeDeroga: any;
  checkPiedilista = false;
  checkPiedilistaValue: number = 1;
  @Output() emitIndex: EventEmitter<any> = new EventEmitter<any>();
  @Output() emitBack: EventEmitter<void> = new EventEmitter();
  tableHeaders: TableHeader[] = PfaConfigComponent.getShootingMirrorHeader();
  @Output() emitRemoveRow: EventEmitter<number> = new EventEmitter();
  @Output() emitAddRow: EventEmitter<void> = new EventEmitter();
  @Output() emitErrors: EventEmitter<StepErrors[]> = new EventEmitter();
  @Output() saveData: EventEmitter<void> = new EventEmitter();
  
  listSpecie: Specie[];

  activeIndex: number;
  isDisabled: boolean;
  isVisible: boolean;
  
  index: number = 0;

  it: any = CONST.IT;

  shootingMirrorData: ShootingMirrorModel[];
  idTipoIstanza: number;
  currentYear: number = new Date().getFullYear();
  intervSelvicolturale: IntervSelvicolturale;
  tipoIntervento: TipoIntervento;
  datiTehnici: DatiTehnici;
  datiTehniciEdit: DatiTehnici;
  speciePfaIntervento: SpeciePfaIntervento[] = [];

  constructor(private pfaService: PfaSampleService,
    private dialogService: DialogService,
    private fb: FormBuilder) {}

  ngOnInit() {
    this.activeIndex = 0;
    this.isDisabled = true;
    this.isVisible = false;
    this.conformeDeroga = ConformeDerogaEnum;
    this.pfaService.getAllSpecie()
      .subscribe((response) => {
      this.listSpecie = response;
    });
  
  }
  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  showMsg(msgType,msg){
    this.dialogService.showDialog(
      true,
      msg,
      msgType,
      DialogButtons.OK,
      "",
      (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
          console.log("BUTTON WORKS");
        }
      }
    );
  }

  deleteFiledValue(index: any) {
    this.emitRemoveRow.emit(index);
    this.index--;
    this.plantsForm.get("volumeRamaglia").setValue(null);
    this.plantsForm.get("stimaMassaRetraibile").setValue(null);
  }

  goToDettaglio() {
    this.emitBack.emit();
  }

  save() {
    this.saveDatiTehnici();
  }

  addSpecie() {
    this.emitAddRow.emit();
    this.index++;
    this.plantsForm.get("volumeRamaglia").setValue(null);
    this.plantsForm.get("stimaMassaRetraibile").setValue(null);
  }

  saveDatiTehnici() {
 
    if (this.tipoForm.get("interventionPlan").value === "C") {
      this.idTipoIstanza = 2;
    } else {
      this.idTipoIstanza = 3;
    }
    this.tipoIntervento = {
      conformeDeroga: this.tipoForm.get("interventionPlan").value,
      // progressivoNumerico: this.tipoForm.get("noIntervento").value,
      fkStatoIntervento: this.tipoForm.get("statoIntervento").value,
      dataPresuntaIntervento: this.pfaService.formatDate(
        this.tipoForm.get("dataIntervento").value
      ),
      annataSilvana: this.tipoForm.get("sylvanYear").value,
      // idParticelaForestale: this.tipoForm.get("noParticleForest").value,
      idEventoCorelato: this.tipoForm.get("relatedEvent").value,
      fkGoverno: this.tipoForm.get("government").value,
      richiedePiedilsta: this.tipoForm.get("piedilista").value === true ? 1 : 0,
      descrizione: this.tipoForm.get("description").value,
      localita: this.tipoForm.get("localita").value,
      superficieInteressata: this.tipoForm.get("superInteressata").value,
      fkTipoIntervento: this.tipoForm.get("tipoIntervento").value,
      fasciaAltimetrica: this.tipoForm.get("fasciaAltemetrica").value,
      fkFinalitaTaglio: this.tipoForm.get("finalitaTaglio").value,
      fkDestLegname: this.tipoForm.get("destinazioneLegname").value,
    };
    this.intervSelvicolturale = {
      idgeoPlPfa: this.pfaPlanId,
      idIntervento: this.idIntervento,
      fkTipoIntervento: this.tipoForm.get("tipoIntervento").value,
      fkStatoIntervento: this.tipoForm.get("statoIntervento").value,
      fkFinalitaTaglio: this.tipoForm.get("finalitaTaglio").value,
      fkDestLegname: this.tipoForm.get("destinazioneLegname").value,
      dataPresaInCarico: this.pfaService.formatDate(
        this.tipoForm.get("dataIntervento").value
      ),
      fkFasciaAltimetrica: this.tipoForm.get("fasciaAltemetrica").value,
      annataSilvana: this.tipoForm.get("sylvanYear").value,
      nrPiante: this.plantsForm.get("numeroDiPiante").value,
      stimaMassaRetraibileM3: this.plantsForm.get("stimaMassaRetraibile").value,
      volumeRamagliaM3: this.plantsForm.get("volumeRamaglia").value,
      nrProgressivoInterv: this.tipoForm.get("noIntervento").value,
      flgIstanzaTaglio: 1,
      flgPiedilista: this.tipoForm.get("piedilista").value === true ? 1 : 0,
      flgConformeDeroga: this.tipoForm.get("interventionPlan").value,
      noteEsbosco: this.plantsForm.get("noteEsbosco").value,
      fkGoverno: this.tipoForm.get("government").value,
      codEsbosco: this.plantsForm.get("tipoEsbosco").value,
      idUsoViabilita: this.plantsForm.get("esbosco").value,
    };
    this.speciePfaIntervento = [];
    this.specieForm.get("specie").value.map((s) =>{
      if(('' + s.specie).length > 0 && s.priorita.length > 0 && s.m3.length > 0 ){
        this.speciePfaIntervento.push({
          idSpecie: s.specie,
          volumeSpecia: +s.m3.replace(',','.'),
          flgSpeciePriorita: s.priorita,
          fkUdm: 3,
        })
      }
    });
    this.datiTehnici = {
      idTipoIstanza: this.idTipoIstanza,
      tipoIntervento: this.tipoIntervento,
      intervSelvicolturale: this.intervSelvicolturale,
      speciePfaIntervento: this.speciePfaIntervento,
    };
    this.pfaService
      .putDatiTehnici(this.datiTehnici, this.idInterventoForEdit)
      .subscribe((response: StepErrors[]) => {
        this.emitErrors.emit(response);
        this.emitIndex.emit(this.idInterventoForEdit);
      });
    
  }

  getShootingMirrorData() {
    this.pfaService
      .getDataForShootingMirror(this.idIntervento)
      .subscribe((res) => {
        this.shootingMirrorData = res;
        this.isVisible = true;
      });
  }

  shootingMirror(dati: any) {}

  continue() {
    this.isDisabled = false;
    this.activeIndex = 1;
  }

  firstAccord(event) {
    this.activeIndex = event.index;

    this.isDisabled = true;
    if (this.activeIndex === 1) {
      this.isDisabled = false;
    }
  }
  showForm() {
    this.isVisible = false;
  }

  msgValorizzaSpecie(){
    this.showMsg('ATTENZIONE','Valorizzare prima la specie!')
  }

  // REFACTOR THIS INTO SMALLER PARTS
  m3ToTon(index) {
    let idSpecie =  ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"] as FormControl)["specie"].value;
    if(idSpecie == ''){
      this.msgValorizzaSpecie();
    }
    let control =  ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"] as FormControl)["m3"];
    let mThree =control.value;
    if(mThree.indexOf('.') > -1){
      control.setValue(mThree.replace('.',','));
    }

    ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"] as FormControl)["ton"]
    .setValue(ConverstionVolume.m3ToTon(mThree,this.getDensitaSpecie(idSpecie)));

    ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"] as FormControl)["q"]
    .setValue(ConverstionVolume.m3ToQ(mThree,this.getDensitaSpecie(idSpecie)));
    this.volumeStima();
  }

  tonToM3(index) {
    let idSpecie =  ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"] as FormControl)["specie"].value;
    if(idSpecie == ''){
      this.msgValorizzaSpecie();
    }
    let control = ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"] as FormControl)["ton"];
    let ton = control.value;

    ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"] as FormControl)["m3"]
    .setValue(ConverstionVolume.tonToM3(ton,this.getDensitaSpecie(idSpecie)));

    ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"
    ] as FormControl)["q"].setValue(ConverstionVolume.tonToQ(ton));
    this.volumeStima();
  }

  qToM3(index) {
    let idSpecie =  ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"] as FormControl)["specie"].value;
    if(idSpecie == ''){
      this.msgValorizzaSpecie();
    }
    let specie =  ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"] as FormControl)["specie"].value;
    let q = ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"
    ] as FormControl)["q"].value;
    

    ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"
    ] as FormControl)["m3"].setValue(ConverstionVolume.qToM3(q,this.getDensitaSpecie(idSpecie)));

    ((this.specieForm.get("specie") as FormArray).at(index)[
      "controls"
    ] as FormControl)["ton"].setValue(ConverstionVolume.qToTon(q));
    this.volumeStima();
  }

  getInt(val:any){
    const parsed = Number.parseInt(val);
  if (Number.isNaN(parsed)) {
    return 0;
  }
  return parsed
  }

  volumeStima() {
    let specieVolume = 0;
    let specieList = this.specieForm.get("specie")['controls'];
    if(specieList && specieList.length > 0){
      specieList.forEach(element => {
        specieVolume += this.getInt(element.value.m3);
      })
    }

    let volumeRamaglia = this.plantsForm.get("volumeRamaglia").value;
    this.plantsForm
      .get("stimaMassaRetraibile")
      .setValue(specieVolume - volumeRamaglia);
  }

  conformeDerogaVal(value) {
    if (value === "C") {
      this.conformeDerogaValue = "CS";
    } else {
      this.conformeDerogaValue = "AUT";
    }
  }

  resetVolume(idx){
    let controls = this.specieForm.get('specie')['controls'][idx];
    controls.get('m3').patchValue('');
    controls.get('ton').patchValue('');
    controls.get('q').patchValue('');
  }

  onSpecieQualitaChange(form:FormGroup, idx){
    console.log('onSpecieQualitaChange');
    this.resetVolume(idx)
    let controls =  this.specieForm.get('specie')['controls'];
    for(let i=0; i< controls.length;i++){
      if(i != idx){
        if(controls[i].get('specie').value == form.get('specie').value 
          && !this.isSpecieReplicable(form.get('specie').value)){
            this.showMsg('ATTENZIONE','La specie selezionata non può essere inserita due volte');
            form.get('specie').patchValue('');
        }else if(controls[i].get('specie').value == form.get('specie').value 
          && controls[i].get('priorita').value == form.get('priorita').value){
            this.showMsg('ATTENZIONE','Valori duplicati per specie e qualità');
            form.get('priorita').patchValue('');
        }
      }
    }
  }

  isSpecieReplicable(idSpecie){
    let item = (this.listSpecie.filter(item => item.idSpecie === parseInt(idSpecie)));
    return item[0].flgConiflatif == 'L';
  }

  getDensitaSpecie(idSpecie){
    let item = (this.listSpecie.filter(item => item.idSpecie === parseInt(idSpecie)));
    return item[0].densita?item[0].densita:1;
  }

  // filterAllegatiById(keys:number[]) {
  //   return (this.tipoAllegatoAll.filter(
  //     (item) => keys.indexOf(item.idTipoAllegato) > -1
  //   ));
  // }

  onChangeGoverno(){
    let idGov = this.tipoForm.get('government').value;
    this.tipoForm.get('tipoIntervento').patchValue('');
    this.tipoInterventi$ = this.pfaService.getTipoInterventiByGoverno(idGov);
  }


  isSpecieError(cmp){
     if(cmp.touched && cmp.errors){
      return true;
    }
    return false;
  }

  onNumPianteChange(){
    let val:string = this.plantsForm.get('numeroDiPiante').value + '';
    if(val.length > 3){
      val = val.substring(0,3);
    }
    if(isNaN(+val)){
      val = '';
    }
    this.plantsForm.get('numeroDiPiante').patchValue(+val);
  }
  
}
