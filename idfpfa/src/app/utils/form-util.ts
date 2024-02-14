/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TableHeader, FieldType } from "../shared/models/table-header";
import { FormBuilder, FormGroup, FormControl, Validators } from "@angular/forms";
import { log } from "util";
import { CONST } from "../shared/constants";

export class FormUtil {


    public static addFormFromDataObject(dataObj: any, fb: FormBuilder, header: TableHeader[]) {

        let formGroup: FormGroup = fb.group({});
    
        header.forEach(headerField => {          
          formGroup.addControl(headerField.field, new FormControl({ value: this.getValueFromObject(dataObj, headerField), disabled: headerField.disabled},[Validators.pattern(CONST.PATTERN_NUMERIC_DECIMAL_COMMA)]));
        })
        // console.log('form group:', formGroup.getRawValue());
        
        return formGroup;
      }



    public static  getValueFromObject(dataObj: any, headerField: TableHeader) {
      // console.log('value:', dataObj[headerField.field],headerField.field);
      
        if (!dataObj[headerField.field]) return dataObj[headerField.field];
        switch (headerField.fieldType) {
          case FieldType.NUMBER:
            const decimalPlaces = headerField.decimalPlaces ? headerField.decimalPlaces : 0;
            const factor = Math.pow(10, decimalPlaces);
            let res = '' + Math.round(dataObj[headerField.field] * factor) / factor;
            res = res.replace('.',',');
            if(decimalPlaces > 0){
              if(res.indexOf(',') == -1){
                res = res + ',';
              }
              let decimalsMissing = decimalPlaces - ((res.length - 1) - res.indexOf(','));
              for(let i = 0; i<decimalsMissing; i++){
                res = res + '0';
              }
            }
            return res
          default: return dataObj[headerField.field];
        }
      }





}