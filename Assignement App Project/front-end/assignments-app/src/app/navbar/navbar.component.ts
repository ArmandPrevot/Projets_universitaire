import { Router } from '@angular/router';
import { AuthService } from './../auth/service/auth.service';
import { Component, OnInit } from '@angular/core';
import { Role } from '../auth/models/auth.model';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  isConnectedObs = this.authService.user;
  isProfessor: boolean = false;
  isLogged: boolean = false;
  userAvatar = '';
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.isConnectedObs.subscribe((data) => {
      console.log(data?.url);
      if (data) {
        this.isProfessor = data.role === Role.Prof;
        this.isLogged = true;
        this.userAvatar = data.url;
      }
    });
  }

  connexion() {
    this.router.navigate(['auth/login']);
  }

  deconnexion() {
    this.authService.doLogout();
    this.isLogged = false;
  }
}
