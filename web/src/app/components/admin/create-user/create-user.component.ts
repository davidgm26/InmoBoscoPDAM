import { Component, OnInit , Inject, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import * as moment from 'moment';
import { UserService } from 'src/app/shared/services/user.service';


const EMAIL_REGEX = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const PHONE_REGEX = /^(?:(?:\+|00)34)?[6-9]\d{8}$/;
const DNI_REGEX = /^\d{8}[A-HJ-NP-TV-Z]$/;
const NAME_REGEX = /^[a-zA-ZÁÉÍÓÚáéíóúÑñ\s-]+$/;
const USERNAME_REGEX = /^[a-zA-Z\s-]+$/;

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  @Output() confirmed = new EventEmitter<void>();

  date = new Date();
  maxDateNgStruct = {
    year: this.date.getFullYear(),
    month: this.date.getMonth() + 1,
    day: this.date.getDate(),
  };

  createForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<CreateUserComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: string
  ) {
    this.createForm = this.formBuilder.group({
      firstname:['', [Validators.required,Validators.pattern(NAME_REGEX)]],
      lastname: ['',[Validators.required,Validators.pattern(NAME_REGEX)]],
      username: ['',[Validators.required,Validators.pattern(USERNAME_REGEX)]],
      dni: ['',[Validators.required,Validators.pattern(DNI_REGEX)]],
      phoneNumber: ['',[Validators.required,Validators.pattern(PHONE_REGEX)]],
      email: ['',[Validators.required,Validators.pattern(EMAIL_REGEX)]],
      birthdate: ['',[Validators.required]],
      password: ['12345'],
      rol:['',[Validators.required]]
    });
  }

  maxDate = new Date();
  minDate = new Date().setFullYear(new Date().getFullYear() - 70);

  ngOnInit(): void {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    if (this.createForm.valid) {
      const formattedBirthdate = moment(this.createForm.value.birthdate).format('YYYY-MM-DD');
      this.createForm.patchValue({birthdate: formattedBirthdate});
      if(this.createForm.value.rol == 'WORKER'){
        this.userService.createUserFromAdmin(this.createForm.value).subscribe(() => {
          Object.assign(this.createForm.value);
          this.dialogRef.close(this.createForm.value);
          this.confirmed.emit();
        })
      }else{
        this.userService.createUserFromAdmin(this.createForm.value).subscribe(() => {
        Object.assign(this.createForm.value);
        this.dialogRef.close(this.createForm.value);
        this.confirmed.emit();
      });
      }

    }
  }

}

