/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { TableHeader } from 'src/app/shared/models/table-header';
import { ForestaliService } from '../../services/forestali.service';
import { ViewInstance, ViewInstanceTable, Content } from 'src/app/shared/models/view-instance';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';

@Component({
  selector: 'app-visualizza',
  templateUrl: './visualizza.component.html',
  styleUrls: ['./visualizza.component.css']
})
export class VisualizzaComponent implements OnInit, OnDestroy {

unsubscribe$ = new Subject<void>();
sortedColumn = 'numeroIstanza';
tableData: ViewInstanceTable[] = [];
tableHeaders: TableHeader[] = [
  {field: 'numeroIstanza', header: 'Numero istanza'},
  {field: 'dataInvio', header: 'Data invio'},
  {field: 'richiedente', header: 'Richiedente'},
  {field: 'comune', header: 'Comune'},
  {field: 'stato', header: 'Stato'}
];
paging: any;
totalCount: number;
sortField:any;

  constructor(private forestaliService: ForestaliService, private router: Router, 
    private dialogService: DialogService) { }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  

  goToHome() { this.router.navigate([''], { queryParams: { modificaSelezione: true }}); }

  getUpdatedTable(event) {
    this.paging = {
      page: event.page,
      limit: event.limit
    };
    this.sortField = event.sortField;
    this.loadPlans(this.paging, event.field);
  }

  loadPlans(paging: any, sortField?: string) {
    this.tableData.length = 0;
    this.forestaliService.getArrayOfInstance(paging.page, paging.limit, sortField).pipe(takeUntil(this.unsubscribe$))
    .subscribe((res: Content<ViewInstance[]>) => {
      if (res.totalElements !== 0) {
      this.totalCount = res.totalElements;
        res.content.forEach((element, index) => {
          if (!element.dataInvio) {
            element['dataInvio'] = {
              dayOfMonth: 0,
              month: '',
              year: 0
            };
          }
        });

      res.content.forEach((element, index) => {
        this.tableData.push(
          {
            idIntervento: element.idIntervento,
            numeroIstanza: element.nrIstanzaForestale,
            idTipoIstanza:element.idTipoIstanza,
            dataInvio: element.dataInvio.dayOfMonth + '/' + element.dataInvio.monthValue + '/' + element.dataInvio.year,
            richiedente: `${element.richiedente.codiceFiscale} - ${element.richiedente.cognome ? element.richiedente.cognome : element.richiedente.denominazione} ${element.richiedente.nome ? element.richiedente.nome : ''}`,
            comune: element.comune.denominazioneComune,
            stato: element.stato,
            idStato: element.idStato
          }
          );
          if (element.dataInvio.year === 0) {
            this.tableData[index].dataInvio = 'N/A';
          }
        });
      }
      // this.tableData.length = 0;
    });
  }

  getRowId(event) {
  }

  deleteRow(event){
    this.dialogService.showDialog(true, 'Confermare la cancellazione dell\'istanza?', 'Attenzione', DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) {
        this.deleteConfirm(event);
      }
    });
  }

  deleteConfirm(event) {
    this.forestaliService.deleteIntervento(event).subscribe(res => {
      console.log('delete done');
      this.loadPlans(this.paging,this.sortField);
    })
  }

  editRow(event) {
    this.router.navigate(['modifica/' + event]);
  }

  viewRow(event : any){
    const viewMode : boolean = true;
    this.router.navigate(['modifica/' + event, {viewMode:viewMode}]);
  }
}
