import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable, throwError } from 'rxjs';
import { catchError, finalize } from 'rxjs/operators';
import { AuthService } from '../auth/service/auth.service';
import { AppService } from '../service/app.service';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
  totalRequests = 0;
  completedRequests = 0;

  constructor(private toastrService: ToastrService, private router: Router, private auth: AuthService, private appService: AppService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Loading purpose
    this.appService.showLoader();
    this.totalRequests++;

    // JWT Token injection
    const authToken = this.auth.getToken();

    if (authToken) {
      request = request.clone({
        setHeaders: {
          Authorization: authToken,
        },
      });
    }

    return next.handle(request).pipe(
      finalize(() => {
        this.completedRequests++;
        if (this.completedRequests === this.totalRequests) {
          this.appService.hideLoader();
          this.completedRequests = 0;
          this.totalRequests = 0;
        }
      }),
      catchError((error: HttpErrorResponse) => {
        if (error instanceof HttpErrorResponse) {
          if ([401, 400, 403].indexOf(error.status) !== -1) {
            // auto logout if 401 Unauthorized or 403 Forbidden response returned from api
            this.toastrService.warning("Vous n'êtes pas autorisé", 'Attention !');
            // this.auth.doLogout();
          } else {
            this.toastrService.error('Une erreur réseau est survenue !', 'Oups !');
            this.auth.doLogout();
          }
        }
        return throwError(error);
      })
    );
  }
}
