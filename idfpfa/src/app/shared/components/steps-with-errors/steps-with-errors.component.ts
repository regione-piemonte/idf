/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnChanges, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-steps-with-errors',
  templateUrl: './steps-with-errors.component.html',
  styleUrls: ['./steps-with-errors.component.css']
})
export class StepsWithErrorsComponent implements OnInit, OnChanges {

  @Input('steps-config') stepsconfig: Step[];
  @Input('activeStep') activeStep: number;
  @Input('lastCompletedStep') lastCompletedStep: number;
  
  @Output() emitActiveStep: EventEmitter<number> = new EventEmitter();

  constructor() { }

  ngOnInit() {
    // this.stepsconfig = [];
    // let item = {activeIndex:1,state:'bhoo',isActive:true,label:'Franco',noOfErrors:2,enabled:true};
    // this.stepsconfig.push(item as Step);
  }

  ngOnChanges(){
    this.createStepper();
  }

  active(i){
    if(i <= this.lastCompletedStep && i != this.activeStep){
      this.emitActiveStep.emit(i);
    }
  }


  createStepper() {
    this.stepsconfig.forEach(item =>{
     if(item.activeIndex < this.lastCompletedStep){
      item.setState("step-completed");
      item.enabled = true;
     }else if(item.activeIndex == this.lastCompletedStep && item.activeIndex != this.activeStep){
        item.setState("step-current");
        item.enabled = true;
      }else{
        item.setState("step-uncompleted");
        item.enabled = false;
      }            
        
      
     if(item.activeIndex == this.activeStep){
       item.isActive = true;
       item.enabled = true;
     }else{
      item.isActive = false;
     }
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

export class StepErrors{
  public errorMessages:any;
  public noOfErrors:number;
  public stepNumber:number;
}
