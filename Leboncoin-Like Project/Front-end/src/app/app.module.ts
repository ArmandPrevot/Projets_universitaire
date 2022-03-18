import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NavbarComponent } from './navbar/navbar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ChartsComponent } from './dashboard/charts/charts.component';
import { CardsComponent } from './dashboard/cards/cards.component';
import { SignInComponent } from './login/sign-in/sign-in.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AnnoncesComponent } from './annonces/annonces.component';
import { UsersComponent } from './users/users.component';
import { ModalEditComponent } from './annonces/modal-edit/modal-edit.component';
import { ModalEditUserComponent } from './users/modal-edit-user/modal-edit-user.component';
import { ErrorModalComponent } from './error-modal/error-modal.component';
import { HttpErrorInterceptor } from './httpInterceptor/http-interceptor';
import { ModelAddUserComponent } from './users/model-add-user/model-add-user.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    DashboardComponent,
    ChartsComponent,
    CardsComponent,
    SignInComponent,
    AnnoncesComponent,
    UsersComponent,
    ModalEditComponent,
    ModalEditUserComponent,
    ErrorModalComponent,
    ModelAddUserComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot(),
  ],
  entryComponents: [
    ModalEditComponent,
    ModalEditUserComponent,
    ModelAddUserComponent,
  ],
  exports: [NavbarComponent],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
