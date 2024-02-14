/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, EventEmitter, Output, Injectable } from '@angular/core';
import { AreaDiSaggio } from 'src/app/shared/models/area-di-saggio';
import { ForestaleSampleService } from 'src/app/shared/services/forestale-sample.service';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Tipologia } from 'src/app/shared/models/tipoEnum';
import { StepErrorsReport } from 'src/app/shared/models/consolida-scheda-error-response';

@Component({
  selector: 'app-stepper',
  templateUrl: './stepper.component.html',
  styleUrls: ['./stepper.component.css']
})

export class StepperComponent implements OnInit {
  @Input('active-step') activeIndex:number=0;
  @Input('last-completed-step') lastCompletedStep:number=0;
  @Input('steps-config') stepsconfig: Step[];
  @Input('optional') optional:number;
  @Input('errors') errors:number;
  stepsErrors:StepErrorsReport[];
  activeStep:Step;
  grayPanel: AreaDiSaggio;
  unsubscribe$: Subject<void> = new Subject<void>();
  id:number;
  @Output() onChange : EventEmitter<number> = new EventEmitter();
  @Input() sendData : EventEmitter<boolean> = new EventEmitter();

  constructor(private forestaleService: ForestaleSampleService, private route: ActivatedRoute, private router: Router) {
   }
  
  ngOnInit(): void {
    this.activeStep=this.stepsconfig[this.activeIndex];
    
    this.activeStep.isActive=true;
    this.route.params
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response: Params) => {
        this.id = response['id'];
        this.next();
        this.loadStepErrors();        
      }
    );
   }

   loadStepErrors(){
    this.forestaleService.getErrorSteps(this.id.toString()).pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response: any) => {
        console.log('getErrorSteps:');
        console.log(response);
        this.updateStesErrorInfo(response);
      })
  }
 
  next(event?: StepErrorsReport[]){
    this.updateStesErrorInfo(event);
    this.forestaleService.getGrayPanelSteps(this.id.toString(), true)
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response: AreaDiSaggio) => {
        this.activeStep.isActive=false;
        this.activeStep.setState('step-completed')
        this.grayPanel = response;
        this.initializeComplitedSteps(this.grayPanel);
        
        if(this.grayPanel.lastCompletedStep < 4) {
          this.activeStep = this.stepsconfig[this.grayPanel.lastCompletedStep];
          
        } else if(this.lastCompletedStep >= 4) {
          this.activeStep = this.stepsconfig[3];
        }
        if(this.grayPanel.tipoAds !== Tipologia.SUPERFICIE_NOTA) {
          //currently it redirects to home
          //this.router.navigate(['/']);
          //it should maybe have a popup that says only Temporanea allowed
        } 
     
     this.activeStep.isActive=true;
     this.activeStep.enabled=true;
     this.onChange.emit(this.activeStep.activeIndex);
      }
    );
  }

  // back(){
  //   if(this.activeStep.activeIndex===1){
  //     return
  //   }
  //   this.activeStep.isActive=false;
  //   this.activeStep=this.stepsconfig[this.activeStep.activeIndex-1];
  //   this.activeStep.isActive=true;
  //   this.onChange.emit(this.activeStep.activeIndex);
  //  }

  active(index:number){
    if(this.stepsconfig[index].isActive || !this.stepsconfig[index].enabled){
      return 
    }
    this.forestaleService.getGrayPanelSteps(this.id.toString(), true)
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response: AreaDiSaggio) => {
        this.activeStep.isActive=false;
        
        this.grayPanel = response;
        this.activeStep=this.stepsconfig[index];
        if(this.grayPanel.tipoAds !== Tipologia.SUPERFICIE_NOTA) {
          //currently it redirects to home
          //this.router.navigate(['/']);
          //it should maybe have a popup that says only Temporanea allowed
        } 
     
     this.activeStep.isActive=true;
     this.onChange.emit(this.activeStep.activeIndex);
      }
    );
  }

isDisable(i:number){
  if(this.lastCompletedStep<=i){
  return false;  
  }else{
    return true;
  }
}

isOptional(){
  if(this.activeStep.activeIndex===this.stepsconfig.length){
    return
 }
 this.activeStep.isActive=false;
 this.activeStep=this.stepsconfig[this.activeStep.activeIndex];
 this.activeStep.isActive=true;
 this.lastCompletedStep=this.activeStep.activeIndex;
 this.onChange.emit(this.lastCompletedStep)
}

initializeComplitedSteps(graypanel:AreaDiSaggio){
  this.lastCompletedStep = graypanel.lastCompletedStep || 0;
  this.errors = this.grayPanel.errors || 2;
  for(let i =0;i<this.lastCompletedStep;i++){
    if(this.optional-1===i){
      this.stepsconfig[i].setState("step-uncompleted");
      
    }else{
    this.stepsconfig[i].setState("step-completed");
  }
  
  this.stepsconfig[i].enabled=true;
}

}

getGreyPanel() {
  this.forestaleService.getGrayPanelSteps(this.id.toString(), true)
  .pipe(takeUntil(this.unsubscribe$))
  .subscribe(
    (response: AreaDiSaggio) => {
      this.activeStep.isActive=false;
      this.activeStep.setState('step-completed')
      this.grayPanel = response;
      
      if(this.grayPanel.lastCompletedStep < 4) {
        this.activeStep = this.stepsconfig[this.grayPanel.lastCompletedStep];
        
      } else if(this.lastCompletedStep >= 4) {
        this.activeStep = this.stepsconfig[3];
      }
      if(this.grayPanel.tipoAds !== Tipologia.SUPERFICIE_NOTA) {
        //currently it redirects to home
        //this.router.navigate(['/']);
        //it should maybe have a popup that says only Temporanea allowed
      } 
      
   this.activeStep.isActive=true;
   this.onChange.emit(this.activeStep.activeIndex);
    }
  );

}
ngOnDestroy() {
  this.unsubscribe$.next();
  this.unsubscribe$.complete();
  this.unsubscribe$.unsubscribe();
}

onConsolidaValidationError(stepsErrors: StepErrorsReport[]) {
  this.updateStesErrorInfo(stepsErrors);  
}

onColidationSuccess() {
  this.stepsconfig.forEach(stepConfig => stepConfig.noOfErrors = 0 );
}

updateStesErrorInfo(stepsErrors: StepErrorsReport[]) {
  this.stepsErrors = stepsErrors;
  console.log('updating stes errors', stepsErrors);
  if (!stepsErrors)  return;
  stepsErrors.forEach( stepError => {
    this.stepsconfig[stepError.stepNumber].noOfErrors = stepError.noOfErrors;
  })
}

}



export class Step{
  public activeIndex:number;
  public state:string;
  public isActive:boolean;
  public label: string;
  public noOfErrors: number = 0;
  public enabled:boolean;

  constructor(index:number,label?:string,state?:string){
    this.activeIndex=index;
    this.state=state || "step-uncompleted";
    this.label=label;
  }
  className(){
    return this.state;
  }
  setState(state:string){
    this.state=state;
  }
  
  
}