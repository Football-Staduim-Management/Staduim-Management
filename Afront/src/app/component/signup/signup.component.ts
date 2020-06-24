import { Component, OnInit, Renderer2, ViewChild, ElementRef } from '@angular/core';
import { SignupService } from 'src/app/services/httpServices/signup.service';
import {  Router } from '@angular/router';
import { FormControl } from '@angular/forms';
import { UserService } from 'src/app/services/httpServices/user.service';
import { UserStateService } from 'src/app/services/storageServices/user-state.service';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  error:string;
  showError:boolean
  @ViewChild("repassword") repassword : ElementRef;
  password = new FormControl('');
  signUpLoading : boolean = false;

  constructor(private signupService: SignupService, 
              private router : Router, 
              private userService : UserService,
              private renderer : Renderer2,
              private userState : UserStateService
              ) { }
 
  ngOnInit(): void {
    
  }

  signup(data){
    if(data.password!=this.password.value){
      this.renderer.setAttribute(this.repassword.nativeElement,"class","form-control is-invalid")
      return
    }
    this.signUpLoading = true;
    this.signupService.signup(data).subscribe((res)=> {
      this.signUpLoading = false;
      this.showError=false
      this.userState.setCurrentUser(res)
      this.router.navigateByUrl("/login")
    },
    (error)=>{
      this.signUpLoading = false;
      this.showError = true
      this.error = error.status === 409 ? "user exist" : error.message
      
    }
    )
  }
}
