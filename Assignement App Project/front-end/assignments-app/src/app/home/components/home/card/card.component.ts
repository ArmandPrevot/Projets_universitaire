import { AssignementCard } from './../../../models/home.models';
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss'],
})
export class CardComponent implements OnInit {
  @Input()
  assignementCard!: AssignementCard;
  @Output() updated = new EventEmitter<AssignementCard>();

  constructor() {}

  ngOnInit(): void {}
}
