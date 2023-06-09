import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User, UserResponse } from '../interfaces/models/userResponse.interface';
import { environment } from 'src/environments/environment';
import { CreateUserRequest } from '../interfaces/dtos/createUserRequest';
import { CreateUserResponse } from '../interfaces/dtos/createUserResponse';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
  ) { }

  getAllUsers(page: number,pageSize: number):Observable<UserResponse>{
    return this.http.get<UserResponse>(`${environment.API_Base_Url}/admin/?page=${page}&size=${pageSize}`);
  }

  changeUserStatus(id :string): Observable<User>{
    return this.http.put<User>(`${environment.API_Base_Url}/admin/ban/${id}`,null);
  }

  deleteUSer(id: String){
    return this.http.delete(`${environment.API_Base_Url}/admin/delete/${id}`);
  }

  editUserInfoFromAdmin(id: String,userDto :CreateUserRequest):Observable<User>{
    return this.http.put<User>(`${environment.API_Base_Url}/admin/profile/${id}`,userDto);
  }

  createUser(createUserRequest :CreateUserRequest):Observable<CreateUserResponse> {
    return this.http.post<CreateUserResponse>(`${environment.API_Base_Url}/auth/register/user`,createUserRequest);
  }

  createWorker(createUserRequest :CreateUserRequest){
    return this.http.post<CreateUserResponse>(`${environment.API_Base_Url}/auth/register/worker`,createUserRequest);
  }








}
