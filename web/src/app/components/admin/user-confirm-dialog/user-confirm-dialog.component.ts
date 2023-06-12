import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/interfaces/models/userResponse.interface';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-confirm-dialog',
  templateUrl: './user-confirm-dialog.component.html',
  styleUrls: ['./user-confirm-dialog.component.css']
})
export class UserConfirmDialogComponent implements OnInit {

  @Output() confirmed = new EventEmitter<void>();



  constructor(
    private dialogRef: MatDialogRef<UserConfirmDialogComponent>,
    private userService: UserService,
    private ngxToast: ToastrService,
    @Inject(MAT_DIALOG_DATA) public data: User
  ) { }


  ngOnInit(): void {}


  onCancel(): void {
    this.dialogRef.close();
  }

  onConfirm(): void {
    this.userService.deleteUSer(this.data.id)
      .subscribe(() => {
        this.dialogRef.close();
        this.confirmed.emit();
      }, error => {
        this.ngxToast.error(`No se ha podido eliminar al usuario con el id:${this.data.id}`, 'Error');
      });

  }
}
