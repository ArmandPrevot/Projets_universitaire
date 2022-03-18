import { StateService } from './../../services/state.service';
import { HttpService } from './../../services/http.service';
import { Component, Input, OnInit } from '@angular/core';
import { UserResult } from 'src/app/models/app-models';

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.scss'],
})
export class ChartsComponent implements OnInit {
  @Input()
  userNb: number = 0;
  @Input()
  annonceNb: number = 0;
  userList: UserResult[] = [];
  constructor(private stateService: StateService) {}

  ngOnInit(): void {
    this.userList = this.stateService.userList;
    console.log(this.userList);
    this.initPie();
    this.initLine();
  }

  // Line chart
  public chartLineType: string = 'line';
  public chartLineDatasets: Array<any> = [];
  public chartLineLabels: Array<any> = [];
  public chartLineColors: Array<any> = [
    {
      backgroundColor: 'rgba(105, 0, 132, .2)',
      borderColor: 'rgba(200, 99, 132, .7)',
      borderWidth: 2,
    },
    {
      backgroundColor: 'rgba(0, 137, 132, .2)',
      borderColor: 'rgba(0, 10, 130, .7)',
      borderWidth: 2,
    },
  ];
  public chartLineOptions: any = {
    responsive: true,
  };

  initLine() {
    const annoncesPerUserNb: number[] = [];
    const usernames: string[] = [];

    this.userList.forEach((user) => {
      annoncesPerUserNb.push(user.annonces.length);
      usernames.push(user.username);
    });

    this.chartLineDatasets = [
      { data: annoncesPerUserNb, label: 'Annonces par utilisateurs' },
    ];
    this.chartLineLabels = usernames;
  }

  // Pie chart
  public chartPieType: string = 'pie';
  public chartPieDatasets: Array<any> = [];
  public chartPieColors: Array<any> = [
    {
      backgroundColor: ['#F7464A', '#46BFBD'],
      hoverBackgroundColor: ['#FF5A5E', '#5AD3D1'],
      borderWidth: 2,
    },
  ];
  public chartPieOptions: any = {
    responsive: true,
  };
  public chartPieLabels: Array<any> = ['Utilisateurs', 'Annonces'];

  initPie() {
    // console.log(this.userNb);
    this.chartPieDatasets = [
      { data: [this.userNb, this.annonceNb], label: 'Nombre total' },
    ];
  }

  public chartClicked(event: Event): void {}
  public chartHovered(e: any): void {}
}
