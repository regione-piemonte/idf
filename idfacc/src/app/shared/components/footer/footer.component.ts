/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { HomeService } from 'src/app/features/home/services/home.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  AGID_VALIDATION_LINK:string;

  constructor(private homeService: HomeService) { }

  ngOnInit() {
    this.homeService.getConfigationByName('AGID_VALIDATION_LINK_ACC').
    subscribe((res: any) => { 
      this.AGID_VALIDATION_LINK = res.value;
    })
  }

}
