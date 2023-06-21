import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(
    private ngxtoast: ToastrService,
    private router: Router,

  ) { }

  canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    const rol = localStorage.getItem('rol');
    if (isLoggedIn && rol?.includes('WORKER')) {
      return true;
    } else {
      if (rol?.includes('USER')) {
        this.ngxtoast.info('Usted no tiene el permiso necesario para acceder', 'No tiene acceso')
        this.router.navigate(['index'])
      } else {
        this.router.navigate(['login'])
        this.ngxtoast.info('Usted no tiene el permiso necesario para acceder', 'No tiene acceso')
        return false;
      }
      return false;
    }
  }

}
