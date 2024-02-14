/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Output, EventEmitter, Input } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { Subject } from 'rxjs';
import { SoggettoModel } from "src/app/shared/models/soggetto-model";
import {
  TipoDeUtilizzatore,
  TipoUtilizzatoreRicerca,
} from "src/app/shared/enums";
import { ComuneModel } from "src/app/shared/models/comune.model";
import { Observable } from "rxjs";
import { InterventoUtilizzatore } from "src/app/shared/models/intervento-utilizzatore";
import { flattenStyles } from "@angular/platform-browser/src/dom/dom_renderer";
import { DialogService } from "src/app/shared/services/dialog.service";
import { ButtonType, DialogButtons } from "src/app/shared/models/dialog.model";
import { StepErrors } from "src/app/shared/components/steps-with-errors/steps-with-errors.component";
import { CONST } from "src/app/shared/constants";
import { takeUntil } from "rxjs/operators";
import { isNumber } from "util";

@Component({
  selector: "app-utilizzatore",
  templateUrl: "./utilizzatore.component.html",
  styleUrls: ["./utilizzatore.component.css"],
})
export class UtilizzatoreComponent implements OnInit {
  utilizzatoreForm: FormGroup;
  fisicaForm: FormGroup;
  giuridicaForm: FormGroup;
  comuneObject: ComuneModel;
  denominazioneComune: string;
  interventoUtilizzatore: InterventoUtilizzatore;
  uilizzatoreData: InterventoUtilizzatore;
  isPersona = true;
  disabled = false;
  isPfaEntePubblico:boolean = false;
  listComuni : ComuneModel[];
  comune: ComuneModel[];
  emptyMessageAC : string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;
  unsubscribe$ = new Subject<void>();
  @Input() allTecnicoForestale$: Observable<SoggettoModel>;
  @Input() tipoDeUtilizzatore: any = TipoDeUtilizzatore;
  @Input() ricercaUtilizzatore: any = TipoUtilizzatoreRicerca;
  @Input() allComuni$: Observable<ComuneModel[]>;
  @Input() idIntervento: number;
  @Input() editMode: boolean;
  @Input() idInterventoForEdit: number;
  @Output() emitIndex: EventEmitter<void> = new EventEmitter();
  @Output() emitBack: EventEmitter<void> = new EventEmitter();
  @Output() emitErrors: EventEmitter<StepErrors[]> = new EventEmitter();
  @Output() emitUtilizzatore: EventEmitter<void> = new EventEmitter();
  @Input() lastCompletedStep: number;
  
  constructor(private fb: FormBuilder, private pfaService: PfaSampleService,
    private dialogService: DialogService) {}

  ngOnInit() {
    if (this.idInterventoForEdit != undefined) {
      this.bildFormsInit();
      this.fillListComuni();
      this.pfaService
        .isPfaEntePubblico(this.idInterventoForEdit)
        .subscribe((res) => {
          this.isPfaEntePubblico = res;
        });

      this.pfaService
        .getDataForUtilizzatore(this.idInterventoForEdit)
        .subscribe((res) => {
          this.uilizzatoreData = res;

          this.utilizzatoreForm = this.fb.group({
            tipoEffettuera: [
              {
                value: this.uilizzatoreData
                  ? this.uilizzatoreData.tipoDiUtilizzatore
                  : "",
                disabled: false,
              },
            ],
            tipoUtilizzatore: [
              {
                value: this.uilizzatoreData
                  ? this.uilizzatoreData.tipoUtilizzatoreRicerca
                  : "",
                disabled: false,
              },
            ],
            tecnicoForestale: [
              {
                value: this.uilizzatoreData
                  ? this.uilizzatoreData.idTecnicoForestale
                  : "",
                disabled: false,
              },
            ],
          });

          if (
            this.uilizzatoreData.tipoUtilizzatoreRicerca ==
            TipoUtilizzatoreRicerca.PERSONA_FISICA
          ) {
            this.fisicaForm = this.fb.group({
              idSoggetto: [],
              codiceFiscale: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.codiceFiscale
                    : "",
                  disabled: true,
                },[Validators.required]
              ],
              cognomeRagSociale: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.cognome
                    : "",
                  disabled: true,
                },[Validators.required]
              ],
              nome: [
                {
                  value: this.uilizzatoreData ? this.uilizzatoreData.nome : "",
                  disabled: true,
                },[Validators.required]
              ],
              comuneResidenza: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.comune
                      ? this.uilizzatoreData.comune
                      : ""
                    : "",
                  disabled: true,
                },[Validators.required]
              ],
              indirizzoResidenza: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.indirizzo
                    : "",
                  disabled: true,
                },[Validators.required]
              ],
              civico: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.civico
                    : "",
                  disabled: true,
                },[Validators.required]
              ],
              cap: [
                {
                  value: this.uilizzatoreData ? this.uilizzatoreData.cap : "",
                  disabled: true,
                },[Validators.required]
              ],
              nrTelefonico: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.nrTelefonico
                    : "",
                  disabled: true,
                },[Validators.required]
              ],
              eMail: [
                {
                  value: this.uilizzatoreData ? this.uilizzatoreData.eMail : "",
                  disabled: true,
                },[Validators.required]
              ],
            });
            this.fisicaForm.enable();
          }
          if (
            this.uilizzatoreData.tipoUtilizzatoreRicerca ==
            TipoUtilizzatoreRicerca.PERSONA_GIURIDICA
          ) {
            this.giuridicaForm = this.fb.group({
              codiceFiscale: [
                {value: this.uilizzatoreData? this.uilizzatoreData.codiceFiscale: "",disabled: true},
                [Validators.required]
              ],
              iva: [
                {value: this.uilizzatoreData? this.uilizzatoreData.partitaIva: "",
                  disabled: true
                },[Validators.required]
              ],
              denominazione: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.denominazione
                    : "",
                  disabled: true,
                },[Validators.required]
              ],
              comune: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.comune
                    : "",
                  disabled: true,
                },[Validators.required]
              ],
              cap: [
                {
                  value: this.uilizzatoreData ? this.uilizzatoreData.cap : "",
                  disabled: true,
                },
              ],
              indirizzo: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.indirizzo
                    : "",
                  disabled: true,
                },
              ],
              civico: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.civico
                    : "",
                  disabled: true,
                },
              ],
              pec: [
                {
                  value: this.uilizzatoreData ? this.uilizzatoreData.pec : "",
                  disabled: true,
                },
              ],
              eMail: [
                {
                  value: this.uilizzatoreData ? this.uilizzatoreData.eMail : "",
                  disabled: true,
                },
              ],
              telefonico: [
                {
                  value: this.uilizzatoreData
                    ? this.uilizzatoreData.nrTelefonico
                    : "",
                  disabled: true,
                },
              ],
            });

            this.giuridicaForm.enable();
          }
        });
    } else {
      this.bildFormsInit();
    }
  }

  fillListComuni(){
    if(!this.listComuni){
       this.pfaService.getComuniList().subscribe((res:ComuneModel[]) => {
        this.listComuni = res;
      });
    }
  }

  filterComune(event) {
    this.pfaService.getComuniList().pipe(takeUntil(this.unsubscribe$)).subscribe((res: ComuneModel[]) => {
      this.comune = this.pfaService.autocompleteFilter(event, res, 'Comune');
    });
  }

  bildFormsInit() {
    this.utilizzatoreForm = this.fb.group({
      tipoEffettuera: [{ value: "" }],
      tipoUtilizzatore: [{ value: "" }],
      tecnicoForestale: [{ value: "" }],
    });
    this.giuridicaForm = this.fb.group({
      codiceFiscale: [null,[Validators.required]],
      iva: [{ value: "", disabled: true },[Validators.required]],
      denominazione: [{ value: "", disabled: true },[Validators.required]],
      comune: [{ value: "", disabled: true },[Validators.required]],
      cap: [{ value: "", disabled: true },[Validators.required]],
      indirizzo: [{ value: "", disabled: true },[Validators.required]],
      civico: [{ value: "", disabled: true },[Validators.required]],
      pec: [{ value: "", disabled: true },[Validators.required, Validators.pattern(CONST.PATTERN_MAIL)]],
      eMail: [{ value: "", disabled: true },[Validators.required, Validators.pattern(CONST.PATTERN_MAIL)]],
      telefonico: [{ value: "", disabled: true },[Validators.required, , Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
    });
    this.fisicaForm = this.fb.group({
      idSoggetto: [],
      codiceFiscale: [null,[Validators.required]],
      cognomeRagSociale: [{ value: "", disabled: true },[Validators.required]],
      nome: [{ value: "", disabled: true },[Validators.required]],
      comuneResidenza: [{ value: "", disabled: true },[Validators.required]],
      indirizzoResidenza: [{ value: "", disabled: true },[Validators.required]],
      civico: [{ value: "", disabled: true },[Validators.required]],
      cap: [{ value: "", disabled: true },[Validators.required]],
      nrTelefonico: [{ value: "", disabled: true },[Validators.required, , Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      eMail: [{ value: "", disabled: true },[Validators.required, Validators.pattern(CONST.PATTERN_MAIL)]],
    });
  }

  selectedTipologiaUtilizzatore() {
    console.log('I do nothing!!!');
  }

  enableButton() {
    this.disabled = false;
  }

  isSaveDiabled(){

    let val = this.utilizzatoreForm.get('tipoEffettuera').value;
    if(isNaN(val)){ return true}
    if(val != "2"){ return false}
    val = this.utilizzatoreForm.get('tipoUtilizzatore').value;
    if(isNaN(val)){ return true}
    if(val == "0"){
      return this.fisicaForm.invalid;
    }
    if(val == "1"){
      return this.giuridicaForm.invalid;
    }
    return isNaN(val) || (this.utilizzatoreForm.get('tipoEffettuera').value == "2")
  }

  proceed() {
    if (
      this.utilizzatoreForm.get("tipoEffettuera").value ==
        TipoDeUtilizzatore.DA_INDIVIDUARE ||
      this.utilizzatoreForm.get("tipoEffettuera").value ==
        TipoDeUtilizzatore.IN_PROPRIO
    ) {
      this.interventoUtilizzatore = {
        tipoDiUtilizzatore: this.utilizzatoreForm.get("tipoEffettuera").value,
        idTecnicoForestale: this.utilizzatoreForm.get("tecnicoForestale").value,
      };
    } else {
      let tipoUtilizzatore = this.utilizzatoreForm.get("tipoUtilizzatore").value;
      if(('' + tipoUtilizzatore).length == 0){
        this.showMsg('ATTENZIONE','Selezionare il tipo di utilizzatore e valorizzare i relativi campi');
        return;
      }
      if (tipoUtilizzatore == TipoUtilizzatoreRicerca.PERSONA_FISICA) {
        let cf = this.fisicaForm.get("codiceFiscale").value;
        let nome = this.fisicaForm.get("nome").value;
        let cognome = this.fisicaForm.get("cognomeRagSociale").value;
        if(!cf || cf == '' || !nome || nome == '' || !cognome || cognome == ''){
          this.showMsg('ATTENZIONE','Valorizzare almeno i campi: Codice fiscale, Nome e Cognome');
          return;
        }
        let comune = this.fisicaForm.get("comuneResidenza").value
        this.interventoUtilizzatore = {
          nome: this.fisicaForm.get("nome").value,
          cognome: this.fisicaForm.get("cognomeRagSociale").value,
          codiceFiscale: this.fisicaForm.get("codiceFiscale").value,
          comune: { idComune: comune? comune.idComune: null },
          indirizzo: this.fisicaForm.get("indirizzoResidenza").value,
          civico: this.fisicaForm.get("civico").value,
          cap: this.fisicaForm.get("cap").value,
          nrTelefonico: this.fisicaForm.get("nrTelefonico").value,
          eMail: this.fisicaForm.get("eMail").value,
          tipoDiUtilizzatore: this.utilizzatoreForm.get("tipoEffettuera").value,
          tipoUtilizzatoreRicerca: this.utilizzatoreForm.get("tipoUtilizzatore")
            .value,
          idTecnicoForestale: this.utilizzatoreForm.get("tecnicoForestale")
            .value,
        };
      } else {
        let cf = this.giuridicaForm.get("codiceFiscale").value;
        let denominazione = this.giuridicaForm.get("denominazione").value;
        if(!cf || cf == '' || !denominazione || denominazione == ''){
          this.showMsg('ATTENZIONE','Valorizzare almeno i campi: CodiceFiscale e Denominazione');
          return;
        }
        let comune = this.giuridicaForm.get("comune").value;
        this.interventoUtilizzatore = {
          codiceFiscale: this.giuridicaForm.get("codiceFiscale").value,
          comune: { idComune: comune? comune.idComune:null },
          indirizzo: this.giuridicaForm.get("indirizzo").value,
          civico: this.giuridicaForm.get("civico").value,
          cap: this.giuridicaForm.get("cap").value,
          nrTelefonico: this.giuridicaForm.get("telefonico").value,
          eMail: this.giuridicaForm.get("eMail").value,
          denominazione: this.giuridicaForm.get("denominazione").value,
          partitaIva: this.giuridicaForm.get("iva").value,
          pec: this.giuridicaForm.get("pec").value,
          tipoDiUtilizzatore: this.utilizzatoreForm.get("tipoEffettuera").value,
          tipoUtilizzatoreRicerca: this.utilizzatoreForm.get("tipoUtilizzatore")
            .value,
          idTecnicoForestale: this.utilizzatoreForm.get("tecnicoForestale")
            .value,
        };
      }
    }
    if(isNaN(this.interventoUtilizzatore.idTecnicoForestale)){
      delete this.interventoUtilizzatore.idTecnicoForestale;
    }
    this.pfaService
      .saveDataForUtilizzatore(
        this.interventoUtilizzatore,
        this.idInterventoForEdit?this.idInterventoForEdit:this.idIntervento
      )
      .subscribe((res) => {
        console.log(res);
        if(this.allTecnicoForestale$){
          this.emitIndex.emit();
          this.emitErrors.emit(res);
        }else{
          this.emitUtilizzatore.emit()
        }
      });
    
  }

  goToDettaglio() {
    this.emitBack.emit();
  }

  isCodiceFiscaleLengthValid(codiceFiscale:string){
    if(codiceFiscale.length != 16){
      this.dialogService.showDialogMsg('Errore','Il codice fiscale deve avere lunghezza 16!',true)
      return false;
    }
    return true;
  }

  isCodicePivaLengthValid(codiceFiscale:string){
    if(codiceFiscale.length == 11 || codiceFiscale.length == 16){
      return true;
    }else{
      this.dialogService.showDialogMsg('Errore','Il codice fiscale/piva deve avere lunghezza 11 o 16!',true)
      return false;
    }
  }

  cercaPersonaFisicaByCodiceFiscale() {
    let codiceFiscale = this.fisicaForm.get("codiceFiscale").value;
    if(!this.isCodiceFiscaleLengthValid(codiceFiscale)){
      return;
    }
    this.pfaService.getSoggettoFisicoByCodiceFiscale(codiceFiscale)
      .subscribe((res) => this.putInFrom(res));
  }

  cercaPersonaGiuridicaByCodiceFiscale() {
    let codiceFiscale = this.giuridicaForm.get("codiceFiscale").value;
    if(!this.isCodicePivaLengthValid(codiceFiscale)){
      return;
    }
    this.pfaService.getSoggettoGiuridicoByCodiceFiscale(codiceFiscale)
    .subscribe((res) => {
      if(res && res.indirizzo){
        let addr = res.indirizzo.trim();
        let idx = addr.lastIndexOf(' ');
        if(idx > -1){
          if(!isNaN(parseInt(addr.substr(idx)))){
            res.indirizzo = addr.substr(0,idx);
            res.civico = addr.substr(idx);
          }
        }
      }
      
      this.putInFrom(res)});
  }
  
  putInFrom(soggetto: SoggettoModel) {
    let tipoUtilizzatore = this.utilizzatoreForm.get("tipoUtilizzatore").value;
    let codiceFiscale;
    if (!soggetto.nome && !soggetto.denominazione) {
      this.dialogService.showDialogMsg('Info','Nessun soggetto trovato!',true)
      if(tipoUtilizzatore == TipoUtilizzatoreRicerca.PERSONA_FISICA){
        codiceFiscale = this.fisicaForm.get("codiceFiscale").value;
        this.fisicaForm.enable();
        this.fisicaForm.reset();
        this.fisicaForm.get("codiceFiscale").setValue(codiceFiscale);
      }else{
        codiceFiscale = this.fisicaForm.get("codiceFiscale").value;
        this.giuridicaForm.enable();
        this.giuridicaForm.reset();
        this.giuridicaForm.get("codiceFiscale").setValue(codiceFiscale);
      }
    } else if (tipoUtilizzatore == TipoUtilizzatoreRicerca.PERSONA_FISICA) {
      this.giuridicaForm.reset();
      this.fisicaForm.get("nome").setValue(soggetto.nome);
      this.fisicaForm.get("cognomeRagSociale").setValue(soggetto.cognome);
      this.setValueComune(soggetto.fkComune,this.fisicaForm.get("comuneResidenza"));
      this.fisicaForm.get("indirizzoResidenza").setValue(soggetto.indirizzo);
      this.fisicaForm.get("civico").setValue(soggetto.civico);
      this.fisicaForm.get("cap").setValue(soggetto.cap);
      this.fisicaForm.get("nrTelefonico").setValue(soggetto.nrTelefonico);
      this.fisicaForm.get("eMail").setValue(soggetto.eMail);
      this.fisicaForm.enable();
    } else {
      this.fisicaForm.reset();
      this.giuridicaForm.get("iva").setValue(soggetto.partitaIva);
      this.giuridicaForm.get("denominazione").setValue(soggetto.denominazione);
      this.setValueComune(soggetto.fkComune,this.giuridicaForm.get("comune"));
      this.giuridicaForm.get("cap").setValue(soggetto.cap);
      this.giuridicaForm.get("indirizzo").setValue(soggetto.indirizzo);
      this.giuridicaForm.get("civico").setValue(soggetto.civico);
      this.giuridicaForm.get("pec").setValue(soggetto.pec);
      this.giuridicaForm.get("eMail").setValue(soggetto.eMail);
      this.giuridicaForm.get("telefonico").setValue(soggetto.nrTelefonico);
      this.giuridicaForm.enable();
      window['form'] = this.fisicaForm;
    }
  }

  setValueComune(id, control){
    this.pfaService.getComuneById(id).subscribe((res) => {
      control.setValue(res);
    });
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

  cambiaUtilizzatore(){
    alert('todo');
  }
}
