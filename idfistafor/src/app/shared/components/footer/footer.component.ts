/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { ForestaliService } from 'src/app/features/istanze-forestali/services/forestali.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  AGID_VALIDATION_LINK:string;

  constructor(private forestaliService: ForestaliService) { }

  ngOnInit() {
    this.forestaliService.getConfigationByName('AGID_VALIDATION_LINK_ISTAFOR').
    subscribe((res: any) => { 
      this.AGID_VALIDATION_LINK = res.value;
    })
  }

}
