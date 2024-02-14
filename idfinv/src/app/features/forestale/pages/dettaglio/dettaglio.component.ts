/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { TableHeader } from "src/app/shared/models/table-header";
import { ForestaleConfig } from "../../forestale-config";
import { ForestaleSampleService } from "src/app/shared/services/forestale-sample.service";
import { Observable, Subject } from "rxjs";
import { DatiStazionaliField } from "src/app/shared/models/dati-stazionali-field";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { takeUntil } from "rxjs/operators";
import { TabsService } from "src/app/services/tabs.service";
import { AlberiCampioneDominante } from "src/app/shared/models/alberi-campione-dominante";
import { RelascopicaSemplice } from "src/app/shared/models/relascopica-semplice";
import { SearchService } from "src/app/services/search.service";
import { SaveFileService } from "src/app/shared/services/save-file.service";
import { AreaDiSaggio } from "src/app/shared/models/area-di-saggio";
import { Content } from "src/app/shared/models/table-object";
import { Tipologia } from "src/app/shared/models/tipoEnum";
import { GeneraliService } from "src/app/services/generali.service";

@Component({
  selector: "app-dettaglio",
  templateUrl: "./dettaglio.component.html",
  styleUrls: ["./dettaglio.component.scss"]
})
export class DettaglioComponent implements OnInit, OnDestroy {
  conteggioAngolareTableHeaders: TableHeader[];
  alberiTableHeaders: TableHeader[];
  alberiTableData$: Observable<AlberiCampioneDominante[]>;
  alberiTableData: RelascopicaSemplice[] = [];
  conteggioTableData$: Observable<RelascopicaSemplice[]>;
  conteggioTableData: RelascopicaSemplice[] = [];
  sortedColumn: string = "codice";
  alberiSortedColumn: string = "specie";
  dataStazionaliFields: DatiStazionaliField[] = [];
  codiceAds: number;
  conteggioCodiceAdsList: string[];
  alberiCodiceAdsList: string[];
  tipologia: number;
  grayPanel: AreaDiSaggio;
  totalElementsCavaletti: number;
  totalElementsDettaglio: number;
  paging: any;

  firstTabTipoCampione: string = 'CAM';
  secondTabTipoCampione: string = 'CAV';

  unsubscribe$ = new Subject<void>();

  sempliceTipologia: number = Tipologia.RELASCOPICA_SEMPLICE;/*Relascopica semplice*/
  completaTipologia: number = Tipologia.RELASCOPICA_COMPLETA/*Relascopica completa*/

  constructor(
    private forestaleService: ForestaleSampleService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private tabsService: TabsService,
    private searchService: SearchService,
    private saveFileService: SaveFileService,
    private generaliService: GeneraliService
  ) {}

  ngOnInit() {
    this.activatedRoute.params
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: Params) => {
        this.codiceAds = res["id"];
        this.forestaleService.getGrayPanelSteps(this.codiceAds.toString())
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(
          (response: AreaDiSaggio) => {
            this.grayPanel = response;
          }
        );
        
        this.forestaleService.getDettaglioData(this.codiceAds.toString()).subscribe(
          response => {
            this.tipologia = response.tipoAds;
            this.dataStazionaliFields = response.datiStazionali;
            this.fillFirstTabTable(this.tipologia);

          }
        );
      });
      this.conteggioAngolareTableHeaders = ForestaleConfig.conteggioAngolareTableHeaders();
      this.alberiTableHeaders = ForestaleConfig.alberiCavallettatiTableHeaders(); 
  }

  fillFirstTabTable(tipologia: number) {
    
    if(tipologia === Tipologia.RELASCOPICA_SEMPLICE) {
      this.tabsService.getDettaglioTableDataSemplice(this.codiceAds.toString())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: RelascopicaSemplice[]) => {
          
          if(response) {
            this.conteggioTableData = response.filter(item => item.codTipoCampione === this.secondTabTipoCampione);
            this.conteggioCodiceAdsList = this.conteggioTableData.length > 0 ? this.conteggioTableData.map(item => item.codiceAds) : [];
            this.alberiCodiceAdsList = this.alberiTableData.length > 0 ? this.alberiTableData.map(item => item.codiceAds) : [];
           }
        }
      );
    } else if(tipologia === Tipologia.RELASCOPICA_COMPLETA) {
      this.tabsService.getDettaglioTableDataCompleta(this.codiceAds.toString())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: Content<RelascopicaSemplice[]>) => {
          
          if(response.content) {
            this.conteggioTableData = response.content;//.filter(item => item.codTipoCampione !== this.secondTabTipoCampione);
          } else {
            this.conteggioTableData = [];
          }
          
        }
      );
    }
  }

  fillTables(tipologia: number, paging: any, sortField: string) {
    if(tipologia === Tipologia.RELASCOPICA_SEMPLICE) {
      this.tabsService.getDettaglioTableDataSemplice(this.codiceAds.toString())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: RelascopicaSemplice[]) => {
          
          this.conteggioTableData = response.filter(item => item.codTipoCampione === this.firstTabTipoCampione);
          this.conteggioCodiceAdsList = this.conteggioTableData.length > 0 ? this.conteggioTableData.map(item => item.codiceAds) : [];
          this.alberiTableData = response.filter(item => item.codTipoCampione === this.secondTabTipoCampione);
          
          this.alberiCodiceAdsList = this.alberiTableData.length > 0 ? this.alberiTableData.map(item => item.codiceAds) : [];
          this.totalElementsCavaletti = response.length;
          this.totalElementsCavaletti = response.length;
        }
      );
    } else if(tipologia === Tipologia.RELASCOPICA_COMPLETA) {
      
      
      this.tabsService.getDettaglioCavallettatiCompletaTable(this.codiceAds.toString(), paging.page, paging.limit, sortField)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: Content<RelascopicaSemplice[]>) => {
          
          this.alberiTableData = response.content ? response.content.filter(relCom => relCom.diametro || relCom.altezza || relCom.incremento)   : [];
          this.totalElementsCavaletti = response.totalElements;
          this.alberiCodiceAdsList = this.alberiTableData.length > 0 ? this.alberiTableData.map(item => item.codiceAds) : [];
        }
      );
    }
  }

  navigateToMap() {
  }

  exportExcel(tableType: string) {
    let obj = {};
    let tipo: string;
    if(tableType === 'conteggio') {
      obj["codiceADSList"] = this.conteggioCodiceAdsList;
    } else if(tableType === 'alberi') {
      obj["codiceADSList"] = this.alberiCodiceAdsList;
    }
    if(this.tipologia === Tipologia.RELASCOPICA_SEMPLICE) {
      tipo = 'Semplice';
    } else if(this.tipologia === Tipologia.RELASCOPICA_COMPLETA) {
      tipo = 'Completa';
    }
    this.searchService
      .downloadExcelFileDettaglio(this.codiceAds, tipo)
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

  getTableChanges(event) {
    this.paging = {
      page: event.page,
      limit: event.limit
    };
    this.fillTables(this.tipologia, this.paging, event.field);
  }

  navigateToSearch() {
    this.router.navigate(['/forestale', 'search'], { queryParams: { recoverPrevious: true }, queryParamsHandling: 'merge' });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  openGeeco(){
    this.generaliService.getCartograficoByIdUrl(1,'' + this.codiceAds)
    .subscribe(
      (response: any) => {
        window.open(response['geecourl'], "_blank");
      }
    );
    return false;
  }

}
