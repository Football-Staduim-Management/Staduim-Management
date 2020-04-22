import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from '../Model/user';
import { Store } from '@ngrx/store';
import { auth } from './reduxService/actions/login.action';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser : User
  BaseUrI: string = "http://localhost:8080"

  constructor(private httpClient : HttpClient,  private store : Store<{currentUser}> ) {
    this.store.select("currentUser").subscribe((state)=>{
      this.currentUser=state;
    })
   }

  saveCurrentUser(user : User, authb : boolean){
    
    this.store.dispatch(auth({authb, user}))
  }

  loadCurrentUser(): Observable<any>{
    let params = new HttpParams().set("email",this.currentUser.email)
    return this.httpClient.get(this.BaseUrI+"/user/get");
  }

  

  
}
