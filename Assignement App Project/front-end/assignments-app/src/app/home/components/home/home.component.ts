import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { Role } from 'src/app/auth/models/auth.model';
import { AuthService } from './../../../auth/service/auth.service';
import { AssignementCard } from './../../models/home.models';
import { HomeService } from './../../service/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  user = this.auth.userValue;
  username: String = '';
  userrole!: Role;
  assignementCardListReviewed: AssignementCard[] = [];
  assignementCardListNotReviewed: AssignementCard[] = [];

  isDragDisabled: boolean = false;
  autorizedRolesToDrag = [Role.Admin, Role.Prof];

  count: number = 0;
  currentPage: number = 0;
  step: number = 10;

  constructor(private auth: AuthService, private homeService: HomeService) {
    if (this.user) {
      this.username = this.user.name;
      this.userrole = this.user.role;
    }
    this.isDragDisabled = this.autorizedRolesToDrag.indexOf(this.userrole) == -1;
    this.getData();
  }

  ngOnInit(): void {}

  drop(event: CdkDragDrop<AssignementCard[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      event.previousContainer.data[event.previousIndex].rendu = !event.previousContainer.data[event.previousIndex].rendu;
      this.homeService
        .putAssignement(
          event.previousContainer.data[event.previousIndex]._id,
          event.previousContainer.data[event.previousIndex].rendu,
          event.previousContainer.data[event.previousIndex].matiere_name
        )
        .subscribe(() => {
          transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
        });
    }
  }

  getData(start = 0) {
    this.homeService.getAssignements(start, this.step).subscribe((data) => {
      this.assignementCardListReviewed = data.listReviewed;
      this.assignementCardListNotReviewed = data.listNotReviewed;
      this.count = data.count;
    });
  }

  changePageInput(event: PageEvent) {
    this.currentPage = event.pageIndex;
    this.step = event.pageSize;
    this.getData(this.currentPage * this.step);
  }
}
