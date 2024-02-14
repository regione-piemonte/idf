/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';
import { AreaSearch } from '../models/area-search';

@Injectable({
  providedIn: 'root'
})
export class SearchStateService {
  
  private _lastAdsSearchParams: AreaSearch;  
  private _sortField: string;  
  private _page: number;  
  private _limit: number;
  private _isUpdate:boolean;
 
  constructor() { }

  public setLastAdsSearch(adsSearchParams: AreaSearch, sortField:string, page: number, limit: number, _isUpdate: boolean)  {
    this._lastAdsSearchParams = adsSearchParams;
    this._sortField = sortField;
    this._page = page;
    this._limit = limit;
    this._isUpdate = _isUpdate;
  }

  public removeLastAdsSearch() {
    this._lastAdsSearchParams = null;
  }

  public get lastAdsSearchParams(): AreaSearch {
    return this._lastAdsSearchParams;
  }
  
  public get sortField(): string {
    return this._sortField;
  }
  
  public get page(): number {
    return this._page;
  }
  
  public get limit(): number {
    return this._limit;
  }
  public set limit(value: number) {
    this._limit = value;
  }

  public get isUpdate(): boolean {
    return this._isUpdate;
  }
  public set isUpdate(isUpdate: boolean) {
    this._isUpdate = isUpdate;
  }
}
