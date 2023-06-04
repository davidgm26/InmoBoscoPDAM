import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginDto } from '../interfaces/dtos/loginDto';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { LoginResponse } from '../interfaces/models/loginResponse.interface';
import { UtilsService } from '../shared/services/utils.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private htpp:HttpClient,
    private utils: UtilsService,

  ) {}

  doLogin(loginDto: LoginDto): Observable<LoginResponse>{
    return this.htpp.post<LoginResponse>(`${environment.API_Base_Url}/auth/login`,loginDto);
  }

  doLogOut(){
    localStorage.removeItem('token');
    localStorage.removeItem('refresh_token');
    this.utils.logged =  false;
  }

}
