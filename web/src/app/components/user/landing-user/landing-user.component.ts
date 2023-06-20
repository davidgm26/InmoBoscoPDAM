import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Property } from 'src/app/interfaces/models/propertyResponse.interface';
import { CityService } from 'src/app/shared/services/city.service';
import { PropertyService } from 'src/app/shared/services/property.service';
@Component({
  selector: 'app-landing-user',
  templateUrl: './landing-user.component.html',
  styleUrls: ['./landing-user.component.css']
})
export class LandingUserComponent implements OnInit {

  propertyList: Property [] = [];
  filtersActive = false;
  page = 0;
  pageSize = 5;
  totalElements = 0;
  cityList: String [] = [];
  selectedType!:String;
  city!:String;


  constructor(
    private propertyService: PropertyService,
    private cityService: CityService,
    private ngxToast:ToastrService
  ) { }

  ngOnInit(): void {
    this.loadData(this.page,this.pageSize);
    console.log(this.propertyList)
  }
  loadData(page: number, pageSize: number) {
    this.propertyService.getProperties(page, pageSize).subscribe(
      resp => {
        this.propertyList = resp.content;
        this.totalElements = resp.totalElements;
      }
    )
    this.cityService.getAllCities().subscribe(
      resp => this.cityList = resp.map(city => city.name)
    )

  }


  search() {
    this.page = 0; // Reinicia la paginación
    this.propertyList = []; // Vacía los resultados actuales

    this.propertyService.filterProperties(this.page, this.pageSize, this.city, this.selectedType).subscribe(
      resp => {
        this.propertyList = resp.content;
        this.filtersActive = true; // Se activan los filtros
      },
      error => {
        this.ngxToast.error('No se pudo realizar la busqueda');
      }
    );
  }

  onScroll(): void {
    if (this.filtersActive) {
      // Si los filtros están activos, usamos el método de filtrado
      this.propertyService.filterProperties(++this.page, this.pageSize, this.city, this.selectedType).subscribe(
        resp => {
          this.propertyList.push(...resp.content);
          this.totalElements = resp.totalElements;
        }
      );
    } else {
      // Si no hay filtros activos, usamos el método estándar
      this.propertyService.getProperties(++this.page, this.pageSize).subscribe(
        resp => {
          this.propertyList.push(...resp.content);
          this.totalElements = resp.totalElements;
        }
      );
    }
  }

  }

