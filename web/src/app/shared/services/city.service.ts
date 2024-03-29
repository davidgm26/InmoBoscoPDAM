import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { CityResponse } from 'src/app/interfaces/dtos/cityResponse';

@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(
    private http:HttpClient
    ) { }

    getAllCities():Observable<CityResponse []>{
      return this.http.get<CityResponse [] >(`${environment.API_Base_Url}/city/`);
    }

}
