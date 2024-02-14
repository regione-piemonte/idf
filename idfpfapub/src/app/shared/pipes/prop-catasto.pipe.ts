/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Pipe, PipeTransform } from '@angular/core';
import { PropCatastos } from '../models/data-taglio';
import { CONST } from '../constants';

@Pipe({
  name: 'propCatasto'
})
export class PropCatastoPipe implements PipeTransform {
  transform(propCatastosData: PropCatastos[], filter: string, args?: any): any[] {
    let filteredData: any[] =[];
    let filtered : any[] = [];
    switch (filter) {
      case CONST.SEZIONE:
        propCatastosData.forEach(function(item){
          let i = filteredData.findIndex(x => x.id === item.sezione);
          if(i <= -1){
            filteredData.push({id: item.sezione, field: item.sezione});
          }
          return null;
        });
        return filteredData;
      case (CONST.FOGLIO):
        filtered = propCatastosData.filter( item =>{
          return item.sezione === args.sezione.field;
        });
        filtered.forEach( item => {
          const isExist = filteredData.some(element => element.id === item.foglio);
          if (!isExist) filteredData.push({ id: item.foglio, field: item.foglio });
        });
        return filteredData;
      case (CONST.PARTICELLA):
        filtered = propCatastosData.filter( item =>{
          return (item.sezione === args.sezione.field && item.foglio === args.foglio.field) ;
        })
        filtered.forEach( item => {
          const isExist = filteredData.some(element => element.id === item.particella);
          if (!isExist) filteredData.push({ id: item.particella, field: item.particella, idProp: item.idGeoPlPropCatasto});
        });
        return filteredData;  
      default:
        return null;
    }
  }

}
