import { StateService } from './../services/state.service';
import { HttpService } from './../services/http.service';
import { Component, OnInit } from '@angular/core';
import { combineLatest } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  userNb: number = 0;
  annonceNb: number = 0;
  isLoading: boolean = false;
  constructor(
    private httpService: HttpService,
    private stateService: StateService
  ) {}

  ngOnInit(): void {
    this.getAllData();
  }

  public fileName = '';

  onFileSelected(event: any) {
    const file: File = event.target.files[0];

    if (file) {
      this.fileName = file.name;

      const formData = new FormData();

      formData.append('thumbnail', file);

      console.log(formData);
      // const upload$ = this.http.post("/api/thumbnail-upload", formData);

      // upload$.subscribe();
    }
  }

  getAllData() {
    this.isLoading = true;
    const $users = this.httpService.getAllUsers();
    const $annonces = this.httpService.getAllAnnonces();
    const $combination = combineLatest([$users, $annonces]);

    combineLatest([$combination]).subscribe((allDataResult) => {
      // console.log(allDataResult);
      allDataResult.map(([users, annonces]) => {
        this.userNb = users.length;
        this.annonceNb = annonces.length;
        this.stateService.annoncesList = annonces;
        this.stateService.userList = users;
        this.isLoading = false;
      });
    });
  }
}
