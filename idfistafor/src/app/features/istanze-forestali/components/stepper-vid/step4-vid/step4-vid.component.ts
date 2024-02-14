/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy, Input, Output, EventEmitter, ViewEncapsulation } from "@angular/core";
import { Subject } from "rxjs";


import { takeUntil } from "rxjs/operators";
import { Step4Model } from "src/app/shared/models/step4.model";
import { FormGroup, Validators, FormBuilder, AbstractControl, FormControl } from "@angular/forms";
import { VincoloStep4 } from "./vincoloStep4";
import { VincoloService } from "../../../services/vincolo.service";


@Component({
  selector: "step4-vid",
  templateUrl: "./step4-vid.component.html",
  styleUrls: ["./step4-vid.component.css"]
})
export class Step4VidComponent implements OnInit, OnDestroy {
  @Output() emitFormData = new EventEmitter();
  @Output() emitNecesseria = new EventEmitter<string>();
  @Output() emitComp = new EventEmitter<string>();
  @Output() formDestoyed = new EventEmitter<void>();
  @Input() compensazioneForm: FormGroup;
  @Input() fourthStepForm: FormGroup;
  @Input() fourthFormData: Step4Model;
  @Input() editMode: number;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;
  @Output() emitNextStep = new EventEmitter<void>();
  @Output() noNecessariaChange = new EventEmitter();
  unsubscribe$ = new Subject<void>();
  userInputs = new Step4Model();

  cauzioneForm: FormGroup;
  cauzione: number = 0;

  text = [
    {
      title: "Si impegna a versare prima dell'inizio dei lavori il deposito cauzionale ai sensi dell'art. 8, punto 1, della l.r. 45/1989 a favore della Regione Piemonte importo pari a € 2.000 per ogni ettaro di terreno trasformato e comunque non inferiore a € 1.000.",
    },
    {
      title: "Dichiara che la modificazione e/o trasformazione ricade in uno dei seguenti casi, esenti dall'obbligo di cauzione ai sensi dell'art. 8, comma 2, della l.r. 45/1989, in quanto:",
      subTitle: [
        "le opere sono realizzate col concorso finanziario regionale, statale o Comunitario",
        "l'opera è finalizzata all'esclusiva valorizzazione agro-silvo-pastorale del territorio",
        "l'opera è finalizzata all'attività estrattiva di cava (L.R. 23/2016)"
      ]
    },
    {
      title: "Chiede l'esonero dal deposito cauzionale ai sensi dell'art. 8, comma 3, della l.r. 45/1989, in quanto si tratta di interventi di modesta rilevanza, comportanti trasformazioni o modificazioni di uso del suolo su superfici non superiori a 250 mq e richiedenti un volume complessivo di scavo non maggiore di 100 mc.",
    },
  ];

  necessaria: boolean = null;
  necessariaValue: string;

  constructor(private vincoloService: VincoloService, private fb: FormBuilder) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    this.initForm();
    this.calculateCauzione();


    this.vincoloService.getStep4(this.editMode).subscribe(x => {
      if(x != null)
        this.cauzioneForm.patchValue({'flagCauzione': x.flagCauzione.toString()});
    });
  }

  initForm() {
    this.cauzioneForm = new FormGroup({
      flagCauzione: new FormControl('', [Validators.required])
    });
  }




  save(nextStep?: boolean) {
    if(this.cauzioneForm.valid)
      this.vincoloService
        .postStep4(new VincoloStep4(this.cauzioneForm.get('flagCauzione').value), this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(() => {
          if(nextStep) this.emitNextStep.emit();
        });
  }

  continue() {
    this.save(true);
  }

  calculateCauzione(){
    this.vincoloService.getCauzione(this.editMode).subscribe(cauzione => {
      if(cauzione != null) {
        this.cauzione =  cauzione;
      }
    });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
}