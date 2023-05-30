import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  username = '';
  password = '';

  type ='password';

  isHide= true;

  aceptar = false;

  constructor() { }

  ngOnInit(): void {

  }
  changeVisibility() {

    this.isHide = !this.isHide

    if (this.isHide) {
        this.type='password';
    } else {
        this.type='text';
    }
  }
}

