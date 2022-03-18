import { UserResult } from './../../models/app-models';
import { HttpService } from './../../services/http.service';
import { StateService } from './../../services/state.service';
import { Component, Input, OnInit } from '@angular/core';
import { combineLatest } from 'rxjs';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.scss'],
})
export class CardsComponent implements OnInit {
  userList: UserResult[] = [];
  @Input()
  userNb: number = 0;
  @Input()
  annonceNb: number = 0;
  constructor(
    private httpService: HttpService,
    private stateService: StateService
  ) {}

  ngOnInit(): void {
    // this.getAllData();
    //console.log(this.annonceNb);
  }

  // getAllData(): void {
  //   const $users = this.httpService.getAllUsers();
  //   const $annonces = this.httpService.getAllAnnonces();
  //   const $combination = combineLatest([$users, $annonces]);

  //   combineLatest([$combination]).subscribe((allDataResult) => {
  //     allDataResult.map(([users, annonces]) => {
  //       this.userNb = users.length;
  //       this.annonceNb = annonces.length;
  //       this.stateService.annoncesList = annonces;
  //       this.stateService.userList = users;
  //     });
  //   });
  // }
}
