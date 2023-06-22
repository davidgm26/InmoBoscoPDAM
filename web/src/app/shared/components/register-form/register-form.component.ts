import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

const EMAIL_REGEX = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const PHONE_REGEX = /^(?:(?:\+|00)34)?[6-9]\d{8}$/;
const DNI_REGEX = /^\d{8}[A-HJ-NP-TV-Z]$/;
const NAME_REGEX = /^[a-zA-ZÁÉÍÓÚáéíóúÑñ\s-]+$/;
const USERNAME_REGEX = /^[a-zA-Z\s-]+$/;


@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
})
export class RegisterFormComponent implements OnInit {

  registerForm: FormGroup;
  avatar!: File ;
  hidePassword2: boolean = true;
  hidePassword: boolean = true;
  date = new Date();

  maxDateNgStruct = {
    year: this.date.getFullYear(),
    month: this.date.getMonth() + 1,
    day: this.date.getDate(),
  };

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private ngxToast: ToastrService,
    private router: Router,
  ) {
    this.registerForm = this.formBuilder.group({
      firstname: ['', [Validators.required,Validators.pattern(NAME_REGEX)]],
      lastname: ['', [Validators.required,Validators.pattern(NAME_REGEX)]],
      username: ['',  Validators.required,Validators.pattern(USERNAME_REGEX)],
      dni: ['', [Validators.required, Validators.pattern(DNI_REGEX)]],
      phoneNumber: ['', [Validators.required, Validators.pattern(PHONE_REGEX)]],
      email: ['', [Validators.required, Validators.pattern(EMAIL_REGEX)]],
      birthdate: ['', [Validators.required]],
      password: ['', [Validators.required]],
      passwordRepeat: ['', [Validators.required]],
    });
  }

  maxDate = new Date();
  minDate = new Date().setFullYear(new Date().getFullYear() - 70);

  ngOnInit() {}

  onSubmit() {
    if(this.registerForm.value.password != this.registerForm.value.passwordRepeat){
      this.ngxToast.error('Las contraseñas deben de ser iguales.')
    }
    if(this.avatar != null){
      if(this.registerForm.valid){
        const formattedBirthdate = moment(this.registerForm.value.birthdate).format('YYYY-MM-DD');
        this.registerForm.patchValue({birthdate: formattedBirthdate});
        this.userService.createUser(this.registerForm.value,this.avatar).subscribe(
          res =>  {},(error =>{
            this.ngxToast.error('No se pudo crear al usuario')
          }
            )
        );
      }else{
        if(this.registerForm.valid){
          const formattedBirthdate = moment(this.registerForm.value.birthdate).format('YYYY-MM-DD');
          this.registerForm.patchValue({birthdate: formattedBirthdate});
          this.userService.createUser(this.registerForm.value).subscribe(
            res =>  {},(error =>{
              this.ngxToast.error('No se pudo crear al usuario')
            }
              )
          );
        }
      }
      this.router.navigate(['index'])
    }
  }

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.avatar = event.target.files[0];
    }
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  togglePasswordVisibility2() {
    this.hidePassword2 = !this.hidePassword2;
  }


}
