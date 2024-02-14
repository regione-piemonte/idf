/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  OnDestroy,
  ViewChild,
  ElementRef,
  ModuleWithComponentFactories
} from "@angular/core";
import { FormBuilder, FormGroup, FormArray } from "@angular/forms";
import { CONST } from "src/app/shared/constants";
import { ForestaleSampleService } from "src/app/shared/services/forestale-sample.service";
import { Observable, Subject } from "rxjs";
import { TableHeader } from "src/app/shared/models/table-header";
import { ForestaleConfig } from "../../forestale-config";
import { SelectItem } from "primeng/components/common/selectitem";
import { AreaSearch } from "src/app/shared/models/area-search";
import { Router, ActivatedRoute } from "@angular/router";
import { takeUntil } from "rxjs/operators";
import { SearchService } from "src/app/services/search.service";
import { AreaDiSaggio } from "src/app/shared/models/area-di-saggio";
import { SaveFileService } from "src/app/shared/services/save-file.service";
import { Content } from "src/app/shared/models/table-object";
import { DialogService } from "src/app/shared/services/dialog.service";
import { DialogButtons, ButtonType, DialogIconType } from "src/app/shared/components/error-dialog/error-dialog.component";
import { Tipologia } from "src/app/shared/models/tipoEnum";
import { SearchStateService } from "src/app/shared/services/search-state.service";
import { StatoAdsEnum } from "src/app/shared/models/stato-ads.enum";
import { GeneraliService } from "src/app/services/generali.service";
import { ErrorCodes } from "src/app/shared/models/error-codes";

@Component({
  selector: "app-search-form",
  templateUrl: "./search-form.component.html",
  styleUrls: ["./search-form.component.scss"]
})
export class SearchFormComponent implements OnInit, OnDestroy {
  @ViewChild("container") container: ElementRef;

  editForm: boolean = false;
  searchForm: FormGroup;
  currentYear: number = new Date().getFullYear();
  it: any;
  startFrom: Date = new Date();
  tableData$: Observable<Content<AreaDiSaggio[]>>;
  tableData: Content<AreaDiSaggio[]> = { content: [] };
  rowId: any;
  tableHeaders: TableHeader[];
  userCF:any;
  previousSearch:any;

  provinciaDropdown$: Observable<SelectItem[]>;
  comuneDropdown$: Observable<SelectItem[]>;
  comuneDropdown: SelectItem[];
  comuneDropdowns: Array<SelectItem[]>;
  categoriaDropdown$: Observable<SelectItem[]>;
  ambitoDiRilevamentoDropdown$: Observable<SelectItem[]>;
  dettaglioDropdown$: Observable<SelectItem[]>;
  dettaglioDropdowns: Array<SelectItem[]>;
  tipologiaDropdown$: Observable<SelectItem[]>;
  statoSchedaDropdown$: Observable<SelectItem[]>;
  dataRilevamentoDropdown$: Observable<SelectItem[]>;
  searchObject: AreaSearch;
  selectedAds: AreaDiSaggio;

  maxDate: Date = new Date();

  
  showDeleteDialog:boolean;

  statoAdsEnum = StatoAdsEnum;
  // sortedColumn: string = "codiceADS";
  // selectableId: string = "codiceADS";

  sortedColumn: string = "idgeoPtAds";
  selectableId: string = "idgeoPtAds";
  // superficieTipologia: string = "Temporanea";/*Superficie nota*/
  // sempliceTipologia: string = "Permanente";/*Relascopica semplice*/
  // completaTipologia: string = "Satellite"/*Relascopica completa*/
  idgeoPtADSList: number[];
  paging: any = {
    page: 1,
    limit: 25
  };;
  sortField: string = "idgeoPtAds";
  unsubscribe$ = new Subject<void>();
  searchQuery: string = "idgeoPtAds";
  sortOrder = 1;
  showNoData: boolean;
  showPreviousSearchParams: boolean = false;

  constructor(
    private fb: FormBuilder,
    private forestaleService: ForestaleSampleService,
    private router: Router,
    private searchService: SearchService,
    private saveFileService: SaveFileService,
    private dialogService: DialogService,
    private activatedRoute: ActivatedRoute,
    private searchStateService: SearchStateService,
    private generaliService: GeneraliService

  ) {
    this.fillSearchForm();
    this.it = CONST.IT;
    this.comuneDropdowns = new Array<SelectItem[]>();
    this.dettaglioDropdowns = new Array<SelectItem[]>();
    if (this.activatedRoute.snapshot.params['torna']) { this.showPreviousSearchParams = true; }
  }

  ngOnInit() {
    this.userCF = JSON.parse(sessionStorage.getItem('user'))['codiceFiscale'];
    this.tableHeaders = ForestaleConfig.searchHeaders();
    this.fillDropdownMenus();
    this.onDateChange(0);
    if (this.activatedRoute.snapshot.queryParams['recoverPrevious']) {
      this.recoverPreviousState();
    }
    if (this.router.url.includes('/modifica-consolida')) {
      this.editForm=true;
    }
    
    console.log('editForm: ' + this.editForm);
  }


  recoverPreviousState() {    
   
    
    if(!sessionStorage.getItem('lastSearch')){
      return;
    }
    this.previousSearch = JSON.parse(sessionStorage.getItem('lastSearch'));
    
    console.log(sessionStorage.getItem('lastSearch'));
    this.paging = {
      page: this.previousSearch['page'],
      limit: this.previousSearch['limit']
    };
    this.searchQuery = this.previousSearch['searchQuery'];
    this.sortField = this.searchQuery.startsWith('-')? this.searchQuery.substring(1): this.searchQuery;
    this.sortOrder = this.searchQuery && this.searchQuery.startsWith('-') ? -1 : 1;
    this.editForm = this.previousSearch['editForm'];


    this.previousSearch['searchObject']['istatProvs'].forEach((ip, index) => {      
      this.setValueToFormArray(index, ip, this.istatProvs, this.fillSearchIstatProv, this.fillComune);
    });

    this.previousSearch['searchObject']['idCategoriaForestales'].forEach((idCatForest, index) => {      
        this.setValueToFormArray(index, idCatForest, this.idCategoriaForestales, this.fillIdCategoriaForestale)
    }
    );

    this.previousSearch['searchObject']['tipologias'].forEach((tipologia, index) => {      
        this.setValueToFormArray(index, tipologia, this.tipologias, this.fillTipologias)
      });

      this.previousSearch['searchObject']['statoSchedas'].forEach((statoScheda, index) => {      
      this.setValueToFormArray(index, statoScheda, this.statoSchedas, this.fillStatoSchedas)
    });

    this.previousSearch['searchObject']['idAmbitos'].forEach((ambito, index) => {      
      this.setValueToFormArray(index, ambito, this.idAmbitos, this.fillIdAmbitos, this.fillDettaglioAmbito)
    });

    (this.previousSearch['searchObject']['formDates'] as {fromDate: string, toDate: string}[]).forEach( (dates , index: number) => {      
      this.setValueToFormArray(index, dates, this.formDates, this.fillFormDates)
    });

    console.log('start searching: ',this.paging, this.searchQuery); 
    
    this.search(this.paging, this.searchQuery);
  }

  

  setValueToFormArray(index: number, object: any, formArray: FormArray, createGroupFN: Function, refreshFunction?: Function) {
    if (index === 0) {
      formArray.removeAt(index);
    }
    formArray.push(createGroupFN(object, this));
    if (refreshFunction) {refreshFunction(index,this);}
  }


  patchValueToComponent(formGroup: FormGroup, object: any) {
    Object.keys(object).forEach(key => {
      formGroup.get(key).patchValue(object[key]);
    })
  }

  pushValueToFormGroup(formGroup: FormGroup, object: any) {
    const newObject = {};
    Object.keys(object).forEach(key => {
      formGroup.get(key).patchValue(object[key]);
    })
  }
  newSearch(paging: any, sortField?: string) {
    paging.page = 1;
    this.search(paging,sortField);
  }

  search(paging: any, sortField?: string) {
    if (!paging || !paging.page) return;
    this.searchQuery = sortField ? sortField : '';
    this.sortField = this.searchQuery && this.searchQuery.startsWith('-') ? this.searchQuery.substring(1): this.searchQuery;
    this.sortOrder = this.searchQuery && this.searchQuery.startsWith('-') ? -1 : 1;
    this.searchObject = this.searchForm.value;
    this.paging = paging; 
    if (Object.keys(this.searchObject).every(item => this.searchObject[item] === "" || this.searchObject[item] === null)) {
      this.dialogService.showDialog(true, 'Tutti i campi sono vuoti', 'Avvertimento', DialogButtons.OK, '', (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
        }
      }, DialogIconType.INFORMATION);
    } else {
      this.forestaleService.getSearchedData(paging.page, paging.limit, this.searchObject, this.searchQuery)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(
          (response: Content<AreaDiSaggio[]>) => {
            this.tableData = response.content ? response : { content: [] }
            this.showNoData = true;
            this.idgeoPtADSList = response.content ? response.content.map(item => item.idgeoPtAds) : [];
            this.searchStateService.setLastAdsSearch(this.searchObject, this.searchQuery, paging.page, paging.limit, this.editForm);
            this.saveLastSearch(paging.page, paging.limit);
          }
        );
      // this.forestaleService
      //   .getSearchedData(paging.page, paging.limit, this.searchObject, sortField)
      //   .pipe(takeUntil(this.unsubscribe$))
      //   .subscribe(res => {
      //     this.idgeoPtADSList = res.content ? res.content.map(item => item.idgeoPtAds) : [];
      //   });
    }
  }

  saveLastSearch(page,limit){
    this.previousSearch = {searchObject:this.searchObject, searchQuery:this.searchQuery, 
      page:page, limit:limit, editForm: this.editForm};
    sessionStorage.setItem('lastSearch',JSON.stringify(this.previousSearch));
  }

  downloadExcelFile() {
    let obj = {};
    obj["codiceADSList"] = this.idgeoPtADSList;
    if (!this.searchStateService.lastAdsSearchParams) return;
    this.searchService
      .downloadSearchResults2Excel(this.forestaleService.createQueryString(this.searchStateService.lastAdsSearchParams))
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

  getRowObject(tableObject: AreaDiSaggio) {
    this.selectedAds = tableObject;
  }

  downloadFile() {
    let tipo: string;
    let id = this.selectedAds.idgeoPtAds;
    if (this.selectedAds.tipoAds === Tipologia.RELASCOPICA_SEMPLICE) {
      tipo = "Semplice"
    } else if (this.selectedAds.tipoAds === Tipologia.RELASCOPICA_COMPLETA) {
      tipo = "Completa"
    } else if (this.selectedAds.tipoAds === Tipologia.SUPERFICIE_NOTA) {
      return this.searchService
        .downloadExcelFileTabs(id)
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
    this.searchService
      .downloadExcelFileDettaglio(id, tipo)
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


  downloadAdsWithoutDetails() {
    let tipo: string;
    return this.searchService
      .downloadSearchResults2ExcelWithoutDetails(this.forestaleService.createQueryString(this.searchStateService.lastAdsSearchParams))
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


  resetSearchForm() {
    let istatProvs: FormArray = <FormArray>this.searchForm.get("istatProvs");
    let formDates: FormArray = <FormArray>this.searchForm.get("formDates");
    let idAmbitos: FormArray = <FormArray>this.searchForm.get("idAmbitos");
    istatProvs.controls.forEach(control => control.get("idComune").disable());
    formDates.controls.forEach(control => control.get("toDate").disable);
    idAmbitos.controls.forEach(control => control.get("dettaglioAmbito").disable);
    this.fillSearchForm();
    this.container.nativeElement.scrollIntoView({
      behavior: "smooth",
      block: "start"
    });
  }

  fillDropdownMenus() {
    this.provinciaDropdown$ = this.forestaleService.getAllProvincia();
    this.categoriaDropdown$ = this.forestaleService.getAllCategoriaForestale();
    this.ambitoDiRilevamentoDropdown$ = this.forestaleService.getAmbitoRilievo(),
      // this.dettaglioDropdown$ = this.forestaleService.getAmbitoRilievo();
      this.tipologiaDropdown$ = this.searchService.getTipoAds();
    this.dataRilevamentoDropdown$ = this.forestaleService.getDropdownTest();
    this.statoSchedaDropdown$ = this.searchService.getStatoScheda();
  }

  getRowId(value: any) {
    this.rowId = value;
  }

  fillComune(i: number, that: any = this) {
    // let that = this;
    let istatProvs: FormArray = <FormArray>that.searchForm.get("istatProvs");

    if (istatProvs.controls[i].get("istatProv").value === null) {
      istatProvs.controls[i].get("idComune").disable();
      istatProvs.controls[i].get("idComune").patchValue("");
    } else {
      that.forestaleService
        .getComuniByProvincia(istatProvs.controls[i].get("istatProv").value)
        .pipe(takeUntil(that.unsubscribe$))
        .subscribe((response: SelectItem[]) => {
          that.comuneDropdowns[i] = response;
          istatProvs.controls[i].get("idComune").enable();
        });
    }

  }
  fillDettaglioAmbito(i: number, that: any = this) {
    // let that = this;
    let idAmbitos: FormArray = <FormArray>that.idAmbitos;

    if (idAmbitos.at(i).get("idAmbito").value === null) {
      idAmbitos.at(i).get("dettaglioAmbito").disable();
      idAmbitos.at(i).get("dettaglioAmbito").patchValue("");
    } else if(this) {
      this.forestaleService
        .getDettaglioAmbitoByIdAmbito(idAmbitos.controls[i].get("idAmbito").value)
        .pipe(takeUntil(that.unsubscribe$))
        .subscribe((response: SelectItem[]) => {

          that.dettaglioDropdowns[i] = response.map(item => {
            if(item.value.idDettaglioAmbito){
              item.value = item.value.idDettaglioAmbito;
            } 
            return item; 
          });
          idAmbitos.controls[i].get("dettaglioAmbito").enable();
        });
    }

  }

  visualizzaTable(ads: AreaDiSaggio) {  
    this.selectedAds = ads;  
    switch (ads.tipoAds) {
      case Tipologia.SUPERFICIE_NOTA:
          this.router.navigate(["/forestale", "tabs", ads.idgeoPtAds]);
          break;
      case  Tipologia.RELASCOPICA_SEMPLICE:
      case  Tipologia.RELASCOPICA_COMPLETA:
          this.router.navigate(["/forestale", "dettaglio", ads.idgeoPtAds]);
          break;
    }
  }

  modificaTable(ads: AreaDiSaggio) {
    this.selectedAds = ads;  
    this.router.navigate(["/forestale", "dati-generali", ads.idgeoPtAds]);
  }

  onDateChange(i: number): void {
    let formDates: FormArray = <FormArray>this.searchForm.get("formDates");
    formDates.controls[i].get("fromDate")
      .valueChanges.pipe(takeUntil(this.unsubscribe$))
      .subscribe({
        next: response => {
          if (!response) {
            formDates.controls[i].get("toDate").disable();
            formDates.controls[i].get("toDate").patchValue(null);
          } else {
            formDates.controls[i].get("toDate").enable();
          }
        }
      });
  }

  enableToDate(i: number) {
    let formDates: FormArray = <FormArray>this.searchForm.get("formDates");
    if (formDates.controls[i].value) {
      formDates.controls[i].get("toDate").enable();
    } else {
      formDates.controls[i].get("toDate").disable();
    }
  }

  getUpdatedTable(event) {
    this.paging = {
      page: event.page,
      limit: event.limit
    };
    if (event.field === 'descrTipoAds') {
      event.field = 'tipologia';
    } else if (event.field === '-descrTipoAds') {
      event.field = '-tipologia';
    }

    this.searchQuery = event.field;
    this.search(this.paging, event.field);
  }

  keepData(paging: any, sortField?: string) {
    this.searchQuery = sortField ? sortField : '';
    this.searchObject = this.searchForm.value;
    if (Object.keys(this.searchObject).every(item => this.searchObject[item] === "" || this.searchObject[item] === null)) {
      this.dialogService.showDialog(true, 'Tutti i campi sono vuoti', 'Avvertimento', DialogButtons.OK, '', (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
        }
      }, DialogIconType.INFORMATION);
    } else {
      this.forestaleService.getSearchedData(paging.page, paging.limit, this.searchObject, sortField)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(
          (response: Content<AreaDiSaggio[]>) => {
            this.tableData = response.content ? response : { content: [] }
            this.showNoData = true;
          }
        );
      this.forestaleService
        .getSearchedData(paging.page, paging.limit, this.searchObject, sortField)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(res => {
          this.idgeoPtADSList = res.content ? res.content.map(item => item.idgeoPtAds) : [];
        });
    }
  }

  navigateToInsert() {
    this.router.navigate(["/forestale", "dati-generali"]);
  }

  fillSearchForm() {
    this.searchForm = this.fb.group({
      istatProvs: this.fb.array([this.fillSearchIstatProv()]),
      idCategoriaForestales: this.fb.array([this.fillIdCategoriaForestale()]),
      idAmbitos: this.fb.array([this.fillIdAmbitos()]),
      tipologias: this.fb.array([this.fillTipologias()]),
      statoSchedas: this.fb.array([this.fillStatoSchedas()]),
      formDates: this.fb.array([this.fillFormDates()])
    });
  }

  get istatProvs(): FormArray {
    return <FormArray>this.searchForm.get("istatProvs");
  }
  get idCategoriaForestales(): FormArray {
    return <FormArray>this.searchForm.get('idCategoriaForestales')
  }
  get idAmbitos(): FormArray {
    return <FormArray>this.searchForm.get('idAmbitos')
  }

  // get dettaglioAmbitos():FormArray{
  //   return <FormArray> this.searchForm.get('dettaglioAmbitos')
  // }

  get tipologias(): FormArray {
    return <FormArray>this.searchForm.get('tipologias')
  }

  get statoSchedas(): FormArray {
    return <FormArray>this.searchForm.get('statoSchedas')
  }

  get formDates(): FormArray {
    return <FormArray>this.searchForm.get('formDates')
  }

  fillSearchIstatProv(obj: any = {}, that = this): FormGroup {
    return that.fb.group({
      istatProv: [obj.istatProv ? obj.istatProv : null],
      idComune: [{ value: obj.idComune, disabled: !obj.istatProv }]
    })
  }

  fillIdCategoriaForestale(obj: any = {}, that: any = this): FormGroup {
    return that.fb.group({
      idCategoriaForestale: obj.idCategoriaForestale
    })
  }
  fillIdAmbitos(obj: any = {}, that: any = this): FormGroup {
    return that.fb.group({
      idAmbito: [obj.idAmbito],
      dettaglioAmbito: [{ value: obj.dettaglioAmbito, disabled: !obj.dettaglioAmbito }]
    })
  }
  // fillDettaglioAmbitos():FormGroup{
  //   return this.fb.group({
  //     dettaglioAmbito:""
  //   })
  // }
  fillTipologias(obj: any = {}, that: any = this): FormGroup {
    return that.fb.group({
      tipologia: obj.tipologia
    })
  }
  fillStatoSchedas(obj: any = {}, that: any = this): FormGroup {
    return that.fb.group({
      statoScheda: [obj.statoScheda]
    })
  }
  fillFormDates(obj: any= {}, that: any = this): FormGroup {
    return that.fb.group({
      fromDate: [obj.fromDate? obj.fromDate: null],
      toDate: [{ value: obj.toDate? obj.toDate: null, disabled: !obj.fromDate }]
    })
  }
  addIstatProvs(): void {
    this.istatProvs.push(this.fillSearchIstatProv())
  }
  addIdCategoriaForestales(): void {
    this.idCategoriaForestales.push(this.fillIdCategoriaForestale());
  }
  addIdAmbito(): void {
    this.idAmbitos.push(this.fillIdAmbitos());
  }
  // addDettaglioAmbito():void{
  //   this.dettaglioAmbitos.push(this.fillDettaglioAmbitos())
  // }
  addTipologia(): void {
    this.tipologias.push(this.fillTipologias())
  }
  addStatoScheda(): void {
    this.statoSchedas.push(this.fillStatoSchedas())
  }
  addFormDates(): void {
    this.formDates.push(this.fillFormDates());
    this.onDateChange(this.formDates.controls.length);

  }
  removeIstatProvs(i: number): void {
    if (this.istatProvs.length > 1)
      this.istatProvs.removeAt(i);
  }
  removeIdCategoriaForestales(i: number): void {
    if (this.idCategoriaForestales.length > 1)
      this.idCategoriaForestales.removeAt(i);
  }
  removeIdAmbito(i: number): void {
    if (this.idAmbitos.length > 1)
      this.idAmbitos.removeAt(i);
  }
  // removeDettaglioAmbito(i:number):void{
  //   if(this.dettaglioAmbitos.length>1)
  //   this.dettaglioAmbitos.removeAt(i);
  // }
  removeTipologia(i: number): void {
    if (this.tipologias.length > 1)
      this.tipologias.removeAt(i);
  }
  removeStatoScheda(i: number): void {
    if (this.statoSchedas.length > 1)
      this.statoSchedas.removeAt(i);
  }
  removeFormDates(i: number): void {
    if (this.formDates.length > 1)
      this.formDates.removeAt(i);
  }

  tooltipProperty = {
    tipologia: "Seleziona tipologia",
    rilevamento: "Seleziona rilevamento",
    dettaglioAmbito: "Seleziona dettaglio ambito",
    fromDate: "Seleziona data",
    provincia: "Seleziona provincia",
    comune: "Seleziona comune",
    categoriaForestale: "Seleziona il categoria forestale",
    statoScheda: "Seleziona stato scheda",
    toDate: "Seleziona data",
    add: "aggiungi un altro campo",
    remove: "rimuovi questo campo"
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  showDialog(ads: AreaDiSaggio){
    this.selectedAds = ads;
    this.showDeleteDialog=true;
  }
  closeDialog(){
    this.showDeleteDialog=false;
  }

  delete() {      
    this.showDeleteDialog=false;
    this.generaliService.deleteAreaDiSaggio(this.selectedAds.idgeoPtAds)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: any) => { 
          if (response.codice && response.codice == ErrorCodes.SUCCESS) {
            this.search(this.paging, this.searchQuery);
          }
         }         
      );
  }
  
  openGeeco(){
    let ids:string[] = [];
    let content = this.tableData.content;
    for(let i in content){
      ids.push(''+content[i]['idgeoPtAds']);
    }
    this.generaliService.getCartograficoPointsUrl(3,ids)
    .subscribe(
      (response: any) => {
          window.open(response['geecourl'], "_blank");
      }
    );
    return false;
  }

}
