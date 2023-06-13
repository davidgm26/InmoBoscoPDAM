import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/shared/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  doLogOut(){
    this.authService.doLogOut();
    this.router.navigate(['admin-login'])
    }

  goToUsers(){
    this.router.navigateByUrl("/admin/user")
  }

  goToProperties(){
    this.router.navigateByUrl("/admin/property")
  }

  }


