/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class ConsolidaSchedaErrorResponse {

    codice: string;
    testo: string;
    payload: StepErrorsReport[];

}

export class StepErrorsReport {

    stepNumber: number;
    noOfErrors: number;
    errorMessages?:{
        [fieldName: string] : string;
    }
}

