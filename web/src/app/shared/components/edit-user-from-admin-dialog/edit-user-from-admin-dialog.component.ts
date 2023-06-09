import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import * as moment from 'moment';
import { User } from 'src/app/interfaces/models/userResponse.interface';
import { UserService } from 'src/app/services/user.service';

const EMAIL_REGEX = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const PHONE_REGEX = /^(?:(?:\+|00)34)?[6-9]\d{8}$/;
const DNI_REGEX = /^\d{8}[A-HJ-NP-TV-Z]$/;

@Component({
  selector: 'app-edit-user-from-admin-dialog',
  templateUrl: './edit-user-from-admin-dialog.component.html',
  styleUrls: ['./edit-user-from-admin-dialog.component.css'],
})
export class EditUserFromAdminDialogComponent implements OnInit {
  editForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<EditUserFromAdminDialogComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: User
  ) {
    this.editForm = this.formBuilder.group({
      firstname: [data.firstname, Validators.required],
      lastname: [data.lastname, Validators.required],
      username: [data.username, Validators.required],
      dni: [data.dni, [Validators.required,Validators.pattern(DNI_REGEX)]],
      phoneNumber: [data.phoneNumber, [Validators.required,Validators.pattern(PHONE_REGEX)]],
      email: [data.email, [Validators.required,Validators.pattern(EMAIL_REGEX)]],
      birthdate: [data.birthdate, Validators.required],
    });
  }

  ngOnInit(): void {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    if (this.editForm.valid) {
      const formattedBirthdate = moment.utc(this.editForm.value.birthdate).format('YYYY-MM-DD');
      this.editForm.patchValue({ birthdate: formattedBirthdate });

      this.userService.editUserInfoFromAdmin(this.data.id, this.editForm.value).subscribe((response) => {
        Object.assign(this.data, this.editForm.value);
        this.dialogRef.close(this.editForm.value);
      });
    }
  }

}
