import { StateService } from './../services/state.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent implements OnInit {
  isConnected: boolean = false;

  constructor(private stateService: StateService) {}

  ngOnInit(): void {}
}
