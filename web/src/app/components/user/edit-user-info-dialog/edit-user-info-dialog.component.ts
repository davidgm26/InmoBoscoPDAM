import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/interfaces/models/userResponse.interface';
import { UserService } from 'src/app/shared/services/user.service';

const EMAIL_REGEX = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const PHONE_REGEX = /^(?:(?:\+|00)34)?[6-9]\d{8}$/;
const DNI_REGEX = /^\d{8}[A-HJ-NP-TV-Z]$/;

@Component({
  selector: 'app-edit-user-info-dialog',
  templateUrl: './edit-user-info-dialog.component.html',
  styleUrls: ['./edit-user-info-dialog.component.css'],
})
export class EditUserInfoDialogComponent implements OnInit {
  editForm: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<EditUserInfoDialogComponent>,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private ngxToast: ToastrService,
    @Inject(MAT_DIALOG_DATA) public data: User
  ) {
    this.editForm = this.formBuilder.group({
      firstname: [data.firstname, Validators.required],
      lastname: [data.lastname, Validators.required],
      username: [data.username, Validators.required],
      dni: [data.dni, [Validators.required, Validators.pattern(DNI_REGEX)]],
      phoneNumber: [
        data.phoneNumber,
        [Validators.required, Validators.pattern(PHONE_REGEX)],
      ],
      email: [
        data.email,
        [Validators.required, Validators.pattern(EMAIL_REGEX)],
      ],
      birthdate: [data.birthdate, Validators.required],
    });
  }
  ngOnInit(): void {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    if (this.editForm.valid) {
      const formattedBirthdate = moment
        .utc(this.editForm.value.birthdate)
        .format('YYYY-MM-DD');
      this.editForm.patchValue({ birthdate: formattedBirthdate });

      this.userService.editUserInfoFromUser(this.editForm.value).subscribe(
        (response) => {
          Object.assign(this.data, this.editForm.value);
          this.dialogRef.close(this.editForm.value);
        },
        (error: any) => {
          this.ngxToast.error(error.error.message);
        }
      );
    }
  }
}
