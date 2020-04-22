import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  BaseUrI: string = "http://localhost:8080"
  

  constructor(private httpClient : HttpClient) {
    
   }

  authentication(data): Observable<any>{
    let p = new HttpParams().set("email",data.email).set("password",data.password);
    return this.httpClient.get(this.BaseUrI+"/api/authenticate", 
                                {
                                  params : p, 
                                  responseType: 'text', 
                                  observe : 'response',
                                  withCredentials: true
                                });
  }
  
}
