/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { VincoloService } from 'src/app/features/istanze-forestali/services/vincolo.service';
import { CONST } from '../../constants';
import { AmbitoInstanza } from '../../models/ambito-instanza.model';
import { UserChoiceModel } from '../../models/user-choice.model';

@Component({
  selector: 'selezionato-vid',
  templateUrl: './selezionato-vid.component.html',
  styleUrls: ['./selezionato-vid.component.css']
})
export class SelezionatoVidComponent implements OnInit, OnDestroy {

  unsubscribe$ = new Subject<void>();
  tipoIstanzaDescr = null;
  tipoAccreditamento: string = null;
  opzione : AmbitoInstanza[] = CONST.AMBITO_OPZIONE;

  constructor(private vincoloService: VincoloService) { }

  ngOnInit() {
    this.vincoloService.getAdpfor(sessionStorage.getItem(CONST.TIPO_ISTANZA_ID_KEY_STORE)).pipe(takeUntil(this.unsubscribe$))
    .subscribe((res: UserChoiceModel) => {
      let tipoIstanzaId = res.tipoIstanzaId;
      this.tipoIstanzaDescr = this.opzione.find(o => o.idAmbitoIstanza === tipoIstanzaId).descrAmbitoIstanza;
      this.tipoAccreditamento = res.fkTipoAccreditamento;
    });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

}
