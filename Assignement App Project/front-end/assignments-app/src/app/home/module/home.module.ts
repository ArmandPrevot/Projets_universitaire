import { DragDropModule } from '@angular/cdk/drag-drop';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { ToastrModule } from 'ngx-toastr';
import { AssignementComponent } from './../components/assignement/assignement.component';
import { EditComponent } from './../components/assignement/edit/edit.component';
import { CreateComponent } from './../components/create/create.component';
import { CardComponent } from './../components/home/card/card.component';
import { HomeComponent } from './../components/home/home.component';
import { HomeRoutingModule } from './home-routing.module';

@NgModule({
  declarations: [HomeComponent, AssignementComponent, CardComponent, CreateComponent, EditComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    MDBBootstrapModule,
    MatCardModule,
    DragDropModule,
    MatButtonModule,
    MatSlideToggleModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-right',
      preventDuplicates: true,
      timeOut: 3500,
    }),
  ],
})
export class HomeModule {}
