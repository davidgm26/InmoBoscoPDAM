import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateUserRequest } from 'src/app/interfaces/dtos/createUserRequest';
import { CreateUserResponse } from 'src/app/interfaces/dtos/createUserResponse';
import { Property } from 'src/app/interfaces/models/propertyResponse.interface';
import { User, UserResponse } from 'src/app/interfaces/models/userResponse.interface';

import { environment } from 'src/environments/environment';


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


  createUser(createUserRequest :CreateUserRequest,file?: File):Observable<CreateUserResponse> {

    const formData = new FormData();

    const body = createUserRequest;

    const blobBody = new Blob([JSON.stringify(body)], {
      type: "application/vnd.api+json",
    });

    if (file) {
      formData.append("file",file);
    }
    formData.append("user" ,blobBody)


    return this.http.post<CreateUserResponse>(`${environment.API_Base_Url}/auth/register/user`,formData);
  }

  createUserFromAdmin(createUserRequest: CreateUserRequest){
    return this.http.post<CreateUserResponse>(`${environment.API_Base_Url}/admin/users/`,createUserRequest);
  }

  markPropertyAsFavourite(propertyId :number){
    return this.http.post<Property>(`${environment.API_Base_Url}/user/favourites/add/${propertyId}/`,null);
  }
  deletePropertyFromFavourite(propertyId :number){
    return this.http.delete<Property>(`${environment.API_Base_Url}/user/favourites/add/${propertyId}/`);
  }






}
