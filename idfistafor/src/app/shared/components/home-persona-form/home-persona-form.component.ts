/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, OnDestroy, Output, EventEmitter } from '@angular/core';
import { ComuneModel } from '../../models/comune.model';
import { SoggettoModel } from '../../models/soggetto.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CONST } from '../../constants';
import { ForestaliService } from 'src/app/features/istanze-forestali/services/forestali.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-home-persona-form',
  templateUrl: './home-persona-form.component.html',
  styleUrls: ['./home-persona-form.component.css']
})
export class HomePersonaFormComponent implements OnInit, OnDestroy {
  HREF = CONST.INFORMATIVA_SULLA;
  @Input() personHome: any;
  comune: ComuneModel[];
  soggetto: SoggettoModel;
  personaForm: FormGroup;
  unsubscribe$ = new Subject<void>();
  @Output() emitCompleted = new EventEmitter();
  emptyMessageAC : string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;

  constructor(private formBuilder: FormBuilder, private forestaliService: ForestaliService) { }


  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  ngOnInit() {
    this.initForm(this.personHome);
  }

  initForm(personHome: any) {
    this.personaForm = this.formBuilder.group({
      codiceFiscale: [personHome ? personHome.codFisc : ''],
      cognome: [personHome ? personHome.cognome : ''],
      nome: [personHome ? personHome.nome : ''],
      indirizzoResidenza: ['', Validators.required],
      civico: ['', Validators.required],
      cap: ['', [Validators.required, Validators.pattern(CONST.PATTERN_CAP)]],
      recapitoTelefonico: ['', [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO), Validators.minLength(7), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.pattern(CONST.PATTERN_MAIL), Validators.maxLength(100)]],
      comuneResidenza: [ '', Validators.required ],
    });
    this.personaForm.controls['codiceFiscale'].disable();
    this.personaForm.controls['cognome'].disable();
    this.personaForm.controls['nome'].disable();
  }

  conferma() {
    this.soggetto = this.createSoggetto(this.personaForm.value);
    this.forestaliService.createSoggetti(this.soggetto)
      .subscribe(response => {
        if (response.status === 200) {
          this.emitCompleted.emit();
        }
      });
  }

  getComune(event) {
    this.forestaliService.findComuniByNome(event.query).pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ComuneModel[]) => {
        this.comune = this.forestaliService.autocompleteFilter(event, res, 'Comune');
      });
  }

  createSoggetto(value: any): SoggettoModel {
    let soggeto = new SoggettoModel();
    soggeto.codiceFiscale = this.personHome.codFisc;
    soggeto.nome = this.personHome.nome;
    soggeto.cognome = this.personHome.cognome;
    soggeto.eMail = value.email;
    soggeto.indirizzo = value.indirizzoResidenza;
    soggeto.nrTelefonico = value.recapitoTelefonico;
    soggeto.civico = value.civico;
    soggeto.cap = value.cap;
    soggeto.flgSettoreRegionale = 0;
    soggeto.fkComune = value.comuneResidenza.idComune;
    return soggeto;
  }
}
