/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HomeShellComponent } from "./home-shell/home-shell.component";
import { HomePageComponent } from "./home-page/home-page.component";
import { UserFormComponent } from "./user-form/user-form.component";

export const pages: any[] = [
    HomeShellComponent,
    HomePageComponent,
    UserFormComponent
];

export { HomeShellComponent } from "./home-shell/home-shell.component";
export { HomePageComponent } from "./home-page/home-page.component";
export { UserFormComponent } from "./user-form/user-form.component";
