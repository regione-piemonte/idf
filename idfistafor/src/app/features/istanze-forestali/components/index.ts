/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { DynamicHeaderComponent } from './stepper/dynamic-header/dynamic-header.component';
import { DynamicHeaderVidComponent} from './stepper-vid/dynamic-header-vid/dynamic-header-vid.component';
import { Step1Component } from './stepper/step1/step1.component';
import { Step2Component } from './stepper/step2/step2.component';
import { SearchForParcelsComponent } from './stepper/step2/search-for-parcels/search-for-parcels.component';
import { ListOfParcelsComponent } from './stepper/step2/list-of-parcels/list-of-parcels.component';
import { GeometryResultComponent } from './stepper/step2/geometry-result/geometry-result.component';
import { Step3Component } from './stepper/step3/step3.component';
import { Step4Component } from './stepper/step4/step4.component';
import { Step5Component } from './stepper/step5/step5.component';
import { Step6Component } from './stepper/step6/step6.component';
import { SignatureComponent } from './stepper/signature/signature.component';
import { SignatureVidComponent } from './stepper-vid/signature/signature-vid.component';
import { ListOfAttachmentsComponent } from './stepper/signature/list-of-attachments/list-of-attachments.component';
import { ListOfAttachmentsVidComponent } from './stepper-vid/signature/list-of-attachments-vid/list-of-attachments-vid.component';
import { IdfistaforHeaderHelper } from '../idfistafor-header-helper';
import { Step1VidComponent } from './stepper-vid/step1-vid/step1-vid.component';
import { Step2VidComponent } from './stepper-vid/step2-vid/step2-vid.component';
import { Step3VidComponent } from './stepper-vid/step3-vid/step3-vid.component';
import { Step4VidComponent } from './stepper-vid/step4-vid/step4-vid.component';
import { Step5VidComponent } from './stepper-vid/step5-vid/step5-vid.component';
import { Step6VidComponent } from './stepper-vid/step6-vid/step6-vid.component';
import { ListOfParcelsVidComponent } from './stepper-vid/step2-vid/list-of-parcels-vid/list-of-parcels-vid.component';
import { GeometryResultVidComponent } from './stepper-vid/step2-vid/geometry-result-vid/geometry-result-vid.component';
import { SearchForParcelsVidComponent } from './stepper-vid/step2-vid/search-for-parcels-vid/search-for-parcels-vid.component';
import { Step1TagliComponent } from './stepper-tagli/step1-tagli/step1-tagli.component';
import { Step2TagliComponent } from './stepper-tagli/step2-tagli/step2-tagli.component';
import { DynamicHeaderTagliComponent } from './stepper-tagli/dynamic-header-tagli/dynamic-header-tagli.component';
import { Step3TagliComponent } from './stepper-tagli/step3-tagli/step3-tagli.component';
import { SearchForParcelsTagliComponent } from './stepper-tagli/step3-tagli/search-for-parcels-tagli/search-for-parcels-tagli.component';
import { ListOfParcelsTagliComponent } from './stepper-tagli/step3-tagli/list-of-parcels-tagli/list-of-parcels-tagli.component';
import { GeometryResultTagliComponent } from './stepper-tagli/step3-tagli/geometry-result-tagli/geometry-result-tagli.component';
import { Step4TagliComponent } from './stepper-tagli/step4-tagli/step4-tagli.component';
import { Step5TagliComponent } from './stepper-tagli/step5-tagli/step5-tagli.component';
import { SignatureTagliComponent } from './stepper-tagli/signature/signature-tagli.component';
import { ListOfAttachmentsTagliComponent } from './stepper-tagli/signature/list-of-attachments-tagli/list-of-attachments-tagli.component';

export const components: any[] = [
  DynamicHeaderComponent,
  DynamicHeaderVidComponent,
  DynamicHeaderTagliComponent,
  Step1Component,
  Step2Component,
  Step3Component,
  Step4Component,
  Step5Component,
  Step6Component,
  Step1VidComponent,
  Step2VidComponent,
  Step3VidComponent,
  Step4VidComponent,
  Step5VidComponent,
  Step6VidComponent,
  Step1TagliComponent,
  Step2TagliComponent,
  Step3TagliComponent,
  Step4TagliComponent,
  Step5TagliComponent,
  SearchForParcelsComponent,
  SearchForParcelsVidComponent,
  SearchForParcelsTagliComponent,
  ListOfParcelsComponent,
  ListOfParcelsVidComponent,
  ListOfParcelsTagliComponent,
  GeometryResultComponent,
  GeometryResultVidComponent,
  GeometryResultTagliComponent,
  SignatureComponent,
  SignatureVidComponent,
  SignatureTagliComponent,
  ListOfAttachmentsComponent,
  ListOfAttachmentsVidComponent,
  ListOfAttachmentsTagliComponent,
  IdfistaforHeaderHelper
];
