import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SearchService } from '../httpServices/search.service';

@Injectable({
  providedIn: 'root'
})
export class SearchGuard implements CanActivate {

  constructor(private search : SearchService, private route : Router){}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
    ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      
    if(this.search.propStadiums){
      return true;
    } 
      this.route.navigateByUrl("/recherche")
      return false
  }
  
}
