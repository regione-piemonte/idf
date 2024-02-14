/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  OnDestroy,
  EventEmitter,
  Output,
  Input,
} from "@angular/core";
import { Subject, combineLatest } from "rxjs";

import { ForestaliService } from "../../../services/forestali.service";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import {
  DialogButtons,
  ButtonType,
} from "src/app/shared/error-dialog/error-dialog.component";
import { TagliService } from "src/app/features/istanze-forestali/services/tagli.service";
import { TagliStep5 } from "./tagli-step5.model";
import { SelectItem } from "primeng/api";
import { UserCompanyDataModel } from "src/app/shared/models/user-company-data.model";
import { CONST } from "src/app/shared/constants";
import { TipoAllegato } from "src/app/shared/models/tipo-allegato";
import { DialogService } from "src/app/shared/services/dialog.service";
import { SaveFileService } from "src/app/shared/services/save-file.service";
import { DocumentoAllegato } from "src/app/shared/models/plain-sesto-sezione.model";
import { TableHeader } from "src/app/shared/models/table-header";
import { TipoSelezionatoTipiAllegatoOnSelectDocumentes } from "src/app/shared/models/plain-sesto-sezione.model";
import { TipoIstanzaEnum } from "src/app/shared/models/tipo-istanza.enum";

@Component({
  selector: "step5-tagli",
  templateUrl: "./step5-tagli.component.html",
  styleUrls: ["./step5-tagli.component.css"],
})
export class Step5TagliComponent implements OnInit, OnDestroy {
  @Input() editMode: number;
  @Input() fifthFormData: TagliStep5;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;

  @Output() nextStepEmitter = new EventEmitter<void>();

  step5Form: FormGroup;
  personalOrCompanyForm: FormGroup;
  userCompanyData: UserCompanyDataModel;

  personaDatiOption = null;

  tipoAccreditamento = null;

  unsubscribe$ = new Subject<void>();
  isUpdate: boolean;
  codiceFiscaleSelectItem: SelectItem[] = [];

  tipiAllegatoOrig: TipoAllegato[] = [];
  tipiAllegato: TipoAllegato[] = [];
  tipoSelezionato: number;
  //FIX GP Documentos confirmacion firna no firma 160 o 161
  tipoSelezionatoTipiAllegatoOnSelectDocumentes: TipoSelezionatoTipiAllegatoOnSelectDocumentes[] =
    [];

  tableData: DocumentoAllegato[] = [];
  filteredTable: DocumentoAllegato[] = [];
  filePopUp: boolean;
  noteAllegato: string;
  noteMotivazione: string;
  motivazioneVisibile: boolean = false;
  noteFinaliRichiedente: string;

  mapInitTipiAllegato: any = {};
  mapTipiAllegatoFirma: any = {};
  mapTipiAllegatoFirmas: any = {};
  isDocWithFirma: boolean;
  versioneDoc: string;
  isDocRadioDisabled: boolean = true;
  tecnico_obbligatorio: boolean = false;
  tipoIstanzaIniziale: string;
  isBotonActivado = false;
  allegatiTableHeaders: TableHeader[] = [
    { field: "descrTipoAllegato", header: "Tipo allegato" },
    { field: "nomeAllegato", header: "Nome file" },
    { field: "note", header: "Note" },
    { field: "dimensioneAllegatoKB", header: "Kb" },
  ];

  tipoIstanzaEnum: any = TipoIstanzaEnum;

  constructor(
    private forestaliService: ForestaliService,
    private tagliService: TagliService,
    private fb: FormBuilder,
    private dialogService: DialogService,
    private saveFileService: SaveFileService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    //console.log("fifthFormData", this.fifthFormData);
    this.tagliService.getStepNumber(this.editMode).subscribe((res) => {
      this.isUpdate = res.nextStep > 4;
    });

    this.restartForm();

    //controllo se è comunicazione semplice ma è caricato un progetto di intervento t.c. rende tecnico forestale obbligatorio
    this.checkAllegatiCaricati();

    combineLatest(
      this.forestaliService.getAdpforByTipoIstanzaId(
        this.fifthFormData.tipoIstanzaId
      ),
      this.tagliService.getTipiAllegatoPfa()
    ).subscribe(([res, allegati]) => {
      //console.log(res);
      console.log(allegati);
      sessionStorage.setItem(
        CONST.TIPO_ISTANZA_ID_KEY_STORE,
        JSON.stringify(res.tipoIstanzaId)
      );
      this.personaDatiOption = "P";
      this.tipoAccreditamento = res.fkTipoAccreditamento;
      //console.log(allegati);
      //gestione allegati duplicati per versione digitale
      allegati.forEach((item) => {
        let parts = item.descrTipoAllegato.split(" – ");
        // console.log(allegati);
        //  console.log(parts);
        if (!this.mapInitTipiAllegato[parts[0]]) {
          this.tipiAllegato.push(item);
          this.mapInitTipiAllegato[parts[0]] = item.idTipoAllegato;
        }
        if (parts.length == 2) {
          this.mapTipiAllegatoFirma[this.mapInitTipiAllegato[parts[0]]] = {
            firma: item.idTipoAllegato,
          };
        }
      });

      allegati = this.tipiAllegato;

      //fine

      // exclude tipi allegato with id in TIPO_ALLEGATO_EXLUDE_IDS
      this.tipiAllegatoOrig = allegati.filter(
        (el) => !CONST.TIPO_ALLEGATO_EXLUDE_IDS.includes(el.idTipoAllegato)
      );

      if (this.fifthFormData.noteFinaliRichiedente) {
        this.noteFinaliRichiedente = this.fifthFormData.noteFinaliRichiedente;
        console.log(
          this.fifthFormData.noteFinaliRichiedente + "---FIFTH FROM DATA---"
        );
      }

      if (this.fifthFormData.tecnicoForestale) {
        this.personalOrCompanyForm.patchValue(
          this.fifthFormData.tecnicoForestale
        );
      }

      if (this.fifthFormData.allegati) {
        this.tableData = this.fifthFormData.allegati;
        this.filteredTable = [...this.tableData];

        this.filteredTable.map((item) => {
          let TmpObjTipopAllegato = {
            idTipoAllegato: item.idTipoAllegato,
            descrTipoAllegato: item.descrTipoAllegato,
            flgObbligatorio: CONST.TIPO_ALLEGATO_OBBLIGATORIO_IDS_OBL.includes(
              item.idTipoAllegato
            ),
          };

          // Encuentra el índice del objeto duplicado dentro de this.tipiAllegato
          const index = this.tipiAllegato.findIndex(
            (item) => item.idTipoAllegato === TmpObjTipopAllegato.idTipoAllegato
          );

          // Verifica si el objeto duplicado fue encontrado
          if (index !== -1) {
            // Reemplaza el objeto duplicado con el nuevo objeto
            this.tipiAllegato[index] = TmpObjTipopAllegato;
            console.log("Objeto duplicado reemplazado.");
          } else {
            this.tipiAllegato.push(TmpObjTipopAllegato);
            console.log("filteredTable : ", this.tipiAllegato);
          }

          let TmpObj = {
            idTipoAllegato: item.idTipoAllegato,
            idDocumentoAllegato: item.idDocumentoAllegato,
            flgObbligatorio: CONST.TIPO_ALLEGATO_OBBLIGATORIO_IDS_OBL.includes(
              item.idTipoAllegato
            ),
          };
          this.tipoSelezionatoTipiAllegatoOnSelectDocumentes.push(TmpObj);
          localStorage.setItem(
            "this.tipoSelezionatoTipiAllegatoOnSelectDocumentesLocal",
            JSON.stringify(this.tipoSelezionatoTipiAllegatoOnSelectDocumentes)
          );
          console.log(
            "local : ",
            JSON.parse(
              localStorage.getItem(
                "this.tipoSelezionatoTipiAllegatoOnSelectDocumentesLocal"
              )
            )
          );
        });
      }

      this.buildTipiAllegato();
    });

    this.tipoIstanzaIniziale = this.fifthFormData.tipoIstanza;
    console.log(this.fifthFormData.motivazione);
    //Inizializza campo motivazione nella fase iniziale se è presente
    if (this.fifthFormData.motivazione) {
      this.motivazioneVisibile = true;
      this.noteMotivazione = this.fifthFormData.motivazione;
    }
  }

  setIstanza() {
    localStorage.setItem("tipoIstanza", this.fifthFormData.tipoIstanza);
  }

  checkAllegatiCaricati() {
    this.tecnico_obbligatorio = false;

    if (this.fifthFormData.allegati != undefined) {
      this.fifthFormData.allegati.forEach((alleg) => {
        console.log("idTipoAllegato: ", alleg.idTipoAllegato);
        if (
          [
            CONST.NUMERO_ALLEGATO_PROG_INTERVENTO_REF2,
            CONST.NUMERO_ALLEGATO_PROG_INTERVENTO,
          ].includes(alleg.idTipoAllegato)
        ) {
          this.tecnico_obbligatorio = true;
          return;
        }
      });
    }
  }

  onTipoAllegatoChange(idTipoAllegato) {
    this.versioneDoc = "";
    //console.log("codigo  de Allegato frima o no: ", idTipoAllegato);
    this.tipoSelezionato = idTipoAllegato;
    //console.log("codigo  mapTipiAllegatoFirma: ", this.mapTipiAllegatoFirma);
    localStorage.setItem("idTipoAllegatoLocal", idTipoAllegato);
    if (!this.mapTipiAllegatoFirma[this.tipoSelezionato]) {
      this.isDocRadioDisabled = false;
      //this.versioneDoc = "noFirma";
    } else {
      //this.versioneDoc = "noFirma";
      this.isDocRadioDisabled = false;
    }
  }

  changeDocType(versioneDoc) {
    this.versioneDoc = versioneDoc;
    if (versioneDoc === "firma") {
      this.tagliService
        .getAllTipoAllegatoTipoById(this.tipoSelezionato)
        .subscribe((res) => {
          this.mapTipiAllegatoFirmas = res;
          this.tipoSelezionato = this.mapTipiAllegatoFirmas.idTipoAllegato;
          //console.log(" this.tipoSelezionato: ", this.tipoSelezionato);
        });
      this.versioneDoc = "firma";
    } else {
      console.log(
        " this.tipoSelezionato: ",
        parseInt(localStorage.getItem("idTipoAllegatoLocal"))
      );
      this.tipoSelezionato = parseInt(
        localStorage.getItem("idTipoAllegatoLocal")
      );
      this.versioneDoc = "noFirma";
    }
  }

  //FIX GP tck12b
  contieneCaracteresEspecialesItaliano(texto) {
    const caracteresEspecialesItaliano = /[àèéìíîòóùú!\"£$%&/()=,\?^*]/;
    return caracteresEspecialesItaliano.test(texto);
  }

  uploadFile(event) {
    if (!this.tipoSelezionato) {
      this.showDialogMsg(
        "Attenzione",
        "Selezionare il tipo di allegato che si stà caricando"
      );
      return;
    }
    console.log(
      "fileName",
      this.contieneCaracteresEspecialesItaliano(event.files[0].name)
    );
    //FIX GP tck12b
    if (this.contieneCaracteresEspecialesItaliano(event.files[0].name)) {
      this.dialogService.showDialog(
        true,
        "I caratteri speciali non sono accettati nel nome del file. Modificare il nome del file",
        "Attenzione"
      );
      return;
    }
    const formData: FormData = new FormData();
    formData.append("form", event.files[0]);
    formData.append("fileName", event.files[0].name);
    if (this.noteAllegato) {
      formData.append("note", this.noteAllegato);
    }
    formData.append("tipoAllegato", "" + this.tipoSelezionato);

    if (this.tagliService.isTypePresent(this.tipoSelezionato, this.tableData)) {
      this.showDialogMsg(
        "Attenzione",
        "Questo tipo di file è già stato caricato"
      );
    } else {
      this.tagliService
        .uploadAllegato(formData, this.editMode, this.tipoSelezionato)
        .subscribe((response: any) => {
          if (response.error) {
            this.dialogService.showDialog(true, response.error, "Errore");
            this.tecnico_obbligatorio = false;
            return;
          }
          this.tableData.push(response);
          this.filteredTable.push(response);
          this.noteAllegato = null;
          // 777 AbiSoft
          if (
            [
              CONST.NUMERO_ALLEGATO_PROG_INTERVENTO_REF2,
              CONST.NUMERO_ALLEGATO_PROG_INTERVENTO,
            ].includes(this.tipoSelezionato)
          ) {
            this.tecnico_obbligatorio = true;
          }
          console.log("uploadAllegato: ", response);
          console.log(
            "uploadAllegato: ",
            CONST.TIPO_ALLEGATO_OBBLIGATORIO_IDS_OBL.includes(
              response.idTipoAllegato
            )
          );
          let TmpObj = {
            idTipoAllegato: response.idTipoAllegato,
            idDocumentoAllegato: response.idDocumentoAllegato,
            flgObbligatorio: CONST.TIPO_ALLEGATO_OBBLIGATORIO_IDS_OBL.includes(
              response.idTipoAllegato
            ),
          };
          this.tipoSelezionatoTipiAllegatoOnSelectDocumentes.push(TmpObj);
          localStorage.setItem(
            "this.tipoSelezionatoTipiAllegatoOnSelectDocumentesLocal",
            JSON.stringify(this.tipoSelezionatoTipiAllegatoOnSelectDocumentes)
          );
        });
    }
    let cmpUpload = document.getElementById("fileUpload");
    cmpUpload.getElementsByTagName("button")[2].click();
  }

  checkAllegati() {
    let missing = 0;
    let tipoSelezionatoTipiAllegatoOnSelectDocumentesTmp = JSON.parse(
      localStorage.getItem(
        "this.tipoSelezionatoTipiAllegatoOnSelectDocumentesLocal"
      )
    );
    console.log("checkAllegati----> ", this.tipiAllegato);
    this.tipiAllegato
      .filter((e) => e.flgObbligatorio)
      .forEach((el) => {
        /*
        if (
          !this.filteredTable.some(
            (t) => t.idTipoAllegato === el.idTipoAllegato
          )
        ) {
          missing++;
        }*/
        const filteredTableIds = this.filteredTable.map(
          (item) => item.idTipoAllegato
        );
        if(!filteredTableIds.includes(el.idTipoAllegato)){
          missing++
        }
        /* const tipoSelezionatoTipiAllegatoOnSelectDocumentesIds =
          tipoSelezionatoTipiAllegatoOnSelectDocumentesTmp.map(
            (item) => item.idTipoAllegato
          );
          console.log(tipoSelezionatoTipiAllegatoOnSelectDocumentesIds);  
        const trueElements =
          tipoSelezionatoTipiAllegatoOnSelectDocumentesTmp.filter(
            (item) => item.flgObbligatorio === true
          );
          console.log(trueElements);
        const isObliga = trueElements.length;
        console.log("Cantidad de true:", isObliga);

        const isSame =
          filteredTableIds.length ===
            tipoSelezionatoTipiAllegatoOnSelectDocumentesIds.length &&
          filteredTableIds.every(
            (id) =>
              tipoSelezionatoTipiAllegatoOnSelectDocumentesIds.includes(id) &&
              isObliga >= 2
          );
          console.log(isSame); */
          
        /* if (isSame) {
          console.log("Los objetos tienen los mismos idTipoAllegato.");
          missing = 0;
        } else {
          console.log(
            "Los objetos no tienen los mismos idTipoAllegato o los arreglos no son iguales."
          );
          missing++;
        } */
      });

    if (missing > 0) {
      return "Mancano " + missing + " documenti obbligatori";
    } else {
      console.log(
        "checkAllegatithis.tipoSelezionatoTipiAllegatoOnSelectDocumentes :",
        tipoSelezionatoTipiAllegatoOnSelectDocumentesTmp
      );
    }
    return null;
  }

  private checkTecnicoForestale() {
    // obbligatorio se caricato il documento "Progetto di intervento"
    if (this.isTecnicoForestaleObbligatorio()) {
      this.addValidators();

      if (this.personalOrCompanyForm.invalid) {
        return "Il tecnico Forestale è obbligatorio";
      }
    } else {
      // if some values....
      // require all values
      let nullFields = [];
      nullFields.push(!this.personalOrCompanyForm.get("codiceFiscale").value);
      nullFields.push(!this.personalOrCompanyForm.get("cognome").value);
      nullFields.push(!this.personalOrCompanyForm.get("nome").value);
      nullFields.push(!this.personalOrCompanyForm.get("partitaIva").value);
      // nullFields.push(!this.personalOrCompanyForm.get("comune").value);
      nullFields.push(!this.personalOrCompanyForm.get("cap").value);
      nullFields.push(!this.personalOrCompanyForm.get("indirizzo").value);
      nullFields.push(!this.personalOrCompanyForm.get("civico").value);
      nullFields.push(!this.personalOrCompanyForm.get("pec").value);
      nullFields.push(!this.personalOrCompanyForm.get("eMail").value);
      nullFields.push(!this.personalOrCompanyForm.get("nrTelefonico").value);
      nullFields.push(
        !this.personalOrCompanyForm.get("nrIscrizioneOrdine").value
      );
      // nullFields.push(
      //   !this.personalOrCompanyForm.get("provIscrizioneOrdine").value
      // );
      /* nullFields.push(
        !this.personalOrCompanyForm.get("nrMartelloForestale").value
      ); */

      // se tutti nulli, OK
      let allNull = nullFields.every(Boolean);
      // this.personalOrCompanyForm.controls['comune'].setValue(null);
      // this.personalOrCompanyForm.controls['provIscrizioneOrdine'].setValue(null);
      // this.personalOrCompanyForm.controls['idSoggetto'].setValue(null);
      // this.personalOrCompanyForm.reset();
      if (allNull) {
        this.personalOrCompanyForm.controls["idSoggetto"].setValue(null); //Per Settare a null campo db
        this.personalOrCompanyForm.reset();
        return null;
      }
      nullFields.push(!this.personalOrCompanyForm.get("comune").value);
      nullFields.push(
        !this.personalOrCompanyForm.get("provIscrizioneOrdine").value
      );
      // se tutti sono valorizzati, OK
      let allNotNull = nullFields.every((e) => !e);
      if (allNotNull) return null;

      // se solo alcuni sono valorizzati
      //tutti vuoti ok, se qualcuno valorizzato KO
      this.addValidators();
      return "Tutti i dati del tecnico Forestale sono obbligatori";
    }

    return null;
  }

  private addValidators() {
    this.personalOrCompanyForm
      .get("codiceFiscale")
      .setValidators([Validators.required, Validators.maxLength(16)]);
    this.personalOrCompanyForm
      .get("partitaIva")
      .setValidators([Validators.required, Validators.maxLength(11)]);
    this.personalOrCompanyForm
      .get("cap")
      .setValidators([
        Validators.required,
        Validators.pattern(CONST.PATTERN_CAP),
      ]);
    this.personalOrCompanyForm
      .get("comune")
      .setValidators([Validators.required]);
    this.personalOrCompanyForm
      .get("indirizzo")
      .setValidators([Validators.required, Validators.maxLength(50)]);
    this.personalOrCompanyForm
      .get("civico")
      .setValidators([Validators.required, Validators.maxLength(20)]);
    this.personalOrCompanyForm
      .get("pec")
      .setValidators([
        Validators.required,
        Validators.pattern(CONST.PATTERN_MAIL),
        Validators.maxLength(200),
      ]);
    this.personalOrCompanyForm
      .get("eMail")
      .setValidators([
        Validators.required,
        Validators.pattern(CONST.PATTERN_MAIL),
        Validators.maxLength(100),
      ]);
    this.personalOrCompanyForm
      .get("nrTelefonico")
      .setValidators([
        Validators.required,
        Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
        Validators.maxLength(20),
      ]);
    this.personalOrCompanyForm
      .get("cognome")
      .setValidators([Validators.required, Validators.maxLength(100)]);
    this.personalOrCompanyForm
      .get("nome")
      .setValidators([Validators.required, Validators.maxLength(100)]);
    this.personalOrCompanyForm
      .get("nrIscrizioneOrdine")
      .setValidators([Validators.required]);
    this.personalOrCompanyForm
      .get("provIscrizioneOrdine")
      .setValidators([Validators.required]);
   /*  this.personalOrCompanyForm
      .get("nrMartelloForestale")
      .setValidators([Validators.required]); */

    this.personalOrCompanyForm.get("codiceFiscale").updateValueAndValidity();
    this.personalOrCompanyForm.get("partitaIva").updateValueAndValidity();
    this.personalOrCompanyForm.get("cap").updateValueAndValidity();
    this.personalOrCompanyForm.get("comune").updateValueAndValidity();
    this.personalOrCompanyForm.get("indirizzo").updateValueAndValidity();
    this.personalOrCompanyForm.get("civico").updateValueAndValidity();
    this.personalOrCompanyForm.get("pec").updateValueAndValidity();
    this.personalOrCompanyForm.get("eMail").updateValueAndValidity();
    this.personalOrCompanyForm.get("nrTelefonico").updateValueAndValidity();
    this.personalOrCompanyForm.get("cognome").updateValueAndValidity();
    this.personalOrCompanyForm.get("nome").updateValueAndValidity();
    this.personalOrCompanyForm
      .get("nrIscrizioneOrdine")
      .updateValueAndValidity();
    this.personalOrCompanyForm
      .get("provIscrizioneOrdine")
      .updateValueAndValidity();
    this.personalOrCompanyForm
      .get("nrMartelloForestale")
      .updateValueAndValidity();
  }

  isTecnicoForestaleObbligatorio() {
    return this.filteredTable.some((el) =>
      el.descrTipoAllegato.includes(CONST.TIPO_ALLEGATO_PROG_INTERVENTO_DESC)
    );
  }

  closeDialog() {
    this.filePopUp = false;
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

  private buildTipiAllegato() {
    /*
        I tagli di maturità della fustaia sono quelli con ID_TIPO_INTERVENTO = 1,2,3,4
     */

    const tagliFustaiaIds = CONST.TAGLI_FUSTAIA_IDS;
    const tp1 = this.fifthFormData.intervSelvicolturale.fkTipoIntervento;
    const tp2 = this.fifthFormData.intervSelvicolturale.fkTipoIntervento2;

    if (tagliFustaiaIds.includes(tp1) || tagliFustaiaIds.includes(tp2)) {
      this.tipiAllegato = this.tipiAllegatoOrig.slice();
    } else {
      this.tipiAllegato = this.tipiAllegatoOrig
        .filter(
          (e) => e.descrTipoAllegato !== CONST.TIPO_ALLEGATO_PIEDILISTA_DESC
        )
        .slice();
    }

    this.tipiAllegato
      .filter(
        (t) => !t.descrTipoAllegato.includes(CONST.TIPO_ALLEGATO_LIBERO_DESC)
      )
      .map((i) => {
        i.flgObbligatorio =
          this.fifthFormData.tipoIstanza === TipoIstanzaEnum.AUTORIZZAZIONE &&
          CONST.TIPO_ALLEGATO_OBBLIGATORIO_IDS.indexOf(i.idTipoAllegato) > -1;
        console.log(" i.flgObbligatorio: S", i.flgObbligatorio);

        if (
          this.fifthFormData.tipoIstanza === TipoIstanzaEnum.AUTORIZZAZIONE ||
          this.fifthFormData.tipoIstanza === TipoIstanzaEnum.COMUNICAZIONE
        ) {
          this.isBotonActivado = true;
        } else {
          this.isBotonActivado = false;
        }
      });
  }

  private initForm(userCompanyData) {
    localStorage.clear();
    this.personalOrCompanyForm = this.fb.group({
      idSoggetto: [
        {
          value: userCompanyData ? userCompanyData.idSoggetto : null,
          disabled: false,
        },
      ],
      codiceFiscale: [
        {
          value: userCompanyData ? userCompanyData.codiceFiscale : null,
          disabled: false,
        },
      ],
      partitaIva: [
        {
          value: userCompanyData ? userCompanyData.partitaIva : null,
          disabled: false,
        },
      ],
      comune: [
        {
          value: userCompanyData ? userCompanyData.comune : null,
          disabled: false,
        },
      ],
      cap: [
        {
          value: userCompanyData ? userCompanyData.cap : null,
          disabled: false,
        },
      ],
      indirizzo: [
        {
          value: userCompanyData ? userCompanyData.indirizzo : null,
          disabled: false,
        },
      ],
      civico: [
        {
          value: userCompanyData ? userCompanyData.civico : null,
          disabled: false,
        },
      ],
      pec: [
        {
          value: userCompanyData ? userCompanyData.pec : null,
          disabled: false,
        },
      ],
      eMail: [
        {
          value: userCompanyData ? userCompanyData.eMail : null,
          disabled: false,
        },
      ],
      nrTelefonico: [
        {
          value: userCompanyData ? userCompanyData.nrTelefonico : null,
          disabled: false,
        },
      ],
      nome: [
        {
          value: userCompanyData ? userCompanyData.nome : null,
          disabled: false,
        },
      ],
      cognome: [
        {
          value: userCompanyData ? userCompanyData.cognome : null,
          disabled: false,
        },
      ],
      nrIscrizioneOrdine: [
        {
          value: userCompanyData ? userCompanyData.nrIscrizioneOrdine : null,
          disabled: false,
        },
      ],
      provIscrizioneOrdine: [
        {
          value: userCompanyData ? userCompanyData.provIscrizioneOrdine : null,
          disabled: false,
        },
      ],
      nrMartelloForestale: [
        {
          value: userCompanyData ? userCompanyData.nrMartelloForestale : null,
          disabled: false,
        },
      ],
    });
  }

  save(nextStep?: boolean) {
    let errorAllegati = this.checkAllegati();
    if (errorAllegati != null) {
      this.showDialogMsg("Attenzione!", errorAllegati);
      return;
    }

    let errorTecnicoForestale = this.checkTecnicoForestale();
    if (errorTecnicoForestale != null) {
      this.showDialogMsg("Attenzione!", errorTecnicoForestale);
      return;
    }

    const sendForm: TagliStep5 = {};
    sendForm.intervento = {};
    sendForm.tecnicoForestale = this.personalOrCompanyForm.getRawValue();
    sendForm.tipoIstanza = this.fifthFormData.tipoIstanza;
    sendForm.motivazione = this.noteMotivazione;
    if (this.motivazioneVisibile && sendForm.motivazione == null) {
      this.showDialogMsg("Attenzione!", "il campo Motivazione è obbligatorio");
      return;
    }
    sendForm.noteFinaliRichiedente = this.noteFinaliRichiedente;

    //change Tipologia
    this.tagliService
      .updateTipologiaIstanza(this.editMode, sendForm)
      .subscribe((res) => {
        //this.fifthFormData = res;
        this.tipiAllegato = [];
        this.buildTipiAllegato();
      });

    this.tagliService.postStep5(sendForm, this.editMode).subscribe((res) => {
      console.log("====================================");
      console.log("RESPONSE save step5 ", res);
      console.log("====================================");
      if (nextStep) {
        this.nextStepEmitter.emit();
      }
    });
  }

  continue() {
    this.save(true);
  }

  getRowId(event) {}
  getUpdatedTable(event) {}
  deleteRow(event) {}

  getCodiceFiscale() {}

  restartForm() {
    this.initForm(this.userCompanyData);
  }

  clearAll(event: any) {
    this.initForm(event);
  }

  fieldToDelete(event, _table) {
    this.tagliService
      .deleteDocumentoAllegato(event.rowIndex)
      .subscribe((res) => {
        this.tableData.forEach((item, _index) => {
          if (item.idDocumentoAllegato === event.rowIndex) {
            item.deleted = true;
            this.filteredTable.splice(event.index, 1);
            this.tableData.splice(event.index, 1);

            // FIX GP
            let tipoSelezionatoTipiAllegatoOnSelectDocumentesTmpDelete =
              JSON.parse(
                localStorage.getItem(
                  "this.tipoSelezionatoTipiAllegatoOnSelectDocumentesLocal"
                )
              );
            const index =
              tipoSelezionatoTipiAllegatoOnSelectDocumentesTmpDelete.findIndex(
                (item) => item.idDocumentoAllegato === event.rowIndex
              );
            if (index !== -1) {
              tipoSelezionatoTipiAllegatoOnSelectDocumentesTmpDelete.splice(
                index,
                1
              );
              localStorage.setItem(
                "this.tipoSelezionatoTipiAllegatoOnSelectDocumentesLocal",
                JSON.stringify(
                  tipoSelezionatoTipiAllegatoOnSelectDocumentesTmpDelete
                )
              );
            }
          }
        });
        this.checkAllegatiCaricati();
      });
  }

  fieldToDownload(event) {
    this.tagliService.downloadAllegato(event).subscribe((response) => {
      let contentDisposition = response.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];
      this.saveFileService.saveFile(response, filename);
    });
  }

  motivazioneChange() {
    if (
      this.noteMotivazione != null &&
      this.noteMotivazione != undefined &&
      this.noteMotivazione.trim().length != 0
    ) {
      this.isBotonActivado = true;
    } else {
      this.isBotonActivado = false;
    }
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  changeTipologia() {
    console.log("cambia" + this.tipoIstanzaIniziale);

    if (this.fifthFormData.motivazione) {
      this.motivazioneVisibile = true;
    }

    if (this.fifthFormData.tipoIstanza === TipoIstanzaEnum.COMUNICAZIONE) {
      this.fifthFormData.tipoIstanza = TipoIstanzaEnum.AUTORIZZAZIONE;
      if (this.fifthFormData.motivazione) {
        this.motivazioneVisibile = true;
      } else
        this.motivazioneVisibile =
          this.tipoIstanzaIniziale == this.tipoIstanzaEnum.COMUNICAZIONE
            ? true
            : false;
    } else {
      this.fifthFormData.tipoIstanza = TipoIstanzaEnum.COMUNICAZIONE;
      if (this.fifthFormData.motivazione) {
        this.motivazioneVisibile = true;
      } else
        this.motivazioneVisibile =
          this.tipoIstanzaIniziale == this.tipoIstanzaEnum.AUTORIZZAZIONE
            ? true
            : false;
    }

    const sendForm: TagliStep5 = {};
    sendForm.intervento = {};
    sendForm.tecnicoForestale = {};
    sendForm.tipoIstanza = this.fifthFormData.tipoIstanza;

    this.buildTipiAllegato();
    // change tipologia
    // this.tagliService
    //   .updateTipologiaIstanza(this.editMode, sendForm)
    //   .subscribe((res) => {
    //     //this.fifthFormData = res;
    //     this.tipiAllegato = [];
    //     this.buildTipiAllegato();
    //   });

    if (!this.motivazioneVisibile) {
      this.noteMotivazione = null;
    }
    this.isBotonActivado = !this.isBotonActivado;
  }
}
