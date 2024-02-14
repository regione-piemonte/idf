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
import { TipoAccreditamento } from 'src/app/shared/models/tipo-accreditamento.model';

@Component({
  selector: 'app-selezionato-tagli',
  templateUrl: './selezionato-tagli.component.html',
  styleUrls: ['./selezionato-tagli.component.css']
})
export class SelezionatoTagliComponent implements OnInit, OnDestroy {

  unsubscribe$ = new Subject<void>();
  tipoIstanzaId = null;
  tipoIstanzaDescr = null;
  tipoAccreditamento: string = null;
  sportelloPG: string = null;
  opzione : AmbitoInstanza[] = CONST.AMBITO_OPZIONE;

  constructor(private forestaliService: ForestaliService) { }

  ngOnInit() {
    this.forestaliService.getAdpforByTipoIstanzaId(sessionStorage.getItem(CONST.TIPO_ISTANZA_ID_KEY_STORE)).pipe(takeUntil(this.unsubscribe$))
    .subscribe((res: UserChoiceModel) => {
      this.tipoIstanzaId = res.tipoIstanzaId;
      this.tipoIstanzaDescr = this.opzione.find(o => o.idAmbitoIstanza === (this.tipoIstanzaId === 3 ? 2 : this.tipoIstanzaId)).descrAmbitoIstanza;
      this.tipoAccreditamento = res.fkTipoAccreditamento;
      this.sportelloPG = JSON.parse(sessionStorage.getItem(CONST.SPORTELLO_PG_KEY_STORE));
    });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

}
