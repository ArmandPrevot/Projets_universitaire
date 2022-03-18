import { ToastrService } from 'ngx-toastr';
import { AuthService } from './../auth/service/auth.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService, private toastr: ToastrService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const isLogged = this.authService.isLoggedIn;
    const user = this.authService.userValue;

    if (isLogged && user) {
      // check if route is restricted by role
      if (route.data['roles'] && route.data['roles'].indexOf(user.role) === -1) {
        // role not authorised so redirect to home page
        this.toastr.warning(`En tant que ${user.role}, vous n'êtes pas autorisé`, 'Attention');
        this.router.navigate(['']);
        return false;
      }
      // authorised so return true
      return true;
    }

    this.router.navigate(['auth']);
    return false;
  }
}
