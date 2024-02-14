/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { ForestaleSampleService } from "src/app/shared/services/forestale-sample.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  AGID_VALIDATION_LINK:string;

  constructor(private forestaleSampleService: ForestaleSampleService) { }

  ngOnInit() {
    this.forestaleSampleService.getConfigationByName('AGID_VALIDATION_LINK_INV').
    subscribe((res: any) => { 
      this.AGID_VALIDATION_LINK = res.value;
    })
  }

}
