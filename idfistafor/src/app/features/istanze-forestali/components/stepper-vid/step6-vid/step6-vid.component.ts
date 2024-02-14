/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, EventEmitter, Output } from "@angular/core";
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormControl,
} from "@angular/forms";
import { TableHeader } from "src/app/shared/models/table-header";
import {
  PlainSestoSezione,
  DocumentoAllegato,
  IstanzaTaglio,
} from "src/app/shared/models/plain-sesto-sezione.model";
import { ForestaliService } from "../../../services/forestali.service";
import { SaveFileService } from "src/app/shared/services/save-file.service";
import { CONST } from "src/app/shared/constants";
import { Subject } from "rxjs";
import { takeUntil } from "rxjs/operators";
import { VincoloService } from "../../../services/vincolo.service";
import { VincoloStep6 } from "./vincoloStep6";
import { DialogService } from "src/app/shared/services/dialog.service";
import { TipoAllegato } from "src/app/shared/models/tipo-allegato";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";

@Component({
  selector: "step6-vid",
  templateUrl: "./step6-vid.component.html",
  styleUrls: ["./step6-vid.component.css"],
})
export class Step6VidComponent implements OnInit {
  @Input() editMode: number;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;
  @Output() emitActiveIndex = new EventEmitter<void>();
  dichiarazioniForm: FormGroup;
  invalid = true;
  unsubscribe$ = new Subject<void>();

  isProroga = false;
  marca: number;
  tipiAllegato: TipoAllegato[] = [];
  mapInitTipiAllegato: any = {};
  mapTipiAllegatoFirma: any = {};
  isDocWithFirma: boolean;
  versioneDoc: string;
  isDocRadioDisabled: boolean = false;

  tableData: DocumentoAllegato[] = [];
  filteredTable: DocumentoAllegato[] = [];
  filePopUp: boolean;
  tipoSelezionato: number;
  noteAllegato: string = "note";

  allegatiTableHeaders: TableHeader[] = [
    { field: "descrTipoAllegato", header: "Tipo allegato" },
    { field: "nomeAllegato", header: "Nome file" },
    // { field: "note", header: "Note" },
    { field: "dimensioneAllegatoKB", header: "Kb" },
  ];

  constructor(
    private vincoloService: VincoloService,
    private fb: FormBuilder,
    private dialogService: DialogService,
    private saveFileService: SaveFileService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    this.vincoloService
      .getInfoVarianteProroga(this.editMode)
      .subscribe((res) => {
        this.isProroga = res.idPadreProroga ? true : false;
        this.initForm();
      });
    this.getTipiAllegatoByIdIntervento();
    this.getValoreMarcaBollo();
    //caricamento
    this.vincoloService
      .getStep6(this.editMode)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        if (res != null) {
          if (res.flagProprieta) {
            this.dichiarazioniForm.patchValue({
              flagProprieta: res.flagProprieta ? "true" : "false",
            });
            this.dichiarazioniForm.patchValue({
              flagDissensi: res.flagDissensi,
            });
            this.dichiarazioniForm.patchValue({
              flagImporto: res.flagImporto ? "true" : "false",
            });
            this.dichiarazioniForm.patchValue({
              flagCopiaConforme: res.flagCopiaConforme,
            });
            this.dichiarazioniForm.patchValue({
              flagConfServizi: res.flagConfServizi,
            });
            this.dichiarazioniForm.patchValue({ flagSuap: res.flagSuap });
            this.dichiarazioniForm.patchValue({ annotazioni: res.annotazioni });
            this.dichiarazioniForm.patchValue({
              flagSpeseIstruttoria: res.flagSpeseIstruttoria,
            });
            this.dichiarazioniForm.patchValue({
              flagEsenzioneMarcaBollo: res.flagEsenzioneMarcaBollo
                ? "true"
                : "false",
            });
            this.dichiarazioniForm.patchValue({ nMarcaBollo: res.nMarcaBollo });
          }
          this.marcaBolloChange(res.flagEsenzioneMarcaBollo ? true : false);
          this.tableData = res.documentiAllegati ? res.documentiAllegati : [];
          this.filteredTable = [...this.tableData];
          //
        } else {
          //
        }
      });
  }

  initForm() {
    this.dichiarazioniForm = new FormGroup({
      flagProprieta: new FormControl("", [Validators.required]),
      flagDissensi: new FormControl(""),
      flagImporto: new FormControl("", [Validators.required]),
      flagCopiaConforme: new FormControl(""),
      flagConfServizi: new FormControl(""),
      flagSuap: new FormControl(""),
      annotazioni: new FormControl(""),
      flagSpeseIstruttoria: new FormControl(
        "",
        this.isProroga ? [] : [Validators.requiredTrue]
      ),
      flagEsenzioneMarcaBollo: new FormControl("", [Validators.required]),
      nMarcaBollo: new FormControl("", [
        Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO),
        Validators.minLength(14),
      ]),
    });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  disableFields(fields: string[]) {
    fields.forEach((item, i) => {
      this.dichiarazioniForm.get(item).disable();
    });
  }

  enableFields(fields: string[]) {
    fields.forEach((item, i) => {
      this.dichiarazioniForm.get(item).enable();
    });
  }

  getValoreMarcaBollo() {
    this.vincoloService.getValoreMarcaBollo().subscribe((response: any) => {
      this.marca = response;
    });
  }

  onTipoAllegatoChange(idTipoAllegato) {
    this.tipoSelezionato = idTipoAllegato;
    if (!this.mapTipiAllegatoFirma[this.tipoSelezionato]) {
      this.isDocRadioDisabled = true;
      this.versioneDoc = "noFirma";
    } else {
      this.isDocRadioDisabled = false;
    }
  }

  getTipiAllegatoByIdIntervento() {
    this.vincoloService
      .getTipiAllegatoByIdIntervento(this.editMode)
      .subscribe((response: any) => {
        response.forEach((item) => {
          let parts = item.descrTipoAllegato.split(" - ");
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
      });
  }

  changeDocType(versioneDoc) {
    this.versioneDoc = versioneDoc;
  }

  continue() {
    this.save(true);
  }

  disableSave() {
    return (
      this.checkAllegati() != null ||
      !this.dichiarazioniForm ||
      this.dichiarazioniForm.invalid
    );
  }

  save(nextStep?: boolean) {
    let errorAllegati = this.checkAllegati();
    if (this.dichiarazioniForm.valid && errorAllegati == null) {
      let vincolo = new VincoloStep6();
      vincolo.flagProprieta = this.dichiarazioniForm.get("flagProprieta").value;
      vincolo.flagDissensi = this.dichiarazioniForm.get("flagDissensi").value;
      vincolo.flagImporto = this.dichiarazioniForm.get("flagImporto").value;
      vincolo.flagCopiaConforme =
        this.dichiarazioniForm.get("flagCopiaConforme").value;
      vincolo.flagConfServizi =
        this.dichiarazioniForm.get("flagConfServizi").value;
      vincolo.flagSuap = this.dichiarazioniForm.get("flagSuap").value;
      vincolo.annotazioni = this.dichiarazioniForm.get("annotazioni").value;
      vincolo.flagSpeseIstruttoria = this.dichiarazioniForm.get(
        "flagSpeseIstruttoria"
      ).value;
      vincolo.flagEsenzioneMarcaBollo = this.dichiarazioniForm.get(
        "flagEsenzioneMarcaBollo"
      ).value;
      vincolo.nMarcaBollo = this.dichiarazioniForm.get("nMarcaBollo").value;

      this.vincoloService
        .postStep6(vincolo, this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(() => {
          if (nextStep) this.emitActiveIndex.emit();
        });
    } else {
      if (
        this.dichiarazioniForm.get("flagEsenzioneMarcaBollo").value ==
          "false" &&
        this.dichiarazioniForm.get("nMarcaBollo").value.length == 0
      ) {
        this.showDialogMsg(
          "Attenzione!",
          "Inserire il numero della marca da bollo!"
        );
      } else if (errorAllegati != null) {
        this.showDialogMsg("Attenziona!", errorAllegati);
      }
    }
  }

  fieldToDownload(event) {
    this.vincoloService.downloadAllegato(event).subscribe((response) => {
      let contentDisposition = response.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];
      this.saveFileService.saveFile(response, filename);
    });
  }

  fieldToDelete(event, table) {
    this.vincoloService
      .deleteDocumentoAllegato(event.rowIndex)
      .subscribe((res) => {
        this.tableData.forEach((item, index) => {
          if (item.idDocumentoAllegato === event.rowIndex) {
            item.deleted = true;
            this.filteredTable.splice(event.index, 1);
            this.tableData.splice(event.index, 1);
          }
        });
      });
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
    // formData.append('note', this.noteAllegato);
    if (
      this.vincoloService.isNamePresent(event.files[0].name, this.tableData)
    ) {
      this.showDialogMsg("Attenzione", "Questo file è già stato caricato");
    } else {
      if (this.versioneDoc == "firma") {
        this.tipoSelezionato =
          this.mapTipiAllegatoFirma[this.tipoSelezionato].firma;
      }
      this.vincoloService
        .uploadAllegato(formData, this.editMode, this.tipoSelezionato)
        .subscribe((response: any) => {
          if (response.error) {
            this.dialogService.showDialog(true, response.error, "Errore");
            return;
          }
          this.tableData.push(response);
          this.filteredTable.push(response);
        });
    }
    let cmpUpload = document.getElementById("fileUpload");
    cmpUpload.getElementsByTagName("button")[2].click();
  }

  checkAllegati() {
    if (this.filteredTable.length < 4) {
      return "Inserire tutti gli allegati obbligatori!";
    } else {
      let allegatiObbligatori = {};
      this.tipiAllegato.forEach((elem) => {
        if (elem.flgObbligatorio) {
          allegatiObbligatori[
            elem.descrTipoAllegato.replace(" - firmato digitalmente", "").trim()
          ] = elem;
        }
      });
      let items;
      for (let i in this.filteredTable) {
        delete allegatiObbligatori[
          this.filteredTable[i].descrTipoAllegato
            .replace(" - firmato digitalmente", "")
            .trim()
        ];
      }
      let missingDocs = Object.keys(allegatiObbligatori);
      if (missingDocs.length > 0) {
        return "Mancano " + missingDocs.length + " documenti obbligatori";
      }
    }
    return null;
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

  marcaBolloChange(value) {
    let nMarcaBollo = this.dichiarazioniForm.get("nMarcaBollo");
    if (value) {
      nMarcaBollo.disable();
      nMarcaBollo.patchValue("");
    } else {
      nMarcaBollo.enable();
    }
  }
}
