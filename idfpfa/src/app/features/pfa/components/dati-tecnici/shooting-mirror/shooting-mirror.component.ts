/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';
import { TableHeader } from 'src/app/shared/models/table-header';
import { PfaConfigComponent } from '../../../pfa-config';
import { ShootingMirrorModel } from 'src/app/shared/models/shooting-mirror';


@Component({
  selector: 'app-shooting-mirror',
  templateUrl: './shooting-mirror.component.html',
  styleUrls: ['./shooting-mirror.component.css']
})


export class ShootingMirrorComponent implements OnInit {
  tableHeaders: TableHeader[];
  @Input("shooting-mirror") shootingMirrors: ShootingMirrorModel[];  


  constructor() {
    
   }

  ngOnInit() {

    this.tableHeaders = PfaConfigComponent.getShootingMirrorHeader();
  }

  
}
