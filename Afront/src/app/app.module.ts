import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { UserService } from './services/user.service';
import { LoginService } from './services/login.service';
import { StoreModule } from '@ngrx/store';
import { reducer } from './services/reduxService/actions/reducers/login.reducers';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { LoginInterceptorService } from './services/interceptors/login-interceptor.services';
import { RouterModule } from '@angular/router';

import {routes} from "./app.router";
import { SignupComponent } from './component/signup/signup.component';
import { MdpValidatorDirective } from './Directives/mdp-validator.directive'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    MdpValidatorDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MDBBootstrapModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    StoreModule.forRoot({currentUser: reducer}),
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: environment.production }),
    RouterModule.forRoot(routes)
  ],
  providers: [LoginService, UserService,
  {
    provide : HTTP_INTERCEPTORS,
    useClass : LoginInterceptorService,
    multi : true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
