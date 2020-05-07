import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from '../../Model/user';
import { Store } from '@ngrx/store';
import { auth } from '../reduxService/actions/login.action';
import { Observable } from 'rxjs';
import {BaseUrI} from "../share"
import { UserStateService } from '../storageServices/user-state.service';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  
  

  constructor(private httpClient : HttpClient, private userState : UserStateService) {
    
   }

  loadUser(email : string): Observable<any>{
    let params = new HttpParams().set("email",email)
    return this.httpClient.get(BaseUrI+"/user/get");
  }
  
}
