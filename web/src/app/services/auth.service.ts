import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginDto } from '../interfaces/dtos/loginDto';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { LoginResponse } from '../interfaces/models/loginResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private htpp:HttpClient,

  ) {}

  doLogin(loginDto: LoginDto): Observable<LoginResponse>{

    return this.htpp.post<LoginResponse>(environment.API_Base_Url,loginDto)

  }
}
