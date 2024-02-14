/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { ForestaleSampleService } from "src/app/shared/services/forestale-sample.service";
import { Observable, Subject } from "rxjs";
import { TableHeader } from "src/app/shared/models/table-header";
import { ForestaleConfig } from "../../forestale-config";
import { CONST } from "src/app/shared/constants";
import { takeUntil } from "rxjs/operators";
import { Soggetto } from "src/app/shared/models/soggetto";
import { Router } from "@angular/router";
import { User } from "src/app/shared/models/user";
import { Comune } from "src/app/shared/models/comune";

@Component({
  selector: "app-personali",
  templateUrl: "./personali.component.html",
  styleUrls: ["./personali.component.css"]
})
export class PersonaliComponent implements OnInit, OnDestroy {
  personaliForm: FormGroup;
  isConfirmed: boolean = false;
  unsubscribe$ = new Subject<void>();
  user: User;
  testUser: any;
  filteredCities: any;
  city: any;
  flgPrivacy: number;

  constructor(
    private fb: FormBuilder,
    private forestaleService: ForestaleSampleService,
    private router: Router
  ) {}

  ngOnInit() {
    this.testUser = {
      codiceFiscale: '123123',
      cognome: 'marko',
      nome: 'marko',
      ruolo: 'test',
      provider: 'test',
    };

    this.forestaleService.postUser(this.testUser)
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response: User) => {
        this.user = response;
        this.fillUserForm();
      }
    );
  }

  filterCities(event) {
    this.forestaleService.getAllComuni()
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response: Comune[]) => {
        this.filteredCities = this.filterResidence(event.query, response);
      }
    )
  }

  sendPersonaliForm() {
    this.flgPrivacy = +this.isConfirmed;
    const user: User = this.personaliForm.getRawValue();
    user.flgPrivacy = this.flgPrivacy;
    this.forestaleService.changeUser(user)
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (res: any) => {
        this.router.navigate(["/forestale", "links"]);
      },
      error => {
        //error ?
      }
    )
  }

  test() {
    const userForm: Soggetto = this.personaliForm.getRawValue();
    this.forestaleService
      .putUserData(userForm)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe({
        next: response => {
          this.router.navigate(["/forestale", "links"]);
        },
        error: response => {
          // ?
        }
      });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  fillUserForm() {
    this.personaliForm = this.fb.group({
      idSoggetto: [this.user ? this.user.idSoggetto : ""],
      codiceFiscale: [
        { value: this.user ? this.user.codiceFiscale : "", disabled: true },
        [Validators.required]
      ],
      cognome: [
        { value: this.user ? this.user.cognome : "", disabled: true },
        [Validators.required]
      ],
      nome: [
        { value: this.user ? this.user.nome : "", disabled: true },
        [Validators.required]
      ],
      ruolo: [
        this.user ? this.user.ruolo : "",
        [Validators.required]
      ],
      provider: [
        this.user ? this.user.provider : "",
        [Validators.required]
      ],
      comune: [
        this.user ? this.user.comune : "",
        [Validators.required]
      ],
      flgPrivacy: [
        this.user ? this.user.flgPrivacy : "",
        [Validators.required]
      ],
      indirizzo: [
        this.user ? this.user.indirizzo : "",
        [Validators.required]
      ],
      nrTelefonico: [
        this.user ? this.user.nrTelefonico : "",
        [Validators.required]
      ],
      email: [
        this.user ? this.user.email : "",
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL)]
      ],
      civico: [
        this.user ? this.user.civico : "",
        [Validators.required, Validators.pattern(CONST.PATTERN_CAP)]
      ],
      cap: [
        this.user ? this.user.cap : "",
        [Validators.required]
      ],
      idConfigUtente: [
        this.user ? this.user.idConfigUtente : "",
        [Validators.required]
      ],
    });
  }

  filterResidence(query, cities: Comune[]): any[] {
    let filtered: any[] = [];
    cities.forEach((item, index) => {
      let city = cities[index];
      if(city.denominazioneComune.toLowerCase().indexOf(query.toLowerCase()) === 0) {
        filtered.push(city.denominazioneComune);
      }
    });
    return filtered;
  }
}
