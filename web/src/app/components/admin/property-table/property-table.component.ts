import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { tap } from 'rxjs';
import { CityResponse } from 'src/app/interfaces/dtos/cityResponse';
import { Property } from 'src/app/interfaces/models/propertyResponse.interface';
import { CityService } from 'src/app/services/city.service';
import { PropertyService } from 'src/app/services/property.service';
import { ConfirmationDialogComponent } from 'src/app/components/admin/confirmation-dialog/confirmation-dialog.component';
import { EditPropertyDialogComponent } from 'src/app/shared/components/edit-property-dialog/edit-property-dialog.component';
import { UtilsService } from 'src/app/shared/services/utils.service';

@Component({
  selector: 'app-property-table',
  templateUrl:'./property-table.component.html',
  styleUrls: ['./property-table.component.css']
})
export class PropertyTableComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  page = 0;
  pageSize = 5;
  totalElements = 0;
  propertyList: Property [] = [];
  cityList!: CityResponse;
  filteredPropertyList: any[];
  citySelected!: CityResponse;

  constructor(
    private propertyService: PropertyService,
    private utilsService: UtilsService,
    private dialog: MatDialog,
    private cityService: CityService,
  ) {
    this.filteredPropertyList = this.propertyList;

  }

  ngOnInit(): void {
    this.loadData(this.page,this.pageSize);
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
          tap((event) => this.loadData(event.pageIndex, event.pageSize))
      )
      .subscribe();
  }

  loadData(page: number, pageSize: number) {
    this.propertyService.getProperties(page, pageSize).subscribe(
      resp => {
        this.propertyList = resp.content;
        this.totalElements = resp.totalElements;
      }
    )
  }

  editProperty(property: Property) {
    this.dialog.open(EditPropertyDialogComponent, {
      data: property,
    });
  }

  deleteProperty(property: Property) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: property
    });

    dialogRef.componentInstance.confirmed.subscribe(() => {
      this.loadData(this.page, this.pageSize);
    });
  }


}
