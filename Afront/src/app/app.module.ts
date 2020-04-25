import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { UserService } from './services/user.service';
import { LoginService } from './services/login.service';
import { StoreModule } from '@ngrx/store';
import { reducer } from './services/reduxService/actions/reducers/login.reducers';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { LoginInterceptorService } from './services/interceptors/login-interceptor.services';
import { RouterModule } from '@angular/router';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import {NgbDatepickerModule} from '@ng-bootstrap/ng-bootstrap'
import {routes} from "./app.router";
import { SignupComponent } from './component/signup/signup.component';
import { SearchComponent } from './component/search/search.component'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AgmCoreModule } from '@agm/core';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MDBBootstrapModule.forRoot(),
    FormsModule,
    HttpClientModule,
    StoreModule.forRoot({currentUser: reducer}),
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: environment.production }),
    RouterModule.forRoot(routes),
    NgxMaterialTimepickerModule,
    BrowserAnimationsModule,
    NgbDatepickerModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCJQXd7xxQSc4jM2URmmHV2XGwWBFIcsRg',
      libraries: ['places'] 
    })
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
