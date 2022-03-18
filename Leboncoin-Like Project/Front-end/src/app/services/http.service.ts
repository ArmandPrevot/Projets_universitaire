import {
  User,
  Users,
  Annonce,
  Annonces,
  UserResult,
  AnnonceResult,
  UserToModify,
  UserDisplay,
  UserLogin,
  AnnonceDisplay,
} from './../models/app-models';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  constructor(private http: HttpClient) {}

  // GET
  getOneUser(userId: number): Observable<User> {
    return this.http.get<User>(`${environment.apiURL}/users/${userId}`);
  }

  getAllUsers(): Observable<UserResult[]> {
    return this.http.get<UserResult[]>(`${environment.apiURL}/users`);
  }

  // POST
  addOneUser(userToSend: UserLogin): Observable<User> {
    return this.http.post<User>(`${environment.apiURL}/user/`, userToSend);
  }

  // PUT
  editOneUser(userToEdit: UserDisplay) {
    return this.http.put<UserResult>(
      `${environment.apiURL}/user/${userToEdit.id}`,
      {
        username: userToEdit.username,
        role: userToEdit.role,
      }
    );
  }

  // DELETE
  deleteOneUser(userToDelete: UserDisplay) {
    const headers = { responseType: 'text' };
    return this.http.delete<string>(
      `${environment.apiURL}/user/${userToDelete.id}`,
      { headers: { skip: 'true' } }
    );
  }

  // Annonce

  // GET
  getOneAnnonce(annonceId: number): Observable<Annonce> {
    return this.http.get<Annonce>(
      `${environment.apiURL}/annonces/${annonceId}`
    );
  }

  getAllAnnonces(): Observable<AnnonceResult[]> {
    return this.http.get<AnnonceResult[]>(`${environment.apiURL}/annonces`);
  }

  // POST
  addOneAnnonce(annonceToSend: Annonce) {
    return this.http.post<Annonce>(
      `${environment.apiURL}/annonces`,
      annonceToSend
    );
  }

  // PUT
  editOneAnnonce(annonceToModify: Annonce) {
    return this.http.put<Annonce>(
      `${environment.apiURL}/annonces/${annonceToModify.id}`,
      annonceToModify
    );
  }

  // DELETE
  deleteAnnonceUser(annonceToDelete: AnnonceDisplay) {
    const headers = { responseType: 'text' };
    return this.http.delete<string>(
      `${environment.apiURL}/annonce/${annonceToDelete.id}`,
      { headers: { skip: 'true' } }
    );
  }
}
