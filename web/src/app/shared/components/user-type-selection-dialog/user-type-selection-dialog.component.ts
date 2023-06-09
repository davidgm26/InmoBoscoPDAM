import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserService } from 'src/app/services/user.service';
import { CreateUserComponent } from '../create-user/create-user.component';

@Component({
  selector: 'app-user-type-selection-dialog',
  templateUrl: './user-type-selection-dialog.component.html',
  styleUrls: ['./user-type-selection-dialog.component.css']
})
export class UserTypeSelectionDialogComponent implements OnInit {

  constructor(
    private userService: UserService,
    private dialog: MatDialog,



  ) { }

  ngOnInit(): void {
  }

  createUser(rol: string){
    this.dialog.open(CreateUserComponent,{
      data: rol
    });
  }

  createWorker(rol: string){
    this.dialog.open(CreateUserComponent,{
      data: rol
    });
  }

}
