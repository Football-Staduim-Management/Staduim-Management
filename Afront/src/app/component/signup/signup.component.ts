import { Component, OnInit, Renderer2, ViewChild, ElementRef } from '@angular/core';
import { SignupService } from 'src/app/services/signup.service';
import {  Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { FormControl } from '@angular/forms';
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

  constructor(private signupService: SignupService, 
              private router : Router, 
              private userService : UserService,
              private renderer : Renderer2
              ) { }
 
  ngOnInit(): void {
    
  }

  signup(data){
    if(data.password!=this.password.value){
      this.renderer.setAttribute(this.repassword.nativeElement,"class","form-control is-invalid")
      return
    }
    
    this.signupService.signup(data).subscribe((res)=> {
      this.showError=false
      this.userService.saveCurrentUser(res,false)
      this.router.navigateByUrl("/login")
    },
    (error)=>{
      this.showError = true
      this.error = error.status === 409 ? "user exist" : error.message
      
    }
    )
  }
}
