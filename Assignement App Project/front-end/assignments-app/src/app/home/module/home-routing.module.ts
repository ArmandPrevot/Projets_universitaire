import { EditComponent } from './../components/assignement/edit/edit.component';
import { AssignementComponent } from './../components/assignement/assignement.component';
import { Role } from '../../auth/models/auth.model';
import { AuthGuard } from './../../helpers/auth.guard';
import { CreateComponent } from './../components/create/create.component';
import { HomeComponent } from './../components/home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'assignements',
    component: HomeComponent,
  },
  {
    path: 'create',
    component: CreateComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Admin, Role.Etu] },
  },
  {
    path: 'assignement/:id',
    component: AssignementComponent,
  },
  {
    path: 'assignement/edit/:id',
    component: EditComponent,
    canActivate: [AuthGuard],
    data: { roles: [Role.Admin, Role.Prof] },
  },
  { path: '**', redirectTo: '/assignements' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
