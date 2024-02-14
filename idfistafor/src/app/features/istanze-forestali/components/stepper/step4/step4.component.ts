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
  EventEmitter
} from "@angular/core";
import { Subject } from "rxjs";

import { ForestaliService } from "../../../services/forestali.service";
import { takeUntil, distinctUntilChanged } from "rxjs/operators";
import { Step4Model } from "src/app/shared/models/step4.model";
import { FormGroup, Validators, FormBuilder } from "@angular/forms";

@Component({
  selector: "app-step4",
  templateUrl: "./step4.component.html",
  styleUrls: ["./step4.component.css"]
})
export class Step4Component implements OnInit, OnDestroy {
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
  @Output() noNecessariaChange = new EventEmitter<boolean>();
  unsubscribe$ = new Subject<void>();
  userInputs = new Step4Model();

  text = [
    {
      title: "NON NECESSARIA - con autorizzazione paesaggistica richiesta dal 21/10/2021",
      subTitle: [
        "a) interessanti superfici inferiori ai 1000 m2;",
        "b) finalizzati al ripristino degli habitat di interesse comunitario, se previste dagli strumenti di gestione o pianificazione di dettaglio vigenti;",
        "c) volti al recupero a fini produttivi per l'esercizio dell'attività agro-pastorale svolte da coltivatori diretti e da imprenditori agricoli singoli o associati, nelle categorie forestali “Robinieti”, “Castagneti”, “Boscaglie d’invasione”, “Arbusteti subalpini” e “Acero-tiglio frassineti” nel tipo “d’invasione”.; ",
        "d) per la realizzazione o adeguamento di opere di difesa dagli incendi, di opere pubbliche , se previsti dagli strumenti di 	gestione o pianificazione di dettaglio vigenti;",
        "d ter) in aree di interfaccia urbano-rurale al fine di garantire la sicurezza pubblica e la prevenzione antincendio; l’estensione di tali aree è stabilita dal piano antincendio della Regione Piemonte di cui alla legge 21 novembre 2000, n. 353 (Legge quadro in materia di incendi boschivi), a condizione che l’eventuale rimanente porzione di soprassuolo conservi le caratteristiche per essere riconosciuta come bosco ai sensi dell’articolo 3, commi 3 e 4 del decreto legislativo 3 aprile 2018, n. 34 (Testo unico in materia di foreste e filiere forestali) e che nella porzione trasformata non vengano realizzate edificazioni o ampliate quelle esistenti;",
        "d quater) entro i 25 metri da immobili esistenti per riduzioni di superfici boscate non superiori a 2000 metri quadri, a condizione che la rimanente porzione di soprassuolo conservi le caratteristiche per essere considerato bosco ai sensi dell’articolo 3, commi 3 e 4 del d. lgs. 34/2018 e che nella porzione trasformata non vengano realizzate edificazioni o ampliate quelle esistenti;",
        "d quinquies) per il recupero di aree dichiarate di interesse archeologico e storico artistico.",
      ]
    },
    {
      title: "NON NECESSARIA - con autorizzazione paesaggistica richiesta entro il 20/10/2021",
      subTitle: [
        "a) interessanti superfici inferiori ai 500 m2;",
        "b) finalizzati alla conservazione del paesaggio o al ripristino degli habitat di interesse comunitario, se previste dagli strumenti di gestione   	o pianificazione di dettaglio vigenti;",
        "c) volti al recupero a fini produttivi per l'esercizio dell'attività agro-pastorale svolte da coltivatori diretti e da imprenditori agricoli singoli 	o associati, di boschi di neoformazione insediatisi su ex coltivi, prati e pascoli abbandonati da non oltre trent'anni;",
        "d) per la realizzazione o adeguamento di opere di difesa dagli incendi, di opere pubbliche di difesa del suolo, se previsti dagli strumenti di 	gestione o pianificazione di dettaglio vigenti;"
        //,"dbis) per la realizzazione di viabilità forestale in aree non servite."
      ]
    },
    {
      title: "NECESSARIA (art. 19, comma 4)",
      subTitle: [
        "Compensazione fisica",
        "Compensazione monetaria"
      ]
    }
  ];

  necessaria: boolean = null;
  necessariaValue: string;

  constructor(private forestaliService: ForestaliService, private fb: FormBuilder) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    this.fourStepForm(this.fourthFormData);
    console.log(this.fourthStepForm.valid);
    console.log(this.fourthStepForm);
    
  }

  private fourStepForm(necessaria: Step4Model) {
    if(!necessaria.nonNecessaria21){
      necessaria.nonNecessaria21 = false;
    }
    this.fourthStepForm = this.fb.group({
      nonNecessaria: [
        necessaria ? necessaria.nonNecessaria : false,
        [Validators.required]
      ],
      flgA: [
        {
          value: necessaria ? necessaria.flgA : false,
          disabled: necessaria.nonNecessaria === false ? true : false
        }
      ],
      flgB: [
        {
          value: necessaria ? necessaria.flgB : false,
          disabled: necessaria.nonNecessaria === false ? true : false
        }
      ],
      flgC: [
        {
          value: necessaria ? necessaria.flgC : false,
          disabled: necessaria.nonNecessaria === false ? true : false
        }
      ],
      flgD: [
        {
          value: necessaria ? necessaria.flgD : false,
          disabled: necessaria.nonNecessaria === false ? true : false
        }
      ],
      nonNecessaria21: [
        necessaria ? necessaria.nonNecessaria21 : false,
        [Validators.required]
      ],
      flgA21: [
        {
          value: necessaria ? necessaria.flgA21 : false,
          disabled: necessaria.nonNecessaria21 === false ? true : false
        }
      ],
      flgB21: [
        {
          value: necessaria ? necessaria.flgB21 : false,
          disabled: necessaria.nonNecessaria21 === false ? true : false
        }
      ],
      flgC21: [
        {
          value: necessaria ? necessaria.flgC21 : false,
          disabled: necessaria.nonNecessaria21 === false ? true : false
        }
      ],
      flgD21: [
        {
          value: necessaria ? necessaria.flgD21 : false,
          disabled: necessaria.nonNecessaria21 === false ? true : false
        }
      ],
      flgDter21: [
        {
          value: necessaria ? necessaria.flgDter21 : false,
          disabled: necessaria.nonNecessaria21 === false ? true : false
        }
      ],
      flgDquater21: [
        {
          value: necessaria ? necessaria.flgDquater21 : false,
          disabled: necessaria.nonNecessaria21 === false ? true : false
        }
      ],
      flgDquinquies21: [
        {
          value: necessaria ? necessaria.flgDquinquies21 : false,
          disabled: necessaria.nonNecessaria21 === false ? true : false
        }
      ],
      necessaria: [
        necessaria ? necessaria.necessaria : false,
        [Validators.required]
      ],
      compFisica: [
        {
          value: necessaria ? necessaria.compFisica : false,
          disabled: necessaria.necessaria === false ? true : false
        }
      ],
      compMonetaria: [
        {
          value: necessaria ? necessaria.compMonetaria : false,
          disabled: necessaria.necessaria === false ? true : false
        }
      ]
    });
  }

  updateNecesseria(necesserity: string) {
    if (necesserity === 'necessaria') {
      this.patchAndUpdateValidity(
        ['nonNecessaria', 'flgA', 'flgB', 'flgC', 'flgD'],
        false
      );
      this.disableFields(['flgA', 'flgB', 'flgC', 'flgD']);
      this.enableFields(['compFisica', 'compMonetaria']);
    } else if (necesserity === 'nonNecessaria') {
      this.fourthStepForm.get('necessaria').patchValue(false);
      this.fourthStepForm.get('necessaria').updateValueAndValidity();
      this.patchAndUpdateValidity(
        ['necessaria', 'compFisica', 'compMonetaria'],
        false
      );
      this.disableFields(['compFisica', 'compMonetaria']);
      this.enableFields(['flgA', 'flgB', 'flgC', 'flgD']);
    }
    this.noNecessariaChange.emit(necesserity != 'necessaria');
    console.log('UPDATED', this.fourthStepForm.getRawValue());
  }

  disableFields(fields: string[]) {
    fields.forEach((item, i) => {
      this.fourthStepForm.get(item).disable();
    });
  }

  enableFields(fields: string[]) {
    fields.forEach((item, i) => {
      this.fourthStepForm.get(item).enable();
    });
  }

  updateComp(comp: string) {
    if (comp === 'compFisica') {
      this.patchAndUpdateValidity(['compMonetaria'], false);
    } else if (comp === 'compMonetaria') {
      this.patchAndUpdateValidity(['compFisica'], false);
    }
    console.log('UPDATED', this.fourthStepForm.getRawValue());
  }

  patchAndUpdateValidity(fields: string[], value: boolean) {
    fields.forEach((item, i) => {
      this.fourthStepForm.get(item).patchValue(value);
      this.fourthStepForm.get(item).updateValueAndValidity();
    });
  }

  save(nextStep?: boolean) {
    if (this.fourthStepForm.valid) {
      const step: Step4Model = this.fourthStepForm.getRawValue();
      let isValidData = false;
      if (step.nonNecessaria) {
        if (
          step.flgA !== false ||
          step.flgB !== false ||
          step.flgC !== false ||
          step.flgD !== false 
        ) {
          isValidData = true;
        } 
      } else if (step.nonNecessaria21) {
        if (
          step.flgA21 !== false ||
          step.flgB21 !== false ||
          step.flgC21 !== false ||
          step.flgD21 !== false ||
          step.flgDter21 !== false ||
          step.flgDquater21 !== false ||
          step.flgDquinquies21 !== false 
        ) {
          isValidData = true;
        } 
      }else if (step.necessaria) {
        if (step.compFisica !== false || step.compMonetaria !== false) {
          isValidData = true;
        } 
      }

      if(isValidData){
        this.forestaliService
            .sendFourthForm(step, this.editMode)
            .pipe(takeUntil(this.unsubscribe$))
            .subscribe(response => {
              console.log('SEND WORKS');
              if(nextStep) {
                this.emitNextStep.emit();
              }
            });
      }
    }
  }

  updateNecessery(necesserity) {
    if (necesserity === 'necessaria') {
      this.patchAndUpdateValidity(
        ['nonNecessaria', 'flgA', 'flgB', 'flgC', 'flgD', 'nonNecessaria21',
         'flgA21', 'flgB21', 'flgC21', 'flgD21', 'flgDter21', 'flgDquater21', 'flgDquinquies21'],
        false
      );
      this.disableFields(['flgA', 'flgB', 'flgC', 'flgD','flgA21', 'flgB21', 'flgC21', 'flgD21', 'flgDter21', 'flgDquater21', 'flgDquinquies21']);
      this.enableFields(['compFisica', 'compMonetaria']);
    } else if (necesserity === 'nonNecessaria') {
      this.fourthStepForm.get('necessaria').patchValue(false);
      this.fourthStepForm.get('necessaria').updateValueAndValidity();
      this.patchAndUpdateValidity(
        ['necessaria', 'compFisica', 'compMonetaria','nonNecessaria21',
        'flgA21', 'flgB21', 'flgC21', 'flgD21', 'flgDter21', 'flgDquater21', 'flgDquinquies21'],
        false
      );
      this.disableFields(['compFisica', 'compMonetaria','flgA21', 'flgB21', 'flgC21', 'flgD21', 'flgDter21', 'flgDquater21', 'flgDquinquies21']);
      this.enableFields(['flgA', 'flgB', 'flgC', 'flgD']);
    } else if (necesserity === 'nonNecessaria21') {
      
      this.patchAndUpdateValidity(
        ['necessaria', 'compFisica', 'compMonetaria','nonNecessaria','flgA', 'flgB', 'flgC', 'flgD'],
        false
      );
      this.disableFields(['compFisica', 'compMonetaria','flgA', 'flgB', 'flgC', 'flgD']);
      this.enableFields(['flgA21', 'flgB21', 'flgC21', 'flgD21', 'flgDter21', 'flgDquater21', 'flgDquinquies21']);
    }
    this.noNecessariaChange.emit(necesserity != 'necessaria');
    console.log('UPDATED', this.fourthStepForm.getRawValue());
  }

  continue() {
    this.save(true);
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  isSaveDisabled(){
    return (this.fourthStepForm.invalid)
    || (this.fourthStepForm.get('necessaria').value && this.isAllUncheked(['compFisica', 'compMonetaria']))
    || (this.fourthStepForm.get('nonNecessaria').value && this.isAllUncheked(['flgA', 'flgB', 'flgC', 'flgD']))
    || (this.fourthStepForm.get('nonNecessaria21').value && this.isAllUncheked(['flgA21', 'flgB21', 'flgC21', 'flgD21', 'flgDter21', 'flgDquater21', 'flgDquinquies21']))
  }

  isAllUncheked(items:string[]){
    let item;
    for(let i in items){
      item = items[i];
      let x = this.fourthStepForm.get(item).value;
      if(this.fourthStepForm.get(item).value){
        return false;
      }
    }
    return true;
  }
}
