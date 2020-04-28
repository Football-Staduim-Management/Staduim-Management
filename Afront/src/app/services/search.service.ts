import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { stadium } from '../Model/Stadium';
import { GeoPoint } from '../Model/SearchInfos';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  propStadiums: stadium[]
  centre : GeoPoint;
  date : String
  time : String
  address : String
  BaseUrI: string = "http://localhost:8080"
  constructor(private httpClient: HttpClient) { }

  searchStadiums(searchInfo) : Observable<any>{
    return this.httpClient.post(this.BaseUrI+"/stadium/search", searchInfo);
  }


}
