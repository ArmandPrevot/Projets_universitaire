import { AnnonceDisplay } from './../../models/app-models';
import { MDBModalRef } from 'angular-bootstrap-md';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs/internal/Subject';
import { formatDate } from '@angular/common';
// import {Subject} from "rxjs";

@Component({
  selector: 'app-modal-edit',
  templateUrl: './modal-edit.component.html',
  styleUrls: ['./modal-edit.component.scss'],
})
export class ModalEditComponent {
  public editableRow: AnnonceDisplay;
  public saveButtonClicked: Subject<any> = new Subject<any>();
  selectedFiles: FileList;
  formData: FormData;
  form: FormGroup = new FormGroup({
    id: new FormControl({ value: '', disabled: true }),
    title: new FormControl('', Validators.required),
    description: new FormControl('', Validators.required),
    price: new FormControl('', Validators.required),
  });

  files: File[];

  onFileAdd(file: File) {
    this.files.push(file);
  }

  onFileRemove() {
    this.files = [];
  }

  constructor(public modalRef: MDBModalRef) {}

  ngOnInit() {
    this.form.controls['id'].patchValue(this.editableRow.id);
    this.form.controls['title'].patchValue(this.editableRow.title);
    this.form.controls['description'].patchValue(this.editableRow.description);
    this.form.controls['price'].patchValue(this.editableRow.price);
  }

  editRow() {
    this.editableRow = this.form.getRawValue();
    this.editableRow.images = this.formData;
    this.saveButtonClicked.next(this.editableRow);
    this.modalRef.hide();
  }

  selectFiles(event) {
    let fileList: FileList = event.target.files;
    console.log(event.target.files);
    if (fileList.length > 0) {
      if (fileList.length > 5) {
        return;
      }

      let file: File = fileList[0];
      console.log(file);
      this.formData = new FormData();
      this.formData.append('file', file, file.name);
    }
  }

  get title() {
    return this.form.get('title');
  }

  get description() {
    return this.form.get('description');
  }

  get price() {
    return this.form.get('price');
  }

  get images() {
    return this.form.get('images');
  }
}
