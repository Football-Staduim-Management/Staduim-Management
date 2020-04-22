import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpHeaders } from '@angular/common/http';
import {tap} from 'rxjs/operators'
import { Observable } from 'rxjs';

export class LoginInterceptorService implements HttpInterceptor{
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const headers = new HttpHeaders({ "sessionID" :  localStorage.getItem("sessionID")});
        const cloneReq = req.clone({
            withCredentials: true,
            //headers
          });
        return next.handle(cloneReq);
    }
    
}