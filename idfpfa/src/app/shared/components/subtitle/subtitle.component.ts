/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from "@angular/core";

@Component({
  selector: "app-subtitle",
  templateUrl: "./subtitle.component.html",
  styleUrls: ["./subtitle.component.css"]
})
export class SubtitleComponent implements OnInit {
  @Input() subtitle: string;
  constructor() {}

  ngOnInit() {}
}
