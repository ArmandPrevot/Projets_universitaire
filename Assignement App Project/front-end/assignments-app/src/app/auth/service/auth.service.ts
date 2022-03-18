import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { User, Role } from '../models/auth.model';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { Injectable } from '@angular/core';
import { LoginResult } from 'src/app/auth/models/auth.model';
import { Router } from '@angular/router';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private userSubject: BehaviorSubject<User | null>;
  public user: Observable<User | null>;

  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject<User | null>(this.tokenToUser());
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User | null {
    return this.userSubject.value;
  }

  setToken(tkn: string) {
    localStorage.setItem('access_token', tkn);
    this.userSubject.next(this.tokenToUser());
  }

  login(mail: string, pwd: string): Observable<LoginResult> {
    return this.http.post<LoginResult>(`${environment.apiUrl}/login`, { email: mail, password: pwd }).pipe(
      tap((loginResult) => {
        this.setToken(loginResult.token);
      })
    );
  }

  signup(
    name: string,
    mail: string,
    pwd: string,
    role: Role,
    url?: string,
    matiereName?: string,
    matiereUrl?: string,
    matiereDesc?: string
  ): Observable<LoginResult> {
    return this.http
      .post<LoginResult>(`${environment.apiUrl}/signup`, {
        name: name,
        email: mail,
        password: pwd,
        role: role,
        avatar: url,
        matiereName: matiereName,
        matiereAvatar: matiereUrl,
        matiereDescription: matiereDesc,
      })
      .pipe(
        tap((loginResult) => {
          this.setToken(loginResult.token);
        })
      );
  }

  tokenToUser(): User | null {
    const authtoken = localStorage.getItem('access_token');
    if (authtoken) {
      const tokenInfo = this.getDecodedAccessToken(authtoken);
      const currentUser: User = {
        name: tokenInfo.name,
        email: tokenInfo.email,
        role: tokenInfo.role,
        url: tokenInfo.avatar,
      };
      return currentUser;
    }
    return null;
  }

  getDecodedAccessToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }
  }

  get isLoggedIn(): boolean {
    const authToken = localStorage.getItem('access_token');
    return authToken !== null ? true : false;
  }

  getToken() {
    return localStorage.getItem('access_token');
  }

  doLogout() {
    let removeToken = localStorage.removeItem('access_token');
    if (removeToken == null) {
      this.userSubject.next(null);
      this.router.navigate(['auth']);
    }
  }
}
