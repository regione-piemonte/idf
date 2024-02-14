/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { ForestaliService } from 'src/app/features/istanze-forestali/services/forestali.service';
import { CONST } from '../../constants';
import { AmbitoInstanza } from '../../models/ambito-instanza.model';
import { UserChoiceModel } from '../../models/user-choice.model';

@Component({
  selector: 'app-selezionato',
  templateUrl: './selezionato.component.html',
  styleUrls: ['./selezionato.component.css']
})
export class SelezionatoComponent implements OnInit, OnDestroy {

  unsubscribe$ = new Subject<void>();
  tipoIstanzaId = null;
  tipoIstanzaDescr = null;
  tipoAccreditamento: string = null;
  opzione : AmbitoInstanza[] = CONST.AMBITO_OPZIONE;

  constructor(private forestaliService: ForestaliService) { }

  ngOnInit() {
    this.forestaliService.getAdpforByTipoIstanzaId(sessionStorage.getItem(CONST.TIPO_ISTANZA_ID_KEY_STORE)).pipe(takeUntil(this.unsubscribe$))    
    .subscribe((res: UserChoiceModel) => {
      this.tipoIstanzaId = res.tipoIstanzaId;
      this.tipoIstanzaDescr = this.opzione.find(o => o.idAmbitoIstanza === this.tipoIstanzaId).descrAmbitoIstanza;
      this.tipoAccreditamento = res.fkTipoAccreditamento;
    });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

}
