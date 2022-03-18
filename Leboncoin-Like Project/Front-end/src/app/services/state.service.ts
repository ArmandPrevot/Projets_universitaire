import { HttpService } from './http.service';
import { Injectable } from '@angular/core';
import { BehaviorSubject, combineLatest } from 'rxjs';
import { AnnonceResult, UserResult } from '../models/app-models';

@Injectable({
  providedIn: 'root',
})
export class StateService {
  isConnected: boolean = false;
  isWaiting: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  userList: UserResult[] = [];
  annoncesList: AnnonceResult[] = [];

  constructor(private httpService: HttpService) {}

  getAllData() {
    const $users = this.httpService.getAllUsers();
    const $annonces = this.httpService.getAllAnnonces();
    const $combination = combineLatest([$users, $annonces]);

    return combineLatest([$combination]);
  }
}
