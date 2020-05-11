import { Component, OnInit, ViewChild, ElementRef, Renderer2, OnChanges, AfterViewInit } from '@angular/core';
import { LoginService } from 'src/app/services/httpServices/login.service';
import { User } from 'src/app/Model/user';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/httpServices/user.service';
import { UserStateService } from 'src/app/services/storageServices/user-state.service';

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
    private rendere: Renderer2,
    private userService: UserService,
    private router: Router,
    private userState: UserStateService
  ) {

  }
  ngAfterViewInit(): void {
    this.rendere.setAttribute(this.email.nativeElement, "placeholder",
      this.userState.currentUser.email === "" ? "email" : this.userState.currentUser.email)

    this.rendere.setValue(this.email.nativeElement,
      this.userState.currentUser.email === "" ? "email" : this.userState.currentUser.email)
  }


  ngOnInit(): void {

  }


  login(data) {
    let user = new User()
    user.email = data.email
    user.password = data.password
    this.userState.currentUser.password=user.password
    this.userState.currentUser.email=user.email
    this.loginService.authentication(data).subscribe((res: HttpResponse<any>) => {
      this.authError = false
      

      this.loadUser(this.userState.currentUser.email)
    }, (error: HttpErrorResponse) => {
      this.authError = true
      this.authErrorMessage = error.status === 401 ? "verifier votre email ou mot de passe" : error.message
    })
  }

  loadUser(email: string) {
    this.userService.loadUser(email).subscribe((res: any) => {
      let user: User = new User();
      user.email = res.email;
      user.id = res.id;
      user.name = res.name
      user.isAdmin = res.isAdmin;
      user.password = this.userState.currentUser.password
      user.isAuth = true
      this.userState.setCurrentUser( user);
      this.router.navigateByUrl("/recherche")
    }, (error: HttpErrorResponse) => {
      this.authError = true
      this.authErrorMessage = error.status === 401 ? "verifier votre email ou mot de passe" : error.message
    })
  }


}
