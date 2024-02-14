/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { MenuItem } from 'primeng/components/common/menuitem';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ForestaleSampleService } from 'src/app/shared/services/forestale-sample.service';
import { Observable, Subject } from 'rxjs';
import { SelectItem } from 'primeng/components/common/selectitem';
import { TableHeader } from 'src/app/shared/models/table-header';
import { takeUntil } from 'rxjs/operators';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { AreaDiSaggio } from 'src/app/shared/models/area-di-saggio';
import { Step } from '../stepper/stepper.component';
import { Tipologia } from 'src/app/shared/models/tipoEnum';

@Component({
  selector: 'app-steps',
  templateUrl: './steps.component.html',
  styleUrls: ['./steps.component.css']
})
export class StepsComponent implements OnInit, OnDestroy {

  id: number;
  steps:Step[];
  // steps: MenuItem[];
  activeIndex: number;
  unsubscribe$: Subject<void> = new Subject<void>();

  alberiCampitoneHeaders: TableHeader[];
  fixedFields: string[];
  tipoForestaleList: SelectItem[] = [];


  grayPanel: AreaDiSaggio;

  constructor(private forestaleService: ForestaleSampleService, private route: ActivatedRoute, private router: Router) {
    this.steps=[
      new Step( 0,"Dati stazionali 1"),
      new Step( 1,"Dati stazionali 2"),
      new Step( 2,"Alberi campione e dominante"),
      new Step( 3,"Cavallettamento")
    ];
    
   }

  ngOnInit() {
    // this.route.params
    // .pipe(takeUntil(this.unsubscribe$))
    // .subscribe(
    //   (response: Params) => {
    //     this.id = response['id'];
    //     this.getGreyPanel();
    //   }
    // );
  }

  getGreyPanel() {
    
    this.forestaleService.getGrayPanelSteps(this.id.toString(), true)
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response: AreaDiSaggio) => {
        this.grayPanel = response;
        // this.steps = this.buildSteps();
        if(this.grayPanel.lastCompletedStep < 4) {
          this.activeIndex = this.grayPanel.lastCompletedStep;
        } else if(this.grayPanel.lastCompletedStep >= 4) {
          this.activeIndex = 3;
        }
        if(this.grayPanel.tipoAds !== Tipologia.SUPERFICIE_NOTA) {
          //currently it redirects to home
          //this.router.navigate(['/']);
          //it should maybe have a popup that says only Temporanea allowed
        } 
      }
    );
  }

  buildSteps(): MenuItem[] {
    return [
      {
        label: 'Dati stazionali 1',
      },
      {
        label: 'Dati stazionali 2',
        disabled: this.grayPanel.lastCompletedStep < 1
      },
      {
        label: 'Alberi campione e dominante',
        disabled: this.grayPanel.lastCompletedStep < 2

      },
      {
        label: 'Cavallettamento',
        disabled: this.grayPanel.lastCompletedStep < 3
      }
    ];
  }

  sendFourthForm() {
    console.log('LOGIC');
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
}
