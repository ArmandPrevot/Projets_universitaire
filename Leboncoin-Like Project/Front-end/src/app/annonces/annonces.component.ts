import { HttpService } from './../services/http.service';
import { AnnonceResult, AnnonceDisplay, Annonce } from './../models/app-models';
import { StateService } from './../services/state.service';
import { UserResult } from 'src/app/models/app-models';
import { ModalEditComponent } from './modal-edit/modal-edit.component';
import {
  ChangeDetectorRef,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import {
  MDBModalRef,
  MDBModalService,
  MdbTableDirective,
  MdbTablePaginationComponent,
} from 'angular-bootstrap-md';
@Component({
  selector: 'app-annonces',
  templateUrl: './annonces.component.html',
  styleUrls: ['./annonces.component.scss'],
})
export class AnnoncesComponent implements OnInit {
  @ViewChild(MdbTableDirective, { static: true })
  mdbTable: MdbTableDirective;
  @ViewChild(MdbTablePaginationComponent, { static: true })
  mdbTablePagination: MdbTablePaginationComponent;
  @ViewChild('row', { static: true }) row: ElementRef;

  elements: AnnonceDisplay[] = [];
  headElements = ['id', 'Titre', 'Prix', 'Description', 'Images', 'Éditer'];

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

      this.annoncesList.forEach((annonce) => {
        this.elements.push({
          id: annonce.id,
          title: annonce.title,
          price: annonce.price,
          description: annonce.description,
          nbImage: annonce.images.length,
          images: new FormData(),
        });
      });

      this.mdbTable.setDataSource(this.elements);
      this.mdbTablePagination.setMaxVisibleItemsNumberTo(5);
      this.mdbTablePagination.calculateFirstItemIndex();
      this.mdbTablePagination.calculateLastItemIndex();
    });
  }

  ngAfterViewInit() {
    this.cdRef.detectChanges();
  }

  editRow(el: AnnonceDisplay) {
    const annonceToSend: Annonce = {
      id: el.id,
      title: el.title,
      description: el.description,
      price: el.price,
      images: el.images,
    };
    const elementIndex = this.elements.findIndex(
      (elem: AnnonceDisplay) => el === elem
    );
    const modalOptions = {
      data: {
        editableRow: el,
      },
    };
    this.modalRef = this.modalService.show(ModalEditComponent, modalOptions);
    this.modalRef.content.saveButtonClicked.subscribe(
      (newElement: AnnonceDisplay) => {
        this.httpService.editOneAnnonce(newElement).subscribe((response) => {
          console.log(response);
        });
        this.elements[elementIndex] = newElement;
      }
    );
    this.mdbTable.setDataSource(this.elements);
  }

  removeRow(el: AnnonceDisplay) {
    const elementIndex = this.elements.findIndex(
      (elem: AnnonceDisplay) => el.id === elem.id
    );
    this.httpService.deleteAnnonceUser(el).subscribe(() => {
      console.log('deleted');
    });
    this.mdbTable.removeRow(elementIndex);
    this.mdbTable.setDataSource(this.elements);
  }

  add() {}
}
