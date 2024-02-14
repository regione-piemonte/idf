/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Validators } from "@angular/forms";

export class ConsolidaValidator extends Validators{
  
    static valid:boolean = false;

    static required(){
        if(this && this.valid === true){
           return Validators.required;
        }else{
          return  ()=> null;
        }
    }
}