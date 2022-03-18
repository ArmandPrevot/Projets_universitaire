import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { map, Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import {
  Assignement,
  AssignementCard,
  AssignementCardLists,
  AssignementResult,
  AssignementResultPost,
  CreateAssignement,
  Matiere,
} from './../models/home.models';

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  assignementList: Assignement[] = [];
  assignementCardList: AssignementCard[] = [];
  maxElements: number = 10;

  constructor(private http: HttpClient, private toastr: ToastrService) {}

  getAssignements(start?: number, end?: number): Observable<AssignementCardLists> {
    return this.http.get<AssignementResult>(`${environment.apiUrl}/assignments?start=${start}&end=${end}`).pipe(
      map((assignementResult) => {
        console.log(assignementResult.assignments);
        this.assignementList = assignementResult.assignments;
        this.maxElements = assignementResult.count;
        let assignementResRewied: AssignementCard[] = [];
        let assignementResNotRewied: AssignementCard[] = [];
        this.assignementList.forEach((assignement) => {
          let res: AssignementCard = {
            _id: assignement._id,
            date: assignement.date,
            rendu: assignement.reviewed,
            note: assignement.grade,
            title: assignement.title,
            auteur_name: assignement.author.name,
            auteur_role: assignement.author.role,
            desc: assignement.description,
            matiere_logo: assignement.matiere.logo,
            matiere_name: assignement.matiere.nom,
            prof_avatar: assignement.matiere.professor.avatar,
          };
          if (res.rendu) {
            assignementResRewied.push(res);
          } else {
            assignementResNotRewied.push(res);
          }
        });
        const resultat: AssignementCardLists = {
          listNotReviewed: assignementResNotRewied,
          listReviewed: assignementResRewied,
          count: this.maxElements,
        };
        return resultat;
      })
    );
  }

  getMatieres(): Observable<CreateAssignement[]> {
    return this.http.get<Matiere[]>(`${environment.apiUrl}/matieres`).pipe(
      map((matieresResult) => {
        const result = matieresResult;
        const listToReturn: CreateAssignement[] = [];
        result.forEach((matiere) => {
          let listItem: CreateAssignement = {
            matiere_img: matiere.logo,
            matiere_nom: matiere.nom,
            prof_name: matiere.professor.name,
          };
          listToReturn.push(listItem);
        });
        return listToReturn;
      })
    );
  }

  postAssignement(nameMatiere: string, titre: string, desc: string): Observable<AssignementResultPost> {
    return this.http.post<AssignementResultPost>(`${environment.apiUrl}/assignments`, { matiereName: nameMatiere, title: titre, description: desc }).pipe(
      tap(() => {
        this.toastr.success('Assignement créé', 'Bravo !');
      })
    );
  }

  getAssignementById(id: string): Observable<Assignement> {
    return this.http.get<Assignement>(`${environment.apiUrl}/assignments/${id}`);
  }

  putAssignement(
    id: string,
    reviewed?: boolean,
    nameMatiere?: string,
    titre?: string,
    desc?: string,
    grade?: number,
    comments?: string
  ): Observable<AssignementResultPost> {
    return this.http
      .put<AssignementResultPost>(`${environment.apiUrl}/assignments/${id}`, {
        grade,
        comments,
        reviewed,
        matiereName: nameMatiere,
        title: titre,
        description: desc,
      })
      .pipe(
        tap(() => {
          this.toastr.success('Assignement modifié', 'Bravo !');
        })
      );
  }

  deleteAssignement(_id: string): Observable<any> {
    return this.http.delete<any>(`${environment.apiUrl}/assignments/${_id}`).pipe(
      tap(() => {
        this.toastr.info('Assignement supprimé', 'Bravo !');
      })
    );
  }
}
