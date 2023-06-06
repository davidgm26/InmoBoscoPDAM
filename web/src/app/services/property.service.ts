import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PropertyResponse } from '../interfaces/models/propertyResponse.interface';
import { environment } from 'src/environments/environment';
import { PropertyRequest } from '../interfaces/dtos/propertyDto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  constructor(
    private http: HttpClient
  ) { }

    getProperties(page: number,pageSize: number){
      return this.http.get<PropertyResponse>(`${environment.API_Base_Url}/property/?page=${page}&size=${pageSize}`);
    }

    editProperty(editDto: PropertyRequest,id: number): Observable<PropertyResponse>{
      debugger;
      console.log(editDto);
      return this.http.put<PropertyResponse>(`${environment.API_Base_Url}/property/${id}`,editDto);
    }

    createProperty(createDto: PropertyRequest){
      return this.http.post<PropertyResponse>(`${environment.API_Base_Url}/property/`,createDto);
    }

    deleteProperty(id: number){
      return this.http.delete(`${environment.API_Base_Url}/property/${id}`)
    }



}
