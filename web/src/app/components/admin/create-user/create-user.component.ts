import { Component, OnInit , Inject, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import * as moment from 'moment';
import { UserService } from 'src/app/shared/services/user.service';


const EMAIL_REGEX = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const PHONE_REGEX = /^(?:(?:\+|00)34)?[6-9]\d{8}$/;
const DNI_REGEX = /^\d{8}[A-HJ-NP-TV-Z]$/;

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

  editForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<CreateUserComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: string
  ) {
    this.editForm = this.formBuilder.group({
      firstname:['', [Validators.required]],
      lastname: ['',[Validators.required]],
      username: ['',[Validators.required]],
      dni: ['',[Validators.required,Validators.pattern(DNI_REGEX)]],
      phoneNumber: ['',[Validators.required,Validators.pattern(PHONE_REGEX)]],
      email: ['',[Validators.required,Validators.pattern(EMAIL_REGEX)]],
      birthdate: ['',[Validators.required]],
      password: ['12345'],
      rol:['',[Validators.required]]
    });
  }

  ngOnInit(): void {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    if (this.editForm.valid) {
      const formattedBirthdate = moment.utc(this.editForm.value.birthdate).format('YYYY-MM-DD');
      this.editForm.patchValue({birthdate: formattedBirthdate});
      if(this.editForm.value.rol == 'WORKER'){
        this.userService.createWorker(this.editForm.value).subscribe(() => {
          Object.assign(this.editForm.value);
          this.dialogRef.close(this.editForm.value);
          this.confirmed.emit();
        })
      }else{
        this.userService.createUser(this.editForm.value).subscribe(() => {
        Object.assign(this.editForm.value);
        this.dialogRef.close(this.editForm.value);
        this.confirmed.emit();
      });
      }

    }
  }

}

