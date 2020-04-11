import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { User } from '../Model/user';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  BaseUrI: string = "http://localhost:8080"

  constructor(private httpClient : HttpClient ) { }

  authentication(data): Observable<any>{
    let p = new HttpParams().set("email",data.email).set("password",data.password);
    return this.httpClient.get(this.BaseUrI+"/api/authenticate",{params : p});
  }
  setCurrentUser(user : User){
    localStorage.setItem("currentUser", JSON.stringify(user))
  }
}
