/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js'

@Component({
  selector: 'app-scatter-chart',
  templateUrl: './scatter-chart.component.html',
  styleUrls: ['./scatter-chart.component.css']
})
export class ScatterChartComponent implements OnInit { 

   



  constructor() { }

  ngOnInit() {
   new Chart(document.getElementById("myChart"), {
      type: 'scatter',
    data: {
        datasets: [{ 
            label: 'Scatter Dataset',
            data: [{
                x: -10,
                y: 0
            }, {
                x: 1,
                y: 10
            }, {
                x: 10,
                y: 5
            }],
			borderColor: "rgb(255, 99, 132)"
        }, {
            "label": "Line Dataset",
            "data": [{
                x: -8,
                y: 0
            }, {
                x: 1,
                y: 8
            }, {
                x: 10,
                y: 4
            }],
            "type": "line",
            "fill": false,
            "borderColor": "rgb(54, 162, 235)"
        }]
    },
    options: {
        scales: {
            xAxes: [{
                type: 'linear',
                position: 'bottom'
            }]
        }
    }
});
  }
  
  

}
