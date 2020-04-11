import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/app/Model/user';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authError : boolean = false
  authErrorMessage : string
  constructor(private loginService : LoginService) { }

  ngOnInit(): void {
  }

  login(data:NgForm){
    this.loginService.authentication(data).subscribe(res=>{
      this.authError=false
      let user : User = new User();
      user.email= res.email;
      user.id = res.id;
      user.name=res.name
      user.isAdmin= res.isAdmin;
      user.password=res.password;
      this.loginService.setCurrentUser(user);
    }, (error : HttpErrorResponse) =>{
      this.authError=true
       this.authErrorMessage = error.status === 401 ? "verifier votre email ou mot de passe" : error.message
    })
  }

}
