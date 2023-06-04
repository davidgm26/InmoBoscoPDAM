import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { UtilsService } from '../services/utils.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private ngxtoast:ToastrService,
    private router: Router,
    private authService: AuthService,
    private utils:UtilsService,
  ){}

  canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.utils.logged){
      return true;
    }else{
      this.router.navigate(['login'])
      this.ngxtoast.info('Usted no tiene el permiso necesario para acceder','No tiene acceso')
    return false;
    }
  }


}
