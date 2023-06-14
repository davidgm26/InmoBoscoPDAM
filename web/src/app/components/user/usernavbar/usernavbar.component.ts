import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/shared/services/auth.service';

@Component({
  selector: 'app-usernavbar',
  templateUrl: './usernavbar.component.html',
  styleUrls: ['./usernavbar.component.css']
})
export class UsernavbarComponent implements OnInit {

  userLogged: boolean = false

  constructor(
    private authService: AuthService,
    private router: Router
    ) { }

  ngOnInit(): void {
    debugger
    if(localStorage.getItem('token') != null){
      this.userLogged = true;
    }


  }

  doLogOut(){
    this.authService.doLogOut();
    this.userLogged = false; // Actualiza el estado de inicio de sesión en el componente
    this.router.navigate([''])
  }

  goToProfile(){
    this.router.navigateByUrl("/user/me")
  }

  goToProperties(){
    this.router.navigateByUrl("")
  }
  doLogin(){
    this.router.navigate(['login'])
  }

  }
