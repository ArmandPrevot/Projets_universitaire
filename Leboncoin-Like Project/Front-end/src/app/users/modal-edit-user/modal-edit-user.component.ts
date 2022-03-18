import { MDBModalRef } from 'angular-bootstrap-md';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Subject } from 'rxjs/internal/Subject';
import { UserDisplay } from './../../models/app-models';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-modal-edit-user',
  templateUrl: './modal-edit-user.component.html',
  styleUrls: ['./modal-edit-user.component.scss'],
})
export class ModalEditUserComponent implements OnInit {
  public editableRow: UserDisplay;
  isInvalid: boolean = true;
  public saveButtonClicked: Subject<any> = new Subject<any>();
  selectedFiles: FileList;
  formData: FormData;
  form: FormGroup = new FormGroup({
    id: new FormControl({ value: '', disabled: true }),
    username: new FormControl('', Validators.required),
    role: new FormControl('', Validators.required),
    // price: new FormControl('', Validators.required),
  });

  constructor(public modalRef: MDBModalRef) {}

  ngOnInit() {
    this.form.controls['id'].patchValue(this.editableRow.id);
    this.form.controls['username'].patchValue(this.editableRow.username);
    // this.form.controls['role'].patchValue(this.editableRow.role);
    // this.form.controls['price'].patchValue(this.editableRow.price);
  }

  editRow() {
    this.editableRow = this.form.getRawValue();
    // this.editableRow.images = this.formData;
    this.saveButtonClicked.next(this.editableRow);
    this.modalRef.hide();
  }

  get username() {
    return this.form.get('username');
  }

  // get role() {
  //   return this.form.get('role');
  // }

  // get price() {
  // //   return this.form.get('price');
  // // }

  // // get images() {
  // //   return this.form.get('images');
  // // }
}
