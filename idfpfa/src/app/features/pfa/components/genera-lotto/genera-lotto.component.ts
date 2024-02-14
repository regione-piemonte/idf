/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  Output,
  EventEmitter,
  Input,
  OnDestroy,
} from "@angular/core";
import { FormGroup, FormBuilder, Validators, FormArray } from "@angular/forms";
import { TableHeader } from "src/app/shared/models/table-header";
import { PfaConfigComponent } from "../../pfa-config";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { ActivatedRoute, Router, ParamMap } from "@angular/router";
import {
  ShowParcel,
  ParticleCadastral,
} from "src/app/shared/models/particle-cadastral";
import { Subject } from "rxjs";
import { SecondForm } from "src/app/shared/models/secondForm";
import { ParticelleCatastali } from "src/app/shared/models/comune.model";
import { SearchForParcelsComponent } from "./search-for-parcels/search-for-parcels.component";
import { takeUntil } from "rxjs/operators";
import { DialogService } from "src/app/shared/services/dialog.service";
import { TagliService } from "../../services/tagli.service";
import { FasciaAltimetrica } from "src/app/shared/models/fascia-altimetrica.model";
import { RicadenzaIntervento } from "src/app/shared/models/ricadenzaIntervento.model";

@Component({
  selector: "app-genera-lotto",
  templateUrl: "./genera-lotto.component.html",
  styleUrls: ["./genera-lotto.component.css"],
})
export class GeneraLottoComponent implements OnInit, OnDestroy {
  sub = "Ricerca particelle catastali";
  sub1 = "Elenco PARTICELLE CATASTALI SU CUI RICADE L’INTERVENTO";
  sub2 = "ELENCO PARTICELLE CATASTALI SU CUI RICADE L’INTERVENTO/EVENTO";
  catastaliHeader: TableHeader[];
  ricadeHeader: TableHeader[];

  unsubscribe$ = new Subject<void>();
  isUpdate: boolean = false;
  catastaliData: any[];
  ricadeData: any[];
  ricercaForm: FormGroup;
  isAcquisisci = false;
  selectedRow: number;
  selectedRow2: number;
  isTableEmpty: boolean;
  parcelTable: ShowParcel[] = [];
  ricadenzeForm: FormGroup;
  annotazioni = "";
  idGeoPlPfa: any;
  secondFormData: any; //SecondForm;
  transferSecondFormData: any;
  particelleCatastaliData: any = {
    particelleCatastali: [],
    ricadenzaVincoloIdrogeologico: false,
  };
  partCatastaliDataToSend: any = {
    particelleCatastali: [],
    ricadenzaVincoloIdrogeologico: false,
  };
  tableData: RicadenzaIntervento[] = [];
  fasceAltimetriche: FasciaAltimetrica[] = [];

  invalid = true;
  activeIndex: number;
  @Input() pfaPlanId: number;
  @Input() idIntervento: number;
  @Input() idInterventoForEdit: number;
  @Input() editMode: number;
  @Input() viewMode: boolean;
  @Output() emitNextStep = new EventEmitter();
  @Output() emitIndex: EventEmitter<any> = new EventEmitter<any>();
  @Output() emitIdIntervento: EventEmitter<any> = new EventEmitter<any>();
  @Output() emitBack: EventEmitter<void> = new EventEmitter();
  @Output() emitRicadenze: EventEmitter<void> = new EventEmitter();
  disableAcquisisci: boolean = false;
  istatProve: number;
  queryObject: Object;
  polje = [
    { name: "Ricadenza in Aree Protette", token: "100" },
    { name: "Ricadenza in Rete Natura 2000", token: "100" },
    { name: "Ricadenza in popolamenti da seme", token: "100" },
    { name: "Ricadenza in categorie forestali del PFA", token: "100" },
  ];

  particella: ParticelleCatastali = { particelleCatastali: [] };
  formDisabled: boolean;
  drawedGeometryInfo: any;
  constructor(
    private fb: FormBuilder,
    private pfaService: PfaSampleService,
    private dialogService: DialogService,
    public router: Router,
    public activatedRoute: ActivatedRoute,
    private tagliService: TagliService
  ) {}

  ngOnInit() {
    console.log(this.viewMode);
    
    this.tagliService.getFasceAltimetriche().subscribe((res) => {
      this.fasceAltimetriche = res;
    });
    /* this.pfaService.getSecondTableData(this.idIntervento).subscribe((response: any) => { //  validar-------------------

      this.disableAcquisisci = true;
      this.invalid = false;
      console.log("getSecondTableData : ", response);
      this.transferSecondFormData = {
        totaleSuperficieCatastale: response.totaleSuperficieCatastale
          ? JSON.parse(JSON.stringify(response.totaleSuperficieCatastale))
          : "",
        totaleSuperficieTrasf: response.totaleSuperficieTrasf
          ? JSON.parse(JSON.stringify(response.totaleSuperficieTrasf))
          : "",
        ricadenzaVincoloIdrogeologico: response.ricadenzaVincoloIdrogeologico
          ? JSON.parse(JSON.stringify(response.ricadenzaVincoloIdrogeologico))
          : "",
      };
      response.annotazioni = this.annotazioni;
      this.tableData = response.ricadenzaIntervento;
  }) */
    
  
     this.tagliService.getStepNumber(this.editMode).subscribe((res) => {
        this.isUpdate = (res.nextStep > 2);
      },
      (e)=> console.log(e),
      ()=>{
        console.log(this.isUpdate || (window.location.href.indexOf("return-modifica-tagli") > -1));
        console.log(this.isUpdate);
        if(this.isUpdate || (window.location.href.indexOf("return-modifica-tagli") > -1 || window.location.href.indexOf("dettaglio") > -1)){
          this.activatedRoute.params.subscribe((res) => {          
            if (this.isUpdate || (window.location.href.indexOf("return-modifica-tagli") > -1) || window.location.href.indexOf("dettaglio") > -1) {
              this.idInterventoForEdit = this.editMode
              this.pfaService
                .ricalcolaParticelleFromGeeco(this.editMode)
                .subscribe((res) => {
                  res.forEach((data) => {
                    console.log(".data en ricalcolaParticelleFromGeeco...", data);
                    this.emitedShowParcels(data);
                  });
                  this.acquisiciParticelle();
                  this.pfaService
                    .getInterventiDrawedGeometryInfo(this.editMode)
                    .subscribe((response) => (this.drawedGeometryInfo = response));
                });
            } else {
              this.loadData();
              this.pfaService
                .getInterventiDrawedGeometryInfo(this.editMode)
                .subscribe((response) => (this.drawedGeometryInfo = response));
            }
          });
  
        }

      }
      );
      
      
    
  }

  loadData() {
    this.pfaService
      .getLocalizzaLottoForEdit(this.editMode)
      .subscribe((res) => {
        res.forEach((data) => {
          this.emitedShowParcels(data);
        });
      });

    this.acquisiciParticelle();
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  emitIdInterventoForEdit(event: number) {
    this.idInterventoForEdit = event;
    //this.emitIdIntervento.emit(event);
  }

  emitedShowParcels(event: ParticleCadastral) {
    this.disableAcquisisci = false;
    this.invalid = true;
    this.parcelTable.push({
      denominazioneComune: event.comune.denominazioneComune,
      sezione: event.sezione,
      foglio: event.foglio,
      particella: event.particella,
      supCatastale: event.supCartograficaHa,
      supIntervento: event.supIntervento
        ? event.supIntervento
        : event.supCartograficaHa,
      idGeoPlPropCatasto: event.idGeoPlPropCatasto,
      comune: event.comune,
    });

    this.particelleCatastaliData["particelleCatastali"].push({
      id: event.id,
      comune: event.comune,
      sezione: event.sezione,
      foglio: event.foglio,
      particella: event.particella,
      supCatastale: event.supCartograficaHa,
      toDelete: false,
      supIntervento: event.supIntervento,
    });
  }

  goElenco() {
    if (this.isAcquisisci) {
      this.isAcquisisci = false;
    } else {
      this.isAcquisisci = true;
    }
  }

  onTabClose(event) {
    if (this.isAcquisisci) {
      this.isAcquisisci = false;
    }
  }

  getFieldId(index: number) {
    this.selectedRow = index;
  }
  getFieldId2(index: number) {
    this.selectedRow2 = index;
  }

  goToDettaglio() {
    this.emitBack.emit();
  }

  acquisiciParticelle() {
    delete this.particelleCatastaliData.totaleSuperficieCatastale;
    delete this.particelleCatastaliData.totaleSuperficieTrasf;
    delete this.particelleCatastaliData.ricadenzaVincoloIdrogeologico;
    delete this.particelleCatastaliData.ricadenzaAreeProtette;
    delete this.particelleCatastaliData.ricadenzaNatura2000;
    delete this.particelleCatastaliData.ricadenzaPopolamentiDaSeme;
    delete this.particelleCatastaliData.ricadenzaCategorieForestali;
    this.calculateFieldsOfForm();
  }

  calculateFieldsOfForm() {
    const localPericelle = { particelleCatastali: [] };
    this.partCatastaliDataToSend.particelleCatastali.forEach((item) => {
      if (item.toDelete === false) {
        localPericelle["particelleCatastali"].push(item);
      }
    });
    this.pfaService.getSecondTableData(this.editMode).subscribe((response: any) => { //  validar-------------------

      this.disableAcquisisci = true;
      this.invalid = false;
      console.log("getSecondTableData : ", response);
      this.transferSecondFormData = {
        totaleSuperficieCatastale: response.totaleSuperficieCatastale
          ? JSON.parse(JSON.stringify(response.totaleSuperficieCatastale))
          : "",
        totaleSuperficieTrasf: response.totaleSuperficieTrasf
          ? JSON.parse(JSON.stringify(response.totaleSuperficieTrasf))
          : "",
        ricadenzaVincoloIdrogeologico: response.ricadenzaVincoloIdrogeologico
          ? JSON.parse(JSON.stringify(response.ricadenzaVincoloIdrogeologico))
          : "",
      };
      response.annotazioni = this.annotazioni;
      this.tableData = response.ricadenzaIntervento;
      this.createRicadenzeForm(response);
      //this.emitRicadenze.emit(response);
    });
  }

  deleteRowGetter(event) {
    if (this.idInterventoForEdit != undefined) {
      this.pfaService
        .deleteParticelleCadastral(
          this.idInterventoForEdit,
          this.parcelTable[event.index].idGeoPlPropCatasto
        )
        .subscribe((res) => {
          this.parcelTable.splice(event.index, 1);
          this.invalid = true;
          this.disableAcquisisci = false;
        });
    }
  }

  changeValueAnnotazioni(event) {
    this.annotazioni = event.target.value;
  }

  createRicadenzeForm(polygonData: any) {
    this.ricadenzeForm = this.fb.group({
      particelleCatastali: [
        {
          value: polygonData ? polygonData.totaleSuperficieCatastale : "",
          disabled: true,
        },
        Validators.required,
      ],
      totaleSuperficieTransf: [
        {
          value: polygonData ? polygonData.totaleSuperficieTrasf : "",
          disabled: true,
        },
        Validators.required,
      ],
      ricadenzaAreeProtette: this.fb.array([]),
      ricadenzaNatura2000: this.fb.array([]),
      ricadenzaPopolamentiDaSeme: this.fb.array([]),
      ricadenzaPortaSeme: this.fb.array([]),
      ricadenzaCategorieForestali: this.fb.array([]),
      // tslint:disable-next-line: max-line-length
      ricadenzaVincoloIdrogeologico: [
        {
          value: polygonData ? polygonData.ricadenzaVincoloIdrogeologico : "",
          disabled: true,
        },
        Validators.required,
      ],
      annotazioni: [polygonData ? polygonData.annotazioni : this.annotazioni],
      fasciaAltimetrica: [
       {
         value: polygonData.fkFasciaAltimetrica != null ? this.setFasciaAltimetrica(polygonData.fkFasciaAltimetrica) : null,  
         disabled: false,      
       },
        Validators.required
      ],
      localita: [{
        value: polygonData.localita != null ? polygonData.localita : null,
        disabled: false,
      },
       Validators.required
      ]
    });

    this.pushAreaProtette(polygonData.ricadenzaAreeProtette);
    let localNatura;
    if (polygonData.ricadenzaNatura2000) {
      localNatura = JSON.parse(JSON.stringify(polygonData.ricadenzaNatura2000));
    }
    if (localNatura) {
      this.pushRicadenzaNatura2000(localNatura);
    }
    if (polygonData.ricadenzaPopolamentiDaSeme) {
      this.pushPopolamentiDaSeme(polygonData.ricadenzaPopolamentiDaSeme);
    }
    if (polygonData.ricadenzaPortaSeme)
      this.pushPortaSeme(polygonData.ricadenzaPortaSeme);

    if (polygonData.ricadenzaCategorieForestali) {
      this.pushCategorieForestali(polygonData.ricadenzaCategorieForestali);
    }
  }

  setFasciaAltimetrica(id:number){
    return this.fasceAltimetriche.filter(fascia=> id == fascia.idFasciaAltimetrica)[0];
  }

  buildParticelle(event?) {
    return this.fb.group({
      comune: [event ? event.comune : ""],
      denomazione: [event ? event.comune.denominazioneComune : ""],
      istatComune: [event ? +event.comune.istatComune : ""],
      sezione: [event ? event.sezione : ""],
      foglio: [event ? event.foglio : ""],
      particella: [event ? event.particella : ""],
      supCatastale: [event ? event.supCatastale : ""],
    });
  }

  pushAreaProtette(ricadenzaAreeProtette: any[]) {
    if (ricadenzaAreeProtette) {
      ricadenzaAreeProtette = this.filterDuplicates(
        ricadenzaAreeProtette,
        "codiceAmministrativo"
      );
      ricadenzaAreeProtette.forEach((item) => {
        (this.ricadenzeForm.get("ricadenzaAreeProtette") as FormArray).push(
          this.areeProtette(item)
        );
      });
    }
  }

  filterDuplicates(array, key) {
    return (array = array.filter(
      (item, index, self) =>
        index === self.findIndex((t) => t[key] === item[key])
    ));
  }

  areeProtette(item) {
    return this.fb.group({
      codiceAmministrativo: [item ? item.codiceAmministrativo : ""],
      nome: [item ? item.nome : ""],
      percentualeRicadenza: [item ? item.percentualeRicadenza : ""],
    });
  }

  pushRicadenzaNatura2000(ricadenzaNatura2000: any) {
    if (ricadenzaNatura2000) {
      // FILTER DUPLICATES
      ricadenzaNatura2000 = this.filterDuplicates(
        ricadenzaNatura2000,
        "codiceAmministrativo"
      );
      ricadenzaNatura2000.forEach((item) => {
        (this.ricadenzeForm.get("ricadenzaNatura2000") as FormArray).push(
          this.areeProtette(item)
        );
      });
    }
  }

  pushPopolamentiDaSeme(ricadenzaPopolamentiDaSeme: any) {
    if (ricadenzaPopolamentiDaSeme) {
      ricadenzaPopolamentiDaSeme = this.filterDuplicates(
        ricadenzaPopolamentiDaSeme,
        "codiceAmministrativo"
      );
      ricadenzaPopolamentiDaSeme.forEach((item) => {
        (
          this.ricadenzeForm.get("ricadenzaPopolamentiDaSeme") as FormArray
        ).push(this.areeProtette(item));
      });
    }
  }

  pushPortaSeme(ricadenzaPortaSeme: any) {
    if (ricadenzaPortaSeme) {
      ricadenzaPortaSeme = this.filterDuplicates(
        ricadenzaPortaSeme,
        "localita"
      );
      ricadenzaPortaSeme.forEach((item) => {
        (this.ricadenzeForm.get("ricadenzaPortaSeme") as FormArray).push(
          this.areePortaseme(item) // aqui mismo ese
        );
      });
    }
  }

  areePortaseme(item) {
    return this.fb.group({
      id: [item ? item.id : ""],
      localita: [item ? item.localita : ""],
      specie: [item ? item.specie : ""],
    });
  }

  pushCategorieForestali(ricadenzaCategorieForestali: any) {
    if (ricadenzaCategorieForestali) {
      ricadenzaCategorieForestali = this.filterDuplicates(
        ricadenzaCategorieForestali,
        "codiceAmministrativo"
      );
      ricadenzaCategorieForestali.forEach((item) => {
        (
          this.ricadenzeForm.get("ricadenzaCategorieForestali") as FormArray
        ).push(this.areeProtette(item));
      });
    }
  }

  fillParticelleCatastaliToSend() {
    let particelle = JSON.parse(JSON.stringify(this.parcelTable));
    for (let i = 0; i < particelle.length; i++) {
      this.partCatastaliDataToSend["particelleCatastali"].push({
        comune: particelle[i].comune,
        id: particelle[i].idGeoPlPropCatasto,
        sezione: particelle[i].sezione,
        foglio: particelle[i].foglio,
        particella: particelle[i].particella,
        supCatastale: particelle[i].supCatastale,
        toDelete: false,
        supIntervento: particelle[i].supIntervento,
      });
    }
  }

  continue(){
    this.save(true);
  }

  save(nextStep: boolean) {
    this.fillParticelleCatastaliToSend();
    const ricadenzeFormData = {
      particelleCatastali: this.partCatastaliDataToSend.particelleCatastali,

      ricadenzaAreeProtette:this.ricadenzeForm.get("ricadenzaAreeProtette").value,
      ricadenzaNatura2000:this.ricadenzeForm.get("ricadenzaNatura2000").value,

      // ricadenzaAreeProtette: [],
      // ricadenzaNatura2000: [],
      ricadenzaPopolamentiDaSeme:this.ricadenzeForm.get( "ricadenzaPopolamentiDaSeme").value,
      ricadenzaPortaSeme : this.ricadenzeForm.get( "ricadenzaPortaSeme").value,
      ricadenzaIntervento : this.tableData ,
      // ricadenzaPopolamentiDaSeme: [],
      /*

      ricadenzaVincoloIdrogeologico: this.ricadenzeForm.get(
        "ricadenzaVincoloIdrogeologico"
      ).value,
      */
      ricadenzaCategorieForestali: this.ricadenzeForm.get(
        "ricadenzaCategorieForestali"
      ).value,
      // ricadenzaVincoloIdrogeologico: [],
      totaleSuperficieCatastale:
        this.transferSecondFormData.totaleSuperficieCatastale,
      totaleSuperficieTrasf: this.transferSecondFormData.totaleSuperficieTrasf,
     //annotazioni: localStorage.getItem("annotazioni"),
      fasciaAltimetrica: this.ricadenzeForm.get("fasciaAltimetrica").value,
      localita: this.ricadenzeForm.get("localita").value
    };
    //localStorage.getItem("annotazioni");
    const localPericelle = [];
    
    ricadenzeFormData.particelleCatastali.forEach((item) => {
      if (
        "id" in item ||
        (!item.hasOwnProperty("id") && item.toDelete === false)
      ) {
        localPericelle.push(item);
      }
    });
    ricadenzeFormData.particelleCatastali = localPericelle;
    
       /* if (ricadenzeFormData.particelleCatastali.length > 0) {
      console.log("data update ",this.idInterventoForEdit) */
    if (this.idInterventoForEdit != undefined) {
      console.log("save",this.emitIndex.emit())
      this.pfaService
        .localizzaLottoUpdate(
          ricadenzeFormData,
          this.idInterventoForEdit,
          this.pfaPlanId
        )
        .subscribe((res) => {
          console.log("save",this.emitIndex.emit())
         // this.emitIndex.emit();
         if(nextStep){
           this.emitNextStep.emit()
         }
        });
        //.subscribe((res) =>);
    } else {
      this.pfaService
        .localizzaLottoSave(
          ricadenzeFormData,
          this.editMode,
          this.pfaPlanId
        )
        .subscribe((res) => {
          //this.emitIndex.emit();
          if(nextStep){
            this.emitNextStep.emit()
          }
        });
    }
    /*  } */
  }

  createInterventoAndOpenGeeco() {
    /*if (!this.idIntervento && !this.idInterventoForEdit) {
      let tabIndex = parseInt(localStorage.getItem("tabIndex"), 10);
      console.log("createInterventoAndOpenGeeco", tabIndex);
      this.pfaService.creaIntervento(tabIndex).subscribe((res) => {
        if (res["ERROR"]) {
          this.dialogService.showDialogMsg("Errore", res["ERROR"], true);
        } else {
          this.idIntervento = res.istanzaId;

          this.openMappa();
        }
      });
    } else {
      this.openMappa();
    }*/
    this.openMappa();
  }

  openMappa() {
    //window.scrollTo(0, 0);
    //if (this.idIntervento === null || this.idIntervento === undefined) {
    this.activatedRoute.params.subscribe((params) => {
      const tabIndex = parseInt(params["id"], 10); // Convertir a número entero
      console.log(tabIndex);

      // Asignar el valor de tabIndex a this.idIntervento si está vacío
      if (!this.idIntervento) {
        this.idIntervento = tabIndex;
        console.log(this.idIntervento); // Muestra el valor asignado a this.idIntervento
      }
    });
    //}
    console.log(this.idIntervento);
    this.pfaService
      .getCartograficoByIdUrl(8, "" + (this.editMode || this.idIntervento))
      .subscribe((response: any) => {
        console.log("gecooUrl: ", response["geecourl"]);
        window.location.href = response["geecourl"];
      });
    return false;
  }

  emitedParcelsLoad(event: ShowParcel[]) {
    this.parcelTable = event;
  }
}
