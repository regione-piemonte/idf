/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Output, EventEmitter, Input } from "@angular/core";
import { takeUntil } from "rxjs/operators";
import { Subject } from "rxjs";

import { UserChoiceModel } from "src/app/shared/models/user-choice.model";
import { ForestaliService } from "../../../services/forestali.service";
import {
  DocumentoAllegato,
  PlainSestoSezione,
} from "src/app/shared/models/plain-sesto-sezione.model";
import { SaveFileService } from "src/app/shared/services/save-file.service";
import { IdfistaforHeaderHelper } from "../../../idfistafor-header-helper";
import { IstanzaInviata } from "src/app/shared/models/view-instance";
import { DialogService } from "./../../../../../shared/services/dialog.service";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { VincoloService } from "../../../services/vincolo.service";
import { HomeModel } from "src/app/shared/models/home.model";
import { CONST } from "src/app/shared/constants";

@Component({
  selector: "app-signature",
  templateUrl: "./signature.component.html",
  styleUrls: ["./signature.component.css"],
})
export class SignatureComponent implements OnInit {
  @Output() emitCompleted = new EventEmitter();
  tableData: DocumentoAllegato[];
  @Input() editMode: number;
  @Input() activeIndex: number;
  @Input() viewMode: boolean;
  @Input() boMode: boolean;
  @Input() istanzaInviata: IstanzaInviata;
  @Input() user: HomeModel = {};
  unsubscribe$ = new Subject<void>();
  tipoAccreditamento = null;
  isCompleted = false;
  dataInvio = null;
  docType = null;
  invioFinished = true;
  preparedDocuments: DocumentoAllegato[] = [];
  isDownloadSuccess = false;
  allegatiTableHeaders = IdfistaforHeaderHelper.getAllegatiTableHeaders();
  filePopUp: boolean;
  titolaritaPopUp: boolean;
  professionistiList;
  professionistiFilteredList;
  titolaritaForm: FormGroup;

  constructor(
    private forestaliService: ForestaliService,
    private saveFileService: SaveFileService,
    private dialogService: DialogService,
    private vincoloService: VincoloService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    if (!this.istanzaInviata) {
      this.istanzaInviata = {};
    }
    if (this.istanzaInviata && this.istanzaInviata.dataInvio) {
      this.emitCompleted.emit("true");
    }
    if (this.boMode === false) {
      this.forestaliService
        .getAdpfor()
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res: UserChoiceModel) => {
          this.tipoAccreditamento = res.fkTipoAccreditamento;
        });
    }
    this.isCompleted = this.activeIndex >= 6 ? true : false;
    this.loadDocuments();
  }

  setTitolarita() {
    switch (this.user.ruolo) {
      case CONST.ROLE_CITADINO || CONST.ROLE_CONSULTAZIONE: {
        return true;
      }
      default: {
        return false;
      }
    }
  }

  loadDocuments() {
    this.forestaliService
      .getAllDocuments(this.editMode)
      .subscribe((response: [DocumentoAllegato]) => {
        this.tableData = response;
        if (this.idDocPresent(2)) {
          this.docType = 2;
        } else if (this.idDocPresent(3)) {
          this.docType = 3;
        }
      });
  }

  changeDocType(docType) {
    this.docType = docType;
  }

  inserisciInElenco(event) {
    this.preparedDocuments.forEach((document) => {
      this.tableData.push(document);
    });
    this.preparedDocuments = [];
  }

  //FIX GP tck12b
  contieneCaracteresEspecialesItaliano(texto) {
    const caracteresEspecialesItaliano = /[àèéìíîòóùú!\"£$%&/()=,\?^*]/;
    return caracteresEspecialesItaliano.test(texto);
  }

  uploadImage(event, tipo) {
    if (tipo == 0) {
      if (!this.docType) {
        this.dialogService.showDialog(
          true,
          "Selezionare la tipologia di allegato!",
          "Attenzione",
          DialogButtons.OK,
          "",
          (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
              console.log("BUTTON WORKS");
            }
          }
        );
        return;
      } else {
        tipo = this.docType;
      }
    }
    //FIX GP tck12b

    console.log(
      "fileName",
      this.contieneCaracteresEspecialesItaliano(event.files[0].name)
    );
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
    if (
      this.forestaliService.isNamePresent(
        event.files[0].name,
        this.tableData ? this.tableData : []
      )
    ) {
      this.filePopUp = true;
    } else {
      this.filePopUp = false;
      this.forestaliService
        .uploadFileStep6(formData, this.editMode, tipo)
        .subscribe((response: any) => {
          if (response.error) {
            this.dialogService.showDialog(true, response.error, "Errore");
            return;
          }
          this.tableData.push(response);
        });
    }
    let cmpUpload = document.getElementById(
      "fileUpload_" + (tipo == 4 ? tipo : 0)
    );
    cmpUpload.getElementsByTagName("button")[2].click();
  }

  disableRadio(name: string, value: boolean) {
    let items = document.getElementsByName(name);
    Array.from(items).forEach((item) => (item["disabled"] = value));
  }

  isDichiarazioneUploaded() {
    console.log(
      "dataTable dim:" + (this.tableData ? this.tableData.length : null)
    );
    if (this.tableData && this.tableData.length > 1) {
      console.log(this.tableData);
    }
    for (let i in this.tableData ? this.tableData : []) {
      if (this.idDocPresent(2) || this.idDocPresent(3)) {
        this.disableRadio("radio-docType", true);
        return true;
      }
    }
    this.disableRadio("radio-docType", false);
    return false;
  }

  isInviaDisabled() {
    if (!this.docType) {
      return true;
    } else if (
      (this.docType == 2 && this.idDocPresent(2)) ||
      (this.docType == 3 && this.idDocPresent(3) && this.idDocPresent(4))
    ) {
      return false;
    }
    return true;
  }

  idDocPresent(tocType: number) {
    return (
      this.tableData.filter((elem) => elem.idTipoAllegato == tocType).length > 0
    );
  }

  submit(event) {
    this.forestaliService
      .postDataInvio(this.editMode, this.tableData)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          this.loadDocuments();
          this.forestaliService
            .getDataInvio(this.editMode)
            .pipe(takeUntil(this.unsubscribe$))
            .subscribe((response) => {
              this.dataInvio = response.dataInvio;
              this.isCompleted = true;
              this.invioFinished = false;
              this.emitCompleted.emit("true");
            });
        }
      });
  }

  fieldToDownload(event) {
    this.forestaliService
      .downloadStep6(event)
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

  downloadDichiarazione(event, tipoAllegato: number) {
    this.downloadDichiarazioneRec(event, tipoAllegato, 1);
  }

  downloadDichiarazioneRec(event, tipoAllegato: number, rip: number) {
    this.isDownloadSuccess = false;
    this.forestaliService
      .downloadDichiarazione(this.editMode, tipoAllegato)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response) => {
        if (response.status === 200) {
          //se il file è vuoto fa fino a 5 tentativi
          if (response.body.size > 5 || rip > 5) {
            let contentDisposition = response.headers.get(
              "content-disposition"
            );
            let filename = contentDisposition
              .split(";")[1]
              .split("filename")[1]
              .split("=")[1];
            this.saveFileService.saveFile(response, filename);
            this.isDownloadSuccess = true;
          } else {
            console.log(
              rip +
                " - Retry document download. Document size: " +
                response.body.size
            );
            this.downloadDichiarazioneRec(event, tipoAllegato, rip + 1);
          }
        }
      });
  }

  fieldToDelete(event, table) {
    if (table === "elenco") {
      this.forestaliService
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

  closeDialog() {
    this.filePopUp = false;
  }

  initTitolaritaForm() {
    this.titolaritaForm = new FormGroup({
      professionista: new FormControl("", [Validators.required]),
    });
  }

  cambiaTitolarita() {
    this.initTitolaritaForm();
    this.titolaritaPopUp = true;
  }

  filterProfessionista(event) {
    if (!this.professionistiList) {
      this.vincoloService
        .getProfessionistiList()
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe((res) => {
          this.professionistiList = res;
          this.professionistiFilteredList =
            this.forestaliService.autocompleteFilter(
              event,
              this.professionistiList,
              "Professionista"
            );
        });
    } else {
      this.professionistiFilteredList =
        this.forestaliService.autocompleteFilter(
          event,
          this.professionistiList,
          "Professionista"
        );
    }
  }

  salvaCambioTitolarita() {
    let soggetto = this.titolaritaForm.get("professionista").value;
    this.vincoloService
      .putTitolarita(this.editMode, soggetto.fkConfigUtente)
      .subscribe((res) => {
        this.istanzaInviata.utenteCompilatore = res.utenteCompilatore;
        this.titolaritaPopUp = false;
      });
  }

  closeTitolarita() {
    this.titolaritaPopUp = false;
  }
}
