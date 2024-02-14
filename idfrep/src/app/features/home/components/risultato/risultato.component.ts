/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Output } from '@angular/core';
import { FormGroup,FormControl} from "@angular/forms";
import { Observable} from "rxjs";
import { SelectItem } from "primeng/components/common/selectitem";
import { ReportService } from "src/app/shared/services/report.service";
import { isNgTemplate } from '@angular/compiler';
import { takeUntil } from "rxjs/operators";
import { Subject } from "rxjs";
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-risultato',
  templateUrl: './risultato.component.html',
  styleUrls: ['./risultato.component.css']
})
export class RisultatoComponent implements OnInit {
  reportForm: FormGroup;
  reportDropdown$: Observable<SelectItem[]>;
  areeDiSaggioDropDown:any =[];
  @Output() public reportData:any;
  fileToUpload:File;
  formError:string;
  jobInfo:any;
  infoDatiCampione:any;
  tableHeaders:string[];
  originUrl:any = window.location.origin;
  reportTypeValue:any;
  adsTypeValue:any;
  unsubscribe$ = new Subject<void>();
  reportDropdownList:any = [];
  serviceSiforUrl:string;

  constructor(
    private reportService: ReportService
    ) {}

  ngOnInit() {
    this.serviceSiforUrl = environment.serviziSiforPrefix;
    this.fillDropdownAds();
    this.reportForm = new FormGroup({
      report: new FormControl(),
      file: new FormControl(),
      ads: new FormControl()
   });
  }


  disabilitaBtnElabora(){
   if(this.reportForm.get('ads').value && this.reportForm.get('report').value
    &&  this.fileToUpload){
    return false;
   }else{
     return true;
   }
  }

  fillDropdownAds() {
    this.reportService.getAdsTypeList().pipe(takeUntil(this.unsubscribe$))
    .subscribe(resp => {
      this.areeDiSaggioDropDown =  resp;
    });
  }

  fillDropdownReport(adsType:string) {
    this.reportDropdownList = this.reportService.getReportByAdsType(adsType).pipe(takeUntil(this.unsubscribe$))
    .subscribe(resp => {
      this.reportDropdownList =  resp;
    });
    console.log(this.reportDropdown$);
  }

  onAreaDiSaggioChange(event){
    let type = event['value'];
    this.reportForm.get('report').patchValue(null);
    this.fillDropdownReport(type);
    this.disabilitaBtnElabora();
  }

  handleFileInput(files: FileList) {
    console.log('handleFileInput');
    console.log(files[0]);
    if(files && files[0]){
      this.fileToUpload = files[0];
    }else{
      this.fileToUpload = null;
    }
    console.log('fileToUpload:');
    console.log(this.fileToUpload);
}

  elaboraReport(){
    this.formError = null;
    this.jobInfo  = null;
    this.infoDatiCampione = null;
    let reportId = this.reportForm.get('report').value;
    console.log('ReportId: ' + reportId);
    this.adsTypeValue = this.reportForm.get('ads').value;
    console.log('AreaSaggio: ' + this.adsTypeValue);
    console.log('ReportFile: ' + this.reportForm.get('file').value);
    console.log(this.fileToUpload);
    const formData: FormData = new FormData();
    formData.append('form', this.fileToUpload);

    let isometriaFilter = (<HTMLInputElement>document.getElementById('ipsometriaFilter'));
    let filterSpecie = '';
    let filterTipoForestale = '';
    let filterCatForestale = '';
    let filters = {};
    if(isometriaFilter && isometriaFilter.value !=''){
      let filterValue = isometriaFilter.value; 
      console.log('filterValue: ' + filterValue);
      if(this.reportData.ipsometria.filters.tipiForestali.includes(filterValue)){
        console.log('tipiForestali: ', filterValue);
        filters['filterTipoForestale'] = filterValue;
      }
      if(this.reportData.ipsometria.filters.categorieForestali.includes(filterValue)){
        console.log('categorieForestali: ', filterValue);
        filters['filterCatForestale'] = filterValue;
      }
      if(this.reportData.ipsometria.filters.specie.includes(filterValue)){
        console.log('specie: ', filterValue);
        filters['filterSpecie'] = filterValue;
      }
    }

    let filterIncrementi = (<HTMLInputElement>document.getElementById('incrementiSpecieFilter'));
    if(filterIncrementi && filterIncrementi.value !=''){
      filters['filterSpecie'] = filterIncrementi.value;
    }
    filterIncrementi = (<HTMLInputElement>document.getElementById('incrementiMaxDiametroFilter'));
    if(filterIncrementi && filterIncrementi.value !=''){
      filters['filterDiametroMax'] = filterIncrementi.value;
    }
    filterIncrementi = (<HTMLInputElement>document.getElementById('incrementiMinDiametroFilter'));
    if(filterIncrementi && filterIncrementi.value !=''){
      filters['filterDiametroMin'] = filterIncrementi.value;
    }


    this.reportService.getElaborateReportData(this.adsTypeValue,reportId,filters,formData).subscribe(
      (response: any) => {
        console.log('RES', response);
        this.reportData = response;
        this.jobInfo = this.getJobInfo(response);
        this.infoDatiCampione = response.infoDatiCampione;
        console.log('reportData: ');
        console.log(this.reportData);
      },
      (response: any) => {
        this.formError = 'Errore: ' + response.error.text;
        console.log('formError: ' + this.formError);
        console.log(this.formError);
      }
    );
    
  }

  getJobInfo(data){
    let info = {};
    info['fileName'] = this.fileToUpload.name;
    let fileData = new Date(this.fileToUpload.lastModified);
    info['fileDate'] = fileData.toLocaleDateString("it-IT") + " " 
    + fileData.getHours() + ":" + fileData.getMinutes();
    info['elaborazione'] = data.riepilogo.lavorazione;
    info['ads'] = data.riepilogo.ads;
    info['alberi'] = data.riepilogo.alberi;
    return info;
  }

}
