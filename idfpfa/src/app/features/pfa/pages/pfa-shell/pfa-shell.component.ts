/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from "@angular/core";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";

@Component({
  selector: "app-pfa-shell",
  templateUrl: "./pfa-shell.component.html",
  styleUrls: ["./pfa-shell.component.css"],
})
export class PfaShellComponent implements OnInit {
  constructor(private pfaService: PfaSampleService) {}

  ngOnInit() {
    
  }
}
