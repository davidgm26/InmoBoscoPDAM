import { Component, Input, OnInit } from '@angular/core';
import {
  Property,
  PropertyResponse,
} from 'src/app/interfaces/models/propertyResponse.interface';
import { PropertyService } from '../../services/property.service';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../services/user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-property-detail',
  templateUrl: './property-detail.component.html',
  styleUrls: ['./property-detail.component.css'],
})
export class PropertyDetailComponent implements OnInit {
  property!: Property;
  userLogged = false;

  constructor(
    private propertyService: PropertyService,
    private route: ActivatedRoute,
    private userService: UserService,
    private ngxToast: ToastrService,
  ) {}

  ngOnInit() {
    const propertyId = this.route.snapshot.paramMap.get('id');
    if(localStorage.getItem('token') != null){
      this.userLogged = true;
    }

    if (propertyId !== null) {
      this.propertyService
        .getPropertyDetail(+propertyId)
        .subscribe((response) => {
          this.property = response;
        });
    } else {
      // Manejar el caso en que no se pudo obtener un ID de propiedad.
    }

  }

  makeFavourite(){
    this.userService.markPropertyAsFavourite(this.property.id).subscribe(
      resp =>{

      },error =>{
        this.ngxToast.error(error.error.message)
      }
    )
  }
}
