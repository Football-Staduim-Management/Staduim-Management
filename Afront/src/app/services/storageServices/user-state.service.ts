import { Injectable } from '@angular/core';
import { GenericStorage } from './genericStorage';
import { User } from 'src/app/Model/user';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserStateService extends GenericStorage {
  currentUser : User
  auth = new Subject<Boolean>();
  constructor() {
    super();
    this.currentUser = this.getCurrentUser();
    this.watchStorage().subscribe((resp)=>{
      switch (resp){
        case "SET" : 
              this.currentUser = this.getCurrentUser();
              this.auth.next(this.currentUser.isAuth)
              break ;
        case "DELETE" : 
              this.currentUser.isAuth = false
              this.setCurrentUser(this.currentUser)
              this.auth.next(this.currentUser.isAuth)
      }
    })
  }

  getCurrentUser(){
    let obj = this.getState("currentUser");
    return  obj === null ? new User() : JSON.parse(obj)
  }

  isAuth():Observable<Boolean>{
    return this.auth.asObservable()
  }
  deleteCurrentUser(key){
    this.storageSub.next('DELETE');
  }
  setCurrentUser(data){
    this.setState("currentUser",JSON.stringify(data))
  }  

  
}
