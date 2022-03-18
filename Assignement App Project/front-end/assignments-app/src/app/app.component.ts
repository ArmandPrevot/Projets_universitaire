import { AppService } from './service/app.service';
import { Component, ChangeDetectorRef, AfterContentChecked } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'assignments-app';
  isLoadingObs = this.appService.isLoadingObs;

  constructor(private appService: AppService) {}

  ngOnInit() {}
}
