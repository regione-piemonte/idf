/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, OnDestroy, Inject } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { TableHeader, FieldType } from '../../models/table-header';
import { TableElements } from '../../models/tableElements';
import { TableTransformPipe } from '../../pipes/table-transform.pipe';
import { FormUtil } from 'src/app/utils/form-util';

@Component({
  selector: 'app-vertical-table',
  templateUrl: './vertical-table.component.html',
  styleUrls: ['./vertical-table.component.css'],
})
export class VerticalTableComponent implements OnInit {

  @Input() data: any[];
  @Input() header: TableHeader[];
  @Input() disabled: boolean;
  @Input() showTotal: boolean;
  @Input() summaryColumnData: any;
  @Input() columnsForm: FormGroup;


  // columnsForm: FormGroup;
  columnForm: FormGroup;
  transformedTableElements: TableElements[] = [];
  totalRipresaIntervento: number = 0;
  _fieldTypeEnum = FieldType;
  constructor(private fb: FormBuilder, private mirrorPipe: TableTransformPipe) {
  }
  
  // ****                            **** //

  _form: FormArray;

  ngOnInit() {
        
    
  }

  getValue(form,value){
    return ((form.controls[value].value?form.controls[value].value:'') + '').replace('.',',');
  }

  formatTwoDecimal(value){
    if(typeof value == 'number'){
      return value.toFixed(2).replace('.',',');
    }
    return value;
  }
 

  

}



