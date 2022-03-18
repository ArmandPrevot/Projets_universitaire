import { StateService } from './../../services/state.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss'],
})
export class SignInComponent implements OnInit {
  username: string;
  passwd: string;
  signInForm: FormGroup;

  constructor(
    private authService: AuthService,
    private stateService: StateService,
    private router: Router
  ) {
    this.username = '';
    this.passwd = '';

    this.signInForm = new FormGroup({
      userI: new FormControl(null, [Validators.required]),
      passI: new FormControl(null, [Validators.required]),
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    this.stateService.isWaiting.next(false);

    if (!this.getUsername?.value && !this.getPwd?.value) {
      return;
    }

    this.authService
      .login(this.getUsername?.value, this.getPwd?.value)
      .subscribe((result) => {
        if (result.access_token !== null)
          this.authService.setToken(result.access_token);
        // console.log(result.access_token);
        this.router.navigate(['/dashboard']);
      });
  }

  // Username Control getter
  get getUsername() {
    return this.signInForm.get('userI');
  }

  // Pwd Control getter
  get getPwd() {
    return this.signInForm.get('passI');
  }
}
