import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Property, PropertyResponse } from 'src/app/interfaces/models/propertyResponse.interface';
import { PropertyRequest } from 'src/app/interfaces/dtos/propertyDto';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  constructor(
    private http: HttpClient
  ) { }

    getProperties(page: number,pageSize: number): Observable<PropertyResponse>{
      return this.http.get<PropertyResponse>(`${environment.API_Base_Url}/property/?page=${page}&size=${pageSize}`);
    }

    editProperty(editDto: PropertyRequest,id: number): Observable<PropertyResponse>{
      return this.http.put<PropertyResponse>(`${environment.API_Base_Url}/property/${id}`,editDto);
    }

    createProperty(createDto: PropertyRequest){
      return this.http.post<PropertyResponse>(`${environment.API_Base_Url}/property/`,createDto);
    }

    deleteProperty(id: number){
      return this.http.delete(`${environment.API_Base_Url}/property/${id}`)
    }
}
