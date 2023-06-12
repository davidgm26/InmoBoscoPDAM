import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Property } from 'src/app/interfaces/models/propertyResponse.interface';
import { PropertyService } from 'src/app/services/property.service';


@Component({
  selector: 'app-edit-property-dialog',
  templateUrl: './edit-property-dialog.component.html',
  styleUrls: ['./edit-property-dialog.component.css']
})
export class EditPropertyDialogComponent implements OnInit {

  editForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<EditPropertyDialogComponent>,
    private propService: PropertyService,
    @Inject(MAT_DIALOG_DATA) public data: Property
  ) {
    this.editForm = this.formBuilder.group({
      lat: [data.lat, Validators.required],
      lon: [data.lon, Validators.required],
      name: [data.name, Validators.required],
      title: [data.title, Validators.required],
      price: [data.price, Validators.required],
      m2: [data.m2, Validators.required],
      description: [data.description, Validators.required],
      totalBedRooms: [data.totalBedRooms, Validators.required],
      totalBaths: [data.totalBaths, Validators.required],
      propertyType: [data.propertyType, Validators.required],
      city: [data.city, Validators.required]
    });
  }

  ngOnInit(): void {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    if (this.editForm.valid) {
      this.propService.editProperty(this.editForm.value, this.data.id)
        .subscribe(
          (response) => {
            Object.assign(this.data, this.editForm.value);
            this.dialogRef.close(this.editForm.value);
          }
        );
    }
  }

}

