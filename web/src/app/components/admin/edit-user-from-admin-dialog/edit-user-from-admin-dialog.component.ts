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
const NAME_REGEX = /^[a-zA-ZÁÉÍÓÚáéíóúÑñ\s-]+$/;
const USERNAME_REGEX = /^[a-zA-Z\s-]+$/;
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
    private ngxToast: ToastrService,
    @Inject(MAT_DIALOG_DATA) public data: User
  ) {
    this.editForm = this.formBuilder.group({
      firstname:[data.firstname, [Validators.required,Validators.pattern(NAME_REGEX)]],
      lastname: [data.lastname,[Validators.required,Validators.pattern(NAME_REGEX)]],
      username: [data.username,[Validators.required,Validators.pattern(USERNAME_REGEX)]],
      dni: [data.dni,[Validators.required,Validators.pattern(DNI_REGEX)]],
      phoneNumber: [data.phoneNumber,[Validators.required,Validators.pattern(PHONE_REGEX)]],
      email: [data.email,[Validators.required,Validators.pattern(EMAIL_REGEX)]],
      birthdate: [data.birthdate, Validators.required],
    });
  }
  maxDate = new Date();
  minDate = new Date().setFullYear(new Date().getFullYear() - 70);

  ngOnInit(): void {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    if (this.editForm.valid) {
      const formattedBirthdate = moment(this.editForm.value.birthdate).format('YYYY-MM-DD');
      this.editForm.patchValue({ birthdate: formattedBirthdate });

      this.userService.editUserInfoFromAdmin(this.data.id, this.editForm.value).subscribe((response) => {
        Object.assign(this.data, this.editForm.value);
        this.dialogRef.close(this.editForm.value);
        this.ngxToast.success('Usuario modificado con éxito')
      });
    }
  }

}
