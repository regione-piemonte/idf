/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../services/report.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  AGID_VALIDATION_LINK:string;

  constructor(private reportService: ReportService) { }

  ngOnInit() {
    this.reportService.getConfigationByName('AGID_VALIDATION_LINK_REP').
    subscribe((res: any) => { 
      this.AGID_VALIDATION_LINK = res.value;
    })
  }

}
