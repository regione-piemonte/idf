/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { User } from 'src/app/shared/models/user';
import { CONST } from 'src/app/shared/constants';
import { HomeService } from '../../services/home.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { MessageService } from 'primeng/components/common/messageservice';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css'],
  providers: [MessageService]
})
export class UserFormComponent implements OnInit, OnDestroy {

  userForm: FormGroup;
  testUser: User;
  user: User;
  unsubscribe$ = new Subject<void>();
  flgPrivacy: number;

  constructor(private fb: FormBuilder, private homeService: HomeService, private messageService: MessageService, private router: Router) { }

  ngOnInit() {
    this.homeService.isUtenteAlreadyUpdate()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: any) => {
          console.log('RESPONSE POST', response);
          if(response['isUpdate']){
            var c = this.getRedirectUrl();
            if(c){
              window.location.href = c;
            }else{
              this.postUser();
            }
          }else{
            this.postUser();
          }
        }
      );

    
  }

  getRedirectUrl(){
    return sessionStorage.getItem("redirect");
  }

  postUser(){
    this.testUser = {
      codiceFiscale: 'LDDPLA73P27B354T',
      cognome: 'Loddo',
      nome: 'Paolo',
      ruolo: 'ruolo',
      provider: 'provider',
    };

    this.homeService.postUser(this.testUser)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: User) => {
          console.log('RESPONSE POST', response);
          this.user = response;
          this.buildUserForm();
        }
      );
  }

  buildUserForm() {
    this.userForm = this.fb.group({
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
        this.user ? this.user.ruolo : ""
      ],
      provider: [
        this.user ? this.user.provider : ""
      ],
      flgPrivacy: [
        this.user ? this.user.flgPrivacy : ""
      ],
      nrTelefonico: [
        this.user ? this.user.nrTelefonico : "",
        [Validators.required]
      ],
      email: [
        this.user ? this.user.email : "",
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL), Validators.maxLength(100)]
      ],
      idConfigUtente: [
        this.user ? this.user.idConfigUtente : ""
      ],
    });
  }

  sendUserForm(event) {
    this.flgPrivacy = +event;
    const user: User = this.userForm.getRawValue();
    user.flgPrivacy = this.flgPrivacy;
    console.log('USER', user);
    this.homeService.changeUser(user).subscribe(
      (res: any) => {
        console.log('user form sent', res);
        this.showSuccessToast();
        var c = this.getRedirectUrl();
            if(c){
              window.location.href = c;
            }else{
              this.router.navigate(['index']);
            }
      },
      error => {
        this.showErrorToast();
      }
    )
  }

  showSuccessToast() {
    this.messageService.add({ severity: 'success', summary: 'Successo', detail: 'Modulo inviato' });
  }

  showErrorToast() {
    this.messageService.add({ severity: 'error', summary: 'Errore', detail: 'Modulo non inviato' });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

}
