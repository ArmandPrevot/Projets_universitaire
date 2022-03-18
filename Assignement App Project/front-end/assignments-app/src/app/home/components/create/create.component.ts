import { Router } from '@angular/router';
import { CreateAssignement } from './../../models/home.models';
import { HomeService } from './../../service/home.service';
import { Role } from '../../../auth/models/auth.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss'],
})
export class CreateComponent implements OnInit {
  createForm: FormGroup;
  formInfos: CreateAssignement[] = [];
  currentInfos!: CreateAssignement;
  constructor(private homeService: HomeService, private router: Router) {
    this.createForm = new FormGroup({
      title_I: new FormControl('', Validators.required),
      matiere_I: new FormControl('', Validators.required),
      description_I: new FormControl('', Validators.required),
    });
    this.homeService.getMatieres().subscribe((formInfo) => {
      this.formInfos = formInfo;
    });
  }

  ngOnInit(): void {}

  changeMatiere(e: any) {
    this.getMatiere?.setValue(e.target.value, {
      onlySelf: true,
    });
    const res = this.formInfos.find((formInfo) => {
      if (formInfo.matiere_nom == this.getMatiere?.value.split('-')[0].trim()) {
        return formInfo;
      } else {
        return false;
      }
    });
    if (res) {
      this.currentInfos = res;
    }
  }

  onSubmit(): void {
    const matiere = this.getMatiere?.value.split('-')[0].trim();
    this.homeService.postAssignement(matiere, this.getTitle?.value, this.getDesc?.value).subscribe((data) => {
      console.log(data);
      this.router.navigate(['/assignements']);
    });
  }

  // Mail Control getter
  get getTitle() {
    return this.createForm.get('title_I');
  }

  // Pwd Control getter
  get getMatiere() {
    return this.createForm.get('matiere_I');
  }

  get getDesc() {
    return this.createForm.get('description_I');
  }
}
