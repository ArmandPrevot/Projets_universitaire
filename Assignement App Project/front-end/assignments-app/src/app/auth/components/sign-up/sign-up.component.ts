import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Role } from '../../models/auth.model';
import { AuthService } from './../../service/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss'],
})
export class SignUpComponent implements OnInit {
  signupForm: FormGroup;
  pwdRegEx = new RegExp('^(?=.*[A-Za-z])(?=.*d)[A-Za-zd]{8,}$');
  mailRegEx = new RegExp('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$');
  areLogsCorrect: boolean = true;
  roles: Role[] = [Role.Admin, Role.Etu, Role.Prof];
  imgPath: string = '';

  constructor(private authService: AuthService, private router: Router) {
    this.signupForm = new FormGroup({
      email_I: new FormControl('', [Validators.required, Validators.minLength(5), Validators.pattern(this.mailRegEx)]),
      pwd_I: new FormControl('', Validators.required),
      role_I: new FormControl('', Validators.required),
      name_I: new FormControl('', Validators.required),
      url_I: new FormControl(''),
      nomMatiere_I: new FormControl(''),
      descMatiere_i: new FormControl(''),
      photoMatiere_i: new FormControl(''),
    });
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.signupForm.invalid) return;

    const roleIndex = this.roles.indexOf(this.getRole?.value.split(':')[1].trim());
    this.authService
      .signup(
        this.getName?.value,
        this.getMail?.value,
        this.getPwd?.value,
        this.roles[roleIndex],
        this.getUrl?.value,
        this.getNomMatiere?.value,
        this.getUrlPhoto?.value,
        this.getDescMatiere?.value
      )
      .subscribe(() => {
        this.router.navigate(['assignements']);
      });
  }

  changeRole(e: any) {
    this.getRole?.setValue(e.target.value, {
      onlySelf: true,
    });
  }

  // Mail Control getter
  get getMail() {
    return this.signupForm.get('email_I');
  }

  // Pwd Control getter
  get getPwd() {
    return this.signupForm.get('pwd_I');
  }

  get getRole() {
    return this.signupForm.get('role_I');
  }

  get getName() {
    return this.signupForm.get('name_I');
  }

  get getUrl() {
    return this.signupForm.get('url_I');
  }

  get getUrlPhoto() {
    return this.signupForm.get('photoMatiere_i');
  }

  get getNomMatiere() {
    return this.signupForm.get('nomMatiere_I');
  }

  get getDescMatiere() {
    return this.signupForm.get('descMatiere_i');
  }
}
