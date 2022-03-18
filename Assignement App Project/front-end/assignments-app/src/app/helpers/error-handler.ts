import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandler, Injectable, Injector } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable()
export class AppErrorHandler extends ErrorHandler {
  constructor(private injector: Injector) {
    super();
  }

  private get toastrService(): ToastrService {
    return this.injector.get(ToastrService);
  }

  public override handleError(error: any) {
    if (!(error instanceof HttpErrorResponse)) {
      this.toastrService.error('Une erreur est survenue !', 'Oups !');
      // this.auth.doLogout();
    }
    super.handleError(error);
  }
}
