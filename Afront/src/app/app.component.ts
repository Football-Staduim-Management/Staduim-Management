import { Component } from '@angular/core';
import { LoginService } from './services/httpServices/login.service';
import { Router } from '@angular/router';
import { UserStateService } from './services/storageServices/user-state.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Afront';
  isAuth : Boolean=false;
  
  constructor(
     private userState : UserStateService,
     private router: Router,
     private loginService : LoginService
     ){
    this.isAuth=userState.currentUser.isAuth
    userState.isAuth().subscribe((resp)=>{
      this.isAuth = resp
    })
  }

  logout(){
    this.loginService.logout().subscribe((resp)=>{    
      this.userState.deleteCurrentUser("currentUser");
      this.router.navigateByUrl("/login")
    },(error)=>{
      this.isAuth=false
      this.userState.deleteCurrentUser("currentUser");
      this.router.navigateByUrl("/login")
    })
  }

  goToRecherche(){
    this.router.navigateByUrl("/recherche")
  }
}
