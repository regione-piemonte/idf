/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { HomeService } from "../../services/home.service";
import { CONST } from 'src/app/shared/constants';

@Component({
  selector: 'app-user-form-inputs',
  templateUrl: './user-form-inputs.component.html',
  styleUrls: ['./user-form-inputs.component.css']
})
export class UserFormInputsComponent implements OnInit {

  @Input() userForm: FormGroup;
  @Output() emitUserForm = new EventEmitter<boolean>();
  isConfirmed: boolean = false;
  hrefPrivacy:string;

  constructor(private homeService: HomeService) { }

  ngOnInit() {
    this.homeService.getConfigationByName('URL_PRIVACY').
    subscribe((res: any) => { 
      this.hrefPrivacy = res.value;
    })
  }

  sendUserForm() {
    this.emitUserForm.emit(this.isConfirmed);
  }

}
