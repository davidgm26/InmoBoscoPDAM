import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Property } from 'src/app/interfaces/models/propertyResponse.interface';
import { PropertyService } from 'src/app/shared/services/property.service';

@Component({
  selector: 'app-property-card',
  templateUrl: './property-card.component.html',
  styleUrls: ['./property-card.component.css']
})
export class PropertyCardComponent implements OnInit {

  @Input() property!: Property;

  constructor(
    private propService: PropertyService,
    private router:Router
    ) { }

  ngOnInit(): void {
  }

  getPropertyImg(property: Property){
    return `http://localhost:8080/download/${property.img}`
  }

  goToDetail(){
    return this.router.navigate(['property-detail'])
  }

}
