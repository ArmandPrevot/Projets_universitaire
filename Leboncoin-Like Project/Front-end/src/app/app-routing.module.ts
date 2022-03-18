import { AnnoncesComponent } from './annonces/annonces.component';
import { SignInComponent } from './login/sign-in/sign-in.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'sign-in', component: SignInComponent },
  { path: 'annonces', component: AnnoncesComponent },
  { path: 'users', component: UsersComponent },
  { path: '', component: SignInComponent, pathMatch: 'full' },
  { path: '**', component: SignInComponent, pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
