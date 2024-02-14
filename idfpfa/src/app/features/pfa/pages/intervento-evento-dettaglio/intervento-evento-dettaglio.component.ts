/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from "@angular/core";
import { Detail } from "src/app/shared/models/dettaglio";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { ActivatedRoute, Router } from "@angular/router";
import { TableHeader } from "src/app/shared/models/table-header";
import { PfaConfigComponent } from "../../pfa-config";
import { Observable } from "rxjs";
import {
  TipoInterventoDettaglio,
  PropAndRicandeza,
  SpeciesAndTaglio,
} from "src/app/shared/models/dettaglio-intervento";
import { UtilizzatoreDetails } from "src/app/shared/models/data-taglio";
import { DownloadService } from "src/app/shared/services/download.service";
import { StatoInterventoEnum } from "src/app/shared/enums";
import { ShootingMirrorModel } from "src/app/shared/models/shooting-mirror";
import { ButtonType, DialogButtons } from "src/app/shared/models/dialog.model";
import { DialogService } from "src/app/shared/services/dialog.service";
import { FormBuilder, FormGroup } from "@angular/forms";
import { FormUtil } from "src/app/utils/form-util";

@Component({
  selector: "app-intervento-evento-dettaglio",
  templateUrl: "./intervento-evento-dettaglio.component.html",
  styleUrls: ["./intervento-evento-dettaglio.component.css"],
})
export class InterventoEventoDettaglioComponent implements OnInit {
  denom: Detail;
  routeId: number;
  params: any;
  allegatiHeader: TableHeader[];
  allegatiFiles: any[];
  selectedRow: number;
  tipoInterventoDettaglio$: Observable<TipoInterventoDettaglio>;
  propAndRicandeza$: Observable<PropAndRicandeza>;
  specieAndTaglio$: Observable<SpeciesAndTaglio>;
  utilizzatoreDettaglio$: Observable<UtilizzatoreDetails>;
  idIntervento: number;
  idPfa: number;
  statoIntervento = StatoInterventoEnum;

  tableHeaders: TableHeader[];
  shootingMirrorData: ShootingMirrorModel[];
  shootingMirrorVisible = false;
  shootingMirrorForm: FormGroup;
  summaryColumnData: any;

  constructor(
    private pfaService: PfaSampleService,
    private activatedRoute: ActivatedRoute,
    private routeService: Router,
    private downloadService: DownloadService,
    private fb: FormBuilder,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((res) => {
      this.idPfa = res.id;
      this.idIntervento = res.idInterventi; 
  
    });

    this.allegatiHeader = PfaConfigComponent.getAllegatoHeaders();

    this.pfaService.getAllDocumentiByIntervento(this.idIntervento).subscribe((resp) => {
      this.allegatiFiles = resp;
    });

    this.tipoInterventoDettaglio$ = this.pfaService.getTipoInterventoDettaglio(
      this.idIntervento
    );
    this.propAndRicandeza$ = this.pfaService.getPropAndRicandeza(
      this.idIntervento
    );
    this.specieAndTaglio$ = this.pfaService.getSpecieAndTaglio(
      this.idIntervento
    );
    this.utilizzatoreDettaglio$ = this.pfaService.getUtilizzatoreDettaglio(
      this.idIntervento
    );
  }

  onShootingMirrorDialogHide(){

  }

  getFieldId(index: number) {
    this.selectedRow = index;
  }
  onModifica() {
    this.routeService.navigate([
      "pfa",
      "step",
      this.idPfa,

      { idIntervento: this.idIntervento, editmode: true },
    ]);
  }

  backToTabs() {
    this.routeService.navigate(["pfa", "tabs", this.idPfa,{tab:'interventi'}]);
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

  openMappa(){
    this.pfaService.getCartograficoPointsUrl(10,[''+ this.idIntervento])
    .subscribe(
      (response: any) => {
          window.open(response['geecourl'], "_blank");
      }
    );
    return false;
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

  openShootingMirror() {    
    this.tableHeaders = PfaConfigComponent.getShootingMirrorHeader();
    if (this.idIntervento) {
      this.pfaService
        .getDataForShootingMirror(this.idIntervento)
        .subscribe((res) => {
          if(res && res.length > 0){
            this.shootingMirrorData = res;
            this.createShootingMirrorForm(this.shootingMirrorData);
            this.shootingMirrorVisible = true;
          }else{
            this.showMsg('Info','Nessun dato disponibile!');
          }          
        });      
    }
  }

  createShootingMirrorForm(data: any[]) {
    let _form = this.fb.array([]);
    this.shootingMirrorForm = this.fb.group({
      columnForm: _form
    })
    data.forEach(dataObj => {
      _form.push(FormUtil.addFormFromDataObject(dataObj, this.fb, this.tableHeaders));
    });

    const ripresaIntervenTototal = data.map( obj => obj['ripresaIntervento']? obj['ripresaIntervento'] : 0 ).reduce((pv, ripresaIntervento) => { return pv+ ripresaIntervento});
    this.summaryColumnData = 
      {
        ripresaIntervento: {
          totale: ripresaIntervenTototal
        }
      };
  }

}
