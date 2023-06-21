import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Property } from 'src/app/interfaces/models/propertyResponse.interface';
import { PropertyService } from 'src/app/shared/services/property.service';

@Component({
  selector: 'app-favourite-property-card',
  templateUrl: './favourite-property-card.component.html',
  styleUrls: ['./favourite-property-card.component.css']
})
export class FavouritePropertyCardComponent implements OnInit {

  @Input() property!: Property;
  @Output() propertyDeleted = new EventEmitter<number>();




  constructor(
    private propService: PropertyService,
    private router:Router,
    private ngxToast: ToastrService,
    ) { }

  ngOnInit(): void {
  }

  getPropertyImg(property: Property){
    return `http://localhost:8080/download/${property.img}`
  }

  goToDetail(){
    return this.router.navigate(['property-detail'])
  }

  deleteFromFavourites(){
    this.propService.removeFromFavourite(this.property.id).subscribe(
      resp => {
        this.propertyDeleted.emit(this.property.id);
        this.ngxToast.success('La propiedad se eliminó de la lista de favoritos');
      },
    ),(error:any)=>{
      this.ngxToast.error(error.error.message)
    }

  }

}
