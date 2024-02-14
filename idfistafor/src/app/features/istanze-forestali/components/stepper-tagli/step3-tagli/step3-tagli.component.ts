/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  OnDestroy,
  Input,
  Output,
  EventEmitter,
  ViewChild,
} from "@angular/core";
import { FormGroup, FormBuilder, FormArray, Validators } from "@angular/forms";
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import { Router } from "@angular/router";
import { Observable, Subject, of } from "rxjs";
import { CONST } from "src/app/shared/constants";

import {
  ParticleCadastral,
  ShowParcel,
} from "src/app/shared/models/particle-cadastral";
import { UtilService } from "src/app/shared/services/util.service";
import { ForestaliService } from "../../../services/forestali.service";
import { VincoloService } from "../../../services/vincolo.service";
import { TagliService } from "./../../../services/tagli.service";
import { TagliStep3 } from "./tagli-step3.model";
import { FasciaAltimetrica } from "src/app/shared/models/fascia-altimetrica.model";
import { GeometryResultTagliComponent } from "./geometry-result-tagli/geometry-result-tagli.component";
import { RicadenzaIntervento } from "src/app/shared/models/ricadenzaIntervento.model";
import { catchError, map } from "rxjs/operators";
import { DialogService } from "src/app/shared/services/dialog.service";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";

@Component({
  selector: "step3-tagli",
  templateUrl: "./step3-tagli.component.html",
  styleUrls: ["./step3-tagli.component.css"],
})
export class Step3TagliComponent implements OnInit, OnDestroy {
  @Input() editMode: number;
  @Input() thirdFormData: any;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;

  @Output() emitNextStep = new EventEmitter<void>();
  @Output() emitThirdFormData = new EventEmitter<void>();
  @ViewChild(GeometryResultTagliComponent) geometryResultTagliComponent;

  polygonForm: FormGroup;
  stepDataToSend: TagliStep3 = {
    particelleCatastali: [],
    ricadenzaPfa: false,
  };
  unsubscribe$ = new Subject<void>();
  isTableEmpty: boolean;
  parcelsTable: ShowParcel[] = [];
  transferSecondFormData: TagliStep3;
  annotazioni = "";
  invalid = true;
  showError = false;
  showGeeco: boolean = false;
  mapUrl: SafeResourceUrl;
  accordionHeders: string[] = CONST.ACCORDION_STEP2;
  isUpdate: boolean;
  hiddenNote = true;
  fasceAltimetriche: FasciaAltimetrica[] = [];
  tableData: RicadenzaIntervento[] = [];
  isOpenGeeco = true;
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private utilService: UtilService,
    private forestaliService: ForestaliService,
    private sanitizer: DomSanitizer,
    private vincoloService: VincoloService,
    private tagliService: TagliService,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    this.isTableEmpty = this.thirdFormData.particelleCatastali ? false : true;

    if (this.thirdFormData.totaleSuperficieIntervento > 0) {
      this.createPolygonForm(this.thirdFormData);
      this.tagliService.getFasceAltimetriche().subscribe((res) => {
        this.fasceAltimetriche = res;
      });
      this.annotazioni = this.thirdFormData.annotazioni;
    }

    this.tagliService.getStepNumber(this.editMode).subscribe((res) => {
      this.isUpdate = res.nextStep > 2;
    });

    console.log("====================================");
    console.log("STEP3 INIT ");
    console.log("FORM ", this.thirdFormData);
    console.log("====================================", this.editMode);

    /*if( this.thirdFormData===null){
    this.thirdFormData = this.tagliService.getStep3(this.editMode);
  }*/
    this.getPolygonEvent();
    this.thirdFormData["particelleCatastali"]
      ? (this.stepDataToSend = JSON.parse(JSON.stringify(this.thirdFormData)))
      : (this.stepDataToSend.ricadenzaPfa = this.thirdFormData["ricadenzaPfa"]);

    this.parcelsTable = this.thirdFormData["particelleCatastali"]
      ? this.thirdFormData["particelleCatastali"]
      : [];

    if (this.parcelsTable && this.parcelsTable.length > 0) {
      this.parcelsTable.forEach((item, index) => {
        this.parcelsTable[index].comune =
          this.thirdFormData["particelleCatastali"][
            index
          ].comune.denominazioneComune;
      });

      // popola tableData che viene passato in input a geometry-result
      this.tableData = this.thirdFormData.ricadenzaIntervento;
      

      //this.getPolygonEvent();
      this.calculateFieldsOfForm();
      this.parcelsTable.sort(this.compareParticelle);
    }
  }

  ngOnDestroy() {}

  openGeeco() {
    this.createGeeko().subscribe((resp) => {
      if (!resp) {
        this.continueWhitError();
      } else {
        window.location.href = resp["geecourl"];
      }
    });
  }

  showDialogMsg(msgType: string, msg: string) {
    this.dialogService.showDialog(
      true,
      msg,
      msgType,
      DialogButtons.OK,
      "",
      (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
        }
      }
    );
  }
  createGeeko(): Observable<any> {
    return this.forestaliService
      .getCartograficoByIdUrl(22, "" + this.editMode)
      .pipe(
        map((resp: any) => {
          return resp;
        }),
        catchError((err) => {
          return of(undefined);
        })
      );
  }
  continueWhitError() {
    this.tagliService.getStep3(this.editMode).subscribe((resp) => {
      this.thirdFormData = resp;
      this.parcelsTable = resp["particelleCatastali"];
      if (this.parcelsTable.length > 0) {
        this.parcelsTable.forEach((item, index) => {
          this.parcelsTable[index].comune =
            resp["particelleCatastali"][index].comune.denominazioneComune;
        });
      }
      this.parcelsTable.sort(this.compareParticelle);
      this.createPolygonForm(resp);
      if (this.thirdFormData) {
        const { particelleCatastali } = this.thirdFormData;
        const { comune, foglio, sezione, particella } = particelleCatastali[0];
        this.showDialogMsg(
          "Attenzione",
          `La particella nr ${particella}, foglio ${foglio}, sezione ${sezione} , comune ${comune} ha una geometria 
          con errori topologici e non è gestibile dal sistema. Si consiglia di 
          eliminarla e di segnalare il problema nel campo 'note del richiedente' nell'ultima schermata di inserimento.`
        );
      }
      this.tagliService.getFasceAltimetriche().subscribe((res) => {
        this.fasceAltimetriche = res;
      });
    });
  }

  closeGeeco() {
    //window.scrollTo(0, 0);
    //this.showGeeco = false;
    this.router.navigate(["return-modifica-tagli/" + this.editMode]);
  }

  getPolygonEvent() {
    this.forestaliService.polygonEmitter.subscribe((response) => {
      console.log("polygonEmitterService");
      this.polyEvent();
    });
    console.log("polygonEmitter");
    this.invalid = false;
  }

  polyEvent() {
    delete this.stepDataToSend.totaleSuperficieCatastale;
    delete this.stepDataToSend.totaleSuperficieIntervento;
    delete this.stepDataToSend.ricadenzaAreeProtette;
    delete this.stepDataToSend.ricadenzaNatura2000;
    delete this.stepDataToSend.ricadenzaPopolamentiDaSeme;
    delete this.stepDataToSend.ricadenzaPortaSeme;
    delete this.stepDataToSend.ricadenzaCategorieForestali;

    delete this.stepDataToSend.annotazioni;
    delete this.stepDataToSend.ricadenzaPfa;

    this.calculateFieldsOfForm();
    this.invalid = false;
  }

  calculateFieldsOfForm() {
    const localParticelle = { particelleCatastali: [] };

    this.stepDataToSend.particelleCatastali.forEach((item) => {
      if (item.toDelete === false) {
        localParticelle["particelleCatastali"].push(item);
      }
    });

    this.tagliService.getRicadenze(this.editMode).subscribe((response: any) => {
      this.transferSecondFormData = {
        totaleSuperficieCatastale:
          response.totaleSuperficieCatastale ||
          response.totaleSuperficieCatastale == 0
            ? this.utilService.format4Decimals(
                JSON.stringify(response.totaleSuperficieCatastale)
              )
            : "",
        totaleSuperficieIntervento:
          response.totaleSuperficieIntervento ||
          response.totaleSuperficieIntervento == 0
            ? this.utilService.format4Decimals(
                JSON.stringify(response.totaleSuperficieIntervento)
              )
            : "",

        ricadenzaPfa: response.ricadenzaPfa,
      };
      response.annotazioni = this.annotazioni;
      response.fasciaAltimetrica =
        this.polygonForm.get("fasciaAltimetrica").value || "";
      response.totaleSuperficieCatastale =
        this.transferSecondFormData.totaleSuperficieCatastale;
      response.totaleSuperficieIntervento =
        this.transferSecondFormData.totaleSuperficieIntervento;

      this.createPolygonForm(response);
      if(response.ricadenzaPfa != null ){
        this.showDialogMsg(
          "Attenzione",
          `Attenzione, una parte dell'area ricade in zona Pgf. Non è possibile proseguire.`
        );
      }
    });
  }

  changeValueAnnotazioni(event) {
    this.annotazioni = event.target.value;
  }

  createPolygonForm(polygonData: any) {
    this.polygonForm = this.fb.group({
      particelleCatastali: [
        {
          value: polygonData ? polygonData.totaleSuperficieCatastale : "",
          disabled: true,
        },
        Validators.required,
      ],
      totaleSuperficieIntervento: [
        {
          value: polygonData ? polygonData.totaleSuperficieIntervento : "",
          disabled: true,
        },
        Validators.required,
      ],

      ricadenzaAreeProtette: this.fb.array([]),
      ricadenzaNatura2000: this.fb.array([]),
      ricadenzaPopolamentiDaSeme: this.fb.array([]),
      ricadenzaPortaSeme: this.fb.array([]),
      ricadenzaCategorieForestali: this.fb.array([]),
      ricadenzaPfa: [
        { value: polygonData.ricadenzaPfa ? polygonData.ricadenzaPfa : false },
      ],
      fasciaAltimetrica: [
        polygonData ? polygonData.fasciaAltimetrica : null,
        Validators.required,
      ],

      annotazioni: [polygonData ? polygonData.annotazioni : this.annotazioni],
    });

    this.pushAreaProtette(polygonData.ricadenzaAreeProtette);
    let localNatura;

    if (polygonData.ricadenzaNatura2000)
      localNatura = JSON.parse(JSON.stringify(polygonData.ricadenzaNatura2000));

    if (localNatura) this.pushRicadenzaNatura2000(localNatura);

    if (polygonData.ricadenzaPopolamentiDaSeme)
      this.pushPopolamentiDaSeme(polygonData.ricadenzaPopolamentiDaSeme);

    if (polygonData.ricadenzaPortaSeme)
      this.pushPortaSeme(polygonData.ricadenzaPortaSeme);

    if (polygonData.ricadenzaCategorieForestali)
      this.pushCategorieForestali(polygonData.ricadenzaCategorieForestali);
  }

  pushAreaProtette(ricadenzaAreeProtette: any[]) {
    if (ricadenzaAreeProtette) {
      ricadenzaAreeProtette = this.filterDuplicates(
        ricadenzaAreeProtette,
        "codiceAmministrativo"
      );
      console.log("testareaprotte:");
      ricadenzaAreeProtette.forEach((item) => {
        console.log("testareaprotte:---> ", item);
        (this.polygonForm.get("ricadenzaAreeProtette") as FormArray).push(
          this.areeProtette(item)
        );
      });
    }
  }

  pushCategorieForestali(ricadenzaCategorieForestali: any) {
    if (ricadenzaCategorieForestali) {
      ricadenzaCategorieForestali = this.filterDuplicates(
        ricadenzaCategorieForestali,
        "codiceAmministrativo"
      );
      ricadenzaCategorieForestali.forEach((item) => {
        (this.polygonForm.get("ricadenzaCategorieForestali") as FormArray).push(
          this.areeProtette(item)
        );
      });
    }
  }

  filterDuplicates(array, key) {
    return (array = array.filter(
      (item, index, self) =>
        index === self.findIndex((t) => t[key] === item[key])
    ));
  }

  pushRicadenzaNatura2000(ricadenzaNatura2000: any) {
    if (ricadenzaNatura2000) {
      // FILTER DUPLICATES
      ricadenzaNatura2000 = this.filterDuplicates(
        ricadenzaNatura2000,
        "codiceAmministrativo"
      );
      ricadenzaNatura2000.forEach((item) => {
        (this.polygonForm.get("ricadenzaNatura2000") as FormArray).push(
          this.areeProtette(item)
        );
      });
    }
  }

  pushPopolamentiDaSeme(ricadenzaPopolamentiDaSeme: any) {
    if (ricadenzaPopolamentiDaSeme) {
      ricadenzaPopolamentiDaSeme = this.filterDuplicates(
        ricadenzaPopolamentiDaSeme,
        "codiceAmministrativo"
      );
      ricadenzaPopolamentiDaSeme.forEach((item) => {
        (this.polygonForm.get("ricadenzaPopolamentiDaSeme") as FormArray).push(
          this.areeProtette(item)
        );
      });
    }
  }

  pushPortaSeme(ricadenzaPortaSeme: any) {
    if (ricadenzaPortaSeme) {
      ricadenzaPortaSeme = this.filterDuplicates(
        ricadenzaPortaSeme,
        "localita"
      );
      ricadenzaPortaSeme.forEach((item) => {
        (this.polygonForm.get("ricadenzaPortaSeme") as FormArray).push(
          this.areePortaseme(item) // aqui mismo ese
        );
      });
    }
  }

  validateForm() {
    if (!this.polygonForm.valid) {
      this.showError = true;
      return false;
    }
    return true;
  }
  save(nextStep?: boolean) {
    this.showError = false;
    if (!this.validateForm()) return;
    const step3Form: TagliStep3 = {
      particelleCatastali: this.stepDataToSend.particelleCatastali,
      ricadenzaAreeProtette: this.polygonForm.get("ricadenzaAreeProtette")
        .value,
      ricadenzaNatura2000: this.polygonForm.get("ricadenzaNatura2000").value,
      ricadenzaPopolamentiDaSeme: this.polygonForm.get(
        "ricadenzaPopolamentiDaSeme"
      ).value,
      ricadenzaPortaSeme: this.polygonForm.get("ricadenzaPortaSeme").value,
      ricadenzaCategorieForestali: this.polygonForm.get(
        "ricadenzaCategorieForestali"
      ).value,

      totaleSuperficieCatastale: this.polygonForm.get("particelleCatastali")
        .value,
      totaleSuperficieIntervento: this.polygonForm.get(
        "totaleSuperficieIntervento"
      ).value,

      fasciaAltimetrica: this.polygonForm.get("fasciaAltimetrica").value,

      annotazioni: this.polygonForm.get("annotazioni").value,
    };

    //Dal poligono Geeco non riesce a prendere i dati dal portaseme
    step3Form.ricadenzaPortaSeme = this.thirdFormData.ricadenzaPortaSeme;

    const localParticelle = [];
    step3Form.particelleCatastali.forEach((item) => {
      if (
        "id" in item ||
        (!item.hasOwnProperty("id") && item.toDelete === false)
      )
        localParticelle.push(item);
    });

    step3Form.ricadenzaIntervento = this.thirdFormData.ricadenzaIntervento;

    step3Form.particelleCatastali = localParticelle;
    if (step3Form.particelleCatastali.length > 0 || +step3Form.totaleSuperficieIntervento > 0) {
      if (this.isUpdate) {
        this.tagliService
          .putStep3(step3Form, this.editMode)
          .subscribe((response: any) => {
            nextStep && this.emitNextStep.emit();
          });
      } else {
        this.tagliService
          .postStep3(step3Form, this.isTableEmpty, this.editMode)
          .subscribe((response: any) => {
            nextStep && this.emitNextStep.emit();
          });
      }
    }
  }

  continue() {
    this.save(true);
  }

  areeProtette(item) {
    return this.fb.group({
      codiceAmministrativo: [item ? item.codiceAmministrativo : ""],
      nome: [item ? item.nome : ""],
      percentualeRicadenza: [item ? item.percentualeRicadenza : ""],
    });
  }

  areePortaseme(item) {
    return this.fb.group({
      id: [item ? item.id : ""],
      localita: [item ? item.localita : ""],
      specie: [item ? item.specie : ""],
    });
  }

  emitedShowParcels(event: ParticleCadastral) {
    this.parcelsTable.push({
      id: event.id,
      comune: event.comune.denominazioneComune,
      sezione: event.sezione,
      foglio: event.foglio,
      particella: event.particella,
      supCatastale: event.supCatastale.toString().includes('.') ? event.supCatastale.toString().replace('.',',') : event.supCatastale,
    });

    this.parcelsTable.sort(this.compareParticelle);

    this.stepDataToSend["particelleCatastali"].push({
      id: event.id,
      comune: event.comune,
      sezione: event.sezione,
      foglio: event.foglio,
      particella: event.particella,
      supCatastale: event.supCatastale,
      toDelete: false,
    });

    this.invalid = true;
  }

  deleteRowGetter(event) {
    let obj = this.parcelsTable.find((row, index) => {
      return index === event.index;
    });
    if (obj && obj.id) {
      this.tagliService
        .deletePlainParticella(this.editMode, obj.id)
        .subscribe((res) => {
          this.parcelsTable.splice(event.index, 1);
          let particella = { particelleCatastali: [] };
          particella.particelleCatastali =
            this.stepDataToSend.particelleCatastali.filter((par) => {
              return par.id !== obj.id;
            });
          this.stepDataToSend.particelleCatastali =
            particella.particelleCatastali;
        });
    }
    this.invalid = true;
    // test
    this.polygonForm = null;
  }

  compareParticelle(p1: ShowParcel, p2: ShowParcel) {
    if (p1.comune != p2.comune) {
      return p1.comune < p2.comune ? -1 : 1;
    }
    if (p1.sezione && p2.sezione && p1.sezione.length != p2.sezione.length) {
      return p1.sezione.length < p2.sezione.length ? -1 : 1;
    }
    if (p1.sezione != p2.sezione) {
      return p1.sezione < p2.sezione ? -1 : 1;
    }
    if (p1.foglio != p2.foglio) {
      return p1.foglio < p2.foglio ? -1 : 1;
    }
    if (p1.particella.length != p2.particella.length) {
      return p1.particella.length < p2.particella.length ? -1 : 1;
    }
    if (p1.particella != p2.particella) {
      return p1.particella < p2.particella ? -1 : 1;
    }
    return 0;
  }
}
