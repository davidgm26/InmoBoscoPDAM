import { Component, OnInit, Input } from '@angular/core';
import { Property } from 'src/app/interfaces/models/propertyResponse.interface';

@Component({
  selector: 'app-property-card',
  templateUrl: './property-card.component.html',
  styleUrls: ['./property-card.component.css']
})
export class PropertyCardComponent implements OnInit {

  @Input() property!: Property;

  constructor() { }

  ngOnInit(): void {
  }

}
