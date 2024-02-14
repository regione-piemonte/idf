/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  OnDestroy,
  EventEmitter,
  Output,
  Input,
} from "@angular/core";
import { Subject } from "rxjs";
import { ForestaliService } from "../../../services/forestali.service";
import {
  FormArray,
  FormGroup,
  FormBuilder,
  Validators,
  ValidatorFn,
} from "@angular/forms";
import { DialogService } from "src/app/shared/services/dialog.service";
import { TagliService } from "src/app/features/istanze-forestali/services/tagli.service";
import { Governo } from "src/app/shared/models/governo.model";
import {
  Intervento,
  IntervSelvicolturale,
  TagliStep4,
} from "./tagli-step4.model";
import { TipoIntervento } from "src/app/shared/models/tipo-intervento";
import { Specie } from "src/app/shared/models/specie.model";
import { FinalitaTaglio } from "src/app/shared/models/finalita-taglio.model";
import { DestinazioneLegname } from "src/app/shared/models/destinazione-legname.model";
import { ConverstionVolume } from "src/app/utils/util-conversion";
import { SpeciePFA } from "src/app/shared/models/specie-pfa-model";
import { SpecieFinalita } from "src/app/shared/models/specie-finalita.model";

const SuperficieValidator: ValidatorFn = (fg: FormGroup) => {
  const max = parseFloat(fg.get("superficieTotale").value || 0);
  const princ = parseFloat(fg.get("superficiePrincipale").value || 0);
  const sec = parseFloat(fg.get("superficieSecondaria").value || 0);

  let s = parseFloat((princ + sec).toFixed(10));
  let diff = parseFloat((max - s).toFixed(10));
  /*   console.log("princ + sec ", s);
    console.log("DIFF  ", diff);
    console.log("diff > 0  ", diff >= 0);
   */
  return diff >= 0 ? null : { wrongValue: true };
};

const DestinazioneValidator: ValidatorFn = (fa: FormArray) => {
  let arr = fa.value;
  const auto = arr
    .map((item) => Number(item.autoconsumo))
    .reduce((prev, curr) => prev + curr, 0);
  const comm = arr
    .map((item) => Number(item.commerciale))
    .reduce((prev, curr) => prev + curr, 0);

  // 777 if(auto === 0 && comm === 0) { return { wrongPerc: false } }
  //console.log("Entro validador de volumenes ...");
  const fg = fa.parent;
  //(fg.get("specieInteressate") as FormArray)  .at(idx)  .get("numPiante")
  //console.log("Ingreso fg: ", fg);
  let t = 0;
  if (fg) {
    t = fg.value["t"];
    //console.log("Ingreso fg.get: ", t);
  }
  const tt = Number(t);
  if (auto + comm === 0 && tt === 0) {
    return null;
  }
  if (auto + comm === 100 && tt > 0) {
    return null;
  }
  //return (auto + comm === 100) || (auto + comm === 0) ? null : { wrongPerc: true };
  return { wrongPerc: true };
};

@Component({
  selector: "step4-tagli",
  templateUrl: "./step4-tagli.component.html",
  styleUrls: ["./step4-tagli.component.css"],
})
export class Step4TagliComponent implements OnInit, OnDestroy {
  @Input() editMode: number;
  @Input() fourthFormData: TagliStep4;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;

  @Output() nextStepEmitter = new EventEmitter<void>();

  unsubscribe$ = new Subject<void>();

  disableCategoriaForestale = false;
  invalid: boolean = true;

  step4Form: FormGroup;
  isUpdate: boolean;

  governiPrincipali: Governo[] = null;
  tipiInterventoPrincipali: TipoIntervento[] = [];
  governiSecondari: Governo[] = null;
  tipiInterventoSecondari: TipoIntervento[] = [];

  specie: Specie[] = [];
  finalitataglio: FinalitaTaglio[] = [];
  destinazioneLegname: DestinazioneLegname[] = [];

  showSupPrincipale: boolean = false;
  showSupSecondaria: boolean = false;
  superficieTotale: number;

  switchOff: boolean = false;

  constructor(private tagliService: TagliService, private fb: FormBuilder, private dialogService : DialogService) {}

  ngOnInit() {
    window.scrollTo(0, 0);

    console.log("====================================");
    console.log("4FORMDATA ", this.fourthFormData);
    console.log("====================================");

    this.tagliService.getStepNumber(this.editMode).subscribe((res) => {
      this.isUpdate =
        res.nextStep > 3 ||
        this.fourthFormData.intervSelvicolturale.tipoInterventoPrincipale
          .idTipoIntervento != 0;

      if (this.isUpdate) {
        this.showSupPrincipale = true;
        this.showSupSecondaria = true;
      }
    });

    this.tagliService.getAllSpecie().subscribe((results) => {
      this.specie = results;
    });

    if (this.fourthFormData.intervSelvicolturale.fkCategoriaIntervento === 1) {
      this.tagliService.getGoverni().subscribe((res) => {
        this.governiPrincipali = res;
        this.governiSecondari = res;
        this.changeGovernoPrincipale(
          this.fourthFormData.intervSelvicolturale.governoPrincipale
        );
        this.changeGovernoSecondario(
          this.fourthFormData.intervSelvicolturale.governoSecondario
        );
      });
    } else {
      this.tagliService
        .getTipiInterventoCategoria(
          8,
          this.fourthFormData.intervSelvicolturale.fkCategoriaIntervento
        )
        .subscribe((res) => {
          this.tipiInterventoPrincipali = res;
          this.tipiInterventoSecondari = res;
        });
    }
    //Recupero superficie totale
    this.tagliService.getRicadenze(this.editMode).subscribe((response) => {
      this.superficieTotale = response.totaleSuperficieIntervento;

      console.log("totaleSuperficieIntervento: ", this.superficieTotale);
      this.step4Form = this.createFormGroup();

      this.step4Form
        .get("superficiePrincipale")
        .valueChanges.subscribe((val) => {
          //let max = this.fourthFormData.intervento.superficieInteressata;
          let max = this.superficieTotale;
          console.log("superficieTotale: ", max);
          if (val > max) {
            return false;
          }
          let intSec = this.step4Form.get("interventoSecondario").value
            .idTipoIntervento;
          if (intSec == 0) {
            return false;
          }

          let tot = (max - parseFloat(val)).toFixed(4);
          console.log("set superficieSecondaria: ", tot);
          this.step4Form
            .get("superficieSecondaria")
            .setValue(tot, { emitEvent: false });
        });

      this.step4Form
        .get("superficieSecondaria")
        .valueChanges.subscribe((val) => {
          //let max = this.fourthFormData.intervento.superficieInteressata;
          let max = this.superficieTotale;
          if (val > max) {
            return false;
          }
          let tot = (max - parseFloat(val)).toFixed(4);
          this.step4Form
            .get("superficiePrincipale")
            .setValue(tot, { emitEvent: false });
        });

      if (
        (this.step4Form.get("headings") as FormArray).length === 1 &&
        (this.step4Form.get("headings") as FormArray).value[0].id === null
      ) {
        (this.step4Form.get("headings") as FormArray).removeAt(0);
        this.step4Form.patchValue(this.fourthFormData);

        this.fourthFormData.headings.forEach((element, index, array) => {
          let disabled = false;
          if(array[3].checked && element.id != 4){
            disabled = true
          }
          const tempHeading: FormGroup = this.fb.group({
            id: element.id,
            name: element.name,
            visibility: element.visibility,
            type: "checkbox",
            checked: element.checked,
            disabled:disabled,
            subheadings: this.fb.array([]),
          });
          if (element.subheadings) {
            element.subheadings.forEach((subElement, subIndex) => {
              const tempSubHeading: FormGroup = this.fb.group({
                id: subElement.id || subElement.codice,
                name: subElement.descrizione,
                codice: subElement.codice,
                visibility: subElement.visibility,
                valore: subElement.valore,
                checked: subElement.checked,
                disabled:disabled
              });
              (tempHeading.get("subheadings") as FormArray).push(
                tempSubHeading
              );
            });
          }

          (this.step4Form.get("headings") as FormArray).push(tempHeading);
        });
        this.checkValidation();
      }

      // specie interessate
      if (
        (this.step4Form.get("specieInteressate") as FormArray).length === 1 &&
        (this.step4Form.get("specieInteressate") as FormArray).value[0].id ===
          null
      ) {
        if (this.fourthFormData.specieInteressate) {
          (this.step4Form.get("specieInteressate") as FormArray).removeAt(0);
          this.step4Form.patchValue(this.fourthFormData);

          this.fourthFormData.specieInteressate.forEach((element, index) => {
            console.log("readyyyyyy");
            const tempSpecieInteressate: FormGroup = this.fb.group({
              id: index,
              specie: element.specie,
              numPiante: element.numPiante === 0 ? "" : element.numPiante,
              mc: element.volumeSpecia,
              t: ConverstionVolume.m3ToTon(
                element.volumeSpecia + "",
                element.specie.densita || 1
              ),
              q: ConverstionVolume.m3ToQ(
                element.volumeSpecia + "",
                element.specie.densita || 1
              ),
              finalitaTaglio:
                this.fb.array([], [DestinazioneValidator]) || true,
            });

            if (element.specieFinalita) {
              element.specieFinalita.forEach((subElement, subIndex) => {
                let perc =
                  (subElement.percAutoconsumo || 0) +
                  (subElement.percCommerciale || 0);
                const tempFinalita: FormGroup = this.fb.group({
                  id: subElement.idFinalitaTaglio,
                  mc: (tempSpecieInteressate.get("mc").value / 100) * perc,
                  t: (tempSpecieInteressate.get("t").value / 100) * perc,
                  q: (tempSpecieInteressate.get("q").value / 100) * perc,
                  description: subElement.descrFinalitaTaglio,
                  autoconsumo: subElement.percAutoconsumo,
                  commerciale: subElement.percCommerciale,
                });

                (tempSpecieInteressate.get("finalitaTaglio") as FormArray).push(
                  tempFinalita
                );
              });
            }
            (this.step4Form.get("specieInteressate") as FormArray).push(
              tempSpecieInteressate
            );
          });
        }
      }
      this.calculateTotals();
    });
  }

  createFormGroup() {
    return this.fb.group(
      {
        governoPrincipale: [
          this.fourthFormData
            ? this.fourthFormData.intervSelvicolturale.governoPrincipale
            : null,
          Validators.required,
        ],
        governoSecondario: [
          this.fourthFormData
            ? this.fourthFormData.intervSelvicolturale.governoSecondario
            : null,
        ],
        interventoPrincipale: [
          this.fourthFormData
            ? this.fourthFormData.intervSelvicolturale.tipoInterventoPrincipale
            : null,
          Validators.required,
        ],
        interventoSecondario: [
          this.fourthFormData
            ? this.fourthFormData.intervSelvicolturale.tipoInterventoSecondario
            : null,
        ],
        superficiePrincipale: [
          {
            value: this.fourthFormData
              ? this.fourthFormData.intervSelvicolturale.superficieInt1Ha
              : "",
            disabled: false,
          },
          [Validators.required, Validators.pattern("^[0-9]+(.[0-9]{1,4})?$")],
        ],
        superficieSecondaria: [
          {
            value: this.fourthFormData
              ? this.fourthFormData.intervSelvicolturale.superficieInt2Ha
              : "",
            disabled: false,
          },
          [Validators.pattern("^[0-9]+(.[0-9]{1,4})?$")],
        ],
        descrizioneTecnica: [
          {
            value: this.fourthFormData
              ? this.fourthFormData.intervento.descrizioneIntervento
              : "",
            disabled: false,
          },
          [Validators.required, Validators.minLength(100)],
        ],
        noteEsbosco: [
          {
            value: this.fourthFormData
              ? this.fourthFormData.intervSelvicolturale.noteEsbosco
              : "",
            disabled: false,
          },
        ],
        numeroPiante: [
          { value: "", disabled: false },
          [Validators.pattern("^[0-9]*$")],
        ],
        superficieTotale: [
          {
            value: this.fourthFormData ? this.superficieTotale : "",
            disabled: true,
          },
        ],
        headings: this.fb.array([this.createheadingArray()]),

        specieInteressate: this.fb.array([this.species]),

        ramagliaMc: [
          this.fourthFormData.intervSelvicolturale.volumeRamagliaM3
            ? this.fourthFormData.intervSelvicolturale.volumeRamagliaM3
            : 0,
          [Validators.pattern("^[0-9]+(.[0-9]{1,2})?$")],
        ],
        ramagliaT: [
          this.fourthFormData.intervSelvicolturale.volumeRamagliaM3
            ? ConverstionVolume.m3ToTon(
                this.fourthFormData.intervSelvicolturale.volumeRamagliaM3.toString(),
                1
              )
            : 0,
          [Validators.pattern("^[0-9]+(.[0-9]{1,2})?$")],
        ],
        ramagliaQ: [
          this.fourthFormData.intervSelvicolturale.volumeRamagliaM3
            ? ConverstionVolume.format(
                this.fourthFormData.intervSelvicolturale.volumeRamagliaM3.toString()
              )
            : 0,
          [Validators.pattern("^[0-9]+(.[0-9]{1,2})?$")],
        ],

        totaliPiante: [null, [Validators.pattern("^[0-9]+(.[0-9]{1,2})?$")]],
        totaliMc: [null, [Validators.pattern("^[0-9]+(.[0-9]{1,2})?$")]],
        totaliT: [null, [Validators.pattern("^[0-9]+(.[0-9]{1,2})?$")]],
        totaliQ: [null, [Validators.pattern("^[0-9]+(.[0-9]{1,2})?$")]],
      },
      { validator: SuperficieValidator }
    );
  }

  calculateTotals() {
    let arr = (this.step4Form.get("specieInteressate") as FormArray).value;
    const piante = arr
      .map((item) => Number(item.numPiante))
      .reduce((prev, curr) => prev + curr, 0);
    const mc =
      arr
        .map((item) => parseFloat(item.mc || 0))
        .reduce((prev, curr) => prev + curr, 0) || 0;
    const t =
      arr
        .map((item) => parseFloat(item.t || 0))
        .reduce((prev, curr) => prev + curr, 0) || 0;
    const q =
      arr
        .map((item) => parseFloat(item.q || 0))
        .reduce((prev, curr) => prev + curr, 0) || 0;

    let ramMc = parseFloat(this.step4Form.get("ramagliaMc").value || 0);
    let ramT = parseFloat(this.step4Form.get("ramagliaT").value || 0);
    let ramQ = parseFloat(this.step4Form.get("ramagliaQ").value || 0);

    this.step4Form.get("totaliPiante").setValue(piante, { emitEvent: false });
    this.step4Form
      .get("totaliMc")
      .setValue(mc.toFixed(2), { emitEvent: false });
    this.step4Form.get("totaliT").setValue(t.toFixed(2), { emitEvent: false });
    this.step4Form.get("totaliQ").setValue(q.toFixed(2), { emitEvent: false });
  }

  checkValidation() {
    let someChecked = (
      this.step4Form.get("headings") as FormArray
    ).controls.some(function (el) {
      return el.get("checked").value === true;
    });
    this.invalid = !someChecked;
  }

  changeGovernoPrincipale(opt?: Governo) {
    if(opt==null){
      this.step4Form
      .get("interventoPrincipale")
      .setValue(0, { emitEvent: false });
      this.step4Form
      .get("superficieSecondaria")
      .setValue(0, { emitEvent: false });
      this.changeTipoInterventoPrincipale(null)
      return
    }
    this.tagliService
      .getTipiInterventoGov(8, opt.idGoverno)
      .subscribe((res) => {
        this.tipiInterventoPrincipali = res;
      });
  }

  changeTipoInterventoPrincipale(opt?: Governo) {
    if(opt == null){
      this.step4Form
        .get("governoSecondario")
        .setValue(null, { emitEvent: false });
      this.step4Form
        .get("interventoSecondario")
        .setValue(null, { emitEvent: false });
      this.step4Form
        .get("superficiePrincipale")
        .setValue(this.step4Form.get("superficieTotale").value, { emitEvent: false });
      this.step4Form
        .get("superficieSecondaria")
        .setValue(0, { emitEvent: false });
    }
    if (!this.showSupPrincipale) {
      let val = this.step4Form.get("superficieTotale").value;
      this.step4Form
        .get("superficiePrincipale")
        .setValue(val, { emitEvent: false });
    }
    this.showSupPrincipale = true;
  }

  changeGovernoSecondario(opt?: Governo) {
    if(opt==null){
      this.step4Form
        .get("interventoSecondario")
        .setValue(null, { emitEvent: false });
      this.step4Form
        .get("superficieSecondaria")
        .setValue(0, { emitEvent: false });
        this.step4Form
        .get("superficiePrincipale")
        .setValue(this.step4Form.get("superficieTotale").value, { emitEvent: false });
        return
    }
    this.tagliService
      .getTipiInterventoGov(8, opt.idGoverno)
      .subscribe((res) => {
        this.tipiInterventoSecondari = res;
      });
  }

  changeTipoInterventoSecondario(opt?: Governo) {
    if(opt== null){
      this.step4Form
        .get("superficieSecondaria")
        .setValue(0, { emitEvent: false });
        this.step4Form
        .get("superficiePrincipale")
        .setValue(this.step4Form.get("superficieTotale").value, { emitEvent: false });
    }
    this.showSupSecondaria = true;
  }

  changeSpecie(opt: Specie, idx: number) {
    // 777 (this.step4Form.get('specieInteressate') as FormArray).at(idx).get('numPiante').setValidators([Validators.required, Validators.min(0.1)]); // set validator
    (this.step4Form.get("specieInteressate") as FormArray)
      .at(idx)
      .get("numPiante")
      .setValue("", { emitEvent: false }); // 777 for default ''
    // 777 (this.step4Form.get('specieInteressate') as FormArray).at(idx).get('mc').setValidators([Validators.required, Validators.min(0.1)]); // set validator
    (this.step4Form.get("specieInteressate") as FormArray)
      .at(idx)
      .get("mc")
      .setValue(0, { emitEvent: false });
    (this.step4Form.get("specieInteressate") as FormArray)
      .at(idx)
      .get("t")
      .setValue(0, { emitEvent: false });
    (this.step4Form.get("specieInteressate") as FormArray)
      .at(idx)
      .get("q")
      .setValue(0, { emitEvent: false });

    this.step4Form
      .get("specieInteressate")
      ["controls"].forEach(function (value,i) {
        if(i == idx){
          value.get("finalitaTaglio").controls.forEach(function (value2) {                      
              value2.get("q").setValue(0, { emitEvent: false });
              value2.get("mc").setValue(0, { emitEvent: false });
              value2.get("t").setValue(0, { emitEvent: false }); 
              value2.get("autoconsumo").setValue(0, { emitEvent: false });
              value2.get("commerciale").setValue(0, { emitEvent: false });  
          });
        }
      });
  }

  cleanPercents(superf: number) {
    let that = this;
    this.step4Form
      .get("specieInteressate")
      ["controls"].forEach(function (value) {
        value.get("finalitaTaglio").controls.forEach(function (value2) {
          if (superf <= 0) {
            value2.get("autoconsumo").setValue(0, { emitEvent: false });
            value2.get("commerciale").setValue(0, { emitEvent: false });
            that.switchOff = true;
          } else {
            that.switchOff = false;
          }
        });
      });
  }

  handleVolume(event: any, ix: number, item: any) {
    let cname = event.target.getAttribute("formControlName");
    let spSelected = (this.step4Form.get("specieInteressate") as FormArray)
      .at(ix)
      .get("specie").value;
    /*
    let numP = (this.step4Form.get("specieInteressate") as FormArray)
      .at(ix)
      .get("numPiante")
      .setValue("", { emitEvent: false });*/
      console.log(event);
      
    /* if(event.target.value.includes(',')){
       this.dialogService.showDialog(true, `Non Utilizzare le virgole nel campo ${event.target.name}! Utilizzare il punto`,"Errore", [
        { id: 1, label: 'OK' }
      ], "")
      return;
    } */
    let mc, t, q;
    switch (cname) {
      case "mc":
        //mc = event.target.value;
        t = ConverstionVolume.m3ToTon(
          event.target.value,
          spSelected.densita || 1
        );
        q = ConverstionVolume.m3ToQ(
          event.target.value,
          spSelected.densita || 1
        );
        (this.step4Form.get("specieInteressate") as FormArray)
          .at(ix)
          .get("t")
          .setValue(t, { emitEvent: false });
        (this.step4Form.get("specieInteressate") as FormArray)
          .at(ix)
          .get("q")
          .setValue(q, { emitEvent: false });
        (this.step4Form.get("specieInteressate") as FormArray)
          .at(ix)
          .get("mc")
          .setValue(event.target.value.replace(",","."), { emitEvent: false });
        break;
      case "t":
        mc = ConverstionVolume.tonToM3(
          event.target.value,
          spSelected.densita || 1
        );
        //t = event.target.value;
        q = ConverstionVolume.tonToQ(event.target.value);
        (this.step4Form.get("specieInteressate") as FormArray)
          .at(ix)
          .get("mc")
          .setValue(mc, { emitEvent: false });
        (this.step4Form.get("specieInteressate") as FormArray)
          .at(ix)
          .get("q")
          .setValue(q, { emitEvent: false });
        (this.step4Form.get("specieInteressate") as FormArray)
          .at(ix)
          .get("t")
          .setValue(event.target.value.replace(",","."), { emitEvent: false });
        break;
      case "q":
        mc = ConverstionVolume.qToM3(
          event.target.value,
          spSelected.densita || 1
        );
        t = ConverstionVolume.qToTon(event.target.value);
        //q = event.target.value;
        (this.step4Form.get("specieInteressate") as FormArray)
          .at(ix)
          .get("mc")
          .setValue(mc, { emitEvent: false });
        (this.step4Form.get("specieInteressate") as FormArray)
          .at(ix)
          .get("t")
          .setValue(t, { emitEvent: false });
        (this.step4Form.get("specieInteressate") as FormArray)
          .at(ix)
          .get("q")
          .setValue(event.target.value.replace(",","."), { emitEvent: false });
        break;
    }

    const superf = parseFloat(event.target.value);
    if (superf <= 0) {
      (this.step4Form.get("specieInteressate") as FormArray)
        .at(ix)
        .get("numPiante")
        .setValue("", { emitEvent: false });
    }

    this.cleanPercents(superf);
    this.callHandleDestinazione(ix);
    this.calculateTotals();
  }

  callHandleDestinazione(specieIdx: number) {
    let specieFa = this.step4Form.get("specieInteressate") as FormArray;
    let taglioFa = specieFa.at(specieIdx).get("finalitaTaglio") as FormArray;

    taglioFa.controls.forEach((element, index) => {
      this.handleDestinazione(null, specieIdx, index, null);
    });
  }

  handleDestinazione(event: any, idx: number, subIdx: number, item: any) {
    let specieFa = this.step4Form.get("specieInteressate") as FormArray;
    let taglioFa = specieFa.at(idx).get("finalitaTaglio") as FormArray;

    let ac = taglioFa.at(subIdx).get("autoconsumo").value;
    let com = taglioFa.at(subIdx).get("commerciale").value;

    let mc = parseFloat(specieFa.at(idx).get("mc").value);
    let t = parseFloat(specieFa.at(idx).get("t").value);
    let q = parseFloat(specieFa.at(idx).get("q").value);

    let perc = parseFloat(ac || 0) + parseFloat(com || 0);

    taglioFa
      .at(subIdx)
      .get("mc")
      .setValue(((mc / 100) * perc).toFixed(2), { emitEvent: false });
    taglioFa
      .at(subIdx)
      .get("t")
      .setValue(((t / 100) * perc).toFixed(2), { emitEvent: false });
    taglioFa
      .at(subIdx)
      .get("q")
      .setValue(((q / 100) * perc).toFixed(2), { emitEvent: false });

    if (
      taglioFa.at(subIdx).get("autoconsumo").value === "" ||
      taglioFa.at(subIdx).get("autoconsumo").value === null
    ) {
      taglioFa.at(subIdx).get("autoconsumo").setValue(0);
    }

    if (
      taglioFa.at(subIdx).get("commerciale").value === "" ||
      taglioFa.at(subIdx).get("commerciale").value === null
    ) {
      taglioFa.at(subIdx).get("commerciale").setValue(0);
    }
  }

  handleRamaglia(event: any) {
    let cname = event.target.getAttribute("formControlName");

    event.target.value = event.target.value ? event.target.value : 0;

    let mc, t, q;
    switch (cname) {
      case "ramagliaMc":
        t = ConverstionVolume.m3ToTon(event.target.value || 0, 1);
        q = ConverstionVolume.m3ToQ(event.target.value || 0, 1);
        this.step4Form.get("ramagliaT").setValue(t, { emitEvent: false });
        this.step4Form.get("ramagliaQ").setValue(q, { emitEvent: false });
        break;
      case "ramagliaT":
        mc = ConverstionVolume.tonToM3(event.target.value || 0, 1);
        q = ConverstionVolume.tonToQ(event.target.value || 0);
        this.step4Form.get("ramagliaMc").setValue(mc, { emitEvent: false });
        this.step4Form.get("ramagliaQ").setValue(q, { emitEvent: false });
        break;
      case "ramagliaQ":
        mc = ConverstionVolume.qToM3(event.target.value || 0, 1);
        t = ConverstionVolume.qToTon(event.target.value || 0);
        this.step4Form.get("ramagliaMc").setValue(mc, { emitEvent: false });
        this.step4Form.get("ramagliaT").setValue(t, { emitEvent: false });
        break;
    }
    this.calculateTotals();
  }

  onChange(index, event) {
    const headingArray = (this.step4Form.get("headings") as FormArray).controls[
      index
    ];
    headingArray.get("checked").patchValue(!headingArray.get("checked").value);

    const subHeadingArray = (headingArray.get("subheadings") as FormArray)
      .controls;

    subHeadingArray.forEach(function (el) {
      el.get("checked").patchValue(headingArray.get("checked").value);
    });
    if((this.step4Form.get("headings") as FormArray).controls[3].value.checked){
      (this.step4Form.get("headings") as FormArray).controls[0].get("checked").setValue(false);
      ((this.step4Form.get("headings") as FormArray).controls[0].get("subheadings") as FormArray).controls.forEach(function (el) {
        el.get("checked").setValue(false);
        el.get("disabled").setValue(true);
      });
      (this.step4Form.get("headings") as FormArray).controls[1].get("checked").setValue(false);
      ((this.step4Form.get("headings") as FormArray).controls[1].get("subheadings") as FormArray).controls.forEach(function (el) {
        el.get("checked").setValue(false);
        el.get("disabled").setValue(true);
      });
      (this.step4Form.get("headings") as FormArray).controls[2].get("checked").setValue(false);
      ((this.step4Form.get("headings") as FormArray).controls[2].get("subheadings") as FormArray).controls.forEach(function (el) {
        el.get("checked").setValue(false);
        el.get("disabled").setValue(true);
      });
      (this.step4Form.get("headings") as FormArray).controls[4].get("checked").setValue(false);
      (this.step4Form.get("headings") as FormArray).controls[0].get("disabled").setValue(true);
      (this.step4Form.get("headings") as FormArray).controls[1].get("disabled").setValue(true);
      (this.step4Form.get("headings") as FormArray).controls[2].get("disabled").setValue(true);
      (this.step4Form.get("headings") as FormArray).controls[4].get("disabled").setValue(true);
    } else {
      (this.step4Form.get("headings") as FormArray).controls[0].get("disabled").setValue(false);
      ((this.step4Form.get("headings") as FormArray).controls[0].get("subheadings") as FormArray).controls.forEach(function (el) {
        el.get("disabled").setValue(false);
      });
      (this.step4Form.get("headings") as FormArray).controls[1].get("disabled").setValue(false);
      ((this.step4Form.get("headings") as FormArray).controls[1].get("subheadings") as FormArray).controls.forEach(function (el) {
        el.get("disabled").setValue(false);
      });
      (this.step4Form.get("headings") as FormArray).controls[2].get("disabled").setValue(false);
      ((this.step4Form.get("headings") as FormArray).controls[2].get("subheadings") as FormArray).controls.forEach(function (el) {
        el.get("disabled").setValue(false);
      });
      (this.step4Form.get("headings") as FormArray).controls[4].get("disabled").setValue(false);
    }
    this.checkValidation();
  }

  onChangeSub(index, subIndex, event) {
    const headingArray = (this.step4Form.get("headings") as FormArray).controls[
      index
    ];
    const subHeadingArray = (headingArray.get("subheadings") as FormArray)
      .controls[subIndex];
    subHeadingArray
      .get("checked")
      .patchValue(!subHeadingArray.get("checked").value);

    let someChecked = (
      headingArray.get("subheadings") as FormArray
    ).controls.some(function (el) {
      return el.get("checked").value === true;
    });
    headingArray.get("checked").patchValue(someChecked);
    this.checkValidation();
  }

  createheadingArray() {
    return this.fb.group({
      id: null,
      name: "",
      visibility: "",
      type: "",
      checked: "",
      subheadings: this.fb.array([this.createsubheadingsArray()]),
    });
  }
  createsubheadingsArray() {
    return this.fb.group({
      id: "",
      name: "",
      visibility: "",
      valore: "",
      checked: "",
    });
  }

  get species(): FormGroup {
    return this.fb.group({
      id: null,
      specie: "",
      numPiante: "",
      mc: "",
      t: "",
      q: "",
      finalitaTaglio: this.finalitaTaglioArr,
    });
  }

  get finalitaTaglio(): FormGroup {
    return this.fb.group({
      player_name: "",
      player_number: "",
    });
  }

  get finalitaTaglioArr(): FormArray {
    let finalita = this.tagliService.getAllFinalitaTaglio();

    let tmpArr = this.fb.array([], [DestinazioneValidator]);

    finalita.subscribe((res) => {
      this.finalitataglio = res;
      this.finalitataglio.forEach((f) => {
        tmpArr.push(
          this.fb.group({
            id: f.idFinalitaTaglio,
            mc: "",
            t: "",
            q: "",
            description: f.descrFinalitaTaglio,
            autoconsumo: 0,
            commerciale: 0,
          })
        );
      });
    });
    return tmpArr;
  }

  addSpecie() {
    (this.step4Form.get("specieInteressate") as FormArray).push(this.species);
  }

  deleteSpecie(index) {
    (this.step4Form.get("specieInteressate") as FormArray).removeAt(index);
  }

  save(nextStep?: boolean) {
    const thirdFormObject: any = this.step4Form.getRawValue();

    const dataToSend: TagliStep4 = {};

    const intervento: Intervento = {};
    intervento.descrizioneIntervento = thirdFormObject.descrizioneTecnica;
    dataToSend.intervento = intervento;

    const selv: IntervSelvicolturale = {};
    selv.fkGoverno = thirdFormObject.governoPrincipale.idGoverno;
    selv.fkGoverno2 = thirdFormObject.governoSecondario
      ? thirdFormObject.governoSecondario.idGoverno
      : 0;

    selv.fkTipoIntervento =
      thirdFormObject.interventoPrincipale.idTipoIntervento;

    selv.fkTipoIntervento2 = thirdFormObject.interventoSecondario
      ? thirdFormObject.interventoSecondario.idTipoIntervento
      : 0;
    selv.superficieInt1Ha = thirdFormObject.superficiePrincipale;
    selv.superficieInt2Ha = thirdFormObject.superficieSecondaria;
    selv.volumeRamagliaM3 = +thirdFormObject.ramagliaMc;
    selv.noteEsbosco = thirdFormObject.noteEsbosco;
    dataToSend.intervSelvicolturale = selv;

    dataToSend.headings = thirdFormObject.headings;

    dataToSend.specieInteressate = [];
    thirdFormObject.specieInteressate.sort(
      (a, b) => Number(b.mc) - Number(a.mc)
    );

    thirdFormObject.specieInteressate.forEach((s, i) => {
      const sp: SpeciePFA = {};
      sp.idSpecie = s.specie.idSpecie;
      sp.idIntervento = this.editMode;
      sp.numPiante = s.numPiante;
      sp.volumeSpecia = Number(s.mc.toString().replace(",","."));
      sp.flgSpeciePriorita = i == 0 ? "P" : i == 1 ? "S" : "A";
      sp.specieFinalita = [];
      sp.fkUdm = 3; //mc

      s.finalitaTaglio.forEach((el, idx) => {
        const spfin: SpecieFinalita = {};
        spfin.idFinalitaTaglio = el.id;
        spfin.idIntervento = this.editMode;
        spfin.idSpecie = s.specie.idSpecie;
        spfin.percAutoconsumo = parseFloat(el.autoconsumo || 0);
        spfin.percCommerciale = parseFloat(el.commerciale || 0);
        spfin.flgSpeciePriorita = sp.flgSpeciePriorita;
        sp.specieFinalita.push(spfin);
      });

      dataToSend.specieInteressate.push(sp);
    });

    if (this.isUpdate) {
      this.tagliService
        .putStep4(dataToSend, this.editMode)
        .subscribe((response) => {
          if (nextStep) {
            this.nextStepEmitter.emit();
          }
        });
    } else {
      this.tagliService
        .postStep4(dataToSend, this.editMode)
        .subscribe((response) => {
          this.tagliService.getStepNumber(this.editMode).subscribe((res) => {
            this.isUpdate =
              res.nextStep > 3 ||
              this.fourthFormData.intervSelvicolturale.tipoInterventoPrincipale
                .idTipoIntervento != 0;
      
            if (this.isUpdate) {
              this.showSupPrincipale = true;
              this.showSupSecondaria = true;
            }
          });
          if (nextStep) {
            this.nextStepEmitter.emit();
          }
        });
    }
  }

  continue() {
    this.save(true);
  }
  cancelasec() {
    this.showSupSecondaria = false;
    this.step4Form.get("superficieSecondaria").setValue(0, { emitEvent: true });
    this.step4Form.patchValue({
      governoSecondario: null,
      interventoSecondario: null,
    });
  }
  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
}
