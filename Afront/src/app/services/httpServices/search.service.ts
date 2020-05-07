import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { stadium } from '../../Model/Stadium';
import { GeoPoint, SearchInfos } from '../../Model/SearchInfos';
import { BaseUrI } from '../share';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  propStadiums: stadium[]
  centre : GeoPoint;
  date : String
  time : String
  address : String
  searchInf: SearchInfos = new SearchInfos();
  constructor(private httpClient: HttpClient) { }

  searchStadiums(searchInfo) : Observable<any>{
    return this.httpClient.post(BaseUrI+"/stadium/search", searchInfo);
  }


}
