/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Output, EventEmitter, Input } from "@angular/core";
import { FormGroup, FormBuilder } from "@angular/forms";
import { TableHeader } from "src/app/shared/models/table-header";
import { PfaConfigComponent } from "../../pfa-config";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { ActivatedRoute } from "@angular/router";
import { TipoAllegato } from "src/app/shared/models/tipo-allegato";
import { Observable } from "rxjs";
import { DocumentoAllegato } from "src/app/shared/models/documento-allegato";
import { DownloadService } from "src/app/shared/services/download.service";
import { DataTaglio } from "src/app/shared/models/data-taglio";
import { DialogService } from "src/app/shared/services/dialog.service";
import { ButtonType, DialogButtons } from "src/app/shared/models/dialog.model";
import { StepErrors } from "src/app/shared/components/steps-with-errors/steps-with-errors.component";
import { CONST } from "src/app/shared/constants";

@Component({
  selector: "app-allegati",
  templateUrl: "./allegati.component.html",
  styleUrls: ["./allegati.component.css"],
})
export class AllegatiComponent implements OnInit {
  allegatiForm: FormGroup;
  allegatiHeader: TableHeader[];
  allegatiFiles: any[];
  selectedRow: number;
  tipo: number;
  route: ActivatedRoute;
  tableData: DocumentoAllegato[] = [];
  documentoAllegato: DocumentoAllegato;
  @Input() idIntervento: number;
  @Input() errorsOnAllegati: boolean;
  @Input() pfaPlanId: number;
  @Input() editMode: boolean;
  @Input() enableSalvaButton: boolean = true;
  @Input() idInterventoForEdit: number;
  @Input() tipoAllegato: TipoAllegato[];
  @Input() interventionPlan:string;
  @Input() viewMode:boolean;
  @Output() emitIndex: EventEmitter<void> = new EventEmitter();
  @Output() emitErrors: EventEmitter<StepErrors[]> = new EventEmitter();
  @Output() emitUpdateErrors: EventEmitter<void> = new EventEmitter();
  @Output() emitBack: EventEmitter<void> = new EventEmitter();
  @Output() checkDrell: EventEmitter<boolean> = new EventEmitter();

  allegatiTableHeaders: TableHeader[] = [
    { field: "descrTipoAllegato", header: "Tipo allegato" },
    { field: "nomeAllegato", header: "Nome file" },
    { field: "nomeAllegato", header: "Note" },
    { field: "dimensioneAllegatoKB", header: "Kb" },
  ];
  filePopUp: boolean;

  constructor(
    private fb: FormBuilder,
    private pfaService: PfaSampleService,
    private downloadService: DownloadService,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    this.makeForm();
    this.allegatiHeader = PfaConfigComponent.getAllegatoHeaders();
    this.printDocumentoAllegato();
  }

  printDocumentoAllegato() {
    if (this.idInterventoForEdit != undefined) {
      this.printDocumentoService(this.idInterventoForEdit);
    } else if (this.idIntervento != undefined) {
      this.printDocumentoService(this.idIntervento);
    }
  }

  printDocumentoService(id: number) {
    this.pfaService.getAllDocumentiByIntervento(id).subscribe((resp) => {
      this.allegatiFiles = resp; 
      const drell = resp.filter(el=> el.descrTipoAllegato == CONST.NOME_ALLEGATO_DRELL)    
      this.checkDrell.emit(drell.length > 0 ? true : false)
    });
  }

  makeForm() {
    this.allegatiForm = this.fb.group({
      tipoAllegato: [{value:''}],
      // scegliFile: [{}],
      note: [],
    });
  }

  getFieldId(index: number) {
    this.selectedRow = index;
  }

  deleteDocumento(id: number) {
    this.pfaService
      .deletePfaDocumenti(id)
      .subscribe((res) => {
        this.emitUpdateErrors.emit();
        this.printDocumentoAllegato();
      
      });
  }

  onBasicUpload(event: any) {}

  proceed() {
    let idIntervento = this.idInterventoForEdit?this.idInterventoForEdit:this.idIntervento;
    this.saveData(idIntervento);
    this.getDataTaglio(idIntervento);
  }

  saveData(idIntervento: number) {
    this.pfaService.saveDataForAllegati(idIntervento).subscribe((response: any) => {
      this.emitErrors.emit(response);
    });
  }

  getDataTaglio(idIntervento: number) {
    if(idIntervento){
      this.pfaService.getDataTaglio(idIntervento).subscribe((response: any) => {
        this.emitIndex.emit(response);
      });
    }
  }

  goToDettaglio() {
    this.emitBack.emit();
  }

  contieneCaracteresEspecialesItaliano(texto) {
    const caracteresEspecialesItaliano = /[àèéìíîòóùú!\"£$%&/()=,\?^*]/;
    return caracteresEspecialesItaliano.test(texto);
  }

  uploadFile(event) {
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
    this.tipo = parseInt(this.allegatiForm.get("tipoAllegato").value);
    let value = (<HTMLInputElement>document.getElementById('tipoAllegato')).value;
    if(!this.tipo && value && value.length>0){
      this.allegatiForm.get("tipoAllegato").patchValue(value);
      this.tipo = parseInt(this.allegatiForm.get("tipoAllegato").value);
    }
    if(!this.tipo){
      this.showMsg('ATTENZIONE','Scegliere la tipologia di allegato!');
      return;
    }
    if(this.allegatiFiles && this.allegatiFiles.length > 0){
      for(let i in this.allegatiFiles){
        if(this.allegatiFiles[i].idTipoAllegato == this.tipo){
          this.showMsg('ATTENZIONE','Tipologia di allegato già inserito! Eliminare il precedente per sostituirlo.');
          return;
        }
      }
    }
    if (this.idInterventoForEdit != undefined) {
      if (
        this.pfaService.isNamePresent(
          event.files[0].name,
          this.allegatiFiles
        ) &&
        this.pfaService.isTipoPresent(this.tipo, this.allegatiFiles)
      ) {
        this.filePopUp = true;
      } else {
        this.filePopUp = false;
        this.pfaService
          .uploadPfa(
            formData,
            this.idInterventoForEdit,
            this.pfaPlanId,
            this.tipo
          )
          .subscribe((response: DocumentoAllegato) => {
            this.emitUpdateErrors.emit();
            this.documentoAllegato = {
              idDocumentoAllegato: response.idDocumentoAllegato,
              uidIndex: response.uidIndex,
              note: this.allegatiForm.get("note").value,
            };
            this.pfaService
              .updateUploadingPfa(this.documentoAllegato)
              .subscribe((res) => {
                this.printDocumentoAllegato();
              });
          });
      }
    } else {
      if (
        this.pfaService.isNamePresent(
          event.files[0].name,
          this.allegatiFiles
        ) &&
        this.pfaService.isTipoPresent(this.tipo, this.allegatiFiles)
      ) {
        this.filePopUp = true;
      } else {
        this.filePopUp = false;
        this.pfaService
          .uploadPfa(formData, this.idIntervento, this.pfaPlanId, this.tipo)
          .subscribe((response: DocumentoAllegato) => {
            this.emitUpdateErrors.emit();
            this.documentoAllegato = {
              idDocumentoAllegato: response.idDocumentoAllegato,
              uidIndex: response.uidIndex,
              note: this.allegatiForm.get("note").value,
            };
            this.pfaService
              .updateUploadingPfa(this.documentoAllegato)
              .subscribe((res) => this.printDocumentoAllegato());
          });
      }
    }
    let cmpUpload = document.getElementById("idfileUploadPfa");
    cmpUpload.getElementsByTagName('button')[2].click();

  }
  closeDialog() {
    this.filePopUp = false;
  }
  documentToDownload(id: number) {
    this.pfaService.downloadPfaDocumenti(id).subscribe((response) => {
      let contentDisposition = response.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];
      this.downloadService.saveFile(response, filename);
    });
  }

  showMsg(msgType,msg){
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

  formatNumber(value){
    if(value){
      return (value + '').replace('.',',');
    }
    return '';
  }

}
