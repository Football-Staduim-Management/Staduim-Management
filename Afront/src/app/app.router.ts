import { Routes } from "@angular/router";
import { LoginComponent } from './component/login/login.component';
import { SignupComponent } from './component/signup/signup.component';
import { SearchComponent } from './component/search/search.component';
import { PropositionsComponent } from './component/propositions/propositions.component';

    export const routes : Routes = [
        {path:"login", component : LoginComponent},
        {path:"signup", component : SignupComponent},
        {path:"recherche", component : SearchComponent},
        {path:"propositions", component : PropositionsComponent},
        {path:"", redirectTo : "/login", pathMatch: 'full'},
    ]

