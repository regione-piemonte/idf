/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { AbstractControl, ValidatorFn,AsyncValidatorFn } from '@angular/forms';
import { CONST } from '../shared/constants';
import { ForestaliService } from '../features/istanze-forestali/services/forestali.service';
import { map } from 'rxjs/operators';



/* export function cfValidator(forestaliService: ForestaliService):AsyncValidatorFn  {
    return (control: AbstractControl) => {
        return forestaliService.checkCF(control.value)
            .pipe(
                map(user => {
                    console.log(user);
                    
                    if(user){
                        return {userExists:true}
                    } else{
                        return null
                    }})
            );
    }
} */
export const cfOrPiva = () => {
    return (g: AbstractControl) => {        
        if (g.value && g.value.length != 16 && g.value.length != 11) {
            return {cfOrPiva: true};
        }
    };
}

export const codiceFiscale = () => {
    return (g: AbstractControl) => {        
        if (g.value && g.value.length == 16) {
            var pattern = CONST.PATTERN_CODICE_FISCALE;
            var re = new RegExp(pattern);
            if (!re.test(g.value)) {
                return {codiceFiscale: true};
            }
        }
    };
}

export const partitaIva = () => {
    return (g: AbstractControl) => {        
        if (g.value && g.value.length == 11) {
            var pattern = CONST.PATTERN_PARTITA_IVA;
            var re = new RegExp(pattern);
            if (!re.test(g.value)) {
                return {partitaIva: true};
            }
        }
    };
}

// Funzione ESTERNA
// Accoglie le informazioni necessarie per effettuare la validazione
export const forbiddenValidator = (...values: string[]): ValidatorFn => {
    // Funzione INTERNA
    // Viene restituita dalla funzione esterna ed è quella utilizzata da Angular
    // ai fini della validazione
    return (c: AbstractControl) => {
        for (const value of values) {
            if (value === c.value) {
                // Oggetto di errore
                // chiave: nome validatore
                // valore: true
                return {
                    forbidden: true
                };
            }
        }
    };
};