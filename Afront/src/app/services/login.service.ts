import { Injectable } from '@angular/core';

import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  BaseUrI: string = "http://localhost:8080"
  

  constructor(private httpClient : HttpClient) {
    
   }

   private storageSub= new Subject<String>();
  

  watchStorage(): Observable<any> {
    return this.storageSub.asObservable();
  }

  setItem(key: string, data: any) {
    localStorage.setItem(key, data);
    this.storageSub.next('setted');
  }

  removeItem(key) {
    localStorage.removeItem(key);
    this.storageSub.next('removed');
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
