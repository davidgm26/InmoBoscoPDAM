import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginDto } from 'src/app/interfaces/dtos/loginDto';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css'],
})
export class AuthComponent implements OnInit {

  hidePassword: boolean = true;

  loginForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });


  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private ngxtoast: ToastrService,
    private router: Router

  ) {}

  ngOnInit() {}

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }



  onSubmit() {
    const loginRequest: LoginDto = {
      username: this.loginForm.get('username')?.value!,
      password: this.loginForm.get('password')?.value!
    }
    this.authService.doLogin(loginRequest).subscribe(resp =>{
        if(resp.rol  == 'USER'){
          localStorage.setItem('token',resp.token);
          localStorage.setItem('refresh_token', resp.refreshToken);
          this.router.navigate(['home'])
        }else{
          this.ngxtoast.error('No tiene autorización para iniciar sesión','Error')
        }

    },(error: any) =>{
      this.ngxtoast.error('Este usuario no se encuentra registrado','Error')
    }
    )
  }

}

