/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from "@angular/core";
import { MenuItem } from "primeng/components/common/menuitem";
import { FormGroup, FormBuilder, Validators, FormArray } from "@angular/forms";
import { ForestaleSampleService } from "src/app/shared/services/forestale-sample.service";
import { AreaDiSaggio } from "src/app/shared/models/area-di-saggio";
import { ActivatedRoute, Params } from "@angular/router";
import { StepsService } from "src/app/services/steps.service";
import { SelectItem } from "primeng/components/common/selectitem";
import { Tipologia } from "src/app/shared/models/tipoEnum";

@Component({
  selector: "app-inseriment-modifica",
  templateUrl: "./inseriment-modifica.component.html",
  styleUrls: ["./inseriment-modifica.component.css"]
})
export class InserimentModificaComponent implements OnInit {

  id: number;
  grayPanel: AreaDiSaggio;
  sempliceTipologia: number = Tipologia.RELASCOPICA_SEMPLICE;/*Relascopica semplice*/
  completaTipologia: number = Tipologia.RELASCOPICA_COMPLETA/*Relascopica completa*/
  specieList: SelectItem[];
  
  steps: MenuItem[];
  activeIndex: number;
  secondStepForm: FormGroup;
  dettaglioFormData: any;
  filteredSpecieList: SelectItem[];

  constructor(private fb: FormBuilder, private forestaleService: ForestaleSampleService, private route: ActivatedRoute, private stepsService: StepsService) {}

  ngOnInit() {
    
    this.activeIndex = 0;
    this.route.params.subscribe(
      (response: Params) => {
        this.id = response['id'];
        this.getGreyPanel();
      }
    );
    this.stepsService.getSpecie(true).subscribe(
      (response: SelectItem[]) => {
        this.specieList = response;
      }
    );
    this.stepsService.dettaglioForm.subscribe(
      (response) => {
        this.dettaglioFormData = response;
        this.activeIndex = 1;
        const plainSpecie = this.dettaglioFormData.plainAdsrelSpecie.map(item => item.idSpecie);
        this.filteredSpecieList = this.specieList.filter(item => {
          return plainSpecie.indexOf(item.value) !== -1;
        });
      }
    )
    this.buildSteps();
  }

  getGreyPanel() {
    this.forestaleService.getGrayPanelSteps(this.id.toString()).subscribe(
      (response: AreaDiSaggio) => {
        this.grayPanel = response;
      }
    );
  }

  changedIndex() {
    this.steps[this.activeIndex].command();
  }

  buildSteps() {
    this.steps = [
      {
        label: "Dettaglio",
        command: (event: any) => {
          
        }
      },
      {
        label: "Cavallettamento",
        disabled: true,
        command: (event: any) => {
          this.stepsService.emitDettaglioStep();
        }
      }
    ];
  }

  sendFirstForm() {
    this.activeIndex++;
  }

  sendSecondForm() {

  }
}
