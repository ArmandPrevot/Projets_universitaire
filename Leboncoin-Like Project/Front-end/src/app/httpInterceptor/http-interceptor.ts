import { ErrorModalComponent } from './../error-modal/error-modal.component';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from './../services/auth.service';
import { MDBModalRef, MDBModalService } from 'angular-bootstrap-md';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';
@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {
  modalRef: MDBModalRef;
  constructor(
    private injector: Injector,
    private router: Router,
    private auth: AuthService
  ) {}

  private get modalService(): MDBModalService {
    return this.injector.get(MDBModalService);
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (this.auth.getToken()) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${this.auth.getToken()}`,
        },
      });
      if (req.headers.get('skip')) {
        return next.handle(req);
      }
    }

    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error instanceof HttpErrorResponse) {
          this.modalRef = this.modalService.show(ErrorModalComponent);
          this.router.navigate(['/sign-in']);
        }
        return throwError(error);
      })
    );
  }
}
