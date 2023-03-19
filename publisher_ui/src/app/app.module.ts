import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {MaterialModule} from '../material.module';
import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatNativeDateModule} from '@angular/material/core';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { ItemPageCenterComponent } from './item-page-center/item-page-center.component';
import { ItemPageTopLeftComponent } from './item-page-top-left/item-page-top-left.component';
import { ItemPageTopRigthComponent } from './item-page-center/item-page-top-rigth/item-page-top-rigth.component';
import { ItemPageBottomLeftComponent } from './item-page-bottom-left/item-page-bottom-left.component';
import { ItemPageBottomRigthComponent } from './item-page-bottom-rigth/item-page-bottom-rigth.component';
import { ItemPageFooterComponent } from './item-page-footer/item-page-footer.component';
import { HeaderLogoComponent } from './header-logo/header-logo.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { CatalogComponent } from './components/catalog/catalog.component';
import { AttributesComponent } from './components/attributes/attributes.component';
import { AttributeComponent } from './components/attribute/attribute.component';




@NgModule({
  declarations: [AppComponent, ItemPageCenterComponent, ItemPageTopLeftComponent, ItemPageTopRigthComponent, ItemPageBottomLeftComponent, ItemPageBottomRigthComponent, ItemPageFooterComponent, HeaderLogoComponent, FileUploadComponent, CatalogComponent, AttributesComponent, AttributeComponent],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatNativeDateModule,
    MaterialModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MatFileUploadModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
