/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { PfaSampleService } from '../../services/pfa-sample.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  AGID_VALIDATION_LINK:string;

  constructor(private pfaSampleService: PfaSampleService) { }

  ngOnInit() {
    this.pfaSampleService.getConfigationByName('AGID_VALIDATION_LINK_PFA').
    subscribe((res: any) => { 
      this.AGID_VALIDATION_LINK = res.value;
    })
  }

}
