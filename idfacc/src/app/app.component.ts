/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'app';

  ngOnInit() {
    sessionStorage.setItem("redirect", this.getRedirectUrl());
  }

  getRedirectUrl(){
    var url_string = window.location.href
    var url = new URL(url_string);
    return url.searchParams.get("redirect");
  }
}
