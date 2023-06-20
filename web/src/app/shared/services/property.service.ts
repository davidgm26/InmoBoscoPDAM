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

    filterProperties( page: number, pageSize: number,city?: String, propertyType?: String): Observable<PropertyResponse>{
      let url = `${environment.API_Base_Url}/property/filters/?`;
      console.log(pageSize)
      if (propertyType) {
        url += `propertyType=${propertyType}&`;
      }

      if (city) {
        url += `cityName=${city}&`;
      }

      // Añade los parámetros de paginación a la URL
      url += `page=${page}&pageSize=${pageSize}`;

      return this.http.get<PropertyResponse>(url);
    }

    getPropertyDetail(id: number):Observable<Property>{
      return this.http.get<Property>(`${environment.API_Base_Url}/property/${id}`);
    }

    removeFromFavourite(id: number):Observable<PropertyResponse>{
    return this.http.delete<PropertyResponse>(`${environment.API_Base_Url}/user/favourites/${id}/`);
    }




}
