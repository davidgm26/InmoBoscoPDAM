import { Component, OnInit } from '@angular/core';
import { Property } from 'src/app/interfaces/models/propertyResponse.interface';
import { PropertyService } from 'src/app/shared/services/property.service';
@Component({
  selector: 'app-landing-user',
  templateUrl: './landing-user.component.html',
  styleUrls: ['./landing-user.component.css']
})
export class LandingUserComponent implements OnInit {

  propertyList: Property [] = [];
  page = 0;
  pageSize = 5;
  totalElements = 0;


  constructor(
    private propertyService: PropertyService,
  ) { }

  ngOnInit(): void {
    this.loadData(this.page,this.pageSize);
    console.log(this.propertyList)
  }
  loadData(page: number, pageSize: number) {
    debugger
    this.propertyService.getProperties(page, pageSize).subscribe(
      resp => {
        this.propertyList = resp.content;
        this.totalElements = resp.totalElements;
      }
    )
  }
  onScroll(): void {
    this.propertyService
    .getProperties(++this.page,this.pageSize).subscribe(
      resp => {
        this.propertyList = resp.content;
        this.totalElements = resp.totalElements;
        this.propertyList.push(...resp.content);
      })
  }

}
