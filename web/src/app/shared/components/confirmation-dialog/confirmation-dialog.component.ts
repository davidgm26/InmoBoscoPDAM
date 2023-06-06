import { Component, OnInit, Inject, Output, EventEmitter } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Property } from 'src/app/interfaces/models/propertyResponse.interface';
import { PropertyService } from 'src/app/services/property.service';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent implements OnInit {

  @Output() confirmed = new EventEmitter<void>();



  constructor(
    private dialogRef: MatDialogRef<ConfirmationDialogComponent>,
    private propertyService: PropertyService,
    private ngxToast: ToastrService,
    @Inject(MAT_DIALOG_DATA) public data: Property
  ) { }


  ngOnInit(): void {}


  onCancel(): void {
    this.dialogRef.close();
  }

  onConfirm(): void {
    this.propertyService.deleteProperty(this.data.id)
      .subscribe(() => {
        this.dialogRef.close();
        this.confirmed.emit();
      }, error => {
        this.ngxToast.error(`No se ha podido eliminar la propiedad con el id:${this.data.id}`, 'Error');
      });

  }
}
