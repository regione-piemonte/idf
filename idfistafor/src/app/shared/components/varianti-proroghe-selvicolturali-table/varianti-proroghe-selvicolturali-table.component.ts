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
  selector: 'app-varianti-proroghe-selvicolturali-table',
  templateUrl: './varianti-proroghe-selvicolturali-table.component.html',
  styleUrls: ['./varianti-proroghe-selvicolturali-table.component.css']
})
export class VariantiProrogheSelvicolturaliTableComponent implements OnInit, OnChanges {

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

  tableHeaders: TableHeader[] =[
    {field: 'nrIstanza', header: 'Numero istanza'},
    {field: 'dataInserimento', header: 'Data inserimento'},
    {field: 'descrizioneTipoIstanza', header: 'Tipo Istanza'},
    {field: 'descrizioneIntervento', header: 'Descrizione'},
    {field: 'tipoIntervento', header: 'Tipo Intervento'},
    {field: 'richiedente', header: 'Richiedente'},
    {field: 'comune', header: 'Comune'},
    {field: 'stato', header: 'Stato'},
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

  elaboraDatiRicerca(data:any){
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
          idIntervento: element.idIntervento,
          nrIstanza: element.nrIstanza,
          dataInserimento: element.dataInserimento.dayOfMonth + '/' + element.dataInserimento.monthValue + '/' + element.dataInserimento.year,
          richiedente: element.richiedente,
          comune: element.comune.denominazioneComune,
          stato: element.stato,
          descrizioneTipoIstanza: element.descrizioneTipoIstanza,
          descrizioneIntervento: element.descrizioneIntervento,
          tipoIntervento: element.tipoIntervento,

        }
      );
      if (element.dataInserimento.year === 0) {
        this.tableData[index].dataInserimento = 'N/A';
      }
    });

  }

  ricerca(){

    let prefixSearchString ='tipoIstanza=2&page=' + this.page + '&limit=' + this.limit + '&sort=' + this.sort;
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
        this.elaboraDatiRicerca(searchResult);
      }
    });


  }

  updateData(event: any) {
    this.page = event.first/event.rows + 1;
    this.limit = event.rows;
    this.ricerca();
  }

  editRow(item) {
    let index = item.idIntervento;
    if(this.boModifica){
      this.viewRow(index);
    }
    this.router.navigate(['modifica-tagli/' + index]);
  }
  deleteRow() {}

  viewRow(item){
    let index = item.idIntervento;
    const viewMode : boolean = true;
    let params = {viewMode:viewMode}
    if(this.boModifica){
      if(item.statoIstanza == 'Bozza'){
        this.showDialogError('L’istanza in oggetto è ancora in fase di bozza da parte del richiedente per cui non può ancora essere consultata');
        return;
      }
      params['boModifica'] = this.boModifica;
    }
    this.router.navigate(['modifica-tagli/' + index, params]);
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
