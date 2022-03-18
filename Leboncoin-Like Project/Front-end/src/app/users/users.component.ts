import { ModalEditUserComponent } from './modal-edit-user/modal-edit-user.component';
import { HttpService } from './../services/http.service';
import { StateService } from './../services/state.service';
import {
  AnnonceResult,
  UserDisplay,
  UserToModify,
} from './../models/app-models';
import { UserResult } from 'src/app/models/app-models';
import {
  ChangeDetectorRef,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import {
  MdbTableDirective,
  MdbTablePaginationComponent,
  MDBModalRef,
  MDBModalService,
} from 'angular-bootstrap-md';
import { ModelAddUserComponent } from './model-add-user/model-add-user.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UsersComponent implements OnInit {
  @ViewChild(MdbTableDirective, { static: true })
  mdbTable: MdbTableDirective;
  @ViewChild(MdbTablePaginationComponent, { static: true })
  mdbTablePagination: MdbTablePaginationComponent;
  @ViewChild('row', { static: true }) row: ElementRef;

  elements: UserDisplay[] = [];
  headElements = ['id', 'Nom', 'Rôle', 'Annonces', 'Éditer'];

  modalRef: MDBModalRef;
  userList: UserResult[];
  annoncesList: AnnonceResult[];
  isLoading: boolean = false;

  constructor(
    private cdRef: ChangeDetectorRef,
    private modalService: MDBModalService,
    private stateService: StateService,
    private httpService: HttpService
  ) {}

  ngOnInit() {
    this.refreshData();
  }

  refreshData() {
    this.stateService.getAllData().subscribe((allDataResult) => {
      allDataResult.map(([users, annonces]) => {
        this.userList = users;
        this.annoncesList = annonces;
        this.stateService.userList = this.userList;
        this.stateService.annoncesList = this.annoncesList;
      });

      this.userList.forEach((user) => {
        this.elements.push({
          id: user.id,
          username: user.username,
          role: user.role,
          annonces: user.annonces.length,
        });
      });
      this.mdbTablePagination.setMaxVisibleItemsNumberTo(5);
      this.mdbTablePagination.calculateFirstItemIndex();
      this.mdbTablePagination.calculateLastItemIndex();
      this.mdbTable.setDataSource(this.elements);
    });
  }

  ngAfterViewInit() {
    this.cdRef.detectChanges();
  }

  editRow(el: UserDisplay) {
    const elementIndex = this.elements.findIndex(
      (elem: UserDisplay) => el === elem
    );
    const modalOptions = {
      data: {
        editableRow: el,
      },
    };
    this.modalRef = this.modalService.show(
      ModalEditUserComponent,
      modalOptions
    );
    this.modalRef.content.saveButtonClicked.subscribe((newElement) => {
      this.httpService.editOneUser(newElement).subscribe((response) => {
        console.log(response);
        const responseElement = {
          username: response.username,
          id: response.id,
          role: response.role,
          annonces: response.annonces.length,
        };
        this.elements[elementIndex] = responseElement;
      });
    });
    this.mdbTable.setDataSource(this.elements);
  }

  removeRow(el: UserDisplay) {
    const elementIndex = this.elements.findIndex(
      (elem: UserDisplay) => el.id === elem.id
    );
    this.httpService.deleteOneUser(el).subscribe(() => {
      // this.mdbTable.getDataSource().forEach((el: UserDisplay, index: any) => {
      //   el.id = index + 1;
      // });
    });
    this.mdbTable.removeRow(elementIndex);
    // this.mdbTable.setDataSource(this.elements);
  }

  add() {
    console.log('add');
    this.modalRef = this.modalService.show(ModelAddUserComponent);

    this.modalRef.content.saveButtonClicked.subscribe(
      (newElement: UserDisplay) => {
        this.elements.push(newElement);
        newElement.role = ['ROLE_USER'];
        this.mdbTable.setDataSource(this.elements);
      }
    );
    this.mdbTable.setDataSource(this.elements);
  }
}
