import { Injectable } from '@angular/core';

import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { BaseUrI } from '../share';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient : HttpClient) {}

  authentication(data): Observable<any>{
    let p = new HttpParams().set("email",data.email).set("password",data.password);
    return this.httpClient.get(BaseUrI+"/api/authenticate", 
                                {
                                  params : p, 
                                  responseType: 'text', 
                                  observe : 'response',
                                  withCredentials: true
                                });
  }

  logout() : Observable<any>{
    return this.httpClient.post(BaseUrI+"/logoutUser",null);
  }
  
}
