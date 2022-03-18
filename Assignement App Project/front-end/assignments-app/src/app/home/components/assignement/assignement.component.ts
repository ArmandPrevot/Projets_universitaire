import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Role } from 'src/app/auth/models/auth.model';
import { AuthService } from './../../../auth/service/auth.service';
import { Assignement } from './../../models/home.models';
import { HomeService } from './../../service/home.service';

@Component({
  selector: 'app-assignement',
  templateUrl: './assignement.component.html',
  styleUrls: ['./assignement.component.scss'],
})
export class AssignementComponent implements OnInit {
  _id: string = '';
  data!: Assignement;
  isStudent: boolean = false;
  isAdmin: boolean = false;
  isRenduString: string = '';
  user = this.authService.userValue;

  constructor(private homeService: HomeService, private route: ActivatedRoute, private authService: AuthService, private router: Router) {
    let id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this._id = id;
    }
    if (this.user) {
      this.isStudent = this.user.role == Role.Etu;
      this.isAdmin = this.user.role == Role.Admin;
    }
    this.homeService.getAssignementById(this._id).subscribe((data) => {
      this.isRenduString = data.reviewed ? 'Rendu' : 'Non-rendu';
      this.data = data;
    });
  }

  ngOnInit(): void {}

  deleteAssignement() {
    this.homeService.deleteAssignement(this._id).subscribe((data) => {
      console.log(data);
      this.router.navigate(['/assignements']);
    });
  }
}
