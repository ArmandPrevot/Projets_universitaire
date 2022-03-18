import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './../../service/auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
})
export class AuthComponent implements OnInit {
  loginForm: FormGroup;
  mailRegEx = new RegExp('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$');
  areLogsCorrect: boolean = true;

  constructor(private authService: AuthService, private router: Router) {
    this.loginForm = new FormGroup({
      email_I: new FormControl('', [Validators.required, Validators.minLength(5), Validators.pattern(this.mailRegEx)]),
      pwd_I: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    if (this.authService.isLoggedIn) {
      this.router.navigate(['home']);
    }
  }

  onSubmit(): void {
    if (this.loginForm.invalid) return;

    this.authService.login(this.getMail?.value, this.getPwd?.value).subscribe({
      next: () => {
        this.router.navigate(['assignements']);
      },
      error: (error) => {
        console.log(error);
        if ([401].indexOf(error.status) !== -1) {
          this.areLogsCorrect = false;
        }
      },
    });
  }

  // Mail Control getter
  get getMail() {
    return this.loginForm.get('email_I');
  }

  // Pwd Control getter
  get getPwd() {
    return this.loginForm.get('pwd_I');
  }
}
