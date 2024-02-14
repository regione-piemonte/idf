/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  OnDestroy,
  OnChanges,
  ChangeDetectorRef,
} from "@angular/core";
import { Router, ActivatedRoute, ParamMap } from "@angular/router";
import { MenuItem } from "primeng/api";
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormArray,
  FormControl,
} from "@angular/forms";
import { Subject, Observable } from "rxjs";
import { takeUntil } from "rxjs/operators";

import { ForestaliService } from "../../services/forestali.service";
import { Step4Model } from "src/app/shared/models/step4.model";
import { CheckboxAndRadio } from "src/app/shared/models/checkbox-and-radio";
import { StepPosition } from "src/app/shared/models/step-position.model";
import { ShowParcel } from "src/app/shared/models/particle-cadastral";
import {
  PlainSestoSezione,
  DocumentoAllegato,
} from "src/app/shared/models/plain-sesto-sezione.model";
import { TableHeader } from "src/app/shared/models/table-header";
import { IstanzaInviata } from "src/app/shared/models/view-instance";
import { e } from "@angular/core/src/render3";
import { HomeModel } from "src/app/shared/models/home.model";
import { DomSanitizer, SafeResourceUrl } from "@angular/platform-browser";
import { DialogService } from "src/app/shared/services/dialog.service";
import { CONST } from "src/app/shared/constants";
import { TagliService } from "../../services/tagli.service";
import { TagliStep3 } from "../../components/stepper-tagli/step3-tagli/tagli-step3.model";
import { TagliStep4 } from "../../components/stepper-tagli/step4-tagli/tagli-step4.model";
import { TagliStep5 } from "../../components/stepper-tagli/step5-tagli/tagli-step5.model";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";
import { Input } from "@angular/core";
import { SaveFileService } from "src/app/shared/services/save-file.service";
import { AuthService } from "../../services/auth.service";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import {
  Step,
  StepErrors,
} from "src/app/shared/components/steps-with-errors/steps-with-errors.component";
import { Location } from "@angular/common";
import { FormUtil } from "src/app/utils/form-util";
import { ShootingMirrorModel } from "src/app/shared/models/shooting-mirror";
import { PfaConfigComponent } from "../../pfa-config";

@Component({
  selector: "app-inserisci-nuova-pfa",
  templateUrl: "./inserisci-nuova-pfa.component.html",
  styleUrls: ["./inserisci-nuova-pfa.component.css"],
})
export class InserisciNuovaPfaComponent implements OnInit, OnDestroy {
  maxLengthMotivoRifiuto = 1990;
  editMode: number;
  completed: boolean = false;
  idInterventoParameter: number;
  idIntervento: number;
  tipoAccreditamento = null;
  shootingMirrorData: ShootingMirrorModel[];
  summaryColumnData: any;
  shootingMirrorVisible = false;
  shootingMirrorForm: FormGroup;
  tableHeaders: TableHeader[];
  delegates = [];
  idInterventoForEdit: number;
  datiDelProfessionistaForm: FormGroup;
  descriptionOfTheForestForm: FormGroup;
  fourthStepForm: FormGroup;
  fifthStepForm: FormGroup;
  sixthStepForm: FormGroup;
  polygonForm: FormGroup;
  errorsOnAllegati: boolean;
  invalidMessage = "il modulo non è valido";
  unsubscribe$ = new Subject<void>();
  lastCompletedStep;
  activeIndex = -1;
  numberOfSteps = 5;
  secondStepTable: ShowParcel[] = [];
  secondStepDataToSend: any = {
    particelleCatastali: [],
    ricadenzaVincoloIdrogeologico: false,
  };
  isTableEmpty: boolean;
  transferSecondFormData: any;
  sixthStepData: PlainSestoSezione;
  formBuilt: boolean;
  isCompleted = false;
  isGeecoCallBack = false;
  transferSixFormDocumento: DocumentoAllegato[];

  /* descriptionOfTheForest: CheckboxAndRadio; */

  items: MenuItem[] = [];

  thirdFormData: CheckboxAndRadio;
  fourthFormData: Step4Model;
  newPlan: boolean = false;
  isFinished: boolean;

  istanzaInviata: IstanzaInviata = {};
  secondFormData$: Observable<any>;
  thirdFormData$: Observable<TagliStep3>;
  fourthFormData$: Observable<TagliStep4>;
  fifthFormData$: Observable<TagliStep5>;
  sixthStepData$: Observable<PlainSestoSezione>;

  isNoNecessaria: boolean = null;
  viewModeOn: boolean = false;
  modificaModeOn: boolean = false;
  boModificaOn: boolean = false;
  boOperatingEnabled: boolean = false;
  instanzaOwnership: any;
  personaOwner: any;
  allegatiTableHeaders: TableHeader[] = [
    { field: "descrTipoAllegato", header: "Tipo allegato" },
    { field: "nomeAllegato", header: "Nome file" },
    //{ field: "note", header: "Note" },
    { field: "dimensioneAllegatoKB", header: "Kb", width: "150px" },
  ];
  tableData: DocumentoAllegato[] = [];
  activateInserci: boolean = true;
  boFinishedAssociareDocumenti: boolean = true;
  allProfessionals = [];
  editProfessionista: boolean = true;
  filePopUp: boolean;
  rifiutoPupUp: boolean;
  motivoRifiuto: string = "";
  user: HomeModel = {};
  isIstanzaInviata: boolean = false;
  showGeeco: boolean = false;
  mapUrl: SafeResourceUrl;

  idTIpoIstanza: string = sessionStorage.getItem(
    CONST.TIPO_ISTANZA_ID_KEY_STORE
  );
  autorizzaPopUp: boolean;
  autorizzaForm: FormGroup;
  currentYear: number = new Date().getFullYear();
  it: any;
  idTipoRichiedente: number;
  pfaPlanId: number;
  returnToSearchResult: boolean = false;
  returnToSearchGestore: boolean = false;
  steps: Step[];
  constructor(
    private pfaService: PfaSampleService,
    private router: Router,
    private forestaliService: ForestaliService,
    private saveFileService: SaveFileService,
    private tagliService: TagliService,
    private authService: AuthService,
    private fb: FormBuilder,
    public route: ActivatedRoute,
    public dialogService: DialogService,
    private sanitizer: DomSanitizer,
    private cdref: ChangeDetectorRef,
    private activatedRoute: ActivatedRoute,
    private location: Location
  ) {}

  ngAfterContentChecked() {
    this.cdref.detectChanges();
  }

  ngDoCheck() {}

  ngOnInit() {
    this.checkUriParams();
    this.it = CONST.IT;
    this.user = this.authService.currentUser();

    if (!this.router.url.includes("/inserisciNuovaPfa")) {
      this.route.paramMap.subscribe((paramMap: ParamMap) => {
        if (paramMap.get("returnToSearchResult") !== null) {
          this.returnToSearchResult = true;
        } else {
          this.returnToSearchResult = false;
        }

        if (paramMap.get("returnToSearchGestore") !== null) {
          this.returnToSearchGestore = true;
        } else {
          this.returnToSearchGestore = false;
        }
        console.log("param", paramMap.get("id"));
        this.editMode = +paramMap.get("id");
        this.isFinished = this.activeIndex >= 5 ? true : false;
        if (!this.editMode) {
          //this.router.navigate(["visualizza-tagli"]);
        }

        this.loadPraticaInviata();
        if (this.isFinished) {
          this.checkIstanzaOwner(this.editMode);
        }

        this.modificaModeOn = !this.modificaModeOn;
        if (this.viewModeOn) {
          this.modificaModeOn = !this.modificaModeOn;
        }
      });
    } else {
      this.route.paramMap.subscribe((paramMap: ParamMap) => {
        if (paramMap.get("idIntervento") === null) {
          this.newPlan = true;
        }
      });
    }

    if ("" + this.boModificaOn === "true") {
      this.tagliService.getDocGestore(this.editMode).subscribe((res) => {
        this.tableData = res;
      });
    }

    if (window.location.href.indexOf("return-modifica-tagli") > -1) {
      this.isGeecoCallBack = true;
      /* this.tagliService
        .recalculateParticelle(this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response) => {
        }); */
        this.setActiveIndex();
      //this.thirdFormData$ = this.tagliService.getStep3(this.editMode)
    } else {
      console.log("...retorno ....> ", this.tagliService.getStep3(this.editMode));
      this.setActiveIndex();
    }

    if (this.boModificaOn) {
      this.checkIstanzaOwner(this.editMode);
    }

    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      console.log({ paramMap });
      this.idInterventoParameter = +paramMap.get("id");
      //this.idInterventoParameter = +paramMap.get("idIntervento");
      localStorage.setItem(
        "idInterventoParameter",
        paramMap.get("id")
      );
    });
    this.activatedRoute.params.subscribe((res) => {
      console.log(res);
      
      this.pfaPlanId = JSON.parse(localStorage.getItem("tabIndex"));
      this.idInterventoForEdit = res.idIntervento;
      //this.editMode = res.editmode;
      this.editMode = res.id; // deberia utilizarse esta forma de captar el id del intervento para poder editar en el step2
    });
    if (this.idIntervento || this.idInterventoForEdit) {
      this.checkForErrors();
    } else {
      this.createStepper();
    }
  }
  nextStep() {
    this.activeIndex++;
    this.createStepper();
  }
  onShootingMirrorDialogHide(){}
  checkForErrors() {
    let idIntervento =
      this.idIntervento != null
        ? this.idIntervento
        : parseInt(localStorage.getItem("idInterventoParameter"), 10);
    console.log("checkForErrors", idIntervento);
    this.pfaService.getStepNumber(idIntervento).subscribe((res) => {
      this.activeIndex = res["nextStep"];
      console.log("getStepNumber", this.activeIndex);
      this.createStepper();
      /* this.pfaService.getErroriIntervento(idIntervento).subscribe((res) => {
        this.updateErrors(res);
      }); */
    });
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
  loadPraticaInviata() {
    this.forestaliService
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
      });
    //this.modificaModeOn = true;
  }

  toDelete() {
    switch (this.user.ruolo) {
      case CONST.ROLE_GESTORE || CONST.ROLE_SPORTELLO_GESTORE: {
        return true;
      }
      default: {
        return false;
      }
    }
  }

  getFormatDateIT(date: string) {
    if (date != null && date.length == 10) {
      let today = new Date(date);
      let dd = String(today.getDate()).padStart(2, "0");
      let mm = String(today.getMonth() + 1).padStart(2, "0"); //January is 0!
      let yyyy = today.getFullYear();
      return dd + "/" + mm + "/" + yyyy;
    }
    return "n.d.";
  }

  checkIstanzaOwner(idIntervento: number) {
    this.tagliService
      .getUtenteForIstanza(idIntervento)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.personaOwner = res.body;
        if (
          this.personaOwner.tipoTitolarita === "RG" ||
          this.personaOwner.tipoTitolarita === "RF"
        ) {
          this.instanzaOwnership = "Richiedente";
        } else if (
          this.personaOwner.tipoTitolarita === "PF" ||
          this.personaOwner.tipoTitolarita === "PG"
        ) {
          this.instanzaOwnership = "Professionista";
          let option = {
            label: this.personaOwner.richCodiceFiscale,
            value: this.personaOwner.richCodiceFiscale,
          };
          this.allProfessionals.push(option);
        } else if (
          this.personaOwner.tipoTitolarita === "SF" ||
          this.personaOwner.tipoTitolarita === "SG"
        ) {
          this.instanzaOwnership = "Sportello";
        }
        this.idTipoRichiedente = this.personaOwner.fkTipoRichiedente;
      });
  }
  getAllProfessionals(event: any) {
    this.allProfessionals = [];
    this.personaOwner = {};
    this.tagliService
      .getStep8Professionista(this.istanzaInviata.fkTipoIstanza)
      .subscribe((res) => {
        let tempArray = res;
        this.editProfessionista = false;
        this.fillSelect(tempArray);
      });
  }

  fillSelect(soggetos: any) {
    soggetos.forEach((element) => {
      let option = { label: element.codiceFiscale, value: element };
      this.allProfessionals.push(option);
    });
  }

  changeOwner(event) {
    this.personaOwner = {
      richNome: event.value.nome,
      richCognome: event.value.cognome,
      richCodiceFiscale: event.value.codiceFiscale,
    };
    //TODO
  }

  checkUriParams() {
    const data = this.route.snapshot.params["viewMode"];
    data ? (this.viewModeOn = data) : (this.viewModeOn = false);
    const boModifica = this.route.snapshot.params["boModifica"];
    boModifica ? (this.boModificaOn = boModifica) : (this.boModificaOn = false);
  }

  setIdIstanze(event) {
    if (!this.editMode) {
      this.editMode = event;
    }
  }
  setNoNecessaria(event) {
    this.isNoNecessaria = event;
  }

  setActiveIndex() {
    if (this.editMode) {
      this.tagliService
        .getStepNumber(this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response: StepPosition) => {
          if (response.nextStep === 0) {
            this.activeIndex++;
            this.createStepper();
            this.items[this.activeIndex].command();
            return;
          }
          if (this.isGeecoCallBack) {
            // console.log(
            //   "this.tagliService setActiveIndex---------------------> ",
            //   this.isGeecoCallBack
            // );
            // let particelleGuardateTMP = JSON.parse(
            //   localStorage.getItem("particellaGuardada")
            // );
            // console.log(
            //   "particelleGuardateTMP---------------------> ",
            //   particelleGuardateTMP
            // );
            // if (particelleGuardateTMP !== null) {
            //   this.enviarParticelleGuardate(particelleGuardateTMP);
            // }

            this.isGeecoCallBack = false;
            this.backward();
            this.activeIndex = 2;
          } else {
            //utente demo 36 se clicca su matita o foglio entra direttamente su riepilogo(step5+1)
            this.activeIndex =
              this.user.ruolo == CONST.ROLE_UF_TERRIRORIALE
                ? response.nextStep + 1
                : response.nextStep;
          }
          if (this.activeIndex == 5) {
            this.modificaModeOn = false;

            this.tagliService
              .getAllDocuments(this.editMode)
              .subscribe((res) => {
                this.transferSixFormDocumento = res;
              });

            this.loadPraticaInviata();
          }

          this.createStepper();
          if (this.activeIndex !== 5) {
            this.items[this.activeIndex].command();
          }
        });
    } else {
      this.activeIndex++;
      this.createStepper();
    }
  }

  enviarParticelleGuardate(particelleGuardate: any[]) {
    particelleGuardate.forEach((particella) => {
      // Convertir el objeto particella en una cadena JSON
      //let particellaJSON = JSON.stringify(particella);
      // Llamar al servicio y enviar la cadena JSON en el cuerpo de la solicitud
      this.tagliService
        .insertGeometryFromSingleParticella(this.editMode, particella)
        .subscribe((res) => {
          if (res["error"]) {
            // this.particellaNotFound = true;
            console.log("error ", true);
          } else {
            console.log("particelleCatastali ", res);
            //localStorage.removeItem("particellaGuardada");
          }
        });
    });
  }

  createStepper() {
    if (this.activeIndex < 6) {
      this.createStepperImpl();
    }
  }

  createStepperImpl() {
    console.log("this.createStepperImpl------");
    this.items = [
      {
        label: "Dati Generali",
        command: (event: any) => {
          this.activeIndex = 0;
          console.log("this.Dati Generali------");
        },
        disabled: this.activeIndex >= 0 ? false : true,
        styleClass: this.activeIndex >= 0 ? "green-step" : "yellow-step",
      },
      {
        label: "Richiedente e Utilizzatore",
        command: (event: any) => {
          this.activeIndex = 1;
          console.log(
            "this.isNoNecessaria------",
            this.tagliService.getStep2(this.editMode)
          );
          this.secondFormData$ = this.tagliService.getStep2(this.editMode);
        },
        disabled: this.activeIndex >= 1 ? false : true,
        styleClass: this.activeIndex >= 1 ? "green-step" : "yellow-step",
      },
      {
        label: "Localizzazione intervento",
        command: (event: any) => {
          this.activeIndex = 2;
          this.thirdFormData$ =null;
          let idInterventoParameter = parseInt(
            localStorage.getItem("idInterventoParameter"),
            10
          );
          this.thirdFormData$ = this.tagliService.getStep3(this.editMode);
        },
        disabled: this.activeIndex >= 2 ? false : true,
        badgeStyleClass: this.activeIndex >= 2 ? "green-step" : "yellow-step",
      },
      {

        label: "Descrizione intervento",
        command: (event: any) => {
          console.log(
            "this.paso4 ------"
          );
          this.activeIndex = 3;
          let idInterventoParameter = parseInt(
            localStorage.getItem("idInterventoParameter"),
            10
          );
          this.fourthFormData$ = this.tagliService.getStep4(idInterventoParameter);
        },
        disabled: this.activeIndex >= 3 ? false : true,
        badgeStyleClass: this.activeIndex >= 3 ? "green-step" : "yellow-step",
      },
      {
        label: "Allegati",
        command: (event: any) => {
          this.activeIndex = 4;
          this.fifthFormData$ = this.tagliService.getStep5(this.editMode);
        },
        disabled: this.activeIndex >= 4 ? false : true,
        badgeStyleClass: this.activeIndex >= 4 ? "green-step" : "yellow-step",
      },
    ];
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  onChangeStep(activeIndex) {}

  goHome() {
    if (this.boModificaOn) {
      this.forestaliService.clearSearchStorage();
      this.router.navigate([""]);
    } else {
      this.router.navigate([""]);
    }
  }

  goVisualizza() {
    if (this.returnToSearchGestore) {
      this.router.navigate(["ricerca-gestore-sportello-tagli"]);
    } else if (this.returnToSearchResult) {
      this.router.navigate(["ricerca-archivio-tagli"]);
    } else {
      //this.location.back()
      //this.router.navigate(["pfa/tabs/"+ JSON.parse(localStorage.getItem("tabIndex"))]);
      this.router.navigate(["pfa", "tabs", JSON.parse(localStorage.getItem("tabIndex")),{tab:'interventi'}]);
    }
  }

  backward() {
    this.modificaModeOn = !this.modificaModeOn;
  }

  returnToArhiveList() {
    if (this.returnToSearchGestore) {
      this.router.navigate(["ricerca-gestore-sportello-tagli"]);
    } else if (this.returnToSearchResult) {
      this.router.navigate(["ricerca-archivio-tagli"]);
    } else {
      this.router.navigate([""]);
    }
  }

  setCompleted(event) {
    this.isCompleted = event;
    this.loadPraticaInviata();
    this.viewModeOn = true;
  }

  upload(event, tipo) {
    let dataAllegati;
    const formData: FormData = new FormData();
    formData.append("form", event.files[0]);
    formData.append("fileName", event.files[0].name);
    if (this.tagliService.isNamePresent(event.files[0].name, this.tableData)) {
      this.filePopUp = true;
    } else if (
      this.tagliService.isNamePresent(
        event.files[0].name,
        this.transferSixFormDocumento
      )
    ) {
      this.filePopUp = true;
    } else {
      this.filePopUp = false;
      this.tagliService.uploadAllegato(formData, this.editMode, tipo).subscribe(
        (response: DocumentoAllegato) => {
          this.tableData.push(response);
          this.activateInserci = false;
        },
        (err) => {
          console.log("====================================");
          console.log(err);
          console.log(err.error ? err.error.error : err.message);
          console.log("====================================");
        }
      );
    }
  }
  closeDialog() {
    this.filePopUp = false;
  }

  associareDocumenti() {
    /*
    TODO: implement for tagli
    */
    this.forestaliService
      .associareDocumentiBo(this.editMode, this.tableData)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          this.activateInserci = !this.activateInserci;
          this.tableData = [];
          this.boFinishedAssociareDocumenti = false;
        }
      });
  }

  fieldToDelete(event, table) {
    if (table === "elenco") {
      this.tagliService
        .deleteDocumentoAllegato(event.rowIndex)
        .subscribe((res) => {
          this.tableData.forEach((item, index) => {
            if (item.idDocumentoAllegato === event.rowIndex) {
              item.deleted = true;
              this.tableData.splice(event.index, 1);
            }
          });
        });
    }
  }

  fieldToDownload(event) {
    this.tagliService
      .downloadAllegato(event)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        let contentDisposition = response.headers.get("content-disposition");
        let filename = contentDisposition
          .split(";")[1]
          .split("filename")[1]
          .split("=")[1];
        this.saveFileService.saveFile(response, filename);
      });
  }

  autorizzaIstanza() {
    this.initAutorizzaForm();
    this.autorizzaPopUp = true;
  }

  initAutorizzaForm() {
    this.autorizzaForm = new FormGroup({
      dataVerifica: new FormControl("", [Validators.required]),
      nrDeterminaAutoriz: new FormControl("", [Validators.required]),
      dataTermineAut: new FormControl(""),
      definitaNelProvvedimentoUnicoFinale: new FormControl(""),
      dataFineIntervento: new FormControl(""),
    });
  }

  isAutorizzaFormValid() {
    let dataTermineAut = this.autorizzaForm.get("dataTermineAut").value;
    let dataFineIntervento = this.autorizzaForm.get("dataFineIntervento").value;
    let provUnicoFInale = this.autorizzaForm.get(
      "definitaNelProvvedimentoUnicoFinale"
    ).value;

    return (
      this.autorizzaForm &&
      this.autorizzaForm.valid &&
      ((dataTermineAut == "" && provUnicoFInale == true) ||
        (dataTermineAut != "" && provUnicoFInale == false))
    );
    /*     return this.autorizzaForm && this.autorizzaForm.valid && (
            (dataTermineAut == '' && provUnicoFInale == true && dataFineIntervento != '') ||
            (dataTermineAut != '' && dataFineIntervento != '' && dataFineIntervento >= dataTermineAut)
          );
 */
  }

  rifiutataIstanza() {
    this.rifiutoPupUp = false;
    this.forestaliService
      .rifiutataIstanza(this.editMode, this.motivoRifiuto)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          this.completed = true;
          this.loadPraticaInviata();
        }
      });
  }

  onRifiutataClick() {
    this.rifiutoPupUp = true;
  }

  onMotivoRifiutoChange(event) {
    this.motivoRifiuto = event.target.value;
    if (this.motivoRifiuto.length > this.maxLengthMotivoRifiuto) {
      this.motivoRifiuto = this.motivoRifiuto.substr(
        0,
        this.maxLengthMotivoRifiuto
      );
    }
  }

  disabledRifiuto() {
    return this.motivoRifiuto.length < 20;
  }

  mostraDettaglioFinale() {
    this.activeIndex = 5;
  }
/*
  openMappa() {
    //window.scrollTo(0, 0);
    this.forestaliService
      .getCartograficoPointsUrl(this.boModificaOn ? 4 : 7, ["" + this.editMode])
      .subscribe((response: any) => {
        this.mapUrl = this.sanitizer.bypassSecurityTrustResourceUrl(
          response["geecourl"]
        );
        window.open(response["geecourl"], "_blank");
      });
    return false;
  } */

  confermaAutorizzazione() {
    let values = JSON.parse(JSON.stringify(this.autorizzaForm.value));
    delete values.definitaNelProvvedimentoUnicoFinale;
    values["idIntervento"] = this.editMode;

    this.tagliService.autorizzaIstanza(values).subscribe((res) => {
      this.completed = true;
      this.closeAutorizzazione();

      this.dialogService.showDialog(
        true,
        "Istanza autorizzata con successo!",
        "Operazione completata",
        DialogButtons.OK,
        "",
        (buttonId: number): void => {
          if (buttonId === ButtonType.OK_BUTTON) {
            console.log("BUTTON WORKS");
          }
          this.loadPraticaInviata();
        }
      );
    });
  }

  verificaIstanza() {
    let values = { idIntervento: this.editMode };

    this.tagliService
      .verificaIstanza(values)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        //if(response.status === 200){
        this.completed = true;
        this.dialogService.showDialog(
          true,
          "Istanza verificata con successo!",
          "Operazione completata",
          DialogButtons.OK,
          "",
          (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
              console.log("BUTTON WORKS");
            }
            this.loadPraticaInviata();
          }
        );
      });
  }

  closeAutorizzazione() {
    this.autorizzaPopUp = false;
  }

  onChangeCheckboxDefinitaData() {
    if (
      this.autorizzaForm.get("definitaNelProvvedimentoUnicoFinale").value ==
      true
    ) {
      this.autorizzaForm.get("dataTermineAut").patchValue("");
    }
  }

  onChangeDataTermineAut() {
    this.autorizzaForm
      .get("definitaNelProvvedimentoUnicoFinale")
      .patchValue(false);
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
      : this.editMode;
    this.pfaService
      .getCartograficoByIdUrl(10, "" + idIntervento)
      .subscribe((response: any) => {
        window.open(response["geecourl"], "_blank");
      });
    return false;
  }

  openShootingMirror() {
    this.tableHeaders = PfaConfigComponent.getShootingMirrorHeader();
    if (this.idIntervento || this.editMode) {
      this.pfaService
        .getDataForShootingMirror(
          this.idIntervento ? this.idIntervento : this.editMode
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
}
