/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { TableHeader } from 'src/app/shared/models/table-header';
import { ForestaleConfig } from '../../forestale-config';
import { Specie } from 'src/app/shared/models/specie';
import { Observable, Subject } from 'rxjs';
import { SrvError } from 'src/app/shared/models/test/error';
import { ForestaleSampleService } from 'src/app/shared/services/forestale-sample.service';
import { Dettaglio } from 'src/app/shared/models/dettaglio';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { AreaDiSaggio } from 'src/app/shared/models/area-di-saggio';
import { AlberiCampioneDominante } from 'src/app/shared/models/alberi-campione-dominante';
import { TabsService } from 'src/app/services/tabs.service';
import { Content } from 'src/app/shared/models/table-object';
import { SearchService } from 'src/app/services/search.service';
import { SaveFileService } from 'src/app/shared/services/save-file.service';
import { GeneraliService } from 'src/app/services/generali.service';

@Component({
  selector: 'app-forestale-tabs',
  templateUrl: './forestale-tabs.component.html',
  styleUrls: ['./forestale-tabs.component.css']
})
export class ForestaleTabsComponent implements OnInit, OnDestroy {

  tableHeaders: TableHeader[];
  tableData$: Observable<Specie[] | SrvError>;
  datiStazionali$: Observable<AreaDiSaggio>;
  sortableColumnAlberi: string  = 'codiceADS';
  codiceAds: number;
  unsubscribe$ = new Subject<void>();
  otherTabsData$: Observable<AlberiCampioneDominante[]>;
  alberiTableData: AlberiCampioneDominante[] = [];
  grayPanel$: Observable<AreaDiSaggio>;
  paging: any;
  totalElements: number;

  constructor(private forestaleService: ForestaleSampleService, 
              private activatedRoute: ActivatedRoute, 
              private tabsService: TabsService,
              private router: Router,
              private searchService: SearchService,
              private saveFileService: SaveFileService,
              private generaliService: GeneraliService) { }

  ngOnInit() {
    this.activatedRoute.params
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response: Params) => {
        this.codiceAds = response['id'];
        this.grayPanel$ = this.forestaleService.getGrayPanelSteps(this.codiceAds.toString());
        this.datiStazionali$ = this.forestaleService.getAreaDiSaggioByCodiceAds(this.codiceAds.toString());
        this.otherTabsData$ = this.tabsService.getChampionTrees(this.codiceAds);
      }
    );
    this.tableHeaders = ForestaleConfig.cavalettatiTableHeaders();
  }

  alberiTableChanges(event) {
    this.paging = {
      page: event.page,
      limit: event.limit
    };
    if(event.field === 'codiceAds') {
      event.field = 'codiceADS';
    }
    if(event.field === 'semePollone') {
      event.field = 'seme';
    }
    if(event.field === '-codiceAds') {
      event.field = '-codiceADS';
    }
    if(event.field === '-semePollone') {
      event.field = '-seme';
    }
    this.tabsService.getAlberiCavalletattiTable(this.codiceAds.toString(), this.paging.limit, this.paging.page, event.field).subscribe(
      (response: Content<AlberiCampioneDominante[]>) => {
        this.alberiTableData = response.content || [];
        this.totalElements = response.totalElements;
      }
    );
  
  }

  downloadExcel() {
    this.searchService
      .downloadExcelFileTabs(this.codiceAds)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response: any) => {
        let contentDisposition = response.headers.get("content-disposition");
        let filename = contentDisposition
          .split(";")[1]
          .split("filename")[1]
          .split("=")[1];
        this.saveFileService.saveFile(response, filename);
      });
  }
  
  navigateToSearch() {
    //this.router.navigate(['/forestale', 'search']);
    this.router.navigate(['/forestale', 'search'], { queryParams: { recoverPrevious: true }, queryParamsHandling: 'merge' });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  openGeeco(){
    this.generaliService.getCartograficoByIdUrl(1,''+this.codiceAds)
    .subscribe(
      (response: any) => {
          window.open(response['geecourl'], "_blank");
      }
    );
    return false;
  }
 
}
