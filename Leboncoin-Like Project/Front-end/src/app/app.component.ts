import { StateService } from './services/state.service';
import { Component } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'front-UI';
  isLoading: boolean;
  constructor(private stateService: StateService) {
    this.isLoading = this.stateService.isWaiting.getValue();
  }
}
