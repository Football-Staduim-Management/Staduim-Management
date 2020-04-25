import { Component, OnInit, ViewChild, ElementRef, Renderer2, OnChanges, AfterViewInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { User } from 'src/app/Model/user';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Store, select } from '@ngrx/store';
import { login } from 'src/app/services/reduxService/actions/login.action';
import { ArrayType } from '@angular/compiler';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit, AfterViewInit {

  authError: boolean = false
  authErrorMessage: string
  @ViewChild("email") email: ElementRef
  @ViewChild("password") password: ElementRef
  
  
  constructor(
    private loginService: LoginService,
    private store: Store<{ currentUser }>,
    private rendere: Renderer2,
    private userService: UserService
    ) { 
      
        }
  ngAfterViewInit(): void {
      this.rendere.setAttribute(this.email.nativeElement,"placeholder",
      this.userService.currentUser.email===""?  "email":this.userService.currentUser.email)
      
  }


  ngOnInit(): void {
    
  }


  login(data) {
    let email = data.email
    let password = data.password
    this.store.dispatch(login({ email, password }));
    this.loginService.authentication(data).subscribe((res: HttpResponse<any>) => {
      this.authError = false
      let s = res.headers.get("sessionID")
      localStorage.setItem("sessionID",s)
      this.loadUser()
    }, (error: HttpErrorResponse) => {
      this.authError = true
      this.authErrorMessage = error.status === 401 ? "verifier votre email ou mot de passe" : error.message
    })
  }

  loadUser() {
    this.userService.loadCurrentUser().subscribe((res: any) => {
      let user: User = new User();
      user.email = res.email;
      user.id = res.id;
      user.name = res.name
      user.isAdmin = res.isAdmin;
      user.password = res.password;
      this.userService.saveCurrentUser(user, true);
    }, (error: HttpErrorResponse) => {
      this.authError = true
      this.authErrorMessage = error.status === 401 ? "verifier votre email ou mot de passe" : error.message
    })
  }


}
