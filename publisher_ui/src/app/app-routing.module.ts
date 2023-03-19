import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderLogoComponent } from './header-logo/header-logo.component';
import { ItemPageBottomLeftComponent } from './item-page-bottom-left/item-page-bottom-left.component';
import { ItemPageBottomRigthComponent } from './item-page-bottom-rigth/item-page-bottom-rigth.component';
import { ItemPageCenterComponent } from './item-page-center/item-page-center.component';
import { ItemPageFooterComponent } from './item-page-footer/item-page-footer.component';
import { ItemPageTopLeftComponent } from './item-page-top-left/item-page-top-left.component';
import { ItemPageTopRigthComponent } from './item-page-top-rigth/item-page-top-rigth.component';

const routes: Routes = [
  { path: 'HeaderLogo', component: HeaderLogoComponent },
  { path: 'ItemPageCenter', component: ItemPageCenterComponent },
  { path: 'ItemPageTopLeft', component: ItemPageTopLeftComponent },
  { path: 'ItemPageTopRigth', component: ItemPageTopRigthComponent },
  { path: 'ItemPageBottomLeft', component: ItemPageBottomLeftComponent },
  { path: 'ItemPageBottomRigth', component: ItemPageBottomRigthComponent },
  { path: 'ItemPageFooter', component: ItemPageFooterComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
