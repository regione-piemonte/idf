/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TableHeader, FieldType } from "../shared/models/table-header";
import { FormBuilder, FormGroup, FormControl } from "@angular/forms";
import { log } from "util";

export class FormUtil {


    public static addFormFromDataObject(dataObj: any, fb: FormBuilder, header: TableHeader[]) {

        let formGroup: FormGroup = fb.group({});
    
        header.forEach(headerField => {          
          formGroup.addControl(headerField.field, new FormControl({ value: this.getValueFromObject(dataObj, headerField), disabled: headerField.disabled}));
        })
        
        return formGroup;
      }



    public static  getValueFromObject(dataObj: any, headerField: TableHeader) {
      
        if (!dataObj[headerField.field]) return dataObj[headerField.field];
        switch (headerField.fieldType) {
          case FieldType.NUMBER:
            const decimalPlaces = headerField.decimalPlaces ? headerField.decimalPlaces : 0;
            const factor = Math.pow(10, decimalPlaces);
            return Math.round(dataObj[headerField.field] * factor) / factor;
          default: return dataObj[headerField.field];
        }
      }





}