/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl} from "@angular/forms";
import { Observable} from "rxjs";
import { SelectItem } from "primeng/components/common/selectitem";
import { ReportService } from "src/app/shared/services/report.service";

@Component({
  selector: 'app-redirection-blocks',
  templateUrl: './redirection-blocks.component.html',
  styleUrls: ['./redirection-blocks.component.css']
})
export class RedirectionBlocksComponent implements OnInit {
  reportForm: FormGroup;
  reportDropdown$: Observable<SelectItem[]>;
  reportData:any;
  fileToUpload:File;
  formError:string;

  constructor(
    private reportService: ReportService
    ) {}

  ngOnInit() {
    this.fillDropdownReport();
    this.reportForm = new FormGroup({
      id: new FormControl(),
      file: new FormControl()
   });
  }

  fillDropdownReport() {
    //this.reportDropdown$ = this.reportService.getAllReport();
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
    console.log('ReportId: ' + this.reportForm.get('id').value);
    console.log('ReportFile: ' + this.reportForm.get('file').value);
    console.log(this.reportForm);
    let reportId = this.reportForm.get('report').value;
    let adsType = this.reportForm.get('ads').value;
    const formData: FormData = new FormData();
    formData.append('form', this.fileToUpload);
    this.reportService.getElaborateReportData(adsType,reportId,null,formData).subscribe(
      (response: any) => {
        console.log('RES', response);
        this.reportData = response;
        this.formError = response;
        console.log('formError: ' + this.formError);
      },
      (response: any) => {
        this.formError = response.error.text;
        console.log('formError: ' + this.formError);
        console.log(this.formError);
      }
    );
    
  }

}
