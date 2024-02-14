/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { FormGroup, Validators, FormBuilder } from "@angular/forms";
import { Router, ActivatedRoute } from "@angular/router";
import { Subject } from "rxjs";
import { takeUntil } from "rxjs/operators";
import { environment } from "src/environments/environment";
import { ForestaliService } from "../../services/forestali.service";
import { VincoloService } from "../../services/vincolo.service";
import { UserChoiceModel } from "src/app/shared/models/user-choice.model";
import { CONST } from "src/app/shared/constants";
import { ProvinciaModel } from "src/app/shared/models/provincia.model";
import { AmbitoInstanza } from "src/app/shared/models/ambito-instanza.model";
import { ProfilioUtente } from "src/app/shared/models/profilio-utente.model";
import { TipoAccreditamento } from "src/app/shared/models/tipo-accreditamento.model";
import { HomeModel } from "src/app/shared/models/home.model";
import { CategoriaProfessionale } from "src/app/shared/models/categoria-professionale";
import { AuthService } from "./../../../../shared/services/auth.service";
import { TagliService } from "../../services/tagli.service";
import {
  cfOrPiva,
  codiceFiscale,
  partitaIva,
} from "src/app/validators/string.validators";
import { SoggettoModel } from "src/app/shared/models/soggetto.model";
import { DelegaModel } from "src/app/shared/models/delega.model";
import { DialogService } from "src/app/shared/services/dialog.service";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";
import { TableHeader } from "src/app/shared/models/table-header";
import { SaveFileService } from "src/app/shared/services/save-file.service";

@Component({
  selector: "app-home-page",
  templateUrl: "./home-page.component.html",
  styleUrls: ["./home-page.component.css"],
})
export class HomePageComponent implements OnInit, OnDestroy {
  personalDataForm: FormGroup;
  unsubscribe$ = new Subject<void>();
  display = false;
  fields = [
    "categoriaProfessionale",
    "partitaIva",
    "pec",
    "provinciaOrdine",
    "numeroIscrizione",
    "codiceFiscaleDelega",
  ];
  datiPersonali: boolean = false;
  datiPersonaliForm: boolean = false;
  personHome: any;
  userRole: any;
  ambitoInstanza: AmbitoInstanza[] = CONST.AMBITO_OPZIONE;
  dialogText: string = CONST.TOOLTIP_CODICE_PROFESSIONISTA;
  emptyMessageAC: string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;
  provincies: ProvinciaModel[];
  onlyRich: UserChoiceModel;
  tipoIstanza: number;
  homedata: HomeModel;
  sportelli: SoggettoModel[];

  modificaSelezione: boolean = false;
  categorieProfessionali: CategoriaProfessionale[];

  TipoAccreditamento: any = TipoAccreditamento;

  showProfessionistaForm = false;

  isEnviromentProd: boolean;

  listDeleghe: DelegaModel[] = [];
  delegaForm: FormGroup;
  fileToUpload: File;

  currentYear: number = new Date().getFullYear();
  tomorrow: Date = new Date();
  it: any;

  delegheTableHeaders: TableHeader[] = [
    { field: "cfDelegante", header: "Codice fiscale" },
    { field: "dataInizioFormat", header: "Data inizio" },
    { field: "dataFineFormat", header: "Data fine" },
  ];

  constructor(
    private vincoloService: VincoloService,
    private dialogService: DialogService,
    private forestaliService: ForestaliService,
    private tagliService: TagliService,
    private saveFileService: SaveFileService,
    private router: Router,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.tomorrow.setDate(this.tomorrow.getDate() + 1);
    this.it = CONST.IT;
    this.isEnviromentProd =
      environment.beServerPrefix == "https://idf.sistemapiemonte.it";
    this.checkParams();
    this.getHomeData();
    this.loadListDeleghe();
  }

  initDelegaForm() {
    this.delegaForm = this.fb.group({
      cfDelegante: [
        { value: "", disabled: false },
        [Validators.required, cfOrPiva(), codiceFiscale(), partitaIva()],
      ],
      dataFine: [{ value: "", disabled: false }, [Validators.required]],
    });
  }

  loadListDeleghe() {
    this.forestaliService.getMiaListaDeleghe().subscribe((res) => {
      this.listDeleghe = res;
      this.listDeleghe.forEach((item) => {
        item["dataInizioFormat"] = this.getDataIt(item.dataInizio);
        item["dataFineFormat"] = this.getDataIt(item.dataFine);
      });
    });
  }

  downloadDelega(event) {
    this.forestaliService
      .downloadDocumentoDelega(event)
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

  onFileRemove(data: any) {
    this.fileToUpload = null;
  }

  onFileUpload(data: any) {
    this.fileToUpload = null;
    let files: FileList = data && data.files ? data.files : null;
    if (files && files[0]) {
      let fileType = files[0].type;
      if (
        fileType == "application/pdf" ||
        fileType == "application/pkcs7-mime"
      ) {
        this.fileToUpload = files[0];
      }
    }
  }

  getDataIt(data) {
    if (data) {
      let aux = data.split("-");
      return aux[2] + "-" + aux[1] + "-" + aux[0];
    }
    return "";
  }

  checkParams() {
    let si = this.route.snapshot.queryParams["modificaSelezione"];
    if (si === "true") {
      this.modificaSelezione = true;
    }
  }

  showDialogMsg(msgType: string, msg: string) {
    this.dialogService.showDialog(
      true,
      msg,
      msgType,
      DialogButtons.OK,
      "",
      (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
        }
      }
    );
  }

  returnToHome() {
    this.modificaSelezione = true;
    this.getHomeData();
  }

  getHomeData() {
    this.datiPersonali = false;
    this.forestaliService.getHomeData().subscribe((homedata) => {
      this.homedata = homedata;
      this.checkRole(homedata.fkProfiloUtente);
    });
  }

  showFormDelega() {
    this.initDelegaForm();
  }

  annullaEditDelega() {
    this.delegaForm = null;
  }

  saveDelega() {
    let user: HomeModel = this.authService.currentUser();
    let values = this.delegaForm.value;
    let delega = this.listDeleghe.filter(
      (item) => item.cfDelegante == values.cfDelegante.toUpperCase()
    );
    if (delega.length > 0) {
      this.showDialogMsg("Attenzione", "Delega già presente!");
      return;
    }
    values.dataFine = this.forestaliService.formatDate(values.dataFine);
    const formData: FormData = new FormData();
    formData.append("delegaFirmata", this.fileToUpload);
    formData.append("data", encodeURIComponent(JSON.stringify(values)));
    if (user.ruolo == ProfilioUtente.NO_DATA) {
      user.ruolo = ProfilioUtente.CITTADINO; // solo per chi non ha profilo settato
      sessionStorage.setItem(CONST.USER_KEY_STORE, JSON.stringify(user));
      sessionStorage.removeItem(CONST.SPORTELLO_PG_KEY_STORE);
      if (
        this.personalDataForm.value.fkTipoAccreditamento ===
        TipoAccreditamento.RICHIEDENTE
      ) {
        this.onlyRich.fkTipoAccreditamento =
          this.personalDataForm.value.fkTipoAccreditamento;
        this.onlyRich.tipoIstanzaId = this.personalDataForm.value.tipoIstanzaId;
      } else if (
        this.personalDataForm.valid &&
        this.personalDataForm.value.fkTipoAccreditamento ===
          TipoAccreditamento.PROFESSIONISTA
      ) {
        sessionStorage.setItem(
          CONST.CODICE_FISCALE_KEY_STORE,
          this.personalDataForm.value.codiceFiscaleDelega
        );
        this.onlyRich.fkTipoAccreditamento =
          this.personalDataForm.value.fkTipoAccreditamento;
        this.onlyRich.tipoIstanzaId = this.personalDataForm.value.tipoIstanzaId;
        this.onlyRich.provinciaOrdine =
          this.personalDataForm.value.provinciaOrdine.istatProv;
        if (this.personalDataForm.value.categoriaProfessionale != null) {
          this.onlyRich.categoriaProfessionale =
            this.personalDataForm.value.categoriaProfessionale.idCategoriaProfessionale;
        }
        this.onlyRich.partitaIva = this.personalDataForm.value.partitaIva;
        this.onlyRich.pec = this.personalDataForm.value.pec;
        this.onlyRich.numeroIscrizione =
          this.personalDataForm.value.numeroIscrizione;

        this.onlyRich.codiceFiscaleDelega = this.delegaForm.value.cfDelegante;
      } else if (
        this.personalDataForm.value.fkTipoAccreditamento ===
        TipoAccreditamento.SPORTELLO
      ) {
        sessionStorage.setItem(
          CONST.SPORTELLO_PG_KEY_STORE,
          JSON.stringify(this.personalDataForm.value.sportello)
        );
        this.onlyRich.fkTipoAccreditamento =
          this.personalDataForm.value.fkTipoAccreditamento;
        this.onlyRich.tipoIstanzaId = this.personalDataForm.value.tipoIstanzaId;
        this.onlyRich.fkSoggettoSportello =
          this.personalDataForm.value.sportello.idSoggetto;
      } else if (
        this.personalDataForm.value.fkTipoAccreditamento ===
        TipoAccreditamento.GESTORE
      ) {
        //this.userRole='BO';
        this.tipoIstanza = 2;
        sessionStorage.setItem(
          CONST.TIPO_ISTANZA_ID_KEY_STORE,
          JSON.stringify(this.tipoIstanza)
        );
        this.onlyRich.fkTipoAccreditamento =
          this.personalDataForm.value.fkTipoAccreditamento;
        this.onlyRich.tipoIstanzaId = this.tipoIstanza;
        //return;
      }
      this.tipoIstanza = this.onlyRich.tipoIstanzaId;
      sessionStorage.setItem(
        CONST.TIPO_ISTANZA_ID_KEY_STORE,
        JSON.stringify(this.onlyRich.tipoIstanzaId)
      );

      this.forestaliService
        .putAdpfor(this.onlyRich)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(res=>console.log(res),(e)=>console.log(e),()=>{
          this.forestaliService.saveDelega(formData).subscribe((res) => {
            if (res && res.error) {
              this.showDialogMsg("Errore", res.error);
            } else if (res > 0) {
              this.showDialogMsg("Into", "Delega salvata correttamente");
              this.loadListDeleghe();
              this.delegaForm = null;
            } else {
              this.showDialogMsg("Errore", "Non è stato possibile salvare la delega");
            }
          });
          return;
        }
        
        );
    }
    

    this.forestaliService.saveDelega(formData).subscribe((res) => {
      if (res && res.error) {
        this.showDialogMsg("Errore", res.error);
      } else if (res > 0) {
        this.showDialogMsg("Into", "Delega salvata correttamente");
        this.loadListDeleghe();
        this.delegaForm = null;
      } else {
        this.showDialogMsg("Errore", "Non è stato possibile salvare la delega");
      }
    });
  }

  deleteDelega(item) {
    this.forestaliService.deleteDelegaByCf(item).subscribe((res) => {
      if (res > 0) {
        this.showDialogMsg("Attenzione", "Delega eliminata correttamente");
        this.loadListDeleghe();
      } else {
        this.showDialogMsg(
          "Errore",
          "Non è stato possibile eliminare la delega"
        );
      }
    });
  }

  checkRole(profilioUtente: number) {
    switch (profilioUtente) {
      case ProfilioUtente.GESTORE:
        this.userRole = "BO";
        break;
      case ProfilioUtente.UFFICIO_TERRITORIALE:
        this.userRole = "BO";
        break;
      case ProfilioUtente.COMUNE:
        this.userRole = "BO";
        break;
      case ProfilioUtente.CONSULTAZIONE:
        this.userRole = "BO";
        break;
      case ProfilioUtente.CITTADINO:
        this.userRole = "FO";
        this.forestaliService
          .getAdpfor()
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe((res: UserChoiceModel) => {
            this.tipoIstanza = res.tipoIstanzaId;
            this.onlyRich = res;
            this.initForm(res);
          });
        break;
      case ProfilioUtente.SPORTELLO:
        this.userRole = "FO";
        this.forestaliService
          .getAdpfor()
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe((res: UserChoiceModel) => {
            this.tipoIstanza = res.tipoIstanzaId ? res.tipoIstanzaId : 2;
            this.onlyRich = res;

            this.tagliService.getSportelliAssociati().subscribe((response) => {
              this.sportelli = response;
              res.fkSoggettoSportello = this.homedata.fkSoggettoSportello;
              res.categoriaProfessionale = 1; // force for tagli selvicolturali
              this.initForm(res);
            });
          });
      case ProfilioUtente.SPORTELLO_GESTORE:
        this.userRole = "FO";
        this.forestaliService
          .getAdpfor()
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe((res: UserChoiceModel) => {
            this.tipoIstanza = res.tipoIstanzaId ? res.tipoIstanzaId : 2;
            this.onlyRich = res;

            this.tagliService.getSportelliAssociati().subscribe((response) => {
              this.sportelli = response;
              res.fkSoggettoSportello = this.homedata.fkSoggettoSportello;
              res.categoriaProfessionale = 1; // force for tagli selvicolturali
              this.initForm(res);
            });
          });

        break;
      case ProfilioUtente.NO_DATA:
        this.userRole = "FO";
        this.tipoIstanza = 1;
        this.onlyRich = {
          tipoIstanzaId: this.tipoIstanza,
          fkTipoAccreditamento: TipoAccreditamento.RICHIEDENTE,
          numeroIscrizione: this.homedata.numeroIscrizione,
          partitaIva: this.homedata.partitaIva,
          pec: this.homedata.pec,
          provinciaOrdine: this.homedata.provinciaOrdine,
        };
        this.initForm(this.onlyRich);
        break;
    }
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  private initForm(res: UserChoiceModel) {
    let currentSportello = this.sportelli
      ? this.sportelli.find((s) => s.idSoggetto === res.fkSoggettoSportello)
      : null;

    let storedTipoIstanzaId = JSON.parse(
      sessionStorage.getItem(CONST.TIPO_ISTANZA_ID_KEY_STORE)
    );
    if (storedTipoIstanzaId === 3) storedTipoIstanzaId = 2;
    let tipoIstanza = storedTipoIstanzaId
      ? storedTipoIstanzaId
      : res && res.tipoIstanzaId
      ? res.tipoIstanzaId
      : this.tipoIstanza
      ? this.tipoIstanza
      : 1;
    this.personalDataForm = this.fb.group({
      tipoIstanzaId: [tipoIstanza, Validators.required],
      fkTipoAccreditamento: [
        res && res.fkTipoAccreditamento ? res.fkTipoAccreditamento : null,
        Validators.required,
      ],
      partitaIva: [res && res.partitaIva ? res.partitaIva : null],
      pec: [res && res.pec ? res.pec : null],
      categoriaProfessionale: [
        res && res.categoriaProfessionale
          ? this.findCategoriaProfessionale(res.categoriaProfessionale)
          : null,
      ],
      provinciaOrdine: [
        res && res.provinciaOrdine
          ? this.findProvincia(res.provinciaOrdine)
          : null,
        Validators.required,
      ],
      numeroIscrizione: [
        res && res.numeroIscrizione ? res.numeroIscrizione : null,
      ],
      codiceFiscaleDelega: [
        res && res.codiceFiscaleDelega ? res.codiceFiscaleDelega : null,
      ],
      sportello: [res && res.fkSoggettoSportello ? currentSportello : null],
    });
    this.changeValidation();

    this.personalDataForm
      .get("fkTipoAccreditamento")
      .valueChanges.pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        if (res === TipoAccreditamento.GESTORE) {
          this.personalDataForm.controls["tipoIstanzaId"].disable();
        } else {
          this.personalDataForm.controls["tipoIstanzaId"].enable();
        }
        this.changeValidation();
      });

    this.personalDataForm
      .get("tipoIstanzaId")
      .valueChanges.pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.changeValidation();
      });
  }

  findProvincia(provinciaOrdine: string): any {
    this.forestaliService
      .getProvinciaById(provinciaOrdine)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ProvinciaModel) => {
        this.personalDataForm.get("provinciaOrdine").patchValue(res);
      });
  }

  findCategoriaProfessionale(categoriaProfessionale: number): any {
    this.vincoloService
      .getCategoriaProfessionaleById(categoriaProfessionale)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: CategoriaProfessionale) => {
        this.personalDataForm.get("categoriaProfessionale").patchValue(res);
      });
  }

  changeValidation() {
    for (const field of this.fields) {
      this.personalDataForm.get(field).setValidators(null);
      if (
        this.personalDataForm.get("fkTipoAccreditamento").value ===
        TipoAccreditamento.PROFESSIONISTA
      ) {
        if (field === "partitaIva") {
          this.personalDataForm
            .get(field)
            .setValidators([Validators.required, Validators.maxLength(11)]);
        }
        if (field === "pec") {
          this.personalDataForm
            .get(field)
            .setValidators([
              Validators.required,
              Validators.pattern(CONST.PATTERN_MAIL),
              Validators.maxLength(200),
            ]);
        }
        if (field === "provinciaOrdine") {
          this.personalDataForm.get(field).setValidators([Validators.required]);
        }
        if (
          field === "categoriaProfessionale" &&
          this.personalDataForm.get("tipoIstanzaId").value > 3
        ) {
          this.personalDataForm.get(field).setValidators([Validators.required]);
        }
        if (field === "numeroIscrizione") {
          this.personalDataForm
            .get(field)
            .setValidators([
              Validators.required,
              //Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
              Validators.maxLength(50),
            ]);
        }
        if (field === "codiceFiscaleDelega") {
          this.personalDataForm
            .get(field)
            .setValidators([Validators.required, Validators.maxLength(16)]);
        }
      }
    }

    const idIstanza = this.personalDataForm.get(
      CONST.TIPO_ISTANZA_ID_KEY_STORE
    ).value;

    if (
      this.personalDataForm.get("fkTipoAccreditamento").value ===
      TipoAccreditamento.PROFESSIONISTA
    ) {
      this.showProfessionistaForm = true;
    } else if (
      this.personalDataForm.get("fkTipoAccreditamento").value ===
      TipoAccreditamento.SPORTELLO
    ) {
      this.showProfessionistaForm = false;
      if (idIstanza !== 2 && idIstanza !== 3) {
        this.personalDataForm.get("fkTipoAccreditamento").reset();
      }
    } else if (
      this.personalDataForm.get("fkTipoAccreditamento").value ===
      TipoAccreditamento.GESTORE
    ) {
      this.showProfessionistaForm = false;
    } else {
      this.showProfessionistaForm = false;
    }

    if (idIstanza === 2 || idIstanza === 3) {
      this.getCategoriaProfessionale(1);
    }
    this.personalDataForm.updateValueAndValidity();
  }

  // IDF.
  onConfirmation() {
    sessionStorage.removeItem(CONST.SPORTELLO_PG_KEY_STORE);
    if (
      this.personalDataForm.value.fkTipoAccreditamento ===
      TipoAccreditamento.RICHIEDENTE
    ) {
      this.onlyRich.fkTipoAccreditamento =
        this.personalDataForm.value.fkTipoAccreditamento;
      this.onlyRich.tipoIstanzaId = this.personalDataForm.value.tipoIstanzaId;
    } else if (
      this.personalDataForm.valid &&
      this.personalDataForm.value.fkTipoAccreditamento ===
        TipoAccreditamento.PROFESSIONISTA
    ) {
      sessionStorage.setItem(
        CONST.CODICE_FISCALE_KEY_STORE,
        this.personalDataForm.value.codiceFiscaleDelega
      );
      this.onlyRich.fkTipoAccreditamento =
        this.personalDataForm.value.fkTipoAccreditamento;
      this.onlyRich.tipoIstanzaId = this.personalDataForm.value.tipoIstanzaId;
      this.onlyRich.provinciaOrdine =
        this.personalDataForm.value.provinciaOrdine.istatProv;
      if (this.personalDataForm.value.categoriaProfessionale != null) {
        this.onlyRich.categoriaProfessionale =
          this.personalDataForm.value.categoriaProfessionale.idCategoriaProfessionale;
      }
      this.onlyRich.partitaIva = this.personalDataForm.value.partitaIva;
      this.onlyRich.pec = this.personalDataForm.value.pec;
      this.onlyRich.numeroIscrizione =
        this.personalDataForm.value.numeroIscrizione;
      if (this.listDeleghe && this.listDeleghe.length > 0) {
        this.onlyRich.codiceFiscaleDelega = this.listDeleghe[0].cfDelegante;
      } else {
        this.showDialogMsg(
          "Attenzione",
          "Per operare come professionista serve avere ameno una delega"
        );
        return;
      }
    } else if (
      this.personalDataForm.value.fkTipoAccreditamento ===
      TipoAccreditamento.SPORTELLO
    ) {
      sessionStorage.setItem(
        CONST.SPORTELLO_PG_KEY_STORE,
        JSON.stringify(this.personalDataForm.value.sportello)
      );
      this.onlyRich.fkTipoAccreditamento =
        this.personalDataForm.value.fkTipoAccreditamento;
      this.onlyRich.tipoIstanzaId = this.personalDataForm.value.tipoIstanzaId;
      this.onlyRich.fkSoggettoSportello =
        this.personalDataForm.value.sportello.idSoggetto;
    } else if (
      this.personalDataForm.value.fkTipoAccreditamento ===
      TipoAccreditamento.GESTORE
    ) {
      //this.userRole='BO';
      this.tipoIstanza = 2;
      sessionStorage.setItem(
        CONST.TIPO_ISTANZA_ID_KEY_STORE,
        JSON.stringify(this.tipoIstanza)
      );
      this.onlyRich.fkTipoAccreditamento =
        this.personalDataForm.value.fkTipoAccreditamento;
      this.onlyRich.tipoIstanzaId = this.tipoIstanza;
      //return;
    }

    this.updateIdfistafor(this.onlyRich);
  }

  // IDF.
  updateIdfistafor(form: any) {
    let user: HomeModel = this.authService.currentUser();
    if (user.ruolo == ProfilioUtente.NO_DATA) {
      user.ruolo = ProfilioUtente.CITTADINO; // solo per chi non ha profilo settato
      sessionStorage.setItem(CONST.USER_KEY_STORE, JSON.stringify(user));
    }
    this.tipoIstanza = form.tipoIstanzaId;
    sessionStorage.setItem(
      CONST.TIPO_ISTANZA_ID_KEY_STORE,
      JSON.stringify(form.tipoIstanzaId)
    );

    this.forestaliService
      .putAdpfor(form)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (res) => {
          switch (form.tipoIstanzaId) {
            case 2: // Autorizzazione di taglio.
              // gestore sportello va in ricerca
              if (
                this.personalDataForm.value.fkTipoAccreditamento ===
                TipoAccreditamento.GESTORE
              ) {
                //this.userRole='BO';
                this.router.navigate(["ricerca-gestore-sportello-tagli"], {
                  relativeTo: this.route,
                });
              } else {
                this.router.navigate([`/visualizza-tagli`], {
                  relativeTo: this.route,
                });
              }

              break;
            case 1: // Trasformazione del bosco.
              this.router.navigate([`/visualizza`], { relativeTo: this.route });
              break;
            case 4: // Vincolo idrogeologico.
            case 5: // Vincolo idrogeologico.
              this.router.navigate([`/visualizza-vid`], {
                relativeTo: this.route,
              });
              break;
          }
        },
        (err) => {
          this.display = true;
        }
      );
  }

  onHide() {
    this.display = false;
    this.personalDataForm
      .get("codiceFiscaleDelega")
      .setErrors({ incorrect: true });
    this.personalDataForm.get("codiceFiscaleDelega").markAsTouched();
  }
  close() {
    this.display = false;
  }

  showDatiPersonali() {
    this.datiPersonaliForm = true;
  }

  setCompleted(event) {
    this.datiPersonaliForm = false;
    this.getHomeData();
  }

  getProvincia(event) {
    this.personalDataForm.get("provinciaOrdine").patchValue(undefined);
    this.forestaliService
      .getProvinciaItalia()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: ProvinciaModel[]) => {
        this.provincies = this.forestaliService.autocompleteFilter(
          event,
          res,
          "Provincia"
        );
      });
  }

  filterCategorieProfessionali(event: any) {
    this.vincoloService
      .getCategorieProfessionali()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: CategoriaProfessionale[]) => {
        this.categorieProfessionali = this.vincoloService.autocompleteFilter(
          event,
          res,
          "CategoriaProfessionale"
        );
      });
  }

  onChangeDropdownValue(event) {
    this.personalDataForm.get("provinciaOrdine").patchValue(event);
  }

  getCategoriaProfessionale(id: number) {
    this.vincoloService
      .getCategorieProfessionali()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: CategoriaProfessionale[]) => {
        this.categorieProfessionali = res.filter(
          (i) => i.idCategoriaProfessionale === id
        );
      });
  }
}
