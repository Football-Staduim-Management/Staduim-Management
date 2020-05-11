import { Routes } from "@angular/router";
import { LoginComponent } from './component/login/login.component';
import { SignupComponent } from './component/signup/signup.component';
import { SearchComponent } from './component/search/search.component';
import { PropositionsComponent } from './component/propositions/propositions.component';
import { AuthGuard } from './services/gaurds/auth.guard';
import { SearchGuard } from './services/gaurds/search.guard';

    export const routes : Routes = [
        {path:"login", component : LoginComponent},
        {path:"signup", component : SignupComponent},
        {path:"recherche", component : SearchComponent, canActivate : [AuthGuard] },
        {path:"propositions", component : PropositionsComponent, canActivate : [SearchGuard]},
        {path:"", redirectTo : "/recherche", pathMatch: 'full'},
    ]

