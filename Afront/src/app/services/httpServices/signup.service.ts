import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseUrI } from '../share';


@Injectable({
    providedIn: 'root'
  })
  export class SignupService{

    

    constructor(private httpClient:HttpClient){}

    signup(data) : Observable<any>{
        return this.httpClient.put(BaseUrI+"/user/put",data);
    }
  }