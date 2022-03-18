import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  constructor() {}

  isLoading$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isLoadingObs: Observable<boolean> = this.isLoading$.asObservable();

  showLoader() {
    this.isLoading$.next(true);
  }

  hideLoader() {
    this.isLoading$.next(false);
  }
}
