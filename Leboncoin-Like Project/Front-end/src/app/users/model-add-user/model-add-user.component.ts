import { HttpService } from './../../services/http.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Subject } from 'rxjs/internal/Subject';
import { UserDisplay } from './../../models/app-models';
import { Component, OnInit } from '@angular/core';
import { MDBModalRef } from 'angular-bootstrap-md';

@Component({
  selector: 'app-model-add-user',
  templateUrl: './model-add-user.component.html',
  styleUrls: ['./model-add-user.component.scss'],
})
export class ModelAddUserComponent implements OnInit {
  public editableRow: UserDisplay;
  isInvalid: boolean = true;
  public saveButtonClicked: Subject<any> = new Subject<any>();
  selectedFiles: FileList;
  formData: FormData;
  form: FormGroup = new FormGroup({
    id: new FormControl({ value: '', disabled: true }),
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    role: new FormControl('', Validators.required),
    // price: new FormControl('', Validators.required),
  });

  constructor(public modalRef: MDBModalRef, private httpService: HttpService) {}

  ngOnInit() {
    // this.form.controls['id'].patchValue(this.editableRow.id);
    // this.form.controls['username'].patchValue(this.editableRow.username);
    // this.form.controls['password'].patchValue(this.editableRow.password);
    // this.form.controls['role'].patchValue(this.editableRow.role);
    // this.form.controls['price'].patchValue(this.editableRow.price);
  }

  editRow() {
    if (this.username === null && this.username === null) {
      return;
    }
    this.editableRow = this.form.getRawValue();
    this.httpService
      .addOneUser({
        username: this.username.value,
        password: this.password?.value,
      })
      .subscribe((result) => {
        console.log(result);
        this.editableRow.annonces = result.annonces.length;
        this.saveButtonClicked.next(this.editableRow);
        this.modalRef.hide();
      });
    // this.editableRow.images = this.formData;
  }

  get username() {
    return this.form.get('username');
  }

  get password() {
    return this.form.get('password');
  }

  get role() {
    return this.form.get('role');
  }
}
