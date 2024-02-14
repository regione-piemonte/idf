/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  Output,
  Input,
  OnDestroy,
  EventEmitter,
} from "@angular/core";
import { MenuItem } from "primeng/components/common/menuitem";
import { Dettaglio, Detail } from "src/app/shared/models/dettaglio";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { timeout, retryWhen, catchError, takeUntil } from "rxjs/operators";
import { selectiveRetryStrategy } from "src/app/service.utils";
import { ActivatedRoute, Router } from "@angular/router";
import { RouteParams } from "src/app/shared/models/routeParams";
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormArray,
  Form,
  FormControl,
} from "@angular/forms";
import { Subject, Observable } from "rxjs";
import { EventoCorrelato } from "src/app/shared/models/progressiviNomi";
import { ForestaliService } from '../../../pfa/services/forestali.service';
import { DestLegnami } from "src/app/shared/models/destLegnami";
import { FinalitaTaglie } from "src/app/shared/models/finalTaglie";
import { TipoInterventi } from "src/app/shared/models/tipoInterventi";
import { Governi } from "src/app/shared/models/governi";
import { Esbosco } from "src/app/shared/models/esbosco";
import { UsoViabilita } from "src/app/shared/models/viabilita";
import { FasciaAltimetrica } from "src/app/shared/models/fascia-altimetrica";
import { Specie } from "src/app/shared/models/specie";
import { DatiTehnici } from "src/app/shared/models/dati-tehnici";
import { StatoIntervento } from "src/app/shared/models/stato-intervento";
import { TipoAllegato } from "src/app/shared/models/tipo-allegato";
import { SoggettoModel } from "src/app/shared/models/soggetto-model";
import { ComuneModel } from "src/app/shared/models/comune.model";
import { ConverstionVolume } from "src/app/shared/models/util-conversion";
import { ShootingMirrorModel } from "src/app/shared/models/shooting-mirror";
import { TableHeader } from "src/app/shared/models/table-header";
import { PfaConfigComponent } from "../../pfa-config";
import { FormUtil } from "src/app/utils/form-util";
import {
  Step,
  StepErrors,
} from "src/app/shared/components/steps-with-errors/steps-with-errors.component";
import { DialogService } from "src/app/shared/services/dialog.service";
import { ButtonType, DialogButtons } from "src/app/shared/models/dialog.model";

@Component({
  selector: "app-step",
  templateUrl: "./step.component.html",
  styleUrls: ["./step.component.css"],
})
export class StepComponent implements OnInit {
  items: MenuItem[];
  activeIndex = 1;
  lastCompletedStep;
  denom: Detail;
  queryParametrs: Object;
  pfaPlanId: number;
  routerParams: RouteParams;
  unsubscribe$ = new Subject<void>();
  interventionPlan: string;
  errorsOnAllegati: boolean;
  editModeNu: number;
  tipoInterventoForm: FormGroup;
  specieForm: FormGroup;
  specieForm1: FormGroup;
  dataRowArray: FormArray;
  plantsForm: FormGroup;

  datiTehnici: DatiTehnici;
  datiTehniciEdit: DatiTehnici;
  idInterv: number;
  intervSelvicolturale: any;
  tipoIntervento: any;
  name: any;
  ricadenzeObj: any;
  shootingMirrorData: ShootingMirrorModel[];
  shootingMirrorVisible = false;
  tableHeaders: TableHeader[];
  isIstanzaInviata: boolean = false;
  idInterventoForEdit: number;
  idIntervento: number;
  statoIntervento: StatoIntervento;
  editMode: boolean;
  conformeDerogaValue: string;
  specieArray: FormArray;
  @Input() secondFormData: any;
  @Output() emitQ3ToTon: EventEmitter<void> = new EventEmitter();
  //dropdowns 2. step
  governi$: Observable<Governi>;
  tipoInterventi$: Observable<TipoInterventi>;
  finalitaTaglie$: Observable<FinalitaTaglie>;
  destLegnami$: Observable<DestLegnami>;
  secondFormData$: Observable<any>;
  eventCorrelato$: Observable<EventoCorrelato>;
  esbosco$: Observable<Esbosco>;
  usoViabilita$: Observable<UsoViabilita>;
  fasciaAltimetrica$: Observable<FasciaAltimetrica>;
  specie$: Observable<Specie[]>;
  // priorita$: Observable<Priorita>;
  datiTehnici$: Observable<DatiTehnici>;
  statoIntervento$: Observable<StatoIntervento>;
  tipoAllegato: TipoAllegato[];
  tipoAllegatoAll: TipoAllegato[];
  allTecnicoForestale$: Observable<SoggettoModel>;
  allComuni$: Observable<ComuneModel[]>;
  dataRow: any;
  index: any;
  shootingMirrorForm: FormGroup;
  summaryColumnData: any;
  listSpecie: Specie[];
  steps: Step[];

  constructor(
    private pfaService: PfaSampleService,
    private forestaliService: ForestaliService,
    private activatedRoute: ActivatedRoute,
    private routerService: Router,
    private fb: FormBuilder,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    this.pfaService.getAllSpecie().subscribe((response) => {
      this.listSpecie = response;
    });
    this.steps = [
      new Step(0, "Dati Generali"),
      new Step(1, "Richiedente e Utilizzatore"),
      new Step(2, "Localizza intervento"),
      new Step(3, "Descrizione intervento"),
      new Step(4, "Allegati"),
      /*
      new Step(1, "Localizza intervento"),
      new Step(2, "Dati tecnici"),
      new Step(3, "Utilizzatore e tecnico"),
      new Step(4, "Allegati"),
      new Step(5, "Istanza di taglio"),*/
    ];

    this.items = [
      { label: "Dati Generali" },
      { label: "Richiedente e Utilizzatore" },
      { label: "Localizza intervento" },
      { label: "Descrizione intervento" },
      { label: "Allegati" },
      /*
      { label: "Istanza di taglio" },*/
      /*
      { label: "Localizza intervento" },
      { label: "Dati tecnici" },
      { label: "Utilizzatore e tecnico" },
      { label: "Allegati" },
      { label: "Istanza di taglio" },*/
    ];
    this.activatedRoute.params.subscribe((res) => {
      this.pfaPlanId = res.id;
      this.idInterventoForEdit = res.idIntervento;
      this.editMode = res.editmode;
      this.editModeNu = res.idIntervento;
      if (this.idInterventoForEdit != undefined) {
        this.loadDatiTecnici(this.idInterventoForEdit);
      } else {
        this.buildTecniciForm(this.datiTehnici);
      }

      this.activatedRoute.queryParams.subscribe((params) => {
        this.queryParametrs = {
          idComune: params.idComune,
          idPopolamento: params.idPopolamento,
        };
      });
    });
    if (this.idIntervento || this.idInterventoForEdit) {
      this.checkForErrors();
    } else {
      this.createStepper();
    }

    // this.buildTecniciForm(this.datiTehnici);

    setTimeout(() => {
      this.governi$ = this.pfaService.getGoverni();
      let datiTecnici = this.datiTehnici
        ? this.datiTehnici
        : this.datiTehniciEdit;
      this.finalitaTaglie$ = this.pfaService.getFinalitaTaglie();
      this.destLegnami$ = this.pfaService.getDestLegnami();
      this.eventCorrelato$ = this.pfaService.getEventoCorrelato(this.pfaPlanId);
      this.esbosco$ = this.pfaService.getAllTipoEsbosco();
      this.usoViabilita$ = this.pfaService.getAllUsoViabilita();
      this.fasciaAltimetrica$ = this.pfaService.getAllFasciaAltimetrica();
      this.specie$ = this.pfaService.getAllSpecie();
      // this.priorita$ = this.pfaService.getAllPriorita();
      this.statoIntervento$ = this.pfaService.getAllStatoIntervento();
      this.allTecnicoForestale$ = this.pfaService.getAllTecnicoForestale();
      this.allComuni$ = this.pfaService.getAllComuni();
    }, 300);
  }

  setIdIstanze(event) {
    if (!this.editMode) {
      this.editMode = event;
    }
  }
  checkForErrors() {
    let idIntervento =
      this.idIntervento != null ? this.idIntervento : this.idInterventoForEdit;
    this.pfaService.getStepNumber(idIntervento).subscribe((res) => {
      this.activeIndex = res["nextStep"];
      this.createStepper();
      this.pfaService.getErroriIntervento(idIntervento).subscribe((res) => {
        this.updateErrors(res);
      });
    });
  }

  loadPraticaInviata() {
    /* this.forestaliService
      .getPraticaInviata(this.editMode)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        this.istanzaInviata = response;
        this.istanzaInviata.annoInserimento = new Date(
          this.istanzaInviata.dataInserimento
        ).getFullYear();
        this.istanzaInviata.dataInserimento = this.getFormatDateIT(
          this.istanzaInviata.dataInserimento
        );
        this.istanzaInviata.dataProtocollo = this.getFormatDateIT(
          this.istanzaInviata.dataProtocollo
        );
        this.istanzaInviata.dataInvioFormated = this.getFormatDateIT(
          this.istanzaInviata.dataInvio
        );
        this.istanzaInviata.dataVerifica = this.getFormatDateIT(
          this.istanzaInviata.dataVerifica
        );
        this.istanzaInviata.dataAggiornamento = this.getFormatDateIT(
          this.istanzaInviata.dataAggiornamento
        );
        this.isIstanzaInviata =
          this.istanzaInviata.dataInvio &&
          this.istanzaInviata.dataInvio.length > 0;
        //this.boOperatingEnabled = this.boModificaOn && this.user.ruolo != 1
        this.boOperatingEnabled =
          this.boModificaOn ||
          this.user.ruolo == CONST.ROLE_CITADINO ||
          this.user.ruolo == CONST.ROLE_SPORTELLO;
        // && !(this.user.ruolo == CONST.ROLE_UF_TERRIRORIALE && this.istanzaInviata.fkTipoIstanza == 5)
        // && !(this.user.ruolo == CONST.ROLE_COMUNE && this.istanzaInviata.fkTipoIstanza == 4)
      });*/
    this.isIstanzaInviata = false;
  }

  loadDatiTecnici(idIntervento) {
    if (!this.idInterventoForEdit) {
      this.idInterventoForEdit = idIntervento;
    }
    this.pfaService
      .editDatiTehnici(this.idInterventoForEdit)
      .subscribe((res) => {
        this.datiTehniciEdit = res;
        this.buildTecniciForm(this.datiTehniciEdit);
        this.pfaService.allTipoAllegatoIntervento().subscribe((res) => {
          this.tipoAllegatoAll = res;
          this.filterTipoAllegati();
        });
      });
  }

  buildTecniciForm(datiTehnici) {
    if (
      datiTehnici &&
      datiTehnici.tipoIntervento &&
      datiTehnici.tipoIntervento.fkGoverno
    ) {
      this.tipoInterventi$ = this.pfaService.getTipoInterventiByGoverno(
        datiTehnici.tipoIntervento.fkGoverno
      );
    }
    this.tipoInterventoForm = this.fb.group({
      interventionPlan: [
        {
          value:
            datiTehnici &&
            datiTehnici.tipoIntervento &&
            datiTehnici.tipoIntervento.conformeDeroga
              ? datiTehnici.tipoIntervento.conformeDeroga
              : "C",
          disabled: false,
        },

        Validators.required,
      ],
      noIntervento: [
        {
          value: datiTehnici
            ? datiTehnici.tipoIntervento.progressivoNumerico
            : "",
          disabled: true,
        },
        Validators.required,
      ],
      statoIntervento: [
        {
          value: datiTehnici
            ? datiTehnici.tipoIntervento.fkStatoIntervento
            : "",
          disabled: true,
        },

        Validators.required,
      ],
      dataIntervento: [
        {
          value: datiTehnici
            ? datiTehnici.tipoIntervento.dataPresuntaIntervento
            : "",
          disabled: false,
        },
        Validators.required,
      ],
      sylvanYear: [
        {
          value: datiTehnici ? datiTehnici.tipoIntervento.annataSilvana : "",
          disabled: false,
        },
        Validators.required,
      ],
      noParticleForest: [
        {
          value: datiTehnici
            ? datiTehnici.tipoIntervento.idParticelaForestale
            : "",
          disabled: true,
        },
        Validators.required,
      ],
      relatedEvent: [
        {
          value:
            datiTehnici &&
            datiTehnici.tipoIntervento &&
            datiTehnici.tipoIntervento.idEventoCorelato
              ? datiTehnici.tipoIntervento.idEventoCorelato
              : "",
          disabled: false,
        },
      ],
      government: [
        {
          value: datiTehnici ? datiTehnici.tipoIntervento.fkGoverno : "",
          disabled: false,
        },
        Validators.required,
      ],
      piedilista: [
        {
          value: datiTehnici
            ? datiTehnici.tipoIntervento.richiedePiedilsta == 1
            : false,
          disabled: false,
        },
        Validators.required,
      ],
      description: [
        {
          value: datiTehnici ? datiTehnici.tipoIntervento.descrizione : "",
          disabled: false,
        },
        Validators.required,
      ],
      localita: [
        {
          value: datiTehnici ? datiTehnici.tipoIntervento.localita : "",
          disabled: false,
        },
        Validators.required,
      ],
      superInteressata: [
        {
          value: datiTehnici
            ? datiTehnici.tipoIntervento.superficieInteressata
            : "",
          disabled: true,
        },
        Validators.required,
      ],
      tipoIntervento: [
        {
          value: datiTehnici ? datiTehnici.tipoIntervento.fkTipoIntervento : "",
          disabled: false,
        },
        Validators.required,
      ],
      fasciaAltemetrica: [
        {
          value: datiTehnici
            ? datiTehnici.intervSelvicolturale.fkFasciaAltimetrica
            : "",
          disabled: false,
        },
        Validators.required,
      ],
      finalitaTaglio: [
        {
          value: datiTehnici ? datiTehnici.tipoIntervento.fkFinalitaTaglio : "",
          disabled: false,
        },
        Validators.required,
      ],
      destinazioneLegname: [
        {
          value: datiTehnici ? datiTehnici.tipoIntervento.fkDestLegname : "",
          disabled: false,
        },
        Validators.required,
      ],
    });

    if (datiTehnici != undefined) {
      this.specieForm = this.fb.group({
        specie: this.fb.array([]),
      });
      if (datiTehnici.speciePfaIntervento) {
        datiTehnici.speciePfaIntervento.map((data, index) => {
          this.allRows.push(this.bildeSpecieRow(data));

          let idSpecie = (
            (this.specieForm.get("specie") as FormArray).at(index)[
              "controls"
            ] as FormControl
          )["specie"].value;
          let densita = this.getDensitaSpecie(idSpecie);

          let mThree =
            "" +
            (
              (this.specieForm.get("specie") as FormArray).at(index)[
                "controls"
              ] as FormControl
            )["m3"].value;

          (
            (this.specieForm.get("specie") as FormArray).at(index)[
              "controls"
            ] as FormControl
          )["ton"].setValue(ConverstionVolume.m3ToTon(mThree, densita));

          (
            (this.specieForm.get("specie") as FormArray).at(index)[
              "controls"
            ] as FormControl
          )["q"].setValue(ConverstionVolume.m3ToQ(mThree, densita));
        });
      }
    } else {
      this.specieForm = this.fb.group({
        specie: this.fb.array([this.specieTableForm]),
      });
    }

    this.plantsForm = this.fb.group({
      numeroDiPiante: [
        {
          value: datiTehnici ? datiTehnici.intervSelvicolturale.nrPiante : "",
          disabled: false,
        },
        Validators.required,
      ],
      volumeRamaglia: [
        {
          value:
            datiTehnici &&
            datiTehnici.intervSelvicolturale &&
            datiTehnici.intervSelvicolturale.volumeRamagliaM3
              ? datiTehnici.intervSelvicolturale.volumeRamagliaM3 + ""
              : "0",
          disabled: false,
        },
        Validators.required,
      ],
      unitaDiMisura: [{ value: "", disabled: true }, Validators.required],
      stimaMassaRetraibile: [
        {
          value:
            datiTehnici &&
            datiTehnici.intervSelvicolturale &&
            datiTehnici.intervSelvicolturale.stimaMassaRetraibileM3
              ? datiTehnici.intervSelvicolturale.stimaMassaRetraibileM3
              : 0,
          disabled: true,
        },
      ],
      esbosco: [
        {
          value: datiTehnici
            ? datiTehnici.intervSelvicolturale.idUsoViabilita
            : "",
          disabled: false,
        },
        Validators.required,
      ],
      tipoEsbosco: [
        {
          value: datiTehnici ? datiTehnici.intervSelvicolturale.codEsbosco : "",
          disabled: false,
        },
        Validators.required,
      ],
      noteEsbosco: [
        {
          value: datiTehnici
            ? datiTehnici.intervSelvicolturale.noteEsbosco
            : "",
          disabled: false,
        },
        Validators.required,
      ],
      tipologia: [
        {
          value: "",
          disabled: true,
        },
        Validators.required,
      ],
    });
    let specieValue = this.specieForm.get("specie").value;
    let specieVolume = !specieValue["m3"]
      ? 0
      : specieValue
          .map((x) => parseInt(x.m3))
          .filter(Boolean)
          .reduce((a, b) => a + b);

    let volumeRamaglia = this.plantsForm.get("volumeRamaglia").value;
    // this.plantsForm.get("stimaMassaRetraibile").setValue(specieVolume - volumeRamaglia);

    if (this.tipoInterventoForm.get("interventionPlan").value === "C") {
      this.conformeDerogaValue = "CS";
    } else {
      this.conformeDerogaValue = "AUT";
    }
  }

  getDensitaSpecie(idSpecie) {
    let item = this.listSpecie.filter(
      (item) => item.idSpecie === parseInt(idSpecie)
    );
    return item[0].densita ? item[0].densita : 1;
  }

  bildeSpecieRow(data) {
    return this.fb.group({
      specie: [data.idSpecie, [Validators.required]],
      priorita: [data.flgSpeciePriorita, [Validators.required]],
      m3: [("" + data.volumeSpecia).replace(".", ","), [Validators.required]],
      ton: [""],
      q: [""],
    });
  }

  get allRows(): FormArray {
    return this.specieForm.get("specie") as FormArray;
  }

  get specieTableForm() {
    return this.fb.group({
      specie: ["", [Validators.required]],
      priorita: ["", [Validators.required]],
      m3: ["", [Validators.required]],
      ton: [""],
      q: [""],
    });
  }

  nextStep() {
    this.activeIndex++;
    this.createStepper();
  }

  firstStepCompleted(event: number) {
    this.idIntervento = event;
    this.nextStep();
    this.filterTipoAllegati();
  }

  filterTipoAllegati() {
    this.interventionPlan =
      this.tipoInterventoForm.get("interventionPlan").value;
    if (this.interventionPlan == "D") {
      this.tipoAllegato = this.filterAllegatiById([27, 28, 29, 31, 34]);
    } else if (this.interventionPlan == "C") {
      this.tipoAllegato = this.filterAllegatiById([30, 31, 34]);
    } else {
      this.tipoAllegato = this.tipoAllegatoAll;
    }
  }

  filterAllegatiById(keys: number[]) {
    return this.tipoAllegatoAll.filter(
      (item) => keys.indexOf(item.idTipoAllegato) > -1
    );
  }

  fourthStepCompleted() {
    this.nextStep();
  }

  saveRicadenze(event: any) {
    this.ricadenzeObj = event;
  }

  updateErrors(errors: StepErrors[]) {
    this.errorsOnAllegati = false;
    if (errors != null && errors.length > 0) {
      for (let i in errors) {
        this.steps[errors[i].stepNumber].noOfErrors = errors[i].noOfErrors;
        if (errors[i].stepNumber == 3 && errors[i].noOfErrors > 0) {
          this.errorsOnAllegati = true;
        }
      }
    }
  }

  createStepper() {
    this.lastCompletedStep = this.activeIndex;
    this.steps.forEach((item) => {
      if (item.activeIndex < this.activeIndex) {
        item.setState("step-completed");
        item.enabled = true;
      } else {
        item.setState("step-uncompleted");
      }
      if (item.activeIndex == this.activeIndex) {
        item.isActive = true;
        item.enabled = true;
      }
    });

    this.items = [
      {
        label: "Dati Generali",
        disabled: this.activeIndex >= 0 ? false : true,
      },
      {
        label: "Richiedente e Utilizzatore",
        disabled: this.activeIndex >= 1 ? false : true,
      },
      {
        label: "Localizza intervento",
        disabled: this.activeIndex >= 2 ? false : true,
      },
      {
        label: "Descrizione intervento",
        disabled: this.activeIndex >= 3 ? false : true,
      },
      {
        label: "Allegati",
        disabled: this.activeIndex >= 4 ? false : true,
      },
    ];
  }
  removeRow(index: number) {
    (this.specieForm.get("specie") as FormArray).removeAt(index);
  }

  addRow() {
    this.specieArray = this.specieForm.get("specie") as FormArray;
    this.specieArray.push(this.specieTableForm);
  }

  m3ToTon() {}

  backToDetailo() {
    this.routerService.navigate(
      ["pfa", "tabs", this.pfaPlanId, { tab: "interventi" }],
      {
        queryParams: this.queryParametrs,
      }
    );
  }

  openShootingMirror() {
    this.tableHeaders = PfaConfigComponent.getShootingMirrorHeader();
    if (this.idIntervento || this.idInterventoForEdit) {
      this.pfaService
        .getDataForShootingMirror(
          this.idIntervento ? this.idIntervento : this.idInterventoForEdit
        )
        .subscribe((res) => {
          if (res && res.length > 0) {
            this.shootingMirrorData = res;
            this.createShootingMirrorForm(this.shootingMirrorData);
            this.shootingMirrorVisible = true;
          } else {
            this.showMsg("Info", "Nessun dato disponibile!");
          }
        });
    }
  }

  onShootingMirrorDialogHide() {}

  createShootingMirrorForm(data: any[]) {
    let _form = this.fb.array([]);
    this.shootingMirrorForm = this.fb.group({
      columnForm: _form,
    });
    data.forEach((dataObj) => {
      _form.push(
        FormUtil.addFormFromDataObject(dataObj, this.fb, this.tableHeaders)
      );
    });

    const ripresaIntervenTototal = data
      .map((obj) => (obj["ripresaIntervento"] ? obj["ripresaIntervento"] : 0))
      .reduce((pv, ripresaIntervento) => {
        return pv + ripresaIntervento;
      });
    this.summaryColumnData = {
      ripresaIntervento: {
        totale: ripresaIntervenTototal,
      },
    };
  }

  setIdIntervento(value) {
    this.loadDatiTecnici(value);
  }

  activeStep(stepNum) {
    this.activeIndex = stepNum;
  }

  showMsg(msgType, msg) {
    this.dialogService.showDialog(
      true,
      msg,
      msgType,
      DialogButtons.OK,
      "",
      (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
          console.log("BUTTON WORKS");
        }
      }
    );
  }

  openMappa() {
    //window.scrollTo(0, 0);
    let idIntervento = this.idIntervento
      ? this.idIntervento
      : this.idInterventoForEdit;
    this.pfaService
      .getCartograficoByIdUrl(10, "" + idIntervento)
      .subscribe((response: any) => {
        window.open(response["geecourl"], "_blank");
      });
    return false;
  }
}
