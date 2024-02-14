/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ForestaliService } from 'src/app/features/istanze-forestali/services/forestali.service';
import { ButtonType, DialogButtons } from '../../error-dialog/error-dialog.component';
import { TableHeader } from '../../models/table-header';
import { DialogService } from '../../services/dialog.service';

@Component({
  selector: 'app-varianti-proroghe-table',
  templateUrl: './varianti-proroghe-table.component.html',
  styleUrls: ['./varianti-proroghe-table.component.css']
})
export class VariantiProrogheTableComponent implements OnInit, OnChanges {

  @Input() data: any;

  tableData = [];
  paging: any;
  totalCount: number;
  page = 1;
  limit = 5;
  sort = '-numeroIstanza';

  replaceTrueFalse=['X',''];

  unsubscribe$ = new Subject<void>();

  boModifica:boolean;

  tableHeaders: TableHeader[] = [
    {field: 'numeroIstanza', header: 'Numero istanza'},
    {field: 'dataInvio', header: 'Data invio'},
    {field: 'competenza', header: 'Competenza'},
    {field: 'richiedente', header: 'Richiedente'},
    {field: 'comune', header: 'Comune'},
    {field: 'statoIstanza', header: 'Stato'},
    {field: 'statoCauzione', header: 'Ricevute di versamento'},
    {field: 'varianteProroga', header: 'Con varianti/proroghe'},
    {field: 'rimboschimento', header: 'Rimboschimento'}
  ];

  constructor(private forestaliService: ForestaliService, private router: Router, 
    private dialogService: DialogService, public route: ActivatedRoute) { }

  ngOnInit() {    
    this.boModifica = this.route.snapshot.params['boModifica'];
    this.ricerca();
  }

  ngOnChanges(){
    this.ricerca();
  }

  getValue(val:any){
    if(this.replaceTrueFalse && this.replaceTrueFalse.length == 2){
      return val==true?this.replaceTrueFalse[0]:val==false?this.replaceTrueFalse[1]:val;
    }
    return val;
  }

  getBgColor(car: any, col:any){
    if (col.field == 'statoCauzione' && this.getValue(car[col.field]) === 'MANCANTE'){
      return 'red';
    }
    if (col.field == 'varianteProroga' && (this.getValue(car[col.field]) === 'VARIANTE' || this.getValue(car[col.field]) === 'PROROGA' || this.getValue(car[col.field]) === 'SI')){
      return 'yellow';
    }
  }

  elaboraDatiRicercaVincolo(data:any){
    data.content.forEach((element, index) => {
      if(!element.dataInvio) {
        element['dataInvio'] = {
          dayOfMonth: 0,
          month: '',
          year: 0
        };
      }
    });

    this.tableData = [];
    data.content.forEach((element, index) => {
    this.tableData.push(
      {
        idIstanza: element.idIntervento,
        numeroIstanza: element.nrIstanzaForestale,
        dataInvio: element.dataInvio.dayOfMonth + '/' + element.dataInvio.monthValue + '/' + element.dataInvio.year,
        richiedente: `${element.richiedente.codiceFiscale} - ${element.richiedente.cognome ? element.richiedente.cognome : element.richiedente.denominazione} ${element.richiedente.nome ? element.richiedente.nome : ''}`,
        comune: element.comune.denominazioneComune,
        statoIstanza: element.stato,
        competenza: element.competenza,
        statoCauzione: element.statoCauzione,
        varianteProroga: element.varianteProroga,
        rimboschimento: element.rimboschimento
      }
      );
      if (element.dataInvio.year === 0) {
        this.tableData[index].dataInvio = 'N/A';
      }
    });
  }

  ricerca(){
    let prefixSearchString ='tipoIstanza=4&page=' + this.page + '&limit=' + this.limit + '&sort=' + this.sort;
    if(this.data.type == 'variante'){
      prefixSearchString+='&padreVariante=' + this.data.idPadre;
    }else if(this.data.type == 'proroga'){
      prefixSearchString+='&padreProroga=' + this.data.idPadre;
    }  
    this.forestaliService.ricerca(prefixSearchString)
    .pipe(takeUntil(this.unsubscribe$)).subscribe((searchResult) => {
      this.tableData = searchResult.content;
      this.totalCount = searchResult.totalElements;    
      if(searchResult.content !== undefined){
        this.elaboraDatiRicercaVincolo(searchResult);
      }      
    });
  }

  updateData(event: any) {
    this.page = event.first/event.rows + 1;
    this.limit = event.rows;
    this.ricerca();
  }

  editRow(item) {
    let index = item.idIstanza;
    if(this.boModifica){
      this.viewRow(index);
    }
    this.router.navigate(['modifica-vid/' + index]);
  }
  deleteRow() {}

  viewRow(item){
    let index = item.idIstanza;
    const viewMode : boolean = true;
    let params = {viewMode:viewMode}
    if(this.boModifica){
      if(item.statoIstanza == 'Bozza'){
        this.showDialogError('L’istanza in oggetto è ancora in fase di bozza da parte del richiedente per cui non può ancora essere consultata');
        return;
      }
      params['boModifica'] = this.boModifica;
    }
    this.router.navigate(['modifica-vid/' + index, params]);
  }

  showDialogError(msg){
    this.dialogService.showDialog(true, msg, 'Attenzione', DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) {
        console.log('BUTTON WORKS');
      }
    });
  }

  getDescr(elem:any){
    let label = this.tableHeaders[0].header;
    let key = this.tableHeaders[0].field;
    return label.toLocaleLowerCase() + " " + elem[key];
  }

}
