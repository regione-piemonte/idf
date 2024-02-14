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
} from "@angular/core";
import { FormGroup, FormBuilder, FormArray, Validators } from "@angular/forms";
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import { Router } from "@angular/router";
import { Subject } from "rxjs";
import { CONST } from "src/app/shared/constants";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";

import {
  ParticleCadastral,
  ShowParcel,
} from "src/app/shared/models/particle-cadastral";
import { DialogService } from "src/app/shared/services/dialog.service";
import { ForestaliService } from "../../../services/forestali.service";

@Component({
  selector: "app-step2",
  templateUrl: "./step2.component.html",
  styleUrls: ["./step2.component.css"],
})
export class Step2Component implements OnInit, OnDestroy {
  @Input() editMode: number;
  @Input() secondFormData: any;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;

  @Output() emitNextStep = new EventEmitter<void>();
  @Output() emitSecondFormData = new EventEmitter<void>();

  polygonForm: FormGroup;
  secondStepDataToSend: any = {
    particelleCatastali: [],
    ricadenzaVincoloIdrogeologico: false,
  };
  unsubscribe$ = new Subject<void>();
  isTableEmpty: boolean;
  secondStepTable: ShowParcel[] = [];
  transferSecondFormData: any;
  annotazioni = "";
  invalid = true;
  showGeeco: boolean = false;
  mapUrl: SafeResourceUrl;
  accordionHeders: string[] = CONST.ACCORDION_STEP2;

  constructor(
    private fb: FormBuilder,
    private forestaliService: ForestaliService,
    private sanitizer: DomSanitizer,
    private router: Router,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    console.log("isTableEmpty?: ", this.secondFormData.particelleCatastali);
    this.isTableEmpty = this.secondFormData.particelleCatastali ? false : true;
    if (
      this.isTableEmpty &&
      window.location.href.indexOf("/return-modifica") > -1
    ) {
      this.showDialogMsg(
        "Attenzione",
        "Il sistema non è stato in grado di recuperare automaticamente le particelle catastali interessate: impossibile proseguire. Contattare l'assistenza."
      );
    }
    if (this.secondFormData.totaleSuperficieCatastale) {
      this.createPolygonForm(this.secondFormData);
      this.annotazioni = this.secondFormData.annotazioni;
    }
    this.getPolygonEvent();
    this.secondFormData["particelleCatastali"]
      ? (this.secondStepDataToSend = JSON.parse(
          JSON.stringify(this.secondFormData)
        ))
      : (this.secondStepDataToSend.ricadenzaVincoloIdrogeologico =
          this.secondFormData["ricadenzaVincoloIdrogeologico"]);
    this.secondStepTable = this.secondFormData["particelleCatastali"]
      ? this.secondFormData["particelleCatastali"]
      : [];
    if (this.secondStepTable && this.secondStepTable.length > 0) {
      this.secondStepTable.forEach((item, index) => {
        this.secondStepTable[index].comune =
          this.secondFormData["particelleCatastali"][
            index
          ].comune.denominazioneComune;
      });
      this.calculateFieldsOfForm();
      this.secondStepTable.sort(this.compareParticelle);
    }
  }

  ngOnDestroy() {}

  // openGeeco() {
  //   //window.scrollTo(0, 0);
  //   this.forestaliService
  //     .getCartograficoByIdUrl(5, "" + this.editMode)
  //     .subscribe((response: any) => {
  //       this.mapUrl = this.sanitizer.bypassSecurityTrustResourceUrl(
  //         response["geecourl"]
  //       );
  //       if (window.location.href.indexOf("http://localhost:") == -1) {
  //         window.location.href = response["geecourl"];
  //       } else {
  //         //this.showGeeco = true;
  //         window.open(response["geecourl"], "_blank");
  //       }
  //     });
  //   return false;
  // }
  openGeeco() {
    this.forestaliService
      .getCartograficoByIdUrl(5, "" + this.editMode) // aqui estaba el 5
      .subscribe((response: any) => {
        console.log("Response: ", response["geecourl"]);
        //this.getPolygonEvent();
        /*this.mapUrl = this.sanitizer.bypassSecurityTrustResourceUrl(
          response["geecourl"]
        );*/
        //if (window.location.href.indexOf("http://localhost:") == -1) {
        // console.log("Response: response");
        //window.open(response["geecourl"], "_blank");
        //} else {
        //console.log("Response: _blank");
        //this.showGeeco = true;
        window.location.href = response["geecourl"];
        //window.open(response["geecourl"]);
        //}
      });
    return true;
  }

  closeGeeco() {
    //window.scrollTo(0, 0);
    //this.showGeeco = false;
    this.router.navigate(["return-modifica/" + this.editMode]);
  }

  getPolygonEvent() {
    this.forestaliService.polygonEmitter.subscribe((response) => {
      this.polyEvent();
    });
    this.invalid = false;
  }

  polyEvent() {
    delete this.secondStepDataToSend.totaleSuperficieCatastale;
    delete this.secondStepDataToSend.totaleSuperficieTrasf;
    delete this.secondStepDataToSend.ricadenzaVincoloIdrogeologico;
    delete this.secondStepDataToSend.ricadenzaAreeProtette;
    delete this.secondStepDataToSend.ricadenzaNatura2000;
    delete this.secondStepDataToSend.ricadenzaPopolamentiDaSeme;
    delete this.secondStepDataToSend.ricadenzaCategorieForestali;
    /* delete this.secondStepDataToSend.annotazioni; */
    // this.secondStepDataToSend.particelleCatastali = localPericelle;
    this.calculateFieldsOfForm();
    this.invalid = false;
  }

  calculateFieldsOfForm() {
    const localPericelle = { particelleCatastali: [] };
    this.secondStepDataToSend.particelleCatastali.forEach((item) => {
      if (item.toDelete === false) {
        localPericelle["particelleCatastali"].push(item);
      }
    });
    //this.forestaliService..postSecondTableData(localPericelle)
    this.forestaliService
      .getSecondStepRicadenze(this.editMode)
      .subscribe((response: any) => {
        this.transferSecondFormData = {
          totaleSuperficieCatastale: response.totaleSuperficieCatastale
            ? JSON.parse(JSON.stringify(response.totaleSuperficieCatastale))
            : "",
          totaleSuperficieTrasf: response.totaleSuperficieTrasf
            ? JSON.parse(JSON.stringify(response.totaleSuperficieTrasf))
            : "",
          ricadenzaVincoloIdrogeologico: response.ricadenzaVincoloIdrogeologico
            ? JSON.parse(JSON.stringify(response.ricadenzaVincoloIdrogeologico))
            : "",
        };
        response.annotazioni = this.annotazioni;
        this.createPolygonForm(response);
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
      totaleSuperficieTransf: [
        {
          value: polygonData ? polygonData.totaleSuperficieTrasf : "",
          disabled: true,
        },
        Validators.required,
      ],
      ricadenzaAreeProtette: this.fb.array([]),
      ricadenzaNatura2000: this.fb.array([]),
      ricadenzaPopolamentiDaSeme: this.fb.array([]),
      ricadenzaCategorieForestali: this.fb.array([]),
      // tslint:disable-next-line: max-line-length
      ricadenzaVincoloIdrogeologico: [
        {
          value: polygonData ? polygonData.ricadenzaVincoloIdrogeologico : "",
          disabled: true,
        },
        Validators.required,
      ],
      annotazioni: [polygonData ? polygonData.annotazioni : this.annotazioni],
    });

    this.pushAreaProtette(polygonData.ricadenzaAreeProtette);
    let localNatura;
    if (polygonData.ricadenzaNatura2000) {
      localNatura = JSON.parse(JSON.stringify(polygonData.ricadenzaNatura2000));
    }
    if (localNatura) {
      this.pushRicadenzaNatura2000(localNatura);
    }
    if (polygonData.ricadenzaPopolamentiDaSeme) {
      this.pushPopolamentiDaSeme(polygonData.ricadenzaPopolamentiDaSeme);
    }
    if (polygonData.ricadenzaCategorieForestali) {
      this.pushCategorieForestali(polygonData.ricadenzaCategorieForestali);
    }
  }

  pushAreaProtette(ricadenzaAreeProtette: any[]) {
    if (ricadenzaAreeProtette) {
      ricadenzaAreeProtette = this.filterDuplicates(
        ricadenzaAreeProtette,
        "codiceAmministrativo"
      );
      ricadenzaAreeProtette.forEach((item) => {
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

  save(nextStep?: boolean) {
    const secondForm = {
      particelleCatastali: this.secondStepDataToSend.particelleCatastali,
      ricadenzaAreeProtette: this.polygonForm.get("ricadenzaAreeProtette")
        .value,
      ricadenzaNatura2000: this.polygonForm.get("ricadenzaNatura2000").value,
      ricadenzaPopolamentiDaSeme: this.polygonForm.get(
        "ricadenzaPopolamentiDaSeme"
      ).value,
      ricadenzaCategorieForestali: this.polygonForm.get(
        "ricadenzaCategorieForestali"
      ).value,
      ricadenzaVincoloIdrogeologico: this.polygonForm.get(
        "ricadenzaVincoloIdrogeologico"
      ).value,
      totaleSuperficieCatastale:
        this.transferSecondFormData.totaleSuperficieCatastale,
      totaleSuperficieTrasf: this.transferSecondFormData.totaleSuperficieTrasf,
      annotazioni: this.polygonForm.get("annotazioni").value,
    };
    const localPericelle = [];
    secondForm.particelleCatastali.forEach((item) => {
      if (
        "id" in item ||
        (!item.hasOwnProperty("id") && item.toDelete === false)
      ) {
        localPericelle.push(item);
      }
    });
    secondForm.particelleCatastali = localPericelle;
    if (secondForm.particelleCatastali.length > 0) {
      this.forestaliService
        .saveStep2(secondForm, this.isTableEmpty, this.editMode)
        .subscribe((response: any) => {
          nextStep && this.emitNextStep.emit();
        });
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

  emitedShowParcels(event: ParticleCadastral) {
    this.secondStepTable.push({
      id: event.id,
      comune: event.comune.denominazioneComune,
      sezione: event.sezione,
      foglio: event.foglio,
      particella: event.particella,
      supCatastale: event.supCatastale.toString().includes('.') ? event.supCatastale.toString().replace('.',',') : event.supCatastale,
    });
    this.secondStepTable.sort(this.compareParticelle);
    this.secondStepDataToSend["particelleCatastali"].push({
      id: event.id,
      comune: event.comune,
      sezione: event.sezione,
      foglio: event.foglio,
      particella: event.particella,
      supCatastale: event.supCatastale,
      toDelete: false,
    });
    this.invalid = true;
    // let particella = { particelleCatastali: this.secondStepDataToSend.particelleCatastali }
    //this.forestaliService.postSecondTableData(particella)
    //this.forestaliService.getSecondStepRicadenze(this.editMode)
    // .subscribe(res => {
    //   this.createPolygonForm(res);
    // })
  }

  deleteRowGetter(event) {
    let obj = this.secondStepTable.find((row, index) => {
      return index === event.index;
    });
    if (obj && obj.id) {
      this.forestaliService
        .deletePlainParticella(this.editMode, obj.id)
        .subscribe((res) => {
          this.secondStepTable.splice(event.index, 1);
          let particella = { particelleCatastali: [] };
          particella.particelleCatastali =
            this.secondStepDataToSend.particelleCatastali.filter((par) => {
              return par.id !== obj.id;
            });
          this.secondStepDataToSend.particelleCatastali =
            particella.particelleCatastali;
          //this.forestaliService.postSecondTableData(particella)
          // this.forestaliService.getSecondStepRicadenze(this.editMode)
          // .subscribe(res => {
          //   this.createPolygonForm(res);
          // })
        });
    }
    this.invalid = true;
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
}
