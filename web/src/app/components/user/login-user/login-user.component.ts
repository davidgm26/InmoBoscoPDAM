import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginDto } from 'src/app/interfaces/dtos/loginDto';
import { AuthService } from 'src/app/shared/services/auth.service';
import { UtilsService } from 'src/app/shared/services/utils.service';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {

  hidePassword: boolean = true;

  loginForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });


  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private ngxtoast: ToastrService,
    private router: Router,
    private utils:UtilsService

  ) {}

  ngOnInit(): void {
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit() {
    const loginRequest: LoginDto = {
      username: this.loginForm.get('username')?.value!,
      password: this.loginForm.get('password')?.value!
    }
    this.authService.doLogin(loginRequest).subscribe(resp =>{
      //Mirar con Miguel el porque esto no funciona correctamente
      if(resp.enabled){
        localStorage.setItem('rol', resp.rol)
        if(resp.rol.includes("USER")){
          localStorage.setItem('token',resp.token);
          localStorage.setItem('refresh_token', resp.refreshToken);
          localStorage.setItem('isLoggedIn', 'true');
          this.router.navigate(['index'])
        }else{
          this.ngxtoast.error('No tiene autorización para iniciar sesión','Error')
        }
      }else{
        this.ngxtoast.info('Este usuario se encuentra desactivado', 'Cuenta desactivada')
      }
    },(error: any) =>{
      this.ngxtoast.error(error.error.message,'Error')
    }
    )
  }

}
