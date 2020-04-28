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
  title = 'Afront';
  isAuth : String;
  constructor(private logingService: LoginService, private router: Router){
    this.isAuth=localStorage.getItem("isAuth")===null? undefined : localStorage.getItem("isAuth") 
    logingService.watchStorage().subscribe((elem)=>{
      this.isAuth=(elem==="removed") ? undefined : elem
    })
  }
  logout(){
    this.logingService.removeItem("isAuth");
    this.router.navigateByUrl("/login")
  }
}
