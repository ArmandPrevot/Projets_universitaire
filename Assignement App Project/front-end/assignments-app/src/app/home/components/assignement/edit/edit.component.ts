import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { Role, User } from '../../../../auth/models/auth.model';
import { AuthService } from './../../../../auth/service/auth.service';
import { Assignement, CreateAssignement } from './../../../models/home.models';
import { HomeService } from './../../../service/home.service';

@Component({
  selector: 'assignement-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss'],
})
export class EditComponent implements OnInit {
  _id = '';
  data!: Assignement;
  formInfos: CreateAssignement[] = [];
  editForm: FormGroup;
  currentInfos!: CreateAssignement;
  user = this.authService.userValue;
  currentUser!: User;
  isProf!: boolean;

  constructor(private homeService: HomeService, private route: ActivatedRoute, private authService: AuthService, private router: Router) {
    let id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this._id = id;
    }
    if (this.user) {
      this.currentUser = this.user;
    }
    this.editForm = new FormGroup({
      grade_I: new FormControl(''),
      comments_I: new FormControl(''),
      description_I: new FormControl(''),
      matiere_I: new FormControl(''),
      title_I: new FormControl(''),
      reviewed_I: new FormControl(''),
    });

    const $matiere = this.homeService.getMatieres();
    const $data = this.homeService.getAssignementById(this._id);
    combineLatest([$matiere, $data]).subscribe(([formInfos, data]) => {
      this.formInfos = formInfos;
      this.currentInfos = {
        matiere_img: data.matiere.logo,
        matiere_nom: data.matiere.nom,
        prof_name: data.matiere.professor.name,
      };

      this.title_I?.setValue(data.title);
      this.matiere_I?.setValue(data.matiere.nom);
      this.description_I?.setValue(data.description);
      this.comments_I?.setValue(data.comments);
      this.grade_I?.setValue(data.grade);
      this.reviewed_I?.setValue(data.reviewed);

      switch (this.currentUser.role) {
        case Role.Prof:
          this.isProf = true;
          this.title_I?.disable;
          this.matiere_I?.disable;
          this.description_I?.disable;
      }

      this.data = data;
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    let matiere_name = '';
    let reviewed = false;
    matiere_name = this.matiere_I?.value.includes(':') ? this.matiere_I?.value.split(':')[1].trim() : this.matiere_I?.value;
    reviewed = this.grade_I?.value ? this.reviewed_I?.value : null;
    this.homeService
      .putAssignement(this._id, reviewed, matiere_name, this.title_I?.value, this.description_I?.value, this.grade_I?.value, this.comments_I?.value)
      .subscribe(() => {
        this.router.navigate(['/assignement', this._id]);
      });
  }

  changeMatiere(e: any) {
    this.matiere_I?.setValue(e.target.value, {
      onlySelf: true,
    });
    const res = this.formInfos.find((formInfo) => {
      if (formInfo.matiere_nom == this.matiere_I?.value.split(':')[1].trim()) {
        return formInfo;
      } else {
        return false;
      }
    });
    if (res) {
      this.currentInfos = res;
    }
  }

  // Mail Control getter
  get grade_I() {
    return this.editForm.get('grade_I');
  }

  // Pwd Control getter
  get comments_I() {
    return this.editForm.get('comments_I');
  }

  get title_I() {
    return this.editForm.get('title_I');
  }
  get matiere_I() {
    return this.editForm.get('matiere_I');
  }
  get description_I() {
    return this.editForm.get('description_I');
  }
  get reviewed_I() {
    return this.editForm.get('reviewed_I');
  }
}
