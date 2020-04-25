import { Routes } from "@angular/router";
import { LoginComponent } from './component/login/login.component';
import { SignupComponent } from './component/signup/signup.component';
import { SearchComponent } from './component/search/search.component';

    export const routes : Routes = [
        {path:"login", component : LoginComponent},
        {path:"signup", component : SignupComponent},
        {path:"recherche", component : SearchComponent},
        {path:"", redirectTo : "/recherche", pathMatch: 'full'},
    ]

