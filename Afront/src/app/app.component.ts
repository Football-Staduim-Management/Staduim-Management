import { Component } from '@angular/core';
import { LoginService } from './services/login.service';
import { element } from 'protractor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  authentication=false;
  title = 'Afront';
  isAuth : String;
  constructor(private loginService: LoginService, private router: Router){
    
    this.isAuth=localStorage.getItem("isAuth")===null? undefined : localStorage.getItem("isAuth") 
    loginService.watchStorage().subscribe((elem)=>{
      this.isAuth=(elem==="removed") ? undefined : elem
      this.authentication=false
    })
  }
  logout(){
    this.authentication=true
    this.loginService.logout().subscribe((resp)=>{
      console.log(resp)
      this.loginService.removeItem("isAuth");
      this.router.navigateByUrl("/login")
    })
  }
}
